package com.sangwookim.aop;


import lombok.extern.log4j.Log4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.Arrays;
import java.util.List;

@Aspect
@Component
@Log4j
public class ValidCheck {
    @Around("execution(* com.sangwookim.controller.UserController.*pro(..))")
    public Object Check(ProceedingJoinPoint joinPoint) throws Throwable{
        log.info("AOP실행!");
        for(Object result : joinPoint.getArgs()){
            if(result instanceof BindingResult){
                List<ObjectError> list = ((BindingResult) result).getAllErrors();
                for(ObjectError e :list){
                    log.info(e.getDefaultMessage());
                    //디폴트메시지 말고 커스텀한 메시지를 띄울 수 있는 방법 없나요...
                }
            }
        }
        Object result = null;
        result = joinPoint.proceed();
        return result;
    }

}
