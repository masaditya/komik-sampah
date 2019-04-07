package com.adityaeka.sampahisasi;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.adityaeka.sampahisasi.adapter.ChapterAdapter;
import com.adityaeka.sampahisasi.adapter.ComicListAdapter;
import com.adityaeka.sampahisasi.models.Chapter;
import com.adityaeka.sampahisasi.models.Comic;

import java.util.ArrayList;

public class ListComicActivity extends AppCompatActivity {

    GridView gridView;
    ArrayList<Comic> comics;
    ComicListAdapter adapter = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_comic);

        gridView = findViewById(R.id.gv_comic);
        comics = new ArrayList<>();
        adapter = new ComicListAdapter(this, R.layout.comic_items, comics);
        gridView.setAdapter(adapter);





//        get all data
        Cursor cursor = AddComicActivity.dbComic.getData("SELECT * FROM Comic");
        comics.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String desc = cursor.getString(2);
            byte[] cover = cursor.getBlob(3);


            comics.add(new Comic(id, title, desc, cover));
        }
        adapter.notifyDataSetChanged();

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                CharSequence[] items = {"Update", "Delete", "Add Chapter"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(ListComicActivity.this);
                dialog.setTitle("Chooose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which==0){
//                            update func

                                Cursor c = AddComicActivity.dbComic.getData("SELECT idComic FROM Comic");
                                ArrayList<Integer> arrId = new ArrayList<Integer>();
                                while (c.moveToNext()){
                                    arrId.add(c.getInt(0));
                                }

                            int id = arrId.get(position);
                            Intent i = new Intent(ListComicActivity.this, AddComicActivity.class);
                            i.putExtra("updateId", id);
                            startActivity(i);

                            Toast.makeText(getApplicationContext(), "Update id "+id, Toast.LENGTH_SHORT).show();

                        }else if(which == 1){
//                            delete func
                            Toast.makeText(getApplicationContext(), "Delete", Toast.LENGTH_SHORT).show();
                        }else {

                            Cursor c = AddComicActivity.dbComic.getData("SELECT idComic FROM Comic");
                            ArrayList<Integer> arrIdComic = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrIdComic.add(c.getInt(0));
                            }
//
                            int id = arrIdComic.get(position);
                            Intent intentAddChapter = new Intent(ListComicActivity.this, AddChapterActivity.class);
                            intentAddChapter.putExtra("idComic", id);
                            startActivity(intentAddChapter);
                            Toast.makeText(getApplicationContext(), "Add Chapter comic id "+id, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();
                return true;
            }

        });


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor c = AddComicActivity.dbComic.getData("SELECT idComic FROM Comic");
                ArrayList<Integer> arrId = new ArrayList<Integer>();
                while (c.moveToNext()){
                    arrId.add(c.getInt(0));
                }

                int idReadComic = arrId.get(position);
                Intent intentRead = new Intent(ListComicActivity.this, ReadComicActivity.class);
                intentRead.putExtra("idReadComic", idReadComic);
                startActivity(intentRead);

            }
        });

    }

//    private void showDialogUpdate(Activity activity){
//        Dialog dialog = new Dialog(activity);
//        dialog.setContentView(R.layout.dialog_update_comic);
//
//        dialog.setTitle("Update Comic");
//        ImageView ivUpdateCover = dialog.findViewById(R.id.iv_update_cover);
//        EditText etUpdateTitle = dialog.findViewById(R.id.et_update_title);
//        EditText etUpdateDesc = dialog.findViewById(R.id.et_desc);
////        Button btnUpdate = dialog.findViewById(R.id.btn_update);
//
//        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.9);
//        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.7);
//
//        dialog.getWindow().setLayout(width, height);
//        dialog.show();
//
////        ivUpdateCover.setOnClickListener(this);
//
//    }

}
