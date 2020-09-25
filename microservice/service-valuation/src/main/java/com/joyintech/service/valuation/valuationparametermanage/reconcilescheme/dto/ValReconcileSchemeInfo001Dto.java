/*
 * 文件名：ReconcileSchemeInfo001Dto.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算POC
 * 修改人：jiangchuanzhi
 * 修改时间：2018年1月22日
 * 修改内容：新建
 * 系统名称：估值核算POC
 */

package com.joyintech.service.valuation.valuationparametermanage.reconcilescheme.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.service.valuation.valuationparametermanage.reconcilescheme.validate.ValReconcileSchemeInfo001DtoValidator;
import com.joyintech.tams.constant.DictCode;

/**
 * 对账规则方案配置DTO
 * 
 * @author weipeng
 * @version 1.0 2018年11月13日
 * @see ValReconcileSchemeInfo001Dto
 * @since 1.0
 */
public class ValReconcileSchemeInfo001Dto extends BaseEntity {

    /** serialVersionUID */
    private static final long serialVersionUID = -769921715617293367L;

    /**
     * 附件IDS
     */
    private String fileIds;

    /**
     * 文件路径
     */
    private String path;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 错误信息
     */
    private String errorMessage;

    /**
     * 方案代码
     */
    @NotEmpty(fieldName = "ValReconcileSchemeInfo.schName")
    private String schId;

    /**
     * 方案名称
     */
    private String schName;

    /**
     * 对账类型 GZB：估值表 YEB：余额表
     */
    @NotEmpty(fieldName = "ValReconcileSchemeInfo.schemeType")
    private String schemeType;

    /**
     * 账套代码
     */
    @NotEmpty(fieldName = "ValReconcileSchemeInfo.booksetId")
    private String booksetId;

    /**
     * 账套名称
     */
    private String booksetName;

    /**
     * 是否默认方案 Y：是 N：否
     */
    private String isDefault;

    /**
     * 原默认方案代码
     */
    private String oldDefaultSchemeId;

    /**
     * 本方数据起始行
     */
    @NotEmpty(fieldName = "ValReconcileSchemeInfo.myStartRowNo")
    private Long myStartRowNo;

    /**
     * 对方数据起始行
     */
    @NotEmpty(fieldName = "ValReconcileSchemeInfo.othersideStartRowNo")
    private Long othersideStartRowNo;

    /**
     * 本方数据结束行
     */
    private Long myEndRowNo;

    /**
     * 对方数据结束行
     */
    private Long othersideEndRowNo;

    /**
     * 对账误差设置
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    @NotEmpty(fieldName = "ValReconcileSchemeInfo.errorNumber")
    private BigDecimal errorNumber;

    /**
     * 账套估值方案代码
     */
    private String booksetSchemeId;

    /**
     * 对账方案结构映射表
     */
    private List<ValReconcileSchemeStructConfigDto> structConfigList;

    /**
     * 对账方案科目映射
     */
    private List<ValReconcileSchemeSubjectConfigDto> subjectConfigList;

    /**
     * 对账方案科目性质映射
     */
    private List<ValReconcileSchemeNatureConfigDto> natureConfigList;

    /**
     * 对账方案资产映射表
     */
    private List<ValReconcileSchemeAssetConfigDto> assetConfigList;

    /**
     * 取得 附件IDs
     * 
     * @return 附件IDs
     * @since 1.0
     */
    public String getFileIds() {
        return fileIds;
    }

    /**
     * 设置 附件IDs
     * 
     * @param fileIds 附件IDs
     * @since 1.0
     */
    public void setFileIds(String fileIds) {
        this.fileIds = fileIds;
    }

    /**
     * 取得 方案代码
     * 
     * @return 方案代码
     */
    public String getSchId() {
        return schId;
    }

    /**
     * 设置 方案代码
     * 
     * @param schId 方案代码
     */
    public void setSchId(String schId) {
        this.schId = schId == null ? null : schId.trim();
    }

    /**
     * 取得 方案名称
     * 
     * @return 方案名称
     */
    public String getSchName() {
        return schName;
    }

    /**
     * 设置 方案名称
     * 
     * @param schName 方案名称
     */
    public void setSchName(String schName) {
        this.schName = schName == null ? null : schName.trim();
    }

    /**
     * 取得 对账类型 GZB：估值表 YEB：余额表
     * 
     * @return 对账类型 GZB：估值表 YEB：余额表
     */
    public String getSchemeType() {
        return schemeType;
    }

    /**
     * 设置 对账类型 GZB：估值表 YEB：余额表
     * 
     * @param schemeType 对账类型 GZB：估值表 YEB：余额表
     */
    public void setSchemeType(String schemeType) {
        this.schemeType = schemeType == null ? null : schemeType.trim();
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
     * 获取 账套名称
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
        this.booksetName = booksetName;
    }

    /**
     * 获取 是否默认方案 Y：是 N：否
     * 
     * @return 是否默认方案 Y：是 N：否
     */
    public String getIsDefault() {
        return isDefault;
    }

    /**
     * 设置 是否默认方案 Y：是 N：否
     * 
     * @param isDefault 是否默认方案 Y：是 N：否
     */
    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * 取得 本方数据起始行
     * 
     * @return 本方数据起始行
     */
    public Long getMyStartRowNo() {
        return myStartRowNo;
    }

