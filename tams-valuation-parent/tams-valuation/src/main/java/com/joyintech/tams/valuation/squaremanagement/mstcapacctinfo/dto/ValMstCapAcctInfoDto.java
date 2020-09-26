/*
 * 文件名：ValMstCapAcctInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月23日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.squaremanagement.mstcapacctinfo.dto;

import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

/**
 * 
 * 结算指令查询Dto
 * @author ruanhongming
 * @version 1.0 2018年5月23日
 * @see ValMstCapAcctInfoDto
 * @since 1.0
 */
public class ValMstCapAcctInfoDto extends BaseEntity {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 账号
     */
    private String acctNo;

    /**
     * 币种
     */
    private String cyy;

    /**
     * 户名
     */
    private String acctName;

    /**
     * 开户行
     */
    private String bankDep;
    
    /**
     * 行内行外
     */
    private String inOutBank;
    
    /**
     * 联行行号
     */
    private String corrNo;
    
    /**
     * 大额支付系统号
     */
    private String largePayno;
    
    /**
     * 境内境外
     */
    private String inOut;

    /**
     * 是否是DVP清算
     */
    private String isDvp;
    
    /**
     * 是否允许透支
     */
    private String isOverdraw;
    
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
     * 处理操作
     */
    private String dealType;

	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public String getCyy() {
		return cyy;
	}

	public void setCyy(String cyy) {
		this.cyy = cyy;
	}

	public String getAcctName() {
		return acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

	public String getBankDep() {
		return bankDep;
	}

	public void setBankDep(String bankDep) {
		this.bankDep = bankDep;
	}

	public String getInOutBank() {
		return inOutBank;
	}

	public void setInOutBank(String inOutBank) {
		this.inOutBank = inOutBank;
	}

	public String getCorrNo() {
		return corrNo;
	}

	public void setCorrNo(String corrNo) {
		this.corrNo = corrNo;
	}

	public String getLargePayno() {
		return largePayno;
	}

	public void setLargePayno(String largePayno) {
		this.largePayno = largePayno;
	}

	public String getInOut() {
		return inOut;
	}

	public void setInOut(String inOut) {
		this.inOut = inOut;
	}

	public String getIsDvp() {
		return isDvp;
	}

	public void setIsDvp(String isDvp) {
		this.isDvp = isDvp;
	}

	public String getIsOverdraw() {
		return isOverdraw;
	}

	public void setIsOverdraw(String isOverdraw) {
		this.isOverdraw = isOverdraw;
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

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}
}     				
      			
      