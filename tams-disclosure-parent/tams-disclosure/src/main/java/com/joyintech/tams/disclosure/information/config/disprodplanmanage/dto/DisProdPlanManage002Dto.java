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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.common.base.entity.DisProdPlanInfo;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;
import org.apache.commons.lang3.StringUtils;

@PageId("DisProdPlanManage002")
public class DisProdPlanManage002Dto extends DisProdPlanInfo implements ITableRow, ProcessEntity {
	/**
	 *
	 */
	private static final long serialVersionUID = -459083868293354979L;
	private List<DisProdPlanManage002Dto> addParam=new ArrayList<>();

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

	public Date openEndDateO;

	public String openEndDateOStr;

	public Date getOpenEndDateO() {
		return openEndDateO;
	}

	public void setOpenEndDateO(Date openEndDateO) {
		this.openEndDateO = openEndDateO;
	}
	public String getOpenEndDateOStr() {
		return DateUtil.format(this.getOpenEndDateO());
	}
	/**
	 * 操作类型
	 */
	private String operateType;


	private String operationType ;

	/**
	 * 选中的数据
	 */
	private transient List<DisProdPlanManage002Dto> selectedItem;
	/**
	 * 元素ID
	 */
	private String elementId;


	/**
	 * 发行方式：无需区分母子产品 01 开放式/02 封闭式
	 */
	private String issuingWay;

	/**
	 * 是否是客户周期型产品：不区分母子产品 01 是/02 否
	 */
	private String isPeriod;

	/**
	 * 募集方式
	 */
	private String raiseType;

	@Override
	@PrimaryKey(value = PrimaryKey.Field.PK_1)
	public String getProdPlanId() {
		return super.getProdPlanId();
	}


	public String getIssuingWay() {
		return issuingWay;
	}

	public void setIssuingWay(String issuingWay) {
		this.issuingWay = issuingWay;
	}

	public String getIsPeriod() {
		return isPeriod;
	}

	public void setIsPeriod(String isPeriod) {
		this.isPeriod = isPeriod;
	}

	public String getRaiseType() {
		return raiseType;
	}

	public void setRaiseType(String raiseType) {
		this.raiseType = raiseType;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public List<DisProdPlanManage002Dto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<DisProdPlanManage002Dto> selectedItem) {
		this.selectedItem = selectedItem;
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getOpenStartDateStr() {
		// return openStartDateStr;
		if(StringUtils.isNotBlank(this.openStartDateStr)){
			return this.openStartDateStr ;
		}else{
			return this.openStartDateStr = this.getOpenStartDate()==null?null: DateUtil.format(this.getOpenStartDate(), "yyyy-MM-dd");
		}

	}

	public void setOpenStartDateStr(String openStartDateStr) {
		this.openStartDateStr = openStartDateStr;
	}

	public String getOpenEndDateStr() {
		if(StringUtils.isNotBlank(this.openEndDateStr)){
			return this.openEndDateStr ;
		}else{
			return this.openEndDateStr = this.getOpenEndDate()==null?null: DateUtil.format(this.getOpenEndDate(), "yyyy-MM-dd");
		}

	}

	public void setOpenEndDateStr(String openEndDateStr) {
		this.openEndDateStr = openEndDateStr;
	}

	public String getCollingoffPeriodStr() {
//		return collingoffPeriodStr;
		if(StringUtils.isNotBlank(this.collingoffPeriodStr)){
			return this.collingoffPeriodStr ;
		}else{
			return this.collingoffPeriodStr = this.getCollingoffPeriod()==null?null: DateUtil.format(this.getCollingoffPeriod(), "yyyy-MM-dd");
		}

	}

	public void setCollingoffPeriodStr(String collingoffPeriodStr) {
		this.collingoffPeriodStr = collingoffPeriodStr;
	}

	public String getOperationStartDateStr() {
//		return operationStartDateStr;
		if(StringUtils.isNotBlank(this.operationStartDateStr)){
			return this.operationStartDateStr ;
		}else{
			return this.operationStartDateStr = this.getOperationStartDate()==null?null: DateUtil.format(this.getOperationStartDate(), "yyyy-MM-dd");
		}
	}

	public void setOperationStartDateStr(String operationStartDateStr) {
		this.operationStartDateStr = operationStartDateStr;
	}

	public String getAutoRedeemDateStr() {
		//return autoRedeemDateStr;
		if(StringUtils.isNotBlank(this.autoRedeemDateStr)){
			return this.autoRedeemDateStr ;
		}else{
			return this.autoRedeemDateStr = this.getAutoRedeemDate()==null?null: DateUtil.format(this.getAutoRedeemDate(), "yyyy-MM-dd");
		}
	}

	public void setAutoRedeemDateStr(String autoRedeemDateStr) {
		this.autoRedeemDateStr = autoRedeemDateStr;
	}

	public String getUpdateDateStr() {
//		return updateDateStr;
		if(StringUtils.isNotBlank(this.updateDateStr)){
			return this.updateDateStr ;
		}else{
			return this.updateDateStr = this.getUpdateTime()==null?null: DateUtil.format(this.getUpdateTime(), "yyyy-MM-dd hh:mm:ss");
		}
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

	public String getDataStatusName() {
		return dataStatusName;
	}

	public void setDataStatusName(String dataStatusName) {
		this.dataStatusName = dataStatusName;
	}


	public List<DisProdPlanManage002Dto> getAddParam() {
		return addParam;
	}

	public void setAddParam(List<DisProdPlanManage002Dto> addParam) {
		this.addParam = addParam;
	}
}