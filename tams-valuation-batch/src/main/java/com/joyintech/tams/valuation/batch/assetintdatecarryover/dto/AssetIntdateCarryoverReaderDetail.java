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

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.common.base.entity.FinCashPlan;

/**
 * 资产理论结息日结转明细信息
 * 
 * @author chengzhong
 * @version 1.0 2018年6月7日
 * @see AssetIntdateCarryoverReaderDetail
 * @since 1.0
 */
public class AssetIntdateCarryoverReaderDetail extends FinCashPlan {


    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1136808423039141893L;

    /**
     * 金融产品代码
     */
    private String finprodId;
    
    /**
     * 金融产品类型
     */
    private String finprodType;

    /**
     * 现金流一级类型，本金、利息、费用
     */
    private String cashType1;

    /**
     * 现金流二级类型，本金、利息、托管费、固定管理费、浮动管理费等
     */
    private String cashType2;
    
    /**
     * 发行规模
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal issueAmt;
    
    /**
     * 币种
     */
    private String ccy;
    
    /**
     * 通道代码
     */
    private String chlFinprodId;
    

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

    /**
     * 获取 cashType1
     * @return cashType1.
     */
    public String getCashType1() {
        return cashType1;
    }

    
    /**
     * 设置 cashType1
     * @param cashType1 cashType1
     */
    public void setCashType1(String cashType1) {
        this.cashType1 = cashType1;
    }

    
    /**
     * 获取 cashType2
     * @return cashType2.
     */
    public String getCashType2() {
        return cashType2;
    }

    
    /**
     * 设置 cashType2
     * @param cashType2 cashType2
     */
    public void setCashType2(String cashType2) {
        this.cashType2 = cashType2;
    }

    
    /**
     * 获取 issueAmt
     * @return issueAmt.
     */
    public BigDecimal getIssueAmt() {
        return issueAmt;
    }

    
    /**
     * 设置 issueAmt
     * @param issueAmt issueAmt
     */
    public void setIssueAmt(BigDecimal issueAmt) {
        this.issueAmt = issueAmt;
    }

    
    /**
     * 获取 ccy
     * @return ccy.
     */
    public String getCcy() {
        return ccy;
    }

    
    /**
     * 设置 ccy
     * @param ccy ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    
    /**
     * 获取 chlFinprodId
     * @return chlFinprodId.
     */
    public String getChlFinprodId() {
        return chlFinprodId;
    }

    
    /**
     * 设置 chlFinprodId
     * @param chlFinprodId chlFinprodId
     */
    public void setChlFinprodId(String chlFinprodId) {
        this.chlFinprodId = chlFinprodId;
    }

    
    /**
     * 获取 tradeMarket
     * @return tradeMarket.
     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    
    /**
     * 设置 tradeMarket
     * @param tradeMarket tradeMarket
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    
    /**
     * 获取 actmdate
     * @return actmdate.
     */
    public Date getActmdate() {
        return actmdate;
    }

    
    /**
     * 设置 actmdate
     * @param actmdate actmdate
     */
    public void setActmdate(Date actmdate) {
        this.actmdate = actmdate;
    }

    
    /**
     * 获取 booksetId
     * @return booksetId.
     */
    public String getBooksetId() {
        return booksetId;
    }

    
    /**
     * 设置 booksetId
     * @param booksetId booksetId
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    
    /**
     * 获取 booksetDate
     * @return booksetDate.
     */
    public Date getBooksetDate() {
        return booksetDate;
    }

    
    /**
     * 设置 booksetDate
     * @param booksetDate booksetDate
     */
    public void setBooksetDate(Date booksetDate) {
        this.booksetDate = booksetDate;
    }


    
    public String getFinprodId() {
        return finprodId;
    }


    
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }


    
    public String getFinprodType() {
        return finprodType;
    }


    
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

    /**
     * 获取 金融产品名称
     * 
     * @return 金融产品名称
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * 设置 金融产品名称
     * 
     * @param finprodName 金融产品名称
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

}
