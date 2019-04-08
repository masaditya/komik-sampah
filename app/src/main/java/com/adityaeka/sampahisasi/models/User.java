package com.adityaeka.sampahisasi.models;

public class User {

    public String username, password;
    public int idUser;

    public User(int idUser, String username, String password) {
        this.username = username;
        this.password = password;
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
