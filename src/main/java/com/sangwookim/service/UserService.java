package com.sangwookim.service;

import com.sangwookim.domain.User;

public interface UserService {
    public boolean checkUserId(String id);
    public boolean addUser(User user);
    public boolean login(User user);
    public User getLoginUser(String id);
    public boolean modify(User user);

}
