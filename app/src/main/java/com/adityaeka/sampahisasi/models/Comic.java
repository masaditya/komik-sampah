package com.adityaeka.sampahisasi.models;

public class Comic {

    private int idComic;
    private String title, description;
    private byte[] cover;


    public Comic(int idComic, String title, String description, byte[] cover) {
        this.idComic = idComic;
        this.title = title;
        this.description = description;
        this.cover = cover;
    }


    public int getIdComic() {
        return idComic;
    }

    public void setIdComic(int idComic) {
        this.idComic = idComic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getCover() {
        return cover;
    }

    public void setCover(byte[] cover) {
        this.cover = cover;
    }
}
