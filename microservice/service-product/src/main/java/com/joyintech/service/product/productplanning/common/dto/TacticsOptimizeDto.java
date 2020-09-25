package com.joyintech.service.product.productplanning.common.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;
import java.util.Date;

public class TacticsOptimizeDto {
    /**
     * 战术配置编号
     */
    private String tacticConfigCode;
    /**
     * 开始日
     */
    private Date vdate;
    /**
     * 结束日
     */
    private Date edate;
    /**
     * 投研支持观点
     */
    private String opinionRinv;
    /**
     * 备注
     */
    private String remark;
    /**
     * 资产大类
     */
    private String assetType;
    /**
     * 理论配置比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal theoConfigRate;
    /**
     * 样本平均收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal samleAverRate;

    /**
     * 波动率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal vix;
    /**
     * 修正比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal corRate;
    /**
     * 修正场景
     */
    private String corScene;
    /**
     * 修正后占比
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal corRatio;
    /**
     * 修正观点
     */
    private String corView;

    public String getTacticConfigCode() {
        return tacticConfigCode;
    }

    public void setTacticConfigCode(String tacticConfigCode) {
        this.tacticConfigCode = tacticConfigCode;
    }

    public Date getVdate() {
        return vdate;
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public Date getEdate() {
        return edate;
    }

    public void setEdate(Date edate) {
        this.edate = edate;
    }

    public String getOpinionRinv() {
        return opinionRinv;
    }

    public void setOpinionRinv(String opinionRinv) {
        this.opinionRinv = opinionRinv;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public BigDecimal getTheoConfigRate() {
        return theoConfigRate;
    }

    public void setTheoConfigRate(BigDecimal theoConfigRate) {
        this.theoConfigRate = theoConfigRate;
    }

    public BigDecimal getSamleAverRate() {
        return samleAverRate;
    }

    public void setSamleAverRate(BigDecimal samleAverRate) {
        this.samleAverRate = samleAverRate;
    }

    public BigDecimal getVix() {
        return vix;
    }

    public void setVix(BigDecimal vix) {
        this.vix = vix;
    }

    public BigDecimal getCorRate() {
        return corRate;
    }

    public void setCorRate(BigDecimal corRate) {
        this.corRate = corRate;
    }

    public String getCorScene() {
        return corScene;
    }

    public void setCorScene(String corScene) {
        this.corScene = corScene;
    }

    public BigDecimal getCorRatio() {
        return corRatio;
    }

    public void setCorRatio(BigDecimal corRatio) {
        this.corRatio = corRatio;
    }

    public String getCorView() {
        return corView;
    }

    public void setCorView(String corView) {
        this.corView = corView;
    }
}
