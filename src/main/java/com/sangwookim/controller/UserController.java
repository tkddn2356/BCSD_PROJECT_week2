package com.sangwookim.controller;

import com.sangwookim.domain.User;
import com.sangwookim.service.UserService;
import com.sangwookim.validator.UserValidator;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
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

    @ResponseBody
    @RequestMapping(value="/join", method = RequestMethod.POST)
    public ResponseEntity<String>join(@Validated @RequestBody User user, BindingResult result){
        log.info(user);
        if(result.hasErrors()) {
            new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return service.addUser(user) ? new ResponseEntity<>("success", HttpStatus.OK) :
                new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @RequestMapping(value="/login_pro", method = RequestMethod.POST)
//    public String login_pro(@Valid @ModelAttribute("loginUser") User user, BindingResult result, Model model){
//        if(result.hasErrors() || !service.login(user))
//        {
//            log.info("로그인실패");
//            model.addAttribute("loginResult","fail");
//            return "/user/login";
//        }
//        else if(service.login(user)) {
//            log.info("로그인완료");
//            model.addAttribute("loginUser",service.getLoginUser(user.getId()));
//        }
//        return "/main";
//    }
//
//    @RequestMapping(value="/modify_pro", method = RequestMethod.POST)
//    public String modify_pro(@Valid @ModelAttribute("modifyUser") User user, BindingResult result, Model model){
//        if(result.hasErrors()|| !service.modify(user))
//        {
//            log.info("수정실패");
//            model.addAttribute("modifyResult","fail");
//            return "/user/modify";
//        }
//        else if(service.modify(user)) {
//            log.info("수정성공");
//        }
//        return "/main";
//    }
//
//    @ResponseBody
//    @RequestMapping(value="/checkUserId/{id}", method = RequestMethod.GET)
//    public String checkUserId(@PathVariable("id")String id){
//        boolean check = service.checkUserId(id);
//        return check+"";
//    }
//
//    @InitBinder
//    public void initBinder(WebDataBinder binder){
//        UserValidator validator = new UserValidator();
//        binder.addValidators(validator);
//    }
}
