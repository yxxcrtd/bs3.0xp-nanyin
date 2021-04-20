package com.joyintech.background.component.common.pack.common.security;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.joyintech.background.component.common.pack.common.base.BaseEntity;
import com.joyintech.background.component.common.pack.common.util.StringUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Properties;

/**
 * @author 何阳阳
 * 用户信息拦截器
 */
@Component
@Aspect
public class UserInfoInterceptor {

    /**
     * 拦截点
     */
    @Pointcut("execution(* com.joyintech.background.component.uplink.interbank..*Service+.*(..))")
    public void excute() {}

    /**
     * 接口方法执行开始，绑定线程变量
     * @param joinPoint
     */
    @Before(value = "excute()")
    public void beforeExcute(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            for(Object arg : args) {
                if(arg instanceof User) {
                    User user = (User)arg;
                    UserUtil.bindUser(user);
                }
            }
        }
    }

    /**
     * 接口方法执行完毕，清理线程变量
     * 无论是正常结束还是异常结束都会进入该方法
     * @param joinPoint
     */
    @After(value = "excute()")
    public void afterExcute(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            for(Object arg : args) {
                if(arg instanceof User) {
                    UserUtil.clear();
                }
            }
        }
    }
}
