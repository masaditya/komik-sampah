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

//    CREATE

    public void insertData(String title, String desc, byte[] cover){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO Comic VALUES (NULL, ?, ?, ?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, title);
        statement.bindString(2, desc);
        statement.bindBlob(3, cover);

        statement.executeInsert();
    }

//   READ

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }


//    UPDATE
    public void updateData(String title, String desc, byte[] cover, int id){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "UPDATE Comic SET title = ?, description = ?, cover = ? WHERE idComic = ?";
        SQLiteStatement statement =  database.compileStatement(sql);
        statement.bindString(1, title);
        statement.bindString(2, desc);
        statement.bindBlob(3, cover);
        statement.bindDouble(4, id);

        statement.execute();
        database.close();
    }

//    DELETE
    public void deleteData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        SQLiteStatement statement = database.compileStatement(sql);
        statement.execute();
        database.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

//    CREATE CHAPTER

    public void insertChapter(byte[] chapter, int idComic){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO Chapter VALUES (NULL, ?, ?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindBlob(1, chapter);
        statement.bindDouble(2, idComic);

        statement.executeInsert();
    }

}
