package com.gokisoft.c2009g.lesson02;

import java.util.ArrayList;
import java.util.List;

public class DataMgr {
    List<Book> bookList = new ArrayList<>();

    private static DataMgr instance = null;

    private DataMgr() {}

    public static synchronized DataMgr getInstance() {
        if(instance == null) {
            instance = new DataMgr();
        }
        return instance;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
