package com.joyintech.tams.exchange.quoteterminal.szse.binary.model.message;


import com.joyintech.tams.exchange.quoteterminal.szse.binary.enums.SZSEBinaryPriceType;
import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field.MDEntryType;
import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field.MDPriceLevel;
import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field.NoMDEntries;
import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field.NoOrders;
import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * 300111 集中竞价交易行情
 */

public class SZSEBinaryLevel1MarketSnapshot extends SZSEBinaryMarketSnapshot {

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
     * 最高价
     */
    private double highPx;
    /**
     * 最低价
     */
    private double lowPx;
    /**
     * 升跌1
     */
    private double udPx1;
    /**
     * 升跌2
     */
    private double udPx2;
    /**
     * 买入汇总
     */
    private double summaryBuyPx;
    /**
     * 卖出汇总
     */
    private double summarySellPx;
    /**
     * 股票市盈率1
     */
    private double stockPERatio1;

    /**
     * 股票市盈率2
     */
    private double stockPERatio2;
    /**
     * 基金 T-1 日净值
     */
    private double fundNetWorth;
    /**
     * 基金实时参考净值
     */
    private double fundRtReferenceNetWorth;
    /**
     * 权证溢价率
     */
    private double shareWarrantPremiumRate;
    /**
     * 涨停价
     */
    private double highLimitPx;
    /**
     * 跌停价
     */
    private double lowLimitPx;
    /**
     * 合约持仓量
     */
    private double contractPosition;
    /**
     * 加权平均价涨跌BP
     */
    private double weightedAverageBP;
    /**
     * 昨收盘加权平均价
     */
    private double weightedAverageYestadyClosePx;

    /**
     * 结算价格
     */
    private double clearingPrice;

    private long numberOfOrders;
    /**
     * 委托数量
     */
    private List<Long> orderQty;

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
    public long getTotalVolumeTrade() {
        return super.getTotalVolumeTrade() / 100;
    }

    @Override
    public long getTotalValueTrade() {
        return super.getTotalValueTrade() / 10000;
    }

    @Override
    protected void read(ByteBuf body) {
        super.read(body);
        //重复组, 代表有多少个档位
        NoMDEntries noMDEntries = new NoMDEntries(body.readUnsignedInt());
        for(int i = 0; i < noMDEntries.getValue(); i++) {
            MDEntryType mdEntryType = new MDEntryType(
                    body.readCharSequence(MDEntryType.FIELD_LENGTH, StandardCharsets.UTF_8).toString());
            SZSEBinaryPriceType priceTypeEnum = mdEntryType.getPriceTypeEnum();
            long mdEntryPx = body.readLong();
            long mdEntrySize = body.readLong();
            MDPriceLevel mdPriceLevel = new MDPriceLevel(body.readUnsignedShort());
            setPriceDetail(priceTypeEnum, mdPriceLevel.getValue() -1, mdEntrySize, mdEntryPx);
            this.numberOfOrders = body.readLong();
            NoOrders noOrders = new NoOrders(body.readUnsignedInt());
            if(noOrders.getValue() != 0) {
                this.orderQty = new LinkedList<>();
            }
            for(int j = 0; j < noOrders.getValue(); j++) {
                orderQty.add(body.readLong() / 100);
            }
        }
    }

