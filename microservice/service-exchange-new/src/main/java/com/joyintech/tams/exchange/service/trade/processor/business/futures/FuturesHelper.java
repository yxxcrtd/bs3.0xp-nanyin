package com.joyintech.tams.exchange.service.trade.processor.business.futures;

import com.joyintech.tams.exchange.common.bean.MinPrice;
import com.joyintech.tams.exchange.common.bean.MinTrdUnitLimit;
import com.joyintech.tams.exchange.common.cache.QuoteTickService;
import com.joyintech.tams.exchange.common.constant.EntrustProp;
import com.joyintech.tams.exchange.common.constant.ErrorCode;
import com.joyintech.tams.exchange.common.constant.InstructionPs;
import com.joyintech.tams.exchange.common.db.domain.FuturesInstrument;
import com.joyintech.tams.exchange.common.db.domain.Instruction;
import com.joyintech.tams.exchange.common.db.domain.Instrument;
import com.joyintech.tams.exchange.common.db.domain.OrderInterface;
import com.joyintech.tams.exchange.common.proto.QuoteProto;
import com.joyintech.tams.exchange.common.utils.InvestmentUtils;
import com.joyintech.tams.exchange.common.utils.TradeUtils;
import com.joyintech.tams.exchange.datacommon.db.service.FuturesInstrumentService;
import com.joyintech.tams.exchange.datacommon.db.service.InstrumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;

@Component
public class FuturesHelper {
    private static final Logger logger = LoggerFactory.getLogger(FuturesHelper.class);

    @Autowired
    private QuoteTickService quoteTickService;
    @Autowired
    private FuturesInstrumentService futuresInstrumentService;
    @Autowired
    private InstrumentService instrumentService;

    public boolean checkCommonParam(BigDecimal entrustPrice, long entrustVolume, OrderInterface orderInterface, Instruction instruction) {
        //目前只考虑限价
        FuturesInstrument instrument = futuresInstrumentService.queryByInnerCode(orderInterface.getStockCode());
        if (Objects.nonNull(instrument)) {
            if (EntrustProp.NORMAL.value.equals(orderInterface.getEntrustProp())) {
                //限价报单量限制
                if (instrument.getMinLimitOrderVolume() > 0 && entrustVolume < instrument.getMinLimitOrderVolume()) {
                    orderInterface.setErrorCode(ErrorCode.INNER_RISK_ERROR.value);
                    orderInterface.setErrorMsg("限价报单小于最小报单量:" + instrument.getMinLimitOrderVolume());
                    return false;
                }
                //拆单功能代替
                /*if (instrument.getMaxLimitOrderVolume().longValue() > 0 && entrustVolume > instrument.getMaxLimitOrderVolume().longValue()) {
                    orderInterface.setErrorCode(ErrorCode.INNER_RISK_ERROR.value);
                    orderInterface.setErrorMsg("限价报单大于最大报单量:" + instrument.getMaxLimitOrderVolume());
                    return false;
                }*/
                //限价涨跌停范围限制
                QuoteProto.TickBean tick = quoteTickService.getTickFromCache(orderInterface.getStockCode());
                if (Objects.nonNull(tick)) {
                    double lowerLimitPrice = TradeUtils.formatDouble(tick.getLowerLimitPrice(), 4);
                    double upperLimitPrice = TradeUtils.formatDouble(tick.getUpperLimitPrice(), 4);
                    if ((Double.compare(lowerLimitPrice, 0.0) > 0 && Double.compare(lowerLimitPrice, entrustPrice.doubleValue()) > 0)
                            || (Double.compare(upperLimitPrice, 0.0) > 0 && Double.compare(upperLimitPrice, entrustPrice.doubleValue()) < 0)) {
                        orderInterface.setErrorCode(ErrorCode.INNER_RISK_ERROR.value);
                        orderInterface.setErrorMsg("不满足涨跌停限制。 跌停价：[" + lowerLimitPrice + "] -- 涨停价：[" + upperLimitPrice + "]; 委托价格：[" + entrustPrice + "]");
                        return false;
                    }
                }
            }
            if (Double.compare(instrument.getPriceTick().doubleValue(), 0.0) > 0
                    && TradeUtils.divideAndRemainder(entrustPrice, instrument.getPriceTick()).compareTo(BigDecimal.ZERO) != 0) {
                orderInterface.setErrorCode(ErrorCode.INNER_RISK_ERROR.value());
                orderInterface.setErrorMsg("委托价格不满足最小价差：" + instrument.getPriceTick().doubleValue());
                return false;
            }
        }
        return true;
    }


    public void setFuturesInstrumentData(Instrument instrument, FuturesInstrument futuresInstrument) {
        futuresInstrument.setInnerCode(instrument.getInnerCode());
        futuresInstrument.setInstrumentName(instrument.getStockName());
        futuresInstrument.setInstrumentId(instrument.getStockCode());
        futuresInstrument.setExchangeInstId(instrument.getStockCode());
        String futuresMarket = InvestmentUtils.convertInvestMarketToTrade(instrument.getMarket());
        futuresInstrument.setExchangeId(futuresMarket);
        futuresInstrument.setVolumeMultiple(instrument.getContractMultiplier());
        futuresInstrument.setProductClass(instrument.getAssetType1());
        MinPrice minPrice = instrumentService.getMinPrice(null, futuresMarket, instrument);
        if (Objects.nonNull(minPrice)) {
            futuresInstrument.setPriceTick(minPrice.getMinPrice());
        }
        MinTrdUnitLimit minTrdUnitLimit = instrumentService.getMinTrdUnitLimit(null, futuresMarket, instrument, InstructionPs.BUYOPEN.value);
        if (Objects.nonNull(minTrdUnitLimit)) {
            futuresInstrument.setBuyUnit(minTrdUnitLimit.getMinStep());
            futuresInstrument.setMaxLimitOrderVolume(minTrdUnitLimit.getMaxNum());
            futuresInstrument.setMinLimitOrderVolume(minTrdUnitLimit.getMinNum());
            futuresInstrument.setMinMarketOrderVolume(minTrdUnitLimit.getMinNumMarket());
            futuresInstrument.setMaxMarketOrderVolume(minTrdUnitLimit.getMaxNumMarket());
        }
    }
}
