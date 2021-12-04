package com.joyintech.service.basicdata.dayendaccount.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.workflow.engine.annotation.PrimaryKey;

import java.util.List;

public class DayEndAccount002Dto extends BaseEntity {
	/**
	 * 操作类型
	 */
	private String operateType;

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private String pageModel;


	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private String elementId;


	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private String seqNo;

	/**
	 * 券商成交单数据
	 */
	List<DayEndAccountDto> reconDealInfoList;

	/**
	 * 场内成交明细数据
	 */
	List<DayEndAccountDto> dealDetailList;

	public List<DayEndAccountDto> getReconDealInfoList() {
		return reconDealInfoList;
	}

	public void setReconDealInfoList(List<DayEndAccountDto> reconDealInfoList) {
		this.reconDealInfoList = reconDealInfoList;
	}

	public List<DayEndAccountDto> getDealDetailList() {
		return dealDetailList;
	}

	public void setDealDetailList(List<DayEndAccountDto> dealDetailList) {
		this.dealDetailList = dealDetailList;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getPageModel() {
		return pageModel;
	}

	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	@PrimaryKey(value= PrimaryKey.Field.PK_1)
	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

}
