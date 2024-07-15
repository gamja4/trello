package com.gamja.trello.aop;

import com.gamja.trello.common.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ControllerAspect {

    @Around("execution(* com.gamja.trello.controller.*.*(..))")
    public Object response(ProceedingJoinPoint joinPoint) throws Throwable {
        Object res = joinPoint.proceed();
        log.info( joinPoint.getSignature().getName()+ "() controller 실행 완료");
        if (res instanceof ResponseEntity response) {
             return CommonResponse.builder()
                    .status(response.getStatusCode().value())
                    .msg("성공하였습니다.")
                    .data(response.getBody())
                    .build().toResponseEntity();
        }
        return res;
    }
}
