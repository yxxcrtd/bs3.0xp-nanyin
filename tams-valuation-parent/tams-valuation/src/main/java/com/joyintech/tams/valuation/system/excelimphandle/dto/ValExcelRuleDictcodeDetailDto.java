/*
 * 文件名：ValExcelRuleDictcodeDetailDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年5月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.system.excelimphandle.dto;

import com.joyintech.framework.base.BaseEntity;

/**
 * Excel导入数据字典转化规则明细DTO
 * @author weipeng
 * @version 1.0 2018年5月9日
 * @see ValExcelRuleDictcodeDetailDto
 * @since 1.0
 */
public class ValExcelRuleDictcodeDetailDto extends BaseEntity {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7774597127396963845L;

    /** 规则明细代码 */
    private String ruleDetailId;

    /** 源值 */
    private String columnValue;

    /** 目标值 */
    private String targetValue;

    /** 工作流代码 */
    private String workflowId;

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
     * 获取 columnValue
     * 
     * @return columnValue.
     */
    public String getColumnValue() {
        return columnValue;
    }

    /**
     * 设置 columnValue
     * 
     * @param columnValue columnValue
     */
    public void setColumnValue(String columnValue) {
        this.columnValue = columnValue;
    }

    /**
     * 获取 targetValue
     * 
     * @return targetValue.
     */
    public String getTargetValue() {
        return targetValue;
    }

    /**
     * 设置 targetValue
     * 
     * @param targetValue targetValue
     */
    public void setTargetValue(String targetValue) {
        this.targetValue = targetValue;
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

}
