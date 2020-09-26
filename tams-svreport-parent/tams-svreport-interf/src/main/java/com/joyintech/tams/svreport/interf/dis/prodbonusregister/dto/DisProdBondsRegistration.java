package com.joyintech.tams.svreport.interf.dis.prodbonusregister.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

public class DisProdBondsRegistration extends BaseEntity{	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * ID
     */
    private String id;   
    /**
     * 产品代码
     */
    private String prodId;    
    /**
     * 分红登记日 
     */
    private Date bonusRegisterDate;    
    /**
     * 分红下发日
     */
    private Date bonusDealtDate;    
    /**
     * 单位分红金额
     */
    private BigDecimal unitBonusAmt; 
    /**
     * 导入方法
     */
    private String inputType;
    /**
     * 导入用户
     */
    private String inputUser;
    /**
     * 适配器ID
     */
    private String adapterId;
    
    /**
     * 备注
     */
    private String remark;
    /**
     * 数据状态
     */
    private String status;
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
    private String updateteUser;    
    /**
     * 更新时间
     */
    private Date updateTime;

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public Date getBonusRegisterDate() {
		return bonusRegisterDate;
	}

	public void setBonusRegisterDate(Date bonusRegisterDate) {
		this.bonusRegisterDate = bonusRegisterDate;
	}

	public Date getBonusDealtDate() {
		return bonusDealtDate;
	}

	public void setBonusDealtDate(Date bonusDealtDate) {
		this.bonusDealtDate = bonusDealtDate;
	}

	public BigDecimal getUnitBonusAmt() {
		return unitBonusAmt;
	}

	public void setUnitBonusAmt(BigDecimal unitBonusAmt) {
		this.unitBonusAmt = unitBonusAmt;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getUpdateteUser() {
		return updateteUser;
	}

	public void setUpdateteUser(String updateteUser) {
		this.updateteUser = updateteUser;
	}

	public String getInputType() {
		return inputType;
	}

	public void setInputType(String inputType) {
		this.inputType = inputType;
	}

	public String getInputUser() {
		return inputUser;
	}

	public void setInputUser(String inputUser) {
		this.inputUser = inputUser;
	}

	public String getAdapterId() {
		return adapterId;
	}

	public void setAdapterId(String adapterId) {
		this.adapterId = adapterId;
	}	
}