    private void setPriceDetail(SZSEBinaryPriceType priceType, int level, long volume, long price) {
        if(priceType == null) {
            return;
        }
        double px = price / 1000000.0;
        switch(priceType) {
            case BUY:
                addBidPx(px, level);
                addBidVolume(volume / 100, level);
                break;
            case SELL:
                addOfferPx(px, level);
                addOfferVolume(volume / 100, level);
                break;
            case LAST:
                this.lastPx = px;
                break;
            case OPEN:
                this.openPx = px;
                break;
            case HIGH:
                this.highPx = px;
                break;
            case LOW:
                this.lowPx = px;
                break;
            case HIGH_LIMIT:
                this.highLimitPx = px;
                break;
            case LOW_LIMIT:
                this.lowLimitPx = px;
                break;
            case UD1:
                this.udPx1 = px;
                break;
            case UD2:
                this.udPx2 = px;
                break;
            case SUMMARY_BUY:
                this.summaryBuyPx = px;
                break;
            case SUMMARY_SELL:
                this.summarySellPx = px;
                break;
            case STOCK_PE1:
                this.stockPERatio1 = px;
                break;
            case STOCK_PE2:
                this.stockPERatio2 = px;
                break;
            case FUND_DNW:
                this.fundNetWorth = px;
                break;
            case FUND_RT_RNW:
                this.fundRtReferenceNetWorth = px;
                break;
            case WPR:
                this.shareWarrantPremiumRate = px;
                break;
            case CONTRACT_POSITION:
                this.contractPosition = px;
                break;
            case WEIGHTED_AVERAGE_BP:
                this.weightedAverageBP = px;
                break;
            case WEIGHTED_AVERAGE_YESTDAY_CLOSE:
                this.weightedAverageYestadyClosePx = px;
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

    public double getUdPx1() {
        return udPx1;
    }

    public void setUdPx1(double udPx1) {
        this.udPx1 = udPx1;
    }

    public double getUdPx2() {
        return udPx2;
    }

    public void setUdPx2(double udPx2) {
        this.udPx2 = udPx2;
    }

    public double getSummaryBuyPx() {
        return summaryBuyPx;
    }

    public void setSummaryBuyPx(double summaryBuyPx) {
        this.summaryBuyPx = summaryBuyPx;
    }

    public double getSummarySellPx() {
        return summarySellPx;
    }

    public void setSummarySellPx(double summarySellPx) {
        this.summarySellPx = summarySellPx;
    }

    public double getStockPERatio1() {
        return stockPERatio1;
    }

    public void setStockPERatio1(double stockPERatio1) {
        this.stockPERatio1 = stockPERatio1;
    }

    public double getStockPERatio2() {
        return stockPERatio2;
    }

    public void setStockPERatio2(double stockPERatio2) {
        this.stockPERatio2 = stockPERatio2;
    }

    public double getFundNetWorth() {
        return fundNetWorth;
    }

    public void setFundNetWorth(double fundNetWorth) {
        this.fundNetWorth = fundNetWorth;
    }

    public double getFundRtReferenceNetWorth() {
        return fundRtReferenceNetWorth;
    }

    public void setFundRtReferenceNetWorth(double fundRtReferenceNetWorth) {
        this.fundRtReferenceNetWorth = fundRtReferenceNetWorth;
    }

    public double getShareWarrantPremiumRate() {
        return shareWarrantPremiumRate;
    }

    public void setShareWarrantPremiumRate(double shareWarrantPremiumRate) {
        this.shareWarrantPremiumRate = shareWarrantPremiumRate;
    }

    public double getHighLimitPx() {
        return highLimitPx;
    }

    public void setHighLimitPx(double highLimitPx) {
        this.highLimitPx = highLimitPx;
    }

    public double getLowLimitPx() {
        return lowLimitPx;
    }

    public void setLowLimitPx(double lowLimitPx) {
        this.lowLimitPx = lowLimitPx;
    }

    public double getContractPosition() {
        return contractPosition;
    }

    public void setContractPosition(double contractPosition) {
        this.contractPosition = contractPosition;
    }

    public double getWeightedAverageBP() {
        return weightedAverageBP;
    }

    public void setWeightedAverageBP(double weightedAverageBP) {
        this.weightedAverageBP = weightedAverageBP;
    }

    public double getWeightedAverageYestadyClosePx() {
        return weightedAverageYestadyClosePx;
    }

    public void setWeightedAverageYestadyClosePx(double weightedAverageYestadyClosePx) {
        this.weightedAverageYestadyClosePx = weightedAverageYestadyClosePx;
    }

    public long getNumberOfOrders() {
        return numberOfOrders;
    }

    public void setNumberOfOrders(long numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }

    public List<Long> getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(List<Long> orderQty) {
        this.orderQty = orderQty;
    }

    public double getClearingPrice() {
        return clearingPrice;
    }

    public void setClearingPrice(double clearingPrice) {
        this.clearingPrice = clearingPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SZSEBinaryLevel1MarketSnapshot that = (SZSEBinaryLevel1MarketSnapshot) o;
        return Double.compare(that.lastPx, lastPx) == 0 &&
                Double.compare(that.openPx, openPx) == 0 &&
                Double.compare(that.highPx, highPx) == 0 &&
                Double.compare(that.lowPx, lowPx) == 0 &&
                Double.compare(that.udPx1, udPx1) == 0 &&
                Double.compare(that.udPx2, udPx2) == 0 &&
                Double.compare(that.summaryBuyPx, summaryBuyPx) == 0 &&
                Double.compare(that.summarySellPx, summarySellPx) == 0 &&
                Double.compare(that.stockPERatio1, stockPERatio1) == 0 &&
                Double.compare(that.stockPERatio2, stockPERatio2) == 0 &&
                Double.compare(that.fundNetWorth, fundNetWorth) == 0 &&
                Double.compare(that.fundRtReferenceNetWorth, fundRtReferenceNetWorth) == 0 &&
                Double.compare(that.shareWarrantPremiumRate, shareWarrantPremiumRate) == 0 &&
                Double.compare(that.highLimitPx, highLimitPx) == 0 &&
                Double.compare(that.lowLimitPx, lowLimitPx) == 0 &&
                Double.compare(that.contractPosition, contractPosition) == 0 &&
                Double.compare(that.weightedAverageBP, weightedAverageBP) == 0 &&
                Double.compare(that.weightedAverageYestadyClosePx, weightedAverageYestadyClosePx) == 0 &&
                Double.compare(that.clearingPrice, clearingPrice) == 0 &&
                numberOfOrders == that.numberOfOrders &&
                Objects.equals(offerPrices, that.offerPrices) &&
                Objects.equals(bidPrices, that.bidPrices) &&
                Objects.equals(offerVolumes, that.offerVolumes) &&
                Objects.equals(bidVolumes, that.bidVolumes) &&
                Objects.equals(orderQty, that.orderQty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), offerPrices, bidPrices, offerVolumes, bidVolumes, lastPx, openPx, highPx, lowPx, udPx1, udPx2, summaryBuyPx, summarySellPx, stockPERatio1, stockPERatio2, fundNetWorth, fundRtReferenceNetWorth, shareWarrantPremiumRate, highLimitPx, lowLimitPx, contractPosition, weightedAverageBP, weightedAverageYestadyClosePx, clearingPrice, numberOfOrders, orderQty);
    }
}
