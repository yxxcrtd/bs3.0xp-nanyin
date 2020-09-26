/*
 * 文件名：ProductTradeDealQuery002Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年8月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.trademanage.producttradedeal.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * TA交易-明细查询 DTO
 * @author zhangwei
 * @version 1.0 2018年8月7日
 * @see ProductTradeDealQuery002Dto
 * @since 1.0
 */@SuppressWarnings("serial")

public class ProductTradeDealQuery002Dto extends BaseEntity{
     
	 /** 交易编号 **/
	 private String tradeId;
	 
     /**产品代码**/
     private String finprodId;
     
     /**产品简称**/
     private String finprodAbbr;
     

     /**交易类型Code - 交易方向**/
     private String psCode;
     
     /**交易类型 - 交易方向**/
     private String ps;
     
     /**币种Code**/
     private String ccyCode;
     
     /**币种**/
     private String ccy;
     
     /**交易份额**/
     @JsonSerialize(using = BigDecimalSerializer.class)
     private BigDecimal shareAmt;
     
     /**交易金额**/
     @JsonSerialize(using = BigDecimalSerializer.class)
     private BigDecimal tradeAmt;
     
     /**交易本金-本金金额**/
     @JsonSerialize(using = BigDecimalSerializer.class)
     private BigDecimal prinAmt;
     
     /**红利利息金额-利息总额**/
     @JsonSerialize(using = BigDecimalSerializer.class)
     private BigDecimal intAmt;
     
     /**本金交易日**/
     private String tradeDate;
     
     /**红利利息支付日**/
     private String settleDate;
     
     

    
    public String getFinprodId() {
        return finprodId;
    }

    
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    
    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    
    
    





	public String getCcy() {
//        return SysDataUtil.getNameFromDataDict(DictCode.币种, ccy);
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.币种, ccy))?
            SysDataUtil.getNameFromDataDict(DictCode.币种, ccy):ccy;
    }

    
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }
    
    

    
    public String getCcyCode() {
		return ccyCode;
	}


	public void setCcyCode(String ccyCode) {
		this.ccyCode = ccyCode;
	}


	
     
    
    
    public String getPsCode() {
		return psCode;
	}


	public void setPsCode(String psCode) {
		this.psCode = psCode;
	}


	public String getPs() {
//		return SysDataUtil.getNameFromDataDict(DictCode.交易方向, ps);
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.交易方向, ps))?
            SysDataUtil.getNameFromDataDict(DictCode.交易方向, ps):ps;
	}


	public void setPs(String ps) {
		this.ps = ps;
	}


	public String getSettleDate() {
		return settleDate;
	}


	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}


	public String getTradeId() {
		return tradeId;
	}


	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	

	public BigDecimal getShareAmt() {
		if(shareAmt == null){
			return BigDecimal.ZERO;
		}
		return shareAmt;
	}


	public void setShareAmt(BigDecimal shareAmt) {
		this.shareAmt = shareAmt;
	}


	public BigDecimal getTradeAmt() {
		if(tradeAmt == null){
	     	return BigDecimal.ZERO;
		}
		return tradeAmt;
	}


	public void setTradeAmt(BigDecimal tradeAmt) {
		this.tradeAmt = tradeAmt;
	}


	public BigDecimal getPrinAmt() {
		if(prinAmt == null){
			return BigDecimal.ZERO;
		}
		return prinAmt;
	}


	public void setPrinAmt(BigDecimal prinAmt) {
		this.prinAmt = prinAmt;
	}


	public BigDecimal getIntAmt() {
		if(intAmt == null){
			return BigDecimal.ZERO;
		}
		return intAmt;
	}


	public void setIntAmt(BigDecimal intAmt) {
		this.intAmt = intAmt;
	}


	public String getTradeDate() {
		return tradeDate;
	}


	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}


	
     

}
