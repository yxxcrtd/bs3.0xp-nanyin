package com.jixu.data.pojo;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/** 
 * @ClassName 		PriceCenter
 * @author  		Pan Wei 
 * @Date 			2017年11月29日 下午12:34:24 	
 * @Description		[]  
 * @return  
 */
@Entity 
@Table(name="PriceCenter") 
public class PriceCenter implements Serializable{

	private static final long serialVersionUID = -5087095424826955133L;
	
	private int id;
	
	private int secMasterID;
	
	/**
	 * 证券代码
	 */
	private String securityID;
	
	/**
	 * 价格日期
	 */
	private Date priceDate;
	
	/**
	 * 动态利率(收益率)
	 */
	private double yield;
	
	
	/**
	 * 定价基准(市场,成本)
	 */
	private int priceTo;
	
	/**
	 * 开盘价
	 */
	private double openPrice;
	
	/**
	 * 最高价
	 */
	private double highPrice;
	
	/**
	 * 最低价
	 */
	private double lowPrice;
	
	/**
	 * 收盘价
	 */
	private double closePrice;
	
	/**
	 * 成交金额
	 */
	private double turnOver;
	
	/**
	 * 成交量
	 */
	private double volume;
	
	/**
	 * 平均价
	 */
	private double vWap;
	
	/**
	 * 最高价涨幅,以昨日收盘价为基准
	 */
	private double highPercent;
	
	/**
	 * 最低价涨幅,以昨日收盘价为基准
	 */
	private double lowPercent;
	
	/**
	 * 开盘价涨幅,以昨日收盘价为基准
	 */
	private double openPercent;
	
	/**
	 * 收盘价涨幅,以昨日收盘价为基准
	 */
	private double closePercent;
	
	/**
	 * 最高价离 max(open,close)的振幅,,以昨日收盘价为基准
	 */
	private double highOpenClosePercent;
	
	/**
	 * 最低价离min(open,close)的振幅,以昨日收盘价为基准
	 */
	private double lowOpenClosePercent;
	
	/**
	 * abs(open-close)的振幅,以昨日收盘价为基准
	 */
	private double openClosePercent;
	
	/**
	 * 复权价
	 */
	private double resRightPrice;
	
	/**
	 *	数据源 
	 */
	private int sourceID;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="secMasterID")
	public int getSecMasterID() {
		return secMasterID;
	}

	public void setSecMasterID(int secMasterID) {
		this.secMasterID = secMasterID;
	}

	@Column(name="priceDate")
	public Date getPriceDate() {
		return priceDate;
	}

	public void setPriceDate(Date priceDate) {
		this.priceDate = priceDate;
	}

	@Column(name="yield")
	public double getYield() {
		return yield;
	}

	public void setYield(double yield) {
		this.yield = yield;
	}

	@Column(name="priceTo")
	public int getPriceTo() {
		return priceTo;
	}

	public void setPriceTo(int priceTo) {
		this.priceTo = priceTo;
	}

	@Column(name="openPrice")
	public double getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(double openPrice) {
		this.openPrice = openPrice;
	}

	@Column(name="highPrice")
	public double getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(double highPrice) {
		this.highPrice = highPrice;
	}

	@Column(name="lowPrice")
	public double getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(double lowPrice) {
		this.lowPrice = lowPrice;
	}

	@Column(name="closePrice")
	public double getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(double closePrice) {
		this.closePrice = closePrice;
	}

	@Column(name="turnOver")
	public double getTurnOver() {
		return turnOver;
	}

	public void setTurnOver(double turnOver) {
		this.turnOver = turnOver;
	}

	@Column(name="volume")
	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	@Column(name="vWap")
	public double getvWap() {
		return vWap;
	}

	public void setvWap(double vWap) {
		this.vWap = vWap;
	}

	@Column(name="highPercent")
	public double getHighPercent() {
		return highPercent;
	}

	public void setHighPercent(double highPercent) {
		this.highPercent = highPercent;
	}

	@Column(name="lowPercent")
	public double getLowPercent() {
		return lowPercent;
	}

	public void setLowPercent(double lowPercent) {
		this.lowPercent = lowPercent;
	}

	@Column(name="openPercent")
	public double getOpenPercent() {
		return openPercent;
	}

	public void setOpenPercent(double openPercent) {
		this.openPercent = openPercent;
	}

	@Column(name="closePercent")
	public double getClosePercent() {
		return closePercent;
	}

	public void setClosePercent(double closePercent) {
		this.closePercent = closePercent;
	}

	@Column(name="highOpenClosePercent")
	public double getHighOpenClosePercent() {
		return highOpenClosePercent;
	}

	public void setHighOpenClosePercent(double highOpenClosePercent) {
		this.highOpenClosePercent = highOpenClosePercent;
	}

	@Column(name="lowOpenClosePercent")
	public double getLowOpenClosePercent() {
		return lowOpenClosePercent;
	}

	public void setLowOpenClosePercent(double lowOpenClosePercent) {
		this.lowOpenClosePercent = lowOpenClosePercent;
	}

	@Column(name="openClosePercent")
	public double getOpenClosePercent() {
		return openClosePercent;
	}

	public void setOpenClosePercent(double openClosePercent) {
		this.openClosePercent = openClosePercent;
	}

	@Column(name="sourceID")
	public int getSourceID() {
		return sourceID;
	}

	public void setSourceID(int sourceID) {
		this.sourceID = sourceID;
	}
	
	@Column(name="SecurityID")
	public String getSecurityID() {
		return securityID;
	}

	public void setSecurityID(String securityID) {
		this.securityID = securityID;
	}

	public double getResRightPrice() {
		return resRightPrice;
	}

	public void setResRightPrice(double resRightPrice) {
		this.resRightPrice = resRightPrice;
	}
}
