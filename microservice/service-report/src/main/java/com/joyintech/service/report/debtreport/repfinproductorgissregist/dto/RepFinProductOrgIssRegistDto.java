/*
 * 文件名：RepFinProductOrgIssRegistDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.debtreport.repfinproductorgissregist.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.common.base.entity.RepProdDeclare;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2018年11月21日
 * @see RepFinProductOrgIssRegistDto
 * @since 1.0
 */
public class RepFinProductOrgIssRegistDto extends RepProdDeclare{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 产品起始日期
     */
    private Date repPrdVdate;

    /**
     * 产品终止日期
     */
    private Date repPrdMdate;
    /**
     * 收益率分档情况说明
     */
    private String repYieldComment;

    /**
     * 开放模式
     */
    private String repOpenMode;

    /**
     * 规律开放周期
     */
    private String repOpenCircle;

    /**
     * 其他规律开放周期（天）
     */
    private Short repOpenCircleDays;

    /**
     * 无规律开放说明
     */
    private String repRuleComment;

    /**
     * 首次开放周期起始日
     */
    private Date repFirstOpenDay;

    /**
     * 节假日是否开放
     */
    private String repHolidayOnFlag;

    /**
     * 平均开放次数（年化）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repAvgOpenCount;

    /**
     * 开放期业务
     */
    private String repOpenBuss;

    /**
     * 开放期业务说明
     */
    private String repOpenBussRemark;
    /**
     * 是否为结构化（分级）产品
     */
    private String isItStructured;
    /**
     * 层级类别
     */
    private String hierarchicalCategory;
    
    public Date getRepPrdVdate() {
        return repPrdVdate;
    }

    
    public void setRepPrdVdate(Date repPrdVdate) {
        this.repPrdVdate = repPrdVdate;
    }

    
    public Date getRepPrdMdate() {
        return repPrdMdate;
    }

    
    public void setRepPrdMdate(Date repPrdMdate) {
        this.repPrdMdate = repPrdMdate;
    }

    
    public String getRepYieldComment() {
        return repYieldComment;
    }

    
    public void setRepYieldComment(String repYieldComment) {
        this.repYieldComment = repYieldComment;
    }

    
    public String getRepOpenMode() {
        return repOpenMode;
    }

    
    public void setRepOpenMode(String repOpenMode) {
        this.repOpenMode = repOpenMode;
    }

    
    public String getRepOpenCircle() {
        return repOpenCircle;
    }

    
    public void setRepOpenCircle(String repOpenCircle) {
        this.repOpenCircle = repOpenCircle;
    }

    
    public Short getRepOpenCircleDays() {
        return repOpenCircleDays;
    }

    
    public void setRepOpenCircleDays(Short repOpenCircleDays) {
        this.repOpenCircleDays = repOpenCircleDays;
    }

    
    public String getRepRuleComment() {
        return repRuleComment;
    }

    
    public void setRepRuleComment(String repRuleComment) {
        this.repRuleComment = repRuleComment;
    }

    
    public Date getRepFirstOpenDay() {
        return repFirstOpenDay;
    }

    
    public void setRepFirstOpenDay(Date repFirstOpenDay) {
        this.repFirstOpenDay = repFirstOpenDay;
    }

    
    public String getRepHolidayOnFlag() {
        return repHolidayOnFlag;
    }

    
    public void setRepHolidayOnFlag(String repHolidayOnFlag) {
        this.repHolidayOnFlag = repHolidayOnFlag;
    }

    
    public BigDecimal getRepAvgOpenCount() {
        return repAvgOpenCount;
    }

    
    public void setRepAvgOpenCount(BigDecimal repAvgOpenCount) {
        this.repAvgOpenCount = repAvgOpenCount;
    }

    
    public String getRepOpenBuss() {
        return repOpenBuss;
    }

    
    public void setRepOpenBuss(String repOpenBuss) {
        this.repOpenBuss = repOpenBuss;
    }

    
    public String getRepOpenBussRemark() {
        return repOpenBussRemark;
    }

    
    public void setRepOpenBussRemark(String repOpenBussRemark) {
        this.repOpenBussRemark = repOpenBussRemark;
    }


    
    public String getIsItStructured() {
        return isItStructured;
    }


    
    public void setIsItStructured(String isItStructured) {
        this.isItStructured = isItStructured;
    }


    
    public String getHierarchicalCategory() {
        return hierarchicalCategory;
    }


    
    public void setHierarchicalCategory(String hierarchicalCategory) {
        this.hierarchicalCategory = hierarchicalCategory;
    }
    
}
