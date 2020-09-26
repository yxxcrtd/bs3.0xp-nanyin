package com.joyintech.tams.valuation.report.common.dto;

public class MstHoliday extends MstHolidayKey {
    /**
     * 是否节假日
     */
    private Long isHoliday;

    /**
     * 取得 是否节假日
     * @return 是否节假日
     */
    public Long getIsHoliday() {
        return isHoliday;
    }

    /**
     * 设置 是否节假日
     * @param isHoliday 是否节假日
     */
    public void setIsHoliday(Long isHoliday) {
        this.isHoliday = isHoliday;
    }
}