package com.example.dtanp.masoi;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dtanp.masoi.adapter.CustomAdapterChat;
import com.example.dtanp.masoi.control.StaticFirebase;
import com.example.dtanp.masoi.control.StaticUser;
import com.example.dtanp.masoi.model.Chat;
import com.example.dtanp.masoi.model.Phong;
import com.example.dtanp.masoi.model.TextViewAdd;
import com.example.dtanp.masoi.model.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Ban extends Activity implements View.OnClickListener{

    ListView listchat;

    CustomAdapterChat adapterChat;
    Button btnss,btnsend;
    ImageView imgnhanvat;
    LinearLayout linearLayout;
    List<TextViewAdd> textViewAddList;
    TextView txtthoigian;
    List<Chat> list;
    FirebaseDatabase database;
    EditText edtchat;
    DatabaseReference reference;
    TextView user1,user2,user3,user4,user5,user6,user7,user8,user9;
    boolean buser1=false,buser2=false,buser3=false,buser4=false,buser5=false,buser6=false,buser7=false,buser8=false,buser9=false;
    ImageButton btnuser1,btnuser2,btnuser3,btnuser4,btnuser5,btnuser6,btnuser7,btnuser8,btnuser9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban);
        database = StaticFirebase.database;
//        DatabaseReference reference = database.getReference();
//        reference.child("Chat").child("cKvRXGjGbOX5e6t7fMM7YKRAoyp2").push().setValue(new Chat(" ","tao ne"));
        textViewAddList = new ArrayList<>();

        btnss = findViewById(R.id.btnss);
        imgnhanvat = findViewById(R.id.imgnhanvat);
        imgnhanvat.setImageAlpha(0);
        txtthoigian = findViewById(R.id.txtthoigian);
        txtthoigian.setAlpha(1);
        listchat = findViewById(R.id.listchat);
        list =new ArrayList<>();
        adapterChat = new CustomAdapterChat(this,R.layout.custom_chat,list);
        listchat.setAdapter(adapterChat);
        btnsend = findViewById(R.id.btnsend);
        edtchat = findViewById(R.id.edtchat);

        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Chat chat = new Chat();
                if(edtchat.getText().toString()!="")
                {
                    chat.setUsername(StaticUser.user.getUser());
                    chat.setMesage(edtchat.getText().toString());
                    send(chat);
                }
            }
        });





        capnhatlistchat();
        anhxa();
        findViewById(R.id.lnrchat).setAlpha(1);
        capnhatlistuser();
        //laylistuser();






    }



    public void capnhatlistuser()
    {
        reference = database.getReference();
        reference.child("Room").child(StaticUser.userHost.getId()).child("ListUser").child(StaticUser.user.getId()).setValue(StaticUser.user);
    }

    public void capnhatlistchat()
    {
        reference = database.getReference();
        reference.child("Chat").child(StaticUser.userHost.getId()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Chat chat = dataSnapshot.getValue(Chat.class);
                if (!chat.getMesage().equals(" ")) {
                    list.add(chat);
                    adapterChat.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void send(Chat chat)
    {
        reference = database.getReference();
        reference.child("Chat").child(StaticUser.userHost.getId()).push().setValue(chat);

    }
    public void startmhhome()
    {
        Intent intent = new Intent(this,home.class);
        startActivity(intent);
    }
    public void anhxa()
    {
        user1 = findViewById(R.id.txtuser1);
        user2 = findViewById(R.id.txtuser2);
        user3 = findViewById(R.id.txtuser3);
        user4 = findViewById(R.id.txtuser4);
        user5 = findViewById(R.id.txtuser5);
        user6 = findViewById(R.id.txtuser6);
        user7 = findViewById(R.id.txtuser7);
        user8 = findViewById(R.id.txtuser8);
        user9 = findViewById(R.id.txtuser9);
       textViewAddList.add(new TextViewAdd(R.id.txtuser1,false));
        textViewAddList.add(new TextViewAdd(R.id.txtuser2,false));
        textViewAddList.add(new TextViewAdd(R.id.txtuser3,false));
        btnuser1=findViewById(R.id.btnuser1);
//        textViewAddList.add(new TextViewAdd(user4.getId(),false));
//        textViewAddList.add(new TextViewAdd(user5.getId(),false));
//        textViewAddList.add(new TextViewAdd(user6.getId(),false));
//        textViewAddList.add(new TextViewAdd(user7.getId(),false));
//        textViewAddList.add(new TextViewAdd(user8.getId(),false));
//        textViewAddList.add(new TextViewAdd(user9.getId(),false));

    }


    public void ghitextview(int id,User user)
    {
        reference = database.getReference();
        reference.child("Room").child(StaticUser.userHost.getId()).child("TextView").child(user.getId()).setValue(id);
    }

    public void adduser(User user)
    {
        int id = 1;
        if(buser1==false)
        {
            id=user1.getId();
            buser1=true;
            user1.setText(user.getUser().toString());
        }
        else if(buser2==false)
        {
            id=user2.getId();
            buser2=true;
            user2.setText(user.getUser().toString());
        }
        else if(buser3==false)
        {
            buser3=true;
            user3.setText(user.getUser().toString());
            id=user3.getId();
        }
        else if(buser4==false)
        {
            buser4=true;
            user4.setText(user.getUser().toString());
            id=user4.getId();
        }
        else if(buser5==false)
        {
            buser5=true;
            user5.setText(user.getUser().toString());
            id=user5.getId();
        }
        else if(buser6==false)
        {
            buser6=true;
            user6.setText(user.getUser().toString());
            id=user6.getId();
        }
        else if(buser7==false)
        {
            buser7=true;
            user7.setText(user.getUser().toString());
            id=user7.getId();
        }
        else if(buser8==false)
        {
            buser8=true;
            user8.setText(user.getUser().toString());
            id=user8.getId();
        }
        else if(buser9==false)
        {
            buser9=true;
            user9.setText(user.getUser().toString());
            id=user9.getId();
        }
        ghitextview(id,user);
    }
    public void laylistuser()
    {
        reference = database.getReference();
        reference.child("Room").child(StaticUser.userHost.getId()).child("ListUser").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                User us = dataSnapshot.getValue(User.class);
                if(us.getId()!= StaticUser.user.getId())
                {
                    System.out.println(us.getUser());
                    adduser(us);

                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        st();

    }
    public void st()
    {
        Intent intent = new Intent(this,newfriend.class);
        startActivity(intent);
    }
}
