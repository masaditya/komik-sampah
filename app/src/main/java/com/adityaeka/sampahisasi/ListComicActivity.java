package com.adityaeka.sampahisasi;

import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.adityaeka.sampahisasi.adapter.ComicListAdapter;
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
            byte[] image = cursor.getBlob(3);


            comics.add(new Comic(id, title, desc, image));
        }
        adapter.notifyDataSetChanged();

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                CharSequence[] items = {"Update", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(ListComicActivity.this);
                dialog.setTitle("Chooose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which==0){
//                            update func



                            Toast.makeText(getApplicationContext(), "Update", Toast.LENGTH_SHORT).show();

                        }else {
//                            delete func
                            Toast.makeText(getApplicationContext(), "Delete", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();
                return true;
            }

        });


    }
}
