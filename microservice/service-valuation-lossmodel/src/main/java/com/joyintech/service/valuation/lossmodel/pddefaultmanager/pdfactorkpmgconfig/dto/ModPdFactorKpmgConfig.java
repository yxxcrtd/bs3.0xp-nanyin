package com.joyintech.service.valuation.lossmodel.pddefaultmanager.pdfactorkpmgconfig.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;

public class ModPdFactorKpmgConfig extends ModPdFactorKpmgConfigKey {

    /**
     * 年份
     */
    private String vouchYear;

    /**
     * 综合因素
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal combinedFactor;

    public String getVouchYear() {
        return vouchYear;
    }

    public void setVouchYear(String vouchYear) {
        this.vouchYear = vouchYear;
    }

    public BigDecimal getCombinedFactor() {
        return combinedFactor;
    }

    public void setCombinedFactor(BigDecimal combinedFactor) {
        this.combinedFactor = combinedFactor;
    }
}
