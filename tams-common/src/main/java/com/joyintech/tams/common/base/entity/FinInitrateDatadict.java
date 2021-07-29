package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class FinInitrateDatadict extends BaseEntity {
    /**
     * 所属营业部
     */
    private String ownDept;

    /**
     * 初始利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 所属营业部
     * @return 所属营业部
     */
    public String getOwnDept() {
        return ownDept;
    }

    /**
     * 设置 所属营业部
     * @param ownDept 所属营业部
     */
    public void setOwnDept(String ownDept) {
        this.ownDept = ownDept == null ? null : ownDept.trim();
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