/*
 * 文件名：Interf291002InEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年1月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i291002.entity;

import org.springframework.beans.factory.annotation.Required;

import com.joyintech.tams.common.base.entity.InterfNetProdBuyDraw;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * 净值型产品申购赎回确认数据查询（291002）-输入
 * 
 * @author zhangrui1
 * @version 1.0 2019年1月19日
 * @see Interf291002InEntity
 * @since 1.0
 */
public class Interf291002InEntity extends InterfNetProdBuyDraw {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -7512752924807569537L;

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "QUERY_DATA_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    @Override
    public String getCdate() {
        return super.getCdate();
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "PRODUCT_CODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 32)
    @Required
    @Override
    public String getProdId() {
        return super.getProdId();
    }
}
