/*
 * 文件名：IPortfolio.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年8月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.riskinfrds.prod.entity;

import java.util.Map;
import java.util.Set;

import com.joyintech.tams.invest.common.constantrds.RedisKeyEnum;
import com.joyintech.tams.invest.common.riskinfrds.base.entity.IBusiness;

/**
 * 组合关联指标
 * 
 * @author zhangrui1
 * @version 1.0 2019年8月15日
 * @see IPtlIndicator
 * @since 1.0
 */
public interface IPtlIndicator extends IBusiness {

    default String getKey() {
        return RedisKeyEnum.组合关联指标配置.key();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    default String getField() {
        return getPtlCode();
    }

    /**
     * 取得 组合代码
     * 
     * @return 组合代码
     * @see
     * @since 1.0
     */
    String getPtlCode();

    /**
     * 取得 组合名称
     * 
     * @return 组合名称
     * @see
     * @since 1.0
     */
    String getPtlName();

    /**
     * 取得 组合所在的所有组合包代码
     *  key为组合包代码，value为组合包名称
     * @return 组合所在的所有组合包代码
     * @see
     * @since 1.0
     */
    Map<String, Object> getPtlBagIdList();

    /**
     * 取得 组合关联指标信息
     * key为组合或组合包，value为Set集合，存的为指标CODE
     * @return 组合关联指标信息
     * @see
     * @since 1.0
     */
    Map<String, Set<String>> getPtlIndicator();
}
