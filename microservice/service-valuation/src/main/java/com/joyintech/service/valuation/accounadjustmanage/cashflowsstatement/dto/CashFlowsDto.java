/*
 * 文件名：CashFlowsDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：QuJianZhong
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.accounadjustmanage.cashflowsstatement.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 交易现金流信息Dto
 * 
 * @author qujianzhong
 * @version 1.0 2018年10月23日
 * @see CashFlowsDto
 * @since 1.0
 */
public class CashFlowsDto {

    /**
     * 金融产品类型
     */
    private String finProdType;

    /**
     * 金融产品代码
     */
    private String booksetId;
    
    /**
     * 交易方向
     */
    private String ps;
    
    /**
     * 本金
     */
    private BigDecimal prinamt;
    
    /**
     * 利息
     */
    private BigDecimal intamt;
    
    /**
     * 交易金额
     */
    private BigDecimal tradeamt;

    /**
     * 费用金额
     */
    private BigDecimal feeamtpay;


    /**
     * 交易编号
     */
    private String tradeId;

    /**
     * 结算日期
     */
    private Date settleDate;

    /**
     * 交易币种
     */
    private String tradeCcy;

    /**
     * 买入端交易金额
     */
    private BigDecimal buyTradeAmt;

    /**
     * 收息
     */
    private BigDecimal recInt;

    /**
     * 卖出币种
     */
    private String sellCcy;

    /**
     * 付息
     */
    private BigDecimal payInt;

    /**
     * 卖出端交易金额
     */
    private BigDecimal sellTradeAmt;

    /**
     * 交易汇率
     */
    private BigDecimal fxrate;

    /**
     * 空方交易汇率
     */
    private BigDecimal fxrate2;
    
    /**
     * 业务类型
     * ADD BY WUJIE ON 20200213
     */
    private String busiType;

    /**
     * 外汇交易方向
     * add by yanya on 20200219
     */
    private String excPs;

    public BigDecimal getFxrate() {
        return fxrate;
    }

    public void setFxrate(BigDecimal fxrate) {
        this.fxrate = fxrate;
    }

    public BigDecimal getFxrate2() {
        return fxrate2;
    }

    public void setFxrate2(BigDecimal fxrate2) {
        this.fxrate2 = fxrate2;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public Date getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    public String getTradeCcy() {
        return tradeCcy;
    }

    public void setTradeCcy(String tradeCcy) {
        this.tradeCcy = tradeCcy;
    }

    public BigDecimal getBuyTradeAmt() {
        return buyTradeAmt;
    }

    public void setBuyTradeAmt(BigDecimal buyTradeAmt) {
        this.buyTradeAmt = buyTradeAmt;
    }

    public BigDecimal getRecInt() {
        return recInt;
    }

    public void setRecInt(BigDecimal recInt) {
        this.recInt = recInt;
    }

    public String getSellCcy() {
        return sellCcy;
    }

    public void setSellCcy(String sellCcy) {
        this.sellCcy = sellCcy;
    }

    public BigDecimal getPayInt() {
        return payInt;
    }

    public void setPayInt(BigDecimal payInt) {
        this.payInt = payInt;
    }

    public BigDecimal getSellTradeAmt() {
        return sellTradeAmt;
    }

    public void setSellTradeAmt(BigDecimal sellTradeAmt) {
        this.sellTradeAmt = sellTradeAmt;
    }

    /**
     * 获取 finProdType
     * @return finProdType.
     */
    public String getFinProdType() {
        return finProdType;
    }

    
    /**
     * 设置 finProdType
     * @param finProdType finProdType
     */
    public void setFinProdType(String finProdType) {
        this.finProdType = finProdType;
    }

    
    /**
     * 获取 ps
     * @return ps.
     */
    public String getPs() {
        return ps;
    }

    
    /**
     * 设置 ps
     * @param ps ps
     */
    public void setPs(String ps) {
        this.ps = ps;
    }

    
    /**
     * 获取 prinamt
     * @return prinamt.
     */
    public BigDecimal getPrinamt() {
        return prinamt;
    }

    
    /**
     * 设置 prinamt
     * @param prinamt prinamt
     */
    public void setPrinamt(BigDecimal prinamt) {
        this.prinamt = prinamt;
    }

    
    /**
     * 获取 intamt
     * @return intamt.
     */
    public BigDecimal getIntamt() {
        return intamt;
    }

    
    /**
     * 设置 intamt
     * @param intamt intamt
     */
    public void setIntamt(BigDecimal intamt) {
        this.intamt = intamt;
    }

    
    /**
     * 获取 tradeamt
     * @return tradeamt.
     */
    public BigDecimal getTradeamt() {
        return tradeamt;
    }

    
    /**
     * 设置 tradeamt
     * @param tradeamt tradeamt
     */
    public void setTradeamt(BigDecimal tradeamt) {
        this.tradeamt = tradeamt;
    }


    public String getBooksetId() {
        return booksetId;
    }

    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

	public String getBusiType() {
		return busiType;
	}

	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

    public String getExcPs() {
        return excPs;
    }

    public void setExcPs(String excPs) {
        this.excPs = excPs;
    }

    public BigDecimal getFeeamtpay() {
        return feeamtpay;
    }

    public void setFeeamtpay(BigDecimal feeamtpay) {
        this.feeamtpay = feeamtpay;
    }
}
