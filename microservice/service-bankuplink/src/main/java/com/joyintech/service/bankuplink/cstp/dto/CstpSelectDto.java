/*
 * 文件名：CstpSelectDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年12月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.bankuplink.cstp.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.tams.common.base.entity.InterfCstpTrade;

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
    /**
     * 证券托管户
     */
    private String acctNo;
    /**
     * 交割日期
     */
    private Date settleDate;
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

    
    /**
     * 获取 acctNo
     * @return acctNo.
     */
    public String getAcctNo() {
        return acctNo;
    }

    
    /**
     * 设置 acctNo
     * @param acctNo acctNo
     */
    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    
    /**
     * 获取 settleDate
     * @return settleDate.
     */
    public Date getSettleDate() {
        return settleDate;
    }

    
    /**
     * 设置 settleDate
     * @param settleDate settleDate
     */
    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    //add by chenjiaxing 质押式回购改造 start
    /**
     * 对手方交易员名称
     */
    private String counterTraderName;

    /**
     * 首次结算方式
     */
    private Integer deliveryType;

    /**
     * 到期结算方式
     */
    private Integer deliveryType2;

    /**
     * 交易方式
     */
    private String tradeMethod;

    /**
     *交易品种  债券代码
     */
    private String securityId;
    /**
     * 清算速度
     */
    private String settleSpeed;

    public String getSettleSpeed() {
        return settleSpeed;
    }

    public void setSettleSpeed(String settleSpeed) {
        this.settleSpeed = settleSpeed;
    }

    /**
     * 成交状态
     */
    private String execType;

    /**
     * 债券币种
     */
    private String ccy;

    /**
     * 清算类型
     */
    private String settType;

    /**
     * 净额清算状态
     */
    private String settleStatus;

    /**
     * 回购方式
     */
    private String repoMethod;

    /**
     * 结算状态
     */
    private String settleStatus2;

    /**
     * 汇率
     */
    private BigDecimal exchangerate;

    /**
     * 回复编号
     */
    private BigDecimal shareId;
    /**
     * 对手方机构名
     */
    private String cpPartyName;
    /**
     * 折算比例%
     */
    private BigDecimal pledgeRate;

    /**
     * 代理方交易员名称
     */
    private String otherTraderName;

    public String getOtherTraderName() {
        return otherTraderName;
    }

    public void setOtherTraderName(String otherTraderName) {
        this.otherTraderName = otherTraderName;
    }

    public BigDecimal getPledgeRate() {
        return pledgeRate;
    }

    public void setPledgeRate(BigDecimal pledgeRate) {
        this.pledgeRate = pledgeRate;
    }

    public String getCpPartyName() {
        return cpPartyName;
    }

    public void setCpPartyName(String cpPartyName) {
        this.cpPartyName = cpPartyName;
    }

    public String getCounterTraderName() {
        return counterTraderName;
    }

    public void setCounterTraderName(String counterTraderName) {
        this.counterTraderName = counterTraderName;
    }


    public Integer getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(Integer deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Integer getDeliveryType2() {
        return deliveryType2;
    }

    public void setDeliveryType2(Integer deliveryType2) {
        this.deliveryType2 = deliveryType2;
    }

    public String getTradeMethod() {
        return tradeMethod;
    }

    public void setTradeMethod(String tradeMethod) {
        this.tradeMethod = tradeMethod;
    }

    public String getSecurityId() {
        return securityId;
    }

    public void setSecurityId(String securityId) {
        this.securityId = securityId;
    }

    public String getExecType() {
        return execType;
    }

    public void setExecType(String execType) {
        this.execType = execType;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getSettType() {
        return settType;
    }

    public void setSettType(String settType) {
        this.settType = settType;
    }

    public String getSettleStatus() {
        return settleStatus;
    }

    public void setSettleStatus(String settleStatus) {
        this.settleStatus = settleStatus;
    }

    public String getRepoMethod() {
        return repoMethod;
    }

    public void setRepoMethod(String repoMethod) {
        this.repoMethod = repoMethod;
    }

    public String getSettleStatus2() {
        return settleStatus2;
    }

    public void setSettleStatus2(String settleStatus2) {
        this.settleStatus2 = settleStatus2;
    }

    public BigDecimal getExchangerate() {
        return exchangerate;
    }

    public void setExchangerate(BigDecimal exchangerate) {
        this.exchangerate = exchangerate;
    }

    public BigDecimal getShareId() {
        return shareId;
    }

    public void setShareId(BigDecimal shareId) {
        this.shareId = shareId;
    }

    /**
     * 交易编号
     */
    private String tradeId;

    /**
     * 成交编号
     */
    private String fTradeId;
    /**
     * 反向交易编号
     */
    private String rTradeId;

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getfTradeId() {
        return fTradeId;
    }

    public void setfTradeId(String fTradeId) {
        this.fTradeId = fTradeId;
    }

    public String getrTradeId() {
        return rTradeId;
    }

    public void setrTradeId(String rTradeId) {
        this.rTradeId = rTradeId;
    }
    /**
     * 金融产品代码
     */
    private String finprodId;

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    //add by chenjiaxing 质押式回购改造 end
}
