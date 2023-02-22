package com.gokisoft.c2009g.lesson04;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.gokisoft.c2009g.R;
import com.gokisoft.c2009g.lesson02.Book;
import com.gokisoft.c2009g.lesson02.BookAdapter;
import com.gokisoft.c2009g.lesson02.BookEditorActivity;
import com.gokisoft.c2009g.lesson02.BookListActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class BookActivity extends AppCompatActivity {
    List<Book> bookList = new ArrayList<>();

    ListView listView;
    BookAdapter adapter;
    int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        DBHelper.getInstance(this);

        //Fake data
//        BookDAO.insert(new Book("LAP TRINH C", "TRAN VAN A", 100000));
//        BookDAO.insert(new Book("SQL SERVER", "TRAN VAN B", 150000));
//        BookDAO.insert(new Book("HTML/CSS/JS", "TRAN VAN C", 200000));

        bookList = BookDAO.getList();
        listView = findViewById(R.id.ab_listview);
        adapter = new BookAdapter(this, bookList);
        listView.setAdapter(adapter);

        registerForContextMenu(listView);
//        readSharedPreferences();
//        readFile();

//        List<Book> dataList = BookDAO.getList();
//        for(Book book:dataList) {
//            Log.d(BookActivity.class.getName(), book.toString());
//        }
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
                showBookDialog();
                break;
            case R.id.menu_exit_app:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
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
                        bookList.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Huy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).show();
    }

    void showBookDialog() {
        View view = getLayoutInflater().inflate(R.layout.activity_book_editor, null);

        EditText bookNameTxt = view.findViewById(R.id.abe_bookname);
        EditText authorNameTxt = view.findViewById(R.id.abe_authorname);
        EditText priceTxt = view.findViewById(R.id.abe_price);

        Button cancelBtn = view.findViewById(R.id.abe_cancel_btn);
        Button saveBtn = view.findViewById(R.id.abe_save_btn);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);

        Dialog dialog = builder.show();

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

                String bookName = bookNameTxt.getText().toString();
                String authorName = authorNameTxt.getText().toString();
                String price = priceTxt.getText().toString();

                Book book = new Book(bookName, authorName, Float.parseFloat(price));

                bookList.add(book);
                adapter.notifyDataSetChanged();

                //Cach 1:
//                saveSharedPreferences();

                //Cach 2:
//                saveFile();
            }
        });
    }

    void saveSharedPreferences() {
        //B1. Khai bao
        SharedPreferences sharedPreferences = getSharedPreferences("books", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //B2. Luu du lieu
        Gson gson = new Gson();
        String data = gson.toJson(bookList);

        editor.putString("bookList", data);

        //B3. Dong ket noi
        editor.commit();
    }

    void readSharedPreferences() {
        //B1. Khai bao
        SharedPreferences sharedPreferences = getSharedPreferences("books", MODE_PRIVATE);

        //B2. Doc du lieu
        String data = sharedPreferences.getString("bookList", "[]");

        //Chuyen data string -> ArrayList
        Type baseType = new TypeToken<List<Book>>() {}.getType();
        Gson gson = new Gson();
        bookList = gson.fromJson(data, baseType);

        adapter.setDataList(bookList);
        adapter.notifyDataSetChanged();
    }

    void saveFile() {
        Gson gson = new Gson();
        String data = gson.toJson(bookList);

        FileOutputStream fos = null;

        try {
            fos = openFileOutput("books.txt", MODE_PRIVATE);

            byte[] bits = data.getBytes(StandardCharsets.UTF_8);

            fos.write(bits);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void readFile() {
        FileInputStream fis = null;
        StringBuilder builder = new StringBuilder();

        try {
            fis = openFileInput("books.txt");

            int code;
            while((code = fis.read()) != -1) {
                builder.append((char) code);
            }

            Type baseType = new TypeToken<List<Book>>() {}.getType();
            Gson gson = new Gson();
            bookList = gson.fromJson(builder.toString(), baseType);

            adapter.setDataList(bookList);
            adapter.notifyDataSetChanged();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}