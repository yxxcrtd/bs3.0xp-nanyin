/**
 * 
 */
package com.joyintech.tams.disclosure.rptdata.dto;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Administrator
 *
 */
public class SettReportDto {
	
	private String prodName;  // 产品名称
	private String prodRe;  // 登记编码
	private String eDate;  //成立日期
	private String trustName; //产品托管人名称
	private String mDate;  //到期日
	private Map<String,BigDecimal> mapBalance;  //科目余额
	private BigDecimal sumAssetAmt;//资产合计
	private String deposit;  //银行存款
	private String guarantyAmt;  //保证金
	private String intAmt;  //应计利息
	private String sumDebtAmt; //负债合计
	private String mgrFee;  //管理费
	private String  floatFee;  //应付管理人业绩报酬
	private BigDecimal trustFee;  //托管费
	private BigDecimal saleFee;  //销售费
	private BigDecimal redemAmt;  //赎回紧额
	private BigDecimal taxFee;  //税费
	private BigDecimal profit;  //应付利润
	private BigDecimal otherPayAmt;  //其他应付紧额
	private BigDecimal assetValue;  //资产价值，固定值0
	
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getProdRe() {
		return prodRe;
	}
	public void setProdRe(String prodRe) {
		this.prodRe = prodRe;
	}
	public String geteDate() {
		return eDate;
	}
	public void seteDate(String eDate) {
		this.eDate = eDate;
	}
	public String getTrustName() {
		return trustName;
	}
	public void setTrustName(String trustName) {
		this.trustName = trustName;
	}
	public String getmDate() {
		return mDate;
	}
	public void setmDate(String mDate) {
		this.mDate = mDate;
	}
	public Map<String, BigDecimal> getMapBalance() {
		return mapBalance;
	}
	public void setMapBalance(Map<String, BigDecimal> mapBalance) {
		this.mapBalance = mapBalance;
	}
	public BigDecimal getSumAssetAmt() {
		return sumAssetAmt;
	}
	public void setSumAssetAmt(BigDecimal sumAssetAmt) {
		this.sumAssetAmt = sumAssetAmt;
	}
	public String getDeposit() {
		return deposit;
	}
	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}
	public String getGuarantyAmt() {
		return guarantyAmt;
	}
	public void setGuarantyAmt(String guarantyAmt) {
		this.guarantyAmt = guarantyAmt;
	}
	public String getIntAmt() {
		return intAmt;
	}
	public void setIntAmt(String intAmt) {
		this.intAmt = intAmt;
	}
	public String getSumDebtAmt() {
		return sumDebtAmt;
	}
	public void setSumDebtAmt(String sumDebtAmt) {
		this.sumDebtAmt = sumDebtAmt;
	}
	public String getMgrFee() {
		return mgrFee;
	}
	public void setMgrFee(String mgrFee) {
		this.mgrFee = mgrFee;
	}
	public String getFloatFee() {
		return floatFee;
	}
	public void setFloatFee(String floatFee) {
		this.floatFee = floatFee;
	}
	public BigDecimal getTrustFee() {
		return trustFee;
	}
	public void setTrustFee(BigDecimal trustFee) {
		this.trustFee = trustFee;
	}
	public BigDecimal getSaleFee() {
		return saleFee;
	}
	public void setSaleFee(BigDecimal saleFee) {
		this.saleFee = saleFee;
	}
	public BigDecimal getRedemAmt() {
		return redemAmt;
	}
	public void setRedemAmt(BigDecimal redemAmt) {
		this.redemAmt = redemAmt;
	}
	public BigDecimal getTaxFee() {
		return taxFee;
	}
	public void setTaxFee(BigDecimal taxFee) {
		this.taxFee = taxFee;
	}
	public BigDecimal getProfit() {
		return profit;
	}
	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}
	public BigDecimal getOtherPayAmt() {
		return otherPayAmt;
	}
	public void setOtherPayAmt(BigDecimal otherPayAmt) {
		this.otherPayAmt = otherPayAmt;
	}
	public BigDecimal getAssetValue() {
		return assetValue;
	}
	public void setAssetValue(BigDecimal assetValue) {
		this.assetValue = assetValue;
	}
	
}
