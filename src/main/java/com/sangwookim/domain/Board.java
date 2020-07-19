package com.sangwookim.domain;

import lombok.Data;

import java.sql.Timestamp;

public class Board {
    private Long id;
    private String category;
    private String title;
    private String content;
    private String writer;
    private Timestamp created_at;
    private Timestamp updated_at;

    private int reply_count;

    private int hit;
    private int hit_not;

    private String user_id;

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public void setHit_not(int hit_not) {
        this.hit_not = hit_not;
    }

    public int getHit() {
        return hit;
    }

    public int getHit_not() {
        return hit_not;
    }

    public void setReply_count(int reply_count) {
        this.reply_count = reply_count;
    }

    public int getReply_count() {
        return reply_count;
    }

    public Long getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getWriter() {
        return writer;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(Timestamp update_at) {
        this.updated_at = update_at;
    }
}

