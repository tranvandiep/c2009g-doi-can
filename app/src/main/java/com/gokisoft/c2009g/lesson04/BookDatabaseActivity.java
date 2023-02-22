package com.gokisoft.c2009g.lesson04;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
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

public class BookDatabaseActivity extends AppCompatActivity {
    ListView listView;
    int position = -1;
    BookDatabaseAdapter adapter;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_database);

        DBHelper.getInstance(this);
        cursor = BookDAO.getCursor();

        listView = findViewById(R.id.abd_listview);
        adapter = new BookDatabaseAdapter(this, cursor);

        listView.setAdapter(adapter);

        registerForContextMenu(listView);
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
                showBookDialog(-1);
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
                showBookDialog(position);
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
                        cursor.moveToPosition(position);
                        Book book = new Book();
                        book.setData(cursor);

                        BookDAO.delete(book.get_id());

                        updateCursor();
                    }
                })
                .setNegativeButton("Huy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).show();
    }

    void showBookDialog(int position) {
        View view = getLayoutInflater().inflate(R.layout.activity_book_editor, null);

        EditText bookNameTxt = view.findViewById(R.id.abe_bookname);
        EditText authorNameTxt = view.findViewById(R.id.abe_authorname);
        EditText priceTxt = view.findViewById(R.id.abe_price);

        if(position >= 0) {
            cursor.moveToPosition(position);
            Book book = new Book();
            book.setData(cursor);

            bookNameTxt.setText(book.getBookName());
            authorNameTxt.setText(book.getAuthorName());
            priceTxt.setText(book.getPrice() + "");
        }

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

                if(position >= 0) {
                    cursor.moveToPosition(position);
                    Book book = new Book();
                    book.setData(cursor);

                    book.setBookName(bookName);
                    book.setAuthorName(authorName);
                    book.setPrice(Float.parseFloat(price));

                    BookDAO.update(book);
                } else {
                    Book book = new Book(bookName, authorName, Float.parseFloat(price));
                    BookDAO.insert(book);
                }

                updateCursor();
            }
        });
    }

    void updateCursor() {
        cursor = BookDAO.getCursor();
        adapter.changeCursor(cursor);

        adapter.notifyDataSetChanged();
    }
}