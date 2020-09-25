package com.joyintech.tams.invest.product.rddesign.productsimulationtrial.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.product.rddesign.productsimulationtrial.validator.ProductSimulationTrial002Validator;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 产品模拟交易dto
 * 
 * @author jiaobo
 * @version 1.0 2019年10月31日
 * @see ProductSimulationTrial002Dto
 * @since 1.0
 * 	产品模拟交易表
 */
@PageId(value = "ProductSimulationTrial002")
@ValidLogic(targetClass = ProductSimulationTrial002Validator.class)
public class ProductSimulationTrial002Dto extends BaseEntity implements ProcessEntity {
	
	/**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 流水号
     */
    private String seqNo;
    
    /**
     * 模拟交易编号
     */
    private String tradeId;

    /**
     * 测算日
     */
    private Date mockDate;
    

    /**
     * 产品设计系列编号
     */
    private String prdDesignId;
    
    /**
     * 产品需求编号
     */
    private String requirementId;
    
    /**
     * 产品规模
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prdScale;
    
    /**
     * 产品期限
     */
    private String prdPeriod;
    
    /**
     * 收益率上限
     */
    private String rateMax;

    /**
     * 收益率下限
     */
    private String rateMix;
    
    /**
     * 资产大类
     */
    private String assetType;

    /**
     * 资产风险等级
     */
    private String assetRiskLevel;

    /**
     * 模拟匹配金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mockAmt;

    /**
     * 模拟匹配份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mockShareAmt;
    
    /**
     * 模拟可用现金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mockAvailableAmt;
    
    /**
     * 利率类型
     */
    private String rateType;


    /**
     * 浮动利率下限
     */
    private String floatMinRate;

    /**
     * 浮动利率上限
     */
    private String floatMaxRate;


    /**
     * 上限系数
     */
    private BigDecimal coefficientMax;
    
    /**
     * 下限系数
     */
    private BigDecimal coefficientMin;

    /**
     * 上限利差
     */
    private BigDecimal spreadRateMax;

    /**
     * 下限利差
     */
    private BigDecimal spreadRateMin;
    
    /**
     * 上限基准
     */
    private String benchmarkMax;
    
    /**
     * 上线基准来源
     */
    private String benchmarkMaxSource;
    
    /**
     * 下限基准
     */
    private String benchmarkMin;

    /**
     * 下限基准来源
     */
    private String benchmarkMinSource;
    /**
     * 批次号
     */
    private String batchId;
    
    /**
     * 操作类型
     */
    private String operateType;
    
    /**
     * 
     */
    private String elementId;
    
    private List<ProductSimulationTrial002Dto> simulationAssetsList;

    public BigDecimal getCoefficientMin() {
        return coefficientMin;
    }

    public void setCoefficientMin(BigDecimal coefficientMin) {
        this.coefficientMin = coefficientMin;
    }

    public BigDecimal getSpreadRateMin() {
        return spreadRateMin;
    }

    public void setSpreadRateMin(BigDecimal spreadRateMin) {
        this.spreadRateMin = spreadRateMin;
    }

    public BigDecimal getCoefficientMax() {
        return coefficientMax;
    }

    public void setCoefficientMax(BigDecimal coefficientMax) {
        this.coefficientMax = coefficientMax;
    }

    public BigDecimal getSpreadRateMax() {
        return spreadRateMax;
    }

    public void setSpreadRateMax(BigDecimal spreadRateMax) {
        this.spreadRateMax = spreadRateMax;
    }

    public String getFloatMinRate() {
        return floatMinRate;
    }

    public void setFloatMinRate(String floatMinRate) {
        this.floatMinRate = floatMinRate;
    }

    public String getFloatMaxRate() {
        return floatMaxRate;
    }

