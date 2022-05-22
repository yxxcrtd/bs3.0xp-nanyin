package com.joyintech.tams.common.base.entity;

import java.util.Date;

public class ChbPrdBaseinfoErrordataWithBLOBs extends ChbPrdBaseinfoErrordata {
    /**
     * 募集起始日期
     */
    private Date raiseVdate;

    /**
     * 起息日
     */
    private Date vdate;

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
     * 客户类型
     */
    private String customerType;
    /**
     * 发行状态
     */
    private String issuingStatus;
    /**
     * 机构代码
     */
    private String orgCode;
    /**
     * 部门代码
     */
    private String deptCode;

    private String statusValid;


    /**
     * 取得 募集起始日期
     * @return 募集起始日期
     */
    public Date getRaiseVdate() {
        return raiseVdate;
    }

    /**
     * 设置 募集起始日期
     * @param raiseVdate 募集起始日期
     */
    public void setRaiseVdate(Date raiseVdate) {
        this.raiseVdate = raiseVdate == null ? null : raiseVdate;
    }

    /**
     * 取得 起息日
     * @return 起息日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 起息日
     * @param vdate 起息日
     */
    public void setVdate(Date vdate) {
        this.vdate = vdate == null ? null : vdate;
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

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getIssuingStatus() {
        return issuingStatus;
    }

    public void setIssuingStatus(String issuingStatus) {
        this.issuingStatus = issuingStatus;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getStatusValid() {
        return statusValid;
    }

    public void setStatusValid(String statusValid) {
        this.statusValid = statusValid;
    }
}