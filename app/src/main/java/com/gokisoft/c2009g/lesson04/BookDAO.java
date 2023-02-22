package com.gokisoft.c2009g.lesson04;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gokisoft.c2009g.lesson02.Book;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    public static final String SQL_CREATE_TABLE = "create table books (\n" +
            "\t_id integer primary key autoincrement,\n" +
            "\tbook_name varchar(250),\n" +
            "\tauthor_name varchar(250),\n" +
            "\tprice float\n" +
            ")\n";

    public static List<Book> getList() {
        List<Book> dataList = new ArrayList<>();

        //B1. Mo ket noi database
        SQLiteDatabase db = DBHelper.getInstance(null).getReadableDatabase();

        //B2. Thuc thi lenh
        String sql = "select * from books";
        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            Book book = new Book();
            book.setData(cursor);

            dataList.add(book);
        }

        return dataList;
    }

    public static Cursor getCursor() {
        List<Book> dataList = new ArrayList<>();

        //B1. Mo ket noi database
        SQLiteDatabase db = DBHelper.getInstance(null).getReadableDatabase();

        //B2. Thuc thi lenh
        String sql = "select * from books";
        Cursor cursor = db.rawQuery(sql, null);

        return cursor;
    }

    public static void insert(Book book) {
        SQLiteDatabase db = DBHelper.getInstance(null).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("book_name", book.getBookName());
        values.put("author_name", book.getAuthorName());
        values.put("price", book.getPrice());

        db.insert("books", null, values);
    }

    public static void update(Book book) {
        SQLiteDatabase db = DBHelper.getInstance(null).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("book_name", book.getBookName());
        values.put("author_name", book.getAuthorName());
        values.put("price", book.getPrice());

        db.update("books", values, "_id = " + book.get_id(), null);
    }

    public static void delete(int id) {
        SQLiteDatabase db = DBHelper.getInstance(null).getWritableDatabase();

        db.delete("books", "_id = " + id, null);
    }
}
