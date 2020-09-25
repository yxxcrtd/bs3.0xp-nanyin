package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SttExtConfig extends BaseEntity {
    /**
     * 配置代码
     */
    private String configId;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 查询字段
     */
    private String selectColumn;

    /**
     * 查询条件
     */
    private String selectCriteria;

    /**
     * 输入字段
     */
    private String inputColumn;

    /**
     * 区间字段
     */
    private String datePeriod;

    /**
     * 升序字段
     */
    private String ascColumn;

    /**
     * 降序字段
     */
    private String descColumn;

    /**
     * 取得 配置代码
     * @return 配置代码
     */
    public String getConfigId() {
        return configId;
    }

    /**
     * 设置 配置代码
     * @param configId 配置代码
     */
    public void setConfigId(String configId) {
        this.configId = configId == null ? null : configId.trim();
    }

    /**
     * 取得 表名
     * @return 表名
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * 设置 表名
     * @param tableName 表名
     */
    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    /**
     * 取得 查询字段
     * @return 查询字段
     */
    public String getSelectColumn() {
        return selectColumn;
    }

    /**
     * 设置 查询字段
     * @param selectColumn 查询字段
     */
    public void setSelectColumn(String selectColumn) {
        this.selectColumn = selectColumn == null ? null : selectColumn.trim();
    }

    /**
     * 取得 查询条件
     * @return 查询条件
     */
    public String getSelectCriteria() {
        return selectCriteria;
    }

    /**
     * 设置 查询条件
     * @param selectCriteria 查询条件
     */
    public void setSelectCriteria(String selectCriteria) {
        this.selectCriteria = selectCriteria == null ? null : selectCriteria.trim();
    }

    /**
     * 取得 输入字段
     * @return 输入字段
     */
    public String getInputColumn() {
        return inputColumn;
    }

    /**
     * 设置 输入字段
     * @param inputColumn 输入字段
     */
    public void setInputColumn(String inputColumn) {
        this.inputColumn = inputColumn == null ? null : inputColumn.trim();
    }

    /**
     * 取得 区间字段
     * @return 区间字段
     */
    public String getDatePeriod() {
        return datePeriod;
    }

    /**
     * 设置 区间字段
     * @param datePeriod 区间字段
     */
    public void setDatePeriod(String datePeriod) {
        this.datePeriod = datePeriod == null ? null : datePeriod.trim();
    }

    /**
     * 取得 升序字段
     * @return 升序字段
     */
    public String getAscColumn() {
        return ascColumn;
    }

    /**
     * 设置 升序字段
     * @param ascColumn 升序字段
     */
    public void setAscColumn(String ascColumn) {
        this.ascColumn = ascColumn == null ? null : ascColumn.trim();
    }

    /**
     * 取得 降序字段
     * @return 降序字段
     */
    public String getDescColumn() {
        return descColumn;
    }

    /**
     * 设置 降序字段
     * @param descColumn 降序字段
     */
    public void setDescColumn(String descColumn) {
        this.descColumn = descColumn == null ? null : descColumn.trim();
    }
}