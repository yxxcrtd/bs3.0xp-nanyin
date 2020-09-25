/*
 * 文件名：InterfG1010Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ig1010.service;

import org.springframework.stereotype.Service;

import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.core.service.SendEsbService;
import com.joyintech.service.interf.ig1010.entity.InterfG1010InEntity;
import com.joyintech.service.interf.trade.dto.TradeClearDto;

/**
 * 新核心记账撤销1010接口处理详细
 * 
 * @author changwenbin
 * @version 1.0 2018年11月28日
 * @see InterfG1010Service
 * @since 1.0
 */
@Service
@EsbSyshead(value = EsbServiceInfoList.INF_G1010)
public class InterfG1010Service extends SendEsbService<InterfG1010InEntity, InterfG1010InEntity> {

    /**
     * {@inheritDoc}
     */
    @Override
    public InterfG1010InEntity preparedLocalData(Object param)
        throws Exception {
        TradeClearDto dto = (TradeClearDto)param;
        // 取得参数
        String oldtranseqno = dto.getOrderOrTradeNo();
        InterfG1010InEntity inentity = new InterfG1010InEntity();
        // 设置原交易流水号
        inentity.setOldtranseqno(oldtranseqno);
        // 设置标志
        inentity.setFlag("0");
        return inentity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveInterfData(InterfG1010InEntity entity)
        throws Exception {
        // 无需登录接口表
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveBusinessData(InterfG1010InEntity entity)
        throws Exception {
        // 无需登录业务表
        return 0;
    }

}
