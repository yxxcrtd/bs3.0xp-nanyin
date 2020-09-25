package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class TradorSeq extends BaseEntity {
    /**
     * 规则代码
     */
    private String userId;

    /**
     * 序号
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal seq;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 规则代码
     * @return 规则代码
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置 规则代码
     * @param userId 规则代码
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 取得 序号
     * @return 序号
     */
    public BigDecimal getSeq() {
        return seq;
    }

    /**
     * 设置 序号
     * @param seq 序号
     */
    public void setSeq(BigDecimal seq) {
        this.seq = seq;
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