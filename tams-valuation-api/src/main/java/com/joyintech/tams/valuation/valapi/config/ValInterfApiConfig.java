package com.joyintech.tams.valuation.valapi.config;/**
 * @author yanya
 * @create 2021-02-02 11:02
 */

import com.joyintech.tams.valuation.valapi.intercept.ValInterfApiInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName ValInterfApiConfig
 * @Description: 配置估值apiConfig
 * @Author yanya
 * @Date 2021/2/3
 * @Version V1.0
 **/
@Configuration
public class ValInterfApiConfig {

    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisorValApi() {
        ValInterfApiInterceptor interceptor = new ValInterfApiInterceptor();
        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
        pointcut.setPatterns("com.joyintech.tams.valuation.valapi.*");
        // 配置增强类advisor
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(interceptor);
        return advisor;
    }
}
