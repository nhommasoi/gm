package com.example.dtanp.masoi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dtanp.masoi.adapter.UserAdapter;
import com.example.dtanp.masoi.adapter.dapterfriend;
import com.example.dtanp.masoi.control.StaticFirebase;
import com.example.dtanp.masoi.control.StaticUser;
import com.example.dtanp.masoi.model.Phong;
import com.example.dtanp.masoi.model.User;
import com.example.dtanp.masoi.model.Userban;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class home extends Activity {
    ListView listban;
    List<User> list;
    UserAdapter adapter;
    private FirebaseDatabase database;
    DatabaseReference reference;
    DatabaseReference referenceuser;
    DatabaseReference friendr;
    TextView txtuser;
    EditText edtsearch;
    ArrayList<User> arrayList;
    ImageButton imgsearch;
    RecyclerView recyclerView;
    FirebaseAuth auth;
    String id_ol;
    Button btntimban;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        arrayList=new ArrayList<>();

        database = StaticFirebase.database;
        reference =database.getReference();
        referenceuser=database.getReference();
       //listban=findViewById(R.id.lsvlistfriend);

        list=new ArrayList<>();

        txtuser=findViewById(R.id.txtuser);
        btntimban=findViewById(R.id.btntimban);

        //adduser();
        //laylistban();

       // adapter  = new UserAdapter(this,R.layout.user_adapter,list);
        //adapter.notifyDataSetChanged();
        //listban.setAdapter(adapter);

        recyclerView=(RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(gridLayoutManager);
        dta();


        findViewById(R.id.btnchonban).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //finish();
               startmhroom();
               finish();
            }
        });
        btntimban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startmhf();
            }
        });
//        edtsearch.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if(s.toString().isEmpty()){
//                    seach(s.toString());
//                }else
//                    {
//                    seach("");
//                }
//            }
//        });

//        imgsearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                    addfiend();
//                final String seach = edtsearch.getText().toString();
//               // dta(seach);
//              }
//
//        });
//
//
   }
    public void startmhf()
    {
        Intent intent = new Intent(this,newfriend.class);
        startActivity(intent);
    }
    private void seach(String s){

        Query query = reference.orderByChild("User").
                startAt(s);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                if(dataSnapshot.hasChildren()){
                    arrayList.clear();
                    for(DataSnapshot ds:dataSnapshot.getChildren()){
                        final  User user =ds.getValue(User.class);
                        arrayList.add(user);
                    }


                    dapterfriend my =new dapterfriend(getApplicationContext(),arrayList);
                    recyclerView.setAdapter(my);
                    my.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    public void addfiend(){
        friendr=FirebaseDatabase.getInstance().getReference().child("friendd");

        final String seach = edtsearch.getText().toString();


        String id=auth.getCurrentUser().getUid();
        String sent=getIntent().getExtras().get("vi").toString();
        //if(seach==StaticUser.user.getUser()) {
            reference.child("friendships").child(StaticUser.user.getId()).child(id).child("email").setValue(StaticUser.user);

       // }
        //else
        {
            Toast.makeText(home.this,"thatbai",Toast.LENGTH_LONG).show();
        }

    }
    public void startmhroom()
    {
        Intent intent = new Intent(this,room.class);
        startActivity(intent);
    }
    public void adduser() {
        reference.child("Room").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Phong us = new Phong();
              //  txtuser.setText(dataSnapshot.child("tenphong").getValue(String.class));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public  void dta()
    {
        auth=FirebaseAuth.getInstance();
        id_ol=auth.getCurrentUser().getUid();

        String id= reference.push().getKey();


        //reference.child("friendships").child(StaticUser.user.getId());
        reference=FirebaseDatabase.getInstance().getReference().child("friendships").child(StaticUser.user.getId());

        FirebaseRecyclerOptions<Userban> options=
                new FirebaseRecyclerOptions.Builder<Userban>()
                .setQuery(reference,Userban.class)
                .build();
        FirebaseRecyclerAdapter<Userban,UsersViewHolder> recyclerAdapter =
        new FirebaseRecyclerAdapter<Userban, UsersViewHolder>(options) {

            @Override
            protected void onBindViewHolder(@NonNull UsersViewHolder holder, int position, @NonNull Userban model) {

                holder.txtbanne.setText(model.getEmail());
               //holder.txtbanne.setText(model.getUser());
                String listuser =getRef(position).getKey();


                referenceuser.child("User").child(listuser).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                     String userf=dataSnapshot.child("user").getValue().toString();
                    UsersViewHolder.setUserf(userf);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

            @NonNull
            @Override
            public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
            {
               View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_adapter,parent,false);
                UsersViewHolder viewHolder=new UsersViewHolder(view);
                return  viewHolder;
            }
        };
        recyclerAdapter.startListening();
        recyclerView.setAdapter(recyclerAdapter);
    }
    public static class UsersViewHolder extends RecyclerView.ViewHolder {
      TextView txtids;
      TextView txtbanne;
        static View mview;
        public UsersViewHolder(final View itemView) {

            super(itemView);
            mview=itemView;

          txtbanne = itemView.findViewById(R.id.txtbanne);


        }
        public static void setUserf(String userf){
            TextView txtids=(TextView)mview.findViewById(R.id.txtids);
              txtids.setText(userf);

        }


    }

    public void laylistban()
    {

        final String seach = edtsearch.getText().toString();
        reference.child("friendships").child("ListUserfriend").child(StaticUser.user.getId()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


              User userban=new User();
                String id= reference.push().getKey();
                userban.setId(dataSnapshot.getValue(String.class));
                  list.add(userban);
                  adapter.notifyDataSetChanged();
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Toast.makeText(home.this,"changed",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Toast.makeText(home.this,"removew ",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Toast.makeText(home.this,"move ",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(home.this,"cancell ",Toast.LENGTH_SHORT).show();

            }
        });
    }

}
