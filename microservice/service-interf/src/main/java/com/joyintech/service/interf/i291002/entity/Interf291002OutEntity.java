/*
 * 文件名：Interf291002OutEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年1月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i291002.entity;

import java.util.List;

import com.joyintech.tams.common.base.entity.InterfNetProdBuyDraw;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * 净值型产品申购赎回确认数据查询（291002）-输出
 * 
 * @author zhangrui1
 * @version 1.0 2019年1月19日
 * @see Interf291002OutEntity
 * @since 1.0
 */
public class Interf291002OutEntity extends InterfNetProdBuyDraw {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -8827611196967282726L;

    /**
     * 信息数组
     */
    private List<Interf291002OutArrEntity> infoList;

    /**
     * 获取 信息数组
     * 
     * @return 信息数组.
     */
    public List<Interf291002OutArrEntity> getInfoList() {
        return infoList;
    }

    /**
     * 设置 信息数组
     * 
     * @param infoList 信息数组
     */
    @EsbName(value = "INFO_ARRAY")
    @EsbFieldType(value = EsbFieldType.ARRAY)
    public void setInfoList(List<Interf291002OutArrEntity> infoList) {
        this.infoList = infoList;
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "LAST_DAY_SUBSCRIBE_AMT")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setBuyAmt(String buyAmt) {
        super.setBuyAmt(buyAmt);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "LAST_DAY_SUBSCRIBE_LOT")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setBuyLot(String buyLot) {
        super.setBuyLot(buyLot);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "CCY")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setCcy(String ccy) {
        super.setCcy(ccy);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "MOTHER_PROD_CODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setMotherProdId(String motherProdId) {
        super.setMotherProdId(motherProdId);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "LAST_DAY_NET_LOT")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setNetLot(String netLot) {
        super.setNetLot(netLot);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "UNIT_NET_VALUE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setNetValue(String netValue) {
        super.setNetValue(netValue);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "PRODUCT_CODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setProdId(String prodId) {
        super.setProdId(prodId);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "PRODUCT_NAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setProdName(String prodName) {
        super.setProdName(prodName);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "LAST_DAY_REDEEM_CLIENT_AMT")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setTotalDrawCltAmt(String totalDrawCltAmt) {
        super.setTotalDrawCltAmt(totalDrawCltAmt);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "LAST_DAY_REDEEM_COMMISSION")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setTotalDrawCommission(String totalDrawCommission) {
        super.setTotalDrawCommission(totalDrawCommission);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "LAST_DAY_REDEEM_LOT")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setTotalDrawLot(String totalDrawLot) {
        super.setTotalDrawLot(totalDrawLot);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "LAST_DAY_REDEEM_VALUE_AMT")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setTotalDrawValAmt(String totalDrawValAmt) {
        super.setTotalDrawValAmt(totalDrawValAmt);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "LAST_DAY_RETENTION_COMMISSION")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setTotalLeftCommission(String totalLeftCommission) {
        super.setTotalLeftCommission(totalLeftCommission);
    }
}
