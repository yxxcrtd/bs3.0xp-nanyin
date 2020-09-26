/*
 * 文件名：InterfWriterListener.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年6月12日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.core;

import java.util.List;

import org.springframework.batch.core.ItemWriteListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.joyintech.framework.base.BaseEntity;

@Component
@Scope("prototype")
public class InterfWriterListener implements ItemWriteListener<BaseEntity> {

    private InterfBaseService service;
    
    @Autowired
    private InterfCoreService interfCoreService;
    
    public InterfBaseService getService() {
        return service;
    }

    
    public void setService(InterfBaseService service) {
        this.service = service;
    }

    @Override
    public void beforeWrite(List<? extends BaseEntity> items) {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterWrite(List<? extends BaseEntity> items) {
        interfCoreService.onAfterWriter(items, service);

    }

    @Override
    public void onWriteError(Exception exception, List<? extends BaseEntity> items) {
        interfCoreService.onWriterError(exception, null, service);
    }

}
