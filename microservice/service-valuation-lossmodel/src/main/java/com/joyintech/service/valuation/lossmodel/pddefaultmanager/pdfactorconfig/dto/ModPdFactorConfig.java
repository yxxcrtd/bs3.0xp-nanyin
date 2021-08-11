package com.joyintech.service.valuation.lossmodel.pddefaultmanager.pdfactorconfig.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;

public class ModPdFactorConfig extends ModPdFactorConfigKey{

    /**
     * 年份
     */
    private String vouchYear;
    /**
     * 季度
     */
    private String vouchQuarter;

    /**
     * 乐观因子
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal optimisticFactor;

    /**
     * 基础因子
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal basicFactor;

    /**
     * 悲观因子
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal pessimismFactor;


    public String getVouchYear() {
        return vouchYear;
    }

    public void setVouchYear(String vouchYear) {
        this.vouchYear = vouchYear;
    }

    public String getVouchQuarter() {
        return vouchQuarter;
    }

    public void setVouchQuarter(String vouchQuarter) {
        this.vouchQuarter = vouchQuarter;
    }

    public BigDecimal getOptimisticFactor() {
        return optimisticFactor;
    }

    public void setOptimisticFactor(BigDecimal optimisticFactor) {
        this.optimisticFactor = optimisticFactor;
    }

    public BigDecimal getBasicFactor() {
        return basicFactor;
    }

    public void setBasicFactor(BigDecimal basicFactor) {
        this.basicFactor = basicFactor;
    }

    public BigDecimal getPessimismFactor() {
        return pessimismFactor;
    }

    public void setPessimismFactor(BigDecimal pessimismFactor) {
        this.pessimismFactor = pessimismFactor;
    }


}
