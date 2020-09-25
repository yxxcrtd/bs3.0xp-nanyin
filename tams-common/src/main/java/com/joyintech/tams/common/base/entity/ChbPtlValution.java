package com.joyintech.tams.common.base.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述:CHB_PTL_VALUTION表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-02-16
 */
public class ChbPtlValution extends ChbPtlValutionKey {
    /**
     * 币种
     */
    private String ccy;

    /**
     * 数量
     */
    private BigDecimal shareAmt;

    /**
     * 摊销总成本
     */
    private BigDecimal dscCostAmt;

    /**
     * 摊销成本净价
     */
    private BigDecimal dscCleanPrice;

    /**
     * 买入总成本
     */
    private BigDecimal buyCostAmt;

    /**
     * 买入成本净价
     */
    private BigDecimal buyCleanPrice;

    /**
     * 市值
     */
    private BigDecimal marketValue;

    /**
     * 市价净价
     */
    private BigDecimal marketCleanPrice;

    /**
     * 当日应计利息
     */
    private BigDecimal tdyIntincexp;

    /**
     * 当日发生摊销收入
     */
    private BigDecimal tdyDscincexpAdd;

    /**
     * 当日发生利息收入
     */
    private BigDecimal tdyIntincexpAdd;

    /**
     * 当日发生价差收入
     */
    private BigDecimal tdyDsclossAdd;

    /**
     * 当日发生浮动盈亏
     */
    private BigDecimal tdyFloatIngplAdd;

    /**
     * 当日发生费用支出
     */
    private BigDecimal tdyFeeAdd;

    /**
     * 累计单位净值
     */
    private BigDecimal accuNetValue;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建部门
     */
    private String createDept;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 币种
     * @return CCY 币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 币种
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 数量
     * @return SHARE_AMT 数量
     */
    public BigDecimal getShareAmt() {
        return shareAmt;
    }

    /**
     * 数量
     * @param shareAmt 数量
     */
    public void setShareAmt(BigDecimal shareAmt) {
        this.shareAmt = shareAmt;
    }

    /**
     * 摊销总成本
     * @return DSC_COST_AMT 摊销总成本
     */
    public BigDecimal getDscCostAmt() {
        return dscCostAmt;
    }

    /**
     * 摊销总成本
     * @param dscCostAmt 摊销总成本
     */
    public void setDscCostAmt(BigDecimal dscCostAmt) {
        this.dscCostAmt = dscCostAmt;
    }

    /**
     * 摊销成本净价
     * @return DSC_CLEAN_PRICE 摊销成本净价
     */
    public BigDecimal getDscCleanPrice() {
        return dscCleanPrice;
    }

    /**
     * 摊销成本净价
     * @param dscCleanPrice 摊销成本净价
     */
    public void setDscCleanPrice(BigDecimal dscCleanPrice) {
        this.dscCleanPrice = dscCleanPrice;
    }

    /**
     * 买入总成本
     * @return BUY_COST_AMT 买入总成本
     */
    public BigDecimal getBuyCostAmt() {
        return buyCostAmt;
    }

    /**
     * 买入总成本
     * @param buyCostAmt 买入总成本
     */
    public void setBuyCostAmt(BigDecimal buyCostAmt) {
        this.buyCostAmt = buyCostAmt;
    }

    /**
     * 买入成本净价
     * @return BUY_CLEAN_PRICE 买入成本净价
     */
    public BigDecimal getBuyCleanPrice() {
        return buyCleanPrice;
    }

    /**
     * 买入成本净价
     * @param buyCleanPrice 买入成本净价
     */
    public void setBuyCleanPrice(BigDecimal buyCleanPrice) {
        this.buyCleanPrice = buyCleanPrice;
    }

    /**
     * 市值
     * @return MARKET_VALUE 市值
     */
    public BigDecimal getMarketValue() {
        return marketValue;
    }

    /**
     * 市值
     * @param marketValue 市值
     */
    public void setMarketValue(BigDecimal marketValue) {
        this.marketValue = marketValue;
    }

    /**
     * 市价净价
     * @return MARKET_CLEAN_PRICE 市价净价
     */
    public BigDecimal getMarketCleanPrice() {
        return marketCleanPrice;
    }

