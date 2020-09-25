package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class FinPledgeDisplaceResultKey extends BaseEntity {
    /**
     * 业务编号
     */
    private String tradeId;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 分支序号
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal branch;

    /**
     * 结果类型
     */
    private String resultType;

    /**
     * 取得 业务编号
     * @return 业务编号
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 业务编号
     * @param tradeId 业务编号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
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
    public BigDecimal getBranch() {
        return branch;
    }

    /**
     * 设置 分支序号
     * @param branch 分支序号
     */
    public void setBranch(BigDecimal branch) {
        this.branch = branch;
    }

    /**
     * 取得 结果类型
     * @return 结果类型
     */
    public String getResultType() {
        return resultType;
    }

    /**
     * 设置 结果类型
     * @param resultType 结果类型
     */
    public void setResultType(String resultType) {
        this.resultType = resultType == null ? null : resultType.trim();
    }
}