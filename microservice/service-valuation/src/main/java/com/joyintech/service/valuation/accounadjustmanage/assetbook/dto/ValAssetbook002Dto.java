/*
 * 文件名：ValAssetbook001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：huangjian
 * 修改时间：2018年5月24日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.accounadjustmanage.assetbook.dto;

import java.util.Date;

import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.service.valuation.accounadjustmanage.assetbook.validator.ValAssetBook002Validator;
import com.joyintech.tams.common.base.entity.BokBookset;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 账套管理Dto
 * 
 * @author 黄健
 * @version 1.0 2018年5月10日
 * @see ValAssetbook002Dto
 * @since 1.0
 */
@ValidLogic(targetClass = ValAssetBook002Validator.class)
public class ValAssetbook002Dto extends BokBookset implements ProcessEntity{

    private static final long serialVersionUID = 1L;

    /**
     * 账套代码
     */
    @PrimaryKey(value=Field.PK_1)
    @NotEmpty(fieldName = "ValAssetbook.booksetId")
    private String booksetId;

    /**
     * 账套名称
     */
    @NotEmpty(fieldName = "ValAssetbook.booksetName")
    private String booksetName;

    /**
     * 账套类别
     */
    @NotEmpty(fieldName = "ValAssetbook.booksetType")
    private String booksetType;

    /**
     * 核算主体代码
     */
    private String acctSubjectId;

    /**
     * 核算主体类别
     */
    private String acctsubjectType;

    /**
     * 本位币
     */
    @NotEmpty(fieldName = "ValAssetbook.standardCcy")
    private String standardCcy;

    /**
     * 模板账套代码
     */
    private String booksetIdTmpl;

    /**
     * 会计准则
     */
    @NotEmpty(fieldName = "ValAssetbook.acctStandard")
    private String acctStandard;

    /**
     * 账套开始日
     */
    @NotEmpty(fieldName = "ValAssetbook.startDate")
    private Date startDate;

    /**
     * 账套结束日
     */
    private Date endDate;

    /**
     * 是否默认模板
     */
    private String isDefault;

    /**
     * 是新建还是修改操作 Y：修改 N：新建
     */
    private String isEdit;

    /**
     * 账套估值方案 - 主键
     */
    private String booksetSchemeId;
    
    /**
     * 所属估值方案
     */
    private String schemeId;
    
    /**
     * 当前系统日期
     */
    private String bookSetDate;
    
    
    /**
     * 账套全称
     */
    @NotEmpty(fieldName = "ValAssetbook.booksetFullname")
    private String booksetFullname;
    
    /**
     * 所属影子定价方案
     */
    private String shadowPriceSchemeId;
    
    
    /**
     * 所属税务方案
     */
    private String taxSchemeId;
    
    /**
     * 所属影子定价方案 - 主键
     */
    private String booksetShadowPriceSchemeId;
    
    /**
     * 所属税务方案 - 主键
     */
    private String booksetTaxSchemeId;
    
    

    public String getBookSetDate() {
        return bookSetDate;
    }

    
    public void setBookSetDate(String bookSetDate) {
        this.bookSetDate = bookSetDate;
    }

    /**
     * 取得 账套代码
     * 
     * @return 账套代码
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 账套代码
     * 
     * @param booksetId 账套代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId == null ? null : booksetId.trim();
    }

    /**
     * 取得 账套名称
     * 
     * @return 账套名称
     */
    public String getBooksetName() {
        return booksetName;
    }

    /**
     * 设置 账套名称
     * 
     * @param booksetName 账套名称
     */
    public void setBooksetName(String booksetName) {
        this.booksetName = booksetName == null ? null : booksetName.trim();
    }

    /**
     * 取得 账套类别
     * 
     * @return 账套类别
     */
    public String getBooksetType() {
        return booksetType;
    }

    /**
     * 设置 账套类别
     * 
     * @param booksetType 账套类别
     */
    public void setBooksetType(String booksetType) {
        this.booksetType = booksetType == null ? null : booksetType.trim();
    }

    /**
     * 取得 核算主体代码
     * 
     * @return 核算主体代码
     */
    public String getAcctSubjectId() {
        return acctSubjectId;
    }

    /**
     * 设置 核算主体代码
     * 
     * @param acctSubjectId 核算主体代码
     */
    public void setAcctSubjectId(String acctSubjectId) {
        this.acctSubjectId = acctSubjectId == null ? null : acctSubjectId.trim();
    }

    /**
     * 获取 acctsubjectType
     * 
     * @return acctsubjectType.
     */
    public String getAcctsubjectType() {
        return acctsubjectType;
    }

    /**
     * 设置 acctsubjectType
     * 
     * @param acctsubjectType acctsubjectType
     */
    public void setAcctsubjectType(String acctsubjectType) {
        this.acctsubjectType = acctsubjectType;
    }

