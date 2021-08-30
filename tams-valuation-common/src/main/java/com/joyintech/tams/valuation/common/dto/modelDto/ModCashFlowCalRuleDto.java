package com.joyintech.tams.valuation.common.dto.modelDto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 现金流计算规则DTO
 * @author zhangfei
 */
public class ModCashFlowCalRuleDto extends FinCashCalcRule {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 现金流二级类型
	 */
	private String cashType2;

	public String getCashType2() {
		return cashType2;
	}

	public void setCashType2(String cashType2) {
		this.cashType2 = cashType2;
	}
}
