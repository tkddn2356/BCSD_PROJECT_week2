package com.sangwookim.service;

import com.sangwookim.domain.*;
import com.sangwookim.repository.BoardMapper;
import com.sangwookim.repository.ReplyMapper;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Transactional
@Service
@Log4j
public class ReplyServiceImpl implements ReplyService{

    @Autowired
    private ReplyMapper mapper;

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private UserService userService;


    @Override
    public boolean register(Reply reply) {
        log.info("register......" + reply);
        if(userService.checkLoginUser(reply.getUser_id()) && (mapper.insert(reply) == 1) ){
            boardMapper.updateReplyCount(reply.getBoard_id(), 1); // 게시글의 총 댓글수 업데이트
            return true;
        }
        return false;
    }

    @Override
    public Reply read(Long id) {
        log.info("read......" + id);
        return mapper.read(id);
    }

    @Override
    public boolean modify(Reply reply) {
        Reply reply_prev = mapper.read(reply.getId());
        if(userService.checkLoginUser(reply_prev.getUser_id()) && (mapper.update(reply) == 1) ){
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Long id) {
        Reply reply = mapper.read(id);
        log.info("remove......" + reply);
        if(userService.checkLoginUser(reply.getUser_id()) && (mapper.delete(id) == 1) ){
            boardMapper.updateReplyCount(reply.getBoard_id(), -1);
            return true;
        }
        return false;
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


    @Override
    public Paging getReplyPage(int page, Long board_id) {
        Criteria cri = new Criteria(page, 10);
        int total = mapper.getTotalReply(board_id);
        return new Paging(cri, total);
    }// 페이징 정보에 대해 가져온다.

}
