package com.joyintech.tams.exchange.quoteterminal.wd;

import cn.com.wind.td.tdf.*;
import com.joyintech.tams.exchange.common.proto.QuoteProto;
import com.joyintech.tams.exchange.common.utils.StockCodeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * create By MAXX on 2020/11/3
 */
public class WDConverter {
    private static final Logger logger = LoggerFactory.getLogger(WDConverter.class);

    public static void printConnectResult(TDF_CONNECT_RESULT data) {
        logger.info("TDF_CONNECT_RESULT");
        StringBuilder sb = new StringBuilder();
        sb.append("            szIp = " + data.getIp() + "\n")
          .append("          szPort = " + data.getPort() + "\n")
          .append("          szUser = " + data.getUser() + "\n")
          .append("           szPwd = " + data.getPwd() + "\n")
          .append("   nConnectionID = " + data.getConnectionID() + "\n")
          .append("     nConnResult = " + data.getConnResult() + "\n");
        logger.info(sb.toString());
    }

    public static void printLoginResult(TDF_LOGIN_RESULT data) {
        logger.info("登陆结果：");
        StringBuilder sb = new StringBuilder();
        sb.append("    nLoginResult = " + data.getLoginResult() + "\n")
          .append("          szInfo = " + data.getInfo() + "\n")
          .append("        nMarkets = " + data.getMarkets() + "\n")
          .append("        szMarket = " + arrayToStr(data.getMarket(), data.getMarkets()) + "\n")
          .append("        nDynDate = " + arrayToStr(data.getDynDate(), data.getMarkets()) + "\n");
        logger.info(sb.toString());
    }

    public static void printMarketClose(TDF_MARKET_CLOSE data) {
        logger.info("TDF_MARKET_CLOSE");
        StringBuilder sb = new StringBuilder();
        sb.append("        szMarket = " + data.getMarket() + "\n")
          .append("           nTime = " + data.getTime() + "\n")
          .append("          chInfo = " + data.getInfo() + "\n");
        logger.info(sb.toString());
    }

    public static void printDateChange(TDF_QUOTATIONDATE_CHANGE data) {
        logger.info("TDF_QUOTATIONDATE_CHANGE");
        StringBuilder sb = new StringBuilder();
        sb.append("        szMarket = " + data.getMarket() + "\n")
          .append("        nOldDate = " + data.getOldDate() + "\n")
          .append("        nNewDate = " + data.getNewDate() + "\n");
        logger.info(sb.toString());
    }

    public static void printDataMarket(TDF_MARKET_DATA data) {
        logger.info("TDF_MARKET_DATA");
        StringBuilder sb = new StringBuilder();
        sb.append("          SzWindCode = " + data.getWindCode() + "\n")
          .append("          nActionDay = " + data.getActionDay() + "\n")
          .append("         nTradingDay = " + data.getTradingDay() + "\n")
          .append("               nTime = " + data.getTime() + "\n")
          .append("             nStatus = " + data.getStatus() + "\n")
          .append("           nPreClose = " + data.getPreClose() + "\n")
          .append("               nOpen = " + data.getOpen() + "\n")
          .append("               nHigh = " + data.getHigh() + "\n")
          .append("                nLow = " + data.getLow() + "\n")
          .append("              nMatch = " + data.getMatch() + "\n")
          .append("           nAskPrice = " + arrayToStr(data.getAskPrice()) + "\n")
          .append("            nAskVol] = " + arrayToStr(data.getAskVol()) + "\n")
          .append("           nBidPrice = " + arrayToStr(data.getBidPrice()) + "\n")
          .append("            nBidVol] = " + arrayToStr(data.getBidVol()) + "\n")
          .append("          nNumTrades = " + data.getNumTrades() + "\n")
          .append("             iVolume = " + data.getVolume() + "\n")
          .append("           iTurnover = " + data.getTurnover() + "\n")
          .append("        nTotalBidVol = " + data.getTotalBidVol() + "\n")
          .append("        nTotalAskVol = " + data.getTotalAskVol() + "\n")
          .append("nWeightedAvgBidPrice = " + data.getWeightedAvgBidPrice() + "\n")
          .append("nWeightedAvgAskPrice = " + data.getWeightedAvgAskPrice() + "\n")
          .append("               nIOPV = " + data.getIOPV() + "\n")
          .append("    nYieldToMaturity = " + data.getYieldToMaturity() + "\n")
          .append("        nHighLimited = " + data.getHighLimited() + "\n")
          .append("         nLowLimited = " + data.getLowLimited() + "\n")
          .append("            chPrefix = " + data.getPrefix() + "\n")
          .append("               nSyl1 = " + data.getSyl1() + "\n")
          .append("               nSyl2 = " + data.getSyl2() + "\n")
          .append("                nSD2 = " + data.getSD2() + "\n");
        logger.info(sb.toString());
    }

