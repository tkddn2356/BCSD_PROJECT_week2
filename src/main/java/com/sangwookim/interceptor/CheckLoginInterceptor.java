package com.sangwookim.interceptor;

import lombok.extern.log4j.Log4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Log4j
public class CheckLoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session  = request.getSession();
        if(session.getAttribute("loginUser") == null){
            log.info("로그인안됨");
            response.sendRedirect("/user/login");
            return false;
        }
        return true;
    }
}
