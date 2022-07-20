/**
 * 
 */
package com.joyintech.tams.disclosure.rptdata.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * @author Administrator
 *
 */
public class MatureReportDto extends ReportDto {

	private Integer runDays;  //期限天数
	private BigDecimal sumValueAmt;//累计净值
	private BigDecimal refYearYield;  //参考年化收益率
	private BigDecimal custProfit;   //客户收益
	
	private BigDecimal trustFee;  //托管费
	private BigDecimal mgrFee;  //固定管理费
	private BigDecimal  floatFee;  //超额管理费
	private BigDecimal saleFee;  //销售费
	private BigDecimal taxFee;  //申购费
	private BigDecimal redemAmt;  //赎回费
	public Integer getRunDays() {
		return runDays;
	}
	public void setRunDays(Integer runDays) {
		this.runDays = runDays;
	}
	public BigDecimal getSumValueAmt() {
		return sumValueAmt;
	}
	public void setSumValueAmt(BigDecimal sumValueAmt) {
		this.sumValueAmt = sumValueAmt;
	}
	public BigDecimal getRefYearYield() {
		return refYearYield;
	}
	public void setRefYearYield(BigDecimal refYearYield) {
		this.refYearYield = refYearYield;
	}
	public BigDecimal getCustProfit() {
		return custProfit;
	}
	public void setCustProfit(BigDecimal custProfit) {
		this.custProfit = custProfit;
	}
	public BigDecimal getTrustFee() {
		return trustFee;
	}
	public void setTrustFee(BigDecimal trustFee) {
		this.trustFee = trustFee;
	}
	public BigDecimal getMgrFee() {
		return mgrFee;
	}
	public void setMgrFee(BigDecimal mgrFee) {
		this.mgrFee = mgrFee;
	}
	public BigDecimal getFloatFee() {
		return floatFee;
	}
	public void setFloatFee(BigDecimal floatFee) {
		this.floatFee = floatFee;
	}
	public BigDecimal getSaleFee() {
		return saleFee;
	}
	public void setSaleFee(BigDecimal saleFee) {
		this.saleFee = saleFee;
	}
	public BigDecimal getTaxFee() {
		return taxFee;
	}
	public void setTaxFee(BigDecimal taxFee) {
		this.taxFee = taxFee;
	}
	public BigDecimal getRedemAmt() {
		return redemAmt;
	}
	public void setRedemAmt(BigDecimal redemAmt) {
		this.redemAmt = redemAmt;
	}

}
