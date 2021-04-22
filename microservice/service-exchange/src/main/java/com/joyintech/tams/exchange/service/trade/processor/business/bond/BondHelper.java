package com.joyintech.tams.exchange.service.trade.processor.business.bond;

import com.joyintech.tams.exchange.common.bean.MinTrdUnitLimit;
import com.joyintech.tams.exchange.common.cache.QuoteTickService;
import com.joyintech.tams.exchange.common.constant.*;
import com.joyintech.tams.exchange.common.db.domain.Instruction;
import com.joyintech.tams.exchange.common.db.domain.Instrument;
import com.joyintech.tams.exchange.common.db.domain.OrderInterface;
import com.joyintech.tams.exchange.common.db.domain.Trade;
import com.joyintech.tams.exchange.common.proto.QuoteProto;
import com.joyintech.tams.exchange.common.utils.CheckParamUtils;
import com.joyintech.tams.exchange.common.utils.EntrustUtils;
import com.joyintech.tams.exchange.common.utils.TradeUtils;
import com.joyintech.tams.exchange.datacommon.db.service.InstrumentService;
import com.joyintech.tams.exchange.service.trade.management.service.InvestmentManagement;
import com.joyintech.tams.exchange.service.trade.processor.business.price.PriceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

/**
 * 债券业务处理
 */

@Component
public class BondHelper {
    private static final Logger logger = LoggerFactory.getLogger(BondHelper.class);

    @Autowired
    private QuoteTickService quoteTickService;
    @Autowired
    private InstrumentService instrumentService;
    @Autowired
    private InvestmentManagement investmentManagement;
    @Autowired
    private PriceHelper priceHelper;

    public boolean checkCommonParam(BigDecimal fullPrice, BigDecimal entrustPrice, long entrustVolume, OrderInterface orderInterface, Instrument instrument, String tradePlat, String instructionPs, BigDecimal entrustBalance, String entrustBussType, String priceMode) {
        MinTrdUnitLimit minTrdUnitLimit = instrumentService.getMinTrdUnitLimit(tradePlat, instrument.getMarket(), instrument, instructionPs);
        if (EntrustUtils.isNoTrade(orderInterface.getEntrustProp())) {
            if (Objects.nonNull(minTrdUnitLimit)) {
                if (!CheckParamUtils.checkBuyUnit(orderInterface, entrustVolume, minTrdUnitLimit.getMinStep()))
                    return false;
            } else {
                logger.info("最小交易单位、交易上下限获取失败：TradePlat {}  instrument {}  instructionP {}", tradePlat, instrument.getInnerCode(), instructionPs);
            }
        } else if (EntrustBs.BUY.value.equals(orderInterface.getEntrustBs())
                && instrument.getBuyUnit() > 0
                && entrustVolume % instrument.getBuyUnit() != 0) {
            orderInterface.setErrorCode(ErrorCode.INNER_RISK_ERROR.value);
            orderInterface.setErrorMsg("不满足最小买入单位:" + instrument.getBuyUnit());
            return false;
        }
        boolean isFullPrice = false;
        // 申报类不需要check最小价差和涨跌停
        if (!EntrustUtils.isNoTrade(orderInterface.getEntrustProp())) {
            if (EntrustUtils.isFullPrice(instrument, orderInterface.getAssetType(), orderInterface.getEntrustProp(), orderInterface.getIsFullPrice())) {
                isFullPrice = true;
                if (Double.compare(instrument.getPriceStep().doubleValue(), 0.0) > 0
                        && TradeUtils.divideAndRemainder(fullPrice, instrument.getPriceStep()).compareTo(BigDecimal.ZERO) != 0) {
                    orderInterface.setErrorCode(ErrorCode.INNER_RISK_ERROR.value());
                    orderInterface.setErrorMsg("委托全价价格不满足最小价差：" + instrument.getPriceStep().doubleValue());
                    return false;
                }
                orderInterface.setIsFullPrice(FullPriceConst.FULL_PRICE.value);
            } else {
                orderInterface.setIsFullPrice(FullPriceConst.NET_PRICE.value);
            }
            if (Double.compare(instrument.getPriceStep().doubleValue(), 0.0) > 0
                    && TradeUtils.divideAndRemainder(entrustPrice, instrument.getPriceStep()).compareTo(BigDecimal.ZERO) != 0) {
                orderInterface.setErrorCode(ErrorCode.INNER_RISK_ERROR.value());
                orderInterface.setErrorMsg("委托价格 {" + entrustPrice + "} 不满足最小价差：" + instrument.getPriceStep().doubleValue());
                return false;
            }
            QuoteProto.TickBean tick = quoteTickService.getTickFromCache(orderInterface.getStockCode());
            if (Objects.nonNull(tick)) {
                double lowerLimitPrice = TradeUtils.formatDouble(tick.getLowerLimitPrice(), 4);
                double upperLimitPrice = TradeUtils.formatDouble(tick.getUpperLimitPrice(), 4);
                if (Double.compare(lowerLimitPrice, 0.0) == 0 && Double.compare(upperLimitPrice, 0.0) == 0) {
                    logger.info("没有涨跌幅");
                    boolean checkPrice;
                    if (!isFullPrice) {
                        checkPrice = priceHelper.checkPrice(entrustPrice, orderInterface, instrument, tick, tradePlat, entrustBussType, priceMode);
                    } else {
                        checkPrice = priceHelper.checkPrice(fullPrice, orderInterface, instrument, tick, tradePlat, entrustBussType, priceMode);
                    }
                    if (!checkPrice) {
                        return false;
                    }
                } else {
                    if (!isFullPrice && (Double.compare(lowerLimitPrice, 0.0) > 0 && Double.compare(lowerLimitPrice, entrustPrice.doubleValue()) > 0)
                            || (Double.compare(upperLimitPrice, 0.0) > 0 && Double.compare(upperLimitPrice, entrustPrice.doubleValue()) < 0)) {
                        orderInterface.setErrorCode(ErrorCode.INNER_RISK_ERROR.value);
                        orderInterface.setErrorMsg("不满足涨跌停限制。 跌停价：[" + lowerLimitPrice + "] -- 涨停价：[" + upperLimitPrice + "]; 委托价格：[" + entrustPrice + "]");
                        return false;
                    }
                    if (isFullPrice && (((Double.compare(lowerLimitPrice, 0.0) > 0 && Double.compare(lowerLimitPrice, fullPrice.doubleValue()) > 0)
                            || (Double.compare(upperLimitPrice, 0.0) > 0 && Double.compare(upperLimitPrice, fullPrice.doubleValue()) < 0)))) {
                        orderInterface.setErrorCode(ErrorCode.INNER_RISK_ERROR.value);
                        orderInterface.setErrorMsg("全价不满足涨跌停限制。 跌停价：[" + lowerLimitPrice + "] -- 涨停价：[" + upperLimitPrice + "]; 委托全价价格：[" + fullPrice + "]");
                        return false;
                    }
                }
            }
        }
        // 交易上下限检查
        if (TradePlat.NORMAL.value.equals(tradePlat) || TradePlat.SH_FIXED_INCOME.value.equals(tradePlat)) {
            String tradePlatName;
            if (TradePlat.NORMAL.value.equals(tradePlat)) {
                tradePlatName = TradePlat.NORMAL.name;
            } else {
                tradePlatName = TradePlat.SH_FIXED_INCOME.name;
            }
            return compareMinOrMaxVolumeAndBalance(orderInterface, entrustBalance, BigInteger.valueOf(entrustVolume), minTrdUnitLimit, tradePlatName);
        }
        return true;
    }

