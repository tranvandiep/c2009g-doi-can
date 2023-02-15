package com.gokisoft.c2009g.lesson02;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gokisoft.c2009g.R;

public class BookDetailActivity extends AppCompatActivity {
    TextView bookNameView, authorNameView, priceView;
    Button editBtn;
    Book book = new Book("LAP TRINH C", "TRAN VAN A", 200000);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        bookNameView = findViewById(R.id.abd_bookname);
        authorNameView = findViewById(R.id.abd_authorname);
        priceView = findViewById(R.id.abd_price);
        editBtn = findViewById(R.id.abd_edit_btn);

        updateView();

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookDetailActivity.this, BookEditorActivity.class);
                intent.putExtra("bookname", book.getBookName());
                intent.putExtra("authorname", book.getAuthorName());
                intent.putExtra("price", book.getPrice());
//                startActivity(intent);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (resultCode) {
            case 1:
                String bookname = data.getStringExtra("bookname");
                String authorname = data.getStringExtra("authorname");
                String price = data.getStringExtra("price");

                book.setBookName(bookname);
                book.setAuthorName(authorname);
                book.setPrice(Float.parseFloat(price));

                updateView();
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    void updateView() {
        bookNameView.setText("Ten sach: " + book.getBookName());
        authorNameView.setText("Tac gia: " + book.getAuthorName());
        priceView.setText("Gia: " + book.getPrice());
    }
}