package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ChbAssetRate extends BaseEntity {
    /**
     * 交易编号
     */
    private String seqno;

    /**
     * 资产代码
     */
    private String assetId;

    /**
     * 资产类型
     */
    private String finprodType2;

    /**
     * 资产收益类型
     */
    private String profitType;

    /**
     * 变动类型
     */
    private String changeType;

    /**
     * 变动日期
     */
    private Date changeDate;

    /**
     * 资产最新收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal assetRate;

    /**
     * 资产分红或红利再投单位净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal assetBonusreinvestRate;

    /**
     * 收盘价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal closePriceFull;

    /**
     * 成本
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal costAmt;

    /**
     * 交易编号
     */
    private String tradeId;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 撤销日
     */
    private Date revDate;

    /**
     * 数据来源
     */
    private String dataSource;

    /**
     * 备注
     */
    private String remark;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 资金账户流水号
     */
    private String scino;

    /**
     * 取得 交易编号
     * @return 交易编号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 交易编号
     * @param seqno 交易编号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 资产代码
     * @return 资产代码
     */
    public String getAssetId() {
        return assetId;
    }

    /**
     * 设置 资产代码
     * @param assetId 资产代码
     */
    public void setAssetId(String assetId) {
        this.assetId = assetId == null ? null : assetId.trim();
    }

    /**
     * 取得 资产类型
     * @return 资产类型
     */
    public String getFinprodType2() {
        return finprodType2;
    }

    /**
     * 设置 资产类型
     * @param finprodType2 资产类型
     */
    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2 == null ? null : finprodType2.trim();
    }

    /**
     * 取得 资产收益类型
     * @return 资产收益类型
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 资产收益类型
     * @param profitType 资产收益类型
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType == null ? null : profitType.trim();
    }

    /**
     * 取得 变动类型
     * @return 变动类型
     */
    public String getChangeType() {
        return changeType;
    }

    /**
     * 设置 变动类型
     * @param changeType 变动类型
     */
    public void setChangeType(String changeType) {
        this.changeType = changeType == null ? null : changeType.trim();
    }

    /**
     * 取得 变动日期
     * @return 变动日期
     */
    public Date getChangeDate() {
        return changeDate;
    }

    /**
     * 设置 变动日期
     * @param changeDate 变动日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    /**
     * 取得 资产最新收益率
     * @return 资产最新收益率
     */
    public BigDecimal getAssetRate() {
        return assetRate;
    }

    /**
     * 设置 资产最新收益率
     * @param assetRate 资产最新收益率
     */
    public void setAssetRate(BigDecimal assetRate) {
        this.assetRate = assetRate;
    }

    /**
     * 取得 资产分红或红利再投单位净值
     * @return 资产分红或红利再投单位净值
     */
    public BigDecimal getAssetBonusreinvestRate() {
        return assetBonusreinvestRate;
    }

    /**
     * 设置 资产分红或红利再投单位净值
     * @param assetBonusreinvestRate 资产分红或红利再投单位净值
     */
    public void setAssetBonusreinvestRate(BigDecimal assetBonusreinvestRate) {
        this.assetBonusreinvestRate = assetBonusreinvestRate;
    }

    /**
     * 取得 收盘价
     * @return 收盘价
     */
    public BigDecimal getClosePriceFull() {
        return closePriceFull;
    }

    /**
     * 设置 收盘价
     * @param closePriceFull 收盘价
     */
    public void setClosePriceFull(BigDecimal closePriceFull) {
        this.closePriceFull = closePriceFull;
    }

    /**
     * 取得 成本
     * @return 成本
     */
    public BigDecimal getCostAmt() {
        return costAmt;
    }

    /**
     * 设置 成本
     * @param costAmt 成本
     */
    public void setCostAmt(BigDecimal costAmt) {
        this.costAmt = costAmt;
    }

    /**
     * 取得 交易编号
     * @return 交易编号
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 交易编号
     * @param tradeId 交易编号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    /**
     * 取得 机构代码
     * @return 机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 设置 机构代码
     * @param orgCode 机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    /**
     * 取得 部门代码
     * @return 部门代码
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 设置 部门代码
     * @param deptCode 部门代码
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    /**
     * 取得 撤销日
     * @return 撤销日
     */
    public Date getRevDate() {
        return revDate;
    }

    /**
     * 设置 撤销日
     * @param revDate 撤销日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRevDate(Date revDate) {
        this.revDate = revDate;
    }

    /**
     * 取得 数据来源
     * @return 数据来源
     */
    public String getDataSource() {
        return dataSource;
    }

    /**
     * 设置 数据来源
     * @param dataSource 数据来源
     */
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource == null ? null : dataSource.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 取得 币种
     * @return 币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 资金账户流水号
     * @return 资金账户流水号
     */
    public String getScino() {
        return scino;
    }

    /**
     * 设置 资金账户流水号
     * @param scino 资金账户流水号
     */
    public void setScino(String scino) {
        this.scino = scino == null ? null : scino.trim();
    }
}