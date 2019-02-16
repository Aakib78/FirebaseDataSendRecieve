package com.soober.admin.firebasedatasendrecieve;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GetData extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private ListView userListView;
    private Button getData;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_data);

        userListView=(ListView)findViewById(R.id.UserListView);
        getData=(Button)findViewById(R.id.getData);
        databaseReference =FirebaseDatabase.getInstance().getReference("users");
        userList = new ArrayList<>();

        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUser();
            }
        });
    }

    private void showUser() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userList.clear();
                for(DataSnapshot userSnapshot: dataSnapshot.getChildren()){
                    User user =userSnapshot.getValue(User.class);
                    userList.add(user);
                }
                UserList adapter =new UserList(GetData.this,userList);
                userListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(GetData.this, "Something Went Wrong.", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
