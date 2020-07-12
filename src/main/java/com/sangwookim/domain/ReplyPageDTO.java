package com.sangwookim.domain;

import java.util.List;

public class ReplyPageDTO {
    private int TotalReply;
    private List<ReplyVO> ReplyList;

    public ReplyPageDTO(int totalReply, List<ReplyVO> replyList) {
        TotalReply = totalReply;
        ReplyList = replyList;
    }

    public int getTotalReply() {
        return TotalReply;
    }

    public List<ReplyVO> getReplyList() {
        return ReplyList;
    }

    public void setTotalReply(int totalReply) {
        TotalReply = totalReply;
    }

    public void setReplyList(List<ReplyVO> replyList) {
        ReplyList = replyList;
    }
}
