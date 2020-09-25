package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class FinRateInfoLevel extends FinRateInfoLevelKey {
    /**
     * 客户等级
     */
    private String customLevel;

    /**
     * 金额下限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal amtMin;

    /**
     * 金额上限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal amtMax;

    /**
     * 期限上限
     */
    private Long termMax;

    /**
     * 期限下限
     */
    private Long termMin;

    /**
     * 利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yield;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 客户等级
     * @return 客户等级
     */
    public String getCustomLevel() {
        return customLevel;
    }

    /**
     * 设置 客户等级
     * @param customLevel 客户等级
     */
    public void setCustomLevel(String customLevel) {
        this.customLevel = customLevel == null ? null : customLevel.trim();
    }

    /**
     * 取得 金额下限
     * @return 金额下限
     */
    public BigDecimal getAmtMin() {
        return amtMin;
    }

    /**
     * 设置 金额下限
     * @param amtMin 金额下限
     */
    public void setAmtMin(BigDecimal amtMin) {
        this.amtMin = amtMin;
    }

    /**
     * 取得 金额上限
     * @return 金额上限
     */
    public BigDecimal getAmtMax() {
        return amtMax;
    }

    /**
     * 设置 金额上限
     * @param amtMax 金额上限
     */
    public void setAmtMax(BigDecimal amtMax) {
        this.amtMax = amtMax;
    }

    /**
     * 取得 期限上限
     * @return 期限上限
     */
    public Long getTermMax() {
        return termMax;
    }

    /**
     * 设置 期限上限
     * @param termMax 期限上限
     */
    public void setTermMax(Long termMax) {
        this.termMax = termMax;
    }

    /**
     * 取得 期限下限
     * @return 期限下限
     */
    public Long getTermMin() {
        return termMin;
    }

    /**
     * 设置 期限下限
     * @param termMin 期限下限
     */
    public void setTermMin(Long termMin) {
        this.termMin = termMin;
    }

    /**
     * 取得 利率
     * @return 利率
     */
    public BigDecimal getYield() {
        return yield;
    }

    /**
     * 设置 利率
     * @param yield 利率
     */
    public void setYield(BigDecimal yield) {
        this.yield = yield;
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
}