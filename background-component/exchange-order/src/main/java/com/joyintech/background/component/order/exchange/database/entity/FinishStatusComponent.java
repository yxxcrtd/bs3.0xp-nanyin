package com.joyintech.background.component.order.exchange.database.entity;

import com.joyintech.background.component.common.pack.common.base.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;

public class FinishStatusComponent extends BaseEntity {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 1013263224379002766L;

    /**
     * 委托代码
     */
    private String entrustId;

    /**
     * 交易编号
     */
    private String tradeId;

    /**
     * 完成日期
     */
    private Date finishDate;

    /**
     * 交易完成额度
     */
    private BigDecimal finishAmt;

    /**
     * 交易员
     */
    private String trader;

    /**
     * 委托完成额度
     */
    private BigDecimal entFinishAmt;

    /**
     * 订单完成额度
     */
    private BigDecimal orderFinishAmt;

    /**
     * 确认完成额度
     */
    private BigDecimal confirmFinishAmt;

    /**
     * 取得 委托代码
     * 
     * @return 委托代码
     */
    public String getEntrustId() {
        return entrustId;
    }

    /**
     * 设置 委托代码
     * 
     * @param entrustId 委托代码
     */
    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId == null ? null : entrustId.trim();
    }

    /**
     * 取得 交易编号
     * 
     * @return 交易编号
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 交易编号
     * 
     * @param tradeId 交易编号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    /**
     * 取得 完成日期
     * 
     * @return 完成日期
     */
    public Date getFinishDate() {
        return finishDate;
    }

    /**
     * 设置 完成日期
     * @param finishDate 完成日期
     */
    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    /**
     * 取得 交易完成额度
     * @return 交易完成额度
     */
    public BigDecimal getFinishAmt() {
        return finishAmt;
    }

    /**
     * 设置 交易完成额度
     * @param finishAmt 交易完成额度
     */
    public void setFinishAmt(BigDecimal finishAmt) {
        this.finishAmt = finishAmt;
    }

    /**
     * 取得 交易员
     * @return 交易员
     */
    public String getTrader() {
        return trader;
    }

    /**
     * 设置 交易员
     * @param trader 交易员
     */
    public void setTrader(String trader) {
        this.trader = trader == null ? null : trader.trim();
    }

    /**
     * 取得 委托完成额度
     * @return 委托完成额度
     */
    public BigDecimal getEntFinishAmt() {
        return entFinishAmt;
    }

    /**
     * 设置 委托完成额度
     * @param entFinishAmt 委托完成额度
     */
    public void setEntFinishAmt(BigDecimal entFinishAmt) {
        this.entFinishAmt = entFinishAmt;
    }

    /**
     * 取得 订单完成额度
     * @return 订单完成额度
     */
    public BigDecimal getOrderFinishAmt() {
        return orderFinishAmt;
    }

    /**
     * 设置 订单完成额度
     * @param orderFinishAmt 订单完成额度
     */
    public void setOrderFinishAmt(BigDecimal orderFinishAmt) {
        this.orderFinishAmt = orderFinishAmt;
    }

    /**
     * 获取 确认完成额度
     * 
     * @return 确认完成额度.
     */
    public BigDecimal getConfirmFinishAmt() {
        return confirmFinishAmt;
    }

    /**
     * 设置 确认完成额度
     * 
     * @param confirmFinishAmt 确认完成额度
     */
    public void setConfirmFinishAmt(BigDecimal confirmFinishAmt) {
        this.confirmFinishAmt = confirmFinishAmt;
    }
}