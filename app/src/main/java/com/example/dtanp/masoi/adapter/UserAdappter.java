package com.example.dtanp.masoi.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dtanp.masoi.R;
import com.example.dtanp.masoi.model.User;

import java.util.List;

public class UserAdappter extends BaseAdapter {

    Context myContext;
    int myLayout;
    List<User> arrayUser;
    public UserAdappter(Context context, int layout, List<User> userList){
        myContext=context;
        myLayout=layout;
        arrayUser=userList;

    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(myLayout,null);
      //TextView txtids=(TextView) convertView.findViewById(R.id.txtids);
       // txtids.setText(arrayUser.get(position).getId());
        TextView txtbanne=(TextView) convertView.findViewById(R.id.txtbanne);
        txtbanne.setText(arrayUser.get(position).getUser());

        return convertView;
    }
}















