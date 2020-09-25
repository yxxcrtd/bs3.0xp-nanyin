package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class DisPrams extends BaseEntity {
    /**
     * 查询条件编号
     */
    private String disId;

    /**
     * 查询条件名称(界面展示)
     */
    private String disName;

    /**
     * 查询条件表名
     */
    private String disTable;

    /**
     * 查询条件表主键
     */
    private String disKey;

    /**
     * 查询条件表名称
     */
    private String disVal;

    /**
     * 查询类型
     */
    private String disType;

    /**
     * 查询条件(查询sql后附加的条件)
     */
    private String disParams;

    /**
     * 取得 查询条件编号
     * @return 查询条件编号
     */
    public String getDisId() {
        return disId;
    }

    /**
     * 设置 查询条件编号
     * @param disId 查询条件编号
     */
    public void setDisId(String disId) {
        this.disId = disId == null ? null : disId.trim();
    }

    /**
     * 取得 查询条件名称(界面展示)
     * @return 查询条件名称(界面展示)
     */
    public String getDisName() {
        return disName;
    }

    /**
     * 设置 查询条件名称(界面展示)
     * @param disName 查询条件名称(界面展示)
     */
    public void setDisName(String disName) {
        this.disName = disName == null ? null : disName.trim();
    }

    /**
     * 取得 查询条件表名
     * @return 查询条件表名
     */
    public String getDisTable() {
        return disTable;
    }

    /**
     * 设置 查询条件表名
     * @param disTable 查询条件表名
     */
    public void setDisTable(String disTable) {
        this.disTable = disTable == null ? null : disTable.trim();
    }

    /**
     * 取得 查询条件表主键
     * @return 查询条件表主键
     */
    public String getDisKey() {
        return disKey;
    }

    /**
     * 设置 查询条件表主键
     * @param disKey 查询条件表主键
     */
    public void setDisKey(String disKey) {
        this.disKey = disKey == null ? null : disKey.trim();
    }

    /**
     * 取得 查询条件表名称
     * @return 查询条件表名称
     */
    public String getDisVal() {
        return disVal;
    }

    /**
     * 设置 查询条件表名称
     * @param disVal 查询条件表名称
     */
    public void setDisVal(String disVal) {
        this.disVal = disVal == null ? null : disVal.trim();
    }

    /**
     * 取得 查询类型
     * @return 查询类型
     */
    public String getDisType() {
        return disType;
    }

    /**
     * 设置 查询类型
     * @param disType 查询类型
     */
    public void setDisType(String disType) {
        this.disType = disType == null ? null : disType.trim();
    }

    /**
     * 取得 查询条件(查询sql后附加的条件)
     * @return 查询条件(查询sql后附加的条件)
     */
    public String getDisParams() {
        return disParams;
    }

    /**
     * 设置 查询条件(查询sql后附加的条件)
     * @param disParams 查询条件(查询sql后附加的条件)
     */
    public void setDisParams(String disParams) {
        this.disParams = disParams == null ? null : disParams.trim();
    }
}