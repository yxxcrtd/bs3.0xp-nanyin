package com.joyintech.tams.valuation.batch.outrightrepoprecalacctreat.dto;

import java.math.BigDecimal;

import com.joyintech.tams.common.base.entity.BokDetailId;

public class BokDetailIdOutRightPro extends BokDetailId{

	/**
     * 基础科目性质
     */
    private String basicsSubject;
    
    

    public String getBasicsSubject() {
		return basicsSubject;
	}

	public void setBasicsSubject(String basicsSubject) {
		this.basicsSubject = basicsSubject;
	}

}
