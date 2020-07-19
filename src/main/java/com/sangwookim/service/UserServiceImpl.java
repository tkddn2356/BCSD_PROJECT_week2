package com.sangwookim.service;


import com.sangwookim.domain.User;
import com.sangwookim.repository.UserMapper;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.annotations.Result;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Service
@Log4j
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper mapper;

//    @Resource(name = "loginUser")
//    private User loginUser;

    @Override
    public boolean checkUserId(String id) {
        String user_id = mapper.getId(id);
        if(user_id == null){
            return true;
        } else
        {
            return false;
        }
    }

    @Override
    public int addUser(User user) {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        return mapper.insert(user);
    }

    @Override
    public boolean login(User user) {
        User userInfo = mapper.getUser(user.getId());
        if(userInfo != null && BCrypt.checkpw(user.getPassword(), userInfo.getPassword())){
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
        if(BCrypt.checkpw(user.getPassword_prev(), userInfo.getPassword())){
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            user.setPassword(hashedPassword);
            mapper.update(user);
            return true;
        }
        else
            return false;
    }// 이전 비밀번호 확인하는거랑 수정하는거랑 합쳤음.


}
