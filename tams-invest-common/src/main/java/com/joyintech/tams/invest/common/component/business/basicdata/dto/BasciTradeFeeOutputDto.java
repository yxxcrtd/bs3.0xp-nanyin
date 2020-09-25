package com.joyintech.tams.invest.common.component.business.basicdata.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 * 费用明细dto
 * @author 裴家俊
 * @version 1.0 2019年3月22日
 * @see BasciTradeFeeOutputDto
 * @since 1.0
 */
public class BasciTradeFeeOutputDto {


    private String feeType;

    /**
     * 计费值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal chargeValue;
    
    private String ifTradePay;

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public BigDecimal getChargeValue() {
		return chargeValue;
	}

	public void setChargeValue(BigDecimal chargeValue) {
		this.chargeValue = chargeValue;
	}

	public String getIfTradePay() {
		return ifTradePay;
	}

	public void setIfTradePay(String ifTradePay) {
		this.ifTradePay = ifTradePay;
	}
  
}
