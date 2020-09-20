package com.sangwookim.aop;

import lombok.extern.log4j.Log4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.annotation.Annotation;

@Aspect
@Component
@Log4j
public class BoardAOP {
    @Around("execution(* com.sangwookim.service.BoardService.getList(..))")
    public Object getListAround(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        log.info("methodSignature =" +  methodSignature);
        log.info("getMethod =" + methodSignature.getMethod());
        for(Object args : joinPoint.getArgs()) // 파라미터로 뭐가 들어왔는지 확인
        {
            log.info("args = " + args);
        }
        Object result = null;
        try{
            log.info("Around-pre-getList");
            result = joinPoint.proceed();
            log.info("Around-after-getList");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.info("에러발생");
        } finally {
            log.info("Around 실행완료!!");
        }
        int index = -1;
        Annotation[][] annotationMatrix = methodSignature.getMethod().getParameterAnnotations();

        for(Annotation[] annotations : annotationMatrix){
            index++;
            for(Annotation annotation : annotations){
                if(!(annotation instanceof RequestBody)){ //RequestBody어노테이션이 있으면 Xss체크
                    System.out.println("!!!"+ annotation.annotationType().getCanonicalName());
                    continue;
                }
                System.out.println("@@@"+ annotation.annotationType().getCanonicalName());
//                proceedingJoinPoint.getArgs()[index] = xssCheck(proceedingJoinPoint.getArgs()[index]); // 들어온 파라미터 확인

                return joinPoint.proceed(joinPoint.getArgs());
            }
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