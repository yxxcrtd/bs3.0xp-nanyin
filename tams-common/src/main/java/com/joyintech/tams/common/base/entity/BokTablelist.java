package com.joyintech.tams.common.base.entity;

public class BokTablelist extends BokTablelistKey {
    /**
     * 会计场景名称
     */
    private String tableName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 会计场景名称
     * @return 会计场景名称
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * 设置 会计场景名称
     * @param tableName 会计场景名称
     */
    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}