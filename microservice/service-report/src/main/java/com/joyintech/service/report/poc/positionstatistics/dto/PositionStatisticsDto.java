/*
 * 文件名：SecAssetStatistics001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年10月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.poc.positionstatistics.dto;

import java.math.BigDecimal;

import com.joyintech.framework.base.BaseEntity;

/**
 * 头寸统计表
 * 〈功能详细描述〉
 * @author liupeilin
 * @version 1.0 2019年11月6日
 * @see PositionStatisticsDto
 * @since 1.0
 */
public class PositionStatisticsDto extends BaseEntity{
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    private String cdate;//统计日期
    private BigDecimal prodestablish;//产品成立
    private BigDecimal nhgexpire;//逆回购到期
    private BigDecimal zhgsq;//正回购首期
    private BigDecimal ccexpire;//拆出到期
    private BigDecimal crsq;//拆入首期
    private BigDecimal assetsale;//资产卖出
    private BigDecimal assetexpire;//资产到期
    private BigDecimal assetpayments;//资产中间收息
    private BigDecimal calclr;//现金流入-小计
    private BigDecimal prodexpire;//产品到期
    private BigDecimal nhgsq;//逆回购首期
    private BigDecimal zhgexpire;//正回购到期
    private BigDecimal ccsq;//拆出首期
    private BigDecimal crexpire;//拆入到期
    private BigDecimal assetbuy;//资产买入
    private BigDecimal calclc;//现金流出-小计
    private BigDecimal cleancashflow;//净现金流
    
    /**
     * 获取 cdate
     * @return cdate.
     */
    public String getCdate() {
        return cdate;
    }
    
    /**
     * 设置 cdate
     * @param cdate cdate
     */
    public void setCdate(String cdate) {
        this.cdate = cdate;
    }
    
    /**
     * 获取 prodestablish
     * @return prodestablish.
     */
    public BigDecimal getProdestablish() {
        return prodestablish;
    }
    
    /**
     * 设置 prodestablish
     * @param prodestablish prodestablish
     */
    public void setProdestablish(BigDecimal prodestablish) {
        this.prodestablish = prodestablish;
    }
    
    /**
     * 获取 nhgexpire
     * @return nhgexpire.
     */
    public BigDecimal getNhgexpire() {
        return nhgexpire;
    }
    
    /**
     * 设置 nhgexpire
     * @param nhgexpire nhgexpire
     */
    public void setNhgexpire(BigDecimal nhgexpire) {
        this.nhgexpire = nhgexpire;
    }
    
    /**
     * 获取 zhgsq
     * @return zhgsq.
     */
    public BigDecimal getZhgsq() {
        return zhgsq;
    }
    
    /**
     * 设置 zhgsq
     * @param zhgsq zhgsq
     */
    public void setZhgsq(BigDecimal zhgsq) {
        this.zhgsq = zhgsq;
    }
    
    /**
     * 获取 ccexpire
     * @return ccexpire.
     */
    public BigDecimal getCcexpire() {
        return ccexpire;
    }
    
    /**
     * 设置 ccexpire
     * @param ccexpire ccexpire
     */
    public void setCcexpire(BigDecimal ccexpire) {
        this.ccexpire = ccexpire;
    }
    
    /**
     * 获取 crsq
     * @return crsq.
     */
    public BigDecimal getCrsq() {
        return crsq;
    }
    
    /**
     * 设置 crsq
     * @param crsq crsq
     */
    public void setCrsq(BigDecimal crsq) {
        this.crsq = crsq;
    }
    
    /**
     * 获取 assetsale
     * @return assetsale.
     */
    public BigDecimal getAssetsale() {
        return assetsale;
    }
    
    /**
     * 设置 assetsale
     * @param assetsale assetsale
     */
    public void setAssetsale(BigDecimal assetsale) {
        this.assetsale = assetsale;
    }
    
    /**
     * 获取 assetexpire
     * @return assetexpire.
     */
    public BigDecimal getAssetexpire() {
        return assetexpire;
    }
    
    /**
     * 设置 assetexpire
     * @param assetexpire assetexpire
     */
    public void setAssetexpire(BigDecimal assetexpire) {
        this.assetexpire = assetexpire;
    }
    
    /**
     * 获取 assetpayments
     * @return assetpayments.
     */
    public BigDecimal getAssetpayments() {
        return assetpayments;
    }
    
    /**
     * 设置 assetpayments
     * @param assetpayments assetpayments
     */
    public void setAssetpayments(BigDecimal assetpayments) {
        this.assetpayments = assetpayments;
    }
    
    /**
     * 获取 calclr
     * @return calclr.
     */
    public BigDecimal getCalclr() {
        return calclr;
    }
    
    /**
     * 设置 calclr
     * @param calclr calclr
     */
    public void setCalclr(BigDecimal calclr) {
        this.calclr = calclr;
    }
    
    /**
     * 获取 prodexpire
     * @return prodexpire.
     */
    public BigDecimal getProdexpire() {
        return prodexpire;
    }
    
    /**
     * 设置 prodexpire
     * @param prodexpire prodexpire
     */
    public void setProdexpire(BigDecimal prodexpire) {
        this.prodexpire = prodexpire;
    }
    
    /**
     * 获取 nhgsq
     * @return nhgsq.
     */
    public BigDecimal getNhgsq() {
        return nhgsq;
    }
    
    /**
     * 设置 nhgsq
     * @param nhgsq nhgsq
     */
    public void setNhgsq(BigDecimal nhgsq) {
        this.nhgsq = nhgsq;
    }
    
    /**
     * 获取 zhgexpire
     * @return zhgexpire.
     */
    public BigDecimal getZhgexpire() {
        return zhgexpire;
    }
    
    /**
     * 设置 zhgexpire
     * @param zhgexpire zhgexpire
     */
    public void setZhgexpire(BigDecimal zhgexpire) {
        this.zhgexpire = zhgexpire;
    }
    
    /**
     * 获取 ccsq
     * @return ccsq.
     */
    public BigDecimal getCcsq() {
        return ccsq;
    }
    
    /**
     * 设置 ccsq
     * @param ccsq ccsq
     */
    public void setCcsq(BigDecimal ccsq) {
        this.ccsq = ccsq;
    }
    
    /**
     * 获取 crexpire
     * @return crexpire.
     */
    public BigDecimal getCrexpire() {
        return crexpire;
    }
    
    /**
     * 设置 crexpire
     * @param crexpire crexpire
     */
    public void setCrexpire(BigDecimal crexpire) {
        this.crexpire = crexpire;
    }
    
    /**
     * 获取 assetbuy
     * @return assetbuy.
     */
    public BigDecimal getAssetbuy() {
        return assetbuy;
    }
    
    /**
     * 设置 assetbuy
     * @param assetbuy assetbuy
     */
    public void setAssetbuy(BigDecimal assetbuy) {
        this.assetbuy = assetbuy;
    }
    
    /**
     * 获取 calclc
     * @return calclc.
     */
    public BigDecimal getCalclc() {
        return calclc;
    }
    
    /**
     * 设置 calclc
     * @param calclc calclc
     */
    public void setCalclc(BigDecimal calclc) {
        this.calclc = calclc;
    }
    
    /**
     * 获取 cleancashflow
     * @return cleancashflow.
     */
    public BigDecimal getCleancashflow() {
        return cleancashflow;
    }
    
    /**
     * 设置 cleancashflow
     * @param cleancashflow cleancashflow
     */
    public void setCleancashflow(BigDecimal cleancashflow) {
        this.cleancashflow = cleancashflow;
    }
    
    
    


}
