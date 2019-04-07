package com.adityaeka.sampahisasi;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.adityaeka.sampahisasi.adapter.ChapterAdapter;
import com.adityaeka.sampahisasi.models.Chapter;
import com.adityaeka.sampahisasi.models.Comic;

import java.util.ArrayList;

public class ReadComicActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ChapterAdapter chapterAdapter;
    private ArrayList<Chapter> chapterArrayList;
    private int idComic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_comic);
        int comic = getIntent().getExtras().getInt("idReadComic", 1);

        recyclerView = findViewById(R.id.rv_read_comic);
        chapterArrayList = new ArrayList<>();
        Cursor cursor = AddComicActivity.dbComic.getData("SELECT * FROM Chapter WHERE idComic = "+comic);
        chapterArrayList.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            byte[] image = cursor.getBlob(1);
            int idComic = cursor.getInt(2);

            chapterArrayList.add(new Chapter(id, image, idComic));
        }
        chapterAdapter = new ChapterAdapter( chapterArrayList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ReadComicActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(chapterAdapter);
    }
}
