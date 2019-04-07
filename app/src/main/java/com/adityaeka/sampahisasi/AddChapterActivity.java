package com.adityaeka.sampahisasi;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.adityaeka.sampahisasi.db.DbComic;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddChapterActivity extends AppCompatActivity implements View.OnClickListener {

    public static DbComic dbComic;
    Button btnChooseChapter, btnAddChapter;
    ImageView ivChapter;
    int comicId;
    final int REQUEST_CODE_GALLERY = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chapter);

        btnChooseChapter = findViewById(R.id.btn_choose_chapter);
        btnAddChapter = findViewById(R.id.btn_add_chapter);
        ivChapter = findViewById(R.id.iv_chapteritem);

        dbComic = new DbComic(this, "Comic.db", null, 1);

        dbComic.queryData("CREATE TABLE IF NOT EXISTS Chapter(idChapter integer PRIMARY KEY AUTOINCREMENT, chapter blob, idComic integer, FOREIGN KEY (idComic) REFERENCES Comic(idComic))");

        comicId = getIntent().getIntExtra("idComic", 0);

        btnAddChapter.setOnClickListener(this);
        btnChooseChapter.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_choose_chapter:
                ActivityCompat.requestPermissions(
                        AddChapterActivity.this,
                        new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
                break;
            case R.id.btn_add_chapter:
                try{
                    dbComic.insertChapter(
                            imageViewToByte(ivChapter),
                            comicId
                    );
                    Toast.makeText(getApplicationContext(), "Added New Chapter", Toast.LENGTH_SHORT).show();
//                    etTitle.setText("");
//                    etDesc.setText("");
                    ivChapter.setImageResource(R.mipmap.ic_launcher_round);

                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.btn_showListComic:
                Intent intent = new Intent(AddChapterActivity.this, ListComicActivity.class);
                startActivity(intent);
                break;
        }
    }

    private byte[] imageViewToByte(ImageView ivCover) {
        Bitmap bitmap = ((BitmapDrawable)ivCover.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bytes = stream.toByteArray();
        return bytes;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_GALLERY){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else{
                Toast.makeText(getApplicationContext(), "You dont have permission", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                ivChapter.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
