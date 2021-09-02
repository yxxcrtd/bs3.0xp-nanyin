package com.joyintech.tams.valuation.common.dto.modelDto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.base.entity.FinCashPayRule;

import java.util.Date;

/**
 * 现金流支付规则DTO
 * @author zhangfei
 *
 */
public class ModCashFlowPayRulesDto extends FinCashPayRule {
	
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
