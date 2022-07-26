/**
 * 
 */
package com.joyintech.tams.disclosure.rptdata.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 分红报告实体类
 * @author chenxc
 * @version 1.0 2022年07月20日
 * @since 1.0
 */
public class BonusReportDto  extends ReportDto {
	
	private String issuingWay;        //开放模式
	private String bonusMode;     //分红模式
	private BigDecimal assetsValue;        //产品资产净值
	private BigDecimal capital;        //产品期末余额（份额））
	private BigDecimal unitValue;        //产品单位净值
	private Date lastSettDate;        //上一收益分配基准日
	private Date settDate;        //本期收益分配基准日
	private Integer runDays;          //运行天数
	private BigDecimal distributableProfit;         //可分配利润
	private String achievementBasis;        //业绩比较基准
	private BigDecimal achievementBasisProfit;  //业绩比较基准收益；
	private BigDecimal achievementBasisProp;        //业绩比较基准收益比例
	private BigDecimal excessProfit;        //超出部分
	private BigDecimal excessAchievementProp;        //超额业绩报酬收取比例
	private BigDecimal floatFee;        //业绩报酬
	private BigDecimal distributedProfit;        //应分配分红金额
	private BigDecimal unitProfit;        //单位分红
	private BigDecimal yearYield;        //年化收益率

	public String getIssuingWay() {
		return issuingWay;
	}
	public void setIssuingWay(String issuingWay) {
		this.issuingWay = issuingWay;
	}
	public String getBonusMode() {
		return bonusMode;
	}
	public void setBonusMode(String bonusMode) {
		this.bonusMode = bonusMode;
	}
	public BigDecimal getAssetsValue() {
		return assetsValue;
	}
	public void setAssetsValue(BigDecimal assetsValue) {
		this.assetsValue = assetsValue;
	}
	public BigDecimal getCapital() {
		return capital;
	}
	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}
	public BigDecimal getUnitValue() {
		return unitValue;
	}
	public void setUnitValue(BigDecimal unitValue) {
		this.unitValue = unitValue;
	}
	public Date getLastSettDate() {
		return lastSettDate;
	}
	public void setLastSettDate(Date lastSettDate) {
		this.lastSettDate = lastSettDate;
	}
	public Date getSettDate() {
		return settDate;
	}
	public void setSettDate(Date bonusDate) {
		this.settDate = bonusDate;
	}
	public Integer getRunDays() {
		return runDays;
	}
	public void setRunDays(Integer runDays) {
		this.runDays = runDays;
	}
	public BigDecimal getDistributableProfit() {
		return distributableProfit;
	}
	public void setDistributableProfit(BigDecimal distributableProfit) {
		this.distributableProfit = distributableProfit;
	}
	public String getAchievementBasis() {
		return achievementBasis;
	}
	public void setAchievementBasis(String achievementBasis) {
		this.achievementBasis = achievementBasis;
	}
	public BigDecimal getAchievementBasisProfit() {
		return achievementBasisProfit;
	}
	public void setAchievementBasisProfit(BigDecimal achievementBasisProfit) {
		this.achievementBasisProfit = achievementBasisProfit;
	}
	public BigDecimal getAchievementBasisProp() {
		return achievementBasisProp;
	}
	public void setAchievementBasisProp(BigDecimal achievementBasisProp) {
		this.achievementBasisProp = achievementBasisProp;
	}
	public BigDecimal getExcessProfit() {
		return excessProfit;
	}
	public void setExcessProfit(BigDecimal excessProfit) {
		this.excessProfit = excessProfit;
	}
	public BigDecimal getFloatFee() {
		return floatFee;
	}
	public void setFloatFee(BigDecimal floatFee) {
		this.floatFee = floatFee;
	}
	public BigDecimal getExcessAchievementProp() {
		return excessAchievementProp;
	}
	public void setExcessAchievementProp(BigDecimal excessAchievementProp) {
		this.excessAchievementProp = excessAchievementProp;
	}
	public BigDecimal getDistributedProfit() {
		return distributedProfit;
	}
	public void setDistributedProfit(BigDecimal distributedProfit) {
		this.distributedProfit = distributedProfit;
	}
	public BigDecimal getUnitProfit() {
		return unitProfit;
	}
	public void setUnitProfit(BigDecimal unitProfit) {
		this.unitProfit = unitProfit;
	}
	public BigDecimal getYearYield() {
		return yearYield;
	}
	public void setYearYield(BigDecimal yearYield) {
		this.yearYield = yearYield;
	}
	
}
