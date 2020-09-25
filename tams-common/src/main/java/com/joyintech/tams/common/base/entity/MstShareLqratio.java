package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class MstShareLqratio extends MstShareLqratioKey {
    /**
     * 流动性折扣
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal ratio;

    /**
     * 限售期结束日
     */
    private Date mDate;

    /**
     * 股份变动公告日
     */
    private Date issueDate;

    /**
     * 所属行业
     */
    private String industry;

    /**
     * 对应行业指数
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal industryIndex;

    /**
     * 剩余限售期
     */
    private Long limitationPeriod;

    /**
     * 录入方式
     */
    private String inputType;

    /**
     * 取得 流动性折扣
     * @return 流动性折扣
     */
    public BigDecimal getRatio() {
        return ratio;
    }

    /**
     * 设置 流动性折扣
     * @param ratio 流动性折扣
     */
    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    /**
     * 取得 限售期结束日
     * @return 限售期结束日
     */
    public Date getmDate() {
        return mDate;
    }

    /**
     * 设置 限售期结束日
     * @param mDate 限售期结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    /**
     * 取得 股份变动公告日
     * @return 股份变动公告日
     */
    public Date getIssueDate() {
        return issueDate;
    }

    /**
     * 设置 股份变动公告日
     * @param issueDate 股份变动公告日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    /**
     * 取得 所属行业
     * @return 所属行业
     */
    public String getIndustry() {
        return industry;
    }

    /**
     * 设置 所属行业
     * @param industry 所属行业
     */
    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    /**
     * 取得 对应行业指数
     * @return 对应行业指数
     */
    public BigDecimal getIndustryIndex() {
        return industryIndex;
    }

    /**
     * 设置 对应行业指数
     * @param industryIndex 对应行业指数
     */
    public void setIndustryIndex(BigDecimal industryIndex) {
        this.industryIndex = industryIndex;
    }

    /**
     * 取得 剩余限售期
     * @return 剩余限售期
     */
    public Long getLimitationPeriod() {
        return limitationPeriod;
    }

    /**
     * 设置 剩余限售期
     * @param limitationPeriod 剩余限售期
     */
    public void setLimitationPeriod(Long limitationPeriod) {
        this.limitationPeriod = limitationPeriod;
    }

    /**
     * 取得 录入方式
     * @return 录入方式
     */
    public String getInputType() {
        return inputType;
    }

    /**
     * 设置 录入方式
     * @param inputType 录入方式
     */
    public void setInputType(String inputType) {
        this.inputType = inputType == null ? null : inputType.trim();
    }
}