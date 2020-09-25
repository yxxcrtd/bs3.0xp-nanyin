package com.joyintech.tams.invest.product.productduration.netproductduration.dto;

import java.math.BigDecimal;

import com.joyintech.tams.common.base.entity.FinCashPlan;

public class FinCashPlanDto extends FinCashPlan {

	/*分红规则:01到期支付 02周期支付 07日付 08申购同频 09不规则*/
	private String payCycle;
	
	/*分红方式:01现金分红，02红利再投*/
	private String payType;
	/*计息日前一日的未分配利润（待分红金额）*/
	private BigDecimal bonusShareAmtT;
	/*计息日前一日的持有份额*/
	private BigDecimal bonusShareAfterShareNumBf;
	
	public String getPayCycle() {
		return payCycle;
	}

	public void setPayCycle(String payCycle) {
		this.payCycle = payCycle;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public BigDecimal getBonusShareAmtT() {
		return bonusShareAmtT;
	}

	public void setBonusShareAmtT(BigDecimal bonusShareAmtT) {
		this.bonusShareAmtT = bonusShareAmtT;
	}

	public BigDecimal getBonusShareAfterShareNumBf() {
		return bonusShareAfterShareNumBf;
	}

	public void setBonusShareAfterShareNumBf(BigDecimal bonusShareAfterShareNumBf) {
		this.bonusShareAfterShareNumBf = bonusShareAfterShareNumBf;
	}
	
	
}
