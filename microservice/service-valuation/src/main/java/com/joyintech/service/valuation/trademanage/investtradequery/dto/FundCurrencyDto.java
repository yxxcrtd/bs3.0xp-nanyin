/*
 * 文件名：fundCurrencyDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年7月28日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.trademanage.investtradequery.dto;

import java.math.BigDecimal;

/**
 * 基金类资产查询 dto
 * 
 * @author Administrator
 * @version 1.0 2018年7月28日
 * @see FundCurrencyDto
 * @since 1.0
 */
public class FundCurrencyDto extends InvestTradeDto {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 交易份额
     */
    private BigDecimal shareAmt;

    /**
     * 利息金额
     */
    private BigDecimal intAmt;

//    /**
//     * 入账日
//     */
//    private String bookDate;

    /**
     * 赎回净值
     */
    private BigDecimal unitCprice;

    /**
     * 现金分红金额
     */
    private BigDecimal cashAmt;

    /**
     * 红利再投金额
     */
    private BigDecimal bonusAmt;

    /**
     * 红利再投份额
     */
    private BigDecimal bonusShare;

    /**
     * 应收红利
     */
    private BigDecimal bAmt;

    /**
     * 实收红利
     */
    private BigDecimal realAmt;

//    /**
//     * 确认日
//     */
//    private String confirmDate;

    /**
     * 退款金额
     */
    private BigDecimal settleAmt;

    /**
     * 预计到账日
     */
    private String settleDate;

    /**
     * 实际到账日
     */
    private String realDate;
    
    /**
     * 交易场所
     */
    private String tradeMarket;
    
    /**
     * 转换资产代码
     */
    private String rFinprodId;
    
    /**
     * 通道费是否随交易支付
     */
    private String isPayWithTrade;
    
    /**
     * 佣金
     */
    private BigDecimal tdFeeAmt2;
    
    /**
     * 佣金是否随交易支付
     */
    private String isPayWithTrade2;

//    /**
//     * 获取 confirmDate
//     * 
//     * @return confirmDate.
//     */
//    public String getConfirmDate() {
//        return confirmDate;
//    }

//    /**
//     * 设置 confirmDate
//     * 
//     * @param confirmDate confirmDate
//     */
//    public void setConfirmDate(String confirmDate) {
//        this.confirmDate = confirmDate;
//    }

    /**
     * 获取 settleAmt
     * 
     * @return settleAmt.
     */
    public BigDecimal getSettleAmt() {
        return null!=settleAmt?settleAmt:BigDecimal.ZERO;
    }

    /**
     * 设置 settleAmt
     * 
     * @param settleAmt settleAmt
     */
    public void setSettleAmt(BigDecimal settleAmt) {
        this.settleAmt = settleAmt;
    }

    /**
     * 获取 settleDate
     * 
     * @return settleDate.
     */
    public String getSettleDate() {
        return settleDate;
    }

    /**
     * 设置 settleDate
     * 
     * @param settleDate settleDate
     */
    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }

    /**
     * 获取 realDate
     * 
     * @return realDate.
     */
    public String getRealDate() {
        return realDate;
    }

    /**
     * 设置 realDate
     * 
     * @param realDate realDate
     */
    public void setRealDate(String realDate) {
        this.realDate = realDate;
    }

