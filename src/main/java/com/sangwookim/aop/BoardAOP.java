package com.sangwookim.aop;

import lombok.extern.log4j.Log4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j
public class BoardAOP {
    @Around("execution(* com.sangwookim.service.BoardService.getList(..))")
    public Object getListAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        try{
            log.info("Around-pre-getList");
            result = joinPoint.proceed();
            log.info("Around-after-getList");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.info("에러발생");
        } finally {
            log.info("Around 실행완료");
        }
        return result;
    }
    @Before("execution(* com.sangwookim.service.BoardService.write(..))")
    public void writeBefore(JoinPoint joinPoint) {
        log.info("After-"+joinPoint.getSignature().getName());
    }
    @After("execution(* com.sangwookim.service.BoardService.write(..))")
    public void writeAfter(JoinPoint joinPoint) {
        log.info("Before-"+joinPoint.getSignature().getName());
    }

    @AfterReturning("execution(* com.sangwookim.service.BoardService.write(..))")
    public void writeAfterReturning(JoinPoint joinPoint) {
        log.info("AfterReturning-"+joinPoint.getSignature().getName());
    }
}