/*
 * 文件名：InterfInterceptor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年10月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.joyintech.service.interf.core.service.AddLogService;
import com.joyintech.service.interf.core.util.ServiceMapperUtil;

/**
 * 拦截器，将HTTP请求记录日志
 * 
 * @author zhangrui1
 * @version 1.0 2018年10月31日
 * @see InterfInterceptor
 * @since 1.0
 */
public class InterfInterceptor extends HandlerInterceptorAdapter {

    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 日志记录
     */
    private AddLogService logService;

    /**
     * 初始化
     * 
     * @see
     * @since 1.0
     */
    private void initService() {
        if (logService == null) {
            logService = ServiceMapperUtil.getApplicationContext().getBean(AddLogService.class);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler)
        throws Exception {
        initService();
        return super.preHandle(request, response, handler);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView)
        throws Exception {
        if (logService != null) {
            // 日志登录
            // logService.addHttpLog(httpLogInfo)
        }
        else {
            logger.info("不存在bean:{},无法记录用户操作日志！", AddLogService.class);
        }
        super.postHandle(request, response, handler, modelAndView);
    }
}
