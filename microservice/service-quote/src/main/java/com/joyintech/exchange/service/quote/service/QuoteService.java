package com.joyintech.exchange.service.quote.service;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.joyintech.tams.exchange.common.bean.QuoteDataProto;
import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.bean.TickBean;
import com.joyintech.tams.exchange.common.bean.TickByte;
import com.joyintech.tams.exchange.common.cache.QuoteTickService;
import com.joyintech.tams.exchange.common.constant.ErrorCode;
import com.joyintech.tams.exchange.common.db.domain.FuturesInstrument;
import com.joyintech.tams.exchange.common.proto.QuoteProto;
import com.joyintech.tams.exchange.common.utils.TestConverter;
import com.joyintech.tams.exchange.common.utils.TickConverter;
import com.joyintech.tams.exchange.datacommon.db.service.FuturesInstrumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class QuoteService {
    private static final Logger logger = LoggerFactory.getLogger(QuoteService.class);
    private ThreadLocal<QuoteProto.TickBean.Builder> threadLocalBuilder = ThreadLocal.withInitial(QuoteProto.TickBean::newBuilder);

    @Autowired
    private QuoteTickService quoteTickService;
    @Autowired
    private FuturesInstrumentService futuresInstrumentService;

    public ResultBean getTick(String stockCode) {
        if ("*".equals(stockCode)) {
            return new ResultBean(ErrorCode.SUCCESS, quoteTickService.getAll().values()
                    .stream().map(TickConverter::convertTick).collect(Collectors.toList()));
        }
        FuturesInstrument futuresInstrument = futuresInstrumentService.queryByStockCode(stockCode);
        if (Objects.nonNull(futuresInstrument)) {
            stockCode = futuresInstrument.getInnerCode();
        }
        return new ResultBean(ErrorCode.SUCCESS, TickConverter.convertTick(quoteTickService.findFromRedisById(stockCode)));
    }

    public ResultBean addTicks(List<TickBean> tickBeanList, byte[] getSnapshot) {
        Map<String, TickByte> tickBeans = new HashMap<>();
        QuoteProto.TickBean.Builder builder = threadLocalBuilder.get();
        if (Objects.nonNull(tickBeanList)) {
            try {
                tickBeanList.forEach(tickBean -> {
                    TickConverter.convertToQuoteProtoTickBean(tickBean, builder);
                    QuoteProto.TickBean tickBean1 = builder.build();
                    tickBeans.put(builder.getInstrumentID(), new TickByte(tickBean1.toByteArray()));
                });
            } catch (Exception e) {
                logger.error("getSnapshot error:{}", e);
                return new ResultBean(ErrorCode.SUCCESS, "获取数据失败");
            }
        } else {
            try {
                logger.info("start write quote data ...");
                QuoteDataProto.QuoteResult quoteResult = QuoteDataProto.QuoteResult.parseFrom(getSnapshot);
                List<ByteString> byteStringList = quoteResult.getDataList();
                byteStringList.forEach(bytes -> {
                    try {
                        QuoteDataProto.TickBean tickBean = QuoteDataProto.TickBean.parseFrom(bytes);
                        builder.clear();
                        QuoteProto.TickBean newTick = TestConverter.convertTick(tickBean, builder);
                        tickBeans.put(newTick.getInstrumentID(), new TickByte(newTick.toByteArray()));
                    } catch (InvalidProtocolBufferException e) {
                        logger.error("tickBean parseFrom:{}", e);
                    }
                });
            } catch (Exception e) {
                logger.error("getSnapshot error:{}", e);
            }
        }
        quoteTickService.saveTicks(tickBeans);
        logger.info("finish write quote data");
        return new ResultBean(ErrorCode.SUCCESS, "存入数据成功");
    }
}
