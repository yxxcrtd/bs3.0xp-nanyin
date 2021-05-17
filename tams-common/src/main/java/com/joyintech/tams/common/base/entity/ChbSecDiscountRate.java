package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class ChbSecDiscountRate extends BaseEntity {
    /**
     * 主键UUID
     */
    private String datauuid;

    /**
     * 债券类型
     */
    private String secType;

    /**
     * 发行方式
     */
    private String issueType;

    /**
     * 评级类型
     */
    private String gradeType;

    /**
     * 折扣率（%）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal discountRate;

    /**
     * 取得 主键UUID
     * @return 主键UUID
     */
    public String getDatauuid() {
        return datauuid;
    }

    /**
     * 设置 主键UUID
     * @param datauuid 主键UUID
     */
    public void setDatauuid(String datauuid) {
        this.datauuid = datauuid == null ? null : datauuid.trim();
    }

    /**
     * 取得 债券类型
     * @return 债券类型
     */
    public String getSecType() {
        return secType;
    }

    /**
     * 设置 债券类型
     * @param secType 债券类型
     */
    public void setSecType(String secType) {
        this.secType = secType == null ? null : secType.trim();
    }

    /**
     * 取得 发行方式
     * @return 发行方式
     */
    public String getIssueType() {
        return issueType;
    }

    /**
     * 设置 发行方式
     * @param issueType 发行方式
     */
    public void setIssueType(String issueType) {
        this.issueType = issueType == null ? null : issueType.trim();
    }

    /**
     * 取得 评级类型
     * @return 评级类型
     */
    public String getGradeType() {
        return gradeType;
    }

    /**
     * 设置 评级类型
     * @param gradeType 评级类型
     */
    public void setGradeType(String gradeType) {
        this.gradeType = gradeType == null ? null : gradeType.trim();
    }

    /**
     * 取得 折扣率（%）
     * @return 折扣率（%）
     */
    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    /**
     * 设置 折扣率（%）
     * @param discountRate 折扣率（%）
     */
    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }
}