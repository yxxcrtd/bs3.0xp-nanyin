package com.joyintech.tams.common.base.entity;

public class ChbInvestTradeErrordataWithBLOBs extends ChbInvestTradeErrordata {
    /**
     * 行内资产/负债编码
     */
    private String assetCode;

    /**
     * 交易日
     */
    private String dealdate;

    /**
     * 原始数据
     */
    private String orgData;

    /**
     * 错误信息
     */
    private String errMsg;

    /**
     * 取得 行内资产/负债编码
     * @return 行内资产/负债编码
     */
    public String getAssetCode() {
        return assetCode;
    }

    /**
     * 设置 行内资产/负债编码
     * @param assetCode 行内资产/负债编码
     */
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode == null ? null : assetCode.trim();
    }

    /**
     * 取得 交易日
     * @return 交易日
     */
    public String getDealdate() {
        return dealdate;
    }

    /**
     * 设置 交易日
     * @param dealdate 交易日
     */
    public void setDealdate(String dealdate) {
        this.dealdate = dealdate == null ? null : dealdate.trim();
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