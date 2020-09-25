package com.joyintech.service.basicdata.mstcommissionset.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.common.base.entity.TrdFeeConfig;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 * 佣金设置验证
 * @author 裴家俊
 * @version 1.0 2019年3月22日
 * @see MstCommissionSetDto
 * @since 1.0
 */
public class MstCommissionSetDto extends TrdFeeConfig {

    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;

    private String secAcctName;

    /**
     * 计费值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal chargeValue;

    /**
     * 费用上限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal feeMaxi;

    /**
     * 费用下限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal feeMini;

    /**
     * 计费比率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal chargeRate;
    
	public String getSecAcctName() {
		return secAcctName;
	}

	public void setSecAcctName(String secAcctName) {
		this.secAcctName = secAcctName;
	}
    
	 /**
     * 取得 计费值
     * @return 计费值
     */
    public BigDecimal getChargeValue() {
        return chargeValue;
    }

    /**
     * 设置 计费值
     * @param chargeValue 计费值
     */
    public void setChargeValue(BigDecimal chargeValue) {
        this.chargeValue = chargeValue;
    }

    /**
     * 取得 费用上限
     * @return 费用上限
     */
    public BigDecimal getFeeMaxi() {
        return feeMaxi;
    }

    /**
     * 设置 费用上限
     * @param feeMaxi 费用上限
     */
    public void setFeeMaxi(BigDecimal feeMaxi) {
        this.feeMaxi = feeMaxi;
    }

    /**
     * 取得 费用下限
     * @return 费用下限
     */
    public BigDecimal getFeeMini() {
        return feeMini;
    }

    /**
     * 设置 费用下限
     * @param feeMini 费用下限
     */
    public void setFeeMini(BigDecimal feeMini) {
        this.feeMini = feeMini;
    }

    /**
     * 取得 计费比率
     * @return 计费比率
     */
    public BigDecimal getChargeRate() {
        return chargeRate;
    }

    /**
     * 设置 计费比率
     * @param chargeRate 计费比率
     */
    public void setChargeRate(BigDecimal chargeRate) {
        this.chargeRate = chargeRate;
    }
}
