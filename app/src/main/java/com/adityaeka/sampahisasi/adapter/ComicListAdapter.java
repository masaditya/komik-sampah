package com.adityaeka.sampahisasi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.adityaeka.sampahisasi.R;
import com.adityaeka.sampahisasi.models.Comic;

import java.util.ArrayList;

public class ComicListAdapter extends BaseAdapter {


    private Context context;
    private int layout;
    private ArrayList<Comic> comicArrayList;

    public ComicListAdapter(Context context, int layout, ArrayList<Comic> comicArrayList) {
        this.context = context;
        this.layout = layout;
        this.comicArrayList = comicArrayList;
    }

    @Override
    public int getCount() {
        return comicArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return comicArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        ImageView ivCover;
        TextView tvTitle, tvDesc;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ViewHolder holder = new ViewHolder();

        if (row==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.tvTitle = row.findViewById(R.id.tv_itemTitle);
            holder.tvDesc = row.findViewById(R.id.tv_itemDesc);
            holder.ivCover = row.findViewById(R.id.iv_itemCover);
            row.setTag(holder);
        }



        return null;
    }
}
