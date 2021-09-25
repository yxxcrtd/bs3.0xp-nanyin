package com.joyintech.tams.exchange.quoteterminal.sse.binary.resolver;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.enums.SSEBinaryLimitPriceFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * create By MAXX on 2021/1/22
 */
public class UpperLowerLimitPrice {
    private static final Logger logger = LoggerFactory.getLogger(UpperLowerLimitPrice.class);
    private static Map<String, String> limitMap = Maps.newConcurrentMap();
    private static final String filePostfix = ".txt";
    private static final Splitter splitter = Splitter.on("|");
    private static final int upperIndex = 22;
    private static final int lowerIndex = 23;

    public static double getUpperPrice(String stockCode) {
        return Double.valueOf(Lists.newArrayList(splitter.split(limitMap.get(stockCode))).get(0));
    }

    public static double getLowerPrice(String stockCode) {
        return Double.valueOf(Lists.newArrayList(splitter.split(limitMap.get(stockCode))).get(1));
    }

    public static void upperLowerLimitPrice(String path) {
        SimpleDateFormat tradingDayFormat = new SimpleDateFormat("MMdd");
        FileInputStream fis = null;
        int n = 0;
        while (Objects.isNull(fis) && n < SSEBinaryLimitPriceFile.getFileLen()) {
            String fileName = "";
            try {
                fileName = SSEBinaryLimitPriceFile.getFileName(n++) + tradingDayFormat.format(new Date()) + filePostfix;
                fis = new FileInputStream(path + fileName);
            } catch (FileNotFoundException e) {
                logger.error("upperLowerLimitPrice get file [{}] failed: ", fileName, e.getMessage());
            }
        }
        if (Objects.isNull(fis)) {
            logger.error("upperLowerLimitPrice getfileName error, no file exist");
            return;
        }
        try {
            InputStreamReader isr = new InputStreamReader(fis, "gbk");
            BufferedReader br = new BufferedReader(isr);
            String line = "";
            List<String> messageList = null;
            while ((line = br.readLine()) != null) {
                messageList = Lists.newArrayList(splitter.split(line));
                limitMap.put(messageList.get(0).trim(), messageList.get(upperIndex).trim() + "|" + messageList.get(lowerIndex).trim());
            }
            br.close();
            isr.close();
            fis.close();
        } catch (UnsupportedEncodingException e) {
            logger.error("upperLowerLimitPrice read file error: ", e);
        } catch (IOException e) {
            logger.error("upperLowerLimitPrice read file error: ", e);
        }
    }
}
