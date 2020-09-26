package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.joyintech.framework.base.BaseEntity;

public class ValTradeCalendarInfo extends BaseEntity {
    /**
     * 交易日历编码
     */
    private String calNum;

    /**
     * 日历名称
     */
    private String calName;

    /**
     * 关联交易所
     */
    private String calAgency;

    /**
     * 数据动作标志
     */
    private String dataActionSign;

    /**
     * 取得 交易日历编码
     * @return 交易日历编码
     */
    public String getCalNum() {
        return calNum;
    }

    /**
     * 设置 交易日历编码
     * @param calNum 交易日历编码
     */
    public void setCalNum(String calNum) {
        this.calNum = calNum == null ? null : calNum.trim();
    }

    /**
     * 取得 日历名称
     * @return 日历名称
     */
    public String getCalName() {
        return calName;
    }

    /**
     * 设置 日历名称
     * @param calName 日历名称
     */
    public void setCalName(String calName) {
        this.calName = calName == null ? null : calName.trim();
    }

    /**
     * 取得 关联交易所
     * @return 关联交易所
     */
    public String getCalAgency() {
        return calAgency;
    }

    /**
     * 设置 关联交易所
     * @param calAgency 关联交易所
     */
    public void setCalAgency(String calAgency) {
        this.calAgency = calAgency == null ? null : calAgency.trim();
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