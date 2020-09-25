package com.joyintech.service.interf.ibillinfo.entity;

import java.math.BigDecimal;

/**
 * 单据信息输出入lc0001成立单据；lc0002到期单据
 * 
 * @author 杨静敏
 * @version 1.0 2018年11月21日
 * @see InterfBillInEntity
 * @since 1.0
 */
public class InterfBillInEntity {

    /**
     * 金融产品代码
     */
    private String finProdId;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 销售部门
     */
    private String saleDept;

    /**
     * 销售开始日
     */
    private String startdate;

    /**
     * 销售截止日
     */
    private String enddate;

    /**
     * 平安业务类型
     */
    private String pabussType;

    /**
     * 产品类型
     */
    private String prodType2;

    /**
     * 交易汇总编号
     */
    private String totleTradeId;

    /**
     * 交易方向：产品到期或产品成立
     */
    private String tradePS;

    /**
     * 交易方向2：产品现金分红或中间付息
     */
    private String tradePS2;

    /**
     * 是否分层
     */
    private String isLay;

    /**
     * 委托方,账户分行行号
     */
    private String entruster;

    /**
     * 分层收益率
     */
    private String layYield;

    /**
     * 交易本金
     */
    private BigDecimal amt;

    /**
     * 分层认购金额
     */
    private String layAmt;

    /**
     * 分层本息合计
     */
    private String layAmtYield;

    /**
     * 分层收益
     */
    private String levelactintamt;

    /**
     * 计息基础
     */
    private String basis;

    /**
     * 收益类型
     */
    private String profitType;

    /**
     * 收益率
     */
    private BigDecimal yield;

    /**
     * 产品起息日
     */
    private String vdate;

    /**
     * 处理模式
     */
    private String dealType;

    /**
     * 获取 处理模式
     * 
     * @return 处理模式.
     */
    public String getDealType() {
        return dealType;
    }

    /**
     * 设置 处理模式
     * 
     * @param dealType 处理模式
     */
    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    /**
     * 获取 产品起息日
     * 
     * @return 产品起息日.
     */
    public String getVdate() {
        return vdate;
    }

    /**
     * 设置 产品起息日
     * 
     * @param vdate 产品起息日
     */
    public void setVdate(String vdate) {
        this.vdate = vdate;
    }

    /**
     * 获取 平安业务类型
     * 
     * @return 平安业务类型
     */
    public String getPabussType() {
        return pabussType;
    }

    /**
     * 设置 平安业务类型
     * 
     * @param pabussType 平安业务类型
     */
    public void setPabussType(String pabussType) {
        this.pabussType = pabussType;
    }

    /**
     * 获取 产品类型
     * 
     * @return 产品类型
     */
    public String getProdType2() {
        return prodType2;
    }

    /**
     * 设置 产品类型
     * 
     * @param prodType2 产品类型
     */
    public void setProdType2(String prodType2) {
        this.prodType2 = prodType2;
    }

    /**
     * 获取 交易汇总编号
     * 
     * @return 交易汇总编号
     */
    public String getTotleTradeId() {
        return totleTradeId;
    }

    /**
     * 设置 交易汇总编号
     * 
     * @param totleTradeId 交易汇总编号
     */
    public void setTotleTradeId(String totleTradeId) {
        this.totleTradeId = totleTradeId;
    }

    /**
     * 获取 交易方向
     * 
     * @return 交易方向
     */
    public String getTradePS() {
        return tradePS;
    }

    /**
     * 设置 交易方向
     * 
     * @param tradePS 交易方向
     */
    public void setTradePS(String tradePS) {
        this.tradePS = tradePS;
    }

    /**
     * 获取 交易方向2
     * 
     * @return 交易方向2
     */
    public String getTradePS2() {
        return tradePS2;
    }

    /**
     * 设置 交易方向
     * 
     * @param tradePS2 交易方向
     */
    public void setTradePS2(String tradePS2) {
        this.tradePS2 = tradePS2;
    }

