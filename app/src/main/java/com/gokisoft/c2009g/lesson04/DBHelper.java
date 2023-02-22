package com.gokisoft.c2009g.lesson04;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "C2009G";
    private static final int VERSION = 1;

    private static DBHelper instance = null;

    private DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    public static synchronized DBHelper getInstance(Context context) {
        if(instance == null) {
            instance = new DBHelper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Se dc goi duy nhat 1 lan -> khi cai app
        //Khoi tao tables trong database -> users -> database
        sqLiteDatabase.execSQL(BookDAO.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int newVersion, int oldVersion) {
        //Khi nang cap version database newVersion > oldVersion
        //Bo sung logic o day -> xu ly.
    }
}