    static void printFutureData(TDF_FUTURE_DATA data) {
        logger.info("TDF_FUTURE_DATA");
        StringBuilder sb = new StringBuilder();
        sb.append("      szWindCode = " + data.getWindCode() + "\n")
          .append("          szCode = " + data.getCode() + "\n")
          .append("      nActionDay = " + data.getActionDay() + "\n")
          .append("     nTradingDay = " + data.getTradingDay() + "\n")
          .append("           nTime = " + data.getTime() + "\n")
          .append("         nStatus = " + data.getStatus() + "\n")
          .append("iPreOpenInterest = " + data.getPreOpenInterest() + "\n")
          .append("       nPreClose = " + data.getPreClose() + "\n")
          .append(" nPreSettlePrice = " + data.getPreSettlePrice() + "\n")
          .append("           nOpen = " + data.getOpen() + "\n")
          .append("           nHigh = " + data.getHigh() + "\n")
          .append("            nLow = " + data.getLow() + "\n")
          .append("          nMatch = " + data.getMatch() + "\n")
          .append("         iVolume = " + data.getVolume() + "\n")
          .append("       iTurnover = " + data.getTurnover() + "\n")
          .append("   iOpenInterest = " + data.getOpenInterest() + "\n")
          .append("          nClose = " + data.getClose() + "\n")
          .append("    nSettlePrice = " + data.getSettlePrice() + "\n")
          .append("    nHighLimited = " + data.getHighLimited() + "\n")
          .append("     nLowLimited = " + data.getLowLimited() + "\n")
          .append("       nPreDelta = " + data.getPreDelta() + "\n")
          .append("      nCurrDelta = " + data.getCurrDelta() + "\n")
          .append("       nAskPrice = " + arrayToStr(data.getAskPrice()) + "\n")
          .append("         nAskVol = " + arrayToStr(data.getAskVol()) + "\n")
          .append("       nBidPrice = " + arrayToStr(data.getBidPrice()) + "\n")
          .append("         nBidVol = " + arrayToStr(data.getBidVol()) + "\n");
        logger.info(sb.toString());
    }

    static void printIndexData(TDF_INDEX_DATA data) {
        logger.info("TDF_INDEX_DATA");
        StringBuilder sb = new StringBuilder();
        sb.append("          SzWindCode = " + data.getWindCode() + "\n")
          .append("              szCode = " + data.getCode() + "\n")
          .append("          nActionDay = " + data.getActionDay() + "\n")
          .append("         nTradingDay = " + data.getTradingDay() + "\n")
          .append("               nTime = " + data.getTime() + "\n")
          .append("             nStatus = " + data.getStatus() + "\n")
          .append("          nOpenIndex = " + data.getOpenIndex() + "\n")
          .append("          nHighIndex = " + data.getHighIndex() + "\n")
          .append("           nLowIndex = " + data.getLowIndex() + "\n")
          .append("          nLastIndex = " + data.getLastIndex() + "\n")
          .append("        iTotalVolume = " + data.getTotalVolume() + "\n")
          .append("      nPreCloseIndex = " + data.getPreCloseIndex() + "\n");
        logger.info(sb.toString());
    }

