package com.sangwookim.interceptor;

import com.sangwookim.domain.User;
import lombok.extern.log4j.Log4j;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Log4j
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session  = request.getSession();
        if(session.getAttribute("loginUser") != null){
            log.info("세션 초기화");
            session.removeAttribute("loginUser");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession session = request.getSession();
        ModelMap modelMap = modelAndView.getModelMap();
        User loginUser = (User) modelMap.get("loginUser");
        if (loginUser.getName() != null) {
            session.setAttribute("loginUser", loginUser);
            log.info("로그인세션등록성공");
        }
    }



}
