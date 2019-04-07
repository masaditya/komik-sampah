package com.adityaeka.sampahisasi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.adityaeka.sampahisasi.adapter.ChapterAdapter;
import com.adityaeka.sampahisasi.models.Chapter;

import java.util.ArrayList;

public class ReadComicActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ChapterAdapter chapterAdapter;
    private ArrayList<Chapter> chapterArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_comic);

        getData();

        recyclerView = findViewById(R.id.rv_read_comic);
        chapterAdapter = new ChapterAdapter(chapterArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ReadComicActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(chapterAdapter);

    }

    private void getData() {


    }
}
