package com.sangwookim.controller;

import com.sangwookim.domain.User;
import com.sangwookim.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@Log4j
@RequestMapping("/user/")
public class UserViewController {

    @RequestMapping(value="/join", method = RequestMethod.GET)
    public String join(){
        return "user/join";
    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(){
        return "user/login";
    }


    @RequestMapping(value="/modify", method = RequestMethod.GET)
    public String modify(){
        return "user/modify";
    }


    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logout(){
        return "/main";
    }
}
