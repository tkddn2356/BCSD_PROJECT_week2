package com.sangwookim.service;

import com.sangwookim.domain.Page;
import com.sangwookim.domain.Criteria;
import com.sangwookim.domain.Reply;
import com.sangwookim.repository.BoardMapper;
import com.sangwookim.repository.ReplyMapper;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j
public class ReplyServiceImpl implements ReplyService{

    @Autowired
    private ReplyMapper mapper;

    @Autowired
    private BoardMapper boardMapper;

    @Transactional
    @Override
    public int register(Reply reply) {
        log.info("register......" + reply);
        boardMapper.updateReplyCount(reply.getBoard_id(), 1);
        return mapper.insert(reply);
    }

    @Override
    public Reply read(Long id) {
        log.info("read......" + id);
        return mapper.read(id);
    }

    @Override
    public int modify(Reply reply) {
        log.info("modify......" + reply);
        return mapper.update(reply);
    }

    @Transactional
    @Override
    public int remove(Long id) {
        log.info("remove......" + id);
        Reply reply = mapper.read(id);
        boardMapper.updateReplyCount(reply.getBoard_id(), -1);
        return mapper.delete(id);
    }

    @Override
    public List<Reply> getList(int page, Long board_id) {
        Criteria cri = new Criteria(page, 10);
        log.info("get Reply List of a Board " + board_id);
        return mapper.getListPaging(cri, board_id);
    }

    @Override
    public int getTotalReply(Long board_id){
        return mapper.getTotalReply(board_id);
    }

//
//    @Override
//    public ReplyPage getListPaging(Criteria cri, Long board_id) {
//        return new ReplyPage(
//                mapper.getTotalReply(board_id),
//                mapper.getListPaging(cri, board_id));
//    }

    @Override
    public Page getReplyPage(int page, Long board_id) {
        Criteria cri = new Criteria(page, 10);
        int total = mapper.getTotalReply(board_id);
        return new Page(cri, total);
    }

}
