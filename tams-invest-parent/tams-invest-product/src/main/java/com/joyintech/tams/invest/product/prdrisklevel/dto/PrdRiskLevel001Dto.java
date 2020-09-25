/*
 * 文件名：FinProductPayInt001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.prdrisklevel.dto;

import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

/**
 * 新发产品风险等级评定查询dto
 */
public class PrdRiskLevel001Dto extends BaseEntity {

	private static final long serialVersionUID = -6490393343772328265L;

	// 流水号
	private String seqNo;

	// 生成时间
	private Date generateTime;

	// 金融产品代码
	private String finProductCode;
	
	// 金融产品名称
	private String finProductName;

	// 分支序号
	private Long branch;

	// 风险等级
	private String riskLevel;

	// 调整后风险等级
	private String ajustRiskLevel;

	// 创建人
	private String createUser;

	// 创建部门
	private String createDept;

	// 创建时间
	private Date createTime;

	// 更新人
	private String updateUser;

	// 更新时间
	private Date updateTime;
	
	private String riskLevelStr; // 风险等级str

	private String ajustRiskLevelStr; // 调整后风险等级str
	
	private String prdDesignId; // 产品设计编号
	
    // 要素ID
    private String elementId;

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public Date getGenerateTime() {
		return generateTime;
	}

	public void setGenerateTime(Date generateTime) {
		this.generateTime = generateTime;
	}

	public String getFinProductCode() {
		return finProductCode;
	}

	public void setFinProductCode(String finProductCode) {
		this.finProductCode = finProductCode;
	}

	public Long getBranch() {
		return branch;
	}

	public void setBranch(Long branch) {
		this.branch = branch;
	}

	public String getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}

	public String getAjustRiskLevel() {
		return ajustRiskLevel;
	}

	public void setAjustRiskLevel(String ajustRiskLevel) {
		this.ajustRiskLevel = ajustRiskLevel;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateDept() {
		return createDept;
	}

	public void setCreateDept(String createDept) {
		this.createDept = createDept;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getFinProductName() {
		return finProductName;
	}

	public void setFinProductName(String finProductName) {
		this.finProductName = finProductName;
	}

	public String getRiskLevelStr() {
		return riskLevelStr;
	}

	public void setRiskLevelStr(String riskLevelStr) {
		this.riskLevelStr = riskLevelStr;
	}

	public String getAjustRiskLevelStr() {
		return ajustRiskLevelStr;
	}

	public void setAjustRiskLevelStr(String ajustRiskLevelStr) {
		this.ajustRiskLevelStr = ajustRiskLevelStr;
	}

	public String getPrdDesignId() {
		return prdDesignId;
	}

	public void setPrdDesignId(String prdDesignId) {
		this.prdDesignId = prdDesignId;
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}
}
