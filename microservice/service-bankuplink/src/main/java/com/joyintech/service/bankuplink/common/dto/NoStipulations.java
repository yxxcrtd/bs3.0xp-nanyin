package com.joyintech.service.bankuplink.common.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;

/**
 * @author zhangnian
 * @ClassName 收益率
 * @description TODO
 * @date 2021/1/20 0020 14:21
 **/
public class NoStipulations {

    /*
     * 收益率类型
    */
    private String stipulationType;

    /*
     * 收益率
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal stipulationValue;

    public String getStipulationType() {
        return stipulationType;
    }

    public void setStipulationType(String stipulationType) {
        this.stipulationType = stipulationType;
    }

    public BigDecimal getStipulationValue() {
        return stipulationValue;
    }

    public void setStipulationValue(BigDecimal stipulationValue) {
        this.stipulationValue = stipulationValue;
    }
}