    public void setFloatMaxRate(String floatMaxRate) {
        this.floatMaxRate = floatMaxRate;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    @PrimaryKey(value = PrimaryKey.Field.PK_3)
    public String getPrdDesignId() {
        return prdDesignId;
    }

    public void setPrdDesignId(String prdDesignId) {
        this.prdDesignId = prdDesignId;
    }

    public String getAssetRiskLevel() {
        return assetRiskLevel;
    }
    
    public String getAssetRiskLevelStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.资产风险等级2,this.assetRiskLevel);
    }

    public void setAssetRiskLevel(String assetRiskLevel) {
        this.assetRiskLevel = assetRiskLevel;
    }

    public String getAssetType() {
        return assetType;
    }
    
    public String getAssetTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.资产大类,this.assetType);
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public BigDecimal getMockAmt() {
        return mockAmt;
    }

    public void setMockAmt(BigDecimal mockAmt) {
        this.mockAmt = mockAmt;
    }

    public BigDecimal getMockShareAmt() {
        return mockShareAmt;
    }

    public void setMockShareAmt(BigDecimal mockShareAmt) {
        this.mockShareAmt = mockShareAmt;
    }
    
    public String getRateMax() {
        return rateMax;
    }

    public void setRateMax(String rateMax) {
        this.rateMax = rateMax;
    }



    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    
    /**
     * 获取 mockDate
     * @return mockDate.
     */
    public Date getMockDate() {
        return mockDate;
    }

    
    /**
     * 设置 mockDate
     * @param mockDate mockDate
     */
    public void setMockDate(Date mockDate) {
        this.mockDate = mockDate;
    }

    
    /**
     * 获取 prdScale
     * @return prdScale.
     */
    public BigDecimal getPrdScale() {
        return prdScale;
    }

    
    /**
     * 设置 prdScale
     * @param prdScale prdScale
     */
    public void setPrdScale(BigDecimal prdScale) {
        this.prdScale = prdScale;
    }

    
    /**
     * 获取 prdPeriod
     * @return prdPeriod.
     */
    public String getPrdPeriod() {
        return prdPeriod;
    }

    
    /**
     * 设置 prdPeriod
     * @param prdPeriod prdPeriod
     */
    public void setPrdPeriod(String prdPeriod) {
        this.prdPeriod = prdPeriod;
    }
    
    /**
     * 获取 benchmarkMax
     * @return benchmarkMax.
     */
    public String getBenchmarkMax() {
        return benchmarkMax;
    }
    
    /**
     * 获取 benchmarkMinStr
     * @return benchmarkMin.
     */
    public String getBenchmarkMaxStr() {
        if(StringUtil.equals(this.benchmarkMaxSource, DictCodeListExt1.基准类型_2_利率.getValue())){
            return SysDataUtil.getNameFromDataDict(DictCode.利率行情,this.benchmarkMax);
        } 
        else if (StringUtil.equals(this.benchmarkMaxSource, DictCodeListExt1.基准类型_2_指数.getValue())) {
            return SysDataUtil.getNameFromDataDict(DictCode.曲线指数,this.benchmarkMax);
        } 
        else if (StringUtil.equals(this.benchmarkMaxSource, DictCodeListExt1.基准类型_2_收益率曲线.getValue())) {
            return SysDataUtil.getNameFromDataDict(DictCode.曲线代码,this.benchmarkMax);
        }
        return null;
    }
    

    
    /**
     * 设置 benchmarkMax
     * @param benchmarkMax benchmarkMax
     */
    public void setBenchmarkMax(String benchmarkMax) {
        this.benchmarkMax = benchmarkMax;
    }

    
    /**
     * 获取 benchmarkMin
     * @return benchmarkMin.
     */
    public String getBenchmarkMin() {
        return benchmarkMin;
    }

    /**
     * 获取 benchmarkMinStr
     * @return benchmarkMin.
     */
    public String getBenchmarkMinStr() {
        if(StringUtil.equals(this.benchmarkMinSource, DictCodeListExt1.基准类型_2_利率.getValue())){
            return SysDataUtil.getNameFromDataDict(DictCode.利率行情,this.benchmarkMin);
        } 
        else if (StringUtil.equals(this.benchmarkMinSource, DictCodeListExt1.基准类型_2_指数.getValue())) {
            return SysDataUtil.getNameFromDataDict(DictCode.曲线指数,this.benchmarkMin);
        } 
        else if (StringUtil.equals(this.benchmarkMinSource, DictCodeListExt1.基准类型_2_收益率曲线.getValue())) {
            return SysDataUtil.getNameFromDataDict(DictCode.曲线代码,this.benchmarkMin);
        }
        return null;
    }
    
    /**
     * 设置 benchmarkMin
     * @param benchmarkMin benchmarkMin
     */
    public void setBenchmarkMin(String benchmarkMin) {
        this.benchmarkMin = benchmarkMin;
    }

    /**
     * 获取 simulationAssetsList
     * @return simulationAssetsList.
     */
    public List<ProductSimulationTrial002Dto> getSimulationAssetsList() {
        return simulationAssetsList;
    }

    /**
     * 设置 simulationAssetsList
     * @param simulationAssetsList simulationAssetsList
     */
    public void setSimulationAssetsList(List<ProductSimulationTrial002Dto> simulationAssetsList) {
        this.simulationAssetsList = simulationAssetsList;
    }

    /**
     * 获取 benchmarkMaxSource
     * @return benchmarkMaxSource.
     */
    public String getBenchmarkMaxSource() {
        return benchmarkMaxSource;
    }

    /**
     * 设置 benchmarkMaxSource
     * @param benchmarkMaxSource benchmarkMaxSource
     */
    public void setBenchmarkMaxSource(String benchmarkMaxSource) {
        this.benchmarkMaxSource = benchmarkMaxSource;
    }

    /**
     * 获取 benchmarkMinSource
     * @return benchmarkMinSource.
     */
    public String getBenchmarkMinSource() {
        return benchmarkMinSource;
    }

    /**
     * 设置 benchmarkMinSource
     * @param benchmarkMinSource benchmarkMinSource
     */
    public void setBenchmarkMinSource(String benchmarkMinSource) {
        this.benchmarkMinSource = benchmarkMinSource;
    }

    /**
     * 获取 rateType
     * @return rateType.
     */
    public String getRateType() {
        return rateType;
    }

    /**
     * 设置 rateType
     * @param rateType rateType
     */
    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    /**
     * 获取 seqNo
     * @return seqNo.
     */
    @PrimaryKey(value = PrimaryKey.Field.PK_1)
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 seqNo
     * @param seqNo seqNo
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    /**
     * 获取 operateType
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 operateType
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取 elementId
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * 设置 elementId
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    /**
     * 获取 rateMix
     * @return rateMix.
     */
    public String getRateMix() {
        return rateMix;
    }

    /**
     * 设置 rateMix
     * @param rateMix rateMix
     */
    public void setRateMix(String rateMix) {
        this.rateMix = rateMix;
    }

    /**
     * 获取 requirementId
     * @return requirementId.
     */
    public String getRequirementId() {
        return requirementId;
    }

    /**
     * 设置 requirementId
     * @param requirementId requirementId
     */
    public void setRequirementId(String requirementId) {
        this.requirementId = requirementId;
    }
    
    /**
     * 获取 mockAvailableAmt
     * @return mockAvailableAmt.
     */
    public BigDecimal getMockAvailableAmt() {
        return mockAvailableAmt;
    }

    
    /**
     * 设置 mockAvailableAmt
     * @param mockAvailableAmt mockAvailableAmt
     */
    public void setMockAvailableAmt(BigDecimal mockAvailableAmt) {
        this.mockAvailableAmt = mockAvailableAmt;
    }

    /**
     * 获取 mockDateStr
     * @return mockDateStr.
     */
    @PrimaryKey(value = PrimaryKey.Field.PK_2)
    public String getMockDateStr() {
        return DateUtil.format(this.mockDate);
    }

}
