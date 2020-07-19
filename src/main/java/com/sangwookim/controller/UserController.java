package com.sangwookim.controller;

import com.sangwookim.domain.User;
import com.sangwookim.service.UserService;
import com.sangwookim.validator.UserValidator;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Controller
@Log4j
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private UserService service;

//    @Resource(name = "loginUser")
//    private User loginUser;

    @RequestMapping(value="/join", method = RequestMethod.GET)
    public String join(@ModelAttribute("joinUser") User user){
        return "user/join";
    }

    @RequestMapping(value="/join", method = RequestMethod.POST)
    public String join_pro(@Valid @ModelAttribute("joinUser") User user, BindingResult result){
        if(result.hasErrors()) {
            return "/user/join";
        }
        service.addUser(user);
        return "/user/login";
    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(@ModelAttribute("loginUser") User user){
        return "user/login";
    }

    @RequestMapping(value="/login_pro", method = RequestMethod.POST)
    public String login_pro(@Valid @ModelAttribute("loginUser") User user, BindingResult result, Model model){
        if(result.hasErrors() || !service.login(user))
        {
            log.info("로그인실패");
            model.addAttribute("loginResult","fail");
            return "/user/login";
        }
        else if(service.login(user)) {
            log.info("로그인완료");
            model.addAttribute("loginUser",service.getLoginUser(user.getId()));
        }
        return "/main";
    }

    @RequestMapping(value="/modify", method = RequestMethod.GET)
    public String modify(@ModelAttribute("modifyUser") User user){
        return "user/modify";
    }

    @RequestMapping(value="/modify_pro", method = RequestMethod.POST)
    public String modify_pro(@Valid @ModelAttribute("modifyUser") User user, BindingResult result, Model model){
        if(result.hasErrors()|| !service.modify(user))
        {
            log.info("수정실패");
            model.addAttribute("modifyResult","fail");
            return "/user/modify";
        }
        else if(service.modify(user)) {
            log.info("수정성공");
        }
        return "/main";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logout(){
        return "/main";
    }


    @ResponseBody
    @RequestMapping(value="/checkUserId/{id}", method = RequestMethod.GET)
    public String checkUserId(@PathVariable("id")String id){
        boolean check = service.checkUserId(id);
        return check+"";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        UserValidator validator = new UserValidator();
        binder.addValidators(validator);
    }
}
