/*
 * 文件名：FinProductDurDeal001Dto.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：etliu 修改时间：2018年12月18日 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.durdeal.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品存续期交易查询dto
 *
 * @author etliu
 * @version 1.0 2018年12月18日
 * @see FinProductDurDeal001Dto
 * @since 1.0
 */
public class FinProductDurDeal001Dto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -8291666227730929705L;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 市场代码
     */
    private String marketId;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 产品简称
     */
    private String finprodAbbr;

    /**
     * 处理模式
     */
    private String dealMode;

    /**
     * 收益类型，净值、预期收益、货币、结构
     */
    private String profitType;

    /**
     * 销售部门
     */
    private String saleDepartment;

    /**
     * 销售部门
     */
    private String saleDepartmentText;

    /**
     * 现金流日期
     */
    private Date cashDate;

    /**
     * 现金流日期
     */
    private String cashDateText;

    /**
     * 确认日
     */
    private Date confirmDate;

    /**
     * 当日总份额
     */
    private BigDecimal tdyTotalQty;

    /**
     * 当日申购金额
     */
    private BigDecimal tdyBuyMoney;

    /**
     * 当日申购份额
     */
    private BigDecimal tdyBuyQty;

    /**
     * 当日赎回金额
     */
    private BigDecimal tdyRedMoney;

    /**
     * 当日赎回份额
     */
    private BigDecimal tdyRedQty;

    /**
     * 当日已结转收益
     */
    private BigDecimal tdyCarriedInterest;

    /**
     * 当日红利再投金额
     */
    private BigDecimal tdyCarriedMoney;

    /**
     * 当日红利再投份额
     */
    private BigDecimal tdyCarriedQty;

    /**
     * 赎回成本
     */
    private BigDecimal redCost;

    /**
     * 赎回利息
     */
    private BigDecimal redInt;

    /**
     * 现金分红金额
     */

    private BigDecimal bonusCash;

    /**
     * 当日强增金额
     */
    private BigDecimal tdyForceIncreaseMoney;

    /**
     * 当日强增份额
     */
    private BigDecimal tdyForceIncreaseQty;

    /**
     * 当日强减金额
     */
    private BigDecimal tdyForceReduceMoney;

    /**
     * 当日强减份额
     */
    private BigDecimal tdyForceReduceQty;

    /**
     * 收益率/单位净值/万份收益
     */
    private String yield;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 是否分层
     */
    private String isLay;

    /**
     * 是否周期型
     */
    private String isCycle;

    /**
     * 交易方向
     */
    private String ps;

    /**
     * 交易份额
     */
    private BigDecimal shareAmout;

    /**
     * 到期本金
     */
    private BigDecimal mPrin;

    /**
     * 汇总交易表交易编号
     */
    private String tradeId;

    /**
     * 交易类型
     */
    private String dealType;

    /**
     * 录入方式
     */

    private String genType;

    /**
     * 交易备注
     */
    private String dealRemark;

    /**
     * 有效状态
     */
    private String effectStatus;

    /**
     * 当日净申赎份额
     */
    private BigDecimal tdyRedemptionQty;

    /**
     * 当日净申赎金额
     */
    private BigDecimal tdyRedemptionMoney;

    /**
     * 权限状态
     */
    private String permitFlag;

    /**
     * 当日净申赎份额获取 当日净申赎份额=当日申购份额-赎回份额+强增份额-强减份额+红利再投份额
     */
    public BigDecimal getTdyRedemptionQty() {
        return ArithmeticUtil.doAdd(
            ArithmeticUtil.subtract(ArithmeticUtil.doAdd(ArithmeticUtil.subtract(tdyBuyQty, tdyRedQty), tdyForceIncreaseQty),
                tdyForceReduceQty),
            tdyCarriedQty);
    }

    /**
     * 当日净申赎金额获取 当日净申赎金额=当日申购金额-赎回金额+强增金额-强减金额-现金分红金额
     */
    public BigDecimal getTdyRedemptionMoney() {
        return ArithmeticUtil.doAdd(ArithmeticUtil.subtract(
            ArithmeticUtil.doAdd(ArithmeticUtil.subtract(tdyBuyMoney, tdyRedMoney), tdyForceIncreaseMoney), tdyForceReduceMoney),
            bonusCash);
    }

    public String getEffectStatus() {
        return effectStatus;
    }

    public String getEffectStatusStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.有效状态, effectStatus);
    }

    public void setEffectStatus(String effectStatus) {
        this.effectStatus = effectStatus;
    }

    /**
     * 获取 portfolioId
     *
     * @return portfolioId.
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 portfolioId
     *
     * @param portfolioId portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
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
     * 获取 prodId
     *
     * @return prodId.
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 prodId
     *
     * @param prodId prodId
     */
    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    /**
     * 获取 saleDepartment
     *
     * @return saleDepartment.
     */
    public String getSaleDepartment() {
        return saleDepartment;
    }

    /**
     * 设置 saleDepartment
     *
     * @param saleDepartment saleDepartment
     */
    public void setSaleDepartment(String saleDepartment) {
        this.saleDepartment = saleDepartment;
    }

    /**
     * 获取 cashDate
     *
     * @return cashDate.
     */
    public Date getCashDate() {
        return cashDate;
    }

    /**
     * 设置 cashDate
     *
     * @param cashDate cashDate
     */
    public void setCashDate(Date cashDate) {
        this.cashDate = cashDate;
    }

    /**
     * 获取 tdyTotalQty
     *
     * @return tdyTotalQty.
     */
    public BigDecimal getTdyTotalQty() {
        return tdyTotalQty;
    }

    /**
     * 设置 tdyTotalQty
     *
     * @param tdyTotalQty tdyTotalQty
     */
    public void setTdyTotalQty(BigDecimal tdyTotalQty) {
        this.tdyTotalQty = tdyTotalQty;
    }

    /**
     * 获取 tdyBuyMoney
     *
     * @return tdyBuyMoney.
     */
    public BigDecimal getTdyBuyMoney() {
        return tdyBuyMoney;
    }

    /**
     * 设置 tdyBuyMoney
     *
     * @param tdyBuyMoney tdyBuyMoney
     */
    public void setTdyBuyMoney(BigDecimal tdyBuyMoney) {
        this.tdyBuyMoney = tdyBuyMoney;
    }

    /**
     * 获取 tdyBuyQty
     *
     * @return tdyBuyQty.
     */
    public BigDecimal getTdyBuyQty() {
        return tdyBuyQty;
    }

    /**
     * 设置 tdyBuyQty
     *
     * @param tdyBuyQty tdyBuyQty
     */
    public void setTdyBuyQty(BigDecimal tdyBuyQty) {
        this.tdyBuyQty = tdyBuyQty;
    }

    /**
     * 获取 tdyRedMoney
     *
     * @return tdyRedMoney.
     */
    public BigDecimal getTdyRedMoney() {
        return tdyRedMoney;
    }

    /**
     * 设置 tdyRedMoney
     *
     * @param tdyRedMoney tdyRedMoney
     */
    public void setTdyRedMoney(BigDecimal tdyRedMoney) {
        this.tdyRedMoney = tdyRedMoney;
    }

    /**
     * 获取 tdyRedQty
     *
     * @return tdyRedQty.
     */
    public BigDecimal getTdyRedQty() {
        return tdyRedQty;
    }

    /**
     * 设置 tdyRedQty
     *
     * @param tdyRedQty tdyRedQty
     */
    public void setTdyRedQty(BigDecimal tdyRedQty) {
        this.tdyRedQty = tdyRedQty;
    }

    /**
     * 获取 tdyCarriedInterest
     *
     * @return tdyCarriedInterest.
     */
    public BigDecimal getTdyCarriedInterest() {
        return tdyCarriedInterest;
    }

    /**
     * 设置 tdyCarriedInterest
     *
     * @param tdyCarriedInterest tdyCarriedInterest
     */
    public void setTdyCarriedInterest(BigDecimal tdyCarriedInterest) {
        this.tdyCarriedInterest = tdyCarriedInterest;
    }

    /**
     * 获取 tdyCarriedMoney
     *
     * @return tdyCarriedMoney.
     */
    public BigDecimal getTdyCarriedMoney() {
        return tdyCarriedMoney;
    }

    /**
     * 设置 tdyCarriedMoney
     *
     * @param tdyCarriedMoney tdyCarriedMoney
     */
    public void setTdyCarriedMoney(BigDecimal tdyCarriedMoney) {
        this.tdyCarriedMoney = tdyCarriedMoney;
    }

    /**
     * 获取 tdyCarriedQty
     *
     * @return tdyCarriedQty.
     */
    public BigDecimal getTdyCarriedQty() {
        return tdyCarriedQty;
    }

    /**
     * 设置 tdyCarriedQty
     *
     * @param tdyCarriedQty tdyCarriedQty
     */
    public void setTdyCarriedQty(BigDecimal tdyCarriedQty) {
        this.tdyCarriedQty = tdyCarriedQty;
    }

    /**
     * 获取 tdyForceIncreaseMoney
     *
     * @return tdyForceIncreaseMoney.
     */
    public BigDecimal getTdyForceIncreaseMoney() {
        return tdyForceIncreaseMoney;
    }

    /**
     * 设置 tdyForceIncreaseMoney
     *
     * @param tdyForceIncreaseMoney tdyForceIncreaseMoney
     */
    public void setTdyForceIncreaseMoney(BigDecimal tdyForceIncreaseMoney) {
        this.tdyForceIncreaseMoney = tdyForceIncreaseMoney;
    }

    /**
     * 获取 tdyForceIncreaseQty
     *
     * @return tdyForceIncreaseQty.
     */
    public BigDecimal getTdyForceIncreaseQty() {
        return tdyForceIncreaseQty;
    }

    /**
     * 设置 tdyForceIncreaseQty
     *
     * @param tdyForceIncreaseQty tdyForceIncreaseQty
     */
    public void setTdyForceIncreaseQty(BigDecimal tdyForceIncreaseQty) {
        this.tdyForceIncreaseQty = tdyForceIncreaseQty;
    }

    /**
     * 获取 tdyForceReduceMoney
     *
     * @return tdyForceReduceMoney.
     */
    public BigDecimal getTdyForceReduceMoney() {
        return tdyForceReduceMoney;
    }

    /**
     * 设置 tdyForceReduceMoney
     *
     * @param tdyForceReduceMoney tdyForceReduceMoney
     */
    public void setTdyForceReduceMoney(BigDecimal tdyForceReduceMoney) {
        this.tdyForceReduceMoney = tdyForceReduceMoney;
    }

    /**
     * 获取 tdyForceReduceQty
     *
     * @return tdyForceReduceQty.
     */
    public BigDecimal getTdyForceReduceQty() {
        return tdyForceReduceQty;
    }

    /**
     * 设置 tdyForceReduceQty
     *
     * @param tdyForceReduceQty tdyForceReduceQty
     */
    public void setTdyForceReduceQty(BigDecimal tdyForceReduceQty) {
        this.tdyForceReduceQty = tdyForceReduceQty;
    }

    /**
     * 获取 yield
     *
     * @return yield.
     */
    public String getYield() {
        return yield;
    }

    /**
     * 设置 yield
     *
     * @param yield yield
     */
    public void setYield(String yield) {
        this.yield = yield;
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
     * 币种
     */
    public String getCcyStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.币种, ccy);
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
     * 获取 profitType
     *
     * @return profitType.
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 profitType
     *
     * @param profitType profitType
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    /**
     * 获取 isLay
     *
     * @return isLay.
     */
    public String getIsLay() {
        return isLay;
    }

    /**
     * 设置 isLay
     *
     * @param isLay isLay
     */
    public void setIsLay(String isLay) {
        this.isLay = isLay;
    }

    /**
     * 获取 ps
     *
     * @return ps.
     */
    public String getPs() {
        return ps;
    }

    /**
     * 设置 ps
     *
     * @param ps ps
     */
    public void setPs(String ps) {
        this.ps = ps;
    }

    /**
     * 获取 shareAmout
     *
     * @return shareAmout.
     */
    public BigDecimal getShareAmout() {
        return shareAmout;
    }

    /**
     * 设置 shareAmout
     *
     * @param shareAmout shareAmout
     */
    public void setShareAmout(BigDecimal shareAmout) {
        this.shareAmout = shareAmout;
    }

    /**
     * 获取 mPrin
     *
     * @return mPrin.
     */
    public BigDecimal getmPrin() {
        return mPrin;
    }

    /**
     * 设置 mPrin
     *
     * @param mPrin mPrin
     */
    public void setmPrin(BigDecimal mPrin) {
        this.mPrin = mPrin;
    }

    /**
     * 获取 saleDepartmentText
     *
     * @return saleDepartmentText.
     */
    public String getSaleDepartmentText() {
        saleDepartmentText = SysDataUtil.getNameFromDataDict(DictCode.销售部门, saleDepartment);
        return saleDepartmentText;
    }

    /**
     * 设置 saleDepartmentText
     *
     * @param saleDepartmentText saleDepartmentText
     */
    public void setSaleDepartmentText(String saleDepartmentText) {
        this.saleDepartmentText = saleDepartmentText;
    }

    /**
     * 获取 cashDateText
     *
     * @return cashDateText.
     */
    public String getCashDateText() {
        cashDateText = DateUtil.format(cashDate);
        return cashDateText;
    }

    /**
     * 设置 cashDateText
     *
     * @param cashDateText cashDateText
     */
    public void setCashDateText(String cashDateText) {
        this.cashDateText = cashDateText;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getDealType() {
        return dealType;
    }

    public String getDealTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.交易方向, dealType);
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public String getDealMode() {
        return dealMode;
    }

    public String getDealModeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.处理模式, dealMode);
    }

    public void setDealMode(String dealMode) {
        this.dealMode = dealMode;
    }

    public Date getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    public String getConfirmDateStr() {
        return DateUtil.format(confirmDate);
    }

    public BigDecimal getRedCost() {
        return redCost;
    }

    public void setRedCost(BigDecimal redCost) {
        this.redCost = redCost;
    }

    public BigDecimal getRedInt() {
        return redInt;
    }

    public void setRedInt(BigDecimal redInt) {
        this.redInt = redInt;
    }

    public BigDecimal getBonusCash() {
        return bonusCash;
    }

    public void setBonusCash(BigDecimal bonusCash) {
        this.bonusCash = bonusCash;
    }

    public String getGenType() {
        return genType;
    }

    public void setGenType(String genType) {
        this.genType = genType;
    }

    public String getGenTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.录入方式, genType);
    }

    public String getDealRemark() {
        return dealRemark;
    }

    public void setDealRemark(String dealRemark) {
        this.dealRemark = dealRemark;
    }

    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    public String getIsCycle() {
        return isCycle;
    }

    public void setIsCycle(String isCycle) {
        this.isCycle = isCycle;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public String getPermitFlag() {
        return permitFlag;
    }

    public void setPermitFlag(String permitFlag) {
        this.permitFlag = permitFlag;
    }

    public String getCashDateStr() {
        return DateUtil.format(cashDate);
    }

}
