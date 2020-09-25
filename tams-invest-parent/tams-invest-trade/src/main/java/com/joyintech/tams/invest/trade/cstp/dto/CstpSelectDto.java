/*
 * 文件名：CstpSelectDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年12月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.trade.cstp.dto;

import com.joyintech.tams.common.base.entity.InterfCstpTrade;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

import java.math.BigDecimal;

/**
 * cstp下行交易Dto
 * @author wangxuelie
 * @version 1.0 2018年12月4日
 * @see CstpSelectDto
 * @since 1.0
 */
public class CstpSelectDto extends InterfCstpTrade{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 7604289208626153062L;
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

    private BigDecimal lastqtyready;
    
    
    /**
     * 得到交易完成度百分比
     * @return
     */
    public BigDecimal getDealPercent(){
        if(StringUtil.isNotEmpty(this.getPercent())){
            return this.getPercent().multiply(new BigDecimal(100));
        }else{
            return new BigDecimal(0);
        }
    }

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

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
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
     * 获取资产类型字符串
     * @return 资产类型
     * @see
     * @since 1.0
     */
    public String getBusinesstypeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.交易类型_CSTP, super.getBusinesstype());
    }
    /**
     * 获取交易市场
     * @return 交易市场
     * @see
     * @since 1.0
     */
    public String getMarketStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.交易场所, this.getMarket());
    }
    /**
     * 获取交易方向中文
     * @return 交易方向
     * @see
     * @since 1.0
     */
    public String getPsStr(){
        if(StringUtil.isNotEmpty(super.getSide()) && super.getSide().startsWith("T")){
            return SysDataUtil.getNameFromDataDict(DictCode.交易方向, super.getSide());
        }else{
            return SysDataUtil.getNameFromDataDict(DictCode.回购拆借方向, super.getSide());
        }
    }
    /**
     * 获取匹配状态字符串
     * @return 匹配状态
     * @see
     * @since 1.0
     */
    public String getMatchStatusStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.匹配状态, this.getMatchStatus());
    }

    public String getSettleSpeedStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.清算速度, this.getSettleSpeed());
    }
    /**
     * 获取日期yyyy-mm-dd格式
     * @return 交易日期字符串
     * @see
     * @since 1.0
     */
    public String getTradeDateStr(){
        if(StringUtil.isNotEmpty(super.getTradedate()) && super.getTradedate().length()>=8){
            return super.getTradedate().substring(0, 4) + "-" + super.getTradedate().substring(4, 6) + "-" + super.getTradedate().substring(6, 8);
        }
        return super.getTradedate();
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

    public String getOurTradeName() {
        return ourTradeName;
    }

    public void setOurTradeName(String ourTradeName) {
        this.ourTradeName = ourTradeName;
    }
}
