/*
 * 文件名：InterfLc0009Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ilc0009.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.InterfLc0009Mapper;
import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.core.service.ReceivedEsbService;
import com.joyintech.service.interf.ilc0009.entity.InterfLc0009InEntity;

/**
 * 产品提前赎回受理经办接口Lc0009处理详细
 * 
 * @author changwenbin
 * @version 1.0 2018年11月9日
 * @see InterfLc0009Service
 * @since 1.0
 */
@Service
@EsbSyshead(value = EsbServiceInfoList.INF_LC0009)
public class InterfLc0009Service extends ReceivedEsbService<InterfLc0009InEntity, InterfLc0009InEntity> {

    /**
     * Lc0009mapper
     */
    @Autowired
    private InterfLc0009Mapper interfLc0009Mapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public InterfLc0009InEntity selectResponseData(InterfLc0009InEntity entity)
        throws Exception {
        // Lc0009无输出结果
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveInterfData(InterfLc0009InEntity entity)
        throws Exception {
        // 登录接口表
        entity.setCdate(getSysDate());
        return interfLc0009Mapper.insert(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveBusinessData(InterfLc0009InEntity entity)
        throws Exception {
        // 不登录业务表
        return 0;
    }

}
