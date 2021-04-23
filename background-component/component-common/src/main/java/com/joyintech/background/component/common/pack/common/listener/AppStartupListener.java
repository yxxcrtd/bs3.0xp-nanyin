/*
 * 文件名：AppStartupListener.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年2月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.background.component.common.pack.common.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * 服务启动接口
 * 
 * @author 何阳阳
 * @version 1.0 2018年2月1日
 * @see AppStartupListener
 * @since 1.0
 */
public interface AppStartupListener extends ApplicationListener<ContextRefreshedEvent> {

    /**
     * {@inheritDoc}
     */
    @Override
    default void onApplicationEvent(ContextRefreshedEvent event) {
        Object source = event.getSource();
    }
    
    void onApplicationStartup(ContextRefreshedEvent event);

    /**
     * 项目启动成功后调用的方法
     * 
     * @param applicationContext {@link ApplicationContext}
     */
    void afterStartup(ApplicationContext applicationContext);
}
