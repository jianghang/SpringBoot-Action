package com.hangjiang.action.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created by jianghang on 2017/12/14.
 */
public class ControllerInterceptor extends HandlerInterceptorAdapter{

    private Logger logger = LoggerFactory.getLogger(ControllerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUUID = MDC.get("requestUUID");
        if(requestUUID == null || "".equals(requestUUID)){
            String uuid = UUID.randomUUID().toString();
            uuid = uuid.replaceAll("-","").toUpperCase();
            MDC.put("requestUUID",uuid);
//            logger.info("ControllerInterceptor preHandle 在请求处理之前生成 logback requestUUID:{}",uuid);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        MDC.remove("requestUUID");
//        logger.info("ControllerInterceptor postHandle 请求处理之后清除 logback MDC requestUUID");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.clear();
//        logger.info("ControllerInterceptor afterCompletion 整个请求处理完毕清除 logback MDC requestUUID");
    }
}
