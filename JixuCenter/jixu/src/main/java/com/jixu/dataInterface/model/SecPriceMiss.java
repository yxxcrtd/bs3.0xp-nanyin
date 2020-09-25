package com.jixu.dataInterface.model;

import java.sql.Date;

import com.jixu.common.util.FieldMeta;

/** 
 * @ClassName 		VouchersBean
 * @author  		Pan Wei 
 * @Date 			2018年6月1日 下午3:55:14 	
 * @Description		[]  
 */

public class SecPriceMiss {
	@FieldMeta(desc="证券系统编号")
	private String cusip;
	
	@FieldMeta(desc="证券代码")
	private String securityID;
	
	@FieldMeta(desc="证券名称")
	private String securityName;
	
	@FieldMeta(desc="日期")
	private Date asOfDate;
	
	@FieldMeta(desc="上一日持有数量")
	private double amount;
	
	@FieldMeta(desc="上一日价格")
	private double lastPrice;
	
	@FieldMeta(desc="资产类型")
	private String assetType;

	public String getCusip() {
		return cusip;
	}

	public void setCusip(String cusip) {
		this.cusip = cusip;
	}

	public String getSecurityID() {
		return securityID;
	}

	public void setSecurityID(String securityID) {
		this.securityID = securityID;
	}

	public String getSecurityName() {
		return securityName;
	}

	public void setSecurityName(String securityName) {
		this.securityName = securityName;
	}

	public Date getAsOfDate() {
		return asOfDate;
	}

	public void setAsOfDate(Date asOfDate) {
		this.asOfDate = asOfDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(double lastPrice) {
		this.lastPrice = lastPrice;
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
}
