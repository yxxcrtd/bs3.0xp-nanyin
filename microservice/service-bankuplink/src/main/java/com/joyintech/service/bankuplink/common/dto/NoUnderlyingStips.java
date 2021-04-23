package com.joyintech.service.bankuplink.common.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;

/**
 * @author zhangnian
 * @ClassName  抵押品折算率信息
 * @description TODO
 * @date 2021/1/21 0021 19:01
 **/
public class NoUnderlyingStips {

    /*
     * 类型
     */
    private String underlyingStipType;

    /*
     * 利率
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal underlyingStipValue;

    public String getUnderlyingStipType() {
        return underlyingStipType;
    }

    public void setUnderlyingStipType(String underlyingStipType) {
        this.underlyingStipType = underlyingStipType;
    }

    public BigDecimal getUnderlyingStipValue() {
        return underlyingStipValue;
    }

    public void setUnderlyingStipValue(BigDecimal underlyingStipValue) {
        this.underlyingStipValue = underlyingStipValue;
    }
}

