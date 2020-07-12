package com.sangwookim.service;

import com.sangwookim.domain.Criteria;
import com.sangwookim.domain.ReplyPageDTO;
import com.sangwookim.domain.ReplyVO;
import com.sangwookim.repository.ReplyMapper;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j
public class ReplyServiceImpl implements ReplyService{

    @Autowired
    private ReplyMapper mapper;

    @Override
    public int register(ReplyVO reply) {
        log.info("register......" + reply);
        return mapper.insert(reply);
    }

    @Override
    public ReplyVO read(Long id) {
        log.info("read......" + id);
        return mapper.read(id);
    }

    @Override
    public int modify(ReplyVO reply) {
        log.info("modify......" + reply);
        return mapper.update(reply);
    }

    @Override
    public int remove(Long id) {
        log.info("remove......" + id);
        return mapper.delete(id);
    }

    @Override
    public List<ReplyVO> getList(Criteria cri, Long board_id) {
        log.info("get Reply List of a Board " + board_id);
        return mapper.getListPaging(cri, board_id);
    }

    @Override
    public int getTotalReply(Long board_id){
        return mapper.getTotalReply(board_id);
    }

    @Override
    public ReplyPageDTO getListPaging(Criteria cri, Long board_id) {
        return new ReplyPageDTO(
                mapper.getTotalReply(board_id),
                mapper.getListPaging(cri, board_id));
    }


}
