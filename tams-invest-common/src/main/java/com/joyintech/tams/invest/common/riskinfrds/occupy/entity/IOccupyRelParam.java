/*
 * 文件名：IOccupyRelParam.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年8月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.riskinfrds.occupy.entity;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.tams.invest.common.constantrds.RedisKeyEnum;
import com.joyintech.tams.invest.common.riskinfrds.base.entity.IBusiness;

/**
 * 预占参数信息结构
 * 
 * @author zhangrui1
 * @version 1.0 2019年8月22日
 * @see IOccupyRelParam
 * @since 1.0
 */
public interface IOccupyRelParam extends IBusiness {

    /**
     * {@inheritDoc}
     */
    @Override
    default String getKey() {
        return RedisKeyEnum.预占实占信息.key();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    default String getField() {
        StringBuilder builder = new StringBuilder();
        // 预占/实占编码:预占/实占类型
        builder.append(getOccupyCode()).append(GlobalConstant.COLON).append(getOccupyType());
        return builder.toString();
    }

    /**
     * 取得 预占/实占编号
     * 
     * @return 预占/实占编号
     * @see
     * @since 1.0
     */
    String getOccupyCode();

    /**
     * 取得 预占/实占类型
     * 
     * @return 预占/实占类型（1:预占、2:实占）
     * @see
     * @since 1.0
     */
    int getOccupyType();

    /**
     * 取得 预占/实占参数信息列表
     * 
     * @return 预占/实占参数信息列表
     * @see
     * @since 1.0
     */
    Map<String, IOcpRelParamItem> getItems();
    
    /**
     * 取得 预占/实占时的净值
     * 
     * @return 预占/实占时的净值
     */
    BigDecimal getNetPrice();

    /**
     * 判断是否预占/实占某个参数
     * 
     * @param paraCode 参数代码
     * @return true:是、false:否
     * @see
     * @since 1.0
     */
    default boolean contains(String paraCode) {
        Map<String, IOcpRelParamItem> map = getItems();
        return !CollectionUtils.isEmpty(map) && map.containsKey(paraCode);
    }
}
