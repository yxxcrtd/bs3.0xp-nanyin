package com.joyintech.service.product.rddesgin.colproductrequirement.dto;

import java.util.Date;

/**
 * 产品需求附属表
 * @author yaozhenguo
 *
 */
public class ColProductRequirementAddDto {
	
	/**
	 * 产品需求编号	REQUIREMENT_ID	varchar2（50）
	 */
	private String requirementId;
	
	/**
	 * 初评结果	REPORT_STATUS	varchar2（50）
	 */
	private String reportStatus;
	
	/**
	 * 可行性状态	FEASIBILITY_STATUS	varchar2（50）
	 */
	private String feasibilityStatus;
	
	/**
	 * 可行需求分类	FEASIBILITY_TYPE	varchar2（50）
	 */
	private String feasibilityType;
	
	/**
	 * 创建人	CREATE_USER	VARCHAR(20)
	 */
	private String createUser;
	
	/**
	 * 创建部门	CREATE_DEPT	VARCHAR(32)
	 */
	private String createDept;
	
	/**
	 * 创建时间	CREATE_TIME	TIMESTAMP
	 */
	private Date creeatTime;
	
	/**
	 * 更新人	UPDATE_USER	VARCHAR(20)
	 */
	private String updateUser;
	
	/**
	 * 更新时间	UPDATE_TIME	TIMESTAMP
	 */
	private Date updateTime;

	public String getRequirementId() {
		return requirementId;
	}

	public void setRequirementId(String requirementId) {
		this.requirementId = requirementId;
	}

	public String getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
	}

	public String getFeasibilityStatus() {
		return feasibilityStatus;
	}

	public void setFeasibilityStatus(String feasibilityStatus) {
		this.feasibilityStatus = feasibilityStatus;
	}

	public String getFeasibilityType() {
		return feasibilityType;
	}

	public void setFeasibilityType(String feasibilityType) {
		this.feasibilityType = feasibilityType;
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

	public Date getCreeatTime() {
		return creeatTime;
	}

	public void setCreeatTime(Date creeatTime) {
		this.creeatTime = creeatTime;
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
	
	

}
