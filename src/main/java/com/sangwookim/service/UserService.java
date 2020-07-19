package com.sangwookim.service;

import com.sangwookim.domain.User;

public interface UserService {
    public boolean checkUserId(String id);
    public int addUser(User user);
    public boolean login(User user);
    public User getLoginUser(String id);
    public boolean modify(User user);

}
