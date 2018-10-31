package com.example.dtanp.masoi;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
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
import java.util.Timer;
import java.util.TimerTask;

public class Host extends Activity {
    ListView listchat;
    CustomAdapterChat adapterChat;
    Button btnbatdau,btnsend;
    ImageView imgnhanvat;
    List<TextViewAdd> textViewAddList;
    TextView txtthoigian;
    List<Chat> list;
    FirebaseDatabase database;
    EditText edtchat;
    List<User> listuser;
    DatabaseReference reference;
    TextView user1,user2,user3,user4,user5,user6,user7,user8,user9;
    boolean buser1=false,buser2=false,buser3=false,buser4=false,buser5=false,buser6=false,buser7=false,buser8=false,buser9=false;

    LinearLayout linearLayoutChat;
    private Timer timer;
    private Handler handler;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);
        database = StaticFirebase.database;
        reference = database.getReference();

        taophong();
        listuser = new ArrayList<>();
        textViewAddList = new ArrayList<>();

        btnbatdau = findViewById(R.id.btnbatdau);
        imgnhanvat = findViewById(R.id.imgnhanvat);
        imgnhanvat.setImageAlpha(0);
        txtthoigian = findViewById(R.id.txtthoigian);
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
                    //Toast.makeText(Host.this,"send",Toast.LENGTH_SHORT).show();
                }
            }
        });
        capnhatlistchat();
        anhxa();
        linearLayoutChat = findViewById(R.id.lnrchat);
        linearLayoutChat.setMinimumHeight(0);
        linearLayoutChat.setMinimumWidth(0);
        linearLayoutChat.setAlpha(1);
        btnbatdau.setAlpha(0);


        System.out.println("toi lay list user");
        laylistuser();
        findViewById(R.id.btnbatdau).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(Host.this,"nhan ne",Toast.LENGTH_SHORT).show();
                //timer.cancel();
            }
        });
//        setThoiGian();
//        DemGiay(30);


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
        textViewAddList.add(new TextViewAdd(user1.getId(),false));
        textViewAddList.add(new TextViewAdd(user2.getId(),false));
        textViewAddList.add(new TextViewAdd(user3.getId(),false));
        textViewAddList.add(new TextViewAdd(user4.getId(),false));
        textViewAddList.add(new TextViewAdd(user5.getId(),false));
        textViewAddList.add(new TextViewAdd(user6.getId(),false));
        textViewAddList.add(new TextViewAdd(user7.getId(),false));
        textViewAddList.add(new TextViewAdd(user8.getId(),false));
        textViewAddList.add(new TextViewAdd(user9.getId(),false));

    }


    public void capnhatlistchat()
    {
         reference = database.getReference();
        reference.child("Chat").child(StaticUser.user.getId()).addChildEventListener(new ChildEventListener() {
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

    public void ghitextview(int id,User user)
    {
        reference = database.getReference();
        reference.child("Room").child(StaticUser.user.getId()).child("TextView").child(user.getId()).setValue(id);
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
    public void send(Chat chat)
    {

        reference = database.getReference();
        reference.child("Chat").child(StaticUser.user.getId()).push().setValue(chat);

    }
    public void laylistuser() {
        reference = database.getReference();
        reference.child("Room").child(StaticUser.user.getId()).child("ListUser").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                User us = dataSnapshot.getValue(User.class);
                if (us.getId() != StaticUser.user.getId()) {
                    adduser(us);
                    listuser.add(us);
                    StaticUser.phong.setSonguoi(listuser.size());
                    reference.child("Room").child(StaticUser.user.getId()).child("SoNguoi").setValue(StaticUser.phong.getSonguoi());
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
        public void taophong()
        {
            Phong phong = new Phong();
            System.out.println(StaticUser.user.getId());
            phong.setId(StaticUser.user.getId());
            phong.setTenphong(StaticUser.user.getUser());
            phong.setSonguoi(1);
            StaticUser.phong=phong;
            reference.child("Room").child(phong.getId()).setValue(phong);
            reference.child("Room").child(phong.getId()).child("ListUser").child(StaticUser.user.getId()).setValue(StaticUser.user);
            reference.child("Room").child(phong.getId()).child("UserSang").setValue(" ");
            reference.child("Room").child(phong.getId()).child("SoNguoi").setValue(1);
            Chat chat = new Chat();
            chat.setUsername(StaticUser.user.getUser());
            chat.setMesage(" ");
            reference.child("Chat").child(phong.getId()).push().setValue(chat);


        }
        int dem;
        public void DemGiay(int giay) {
            dem=giay;
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    dem--;
                    handler.sendEmptyMessage(0);
                }
            },0,1000);


        }
        public void setThoiGian()
        {
            handler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    txtthoigian.setText(dem+"");
                }
            };
        }


}
