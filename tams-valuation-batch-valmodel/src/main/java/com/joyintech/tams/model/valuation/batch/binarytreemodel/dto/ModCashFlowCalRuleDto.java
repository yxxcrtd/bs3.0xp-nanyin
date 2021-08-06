package com.joyintech.tams.model.valuation.batch.binarytreemodel.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 现金流计算规则DTO
 * @author zhangfei
 */
public class ModCashFlowCalRuleDto extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	/**
     * 金融产品代码
     */
    private String finprodId;
    /**
     * 金融产品类型（投管分类）
     */
    private String finprodType2;
    /**
     * 现金流代码
     */
    private String cashId;
    /**
     * 现金流二级类型
     */
    private String cashType2;
    /**
     * 生效日
     */
    private Date effDate;
    /**
     * 计息基础
     */
    private String basis;
    
    /**
     * 生效利率
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal yield;

	public String getFinprodId() {
		return finprodId;
	}

	public String getFinprodType2() {
		return finprodType2;
	}

	public String getCashId() {
		return cashId;
	}

	public String getCashType2() {
		return cashType2;
	}

	public Date getEffDate() {
		return effDate;
	}

	public String getBasis() {
		return basis;
	}

	public BigDecimal getYield() {
		return yield;
	}

	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

	public void setFinprodType2(String finprodType2) {
		this.finprodType2 = finprodType2;
	}

	public void setCashId(String cashId) {
		this.cashId = cashId;
	}

	public void setCashType2(String cashType2) {
		this.cashType2 = cashType2;
	}

	public void setEffDate(Date effDate) {
		this.effDate = effDate;
	}

	public void setBasis(String basis) {
		this.basis = basis;
	}

	public void setYield(BigDecimal yield) {
		this.yield = yield;
	}
    
}
