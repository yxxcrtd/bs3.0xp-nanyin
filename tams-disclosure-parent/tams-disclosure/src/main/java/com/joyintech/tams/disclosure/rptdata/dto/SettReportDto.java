/**
 * 
 */
package com.joyintech.tams.disclosure.rptdata.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * 清算报表实体类
 * @author chenxc
 * @version 1.0 2022年07月20日
 * @since 1.0
 */
public class SettReportDto extends ReportDto {
	
	private String prodName;  // 产品名称
	private String prodRe;  // 登记编码
	private Date eDate;  //成立日期
	private String trustName; //产品托管人名称
	private Date mDate;  //到期日
	private Map<String,BigDecimal> mapBalance;  //科目余额
	private BigDecimal sumAssetAmt;//资产合计
	private BigDecimal deposit;  //银行存款
	private BigDecimal guarantyAmt;  //保证金
	private BigDecimal intAmt;  //应计利息
	private BigDecimal sumDebtAmt; //负债合计
	private BigDecimal mgrFee;  //管理费
	private BigDecimal  floatFee;  //应付管理人业绩报酬
	private BigDecimal trustFee;  //托管费
	private BigDecimal saleFee;  //销售费
	private BigDecimal redemAmt;  //赎回紧额
	private BigDecimal taxFee;  //税费
	private BigDecimal profit;  //应付利润
	private BigDecimal otherPayAmt;  //其他应付紧额
	private BigDecimal assetValue;  //资产价值，固定值0
	
	private Date signDate; //报告签字日期
	
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
	public Date geteDate() {
		return eDate;
	}
	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}
	public String getTrustName() {
		return trustName;
	}
	public void setTrustName(String trustName) {
		this.trustName = trustName;
	}
	public Date getmDate() {
		return mDate;
	}
	public void setmDate(Date mDate) {
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
	public BigDecimal getDeposit() {
		return deposit;
	}
	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}
	public BigDecimal getGuarantyAmt() {
		return guarantyAmt;
	}
	public void setGuarantyAmt(BigDecimal guarantyAmt) {
		this.guarantyAmt = guarantyAmt;
	}
	public BigDecimal getIntAmt() {
		return intAmt;
	}
	public void setIntAmt(BigDecimal intAmt) {
		this.intAmt = intAmt;
	}
	public BigDecimal getSumDebtAmt() {
		return sumDebtAmt;
	}
	public void setSumDebtAmt(BigDecimal sumDebtAmt) {
		this.sumDebtAmt = sumDebtAmt;
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
	public Date getSignDate() {
		return signDate;
	}
	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}
	
}
