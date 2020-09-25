/*
 * 文件名：PtlFutureCashPosiDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：renping
 * 修改时间：2019年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.portfolio.ptlfuturecashposi.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.tams.common.base.entity.PtlPortfolio;

/**
 * 组合未来头寸分析头寸
 * 
 * @author renping
 * @version 1.0 2019年11月9日
 * @see PtlFutureCashPosiDto
 * @since 1.0
 */
public class PtlFutureCashPosiDto extends PtlPortfolio {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 11258723436L;

    /**
     * 产品经理
     */
    private String prodManager;

    /**
     * 展示维度
     */
    private String showDimension;

    /**
     * 日期
     */
    private Date cdate;

    /**
     * 组合可用现金
     */
    private BigDecimal usableAmt;

    /**
     * 产品到期
     */
    private BigDecimal prdEndAmt;

    /**
     * 资产存续期（资金流入）
     */
    private BigDecimal assetPeriodInAmt;

    /**
     * 资产存续期（资金流出）
     */
    private BigDecimal assetPeriodOutAmt;

    /**
     * 现金缺口
     */
    private BigDecimal cashGapAmt;

    /**
     * 产品代码
     */
    private String finprodId;

    /**
     * 支付日期
     */
    private Date payDate;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 现金流标志
     */
    private String cashType;

    /**
     * 现金金额
     */
    private BigDecimal cashAmt;

    /**
     * 父节点
     */
    private String upperPortfolioCode;

    /**
     * 子节点
     */
    private String hasChildren;

    public String getUpperPortfolioCode() {
        return upperPortfolioCode;
    }

    public void setUpperPortfolioCode(String upperPortfolioCode) {
        this.upperPortfolioCode = upperPortfolioCode;
    }

    public String getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(String hasChildren) {
        this.hasChildren = hasChildren;
    }

    /**
     * 获取 prodManager
     * 
     * @return prodManager.
     */
    public String getProdManager() {
        return prodManager;
    }

    /**
     * 设置 prodManager
     * 
     * @param prodManager prodManager
     */
    public void setProdManager(String prodManager) {
        this.prodManager = prodManager;
    }

    /**
     * 获取 showDimension
     * 
     * @return showDimension.
     */
    public String getShowDimension() {
        return showDimension;
    }

    /**
     * 设置 showDimension
     * 
     * @param showDimension showDimension
     */
    public void setShowDimension(String showDimension) {
        this.showDimension = showDimension;
    }

    /**
     * 获取 cdate
     * 
     * @return cdate.
     */
    public Date getCdate() {
        return cdate;
    }

    /**
     * 设置 cdate
     * 
     * @param cdate cdate
     */
    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    /**
     * 获取 usableAmt
     * 
     * @return usableAmt.
     */
    public BigDecimal getUsableAmt() {
        return usableAmt;
    }

    /**
     * 设置 usableAmt
     * 
     * @param usableAmt usableAmt
     */
    public void setUsableAmt(BigDecimal usableAmt) {
        this.usableAmt = usableAmt;
    }

    /**
     * 获取 prdEndAmt
     * 
     * @return prdEndAmt.
     */
    public BigDecimal getPrdEndAmt() {
        return prdEndAmt;
    }

    /**
     * 设置 prdEndAmt
     * 
     * @param prdEndAmt prdEndAmt
     */
    public void setPrdEndAmt(BigDecimal prdEndAmt) {
        this.prdEndAmt = prdEndAmt;
    }

    /**
     * 获取 assetPeriodInAmt
     * 
     * @return assetPeriodInAmt.
     */
    public BigDecimal getAssetPeriodInAmt() {
        return assetPeriodInAmt;
    }

    /**
     * 设置 assetPeriodInAmt
     * 
     * @param assetPeriodInAmt assetPeriodInAmt
     */
    public void setAssetPeriodInAmt(BigDecimal assetPeriodInAmt) {
        this.assetPeriodInAmt = assetPeriodInAmt;
    }

    /**
     * 获取 assetPeriodOutAmt
     * 
     * @return assetPeriodOutAmt.
     */
    public BigDecimal getAssetPeriodOutAmt() {
        return assetPeriodOutAmt;
    }

    /**
     * 设置 assetPeriodOutAmt
     * 
     * @param assetPeriodOutAmt assetPeriodOutAmt
     */
    public void setAssetPeriodOutAmt(BigDecimal assetPeriodOutAmt) {
        this.assetPeriodOutAmt = assetPeriodOutAmt;
    }

    /**
     * 获取 cashGapAmt
     * 
     * @return cashGapAmt.
     */
    public BigDecimal getCashGapAmt() {
        return cashGapAmt;
    }

    /**
     * 设置 cashGapAmt
     * 
     * @param cashGapAmt cashGapAmt
     */
    public void setCashGapAmt(BigDecimal cashGapAmt) {
        this.cashGapAmt = cashGapAmt;
    }

    /**
     * 获取 finprodId
     * 
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 finprodId
     * 
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 获取 payDate
     * 
     * @return payDate.
     */
    public Date getPayDate() {
        return payDate;
    }

    /**
     * 设置 payDate
     * 
     * @param payDate payDate
     */
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    /**
     * 获取 ccy
     * 
     * @return ccy.
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 ccy
     * 
     * @param ccy ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 获取 cashType
     * 
     * @return cashType.
     */
    public String getCashType() {
        return cashType;
    }

    /**
     * 设置 cashType
     * 
     * @param cashType cashType
     */
    public void setCashType(String cashType) {
        this.cashType = cashType;
    }

    /**
     * 获取 cashAmt
     * 
     * @return cashAmt.
     */
    public BigDecimal getCashAmt() {
        return cashAmt;
    }

    /**
     * 设置 cashAmt
     * 
     * @param cashAmt cashAmt
     */
    public void setCashAmt(BigDecimal cashAmt) {
        this.cashAmt = cashAmt;
    }

}
