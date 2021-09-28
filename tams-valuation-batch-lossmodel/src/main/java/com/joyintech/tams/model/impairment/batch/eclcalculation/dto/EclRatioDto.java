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
}
