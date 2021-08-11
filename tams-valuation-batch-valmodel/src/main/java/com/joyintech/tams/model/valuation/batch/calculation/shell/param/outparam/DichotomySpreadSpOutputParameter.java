package com.joyintech.tams.model.valuation.batch.calculation.shell.param.outparam;

import com.joyintech.tams.model.valuation.batch.calculation.OutputParameter;

import java.math.BigDecimal;

/**
 * 贴现模型-二分法计算点差结果
 *
 * @author 吴洁
 * @version 1.0 2021年7月2日
 * @see DichotomySpreadSpOutputParameter
 * @since 1.0
 */
public class DichotomySpreadSpOutputParameter implements OutputParameter {
	
	private BigDecimal spread;

	public BigDecimal getSpread() {
		return spread;
	}

	public void setSpread(BigDecimal spread) {
		this.spread = spread;
	}
}
