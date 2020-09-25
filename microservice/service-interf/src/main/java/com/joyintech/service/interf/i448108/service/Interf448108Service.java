/*
 * 文件名：Interf448108.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i448108.service;

import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.InterfProdLadderDetail;
import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.core.service.ProdAliveService;
import com.joyintech.service.interf.i448108.entity.Interf448108InEntity;
import com.joyintech.service.interf.prodtrade.dto.InterfOutEntity;

/**
 * 接口448108处理详细
 * 
 * @author changwenbin
 * @version 1.0 2018年11月7日
 * @see Interf448108Service
 * @since 1.0
 */
@Service
@EsbSyshead(value = EsbServiceInfoList.INF_448108_01)
public class Interf448108Service extends ProdAliveService<Interf448108InEntity, InterfOutEntity> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Interf448108InEntity getReturnEntity() {
        return new Interf448108InEntity();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public InterfProdLadderDetail getReturnLvlEntity() {
        return new InterfProdLadderDetail();
    }
}
