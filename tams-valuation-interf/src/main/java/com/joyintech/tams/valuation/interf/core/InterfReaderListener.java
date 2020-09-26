/*
 * 文件名：InterfReaderListener.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年8月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.core;

import javax.batch.api.chunk.listener.ItemReadListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class InterfReaderListener implements ItemReadListener {

    /**
     * 基础服务类
     */
    private InterfBaseService service;
    
    // 核心Service
    @Autowired
    private InterfCoreService interfCoreService;
    
    /**
     * 获取 service
     * @return service.
     */
    public InterfBaseService getService() {
        return service;
    }

    
    /**
     * 设置 service
     * @param service service
     */
    public void setService(InterfBaseService service) {
        this.service = service;
    }

    @Override
    public void afterRead(Object arg0)
        throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void beforeRead()
        throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onReadError(Exception e)
        throws Exception {
        interfCoreService.onReadError(e, null, service);
    }

}
