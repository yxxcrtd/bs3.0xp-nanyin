package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class WflEtrEntrustPledge extends WflEtrEntrustPledgeKey {
    /**
     * 折算比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal scale;

    /**
     * 质押券库代码
     */
    private String pledgeStockId;

    /**
     * 流程编号
     */
    private String workflowId;

    /**
     * 取得 折算比例
     * @return 折算比例
     */
    public BigDecimal getScale() {
        return scale;
    }

    /**
     * 设置 折算比例
     * @param scale 折算比例
     */
    public void setScale(BigDecimal scale) {
        this.scale = scale;
    }

    /**
     * 取得 质押券库代码
     * @return 质押券库代码
     */
    public String getPledgeStockId() {
        return pledgeStockId;
    }

    /**
     * 设置 质押券库代码
     * @param pledgeStockId 质押券库代码
     */
    public void setPledgeStockId(String pledgeStockId) {
        this.pledgeStockId = pledgeStockId == null ? null : pledgeStockId.trim();
    }

    /**
     * 取得 流程编号
     * @return 流程编号
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 流程编号
     * @param workflowId 流程编号
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }
}