package com.sangwookim.domain;

public class Criteria {
    private int pageNum;
    private int amount;
    private int start;

    private String type;
    private String keyword;

    public Criteria(){
        this(1,10);
    }

    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
        this.start = (pageNum-1) * amount;
    }



    public int getPageNum() {
        return pageNum;
    }

    public int getAmount() {
        return amount;
    }

    public int getStart() {
        return start;
    }

    public String getType() {
        return type;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setStart(){
        this.start = (this.pageNum-1) * this.amount;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
