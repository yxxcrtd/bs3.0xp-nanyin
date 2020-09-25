package com.joyintech.tams.common.base.entity;

public class BokFeeTableSet extends BokFeeTableSetKey {
    /**
     * 场景代码
     */
    private String tableId;

    /**
     * 取得 场景代码
     * @return 场景代码
     */
    public String getTableId() {
        return tableId;
    }

    /**
     * 设置 场景代码
     * @param tableId 场景代码
     */
    public void setTableId(String tableId) {
        this.tableId = tableId == null ? null : tableId.trim();
    }
}