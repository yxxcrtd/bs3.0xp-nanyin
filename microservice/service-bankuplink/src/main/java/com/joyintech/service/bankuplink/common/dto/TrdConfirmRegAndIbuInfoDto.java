/*
 * 文件名：CfetsTrdConfirmRegInfoListResponse.java
 * 版权：Copyright 2020-2022 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：
 * 修改时间：2021年01月25日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */

package com.joyintech.service.bankuplink.common.dto;


import java.math.BigDecimal;
import java.util.Date;

/**
 * 银行间交易成交确认
 * 查询交易登记列表返回数据DTO
 * @author 1
 * @version 1.0 2021年10月26日
 * @see CfetsTrdConfirmRegInfoListResponse
 * @since 1.0
 */
public class TrdConfirmRegAndIbuInfoDto {
    /**
     * 登记或报价编号
     */
    private String totleTradeId;
    /**
     * 成交日期
     */
    private Date tradeDate;

    /**
     * 资产代码
     */
  private String finprodId;
    /**
     * 市场代码
     */
    private String finprodMarketId;

    /**
     * 交易场所
     */
    private String tradeMarket;
    /**
     * 交易方向
     */
    private String ps;
    /**
     * 清算速度
     */
    private String settleSpeed;
    /**
     * 证券账户
     */
    private String secManageAcctId;
    /**
     * 净价/到期收益率
     */
    private BigDecimal tradeYield;
    /**
     * 券面总额
     */
    private BigDecimal faceValue;
    /**
     * 结算日期
     */
    private Date settleDate;
    /**
     * 结算方式
     */
    private String  deliveryType;
    /**
     * 回购期限
     */
    private BigDecimal   repoTerm;
    /**
     * 回购利率
     */
    private BigDecimal rate;
    /**
     * 首期结算日
     */
    private Date   vsettleDate;
    /**
     * 到期结算日
     */
    private Date   msettleDate;
    /**
     * 首期结算金额
     */
    private BigDecimal tradeAmt;
    /**
     * 质押券债券
     */
    private String   pledgeFinprodIds;
    /**
     * 质押券折算比例
     */
    private String   pledgeRatios;
    /**
     * 质押券面值
     */
    private String   pledgeFaceValues;

    /**
     * 投管类型
     */
    private String finprodType2;

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getTradeMarket() {
        return tradeMarket;
    }

    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getSettleSpeed() {
        return settleSpeed;
    }

    public void setSettleSpeed(String settleSpeed) {
        this.settleSpeed = settleSpeed;
    }

    public String getSecManageAcctId() {
        return secManageAcctId;
    }

    public void setSecManageAcctId(String secManageAcctId) {
        this.secManageAcctId = secManageAcctId;
    }

    public BigDecimal getTradeYield() {
        return tradeYield;
    }

    public void setTradeYield(BigDecimal tradeYield) {
        this.tradeYield = tradeYield;
    }

    public BigDecimal getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(BigDecimal faceValue) {
        this.faceValue = faceValue;
    }

    public Date getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public BigDecimal getRepoTerm() {
        return repoTerm;
    }

    public void setRepoTerm(BigDecimal repoTerm) {
        this.repoTerm = repoTerm;
    }

    public Date getVsettleDate() {
        return vsettleDate;
    }

    public void setVsettleDate(Date vsettleDate) {
        this.vsettleDate = vsettleDate;
    }

    public Date getMsettleDate() {
        return msettleDate;
    }

    public void setMsettleDate(Date msettleDate) {
        this.msettleDate = msettleDate;
    }

    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    public String getPledgeFinprodIds() {
        return pledgeFinprodIds;
    }

    public void setPledgeFinprodIds(String pledgeFinprodIds) {
        this.pledgeFinprodIds = pledgeFinprodIds;
    }

    public String getPledgeRatios() {
        return pledgeRatios;
    }

    public void setPledgeRatios(String pledgeRatios) {
        this.pledgeRatios = pledgeRatios;
    }

    public String getPledgeFaceValues() {
        return pledgeFaceValues;
    }

    public void setPledgeFaceValues(String pledgeFaceValues) {
        this.pledgeFaceValues = pledgeFaceValues;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getFinprodType2() {
        return finprodType2;
    }

    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2;
    }

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    public String getTotleTradeId() {
        return totleTradeId;
    }

    public void setTotleTradeId(String totleTradeId) {
        this.totleTradeId = totleTradeId;
    }
}
