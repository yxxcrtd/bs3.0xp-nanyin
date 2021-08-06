package com.joyintech.tams.model.valuation.batch.calculation.shell.param.outparam;

import com.joyintech.tams.model.valuation.batch.calculation.OutputParameter;
import com.joyintech.tams.model.valuation.batch.calculation.entity.CashInfoDto;

import java.math.BigDecimal;
import java.util.List;

public class DisCountOutputParameter implements OutputParameter {
	
    /**
     * 点差
     */
    private BigDecimal spread;
    
    /**
     * 估值全价
     */
    private BigDecimal fullprice;
    
    /**
	 * 现金流信息
	 */
	private List<CashInfoDto> cashInfoDtoList;
    
	public BigDecimal getSpread() {
		return spread;
	}

	public void setSpread(BigDecimal spread) {
		this.spread = spread;
	}

	public BigDecimal getFullprice() {
		return fullprice;
	}

	public void setFullprice(BigDecimal fullprice) {
		this.fullprice = fullprice;
	}

	public List<CashInfoDto> getCashInfoDtoList() {
		return cashInfoDtoList;
	}

	public void setCashInfoDtoList(List<CashInfoDto> cashInfoDtoList) {
		this.cashInfoDtoList = cashInfoDtoList;
	}
	
	
    
}
