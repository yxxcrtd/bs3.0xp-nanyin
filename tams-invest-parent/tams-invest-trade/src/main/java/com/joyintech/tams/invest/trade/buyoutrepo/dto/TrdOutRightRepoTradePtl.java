/*
 * 文件名：TrdOutRightRepoTrade001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2019年01月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.buyoutrepo.dto;

import java.math.BigDecimal;
import java.util.Date;

public class TrdOutRightRepoTradePtl{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8621421223620044916L;
	
	/**
	 * 组合代码
	 */
	private String portfolioId;
	
	/**
	 * 委托代码
	 */
	private String entrustId;
	
	/**
	 * 证券账户
	 */
	private String secManageAcctId;
	
	/**
	 * 剩余委托额度
	 */
	private BigDecimal doEntrustMoney;
	
	/**
     * 券面总额
     */
    private BigDecimal tradeAmt;
    
    /**
     * 回购利率%
     */
    private BigDecimal repoRate;
    
    /**
     * 首次应计利息
     */
    private BigDecimal firstIntAmt;
    
    /**
     * 首次结算金额
     */
    private BigDecimal firstSettleAmt;
    
    /**
     * 到期应计利息
     */
    private BigDecimal matIntAmt;
    
    /**
     * 到期结算金额
     */
    private BigDecimal matSettleAmt;
    
    /**
     * 交易手续费
     */
    private BigDecimal procedureAmt;
    
    /**
     * 结算费
     */
    private BigDecimal settleAmt;
    
    /**
     * 交易金额
     */
    private BigDecimal tradeRepAmt;
    
    /**
     * 组合T日可用现金
     */
    private BigDecimal bussTrdAvailableCashQueryavalibleCash;
    
    /**
     * 组合T+1日可用现金
     */
    private BigDecimal bussTrdAvailableCashQueryavalibleCash1;
    
    /**
     * 资产可用持仓
     */
    private BigDecimal assetsavailablepositions;
    
    /**
     * 
     * 通道可用现金
     */
    private BigDecimal channelAvailableCash;
    
    /**
     * 委托时间
     */
    private Date entrustDate;

    /**
     * 获取 portfolioId
     * @return portfolioId.
     */
    public String getPortfolioId() {
        return portfolioId;
    }


    
    /**
     * 设置 portfolioId
     * @param portfolioId portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }


    /**
     * 获取 entrustId
     * @return entrustId.
     */
    public String getEntrustId() {
        return entrustId;
    }

    
    /**
     * 设置 entrustId
     * @param entrustId entrustId
     */
    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

    
    /**
     * 获取 secManageAcctId
     * @return secManageAcctId.
     */
    public String getSecManageAcctId() {
        return secManageAcctId;
    }

    
    /**
     * 设置 secManageAcctId
     * @param secManageAcctId secManageAcctId
     */
    public void setSecManageAcctId(String secManageAcctId) {
        this.secManageAcctId = secManageAcctId;
    }

    
    /**
     * 获取 doEntrustMoney
     * @return doEntrustMoney.
     */
    public BigDecimal getDoEntrustMoney() {
        return doEntrustMoney;
    }

    
    /**
     * 设置 doEntrustMoney
     * @param doEntrustMoney doEntrustMoney
     */
    public void setDoEntrustMoney(BigDecimal doEntrustMoney) {
        this.doEntrustMoney = doEntrustMoney;
    }

    
    /**
     * 获取 tradeAmt
     * @return tradeAmt.
     */
    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    
    /**
     * 设置 tradeAmt
     * @param tradeAmt tradeAmt
     */
    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    
    /**
     * 获取 repoRate
     * @return repoRate.
     */
    public BigDecimal getRepoRate() {
        return repoRate;
    }

    
    /**
     * 设置 repoRate
     * @param repoRate repoRate
     */
    public void setRepoRate(BigDecimal repoRate) {
        this.repoRate = repoRate;
    }

    
    /**
     * 获取 firstIntAmt
     * @return firstIntAmt.
     */
    public BigDecimal getFirstIntAmt() {
        return firstIntAmt;
    }

    
    /**
     * 设置 firstIntAmt
     * @param firstIntAmt firstIntAmt
     */
    public void setFirstIntAmt(BigDecimal firstIntAmt) {
        this.firstIntAmt = firstIntAmt;
    }

    
    /**
     * 获取 firstSettleAmt
     * @return firstSettleAmt.
     */
    public BigDecimal getFirstSettleAmt() {
        return firstSettleAmt;
    }

    
    /**
     * 设置 firstSettleAmt
     * @param firstSettleAmt firstSettleAmt
     */
    public void setFirstSettleAmt(BigDecimal firstSettleAmt) {
        this.firstSettleAmt = firstSettleAmt;
    }

    
    /**
     * 获取 matIntAmt
     * @return matIntAmt.
     */
    public BigDecimal getMatIntAmt() {
        return matIntAmt;
    }

    
    /**
     * 设置 matIntAmt
     * @param matIntAmt matIntAmt
     */
    public void setMatIntAmt(BigDecimal matIntAmt) {
        this.matIntAmt = matIntAmt;
    }

    
    /**
     * 获取 matSettleAmt
     * @return matSettleAmt.
     */
    public BigDecimal getMatSettleAmt() {
        return matSettleAmt;
    }

    
    /**
     * 设置 matSettleAmt
     * @param matSettleAmt matSettleAmt
     */
    public void setMatSettleAmt(BigDecimal matSettleAmt) {
        this.matSettleAmt = matSettleAmt;
    }

    
    /**
     * 获取 procedureAmt
     * @return procedureAmt.
     */
    public BigDecimal getProcedureAmt() {
        return procedureAmt;
    }

    
    /**
     * 设置 procedureAmt
     * @param procedureAmt procedureAmt
     */
    public void setProcedureAmt(BigDecimal procedureAmt) {
        this.procedureAmt = procedureAmt;
    }

    
    /**
     * 获取 settleAmt
     * @return settleAmt.
     */
    public BigDecimal getSettleAmt() {
        return settleAmt;
    }

    
    /**
     * 设置 settleAmt
     * @param settleAmt settleAmt
     */
    public void setSettleAmt(BigDecimal settleAmt) {
        this.settleAmt = settleAmt;
    }

    
    /**
     * 获取 tradeRepAmt
     * @return tradeRepAmt.
     */
    public BigDecimal getTradeRepAmt() {
        return tradeRepAmt;
    }

    
    /**
     * 设置 tradeRepAmt
     * @param tradeRepAmt tradeRepAmt
     */
    public void setTradeRepAmt(BigDecimal tradeRepAmt) {
        this.tradeRepAmt = tradeRepAmt;
    }

    
    /**
     * 获取 bussTrdAvailableCashQueryavalibleCash
     * @return bussTrdAvailableCashQueryavalibleCash.
     */
    public BigDecimal getBussTrdAvailableCashQueryavalibleCash() {
        return bussTrdAvailableCashQueryavalibleCash;
    }

    
    /**
     * 设置 bussTrdAvailableCashQueryavalibleCash
     * @param bussTrdAvailableCashQueryavalibleCash bussTrdAvailableCashQueryavalibleCash
     */
    public void setBussTrdAvailableCashQueryavalibleCash(BigDecimal bussTrdAvailableCashQueryavalibleCash) {
        this.bussTrdAvailableCashQueryavalibleCash = bussTrdAvailableCashQueryavalibleCash;
    }

    
    /**
     * 获取 bussTrdAvailableCashQueryavalibleCash1
     * @return bussTrdAvailableCashQueryavalibleCash1.
     */
    public BigDecimal getBussTrdAvailableCashQueryavalibleCash1() {
        return bussTrdAvailableCashQueryavalibleCash1;
    }

    
    /**
     * 设置 bussTrdAvailableCashQueryavalibleCash1
     * @param bussTrdAvailableCashQueryavalibleCash1 bussTrdAvailableCashQueryavalibleCash1
     */
    public void setBussTrdAvailableCashQueryavalibleCash1(BigDecimal bussTrdAvailableCashQueryavalibleCash1) {
        this.bussTrdAvailableCashQueryavalibleCash1 = bussTrdAvailableCashQueryavalibleCash1;
    }

    /**
     * 获取 assetsavailablepositions
     * @return assetsavailablepositions.
     */
    public BigDecimal getAssetsavailablepositions() {
        return assetsavailablepositions;
    }



    
    /**
     * 设置 assetsavailablepositions
     * @param assetsavailablepositions assetsavailablepositions
     */
    public void setAssetsavailablepositions(BigDecimal assetsavailablepositions) {
        this.assetsavailablepositions = assetsavailablepositions;
    }



    /**
     * 获取 channelAvailableCash
     * @return channelAvailableCash.
     */
    public BigDecimal getChannelAvailableCash() {
        return channelAvailableCash;
    }

    
    /**
     * 设置 channelAvailableCash
     * @param channelAvailableCash channelAvailableCash
     */
    public void setChannelAvailableCash(BigDecimal channelAvailableCash) {
        this.channelAvailableCash = channelAvailableCash;
    }

    
    /**
     * 获取 serialversionuid
     * @return serialversionuid.
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * 获取 entrustDate
     * @return entrustDate.
     */
    public Date getEntrustDate() {
        return entrustDate;
    }

    /**
     * 设置 entrustDate
     * @param entrustDate entrustDate
     */
    public void setEntrustDate(Date entrustDate) {
        this.entrustDate = entrustDate;
    }
	
    
}
