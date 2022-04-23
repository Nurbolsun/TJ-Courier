package com.example.mytest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterActivity extends AppCompatActivity {
    private   EditText mName, mEmail, mPassword, mNumber;
    private Button mRegisterbtn;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private FirebaseDatabase database;
    private static final String USER = "Users";
    private User user;
    private String userid;
    private static final String TAG = "RegisterActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mName = findViewById(R.id.edtextregname);
        mEmail = findViewById(R.id.edtexregemail);
        mPassword = findViewById(R.id.edtextregpassword);
        mNumber = findViewById(R.id.edtexregnumber);
        mRegisterbtn = findViewById(R.id.btnregregister);

        database = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference(USER);
        mAuth = FirebaseAuth.getInstance();
        userid = mAuth.getUid();


        mRegisterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullEmail = mEmail.getText().toString().trim();
                String fullPassword = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(fullEmail) || TextUtils.isEmpty(fullPassword)){
                    Toast.makeText(getApplicationContext(), "Enter eamil and password",
                            Toast.LENGTH_SHORT).show();             ;
                    return;
                }

                String fullName = mName.getText().toString();
                String fullNumber = mNumber.getText().toString();
                User user = new User(fullName,fullEmail,fullPassword,fullNumber);
                databaseReference.child(userid).child("").setValue(user);
                registerUser(fullEmail,fullPassword);


            }
        });
    }
    public void registerUser(String fullEmail, String fullPassword){
        mAuth.createUserWithEmailAndPassword(fullEmail,fullPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "Пользователь создан", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), FaceMain.class));
                }else{
                    Toast.makeText(RegisterActivity.this, "Ошибка!"+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}






