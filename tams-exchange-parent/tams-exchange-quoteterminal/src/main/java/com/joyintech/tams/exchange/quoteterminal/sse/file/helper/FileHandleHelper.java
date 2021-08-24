package com.joyintech.tams.exchange.quoteterminal.sse.file.helper;

import com.joyintech.tams.exchange.common.constant.Market;
import com.joyintech.tams.exchange.common.proto.QuoteProto;
import com.joyintech.tams.exchange.common.utils.StockCodeUtils;
import com.joyintech.tams.exchange.quoteterminal.service.QuoteService;
import com.joyintech.tams.exchange.quoteterminal.sse.file.cache.LimitPrice;
import com.joyintech.tams.exchange.quoteterminal.sse.file.config.SSEFileConfig;
import com.joyintech.tams.exchange.quoteterminal.sse.file.enums.MDStreamID;
import com.joyintech.tams.exchange.quoteterminal.sse.file.utils.SSEFileConverter;
import com.joyintech.tams.exchange.quoteterminal.sse.file.utils.TxtParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

/**
 * @Author: think
 * @Description:
 * @Date: Created in 11:03 2021/8/10
 */
@Component
public class FileHandleHelper {
    private static final Logger logger = LoggerFactory.getLogger(FileHandleHelper.class);

    @Autowired
    private SSEFileConfig sseFileConfig;
    @Autowired
    private QuoteService quoteService;

    private final ThreadLocal<QuoteProto.TickBean.Builder> threadLocalBuilder = ThreadLocal.withInitial(QuoteProto.TickBean::newBuilder);

    private SimpleDateFormat dateFormat = new SimpleDateFormat("MMdd");

    public void cacheLimitPriceByCpxxFile() {
        try {
            String cpxxName = sseFileConfig.getCpxx() + dateFormat.format(new Date()) + ".txt";
            String cpxxPath = sseFileConfig.getQuoteDir() + "\\" + cpxxName;
            logger.info("开始解析[{}]文件获取涨跌停价格", cpxxPath);
            TxtParser txtParser = new TxtParser(cpxxPath);
            txtParser.getContextList().forEach(record -> {
                String[] contents = record.split("\\|");
                // 字段相关索引位置按交易所文件固定查询获取
                String stockCode = StockCodeUtils.formatStockCode(contents[0].trim(), Market.SHSE.value);
                if (contents.length < 23) {
                    logger.warn("记录格式错误，请检查该数据是否合法！！ record [{}]", Arrays.toString(contents));
                    return;
                }
                LimitPrice.addUpperLimitPrice(stockCode, Double.parseDouble(contents[22].trim()));
                LimitPrice.addLowerLimitPrice(stockCode, Double.parseDouble(contents[23].trim()));
            });
            txtParser.close();
            logger.info("解析[{}]文件获取涨跌停价格成功", cpxxPath);
        } catch (Exception e) {
            logger.error("通过cpxx文件缓存涨跌停价格失败！！ {}", e);
        }
    }

    public void cacheQuoteByFile() {
        if (sseFileConfig.isStockEnable()) {
            String stockFilePath = sseFileConfig.getStockFilePath();
            logger.info("开始通过文件[{}]缓存股票行情信息", stockFilePath);
            handleFileRecord(stockFilePath, MDStreamID.STOCK.value);
            logger.info("缓存股票行情信息成功");
        }
        if (sseFileConfig.isBondEnable()) {
            String bondFilePath = sseFileConfig.getBondFilePath();
            logger.info("开始通过文件[{}]缓存债券行情信息", bondFilePath);
            handleFileRecord(bondFilePath, MDStreamID.BOND.value);
            logger.info("缓存债券行情信息成功");
        }
        if (sseFileConfig.isOptionEnable()) {
            String optionFilePath = sseFileConfig.getOptionFilePath();
            logger.info("开始通过文件[{}]缓存期权行情信息", optionFilePath);
            handleFileRecord(optionFilePath, MDStreamID.OPTION.value);
            logger.info("缓存期权行情信息成功");
        }
    }

