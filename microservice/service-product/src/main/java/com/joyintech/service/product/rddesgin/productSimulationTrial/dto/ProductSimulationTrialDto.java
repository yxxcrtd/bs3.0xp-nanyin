package com.joyintech.service.product.rddesgin.productSimulationTrial.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
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
public class ProductSimulationTrialDto extends BaseEntity implements ProcessEntity {
	
	/**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 流水号
     */
    @PrimaryKey(value = PrimaryKey.Field.PK_1)
    private String seqNo;
    
    /**
     * 模拟交易编号
     */
    private String tradeId;

    /**
     * 有效状态
     */
    private String dataStatus;

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
     * 生成时间
     */
    private Date generateTime;

    /**
     * 流程编号
     * @return
     */
    private String workflowId;

    @Override
    public String getWorkflowId() {
        return workflowId;
    }

    @Override
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }

    private List<ProductSimulationTrialDto> simulationAssetsList;

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

    public String getPrdDesignId() {
        return prdDesignId;
    }

    public void setPrdDesignId(String prdDesignId) {
        this.prdDesignId = prdDesignId;
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
    public List<ProductSimulationTrialDto> getSimulationAssetsList() {
        return simulationAssetsList;
    }

    /**
     * 设置 simulationAssetsList
     * @param simulationAssetsList simulationAssetsList
     */
    public void setSimulationAssetsList(List<ProductSimulationTrialDto> simulationAssetsList) {
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
     * 获取 generateTime
     * @return generateTime.
     */
    public Date getGenerateTime() {
        return generateTime;
    }

    /**
     * 设置 generateTime
     * @param generateTime generateTime
     */
    public void setGenerateTime(Date generateTime) {
        this.generateTime = generateTime;
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


}
