/*
 * 文件名：Interf291002Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年1月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i291002.dao;

import com.joyintech.tams.common.base.entity.InterfNetProdBuyDraw;

/**
 * 净值型产品申购赎回确认数据查询（291002）删除Mapper
 * 
 * @author zhangrui1
 * @version 1.0 2019年1月19日
 * @see Interf291002Mapper
 * @since 1.0
 */
public interface Interf291002Mapper {

    /**
     * 删除信息
     * 
     * @param info 信息
     * @return 删除行数
     */
    int deleteInfo(InterfNetProdBuyDraw info);
}
