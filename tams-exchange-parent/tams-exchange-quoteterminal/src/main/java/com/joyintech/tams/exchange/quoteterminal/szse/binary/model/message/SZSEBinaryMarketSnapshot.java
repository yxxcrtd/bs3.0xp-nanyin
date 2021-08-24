package com.joyintech.tams.exchange.quoteterminal.szse.binary.model.message;


import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field.*;
import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class SZSEBinaryMarketSnapshot extends Message{
    /**
     * 数据生成时间
     */
    protected long origTime;
    /**
     * 频道代码
     */
    protected ChannelNo channelNo;
    /**
     * 行情类别
     */
    protected MDStreamId mdStreamId;
    /**
     * 证券代码
     */
    protected SecurityId securityId;
    /**
     * 证券代码源
     */
    protected SecurityIdSource securityIdSource;
    /**
     * 交易状态
     */
    protected TradePhaseCode tradePhaseCode;
    /**
     * 昨收价 , 转成实际价格需要 除以 10000.
     */
    protected PrevClosePx prevClosePx;
    /**
     * 成交笔数
     */
    protected long numberTrades;
    /**
     * 成交总量
     */
    protected long totalVolumeTrade;
    /**
     * 成交总金额
     */
    protected long totalValueTrade;

    @Override
    protected byte[] toBytes() {
        return new byte[0];
    }

    @Override
    protected void read(ByteBuf body) {
        this.origTime = body.readLong();
        this.channelNo = new ChannelNo(body.readUnsignedShort());
        this.mdStreamId = new MDStreamId(body.readCharSequence(MDStreamId.FIELD_LENGTH, StandardCharsets.UTF_8).toString());
        this.securityId = new SecurityId(body.readCharSequence(SecurityId.FIELD_LENGTH, StandardCharsets.UTF_8).toString());
        this.securityIdSource = new SecurityIdSource(body.readCharSequence(SecurityIdSource.FIELD_LENGTH, StandardCharsets.UTF_8).toString());
        this.tradePhaseCode = new TradePhaseCode(body.readCharSequence(TradePhaseCode.FIELD_LENGTH, StandardCharsets.UTF_8).toString());
        this.prevClosePx = new PrevClosePx(body.readLong());
        this.numberTrades = body.readLong();
        this.totalVolumeTrade = body.readLong();
        this.totalValueTrade = body.readLong();
    }

    public long getOrigTime() {
        return origTime;
    }

    public void setTradeTime(long origTime) {
        this.origTime = origTime;
    }

    public ChannelNo getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(ChannelNo channelNo) {
        this.channelNo = channelNo;
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

    public SecurityIdSource getSecurityIdSource() {
        return securityIdSource;
    }

    public void setSecurityIdSource(SecurityIdSource securityIdSource) {
        this.securityIdSource = securityIdSource;
    }

    public TradePhaseCode getTradePhaseCode() {
        return tradePhaseCode;
    }

    public void setTradePhaseCode(TradePhaseCode tradePhaseCode) {
        this.tradePhaseCode = tradePhaseCode;
    }

    public PrevClosePx getPrevClosePx() {
        return prevClosePx;
    }

    public void setPrevClosePx(PrevClosePx prevClosePx) {
        this.prevClosePx = prevClosePx;
    }

    public long getNumberTrades() {
        return numberTrades;
    }

    public void setNumberTrades(long numberTrades) {
        this.numberTrades = numberTrades;
    }

    public long getTotalVolumeTrade() {
        return totalVolumeTrade;
    }

    public void setTotalVolumeTrade(long totalVolumeTrade) {
        this.totalVolumeTrade = totalVolumeTrade;
    }

    public long getTotalValueTrade() {
        return totalValueTrade;
    }

    public void setTotalValueTrade(long totalValueTrade) {
        this.totalValueTrade = totalValueTrade;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof SZSEBinaryMarketSnapshot)) return false;
        SZSEBinaryMarketSnapshot that = (SZSEBinaryMarketSnapshot) object;
        return origTime == that.origTime &&
                numberTrades == that.numberTrades &&
                totalVolumeTrade == that.totalVolumeTrade &&
                totalValueTrade == that.totalValueTrade &&
                Objects.equals(channelNo, that.channelNo) &&
                Objects.equals(mdStreamId, that.mdStreamId) &&
                Objects.equals(securityId, that.securityId) &&
                Objects.equals(securityIdSource, that.securityIdSource) &&
                Objects.equals(tradePhaseCode, that.tradePhaseCode) &&
                Objects.equals(prevClosePx, that.prevClosePx);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origTime, channelNo, mdStreamId, securityId, securityIdSource, tradePhaseCode, prevClosePx, numberTrades, totalVolumeTrade, totalValueTrade);
    }

    @Override
    public String toString() {
        return "SZSEBinaryMarketSnapshot{" +
                "tradeTime=" + origTime +
                ", channelNo=" + channelNo +
                ", mdStreamId=" + mdStreamId +
                ", securityId=" + securityId +
                ", securityIdSource=" + securityIdSource +
                ", tradePhaseCode=" + tradePhaseCode +
                ", prevClosePx=" + prevClosePx +
                ", numberTrades=" + numberTrades +
                ", totalVolumeTrade=" + totalVolumeTrade +
                ", totalValueTrade=" + totalValueTrade +
                '}';
    }
}
