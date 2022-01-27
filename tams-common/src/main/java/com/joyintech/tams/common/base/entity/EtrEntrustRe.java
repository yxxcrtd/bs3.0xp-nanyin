package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class EtrEntrustRe extends BaseEntity {
    /**
     * 指令编号
     */
    private String entrustId;

    /**
     * 汇总指令编号
     */
    private String totalEntrustId;

    /**
     * 取得 指令编号
     * @return 指令编号
     */
    public String getEntrustId() {
        return entrustId;
    }

    /**
     * 设置 指令编号
     * @param entrustId 指令编号
     */
    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId == null ? null : entrustId.trim();
    }

    /**
     * 取得 汇总指令编号
     * @return 汇总指令编号
     */
    public String getTotalEntrustId() {
        return totalEntrustId;
    }

    /**
     * 设置 汇总指令编号
     * @param totalEntrustId 汇总指令编号
     */
    public void setTotalEntrustId(String totalEntrustId) {
        this.totalEntrustId = totalEntrustId == null ? null : totalEntrustId.trim();
    }
}