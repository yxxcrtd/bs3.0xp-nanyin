package com.joyintech.tams.common.base.entity;

public class MstHoliday extends MstHolidayKey {
    /**
     * 是否节假日，0：工作日，1：节假日
     */
    private Long isHoliday;

    /**
     * 取得 是否节假日，0：工作日，1：节假日
     * @return 是否节假日，0：工作日，1：节假日
     */
    public Long getIsHoliday() {
        return isHoliday;
    }

    /**
     * 设置 是否节假日，0：工作日，1：节假日
     * @param isHoliday 是否节假日，0：工作日，1：节假日
     */
    public void setIsHoliday(Long isHoliday) {
        this.isHoliday = isHoliday;
    }
}