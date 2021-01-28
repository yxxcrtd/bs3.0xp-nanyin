package com.joyintech.tams.invest.batch.susprdfutureopenplangen.dto;

import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinFloatAccrualSche;
import com.joyintech.tams.common.base.entity.FinOpenPlan;

/**
 * 开放现金流信息entity
 * 2019年9月26日
 */
public class FinOpenPlanDto extends NeedToWriteEntity {

	private SusPrdFutureOpenPlanGenDto susPrdFutureOpenPlanGenDto; // 开放现金流信息
	private List<FinOpenPlan> finOpenPlanList;//开放计划
	private List<FinCashPlan> finCashPlanList;//付息计划
	/*private List<FinFloatAccrualSche> finAnapleroticPlanList;//业绩回补计划
*/	
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

	/*public List<FinFloatAccrualSche> getFinAnapleroticPlanList() {
		return finAnapleroticPlanList;
	}

	public void setFinAnapleroticPlanList(List<FinFloatAccrualSche> finAnapleroticPlanList) {
		this.finAnapleroticPlanList = finAnapleroticPlanList;
	}*/

	public List<FinCashPlan> getFinCashPlanList() {
		return finCashPlanList;
	}

	public void setFinCashPlanList(List<FinCashPlan> finCashPlanList) {
		this.finCashPlanList = finCashPlanList;
	}
}
