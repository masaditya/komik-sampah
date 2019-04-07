package com.adityaeka.sampahisasi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adityaeka.sampahisasi.adapter.ChapterAdapter;
import com.adityaeka.sampahisasi.models.Chapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadComicFragment extends Fragment {

    ChapterAdapter chapterAdapter = null;
    ArrayList<Chapter> chapters;
    RecyclerView recyclerView;



    public ReadComicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        int idRead = getArguments().getInt("idRead");


        chapters = new ArrayList<>();
        chapterAdapter = new ChapterAdapter(chapters);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        return inflater.inflate(R.layout.fragment_read_comic, container, false);
    }

}
