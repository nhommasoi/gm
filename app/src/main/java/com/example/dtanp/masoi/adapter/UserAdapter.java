package com.example.dtanp.masoi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dtanp.masoi.R;
import com.example.dtanp.masoi.model.User;

import java.util.List;

public class UserAdapter extends ArrayAdapter {
    Context context;
    int resource;
    List<User> list;
    public UserAdapter(@NonNull Context context, int resource, @NonNull List<User> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        list=objects;
    }

    public static class ViewHolder
    {

        TextView txtbanne;
        TextView txtids;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = convertView;
        if(view==null)
        {
            view=inflater.inflate(resource,parent,false);
            ViewHolder viewHolder = new ViewHolder();
            //viewHolder.txtids = view.findViewById(R.id.txtids);

            viewHolder.txtbanne = view.findViewById(R.id.txtbanne);

            view.setTag(viewHolder);
        }
        ViewHolder holder = (ViewHolder) view.getTag();
       // holder.txtids.setText(list.get(position).getId()+"");

        holder.txtbanne.setText(list.get(position).getId()+"");
        return view;
    }
}
