package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class DebtBaseInfo extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 债券代码(金融产品代码)
	 */
	private String finProdId;
	
	/**
	 * 含权债标记
	 */
	private String isRight;
	
	/**
	 * 交易对手代码
	 */
	private String counterId;
	
	/**
	 * 交易对手类型
	 */
	private String counterType;
	
	/**
	 * 交易对手名称
	 */
	private String counterName;
	/**
	 * 关联代码
	 */
	private String linkId;
	/**
	 * 管理人
	 */
	private String managerId;
	/**
	 * 所属总行
	 */
	private String hendBank;
	/**
	 * 联系人
	 */
	private String contact;
	/**
	 * 联系方式
	 */
	private String contactWay;
	/**
	 * 备注
	 */
	private String remark;	
	/**
	 * 关联内部机构
	 */
	private String LinkDeptCode;
	/**
	 * 人行一级分类
	 */
	private String p_type_one;
	/**
	 * 人行二级分类
	 */
	private String p_type_two;
	/**
	 * 创建人
	 */
	private String createUser;
	/**
	 * 创建部门
	 */
	private String createDept;
	/**
	 * 更新人
	 */
	private String updateUser;
	
	
	
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
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getFinProdId() {
		return finProdId;
	}
	public void setFinProdId(String finProdId) {
		this.finProdId = finProdId;
	}
	public String getIsRight() {
		return isRight;
	}
	public void setIsRight(String isRight) {
		this.isRight = isRight;
	}
	public String getCounterId() {
		return counterId;
	}
	public void setCounterId(String counterId) {
		this.counterId = counterId;
	}
	public String getCounterType() {
		return counterType;
	}
	public void setCounterType(String counterType) {
		this.counterType = counterType;
	}
	public String getCounterName() {
		return counterName;
	}
	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}
	public String getLinkId() {
		return linkId;
	}
	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getHendBank() {
		return hendBank;
	}
	public void setHendBank(String hendBank) {
		this.hendBank = hendBank;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getContactWay() {
		return contactWay;
	}
	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getLinkDeptCode() {
		return LinkDeptCode;
	}
	public void setLinkDeptCode(String linkDeptCode) {
		LinkDeptCode = linkDeptCode;
	}
	public String getP_type_one() {
		return p_type_one;
	}
	public void setP_type_one(String p_type_one) {
		this.p_type_one = p_type_one;
	}
	public String getP_type_two() {
		return p_type_two;
	}
	public void setP_type_two(String p_type_two) {
		this.p_type_two = p_type_two;
	}
}
