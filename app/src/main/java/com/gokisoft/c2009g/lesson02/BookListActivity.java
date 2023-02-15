package com.gokisoft.c2009g.lesson02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.gokisoft.c2009g.R;

import java.util.ArrayList;
import java.util.List;

public class BookListActivity extends AppCompatActivity {
    ListView listView;
    Button addBtn;
    List<String> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        listView = findViewById(R.id.abl_listview);
        addBtn = findViewById(R.id.abl_add_btn);

        //fake data
        for (int i = 0; i < 200; i++) {
            dataList.add("Mon hoc > " + i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_book, R.id.ib_title, dataList);

        listView.setAdapter(adapter);
    }
}