    public static void convertToTick(TDF_FUTURE_DATA data, QuoteProto.TickBean.Builder builder) {
        printFutureData(data);
        builder.clear();
        builder.setExchange(StockCodeUtils.getMarket(data.getWindCode()));
        builder.setInstrumentID(data.getWindCode());
        builder.setLastPrice(data.getMatch());
        builder.setOpenPrice(data.getOpen());
        builder.setHighPrice(data.getHigh());
        builder.setLowPrice(data.getLow());
        builder.setVolume(data.getVolume());
        builder.setTurnover(0); //nsq 没有换手率
        builder.setTradeBalance(data.getTurnover());
        builder.setUpperLimitPrice(data.getHighLimited());
        builder.setLowerLimitPrice(data.getLowLimited());
        builder.setSettlePrice(data.getSettlePrice());
        builder.setPreClosePrice(data.getPreClose());
        for (int i = 0; i < data.getAskPrice().length; i++) {
            builder.addAskPrice(data.getAskPrice()[i]);
        }
        for (int i = 0; i < data.getAskVol().length; i++) {
            builder.addAskVolume((int) data.getAskVol()[i]);
        }
        for (int i = 0; i < data.getBidPrice().length; i++) {
            builder.addBidPrice(data.getBidPrice()[i]);
        }
        for (int i = 0; i < data.getBidVol().length; i++) {
            builder.addBidVolume((int) data.getBidVol()[i]);
        }
        builder.setUpdateTime(data.getTime());
    }

    public static void convertToTick(TDF_MARKET_DATA data, QuoteProto.TickBean.Builder builder) {
        printDataMarket(data);
        builder.clear();
        builder.setExchange(StockCodeUtils.getMarket(data.getWindCode()));
        builder.setInstrumentID(data.getWindCode());
        builder.setLastPrice(data.getMatch());
        builder.setOpenPrice(data.getOpen());
        builder.setHighPrice(data.getHigh());
        builder.setLowPrice(data.getLow());
        builder.setVolume(data.getVolume());
        builder.setTurnover(0); //nsq 没有换手率
        builder.setTradeBalance(data.getTurnover());
        builder.setUpperLimitPrice(data.getHighLimited());
        builder.setLowerLimitPrice(data.getLowLimited());
        builder.setPreClosePrice(data.getPreClose());
        for (int i = 0; i < data.getAskPrice().length; i++) {
            builder.addAskPrice(data.getAskPrice()[i]);
        }
        for (int i = 0; i < data.getAskVol().length; i++) {
            builder.addAskVolume((int) data.getAskVol()[i]);
        }
        for (int i = 0; i < data.getBidPrice().length; i++) {
            builder.addBidPrice(data.getBidPrice()[i]);
        }
        for (int i = 0; i < data.getBidVol().length; i++) {
            builder.addBidVolume((int) data.getBidVol()[i]);
        }
        builder.setUpdateTime(data.getTime());
    }

    public static void convertToTick(TDF_INDEX_DATA data, QuoteProto.TickBean.Builder builder) {
        printIndexData(data);
        builder.clear();
        builder.setExchange(StockCodeUtils.getMarket(data.getWindCode()));
        builder.setInstrumentID(data.getWindCode());
        builder.setLastPrice(data.getLastIndex());
        builder.setOpenPrice(data.getOpenIndex());
        builder.setHighPrice(data.getHighIndex());
        builder.setLowPrice(data.getLowIndex());
        builder.setTurnover(0); //nsq 没有换手率
        builder.setTradeBalance(data.getTurnover());
        builder.setUpdateTime(data.getTime());
    }

    public static String arrayToStr(int[] array, int count) {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (int i = 0; i < count; i++) {
            int v = array[i];
            sb.append(v).append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    public static String arrayToStr(String[] array, int count) {
        if (array == null)
            return "[]";
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (int i = 0; i < count; i++) {
            String v = array[i];
            if (v != null && v.length() > 0)
                sb.append(v).append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    public static String arrayToStr(long[] array) {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (long v : array) {
            sb.append(v).append(",");
        }
        sb.append("]");
        return sb.toString();
    }
}
