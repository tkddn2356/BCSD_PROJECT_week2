package com.sangwookim.service;

import com.sangwookim.domain.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    public boolean checkUserId(String id);
    public boolean addUser(User user);
//    public boolean login(User user);
    public boolean login(User user, HttpServletRequest request);
    public User getLoginUser(String id);
    public boolean modify(User user);
    public boolean logout(HttpServletRequest request);

}
