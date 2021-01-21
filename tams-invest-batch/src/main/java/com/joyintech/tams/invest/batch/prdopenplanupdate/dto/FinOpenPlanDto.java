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
	private List<NewFinOpenPlanDto> finOpenPlanList;
	//分红/付息计划
	private List<NewFinCashPlanDto> finCashPlanList;
	//付费计划计划
	private List<NewFinPayPlanDto> finPayPlanList;
	//业绩报酬计提计划
	private List<NewFinFloatPlanDto> finFloatPlanList;
	//回补计划
	private List<NewFinAnapleroticPlanDto> finAnapleroticPlanList;

	public List<NewFinOpenPlanDto> getFinOpenPlanList() {
		return finOpenPlanList;
	}

	public void setFinOpenPlanList(List<NewFinOpenPlanDto> finOpenPlanList) {
		this.finOpenPlanList = finOpenPlanList;
	}

	public PrdOpenPlanUpdateDto getPrdOpenPlanUpdateDto() {
		return prdOpenPlanUpdateDto;
	}

	public void setPrdOpenPlanUpdateDto(PrdOpenPlanUpdateDto prdOpenPlanUpdateDto) {
		this.prdOpenPlanUpdateDto = prdOpenPlanUpdateDto;
	}

	public List<NewFinCashPlanDto> getFinCashPlanList() {
		return finCashPlanList;
	}

	public void setFinCashPlanList(List<NewFinCashPlanDto> finCashPlanList) {
		this.finCashPlanList = finCashPlanList;
	}
	
	public List<NewFinPayPlanDto> getFinPayPlanList() {
		return finPayPlanList;
	}

	public void setFinPayPlanList(List<NewFinPayPlanDto> finPayPlanList) {
		this.finPayPlanList = finPayPlanList;
	}
	
	public List<NewFinFloatPlanDto> getFinFloatPlanList() {
		return finFloatPlanList;
	}

	public void setFinFloatPlanList(List<NewFinFloatPlanDto> finFloatPlanList) {
		this.finFloatPlanList = finFloatPlanList;
	}
	
	public List<NewFinAnapleroticPlanDto> getFinAnapleroticPlanList() {
		return finAnapleroticPlanList;
	}

	public void setFinAnapleroticPlanList(List<NewFinAnapleroticPlanDto> finAnapleroticPlanList) {
		this.finAnapleroticPlanList = finAnapleroticPlanList;
	}

}
