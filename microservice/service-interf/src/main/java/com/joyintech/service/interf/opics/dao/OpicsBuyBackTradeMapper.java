/*
 * 文件名：OpicsBuyBackTradeMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.opics.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * OPICS-回购交易更新Mapper
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月20日
 * @see OpicsBuyBackTradeMapper
 * @since 1.0
 */
@Repository
public interface OpicsBuyBackTradeMapper {

    /**
     * OPICS数据接入
     * 
     * @param params 参数
     * @return 接入数据件数
     * @see
     * @since 1.0
     */
    int saveOpicsData(Map<String, Object> params);

    /**
     * 更新冲销数据
     * 
     * @param params 参数
     * @return 接入数据件数
     * @see
     * @since 1.0
     */
    int updateRevDate(Map<String, Object> params);

    /**
     * 同步回购抵押品表
     * 
     * @param params 参数
     * @return 接入数据件数
     * @see
     * @since 1.0
     */
    int saveRepoasstData(Map<String, Object> params);

    /**
     * 将交易的券面总额改成汇总抵押品的券面总额
     * 
     * @param params 参数
     * @return 接入数据件数
     * @see
     * @since 1.0
     */
    int updateTradDate(Map<String, Object> params);
}
