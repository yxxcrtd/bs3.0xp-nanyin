/*
 * 文件名：InterfProcessorListener.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年6月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.core;

import org.springframework.batch.core.ItemProcessListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.InterfNeedOperateEntity;

@Component
@Scope("prototype")
public class InterfProcessorListener implements ItemProcessListener<InterfNeedOperateEntity, BaseEntity> {

    // 核心Service
    @Autowired
    private InterfCoreService service;
    
    // 基础Service
    private InterfBaseService interfBaseService;
    
    
    /**
     * 获取 interfBaseService
     * @return interfBaseService.
     */
    public InterfBaseService getInterfBaseService() {
        return interfBaseService;
    }

    
    /**
     * 设置 interfBaseService
     * @param interfBaseService interfBaseService
     */
    public void setInterfBaseService(InterfBaseService interfBaseService) {
        this.interfBaseService = interfBaseService;
    }

    @Override
    public void beforeProcess(InterfNeedOperateEntity item) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void afterProcess(InterfNeedOperateEntity item, BaseEntity result) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onProcessError(InterfNeedOperateEntity item, Exception e) {
        service.onProcessError(e, null , interfBaseService);
    }

}
