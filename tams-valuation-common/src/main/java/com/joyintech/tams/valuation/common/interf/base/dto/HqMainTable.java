package com.joyintech.tams.valuation.common.interf.base.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

public class HqMainTable extends BaseEntity {
    /**
     * 债券名称
     */
    private String bdShrtNm;
    /**
     * 债券代码
     */
    private String bdId;
    /**
     * 估值日期
     */
    private Date valtnDt;
    /**
     * 交易场所
     */
    private String listgMkt;
    /**
     * 日间估值全价
     */
    private BigDecimal valtdDrtyP;
    /**
     * 日间应计利息
     */
    private BigDecimal acrdIntrst;
    /**
     * 估值净价
     */
    private BigDecimal valtdClnPr;
    /**
     * 估价收益率
     */
    private BigDecimal valtdYld;
    /**
     * 估价基点价值
     */
    private BigDecimal valtdPVBP;
    /**
     * 真实全价
     */
    private BigDecimal mktDrtyPri;
    /**
     * 真实净价
     */
    private BigDecimal mktClnPric;
    /**
     * 真实收益率
     */
    private BigDecimal mktYld;
    /**
     * 真实基点价值
     */
    private BigDecimal mktPVBP;

    /**
     *  取得 债券名称
     *  @return 债券名称
     */
    public String getBdShrtNm() {
       return bdShrtNm;
    }

    /**
     *  设置 债券名称
     *  @param bdShrtNm 债券名称
     */
    public void setBdShrtNm( String bdShrtNm)  {
       this.bdShrtNm = bdShrtNm;
    }

    /**
     *  取得 债券代码
     *  @return 债券代码
     */
    public String getBdId() {
       return bdId;
    }

    /**
     *  设置 债券代码
     *  @param bdId 债券代码
     */
    public void setBdId( String bdId)  {
       this.bdId = bdId;
    }

    /**
     *  取得 估值日期
     *  @return 估值日期
     */
    public Date getValtnDt() {
       return valtnDt;
    }

    /**
     *  设置 估值日期
     *  @param valtnDt 估值日期
     */
    public void setValtnDt( Date valtnDt)  {
       this.valtnDt = valtnDt;
    }

    /**
     *  取得 交易场所
     *  @return 交易场所
     */
    public String getListgMkt() {
       return listgMkt;
    }

    /**
     *  设置 交易场所
     *  @param listgMkt 交易场所
     */
    public void setListgMkt( String listgMkt)  {
       this.listgMkt = listgMkt;
    }

    /**
     *  取得 日间估值全价
     *  @return 日间估值全价
     */
    public BigDecimal getValtdDrtyP() {
       return valtdDrtyP;
    }

    /**
     *  设置 日间估值全价
     *  @param valtdDrtyP 日间估值全价
     */
    public void setValtdDrtyP( BigDecimal valtdDrtyP)  {
       this.valtdDrtyP = valtdDrtyP;
    }

    /**
     *  取得 日间应计利息
     *  @return 日间应计利息
     */
    public BigDecimal getAcrdIntrst() {
       return acrdIntrst;
    }

    /**
     *  设置 日间应计利息
     *  @param acrdIntrst 日间应计利息
     */
    public void setAcrdIntrst( BigDecimal acrdIntrst)  {
       this.acrdIntrst = acrdIntrst;
    }

    /**
     *  取得 估值净价
     *  @return 估值净价
     */
    public BigDecimal getValtdClnPr() {
       return valtdClnPr;
    }

    /**
     *  设置 估值净价
     *  @param valtdClnpr 估值净价
     */
    public void setValtdClnPr( BigDecimal valtdClnPr)  {
       this.valtdClnPr = valtdClnPr;
    }

    /**
     *  取得 估价收益率
     *  @return 估价收益率
     */
    public BigDecimal getValtdYld() {
       return valtdYld;
    }

    /**
     *  设置 估价收益率
     *  @param valtdYld 估价收益率
     */
    public void setValtdYld( BigDecimal valtdYld)  {
       this.valtdYld = valtdYld;
    }

    /**
     *  取得 估价基点价值
     *  @return 估价基点价值
     */
    public BigDecimal getValtdPVBP() {
       return valtdPVBP;
    }

    /**
     *  设置 估价基点价值
     *  @param valtdPVBP 估价基点价值
     */
    public void setValtdPVBP( BigDecimal valtdPVBP)  {
       this.valtdPVBP = valtdPVBP;
    }

    /**
     *  取得 真实全价
     *  @return 真实全价
     */
    public BigDecimal getMktDrtyPri() {
       return mktDrtyPri;
    }

    /**
     *  设置 真实全价
     *  @param mktDrtyPri 真实全价
     */
    public void setMktDrtyPri( BigDecimal mktDrtyPri)  {
       this.mktDrtyPri = mktDrtyPri;
    }

    /**
     *  取得 真实净价
     *  @return 真实净价
     */
    public BigDecimal getMktClnPric() {
       return mktClnPric;
    }

    /**
     *  设置 真实净价
     *  @param mktClnPric 真实净价
     */
    public void setMktClnPric( BigDecimal mktClnPric)  {
       this.mktClnPric = mktClnPric;
    }

    /**
     *  取得 真实收益率
     *  @return 真实收益率
     */
    public BigDecimal getMktYld() {
       return mktYld;
    }

    /**
     *  设置 真实收益率
     *  @param mktYld 真实收益率
     */
    public void setMktYld( BigDecimal mktYld)  {
       this.mktYld = mktYld;
    }

    /**
     *  取得 真实基点价值
     *  @return 真实基点价值
     */
    public BigDecimal getMktPVBP() {
       return mktPVBP;
    }

    /**
     *  设置 真实基点价值
     *  @param mktPVBP 真实基点价值
     */
    public void setMktPVBP( BigDecimal mktPVBP)  {
       this.mktPVBP = mktPVBP;
    }

}