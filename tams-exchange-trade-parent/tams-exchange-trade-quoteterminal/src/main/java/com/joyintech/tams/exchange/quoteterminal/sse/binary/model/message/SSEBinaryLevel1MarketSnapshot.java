package com.joyintech.tams.exchange.quoteterminal.sse.binary.model.message;


import com.joyintech.tams.exchange.quoteterminal.sse.binary.enums.SSEBinaryMDEntryTypeEnum;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field.MDEntryPositionNo;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field.MDEntryType;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field.NoMDEntries;
import io.netty.buffer.ByteBuf;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * 集中竞价交易行情
 */

public class SSEBinaryLevel1MarketSnapshot extends SSEBinaryMarketSnapshot {

    private List<Double> offerPrices = new LinkedList<>();

    private List<Double> bidPrices = new LinkedList<>();

    private List<Long> offerVolumes = new LinkedList<>();

    private List<Long> bidVolumes = new LinkedList<>();
    /**
     * 最新价
     */
    private double lastPx;
    /**
     * 开盘价
     */
    private double openPx;
    /**
     * 收盘价
     */
    private double closePx;
    /**
     * 结算价
     */
    private double settlePx;
    /**
     * 最高价
     */
    private double highPx;
    /**
     * 最低价
     */
    private double lowPx;
    /**
     * IOPV
     */
    private double iopv;
    /**
     * 前一日 IOPV
     */
    private double preIopv;
    /**
     * 动态参考价格
     */
    private double dynamicPx;
    /**
     * 虚拟匹配数量
     */
    private long virtualMatchVolume;
    /**
     * 昨日结算价
     */
    private double preSettlePx;

    /**
     * 总持仓量
     */
    private double allPosition;

    public void addBidPx(double px) {
        bidPrices.add(px);
    }

    public void addBidPx(double px, int level) {
        bidPrices.add(level, px);
    }

    public void addBidVolume(long volume) {
        bidVolumes.add(volume);
    }

    public void addBidVolume(long volume, int level) {
        bidVolumes.add(level, volume);
    }

    public void addOfferPx(double px) {
        offerPrices.add(px);
    }

    public void addOfferPx(double px, int level) {
        offerPrices.add(level, px);
    }

    public void addOfferVolume(long volume) {
        offerVolumes.add(volume);
    }

    public void addOfferVolume(long volume, int level) {
        offerVolumes.add(level, volume);
    }

    @Override
    public long getTotalVolumeTraded() {
        return super.getTotalVolumeTraded();
    }

    @Override
    public long getTotalValueTraded() {
        return super.getTotalValueTraded() / 100;
    }

    @Override
    protected void read(ByteBuf body) {
        super.read(body);
        //重复组, 代表有多少个档位
        NoMDEntries noMDEntries = new NoMDEntries(body.readUnsignedShort());
        for (int i = 0; i < noMDEntries.getValue(); i++) {
            MDEntryType mdEntryType = new MDEntryType(
                    body.readCharSequence(MDEntryType.FIELD_LENGTH, Message.GBK).toString());
            SSEBinaryMDEntryTypeEnum mdEntryTypeEnum = mdEntryType.getMdEntryTypeEnum();
            long mdEntryPx = body.readLong();
            long mdEntrySize = body.readLong();
            MDEntryPositionNo mdEntryPositionNo = new MDEntryPositionNo(body.readUnsignedByte());
            setPositionDetail(mdEntryTypeEnum, mdEntryPositionNo.getValue() - 1, mdEntrySize, mdEntryPx);
        }
    }

    private void setPositionDetail(SSEBinaryMDEntryTypeEnum mdEntryTypeEnum, int level, long volume, long price) {
        if (mdEntryTypeEnum == null) {
            return;
        }
        double px = price / 100000.0;
        switch (mdEntryTypeEnum) {
            case BUY:
                addBidPx(px, level);
                addBidVolume(volume, level);
                break;
            case SELL:
                addOfferPx(px, level);
                addOfferVolume(volume, level);
                break;
            case LAST:
                this.lastPx = px;
                break;
            case OPEN:
                this.openPx = px;
                break;
            case CLOSE:
                this.closePx = px;
                break;
            case SETTLE:
                this.settlePx = px;
                break;
            case HIGH:
                this.highPx = px;
                break;
            case LOW:
                this.lowPx = px;
                break;
            case IOPV:
                this.iopv = px;
                break;
            case PRE_IOPV:
                this.preIopv = px;
                break;
            case DYNAMIC:
                this.dynamicPx = px;
                this.virtualMatchVolume = volume;
                break;
            case PRE_SETTLE:
                this.preSettlePx = px;
                break;
            case ALL_POSITION:
                this.allPosition = volume;
                break;
            default:
                break;
        }
    }

    public List<Double> getOfferPrices() {
        return offerPrices;
    }

    public void setOfferPrices(List<Double> offerPrices) {
        this.offerPrices = offerPrices;
    }

    public List<Double> getBidPrices() {
        return bidPrices;
    }

