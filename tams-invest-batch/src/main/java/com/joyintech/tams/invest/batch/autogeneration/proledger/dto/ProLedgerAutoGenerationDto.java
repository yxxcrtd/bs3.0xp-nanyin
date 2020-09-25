package com.joyintech.tams.invest.batch.autogeneration.proledger.dto;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class ProLedgerAutoGenerationDto extends BaseEntity {
    private String finprodId;  //    金融产品代码
    private Date cdate;       //    日期
    private String bonusMode;  //    分红方式
    private String intpayPeriod;  //    付息频率
    private BigDecimal rate;  //    预期收益率/业绩比较基准
    private String rateStr;  //    预期收益率/业绩比较基准
    private String floatFeeProportion;  //    浮动管理费分成比例（%）
    private BigDecimal fixFeeProportion;  //    固定管理费（%）
    private BigDecimal trustFeeProportion;  //    托管费率（%）
    private BigDecimal actYield;  //    客户实际收益率（%）
    private BigDecimal firstPrinAmt;  //    首次认购总额（元）
    private BigDecimal shareAmt;  //    存续份额（元）
    private BigDecimal custProfit;  //    兑付客户收益（元）
    private BigDecimal trustFee;  //    托管费（元）
    private BigDecimal saleFee;  //    销售手续费（元）
    private BigDecimal fixFee;  //    固定管理费（元）
    private BigDecimal proxyFee;  //    代销费（元）
    private BigDecimal addTax;  //    增值附加税费（元）
    private BigDecimal floatFee;  //    浮动管理费（元）
    private BigDecimal otherFee;  //    其他费用（元）
    private BigDecimal actMidIncome;  //    实现中间业务收入（元）
    private BigDecimal monthIssuePrinamt;  //    月发行产品金额（元）
    private BigDecimal monthDuePrinamt;  //    月到期产品金额（元）
    private BigDecimal monthIssueShareamt;  //    月发行产品份额
    private BigDecimal monthDueShareamt;  //    月到期产品份额
    private String profitType;  //    收益类型
    private String dealMode;  //    处理模式
	/**
	 * @return the finprodId
	 */
	public String getFinprodId() {
		return finprodId;
	}
	/**
	 * @param finprodId the finprodId to set
	 */
	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}
	/**
	 * @return the cdate
	 */
	public Date getCdate() {
		return cdate;
	}
	/**
	 * @param cdate the cdate to set
	 */
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	/**
	 * @return the bonusMode
	 */
	public String getBonusMode() {
		return bonusMode;
	}
	/**
	 * @param bonusMode the bonusMode to set
	 */
	public void setBonusMode(String bonusMode) {
		this.bonusMode = bonusMode;
	}
	/**
	 * @return the intpayPeriod
	 */
	public String getIntpayPeriod() {
		return intpayPeriod;
	}
	/**
	 * @param intpayPeriod the intpayPeriod to set
	 */
	public void setIntpayPeriod(String intpayPeriod) {
		this.intpayPeriod = intpayPeriod;
	}
	/**
	 * @return the floatFeeProportion
	 */
	public String getFloatFeeProportion() {
		return floatFeeProportion;
	}
	/**
	 * @param floatFeeProportion the floatFeeProportion to set
	 */
	public void setFloatFeeProportion(String floatFeeProportion) {
		this.floatFeeProportion = floatFeeProportion;
	}
	/**
	 * @return the fixFeeProportion
	 */
	public BigDecimal getFixFeeProportion() {
		return fixFeeProportion;
	}
	/**
	 * @param fixFeeProportion the fixFeeProportion to set
	 */
	public void setFixFeeProportion(BigDecimal fixFeeProportion) {
		this.fixFeeProportion = fixFeeProportion;
	}
	/**
	 * @return the trustFeeProportion
	 */
	public BigDecimal getTrustFeeProportion() {
		return trustFeeProportion;
	}
	/**
	 * @param trustFeeProportion the trustFeeProportion to set
	 */
	public void setTrustFeeProportion(BigDecimal trustFeeProportion) {
		this.trustFeeProportion = trustFeeProportion;
	}
	/**
	 * @return the actYield
	 */
	public BigDecimal getActYield() {
		return actYield;
	}
	/**
	 * @param actYield the actYield to set
	 */
	public void setActYield(BigDecimal actYield) {
		this.actYield = actYield;
	}
	/**
	 * @return the firstPrinAmt
	 */
	public BigDecimal getFirstPrinAmt() {
		return firstPrinAmt;
	}
	/**
	 * @param firstPrinAmt the firstPrinAmt to set
	 */
	public void setFirstPrinAmt(BigDecimal firstPrinAmt) {
		this.firstPrinAmt = firstPrinAmt;
	}
	/**
	 * @return the shareAmt
	 */
	public BigDecimal getShareAmt() {
		return shareAmt;
	}
	/**
	 * @param shareAmt the shareAmt to set
	 */
	public void setShareAmt(BigDecimal shareAmt) {
		this.shareAmt = shareAmt;
	}
	/**
	 * @return the custProfit
	 */
	public BigDecimal getCustProfit() {
		return custProfit;
	}
	/**
	 * @param custProfit the custProfit to set
	 */
	public void setCustProfit(BigDecimal custProfit) {
		this.custProfit = custProfit;
	}
	/**
	 * @return the trustFee
	 */
	public BigDecimal getTrustFee() {
		return trustFee;
	}
	/**
	 * @param trustFee the trustFee to set
	 */
	public void setTrustFee(BigDecimal trustFee) {
		this.trustFee = trustFee;
	}
	/**
	 * @return the saleFee
	 */
	public BigDecimal getSaleFee() {
		return saleFee;
	}
	/**
	 * @param saleFee the saleFee to set
	 */
	public void setSaleFee(BigDecimal saleFee) {
		this.saleFee = saleFee;
	}
	/**
	 * @return the fixFee
	 */
	public BigDecimal getFixFee() {
		return fixFee;
	}
	/**
	 * @param fixFee the fixFee to set
	 */
	public void setFixFee(BigDecimal fixFee) {
		this.fixFee = fixFee;
	}
	/**
	 * @return the proxyFee
	 */
	public BigDecimal getProxyFee() {
		return proxyFee;
	}
	/**
	 * @param proxyFee the proxyFee to set
	 */
	public void setProxyFee(BigDecimal proxyFee) {
		this.proxyFee = proxyFee;
	}
	/**
	 * @return the addTax
	 */
	public BigDecimal getAddTax() {
		return addTax;
	}
	/**
	 * @param addTax the addTax to set
	 */
	public void setAddTax(BigDecimal addTax) {
		this.addTax = addTax;
	}
	/**
	 * @return the floatFee
	 */
	public BigDecimal getFloatFee() {
		return floatFee;
	}
	/**
	 * @param floatFee the floatFee to set
	 */
	public void setFloatFee(BigDecimal floatFee) {
		this.floatFee = floatFee;
	}
	/**
	 * @return the otherFee
	 */
	public BigDecimal getOtherFee() {
		return otherFee;
	}
	/**
	 * @param otherFee the otherFee to set
	 */
	public void setOtherFee(BigDecimal otherFee) {
		this.otherFee = otherFee;
	}
	/**
	 * @return the actMidIncome
	 */
	public BigDecimal getActMidIncome() {
		return actMidIncome;
	}
	/**
	 * @param actMidIncome the actMidIncome to set
	 */
	public void setActMidIncome(BigDecimal actMidIncome) {
		this.actMidIncome = actMidIncome;
	}
	/**
	 * @return the monthIssuePrinamt
	 */
	public BigDecimal getMonthIssuePrinamt() {
		return monthIssuePrinamt;
	}
	/**
	 * @param monthIssuePrinamt the monthIssuePrinamt to set
	 */
	public void setMonthIssuePrinamt(BigDecimal monthIssuePrinamt) {
		this.monthIssuePrinamt = monthIssuePrinamt;
	}
	/**
	 * @return the monthDuePrinamt
	 */
	public BigDecimal getMonthDuePrinamt() {
		return monthDuePrinamt;
	}
	/**
	 * @param monthDuePrinamt the monthDuePrinamt to set
	 */
	public void setMonthDuePrinamt(BigDecimal monthDuePrinamt) {
		this.monthDuePrinamt = monthDuePrinamt;
	}
	
	/**
	 * @return the profitType
	 */
	public String getProfitType() {
		return profitType;
	}
	/**
	 * @param profitType the profitType to set
	 */
	public void setProfitType(String profitType) {
		this.profitType = profitType;
	}
	/**
	 * @return the dealMode
	 */
	public String getDealMode() {
		return dealMode;
	}
	/**
	 * @param dealMode the dealMode to set
	 */
	public void setDealMode(String dealMode) {
		this.dealMode = dealMode;
	}
	/**
	 * @return the monthIssueShareamt
	 */
	public BigDecimal getMonthIssueShareamt() {
		return monthIssueShareamt;
	}
	/**
	 * @param monthIssueShareamt the monthIssueShareamt to set
	 */
	public void setMonthIssueShareamt(BigDecimal monthIssueShareamt) {
		this.monthIssueShareamt = monthIssueShareamt;
	}
	/**
	 * @return the monthDueShareamt
	 */
	public BigDecimal getMonthDueShareamt() {
		return monthDueShareamt;
	}
	/**
	 * @param monthDueShareamt the monthDueShareamt to set
	 */
	public void setMonthDueShareamt(BigDecimal monthDueShareamt) {
		this.monthDueShareamt = monthDueShareamt;
	}
	/**
	 * @return the rate
	 */
	public BigDecimal getRate() {
		return rate;
	}
	/**
	 * @param rate the rate to set
	 */
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	/**
	 * @return the rateStr
	 */
	public String getRateStr() {
		return rateStr;
	}
	/**
	 * @param rateStr the rateStr to set
	 */
	public void setRateStr(String rateStr) {
		this.rateStr = rateStr;
	}

}
