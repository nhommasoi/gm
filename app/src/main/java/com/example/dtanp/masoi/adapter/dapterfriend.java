package com.example.dtanp.masoi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.PixelCopy;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dtanp.masoi.R;
import com.example.dtanp.masoi.model.User;

import java.util.ArrayList;

public class dapterfriend extends RecyclerView.Adapter<dapterfriend.MyAdapterViewHolder> {

    public Context c;
    public  ArrayList<User> arrayList;

public  dapterfriend (Context c, ArrayList<User> arrayList){
    this.c=c;
    this.arrayList=arrayList;
}


    @NonNull
    @Override
    public MyAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    View v=LayoutInflater.from(parent.getContext())
            .inflate(R.layout.user_adapter,parent,false);

    return new MyAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterViewHolder holder, int position) {
        User user=arrayList.get(position);
        holder.id.setText(user.getUser());

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyAdapterViewHolder extends RecyclerView.ViewHolder{

    public TextView id;


    public MyAdapterViewHolder(View itemView) {
        super(itemView);
        id=(TextView)itemView.findViewById(R.id.txtbanne);
    }
}

}



































