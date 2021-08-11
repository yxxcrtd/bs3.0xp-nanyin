package com.joyintech.sso.login.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @auther zhangnian
 * @date 2021/5/12
 */
@Configuration
public class SsoWebAppConfigurer extends WebMvcConfigurerAdapter {


    @Resource
    private SsoInterceptor ssoInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(ssoInterceptor).addPathPatterns("/**");
    }

    /**
     * 允许跨域的域名，可以用*表示允许任何域名使用
     * 允许任何方法（post、get等）
     * 允许任何请求头allowCredentials(true). //带上cookie信息
     * @param corsRegistry
     */
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**").
                allowedOrigins("*").
                allowedMethods("*").
                allowedHeaders("*");
    }

}

