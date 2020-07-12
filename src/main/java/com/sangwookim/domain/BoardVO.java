package com.sangwookim.domain;

import lombok.Data;

public class BoardVO {
    private Long id;
    private Long board_info_id;
    private String title;
    private String content;
    private String writer;
    private String regdate;
    private String updateDate;

    public Long getId() {
        return id;
    }

    public Long getBoard_info_id() {
        return board_info_id;
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

    public String getRegdate() {
        return regdate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBoard_info_id(Long board_info_id) {
        this.board_info_id = board_info_id;
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

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
