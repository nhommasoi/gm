package com.example.dtanp.masoi;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class random extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityramdom);
        //userban.setId(dataSnapshot.child(StaticUser.user.getUser()).getValue(String.class));
        //userban.setUser(dataSnapshot.child(StaticUser.user.getUser()).getValue(String.class));
        //if(StaticUser.user.getUser()=="bahai") {
        //userban.setUser(dataSnapshot.child(StaticUser.user.getId()).getValue(String.class));
        //phong.setId(dataSnapshot.child("id").getValue(String.class));
        //userban.setUser(dataSnapshot.child("friendships").getValue(User.class).getUser());
        // userban.setUser(dataSnapshot.child(StaticUser.user.getUser()).child(userID).getValue(User.class).getUser());
        // userban.setUser(dataSnapshot.child("bahai").getValue(String.class));
        //userban.setUser(dataSnapshot.child(StaticUser.user.getUser()).getValue(String.class));

    }
}
