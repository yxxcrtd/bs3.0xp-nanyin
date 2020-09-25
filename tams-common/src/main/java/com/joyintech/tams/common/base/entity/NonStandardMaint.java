package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class NonStandardMaint extends NonStandardMaintKey {
    /**
     * 产品规划系列名称
     */
    private String prdPlanSerisName;

    /**
     * 非标资产比例上限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal nonStandardLimit;

    /**
     * 取得 产品规划系列名称
     * @return 产品规划系列名称
     */
    public String getPrdPlanSerisName() {
        return prdPlanSerisName;
    }

    /**
     * 设置 产品规划系列名称
     * @param prdPlanSerisName 产品规划系列名称
     */
    public void setPrdPlanSerisName(String prdPlanSerisName) {
        this.prdPlanSerisName = prdPlanSerisName == null ? null : prdPlanSerisName.trim();
    }

    /**
     * 取得 非标资产比例上限
     * @return 非标资产比例上限
     */
    public BigDecimal getNonStandardLimit() {
        return nonStandardLimit;
    }

    /**
     * 设置 非标资产比例上限
     * @param nonStandardLimit 非标资产比例上限
     */
    public void setNonStandardLimit(BigDecimal nonStandardLimit) {
        this.nonStandardLimit = nonStandardLimit;
    }
}