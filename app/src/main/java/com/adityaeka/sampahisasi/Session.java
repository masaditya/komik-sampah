package com.adityaeka.sampahisasi;

import android.database.Cursor;
import android.util.Log;

import com.adityaeka.sampahisasi.DummyTemp.Data;
import com.adityaeka.sampahisasi.models.Comic;
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
        for (User user : Data.getUsers()) {
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
