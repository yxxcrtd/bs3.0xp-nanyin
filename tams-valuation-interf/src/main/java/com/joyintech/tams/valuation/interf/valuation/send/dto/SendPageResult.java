/*
 * 文件名：SendPageResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：wangwie1
 * 修改时间：2018年8月10日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */
package com.joyintech.tams.valuation.interf.valuation.send.dto;

import java.math.BigDecimal;

import com.joyintech.framework.base.BaseEntity;
/**
 * 页面内容dto
 * @see SendPageResult
 * @since 1.0
 */
public class SendPageResult extends BaseEntity{
	 /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    //产品代码
    private String prodId;
    //产品名称
    private String prodName;
    //估值日期
    private String cdate;
    //理财份额
    private BigDecimal financialShare ;
    //产品总净值
    private BigDecimal assetValue;
    //单位净值 万份收益
    private BigDecimal netUnitValue;
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getCdate() {
		return cdate;
	}
	public void setCdate(String cdate) {
		this.cdate = cdate;
	}
	public BigDecimal getFinancialShare() {
		return financialShare;
	}
	public void setFinancialShare(BigDecimal financialShare) {
		this.financialShare = financialShare;
	}
	public BigDecimal getAssetValue() {
		return assetValue;
	}
	public void setAssetValue(BigDecimal assetValue) {
		this.assetValue = assetValue;
	}
	public BigDecimal getNetUnitValue() {
		return netUnitValue;
	}
	public void setNetUnitValue(BigDecimal netUnitValue) {
		this.netUnitValue = netUnitValue;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
}
