package com.sangwookim.controller;

import com.sangwookim.domain.Criteria;
import com.sangwookim.service.MessageService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Log4j
@RequestMapping("/message/")
public class MessageController {

    @Autowired
    private MessageService service;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@RequestParam("mode") String mode, @RequestParam("user_id")String user_id, Model model) {
        model.addAttribute("mode", mode);
        model.addAttribute("list", service.getList(mode, user_id));
        log.info("mode = " + mode);
        log.info("user_id = " + user_id);
        log.info("list" + service.getList(mode, user_id));
        return "message/list";
    }

    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String write(@RequestParam("mode") String mode, Model model) {
        model.addAttribute("mode", mode);
        return "message/write";
    }


}
