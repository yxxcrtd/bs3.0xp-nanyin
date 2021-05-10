package com.joyintech.service.valuation.accounadjustmanage.manualvoucher.dto;

import com.joyintech.tams.common.base.entity.BokDetailId;

public class BokDetailIdDto extends BokDetailId{
	 /**
     * 收益类型
     */
    private String profitType;

	public String getProfitType() {
		return profitType;
	}

	public void setProfitType(String profitType) {
		this.profitType = profitType;
	}
	
}
