/*
 * 文件名：ReconcileSchemeInfo002Dto.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算POC
 * 修改人：jiangchuanzhi
 * 修改时间：2018年1月22日
 * 修改内容：新建
 * 系统名称：估值核算POC
 */

package com.joyintech.tams.valuation.valuationparametermanage.reconcilescheme.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.valuation.valuationparametermanage.reconcilescheme.validate.ValReconcileSchemeInfo002DtoValidator;

/**
 * 对账规则方案配置新建DTO
 * 
 * @author weipeng
 * @version 1.0 2018年11月13日
 * @see ValReconcileSchemeInfo002Dto
 * @since 1.0
 */
@ValidLogic(targetClass = ValReconcileSchemeInfo002DtoValidator.class)
public class ValReconcileSchemeInfo002Dto extends BaseEntity {

    /** serialVersionUID */
    private static final long serialVersionUID = -3285641599453245925L;

    /** 
     * 方案代码
     */
    private String schemeId;

    /** 
     * 方案名称
     */
    @NotEmpty(fieldName = "ValReconcileSchemeInfo.schName")
    private String schemeName;

    /**
     * 方案类型
     */
    @NotEmpty(fieldName = "ValReconcileSchemeInfo.schemeType")
    private String schemeType;

    @NotEmpty(fieldName = "ValReconcileSchemeInfo.isDefault")
    private String isDefault;
    
    /**
     * 所属账套
     */
    @NotEmpty(fieldName = "ValReconcileSchemeInfo.booksetId")
    private String booksetId;

    /**
     * 取得 方案代码
     * 
     * @return 方案代码
     * @since 1.0
     */
    public String getSchemeId() {
        return schemeId;
    }

    /**
     * 设置 方案代码
     * 
     * @param schemeId 方案代码
     * @since 1.0
     */
    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId;
    }

    /**
     * 取得 方案名称
     * 
     * @return 方案名称
     * @since 1.0
     */
    public String getSchemeName() {
        return schemeName;
    }

    /**
     * 设置 方案名称
     * 
     * @param schemeName 方案名称
     * @since 1.0
     */
    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    /**
     * 获取 方案类型
     * 
     * @return 方案类型
     */
    public String getSchemeType() {
        return schemeType;
    }

    /**
     * 设置 方案类型
     * 
     * @param schemeType 方案类型
     */
    public void setSchemeType(String schemeType) {
        this.schemeType = schemeType;
    }

    /**
     * 获取 是否默认方案
     * 
     * @return 是否默认方案
     */
    public String getIsDefault() {
        return isDefault;
    }

    /**
     * 设置 是否默认方案
     * 
     * @param isDefault 是否默认方案
     */
    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    
    /**
     * 获取 所属账套
     * @return 所属账套
     */
    public String getBooksetId() {
        return booksetId;
    }

    
    /**
     * 设置 所属账套
     * @param booksetId 所属账套
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }
    
    
}
