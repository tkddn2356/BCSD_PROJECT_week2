package com.sangwookim.interceptor;

import com.sangwookim.domain.User;
import com.sangwookim.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Log4j
public class RememberMeInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserService service;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie rememberCookie = WebUtils.getCookie(request, "remember_me");
        if(rememberCookie != null){
            log.info("remember_me의 값 = " + rememberCookie.getValue());
            User user = service.getUserBefore(rememberCookie.getValue());
            log.info("remember_me_user = " + user.getId());
            service.loginBefore(user);
            log.info("재로그인 완료");
        }
        return true;
    }

}
