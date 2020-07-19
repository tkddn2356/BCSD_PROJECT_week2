package com.sangwookim.service;


import com.sangwookim.domain.Board;
import com.sangwookim.domain.Criteria;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
@ContextConfiguration(locations = {"file:web/WEB-INF/dispatcher-servlet.xml", "classpath*:common/*.xml"})
public class BoardServiceTests {

    @Autowired
    private BoardService service;

//    @Test
//	public void testRegister() {
//
//		BoardVO board = new BoardVO();
//		board.setBoard_info_idx(1);
//		board.setTitle("새로 작성하는 글");
//		board.setContent("새로 작성하는 내용");
//		board.setWriter("newbie");
//		service.register(board);
//	}

//    @Test
//    public void testGetList() {
//        service.getList(1L).forEach(board->log.info(board));
//    }
//
//    @Test
//    public void testGetListPaging() {
//
////		service.getList().forEach(board -> log.info(board));
//        service.getListPaging(new Criteria(2, 10), 1L).forEach(board -> log.info(board));
//    }
//
//    @Test
//    public void testGetBoardTotal() {
//        service.getBoardTotal(1L);
//
//    }

}
