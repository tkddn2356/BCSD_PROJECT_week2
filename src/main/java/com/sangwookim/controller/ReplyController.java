package com.sangwookim.controller;


import com.sangwookim.domain.Paging;
import com.sangwookim.domain.Reply;
import com.sangwookim.service.ReplyService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Log4j
@RequestMapping("/reply/")
public class ReplyController {
    @Autowired
    private ReplyService service;

    @ResponseBody
    @RequestMapping(value="/write",  method =  RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> write(@RequestBody Reply reply){
        log.info("Reply =  " + reply);
        return service.register(reply) ? new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @RequestMapping(value = "/list/{board_id}/{page}", method = RequestMethod.GET)
    public ResponseEntity<List<Reply>> getList(@PathVariable("board_id") Long board_id, @PathVariable("page") int page){
        log.info("page = "+ page);
        log.info("board_id = " + board_id);
        return new ResponseEntity<>(service.getList(page, board_id), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method=RequestMethod.GET)
    public ResponseEntity<Reply> read(@PathVariable("id") Long id) {
        log.info("get = " + id);
        return new ResponseEntity<>(service.read(id), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<String> remove(@PathVariable("id") Long id) {
        log.info("remove = " + id);
        return service.remove(id)
                ? new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH,
            consumes = "application/json")
    public ResponseEntity<String> modify(@RequestBody Reply reply, @PathVariable("id") Long id) {
        reply.setId(id);
        log.info("id = " + id);
        log.info("modify = " + reply);
        return service.modify(reply)
                ? new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ResponseBody
    @RequestMapping(value = "/page/{board_id}/{page}", method = RequestMethod.GET)
    public ResponseEntity<Paging> getReplyPage(@PathVariable("board_id") Long board_id, @PathVariable("page") int page){
        return new ResponseEntity<>(service.getReplyPage(page,board_id), HttpStatus.OK);
    }
}
