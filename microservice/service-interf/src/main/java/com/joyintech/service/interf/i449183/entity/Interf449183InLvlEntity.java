/*
 * 文件名：Interf449183InLvlEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨静敏
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i449183.entity;

import java.math.BigDecimal;

import com.joyintech.tams.common.base.entity.InterfProdLadderDetail;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbName;
import com.joyintech.service.interf.core.annotation.EsbNumber;

/**
 * 日添利、货币型产品申购赎回接口（449183）-输入-分层信息
 * 
 * @author 杨静敏
 * @version 1.0 2018年11月7日
 * @see Interf449183InLvlEntity
 * @since 1.0
 */
public class Interf449183InLvlEntity extends InterfProdLadderDetail {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 2413908836151606312L;

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
