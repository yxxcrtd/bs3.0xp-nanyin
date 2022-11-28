/*
 * 文件名：ReportModelManage001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.disprodplanmanage.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

import java.util.Date;
import java.util.Map;

public class DisProdPlanManage001Dto extends BaseEntity{
	private Map<String, Object> param;

	/**
	 * 产品排期计划编号
	 */
	private String prodPlanId;

	/**
	 * 产品ID
	 */
	private String prodId;

	/**
	 * 开放期起始日
	 */
	private Date openStartDate;

	/**
	 * 开放期结束日
	 */
	private Date openEndDate;

	/**
	 * 产品冷静期
	 */
	private Date collingoffPeriod;

	/**
	 * 运营起始日
	 */
	private Date operationStartDate;

	/**
	 * 自动赎回日
	 */
	private Date autoRedeemDate;

	/**
	 * 客户持有天数
	 */
	private Short holdDays;

	/**
	 * 数据状态：数据字典，系统模块-DIS，字典code：DATA_STATUS:E-有效，D-删除
	 */
	private String dataStatus;

	/**
	 * 数据来源:数据字典code:DATA_SOURCE
	 */
	private String dataSource;

	private String editFlag;

	/**
	 * 取得 产品排期计划编号
	 * @return 产品排期计划编号
	 */
	public String getProdPlanId() {
		return prodPlanId;
	}



	/**
	 * 开放期起始日
	 */
	private String openStartDateStr;

	/**
	 * 开放期结束日
	 */
	private String openEndDateStr;

	/**
	 * 产品冷静期
	 */
	private String collingoffPeriodStr;

	/**
	 * 运营起始日
	 */
	private String operationStartDateStr;

	/**
	 * 自动赎回日
	 */
	private String autoRedeemDateStr;

	private String updateDateStr;

	private String prodName;

	private String dataStatusName;

	private String holdDaysStr;

	/**
	 * 产品编号拼接
	 */
	private String prodIdArr;

	public String getProdIdArr() {
		return prodIdArr;
	}

	public void setProdIdArr(String prodIdArr) {
		this.prodIdArr = prodIdArr;
	}

	public String getHoldDaysStr() {
		return holdDaysStr;
	}

	public void setHoldDaysStr(String holdDaysStr) {
		this.holdDaysStr = holdDaysStr;
	}

	public Map<String, Object> getParam() {
		return param;
	}

	public void setParam(Map<String, Object> param) {
		this.param = param;
	}

	public String getOpenStartDateStr() {
		return openStartDateStr;
	}

	public void setOpenStartDateStr(String openStartDateStr) {
		this.openStartDateStr = openStartDateStr;
	}

	public String getOpenEndDateStr() {
		return openEndDateStr;
	}

	public void setOpenEndDateStr(String openEndDateStr) {
		this.openEndDateStr = openEndDateStr;
	}

	public String getCollingoffPeriodStr() {
		return collingoffPeriodStr;
	}

	public void setCollingoffPeriodStr(String collingoffPeriodStr) {
		this.collingoffPeriodStr = collingoffPeriodStr;
	}

	public String getOperationStartDateStr() {
		return operationStartDateStr;
	}

	public void setOperationStartDateStr(String operationStartDateStr) {
		this.operationStartDateStr = operationStartDateStr;
	}

	public String getAutoRedeemDateStr() {
		return autoRedeemDateStr;
	}

	public void setAutoRedeemDateStr(String autoRedeemDateStr) {
		this.autoRedeemDateStr = autoRedeemDateStr;
	}

	public String getUpdateDateStr() {
		return updateDateStr;
	}

	public void setUpdateDateStr(String updateDateStr) {
		this.updateDateStr = updateDateStr;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

//	public String getDataStatusName() {
//		return dataStatusName;
//	}
	public String getDataStatusName() {
		return SysDataUtil.getNameFromDataDict(DictCode.维护数据状态, this.getDataStatus());
	}

	public void setDataStatusName(String dataStatusName) {
		this.dataStatusName = dataStatusName;
	}

	/**
	 * 设置 产品排期计划编号
	 * @param prodPlanId 产品排期计划编号
	 */
	public void setProdPlanId(String prodPlanId) {
		this.prodPlanId = prodPlanId == null ? null : prodPlanId.trim();
	}

	/**
	 * 取得 产品ID
	 * @return 产品ID
	 */
	public String getProdId() {
		return prodId;
	}

	/**
	 * 设置 产品ID
	 * @param prodId 产品ID
	 */
	public void setProdId(String prodId) {
		this.prodId = prodId == null ? null : prodId.trim();
	}

	/**
	 * 取得 开放期起始日
	 * @return 开放期起始日
	 */
	public Date getOpenStartDate() {
		return openStartDate;
	}

	/**
	 * 设置 开放期起始日
	 * @param openStartDate 开放期起始日
	 */
	@JsonDeserialize(using = CustomDateDeserialize.class)
	public void setOpenStartDate(Date openStartDate) {
		this.openStartDate = openStartDate;
	}

	/**
	 * 取得 开放期结束日
	 * @return 开放期结束日
	 */
	public Date getOpenEndDate() {
		return openEndDate;
	}

	/**
	 * 设置 开放期结束日
	 * @param openEndDate 开放期结束日
	 */
	@JsonDeserialize(using = CustomDateDeserialize.class)
	public void setOpenEndDate(Date openEndDate) {
		this.openEndDate = openEndDate;
	}

	/**
	 * 取得 查询条件key
	 * @return 查询条件key
	 */
	public Date getCollingoffPeriod() {
		return collingoffPeriod;
	}

	/**
	 * 设置 查询条件key
	 * @param collingoffPeriod 查询条件key
	 */
	@JsonDeserialize(using = CustomDateDeserialize.class)
	public void setCollingoffPeriod(Date collingoffPeriod) {
		this.collingoffPeriod = collingoffPeriod;
	}

	/**
	 * 取得 运营起始日
	 * @return 运营起始日
	 */
	public Date getOperationStartDate() {
		return operationStartDate;
	}

	/**
	 * 设置 运营起始日
	 * @param operationStartDate 运营起始日
	 */
	@JsonDeserialize(using = CustomDateDeserialize.class)
	public void setOperationStartDate(Date operationStartDate) {
		this.operationStartDate = operationStartDate;
	}

	public Date getAutoRedeemDate() {
		return autoRedeemDate;
	}

	public void setAutoRedeemDate(Date autoRedeemDate) {
		this.autoRedeemDate = autoRedeemDate;
	}

	/**
	 * 取得 客户持有天数
	 * @return 客户持有天数
	 */
	public Short getHoldDays() {
		return holdDays;
	}

	/**
	 * 设置 客户持有天数
	 * @param holdDays 客户持有天数
	 */
	public void setHoldDays(Short holdDays) {
		this.holdDays = holdDays;
	}

	/**
	 * 取得 数据状态：数据字典，系统模块-DIS，字典code：DATA_STATUS:E-有效，D-删除
	 * @return 数据状态：数据字典，系统模块-DIS，字典code：DATA_STATUS:E-有效，D-删除
	 */
	public String getDataStatus() {
		return dataStatus;
	}

	/**
	 * 设置 数据状态：数据字典，系统模块-DIS，字典code：DATA_STATUS:E-有效，D-删除
	 * @param dataStatus 数据状态：数据字典，系统模块-DIS，字典code：DATA_STATUS:E-有效，D-删除
	 */
	public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus == null ? null : dataStatus.trim();
	}

	/**
	 * 取得 数据来源:数据字典code:DATA_SOURCE
	 * @return 数据来源:数据字典code:DATA_SOURCE
	 */
	public String getDataSource() {
		return dataSource;
	}

	/**
	 * 设置 数据来源:数据字典code:DATA_SOURCE
	 * @param dataSource 数据来源:数据字典code:DATA_SOURCE
	 */
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource == null ? null : dataSource.trim();
	}

	public String getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}
}


