/*
 * 文件名：TaxScheme001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.taxparametermanage.taxschbokcfgmanage.dto;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * 税务方案 - 主界面通用 Dto
 * 
 * @author zhangwei
 * @version 1.0 2018年10月11日
 * @see TaxSchBokCfgManage001Dto
 * @since 1.0
 */
public class TaxSchBokCfgManage001Dto extends BaseEntity {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaxSchBokCfgManage001Dto.class);

    /**
     * 方案代码
     */
    private String schId;

    /**
     * 方案名称
     */
    private String schName;

    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 资产分类
     */
    private String assetClassify;
    /**
     * 资产分类Code
     */
    private String assetClassifyCode;

    /**
     * 本金收益特征
     */
    private String capiIncomeFeature;

    /**
     * 本金收益特征Code
     */
    private String capiIncomeFeatureCode;

    /**
     * 收入类型
     */
    private String incomeType;

    /**
     * 收入类型Code
     */
    private String incomeTypeCode;

    /**
     * 计税方式
     */
    private String taxableSign;

    /**
     * 计税方式Code
     */
    private String taxableSignCode;
    /**
     * 免税收入类型
     */
    private String feeType;

    /**
     * 免税收入类型Code
     */
    private String feeTypeCode;

    /**
     * 应税税率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal taxRate;

    /**
     * 生效日
     */
    private String effDate;

    /**
     * 税则代码
     */
    private String taxId;

    /**
     * 附加税类型
     */
    private String addTaxType;

    /**
     * 附加税类型Code
     */
    private String addTaxTypeCode;

    /**
     * 资产代码
     */
    private String assetId;

    /**
     * 资产名称
     */
    private String assetName;

    /**
     * 维护方式
     */
    private String maintenanceMode;

    /**
     * 维护方式Code
     */
    private String maintenanceModeCode;

    /**
     * 方案二级类型
     */
    private String schemeType2;

    /**
     * 方案二级类型Code
     */
    private String schemeType2Code;

    /**
     * 配置项代码
     */
    private String configId;

    /**
     * 配置项名称
     */
    private String configName;

    /**
     * 配置项说明
     */
    private String remark;

    /**
     * 处理操作
     */
    private String operateType;

    /**
     * 流水
     */
    private String seqId;

    /**
     * 配置项值
     */
    private String configValue;

    /**
     * 配置项值Code
     */
    private String configValueCode;

    /**
     * 是否默认
     */
    private String isDefault;

    /**
     * 取值数据字典
     */
    private String dictCode;

    /**
     * 记账分类配置
     */
    private String bokConfigId;

    /**
     * 是否使用
     */
    private String isUsed;

    /**
     * 是否使用Code
     */
    private String isUsedCode;

    /**
     * 记账场景(会计事件编码)
     */
    private String tableId;

    /**
     * 记账场景(会计事件编码)Code
     */
    private String tableIdCode;

    /**
     * 发生额科目性质
     */
    private String subjectNature;

    /**
     * 发生额科目性质Code
     */
    private String subjectNatureCode;

    /**
     * 增值税类型
     */
    private String vatType;

    /**
     * 增值税类型Code
     */
    private String vatTypeCode;

    /**
     * 确认类型
     */
    private String taxPType;

    /**
     * 确认类型Code
     */
    private String taxPTypeCode;

    /**
     * 发生场景
     */
    private String incomeSource;

    /**
     * 发生场景Code
     */
    private String incomeSourceCode;

    /**
     * 增项/减项
     */
    private String inOut;

    /**
     * 增项/减项Code
     */
    private String inOutCode;

    /**
     * 计算方式
     */
    private String calcType;

    /**
     * 计算方式Code
     */
    private String calcTypeCode;

    /**
     * 发生科目借贷方向
     */
    private String cdFlag;

    /**
     * 发生科目借贷方向Code
     */
    private String cdFlagCode;

    /**
     * 占比方式
     */
    private String calcScale;

    /**
     * 占比方式Code
     */
    private String calcScaleCode;

    /**
     * 占比科目科目性质
     */
    private String calcSnature;

    /**
     * 占比科目科目性质Code
     */
    private String calcSnatureCode;

    /**
     * 占比基数
     */
    private String calcBase;

    /**
     * 占比基数Code
     */
    private String calcBaseCode;

    /**
     * 金额计入方式
     */
    private String isCarrt;

    /**
     * 处理条件
     */
    private String intRepayHandle;

    public String getSchId() {
        return schId;
    }

    public void setSchId(String schId) {
        this.schId = schId;
    }

    public String getSchName() {
        return schName;
    }

    public void setSchName(String schName) {
        this.schName = schName;
    }

    public String getAssetClassify() {
        return SysDataUtil.getNameFromDataDict(DictCode.增值税资产分类, this.assetClassifyCode);
    }

    public void setAssetClassify(String assetClassify) {
        this.assetClassify = assetClassify;
    }

    /**
     * 获取 assetClassifyCode
     * @return assetClassifyCode.
     */
    public String getAssetClassifyCode() {
        return assetClassifyCode;
    }


    /**
     * 设置 assetClassifyCode
     * @param assetClassifyCode assetClassifyCode
     */
    public void setAssetClassifyCode(String assetClassifyCode) {
        this.assetClassifyCode = assetClassifyCode;
    }

    public String getCapiIncomeFeature() {
        return SysDataUtil.getNameFromDataDict(DictCode.本金收益特征, this.capiIncomeFeatureCode);
    }

    public void setCapiIncomeFeature(String capiIncomeFeature) {
        this.capiIncomeFeature = capiIncomeFeature;
    }

    /**
     * 获取 capiIncomeFeatureCode
     * @return capiIncomeFeatureCode.
     */
    public String getCapiIncomeFeatureCode() {
        return capiIncomeFeatureCode;
    }


    /**
     * 设置 capiIncomeFeatureCode
     * @param capiIncomeFeatureCode capiIncomeFeatureCode
     */
    public void setCapiIncomeFeatureCode(String capiIncomeFeatureCode) {
        this.capiIncomeFeatureCode = capiIncomeFeatureCode;
    }

    public String getIncomeType() {
        return SysDataUtil.getNameFromDataDict(DictCode.增值税类型2, this.incomeTypeCode);
    }

    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }
    /**
     * 获取 incomeTypeCode
     * @return incomeTypeCode.
     */
    public String getIncomeTypeCode() {
        return incomeTypeCode;
    }


    /**
     * 设置 incomeTypeCode
     * @param incomeTypeCode incomeTypeCode
     */
    public void setIncomeTypeCode(String incomeTypeCode) {
        this.incomeTypeCode = incomeTypeCode;
    }

    public String getFeeType() {
        return SysDataUtil.getNameFromDataDict(DictCode.免税收入类型, this.feeTypeCode);
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }



    /**
     * 获取 feeTypeCode
     * @return feeTypeCode.
     */
    public String getFeeTypeCode() {
        return feeTypeCode;
    }


    /**
     * 设置 feeTypeCode
     * @param feeTypeCode feeTypeCode
     */
    public void setFeeTypeCode(String feeTypeCode) {
        this.feeTypeCode = feeTypeCode;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }



    public String getEffDate() {
        return effDate;
    }

    public void setEffDate(String effDate) {
        this.effDate = effDate;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getTaxableSign() {
        return SysDataUtil.getNameFromDataDict(DictCode.计税方式, this.taxableSignCode);
    }

    public void setTaxableSign(String taxableSign) {
        this.taxableSign = taxableSign;
    }

    /**
     * 获取 taxableSignCode
     * @return taxableSignCode.
     */
    public String getTaxableSignCode() {
        return taxableSignCode;
    }


    /**
     * 设置 taxableSignCode
     * @param taxableSignCode taxableSignCode
     */
    public void setTaxableSignCode(String taxableSignCode) {
        this.taxableSignCode = taxableSignCode;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getAddTaxType() {
        return SysDataUtil.getNameFromDataDict(DictCode.附加税类型, this.addTaxTypeCode);
    }

    public void setAddTaxType(String addTaxType) {
        this.addTaxType = addTaxType;
    }


    /**
     * 获取 addTaxTypeCode
     * @return addTaxTypeCode.
     */
    public String getAddTaxTypeCode() {
        return addTaxTypeCode;
    }


    /**
     * 设置 addTaxTypeCode
     * @param addTaxTypeCode addTaxTypeCode
     */
    public void setAddTaxTypeCode(String addTaxTypeCode) {
        this.addTaxTypeCode = addTaxTypeCode;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }


    public String getAssetId() {
        return assetId;
    }


    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }


    public String getAssetName() {
        return assetName;
    }


    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }


    public String getMaintenanceMode() {
        return SysDataUtil.getNameFromDataDict(DictCode.维护方式, this.maintenanceModeCode);
    }


    public void setMaintenanceMode(String maintenanceMode) {
        this.maintenanceMode = maintenanceMode;
    }




    /**
     * 获取 maintenanceModeCode
     * @return maintenanceModeCode.
     */
    public String getMaintenanceModeCode() {
        return maintenanceModeCode;
    }


    /**
     * 设置 maintenanceModeCode
     * @param maintenanceModeCode maintenanceModeCode
     */
    public void setMaintenanceModeCode(String maintenanceModeCode) {
        this.maintenanceModeCode = maintenanceModeCode;
    }

    public String getSchemeType2() {
        return SysDataUtil.getNameFromDataDict(DictCode.税务参数类型, this.schemeType2Code);
    }


    public void setSchemeType2(String schemeType2) {
        this.schemeType2 = schemeType2;
    }


    /**
     * 获取 schemeType2Code
     * @return schemeType2Code.
     */
    public String getSchemeType2Code() {
        return schemeType2Code;
    }


    /**
     * 设置 schemeType2Code
     * @param schemeType2Code schemeType2Code
     */
    public void setSchemeType2Code(String schemeType2Code) {
        this.schemeType2Code = schemeType2Code;
    }

    public String getConfigId() {
        return configId;
    }


    public void setConfigId(String configId) {
        this.configId = configId;
    }


    public String getConfigName() {
        return configName;
    }


    public void setConfigName(String configName) {
        this.configName = configName;
    }


    public String getRemark() {
        return remark;
    }


    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getSeqId() {
        return seqId;
    }


    public void setSeqId(String seqId) {
        this.seqId = seqId;
    }


    public String getConfigValue() {
        try {
            if(StringUtils.isNotBlank(configValue) && StringUtils.isNotBlank(dictCode)) {
                return SysDataUtil.getNameFromDataDict(DictCode.get(dictCode), configValueCode);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return configValue;
        }
        return configValue;
    }


    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }


    public String getDictCode() {
        return dictCode;
    }


    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    /**
     * 获取 isDefault
     * @return isDefault.
     */
    public String getIsDefault() {
        return isDefault;
    }

    /**
     * 设置 isDefault
     * @param isDefault isDefault
     */
    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }


    public String getConfigValueCode() {
        return configValueCode;
    }


    public void setConfigValueCode(String configValueCode) {
        this.configValueCode = configValueCode;
    }

    public String getBokConfigId() {
        return bokConfigId;
    }

    public void setBokConfigId(String bokConfigId) {
        this.bokConfigId = bokConfigId;
    }

    public String getIsUsed() {
        return SysDataUtil.getNameFromDataDict(DictCode.是否, this.isUsed);
    }

    public void setIsUsed(String isUsed) {
        this.isUsed = isUsed;
    }

    public String getTableId() {
        return SysDataUtil.getNameFromDataDict(DictCode.会计场景代码, this.tableId);
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
        return SysDataUtil.getNameFromDataDict(DictCode.增值税类型, this.vatType);
    }

    public void setVatType(String vatType) {
        this.vatType = vatType;
    }

    public String getTaxPType() {
        return SysDataUtil.getNameFromDataDict(DictCode.增值税_确认类型, this.taxPType);
    }

    public void setTaxPType(String taxPType) {
        this.taxPType = taxPType;
    }

    public String getIncomeSource() {
        return SysDataUtil.getNameFromDataDict(DictCode.增值税_发生场景, this.incomeSource);
    }

    public void setIncomeSource(String incomeSource) {
        this.incomeSource = incomeSource;
    }

    public String getInOut() {
        return SysDataUtil.getNameFromDataDict(DictCode.增项_减项, this.inOut);
    }

    public void setInOut(String inOut) {
        this.inOut = inOut;
    }

    public String getCalcType() {
        return SysDataUtil.getNameFromDataDict(DictCode.增值税_计算方式, this.calcType);
    }

    public void setCalcType(String calcType) {
        this.calcType = calcType;
    }

    public String getCdFlag() {
        return SysDataUtil.getNameFromDataDict(DictCode.借贷方向, this.cdFlag);
    }

    public void setCdFlag(String cdFlag) {
        this.cdFlag = cdFlag;
    }

    public String getCalcScale() {
        return SysDataUtil.getNameFromDataDict(DictCode.增值税_占比比例, this.calcScale);
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
        return SysDataUtil.getNameFromDataDict(DictCode.增值税_发生场景, this.calcBase);
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

    public String getIsUsedCode() {
        return isUsedCode;
    }

    public void setIsUsedCode(String isUsedCode) {
        this.isUsedCode = isUsedCode;
    }

    public String getTableIdCode() {
        return tableIdCode;
    }

    public void setTableIdCode(String tableIdCode) {
        this.tableIdCode = tableIdCode;
    }

    public String getSubjectNatureCode() {
        return subjectNatureCode;
    }

    public void setSubjectNatureCode(String subjectNatureCode) {
        this.subjectNatureCode = subjectNatureCode;
    }

    public String getVatTypeCode() {
        return vatTypeCode;
    }

    public void setVatTypeCode(String vatTypeCode) {
        this.vatTypeCode = vatTypeCode;
    }

    public String getTaxPTypeCode() {
        return taxPTypeCode;
    }

    public void setTaxPTypeCode(String taxPTypeCode) {
        this.taxPTypeCode = taxPTypeCode;
    }

    public String getIncomeSourceCode() {
        return incomeSourceCode;
    }

    public void setIncomeSourceCode(String incomeSourceCode) {
        this.incomeSourceCode = incomeSourceCode;
    }

    public String getInOutCode() {
        return inOutCode;
    }

    public void setInOutCode(String inOutCode) {
        this.inOutCode = inOutCode;
    }

    public String getCalcTypeCode() {
        return calcTypeCode;
    }

    public void setCalcTypeCode(String calcTypeCode) {
        this.calcTypeCode = calcTypeCode;
    }

    public String getCdFlagCode() {
        return cdFlagCode;
    }

    public void setCdFlagCode(String cdFlagCode) {
        this.cdFlagCode = cdFlagCode;
    }

    public String getCalcScaleCode() {
        return calcScaleCode;
    }

    public void setCalcScaleCode(String calcScaleCode) {
        this.calcScaleCode = calcScaleCode;
    }

    public String getCalcSnatureCode() {
        return calcSnatureCode;
    }

    public void setCalcSnatureCode(String calcSnatureCode) {
        this.calcSnatureCode = calcSnatureCode;
    }

    public String getCalcBaseCode() {
        return calcBaseCode;
    }

    public void setCalcBaseCode(String calcBaseCode) {
        this.calcBaseCode = calcBaseCode;
    }
}

