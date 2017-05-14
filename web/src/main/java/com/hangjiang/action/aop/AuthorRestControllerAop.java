package com.hangjiang.action.aop;

import com.hangjiang.util.JsonUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by jianghang on 2017/5/13.
 */
@Component
@Aspect
public class AuthorRestControllerAop {

    private static Logger logger = LoggerFactory.getLogger(AuthorRestControllerAop.class);

    @Pointcut("execution(public * com.hangjiang.action.controller..*.*(..))")
    public void webLog(){

    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        logger.info("url: " + request.getRequestURL().toString());
        logger.info("http_method: " + request.getMethod());
        logger.info("ip: " + request.getRemoteAddr() + ":" + request.getRemotePort());
        logger.info("args: " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "object",pointcut = "webLog()")
    public void doAfterReturning(Object object) throws Throwable{
        logger.info("response: " + JsonUtil.obj2Json(object));
    }

    @Around("execution(public * com.hangjiang.action.controller..*.find*(..))")
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
        logger.info("around name: " + proceedingJoinPoint.getSignature().getName());
        try {
            Object obj = proceedingJoinPoint.proceed();
            return obj;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return null;
    }
}
