/*
 * 文件名：Interf449186Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：whm
 * 修改时间：2018年11月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i449186.service;

import com.joyintech.tams.common.base.entity.InterfProdLadderDetail;
import com.joyintech.service.interf.core.service.ProdAliveService;
import com.joyintech.service.interf.i449186.entity.Interf449186InEntity;
import com.joyintech.service.interf.prodtrade.dto.InterfOutEntity;

/**
 * 449186-净值产品T-1日份额
 * 
 * @author whm
 * @version 1.0 2018年11月19日
 * @see Interf449186Service
 * @since 1.0
 */
public class Interf449186Service extends ProdAliveService<Interf449186InEntity, InterfOutEntity> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Interf449186InEntity getReturnEntity() {

        return new Interf449186InEntity();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <L extends InterfProdLadderDetail> L getReturnLvlEntity() {
        return null;
    }

}
