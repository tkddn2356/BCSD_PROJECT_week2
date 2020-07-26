package com.sangwookim.controller;

import com.sangwookim.domain.Board;
import com.sangwookim.domain.Criteria;
import com.sangwookim.domain.Message;
import com.sangwookim.service.MessageService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Log4j
@RequestMapping("/message/")
public class MessageController {

    @Autowired
    private MessageService service;

    @ResponseBody
    @RequestMapping(value ="/list/{mode}/{user_id}", method= RequestMethod.GET, consumes = "application/json")
    public ResponseEntity<List<Message>> getList(@PathVariable("mode")String mode, @PathVariable("user_id") String user_id){
        log.info("mode = " + mode);
        log.info("user_id = " + user_id);
        return new ResponseEntity<>(service.getList(mode, user_id), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method=RequestMethod.GET)
    public ResponseEntity<Message> read(@PathVariable("id") Long id){
        log.info("id = " + id);
        return new ResponseEntity<>(service.read(id), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value="/write",  method =  RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> write(@RequestBody Message message){
        log.info("message =  " + message);
        return service.write(message) ? new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,
            consumes = "application/json")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return service.remove(id) ? new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @RequestMapping(value="/check/{id}",  method =  RequestMethod.GET)
    public ResponseEntity<String> check(@PathVariable("id")Long id){
        log.info("id =  " + id);
        return service.check(id) ? new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }







}
