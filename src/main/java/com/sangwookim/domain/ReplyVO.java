package com.sangwookim.domain;

public class ReplyVO {
    private Long id;
    private Long board_id;
    private String content;
    private String writer;
    private String regdate;
    private String updatedate;
    private Long parent;

    public Long getParent() {
        return parent;
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

    public String getRegdate() {
        return regdate;
    }

    public String getUpdatedate() {
        return updatedate;
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

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public void setUpdatedate(String updatedate) {
        this.updatedate = updatedate;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }
}
