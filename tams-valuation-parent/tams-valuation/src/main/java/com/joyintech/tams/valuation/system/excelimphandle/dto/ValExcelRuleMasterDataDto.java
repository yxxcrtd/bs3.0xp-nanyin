/*
 * 文件名：ValExcelRuleMasterDataDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年5月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.system.excelimphandle.dto;

/**
 * 
 * ValExcelRuleMaster MASTER表数据结果（映射表）
 * 通过master查询数据规则：如excel中只给了产品代码，但是入库需要账套代码，这是就需要通过master表中的映射找到产品代码对应其他表的账套代码
 * @author weipeng
 * @version 1.0 2018年5月28日
 * @see ValExcelRuleMasterDataDto
 * @since 1.0
 */
public class ValExcelRuleMasterDataDto extends ValExcelRuleMasterDataKeyDto {

    private static final long serialVersionUID = 1L;

    /**
     * 列名称
     */
    private String columnName;

    /**
     * 基础数据表名
     */
    private String masterDataTable;

    /**
     * 基础数据表ID项目物理名
     */
    private String masterDataIdColumn;

    /**
     * 基础数据表NAME项目物理名
     */
    private String masterDataNameColumn;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 取得 列名称
     * 
     * @return 列名称
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * 设置 列名称
     * 
     * @param columnName
     *            列名称
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName == null ? null : columnName.trim();
    }

    /**
     * 取得 基础数据表名
     * 
     * @return 基础数据表名
     */
    public String getMasterDataTable() {
        return masterDataTable;
    }

    /**
     * 设置 基础数据表名
     * 
     * @param masterDataTable
     *            基础数据表名
     */
    public void setMasterDataTable(String masterDataTable) {
        this.masterDataTable = masterDataTable == null ? null : masterDataTable.trim();
    }

    /**
     * 取得 基础数据表ID项目物理名
     * 
     * @return 基础数据表ID项目物理名
     */
    public String getMasterDataIdColumn() {
        return masterDataIdColumn;
    }

    /**
     * 设置 基础数据表ID项目物理名
     * 
     * @param masterDataIdColumn
     *            基础数据表ID项目物理名
     */
    public void setMasterDataIdColumn(String masterDataIdColumn) {
        this.masterDataIdColumn = masterDataIdColumn == null ? null : masterDataIdColumn.trim();
    }

    /**
     * 取得 基础数据表NAME项目物理名
     * 
     * @return 基础数据表NAME项目物理名
     */
    public String getMasterDataNameColumn() {
        return masterDataNameColumn;
    }

    /**
     * 设置 基础数据表NAME项目物理名
     * 
     * @param masterDataNameColumn
     *            基础数据表NAME项目物理名
     */
    public void setMasterDataNameColumn(String masterDataNameColumn) {
        this.masterDataNameColumn = masterDataNameColumn == null ? null
                : masterDataNameColumn.trim();
    }

    /**
     * 取得 工作流代码
     * 
     * @return 工作流代码
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 工作流代码
     * 
     * @param workflowId
     *            工作流代码
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }
}