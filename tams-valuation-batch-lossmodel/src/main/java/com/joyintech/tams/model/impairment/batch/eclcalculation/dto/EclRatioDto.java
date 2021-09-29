package com.joyintech.tams.model.impairment.batch.eclcalculation.dto;

import java.math.BigDecimal;

public class EclRatioDto {

    /**
     * ECL比例
     */
    private BigDecimal eclRatio;

    /**
     * 减值阶段
     */
    private String finStage;

    /**
     * PD
     */
    private BigDecimal pd;

    /**
     * LGD
     */
    private BigDecimal lgd;

    /**
     * @return ECL比例
     */
    public BigDecimal getEclRatio() {
        return eclRatio;
    }

    /**
     * 设置 ECL比例
     *
     * @param eclRatio ECL比例
     */
    public void setEclRatio(BigDecimal eclRatio) {
        this.eclRatio = eclRatio;
    }

    /**
     * @return 减值阶段
     */
    public String getFinStage() {
        return finStage;
    }

    /**
     * 设置 减值阶段
     *
     * @param finStage 减值阶段
     */
    public void setFinStage(String finStage) {
        this.finStage = finStage;
    }

    /**
     * @return pd
     */
    public BigDecimal getPd() {
        return pd;
    }

    /**
     * 设置 pd
     *
     * @param pd pd
     */
    public void setPd(BigDecimal pd) {
        this.pd = pd;
    }

    /**
     * @return lgd
     */
    public BigDecimal getLgd() {
        return lgd;
    }

    /**
     * 设置 lgd
     *
     * @param lgd lgd
     */
    public void setLgd(BigDecimal lgd) {
        this.lgd = lgd;
    }
}
