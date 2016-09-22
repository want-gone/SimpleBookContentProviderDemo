package com.qianfeng.bookproviderdemo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by Eline on 2016/5/17.
 */
public class MyContentProvider extends ContentProvider {
    private MyDatabaseHelper helper;
    private SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        helper = new MyDatabaseHelper(this.getContext(),null);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        db = helper.getReadableDatabase();
        Cursor cursor = db.query("book", null, selection, selectionArgs, null, null, null);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        db = helper.getReadableDatabase();
        db.insert("book",null,values);
        return uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        db=helper.getReadableDatabase();
        int num = db.delete("book", selection, selectionArgs);
        return num;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        db=  helper.getReadableDatabase();
        int num = db.update("book", values, selection, selectionArgs);
        return num;
    }
}
