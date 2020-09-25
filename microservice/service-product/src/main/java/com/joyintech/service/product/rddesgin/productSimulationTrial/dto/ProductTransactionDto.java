package com.joyintech.service.product.rddesgin.productSimulationTrial.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品模拟交易dto
 * 
 * @author jiaobo
 * @version 1.0 2019年10月31日
 * @see ProductTransactionDto
 * @since 1.0
 * 	产品模拟交易表
 */
@PageId(value = "ProductSimulationTrial002")
public class ProductTransactionDto extends BaseEntity implements ProcessEntity {
	
	/**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 模拟交易编号
     */
    @PrimaryKey(value = PrimaryKey.Field.PK_1)
    private String tradeId;

    /**
     * 日期
     */
    private Date tradeDate;

    /**
     * 产品设计系列编号
     */
    private String prdDesignId;

    /**
     * 资产风险等级
     */
    private String assetRiskLevel;

    /**
     * 资产大类
     */
    private String assetType;

    /**
     * 模拟匹配金额
     */
    private BigDecimal mockAmt;

    /**
     * 模拟匹配份额
     */
    private BigDecimal mockShareAmt;

    /**
     * 利率类型
     */
    private String intType;

    /**
     * 收益率上限
     */
    private String rateMax;

    /**
     * 收益率下限
     */
    private String rateMix;

    /**
     * 浮动利率下限
     */
    private String floatMinRate;

    /**
     * 浮动利率上限
     */
    private String floatMaxRate;

    /**
     * 基准
     */
    private String baseRuleId;

    /**
     * 系数
     */
    private BigDecimal coefficient;

    /**
     * 利差
     */
    private BigDecimal spreadRate;

    /**
     * 批次号
     */
    private String batchId;

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

    public String getBaseRuleId() {
        return baseRuleId;
    }

    public void setBaseRuleId(String baseRuleId) {
        this.baseRuleId = baseRuleId;
    }

    public BigDecimal getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(BigDecimal coefficient) {
        this.coefficient = coefficient;
    }

    public BigDecimal getSpreadRate() {
        return spreadRate;
    }

    public void setSpreadRate(BigDecimal spreadRate) {
        this.spreadRate = spreadRate;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
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

    public String getIntType() {
        return intType;
    }

    public void setIntType(String intType) {
        this.intType = intType;
    }

    public String getRateMax() {
        return rateMax;
    }

    public void setRateMax(String rateMax) {
        this.rateMax = rateMax;
    }

    public String getRateMix() {
        return rateMix;
    }

    public void setRateMix(String rateMix) {
        this.rateMix = rateMix;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }
}
