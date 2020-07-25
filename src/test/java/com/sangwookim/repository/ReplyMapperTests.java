//package com.sangwookim.repository;
//
//import com.sangwookim.domain.Criteria;
//import com.sangwookim.domain.Reply;
//import lombok.extern.log4j.Log4j;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"file:web/WEB-INF/dispatcher-servlet.xml", "classpath*:common/*.xml"})
//@Log4j
//@Repository
//public class ReplyMapperTests {
//    @Autowired
//    private ReplyMapper mapper;
//
////    @Test
////    public void TestInsert() {
////        ReplyVO reply = new ReplyVO();
////        reply.setBoard_id(8L);
////        reply.setContent("새로 작성하는 댓글");
////        reply.setWriter("새로운 댓글 유저");
////        mapper.insert(reply);
////    }
//
//    @Test
//    public void TestRead() {
//        mapper.read(1L);
//    }
//
//    @Test
//    public void TestGetList(){
//        mapper.getList(8L);
//    }
//
//    @Test
//    public void TestDelete() {
//        mapper.delete(2L);
//    }
//
//    @Test
//    public void TestUpdate() {
//        Reply reply = new Reply();
//        reply.setContent("수정한 댓글");
//        reply.setId(3L);
//        mapper.update(reply);
//    }
//
//    @Test
//    public void TestGetListPaging() {
//        Criteria cri = new Criteria();
//        mapper.getListPaging(cri, 8L);
//    }
//}
