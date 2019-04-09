package com.adityaeka.sampahisasi.preferences;

import android.database.Cursor;

import com.adityaeka.sampahisasi.MainActivity;
import com.adityaeka.sampahisasi.models.User;

import java.util.ArrayList;

public class Session {
    private Settings settings;
    private String user;
    ArrayList<User> userArrayList;

    public Session(Settings settings) {
        this.settings = settings;
        user = settings.getUser();
    }

    public User doLogin(String username, String password) {

        User foundUser = null;
        userArrayList = new ArrayList<>();
        Cursor cursor = MainActivity.dbComic.getData("SELECT * FROM User");
        userArrayList.clear();
        while (cursor.moveToNext()){
            int iduser = cursor.getInt(0);
            String uname = cursor.getString(1);
            String pw = cursor.getString(2);

            userArrayList.add(new User(iduser, uname, pw));
        }


        for (User user : userArrayList) {
            if (username.equals(user.getUsername())
                    && password.equals(user.getPassword())) {
                foundUser = user;
                break;
            }
        }
        return foundUser;
    }

    public void doLogout() {
        settings.removeUser();
        this.user = null;
    }

    public boolean isLogin() {
        return user != null;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        settings.setUser(user);
        this.user = user;
    }
}
