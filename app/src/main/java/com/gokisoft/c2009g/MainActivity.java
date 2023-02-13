package com.gokisoft.c2009g;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText fullnameTxt, emailTxt, addressTxt;
    Button cancelBtn, saveBtn, openActivityBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Map xml <-> var java
        fullnameTxt = findViewById(R.id.am_fullname);
        emailTxt = findViewById(R.id.am_email);
        addressTxt = findViewById(R.id.am_address);
        cancelBtn = findViewById(R.id.am_cancel);
        saveBtn = findViewById(R.id.am_save);
        openActivityBtn = findViewById(R.id.am_open_activity);

        //Xu ly su kien
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
                fullnameTxt.setText("");
                emailTxt.setText("");
                addressTxt.setText("");
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname = fullnameTxt.getText().toString();
                String email = emailTxt.getText().toString();
                String address = addressTxt.getText().toString();
                Toast.makeText(MainActivity.this, "Save > " + fullname, Toast.LENGTH_SHORT).show();
            }
        });

        openActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, NewActivity.class);
                startActivity(i);
            }
        });
    }
}