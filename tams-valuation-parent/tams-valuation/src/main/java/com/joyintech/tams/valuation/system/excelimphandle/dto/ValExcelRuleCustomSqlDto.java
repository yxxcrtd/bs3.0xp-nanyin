/*
 * 文件名：ValExcelRuleCustomSqlDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年5月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.system.excelimphandle.dto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author weipeng
 * @version 1.0 2018年5月9日
 * @see ValExcelRuleCustomSqlDto
 * @since 1.0
 */
public class ValExcelRuleCustomSqlDto extends ValExcelRuleCustomSqlKeyDto{
    
    private static final long serialVersionUID = 1L;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 自定义SQL
     */
    private String customSql;

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

    /**
     * 取得 自定义SQL
     * @return 自定义SQL
     */
    public String getCustomSql() {
        return customSql;
    }

    /**
     * 设置 自定义SQL
     * @param customSql 自定义SQL
     */
    public void setCustomSql(String customSql) {
        this.customSql = customSql == null ? null : customSql.trim();
    }
}

