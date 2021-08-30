package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class ModBintreeConfig extends BaseEntity {
    /**
     * 流水号
     */
    private String seqno;

    /**
     * 金融产品代码，存储上游系统内部代码，比如金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品名称
     */
    private String finprodName;

    /**
     * 标的公司上市状态
     */
    private String stockBordsttus;

    /**
     * 标的股票代码
     */
    private String stockId;

    /**
     * 可比公司
     */
    private String compOrg;

    /**
     * 市场乘数方法，数据字典.市场乘数方法
     */
    private String marketMulti;

    /**
     * 可比公司名称
     */
    private String compOrgname;

    /**
     * 可比公司股票代码
     */
    private String compStockid;

    /**
     * 标的公司每股收益
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal finprodProit;

    /**
     * 标的公司每股净资产
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal finprodNetasset;

    /**
     * 连续股息率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal smoothsection;

    /**
     * 二叉树步数
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bintreeStep;

    /**
     * 无风险曲线代码
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal curveCode;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 流水号
     * @param seqno 流水号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 金融产品代码，存储上游系统内部代码，比如金融产品代码
     * @return 金融产品代码，存储上游系统内部代码，比如金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码，存储上游系统内部代码，比如金融产品代码
     * @param finprodId 金融产品代码，存储上游系统内部代码，比如金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 金融产品名称
     * @return 金融产品名称
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * 设置 金融产品名称
     * @param finprodName 金融产品名称
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName == null ? null : finprodName.trim();
    }

    /**
     * 取得 标的公司上市状态
     * @return 标的公司上市状态
     */
    public String getStockBordsttus() {
        return stockBordsttus;
    }

    /**
     * 设置 标的公司上市状态
     * @param stockBordsttus 标的公司上市状态
     */
    public void setStockBordsttus(String stockBordsttus) {
        this.stockBordsttus = stockBordsttus == null ? null : stockBordsttus.trim();
    }

    /**
     * 取得 标的股票代码
     * @return 标的股票代码
     */
    public String getStockId() {
        return stockId;
    }

    /**
     * 设置 标的股票代码
     * @param stockId 标的股票代码
     */
    public void setStockId(String stockId) {
        this.stockId = stockId == null ? null : stockId.trim();
    }

    /**
     * 取得 可比公司
     * @return 可比公司
     */
    public String getCompOrg() {
        return compOrg;
    }

    /**
     * 设置 可比公司
     * @param compOrg 可比公司
     */
    public void setCompOrg(String compOrg) {
        this.compOrg = compOrg == null ? null : compOrg.trim();
    }

    /**
     * 取得 市场乘数方法，数据字典.市场乘数方法
     * @return 市场乘数方法，数据字典.市场乘数方法
     */
    public String getMarketMulti() {
        return marketMulti;
    }

    /**
     * 设置 市场乘数方法，数据字典.市场乘数方法
     * @param marketMulti 市场乘数方法，数据字典.市场乘数方法
     */
    public void setMarketMulti(String marketMulti) {
        this.marketMulti = marketMulti == null ? null : marketMulti.trim();
    }

    /**
     * 取得 可比公司名称
     * @return 可比公司名称
     */
    public String getCompOrgname() {
        return compOrgname;
    }

    /**
     * 设置 可比公司名称
     * @param compOrgname 可比公司名称
     */
    public void setCompOrgname(String compOrgname) {
        this.compOrgname = compOrgname == null ? null : compOrgname.trim();
    }

    /**
     * 取得 可比公司股票代码
     * @return 可比公司股票代码
     */
    public String getCompStockid() {
        return compStockid;
    }

    /**
     * 设置 可比公司股票代码
     * @param compStockid 可比公司股票代码
     */
    public void setCompStockid(String compStockid) {
        this.compStockid = compStockid == null ? null : compStockid.trim();
    }

    /**
     * 取得 标的公司每股收益
     * @return 标的公司每股收益
     */
    public BigDecimal getFinprodProit() {
        return finprodProit;
    }

    /**
     * 设置 标的公司每股收益
     * @param finprodProit 标的公司每股收益
     */
    public void setFinprodProit(BigDecimal finprodProit) {
        this.finprodProit = finprodProit;
    }

    /**
     * 取得 标的公司每股净资产
     * @return 标的公司每股净资产
     */
    public BigDecimal getFinprodNetasset() {
        return finprodNetasset;
    }

    /**
     * 设置 标的公司每股净资产
     * @param finprodNetasset 标的公司每股净资产
     */
    public void setFinprodNetasset(BigDecimal finprodNetasset) {
        this.finprodNetasset = finprodNetasset;
    }

    /**
     * 取得 连续股息率
     * @return 连续股息率
     */
    public BigDecimal getSmoothsection() {
        return smoothsection;
    }

    /**
     * 设置 连续股息率
     * @param smoothsection 连续股息率
     */
    public void setSmoothsection(BigDecimal smoothsection) {
        this.smoothsection = smoothsection;
    }

    /**
     * 取得 二叉树步数
     * @return 二叉树步数
     */
    public BigDecimal getBintreeStep() {
        return bintreeStep;
    }

    /**
     * 设置 二叉树步数
     * @param bintreeStep 二叉树步数
     */
    public void setBintreeStep(BigDecimal bintreeStep) {
        this.bintreeStep = bintreeStep;
    }

    /**
     * 取得 无风险曲线代码
     * @return 无风险曲线代码
     */
    public BigDecimal getCurveCode() {
        return curveCode;
    }

    /**
     * 设置 无风险曲线代码
     * @param curveCode 无风险曲线代码
     */
    public void setCurveCode(BigDecimal curveCode) {
        this.curveCode = curveCode;
    }
}