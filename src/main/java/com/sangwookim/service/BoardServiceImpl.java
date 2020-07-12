package com.sangwookim.service;

import com.sangwookim.domain.BoardVO;
import com.sangwookim.domain.Criteria;
import com.sangwookim.repository.BoardMapper;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardMapper mapper;

    @Override
    public void write(BoardVO board) {
        log.info("write....." + board);
        mapper.insert(board);
    }

//    @Override
//    public List<BoardVO> getList() {
//        log.info("getList.....");
//        return mapper.getList();
//    }

    @Override
    public List<BoardVO> getList(Long board_info_id) {
        log.info("getList.....");
        return mapper.getList(board_info_id);
    }

    @Override
    public List<BoardVO> getListPaging(Criteria cri, Long board_info_id)
    {
        log.info("getListPaging");
        return mapper.getListPaging(cri, board_info_id);
    }

    //    @Override
//    public BoardVO read(Long id) {
//        log.info("read...."+  id);
//        return mapper.read(id);
//    }
    @Override
    public BoardVO read(BoardVO board) {
        log.info("read...." + board);
        return mapper.read(board);
    }

    @Override
    public String getBoardInfoName(Long board_info_idx) {
        log.info("board_info_id...." + board_info_idx);
        return mapper.getBoardInfoName(board_info_idx);
    }

    @Override
    public boolean modify(BoardVO board) {
        log.info("modify......" + board);
        return mapper.update(board) == 1;
    }

    @Override
    public boolean remove(Long id) {
        log.info("remove...." + id);
        return mapper.delete(id) == 1;
    }

    @Override
    public int getBoardTotal(Long board_info_id)
    {
        log.info("getBoardTotal....");
        return mapper.getBoardTotal(board_info_id);
    }
}
