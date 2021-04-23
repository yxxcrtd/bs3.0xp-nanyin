/*
 * 文件名：BookDetailCompareIntoReadResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.assetintdatecarryover.dto;

import com.joyintech.tams.common.base.entity.FinExercisePlan;

import java.util.Date;

/**
 * 资产理论结息日结转行权明细信息
 * @author yanya
 * @version 1.0 2021年3月11日
 * @see AssetIntdateCarryoverExecReaderDetail
 * @since 1.0
 */
public class AssetIntdateCarryoverExecReaderDetail extends FinExercisePlan {

    private static final long serialVersionUID = -5954110061026065190L;
    /**
     * 行权现金流一级类型
     */
    private String execCashType1;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 交易场所
     */
    private String tradeMarket;

    /**
     * 实际到期日
     */
    private Date actmdate;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 当前日期
     */
    private Date booksetDate;

    /**
     * 金融产品名称
     */
    private String finprodName;


    public String getExecCashType1() {
        return execCashType1;
    }

    public void setExecCashType1(String execCashType1) {
        this.execCashType1 = execCashType1;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getTradeMarket() {
        return tradeMarket;
    }

    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    public Date getActmdate() {
        return actmdate;
    }

    public void setActmdate(Date actmdate) {
        this.actmdate = actmdate;
    }

    public String getBooksetId() {
        return booksetId;
    }

    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    public Date getBooksetDate() {
        return booksetDate;
    }

    public void setBooksetDate(Date booksetDate) {
        this.booksetDate = booksetDate;
    }

    public String getFinprodName() {
        return finprodName;
    }

    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }
}
