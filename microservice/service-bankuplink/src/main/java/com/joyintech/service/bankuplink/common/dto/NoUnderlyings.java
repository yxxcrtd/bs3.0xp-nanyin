package com.joyintech.service.bankuplink.common.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author zhangnian
 * @ClassName  抵押品信息
 * @description TODO
 * @date 2021/1/21 0021 18:58
 **/
public class NoUnderlyings {

    /*
     * 券面总额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal underlyingQty;

    /*
     * 债券代码
     */
    private String underlyingSecurityID;

    /*
     * 债券名称
     */
    private String underlyingSymbol;

    /*
     * 折算率
     */
    private List<NoUnderlyingStips> noUnderlyingStips;

    public BigDecimal getUnderlyingQty() {
        return underlyingQty;
    }

    public void setUnderlyingQty(BigDecimal underlyingQty) {
        this.underlyingQty = underlyingQty;
    }

    public String getUnderlyingSecurityID() {
        return underlyingSecurityID;
    }

    public void setUnderlyingSecurityID(String underlyingSecurityID) {
        this.underlyingSecurityID = underlyingSecurityID;
    }

    public String getUnderlyingSymbol() {
        return underlyingSymbol;
    }

    public void setUnderlyingSymbol(String underlyingSymbol) {
        this.underlyingSymbol = underlyingSymbol;
    }

    public List<NoUnderlyingStips> getNoUnderlyingStips() {
        return noUnderlyingStips;
    }

    public void setNoUnderlyingStips(List<NoUnderlyingStips> noUnderlyingStips) {
        this.noUnderlyingStips = noUnderlyingStips;
    }
}

