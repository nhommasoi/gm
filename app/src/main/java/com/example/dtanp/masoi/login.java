package com.example.dtanp.masoi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.dtanp.masoi.control.StaticFirebase;
import com.example.dtanp.masoi.control.StaticUser;
import com.example.dtanp.masoi.model.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends Activity implements View.OnClickListener {


    public static final int RC_SIGN_IN = 123;
    public static final String TAG = "abc";
    private GoogleSignInClient mGoogleSignInClient;
    private EditText edtuser, edtpassworld;
    private Button btnlogin, btnsignup;
    ImageButton btngg;
    private FirebaseDatabase database;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtuser = findViewById(R.id.edtuser);
        edtpassworld = findViewById(R.id.edtpass);
        btnlogin = findViewById(R.id.btnlogin);
        btnsignup = findViewById(R.id.btnsignup);
        btnlogin.setOnClickListener(this);
        btngg = findViewById(R.id.btngg);
        btngg.setOnClickListener(this);
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        database = StaticFirebase.database;
        auth = StaticFirebase.auth;
        findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
        btnsignup = findViewById(R.id.btnsignup);
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startmhsignup();
            }
        });

    }

    public void startmhsignup()
    {
        Intent intent = new Intent(this,signup.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnlogin) {

            final DatabaseReference reference = database.getReference();
            if (edtuser.getText().toString() != "" && edtpassworld.getText().toString() != "") {
                String user = edtuser.getText().toString();
                String pass = edtpassworld.getText().toString();
                auth.signInWithEmailAndPassword(user, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            //System.out.println(task.getResult().getUser().getUid().toString());
                            reference.child("User").child(task.getResult().getUser().getUid().toString()).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    User us = dataSnapshot.getValue(User.class);
                                    StaticUser.user = us;
                                    startmh();
                                    finish();


                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        } else {
                            System.out.println("Loi Dang Nhap");
                        }
                    }
                });
            }
        } else if (id == R.id.btngg) {
            signIn();
        }
    }

    public void startmh() {
        Intent intent = new Intent(this, home.class);
        startActivity(intent);
    }

    private void signIn() {
        System.out.println("toi 3");
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 1);
        System.out.println("toi 4");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        System.out.println("toi 5");

        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            // Google Sign In was successful, authenticate with Firebase
            System.out.println("tao toi ne");
            GoogleSignInAccount account = task.getResult(ApiException.class);
            System.out.println("toi 1");
            firebaseAuthWithGoogle(account);
            System.out.println("toi 2");
        } catch (ApiException e) {
            // Google Sign In failed, update UI appropriately
            Log.w(TAG, "Google sign in failed", e);
            // ...
            System.out.println("toi tao");
        }

    }


    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        System.out.println("toi ne");
        System.out.println(acct.getId());
        System.out.println(acct.getDisplayName());
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = auth.getCurrentUser();
                            System.out.println(task.getResult().getUser().getEmail());
                            Toast.makeText(login.this, "thanh cong", Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());

                            Toast.makeText(login.this, "that bai", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }




}
