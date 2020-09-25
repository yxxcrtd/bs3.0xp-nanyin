/*
 * 文件名：InterfCUSDEP1207Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年2月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.icusdep1207.service;

import org.springframework.stereotype.Service;

import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.core.service.SendEsbService;
import com.joyintech.service.interf.icusdep1207.entity.InterfCUSDEP1207InEntity;
import com.joyintech.service.interf.order.dto.CusdepOrderDto;

/**
 * 接口CUSDEP1207-详细处理
 * 
 * @author zhangrui1
 * @version 1.0 2019年2月13日
 * @see InterfCUSDEP1207Service
 * @since 1.0
 */
@Service
@EsbSyshead(value = EsbServiceInfoList.INF_CUSDEP1207)
public class InterfCUSDEP1207Service extends SendEsbService<InterfCUSDEP1207InEntity, InterfCUSDEP1207InEntity> {

    /**
     * {@inheritDoc}
     */
    @Override
    public InterfCUSDEP1207InEntity preparedLocalData(Object param)
        throws Exception {
        CusdepOrderDto dto = (CusdepOrderDto)param;
        InterfCUSDEP1207InEntity inEntity = new InterfCUSDEP1207InEntity();
        inEntity.setDto(dto);
        return inEntity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveInterfData(InterfCUSDEP1207InEntity entity)
        throws Exception {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveBusinessData(InterfCUSDEP1207InEntity entity)
        throws Exception {
        return 0;
    }
}
