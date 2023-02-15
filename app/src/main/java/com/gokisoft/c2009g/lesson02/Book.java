package com.gokisoft.c2009g.lesson02;

public class Book {
    String bookName;
    String authorName;
    float price;

    public Book() {
    }

    public Book(String bookName, String authorName, float price) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.price = price;
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
}
