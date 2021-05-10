/*
 * 文件名：ValAssetBookParameter002Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：tanxuan
 * 修改时间：2019年11月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.assetbookparameter.dto;

import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.accounadjustmanage.assetbookparameter.validator.ValAssetBookParameter002Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;
import com.joyintech.framework.base.BaseEntity;

@ValidLogic(targetClass = ValAssetBookParameter002Validator.class)
public class ValAssetBookParameter002Dto extends BaseEntity implements ProcessEntity{

    private static final long serialVersionUID = 1L;

    /**
     * 账套代码
     */
    @PrimaryKey(value=Field.PK_1)
    @NotEmpty(fieldName = "账套名")
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
     * 模板账套代码
     */
    private String booksetIdTmpl;

    /**
     * 是否默认模板
     */
    private String isDefault;

    /**
     * 是新建还是修改操作 Y：修改 N：新建
     */
    private String isEdit;
    
    /**
     * 账套全称
     */
    private String booksetFullname;
    
    /**
     * 参数代码
     */
    @PrimaryKey(value=Field.PK_2)
    @NotEmpty(fieldName = "参数项")
    private String paramCode;

	/**
     * 参数值
     */
    @NotEmpty(fieldName = "参数值")
    private String paramValue;

    /**
     * 账套参数类型
     */
    private String paramType;
    private String paramTypeValue;

    /**
     * 参数名
     */
    private String paramName;

    /**
     * 参数说明
     */
    private String remark;
    
    /**
     * 处理操作
     */
    private String operateType;

    private String pageModel;

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

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public String getParamTypeValue() {
        paramTypeValue = SysDataUtil.getNameFromDataDict(DictCode.账套参数类型, getParamType());
        return paramTypeValue;
    }

    /**
     * 取得 参数值
     * @return 参数值
     */
    public String getParamValue() {
        return paramValue;
    }

    /**
     * 设置 参数值
     * @param paramValue 参数值
     */
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue == null ? null : paramValue.trim();
    }

    /**
     * 取得 参数名
     * @return 参数名
     */
    public String getParamName() {
        return paramName;
    }

    /**
     * 设置 参数名
     * @param paramName 参数名
     */
    public void setParamName(String paramName) {
        this.paramName = paramName == null ? null : paramName.trim();
    }
    
    /**
     * 取得 参数代码
     * @return 参数代码
     */
    public String getParamCode() {
        return paramCode;
    }

    /**
     * 设置 参数代码
     * @param paramCode 参数代码
     */
    public void setParamCode(String paramCode) {
        this.paramCode = paramCode == null ? null : paramCode.trim();
    }

    /**
     * 取得 参数说明
     * @return 参数说明
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 参数说明
     * @param remark 参数说明
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

}
