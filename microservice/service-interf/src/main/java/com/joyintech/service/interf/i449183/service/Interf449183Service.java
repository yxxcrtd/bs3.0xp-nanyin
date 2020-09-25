/*
 * 文件名：Interf449183Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨静敏
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i449183.service;

import org.springframework.stereotype.Service;

import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.core.service.ProdAliveService;
import com.joyintech.service.interf.i449183.entity.Interf449183InEntity;
import com.joyintech.service.interf.i449183.entity.Interf449183InLvlEntity;
import com.joyintech.service.interf.prodtrade.dto.InterfOutEntity;

/**
 * 日添利、货币型产品申购赎回接口（449183）-处理详细
 * 
 * @author 杨静敏
 * @version 1.0 2018年11月7日
 * @see Interf449183Service
 * @since 1.0
 */
@Service
@EsbSyshead(value = EsbServiceInfoList.INF_449183_01)
public class Interf449183Service extends ProdAliveService<Interf449183InEntity, InterfOutEntity> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Interf449183InEntity getReturnEntity() {
        return new Interf449183InEntity();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public Interf449183InLvlEntity getReturnLvlEntity() {
        return new Interf449183InLvlEntity();
    }
}