    private boolean compareMinOrMaxVolumeAndBalance(OrderInterface orderInterface, BigDecimal entrustBalance, BigInteger bgEntrustVolume, MinTrdUnitLimit minTrdUnitLimit, String tradePlatName) {
        if (Objects.nonNull(minTrdUnitLimit) && EntrustBs.BUY.value.equals(orderInterface.getEntrustBs())) {
            Integer minNum;
            if (EntrustProp.isMarketPrice(orderInterface.getEntrustProp())) {
                minNum = minTrdUnitLimit.getMinNumMarket();
            } else {
                minNum = minTrdUnitLimit.getMinNum();
            }
            if (bgEntrustVolume.compareTo(BigInteger.valueOf(minNum)) < 0 && entrustBalance.compareTo(minTrdUnitLimit.getMinAmt()) < 0) {
                orderInterface.setErrorCode(ErrorCode.INNER_RISK_ERROR.value);
                orderInterface.setErrorMsg("不满足" + tradePlatName + "债券交易最小买入数量:" + minNum + ",或者金额" + minTrdUnitLimit.getMinAmt());
                return false;
            }
        }
        return true;
    }

    // 债券成交全价处理 ------> 需求确认后恢复为全价
    public BigDecimal handleTradePriceByFullPrice(Trade trade, String isFullPrice, Instruction instruction) {
        if (Objects.nonNull(instruction) && EntrustUtils.isFullPrice(instruction.getAssetType(), trade.getEntrustProp(), isFullPrice)) {
            logger.info("债券全价成交回报价格和成交金额处理，brokerEntrustId {} brokerTradeId {} tradePrice {} tradeBalance {}", trade.getBrokerEntrustId(), trade.getBrokerTradeId(), trade.getTradedPrice(), trade.getTradedBalance());
            BigDecimal price = investmentManagement.queryEntrustPriceByFullPriceFromInvest(trade.getTradedPrice(), trade.getTradedVolume(), instruction, getPriceRoundByPriceStep(instrumentService.queryByInnerCode(instruction.getFinprodMarketId())));
            BigDecimal calculateBalance = TradeUtils.multiply(price, trade.getTradedVolume(), 2);
            logger.info("全价转换为净价完成，brokerEntrustId {} brokerTradeId {} tradePrice {} tradeBalance {}", trade.getBrokerEntrustId(), trade.getBrokerTradeId(), price, calculateBalance);
            return calculateBalance;
        }
        return trade.getTradedBalance();
    }

    public BigDecimal calculateBalanceForInstruction(OrderInterface orderInterface, BigDecimal entrustBalance) {
        if (EntrustUtils.isFullPrice(orderInterface.getAssetType(), orderInterface.getEntrustProp(), orderInterface.getIsFullPrice())) {
            entrustBalance = TradeUtils.multiply(orderInterface.getEntrustPrice(), orderInterface.getEntrustVolume(), 2);
        }
        return entrustBalance;
    }

    // 返回债券精度位数
    public int getPriceRoundByPriceStep(Instrument instrument) {
        return Objects.nonNull(instrument) ? (Objects.isNull(instrument.getPriceStep()) ? 4 : instrument.getPriceStep().scale()) : 4;
    }
}
