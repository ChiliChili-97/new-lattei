package com.sparta.project.icylattei.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j(topic = "LogAop")
public class LogAop {

    @Around("@within(com.sparta.project.icylattei.global.annotation.Logging)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Start - " + joinPoint.getSignature().getDeclaringTypeName() + " / " + joinPoint.getSignature().getName());
        try {
            Object output = joinPoint.proceed();
            log.info("End - " + joinPoint.getSignature().getDeclaringTypeName() + " / " + joinPoint.getSignature().getName());
            return output;
        } catch (Exception e) {
            log.error("Error - " + joinPoint.getSignature().getDeclaringTypeName() + " / " + joinPoint.getSignature().getName());
            throw e;
        }
    }
}
