package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class PrdIssueplanStrategy extends PrdIssueplanStrategyKey {
    /**
     * 剩余规划变动值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal remainChangeAmt;

    /**
     * 配置规模
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cofingScale;

    /**
     * 计算顺序
     */
    private Long countOrder;

    /**
     * 取得 剩余规划变动值
     * @return 剩余规划变动值
     */
    public BigDecimal getRemainChangeAmt() {
        return remainChangeAmt;
    }

    /**
     * 设置 剩余规划变动值
     * @param remainChangeAmt 剩余规划变动值
     */
    public void setRemainChangeAmt(BigDecimal remainChangeAmt) {
        this.remainChangeAmt = remainChangeAmt;
    }

    /**
     * 取得 配置规模
     * @return 配置规模
     */
    public BigDecimal getCofingScale() {
        return cofingScale;
    }

    /**
     * 设置 配置规模
     * @param cofingScale 配置规模
     */
    public void setCofingScale(BigDecimal cofingScale) {
        this.cofingScale = cofingScale;
    }

    /**
     * 取得 计算顺序
     * @return 计算顺序
     */
    public Long getCountOrder() {
        return countOrder;
    }

    /**
     * 设置 计算顺序
     * @param countOrder 计算顺序
     */
    public void setCountOrder(Long countOrder) {
        this.countOrder = countOrder;
    }
}