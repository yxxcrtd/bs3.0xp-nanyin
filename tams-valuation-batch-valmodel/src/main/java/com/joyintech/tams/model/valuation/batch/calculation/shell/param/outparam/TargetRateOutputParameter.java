package com.joyintech.tams.model.valuation.batch.calculation.shell.param.outparam;

import com.joyintech.tams.model.valuation.batch.calculation.OutputParameter;

import java.math.BigDecimal;

/**
 * 插值输出参数
 * @author zznn
 * @date 2021/7/2
 */
public class TargetRateOutputParameter implements OutputParameter {

	//目标收益率
	private BigDecimal targerYield;

	public BigDecimal getTargerYield() {
		return targerYield;
	}

	public void setTargerYield(BigDecimal targerYield) {
		this.targerYield = targerYield;
	}
	
	
}
