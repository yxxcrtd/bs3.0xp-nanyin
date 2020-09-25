/*
 * 文件名：Interf449283Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i449283.service;

import org.springframework.stereotype.Service;

import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.core.service.ProdAliveService;
import com.joyintech.service.interf.i449283.entity.Interf449283InEntity;
import com.joyintech.service.interf.i449283.entity.Interf449283InLvlEntity;
import com.joyintech.service.interf.prodtrade.dto.InterfOutEntity;

/**
 * 449283接口处理详细
 * 
 * @author changwenbin
 * @version 1.0 2018年11月7日
 * @see Interf449283Service
 * @since 1.0
 */
@Service
@EsbSyshead(value = EsbServiceInfoList.INF_449283_01)
public class Interf449283Service extends ProdAliveService<Interf449283InEntity, InterfOutEntity> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Interf449283InEntity getReturnEntity() {
        return new Interf449283InEntity();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public Interf449283InLvlEntity getReturnLvlEntity() {
        return new Interf449283InLvlEntity();
    }

}
