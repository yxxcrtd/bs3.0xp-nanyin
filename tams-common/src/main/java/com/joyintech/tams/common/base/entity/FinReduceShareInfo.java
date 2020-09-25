package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class FinReduceShareInfo extends FinReduceShareInfoKey {
    /**
     * 减持比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal reducePer;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 减持比例
     * @return 减持比例
     */
    public BigDecimal getReducePer() {
        return reducePer;
    }

    /**
     * 设置 减持比例
     * @param reducePer 减持比例
     */
    public void setReducePer(BigDecimal reducePer) {
        this.reducePer = reducePer;
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