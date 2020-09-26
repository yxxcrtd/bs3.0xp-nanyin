/*
 * 文件名：ValExcelRuleDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年5月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.system.excelimphandle.dto;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.system.excelimp.dto.ExcelImportRuleDetailKeyDto;

/**
 * Excel导入规则
 * @author weipeng
 * @version 1.0 2018年5月9日
 * @see ValExcelRuleDto
 * @since 1.0
 */
public class ValExcelRuleDto extends ExcelImportRuleDetailKeyDto {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -6725793258536277070L;

    /**
     * 目标表逻辑名
     */
    private String targetTableName;

    /**
     * 目标表导入处理service名
     */
    private String impHandleServiceName;

    /**
     * 目标表主键列表
     */
    private List<String> tableKeyList = new ArrayList<String>();

    /**
     * 目标表字段定义信息列表
     */
    private List<ValTableColumnDefineInfoDto> columnDefineInfoList = new ArrayList<ValTableColumnDefineInfoDto>();

    /**
     * 获取 targetTableName
     *
     * @return targetTableName.
     */
    public String getTargetTableName() {
        return targetTableName;
    }

    /**
     * 设置 targetTableName
     *
     * @param targetTableName targetTableName
     */
    public void setTargetTableName(String targetTableName) {
        this.targetTableName = targetTableName;
    }

    /**
     * 获取 impHandleServiceName
     *
     * @return impHandleServiceName.
     */
    public String getImpHandleServiceName() {
        return impHandleServiceName;
    }

    /**
     * 设置 impHandleServiceName
     *
     * @param impHandleServiceName impHandleServiceName
     */
    public void setImpHandleServiceName(String impHandleServiceName) {
        this.impHandleServiceName = impHandleServiceName;
    }

    /**
     * 获取 tableKeyList
     * @return tableKeyList.
     */
    public List<String> getTableKeyList() {
        return tableKeyList;
    }

    /**
     * 设置 tableKeyList
     * @param tableKeyList tableKeyList
     */
    public void setTableKeyList(List<String> tableKeyList) {
        this.tableKeyList = tableKeyList;
    }

    /**
     * 获取 columnDefineInfoList
     * @return columnDefineInfoList.
     */
    public List<ValTableColumnDefineInfoDto> getColumnDefineInfoList() {
        return columnDefineInfoList;
    }

    /**
     * 设置 columnDefineInfoList
     * @param columnDefineInfoList columnDefineInfoList
     */
    public void setColumnDefineInfoList(List<ValTableColumnDefineInfoDto> columnDefineInfoList) {
        this.columnDefineInfoList = columnDefineInfoList;
    }

}
