package com.joyintech.tams.invest.common.component.business.basicdata.dto;

import java.math.BigDecimal;

import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;

public class TrdTradeFeeAddRateDetail extends TrdTradeFeeDetail {

    /**
     * 费率
     */
    private BigDecimal feeRate;

    /**
     * 返回费率
     * @return 费率
     */
	public BigDecimal getFeeRate() {
		return feeRate;
	}

	/**
	 * 设置费率
	 * @param feeRate 费率
	 */
	public void setFeeRate(BigDecimal feeRate) {
		this.feeRate = feeRate;
	}
}