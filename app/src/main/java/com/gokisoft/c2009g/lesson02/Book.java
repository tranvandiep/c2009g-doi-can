package com.gokisoft.c2009g.lesson02;

import android.database.Cursor;

public class Book {
    int _id;
    String bookName;
    String authorName;
    float price;
    String thumbnail;

    public Book() {
    }

    public Book(String bookName, float price, String thumbnail) {
        this.bookName = bookName;
        this.price = price;
        this.thumbnail = thumbnail;
    }

    public Book(String bookName, String authorName, float price) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.price = price;
    }

    public Book(int _id, String bookName, String authorName, float price) {
        this._id = _id;
        this.bookName = bookName;
        this.authorName = authorName;
        this.price = price;
    }

    public void setData(Cursor cursor) {
        this._id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
        this.bookName = cursor.getString(cursor.getColumnIndexOrThrow("book_name"));
        this.authorName = cursor.getString(cursor.getColumnIndexOrThrow("author_name"));
        this.price = cursor.getFloat(cursor.getColumnIndexOrThrow("price"));
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "_id=" + _id +
                ", bookName='" + bookName + '\'' +
                ", authorName='" + authorName + '\'' +
                ", price=" + price +
                '}';
    }
}
