package com.sangwookim.service;

import com.sangwookim.domain.Board;
import com.sangwookim.domain.Criteria;
import com.sangwookim.domain.Paging;
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
    public void write(Board board) {
        log.info("write....." + board);
        mapper.insert(board);
    }

//    @Override
//    public List<BoardVO> getList() {
//        log.info("getList.....");
//        return mapper.getList();
//    }

    @Override
    public List<Board> getList(Criteria cri, String category) {
        log.info("getList.....");
        cri.setStart();
        return mapper.getList(cri, category);
    }

//    @Override
//    public List<Board> getListPaging(Criteria cri, String category)
//    {
//        log.info("getListPaging");
//        return mapper.getListPaging(cri, category);
//    }

    //    @Override
//    public BoardVO read(Long id) {
//        log.info("read...."+  id);
//        return mapper.read(id);
//    }
    @Override
    public Board read(Long id) {
        log.info("read...." + id);
        return mapper.read(id);
    }


    @Override
    public boolean modify(Board board) {
        log.info("modify......" + board);
        return mapper.update(board) == 1;
    }

    @Override
    public boolean remove(Long id) {
        log.info("remove...." + id);
        return mapper.delete(id) == 1;
    }

    @Override
    public int getBoardTotal(String category)
    {
        log.info("getBoardTotal....");
        return mapper.getBoardTotal(category);
    }

    @Override
    public Paging getBoardPage(Criteria cri, String category) {
        int total = mapper.getBoardTotal(category);
        return new Paging(cri, total);
    }

    @Override
    public int getHitTotal(Long id) {
        return mapper.getHit(id);
    }

    @Override
    public int getHit_notTotal(Long id) {
        return mapper.getHit_not(id);
    }

    @Override
    public void updateHit(Long id) {
        mapper.updateHit(id, 1);
    }

    @Override
    public void updateHit_not(Long id) {
        mapper.updateHit_not(id, 1);
    }

}
