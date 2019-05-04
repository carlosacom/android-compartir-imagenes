package com.example.helloword;

public class Plublication {
    private String id;
    private String title;
    private String image;
    private int like;

    public Plublication() {
    }

    public Plublication(String id, String title, String image, int like) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.like = like;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }
}
