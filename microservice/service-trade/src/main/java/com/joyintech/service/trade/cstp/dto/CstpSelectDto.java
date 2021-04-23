/*
 * 文件名：CstpSelectDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年12月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.trade.cstp.dto;

import com.joyintech.tams.common.base.entity.InterfCstpTrade;

import java.math.BigDecimal;

/**
 * cstp下行交易查询Dto
 * @author fanshuanghu
 * @version 1.0 2018年12月4日
 * @see CstpSelectDto
 * @since 1.0
 */
public class CstpSelectDto extends InterfCstpTrade{
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1655410446150216805L;
    /**
     * 组合代码
     */
    private String portfolioId;
    /**
     * 组合名称
     */
    private String portfolioName;
    /**
     * 匹配状态
     */
    private String matchStatus;


    private String market;

    private BigDecimal prinamt;

    private BigDecimal repoamt;

    private String tradeId;

    private String workflowStatus;

    private String settleSpeed;

    private BigDecimal percent;

    private String ourTradeName;
    
    private String  userName;

    private BigDecimal lastqtyready;
    
    private String entrustId;

    public BigDecimal getLastqtyready() {
        return lastqtyready;
    }

    public void setLastqtyready(BigDecimal lastqtyready) {
        this.lastqtyready = lastqtyready;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    public String getSettleSpeed() {
        return settleSpeed;
    }

    public void setSettleSpeed(String settleSpeed) {
        this.settleSpeed = settleSpeed;
    }

    public String getWorkflowStatus() {
        return workflowStatus;
    }

    public void setWorkflowStatus(String workflowStatus) {
        this.workflowStatus = workflowStatus;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

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
     * 获取 portfolioName
     * @return portfolioName.
     */
    public String getPortfolioName() {
        return portfolioName;
    }
    
    /**
     * 设置 portfolioName
     * @param portfolioName portfolioName
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }
    
    /**
     * 获取 matchStatus
     * @return matchStatus.
     */
    public String getMatchStatus() {
        return matchStatus;
    }
    
    /**
     * 设置 matchStatus
     * @param matchStatus matchStatus
     */
    public void setMatchStatus(String matchStatus) {
        this.matchStatus = matchStatus;
    }

    public BigDecimal getPrinamt() {
        return prinamt;
    }

    public void setPrinamt(BigDecimal prinamt) {
        this.prinamt = prinamt;
    }

    public BigDecimal getRepoamt() {
        return repoamt;
    }

    public void setRepoamt(BigDecimal repoamt) {
        this.repoamt = repoamt;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getOurTradeName() {
        return ourTradeName;
    }

    public void setOurTradeName(String ourTradeName) {
        this.ourTradeName = ourTradeName;
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEntrustId() {
		return entrustId;
	}

	public void setEntrustId(String entrustId) {
		this.entrustId = entrustId;
	}
}
