/*
 * 文件名：Interf449283InLvlEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i449283.entity;

import java.math.BigDecimal;

import com.joyintech.tams.common.base.entity.InterfProdLadderDetail;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbName;
import com.joyintech.service.interf.core.annotation.EsbNumber;

/**
 * 449283接口金额分层输入
 * 
 * @author changwenbin
 * @version 1.0 2018年11月8日
 * @see Interf449283InLvlEntity
 * @since 1.0
 */
public class Interf449283InLvlEntity extends InterfProdLadderDetail {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -1526724294133325697L;

    @EsbName(value = "RefAddRate")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 8, scale = 6)
    @Override
    public BigDecimal getRefaddrate() {
        return super.getRefaddrate();
    }

    @EsbName(value = "StartAmount")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 15, scale = 2)
    @Override
    public BigDecimal getStartamount() {
        return super.getStartamount();
    }

    @EsbName(value = "EndAmount")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 15, scale = 2)
    @Override
    public BigDecimal getEndamount() {
        return super.getEndamount();
    }
}
