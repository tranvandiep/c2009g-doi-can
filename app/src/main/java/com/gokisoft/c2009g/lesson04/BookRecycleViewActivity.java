package com.gokisoft.c2009g.lesson04;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.gokisoft.c2009g.R;
import com.gokisoft.c2009g.lesson02.Book;

import java.util.ArrayList;
import java.util.List;

public class BookRecycleViewActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    List<Book> bookList = new ArrayList<>();
    BookRecyleViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_recycle_view);

        //fake data
        bookList.add(new Book("LAP TRINH C", "TRAN VAN A", 1));
        bookList.add(new Book("LAP TRINH C", "TRAN VAN B", 2));
        bookList.add(new Book("LAP TRINH C", "TRAN VAN C", 3));
        bookList.add(new Book("LAP TRINH C", "TRAN VAN D", 4));
        bookList.add(new Book("LAP TRINH C", "TRAN VAN E", 5));

        recyclerView = findViewById(R.id.abrv_recyleview);

        adapter = new BookRecyleViewAdapter(this, bookList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}