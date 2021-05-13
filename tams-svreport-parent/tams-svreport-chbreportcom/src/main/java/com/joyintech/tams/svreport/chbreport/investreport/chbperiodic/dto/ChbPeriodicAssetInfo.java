package com.joyintech.tams.svreport.chbreport.investreport.chbperiodic.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName ChbPeriodicPrdInfo
 * @Description: 定期报告查询及资产基本信息
 * @Author WanShiWei
 * @Date 2021-05-07
 * @Version V1.0
 **/
public class ChbPeriodicAssetInfo extends BaseEntity {

    private static final long serialVersionUID = 109399923067388230L;

    /**
     * 序号
     */
    private String seq;

    /**
     * 资产名称
     */
    private String assetName;

    /**
     * 资产/负债二级类别 参考数据字典:ASSET_TYPE_SECOND_LEVEL
     */
    private String assetType2;

    /**
     * 资产/负债二级类别显示名称
     */
    private String assetType2Fmt;

    /**
     * 融资人
     */
    private String financie;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 剩余期限
     */
    private String surplusTerm;

    /**
     * 收益率/年利率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal assetYield;

    private String assetYieldFmt;

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getAssetType2() {
        return assetType2;
    }

    public void setAssetType2(String assetType2) {
        this.assetType2 = assetType2;
    }

    public String getAssetType2Fmt() {
        return assetType2Fmt;
    }

    public void setAssetType2Fmt(String assetType2Fmt) {
        this.assetType2Fmt = assetType2Fmt;
    }

    public String getFinancie() {
        return financie;
    }

    public void setFinancie(String financie) {
        this.financie = financie;
    }

    public Date getMdate() {
        return mdate;
    }

    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    public BigDecimal getAssetYield() {
        return assetYield;
    }

    public void setAssetYield(BigDecimal assetYield) {
        this.assetYield = assetYield;
    }

    public String getAssetYieldFmt() {
        return assetYieldFmt;
    }

    public void setAssetYieldFmt(String assetYieldFmt) {
        this.assetYieldFmt = assetYieldFmt;
    }

    public String getSurplusTerm() {
        return surplusTerm;
    }

    public void setSurplusTerm(String surplusTerm) {
        this.surplusTerm = surplusTerm;
    }
}
