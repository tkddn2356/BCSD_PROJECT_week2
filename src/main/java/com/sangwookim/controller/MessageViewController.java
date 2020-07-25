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
public class MessageViewController {


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@RequestParam("mode") String mode,  Model model) {
        model.addAttribute("mode", mode);
        log.info("mode = " + mode);
        return "message/list";
    }

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public String read(@RequestParam("mode") String mode, @RequestParam("id")Long id, Model model) {
        model.addAttribute("mode", mode);
        model.addAttribute("id", id);
        return "message/read";
    }

    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String write() {
        return "message/write";
    }


}
