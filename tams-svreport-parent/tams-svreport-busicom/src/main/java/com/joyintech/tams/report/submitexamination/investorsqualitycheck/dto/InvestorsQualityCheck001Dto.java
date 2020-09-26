package com.joyintech.tams.report.submitexamination.investorsqualitycheck.dto;

import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

public class InvestorsQualityCheck001Dto extends BaseEntity{

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 核查失败数量
     */
    private String unQualified;
    /**
     * 总数量
     */
    private String total;
    /**
     * 合格率
     */
    private String rate;
    /**
     * 数据的类型
     */
    private String pageType;
    
    /**
     * 产品登记编码
     */
    private String prdRegistCode;
    
    /**
     * 登记银行代码
     */
    private String registBankCode;
    
    /**
     * 识别标识
     */
    private String indentifyMark;
    
    /**
     * 权益人境内外标识
     */
    private String holderMark;
    
    /**
     * 权益人类别
     */
    private String holderType;
    
    private String personalIdentifyType;// 个人证件类型

    private String institutionIdentifyType;// 机构证件类型

    /**
     * 证件类别
     */
    private String identifyType;
    
    /**
     * 证件类别
     */
    private String identifyTypeStr;
    
    /**
     * 证件号码
     */
    private String identifyNumber;
    
    /**
     * 权益人名称
     */
    private String holderName;
    
    /**
     * 持有日期
     */
    private Date holdDate;

    /**
     * 报送状态
     */
    private String sendStatus;

    /**
     * 报送日期
     */
    private Date submitTime;
    
    private String holdDateStr;
    
    private String submitTimeStr;

	public String getUnQualified() {
		return unQualified;
	}

	public void setUnQualified(String unQualified) {
		this.unQualified = unQualified;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public String getPrdRegistCode() {
		return prdRegistCode;
	}

	public void setPrdRegistCode(String prdRegistCode) {
		this.prdRegistCode = prdRegistCode;
	}

	public String getRegistBankCode() {
		return registBankCode;
	}

	public void setRegistBankCode(String registBankCode) {
		this.registBankCode = registBankCode;
	}

	public String getIndentifyMark() {
		return indentifyMark;
	}

	public void setIndentifyMark(String indentifyMark) {
		this.indentifyMark = indentifyMark;
	}

	public String getHolderMark() {
		return holderMark;
	}

	public void setHolderMark(String holderMark) {
		this.holderMark = holderMark;
	}

	public String getHolderType() {
		return holderType;
	}

	public void setHolderType(String holderType) {
		this.holderType = holderType;
	}

	public String getPersonalIdentifyType() {
		return personalIdentifyType;
	}

	public void setPersonalIdentifyType(String personalIdentifyType) {
		this.personalIdentifyType = personalIdentifyType;
	}

	public String getInstitutionIdentifyType() {
		return institutionIdentifyType;
	}

	public void setInstitutionIdentifyType(String institutionIdentifyType) {
		this.institutionIdentifyType = institutionIdentifyType;
	}

	public String getIdentifyNumber() {
		return identifyNumber;
	}

	public void setIdentifyNumber(String identifyNumber) {
		this.identifyNumber = identifyNumber;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public Date getHoldDate() {
		return holdDate;
	}

	public void setHoldDate(Date holdDate) {
		this.holdDate = holdDate;
	}

	public String getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public String getHoldDateStr() {
		return holdDateStr;
	}

	public void setHoldDateStr(String holdDateStr) {
		this.holdDateStr = holdDateStr;
	}

	public String getSubmitTimeStr() {
		return submitTimeStr;
	}

	public void setSubmitTimeStr(String submitTimeStr) {
		this.submitTimeStr = submitTimeStr;
	}

	public String getIdentifyType() {
		return identifyType;
	}

	public void setIdentifyType(String identifyType) {
		this.identifyType = identifyType;
	}

	public String getIdentifyTypeStr() {
		return identifyTypeStr;
	}

	public void setIdentifyTypeStr(String identifyTypeStr) {
		this.identifyTypeStr = identifyTypeStr;
	}

    
}
