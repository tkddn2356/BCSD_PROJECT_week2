package com.sangwookim.controller;

import com.sangwookim.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
public class MainController {

    @RequestMapping(value = "/",  method = RequestMethod.GET)
    public String home(){
        return "main";
    }

    @RequestMapping(value = "/main",  method = RequestMethod.GET)
    public String main(){
        return "main";
    }
}
