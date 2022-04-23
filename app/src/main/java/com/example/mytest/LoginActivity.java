package com.example.mytest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText mEmail, mPassword;
    Button mLoginbtn;
    TextView mRegister;
    FirebaseAuth fAuth;
    private ProgressDialog progressDialog;
    private CheckBox remember;
    private SharedPreferences mPrefs;
    private static final String PREFS_NAME = "PrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mPrefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        remember = findViewById(R.id.remember);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.edpass);
        mRegister = findViewById(R.id.regtext);
        mLoginbtn = findViewById(R.id.linbtn);

        fAuth = FirebaseAuth.getInstance();

        // dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Pleasa wait");
        progressDialog.setMessage("Creat yoy accaunt");
        progressDialog.setCanceledOnTouchOutside(false);


        mLoginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Ошибка email");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Ошибка пароля");
                    return;
                }
                if (remember.isChecked()) {
                    Boolean bollisChecked = remember.isChecked();
                    SharedPreferences.Editor editor = mPrefs.edit();
                    editor.putString("praf_name", mEmail.getText().toString());
                    editor.putString("praf_pass", mPassword.getText().toString());
                    editor.putBoolean("pref_check", bollisChecked);
                    editor.apply();
                    Toast.makeText(getApplicationContext(), "Settings", Toast.LENGTH_SHORT).show();
                } else {
                    mPrefs.edit().clear().apply();
                }
                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Вы успешно вошли", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), FaceMain.class));
                        } else {
                            Toast.makeText(LoginActivity.this, "Ошибка", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                mEmail.getText().clear();
                mPassword.getText().clear();
            }
        });

    }
}
