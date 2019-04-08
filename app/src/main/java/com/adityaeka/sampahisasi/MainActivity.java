package com.adityaeka.sampahisasi;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.adityaeka.sampahisasi.db.DbComic;
import com.adityaeka.sampahisasi.models.User;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LoginFragment.OnLoginFragmentListener
{

    Button btnMove;
    public Session session;
    public Settings settings;
    DbComic dbComic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        btnMove=findViewById(R.id.btn_moveTemp);

        dbComic = new DbComic(this, "Comic.db", null, 1);
        dbComic.queryData("CREATE TABLE IF NOT EXISTS User(idUser integer PRIMARY KEY AUTOINCREMENT, username text, password text )");


        settings = new Settings(this);
        session = new Session(settings);

        addFragment();

//        btnMove.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, AddComicActivity.class);
        startActivity(intent);
    }

    private void addFragment() {
        Fragment fragment = null;
        if (session.isLogin()) {
            fragment = new HomeFragment();
        } else {
            fragment = new LoginFragment();
            ((LoginFragment) fragment).setListener(this);
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fc_container, fragment)
                .commit();
    }

    @Override
    public void onLoginClicked(View view, String username, String password) {
        User user = session.doLogin(username, password);
        String message = "Authentication failed";
        if (user != null) {
            message = "Welcome " + username;
            session.setUser(username);
        }
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
        addFragment();
    }
}
