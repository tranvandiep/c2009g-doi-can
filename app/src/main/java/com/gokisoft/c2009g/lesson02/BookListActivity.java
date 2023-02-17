package com.gokisoft.c2009g.lesson02;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.gokisoft.c2009g.MainActivity;
import com.gokisoft.c2009g.R;

import java.util.ArrayList;
import java.util.List;

public class BookListActivity extends AppCompatActivity {
    ListView listView;
    Button addBtn;
//    List<String> dataList = new ArrayList<>();
    List<Book> dataList = new ArrayList<>();
    BookAdapter adapter;
    int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        listView = findViewById(R.id.abl_listview);
        addBtn = findViewById(R.id.abl_add_btn);

        adapter = new BookAdapter(this, dataList);

        dataList.add(new Book("LAP TRINH C", "TRAN VAN A", 200000));
        dataList.add(new Book("HTML/CSS/JS", "TRAN VAN B", 300000));

        //fake data
//        for (int i = 0; i < 2; i++) {
//            dataList.add("Mon hoc > " + i);
//        }

//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_book, R.id.ib_title, dataList);

        listView.setAdapter(adapter);

        //Su ly su kien click item trong ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                Log.d(MainActivity.class.getName(), "testing ...");
//                String msg = dataList.get(position);
//                Log.d(MainActivity.class.getName(), "testing > " + msg);
//                Toast.makeText(BookListActivity.this, msg, Toast.LENGTH_SHORT).show();
                Book book = dataList.get(position);

                Log.d(BookListActivity.class.getName(), book.toString());
            }
        });
        registerForContextMenu(listView);

        //Them sach
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookListActivity.this, BookEditorActivity.class);
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
                int position = data.getIntExtra("position", -1);

                Book book = new Book();
                book.setBookName(bookname);
                book.setAuthorName(authorname);
                book.setPrice(Float.parseFloat(price));

                if(position >= 0) {
                    dataList.set(position, book);
                } else {
                    dataList.add(book);
                }

                adapter.notifyDataSetChanged();
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context_book, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        position = info.position;

        switch (item.getItemId()) {
            case R.id.menu_book_edit:
                Book book = dataList.get(position);
                Intent intent = new Intent(BookListActivity.this, BookEditorActivity.class);
                intent.putExtra("bookname", book.getBookName());
                intent.putExtra("authorname", book.getAuthorName());
                intent.putExtra("price", book.getPrice());
                intent.putExtra("position", position);
                startActivityForResult(intent, 1);
                break;
            case R.id.menu_book_delete:
                showDialogDeleteBook();
                break;
        }
        return super.onContextItemSelected(item);
    }

    void showDialogDeleteBook() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        alertDialog.setTitle("XOA SACH")
                .setMessage("Ban chac chan muon xoa sach nay khong")
                .setPositiveButton("Xoa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dataList.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Huy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_book, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_book_add:
                Intent intent = new Intent(BookListActivity.this, BookEditorActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.menu_exit_app:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}