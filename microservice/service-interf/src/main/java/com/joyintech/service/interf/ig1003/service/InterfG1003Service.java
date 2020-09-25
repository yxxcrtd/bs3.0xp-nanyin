/*
 * 文件名：InterfG1003Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ig1003.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.core.service.SendEsbService;
import com.joyintech.service.interf.ig1003.dao.InterfG1003Mapper;
import com.joyintech.service.interf.ig1003.entity.InterfG1003InEntity;
import com.joyintech.service.interf.ig1003.entity.InterfG1003InTransferEntity;
import com.joyintech.service.interf.ig1003.entity.InterfG1003OutEntity;
import com.joyintech.service.interf.trade.dto.TradeClearDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * G1003详细设计
 * 
 * @author changwenbin
 * @version 1.0 2018年11月15日
 * @see InterfG1003Service
 * @since 1.0
 */
@Service
@EsbSyshead(value = EsbServiceInfoList.INF_G1003)
public class InterfG1003Service extends SendEsbService<InterfG1003InEntity, InterfG1003OutEntity> {

    /**
     * 新核心记账G103接口Mapper
     */
    @Autowired
    private InterfG1003Mapper interfG1003Mapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public InterfG1003InEntity preparedLocalData(Object param)
        throws Exception {
        // 参数取得
        TradeClearDto dto = (TradeClearDto)param;
        String orderid = dto.getOrderOrTradeNo();
        // 转账数组取得
        List<InterfG1003InTransferEntity> translist = interfG1003Mapper.selectTransfer(orderid,
            DictCodeList.币种_人民币_CNY.getValue());
        InterfG1003InEntity inentity = new InterfG1003InEntity();
        inentity.setTransferlist(translist);
        return inentity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveInterfData(InterfG1003OutEntity entity)
        throws Exception {
        // 无需登录接口表
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveBusinessData(InterfG1003OutEntity entity)
        throws Exception {
        // 无需登录业务表
        return 0;
    }

}
