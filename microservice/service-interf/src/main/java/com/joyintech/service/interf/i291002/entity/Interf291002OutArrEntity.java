/*
 * 文件名：Interf291002OutArrEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年1月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i291002.entity;

import com.joyintech.tams.common.base.entity.InterfNetProdBuyDraw;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * 净值型产品申购赎回确认数据查询（291002）-输出信息数组
 * 
 * @author zhangrui1
 * @version 1.0 2019年1月19日
 * @see Interf291002OutArrEntity
 * @since 1.0
 */
public class Interf291002OutArrEntity extends InterfNetProdBuyDraw {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1814542452069471669L;

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "LAST_DAY_REDEEM_CLIENT_AMT")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setDrawCltAmt(String drawCltAmt) {
        super.setDrawCltAmt(drawCltAmt);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "LAST_DAY_REDEEM_COMMISSION")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setDrawCommission(String drawCommission) {
        super.setDrawCommission(drawCommission);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "LAST_DAY_REDEEM_LOT")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setDrawLot(String drawLot) {
        super.setDrawLot(drawLot);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "REDEEM_NET_VALUE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setDrawNet(String drawNet) {
        super.setDrawNet(drawNet);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "REDEEM_NET_VALUE_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setDrawNetDate(String drawNetDate) {
        super.setDrawNetDate(drawNetDate);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "LAST_DAY_REDEEM_VALUE_AMT")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setDrawValAmt(String drawValAmt) {
        super.setDrawValAmt(drawValAmt);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "LAST_DAY_RETENTION_COMMISSION")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setLeftCommission(String leftCommission) {
        super.setLeftCommission(leftCommission);
    }
}
