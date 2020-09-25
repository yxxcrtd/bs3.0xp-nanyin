package com.joyintech.tams.invest.product.rddesign.productsimulationtrial.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.invest.product.rddesign.productsimulationtrial.validator.ProductSimulationTrial001Validator;
import java.math.BigDecimal;
import java.util.List;

/**
 * 产品模拟交易dto
 * 
 * @author jiaobo
 * @version 1.0 2019年10月31日
 * @see ProductSimulationTrial004Dto
 * @since 1.0
 * 	产品模拟交易表
 */
@ValidLogic(targetClass = ProductSimulationTrial001Validator.class)
public class ProductSimulationTrial004Dto extends BaseEntity {
	
	/**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 流水号
     */
    private String seqNo;

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
     * 上限基准名称
     */
    private String benchmarkMaxStr;
    
    /**
     * 上线基准来源
     */
    private String benchmarkMaxSource;
    
    /**
     * 下限基准
     */
    private String benchmarkMin;
    
    /**
     * 下限基准名称
     */
    private String benchmarkMinStr;

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
    
    private List<ProductSimulationTrial004Dto> simulationAssetsList;

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


    public String getAssetRiskLevel() {
        return assetRiskLevel;
    }

    public void setAssetRiskLevel(String assetRiskLevel) {
        this.assetRiskLevel = assetRiskLevel;
    }

    public String getAssetType() {
        return assetType;
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
     * 获取 benchmarkMax
     * @return benchmarkMax.
     */
    public String getBenchmarkMax() {
        return benchmarkMax;
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
    public List<ProductSimulationTrial004Dto> getSimulationAssetsList() {
        return simulationAssetsList;
    }

    /**
     * 设置 simulationAssetsList
     * @param simulationAssetsList simulationAssetsList
     */
    public void setSimulationAssetsList(List<ProductSimulationTrial004Dto> simulationAssetsList) {
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

    public String getRateMix() {
        return rateMix;
    }

    public void setRateMix(String rateMix) {
        this.rateMix = rateMix;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    /**
     * 获取 benchmarkMaxStr
     * @return benchmarkMaxStr.
     */
    public String getBenchmarkMaxStr() {
        return benchmarkMaxStr;
    }

    /**
     * 设置 benchmarkMaxStr
     * @param benchmarkMaxStr benchmarkMaxStr
     */
    public void setBenchmarkMaxStr(String benchmarkMaxStr) {
        this.benchmarkMaxStr = benchmarkMaxStr;
    }

    /**
     * 获取 benchmarkMinStr
     * @return benchmarkMinStr.
     */
    public String getBenchmarkMinStr() {
        return benchmarkMinStr;
    }

    /**
     * 设置 benchmarkMinStr
     * @param benchmarkMinStr benchmarkMinStr
     */
    public void setBenchmarkMinStr(String benchmarkMinStr) {
        this.benchmarkMinStr = benchmarkMinStr;
    }
}
