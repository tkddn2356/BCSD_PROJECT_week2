package com.sangwookim.service;

import com.sangwookim.domain.Paging;
import com.sangwookim.domain.Reply;

import java.util.List;

public interface ReplyService {
    public int register(Reply reply);
    public Reply read(Long id);
    public int modify(Reply reply);
    public int remove(Long id);
//    public List<Reply> getList(Criteria cri, Long board_id);
    public List<Reply> getList(int page, Long board_id);
    public int getTotalReply(Long board_id);
//    public ReplyPage getListPaging(Criteria cri, Long board_id);

    public Paging getReplyPage(int page, Long board_id);
}
