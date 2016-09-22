package com.qianfeng.bookproviderdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Eline on 2016/5/17.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "android1602";
    public static final int VERSION_CODE = 1;

    public static final String CREATE_TB_BOOK =
            "create table book ( " + "_id integer primary key autoincrement," +
            "book_name string," +
            "book_price float," +
            "book_author string" + ");";


    public MyDatabaseHelper(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DB_NAME, factory, VERSION_CODE);
    }

    /**
     * 创建book表
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TB_BOOK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
