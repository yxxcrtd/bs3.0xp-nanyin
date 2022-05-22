package com.joyintech.tams.common.base.entity;

import java.util.Date;

public class ChbInvestTradeErrordataWithBLOBs extends ChbInvestTradeErrordata {
    /**
     * 行内资产/负债编码
     */
    private String assetCode;

    /**
     * 交易日
     */
    private Date dealdate;

    /**
     * 原始数据
     */
    private String orgData;

    /**
     * 错误信息
     */
    private String errMsg;
    /**
     * 产品登记编码
     */
    private String regCode;
    /**
     * 交易对手方
     */
    private String tradeCounter;

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
    public Date getDealdate() {
        return dealdate;
    }

    /**
     * 设置 交易日
     * @param dealdate 交易日
     */
    public void setDealdate(Date dealdate) {
        this.dealdate = dealdate == null ? null : dealdate;
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

    public String getRegCode() {
        return regCode;
    }

    public void setRegCode(String regCode) {
        this.regCode = regCode;
    }

    public String getTradeCounter() {
        return tradeCounter;
    }

    public void setTradeCounter(String tradeCounter) {
        this.tradeCounter = tradeCounter;
    }
}