    /**
     * 市价净价
     * @param marketCleanPrice 市价净价
     */
    public void setMarketCleanPrice(BigDecimal marketCleanPrice) {
        this.marketCleanPrice = marketCleanPrice;
    }

    /**
     * 当日应计利息
     * @return TDY_INTINCEXP 当日应计利息
     */
    public BigDecimal getTdyIntincexp() {
        return tdyIntincexp;
    }

    /**
     * 当日应计利息
     * @param tdyIntincexp 当日应计利息
     */
    public void setTdyIntincexp(BigDecimal tdyIntincexp) {
        this.tdyIntincexp = tdyIntincexp;
    }

    /**
     * 当日发生摊销收入
     * @return TDY_DSCINCEXP_ADD 当日发生摊销收入
     */
    public BigDecimal getTdyDscincexpAdd() {
        return tdyDscincexpAdd;
    }

    /**
     * 当日发生摊销收入
     * @param tdyDscincexpAdd 当日发生摊销收入
     */
    public void setTdyDscincexpAdd(BigDecimal tdyDscincexpAdd) {
        this.tdyDscincexpAdd = tdyDscincexpAdd;
    }

    /**
     * 当日发生利息收入
     * @return TDY_INTINCEXP_ADD 当日发生利息收入
     */
    public BigDecimal getTdyIntincexpAdd() {
        return tdyIntincexpAdd;
    }

    /**
     * 当日发生利息收入
     * @param tdyIntincexpAdd 当日发生利息收入
     */
    public void setTdyIntincexpAdd(BigDecimal tdyIntincexpAdd) {
        this.tdyIntincexpAdd = tdyIntincexpAdd;
    }

    /**
     * 当日发生价差收入
     * @return TDY_DSCLOSS_ADD 当日发生价差收入
     */
    public BigDecimal getTdyDsclossAdd() {
        return tdyDsclossAdd;
    }

    /**
     * 当日发生价差收入
     * @param tdyDsclossAdd 当日发生价差收入
     */
    public void setTdyDsclossAdd(BigDecimal tdyDsclossAdd) {
        this.tdyDsclossAdd = tdyDsclossAdd;
    }

    /**
     * 当日发生浮动盈亏
     * @return TDY_FLOAT_INGPL_ADD 当日发生浮动盈亏
     */
    public BigDecimal getTdyFloatIngplAdd() {
        return tdyFloatIngplAdd;
    }

    /**
     * 当日发生浮动盈亏
     * @param tdyFloatIngplAdd 当日发生浮动盈亏
     */
    public void setTdyFloatIngplAdd(BigDecimal tdyFloatIngplAdd) {
        this.tdyFloatIngplAdd = tdyFloatIngplAdd;
    }

    /**
     * 当日发生费用支出
     * @return TDY_FEE_ADD 当日发生费用支出
     */
    public BigDecimal getTdyFeeAdd() {
        return tdyFeeAdd;
    }

    /**
     * 当日发生费用支出
     * @param tdyFeeAdd 当日发生费用支出
     */
    public void setTdyFeeAdd(BigDecimal tdyFeeAdd) {
        this.tdyFeeAdd = tdyFeeAdd;
    }

    /**
     * 累计单位净值
     * @return ACCU_NET_VALUE 累计单位净值
     */
    public BigDecimal getAccuNetValue() {
        return accuNetValue;
    }

    /**
     * 累计单位净值
     * @param accuNetValue 累计单位净值
     */
    public void setAccuNetValue(BigDecimal accuNetValue) {
        this.accuNetValue = accuNetValue;
    }

    /**
     * 创建人
     * @return CREATE_USER 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 创建人
     * @param createUser 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * 创建部门
     * @return CREATE_DEPT 创建部门
     */
    public String getCreateDept() {
        return createDept;
    }

    /**
     * 创建部门
     * @param createDept 创建部门
     */
    public void setCreateDept(String createDept) {
        this.createDept = createDept == null ? null : createDept.trim();
    }

    /**
     * 创建时间
     * @return CREATE_TIME 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新人
     * @return UPDATE_USER 更新人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 更新人
     * @param updateUser 更新人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * 更新时间
     * @return UPDATE_TIME 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}