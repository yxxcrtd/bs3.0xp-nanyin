/**
 * 
 */
package com.joyintech.tams.disclosure.busicommon.rptdata.dto;

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
	private String prodType; //产品类型：无需区分母子产品 01 净值型/04 现金管理类
	private String profitType; //运作方式
	private String issuingWay;  //发行方式：01开放，02封闭
	
	private BigDecimal capital; //期末产品份额
	private BigDecimal leverage; //杠杆率
	private String partner; //合作机构
	
	private int year;           //年
	private int month;       //月
	private int quarter;      //季
	private int halfYear;    //半年
	
	private Date durStartDate;  //报告期开始日
	private Date durEndDate;  //报告期结束日
	private String duration; //报表区间
	
	
	private List<Map<String,Object>> listFinIndex;  //财务指标：资产净值，份额净值，份合累计净值
	
	private List<Map<String,Object>> listAchieveBasis;   //TODO:业绩基准，实体类还未定义，暂时以map定义
	
	private String tzclhyzfx;  //报告期内产品的投资策略和运作分析，从报告指标表中获取;
	private String ldxfxfx;    //报告期内产品的流动性风险分析
	private String tgrbg1;   //报告期内托管人遵规守信情况声明
	private String tgrbg2;   //托管人对报告期内投资运作遵规守信、净值计算、利润分配等情况的说明
	private String tgrbg3;   //托管人对本年度报告中财务信息等内容的真实、准确和完整发表意见
	private String yjbx;       //报告期内产品的业绩表现
	
	private Map<String,Object> prodAssetComb;  //产品资产组合汇总情况
	
	private List<Map<String,Object>> listAssetsDetail;  //报告期末按公允价值占产品资产净值比例大小排序的前十名资产投资明细,REP_ASSETS_COMB

	private List<Map<String,Object>> listInvester; //前10名投资者情况
	private List<Map<String,Object>>  listNostds;  //报告期末非标准化债权类资产明细；DisNostdCreditor
	
	private List<Map<String,Object>> listAccts; //投资账户信息

	private Map<String,Object> repRelDeal;  //关联交易，一个产品1日就一条数据

	public String getIssuingWay() {
		return issuingWay;
	}

	public void setIssuingWay(String issuingWay) {
		this.issuingWay = issuingWay;
	}

	public Integer getRunDays() {
		return runDays;
	}

	public void setRunDays(Integer runDays) {
		this.runDays = runDays;
	}

	public String getProdType() {
		return prodType;
	}

	public void setProdType(String prodType) {
		this.prodType = prodType;
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

	public BigDecimal getLeverage() {
		return leverage;
	}

	public void setLeverage(BigDecimal leverage) {
		this.leverage = leverage;
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

	public String getTzclhyzfx() {
		return tzclhyzfx;
	}

	public void setTzclhyzfx(String tzclhyzfx) {
		this.tzclhyzfx = tzclhyzfx;
	}

	public String getLdxfxfx() {
		return ldxfxfx;
	}

	public void setLdxfxfx(String ldxfxfx) {
		this.ldxfxfx = ldxfxfx;
	}

	public String getTgrbg1() {
		return tgrbg1;
	}

	public void setTgrbg1(String tgrbg1) {
		this.tgrbg1 = tgrbg1;
	}

	public String getTgrbg2() {
		return tgrbg2;
	}

	public void setTgrbg2(String tgrbg2) {
		this.tgrbg2 = tgrbg2;
	}

	public String getTgrbg3() {
		return tgrbg3;
	}

	public void setTgrbg3(String tgrbg3) {
		this.tgrbg3 = tgrbg3;
	}

	public String getYjbx() {
		return yjbx;
	}

	public void setYjbx(String yjbx) {
		this.yjbx = yjbx;
	}

	public List<Map<String, Object>> getListFinIndex() {
		return listFinIndex;
	}

	public void setListFinIndex(List<Map<String, Object>> listFinIndex) {
		this.listFinIndex = listFinIndex;
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

	public List<Map<String, Object>> getListInvester() {
		return listInvester;
	}

	public void setListInvester(List<Map<String, Object>> listInvester) {
		this.listInvester = listInvester;
	}
}
