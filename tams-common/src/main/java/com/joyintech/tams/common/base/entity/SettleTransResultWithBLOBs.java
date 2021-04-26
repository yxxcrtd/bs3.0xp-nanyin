package com.joyintech.tams.common.base.entity;

public class SettleTransResultWithBLOBs extends SettleTransResult {
    /**
     * 成功信息
     */
    private String successMsg;

    /**
     * 失败信息
     */
    private String failureMsg;

    /**
     * 警告信息
     */
    private String warningMsg;

    /**
     * 原始数据
     */
    private String originalData;

    /**
     * 取得 成功信息
     * @return 成功信息
     */
    public String getSuccessMsg() {
        return successMsg;
    }

    /**
     * 设置 成功信息
     * @param successMsg 成功信息
     */
    public void setSuccessMsg(String successMsg) {
        this.successMsg = successMsg == null ? null : successMsg.trim();
    }

    /**
     * 取得 失败信息
     * @return 失败信息
     */
    public String getFailureMsg() {
        return failureMsg;
    }

    /**
     * 设置 失败信息
     * @param failureMsg 失败信息
     */
    public void setFailureMsg(String failureMsg) {
        this.failureMsg = failureMsg == null ? null : failureMsg.trim();
    }

    /**
     * 取得 警告信息
     * @return 警告信息
     */
    public String getWarningMsg() {
        return warningMsg;
    }

    /**
     * 设置 警告信息
     * @param warningMsg 警告信息
     */
    public void setWarningMsg(String warningMsg) {
        this.warningMsg = warningMsg == null ? null : warningMsg.trim();
    }

    /**
     * 取得 原始数据
     * @return 原始数据
     */
    public String getOriginalData() {
        return originalData;
    }

    /**
     * 设置 原始数据
     * @param originalData 原始数据
     */
    public void setOriginalData(String originalData) {
        this.originalData = originalData == null ? null : originalData.trim();
    }
}