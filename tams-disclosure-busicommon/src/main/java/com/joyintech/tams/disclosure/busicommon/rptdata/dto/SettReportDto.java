/**
 * 
 */
package com.joyintech.tams.disclosure.busicommon.rptdata.dto;

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
	private String sumAssetAmt;//资产合计
	private String deposit;  //银行存款
	private String guarantyAmt;  //保证金
	private String intAmt;  //应计利息
	private String sumDebtAmt; //负债合计
	private String mgrFee;  //管理费
	private String  floatFee;  //应付管理人业绩报酬
	private String trustFee;  //托管费
	private String saleFee;  //销售费
	private String redemAmt;  //应付赎回款
	private String taxFee;  //税费
	private String profit;  //应付利润
	private String otherPayAmt;  //其他应付紧额
	private String assetValue;  //资产价值，固定值0
	
	private String bonusPlanId;
	
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
	public String getSumAssetAmt() {
		return sumAssetAmt;
	}
	public void setSumAssetAmt(String sumAssetAmt) {
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
	public String getTrustFee() {
		return trustFee;
	}
	public void setTrustFee(String trustFee) {
		this.trustFee = trustFee;
	}
	public String getSaleFee() {
		return saleFee;
	}
	public void setSaleFee(String saleFee) {
		this.saleFee = saleFee;
	}
	public String getRedemAmt() {
		return redemAmt;
	}
	public void setRedemAmt(String redemAmt) {
		this.redemAmt = redemAmt;
	}
	public String getTaxFee() {
		return taxFee;
	}
	public void setTaxFee(String taxFee) {
		this.taxFee = taxFee;
	}
	public String getProfit() {
		return profit;
	}
	public void setProfit(String profit) {
		this.profit = profit;
	}
	public String getOtherPayAmt() {
		return otherPayAmt;
	}
	public void setOtherPayAmt(String otherPayAmt) {
		this.otherPayAmt = otherPayAmt;
	}
	public String getAssetValue() {
		return assetValue;
	}
	public void setAssetValue(String assetValue) {
		this.assetValue = assetValue;
	}
	public String getBonusPlanId() {
		return bonusPlanId;
	}
	public void setBonusPlanId(String bonusPlanId) {
		this.bonusPlanId = bonusPlanId;
	}
	public Date getSignDate() {
		return signDate;
	}
	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}
	
}
