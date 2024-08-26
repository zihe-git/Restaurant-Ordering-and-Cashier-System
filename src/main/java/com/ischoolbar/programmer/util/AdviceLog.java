package com.ischoolbar.programmer.util;


import org.aopalliance.intercept.Joinpoint;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;

/**
 * 是一个针对日志记录的切面类，用于实现日志的前置增强和后置增强
 */
public class AdviceLog {
    private static final Logger log=Logger.getLogger(AdviceLog.class);

    /**
     * 前置增强
     */
    public void before(JoinPoint joinpoint){
        log.info("~~~~~~~~~~~~~~~~~~~~调用"+joinpoint.getTarget().getClass()+"的"+joinpoint.getSignature().getName()+
                "方法开始~~~~~~~~~~~~~~~~~~~~");
    }
    /**
     * 后置增强
     */
    public void afterReturning(JoinPoint joinpoint){
        log.info("~~~~~~~~~~~~~~~~~~~~调用"+joinpoint.getTarget().getClass()+"的"+joinpoint.getSignature().getName()+
                "方法结束~~~~~~~~~~~~~~~~~~~~");
    }


}
