/*
 * 文件名：ValAssetbook001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：huangjian
 * 修改时间：2018年5月24日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.assetbook.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 账套管理Dto
 * 
 * @author 黄健
 * @version 1.0 2018年5月10日
 * @see ValAssetbook001Dto
 * @since 1.0
 */
@PageId("ValAssetBook005")
public class ValAssetbook001Dto extends BaseEntity implements ProcessEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 账套代码
     */
    @PrimaryKey(value=Field.PK_1)
    private String booksetId;

    /**
     * 账套名称
     */
    private String booksetName;

    /**
     * 账套类别
     */
    private String booksetType;

    /**
     * 账套类别
     */
    private String booksetTypeCode;
    
    /**
     * 核算主体代码
     */
    private String acctSubjectId;

    /**
     * 核算主体类别
     */
    private String acctSubjectType;

    /**
     * 本位币
     */
    private String standardCcy;
    
    /**
     * 本位币Code
     */
    private String standardCcyCode;
    

    /**
     * 模板账套代码
     */
    private String booksetIdTmpl;

    /**
     * 会计准则
     */
    private String acctStandard;

    /**
     * 账套开始日
     */
    private String startDate;

    /**
     * 账套结束日
     */
    private String endDate;
    
    /**
     * 是否默认模板
     */
    private String isDefault;

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
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.账套类别, booksetType))?
            SysDataUtil.getNameFromDataDict(DictCode.账套类别, booksetType):booksetType;
        
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
     * 获取 acctSubjectType
     * 
     * @return acctSubjectType.
     */
    public String getAcctSubjectType() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.金融产品类型, acctSubjectType))?
            SysDataUtil.getNameFromDataDict(DictCode.金融产品类型, acctSubjectType):acctSubjectType;
    }

    /**
     * 设置 acctSubjectType
     * 
     * @param acctsubjectType acctSubjectType
     */
    public void setAcctSubjectType(String acctSubjectType) {
        this.acctSubjectType = acctSubjectType;
    }

    /**
     * 取得 本位币
     * 
     * @return 本位币
     */
    public String getStandardCcy() {        
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.币种, standardCcy))?
            SysDataUtil.getNameFromDataDict(DictCode.币种, standardCcy):standardCcy;
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
     * 获取 standardCcyCode
     * @return standardCcyCode.
     */
    public String getStandardCcyCode() {
        return standardCcyCode;
    }

    
    /**
     * 设置 standardCcyCode
     * @param standardCcyCode standardCcyCode
     */
    public void setStandardCcyCode(String standardCcyCode) {
        this.standardCcyCode = standardCcyCode;
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
    public String getStartDate() {
        return DateUtil.formatDateAddSplit(startDate);
    }

    /**
     * 设置 账套开始日
     * 
     * @param startDate 账套开始日
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * 取得 账套结束日
     * 
     * @return 账套结束日
     */
    public String getEndDate() {
        return DateUtil.formatDateAddSplit(endDate);
    }

    /**
     * 设置 账套结束日
     * 
     * @param endDate 账套结束日
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    
    /**
     * 获取 是否默认模板
     * @return 是否默认模板
     */
    public String getIsDefault() {
        return isDefault;
    }

    
    /**
     * 设置 是否默认模板
     * @param isDefault 是否默认模板
     */
    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

	public String getBooksetTypeCode() {
		return booksetTypeCode;
	}

	public void setBooksetTypeCode(String booksetTypeCode) {
		this.booksetTypeCode = booksetTypeCode;
	}
    
    
}
