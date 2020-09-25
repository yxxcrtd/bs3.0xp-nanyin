package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class AssetConfigPlan extends BaseEntity {
    /**
     * 资产配置编号
     */
    private String assetConfigCode;

    /**
     * 生成日
     */
    private Date genDate;

    /**
     * 基准日
     */
    private Date benchDate;

    /**
     * 配置月份
     */
    private Date configMonth;

    /**
     * 目标总规模
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tagetScale;

    /**
     * 取得 资产配置编号
     * @return 资产配置编号
     */
    public String getAssetConfigCode() {
        return assetConfigCode;
    }

    /**
     * 设置 资产配置编号
     * @param assetConfigCode 资产配置编号
     */
    public void setAssetConfigCode(String assetConfigCode) {
        this.assetConfigCode = assetConfigCode == null ? null : assetConfigCode.trim();
    }

    /**
     * 取得 生成日
     * @return 生成日
     */
    public Date getGenDate() {
        return genDate;
    }

    /**
     * 设置 生成日
     * @param genDate 生成日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setGenDate(Date genDate) {
        this.genDate = genDate;
    }

    /**
     * 取得 基准日
     * @return 基准日
     */
    public Date getBenchDate() {
        return benchDate;
    }

    /**
     * 设置 基准日
     * @param benchDate 基准日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setBenchDate(Date benchDate) {
        this.benchDate = benchDate;
    }

    /**
     * 取得 配置月份
     * @return 配置月份
     */
    public Date getConfigMonth() {
        return configMonth;
    }

    /**
     * 设置 配置月份
     * @param configMonth 配置月份
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setConfigMonth(Date configMonth) {
        this.configMonth = configMonth;
    }

    /**
     * 取得 目标总规模
     * @return 目标总规模
     */
    public BigDecimal getTagetScale() {
        return tagetScale;
    }

    /**
     * 设置 目标总规模
     * @param tagetScale 目标总规模
     */
    public void setTagetScale(BigDecimal tagetScale) {
        this.tagetScale = tagetScale;
    }
}