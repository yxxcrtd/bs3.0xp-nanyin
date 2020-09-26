/*
 * 文件名：ProfitAndLossAccountingSubject.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：杨智
 * 修改时间：2018年5月24日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.profitandlossaccounting.dto;


public class ProfitAndLossAccountingSubject {
    /**
     * 科目号
     */
    private String subjectNo;
    /**
     * 基础科目性质
     */
    private String baseSubjectNature;
    /**
     * 获取 subjectNo
     * @return subjectNo.
     */
    public String getSubjectNo() {
        return subjectNo;
    }
    
    /**
     * 设置 subjectNo
     * @param subjectNo subjectNo
     */
    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo;
    }
    
    /**
     * 获取 baseSubjectNature
     * @return baseSubjectNature.
     */
    public String getBaseSubjectNature() {
        return baseSubjectNature;
    }
    
    /**
     * 设置 baseSubjectNature
     * @param baseSubjectNature baseSubjectNature
     */
    public void setBaseSubjectNature(String baseSubjectNature) {
        this.baseSubjectNature = baseSubjectNature;
    }
    
    
}
