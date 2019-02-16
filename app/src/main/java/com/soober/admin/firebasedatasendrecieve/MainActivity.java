package com.soober.admin.firebasedatasendrecieve;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private EditText phone;
    private Button sendBtn;
    private DatabaseReference database;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name =(EditText)findViewById(R.id.name);
        phone =(EditText)findViewById(R.id.phone);
        sendBtn=(Button)findViewById(R.id.sendData);


        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance().getReference("users");
                userId=database.push().getKey();
                User user = new User(name.getText().toString(), phone.getText().toString());
                database.child(userId).setValue(user);
                Intent i=new Intent(MainActivity.this,GetData.class);
                startActivity(i);
            }
        });
    }
}
