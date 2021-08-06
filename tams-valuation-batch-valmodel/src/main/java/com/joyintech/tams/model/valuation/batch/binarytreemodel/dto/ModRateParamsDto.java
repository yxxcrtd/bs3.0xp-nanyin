package com.joyintech.tams.model.valuation.batch.binarytreemodel.dto;

import java.util.Date;

/**
 * 查询评级信息参数对象
 * @author zhangfei
 *
 */
public class ModRateParamsDto {
	/**
	 * 评级主体
	 */
	private String rateId;
	/**
	 * 数据字典.模型_证券品种
	 */
	private String modSecType;
	/**
	 * 查询日期
	 */
	private Date searchDate;

	public String getRateId() {
		return rateId;
	}

	public String getModSecType() {
		return modSecType;
	}

	public Date getSearchDate() {
		return searchDate;
	}

	public void setRateId(String rateId) {
		this.rateId = rateId;
	}

	public void setModSecType(String modSecType) {
		this.modSecType = modSecType;
	}

	public void setSearchDate(Date searchDate) {
		this.searchDate = searchDate;
	}


}
