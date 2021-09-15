package com.joyintech.tams.common.base.entity;

public class ChbPrdBaseinfoErrordataWithBLOBs extends ChbPrdBaseinfoErrordata {
    /**
     * 募集起始日期
     */
    private String raiseVdate;

    /**
     * 起息日
     */
    private String vdate;

    /**
     * 原始数据
     */
    private String orgData;

    /**
     * 错误信息
     */
    private String errMsg;

    /**
     * 取得 募集起始日期
     * @return 募集起始日期
     */
    public String getRaiseVdate() {
        return raiseVdate;
    }

    /**
     * 设置 募集起始日期
     * @param raiseVdate 募集起始日期
     */
    public void setRaiseVdate(String raiseVdate) {
        this.raiseVdate = raiseVdate == null ? null : raiseVdate.trim();
    }

    /**
     * 取得 起息日
     * @return 起息日
     */
    public String getVdate() {
        return vdate;
    }

    /**
     * 设置 起息日
     * @param vdate 起息日
     */
    public void setVdate(String vdate) {
        this.vdate = vdate == null ? null : vdate.trim();
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