package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class ChbMktHolidayKey extends BaseEntity {
    /**
     * 日期
     */
    private Date busiDate;

    /**
     * 交易市场
     */
    private String marketCode;

    /**
     * 取得 日期
     * @return 日期
     */
    public Date getBusiDate() {
        return busiDate;
    }

    /**
     * 设置 日期
     * @param busiDate 日期
     */
    public void setBusiDate(Date busiDate) {
        this.busiDate = busiDate;
    }

    /**
     * 取得 交易市场
     * @return 交易市场
     */
    public String getMarketCode() {
        return marketCode;
    }

    /**
     * 设置 交易市场
     * @param marketCode 交易市场
     */
    public void setMarketCode(String marketCode) {
        this.marketCode = marketCode == null ? null : marketCode.trim();
    }
}