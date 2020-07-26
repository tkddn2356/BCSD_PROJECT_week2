package com.sangwookim.service;


import com.sangwookim.domain.Board;
import com.sangwookim.domain.Message;
import com.sangwookim.domain.User;
import com.sangwookim.repository.MessageMapper;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.pkcs11.wrapper.CK_LOCKMUTEX;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@Log4j
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper mapper;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

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
    public Message read(Long id) {
        log.info("read....");
        return mapper.read(id);
    }



    @Override
    public boolean write(Message message) {
        log.info("message write" + message);
        if(userService.checkLoginUser(message.getSender_id()) && (mapper.insert(message) == 1) ){
            return true;
        }else
            return false;

    }

    @Override
    public boolean modify(Message message) {
        log.info("modify......" + message);
        if(userService.checkLoginUser(message.getSender_id()) && (mapper.update(message) == 1) ){
            return true;
        }else
            return false;
    }

    @Override
    public boolean remove(Long id) {
        log.info("remove......" + id);
        Message message = mapper.read(id);
        if(userService.checkLoginUser(message.getSender_id()) && (mapper.delete(id) == 1) ){
            return true;
        }else
            return false;
    }

    @Override
    public boolean check(Long id) {
        Message message = mapper.read(id);
        if(userService.checkLoginUser(message.getRecipient_id()) && (mapper.check(id) == 1) ){
            return true;
        }else
            return false;
    }

    @Override
    public int getUncheckMessage(String recipient_id) {
        return mapper.getUncheckMessage(recipient_id);
    }
}
