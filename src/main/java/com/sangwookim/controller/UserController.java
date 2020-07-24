package com.sangwookim.controller;

import com.sangwookim.domain.User;
import com.sangwookim.service.UserService;
import lombok.extern.log4j.Log4j;
import org.mindrot.jbcrypt.BCrypt;
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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public ResponseEntity<String> login(@Valid @RequestBody User user, BindingResult result, HttpServletResponse response){
        if(result.hasErrors()) {
            new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(service.login(user)){
            if(user.isRemember_me()){
                String hashedId = BCrypt.hashpw(user.getId(), BCrypt.gensalt());
                Cookie rememberCookie = new Cookie("remember_me", hashedId);
                rememberCookie.setPath("/"); // 웹어플리케이션의 모든 URL 범위에서 전송
                rememberCookie.setMaxAge(20*60); // 20분
                response.addCookie(rememberCookie);
                service.keepLogin(user.getId(), hashedId); // 유저의 remember_id에 hashedId를 업데이트한다
            }
            return new ResponseEntity<>("success!", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ResponseBody
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public ResponseEntity<String> logout(){
        return service.logout() ? new ResponseEntity<>("success!", HttpStatus.OK) :
                new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @RequestMapping(value="/modify", method = RequestMethod.PATCH)
    public ResponseEntity<String> modify(@RequestBody User user){
        return service.modify(user) ? new ResponseEntity<>("success!", HttpStatus.OK) :
                new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