    public void setBidPrices(List<Double> bidPrices) {
        this.bidPrices = bidPrices;
    }

    public List<Long> getOfferVolumes() {
        return offerVolumes;
    }

    public void setOfferVolumes(List<Long> offerVolumes) {
        this.offerVolumes = offerVolumes;
    }

    public List<Long> getBidVolumes() {
        return bidVolumes;
    }

    public void setBidVolumes(List<Long> bidVolumes) {
        this.bidVolumes = bidVolumes;
    }

    public double getLastPx() {
        return lastPx;
    }

    public void setLastPx(double lastPx) {
        this.lastPx = lastPx;
    }

    public double getOpenPx() {
        return openPx;
    }

    public void setOpenPx(double openPx) {
        this.openPx = openPx;
    }

    public double getClosePx() {
        return closePx;
    }

    public void setClosePx(double closePx) {
        this.closePx = closePx;
    }

    public double getSettlePx() {
        return settlePx;
    }

    public void setSettlePx(double settlePx) {
        this.settlePx = settlePx;
    }

    public double getHighPx() {
        return highPx;
    }

    public void setHighPx(double highPx) {
        this.highPx = highPx;
    }

    public double getLowPx() {
        return lowPx;
    }

    public void setLowPx(double lowPx) {
        this.lowPx = lowPx;
    }

    public double getIopv() {
        return iopv;
    }

    public void setIopv(double iopv) {
        this.iopv = iopv;
    }

    public double getPreIopv() {
        return preIopv;
    }

    public void setPreIopv(double preIopv) {
        this.preIopv = preIopv;
    }

    public double getDynamicPx() {
        return dynamicPx;
    }

    public void setDynamicPx(double dynamicPx) {
        this.dynamicPx = dynamicPx;
    }

    public long getVirtualMatchVolume() {
        return virtualMatchVolume;
    }

    public void setVirtualMatchVolume(long virtualMatchVolume) {
        this.virtualMatchVolume = virtualMatchVolume;
    }

    public double getPreSettlePx() {
        return preSettlePx;
    }

    public void setPreSettlePx(double preSettlePx) {
        this.preSettlePx = preSettlePx;
    }

    public double getAllPosition() {
        return allPosition;
    }

    public void setAllPosition(double allPosition) {
        this.allPosition = allPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SSEBinaryLevel1MarketSnapshot)) return false;
        if (!super.equals(o)) return false;
        SSEBinaryLevel1MarketSnapshot that = (SSEBinaryLevel1MarketSnapshot) o;
        return Double.compare(that.getLastPx(), getLastPx()) == 0 &&
                Double.compare(that.getOpenPx(), getOpenPx()) == 0 &&
                Double.compare(that.getClosePx(), getClosePx()) == 0 &&
                Double.compare(that.getSettlePx(), getSettlePx()) == 0 &&
                Double.compare(that.getHighPx(), getHighPx()) == 0 &&
                Double.compare(that.getLowPx(), getLowPx()) == 0 &&
                Double.compare(that.getIopv(), getIopv()) == 0 &&
                Double.compare(that.getPreIopv(), getPreIopv()) == 0 &&
                Double.compare(that.getDynamicPx(), getDynamicPx()) == 0 &&
                getVirtualMatchVolume() == that.getVirtualMatchVolume() &&
                Double.compare(that.getPreSettlePx(), getPreSettlePx()) == 0 &&
                Double.compare(that.getAllPosition(), getAllPosition()) == 0 &&
                Objects.equals(getOfferPrices(), that.getOfferPrices()) &&
                Objects.equals(getBidPrices(), that.getBidPrices()) &&
                Objects.equals(getOfferVolumes(), that.getOfferVolumes()) &&
                Objects.equals(getBidVolumes(), that.getBidVolumes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getOfferPrices(), getBidPrices(), getOfferVolumes(), getBidVolumes(), getLastPx(), getOpenPx(), getClosePx(), getSettlePx(), getHighPx(), getLowPx(), getIopv(), getPreIopv(), getDynamicPx(), getVirtualMatchVolume(), getPreSettlePx(), getAllPosition());
    }

    @Override
    public String toString() {
        return "SSEBinaryLevel1MarketSnapshot{" +
                "offerPrices=" + offerPrices +
                ", bidPrices=" + bidPrices +
                ", offerVolumes=" + offerVolumes +
                ", bidVolumes=" + bidVolumes +
                ", lastPx=" + lastPx +
                ", openPx=" + openPx +
                ", closePx=" + closePx +
                ", settlePx=" + settlePx +
                ", highPx=" + highPx +
                ", lowPx=" + lowPx +
                ", iopv=" + iopv +
                ", preIopv=" + preIopv +
                ", dynamicPx=" + dynamicPx +
                ", virtualMatchVolume=" + virtualMatchVolume +
                ", preSettlePx=" + preSettlePx +
                ", allPosition=" + allPosition +
                ", securityType=" + securityType +
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
