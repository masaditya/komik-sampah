package com.adityaeka.sampahisasi.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class DbComic extends SQLiteOpenHelper {


    public DbComic(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public void insertData(String title, String desc, byte[] cover){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO comic VALUES (NULL, ?, ?, ?)";
//        String sql = "CREATE TABLE IF NOT EXISTS Comic(idComic integer PRIMARY KEY AUTOINCREMENT, title text, description text, cover blob)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, title);
        statement.bindString(2, desc);
        statement.bindBlob(3, cover);

        statement.executeInsert();
    }

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
