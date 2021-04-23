package com.joyintech.tams.exchange.quoteterminal.test;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.joyintech.tams.exchange.common.bean.QuoteDataProto;
import com.joyintech.tams.exchange.common.bean.TickByte;
import com.joyintech.tams.exchange.common.cache.QuoteTickService;
import com.joyintech.tams.exchange.common.proto.QuoteProto;
import com.joyintech.tams.exchange.common.utils.TestConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * 该类为测试类，只为了给测试环境提供行情使用
 * 测试时打开component注解；关闭其他行情Client的注解即可
 */
@Component
public class TestQuote {
    private static final Logger logger = LoggerFactory.getLogger(TestQuote.class);
    private static String url = "http://182.150.112.115:8100/quote/v1/tick/snapshot?token=1&asset_type=";
    private static int[] assetType = {1, 2, 4, 5};
    private ThreadLocal<QuoteProto.TickBean.Builder> threadLocalBuilder = ThreadLocal.withInitial(QuoteProto.TickBean::newBuilder);

    @Autowired
    private QuoteTickService quoteTickService;

    @Scheduled(fixedRateString = "${scheduled.quote.stock.flush}")
    public void getSnapshot() {
        logger.info("getSnapshot begin...");
        Map<String, TickByte> tickBeans = new HashMap<>();
        for (int i : assetType) {
            QuoteProto.TickBean.Builder builder = threadLocalBuilder.get();
            byte[] snapshot = httpRequest(url + i);
            try {
                QuoteDataProto.QuoteResult quoteResult = QuoteDataProto.QuoteResult.parseFrom(snapshot);
                List<ByteString> byteStringList = quoteResult.getDataList();
                boolean bond = i == 4;
                byteStringList.forEach(bytes -> {
                    try {
                        QuoteDataProto.TickBean tickBean = QuoteDataProto.TickBean.parseFrom(bytes);
                        builder.clear();
                        QuoteProto.TickBean newTick = TestConverter.convertTick(tickBean, builder, bond);
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
        logger.info("getSnapshot end, flush tick {}", tickBeans.size());
    }

    public byte[] httpRequest(String requestUrl) {
        StringBuffer buffer = null;
        InputStream is = null;
        ByteArrayOutputStream os = null;
        byte[] buff = new byte[1024000];
        int len = 0;
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            conn.connect();
            is = conn.getInputStream();
            os = new ByteArrayOutputStream();
            while ((len = is.read(buff)) != -1) {
                os.write(buff, 0, len);
            }
            return os.toByteArray();
        } catch (Exception e) {
            logger.error("get snapshot error {}", e);
        } finally {
            try {
                if (Objects.nonNull(os))
                    os.close();
                if (Objects.nonNull(is))
                    is.close();
            } catch (IOException e) {
                logger.error("get snapshot error {}", e);
            }
        }
        return null;
    }

}
