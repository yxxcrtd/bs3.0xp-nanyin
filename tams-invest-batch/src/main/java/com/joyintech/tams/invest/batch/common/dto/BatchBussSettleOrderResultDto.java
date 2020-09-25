/*
 * 文件名：BatchBussSettleOrderResultDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.common.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.StlCapacctInfo;

/**
 * 清算指令生成结果dto（批处理用）
 *
 * @author etliu
 * @version 1.0 2018年12月12日
 * @see BatchBussSettleOrderResultDto
 * @since 1.0
 */
public class BatchBussSettleOrderResultDto {

    /**
     * 指令编号
     */
    private String orderId;

    /**
     * 指令类型
     */
    private String payType;

    /**
     * 结算日期
     */
    private Date settleDate;

    /**
     * 付款账户
     */
    private List<StlCapacctInfo> myCapacctInfoList;

    /**
     * 本方资金账户
     */
    private String myAcctNo;

    /**
     * 收付方向
     */
    private String ps;

    /**
     * 收款账户
     */
    private List<StlCapacctInfo> counterCapacctInfoList;

    /**
     * 对方资金账户
     */
    private String counterAcctNo;

    /**
     * 结算金额
     */
    private BigDecimal settleAmt;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 结算状态
     */
    private String settleStatus;

    /**
     * 是否需要发送
     */
    private String isNeedSend;

    /**
     * 是否汇总交易
     */
    private String isTotalTrade;

    /**
     * 交易编号
     */
    private String tradeId;

    /**
     * 指令状态
     */
    private String orderStatus;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 获取 orderId
     *
     * @return orderId
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置 orderId
     *
     * @param orderId orderId
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取 payType
     *
     * @return payType
     */
    public String getPayType() {
        return payType;
    }

    /**
     * 设置 payType
     *
     * @param payType payType
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }

    /**
     * 获取 settleDate
     *
     * @return settleDate
     */
    public Date getSettleDate() {
        return settleDate;
    }

    /**
     * 设置 settleDate
     *
     * @param settleDate settleDate
     */
    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    /**
     * 获取 myCapacctInfoList
     *
     * @return myCapacctInfoList
     */
    public List<StlCapacctInfo> getMyCapacctInfoList() {
        return myCapacctInfoList;
    }

    /**
     * 设置 myCapacctInfoList
     *
     * @param myCapacctInfoList myCapacctInfoList
     */
    public void setMyCapacctInfoList(List<StlCapacctInfo> myCapacctInfoList) {
        this.myCapacctInfoList = myCapacctInfoList;
    }

    /**
     * 获取 ps
     *
     * @return ps
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
     * 获取 counterCapacctInfoList
     *
     * @return counterCapacctInfoList
     */
    public List<StlCapacctInfo> getCounterCapacctInfoList() {
        return counterCapacctInfoList;
    }

    /**
     * 设置 counterCapacctInfoList
     *
     * @param counterCapacctInfoList counterCapacctInfoList
     */
    public void setCounterCapacctInfoList(List<StlCapacctInfo> counterCapacctInfoList) {
        this.counterCapacctInfoList = counterCapacctInfoList;
    }

    /**
     * 获取 settleAmt
     *
     * @return settleAmt
     */
    public BigDecimal getSettleAmt() {
        return settleAmt;
    }

    /**
     * 设置 settleAmt
     *
     * @param settleAmt settleAmt
     */
    public void setSettleAmt(BigDecimal settleAmt) {
        this.settleAmt = settleAmt;
    }

    /**
     * 获取 ccy
     *
     * @return ccy
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
     * 获取 settleStatus
     *
     * @return settleStatus
     */
    public String getSettleStatus() {
        return settleStatus;
    }

    /**
     * 设置 settleStatus
     *
     * @param settleStatus settleStatus
     */
    public void setSettleStatus(String settleStatus) {
        this.settleStatus = settleStatus;
    }

    /**
     * 获取 isNeedSend
     *
     * @return isNeedSend
     */
    public String getIsNeedSend() {
        return isNeedSend;
    }

    /**
     * 设置 isNeedSend
     *
     * @param isNeedSend isNeedSend
     */
    public void setIsNeedSend(String isNeedSend) {
        this.isNeedSend = isNeedSend;
    }

    /**
     * 获取 isTotalTrade
     *
     * @return isTotalTrade
     */
    public String getIsTotalTrade() {
        return isTotalTrade;
    }

    /**
     * 设置 isTotalTrade
     *
     * @param isTotalTrade isTotalTrade
     */
    public void setIsTotalTrade(String isTotalTrade) {
        this.isTotalTrade = isTotalTrade;
    }

    /**
     * 获取 tradeId
     *
     * @return tradeId
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 tradeId
     *
     * @param tradeId tradeId
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    /**
     * 获取 orderStatus
     *
     * @return orderStatus
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * 设置 orderStatus
     *
     * @param orderStatus orderStatus
     */
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
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
     * 获取 myAcctNo
     *
     * @return myAcctNo.
     */
    public String getMyAcctNo() {
        return myAcctNo;
    }

    /**
     * 设置 myAcctNo
     *
     * @param myAcctNo myAcctNo
     */
    public void setMyAcctNo(String myAcctNo) {
        this.myAcctNo = myAcctNo;
    }

    /**
     * 获取 counterAcctNo
     *
     * @return counterAcctNo.
     */
    public String getCounterAcctNo() {
        return counterAcctNo;
    }

    /**
     * 设置 counterAcctNo
     *
     * @param counterAcctNo counterAcctNo
     */
    public void setCounterAcctNo(String counterAcctNo) {
        this.counterAcctNo = counterAcctNo;
    }
}
