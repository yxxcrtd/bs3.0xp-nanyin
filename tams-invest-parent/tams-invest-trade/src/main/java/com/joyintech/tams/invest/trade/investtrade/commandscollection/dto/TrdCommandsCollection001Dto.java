/*
 * 文件名：TrdCommandsCollection001Dto.java
 * 版权：Copyright 2018-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.1.2
 * 修改人：王贵奇
 * 修改时间：2019年12月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.1.2
 */

package com.joyintech.tams.invest.trade.investtrade.commandscollection.dto;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.constant.DictCode;

/**
 *指令汇总dto
 * @author 王贵奇
 * @version 1.0 2019年12月31日
 * @see TrdCommandsCollection001Dto
 * @since 1.0
 */
@PageId("TrdCommandsCollection001")
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
     * 一级分类、回购类型、基金类型
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
     * 上市板块
     */
    private String board;

    /**
     * 交易模板
     */
    private String dealMode;

    /**
     * 委托详情列表
     */
    public List<TrdCommandsCollection002Dto> entrustList;

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
     * 获取 type6名称
     * @return type6Str type6名称
     */
    public String getType6Str() {
        return SysDataUtil.getNameFromDataDict(DictCode.WIND一级分类_含债券一级分类等, getType6());
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
     * 获取 type7名称
     * @return type7Str type7名称
     */
    public String getType7Str() {
        return SysDataUtil.getNameFromDataDict(DictCode.WIND二级分类_含债券二级分类等, getType7());
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
     * 获取 pledgeFinprodStr
     * @return pledgeFinprodStr
     */
    public String getPledgeFinprodStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.质押标的, getPledgeFinprod());
    }
    
    /**
     * 获取 交易方向名称
     * @return psStr 交易方向名称
     */
    public String getPsStr() {
        String psStr = SysDataUtil.getNameFromDataDict(DictCode.交易方向, super.getPs());
        if(psStr != null)
            return SysDataUtil.getNameFromDataDict(DictCode.交易方向, super.getPs());
        else 
            return SysDataUtil.getNameFromDataDict(DictCode.回购拆借方向, super.getPs());
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
     * @param  secAcctName
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
     * 获取 币种名称
     * @return ccyStr 
     */
    public String getCcyStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.币种, super.getCcy());
    }

    /**
     * 获取 交易场所名称
     * @return tradeMarketStr 
     */
    public String getTradeMarketStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.交易场所, super.getTradeMarket());
    }

    /**
     * 获取 交易平台名称
     * @return tradePlatStr 
     */
    public String getTradePlatStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.交易平台, super.getTradePlat());
    }

    /**
     * 获取 清算速度名称
     * @return settleSpeedStr 
     */
    public String getSettleSpeedStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.清算速度, getSettleSpeed());
    }

    /**
     * 获取 上市板名称
     * @return boardStr 
     */
    public String getBoardStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.上市板, this.getBoard());
    }

    /**
     * 获取 申购方式名称
     * @return purchModleStr 
     */
    public String getPurchModleStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.申购方式, getPurchModle());
    }

    /**
     * 获取 申购类型名称
     * @return purchTypeStr 
     */
    public String getPurchTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.申购类型, getPurchType());
    }

    /**
     * 获取 回购类型名称
     * @return repoTypeStr 
     */
    public String getRepoTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.回购类型, getType6());
    }

    /**
     * 获取 基金类型名称
     * @return fundTypeStr 
     */
    public String getfundTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.基金类资产类型, getType6());
    }

    /**
     * 获取 计划类资产类型名称
     * @return planTypeStr 
     */
    public String getPlanTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.计划类资产一级分类, getType6());
    }

    /**
     * 获取 存款类资产类型名称
     * @return depoTypeStr 
     */
    public String getDepoTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.存款类资产类型, getType6());
    }

    /**
     * 获取 项目类资产类型名称
     * @return projectTypeStr 
     */
    public String getProjectTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.项目类一级分类, getType6());
    }

    /**
     * 获取 拆借类型名称
     * @return lendTypeStr 
     */
    public String getLendTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.拆借类型, getType6());
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getDealMode() {
        return dealMode;
    }

    public void setDealMode(String dealMode) {
        this.dealMode = dealMode;
    }
}
