/*
 * 文件名：OpicsBondTradeMapper.java
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
 * OPICS-债券交易更新Mapper
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月20日
 * @see OpicsBondTradeMapper
 * @since 1.0
 */
@Repository
public interface OpicsBondTradeMapper {

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
     * @return 更改数据件数
     * @see
     * @since 1.0
     */
    int updateRevDate(Map<String, Object> params);

    /**
     * 删除到期交易
     * 
     * @param params 参数
     * @return 删除数据件数
     * @see
     * @since 1.0
     */
    int deleteMdateTrad(Map<String, Object> params);

    /**
     * 接数据入债券交易信息接口表
     * 
     * @param params 参数
     * @return 接入数据件数
     * @see
     * @since 1.0
     */
    int saveTradData(Map<String, Object> params);

    /**
     * 更新债券交易信息接口表冲销数据
     * 
     * @param params 参数
     * @return 更改数据件数
     * @see
     * @since 1.0
     */
    int updateIntefRevDate(Map<String, Object> params);

    /**
     * 删除债券交易信息接口表到期交易
     * 
     * @param params 参数
     * @return 删除数据件数
     * @see
     * @since 1.0
     */
    int deleteIntefMdateTrad(Map<String, Object> params);
}
