package com.example.listsongs;

public class Song {
    private String nameSong, Author;
    private int srcImg, url;

    public Song(String nameSong, int srcImg, int url, String Author) {
        this.nameSong = nameSong;
        this.url = url;
        this.srcImg = srcImg;
        this.Author = Author;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public Song(String nameSong, int srcImg) {
        this.nameSong = nameSong;
        this.srcImg = srcImg;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }

    public int getSrcImg() {
        return srcImg;
    }

    public void setSrcImg(int srcImg) {
        this.srcImg = srcImg;
    }
}
