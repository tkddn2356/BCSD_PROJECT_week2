package com.sangwookim.domain;

import javafx.util.converter.TimeStringConverter;

import java.sql.Time;
import java.sql.Timestamp;

public class Message {
    private Long id;
    private String recipient_id;
    private String sender_id;
    private String title;
    private String content;
    private Timestamp created_at;
    private Timestamp updated_at;

    public Long getId() {
        return id;
    }

    public String getRecipient_id() {
        return recipient_id;
    }

    public String getSender_id() {
        return sender_id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
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

    public void setRecipient_id(String recipient_id) {
        this.recipient_id = recipient_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
}
