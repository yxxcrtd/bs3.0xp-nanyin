package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.workflow.engine.annotation.PrimaryKey;

import java.math.BigDecimal;
import java.util.Date;

public class DisProdAsset extends BaseEntity {
    /**
     * 主键编号
     */
    @PrimaryKey(value = PrimaryKey.Field.PK_1)
    private String prodAssetId;

    /**
     * 主产品代码
     */
    @PrimaryKey(value = PrimaryKey.Field.PK_2)
    private String prodId;

    /**
     * 固定资产穿透前占总资产的比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fixPreRatio;

    /**
     * 固定资产穿透后占总资产的比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fixAfterRatio;

    /**
     * 权益类资产穿透前占总资产的比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal equityPreRatio;

    /**
     * 权益类资产穿透后占总资产的比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal equityAfterRatio;

    /**
     * 商品及金融衍生品资产穿透前占总资产的比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fdPreRatio;

    /**
     * 商品及金融衍生品资产穿透后占总资产的比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fdAfterRatio;

    /**
     * 混合类资产穿透前占总资产的比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mixPreRatio;

    /**
     * 混合类资产穿透后占总资产的比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mixAfterRatio;

    /**
     * 生效日
     */
    private Date effDate;

    /**
     * 生效截至日
     */
    private Date effEndDate;

    /**
     * 数据状态
     */
    private String dataStatus;

    /**
     * 数据来源，DATA_SOURCE
     */
    private String dataSource;

    /**
     * 取得 主键编号
     * @return 主键编号
     */
    public String getProdAssetId() {
        return prodAssetId;
    }

    /**
     * 设置 主键编号
     * @param prodAssetId 主键编号
     */
    public void setProdAssetId(String prodAssetId) {
        this.prodAssetId = prodAssetId == null ? null : prodAssetId.trim();
    }

    /**
     * 取得 主产品代码
     * @return 主产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 主产品代码
     * @param prodId 主产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 固定资产穿透前占总资产的比例
     * @return 固定资产穿透前占总资产的比例
     */
    public BigDecimal getFixPreRatio() {
        return fixPreRatio;
    }

    /**
     * 设置 固定资产穿透前占总资产的比例
     * @param fixPreRatio 固定资产穿透前占总资产的比例
     */
    public void setFixPreRatio(BigDecimal fixPreRatio) {
        this.fixPreRatio = fixPreRatio;
    }

    /**
     * 取得 固定资产穿透后占总资产的比例
     * @return 固定资产穿透后占总资产的比例
     */
    public BigDecimal getFixAfterRatio() {
        return fixAfterRatio;
    }

    /**
     * 设置 固定资产穿透后占总资产的比例
     * @param fixAfterRatio 固定资产穿透后占总资产的比例
     */
    public void setFixAfterRatio(BigDecimal fixAfterRatio) {
        this.fixAfterRatio = fixAfterRatio;
    }

    /**
     * 取得 权益类资产穿透前占总资产的比例
     * @return 权益类资产穿透前占总资产的比例
     */
    public BigDecimal getEquityPreRatio() {
        return equityPreRatio;
    }

    /**
     * 设置 权益类资产穿透前占总资产的比例
     * @param equityPreRatio 权益类资产穿透前占总资产的比例
     */
    public void setEquityPreRatio(BigDecimal equityPreRatio) {
        this.equityPreRatio = equityPreRatio;
    }

    /**
     * 取得 权益类资产穿透后占总资产的比例
     * @return 权益类资产穿透后占总资产的比例
     */
    public BigDecimal getEquityAfterRatio() {
        return equityAfterRatio;
    }

    /**
     * 设置 权益类资产穿透后占总资产的比例
     * @param equityAfterRatio 权益类资产穿透后占总资产的比例
     */
    public void setEquityAfterRatio(BigDecimal equityAfterRatio) {
        this.equityAfterRatio = equityAfterRatio;
    }

    /**
     * 取得 商品及金融衍生品资产穿透前占总资产的比例
     * @return 商品及金融衍生品资产穿透前占总资产的比例
     */
    public BigDecimal getFdPreRatio() {
        return fdPreRatio;
    }

    /**
     * 设置 商品及金融衍生品资产穿透前占总资产的比例
     * @param fdPreRatio 商品及金融衍生品资产穿透前占总资产的比例
     */
    public void setFdPreRatio(BigDecimal fdPreRatio) {
        this.fdPreRatio = fdPreRatio;
    }

    /**
     * 取得 商品及金融衍生品资产穿透后占总资产的比例
     * @return 商品及金融衍生品资产穿透后占总资产的比例
     */
    public BigDecimal getFdAfterRatio() {
        return fdAfterRatio;
    }

    /**
     * 设置 商品及金融衍生品资产穿透后占总资产的比例
     * @param fdAfterRatio 商品及金融衍生品资产穿透后占总资产的比例
     */
    public void setFdAfterRatio(BigDecimal fdAfterRatio) {
        this.fdAfterRatio = fdAfterRatio;
    }

    /**
     * 取得 混合类资产穿透前占总资产的比例
     * @return 混合类资产穿透前占总资产的比例
     */
    public BigDecimal getMixPreRatio() {
        return mixPreRatio;
    }

    /**
     * 设置 混合类资产穿透前占总资产的比例
     * @param mixPreRatio 混合类资产穿透前占总资产的比例
     */
    public void setMixPreRatio(BigDecimal mixPreRatio) {
        this.mixPreRatio = mixPreRatio;
    }

    /**
     * 取得 混合类资产穿透后占总资产的比例
     * @return 混合类资产穿透后占总资产的比例
     */
    public BigDecimal getMixAfterRatio() {
        return mixAfterRatio;
    }

    /**
     * 设置 混合类资产穿透后占总资产的比例
     * @param mixAfterRatio 混合类资产穿透后占总资产的比例
     */
    public void setMixAfterRatio(BigDecimal mixAfterRatio) {
        this.mixAfterRatio = mixAfterRatio;
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
     * 取得 生效截至日
     * @return 生效截至日
     */
    public Date getEffEndDate() {
        return effEndDate;
    }

    /**
     * 设置 生效截至日
     * @param effEndDate 生效截至日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEffEndDate(Date effEndDate) {
        this.effEndDate = effEndDate;
    }

    /**
     * 取得 数据状态
     * @return 数据状态
     */
    public String getDataStatus() {
        return dataStatus;
    }

    /**
     * 设置 数据状态
     * @param dataStatus 数据状态
     */
    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus == null ? null : dataStatus.trim();
    }

    /**
     * 取得 数据来源，DATA_SOURCE
     * @return 数据来源，DATA_SOURCE
     */
    public String getDataSource() {
        return dataSource;
    }

    /**
     * 设置 数据来源，DATA_SOURCE
     * @param dataSource 数据来源，DATA_SOURCE
     */
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource == null ? null : dataSource.trim();
    }
}