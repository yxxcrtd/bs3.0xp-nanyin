/*
 * 文件名：RepIndexSub001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.reportcontentenum.dto;

import java.util.Date;
import java.util.List;

import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.tams.common.base.entity.RepIndexSub;
import com.joyintech.tams.disclosure.interf.interf.common.constant.DateUtil;

public class ReportContentEnum002Dto extends RepIndexSub {
	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	private String id;


	/**
	 * 指标名称
	 */
	private String indexName;

	/**
	 * 指标值
	 */
	@NotEmpty(fieldName = "指标值")
	private String indexValue;

	/**
	 * 指标类型
	 */
	private String indexType;

	/**
	 * 操作类型
	 */
	private String operateType;

	/**
	 * 审核状态
	 */
	private String auditStatus;
	
	/**
	 * 适用范围说明
	 */
	private String indexDetail;
	
	/**
	 * 文件名称
	 */
	private String indexFile;
	
	/**
	 * 生效日期
	 */
	@NotEmpty(fieldName = "生效日期")
	private Date indexDate;
	/**
	 * 生效日期
	 */
	@SuppressWarnings("unused")
	private String indexDateStr;
	/**
	 * 规则数据
	 */
	private List<ReportContentEnum003Dto> settedRule;
	
	private String updateTimeFmt;

	public Date getIndexDate() {
		return indexDate;
	}

	public void setIndexDate(Date indexDate) {
		this.indexDate = indexDate;
	}

	public String getIndexDateStr() {
		return DateUtil.format(this.getIndexDate());
	}

	public void setIndexDateStr(String indexDateStr) {
		this.indexDateStr = indexDateStr;
	}

	public List<ReportContentEnum003Dto> getSettedRule() {
		return settedRule;
	}

	public void setSettedRule(List<ReportContentEnum003Dto> settedRule) {
		this.settedRule = settedRule;
	}

	public String getIndexFile() {
		return indexFile;
	}

	public void setIndexFile(String indexFile) {
		this.indexFile = indexFile;
	}

	/**
	 * 获取 indexName
	 * 
	 * @return indexName.
	 */
	public String getIndexName() {
		return indexName;
	}

	/**
	 * 设置 indexName
	 * 
	 * @param indexName
	 *            indexName
	 */
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	/**
	 * 获取 indexValue
	 * 
	 * @return indexValue.
	 */
	public String getIndexValue() {
		return indexValue;
	}

	/**
	 * 设置 indexValue
	 * 
	 * @param indexValue
	 *            indexValue
	 */
	public void setIndexValue(String indexValue) {
		this.indexValue = indexValue;
	}

	/**
	 * 获取 indexType
	 * 
	 * @return indexType.
	 */
	public String getIndexType() {
		return indexType;
	}

	/**
	 * 设置 indexType
	 * 
	 * @param indexType
	 *            indexType
	 */
	public void setIndexType(String indexType) {
		this.indexType = indexType;
	}

	/**
	 * 获取 operateType
	 * 
	 * @return operateType.
	 */
	public String getOperateType() {
		return operateType;
	}

	/**
	 * 设置 operateType
	 * 
	 * @param operateType
	 *            operateType
	 */
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	/**
	 * 获取 id
	 * 
	 * @return id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置 id
	 * 
	 * @param id
	 *            id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取 auditStatus
	 * 
	 * @return auditStatus.
	 */
	public String getAuditStatus() {
		return auditStatus;
	}

	/**
	 * 设置 auditStatus
	 * 
	 * @param auditStatus
	 *            auditStatus
	 */
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getUpdateTimeFmt() {
		return updateTimeFmt;
	}

	public void setUpdateTimeFmt(String updateTimeFmt) {
		this.updateTimeFmt = updateTimeFmt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getIndexDetail() {
		return indexDetail;
	}

	public void setIndexDetail(String indexDetail) {
		this.indexDetail = indexDetail;
	}
	
}
