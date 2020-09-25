/*
 * 文件名：Interf150006Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨静敏
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i150006.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.service.interf.i150006.entity.Interf150006InEntity;
import com.joyintech.service.interf.ifeerate.entity.FeeRateEntity;

/**
 * 产品信息预创建接口（150006）查询mapper
 * 
 * @author 杨静敏
 * @version 1.0 2018年11月9日
 * @see Interf150006Mapper
 * @since 1.0
 */
@Repository
public interface Interf150006Mapper {

    /**
     * 根据金融产品代码和系统平台时间获取产品信息
     * 
     * @param feeRateEntity 查询产品信息和现金流信息入参
     * @return 产品信息
     * @see
     * @since 1.0
     */
    Interf150006InEntity selectProdInfo(FeeRateEntity feeRateEntity);

    /**
     * 获取分红方案信息
     * 
     * @param feeRateEntity 查询现金流分红信息
     * @return 分红信息
     * @see
     * @since 1.0
     */
    Interf150006InEntity selectCashPayRule(FeeRateEntity feeRateEntity);

}
