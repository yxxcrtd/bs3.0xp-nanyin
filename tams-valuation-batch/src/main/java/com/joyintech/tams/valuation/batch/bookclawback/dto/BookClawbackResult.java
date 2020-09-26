package com.joyintech.tams.valuation.batch.bookclawback.dto;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.common.base.entity.BokClawbackInfo;
import com.joyintech.tams.common.base.entity.BokClawbackInfoDetail;

public class BookClawbackResult extends BookGenerateResult {

	private List<BokClawbackInfo> bokClawbackInfoList = new ArrayList<BokClawbackInfo>();

	private List<BokClawbackInfoDetail> bokClawbackInfoDetailList = new ArrayList<BokClawbackInfoDetail>();

	public List<BokClawbackInfo> getBokClawbackInfoList() {
		return bokClawbackInfoList;
	}

	public void setBokClawbackInfoList(List<BokClawbackInfo> bokClawbackInfoList) {
		this.bokClawbackInfoList = bokClawbackInfoList;
	}

	public List<BokClawbackInfoDetail> getBokClawbackInfoDetailList() {
		return bokClawbackInfoDetailList;
	}

	public void setBokClawbackInfoDetailList(
			List<BokClawbackInfoDetail> bokClawbackInfoDetailList) {
		this.bokClawbackInfoDetailList = bokClawbackInfoDetailList;
	}
	
	

}
