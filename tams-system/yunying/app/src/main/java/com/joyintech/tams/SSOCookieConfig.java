package com.joyintech.tams;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.sso.SSOCookieOperateor;
import com.joyintech.framework.util.CacheUtil;

@Configuration
@ConditionalOnProperty(name = "application.joyinsso", havingValue = "true")
public class SSOCookieConfig {
	
	@Primary
	@Bean("ssoCookieOperateor")
	public SSOCookieOperateor ssoCookieOperateor() {
		return new SSOCookieOperateor() {
			public String getSsoUserName(HttpServletRequest httpRequest) {
				return httpRequest.getParameter(CacheUtil.getSysParam(GlobalConstant.SSOUSERNAME_KEY));
			}
		};
	}
}