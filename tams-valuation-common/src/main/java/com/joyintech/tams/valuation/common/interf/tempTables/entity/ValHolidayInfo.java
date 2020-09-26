package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;

public class ValHolidayInfo extends BaseEntity {
    /**
     * 交易场所
     */
    private String tradeMarket;

    /**
     * 日期
     */
    private Date cdate;

    /**
     * 是否为节假日
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal isHoliday;

    /**
     * 数据动作标志
     */
    private String dataActionSign;

    /**
     * 取得 交易场所
     * @return 交易场所
     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    /**
     * 设置 交易场所
     * @param tradeMarket 交易场所
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket == null ? null : tradeMarket.trim();
    }

    /**
     * 取得 日期
     * @return 日期
     */
    public Date getCdate() {
        return cdate;
    }

    /**
     * 设置 日期
     * @param cdate 日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    /**
     * 取得 是否为节假日
     * @return 是否为节假日
     */
    public BigDecimal getIsHoliday() {
        return isHoliday;
    }

    /**
     * 设置 是否为节假日
     * @param isHoliday 是否为节假日
     */
    public void setIsHoliday(BigDecimal isHoliday) {
        this.isHoliday = isHoliday;
    }

    /**
     * 取得 数据动作标志
     * @return 数据动作标志
     */
    public String getDataActionSign() {
        return dataActionSign;
    }

    /**
     * 设置 数据动作标志
     * @param dataActionSign 数据动作标志
     */
    public void setDataActionSign(String dataActionSign) {
        this.dataActionSign = dataActionSign == null ? null : dataActionSign.trim();
    }
}