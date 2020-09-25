/*
 * 文件名：BaseFinProductExerciseRuleDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年9月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.core.dto;

import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.FinExerciseRule;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2018年9月5日
 * @see BaseFinProductExerciseRuleDto
 * @since 1.0
 */
public class BaseFinProductExerciseRuleDto {
    /**
     * 现金流代码
     */
    private String cashId;
    /**
     * 行权方式
     */
    private String exerciseType;
    /**
     * 首次行权日
     */
    private Date firstExerciseDate;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 分支序号
     */
    private Long branch;
    /**
     * 行权规则entitylist
     */
    private List<FinExerciseRule> finExerciseRuleList;
    
    /**
     * 获取 cashId
     * @return cashId.
     */
    public String getCashId() {
        return cashId;
    }

    
    /**
     * 设置 cashId
     * @param cashId cashId
     */
    public void setCashId(String cashId) {
        this.cashId = cashId;
    }

    
    /**
     * 获取 exerciseType
     * @return exerciseType.
     */
    public String getExerciseType() {
        return exerciseType;
    }

    
    /**
     * 设置 exerciseType
     * @param exerciseType exerciseType
     */
    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    
    /**
     * 获取 firstExerciseDate
     * @return firstExerciseDate.
     */
    public Date getFirstExerciseDate() {
        return firstExerciseDate;
    }

    
    /**
     * 设置 firstExerciseDate
     * @param firstExerciseDate firstExerciseDate
     */
    public void setFirstExerciseDate(Date firstExerciseDate) {
        this.firstExerciseDate = firstExerciseDate;
    }

    
    /**
     * 获取 finprodId
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }

    
    /**
     * 设置 finprodId
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    
    /**
     * 获取 branch
     * @return branch.
     */
    public Long getBranch() {
        return branch;
    }

    
    /**
     * 设置 branch
     * @param branch branch
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }


    
    /**
     * 获取 finExerciseRuleList
     * @return finExerciseRuleList.
     */
    public List<FinExerciseRule> getFinExerciseRuleList() {
        return finExerciseRuleList;
    }


    
    /**
     * 设置 finExerciseRuleList
     * @param finExerciseRuleList finExerciseRuleList
     */
    public void setFinExerciseRuleList(List<FinExerciseRule> finExerciseRuleList) {
        this.finExerciseRuleList = finExerciseRuleList;
    }
    
    
    
}
