/*
 * 文件名：Interf449188Servicee.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i449188.service;

import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.InterfProdLadderDetail;
import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.core.service.ProdAliveService;
import com.joyintech.service.interf.i449188.entity.Interf449188InEntity;
import com.joyintech.service.interf.prodtrade.dto.InterfOutEntity;

/**
 * N天产品成立接口449188详细处理
 * 
 * @author changwenbin
 * @version 1.0 2018年11月7日
 * @see Interf449188Service
 * @since 1.0
 */

@Service
@EsbSyshead(value = EsbServiceInfoList.INF_449188_01)
public class Interf449188Service extends ProdAliveService<Interf449188InEntity, InterfOutEntity> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Interf449188InEntity getReturnEntity() {
        return new Interf449188InEntity();
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
