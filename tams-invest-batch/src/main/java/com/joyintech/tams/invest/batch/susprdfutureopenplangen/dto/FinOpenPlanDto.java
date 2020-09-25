package com.joyintech.tams.invest.batch.susprdfutureopenplangen.dto;

import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.entity.FinOpenPlan;

/**
 * 开放现金流信息entity
 * 2019年9月26日
 */
public class FinOpenPlanDto extends NeedToWriteEntity {

	private SusPrdFutureOpenPlanGenDto susPrdFutureOpenPlanGenDto; // 开放现金流信息
	private List<FinOpenPlan> finOpenPlanList;

	public List<FinOpenPlan> getFinOpenPlanList() {
		return finOpenPlanList;
	}

	public void setFinOpenPlanList(List<FinOpenPlan> finOpenPlanList) {
		this.finOpenPlanList = finOpenPlanList;
	}

	public SusPrdFutureOpenPlanGenDto getSusPrdFutureOpenPlanGenDto() {
		return susPrdFutureOpenPlanGenDto;
	}

	public void setSusPrdFutureOpenPlanGenDto(SusPrdFutureOpenPlanGenDto susPrdFutureOpenPlanGenDto) {
		this.susPrdFutureOpenPlanGenDto = susPrdFutureOpenPlanGenDto;
	}
}
