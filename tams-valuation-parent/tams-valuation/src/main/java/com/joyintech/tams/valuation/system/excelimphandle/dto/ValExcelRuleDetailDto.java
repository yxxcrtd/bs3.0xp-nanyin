/*
 * 文件名：ValExcelRuleDetailDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年5月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.system.excelimphandle.dto;

import com.joyintech.system.excelimp.dto.ExcelImportRuleDetailKeyDto;

/**
 * @author weipeng
 * @version 1.0 2018年5月9日
 * @see ValExcelRuleDetailDto
 * @since 1.0
 */
public class ValExcelRuleDetailDto extends ExcelImportRuleDetailKeyDto{
    
    private static final long serialVersionUID = 1L;

    /**
     * 规则代码
     */
    private String ruleId;

    /**
     * 列序号
     */
    private String columnNo;

    /**
     * 列名称
     */
    private String columnName;

    /**
     * 目标字段
     */
    private String targetColumn;

    /**
     * 工作流代码
     */
    private String workflowId;


    /**
     * 取得 规则代码
     * @return 规则代码
     */
    public String getRuleId() {
        return ruleId;
    }

    /**
     * 设置 规则代码
     * @param ruleId 规则代码
     */
    public void setRuleId(String ruleId) {
        this.ruleId = ruleId == null ? null : ruleId.trim();
    }

    /**
     * 取得 列序号
     * @return 列序号
     */
    public String getColumnNo() {
        return columnNo;
    }

    /**
     * 设置 列序号
     * @param columnNo 列序号
     */
    public void setColumnNo(String columnNo) {
        this.columnNo = columnNo == null ? null : columnNo.trim();
    }

    /**
     * 取得 列名称
     * @return 列名称
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * 设置 列名称
     * @param columnName 列名称
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName == null ? null : columnName.trim();
    }

    /**
     * 取得 目标字段
     * @return 目标字段
     */
    public String getTargetColumn() {
        return targetColumn;
    }

    /**
     * 设置 目标字段
     * @param targetColumn 目标字段
     */
    public void setTargetColumn(String targetColumn) {
        this.targetColumn = targetColumn == null ? null : targetColumn.trim();
    }

    /**
     * 取得 工作流代码
     * @return 工作流代码
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 工作流代码
     * @param workflowId 工作流代码
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }


}
