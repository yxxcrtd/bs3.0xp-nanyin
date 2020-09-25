/*
 * 文件名：TrdCommandsCollection001Dto.java
 * 版权：Copyright 2018-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.1.2
 * 修改人：王贵奇
 * 修改时间：2019年12月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.1.2
 */

package com.joyintech.service.trade.investtrade.commandscollection.dto;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.tams.common.base.entity.EtrEntrust;

/**
 *指令汇总微服务端dto
 * @author 王贵奇
 * @version 1.0 2019年12月31日
 * @see TrdCommandsCollection001Dto
 * @since 1.0
 */
public class TrdCommandsCollection001Dto extends EtrEntrust{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 资产代码
     */
    private String finprodMarketId;

    /**
     * 资产名称
     */
    private String finprodName;

    /**
     * 一级分类、基金类型、回购方式
     */
    private String type6;

    /**
     * 二级分类
     */
    private String type7;

    /**
     * 质押标的
     */
    private String pledgeFinprod;

    /**
     * 未完成券面总额、数量、股数
     */
    private BigDecimal unfinishedAmt;

    /**
     * 清算速度
     */
    private String settleSpeed;
    
    /**
     * 通道名称
     */
    private String chlName;

    /**
     * 证券账户id
     */
    private String secAcctId;

    /**
     * 证券账户名称
     */
    private String SecAcctName;

    /**
     * 交易对手名称
     */
    private String counterName;

    /**
     * 交易日历名称
     */
    private String calendarName;

    /**
     * 申购方式
     */
    private String purchModle;

    /**
     * 申购类型
     */
    private String purchType;

    /**
     * 正股代码
     */
    private String shareId;

    /**
     * 正股名称
     */
    private String shareName;

    /**
     * 转股代码
     */
    private String conShareId;

    /**
     * 期限（天数）
     */
    private int termDays;

    /**
     * 转股券面总额
     */
    private BigDecimal stocketrPriceTotal;

    /**
     * 预计转股价值
     */
    private BigDecimal expConValue;

    /**
     * 委托详情列表
     */
    private List<TrdCommandsCollection002Dto> entrustList;
    
    /**
     * 获取 finprodMarketId
     * @return finprodMarketId
     */
    public String getFinprodMarketId() {
        return finprodMarketId;
    }
    
    /**
     * 设置 finprodMarketId
     * @param finprodMarketId finprodMarketId
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    /**
     * 获取 finprodName
     * @return finprodName
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * 设置 finprodName
     * @param finprodName finprodName
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    /**
     * 获取 type6
     * @return type6
     */
    public String getType6() {
        return type6;
    }

    /**
     * 设置 type6
     * @param type6 type6
     */
    public void setType6(String type6) {
        this.type6 = type6;
    }

    /**
     * 获取 type7
     * @return type7
     */
    public String getType7() {
        return type7;
    }

    /**
     * 设置 type7
     * @param type7 type7
     */
    public void setType7(String type7) {
        this.type7 = type7;
    }

    /**
     * 获取 pledgeFinprod
     * @return pledgeFinprod
     */
    public String getPledgeFinprod() {
        return pledgeFinprod;
    }

    /**
     * 设置 pledgeFinprod
     * @param pledgeFinprod pledgeFinprod
     */
    public void setPledgeFinprod(String pledgeFinprod) {
        this.pledgeFinprod = pledgeFinprod;
    }

    /**
     * 获取 unfinishedAmt
     * @return unfinishedAmt
     */
    public BigDecimal getUnfinishedAmt() {
        return unfinishedAmt;
    }

    /**
     * 设置 unfinishedAmt
     * @param unfinishedAmt unfinishedAmt
     */
    public void setUnfinishedAmt(BigDecimal unfinishedAmt) {
        this.unfinishedAmt = unfinishedAmt;
    }

    /**
     * 获取 settleSpeed
     * @return settleSpeed
     */
    public String getSettleSpeed() {
        return settleSpeed;
    }

    /**
     * 设置 settleSpeed
     * @param settleSpeed settleSpeed
     */
    public void setSettleSpeed(String settleSpeed) {
        this.settleSpeed = settleSpeed;
    }

