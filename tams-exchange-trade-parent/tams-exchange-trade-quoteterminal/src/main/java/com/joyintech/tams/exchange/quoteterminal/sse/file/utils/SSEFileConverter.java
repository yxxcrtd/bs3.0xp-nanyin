package com.joyintech.tams.exchange.quoteterminal.sse.file.utils;

import com.joyintech.tams.exchange.common.constant.Market;
import com.joyintech.tams.exchange.common.proto.QuoteProto;
import com.joyintech.tams.exchange.common.utils.StockCodeUtils;
import com.joyintech.tams.exchange.quoteterminal.sse.file.bean.SSEFileFundQuoteBean;
import com.joyintech.tams.exchange.quoteterminal.sse.file.bean.SSEFileQuote;
import com.joyintech.tams.exchange.quoteterminal.sse.file.bean.SSEFileStockQuoteBean;
import com.joyintech.tams.exchange.quoteterminal.sse.file.cache.LimitPrice;
import com.joyintech.tams.exchange.quoteterminal.sse.file.enums.MDStreamID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @Author: think
 * @Description:
 * @Date: Created in 13:24 2021/8/10
 */
public class SSEFileConverter {
    private static final Logger logger = LoggerFactory.getLogger(SSEFileConverter.class);

    public static void convertToTick(String[] records, QuoteProto.TickBean.Builder builder) {
        SSEFileQuote sseFileQuoteBean;
        if (records[0].equals(MDStreamID.FUND.value)) {
            sseFileQuoteBean = setParam(SSEFileFundQuoteBean.class, records);
        } else {
            sseFileQuoteBean = setParam(SSEFileStockQuoteBean.class, records);
        }
        if (Objects.nonNull(sseFileQuoteBean)) {
            builder.clear();
            builder.setExchange(Market.SHSE.value);
            String stockCode = StockCodeUtils.formatStockCode(sseFileQuoteBean.getSecurityID(), Market.SHSE.value);
            builder.setInstrumentID(stockCode);
            builder.setLastPrice(sseFileQuoteBean.getTradePrice());
            builder.setOpenPrice(sseFileQuoteBean.getOpenPrice());
            builder.setHighPrice(sseFileQuoteBean.getHighPrice());
            builder.setLowPrice(sseFileQuoteBean.getLowPrice());
            builder.setVolume(sseFileQuoteBean.getTradeVolume());
            builder.setTurnover(0); //nsq 没有换手率
            builder.setTradeBalance(sseFileQuoteBean.getTotalValueTraded());
            builder.setUpperLimitPrice(LimitPrice.getUpperLimitPrice(stockCode));
            builder.setLowerLimitPrice(LimitPrice.getLowerLimitPrice(stockCode));
            builder.setSettlePrice(0);
            builder.setPreClosePrice(sseFileQuoteBean.getPreClosePx());
            builder.addBidPrice(sseFileQuoteBean.getBuyPrice1());
            builder.addBidPrice(sseFileQuoteBean.getBuyPrice2());
            builder.addBidPrice(sseFileQuoteBean.getBuyPrice3());
            builder.addBidPrice(sseFileQuoteBean.getBuyPrice4());
            builder.addBidPrice(sseFileQuoteBean.getBuyPrice5());
            builder.addBidVolume(sseFileQuoteBean.getBuyVolume1());
            builder.addBidVolume(sseFileQuoteBean.getBuyVolume2());
            builder.addBidVolume(sseFileQuoteBean.getBuyVolume3());
            builder.addBidVolume(sseFileQuoteBean.getBuyVolume4());
            builder.addBidVolume(sseFileQuoteBean.getBuyVolume5());
            builder.addAskPrice(sseFileQuoteBean.getSellPrice1());
            builder.addAskPrice(sseFileQuoteBean.getSellPrice2());
            builder.addAskPrice(sseFileQuoteBean.getSellPrice3());
            builder.addAskPrice(sseFileQuoteBean.getSellPrice4());
            builder.addAskPrice(sseFileQuoteBean.getSellPrice5());
            builder.addAskVolume(sseFileQuoteBean.getSellVolume1());
            builder.addAskVolume(sseFileQuoteBean.getSellVolume2());
            builder.addAskVolume(sseFileQuoteBean.getSellVolume3());
            builder.addAskVolume(sseFileQuoteBean.getSellVolume4());
            builder.addAskVolume(sseFileQuoteBean.getSellVolume5());
            builder.setUpdateTime(calTimeStamp(sseFileQuoteBean.getTimestamp()));
        }
    }

    public static long calTimeStamp(LocalTime localTime) {
        // 当前年月日时间 + 行情时、分、秒、毫秒时间
        long dayTime = LocalDate.now().atStartOfDay().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        return dayTime + (localTime.getHour() * 3600 + localTime.getMinute() * 60 + localTime.getSecond()) * 1000 + localTime.getNano() / 1000000;
    }

    public static <T> T setParam(Class<T> clazz, String[] args) {
        try {
            if (clazz == null || args == null) {
                return null;
            }
            T t = clazz.newInstance();
            Class<? super T> superclass = clazz.getSuperclass();
            List<Field> fieldList = new ArrayList<>(Arrays.asList(superclass.getDeclaredFields()));
            fieldList.addAll(Arrays.asList(clazz.getDeclaredFields()));
            if (fieldList.size() > args.length) {
                logger.error("类字段与文件字段个数不对应，请确认该记录正确！！ fields length {} -- record {} -- length {}", fieldList.size(), Arrays.toString(args), args.length);
                return null;
            }
            for (int i = 0; i < fieldList.size(); i++) {
                Field field = fieldList.get(i);
                field.setAccessible(true);
                String typeName = field.getGenericType().getTypeName();
                args[i] = args[i].trim();
                Object arg = args[i];
                switch (typeName) {
                    case "java.lang.Integer":
                        arg = Integer.parseInt(args[i]);
                        break;
                    case "java.lang.Double":
                        arg = Double.parseDouble(args[i]);
                        break;
                    case "java.lang.Long":
                        arg = Long.parseLong(args[i]);
                        break;
                    case "java.time.LocalTime":
                        arg = LocalTime.parse(args[i]);
                        break;
                }
                field.set(t, arg);
            }
            return t;
        } catch (Exception e) {
            logger.error("setParam failed args {} exception {}", Arrays.toString(args), e);
            return null;
        }
    }
}
