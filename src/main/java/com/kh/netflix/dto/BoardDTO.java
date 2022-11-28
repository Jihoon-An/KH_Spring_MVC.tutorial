package com.kh.netflix.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;


public class BoardDTO {
    private int freeBoard_seq;
    private String writer;
    private String title;
    private String content;
    private Timestamp write_date;
    private int view_count;

    public BoardDTO() {}

    public BoardDTO(int freeBoard_seq, String writer, String title, String content, Timestamp write_date, int view_count) {
        this.freeBoard_seq = freeBoard_seq;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.write_date = write_date;
        this.view_count = view_count;
    }

    public BoardDTO(ResultSet resultSet) throws SQLException {
        this.freeBoard_seq = resultSet.getInt("freeBoard_seq");
        this.writer = resultSet.getString("writer");
        this.title = resultSet.getString("title");
        this.content = resultSet.getString("content");
        this.write_date = resultSet.getTimestamp("write_date");
        this.view_count = resultSet.getInt("view_count");
    }

    public int getFreeBoard_seq() {
        return freeBoard_seq;
    }

    public void setFreeBoard_seq(int freeBoard_seq) {
        this.freeBoard_seq = freeBoard_seq;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getWrite_date() {
        return write_date;
    }

    public void setWrite_date(Timestamp write_date) {
        this.write_date = write_date;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }
}
