/*
 * 文件名：RiskIndConditionLine.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年8月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.common.redisinit.dto;

import java.math.BigDecimal;

/**
 * 监督指标条件阈值Redis初始化-Entity
 * 
 * @author zhangrui1
 * @version 1.0 2020年8月31日
 * @see RiskIndConditionLine
 * @since 1.0
 */
public class RiskIndConditionLine {

    /**
     * 条件编号
     */
    private String conditionId;

    /**
     * 指标代码
     */
    private String indId;

    /**
     * 条件项
     */
    private String conditionItem;

    /**
     * 控制类型
     */
    private String controlType;

    /**
     * 数值（起）
     */
    private BigDecimal valueStr;

    /**
     * 数值（末）
     */
    private BigDecimal valueEnd;

    /**
     * 组合（包）代码
     */
    private String prtBagId;

    /**
     * 条件内容
     */
    private String content;

    /**
     * 预警线
     */
    private String warnCond;

    /**
     * 审批线
     */
    private String approveCond;

    /**
     * 禁止线
     */
    private String foulCond;

    /**
     * 是否计算组合包
     */
    private String isBagCalc;

    /**
     * 单位
     */
    private String conditionUnit;

    /**
     * 条件类型
     */
    private String conditionType;

    /**
     * 取得 条件编号
     * 
     * @return 条件编号
     */
    public String getConditionId() {
        return this.conditionId;
    }

    /**
     * 设置 条件编号
     * 
     * @param conditionId 条件编号
     */
    public void setConditionId(String conditionId) {
        this.conditionId = conditionId;
    }

    /**
     * 取得 指标代码
     * 
     * @return 指标代码
     */
    public String getIndId() {
        return this.indId;
    }

    /**
     * 设置 指标代码
     * 
     * @param indId 指标代码
     */
    public void setIndId(String indId) {
        this.indId = indId;
    }

    /**
     * 取得 条件项
     * 
     * @return 条件项
     */
    public String getConditionItem() {
        return this.conditionItem;
    }

    /**
     * 设置 条件项
     * 
     * @param conditionItem 条件项
     */
    public void setConditionItem(String conditionItem) {
        this.conditionItem = conditionItem;
    }

    /**
     * 取得 控制类型
     * 
     * @return 控制类型
     */
    public String getControlType() {
        return this.controlType;
    }

    /**
     * 设置 控制类型
     * 
     * @param controlType 控制类型
     */
    public void setControlType(String controlType) {
        this.controlType = controlType;
    }

    /**
     * 取得 数值（起）
     * 
     * @return 数值（起）
     */
    public BigDecimal getValueStr() {
        return this.valueStr;
    }

    /**
     * 设置 数值（起）
     * 
     * @param valueStr 数值（起）
     */
    public void setValueStr(BigDecimal valueStr) {
        this.valueStr = valueStr;
    }

    /**
     * 取得 数值（末）
     * 
     * @return 数值（末）
     */
    public BigDecimal getValueEnd() {
        return this.valueEnd;
    }

    /**
     * 设置 数值（末）
     * 
     * @param valueEnd 数值（末）
     */
    public void setValueEnd(BigDecimal valueEnd) {
        this.valueEnd = valueEnd;
    }

    /**
     * 取得 组合（包）代码
     * 
     * @return 组合（包）代码
     */
    public String getPrtBagId() {
        return this.prtBagId;
    }

    /**
     * 设置 组合（包）代码
     * 
     * @param prtBagId 组合（包）代码
     */
    public void setPrtBagId(String prtBagId) {
        this.prtBagId = prtBagId;
    }

    /**
     * 取得 条件内容
     * 
     * @return 条件内容
     */
    public String getContent() {
        return this.content;
    }

    /**
     * 设置 条件内容
     * 
     * @param content 条件内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 取得 预警线
     * 
     * @return 预警线
     */
    public String getWarnCond() {
        return this.warnCond;
    }

    /**
     * 设置 预警线
     * 
     * @param warnCond 预警线
     */
    public void setWarnCond(String warnCond) {
        this.warnCond = warnCond;
    }

    /**
     * 取得 审批线
     * 
     * @return 审批线
     */
    public String getApproveCond() {
        return this.approveCond;
    }

    /**
     * 设置 审批线
     * 
     * @param approveCond 审批线
     */
    public void setApproveCond(String approveCond) {
        this.approveCond = approveCond;
    }

    /**
     * 取得 禁止线
     * 
     * @return 禁止线
     */
    public String getFoulCond() {
        return this.foulCond;
    }

    /**
     * 设置 禁止线
     * 
     * @param foulCond 禁止线
     */
    public void setFoulCond(String foulCond) {
        this.foulCond = foulCond;
    }

    /**
     * 取得 是否计算组合包
     * 
     * @return 是否计算组合包
     */
    public String getIsBagCalc() {
        return this.isBagCalc;
    }

    /**
     * 设置 是否计算组合包
     * 
     * @param isBagCalc 是否计算组合包
     */
    public void setIsBagCalc(String isBagCalc) {
        this.isBagCalc = isBagCalc;
    }

    /**
     * 取得 单位
     * 
     * @return 单位
     */
    public String getConditionUnit() {
        return this.conditionUnit;
    }

    /**
     * 设置 单位
     * 
     * @param conditionUnit 单位
     */
    public void setConditionUnit(String conditionUnit) {
        this.conditionUnit = conditionUnit;
    }

    /**
     * 取得 条件类型
     * 
     * @return 条件类型
     */
    public String getConditionType() {
        return this.conditionType;
    }

    /**
     * 设置 条件类型
     * 
     * @param conditionType 条件类型
     */
    public void setConditionType(String conditionType) {
        this.conditionType = conditionType;
    }
}
