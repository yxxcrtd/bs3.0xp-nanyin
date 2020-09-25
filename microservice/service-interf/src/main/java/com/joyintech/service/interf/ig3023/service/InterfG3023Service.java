/*
 * 文件名：InterfG3023Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：whm
 * 修改时间：2018年11月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ig3023.service;

import org.springframework.stereotype.Service;

import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.core.service.SendEsbService;
import com.joyintech.service.interf.ig3023.entity.InterfG3023InEntity;
import com.joyintech.service.interf.ig3023.entity.InterfG3023OutEntity;
import com.joyintech.service.interf.trade.dto.TradeClearDto;

/**
 * 向核心系统查询交易状态
 * 
 * @author whm
 * @version 1.0 2018年11月28日
 * @see InterfG3023Service
 * @since 1.0
 */
@Service
@EsbSyshead(value = EsbServiceInfoList.INF_G3023)
public class InterfG3023Service extends SendEsbService<InterfG3023InEntity, InterfG3023OutEntity> {

    /**
     * {@inheritDoc}
     */
    @Override
    public InterfG3023InEntity preparedLocalData(Object param)
        throws Exception {
        TradeClearDto dto = (TradeClearDto)param;
        // 取得参数
        String tranSeqNo = dto.getOrderOrTradeNo();
        // 新建entity
        InterfG3023InEntity entity = new InterfG3023InEntity();
        // 将接口参数放入entity
        entity.setTranSeqNo(tranSeqNo);
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveInterfData(InterfG3023OutEntity entity)
        throws Exception {
        // 推送接口无登录业务
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveBusinessData(InterfG3023OutEntity entity)
        throws Exception {
        // 推送接口无登录业务
        return 0;
    }

}
