package com.joyintech.tams.svreport.chbreport.hxassetliabilitystatement.dto;


import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.base.entity.HxBalance;

import java.math.BigDecimal;

/**
 * 华夏资产负债表导出Dto
 *
 * @author WanShiwei
 * @version 2.1.6 2021年4月20日
 * @see HXAssetLiabilityStatementQuery002Dto
 * @since 2.1.6
 */
public class HXAssetLiabilityStatementQuery002Dto extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private HxBalance qcBal;

    private HxBalance qmBal;

//    /**
//     * 银行存款-期初
//     */
//    private BigDecimal depositQC;
//
//    /**
//     * 货币市场类投资-期初
//     */
//    private BigDecimal currInvestQC;
//
//    /**
//     * 其他标准债权类投资-期初
//     */
//    private BigDecimal credInvestQC;
//
//    /**
//     * 债券类投资-期初
//     */
//    private BigDecimal bondInvestQC;
//
//    /**
//     * 非标项目类投资-期初
//     */
//    private BigDecimal feibiaoInvestQC;
//
//    /**
//     * 权益类投资-期初
//     */
//    private BigDecimal quanyiInvestQC;
//
//    /**
//     * 衍生品投资-期初
//     */
//    private BigDecimal derivaInvestQC;
//
//    /**
//     * 其它投资-期初
//     */
//    private BigDecimal otherInvestQC;
//
//    /**
//     * 应收利息-期初
//     */
//    private BigDecimal interAccrQC;
//
//    /**
//     * 拆入资金-期初
//     */
//    private BigDecimal inFundQC;
//
//    /**
//     * 卖出回购资金-期初
//     */
//    private BigDecimal huigouFundQC;
//
//    /**
//     * 衍生金融负债-期初
//     */
//    private BigDecimal derivaDebtQC;
//
//    /**
//     * 应付利息-期初
//     */
//    private BigDecimal accrProfitQC;
//
//    /**
//     * 理财产品本金-收益型-期初
//     */
//    private BigDecimal capitalShouyiQC;
//
//    /**
//     * 理财产品本金-净值型-期初
//     */
//    private BigDecimal capitalJingzhiQC;
//
//    /**
//     * 理财产品应付收益-期初
//     */
//    private BigDecimal accrIncomeQC;
//
//    /**
//     * 应交税费-期初
//     */
//    private BigDecimal taxPayQC;
//
//    /**
//     * 预计负债-期初
//     */
//    private BigDecimal yujiFuzhaiQC;
//
//    /**
//     * 其它应付款-期初
//     */
//    private BigDecimal otherPayQC;
//
//    /**
//     * 未分配利润-期初
//     */
//    private BigDecimal undisProfitQC;
//
//    /**
//     * 银行存款-期末
//     */
//    private BigDecimal depositQM;
//
//    /**
//     * 货币市场类投资-期末
//     */
//    private BigDecimal currInvestQM;
//
//    /**
//     * 其他标准债权类投资-期末
//     */
//    private BigDecimal credInvestQM;
//
//    /**
//     * 债券类投资-期末
//     */
//    private BigDecimal bondInvestQM;
//
//    /**
//     * 非标项目类投资-期末
//     */
//    private BigDecimal feibiaoInvestQM;
//
//    /**
//     * 权益类投资-期末
//     */
//    private BigDecimal quanyiInvestQM;
//
//    /**
//     * 衍生品投资-期末
//     */
//    private BigDecimal derivaInvestQM;
//
//    /**
//     * 其它投资-期末
//     */
//    private BigDecimal otherInvestQM;
//
//    /**
//     * 应收利息-期末
//     */
//    private BigDecimal interAccrQM;
//
//    /**
//     * 拆入资金-期末
//     */
//    private BigDecimal inFundQM;
//
//    /**
//     * 卖出回购资金-期末
//     */
//    private BigDecimal huigouFundQM;
//
//    /**
//     * 衍生金融负债-期末
//     */
//    private BigDecimal derivaDebtQM;
//
//    /**
//     * 应付利息-期末
//     */
//    private BigDecimal accrProfitQM;
//
//    /**
//     * 理财产品本金-收益型-期末
//     */
//    private BigDecimal capitalShouyiQM;
//
//    /**
//     * 理财产品本金-净值型-期末
//     */
//    private BigDecimal capitalJingzhiQM;
//
//    /**
//     * 理财产品应付收益-期末
//     */
//    private BigDecimal accrIncomeQM;
//
//    /**
//     * 应交税费-期末
//     */
//    private BigDecimal taxPayQM;
//
//    /**
//     * 预计负债-期末
//     */
//    private BigDecimal yujiFuzhaiQM;
//
//    /**
//     * 其它应付款-期末
//     */
//    private BigDecimal otherPayQM;
//
//    /**
//     * 未分配利润-期末
//     */
//    private BigDecimal undisProfitQM;

    /**
     * 资产合计-期初
     */
    private BigDecimal assetTotalQC;

    /**
     * 资产合计-期末
     */
    private BigDecimal assetTotalQM;

    /**
     * 负债合计-期初
     */
    private BigDecimal liabilityTotalQC;

    /**
     * 负债合计-期末
     */
    private BigDecimal liabilityTotalQM;

    /**
     * 所有者权益合计-期初
     */
    private BigDecimal OwnerEquityTotalQC;

    /**
     * 所有者权益合计-期末
     */
    private BigDecimal OwnerEquityTotalQM;

    /**
     * 负债和所有者权益合计-期初
     */
    private BigDecimal liabilitiesAndOwnerEquityTotalQC;

    /**
     * 负债和所有者权益合计-期末
     */
    private BigDecimal liabilitiesAndOwnerEquityTotalQM;

    public HxBalance getQcBal() {
        return qcBal;
    }

    public void setQcBal(HxBalance qcBal) {
        this.qcBal = qcBal;
    }

    public HxBalance getQmBal() {
        return qmBal;
    }

    public void setQmBal(HxBalance qmBal) {
        this.qmBal = qmBal;
    }

    public BigDecimal getAssetTotalQC() {
        return assetTotalQC;
    }

    public void setAssetTotalQC(BigDecimal assetTotalQC) {
        this.assetTotalQC = assetTotalQC;
    }

    public BigDecimal getAssetTotalQM() {
        return assetTotalQM;
    }

    public void setAssetTotalQM(BigDecimal assetTotalQM) {
        this.assetTotalQM = assetTotalQM;
    }

    public BigDecimal getLiabilityTotalQC() {
        return liabilityTotalQC;
    }

    public void setLiabilityTotalQC(BigDecimal liabilityTotalQC) {
        this.liabilityTotalQC = liabilityTotalQC;
    }

    public BigDecimal getLiabilityTotalQM() {
        return liabilityTotalQM;
    }

    public void setLiabilityTotalQM(BigDecimal liabilityTotalQM) {
        this.liabilityTotalQM = liabilityTotalQM;
    }

    public BigDecimal getOwnerEquityTotalQC() {
        return OwnerEquityTotalQC;
    }

    public void setOwnerEquityTotalQC(BigDecimal ownerEquityTotalQC) {
        OwnerEquityTotalQC = ownerEquityTotalQC;
    }

    public BigDecimal getOwnerEquityTotalQM() {
        return OwnerEquityTotalQM;
    }

    public void setOwnerEquityTotalQM(BigDecimal ownerEquityTotalQM) {
        OwnerEquityTotalQM = ownerEquityTotalQM;
    }

    public BigDecimal getLiabilitiesAndOwnerEquityTotalQC() {
        return liabilitiesAndOwnerEquityTotalQC;
    }

    public void setLiabilitiesAndOwnerEquityTotalQC(BigDecimal liabilitiesAndOwnerEquityTotalQC) {
        this.liabilitiesAndOwnerEquityTotalQC = liabilitiesAndOwnerEquityTotalQC;
    }

    public BigDecimal getLiabilitiesAndOwnerEquityTotalQM() {
        return liabilitiesAndOwnerEquityTotalQM;
    }

    public void setLiabilitiesAndOwnerEquityTotalQM(BigDecimal liabilitiesAndOwnerEquityTotalQM) {
        this.liabilitiesAndOwnerEquityTotalQM = liabilitiesAndOwnerEquityTotalQM;
    }
}
