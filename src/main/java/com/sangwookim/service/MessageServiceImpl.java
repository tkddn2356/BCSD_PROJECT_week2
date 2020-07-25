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
        return mapper.insert(message) == 1;

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

    @Override
    public boolean check(Long id) {
        HttpSession session = request.getSession();
        User loginUser = (User)session.getAttribute("loginUser"); // 세션은 object로 받아지기 때문에 user로 형변환함.
        Message message = mapper.read(id);
        if(message.getRecipient_id().equals(loginUser.getId())){
            return mapper.check(id) == 1;
        }
        return false;
    }
}
