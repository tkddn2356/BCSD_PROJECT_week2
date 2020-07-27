package com.sangwookim.service;

import com.sangwookim.domain.Board;
import com.sangwookim.domain.Criteria;
import com.sangwookim.domain.Paging;
import com.sangwookim.domain.User;
import com.sangwookim.repository.BoardMapper;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@Log4j
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardMapper mapper;

    @Autowired
    private UserService userService;

    @Override
    public boolean write(Board board) {
        log.info("write....." + board);
        if(userService.checkLoginUser(board.getUser_id()) && (mapper.insert(board) == 1) ){
            // 로그인한 유저의 아이디와 board로 들어오는 id가 같고 insert가 성공적으로 끝났을때만 true
            return true;
        }
        else
            return false;
    }

    @Override
    public List<Board> getList(Criteria cri, String category) {
        log.info("getList.....");
        cri.setStart();
        return mapper.getList(cri, category);
    }

    @Override
    public Board read(Long id) {
        log.info("read...." + id);
        return mapper.read(id);
    }

    @Override
    public boolean modify(Board board) {
        log.info("modify......" + board);
        if(userService.checkLoginUser(board.getUser_id()) && (mapper.update(board) == 1) ){
            //세션에 있는 아이디와 board로 들어온 user_id가 일치하고 update가 성공적으로 이루어 졌을때 true 반환
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean remove(Long id) {
        Board board = mapper.read(id);
        log.info("remove...." + board);
        if(userService.checkLoginUser(board.getUser_id()) && (mapper.delete(board) == 1) ){
            // 로그인한 유저의 아이디와 board로 들어오는 id가 같고 remove가 성공적으로 끝났을때만 true
            return true;
        }
        else
            return false;
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



}
