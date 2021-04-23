package com.joyintech.tams.exchange.terminal.trade.mock;

import com.google.common.base.Strings;
import com.joyintech.tams.exchange.common.bean.IntegratedQuote;
import com.joyintech.tams.exchange.common.constant.*;
import com.joyintech.tams.exchange.common.db.domain.IntegratedEntrust;
import com.joyintech.tams.exchange.common.utils.StockCodeUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MockQuoteData {
    private static int[] contractIds = {198, 674};

    private static String[] liaisonName = {"张凤", "汇明发"};

    private static String[] liaisonTel = {"13115741349", "17717641260"};

    private static String[] propSeatNo = {"062700", "432115"};

    private static AtomicInteger orderNo = new AtomicInteger(1);

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

    public static List<IntegratedQuote> makeMockDataForBulk(String stockCode) {
        List<IntegratedQuote> integratedQuotes = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            IntegratedQuote integratedQuote = new IntegratedQuote();
            integratedQuote.setQuoteType(QuoteType.BULK_QUOTE.value);
            integratedQuote.setStockCode(stockCode);
            integratedQuote.setStockName(stockCode);
            integratedQuote.setReportTime(simpleDateFormat.format(new Date(System.currentTimeMillis())));
            integratedQuote.setOrderNo(orderNo.incrementAndGet() + "");
            if (StockCodeUtils.getMarket(stockCode).equals(Market.SHSE.value)) {
                integratedQuote.setPropSeatNo(propSeatNo[0]);
                integratedQuote.setMarket(Market.SHSE.value);
            } else {
                integratedQuote.setPropSeatNo(propSeatNo[1]);
                integratedQuote.setMarket(Market.SZSE.value);
            }
            integratedQuote.setEntrustVolume(300000 * i);
            integratedQuote.setEntrustPrice(10.1 * i);
            if (i == 1 || i == 2) {
                integratedQuote.setEntrustProp(EntrustProp.INTENTION.value);
                integratedQuote.setEntrustBs(EntrustBs.BUY.value);
                integratedQuote.setContractId((contractIds[i - 1] + i * 2) + "");
                integratedQuote.setLiaisonName(liaisonName[i - 1]);
                integratedQuote.setLiaisonTel(liaisonTel[i - 1]);
            } else {
                integratedQuote.setContractId((contractIds[i - 3] + i * 2) + "");
                integratedQuote.setLiaisonName(liaisonName[i - 3]);
                integratedQuote.setLiaisonTel(liaisonTel[i - 3]);
                if (StockCodeUtils.getMarket(stockCode).equals(Market.SHSE.value)) {
                    integratedQuote.setEntrustProp(EntrustProp.INTENTION.value);
                } else {
                    integratedQuote.setEntrustProp(EntrustProp.SET_PRICE.value);
                }
                integratedQuote.setEntrustBs(EntrustBs.SELL.value);
            }
            integratedQuotes.add(integratedQuote);
        }
        return integratedQuotes;
    }

    public static List<IntegratedQuote> makeAfterBulkQuote(String stockCode) {
        List<IntegratedQuote> integratedQuotes = new ArrayList<>();
        IntegratedQuote integratedQuote = new IntegratedQuote();
        integratedQuote.setStockCode(stockCode);
        integratedQuote.setStockName(stockCode);
        integratedQuote.setQuoteType(QuoteType.AFTER_BULK_QUOTE.value);
        integratedQuote.setEntrustPrice(12.1);
        if (StockCodeUtils.getMarket(stockCode).equals(Market.SHSE.value)) {
            integratedQuote.setMarket(Market.SHSE.value);
        } else {
            integratedQuote.setFullPrice(11.8);
            integratedQuote.setMarket(Market.SZSE.value);
        }
        integratedQuote.setEntrustVolume(310000);
        integratedQuote.setTradeBalance(integratedQuote.getEntrustVolume() * integratedQuote.getEntrustPrice());
        integratedQuote.setBuyRemainQuantity1(1000000);
        integratedQuote.setSellRemainQuantity1(2000000);
        integratedQuotes.add(integratedQuote);
        return integratedQuotes;
    }

    public static List<IntegratedQuote> makeFixedIncomeQuote() {
        List<IntegratedQuote> integratedQuotes = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            IntegratedQuote integratedQuote = new IntegratedQuote();
            integratedQuote.setMarket(Market.SHSE.value);
            integratedQuote.setQuoteType(QuoteType.FIXED_INCOME_QUOTE.value);
            integratedQuote.setEntrustPrice(100.00);
            integratedQuote.setContractId((contractIds[i - 1] + i) + "");
            if (i == 1) {
                integratedQuote.setStockCode("010107.SH");
                integratedQuote.setStockName("21国债⑺");
                integratedQuote.setFullPrice(101.00);
                integratedQuote.setExpireIncomeRate(0.012);
                integratedQuote.setEntrustBs(EntrustBs.BUY.value);
            } else {
                integratedQuote.setStockCode("101102.SH");
                integratedQuote.setStockName("国债1102");
                integratedQuote.setFullPrice(102.00);
                integratedQuote.setExpireIncomeRate(0.021);
                integratedQuote.setEntrustBs(EntrustBs.SELL.value);
            }
            integratedQuotes.add(integratedQuote);
        }
        return integratedQuotes;
    }

    public static IntegratedQuote makeIntegratedEntrustQuote(IntegratedEntrust integratedEntrust) {
        IntegratedQuote integratedQuote = new IntegratedQuote();
        integratedQuote.setOrderNo(orderNo.incrementAndGet() + "");
        integratedQuote.setStockCode(integratedEntrust.getStockCode());
        integratedQuote.setStockName(integratedEntrust.getStockName());
        integratedQuote.setEntrustProp(integratedEntrust.getEntrustProp());
        integratedQuote.setEntrustBs(integratedEntrust.getEntrustBs());
        integratedQuote.setEntrustVolume((int) integratedEntrust.getEntrustVolume());
        if (FullPriceConst.FULL_PRICE.value.equals(integratedEntrust.getIsFullPrice())) {
            integratedQuote.setEntrustPrice(integratedEntrust.getFullPrice().doubleValue());
        } else {
            integratedQuote.setEntrustPrice(integratedEntrust.getEntrustPrice().doubleValue());
        }
        integratedQuote.setReportTime(simpleDateFormat.format(new Date(System.currentTimeMillis())));
        if (!Strings.isNullOrEmpty(integratedEntrust.getPropSeatNo()))
            integratedQuote.setPropSeatNo(integratedEntrust.getPropSeatNo());
        if (!Strings.isNullOrEmpty(integratedEntrust.getContractId()))
            integratedQuote.setContractId(integratedEntrust.getContractId());
        if (!Strings.isNullOrEmpty(integratedEntrust.getLiaisonName()))
            integratedQuote.setLiaisonName(integratedEntrust.getLiaisonName());
        if (!Strings.isNullOrEmpty(integratedEntrust.getLiaisonTel()))
            integratedQuote.setLiaisonTel(integratedEntrust.getLiaisonTel());
        if (integratedEntrust.getEntrustProp().equals(EntrustProp.SET_PRICE.value)) {
            integratedQuote.setPropSeatNo("062700");
            integratedQuote.setContractId(123 + orderNo.incrementAndGet() + "");
        }
        return integratedQuote;
    }
}
