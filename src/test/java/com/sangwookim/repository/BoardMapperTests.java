//package com.sangwookim.repository;
//
//
//import com.sangwookim.domain.Board;
//import com.sangwookim.domain.Criteria;
//import lombok.extern.log4j.Log4j;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"file:web/WEB-INF/dispatcher-servlet.xml", "classpath*:common/*.xml"})
//@Log4j
//@Repository
//public class BoardMapperTests {
//    @Autowired
//    private BoardMapper mapper;
//
//    @Test
//    public void testGetList() {
//        mapper.getList("자유게시판").forEach(board -> log.info(board));
//    }
//
////    @Test
////    public void testInsert() {
////        BoardVO board = new BoardVO();
////        board.setBoard_info_idx(1);
////        board.setTitle("새로 작성하는 글");
////        board.setContent("새로 작성하는 내용");
////        board.setWriter("새로운  유저");
////        mapper.insert(board);
////    }
//
////    @Test
////    public void testInsert() {
////        BoardVO board = new BoardVO();
////        board.setBoard_info_id(3L);
////        board.setTitle("새로 작성하는 글");
////        board.setContent("새로 작성하는 내용");
////        board.setWriter("새로운  유저");
////        mapper.insertSelectKey(board);
////    }
//
////    @Test
////    public void testRead(){
////        BoardVO board  = mapper.read(15L);
////        log.info(board);
////    }
//
////    @Test
////    public void testGetBoardInfoName(){
////        String board_info_name = mapper.getBoardInfoName(1L);
////        log.info(board_info_name);
////    }
//
////    @Test
////    public void testDelete() {
////        log.info("DELETE COUNT :" + mapper.delete(3));
////    }
//
////    @Test
////    public void testUpdate() {
////        BoardVO board = new BoardVO();
////        board.setId(1L);
////        board.setTitle("수정된 제목");
////        board.setContent("수정된 내용");
////        board.setWriter("테스트유저");
////        int count = mapper.update(board);
////        log.info("UPDATE COUNT: " + count);
////    }
////    @Test
////    public void testGetListPaging(){
////        Criteria cri = new Criteria();
////        mapper.getListPaging(cri, 1L).forEach(board -> log.info(board));
////    }
//
//
////    @Test
////    public void testGetListPaging(){
////        Criteria cri = new Criteria();
////        cri.setType("TC");
////        cri.setKeyword("테스트");
////        mapper.getListPaging(cri, "자유게시판").forEach(board -> log.info(board));
//
////    }
////    @Test
////    public void testGetBoardTotal(){
////        mapper.getBoardTotal(1L);
////    }
//}
