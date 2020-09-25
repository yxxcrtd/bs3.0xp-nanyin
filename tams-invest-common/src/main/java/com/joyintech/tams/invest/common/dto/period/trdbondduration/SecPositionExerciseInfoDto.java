package com.joyintech.tams.invest.common.dto.period.trdbondduration;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;

/**
 * @program: 2.1.2
 * @description:
 * @author: 强成西
 * @create: 2020-01-19 14:41
 **/
public class SecPositionExerciseInfoDto extends BaseEntity {

    private String portfolioId;

    private String portfolioName;

    /**
     * 证券账户/通道
     */
    private String secAcctId;

    /**
     * 投资目的
     */
    private String invAim;

    /**
     * 持有面额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal qty;

    /**
     * 净价总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cPriceAmt;

    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal channelFee;

    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal settleAmt;

    /**
     * 获取 portfolioId
     *
     * @return portfolioId
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 portfolioId
     *
     * @param portfolioId portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    /**
     * 获取 portfolioName
     *
     * @return portfolioName
     */
    public String getPortfolioName() {
        return portfolioName;
    }

    /**
     * 设置 portfolioName
     *
     * @param portfolioName portfolioName
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    /**
     * 获取 secAcctId
     *
     * @return secAcctId
     */
    public String getSecAcctId() {
        return secAcctId;
    }

    /**
     * 设置 secAcctId
     *
     * @param secAcctId secAcctId
     */
    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }

    /**
     * 获取 invAim
     *
     * @return invAim
     */
    public String getInvAim() {
        return invAim;
    }

    /**
     * 设置 invAim
     *
     * @param invAim invAim
     */
    public void setInvAim(String invAim) {
        this.invAim = invAim;
    }

    /**
     * 获取 qty
     *
     * @return qty
     */
    public BigDecimal getQty() {
        return qty;
    }

    /**
     * 设置 qty
     *
     * @param qty qty
     */
    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    /**
     * 获取 cPriceAmt
     *
     * @return cPriceAmt
     */
    public BigDecimal getcPriceAmt() {
        return cPriceAmt;
    }

    /**
     * 设置 cPriceAmt
     *
     * @param cPriceAmt cPriceAmt
     */
    public void setcPriceAmt(BigDecimal cPriceAmt) {
        this.cPriceAmt = cPriceAmt;
    }

    /**
     * 获取 channelFee
     *
     * @return channelFee
     */
    public BigDecimal getChannelFee() {
        return channelFee;
    }

    /**
     * 设置 channelFee
     *
     * @param channelFee channelFee
     */
    public void setChannelFee(BigDecimal channelFee) {
        this.channelFee = channelFee;
    }

    /**
     * 获取 settleAmt
     *
     * @return settleAmt
     */
    public BigDecimal getSettleAmt() {
        return settleAmt;
    }

    /**
     * 设置 settleAmt
     *
     * @param settleAmt settleAmt
     */
    public void setSettleAmt(BigDecimal settleAmt) {
        this.settleAmt = settleAmt;
    }
}
