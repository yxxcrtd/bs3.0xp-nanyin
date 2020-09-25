/*
 * 文件名：Interf449195Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨静敏
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i449195.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.InterfProdLadderDetail;
import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.core.service.ProdAliveService;
import com.joyintech.service.interf.i449195.dao.Interf449195Mapper;
import com.joyintech.service.interf.i449195.entity.Interf449195InEntity;
import com.joyintech.service.interf.prodtrade.dto.InterfFinProdDto;
import com.joyintech.service.interf.prodtrade.dto.InterfOutEntity;

/**
 * 滚动N天产品到期接口(449195)-处理详细
 * 
 * @author 杨静敏
 * @version 1.0 2018年11月7日
 * @see Interf449195Service
 * @since 1.0
 */
@Service
@EsbSyshead(value = EsbServiceInfoList.INF_449195_01)
public class Interf449195Service extends ProdAliveService<Interf449195InEntity, InterfOutEntity> {

    /**
     * 滚动N天产品到期接口(449195)-查询mapper
     */
    @Autowired
    private Interf449195Mapper mapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public Interf449195InEntity preparedLocalData(Object dto) {
        String prodId = ((InterfFinProdDto)dto).getFinProdId();
        return mapper.selectProdInfo(prodId, getSysDate());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Interf449195InEntity getReturnEntity() {
        return new Interf449195InEntity();
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
