package com.joyintech.tams.report.system.peoplebankcodepoolmanagement.dto;

import java.util.Date;

/**
 * 描述:PBC_PROD_ASSETPOOL_CODE表的实体类
 * 
 * @version
 * @author: kx
 * @创建时间: 2019-11-07
 */
public class ProPeoplebankcodeManagement001Dto extends ProPeoplebankcodeManagement001DtoKey {

	/**
	 * 人行代码
	 */
	private String pbcCode;

	/**
	 * 人行代码（外部）
	 */
	private String pbcProjectCodeExternal;

	/**
	 * 人行资产池代码（外部）
	 */
	private String pbcAssetPoolCodeExternal;

	/**
	 * 人行代码（系统）
	 */
	private String pbcProjectCodeSystem;

	/**
	 * 人行资产池代码（系统）
	 */
	private String pbcAssetPoolCodeSystem;

	/**
	 * 数据状态
	 */
	private String status;

	/**
	 * 机构代码
	 */
	private String orgCode;

	/**
	 * 创建人
	 */
	private String createUser;

	/**
	 * 创建部门
	 */
	private String createDept;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新人
	 */
	private String updateUser;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 代码类型名称
	 */
	private String codeTypeName;
	
	/**
	 * 内部产品代码拼接代码类型
	 */
	private String insideProdIdAndCodeType;

	/**
	 * 人行代码
	 * 
	 * @return PBC_CODE 人行代码
	 */
	public String getPbcCode() {
		return pbcCode;
	}

	/**
	 * 人行代码
	 * 
	 * @param pbcCode
	 *            人行代码
	 */
	public void setPbcCode(String pbcCode) {
		this.pbcCode = pbcCode == null ? null : pbcCode.trim();
	}

	/**
	 * 数据状态
	 * 
	 * @return STATUS 数据状态
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 数据状态
	 * 
	 * @param status
	 *            数据状态
	 */
	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	/**
	 * 机构代码
	 * 
	 * @return ORG_CODE 机构代码
	 */
	public String getOrgCode() {
		return orgCode;
	}

	/**
	 * 机构代码
	 * 
	 * @param orgCode
	 *            机构代码
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode == null ? null : orgCode.trim();
	}

	/**
	 * 创建人
	 * 
	 * @return CREATE_USER 创建人
	 */
	public String getCreateUser() {
		return createUser;
	}

	/**
	 * 创建人
	 * 
	 * @param createUser
	 *            创建人
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser == null ? null : createUser.trim();
	}

	/**
	 * 创建部门
	 * 
	 * @return CREATE_DEPT 创建部门
	 */
	public String getCreateDept() {
		return createDept;
	}

	/**
	 * 创建部门
	 * 
	 * @param createDept
	 *            创建部门
	 */
	public void setCreateDept(String createDept) {
		this.createDept = createDept == null ? null : createDept.trim();
	}

	/**
	 * 创建时间
	 * 
	 * @return CREATE_TIME 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 * 
	 * @param createTime
	 *            创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 更新人
	 * 
	 * @return UPDATE_USER 更新人
	 */
	public String getUpdateUser() {
		return updateUser;
	}

	/**
	 * 更新人
	 * 
	 * @param updateUser
	 *            更新人
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser == null ? null : updateUser.trim();
	}

	/**
	 * 更新时间
	 * 
	 * @return UPDATE_TIME 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 更新时间
	 * 
	 * @param updateTime
	 *            更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getPbcProjectCodeExternal() {
		return pbcProjectCodeExternal;
	}

	public void setPbcProjectCodeExternal(String pbcProjectCodeExternal) {
		this.pbcProjectCodeExternal = pbcProjectCodeExternal;
	}

	public String getPbcAssetPoolCodeExternal() {
		return pbcAssetPoolCodeExternal;
	}

	public void setPbcAssetPoolCodeExternal(String pbcAssetPoolCodeExternal) {
		this.pbcAssetPoolCodeExternal = pbcAssetPoolCodeExternal;
	}

	public String getPbcProjectCodeSystem() {
		return pbcProjectCodeSystem;
	}

	public void setPbcProjectCodeSystem(String pbcProjectCodeSystem) {
		this.pbcProjectCodeSystem = pbcProjectCodeSystem;
	}

	public String getPbcAssetPoolCodeSystem() {
		return pbcAssetPoolCodeSystem;
	}

	public void setPbcAssetPoolCodeSystem(String pbcAssetPoolCodeSystem) {
		this.pbcAssetPoolCodeSystem = pbcAssetPoolCodeSystem;
	}

	public String getCodeTypeName() {
		return codeTypeName;
	}

	public void setCodeTypeName(String codeTypeName) {
		this.codeTypeName = codeTypeName;
	}

	public String getInsideProdIdAndCodeType() {
		return insideProdIdAndCodeType;
	}

	public void setInsideProdIdAndCodeType(String insideProdIdAndCodeType) {
		this.insideProdIdAndCodeType = insideProdIdAndCodeType;
	}
	
	

}