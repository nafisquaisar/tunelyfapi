package com.tunelyf.nf.model;

public class Song {

    private Long id;
    private String title;
    private String artist;
    private String genre;
    private String url;
    private String thumbnail;
    private boolean hidden;
    private int views;
    private String createdAt;

    public Song() {}

    public Song(Long id, String title, String artist, String genre, String url, String thumbnail, boolean hidden, int views, String createdAt) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.url = url;
        this.thumbnail = thumbnail;
        this.hidden = hidden;
        this.views = views;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
