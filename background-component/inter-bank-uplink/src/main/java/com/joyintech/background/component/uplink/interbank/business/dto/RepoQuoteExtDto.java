/*
 * 文件名：BondQuetoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：caoguo
 * 修改时间：2020年5月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.background.component.uplink.interbank.business.dto;

/**
 * 质押式回购报价扩展DTO 供前台查询使用
 * 
 * @author caoguo
 * @version 1.0 2020年5月7日
 * @see RepoQuoteExtDto
 * @since 1.0
 */
public class RepoQuoteExtDto extends IbuQuoteCrepDto {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -2766491650834409687L;
    
    /**
     * 本方交易员姓名
     */
    private String ouTraderName;
    
    /**
     * 交易成员简称(机构简称、交易机构名称、本方银行间机构名称)
     */
    private String partyAbbr;
    
    /**
     * 交易成员全称(机构全称)
     */
    private String partyName;
   
    /**
     * 交易机构ID，21位编号
     */
    private String partyCustomerId;
    
    /**
     * 交易成员英文简称
     */
    private String partyAbbrEn;
    
    /**
     * 交易成员英文全称
     */
    private String partyNameEn;
    
    /**
     * 
     */
    private String validStatus;

	/**
	 * 是否多组合
	 */
	private String isDzh;
    /**
     * 取得 本方交易员姓名
     * @return
     */
    public String getOuTraderName() {
		return ouTraderName;
	}

    /**
     * 设置 本方交易员姓名
     * @param ouTraderName
     */
	public void setOuTraderName(String ouTraderName) {
		this.ouTraderName = ouTraderName;
	}

	/**
	 * 取得 交易成员简称(机构简称、交易机构名称、本方银行间机构名称)
	 * @return
	 */
	public String getPartyAbbr() {
		return partyAbbr;
	}

	/**
	 * 设置 交易成员简称(机构简称、交易机构名称、本方银行间机构名称)
	 * @param partyAbbr
	 */
	public void setPartyAbbr(String partyAbbr) {
		this.partyAbbr = partyAbbr;
	}

	/**
	 * 取得 交易成员全称(机构全称)
	 * @return
	 */
	public String getPartyName() {
		return partyName;
	}

	/**
	 * 设置 交易成员全称(机构全称)
	 * @param partyName
	 */
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	/**
	 * 取得 交易机构ID，21位编号
	 * @return
	 */
	public String getPartyCustomerId() {
		return partyCustomerId;
	}

	/**
	 * 设置 交易机构ID，21位编号
	 * @param partyCustomerId
	 */
	public void setPartyCustomerId(String partyCustomerId) {
		this.partyCustomerId = partyCustomerId;
	}

	/**
	 * 取得 交易成员英文简称
	 * @return
	 */
	public String getPartyAbbrEn() {
		return partyAbbrEn;
	}

	/**
	 * 设置 交易成员英文简称
	 * @param partyAbbrEn
	 */
	public void setPartyAbbrEn(String partyAbbrEn) {
		this.partyAbbrEn = partyAbbrEn;
	}

	/**
	 * 取得 交易成员英文全称
	 * @return
	 */
	public String getPartyNameEn() {
		return partyNameEn;
	}

	/**
	 * 设置 交易成员英文全称
	 * @param partyNameEn
	 */
	public void setPartyNameEn(String partyNameEn) {
		this.partyNameEn = partyNameEn;
	}

	private String cpTraderName;
	
	private String ouPartyName;
	
	private String cpPartyName;
	
	
	public String getCpTraderName() {
		return cpTraderName;
	}

	public void setCpTraderName(String cpTraderName) {
		this.cpTraderName = cpTraderName;
	}

	public String getOuPartyName() {
		return ouPartyName;
	}

	public void setOuPartyName(String ouPartyName) {
		this.ouPartyName = ouPartyName;
	}

	public String getCpPartyName() {
		return cpPartyName;
	}

	public void setCpPartyName(String cpPartyName) {
		this.cpPartyName = cpPartyName;
	}

	/**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "RepoQuoteExtDto [, ouTraderName=" + ouTraderName + ", partyAbbr="+ partyAbbr + ", partyName=" + partyName 
        	   + ", partyCustomerId=" + partyCustomerId+ ", partyAbbrEn=" + partyAbbrEn + ", partyNameEn=" + partyNameEn 
        	   + ", portfolioName=" + super.getPortfolioName() 
               + ", getQuoteId()=" + getQuoteId()+ ", getQuoteType()=" + getQuoteType() + ", getpQuoteId()="+ getpQuoteId() 
        	   + ", getVersion()=" + getVersion() + ", getExeType()="+ getExeType() + ", getCfetsQuoteId()=" 
               + getCfetsQuoteId() + ", getQuoteRoleType()="+ getQuoteRoleType() + ", getPortfolioId()=" 
               + getPortfolioId() + ", getEntrustId()="+ getEntrustId() + ", getQuoteDate()=" 
               + getQuoteDate() + ", getvSettleDate()="+ getvSettleDate() + ", getmSettleDate()=" 
               + getmSettleDate() + ", getSettType()="+ getSettType() + ", getSettCcy()=" 
               + getSettCcy() + ", getRepoPs()="+ getRepoPs() + ", getRate()=" 
               + getRate() + ", getTerm()="+ getTerm() + ", getShareAmt()=" + getShareAmt() + ", getShareCtAmt()=" 
               + getShareCtAmt() + ", getCbShareCtAmt()=" + getCbShareCtAmt() + ", getScShareCtAmt()=" 
               + getScShareCtAmt() + ", getFpriceAmt()=" + getFpriceAmt() + ", getCbFpriceAmt()=" 
               + getCbFpriceAmt() + ", getScFpriceAmt()=" + getScFpriceAmt() + ", getmIntAmt()=" + getmIntAmt()
               + ", getmTradeAmt()=" + getmTradeAmt() + ", getBasis()=" + getBasis()
               + ", getTradeStatus()=" + getTradeStatus() + ", getSendStatus()=" + getSendStatus()
               + ", getEffTime()=" + getEffTime() + ", getOuPartyId()=" + getOuPartyId()
               + ", getOuTraderId()=" + getOuTraderId() + ", getCpPartyId()=" + getCpPartyId()
               + ", getCpTraderId()=" + getCpTraderId() + ", getMyAcctNo()=" + getMyAcctNo()
               + ", getSecManageAcctId()=" + getSecManageAcctId() + ", getExecid()=" + getExecid() 
               + ", getbVersion()=" + getbVersion() + ", getRemark()=" + getRemark() + ", getStatus()=" 
               + getStatus() + ", getWorkflowId()=" + getWorkflowId() + ", getCreateUser()=" + getCreateUser() 
               + ", getCreateDept()=" + getCreateDept() + ", getCreateTime()=" + getCreateTime() 
               + ", getUpdateUser()=" + getUpdateUser() + ", getUpdateTime()=" + getUpdateTime() 
               + ", getUpdateFlg()=" + getUpdateFlg() + ", getTermType()=" + getTermType() +"]";
    }

	public String getValidStatus() {
		return validStatus;
	}

	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}

	public String getIsDzh() {
		return isDzh;
	}

	public void setIsDzh(String isDzh) {
		this.isDzh = isDzh;
	}
}
