package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class FinTermStructInfo extends BaseEntity {
    /**
     * 期限结构ID
     */
    private String termStructId;

    /**
     * 期限结构名称
     */
    private String termStructName;

    /**
     * 最小天数(含)
     */
    private Long minDays;

    /**
     * 最大天数(含)
     */
    private Long maxDays;

    /**
     * 所属期限品种
     */
    private String termStructType;

    /**
     * 取得 期限结构ID
     * @return 期限结构ID
     */
    public String getTermStructId() {
        return termStructId;
    }

    /**
     * 设置 期限结构ID
     * @param termStructId 期限结构ID
     */
    public void setTermStructId(String termStructId) {
        this.termStructId = termStructId == null ? null : termStructId.trim();
    }

    /**
     * 取得 期限结构名称
     * @return 期限结构名称
     */
    public String getTermStructName() {
        return termStructName;
    }

    /**
     * 设置 期限结构名称
     * @param termStructName 期限结构名称
     */
    public void setTermStructName(String termStructName) {
        this.termStructName = termStructName == null ? null : termStructName.trim();
    }

    /**
     * 取得 最小天数(含)
     * @return 最小天数(含)
     */
    public Long getMinDays() {
        return minDays;
    }

    /**
     * 设置 最小天数(含)
     * @param minDays 最小天数(含)
     */
    public void setMinDays(Long minDays) {
        this.minDays = minDays;
    }

    /**
     * 取得 最大天数(含)
     * @return 最大天数(含)
     */
    public Long getMaxDays() {
        return maxDays;
    }

    /**
     * 设置 最大天数(含)
     * @param maxDays 最大天数(含)
     */
    public void setMaxDays(Long maxDays) {
        this.maxDays = maxDays;
    }

    /**
     * 取得 所属期限品种
     * @return 所属期限品种
     */
    public String getTermStructType() {
        return termStructType;
    }

    /**
     * 设置 所属期限品种
     * @param termStructType 所属期限品种
     */
    public void setTermStructType(String termStructType) {
        this.termStructType = termStructType == null ? null : termStructType.trim();
    }
}