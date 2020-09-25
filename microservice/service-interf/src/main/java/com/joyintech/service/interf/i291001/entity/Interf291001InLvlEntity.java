/*
 * 文件名：Interf291001InLvlEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年1月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i291001.entity;

import java.math.BigDecimal;

import com.joyintech.tams.common.base.entity.InterfProdLadderDetail;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * 天天成长当日销售数据统计查询（291001）-输入分层信息
 * 
 * @author zhangrui1
 * @version 1.0 2019年1月19日
 * @see Interf291001InLvlEntity
 * @since 1.0
 */
public class Interf291001InLvlEntity extends InterfProdLadderDetail {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 3624218083224070019L;

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "END_AMT")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Override
    public BigDecimal getEndamount() {
        return super.getEndamount();
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "REF_INCOME_RATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Override
    public BigDecimal getRefaddrate() {
        return super.getRefaddrate();
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "START_AMT")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Override
    public BigDecimal getStartamount() {
        return super.getStartamount();
    }

}
