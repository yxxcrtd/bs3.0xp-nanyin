/*
 * 文件名：ValExcelImpFileInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年5月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.system.excelimphandle.dto;

import java.io.Serializable;

/**
 * 表字段定义信息
 * @author weipeng
 * @version 1.0 2018年5月9日
 * @see ValTableColumnDefineInfoDto
 * @since 1.0
 */
public class ValTableColumnDefineInfoDto implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 表名
     */
    private String tabName;

    /**
     * 列名
     */
    private String colName;

    /**
     * 列序号
     */
    private Integer colNo;

    /**
     * 列类型
     */
    private String typeName;

    /**
     * 列长度
     */
    private Integer length;

    /**
     * 列长度(小数位)
     */
    private Integer scale;

    /**
     * 是否为空（Y N）
     */
    private String nulls;

    /**
     * 列注释
     */
    private String remarks;

    /**
     * 获取 tabName
     *
     * @return tabName.
     */
    public String getTabName() {
        return tabName;
    }

    /**
     * 设置 tabName
     *
     * @param tabName tabName
     */
    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    /**
     * 获取 colName
     *
     * @return colName.
     */
    public String getColName() {
        return colName;
    }

    /**
     * 设置 colName
     *
     * @param colName colName
     */
    public void setColName(String colName) {
        this.colName = colName;
    }

    /**
     * 获取 colNo
     *
     * @return colNo.
     */
    public Integer getColNo() {
        return colNo;
    }

    /**
     * 设置 colNo
     *
     * @param colNo colNo
     */
    public void setColNo(Integer colNo) {
        this.colNo = colNo;
    }

    /**
     * 获取 typeName
     *
     * @return typeName.
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * 设置 typeName
     *
     * @param typeName typeName
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * 获取 length
     *
     * @return length.
     */
    public Integer getLength() {
        return length;
    }

    /**
     * 设置 length
     *
     * @param length length
     */
    public void setLength(Integer length) {
        this.length = length;
    }

    /**
     * 获取 scale
     *
     * @return scale.
     */
    public Integer getScale() {
        return scale;
    }

    /**
     * 设置 scale
     *
     * @param scale scale
     */
    public void setScale(Integer scale) {
        this.scale = scale;
    }

    /**
     * 获取 nulls
     *
     * @return nulls.
     */
    public String getNulls() {
        return nulls;
    }

    /**
     * 设置 nulls
     *
     * @param nulls nulls
     */
    public void setNulls(String nulls) {
        this.nulls = nulls;
    }

    /**
     * 获取 remarks
     *
     * @return remarks.
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置 remarks
     *
     * @param remarks remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
