package com.gokisoft.c2009g.lesson04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.gokisoft.c2009g.R;

public class BookEditor2Activity extends AppCompatActivity implements View.OnClickListener{
    EditText bookNameTxt, authorNameTxt, priceTxt;
    Button cancelBtn, saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_editor);

        bookNameTxt = findViewById(R.id.abe_bookname);
        authorNameTxt = findViewById(R.id.abe_authorname);
        priceTxt = findViewById(R.id.abe_price);

        cancelBtn = findViewById(R.id.abe_cancel_btn);
        saveBtn = findViewById(R.id.abe_save_btn);

        cancelBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.equals(cancelBtn)) {
            //TH -> nguoi dung click vao cancel btn
            finish();
        } else if(view.equals(saveBtn)) {
            Intent intent = new Intent();
            intent.setAction("ADD_NEW_BOOK");
            intent.putExtra("bookName", bookNameTxt.getText().toString());
            intent.putExtra("authorName", authorNameTxt.getText().toString());
            intent.putExtra("price", Float.parseFloat(priceTxt.getText().toString()));

            sendBroadcast(intent);

            finish();
        }
    }
}