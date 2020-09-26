/*
 * 文件名：InterfStartupListener.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年8月16日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.system.config.StartupConfiguration;

/**
 * 接口启动后的操
 * @author 王波
 * @version 1.0 2018年8月16日
 * @see InterfStartupListener
 * @since 1.0
 */
@Component
public class InterfStartupListener extends StartupConfiguration  {
    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(InterfStartupListener.class);

    /**
     * 接口核心Service
     */
    @Autowired
    private InterfCoreService interfCoreService;
    
    @Override
    public void afterStartup(ApplicationContext applicationContext) {
        logger.info("接口程序成功启动..");
        interfCoreService.initInterfActionLog();
    }

}
