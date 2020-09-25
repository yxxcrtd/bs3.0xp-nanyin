package com.joyintech.tams.common.base.entity;

public class ChbReportCheckWithBLOBs extends ChbReportCheck {
    /**
     * 错误信息项
     */
    private String errorInfo;

    /**
     * 警告信息项
     */
    private String warnInfo;

    /**
     * 取得 错误信息项
     * @return 错误信息项
     */
    public String getErrorInfo() {
        return errorInfo;
    }

    /**
     * 设置 错误信息项
     * @param errorInfo 错误信息项
     */
    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo == null ? null : errorInfo.trim();
    }

    /**
     * 取得 警告信息项
     * @return 警告信息项
     */
    public String getWarnInfo() {
        return warnInfo;
    }

    /**
     * 设置 警告信息项
     * @param warnInfo 警告信息项
     */
    public void setWarnInfo(String warnInfo) {
        this.warnInfo = warnInfo == null ? null : warnInfo.trim();
    }
}