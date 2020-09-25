package com.joyintech.tams.invest.common.dto.period.trdrepoduration;

import java.math.BigDecimal;

/**
 * @program: tams-invest-parent
 * @description: 质押券信息
 * @author: 强成西
 * @create: 2018-10-22 13:42
 **/
public class RepoInfo {

    /**
     * 债券代码
     */
    private String finprodMarketId;

    /**
     * 债券简称
     */
    private String finprodAbbr;

    /**
     * 债券金融产品代码
     */
    private String pledgeFinprodId;

    /**
     * 质押券面总额
     */
    private BigDecimal qty;

    /**
     * 质押率
     */
    private BigDecimal pledgeRatio;

    /**
     * 质押数量
     */
    private BigDecimal pledgeNumber;

    /**
     * 获取 pledgeNumber
     *
     * @return pledgeNumber
     */
    public BigDecimal getPledgeNumber() {
        return pledgeNumber;
    }

    /**
     * 设置 pledgeNumber
     *
     * @param pledgeNumber pledgeNumber
     */
    public void setPledgeNumber(BigDecimal pledgeNumber) {
        this.pledgeNumber = pledgeNumber;
    }

    /**
     * 获取 finprodMarketId
     *
     * @return finprodMarketId
     */
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    /**
     * 设置 finprodMarketId
     *
     * @param finprodMarketId finprodMarketId
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    /**
     * 获取 finprodAbbr
     *
     * @return finprodAbbr
     */
    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    /**
     * 设置 finprodAbbr
     *
     * @param finprodAbbr finprodAbbr
     */
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    /**
     * 获取 pledgeFinprodId
     *
     * @return pledgeFinprodId
     */
    public String getPledgeFinprodId() {
        return pledgeFinprodId;
    }

    /**
     * 设置 pledgeFinprodId
     *
     * @param pledgeFinprodId pledgeFinprodId
     */
    public void setPledgeFinprodId(String pledgeFinprodId) {
        this.pledgeFinprodId = pledgeFinprodId;
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
     * 获取 pledgeRatio
     *
     * @return pledgeRatio
     */
    public BigDecimal getPledgeRatio() {
        return pledgeRatio;
    }

    /**
     * 设置 pledgeRatio
     *
     * @param pledgeRatio pledgeRatio
     */
    public void setPledgeRatio(BigDecimal pledgeRatio) {
        this.pledgeRatio = pledgeRatio;
    }
}
