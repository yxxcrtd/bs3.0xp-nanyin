package com.joyintech.tams.svreport.chbreport.investreport.chbperiodic.dto;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;

/**
 * @ClassName ChbPeriodicPositionTop10
 * @Description: 定期报告持仓Top10
 * @Author WanShiWei
 * @Date 2021-05-07
 * @Version V1.0
 **/
public class ChbPeriodicPositionTop10 extends BaseEntity {

    private static final long serialVersionUID = 109399923067388230L;

    /**
     * 序号
     */
    private String seq;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 资产名称
     */
    private String assetName;

    /**
     * 资产类别
     */
    private String assettype2;

    /**
     * 资产类别显示名称
     */
    private String assettype2Fmt;

    /**
     * 穿透后资产
     */
    private BigDecimal AssetvalueB;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 穿透后资产显示名称
     */
    private String assetvalueBFmt;

    /**
     * 占总资产比例
     */
    private BigDecimal percentAssetvalueB;

    /**
     * 占总资产比例显示名称
     */
    private String percentAssetvalueBFmt;

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getAssettype2() {
        return assettype2;
    }

    public void setAssettype2(String assettype2) {
        this.assettype2 = assettype2;
    }

    public String getAssettype2Fmt() {
        return assettype2Fmt;
    }

    public void setAssettype2Fmt(String assettype2Fmt) {
        this.assettype2Fmt = assettype2Fmt;
    }

    public BigDecimal getAssetvalueB() {
        return AssetvalueB;
    }

    public void setAssetvalueB(BigDecimal assetvalueB) {
        AssetvalueB = assetvalueB;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getAssetvalueBFmt() {
        return assetvalueBFmt;
    }

    public void setAssetvalueBFmt(String assetvalueBFmt) {
        this.assetvalueBFmt = assetvalueBFmt;
    }

    public BigDecimal getPercentAssetvalueB() {
        return percentAssetvalueB;
    }

    public void setPercentAssetvalueB(BigDecimal percentAssetvalueB) {
        this.percentAssetvalueB = percentAssetvalueB;
    }

    public String getPercentAssetvalueBFmt() {
        return percentAssetvalueBFmt;
    }

    public void setPercentAssetvalueBFmt(String percentAssetvalueBFmt) {
        this.percentAssetvalueBFmt = percentAssetvalueBFmt;
    }
}
