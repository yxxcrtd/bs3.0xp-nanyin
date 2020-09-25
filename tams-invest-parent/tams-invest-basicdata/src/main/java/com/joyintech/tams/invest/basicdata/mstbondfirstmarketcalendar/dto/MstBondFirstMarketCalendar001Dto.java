/*
 * 文件名：MstBondFirstMarketCalendar001Dto.java
 * 版权：Copyright 2017-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 创建人：赵东东
 * 创建时间：2019年11月16日
 * 创建内容：查询
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.basicdata.mstbondfirstmarketcalendar.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.basicdata.mstbondfirstmarketcalendar.valid.MstBondFirstMarketCalendar001Validator;



/**
 * 
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zhaodongdong 
 * @version 1.0 2018年9月25日
 * @see MstBondFirstMarketCalendar001Dto
 * @since 1.0
 */
@PageId("MstBondFirstMarketCalendar001")
@ValidLogic(targetClass = MstBondFirstMarketCalendar001Validator.class, groups = {ValidateGroupEdit.class,
	    ValidateGroupDelete.class})
public class MstBondFirstMarketCalendar001Dto extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 流水号
	 */
	private String seqno;
	/**
	 * 债券代码
	 */
	private String secId;
	/**
	 * 债券简称
	 */
	private String secAbbr;
	/**
	 * 期限
	 */
	private BigDecimal termYears;
	/**
	 * 发行额度
	 */
	private BigDecimal issueAmt;
	/**
	 * 债项评级
	 */
	private String secGradeResult;
	/**
	 * 主体评级
	 */
	private String issuerGradeResult;
	/**
	 * 投标区间（最小）
	 */
	private BigDecimal bidPriceMin;
	/**
	 * 投标区间（最大）
	 */
	private BigDecimal bidPriceMax;
	/**
	 * 发行利率
	 */
	private BigDecimal issueRate;
	/**
	 * 发行人
	 */
	private String issuer;
	/**
	 * 担保人
	 */
	private String sponsor;
	/**
	 * 特殊条款
	 */
	private String othClause;
	/**
	 * 投标日
	 */
	private Date bidDate;
	/**
	 * 缴款日
	 */
	private Date payDate;
	/**
	 * 上市日
	 */
	private Date onmarketDate;
	/**
	 * 主承销商
	 */
	private String mainSaler;
	/**
	 * 交易场所
	 */
	private String tradeMarket;
	/**
	 * 登记托管机构
	 */
	private String registOrg;
	/**
	 * 发行人名称
	 */
	private String issuerName;
	/**
	 * 担保人名称
	 */
	private String sponsorName;
	/**
	 * 主承销商名称
	 */
	private String mainSalerName;
	/**
	 * 获取mainSalerName
	 * @return
	 */
	public String getMainSalerName() {
		return mainSalerName;
	}
	/**
	 * 设置mainSalerName
	 * @param mainSalerName
	 */
	public void setMainSalerName(String mainSalerName) {
		this.mainSalerName = mainSalerName;
	}
	/**
	 * 获取sponsorName
	 * @return
	 */
	public String getSponsorName() {
		return sponsorName;
	}
	/**
	 * 设置sponsorName
	 * @param sponsorName
	 */
	public void setSponsorName(String sponsorName) {
		this.sponsorName = sponsorName;
	}
	/**
	 * 获取issuerName
	 * @return
	 */
	public String getIssuerName() {
		return issuerName;
	}
	/**
	 * 设置issuerName
	 * @param issuerName
	 */
	public void setIssuerName(String issuerName) {
		this.issuerName = issuerName;
	}
	/**
	 * 获取seqno
	 * @return
	 */
	public String getSeqno() {
		return seqno;
	}
	/**
	 * 设置seqno
	 * @param seqno
	 */
	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}
	/**
	 * 获取secId
	 * @return
	 */
	public String getSecId() {
		return secId;
	}
	/**
	 * 设置secId
	 * @param secId
	 */
	public void setSecId(String secId) {
		this.secId = secId;
	}
	/**
	 * 获取secAbbr
	 * @return
	 */
	public String getSecAbbr() {
		return secAbbr;
	}
	/**
	 * 设置secAbbr
	 * @param secAbbr
	 */
	public void setSecAbbr(String secAbbr) {
		this.secAbbr = secAbbr;
	}
	/**
	 * 获取termYears
	 * @return
	 */
	public BigDecimal getTermYears() {
		return termYears;
	}
	/**
	 * 设置termYears
	 * @param termYears
	 */
	public void setTermYears(BigDecimal termYears) {
		this.termYears = termYears;
	}
	/**
	 * 获取issueAmt
	 * @return
	 */
	public BigDecimal getIssueAmt() {
		return issueAmt;
	}
	/**
	 *设置issueAmt 
	 * @param issueAmt
	 */
	public void setIssueAmt(BigDecimal issueAmt) {
		this.issueAmt = issueAmt;
	}
	/**
	 * 获取secGradeResult
	 * @return
	 */
	public String getSecGradeResult() {
		return secGradeResult;
	}
	/**
	 * 设置secGradeResult
	 * @param secGradeResult
	 */
	public void setSecGradeResult(String secGradeResult) {
		this.secGradeResult = secGradeResult;
	}
	/**
	 * 获取issuerGradeResult
	 * @return
	 */
	public String getIssuerGradeResult() {
		return issuerGradeResult;
	}
	/**
	 * 设置issuerGradeResult
	 * @param issuerGradeResult
	 */
	public void setIssuerGradeResult(String issuerGradeResult) {
		this.issuerGradeResult = issuerGradeResult;
	}
	/**
	 * 获取bidPriceMin
	 * @return
	 */
	public BigDecimal getBidPriceMin() {
		return bidPriceMin;
	}
	/**
	 * 设置bidPriceMin
	 * @param bidPriceMin
	 */
	public void setBidPriceMin(BigDecimal bidPriceMin) {
		this.bidPriceMin = bidPriceMin;
	}
	/**
	 * 获取bidPriceMax
	 * @return
	 */
	public BigDecimal getBidPriceMax() {
		return bidPriceMax;
	}
	/**
	 * 设置bidPriceMax
	 * @param bidPriceMax
	 */
	public void setBidPriceMax(BigDecimal bidPriceMax) {
		this.bidPriceMax = bidPriceMax;
	}
	/**
	 * 获取issueRate
	 * @return
	 */
	public BigDecimal getIssueRate() {
		return issueRate;
	}
	/**
	 *设置issueRate 
	 * @param issueRate
	 */
	public void setIssueRate(BigDecimal issueRate) {
		this.issueRate = issueRate;
	}
	/**
	 * 获取issuer
	 * @return
	 */
	public String getIssuer() {
		return issuer;
	}
	/**
	 * 设置issuer
	 * @param issuer
	 */
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	/**
	 * 获取sponsor
	 * @return
	 */
	public String getSponsor() {
		return sponsor;
	}
	/**
	 * 设置sponsor
	 * @param sponsor
	 */
	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}
	/**
	 * 获取othClause
	 * @return
	 */
	public String getOthClause() {
		return othClause;
	}
	/**
	 * 设置othClause
	 * @param othClause
	 */
	public void setOthClause(String othClause) {
		this.othClause = othClause;
	}
	/**
	 * 获取bidDate
	 * @return
	 */
	public Date getBidDate() {
		return bidDate;
	}
	/**
	 * 设置bidDate
	 * @param bidDate
	 */
	public void setBidDate(Date bidDate) {
		this.bidDate = bidDate;
	}
	/**
	 * 获取payDate
	 * @return
	 */
	public Date getPayDate() {
		return payDate;
	}
	/**
	 * 设置payDate
	 * @param payDate
	 */
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	/**
	 * 获取onmarketDate
	 * @return
	 */
	public Date getOnmarketDate() {
		return onmarketDate;
	}
	/**
	 * 设置onmarketDate
	 * @param onmarketDate
	 */
	public void setOnmarketDate(Date onmarketDate) {
		this.onmarketDate = onmarketDate;
	}
	/**
	 * 获取mainSaler
	 * @return
	 */
	public String getMainSaler() {
		return mainSaler;
	}
	/**
	 * 设置mainSaler
	 * @param mainSaler
	 */
	public void setMainSaler(String mainSaler) {
		this.mainSaler = mainSaler;
	}
	/**
	 * 获取tradeMarket
	 * @return
	 */
	public String getTradeMarket() {
		return tradeMarket;
	}
	/**
	 * 设置tradeMarket
	 * @param tradeMarket
	 */
	public void setTradeMarket(String tradeMarket) {
		this.tradeMarket = tradeMarket;
	}
	/**
	 * 获取registOrg
	 * @return
	 */
	public String getRegistOrg() {
		return registOrg;
	}
	/**
	 * 设置registOrg
	 * @param registOrg
	 */
	public void setRegistOrg(String registOrg) {
		this.registOrg = registOrg;
	}
	/**
	 * 将投标日日期型转成字符串型
	 * @return
	 */
	 public String getBidDateStr(){
	        return DateUtil.format(this.getBidDate());
	    }
	 /**
	  * 将日期型转成字符串型
	  * @return
	  */
	 public String getPayDateStr() {
		 return DateUtil.format(this.getPayDate());
	 }
	 /**
	  * 将日期型转成字符串型
	  * @return
	  */
	 public String getOnmarketDateStr() {
		 return DateUtil.format(this.getOnmarketDate());
	 }
	 /**
	  * 获取交易场所名称
	  * @return
	  */
	 public String getTradeMarketName() {
	        return SysDataUtil.getNameFromDataDict(DictCode.交易场所, this.getTradeMarket());
	    }
	 /**
	     * 登记托管机构名称
	     * <功能详细描述>
	     * @return
	     * @see
	     * @since 1.0
	     */
	    public String getRegistOrgName(){
	        return SysDataUtil.getNameFromDataDict(DictCode.登记托管机构, this.getRegistOrg());
	    }

}
