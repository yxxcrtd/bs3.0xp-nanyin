/*
 * 文件名：RepIndexPrimary001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.reportcontentenum.dto;

import java.util.List;

import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.tams.common.base.entity.RepIndexPrimary;

public class ReportContentEnum001Dto extends RepIndexPrimary {

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@NotEmpty(fieldName = "指标代码")
	private String id;

	/**
	 * 指标名称
	 */
	@NotEmpty(fieldName = "指标名称")
	private String indexName;

	/**
	 * 指标描述
	 */
	private String indexDescribe;

	/**
	 * 操作类型
	 */
	private String operateType;

	private String prodId;
	/**
	 * 已设置规则
	 */
	private List<ReportContentEnum003Dto> settedRule;

	public List<ReportContentEnum003Dto> getSettedRule() {
		return settedRule;
	}

	public void setSettedRule(List<ReportContentEnum003Dto> settedRule) {
		this.settedRule = settedRule;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
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
	 * 获取 indexDescribe
	 * 
	 * @return indexDescribe.
	 */
	public String getIndexDescribe() {
		return indexDescribe;
	}

	/**
	 * 设置 indexDescribe
	 * 
	 * @param indexDescribe
	 *            indexDescribe
	 */
	public void setIndexDescribe(String indexDescribe) {
		this.indexDescribe = indexDescribe;
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

}
