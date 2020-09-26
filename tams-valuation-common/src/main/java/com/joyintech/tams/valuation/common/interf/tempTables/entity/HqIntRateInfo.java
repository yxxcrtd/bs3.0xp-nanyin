package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class HqIntRateInfo extends BaseEntity {
    /**
     * 行情代码
     */
    private String intRateId;

    /**
     * 行情名称
     */
    private String intRateName;

    /**
     * 生效日
     */
    private Date effDate;

    /**
     * 发布日
     */
    private Date relaeseDate;

    /**
     * 利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal intRate;

    /**
     * 取得 行情代码
     * @return 行情代码
     */
    public String getIntRateId() {
        return intRateId;
    }

    /**
     * 设置 行情代码
     * @param intRateId 行情代码
     */
    public void setIntRateId(String intRateId) {
        this.intRateId = intRateId == null ? null : intRateId.trim();
    }

    /**
     * 取得 行情名称
     * @return 行情名称
     */
    public String getIntRateName() {
        return intRateName;
    }

    /**
     * 设置 行情名称
     * @param intRateName 行情名称
     */
    public void setIntRateName(String intRateName) {
        this.intRateName = intRateName == null ? null : intRateName.trim();
    }

    /**
     * 取得 生效日
     * @return 生效日
     */
    public Date getEffDate() {
        return effDate;
    }

    /**
     * 设置 生效日
     * @param effDate 生效日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    /**
     * 取得 发布日
     * @return 发布日
     */
    public Date getRelaeseDate() {
        return relaeseDate;
    }

    /**
     * 设置 发布日
     * @param relaeseDate 发布日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRelaeseDate(Date relaeseDate) {
        this.relaeseDate = relaeseDate;
    }

    /**
     * 取得 利率
     * @return 利率
     */
    public BigDecimal getIntRate() {
        return intRate;
    }

    /**
     * 设置 利率
     * @param intRate 利率
     */
    public void setIntRate(BigDecimal intRate) {
        this.intRate = intRate;
    }
}