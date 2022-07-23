/**
 * 
 */
package com.joyintech.tams.disclosure.rptdata.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.DisNostdCreditor;
import com.joyintech.tams.common.base.entity.DisProdAsset;
import com.joyintech.tams.common.base.entity.RepAssetsComb;
import com.joyintech.tams.common.base.entity.RepInvestAccount;
import com.joyintech.tams.common.base.entity.RepProdIndex;
import com.joyintech.tams.common.base.entity.RepRelDeal;

/**
 * 阶段性报告实体类
 * @author chenxc
 * @version 1.0 2022年07月20日
 * @since 1.0
 */
public class DurationReportDto extends ReportDto {

	private Integer runDays;  //期限天数
	private String profitType; //运作方式
	
	private BigDecimal capital; //期末产品份额
	private String partner; //合作机构
	
	private int year;           //年
	private int month;       //月
	private int quarter;      //季
	private int halfYear;    //半年
	
	private Date durStartDate;  //报告期开始日
	private Date durEndDate;  //报告期结束日
	private String duration; //报表区间
	
	
	private Map<String,Object> finIndex;  //财务指标：资产净值，份额净值，份合累计净值
	
	private List<Map<String,Object>> listAchieveBasis;   //TODO:业绩基准，实体类还未定义，暂时以map定义
	
	private String prodOperating;  //报告期内产品的投资策略和运作分析，从报告指标表中获取;
	private String prodRisk;   //报告期内产品的流动性风险分析

	private Map<String,Object> prodAssetComb;  //产品资产组合汇总情况
	
	private List<Map<String,Object>> listAssetsDetail;  //报告期末按公允价值占产品资产净值比例大小排序的前十名资产投资明细,REP_ASSETS_COMB
	
	private List<Map<String,Object>>  listNostds;  //报告期末非标准化债权类资产明细；DisNostdCreditor
	
	private List<Map<String,Object>> listAccts; //投资账户信息

	private Map<String,Object> repRelDeal;  //关联交易，一个产品1日就一条数据

	public Integer getRunDays() {
		return runDays;
	}

	public void setRunDays(Integer runDays) {
		this.runDays = runDays;
	}

	public String getProfitType() {
		return profitType;
	}

	public void setProfitType(String profitType) {
		this.profitType = profitType;
	}

	public BigDecimal getCapital() {
		return capital;
	}

	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getQuarter() {
		return quarter;
	}

	public void setQuarter(int quarter) {
		this.quarter = quarter;
	}

	public int getHalfYear() {
		return halfYear;
	}

	public void setHalfYear(int halfYear) {
		this.halfYear = halfYear;
	}

	public Date getDurStartDate() {
		return durStartDate;
	}

	public void setDurStartDate(Date durStartDate) {
		this.durStartDate = durStartDate;
	}

	public Date getDurEndDate() {
		return durEndDate;
	}

	public void setDurEndDate(Date durEndDate) {
		this.durEndDate = durEndDate;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public List<Map<String, Object>> getListAchieveBasis() {
		return listAchieveBasis;
	}

	public void setListAchieveBasis(List<Map<String, Object>> listAchieveBasis) {
		this.listAchieveBasis = listAchieveBasis;
	}

	public String getProdOperating() {
		return prodOperating;
	}

	public void setProdOperating(String prodOperating) {
		this.prodOperating = prodOperating;
	}

	public String getProdRisk() {
		return prodRisk;
	}

	public void setProdRisk(String prodRisk) {
		this.prodRisk = prodRisk;
	}

	public Map<String, Object> getFinIndex() {
		return finIndex;
	}

	public void setFinIndex(Map<String, Object> finIndex) {
		this.finIndex = finIndex;
	}

	public Map<String, Object> getProdAssetComb() {
		return prodAssetComb;
	}

	public void setProdAssetComb(Map<String, Object> prodAssetComb) {
		this.prodAssetComb = prodAssetComb;
	}

	public List<Map<String, Object>> getListAssetsDetail() {
		return listAssetsDetail;
	}

	public void setListAssetsDetail(List<Map<String, Object>> listAssetsDetail) {
		this.listAssetsDetail = listAssetsDetail;
	}

	public List<Map<String, Object>> getListNostds() {
		return listNostds;
	}

	public void setListNostds(List<Map<String, Object>> listNostds) {
		this.listNostds = listNostds;
	}

	public List<Map<String, Object>> getListAccts() {
		return listAccts;
	}

	public void setListAccts(List<Map<String, Object>> listAccts) {
		this.listAccts = listAccts;
	}

	public Map<String, Object> getRepRelDeal() {
		return repRelDeal;
	}

	public void setRepRelDeal(Map<String, Object> repRelDeal) {
		this.repRelDeal = repRelDeal;
	}

}
