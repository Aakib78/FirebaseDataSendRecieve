package com.soober.admin.firebasedatasendrecieve;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class UserList extends ArrayAdapter<User> {

    private Activity context;
    private List<User> userList;

    public UserList( Activity context, List<User> userList) {
        super(context,R.layout.list_layout,userList);
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public View getView(int position,View convertView,ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_layout,null,true);

        TextView name=(TextView)listViewItem.findViewById(R.id.name);
        TextView phone=(TextView)listViewItem.findViewById(R.id.phone);

        User user = userList.get(position);
        name.setText("Name: "+user.getName());
        phone.setText("Phone: "+user.getPhone());

        return listViewItem;

    }
}