    /**
     * 获取 金融产品代码
     * 
     * @return 金融产品代码
     */
    public String getFinProdId() {
        return finProdId;
    }

    /**
     * 设置 金融产品代码
     * 
     * @param finProdId 金融产品代码
     */
    public void setFinProdId(String finProdId) {
        this.finProdId = finProdId;
    }

    /**
     * 获取 产品代码
     * 
     * @return 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品代码
     * 
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    /**
     * 获取 销售部门
     * 
     * @return 销售部门
     */
    public String getSaleDept() {
        return saleDept;
    }

    /**
     * 设置 销售部门
     * 
     * @param saleDept 销售部门
     */
    public void setSaleDept(String saleDept) {
        this.saleDept = saleDept;
    }

    /**
     * 获取 金额
     * 
     * @return 金额
     */
    public BigDecimal getAmt() {
        return amt;
    }

    /**
     * 设置 金额
     * 
     * @param amt 金额
     */
    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    /**
     * 取得是否分层
     * 
     * @return 是否分层
     */
    public String getIsLay() {
        return isLay;
    }

    /**
     * 设置是否分层
     * 
     * @param isLay 是否分层
     * @see
     * @since 1.0 isLay
     */
    public void setIsLay(String isLay) {
        this.isLay = isLay;
    }

    /**
     * 获取 账户分行行号
     * 
     * @return 账户分行行号
     */
    public String getEntruster() {
        return entruster;
    }

    /**
     * 设置账户分行行号
     * 
     * @param entruster 嘶Х中行泻?
     */
    public void setEntruster(String entruster) {
        this.entruster = entruster;
    }

    /**
     * 获取 分层收益率
     * 
     * @return 分层收益率
     */
    public String getLayYield() {
        return layYield;
    }

    /**
     * 获取 分层金额
     * 
     * @return 分层金额
     */
    public String getLayAmt() {
        return layAmt;
    }

    /**
     * 设置 分层收益率
     * 
     * @param layYield 分层收益率
     */
    public void setLayYield(String layYield) {
        this.layYield = layYield;
    }

    /**
     * 设置 分层金额
     * 
     * @param layAmt 分层金额
     */
    public void setLayAmt(String layAmt) {
        this.layAmt = layAmt;
    }

    /**
     * 获取 计息基础
     * 
     * @return 计息基础
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 设置 计息基础
     * 
     * @param basis 计息基础
     */
    public void setBasis(String basis) {
        this.basis = basis;
    }

    /**
     * 获取分层收益
     * 
     * @return 分层收益
     */
    public String getLevelactintamt() {
        return levelactintamt;
    }

    /**
     * 设置分层收益
     * 
     * @param levelactintamt 分层收益
     */
    public void setLevelactintamt(String levelactintamt) {
        this.levelactintamt = levelactintamt;
    }

    /**
     * 获取 收益类型
     * 
     * @return 收益类型
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 收益类型
     * 
     * @param profitType 收益类型
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    /**
     * 获取 分层本息合计
     * 
     * @return 分层本息合计
     */
    public String getLayAmtYield() {
        return layAmtYield;
    }

    /**
     * 设置 分层本息合计
     * 
     * @param layAmtYield 分层本息合计
     */
    public void setLayAmtYield(String layAmtYield) {
        this.layAmtYield = layAmtYield;
    }

    /**
     * 获取 收益率
     * 
     * @return 收益率
     */
    public BigDecimal getYield() {
        return yield;
    }

    /**
     * 设置 收益率
     * 
     * @param yield 收益率
     */
    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    /**
     * 获取 销售开始日
     * 
     * @return 销售开始日
     */
    public String getStartdate() {
        return startdate;
    }

    /**
     * 设置 销售开始日
     * 
     * @param startdate 销售开始日
     */
    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    /**
     * 获取 销售截止日
     * 
     * @return 销售截止日
     */
    public String getEnddate() {
        return enddate;
    }

    /**
     * 设置 销售截止日
     * 
     * @param enddate 销售截止日
     */
    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

}
