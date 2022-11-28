package com.kh.netflix.dto;

import java.sql.Timestamp;

public class MoviesDTO {
    private int id;
    private String title;
    private String genre;
    private Timestamp launch_date;

    public MoviesDTO() {
    }

    public MoviesDTO(int seq, String title, String genre, Timestamp launch_date) {
        this.id = seq;
        this.title = title;
        this.genre = genre;
        this.launch_date = launch_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Timestamp getLaunch_date() {
        return launch_date;
    }

    public void setLaunch_date(Timestamp launch_date) {
        this.launch_date = launch_date;
    }
}
