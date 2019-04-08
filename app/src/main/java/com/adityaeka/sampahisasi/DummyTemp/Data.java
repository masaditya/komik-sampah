package com.adityaeka.sampahisasi.DummyTemp;

import com.adityaeka.sampahisasi.models.User;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private static List<User> users;

    static {
        users = new ArrayList<>();
        users.add(new User(1,"adi", "rahasia"));
        users.add(new User(2,"beni", "rahasia"));
        users.add(new User(3, "cindy", "rahasia"));

    }

    public static List<User> getUsers() {
        return users;
    }
}
