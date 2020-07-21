package com.sangwookim.controller;

import com.sangwookim.domain.User;
import com.sangwookim.service.UserService;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@Log4j
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private UserService service;

    @ResponseBody
    @RequestMapping(value="/checkUserExist/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> checkUserId(@PathVariable("id")String id){
        return service.checkUserId(id) ? new ResponseEntity<>("true", HttpStatus.OK) :
                new ResponseEntity<>("false", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @RequestMapping(value="/join", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> join(@Valid @RequestBody User user, BindingResult result){
        if(result.hasErrors()) {
            new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return service.addUser(user) ? new ResponseEntity<>("success!", HttpStatus.OK) :
                new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ResponseBody
    @RequestMapping(value="/login", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> login(@Valid @RequestBody User user, BindingResult result, HttpServletRequest request){
        if(result.hasErrors()) {
            new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return service.login(user, request) ? new ResponseEntity<>("success!", HttpStatus.OK) :
                new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ResponseBody
//    @RequestMapping(value="/login/{test}", method = RequestMethod.POST, consumes = "application/json")
//    public ResponseEntity<String> login(@Valid @RequestParame){
//        return new ResponseEntity<>("success!", HttpStatus.OK);
//    }

    @ResponseBody
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public ResponseEntity<String> logout(HttpServletRequest request){
        return service.logout(request) ? new ResponseEntity<>("success!", HttpStatus.OK) :
                new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }


//    @ResponseBody
//    @RequestMapping(value="/logout", method = RequestMethod.GET)
//    public ResponseEntity<String> logout(HttpServletRequest request){
//        return new ResponseEntity<>("success!", HttpStatus.OK);
//    }
//
//    @InitBinder
//    public void initBinder(WebDataBinder binder){
//        UserValidator validator = new UserValidator();
//        binder.addValidators(validator);
//    }
}
