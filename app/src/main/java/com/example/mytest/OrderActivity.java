package com.example.mytest;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OrderActivity extends AppCompatActivity {
    private static final int RESULT_PICK_CONTACT = 1;
    private static final int RESULT_PICK_CONTACT2 = 2;
    private EditText numberview;
    private ImageView contact;
    private EditText numberview2;
    private ImageView contact2;
    Button btnorder;
    EditText addres1, addres2, coment1,coment2;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        btnorder = (Button) findViewById(R.id.btnorder);
        numberview = findViewById(R.id.numberview);
        contact = findViewById(R.id.contact);
        // contact 2
        numberview2 = findViewById(R.id.numberview2);
        contact2 = findViewById(R.id.contact2);

        addres1 = (EditText) findViewById(R.id.addres1);
        coment1 = (EditText) findViewById(R.id.comment1);
        addres2 = (EditText) findViewById(R.id.addres2);

        coment2 = (EditText) findViewById(R.id.comment2);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        btnorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uaddres1 = addres1.getText().toString();
                String unumberviev = numberview.getText().toString();
                String ucoment1 = coment1.getText().toString();
                String uaddres2 = addres2.getText().toString();
                String unumberviev2 = numberview2.getText().toString();
                String ucoment2 = coment2.getText().toString();

                Ordering ordering = new Ordering(uaddres1, unumberviev, ucoment1, uaddres2, unumberviev2, ucoment2);
                databaseReference.child("Order").child(unumberviev).setValue(ordering);
                Toast.makeText(OrderActivity.this, "Ваш заказ успешно оформлен", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), FaceMain.class));
            }
        });

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(in, RESULT_PICK_CONTACT);
            }
        });

        // 2 contact
        contact2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(in, RESULT_PICK_CONTACT2);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK)
        {
            switch (requestCode)
            {
                case RESULT_PICK_CONTACT:
                contactPicket(data);
                break;

        }
    }

        if (resultCode == RESULT_OK)
        {
            switch (requestCode)
            {
                case RESULT_PICK_CONTACT2:
                    contactPicket(data);
                    break;

            }
        }

    else
        {
            Toast.makeText(this, "Filed to contact", Toast.LENGTH_SHORT).show();
        }
    }

    private void contactPicket(Intent data) {


    Cursor cursor = null;
                try

    {
        String numberviewNO = null;
        String numberview2NO = null;
        Uri uri = data.getData();
        Uri uri2 = data.getData();
        cursor = getContentResolver().query(uri, null, null, null, null );
        cursor.moveToFirst();
        int numberviewIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
        numberviewNO = cursor.getString(numberviewIndex);
        numberview.setText(numberviewNO);
        // contact 2
        cursor = getContentResolver().query(uri, null, null, null, null );
        cursor.moveToLast();
        int numberview2Index = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
        numberview2NO = cursor.getString(numberview2Index);
        numberview2.setText(numberview2NO);
    }
                catch(Exception e)

    {
        e.printStackTrace();
    }
}
}





