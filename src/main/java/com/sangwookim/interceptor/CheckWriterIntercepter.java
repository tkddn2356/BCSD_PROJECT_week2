package com.sangwookim.interceptor;

import com.sangwookim.domain.User;
import com.sangwookim.service.BoardService;
import com.sangwookim.service.MessageService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Log4j
public class CheckWriterIntercepter extends HandlerInterceptorAdapter {

    @Autowired
    private BoardService service;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
                log.info("작성자체크인터셉터 실행");
                String str1 = request.getParameter("id");
                Long board_id = Long.parseLong(str1);
                if (session.getAttribute("loginUser") != null) { // 로그인이 되어있다면
                    User loginUser = (User)session.getAttribute("loginUser"); // 로그인유저 세션을 가져옴
                    if(!(loginUser.getId().equals(service.read(board_id).getUser_id()))){
                        response.sendRedirect("/");
            }
        }
        return true;
    }
}
