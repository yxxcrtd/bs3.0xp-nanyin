/*
 * 文件名：Interf449189Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i449189.service;

import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.InterfProdLadderDetail;
import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.core.service.ProdAliveService;
import com.joyintech.service.interf.i449189.entity.Interf449189InEntity;
import com.joyintech.service.interf.prodtrade.dto.InterfOutEntity;

/**
 * N天产品到期接口449189处理详细
 * 
 * @author changwenbin
 * @version 1.0 2018年11月7日
 * @see Interf449189Service
 * @since 1.0
 */
@Service
@EsbSyshead(value = EsbServiceInfoList.INF_449189_01)
public class Interf449189Service extends ProdAliveService<Interf449189InEntity, InterfOutEntity> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Interf449189InEntity getReturnEntity() {
        return new Interf449189InEntity();
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
