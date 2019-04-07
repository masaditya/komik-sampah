package com.adityaeka.sampahisasi.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.adityaeka.sampahisasi.R;
import com.adityaeka.sampahisasi.models.Chapter;

import java.util.ArrayList;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ViewHolder> {

    ArrayList<Chapter> chapters;

    public ChapterAdapter(ArrayList<Chapter> chapters) {
        this.chapters = chapters;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.chapter_items, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Chapter chapter = chapters.get(i);
        byte[] chapt = chapter.getChapter();
        Bitmap bitmap = BitmapFactory.decodeByteArray(chapt, 0, chapt.length);
        viewHolder.chapterImage.setImageBitmap(bitmap);

    }

    @Override
    public int getItemCount() {
        return chapters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView chapterImage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            chapterImage = itemView.findViewById(R.id.chapter_image);

        }
    }
}
