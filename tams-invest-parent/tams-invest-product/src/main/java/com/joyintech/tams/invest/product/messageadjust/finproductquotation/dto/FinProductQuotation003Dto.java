/*
 * 文件名：FinProductQuotation003Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年11月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.messageadjust.finproductquotation.dto;

import java.math.BigDecimal;

import com.joyintech.tams.common.base.entity.PrdLayerParam;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.Rate;


/**
 * 分层设置列表
 * 
 * @author zhangliang
 * @version 1.0 2018年11月28日
 * @see FinProductQuotation003Dto
 * @since 1.0
 */
public class FinProductQuotation003Dto extends PrdLayerParam {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -4729714825810811703L;

    /**
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "FinProductQuotation002.yield")
    @Rate(fieldName = "FinProductQuotation002.yield", maxDecBit = 4)
    public BigDecimal getYield() {
        return super.getYield();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @Amount(fieldName = "FinProductQuotation002.amtMin")
    public BigDecimal getAmtMin() {
        return super.getAmtMin();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @Amount(fieldName = "FinProductQuotation002.amtMax")
    public BigDecimal getAmtMax() {
        return super.getAmtMax();
    }

    private String cashId;

	public String getCashId() {
		return cashId;
	}

	public void setCashId(String cashId) {
		this.cashId = cashId;
	}
    
    
}
