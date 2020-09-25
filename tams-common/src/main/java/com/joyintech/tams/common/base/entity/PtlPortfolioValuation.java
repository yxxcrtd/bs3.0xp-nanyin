package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class PtlPortfolioValuation extends PtlPortfolioValuationKey {
    /**
     * 估值币种
     */
    private String ccy;

    /**
     * 当日资产净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyAssetNetvalue;

    /**
     * 当日资产总值，暂时不用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyAssetValue;

    /**
     * 取得 估值币种
     * @return 估值币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 估值币种
     * @param ccy 估值币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 当日资产净值
     * @return 当日资产净值
     */
    public BigDecimal getTdyAssetNetvalue() {
        return tdyAssetNetvalue;
    }

    /**
     * 设置 当日资产净值
     * @param tdyAssetNetvalue 当日资产净值
     */
    public void setTdyAssetNetvalue(BigDecimal tdyAssetNetvalue) {
        this.tdyAssetNetvalue = tdyAssetNetvalue;
    }

    /**
     * 取得 当日资产总值，暂时不用
     * @return 当日资产总值，暂时不用
     */
    public BigDecimal getTdyAssetValue() {
        return tdyAssetValue;
    }

    /**
     * 设置 当日资产总值，暂时不用
     * @param tdyAssetValue 当日资产总值，暂时不用
     */
    public void setTdyAssetValue(BigDecimal tdyAssetValue) {
        this.tdyAssetValue = tdyAssetValue;
    }
}