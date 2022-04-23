package com.example.mytest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FaceMain extends AppCompatActivity {
    TextView phone, profname, email;
    FirebaseUser user;
    DatabaseReference reference;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_main);

        TextView btn = findViewById(R.id.btnorder);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FaceMain.this, OrderActivity.class));
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userid = user.getUid();

         profname = (TextView) findViewById(R.id.profname);
         email = (TextView) findViewById(R.id.profemail);
         phone = (TextView) findViewById(R.id.profnumber);




        reference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(FaceMain.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}