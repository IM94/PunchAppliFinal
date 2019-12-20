package fr.imane.punchAppFinal.model;

import com.google.gson.annotations.SerializedName;

public class Punchline {

    @SerializedName("author")
    private String author;


    @SerializedName("album")
    private String album;


    @SerializedName("title")
    private String title;

    @SerializedName("content")
    private String content;

    public Punchline(String author, String album, String title, String content) {
        this.author = author;
        this.album = album;
        this.title = title;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public String getAlbum() {
        return album;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
