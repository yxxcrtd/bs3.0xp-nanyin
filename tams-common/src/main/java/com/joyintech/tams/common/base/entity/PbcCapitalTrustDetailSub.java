package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class PbcCapitalTrustDetailSub extends BaseEntity {
    /**
     * 登记序列:主键uuid
     */
    private String registSeqno;

    /**
     * 主表关联id
     */
    private String reportuuid;

    /**
     * 内部产品代码
     */
    private String prodId;

    /**
     * 资产池代码
     */
    private String pbcAccountId;

    /**
     * 资产代码
     */
    private String assetCode;

    /**
     * 股权种类
     */
    private String stockType;

    /**
     * 产品发起机构编码
     */
    private String capitalTrustOrgCode;

    /**
     * 人行资金信托代码
     */
    private String pobcassetid;

    /**
     * 币种代码
     */
    private String ccy;

    /**
     * 资金信托产品余额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal capitalTrustAmount;

    /**
     * 资金信托产品余额折人民币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal capitalTrustAmountCny;

    /**
     * 取得 登记序列:主键uuid
     * @return 登记序列:主键uuid
     */
    public String getRegistSeqno() {
        return registSeqno;
    }

    /**
     * 设置 登记序列:主键uuid
     * @param registSeqno 登记序列:主键uuid
     */
    public void setRegistSeqno(String registSeqno) {
        this.registSeqno = registSeqno == null ? null : registSeqno.trim();
    }

    /**
     * 取得 主表关联id
     * @return 主表关联id
     */
    public String getReportuuid() {
        return reportuuid;
    }

    /**
     * 设置 主表关联id
     * @param reportuuid 主表关联id
     */
    public void setReportuuid(String reportuuid) {
        this.reportuuid = reportuuid == null ? null : reportuuid.trim();
    }

    /**
     * 取得 内部产品代码
     * @return 内部产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 内部产品代码
     * @param prodId 内部产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 资产池代码
     * @return 资产池代码
     */
    public String getPbcAccountId() {
        return pbcAccountId;
    }

    /**
     * 设置 资产池代码
     * @param pbcAccountId 资产池代码
     */
    public void setPbcAccountId(String pbcAccountId) {
        this.pbcAccountId = pbcAccountId == null ? null : pbcAccountId.trim();
    }

    /**
     * 取得 资产代码
     * @return 资产代码
     */
    public String getAssetCode() {
        return assetCode;
    }

    /**
     * 设置 资产代码
     * @param assetCode 资产代码
     */
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode == null ? null : assetCode.trim();
    }

    /**
     * 取得 股权种类
     * @return 股权种类
     */
    public String getStockType() {
        return stockType;
    }

    /**
     * 设置 股权种类
     * @param stockType 股权种类
     */
    public void setStockType(String stockType) {
        this.stockType = stockType == null ? null : stockType.trim();
    }

    /**
     * 取得 产品发起机构编码
     * @return 产品发起机构编码
     */
    public String getCapitalTrustOrgCode() {
        return capitalTrustOrgCode;
    }

    /**
     * 设置 产品发起机构编码
     * @param capitalTrustOrgCode 产品发起机构编码
     */
    public void setCapitalTrustOrgCode(String capitalTrustOrgCode) {
        this.capitalTrustOrgCode = capitalTrustOrgCode == null ? null : capitalTrustOrgCode.trim();
    }

    /**
     * 取得 人行资金信托代码
     * @return 人行资金信托代码
     */
    public String getPobcassetid() {
        return pobcassetid;
    }

    /**
     * 设置 人行资金信托代码
     * @param pobcassetid 人行资金信托代码
     */
    public void setPobcassetid(String pobcassetid) {
        this.pobcassetid = pobcassetid == null ? null : pobcassetid.trim();
    }

    /**
     * 取得 币种代码
     * @return 币种代码
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种代码
     * @param ccy 币种代码
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 资金信托产品余额
     * @return 资金信托产品余额
     */
    public BigDecimal getCapitalTrustAmount() {
        return capitalTrustAmount;
    }

    /**
     * 设置 资金信托产品余额
     * @param capitalTrustAmount 资金信托产品余额
     */
    public void setCapitalTrustAmount(BigDecimal capitalTrustAmount) {
        this.capitalTrustAmount = capitalTrustAmount;
    }

    /**
     * 取得 资金信托产品余额折人民币
     * @return 资金信托产品余额折人民币
     */
    public BigDecimal getCapitalTrustAmountCny() {
        return capitalTrustAmountCny;
    }

    /**
     * 设置 资金信托产品余额折人民币
     * @param capitalTrustAmountCny 资金信托产品余额折人民币
     */
    public void setCapitalTrustAmountCny(BigDecimal capitalTrustAmountCny) {
        this.capitalTrustAmountCny = capitalTrustAmountCny;
    }
}