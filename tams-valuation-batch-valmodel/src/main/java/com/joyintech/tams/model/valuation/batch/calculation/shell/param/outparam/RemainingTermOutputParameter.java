package com.joyintech.tams.model.valuation.batch.calculation.shell.param.outparam;

import com.joyintech.tams.model.valuation.batch.calculation.OutputParameter;
import com.joyintech.tams.model.valuation.batch.calculation.entity.CashInfoDto;

import java.util.ArrayList;
import java.util.List;

/**剩余期限输出参数
 * @author zznn
 * @date 2021/7/2
 */
public class RemainingTermOutputParameter implements OutputParameter {
	/**
	 * 现金流信息
	 */
	private List<CashInfoDto> cashInfoDtoList;

	public List<CashInfoDto> getCashInfoDtoList() {
		if(cashInfoDtoList==null){
			cashInfoDtoList = new ArrayList<CashInfoDto>();
		}
		return cashInfoDtoList;
	}

	public void setCashInfoDtoList(List<CashInfoDto> cashInfoDtoList) {
		this.cashInfoDtoList = cashInfoDtoList;
	}
	
	
	
	
}
