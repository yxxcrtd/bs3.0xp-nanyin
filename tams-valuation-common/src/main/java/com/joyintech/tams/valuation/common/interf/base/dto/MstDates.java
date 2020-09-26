package com.joyintech.tams.valuation.common.interf.base.dto;

import com.joyintech.framework.base.BaseEntity;

public class MstDates extends BaseEntity {
    /**
     * 日期
     */
    private String dates;

    /**
     * 取得 日期
     * @return 日期
     */
    public String getDates() {
        return dates;
    }

    /**
     * 设置 日期
     * @param dates 日期
     */
    public void setDates(String dates) {
        this.dates = dates == null ? null : dates.trim();
    }
}