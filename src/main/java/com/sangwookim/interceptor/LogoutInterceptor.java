package com.sangwookim.interceptor;

import com.sangwookim.domain.User;
import lombok.extern.log4j.Log4j;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Log4j
public class LogoutInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session  = request.getSession();
        if(session.getAttribute("loginUser") == null){
            log.info("로그아웃 실패");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession session  = request.getSession();
        if(session.getAttribute("loginUser") != null){
            log.info("로그아웃 성공");
            session.removeAttribute("loginUser");
            session.invalidate();
        }
    }


}
