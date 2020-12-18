package com.joyintech.tams.invest.batch.prdopenplanupdate.dto;

import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinOpenPlan;

/**
 * 开放现金流信息entity
 */
public class FinOpenPlanDto extends NeedToWriteEntity {

	private PrdOpenPlanUpdateDto prdOpenPlanUpdateDto; // 开放现金流信息
	//开放计划
	private List<FinOpenPlan> finOpenPlanList;
	//付息计划
	private List<FinCashPlan> finCashPlanList;

	public List<FinOpenPlan> getFinOpenPlanList() {
		return finOpenPlanList;
	}

	public void setFinOpenPlanList(List<FinOpenPlan> finOpenPlanList) {
		this.finOpenPlanList = finOpenPlanList;
	}

	public PrdOpenPlanUpdateDto getPrdOpenPlanUpdateDto() {
		return prdOpenPlanUpdateDto;
	}

	public void setPrdOpenPlanUpdateDto(PrdOpenPlanUpdateDto prdOpenPlanUpdateDto) {
		this.prdOpenPlanUpdateDto = prdOpenPlanUpdateDto;
	}

	public List<FinCashPlan> getFinCashPlanList() {
		return finCashPlanList;
	}

	public void setFinCashPlanList(List<FinCashPlan> finCashPlanList) {
		this.finCashPlanList = finCashPlanList;
	}

}
