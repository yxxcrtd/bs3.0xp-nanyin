/*
 * 文件名：BaseRiskMathCommDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.common.dto;

import java.util.Date;

import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;

/**
 * 风险试算结果dto
 *
 * @author etliu
 * @version 1.0 2018年12月3日
 * @see BaseRiskMathCommDto
 * @since 1.0
 */
public class BaseRiskMathCommDto extends RiskTrialRetDto{

    /**
     * 指标名称
     */
    private String indName;

    /**
     * 风险试算日期
     */
//    private Date riskMathDate;

    /**
     * 监控内容
     */
//    private String monitorContent;

    /**
     * 阀值
     */
    private String threshold;

    /**
     * 当前实际值
     */
//    private String currValue;

    /**
     * 状态
     */
//    private String currStatus;

    /**
     * 指标属性
     */
//    private String indProperty;

    /**
     * 获取 indName
     *
     * @return indName.
     */
//    public String getIndName() {
//        super.get
//        return indName;
//    }

    /**
     * 设置 indName
     *
     * @param indName indName
     */
//    public void setIndName(String indName) {
//        this.indName = indName;
//    }

    /**
     * 获取 riskMathDate
     *
     * @return riskMathDate.
     */
//    public Date getRiskMathDate() {
//        return riskMathDate;
//    }

    /**
     * 设置 riskMathDate
     *
     * @param riskMathDate riskMathDate
     */
//    public void setRiskMathDate(Date riskMathDate) {
//        this.riskMathDate = riskMathDate;
//    }

    /**
     * 获取 monitorContent
     *
     * @return monitorContent.
     */
//    public String getMonitorContent() {
//        return monitorContent;
//    }

    /**
     * 设置 monitorContent
     *
     * @param monitorContent monitorContent
     */
//    public void setMonitorContent(String monitorContent) {
//        this.monitorContent = monitorContent;
//    }

    /**
     * 获取 threshold
     *
     * @return threshold.
     */
    public String getThreshold() {
        return threshold;
    }

    /**
     * 设置 threshold
     *
     * @param threshold threshold
     */
    public void setThreshold(String threshold) {
        this.threshold = threshold;
    }

    /**
     * 获取 currValue
     *
     * @return currValue.
     */
//    public String getCurrValue() {
//        return currValue;
//    }

    /**
     * 设置 currValue
     *
     * @param currValue currValue
     */
//    public void setCurrValue(String currValue) {
//        this.currValue = currValue;
//    }

    /**
     * 获取 currStatus
     *
     * @return currStatus.
     */
//    public String getCurrStatus() {
//        return currStatus;
//    }

    /**
     * 设置 currStatus
     *
     * @param currStatus currStatus
     */
//    public void setCurrStatus(String currStatus) {
//        this.currStatus = currStatus;
//    }

    /**
     * 获取 indProperty
     *
     * @return indProperty.
     */
//    public String getIndProperty() {
//        return indProperty;
//    }

    /**
     * 设置 indProperty
     *
     * @param indProperty indProperty
     */
//    public void setIndProperty(String indProperty) {
//        this.indProperty = indProperty;
//    }

}
