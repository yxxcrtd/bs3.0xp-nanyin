package com.joyintech.tams.exchange.quoteterminal.sse.binary.model.message;


import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field.*;
import io.netty.buffer.ByteBuf;

import java.util.Objects;

public class SSEBinaryMarketSnapshot extends Message {
    /**
     * 证券类型
     */
    protected SecurityType securityType;
    /**
     * 交易盘交易模式
     */
    protected TradSesMode tradSesMode;
    /**
     * 交易日期 YYYYMMDD
     */
    protected long tradeDate;
    /**
     * 最新更新时间 HHMMSSsss
     */
    protected long lastUpdateTime;
    /**
     * 行情类别
     */
    protected MDStreamId mdStreamId;
    /**
     * 产品代码
     */
    protected SecurityId securityId;
    /**
     * 产品简称
     */
    protected Symbol symbol;
    /**
     * 昨收价 , 转成实际价格需要 除以 100000.
     */
    protected PreClosePx preClosePx;
    /**
     * 成交数量
     */
    protected long totalVolumeTraded;
    /**
     * 成交笔数
     */
    protected long numTrades;
    /**
     * 成交金额  , 转成实际价格需要 除以 100.
     */
    protected long totalValueTraded;
    /**
     * 实时阶段及标志
     */
    protected TradingPhaseCode tradingPhaseCode;

    @Override
    protected byte[] toBytes() {
        return new byte[0];
    }

    @Override
    protected void read(ByteBuf body) {
        this.securityType = new SecurityType(body.readUnsignedByte());
        this.tradSesMode = new TradSesMode(body.readUnsignedByte());
        this.tradeDate = body.readInt();
        this.lastUpdateTime = body.readInt();
        this.mdStreamId = new MDStreamId(body.readCharSequence(MDStreamId.FIELD_LENGTH, Message.GBK).toString());
        this.securityId = new SecurityId(body.readCharSequence(SecurityId.FIELD_LENGTH, Message.GBK).toString());
        this.symbol = new Symbol(body.readCharSequence(Symbol.FIELD_LENGTH, Message.GBK).toString());
        this.preClosePx = new PreClosePx(body.readLong());
        this.totalVolumeTraded = body.readLong();
        this.numTrades = body.readLong();
        this.totalValueTraded = body.readLong();
        this.tradingPhaseCode = new TradingPhaseCode(body.readCharSequence(TradingPhaseCode.FIELD_LENGTH, Message.GBK).toString());
    }

    public SecurityType getSecurityType() {
        return securityType;
    }

    public void setSecurityType(SecurityType securityType) {
        this.securityType = securityType;
    }

    public TradSesMode getTradSesMode() {
        return tradSesMode;
    }

    public void setTradSesMode(TradSesMode tradSesMode) {
        this.tradSesMode = tradSesMode;
    }

    public long getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(long tradeDate) {
        this.tradeDate = tradeDate;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public MDStreamId getMdStreamId() {
        return mdStreamId;
    }

    public void setMdStreamId(MDStreamId mdStreamId) {
        this.mdStreamId = mdStreamId;
    }

    public SecurityId getSecurityId() {
        return securityId;
    }

    public void setSecurityId(SecurityId securityId) {
        this.securityId = securityId;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public PreClosePx getPreClosePx() {
        return preClosePx;
    }

    public void setPreClosePx(PreClosePx preClosePx) {
        this.preClosePx = preClosePx;
    }

    public long getTotalVolumeTraded() {
        return totalVolumeTraded;
    }

    public void setTotalVolumeTraded(long totalVolumeTraded) {
        this.totalVolumeTraded = totalVolumeTraded;
    }

    public long getNumTrades() {
        return numTrades;
    }

    public void setNumTrades(long numTrades) {
        this.numTrades = numTrades;
    }

    public long getTotalValueTraded() {
        return totalValueTraded;
    }

    public void setTotalValueTraded(long totalValueTraded) {
        this.totalValueTraded = totalValueTraded;
    }

    public TradingPhaseCode getTradingPhaseCode() {
        return tradingPhaseCode;
    }

    public void setTradingPhaseCode(TradingPhaseCode tradingPhaseCode) {
        this.tradingPhaseCode = tradingPhaseCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SSEBinaryMarketSnapshot)) return false;
        SSEBinaryMarketSnapshot that = (SSEBinaryMarketSnapshot) o;
        return getTradeDate() == that.getTradeDate() &&
                getLastUpdateTime() == that.getLastUpdateTime() &&
                getTotalVolumeTraded() == that.getTotalVolumeTraded() &&
                getNumTrades() == that.getNumTrades() &&
                getTotalValueTraded() == that.getTotalValueTraded() &&
                Objects.equals(getSecurityType(), that.getSecurityType()) &&
                Objects.equals(getTradSesMode(), that.getTradSesMode()) &&
                Objects.equals(getMdStreamId(), that.getMdStreamId()) &&
                Objects.equals(getSecurityId(), that.getSecurityId()) &&
                Objects.equals(getSymbol(), that.getSymbol()) &&
                Objects.equals(getPreClosePx(), that.getPreClosePx()) &&
                Objects.equals(getTradingPhaseCode(), that.getTradingPhaseCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSecurityType(), getTradSesMode(), getTradeDate(), getLastUpdateTime(), getMdStreamId(), getSecurityId(), getSymbol(), getPreClosePx(), getTotalVolumeTraded(), getNumTrades(), getTotalValueTraded(), getTradingPhaseCode());
    }

    @Override
    public String toString() {
        return "SSEBinaryMarketSnapshot{" +
                "securityType=" + securityType +
                ", tradSesMode=" + tradSesMode +
                ", tradeDate=" + tradeDate +
                ", lastUpdateTime=" + lastUpdateTime +
                ", mdStreamId=" + mdStreamId +
                ", securityId=" + securityId +
                ", symbol=" + symbol +
                ", preClosePx=" + preClosePx +
                ", totalVolumeTraded=" + totalVolumeTraded +
                ", numTrades=" + numTrades +
                ", totalValueTraded=" + totalValueTraded +
                ", tradingPhaseCode=" + tradingPhaseCode +
                ", msgType=" + msgType +
                '}';
    }
}
