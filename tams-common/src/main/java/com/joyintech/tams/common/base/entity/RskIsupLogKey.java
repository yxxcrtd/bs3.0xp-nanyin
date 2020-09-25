package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RskIsupLogKey extends BaseEntity {
    /**
     * 日志编号
     */
    private String logId;

    /**
     * 机能代码
     */
    private String functionId;

    /**
     * 取得 日志编号
     * @return 日志编号
     */
    public String getLogId() {
        return logId;
    }

    /**
     * 设置 日志编号
     * @param logId 日志编号
     */
    public void setLogId(String logId) {
        this.logId = logId == null ? null : logId.trim();
    }

    /**
     * 取得 机能代码
     * @return 机能代码
     */
    public String getFunctionId() {
        return functionId;
    }

    /**
     * 设置 机能代码
     * @param functionId 机能代码
     */
    public void setFunctionId(String functionId) {
        this.functionId = functionId == null ? null : functionId.trim();
    }
}