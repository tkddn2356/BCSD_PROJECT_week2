package com.sangwookim.repository;

import com.sangwookim.domain.Reply;
import com.sangwookim.domain.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMapper {
    public String getId(String id);
    public int insert(User user);
    public User getUser(String id);
    public int update(User user);
}
