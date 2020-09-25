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

import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.service.valuation.taxparametermanage.taxscheme.validator.TaxScheme005DtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 资产应税信息
 * 
 * @author zhangwei
 * @version 1.0 2018年10月17日
 * @see TaxScheme005Dto
 * @since 1.0
 */
@PageId("TaxScheme005")
@ValidLogic(targetClass = TaxScheme005DtoValidator.class, groups = {ValidateGroupEdit.class,ValidateGroupDelete.class})
public class TaxScheme005Dto extends BaseEntity  implements ProcessEntity {

    /**
     * 流水号
     */
    @PrimaryKey(value=Field.PK_1)
    private String seqNo;

    /**
     * 方案代码
     */
    @PrimaryKey(value=Field.PK_2)
    private String schId;

    /**
     * 资产代码
     */
    @PrimaryKey(value=Field.PK_3)
    @NotBlank(message="资产代码不能为空!", groups= {ValidateGroupEdit.class})
    private String assetId;
    
    /**
     * 资产名称
     */
    private String assetName;
    
    /** add by yanya on 20191031 原始资产代码ID **/
    private String originalFinprodId;

    /**
     * 金融产品类型
     */
    private String finprodType;
    
    /**
     * 资产分类
     */
    @NotBlank(message="资产分类不能为空!", groups= {ValidateGroupEdit.class})
    private String assetClassify;

    /**
     * 本金收益特征
     */
    @NotBlank(message="本金收益特征不能为空!", groups= {ValidateGroupEdit.class})
    private String capiIncomeFeature;

    /**
     * 收入类型
     */
    @PrimaryKey(value=Field.PK_4)
    @NotBlank(message="收入类型不能为空!", groups= {ValidateGroupEdit.class})
    private String incomeType;

    /**
     * 计税方式
     */
    @NotBlank(message="应税标志不能为空!", groups= {ValidateGroupEdit.class})
    private String taxableSign;

    /**
     * 应税税率
     */
    @NotNull(message="应税税率不能为空!", groups= {ValidateGroupEdit.class})
    @Max(value=100, message="应税税率不能大于100%！", groups= {ValidateGroupEdit.class})
    @Min(value=0, message="应税税率不能小于0%！", groups= {ValidateGroupEdit.class})
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal taxRate;

    /**
     * 免税收入类型
     */
    private String feeType;

    /**
     * 生效日
     */
    @PrimaryKey(value=Field.PK_5)
    @NotBlank(message="生效日不能为空!", groups= {ValidateGroupEdit.class})
    private String effDate;

    /**
     * 维护方式
     */
    private String maintenanceMode;

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String pageModel;
    
    /**
     * 处理操作
     */
    private String operateType;

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getSchId() {
        return schId;
    }

    public void setSchId(String schId) {
        this.schId = schId;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getAssetClassify() {
        return assetClassify;
    }

    public void setAssetClassify(String assetClassify) {
        this.assetClassify = assetClassify;
    }

    public String getCapiIncomeFeature() {
        return capiIncomeFeature;
    }

    public void setCapiIncomeFeature(String capiIncomeFeature) {
        this.capiIncomeFeature = capiIncomeFeature;
    }

    public String getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }

    public String getTaxableSign() {
        return taxableSign;
    }

    public void setTaxableSign(String taxableSign) {
        this.taxableSign = taxableSign;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getEffDate() {
        return effDate;
    }

    public void setEffDate(String effDate) {
        this.effDate = effDate;
    }

    public String getMaintenanceMode() {
        return maintenanceMode;
    }

    public void setMaintenanceMode(String maintenanceMode) {
        this.maintenanceMode = maintenanceMode;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    
    public String getAssetName() {
        return assetName;
    }

    
    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    
    public String getFinprodType() {
        return finprodType;
    }

    
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

    
    public String getPageModel() {
        return pageModel;
    }

    
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    
    public String getOriginalFinprodId() {
        return originalFinprodId;
    }

    
    public void setOriginalFinprodId(String originalFinprodId) {
        this.originalFinprodId = originalFinprodId;
    }

    
    
}
