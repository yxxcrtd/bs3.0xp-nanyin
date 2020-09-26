/*
 * 文件名：EquityInfoDto.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：Administrator
 * 修改时间：2017年9月14日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.report.submitmaintenance.equityinfo.dto;


import java.util.Date;

public class EquityInfoDto{
    /**
     * null
     */
    private String orgIndentifyMark;

    /**
     * null
     */
    private String isBelongBank;

    /**
     * null
     */
    private String theBankName;

    /**
     * null
     */
    private String theBankCode;

    /**
     * null
     */
    private String holderMark;

    /**
     * null
     */
    private String holderRegion;

    /**
     * null
     */
    private String holderType;

    /**
     * null
     */
    private String personalIdentifyType;

    /**
     * null
     */
    private String institutionIdentifyType;

    /**
     * null
     */
    private String otherIdentifyName;

    /**
     * null
     */
    private String identifyNumber;

    /**
     * null
     */
    private String spvCapAccBank;

    /**
     * null
     */
    private String otherCapAccBank;

    /**
     * null
     */
    private String spvRegistCode;

    /**
     * null
     */
    private Date holderDate;

    /**
     * null
     */
    private String holderName;

    /**
     * null
     */
    private String holderSex;

    /**
     * null
     */
    private String holderRiskPrefer;

    /**
     * null
     */
    private String holderCellphone;

    /**
     * null
     */
    private String holderTelephone;

    /**
     * null
     */
    private String holderEmail;

    /**
     * null
     */
    private String type;

    /**
     * null
     */
    private String remark;

    /**
     * null
     */
    private Date submitTime;

    /**
     * null
     */
    private Date chbSubmitDeadline;

    /**
     * null
     */
    private Date submitDeadline;

    /**
     * null
     */
    private String deptCode;

    /**
     * null
     */
    private String sumitSeq;
    /**
     * null
     */
    private String indentifyMark;

    /**
     * null
     */
    private String registBankCode;

    /**
     * null
     */
    private String submitStatus;
    
    /**
     * 中债反馈状态
     */
    private String submitFeedbackStatus;
    /**
     * 数据状态
     */
    private String status;
    
    
    public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	/**
     * 获取 submitFeedbackStatus
     * @return submitFeedbackStatus.
     */
    public String getSubmitFeedbackStatus() {
        return submitFeedbackStatus;
    }

    
    /**
     * 设置 submitFeedbackStatus
     * @param submitFeedbackStatus submitFeedbackStatus
     */
    public void setSubmitFeedbackStatus(String submitFeedbackStatus) {
        this.submitFeedbackStatus = submitFeedbackStatus;
    }

    /**
     * 取得 null
     * @return null
     */
    public String getIndentifyMark() {
        return indentifyMark;
    }

    /**
     * 设置 null
     * @param indentifyMark null
     */
    public void setIndentifyMark(String indentifyMark) {
        this.indentifyMark = indentifyMark == null ? null : indentifyMark.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getRegistBankCode() {
        return registBankCode;
    }

    /**
     * 设置 null
     * @param registBankCode null
     */
    public void setRegistBankCode(String registBankCode) {
        this.registBankCode = registBankCode == null ? null : registBankCode.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getSubmitStatus() {
        return submitStatus;
    }

    /**
     * 设置 null
     * @param submitStatus null
     */
    public void setSubmitStatus(String submitStatus) {
        this.submitStatus = submitStatus == null ? null : submitStatus.trim();
    }
    /**
     * 取得 null
     * @return null
     */
    public String getOrgIndentifyMark() {
        return orgIndentifyMark;
    }

    /**
     * 设置 null
     * @param orgIndentifyMark null
     */
    public void setOrgIndentifyMark(String orgIndentifyMark) {
        this.orgIndentifyMark = orgIndentifyMark == null ? null : orgIndentifyMark.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getIsBelongBank() {
        return isBelongBank;
    }

    /**
     * 设置 null
     * @param isBelongBank null
     */
    public void setIsBelongBank(String isBelongBank) {
        this.isBelongBank = isBelongBank == null ? null : isBelongBank.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getTheBankName() {
        return theBankName;
    }

    /**
     * 设置 null
     * @param theBankName null
     */
    public void setTheBankName(String theBankName) {
        this.theBankName = theBankName == null ? null : theBankName.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getTheBankCode() {
        return theBankCode;
    }

    /**
     * 设置 null
     * @param theBankCode null
     */
    public void setTheBankCode(String theBankCode) {
        this.theBankCode = theBankCode == null ? null : theBankCode.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getHolderMark() {
        return holderMark;
    }

    /**
     * 设置 null
     * @param holderMark null
     */
    public void setHolderMark(String holderMark) {
        this.holderMark = holderMark == null ? null : holderMark.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getHolderRegion() {
        return holderRegion;
    }

    /**
     * 设置 null
     * @param holderRegion null
     */
    public void setHolderRegion(String holderRegion) {
        this.holderRegion = holderRegion == null ? null : holderRegion.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getHolderType() {
        return holderType;
    }

    /**
     * 设置 null
     * @param holderType null
     */
    public void setHolderType(String holderType) {
        this.holderType = holderType == null ? null : holderType.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getPersonalIdentifyType() {
        return personalIdentifyType;
    }

    /**
     * 设置 null
     * @param personalIdentifyType null
     */
    public void setPersonalIdentifyType(String personalIdentifyType) {
        this.personalIdentifyType = personalIdentifyType == null ? null : personalIdentifyType.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getInstitutionIdentifyType() {
        return institutionIdentifyType;
    }

    /**
     * 设置 null
     * @param institutionIdentifyType null
     */
    public void setInstitutionIdentifyType(String institutionIdentifyType) {
        this.institutionIdentifyType = institutionIdentifyType == null ? null : institutionIdentifyType.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getOtherIdentifyName() {
        return otherIdentifyName;
    }

    /**
     * 设置 null
     * @param otherIdentifyName null
     */
    public void setOtherIdentifyName(String otherIdentifyName) {
        this.otherIdentifyName = otherIdentifyName == null ? null : otherIdentifyName.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getIdentifyNumber() {
        return identifyNumber;
    }

    /**
     * 设置 null
     * @param identifyNumber null
     */
    public void setIdentifyNumber(String identifyNumber) {
        this.identifyNumber = identifyNumber == null ? null : identifyNumber.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getSpvCapAccBank() {
        return spvCapAccBank;
    }

    /**
     * 设置 null
     * @param spvCapAccBank null
     */
    public void setSpvCapAccBank(String spvCapAccBank) {
        this.spvCapAccBank = spvCapAccBank == null ? null : spvCapAccBank.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getOtherCapAccBank() {
        return otherCapAccBank;
    }

    /**
     * 设置 null
     * @param otherCapAccBank null
     */
    public void setOtherCapAccBank(String otherCapAccBank) {
        this.otherCapAccBank = otherCapAccBank == null ? null : otherCapAccBank.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getSpvRegistCode() {
        return spvRegistCode;
    }

    /**
     * 设置 null
     * @param spvRegistCode null
     */
    public void setSpvRegistCode(String spvRegistCode) {
        this.spvRegistCode = spvRegistCode == null ? null : spvRegistCode.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public Date getHolderDate() {
        return holderDate;
    }

    /**
     * 设置 null
     * @param holderDate null
     */
    public void setHolderDate(Date holderDate) {
        this.holderDate = holderDate;
    }

    /**
     * 取得 null
     * @return null
     */
    public String getHolderName() {
        return holderName;
    }

    /**
     * 设置 null
     * @param holderName null
     */
    public void setHolderName(String holderName) {
        this.holderName = holderName == null ? null : holderName.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getHolderSex() {
        return holderSex;
    }

    /**
     * 设置 null
     * @param holderSex null
     */
    public void setHolderSex(String holderSex) {
        this.holderSex = holderSex == null ? null : holderSex.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getHolderRiskPrefer() {
        return holderRiskPrefer;
    }

    /**
     * 设置 null
     * @param holderRiskPrefer null
     */
    public void setHolderRiskPrefer(String holderRiskPrefer) {
        this.holderRiskPrefer = holderRiskPrefer == null ? null : holderRiskPrefer.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getHolderCellphone() {
        return holderCellphone;
    }

    /**
     * 设置 null
     * @param holderCellphone null
     */
    public void setHolderCellphone(String holderCellphone) {
        this.holderCellphone = holderCellphone == null ? null : holderCellphone.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getHolderTelephone() {
        return holderTelephone;
    }

    /**
     * 设置 null
     * @param holderTelephone null
     */
    public void setHolderTelephone(String holderTelephone) {
        this.holderTelephone = holderTelephone == null ? null : holderTelephone.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getHolderEmail() {
        return holderEmail;
    }

    /**
     * 设置 null
     * @param holderEmail null
     */
    public void setHolderEmail(String holderEmail) {
        this.holderEmail = holderEmail == null ? null : holderEmail.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getType() {
        return type;
    }

    /**
     * 设置 null
     * @param type null
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 null
     * @param remark null
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public Date getSubmitTime() {
        return submitTime;
    }

    /**
     * 设置 null
     * @param submitTime null
     */
    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    /**
     * 取得 null
     * @return null
     */
    public Date getChbSubmitDeadline() {
        return chbSubmitDeadline;
    }

    /**
     * 设置 null
     * @param chbSubmitDeadline null
     */
    public void setChbSubmitDeadline(Date chbSubmitDeadline) {
        this.chbSubmitDeadline = chbSubmitDeadline;
    }

    /**
     * 取得 null
     * @return null
     */
    public Date getSubmitDeadline() {
        return submitDeadline;
    }

    /**
     * 设置 null
     * @param submitDeadline null
     */
    public void setSubmitDeadline(Date submitDeadline) {
        this.submitDeadline = submitDeadline;
    }

    /**
     * 取得 null
     * @return null
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 设置 null
     * @param deptCode null
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getSumitSeq() {
        return sumitSeq;
    }

    /**
     * 设置 null
     * @param sumitSeq null
     */
    public void setSumitSeq(String sumitSeq) {
        this.sumitSeq = sumitSeq == null ? null : sumitSeq.trim();
    }
}