package com.adityaeka.sampahisasi;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.adityaeka.sampahisasi.db.DbComic;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddComicActivity extends AppCompatActivity implements View.OnClickListener {


    public static DbComic dbComic;
    final int REQUEST_CODE_GALLERY = 999;

    EditText etTitle, etDesc;
    Button btnChoose, btnAddComic, btnShowList;
    ImageView ivCover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comic);

        etTitle = findViewById(R.id.et_title);
        etDesc = findViewById(R.id.et_desc);
        btnChoose = findViewById(R.id.btn_addCover);
        btnAddComic = findViewById(R.id.btn_addComic);
        btnShowList = findViewById(R.id.btn_showListComic);
        ivCover = findViewById(R.id.iv_cover);

        dbComic = new DbComic(this, "Comic.db", null, 1);

        dbComic.queryData("CREATE TABLE IF NOT EXISTS Comic(idComic integer PRIMARY KEY AUTOINCREMENT, title text, description text, cover blob)");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            final int isNew = extras.getInt("updateId", 0);
            btnAddComic.setText("UPDATE COMIC");
            btnAddComic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        dbComic.updateData(
                                etTitle.getText().toString().trim(),
                                etDesc.getText().toString().trim(),
                                imageViewToByte(ivCover),
                                isNew
                        );
                        Toast.makeText(getApplicationContext(), "Update New Comic id "+isNew, Toast.LENGTH_SHORT).show();
                        btnShowList.setText("CHECK UPDATE");
                        etTitle.setText("");
                        etDesc.setText("");
                        ivCover.setImageResource(R.mipmap.ic_launcher_round);

                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Gagal bro "+isNew, Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }else {
            btnAddComic.setOnClickListener(this);
        }


        btnChoose.setOnClickListener(this);

        btnShowList.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_addCover:
                ActivityCompat.requestPermissions(
                        AddComicActivity.this,
                        new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
                break;
            case R.id.btn_addComic:
                try{
                    dbComic.insertData(
                            etTitle.getText().toString().trim(),
                            etDesc.getText().toString().trim(),
                            imageViewToByte(ivCover)
                    );
                    Toast.makeText(getApplicationContext(), "Added New Comic", Toast.LENGTH_SHORT).show();
                    etTitle.setText("");
                    etDesc.setText("");
                    ivCover.setImageResource(R.mipmap.ic_launcher_round);

                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.btn_showListComic:
                Intent intent = new Intent(AddComicActivity.this, ListComicActivity.class);
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
                ivCover.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
