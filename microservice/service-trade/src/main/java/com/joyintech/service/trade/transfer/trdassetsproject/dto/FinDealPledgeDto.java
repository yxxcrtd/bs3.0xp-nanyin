package com.joyintech.service.trade.transfer.trdassetsproject.dto;

import com.joyintech.tams.common.base.entity.FinDealPledge;

import java.math.BigDecimal;

public class FinDealPledgeDto extends FinDealPledge {
    /**
     * 债券代码
     */
    private String finprodAbbr;
    /**
     * 质押全面总额
     */
    private BigDecimal pledgePrin;
    /**
     * 剩余未拆分质押券面总额
     */
    private BigDecimal avaPledgePrin;
    /**
     * 产品组合可质押券面总额
     */
    private BigDecimal prdPledgePrin;
    /**
     * 本次拆分指定质押券面总额
     */
    private BigDecimal desiPledgePrin;

    /**
     * 获取 avaPledgePrin.
     *
     * @return avaPledgePrin
     */
    public BigDecimal getAvaPledgePrin() {
        return avaPledgePrin;
    }

    /**
     * 设置 avaPledgePrin.
     *
     * @param avaPledgePrin avaPledgePrin
     */
    public void setAvaPledgePrin(BigDecimal avaPledgePrin) {
        this.avaPledgePrin = avaPledgePrin;
    }

    /**
     * 获取 prdPledgePrin.
     *
     * @return prdPledgePrin
     */
    public BigDecimal getPrdPledgePrin() {
        return prdPledgePrin;
    }

    /**
     * 设置 prdPledgePrin.
     *
     * @param prdPledgePrin prdPledgePrin
     */
    public void setPrdPledgePrin(BigDecimal prdPledgePrin) {
        this.prdPledgePrin = prdPledgePrin;
    }

    /**
     * 获取 desiPledgePrin.
     *
     * @return desiPledgePrin
     */
    public BigDecimal getDesiPledgePrin() {
        return desiPledgePrin;
    }

    /**
     * 设置 desiPledgePrin.
     *
     * @param desiPledgePrin desiPledgePrin
     */
    public void setDesiPledgePrin(BigDecimal desiPledgePrin) {
        this.desiPledgePrin = desiPledgePrin;
    }

    /**
     * 获取 finprodAbbr.
     *
     * @return finprodAbbr
     */
    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    /**
     * 设置 finprodAbbr.
     *
     * @param finprodAbbr finprodAbbr
     */
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    /**
     * 获取 pledgePrin.
     *
     * @return pledgePrin
     */
    public BigDecimal getPledgePrin() {
        return pledgePrin;
    }

    /**
     * 设置 pledgePrin.
     *
     * @param pledgePrin pledgePrin
     */
    public void setPledgePrin(BigDecimal pledgePrin) {
        this.pledgePrin = pledgePrin;
    }
}
