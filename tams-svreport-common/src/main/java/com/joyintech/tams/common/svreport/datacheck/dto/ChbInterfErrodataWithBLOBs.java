package com.joyintech.tams.common.svreport.datacheck.dto;

public class ChbInterfErrodataWithBLOBs extends ChbInterfErrodata {
    /**
     * 原始数据
     */
    private String orgData;

    /**
     * 错误信息
     */
    private String errMsg;

    /**
     * 原始数据
     * @return ORG_DATA 原始数据
     */
    public String getOrgData() {
        return orgData;
    }

    /**
     * 原始数据
     * @param orgData 原始数据
     */
    public void setOrgData(String orgData) {
        this.orgData = orgData == null ? null : orgData.trim();
    }

    /**
     * 错误信息
     * @return ERR_MSG 错误信息
     */
    public String getErrMsg() {
        return errMsg;
    }

    /**
     * 错误信息
     * @param errMsg 错误信息
     */
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg == null ? null : errMsg.trim();
    }
}