package com.gokisoft.c2009g.lesson02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gokisoft.c2009g.R;

public class BookEditorActivity extends AppCompatActivity implements View.OnClickListener{
    EditText bookNameTxt, authorNameTxt, priceTxt;
    Button cancelBtn, saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_editor);

        bookNameTxt = findViewById(R.id.abe_bookname);
        authorNameTxt = findViewById(R.id.abe_authorname);
        priceTxt = findViewById(R.id.abe_price);

        //update du lieu
        String bookname = getIntent().getStringExtra("bookname");
        String authorname = getIntent().getStringExtra("authorname");
        float price = getIntent().getFloatExtra("price", 0);

        bookNameTxt.setText(bookname);
        authorNameTxt.setText(authorname);
        priceTxt.setText(price+"");

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
            //TH -> nguoi dung click vao save btn
            Intent intent = new Intent();
            intent.putExtra("bookname", bookNameTxt.getText().toString());
            intent.putExtra("authorname", authorNameTxt.getText().toString());
            intent.putExtra("price", priceTxt.getText().toString());

            setResult(1, intent);

            finish();
        }
    }
}