package com.sangwookim.service;

import com.sangwookim.domain.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {
    public boolean checkUserId(String id);
    public boolean addUser(User user);
    public boolean login(User user);
//    public boolean login(User user, HttpServletRequest request);
//    public User getLoginUser(String id);
    public boolean modify(User user);
//    public boolean logout();
    public boolean logout(HttpServletResponse response);
//    public boolean logout(HttpServletRequest request);


//    public boolean checkPassword(User user);

    public int keepLogin(String id, String remember_id);

    public User getUserBefore(String remember_id);

    public boolean loginBefore(User user);

}
