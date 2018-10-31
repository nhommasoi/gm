package com.example.dtanp.masoi;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dtanp.masoi.adapter.CustomAdapter;
import com.example.dtanp.masoi.adapter.UserAdapter;
import com.example.dtanp.masoi.adapter.dapterfriend;
import com.example.dtanp.masoi.control.StaticFirebase;
import com.example.dtanp.masoi.control.StaticUser;

import com.example.dtanp.masoi.model.Chat;
import com.example.dtanp.masoi.model.Phong;
import com.example.dtanp.masoi.model.User;
import com.example.dtanp.masoi.model.Userban;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class newfriend extends Activity implements View.OnClickListener{
    boolean buser1=false,buser2=false,buser3=false,buser4=false,buser5=false,buser6=false,buser7=false,buser8=false,buser9=false;

    TextView user1,user2,user3,user4,user5,user6,user7,user8,user9;
    TextView txtuser11,txtuser12;
    private FirebaseDatabase database;
    private FirebaseUser mCurrenUsser;

    ListView list_new;
    ListView listban;
    List<User> list;
    UserAdapter adapterrr;

    DatabaseReference referenceuser;
    DatabaseReference friendr;
    TextView txtuser;
    EditText edtsearch;
    ArrayList<User> arrayList;
    ImageButton imgsearch;
    RecyclerView recyclerView;
    FirebaseAuth auth;

    Button btnthem;
    DatabaseReference reference;
    static DatabaseReference referenceThem;
    CustomAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newfriend);

        database = StaticFirebase.database;
        reference =database.getReference();
        referenceuser=database.getReference();
        referenceThem=database.getReference();
        //listban=findViewById(R.id.lsvlistfriend);
        edtsearch=findViewById(R.id.edtsearch);
        imgsearch=findViewById(R.id.imgsearch);
        mCurrenUsser=FirebaseAuth.getInstance().getCurrentUser();
        String current_uid=mCurrenUsser.getUid();

        //btnthem.findViewById(R.id.btnthem);
        list=new ArrayList<>();

        txtuser=findViewById(R.id.txtuser);
        //adduser();
        //laylistban();

        // adapter  = new UserAdapter(this,R.layout.user_adapter,list);
        //adapter.notifyDataSetChanged();
        //listban.setAdapter(adapter);

        recyclerView=(RecyclerView) findViewById(R.id.recyclerviewf);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(gridLayoutManager);

        database = StaticFirebase.database;
        reference =database.getReference().child("User");
        referenceuser=database.getReference();
        //String user_id=getIntent().getStringExtra("id");
       // txtuser11.setText(user_id);
        imgsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String seachc = edtsearch.getText().toString();
               dta(seachc);
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
//                {
//                    seach("");
//                }
//            }
//        });


         anhxa();
        // laylistuser();
        //reference.child("Room").child(StaticUser.userHost.getId()).child("ListUser").setValue(null);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






    }
    private void seach(String s){

        Query query = reference.orderByChild("email").
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
    public  void dta(String s){
        Toast.makeText(newfriend.this,"bdau seach",Toast.LENGTH_LONG).show();
        auth=FirebaseAuth.getInstance();

        Query query = reference.orderByChild("email").
                startAt(s);

        //reference.child("friendships").child(StaticUser.user.getId());
       // reference=FirebaseDatabase.getInstance().getReference().child("User");

        FirebaseRecyclerOptions<User> options=
                new FirebaseRecyclerOptions.Builder<User>()
                        .setQuery(query,User.class)
                        .build();
        FirebaseRecyclerAdapter<User,UsersViewHolder> recyclerAdapter =
                new FirebaseRecyclerAdapter<User, UsersViewHolder>(options) {

                    @Override
                    protected void onBindViewHolder(@NonNull final UsersViewHolder holder, final int position, @NonNull User model) {
                        holder.txtbanne.setText(model.getEmail());
                        holder.txtids.setText(model.getUser());
                        String t= model.getId();
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                           final String user_id =getRef(position).getKey();
                            @Override
                            public void onClick(View v) {
                                referenceThem.child("friendships").
                                        child(StaticUser.user.getId()).
                                        child(user_id).child("email").
                                        setValue(holder.txtbanne.getText());

                                referenceThem.child("friendships").
                                        child(StaticUser.user.getId()).
                                        child(user_id).child("user").
                                        setValue(holder.txtids.getText());
                                startmhhom();
                            }
                        });

//                        String listuser =getRef(position).getKey();
//
//                        referenceuser.child("User").child(listuser).child("user").addValueEventListener(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                String userfr=dataSnapshot.getValue().toString();
//                                UsersViewHolder.setUserfr(userfr);
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                            }
//                        });
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

    public static class UsersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtids;
        TextView txtbanne;
        static View mview;

        public UsersViewHolder(final View itemView) {
            super(itemView);
            mview=itemView;


            txtbanne = itemView.findViewById(R.id.txtbanne);
            txtids=itemView.findViewById(R.id.txtids);
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    }
            });


        }
        public static void setUserfr(String userfr){
            TextView txtids=(TextView)mview.findViewById(R.id.txtids);
            txtids.setText(userfr);

        }


        @Override
        public void onClick(View v) {

        }
    }
    public void startmhhom()
    {
        Intent intent = new Intent(this,home.class);
        startActivity(intent);
    }
//    public static void dialog(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(UsersViewHolder.this);
//        builder
//                .setPositiveButton("sd", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // FIRE ZE MISSILES!
//                    }
//                })
//                .setNegativeButton("cannel", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // User cancelled the dialog
//                    }
//                });
//    }
    public void anhxa() {
        user1 = findViewById(R.id.txtuser11);
        user2 = findViewById(R.id.txtuser11);
        txtuser11=findViewById(R.id.txtuser11);


    }

    public void ghitextview(int id,User user)
    {
        reference = database.getReference();
        reference.child("Room").child(StaticUser.user.getId()).child("TextView").child(user.getId()).setValue(id);

    }
    public void adduser(User user) {
        int id = 1;
        if (buser1 == false) {
            id = user1.getId();
            buser1 = true;
            user1.setText(user.getUser().toString());

        }

        else if (buser2 == false) {
            id = user2.getId();
            buser2 = true;
            user2.setText(user.getUser().toString());

        }
        ghitextview(id,user);


    }

    public void laylistuser(DataSnapshot dataSnapshot)
    {
        for(DataSnapshot ds : dataSnapshot.getChildren()) {
            User user = new User();
            user.setId(ds.child(StaticUser.user.getId()).getValue(User.class).getId());

            ArrayList<String> array  = new ArrayList<>();
            array.add(user.getId());
            ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,array);


        }




    }

    @Override
    public void onClick(View v) {


        reference.child("friendships").child(StaticUser.user.getId()).child(StaticUser.userHost.getId()).setValue(StaticUser.userHost);



       //reference.child("Userfriend").child("user").child(""+txtuser11.getId()).child("").setValue(txtuser11.getText().toString());

        Toast.makeText(newfriend.this,"thêm bạn thành công",Toast.LENGTH_SHORT).show();
        reference.child("Userfriend").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

               // txtuser12.setText(dataSnapshot.getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }


}















