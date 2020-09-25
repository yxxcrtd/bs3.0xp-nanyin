/*
 * 文件名：SSOCookieOperateorImpl.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ZhangYangFei
 * 修改时间：2018年12月12日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.disclosure.conf;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.csii.dc.client.util.StringUtil;
import com.joyintech.framework.sso.SSOCookieOperateor;

/**
 * SSOCookieOperateor实现类
 * 从Request获取SSO用户名
 * @author ZhangYangFei
 * @version 1.0 2018年12月12日
 * @see SSOCookieOperateorImpl
 * @since 1.0
 */
@Component
public class SSOCookieOperateorImpl implements SSOCookieOperateor {
    
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(SSOCookieOperateorImpl.class);
    
    /**
     * 获取ssoUserName {@inheritDoc}
     */
    @Override
    public String getSsoUserName(HttpServletRequest httpRequest) {
        String ssoUserName = "";
        if (null == httpRequest) {
            logger.debug("SSOCookieOperateorImpl.getSsoUserName()，httpRequest为空，httpRequest = " + httpRequest);
            return ssoUserName;
        }
        /*
        userName：用户名，资管用户表中的用户名
        password：密码，资管用户表中的密码（加密后的）
        ssoUserName：SSO用户名，用户登录SSO平台时输入的用户名
        ssoPassword：SSO密码，用户登录SSO平台时输入的密码
        */
        ssoUserName = httpRequest.getParameter("ssoUserName");
        if(StringUtil.isEmpty(ssoUserName)){
        	Cookie[] cookies = httpRequest.getCookies();
        	if(cookies != null && cookies.length > 0){
        		for(Cookie cookie : cookies) {
        			logger.debug("SSOCookieOperateorImpl.getSsoUserName()，cookie.getName() = " + cookie.getName());
        			if(cookie.getName().equalsIgnoreCase("tams_user")) {
        				ssoUserName = cookie.getValue();
        			}
        		}
        	}
        }
        logger.debug("SSOCookieOperateorImpl.getSsoUserName()，ssoUserName = " + ssoUserName);
        httpRequest.setAttribute("shiroLoginFailure", "AuthenticationException");
        return ssoUserName;
    }

}
