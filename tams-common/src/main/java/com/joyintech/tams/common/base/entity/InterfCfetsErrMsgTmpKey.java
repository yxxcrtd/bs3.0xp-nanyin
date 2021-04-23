package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class InterfCfetsErrMsgTmpKey extends BaseEntity {
    /**
     * 错误码
     */
    private String errCode;

    /**
     * 错误信息
     */
    private String errMsg;

    /**
     * 错误应用类型
     */
    private String errType;

    /**
     * 取得 错误码
     * @return 错误码
     */
    public String getErrCode() {
        return errCode;
    }

    /**
     * 设置 错误码
     * @param errCode 错误码
     */
    public void setErrCode(String errCode) {
        this.errCode = errCode == null ? null : errCode.trim();
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

    /**
     * 取得 错误应用类型
     * @return 错误应用类型
     */
    public String getErrType() {
        return errType;
    }

    /**
     * 设置 错误应用类型
     * @param errType 错误应用类型
     */
    public void setErrType(String errType) {
        this.errType = errType == null ? null : errType.trim();
    }
}