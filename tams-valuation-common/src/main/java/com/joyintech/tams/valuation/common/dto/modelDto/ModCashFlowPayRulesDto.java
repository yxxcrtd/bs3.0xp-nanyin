package com.joyintech.tams.valuation.common.dto.modelDto;

import com.joyintech.framework.base.BaseEntity;

import java.util.Date;

/**
 * 现金流支付规则DTO
 * @author zhangfei
 *
 */
public class ModCashFlowPayRulesDto extends BaseEntity {
	
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
     * 支付频率
     */
    private String payCycle;
    /**
     * 推算方式
     */
    private String calcRule;
    /**
     * 首次支付日 
     */
    private Date firstPayDate;
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
	public String getPayCycle() {
		return payCycle;
	}
	public Date getFirstPayDate() {
		return firstPayDate;
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
	public void setPayCycle(String payCycle) {
		this.payCycle = payCycle;
	}
	public void setFirstPayDate(Date firstPayDate) {
		this.firstPayDate = firstPayDate;
	}
	public String getCalcRule() {
		return calcRule;
	}
	public void setCalcRule(String calcRule) {
		this.calcRule = calcRule;
	}
    
    
}
