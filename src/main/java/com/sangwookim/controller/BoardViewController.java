package com.sangwookim.controller;

import com.sangwookim.domain.Criteria;
import com.sangwookim.service.BoardService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Log4j
@RequestMapping("/board/")
public class BoardViewController {


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@RequestParam("category") String category, Criteria criteria, Model model) {
        //cri의 기본생성자 실행됨
        model.addAttribute("category", category);
        model.addAttribute("criteria", criteria);
        return "board/list";
    }

    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String write(@RequestParam("category") String category, Model model) {
        model.addAttribute("category", category);
        return "board/write";
    }


    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public String read(@RequestParam("category")String category, @RequestParam("id") Long id, Criteria criteria, Model model) {
        log.info("/read");
        model.addAttribute("category", category);
        model.addAttribute("id", id);
        model.addAttribute("criteria", criteria);
        return "board/read";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String modify(@RequestParam("category")String category, @RequestParam("id") Long id, Criteria criteria, Model model) {
        log.info("/modify");
        model.addAttribute("category", category);
        model.addAttribute("id", id);
        model.addAttribute("criteria", criteria);
        return "board/modify";
    }


}
