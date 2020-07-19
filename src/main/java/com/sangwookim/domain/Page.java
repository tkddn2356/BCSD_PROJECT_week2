package com.sangwookim.domain;

public class Page {
    private int startPage;
    private int endPage;
    private boolean prev, next;
    private Criteria cri;
    private int total;

    public Page(Criteria cri, int total)
    {
        this.cri = cri;
        this.total = total;
        endPage = (int)(Math.ceil(cri.getPageNum()/10.0)) * 10;
        startPage = endPage -9;
        int realEndPage = (int)(Math.ceil(total / (double)cri.getAmount()));
        if(this.endPage > realEndPage)
        {
            this.endPage = realEndPage;
        }
        if(this.endPage < realEndPage)
        {
            next = true;
        }
        if(startPage > 1)
        {
            prev = true;
        }
    }

    public int getStartPage() {
        return startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public boolean isPrev() {
        return prev;
    }

    public boolean isNext() {
        return next;
    }

    public Criteria getCri() {
        return cri;
    }

    public int getTotal() {
        return total;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public void setPrev(boolean prev) {
        this.prev = prev;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public void setCri(Criteria cri) {
        this.cri = cri;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
