package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class FinBaseRuleInfo extends FinBaseRuleInfoKey {
    /**
     * 利率类型
     */
    private String rateType;

    /**
     * 初始利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rate;

    /**
     * 基准编号
     */
    private String indexId;

    /**
     * 基准来源，复合基准、利率行情、指数行情、曲线
     */
    private String indexSource;

    /**
     * 系数
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal coefficient;

    /**
     * 利差
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal spreadRate;

    /**
     * 重置规则，期初、期末等
     */
    private String resetType;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 备注
     */
    private String remark;

    /**
     * 业绩基准类型,产品披露，浮动管理费，业绩回补
     */
    private String baseRuleType;

    /**
     * 业绩基准披露类型，区间披露，单点披露
     */
    private String publishType;

    /**
     * 利率下限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rateLower;

    /**
     * 利率上限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rateLimit;

    private String lowLimitRate;
    
    
    public String getLowLimitRate() {
		return lowLimitRate;
	}

	public void setLowLimitRate(String lowLimitRate) {
		this.lowLimitRate = lowLimitRate;
	}

	/**
     * 取得 利率类型
     * @return 利率类型
     */
    public String getRateType() {
        return rateType;
    }

    /**
     * 设置 利率类型
     * @param rateType 利率类型
     */
    public void setRateType(String rateType) {
        this.rateType = rateType == null ? null : rateType.trim();
    }

    /**
     * 取得 初始利率
     * @return 初始利率
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * 设置 初始利率
     * @param rate 初始利率
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    /**
     * 取得 基准编号
     * @return 基准编号
     */
    public String getIndexId() {
        return indexId;
    }

    /**
     * 设置 基准编号
     * @param indexId 基准编号
     */
    public void setIndexId(String indexId) {
        this.indexId = indexId == null ? null : indexId.trim();
    }

    /**
     * 取得 基准来源，复合基准、利率行情、指数行情、曲线
     * @return 基准来源，复合基准、利率行情、指数行情、曲线
     */
    public String getIndexSource() {
        return indexSource;
    }

    /**
     * 设置 基准来源，复合基准、利率行情、指数行情、曲线
     * @param indexSource 基准来源，复合基准、利率行情、指数行情、曲线
     */
    public void setIndexSource(String indexSource) {
        this.indexSource = indexSource == null ? null : indexSource.trim();
    }

    /**
     * 取得 系数
     * @return 系数
     */
    public BigDecimal getCoefficient() {
        return coefficient;
    }

    /**
     * 设置 系数
     * @param coefficient 系数
     */
    public void setCoefficient(BigDecimal coefficient) {
        this.coefficient = coefficient;
    }

    /**
     * 取得 利差
     * @return 利差
     */
    public BigDecimal getSpreadRate() {
        return spreadRate;
    }

    /**
     * 设置 利差
     * @param spreadRate 利差
     */
    public void setSpreadRate(BigDecimal spreadRate) {
        this.spreadRate = spreadRate;
    }

    /**
     * 取得 重置规则，期初、期末等
     * @return 重置规则，期初、期末等
     */
    public String getResetType() {
        return resetType;
    }

    /**
     * 设置 重置规则，期初、期末等
     * @param resetType 重置规则，期初、期末等
     */
    public void setResetType(String resetType) {
        this.resetType = resetType == null ? null : resetType.trim();
    }

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 分支序号
     * @return 分支序号
     */
    public Long getBranch() {
        return branch;
    }

    /**
     * 设置 分支序号
     * @param branch 分支序号
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 取得 业绩基准类型,产品披露，浮动管理费，业绩回补
     * @return 业绩基准类型,产品披露，浮动管理费，业绩回补
     */
    public String getBaseRuleType() {
        return baseRuleType;
    }

    /**
     * 设置 业绩基准类型,产品披露，浮动管理费，业绩回补
     * @param baseRuleType 业绩基准类型,产品披露，浮动管理费，业绩回补
     */
    public void setBaseRuleType(String baseRuleType) {
        this.baseRuleType = baseRuleType == null ? null : baseRuleType.trim();
    }

    /**
     * 取得 业绩基准披露类型，区间披露，单点披露
     * @return 业绩基准披露类型，区间披露，单点披露
     */
    public String getPublishType() {
        return publishType;
    }

    /**
     * 设置 业绩基准披露类型，区间披露，单点披露
     * @param publishType 业绩基准披露类型，区间披露，单点披露
     */
    public void setPublishType(String publishType) {
        this.publishType = publishType == null ? null : publishType.trim();
    }

    /**
     * 取得 利率下限
     * @return 利率下限
     */
    public BigDecimal getRateLower() {
        return rateLower;
    }

    /**
     * 设置 利率下限
     * @param rateLower 利率下限
     */
    public void setRateLower(BigDecimal rateLower) {
        this.rateLower = rateLower;
    }

    /**
     * 取得 利率上限
     * @return 利率上限
     */
    public BigDecimal getRateLimit() {
        return rateLimit;
    }

    /**
     * 设置 利率上限
     * @param rateLimit 利率上限
     */
    public void setRateLimit(BigDecimal rateLimit) {
        this.rateLimit = rateLimit;
    }
}