    private void handleFileRecord(String filePath, String recordPrefix) {
        try {
            TxtParser txtParser = new TxtParser(filePath);
            txtParser.getContextList().forEach(record -> {
                String[] split = record.split("\\|");
                // 剔除首行、最后一行  以及指数类行情的记录
                if ((recordPrefix.equals(MDStreamID.STOCK.value) && split[0].equals(MDStreamID.INDEX.value)) || split[0].equals("HEADER") || split[0].equals("TRAILER")) {
                    return;
                }
                QuoteProto.TickBean.Builder builder = threadLocalBuilder.get();
                SSEFileConverter.convertToTick(split, builder);
                quoteService.addTick(builder.build());
            });
            txtParser.close();
        } catch (Exception e) {
            logger.error("handleFileRecord exception {}", e);
        }
    }


    //用于刷新成交与委托回写的文件，IMS不会提交更新到系统，watchFile无法自动获取到更新；
    //文件若有更新，打开后会自动触发更新事件
    private void updateFile(String quoteDir) {
        while (true) {
            try {
                Thread.sleep(10);
                FileInputStream stock = new
                        FileInputStream(quoteDir + "\\" + sseFileConfig.getStockName());
                FileInputStream bond = new
                        FileInputStream(quoteDir + "\\" + sseFileConfig.getBondName());
                FileInputStream option = new
                        FileInputStream(quoteDir + "\\" + sseFileConfig.getOptionName());
                stock.close();
                bond.close();
                option.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void watchFile(String dir) {
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            final Path path = Paths.get(dir);
            path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY,
                    StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE);
            while (true) {
                final WatchKey wk = watchService.take();
                for (WatchEvent<?> event : wk.pollEvents()) {
                    final Path changed = (Path) event.context();
                    if (Objects.isNull(changed)) continue;
                    String fileName = changed.toString();
                    if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY
                            && (sseFileConfig.isStockQuote(fileName) || sseFileConfig.isBondQuote(fileName) || sseFileConfig.isOptionQuote(fileName))) {
                        try {
                            if (sseFileConfig.isStockQuote(fileName)) {
                                updateQuoteCache(MDStreamID.STOCK.value, sseFileConfig.getStockFilePath());
                            } else if (sseFileConfig.isBondQuote(fileName)) {
                                updateQuoteCache(MDStreamID.BOND.value, sseFileConfig.getBondFilePath());
                            } else if (sseFileConfig.isOptionQuote(fileName)) {
                                updateQuoteCache(MDStreamID.OPTION.value, sseFileConfig.getOptionFilePath());
                            }
                        } catch (Exception e) {
                            logger.error("read file [{}] failed {}", fileName, e);
                        }
                    }
                }
                boolean valid = wk.reset();
                if (!valid) {
                    logger.info("watch key:{} has been unregistered", wk.toString());
                }
            }
        } catch (Exception e) {
            logger.error("watch dir: {} exception:{}", dir, e);
        }
    }

    private void updateQuoteCache(String recordPrefix, String filePath) throws IOException {
        TxtParser txtParser = new TxtParser(filePath);
        txtParser.getContextList().forEach(record -> {
            String[] splits = record.split("\\|");
            if ((recordPrefix.equals(MDStreamID.STOCK.value) && splits[0].equals(MDStreamID.INDEX.value)) || splits[0].equals("HEADER") || splits[0].equals("TRAILER"))
                return;
            String stockCode = StockCodeUtils.formatStockCode(splits[1].trim(), Market.SHSE.value);
            double updateTimeDb = quoteService.getUpdateTime(stockCode);
            double updateTimeNow = SSEFileConverter.calTimeStamp(LocalTime.parse(splits[splits.length - 1].trim()));
            // 当时间戳发生变更后， 更新该条记录
            if (updateTimeNow > updateTimeDb) {
                cacheUpdateQuoteByWatch(splits);
                logger.info("查询到行情变更信息 instrument [{}] cacheTime [{}] updateTime [{}]", stockCode, updateTimeDb, updateTimeNow);
            }
        });
        txtParser.close();
    }

    private void cacheUpdateQuoteByWatch(String[] records) {
        QuoteProto.TickBean.Builder builder = threadLocalBuilder.get();
        SSEFileConverter.convertToTick(records, builder);
        quoteService.addUpdateTick(builder.build());
    }
}