    /**
     * 获取 chlName
     * @return chlName
     */
    public String getChlName() {
        return chlName;
    }

    /**
     * 设置 chlName
     * @param chlName chlName
     */
    public void setChlName(String chlName) {
        this.chlName = chlName;
    }

    /**
     * 获取 secAcctId
     * @return secAcctId
     */
    public String getSecAcctId() {
        return secAcctId;
    }

    /**
     * 设置 secAcctId
     * @param secAcctId secAcctId
     */
    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }

    /**
     * 获取 SecAcctName
     * @return SecAcctName
     */
    public String getSecAcctName() {
        return SecAcctName;
    }

    /**
     * 设置 SecAcctName
     * @param SecAcctName SecAcctName
     */
    public void setSecAcctName(String secAcctName) {
        SecAcctName = secAcctName;
    }

    /**
     * 获取 counterName
     * @return counterName
     */
    public String getCounterName() {
        return counterName;
    }

    /**
     * 设置 counterName
     * @param counterName counterName
     */
    public void setCounterName(String counterName) {
        this.counterName = counterName;
    }

    /**
     * 获取 calendarName
     * @return calendarName
     */
    public String getCalendarName() {
        return calendarName;
    }

    /**
     * 设置 calendarName
     * @param calendarName calendarName
     */
    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }

    /**
     * 获取 purchModle
     * @return purchModle
     */
    public String getPurchModle() {
        return purchModle;
    }

    /**
     * 设置 purchModle
     * @param purchModle purchModle
     */
    public void setPurchModle(String purchModle) {
        this.purchModle = purchModle;
    }

    /**
     * 获取 purchType
     * @return purchType
     */
    public String getPurchType() {
        return purchType;
    }

    /**
     * 设置 purchType
     * @param purchType purchType
     */
    public void setPurchType(String purchType) {
        this.purchType = purchType;
    }

    /**
     * 获取 shareId
     * @return shareId
     */
    public String getShareId() {
        return shareId;
    }

    /**
     * 设置 shareId
     * @param shareId shareId
     */
    public void setShareId(String shareId) {
        this.shareId = shareId;
    }

    /**
     * 获取 shareName
     * @return shareName
     */
    public String getShareName() {
        return shareName;
    }

    /**
     * 设置 shareName
     * @param shareName shareName
     */
    public void setShareName(String shareName) {
        this.shareName = shareName;
    }

    /**
     * 获取 conShareId
     * @return conShareId
     */
    public String getConShareId() {
        return conShareId;
    }

    /**
     * 设置 conShareId
     * @param conShareId conShareId
     */
    public void setConShareId(String conShareId) {
        this.conShareId = conShareId;
    }

    /**
     * 获取 termDays
     * @return termDays
     */
    public int getTermDays() {
        return termDays;
    }

    /**
     * 设置 termDays
     * @param termDays termDays
     */
    public void setTermDays(int termDays) {
        this.termDays = termDays;
    }

    /**
     * 获取 stocketrPriceTotal
     * @return stocketrPriceTotal
     */
    public BigDecimal getStocketrPriceTotal() {
        return stocketrPriceTotal;
    }

    /**
     * 设置 stocketrPriceTotal
     * @param stocketrPriceTotal stocketrPriceTotal
     */
    public void setStocketrPriceTotal(BigDecimal stocketrPriceTotal) {
        this.stocketrPriceTotal = stocketrPriceTotal;
    }

    /**
     * 获取 expConValue
     * @return expConValue
     */
    public BigDecimal getExpConValue() {
        return expConValue;
    }

    /**
     * 设置 expConValue
     * @param expConValue expConValue
     */
    public void setExpConValue(BigDecimal expConValue) {
        this.expConValue = expConValue;
    }

    /**
     * 获取 entrustList
     * @return entrustList
     */
    public List<TrdCommandsCollection002Dto> getEntrustList() {
        return entrustList;
    }

    /**
     * 设置 entrustList
     * @param entrustList entrustList
     */
    public void setEntrustList(List<TrdCommandsCollection002Dto> entrustList) {
        this.entrustList = entrustList;
    }
}
