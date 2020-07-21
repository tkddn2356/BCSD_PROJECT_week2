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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
@Log4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper mapper;

    @Override
    public boolean checkUserId(String id) {
        User checkUser = mapper.getUser(id);
        if (checkUser == null){
            log.info("중복확인완료");
            return true;
        }
        else
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
//        User userInfo = mapper.getUser(user.getId());
//        if (userInfo != null && BCrypt.checkpw(user.getPassword(), userInfo.getPassword())) {
//            log.info("로그인성공");
//            return true;
//        } else
//            return false;
//    }

    @Override
    public boolean login(User user, HttpServletRequest request) {
        HttpSession session = request.getSession(); // request 요청을 받고 세션을 생성함.
        if ( session.getAttribute("loginUser") != null ){
            session.removeAttribute("loginUser");
        } // 만약 로그인세션이 이미 존재하고 있었으면 그 로그인세션을 삭제

        User userInfo = mapper.getUser(user.getId()); // bcrpyt된 비밀번호와 비교하기 위해 데이터베이스에서 정보를 가져온다.
       if (userInfo != null && BCrypt.checkpw(user.getPassword(), userInfo.getPassword())) {
            User loginUser = new User();
            loginUser.setId(userInfo.getId());
            loginUser.setName(userInfo.getName());
            session.setAttribute("loginUser", loginUser);
            // 비밀번호는 빼고 아이디와 이름만 가진 user객체를 세션에 넣는다.
            log.info("로그인성공");
            return true;
        } else
            return false;
    }

    @Override
    public User getLoginUser(String id) {
        User userInfo = mapper.getUser(id);
        User loginUser = new User();
        loginUser.setId(userInfo.getId());
        loginUser.setName(userInfo.getName());
        loginUser.setUser_login(true);
        return loginUser;
    }

    @Override
    public boolean modify(User user) {
        User userInfo = mapper.getUser(user.getId());
        if (BCrypt.checkpw(user.getPassword_prev(), userInfo.getPassword())) {
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            user.setPassword(hashedPassword);
            mapper.update(user);
            return true;
        } else
            return false;
    }// 이전 비밀번호 확인하는거랑 수정하는거랑 합쳤음.

    @Override
    public boolean logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object object = session.getAttribute("loginUser");
        if(object != null){
            session.removeAttribute("loginUser");
            session.invalidate();
            log.info("로그아웃 성공");
            return true;
        }
        return false;
    }


}
