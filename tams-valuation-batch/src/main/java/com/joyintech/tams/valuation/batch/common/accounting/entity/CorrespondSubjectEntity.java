/*
 * 文件名：CorrespondSubjectEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年6月26日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.common.accounting.entity;


/**
 * 科目信息dto
 * @author niuqianyu
 * @version 1.0 2018年6月26日
 * @see CorrespondSubjectEntity
 * @since 1.0
 */
public class CorrespondSubjectEntity {
    
    /**
     * 科目号
     */
    private String subjectNo;
    /**
     * 基础科目性质
     */
    private String baseSubjectNature;
    
    /**
     * 获取 科目号
     * @return subjectNo.
     */
    public String getSubjectNo() {
        return subjectNo;
    }
    
    /**
     * 设置 科目号
     * @param subjectNo subjectNo
     */
    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo;
    }
    
    /**
     * 获取 基础科目性质
     * @return baseSubjectNature.
     */
    public String getBaseSubjectNature() {
        return baseSubjectNature;
    }
    
    /**
     * 设置 基础科目性质
     * @param baseSubjectNature baseSubjectNature
     */
    public void setBaseSubjectNature(String baseSubjectNature) {
        this.baseSubjectNature = baseSubjectNature;
    }
    
}
