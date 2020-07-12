package com.sangwookim.controller;


import com.sangwookim.domain.Criteria;
import com.sangwookim.domain.ReplyPageDTO;
import com.sangwookim.domain.ReplyVO;
import com.sangwookim.service.ReplyService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.RequestHeaderMapMethodArgumentResolver;

import java.util.List;

@Controller
@Log4j
@RequestMapping("/reply/*")
public class ReplyController {

    @Autowired
    private ReplyService service;

    @ResponseBody
    @RequestMapping(value="/write",  method =  RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> write(@RequestBody ReplyVO reply){
        log.info("Reply =  " + reply);
        return service.register(reply) == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ResponseBody
//    @RequestMapping(value = "/list/{board_id}/{page}", method = RequestMethod.GET)
//    public ResponseEntity<List<ReplyVO>> getList(@PathVariable("board_id") Long board_id, @PathVariable("page") int page){
//        Criteria cri = new Criteria(page, 5);
//        log.info("cri = "+ cri);
//        log.info("board_id = " + board_id);
//        return new ResponseEntity<>(service.getList(cri, board_id), HttpStatus.OK);
//    }

    @ResponseBody
    @RequestMapping(value = "/list/{board_id}/{page}", method = RequestMethod.GET)
    public ResponseEntity<ReplyPageDTO> getList(@PathVariable("board_id") Long board_id, @PathVariable("page") int page){
        Criteria cri = new Criteria(page, 10);
        log.info("cri = "+ cri);
        log.info("board_id = " + board_id);
        return new ResponseEntity<>(service.getListPaging(cri, board_id), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method=RequestMethod.GET)
    public ResponseEntity<ReplyVO> read(@PathVariable("id") Long id) {
        log.info("get = " + id);
        return new ResponseEntity<>(service.read(id), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<String> remove(@PathVariable("id") Long id) {
        log.info("remove = " + id);
        return service.remove(id) == 1
                ? new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = { RequestMethod.PUT, RequestMethod.PATCH },
            consumes = "application/json")
    public ResponseEntity<String> modify(@RequestBody ReplyVO reply, @PathVariable("id") Long id) {
        reply.setId(id);
        log.info("id = " + id);
        log.info("modify = " + reply);
        return service.modify(reply) == 1
                ? new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
