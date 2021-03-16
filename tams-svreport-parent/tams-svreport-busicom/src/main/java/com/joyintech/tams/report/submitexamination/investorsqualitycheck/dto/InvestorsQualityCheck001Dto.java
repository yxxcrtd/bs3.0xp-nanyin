package com.joyintech.tams.report.submitexamination.investorsqualitycheck.dto;

import java.math.BigDecimal;
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
    
    /**
     * 产品发行状态
     */
    private String prdIssueSts;
    
    /**
     * 产品实际终止日期
     */
    private Date prdAclMDate;
    
    /**
     * 产品起息日
     */
    private Date prdVDate;
    
    /**
     * 产品起息日字符串
     */
    private String prdVDateStr;
    
    /**
     * 产品到期日
     */
    private Date prdMDate;
    
    /**
     * 产品到期日字符串
     */
    private String prdMDateStr;
    
    /**
     * 产品端个人投资者数
     */
    private BigDecimal prdSingleTotal;
    
    /**
     * 产品端法人投资者数
     */
    private BigDecimal prdLegalTotal;
    
    /**
     * 产品端非法人投资者数
     */
    private BigDecimal prdUnLegalTotal;
    
    /**
     * 持有端个人投资者数
     */
    private BigDecimal investSingleTotal;
    
    /**
     * 持有端法人投资者数
     */
    private BigDecimal investLegalTotal;
    
    /**
     * 持有端非法人投资者数
     */
    private BigDecimal investUnLegalTotal;
    
    /**
     * 错误记录数
     */
    private BigDecimal errRecTotal;
    
    /**
     * 产品端募集总份额
     */
    private BigDecimal prdRaiseTotalQty;
    
    /**
     * 持有端募集总份额
     */
    
    private BigDecimal investRaiseTotalQty;
    
    /**
     * 产品端募集金额
     */
    private BigDecimal prdRaiseTotalAmt;
    
    /**
     * 持有端募集金额
     */
    private BigDecimal investRaiseTotalAmt;
    
    /**
     * 业务结束日
     */
    private Date busiMDate;
    
    /**
     * 业务结束日字符串
     */
    private String busiMDateStr;
    
    /**
     * 产品端最新产品余额
     */
    private BigDecimal prdBalanceAmt;
    
    /**
     * 持有端产品余额
     */
    private BigDecimal investBalanceAmt;
    
    /**
     * 产品端最新产品份额
     */
    private BigDecimal prdTotalQty;

    /**
     * 持有端产品份额
     */
    private BigDecimal investTotalQty;
    
    public String getPrdIssueSts() {
		return prdIssueSts;
	}

	public void setPrdIssueSts(String prdIssueSts) {
		this.prdIssueSts = prdIssueSts;
	}

	public Date getPrdAclMDate() {
		return prdAclMDate;
	}

	public void setPrdAclMDate(Date prdAclMDate) {
		this.prdAclMDate = prdAclMDate;
	}

	public Date getPrdVDate() {
		return prdVDate;
	}

	public void setPrdVDate(Date prdVDate) {
		this.prdVDate = prdVDate;
	}

	public String getPrdVDateStr() {
		return prdVDateStr;
	}

	public void setPrdVDateStr(String prdVDateStr) {
		this.prdVDateStr = prdVDateStr;
	}

	public Date getPrdMDate() {
		return prdMDate;
	}

	public void setPrdMDate(Date prdMDate) {
		this.prdMDate = prdMDate;
	}

	public String getPrdMDateStr() {
		return prdMDateStr;
	}

	public void setPrdMDateStr(String prdMDateStr) {
		this.prdMDateStr = prdMDateStr;
	}

	public BigDecimal getPrdSingleTotal() {
		return prdSingleTotal;
	}

	public void setPrdSingleTotal(BigDecimal prdSingleTotal) {
		this.prdSingleTotal = prdSingleTotal;
	}

	public BigDecimal getPrdLegalTotal() {
		return prdLegalTotal;
	}

	public void setPrdLegalTotal(BigDecimal prdLegalTotal) {
		this.prdLegalTotal = prdLegalTotal;
	}

	public BigDecimal getPrdUnLegalTotal() {
		return prdUnLegalTotal;
	}

	public void setPrdUnLegalTotal(BigDecimal prdUnLegalTotal) {
		this.prdUnLegalTotal = prdUnLegalTotal;
	}

	public BigDecimal getInvestSingleTotal() {
		return investSingleTotal;
	}

	public void setInvestSingleTotal(BigDecimal investSingleTotal) {
		this.investSingleTotal = investSingleTotal;
	}

	public BigDecimal getInvestLegalTotal() {
		return investLegalTotal;
	}

	public void setInvestLegalTotal(BigDecimal investLegalTotal) {
		this.investLegalTotal = investLegalTotal;
	}

	public BigDecimal getInvestUnLegalTotal() {
		return investUnLegalTotal;
	}

	public void setInvestUnLegalTotal(BigDecimal investUnLegalTotal) {
		this.investUnLegalTotal = investUnLegalTotal;
	}

	public BigDecimal getErrRecTotal() {
		return errRecTotal;
	}

	public void setErrRecTotal(BigDecimal errRecTotal) {
		this.errRecTotal = errRecTotal;
	}

	public BigDecimal getPrdRaiseTotalQty() {
		return prdRaiseTotalQty;
	}

	public void setPrdRaiseTotalQty(BigDecimal prdRaiseTotalQty) {
		this.prdRaiseTotalQty = prdRaiseTotalQty;
	}

	public BigDecimal getInvestRaiseTotalQty() {
		return investRaiseTotalQty;
	}

	public void setInvestRaiseTotalQty(BigDecimal investRaiseTotalQty) {
		this.investRaiseTotalQty = investRaiseTotalQty;
	}

	public BigDecimal getPrdRaiseTotalAmt() {
		return prdRaiseTotalAmt;
	}

	public void setPrdRaiseTotalAmt(BigDecimal prdRaiseTotalAmt) {
		this.prdRaiseTotalAmt = prdRaiseTotalAmt;
	}

	public BigDecimal getInvestRaiseTotalAmt() {
		return investRaiseTotalAmt;
	}

	public void setInvestRaiseTotalAmt(BigDecimal investRaiseTotalAmt) {
		this.investRaiseTotalAmt = investRaiseTotalAmt;
	}

	public Date getBusiMDate() {
		return busiMDate;
	}

	public void setBusiMDate(Date busiMDate) {
		this.busiMDate = busiMDate;
	}

	public String getBusiMDateStr() {
		return busiMDateStr;
	}

	public void setBusiMDateStr(String busiMDateStr) {
		this.busiMDateStr = busiMDateStr;
	}

	public BigDecimal getPrdBalanceAmt() {
		return prdBalanceAmt;
	}

	public void setPrdBalanceAmt(BigDecimal prdBalanceAmt) {
		this.prdBalanceAmt = prdBalanceAmt;
	}

	public BigDecimal getInvestBalanceAmt() {
		return investBalanceAmt;
	}

	public void setInvestBalanceAmt(BigDecimal investBalanceAmt) {
		this.investBalanceAmt = investBalanceAmt;
	}

	public BigDecimal getPrdTotalQty() {
		return prdTotalQty;
	}

	public void setPrdTotalQty(BigDecimal prdTotalQty) {
		this.prdTotalQty = prdTotalQty;
	}

	public BigDecimal getInvestTotalQty() {
		return investTotalQty;
	}

	public void setInvestTotalQty(BigDecimal investTotalQty) {
		this.investTotalQty = investTotalQty;
	}

	
    
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
