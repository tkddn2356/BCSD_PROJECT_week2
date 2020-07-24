package com.sangwookim.domain;

import java.sql.Timestamp;

public class Reply {
    private Long id;
    private Long board_id;
    private String content;
    private String writer;
    private Timestamp created_at;
    private Timestamp updated_at;
    private String user_id;

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public Long getId() {
        return id;
    }

    public Long getBoard_id() {
        return board_id;
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

    public void setBoard_id(Long board_id) {
        this.board_id = board_id;
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

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
}