    /**
     * 取得 本位币
     * 
     * @return 本位币
     */
    public String getStandardCcy() {
        return standardCcy;
    }

    /**
     * 设置 本位币
     * 
     * @param standardCcy 本位币
     */
    public void setStandardCcy(String standardCcy) {
        this.standardCcy = standardCcy == null ? null : standardCcy.trim();
    }

    /**
     * 取得 模板账套代码
     * 
     * @return 模板账套代码
     */
    public String getBooksetIdTmpl() {
        return booksetIdTmpl;
    }

    /**
     * 设置 模板账套代码
     * 
     * @param booksetIdTmpl 模板账套代码
     */
    public void setBooksetIdTmpl(String booksetIdTmpl) {
        this.booksetIdTmpl = booksetIdTmpl == null ? null : booksetIdTmpl.trim();
    }

    /**
     * 取得 会计准则
     * 
     * @return 会计准则
     */
    public String getAcctStandard() {
        return acctStandard;
    }

    /**
     * 设置 会计准则
     * 
     * @param acctStandard 会计准则
     */
    public void setAcctStandard(String acctStandard) {
        this.acctStandard = acctStandard == null ? null : acctStandard.trim();
    }

    /**
     * 取得 账套开始日
     * 
     * @return 账套开始日
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置 账套开始日
     * 
     * @param startDate 账套开始日
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 取得 账套结束日
     * 
     * @return 账套结束日
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置 账套结束日
     * 
     * @param endDate 账套结束日
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 获取 是否默认模板
     * 
     * @return 是否默认模板
     */
    public String getIsDefault() {
        return isDefault;
    }

    /**
     * 设置 是否默认模板
     * 
     * @param isDefault 是否默认模板
     */
    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * 获取 是新建还是修改操作
     * 
     * @return 是新建还是修改操作.
     */
    public String getIsEdit() {
        return isEdit;
    }

    /**
     * 设置 是新建还是修改操作
     * 
     * @param isEdit 是新建还是修改操作
     */
    public void setIsEdit(String isEdit) {
        this.isEdit = isEdit;
    }

	public String getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

	public String getBooksetSchemeId() {
		return booksetSchemeId;
	}

	public void setBooksetSchemeId(String booksetSchemeId) {
		this.booksetSchemeId = booksetSchemeId;
	}


    
    /**
     * 获取 booksetFullname
     * @return booksetFullname.
     */
    public String getBooksetFullname() {
        return booksetFullname;
    }


    
    /**
     * 设置 booksetFullname
     * @param booksetFullname booksetFullname
     */
    public void setBooksetFullname(String booksetFullname) {
        this.booksetFullname = booksetFullname;
    }


    
   


    
    /**
     * 获取 taxSchemeId
     * @return taxSchemeId.
     */
    public String getTaxSchemeId() {
        return taxSchemeId;
    }


    
    /**
     * 设置 taxSchemeId
     * @param taxSchemeId taxSchemeId
     */
    public void setTaxSchemeId(String taxSchemeId) {
        this.taxSchemeId = taxSchemeId;
    }


    
    /**
     * 获取 shadowPriceSchemeId
     * @return shadowPriceSchemeId.
     */
    public String getShadowPriceSchemeId() {
        return shadowPriceSchemeId;
    }


    
    /**
     * 设置 shadowPriceSchemeId
     * @param shadowPriceSchemeId shadowPriceSchemeId
     */
    public void setShadowPriceSchemeId(String shadowPriceSchemeId) {
        this.shadowPriceSchemeId = shadowPriceSchemeId;
    }


    
    /**
     * 获取 booksetShadowPriceSchemeId
     * @return booksetShadowPriceSchemeId.
     */
    public String getBooksetShadowPriceSchemeId() {
        return booksetShadowPriceSchemeId;
    }


    
    /**
     * 设置 booksetShadowPriceSchemeId
     * @param booksetShadowPriceSchemeId booksetShadowPriceSchemeId
     */
    public void setBooksetShadowPriceSchemeId(String booksetShadowPriceSchemeId) {
        this.booksetShadowPriceSchemeId = booksetShadowPriceSchemeId;
    }


    
    /**
     * 获取 booksetTaxSchemeId
     * @return booksetTaxSchemeId.
     */
    public String getBooksetTaxSchemeId() {
        return booksetTaxSchemeId;
    }


    
    /**
     * 设置 booksetTaxSchemeId
     * @param booksetTaxSchemeId booksetTaxSchemeId
     */
    public void setBooksetTaxSchemeId(String booksetTaxSchemeId) {
        this.booksetTaxSchemeId = booksetTaxSchemeId;
    }

    
    
}
