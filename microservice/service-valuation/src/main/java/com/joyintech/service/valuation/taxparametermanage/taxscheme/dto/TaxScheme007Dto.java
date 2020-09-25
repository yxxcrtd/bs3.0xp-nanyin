/*
 * 文件名：TaxScheme004Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.taxparametermanage.taxscheme.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.service.valuation.taxparametermanage.taxscheme.validator.TaxScheme007DtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 参数设置
 * @author zhangwei
 * @version 1.0 2018年10月17日
 * @see TaxScheme007Dto
 * @since 1.0
 */
@PageId("TaxScheme007")
@ValidLogic(targetClass = TaxScheme007DtoValidator.class, groups = {ValidateGroupEdit.class,ValidateGroupDelete.class})
public class TaxScheme007Dto extends BaseEntity implements ProcessEntity {


    /**
     * 方案代码
     */
    private String schemeId;

    /**
     * 配置项代码
     */
    @PrimaryKey(value= PrimaryKey.Field.PK_1)
    private String configId;

    /**
     * 记账分类配置
     */
    private String bokConfigId;

    /**
     * 是否使用
     */
    private String isUsed;

    /**
     * 记账场景(会计事件编码)
     */
    private String tableId;

    /**
     * 发生额科目性质
     */
    private String subjectNature;

    /**
     * 增值税类型
     */
    private String vatType;

    /**
     * 确认类型
     */
    private String taxPType;

    /**
     * 发生场景
     */
    private String incomeSource;

    /**
     * 增项/减项
     */
    private String inOut;

    /**
     * 计算方式
     */
    private String calcType;

    /**
     * 发生科目借贷方向
     */
    private String cdFlag;

    /**
     * 占比方式
     */
    private String calcScale;

    /**
     * 占比科目科目性质
     */
    private String calcSnature;

    /**
     * 占比基数
     */
    private String calcBase;

    /**
     * 金额计入方式
     */
    private String isCarrt;

    /**
     * 处理条件
     */
    private String intRepayHandle;

    /**
     * 流水
     */
    private String seqNo;

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String pageModel;

    /**
     * 处理操作
     */
    private String operateType;

    public String getBokConfigId() {
        return bokConfigId;
    }

    public void setBokConfigId(String bokConfigId) {
        this.bokConfigId = bokConfigId;
    }

    public String getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(String isUsed) {
        this.isUsed = isUsed;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getSubjectNature() {
        return subjectNature;
    }

    public void setSubjectNature(String subjectNature) {
        this.subjectNature = subjectNature;
    }

    public String getVatType() {
        return vatType;
    }

    public void setVatType(String vatType) {
        this.vatType = vatType;
    }

    public String getTaxPType() {
        return taxPType;
    }

    public void setTaxPType(String taxPType) {
        this.taxPType = taxPType;
    }

    public String getIncomeSource() {
        return incomeSource;
    }

    public void setIncomeSource(String incomeSource) {
        this.incomeSource = incomeSource;
    }

    public String getInOut() {
        return inOut;
    }

    public void setInOut(String inOut) {
        this.inOut = inOut;
    }

    public String getCalcType() {
        return calcType;
    }

    public void setCalcType(String calcType) {
        this.calcType = calcType;
    }

    public String getCdFlag() {
        return cdFlag;
    }

    public void setCdFlag(String cdFlag) {
        this.cdFlag = cdFlag;
    }

    public String getCalcScale() {
        return calcScale;
    }

    public void setCalcScale(String calcScale) {
        this.calcScale = calcScale;
    }

    public String getCalcSnature() {
        return calcSnature;
    }

    public void setCalcSnature(String calcSnature) {
        this.calcSnature = calcSnature;
    }

    public String getCalcBase() {
        return calcBase;
    }

    public void setCalcBase(String calcBase) {
        this.calcBase = calcBase;
    }

    public String getIsCarrt() {
        return isCarrt;
    }

    public void setIsCarrt(String isCarrt) {
        this.isCarrt = isCarrt;
    }

    public String getIntRepayHandle() {
        return intRepayHandle;
    }

    public void setIntRepayHandle(String intRepayHandle) {
        this.intRepayHandle = intRepayHandle;
    }

    public String getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId;
    }

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }
}
