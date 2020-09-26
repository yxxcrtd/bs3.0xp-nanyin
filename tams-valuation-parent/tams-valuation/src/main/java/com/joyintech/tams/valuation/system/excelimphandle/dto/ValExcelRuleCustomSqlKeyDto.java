/*
 * 文件名：ValExcelRuleCustomSqlKeyDto.java
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
 * 
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author weipeng
 * @version 1.0 2018年5月9日
 * @see ValExcelRuleCustomSqlKeyDto
 * @since 1.0
 */
public class ValExcelRuleCustomSqlKeyDto extends BaseEntity {
    
    private static final long serialVersionUID = 1L;

    /**
     * 规则代码
     */
    private String ruleId;

    /**
     * 目标字段
     */
    private String targetColumn;

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
}