//    /**
//     * 获取 bookDate
//     * 
//     * @return bookDate.
//     */
//    public String getBookDate() {
//        return bookDate;
//    }
//
//    /**
//     * 设置 bookDate
//     * 
//     * @param bookDate bookDate
//     */
//    public void setBookDate(String bookDate) {
//        this.bookDate = bookDate;
//    }

    /**
     * 获取 unitCprice
     * 
     * @return unitCprice.
     */
    public BigDecimal getUnitCprice() {
        return null!=unitCprice?unitCprice:BigDecimal.ZERO;
    }

    /**
     * 设置 unitCprice
     * 
     * @param unitCprice unitCprice
     */
    public void setUnitCprice(BigDecimal unitCprice) {
        this.unitCprice = unitCprice;
    }

    /**
     * 获取 cashAmt
     * 
     * @return cashAmt.
     */
    public BigDecimal getCashAmt() {
        return null!=cashAmt?cashAmt:BigDecimal.ZERO;
    }

    /**
     * 设置 cashAmt
     * 
     * @param cashAmt cashAmt
     */
    public void setCashAmt(BigDecimal cashAmt) {
        this.cashAmt = cashAmt;
    }

    /**
     * 获取 bonusAmt
     * 
     * @return bonusAmt.
     */
    public BigDecimal getBonusAmt() {
        return null!=bonusAmt?bonusAmt:BigDecimal.ZERO;
    }

    /**
     * 设置 bonusAmt
     * 
     * @param bonusAmt bonusAmt
     */
    public void setBonusAmt(BigDecimal bonusAmt) {
        this.bonusAmt = bonusAmt;
    }

    /**
     * 获取 bonusShare
     * 
     * @return bonusShare.
     */
    public BigDecimal getBonusShare() {
        return null!=bonusShare?bonusShare:BigDecimal.ZERO;
    }

    /**
     * 设置 bonusShare
     * 
     * @param bonusShare bonusShare
     */
    public void setBonusShare(BigDecimal bonusShare) {
        this.bonusShare = bonusShare;
    }

    /**
     * 获取 bAmt
     * 
     * @return bAmt.
     */
    public BigDecimal getbAmt() {
        return null!=bAmt?bAmt:BigDecimal.ZERO;
    }

    /**
     * 设置 bAmt
     * 
     * @param bAmt bAmt
     */
    public void setbAmt(BigDecimal bAmt) {
        this.bAmt = bAmt;
    }

    /**
     * 获取 realAmt
     * 
     * @return realAmt.
     */
    public BigDecimal getRealAmt() {
        return null!=realAmt?realAmt:BigDecimal.ZERO;
    }

    /**
     * 设置 realAmt
     * 
     * @param realAmt realAmt
     */
    public void setRealAmt(BigDecimal realAmt) {
        this.realAmt = realAmt;
    }

    /**
     * 获取 intAmt
     * 
     * @return intAmt.
     */
    public BigDecimal getIntAmt() {
        return null!=intAmt?intAmt:BigDecimal.ZERO;
    }

    /**
     * 设置 intAmt
     * 
     * @param intAmt intAmt
     */
    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }

    /**
     * 获取 shareAmt
     * 
     * @return shareAmt.
     */
    public BigDecimal getShareAmt() {
        return null!=shareAmt?shareAmt:BigDecimal.ZERO;
    }

    /**
     * 设置 shareAmt
     * 
     * @param shareAmt shareAmt
     */
    public void setShareAmt(BigDecimal shareAmt) {
        this.shareAmt = shareAmt;
    }
    
    /**
     * 获取 佣金
     * 
     * @return tdFeeAmt2.
     */
    public BigDecimal getTdFeeAmt2() {
        return null!=tdFeeAmt2?tdFeeAmt2:BigDecimal.ZERO;
    }

    /**
     * 设置 佣金
     * 
     * @param tdFeeAmt2 tdFeeAmt2
     */
    public void setTdFeeAmt2(BigDecimal tdFeeAmt2) {
        this.tdFeeAmt2 = tdFeeAmt2;
    }
    /**
     * 获取 交易场所
     * 
     * @return tradeMarket.
     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    /**
     * 设置 交易场所
     * 
     * @param tradeMarket tradeMarket
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }
    
    /**
     * 获取 转换资产代码
     * 
     * @return rFinprodId.
     */
    public String getrFinprodId() {
        return rFinprodId;
    }

    /**
     * 设置 转换资产代码
     * 
     * @param rFinprodId rFinprodId
     */
    public void setrFinprodId(String rFinprodId) {
        this.rFinprodId = rFinprodId;
    }
    
    /**
     * 获取 通道费是否随交易支付
     * 
     * @return isPayWithTrade.
     */
    public String getIsPayWithTrade() {
        return isPayWithTrade;
    }

    /**
     * 设置 通道费是否随交易支付
     * 
     * @param isPayWithTrade isPayWithTrade
     */
    public void setIsPayWithTrade(String isPayWithTrade) {
        this.isPayWithTrade = isPayWithTrade;
    }
    
    /**
     * 获取 佣金是否随交易支付
     * 
     * @return isPayWithTrade2.
     */
    public String getIsPayWithTrade2() {
        return isPayWithTrade2;
    }

    /**
     * 设置 佣金是否随交易支付
     * 
     * @param isPayWithTrade2 isPayWithTrade2
     */
    public void setIsPayWithTrade2(String isPayWithTrade2) {
        this.isPayWithTrade2 = isPayWithTrade2;
    }

}
