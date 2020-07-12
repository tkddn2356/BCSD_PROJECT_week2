package com.sangwookim.service;

import com.sangwookim.domain.Criteria;
import com.sangwookim.domain.ReplyPageDTO;
import com.sangwookim.domain.ReplyVO;

import java.util.List;

public interface ReplyService {
    public int register(ReplyVO reply);
    public ReplyVO read(Long id);
    public int modify(ReplyVO reply);
    public int remove(Long id);
    public List<ReplyVO> getList(Criteria cri, Long board_id);
    public int getTotalReply(Long board_id);
    public ReplyPageDTO getListPaging(Criteria cri, Long board_id);
}
