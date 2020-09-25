package com.joyintech.tams.common.base.entity;

public class TrdAsynHandleStatus extends TrdAsynHandleStatusKey {
    /**
     * 处理状态（01：待处理 02：处理完成）
     */
    private String handleStatus;

    /**
     * 取得 处理状态（01：待处理 02：处理完成）
     * @return 处理状态（01：待处理 02：处理完成）
     */
    public String getHandleStatus() {
        return handleStatus;
    }

    /**
     * 设置 处理状态（01：待处理 02：处理完成）
     * @param handleStatus 处理状态（01：待处理 02：处理完成）
     */
    public void setHandleStatus(String handleStatus) {
        this.handleStatus = handleStatus == null ? null : handleStatus.trim();
    }
}