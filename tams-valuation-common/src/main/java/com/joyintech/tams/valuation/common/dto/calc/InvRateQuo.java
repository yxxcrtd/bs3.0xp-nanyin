/*
 * 文件名：InvRateQuo.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年6月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.dto.calc;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 * 利率报价 
 * 
 * @author 王波
 * @version 1.0 2018年6月1日
 * @see InvRateQuo
 * @since 1.0
 */
public class InvRateQuo extends InvRateQuoKey{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 资产代码
     */
    private String assetId;

    /**
     * 资产类型 参照数据字典
     */
    private String assetType;

    /**
     * 基准利率代码
     */
    private String basicRateId;

    /**
     * 基准利率生效日
     */
    private Date basicRateEdate;

    /**
     * 基准利率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal basicRate;

    /**
     * 生效日
     */
    private Date effDate;

    /**
     * 撤销日
     */
    private Date revDate;

    /**
     * 启用日
     */
    private Date startDate;

    /**
     * 利率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal paperRate;

    /**
     * 入池利率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal poolRate;

    /**
     * 资产利率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal assetRate;

    /**
     * 利率类型 参照数据字典
     */
    private String intType;

    /**
     * 是否初始利率 参照数据字典
     */
    private String isInitRate;

    /**
     * 录入方式 参照数据字典
     */
    private String inputType;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 备注
     */
    private String remark;
    
    /**
     * 计息基础
     */
    private String accountBasis;
    
    
    /**
     * 获取 计息基础
     * @return 计息基础.
     */
    public String getAccountBasis() {
        return accountBasis;
    }

    
    /**
     * 设置 计息基础
     * @param accountBasis 计息基础
     */
    public void setAccountBasis(String accountBasis) {
        this.accountBasis = accountBasis;
    }

    /**
     * 取得 资产代码
     * 
     * @return 资产代码
     */
    public String getAssetId() {
        return assetId;
    }

    /**
     * 设置 资产代码
     * 
     * @param assetId 资产代码
     */
    public void setAssetId(String assetId) {
        this.assetId = assetId == null ? null : assetId.trim();
    }

    /**
     * 取得 资产类型 参照数据字典
     * 
     * @return 资产类型 参照数据字典
     */
    public String getAssetType() {
        return assetType;
    }

    /**
     * 设置 资产类型 参照数据字典
     * 
     * @param assetType 资产类型 参照数据字典
     */
    public void setAssetType(String assetType) {
        this.assetType = assetType == null ? null : assetType.trim();
    }

    /**
     * 取得 基准利率代码
     * 
     * @return 基准利率代码
     */
    public String getBasicRateId() {
        return basicRateId;
    }

    /**
     * 设置 基准利率代码
     * 
     * @param basicRateId 基准利率代码
     */
    public void setBasicRateId(String basicRateId) {
        this.basicRateId = basicRateId == null ? null : basicRateId.trim();
    }

    /**
     * 取得 基准利率生效日
     * 
     * @return 基准利率生效日
     */
    public Date getBasicRateEdate() {
        return basicRateEdate;
    }

    /**
     * 设置 基准利率生效日
     * 
     * @param basicRateEdate 基准利率生效日
     */
    public void setBasicRateEdate(Date basicRateEdate) {
        this.basicRateEdate = basicRateEdate;
    }

    /**
     * 取得 基准利率
     * 
     * @return 基准利率
     */
    public BigDecimal getBasicRate() {
        return basicRate;
    }

    /**
     * 设置 基准利率
     * 
     * @param basicRate 基准利率
     */
    public void setBasicRate(BigDecimal basicRate) {
        this.basicRate = basicRate;
    }

    /**
     * 取得 生效日
     * 
     * @return 生效日
     */
    public Date getEffDate() {
        return effDate;
    }

    /**
     * 设置 生效日
     * 
     * @param effDate 生效日
     */
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    /**
     * 取得 撤销日
     * 
     * @return 撤销日
     */
    public Date getRevDate() {
        return revDate;
    }

    /**
     * 设置 撤销日
     * 
     * @param revDate 撤销日
     */
    public void setRevDate(Date revDate) {
        this.revDate = revDate;
    }

    /**
     * 取得 启用日
     * 
     * @return 启用日
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置 启用日
     * 
     * @param startDate 启用日
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 取得 利率
     * 
     * @return 利率
     */
    public BigDecimal getPaperRate() {
        return paperRate;
    }

    /**
     * 设置 利率
     * 
     * @param paperRate 利率
     */
    public void setPaperRate(BigDecimal paperRate) {
        this.paperRate = paperRate;
    }

    /**
     * 取得 入池利率
     * 
     * @return 入池利率
     */
    public BigDecimal getPoolRate() {
        return poolRate;
    }

    /**
     * 设置 入池利率
     * 
     * @param poolRate 入池利率
     */
    public void setPoolRate(BigDecimal poolRate) {
        this.poolRate = poolRate;
    }

    /**
     * 取得 资产利率
     * 
     * @return 资产利率
     */
    public BigDecimal getAssetRate() {
        return assetRate;
    }

    /**
     * 设置 资产利率
     * 
     * @param assetRate 资产利率
     */
    public void setAssetRate(BigDecimal assetRate) {
        this.assetRate = assetRate;
    }

    /**
     * 取得 利率类型 参照数据字典
     * 
     * @return 利率类型 参照数据字典
     */
    public String getIntType() {
        return intType;
    }

    /**
     * 设置 利率类型 参照数据字典
     * 
     * @param intType 利率类型 参照数据字典
     */
    public void setIntType(String intType) {
        this.intType = intType == null ? null : intType.trim();
    }

    /**
     * 取得 是否初始利率 参照数据字典
     * 
     * @return 是否初始利率 参照数据字典
     */
    public String getIsInitRate() {
        return isInitRate;
    }

    /**
     * 设置 是否初始利率 参照数据字典
     * 
     * @param isInitRate 是否初始利率 参照数据字典
     */
    public void setIsInitRate(String isInitRate) {
        this.isInitRate = isInitRate == null ? null : isInitRate.trim();
    }

    /**
     * 取得 录入方式 参照数据字典
     * 
     * @return 录入方式 参照数据字典
     */
    public String getInputType() {
        return inputType;
    }

    /**
     * 设置 录入方式 参照数据字典
     * 
     * @param inputType 录入方式 参照数据字典
     */
    public void setInputType(String inputType) {
        this.inputType = inputType == null ? null : inputType.trim();
    }

    /**
     * 取得 工作流代码
     * 
     * @return 工作流代码
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 工作流代码
     * 
     * @param workflowId 工作流代码
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }

    /**
     * 取得 备注
     * 
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * 
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}
