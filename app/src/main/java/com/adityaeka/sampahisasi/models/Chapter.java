package com.adityaeka.sampahisasi.models;

public class Chapter {


    private int idChapter, idComic;
    private byte[] chapter;

    public Chapter(int idChapter, byte[] chapter, int idComic ) {
        this.idChapter = idChapter;
        this.chapter = chapter;
        this.idComic = idComic;
    }

    public int getIdChapter() {
        return idChapter;
    }

    public void setIdChapter(int idChapter) {
        this.idChapter = idChapter;
    }

    public int getIdComic() {
        return idComic;
    }

    public void setIdComic(int idComic) {
        this.idComic = idComic;
    }

    public byte[] getChapter() {
        return chapter;
    }

    public void setChapter(byte[] chapter) {
        this.chapter = chapter;
    }
}
