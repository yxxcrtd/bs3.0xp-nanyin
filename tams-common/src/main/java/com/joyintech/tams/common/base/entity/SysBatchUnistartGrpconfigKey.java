package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class SysBatchUnistartGrpconfigKey extends BaseEntity {
    /**
     * 配置号
     */
    private String configId;

    /**
     * 顺序
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal orderNo;

    /**
     * 取得 配置号
     * @return 配置号
     */
    public String getConfigId() {
        return configId;
    }

    /**
     * 设置 配置号
     * @param configId 配置号
     */
    public void setConfigId(String configId) {
        this.configId = configId == null ? null : configId.trim();
    }

    /**
     * 取得 顺序
     * @return 顺序
     */
    public BigDecimal getOrderNo() {
        return orderNo;
    }

    /**
     * 设置 顺序
     * @param orderNo 顺序
     */
    public void setOrderNo(BigDecimal orderNo) {
        this.orderNo = orderNo;
    }
}