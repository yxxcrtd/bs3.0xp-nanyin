package com.joyintech.tams.common.base.entity;

public class RepCashYieldErrordataWithBLOBs extends RepCashYieldErrordata {
    /**
     * 启用日
     */
    private String startDate;

    /**
     * 费率
     */
    private String feeRate;

    /**
     * 原始数据
     */
    private String orgData;

    /**
     * 错误信息
     */
    private String errMsg;

    /**
     * 取得 启用日
     * @return 启用日
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * 设置 启用日
     * @param startDate 启用日
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate == null ? null : startDate.trim();
    }

    /**
     * 取得 费率
     * @return 费率
     */
    public String getFeeRate() {
        return feeRate;
    }

    /**
     * 设置 费率
     * @param feeRate 费率
     */
    public void setFeeRate(String feeRate) {
        this.feeRate = feeRate == null ? null : feeRate.trim();
    }

    /**
     * 取得 原始数据
     * @return 原始数据
     */
    public String getOrgData() {
        return orgData;
    }

    /**
     * 设置 原始数据
     * @param orgData 原始数据
     */
    public void setOrgData(String orgData) {
        this.orgData = orgData == null ? null : orgData.trim();
    }

    /**
     * 取得 错误信息
     * @return 错误信息
     */
    public String getErrMsg() {
        return errMsg;
    }

    /**
     * 设置 错误信息
     * @param errMsg 错误信息
     */
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg == null ? null : errMsg.trim();
    }
}