package com.joyintech.tams.invest.product.valuation.dto;

import java.math.BigDecimal;

public class ProdFeePositionDto extends ProdFeeInfoDto {

	private BigDecimal unpaidfee;

	public BigDecimal getUnpaidfee() {
		return unpaidfee;
	}

	public void setUnpaidfee(BigDecimal unpaidfee) {
		this.unpaidfee = unpaidfee;
	}

}
