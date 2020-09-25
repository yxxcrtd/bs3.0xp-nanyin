/*
 * 文件名：FeeRateMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨静敏
 * 修改时间：2018年11月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ifeerate.dao;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;

import com.joyintech.service.interf.ifeerate.entity.FeeRateEntity;

/**
 * 查询费率
 * 
 * @author 杨静敏
 * @version 1.0 2018年11月21日
 * @see FeeRateMapper
 * @since 1.0
 */
@Repository
public interface FeeRateMapper {

    /**
     * 查询费率
     * 
     * @param feeRateEntity 查询费率入参
     * @return 费率
     */
    BigDecimal selectFeeRate(FeeRateEntity feeRateEntity);
}
