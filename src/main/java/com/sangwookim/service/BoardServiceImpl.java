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
    private HttpServletRequest request; // 이 방식은 request의 프록시를 가져오는 방법이라고함.

    //HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder .getRequestAttributes()).getRequest();
    //위 방법은 ThreadLocal을 활용해서 이용한다고 하는데.. 둘의 차이점이 뭔지는 잘 모르겠다.

    @Override
    public boolean write(Board board) {
        HttpSession session = request.getSession();
        User loginUser = (User)session.getAttribute("loginUser"); // 세션은 object로 받아지기 때문에 user로 형변환함.
        log.info(loginUser.getId());
        log.info("write....." + board);
        if(board.getUser_id().equals(loginUser.getId()) && (mapper.insert(board) == 1) ){
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
        HttpSession session = request.getSession();
        User loginUser = (User)session.getAttribute("loginUser"); // 세션은 object로 받아지기 때문에 user로 형변환함.
        log.info(loginUser.getId());
        log.info("modify......" + board);
        if(board.getUser_id().equals(loginUser.getId()) && (mapper.update(board) == 1) ){
            // 로그인한 유저의 아이디와 board로 들어오는 id가 같고 modify가 성공적으로 끝났을때만 true
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean remove(Long id) {
        HttpSession session = request.getSession();
        User loginUser = (User)session.getAttribute("loginUser"); // 세션은 object로 받아지기 때문에 user로 형변환함.
        Board board = mapper.read(id);
        log.info("remove...." + board);
        if(board.getUser_id().equals(loginUser.getId()) && (mapper.delete(board) == 1) ){
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

//    @Override
//    public int getHitTotal(Long id) {
//        return mapper.getHit(id);
//    }
//
//    @Override
//    public int getHit_notTotal(Long id) {
//        return mapper.getHit_not(id);
//    }
//
//    @Override
//    public void updateHit(Long id) {
//        mapper.updateHit(id, 1);
//    }
//
//    @Override
//    public void updateHit_not(Long id) {
//        mapper.updateHit_not(id, 1);
//    }

}
