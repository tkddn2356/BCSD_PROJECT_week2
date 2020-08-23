package com.sangwookim.controller;

import com.sangwookim.util.SesMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MailSendController {
    @Autowired
    SesMailSender mailSender;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public ResponseEntity<String> send() {
        mailSender.sendMail("TestSender@tkddn2356.com", "tkddn2356@gmail.com", "테스트용 메일입니다", "<h1>안녕하세요</h1>"
                + "<p>말하자면 테스트라는 것입니다. <a href='https://aws.amazon.com/ses/'>"
                + "블로그</a> 링크입니다 많이 찾아와주세요");
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
