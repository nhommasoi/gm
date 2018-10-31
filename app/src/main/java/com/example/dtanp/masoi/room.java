package com.example.dtanp.masoi;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dtanp.masoi.adapter.CustomAdapter;
import com.example.dtanp.masoi.control.StaticFirebase;
import com.example.dtanp.masoi.control.StaticUser;
import com.example.dtanp.masoi.model.Chat;
import com.example.dtanp.masoi.model.Phong;
import com.example.dtanp.masoi.model.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class room extends Activity {

    private FirebaseDatabase database;
    ListView listroom;
    List<Phong> list;
    Button btnnew;
    ImageView imgback;
    DatabaseReference reference;
    CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        database = StaticFirebase.database;
        reference =database.getReference();
        listroom=findViewById(R.id.listroom);
        list=new ArrayList<>();
        laylistroom();
       adapter  = new CustomAdapter(this,R.layout.custom_adapter,list);
        adapter.notifyDataSetChanged();
        listroom.setAdapter(adapter);
        listroom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println(i);
            }
        });
        btnnew =findViewById(R.id.btnnew);
        imgback =  findViewById(R.id.imgback);
        imgback.setClickable(true);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("back");
            }
        });
        btnnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startmhhost();
               finish();
            }
        });

        listroom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                User us = new User();
                String s = list.get(i).getId();
                us.setId(s);

                StaticUser.userHost = us;
                startmhban();


            }
        });



    }

    public void st()
    {
        Intent intent = new Intent(this,newfriend.class);
        startActivity(intent);
    }

    public void startmhhost()
    {
        Intent intent = new Intent(this,Host.class);
        startActivity(intent);
    }

    public void startmhban()
    {
        Intent intent = new Intent(this,Ban.class);
        startActivity(intent);
    }

    public void listroom()
    {

    }
    public void TaoChat()
    {

    }

    public void laylistroom()
    {
      reference.child("Room").addChildEventListener(new ChildEventListener() {
          @Override
          public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
              Phong phong = new Phong();
              phong.setId(dataSnapshot.child("id").getValue(String.class));
              phong.setSophong(list.size());
              phong.setTenphong(dataSnapshot.child("tenphong").getValue(String.class));
              list.add(phong);
              adapter.notifyDataSetChanged();
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
      })   ;
    }
}
