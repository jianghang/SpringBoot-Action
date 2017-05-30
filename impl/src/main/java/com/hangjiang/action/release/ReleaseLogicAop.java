package com.hangjiang.action.release;

import com.hangjiang.action.domain.release.ReleaseContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * Created by jianghang on 2017/5/29.
 */
@Component
@Aspect
public class ReleaseLogicAop {

    private static Logger logger = LoggerFactory.getLogger(ReleaseLogicAop.class);

    @Pointcut("execution(public * com.hangjiang.action.release.task..*.*(..))")
    public void webLog(){

    }

    @Around("execution(public * com.hangjiang.action.release.task..*.execute*(..))")
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
        try {
            Object obj = proceedingJoinPoint.getArgs()[0];
            ReleaseContext releaseContext = (ReleaseContext) obj;
            StopWatch stopWatch = releaseContext.getStopWatch();
            stopWatch.start(proceedingJoinPoint.getTarget().getClass().getName());

            proceedingJoinPoint.proceed();

            stopWatch.stop();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return null;
    }

}