    /**
     * 设置 本方数据起始行
     * 
     * @param myStartRowNo 本方数据起始行
     */
    public void setMyStartRowNo(Long myStartRowNo) {
        this.myStartRowNo = myStartRowNo;
    }

    /**
     * 取得 对方数据起始行
     * 
     * @return 对方数据起始行
     */
    public Long getOthersideStartRowNo() {
        return othersideStartRowNo;
    }

    /**
     * 设置 对方数据起始行
     * 
     * @param othersideStartRowNo 对方数据起始行
     */
    public void setOthersideStartRowNo(Long othersideStartRowNo) {
        this.othersideStartRowNo = othersideStartRowNo;
    }

    /**
     * 取得 本方数据结束行
     * 
     * @return 本方数据结束行
     */
    public Long getMyEndRowNo() {
        return myEndRowNo;
    }

    /**
     * 设置 本方数据结束行
     * 
     * @param myEndRowNo 本方数据结束行
     */
    public void setMyEndRowNo(Long myEndRowNo) {
        this.myEndRowNo = myEndRowNo;
    }

    /**
     * 取得 对方数据结束行
     * 
     * @return 对方数据结束行
     */
    public Long getOthersideEndRowNo() {
        return othersideEndRowNo;
    }

    /**
     * 设置 对方数据结束行
     * 
     * @param othersideEndRowNo 对方数据结束行
     */
    public void setOthersideEndRowNo(Long othersideEndRowNo) {
        this.othersideEndRowNo = othersideEndRowNo;
    }

    /**
     * 取得 对账误差设置
     * 
     * @return 对账误差设置
     */
    public BigDecimal getErrorNumber() {
        return errorNumber;
    }

    /**
     * 设置 对账误差设置
     * 
     * @param errorNumber 对账误差设置
     */
    public void setErrorNumber(BigDecimal errorNumber) {
        this.errorNumber = errorNumber;
    }

    /**
     * 取得 对账方案结构映射表
     * 
     * @return 对账方案结构映射表
     * @since 1.0
     */
    public List<ValReconcileSchemeStructConfigDto> getStructConfigList() {
        return structConfigList;
    }

    /**
     * 设置 对账方案结构映射表
     * 
     * @param structConfigList 对账方案结构映射表
     * @since 1.0
     */
    public void setStructConfigList(List<ValReconcileSchemeStructConfigDto> structConfigList) {
        this.structConfigList = structConfigList;
    }

    /**
     * 取得 对账方案科目映射
     * 
     * @return 对账方案科目映射
     * @since 1.0
     */
    public List<ValReconcileSchemeSubjectConfigDto> getSubjectConfigList() {
        return subjectConfigList;
    }

    /**
     * 设置 对账方案科目映射
     * 
     * @param subjectConfigList 对账方案科目映射
     * @since 1.0
     */
    public void setSubjectConfigList(List<ValReconcileSchemeSubjectConfigDto> subjectConfigList) {
        this.subjectConfigList = subjectConfigList;
    }

    /**
     * 取得 原默认方案代码
     * 
     * @return 原默认方案代码
     * @since 1.0
     */
    public String getOldDefaultSchemeId() {
        return oldDefaultSchemeId;
    }

    /**
     * 设置 原默认方案代码
     * 
     * @param oldDefaultSchemeId 原默认方案代码
     * @since 1.0
     */
    public void setOldDefaultSchemeId(String oldDefaultSchemeId) {
        this.oldDefaultSchemeId = oldDefaultSchemeId;
    }

    /**
     * 获取 对账方案科目性质映射
     * 
     * @return 对账方案科目性质映射
     */
    public List<ValReconcileSchemeNatureConfigDto> getNatureConfigList() {
        return natureConfigList;
    }

    /**
     * 设置 对账方案科目性质映射
     * 
     * @param natureConfigList 对账方案科目性质映射
     */
    public void setNatureConfigList(List<ValReconcileSchemeNatureConfigDto> natureConfigList) {
        this.natureConfigList = natureConfigList;
    }

    /**
     * 取得 对账方案资产映射表
     * 
     * @return 对账方案资产映射表
     * @since 1.0
     */
    public List<ValReconcileSchemeAssetConfigDto> getAssetConfigList() {
        return assetConfigList;
    }

    /**
     * 设置 对账方案资产映射表
     * 
     * @param assetConfigList 对账方案资产映射表
     * @since 1.0
     */
    public void setAssetConfigList(List<ValReconcileSchemeAssetConfigDto> assetConfigList) {
        this.assetConfigList = assetConfigList;
    }

    /**
     * 获取 文件路径
     * 
     * @return 文件路径
     * @since 1.0
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置 文件路径
     * 
     * @param path 文件路径
     * @since 1.0
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取 文件名
     * 
     * @return 文件名
     * @since 1.0
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置 文件名
     * 
     * @param fileName 文件名
     * @since 1.0
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 获取 错误信息
     * 
     * @return 错误信息
     * @since 1.0
     */

    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * 设置 错误信息
     * 
     * @param errorMessage 错误信息
     * @since 1.0
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * 获取 账套估值方案代码
     * 
     * @return 账套估值方案代码
     */
    public String getBooksetSchemeId() {
        return booksetSchemeId;
    }

    /**
     * 设置 账套估值方案代码
     * 
     * @param booksetSchemeId 账套估值方案代码
     */
    public void setBooksetSchemeId(String booksetSchemeId) {
        this.booksetSchemeId = booksetSchemeId;
    }

}
