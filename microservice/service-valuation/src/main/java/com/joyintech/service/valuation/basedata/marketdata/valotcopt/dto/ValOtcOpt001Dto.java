/*
 * 文件名：FinNCProductBuild001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：tanxuan
 * 修改时间：2020年03月05日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.basedata.marketdata.valotcopt.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 产品资产查询Dto
 * 
 * @author tanxuan
 * @version 1.0 2020年03月05日
 * @see ValOtcOpt001Dto
 * @since 1.0
 */
public class ValOtcOpt001Dto extends BaseEntity implements ProcessEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 有效状态
     */
    private String activeState;
    
    /**
     * 汇总交易编号
     */
    private String totleTradeId;
    
    /**
     * 交易方向
     */
    private String ps;
    
    /**
     * 交易日期
     */
    private String tradeDate;
    
    /**
     * 交易份额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal shareAmt;
    
    /**
     * 全价总额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal fpriceAmt;
    
    /**
     * 账套代码
     */
    private String booksetId;
    
    /**
     * 账套名称
     */
    private String booksetName;
    
    /**
     * 基准编号
     */
    private String benchmarkId;
    
    /**
     * 结算日期
     */
    private String settleDate;
    
    /**
     * 执行价格
     */
    private String exePrice;
    
    /**
     * 期权费支付类型
     */
    private String optFeePayType;

    /**
     * 获取 有效状态
     * 
     * @return 有效状态
     */
    public String getActiveState() {
        return activeState;
    }

    /**
     * 设置 有效状态
     * 
     * @param activeState 有效状态
     */
    public void setActiveState(String activeState) {
        this.activeState = activeState;
    }
    
    /**
     * 获取 汇总交易编号
     * 
     * @return 汇总交易编号
     */
    public String getTotleTradeId() {
        return totleTradeId;
    }

    /**
     * 设置 汇总交易编号
     * 
     * @param totleTradeId 汇总交易编号
     */
    public void setTotleTradeId(String totleTradeId) {
        this.totleTradeId = totleTradeId;
    }
    
    /**
     * 获取 交易方向
     * 
     * @return 交易方向
     */
    public String getPs() {
        return ps;
    }

    /**
     * 设置 交易方向
     * 
     * @param ps 交易方向
     */
    public void setPs(String ps) {
        this.ps = ps;
    }
    
    /**
     * 获取 交易日期
     * 
     * @return 交易日期
     */
    public String getTradeDate() {
        return tradeDate;
    }

    /**
     * 设置 交易日期
     * 
     * @param tradeDate 交易日期
     */
    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }
    
    /**
     * 取得 交易份额
     * @return 交易份额
     */
    public BigDecimal getShareAmt() {
        return shareAmt;
    }
    
    /**
     * 设置 交易份额
     * @param shareAmt 交易份额
     */
    public void setShareAmt(BigDecimal shareAmt) {
        this.shareAmt = shareAmt;
    }
    
    /**
     * 取得 全价总额
     * @return 全价总额
     */
    public BigDecimal getFpriceAmt() {
        return fpriceAmt;
    }
    
    /**
     * 设置 全价总额
     * @param fpriceAmt 全价总额
     */
    public void setFpriceAmt(BigDecimal fpriceAmt) {
        this.fpriceAmt = fpriceAmt;
    }
    
    /**
     * 获取 账套代码
     * 
     * @return 账套代码
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 账套代码
     * 
     * @param booksetId 账套代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }
    
    /**
     * 获取 账套名称
     * 
     * @return 账套名称
     */
    public String getBooksetName() {
        return booksetName;
    }

    /**
     * 设置 账套名称
     * 
     * @param booksetName 账套名称
     */
    public void setBooksetName(String booksetName) {
        this.booksetName = booksetName;
    }
    
    /**
     * 获取 基准编号
     * 
     * @return 基准编号
     */
    public String getBenchmarkId() {
        return benchmarkId;
    }

    /**
     * 设置 基准编号
     * 
     * @param benchmarkId 基准编号
     */
    public void setBenchmarkId(String benchmarkId) {
        this.benchmarkId = benchmarkId;
    }
    
    /**
     * 获取 结算日期
     * 
     * @return 结算日期
     */
    public String getSettleDate() {
        return settleDate;
    }

    /**
     * 设置 结算日期
     * 
     * @param settleDate 结算日期
     */
    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }
    
    /**
     * 获取 执行价格
     * 
     * @return 执行价格
     */
    public String getExePrice() {
        return exePrice;
    }

    /**
     * 设置 执行价格
     * 
     * @param exePrice 执行价格
     */
    public void setExePrice(String exePrice) {
        this.exePrice = exePrice;
    }
    
    /**
     * 获取 期权费支付类型
     * 
     * @return 期权费支付类型
     */
    public String getOptFeePayType() {
        return optFeePayType;
    }

    /**
     * 设置 期权费支付类型
     * 
     * @param optFeePayType 期权费支付类型
     */
    public void setOptFeePayType(String optFeePayType) {
        this.optFeePayType = optFeePayType;
    }

}
