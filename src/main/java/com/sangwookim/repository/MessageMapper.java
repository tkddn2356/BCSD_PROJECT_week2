package com.sangwookim.repository;

import com.sangwookim.domain.Message;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageMapper {
    public List<Message> getSendList(String send_id);
    public List<Message> getReceiveList(String recipient_id);
    public Message read(Long id);
    public int insert(Message message);
    public int delete(Long id);
    public int update(Message message);

    public int check(Long id);

    public int getUncheckMessage(Long id);

}
