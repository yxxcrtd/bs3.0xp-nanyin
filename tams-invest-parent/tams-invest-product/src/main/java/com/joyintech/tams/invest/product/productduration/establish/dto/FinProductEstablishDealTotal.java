/*
 * 文件名：FinProductEstablishDealTotal.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年12月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.productduration.establish.dto;

import java.math.BigDecimal;

import com.joyintech.tams.common.base.entity.PaTrdFinProductDealTotal;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.NotEmpty;


/**
 * 收益型产品认申购新建管理
 * 分层金额明细
 * 
 * @author zhangliang
 * @version 1.0 2018年12月26日
 * @see FinProductEstablishDealTotal
 * @since 1.0
 */
public class FinProductEstablishDealTotal extends PaTrdFinProductDealTotal {

    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -7723191175569426358L;

    /**
     * 产品款型
     */
    private String prodType;

    /**
     * 获取 prodType
     * @return prodType.
     */
    public String getProdType() {
        return prodType;
    }

    /**
     * 设置 prodType
     * @param prodType prodType
     */
    public void setProdType(String prodType) {
        this.prodType = prodType;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "FinProductEstablish003.lastAmt")
    @Amount(fieldName = "FinProductEstablish003.lastAmt")
    public BigDecimal getLastAmt() {
        return super.getLastAmt();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "FinProductEstablish003.currentSub")
    @Amount(fieldName = "FinProductEstablish003.currentSub")
    public BigDecimal getCurrentSub() {
        return super.getCurrentSub();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "FinProductEstablish003.lastRed")
    @Amount(fieldName = "FinProductEstablish003.lastRed")
    public BigDecimal getLastRed() {
        return super.getLastRed();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "FinProductEstablish003.lastAmtSub")
    @Amount(fieldName = "FinProductEstablish003.lastAmtSub")
    public BigDecimal getLastAmtSub() {
        return super.getLastAmtSub();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "FinProductEstablish003.accountingAmt")
    @Amount(fieldName = "FinProductEstablish003.accountingAmt")
    public BigDecimal getAccountingAmt() {
        return super.getAccountingAmt();
    }
}
