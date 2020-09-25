package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class RepInvestdetail extends BaseEntity {
    /**
     * 主键
     */
    private String seqno;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 估值日
     */
    private Date valDate;

    /**
     * 资产类别代码
     */
    private String assetTypeCode;

    /**
     * 资产估值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal assetsValueNet;

    /**
     * 资产估值占组合比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal assetsValueNetScale;

    /**
     * 是否超限
     */
    private String isScaleOut;

    /**
     * 取得 主键
     * @return 主键
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 主键
     * @param seqno 主键
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品代码
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 估值日
     * @return 估值日
     */
    public Date getValDate() {
        return valDate;
    }

    /**
     * 设置 估值日
     * @param valDate 估值日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setValDate(Date valDate) {
        this.valDate = valDate;
    }

    /**
     * 取得 资产类别代码
     * @return 资产类别代码
     */
    public String getAssetTypeCode() {
        return assetTypeCode;
    }

    /**
     * 设置 资产类别代码
     * @param assetTypeCode 资产类别代码
     */
    public void setAssetTypeCode(String assetTypeCode) {
        this.assetTypeCode = assetTypeCode == null ? null : assetTypeCode.trim();
    }

    /**
     * 取得 资产估值
     * @return 资产估值
     */
    public BigDecimal getAssetsValueNet() {
        return assetsValueNet;
    }

    /**
     * 设置 资产估值
     * @param assetsValueNet 资产估值
     */
    public void setAssetsValueNet(BigDecimal assetsValueNet) {
        this.assetsValueNet = assetsValueNet;
    }

    /**
     * 取得 资产估值占组合比例
     * @return 资产估值占组合比例
     */
    public BigDecimal getAssetsValueNetScale() {
        return assetsValueNetScale;
    }

    /**
     * 设置 资产估值占组合比例
     * @param assetsValueNetScale 资产估值占组合比例
     */
    public void setAssetsValueNetScale(BigDecimal assetsValueNetScale) {
        this.assetsValueNetScale = assetsValueNetScale;
    }

    /**
     * 取得 是否超限
     * @return 是否超限
     */
    public String getIsScaleOut() {
        return isScaleOut;
    }

    /**
     * 设置 是否超限
     * @param isScaleOut 是否超限
     */
    public void setIsScaleOut(String isScaleOut) {
        this.isScaleOut = isScaleOut;
    }
}