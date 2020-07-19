package com.sangwookim.service;


import com.sangwookim.domain.Message;
import com.sangwookim.repository.MessageMapper;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.pkcs11.wrapper.CK_LOCKMUTEX;

import java.util.List;

@Service
@Log4j
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper mapper;

//    @Override
//    public List<Message> getSendList(String send_id) {
//        log.info("getSendList..." + send_id);
//        return mapper.getSendList(send_id);
//    }
//
//    @Override
//    public List<Message> getReceiveList(String recipient_id) {
//        log.info("getReceiveList..." + recipient_id);
//        return mapper.getReceiveList(recipient_id);
//    }

    @Override
    public List<Message> getList(String mode, String user_id) {
        log.info("getList...");
        log.info("mode = " + mode);
        log.info("user_id = " +user_id);
        if(mode.equals("send")){
            return mapper.getSendList(user_id);
        }
        else if(mode.equals("receive")){
            return mapper.getReceiveList(user_id);
        }
        else{
            return null;
        }

    }

    @Override
    public void write(Message message) {
        log.info("message write" + message);
        mapper.insert(message);

    }

    @Override
    public boolean modify(Message message) {
        log.info("modify......" + message);
        return mapper.update(message) == 1;
    }

    @Override
    public boolean remove(Long id) {
        log.info("remove......" + id);
        return mapper.delete(id) == 1;
    }
}
