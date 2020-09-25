package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class MstIndexComponent extends MstIndexComponentKey {
    /**
     * 权重
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal weight;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 权重
     * @return 权重
     */
    public BigDecimal getWeight() {
        return weight;
    }

    /**
     * 设置 权重
     * @param weight 权重
     */
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
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