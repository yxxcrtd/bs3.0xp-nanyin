/*
 * 文件名：FeeList.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2018年9月29日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.investfeepay.dto;

/**
 * 费用列表
 * 
 * @author HuangJian
 * @version 1.0 2018年9月29日
 * @see FeeList
 * @since 1.0
 */
public class FeeListDto {

    /**
     * 场景代码
     */
    private String tableId;


    /**
     * 费用类型代码
     */
    private String feeTypeId;


    /**
     * 基础科目性质
     */
    private String baseSubjectNature;


    /**
     * 取得 场景代码
     * 
     * @return 场景代码
     */
    public String getTableId() {
        return tableId;
    }

    /**
     * 设置 场景代码
     * 
     * @param tableId 场景代码
     */
    public void setTableId(String tableId) {
        this.tableId = tableId;
    }



    /**
     * 取得 费用类型代码
     * 
     * @return 费用类型代码
     */
    public String getFeeTypeId() {
        return feeTypeId;
    }

    /**
     * 设置 费用类型代码
     * 
     * @param feeTypeId 费用类型代码
     */
    public void setFeeTypeId(String feeTypeId) {
        this.feeTypeId = feeTypeId;
    }



    /**
     * 取得 基础科目性质
     * 
     * @return 基础科目性质
     */
    public String getBaseSubjectNature() {
        return baseSubjectNature;
    }

    /**
     * 设置 基础科目性质
     * 
     * @param baseSubjectNature 基础科目性质
     */
    public void setBaseSubjectNature(String baseSubjectNature) {
        this.baseSubjectNature = baseSubjectNature;
    }



}
