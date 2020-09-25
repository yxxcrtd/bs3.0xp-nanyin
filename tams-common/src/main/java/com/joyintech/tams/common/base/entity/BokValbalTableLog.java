package com.joyintech.tams.common.base.entity;

public class BokValbalTableLog extends BokValbalTableLogKey {
    /**
     * 生成状态
     */
    private String buildStatus;

    /**
     * 错误类型
     */
    private String errorType;

    /**
     * 错误信息
     */
    private String errorInfo;

    /**
     * 取得 生成状态
     * @return 生成状态
     */
    public String getBuildStatus() {
        return buildStatus;
    }

    /**
     * 设置 生成状态
     * @param buildStatus 生成状态
     */
    public void setBuildStatus(String buildStatus) {
        this.buildStatus = buildStatus == null ? null : buildStatus.trim();
    }

    /**
     * 取得 错误类型
     * @return 错误类型
     */
    public String getErrorType() {
        return errorType;
    }

    /**
     * 设置 错误类型
     * @param errorType 错误类型
     */
    public void setErrorType(String errorType) {
        this.errorType = errorType == null ? null : errorType.trim();
    }

    /**
     * 取得 错误信息
     * @return 错误信息
     */
    public String getErrorInfo() {
        return errorInfo;
    }

    /**
     * 设置 错误信息
     * @param errorInfo 错误信息
     */
    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo == null ? null : errorInfo.trim();
    }
}