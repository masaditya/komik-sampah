package com.adityaeka.sampahisasi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.adityaeka.sampahisasi.db.DbComic;

public class AddChapterActivity extends AppCompatActivity {

    DbComic dbComic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chapter);



        dbComic = new DbComic(this, "Comic.db", null, 1);

        dbComic.queryData("CREATE TABLE IF NOT EXISTS " +
                "Chapter(idChapter integer PRIMARY KEY AUTOINCREMENT" +
                ", chapter blob, idComic integer, FOREIGN KEY (idComic) REFERENCES Comic(idComic)");

    }
}
