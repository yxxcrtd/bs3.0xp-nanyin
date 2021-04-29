/*
 * 文件名：CommonAppStartupListener.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.listener;

import com.joyintech.tams.invest.common.position.service.CommonPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.joyintech.framework.listener.AppStartupListener;
import com.joyintech.tams.invest.common.util.xmlrelation.XmlRelationUtil;

/**
 * 系统启动加载业务缓存
 * 框架缓存以外内容加载
 * @author etliu
 * @version 1.0 2018年12月10日
 * @see CommonAppStartupListener
 * @since 1.0
 */
@Component
public class CommonAppStartupListener implements AppStartupListener {

    @Autowired
    private CommonPositionService commonPositionService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onApplicationStartup(ContextRefreshedEvent event) {
        XmlRelationUtil.getXmlRelationData();
        // 头寸处理基础数据配置加载
        commonPositionService.init();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void afterStartup(ApplicationContext applicationContext) {

    }

}
