/*
 * 文件名：ValExcelRuleDictcodeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年5月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.system.excelimphandle.dto;

import java.util.List;

import com.joyintech.framework.base.BaseEntity;


/**
 * Excel导入数据字典转化规则DTO
 * 需要将excel中的数据经过数据字典转换，例如excel中的产品类型给的是汉字或者数字，
 * 需要转换成对应的T01，这就要查询数据字典表，转换成对应的值入库
 * @author weipeng
 * @version 1.0 2018年5月9日
 * @see ValExcelRuleDictcodeDto
 * @since 1.0
 */
public class ValExcelRuleDictcodeDto extends BaseEntity {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -4645013945786293849L;

    /** 规则代码 */
    private String ruleId;

    /** 列序号 */
    private String columnNo;

    /** 列名称 */
    private String columnName;

    /** 目标字段 */
    private String targetColumn;

    /** 规则明细代码 */
    private String ruleDetailId;

    /** 工作流代码 */
    private String workflowId;

    /** Excel导入数据字典转化规则明细DTO */
    private List<ValExcelRuleDictcodeDetailDto> detailDtos;

    /**
     * 获取 ruleId
     * 
     * @return ruleId.
     */
    public String getRuleId() {
        return ruleId;
    }

    /**
     * 设置 ruleId
     * 
     * @param ruleId ruleId
     */
    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    /**
     * 获取 columnNo
     * 
     * @return columnNo.
     */
    public String getColumnNo() {
        return columnNo;
    }

    /**
     * 设置 columnNo
     * 
     * @param columnNo columnNo
     */
    public void setColumnNo(String columnNo) {
        this.columnNo = columnNo;
    }

    /**
     * 获取 columnName
     * 
     * @return columnName.
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * 设置 columnName
     * 
     * @param columnName columnName
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    /**
     * 获取 targetColumn
     * 
     * @return targetColumn.
     */
    public String getTargetColumn() {
        return targetColumn;
    }

    /**
     * 设置 targetColumn
     * 
     * @param targetColumn targetColumn
     */
    public void setTargetColumn(String targetColumn) {
        this.targetColumn = targetColumn;
    }

    /**
     * 获取 ruleDetailId
     * 
     * @return ruleDetailId.
     */
    public String getRuleDetailId() {
        return ruleDetailId;
    }

    /**
     * 设置 ruleDetailId
     * 
     * @param ruleDetailId ruleDetailId
     */
    public void setRuleDetailId(String ruleDetailId) {
        this.ruleDetailId = ruleDetailId;
    }

    /**
     * 获取 workflowId
     * 
     * @return workflowId.
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 workflowId
     * 
     * @param workflowId workflowId
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    /**
     * 获取 detailDtos
     * 
     * @return detailDtos.
     */
    public List<ValExcelRuleDictcodeDetailDto> getDetailDtos() {
        return detailDtos;
    }

    /**
     * 设置 detailDtos
     * 
     * @param detailDtos detailDtos
     */
    public void setDetailDtos(List<ValExcelRuleDictcodeDetailDto> detailDtos) {
        this.detailDtos = detailDtos;
    }

}
