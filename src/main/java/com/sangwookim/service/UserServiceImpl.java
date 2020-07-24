package com.sangwookim.service;


import com.sangwookim.domain.User;
import com.sangwookim.repository.UserMapper;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.annotations.Result;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
@Log4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper mapper;

    @Autowired
    private HttpServletRequest request;


    @Override
    public boolean checkUserId(String id) {
        User checkUser = mapper.getUser(id);
        if (checkUser == null) {
            log.info("중복확인완료");
            return true;
        } else
            return false;
    }

    @Override
    public boolean addUser(User user) {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        return mapper.insert(user) == 1;
    }


//    @Override
//    public boolean login(User user) {
//        HttpSession session = request.getSession(); // request 요청을 받고 세션을 생성함.
//        if (session.getAttribute("loginUser") != null) {
//            session.removeAttribute("loginUser");
//        } // 만약 로그인세션이 이미 존재하고 있었으면 그 로그인세션을 삭제
//        User userInfo = mapper.getUser(user.getId());
//        if (userInfo != null && BCrypt.checkpw(user.getPassword(), userInfo.getPassword())) { // 데이터베이스상에 있는 비밀번호와 일치하면 실행.
//            User loginUser = new User();
//            loginUser.setId(userInfo.getId());
//            loginUser.setName(userInfo.getName());
//            session.setAttribute("loginUser", loginUser);
//            // 비밀번호는 빼고 아이디와 이름만 가진 user객체를 세션에 넣는다. 세션에 회원의 비밀번호는 왠지 넣으면 안될거같아서...
//            log.info("로그인성공");
//            return true;
//        } else
//            return false;
//    }

    @Override
    public boolean login(User user, HttpServletResponse response) {
        HttpSession session = request.getSession(); // request 요청을 받고 세션을 생성함.
        if (session.getAttribute("loginUser") != null) {
            session.removeAttribute("loginUser");
        } // 만약 로그인세션이 이미 존재하고 있었으면 그 로그인세션을 삭제
        User userInfo = mapper.getUser(user.getId());
        if (userInfo != null && BCrypt.checkpw(user.getPassword(), userInfo.getPassword())) { // 데이터베이스상에 있는 비밀번호와 일치하면 실행.
            User loginUser = new User();
            loginUser.setId(userInfo.getId());
            loginUser.setName(userInfo.getName());
            session.setAttribute("loginUser", loginUser);
            // 비밀번호는 빼고 아이디와 이름만 가진 user객체를 세션에 넣는다. 세션에 회원의 비밀번호는 왠지 넣으면 안될거같아서...
            if(user.isRemember_me()){
                String hashedId = BCrypt.hashpw(user.getId(), BCrypt.gensalt());
                Cookie rememberCookie = new Cookie("remember_me", hashedId);
                rememberCookie.setPath("/"); // 웹어플리케이션의 모든 URL 범위에서 전송
                rememberCookie.setMaxAge(20*60); // 20분
                response.addCookie(rememberCookie);
                mapper.keepLogin(user.getId(), hashedId); // 유저의 remember_id에 hashedId를 업데이트한다
            }
            log.info("로그인성공");
            return true;
        } else
            return false;
    }


    @Override
    public boolean modify(User user) {
        User userInfo = mapper.getUser(user.getId());
        if (userInfo != null && BCrypt.checkpw(user.getPassword_prev(), userInfo.getPassword())) { // 만약 데이터베이스에 있는 유저정보와 일치하면 실행
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            user.setPassword(hashedPassword);
            mapper.update(user);
            return true;
        } else
            return false;
    }

    @Override
    public boolean logout() {
        HttpSession session = request.getSession();
        Object object = session.getAttribute("loginUser");
        if (object != null) {
            session.removeAttribute("loginUser");
            session.invalidate();
            log.info("로그아웃 성공");
            return true;
        }
        return false;
    }

    @Override
    public int keepLogin(String id, String remember_id) {
        log.info("keepLogin....");
        return mapper.keepLogin(id, remember_id);
    }

//    @Override
//    public boolean checkPassword(User user) {
//        User userInfo = mapper.getUser(user.getId());
//        log.info("checkPasword...");
//        if (userInfo != null && BCrypt.checkpw(user.getPassword(), userInfo.getPassword())) {
//            return true;
//        } else
//            return false;
//    }




}
