package com.joyintech.tams.exchange.service.trade.processor.algorithm;

import cn.hutool.core.bean.BeanUtil;
import com.google.common.collect.Maps;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.exchange.common.bean.AlgorithmParameter;
import com.joyintech.tams.exchange.common.bean.OrderPlaceBean;
import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.bean.ReviewDto;
import com.joyintech.tams.exchange.common.cache.QuoteTickService;
import com.joyintech.tams.exchange.common.constant.*;
import com.joyintech.tams.exchange.common.db.domain.*;
import com.joyintech.tams.exchange.common.proto.QuoteProto;
import com.joyintech.tams.exchange.common.utils.JsonUtils;
import com.joyintech.tams.exchange.common.utils.StockCodeUtils;
import com.joyintech.tams.exchange.common.utils.TradeUtils;
import com.joyintech.tams.exchange.datacommon.db.service.*;
import com.joyintech.tams.exchange.service.trade.management.service.CommonManagement;
import com.joyintech.tams.exchange.service.trade.management.service.EntrustManagement;
import com.joyintech.tams.exchange.service.trade.management.service.OrderManagement;
import com.joyintech.tams.exchange.service.trade.processor.TradeHelper;
import com.joyintech.tams.exchange.service.trade.thead.ThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.joyintech.tams.exchange.common.constant.EntrustProp.BEST5_TO_LIMIT;
import static com.joyintech.tams.exchange.common.constant.EntrustProp.COUNTERPARTY;

@Component
public class AlgorithmHelper {
    private static final Logger logger = LoggerFactory.getLogger(AlgorithmHelper.class);

    @Autowired
    private InstructionService instructionService;
    @Autowired
    private AlgorithmService algorithmService;
    @Autowired
    private ThreadPool threadPool;
    @Autowired
    private QuoteTickService quoteTickService;
    @Autowired
    private InstrumentService instrumentService;
    @Autowired
    private FuturesInstrumentService futuresInstrumentService;
    @Autowired
    private EntrustService entrustService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private EntrustManagement entrustManagement;
    @Autowired
    private OrderManagement orderManagement;
    @Autowired
    private BarService barService;
    @Autowired
    private CommonManagement commonManagement;

    @Value("${bar.timetype.begin:true}")
    private boolean begin;

    @Autowired
    private TradeHelper tradeHelper;

    private Map<String, Entrust> supplementEntrustMap = Maps.newConcurrentMap();


    public void handleAlgorithmCreate(String userId, Algorithm algorithm) {
        Instruction instruction = instructionService.findById(algorithm.getInstructionId());
        if (Objects.nonNull(instruction)) {
            if (algorithmCheck(algorithm, instruction)) {
                AlgorithmParameter algorithmParameter = JsonUtils.getObjectFromJson(algorithm.getParameter(), AlgorithmParameter.class);
                if (Objects.isNull(algorithmParameter)) {
                    algorithmParameter = new AlgorithmParameter();
                    algorithmParameter.setPriceMode(BatchPriceMode.LAST_PRICE.value);
                    algorithmParameter.setSupplementAction(AlgorithmAction.SUPPLEMENT.value);
                    algorithm.setParameter(JsonUtils.getJsonFromObject(algorithmParameter));
                }
                OrderPlaceBean orderPlaceBean = new OrderPlaceBean();
                orderPlaceBean.setInstructionId(algorithm.getInstructionId());
                orderPlaceBean.setAlgorithmId(algorithm.getId());
                orderPlaceBean.setPortfolioId(instruction.getPortfolioId());
                orderPlaceBean.setUserId(userId);
                orderPlaceBean.setUsername(userId);
                orderPlaceBean.setAccountId(instruction.getAcctNo());
                orderPlaceBean.setStockCode(algorithm.getStockCode());
                orderPlaceBean.setEntrustBs(algorithm.getEntrustBs());
                orderPlaceBean.setOffsetFlag(algorithm.getOffsetFlag());
                orderPlaceBean.setEntrustVolume(algorithm.getVolume());
                orderPlaceBean.setEntrustPrice(BigDecimal.ZERO);
                orderPlaceBean.setOffsetFlag(algorithm.getOffsetFlag());
                orderPlaceBean.setHedgeFlag(HedgeFlag.Speculation.value);
                if (BatchPriceMode.LAST_PRICE.value == algorithmParameter.getPriceMode()) {
                    orderPlaceBean.setEntrustProp(Market.SHSE.value.equals(StockCodeUtils.getMarket(algorithm.getStockCode())) ? BEST5_TO_LIMIT.value : COUNTERPARTY.value);
                } else {
                    orderPlaceBean.setEntrustProp(algorithm.getEntrustProp());
                }
                ReviewDto dto = BeanUtil.toBean(orderPlaceBean, ReviewDto.class);
                Order order = tradeHelper.createOrder(dto);
                if (!ErrorCode.isSuccess(order.getErrorCode())) {
                    logger.info("算法单母单检查未通过：instructionId [{}] error:{}-{}", algorithm.getInstructionId(), order.getErrorCode(), order.getErrorMsg());
                    algorithm.setErrorCode(order.getErrorCode());
                    algorithm.setErrorMsg(order.getErrorMsg());
                    return;
                }
                algorithm.setTradingDay(order.getTradingDay());
                algorithmService.asyncUpdate(algorithm);
                order.setAlgorithmId(algorithm.getId());
                order.setBusinessFlag(BusinessFlag.ALGO.value);
                orderService.asyncUpdate(order);

                if (commitAlgorithm(algorithm, algorithmParameter, instruction)) {
                    algorithm.setAlgorithmStatus(AlgorithmStatus.PROCESSING.value);
                } else {
                    algorithm.setAlgorithmStatus(AlgorithmStatus.CANCELED.value);
                }
            }
        } else {
            algorithm.setErrorCode(ErrorCode.INSTRUCTIONS_NOT_EXIST.value);
            algorithm.setErrorMsg(ErrorCode.INSTRUCTIONS_NOT_EXIST.errorMsg);
        }
    }

    private boolean algorithmCheck(Algorithm algorithm, Instruction instruction) {
        if (!(Objects.nonNull(algorithm.getBeginTime()) && Objects.nonNull(algorithm.getEndTime())
                && DateUtil.compareDate(algorithm.getBeginTime(), algorithm.getEndTime()) <= 0)) {
            algorithm.setErrorCode(ErrorCode.ALGORITHM_TIME_ERROR.value);
            algorithm.setErrorMsg(ErrorCode.ALGORITHM_TIME_ERROR.errorMsg);
            return false;
        }
        if (!InstructionBusiType.isBiddingType(instruction.getEntrustBussType())) {
            algorithm.setErrorCode(ErrorCode.INSTRUCTION_NOT_SUPPORT_ALGORITHM_ERROR.value);
            algorithm.setErrorMsg(ErrorCode.INSTRUCTION_NOT_SUPPORT_ALGORITHM_ERROR.errorMsg);
            return false;
        }
        return true;
    }


    private boolean commitAlgorithm(Algorithm algorithm, AlgorithmParameter algorithmParameter, Instruction instruction) {
        //提交算法中心，暂时先自行处理
        threadPool.execute(() -> runAlgorithm(algorithm, algorithmParameter, instruction));
        logger.info("算法单[{}]提交成功，parameter：{}", algorithm.getId(), JsonUtils.getJsonFromObject(algorithmParameter));
        return true;
    }

    private void runAlgorithm(Algorithm algorithm, AlgorithmParameter algorithmParameter, Instruction instruction) {
        long currentMill = System.currentTimeMillis();
        long beginTime = algorithm.getBeginTime().getTime();
        long endTime = algorithm.getEndTime().getTime();
        if (currentMill > beginTime) beginTime = currentMill;
        long timeMillSeconds = (int) (endTime - beginTime);
        long interval = 60000;
        long entrustCount = timeMillSeconds / interval;
        Map<String, Bar> barMap = new HashMap<>();
        long totalVolume = 0;
        if (AlgorithmName.TWAP.name.equals(algorithm.getAlgorithmName())) {
            if (algorithmParameter.getMaxVolumePerEntrust() > 0) {
                double minCount = algorithm.getVolume() / (double) algorithmParameter.getMaxVolumePerEntrust();
                BigDecimal bd = new BigDecimal(minCount);
                if (minCount > entrustCount) {
                    entrustCount = bd.setScale(0, BigDecimal.ROUND_UP).intValue();
                    interval = timeMillSeconds / entrustCount;
                }
            }
        } else if (AlgorithmName.VWAP.name.equals(algorithm.getAlgorithmName())) {
            int days = Math.max(algorithmParameter.getDays(), 20);
            String today = commonManagement.getTradingDay();
            List<Bar> barList = barService.queryLastDaysBar(algorithm.getStockCode(), days, today);
            totalVolume = filterBars(barList, beginTime, endTime, barMap);
        }
        logger.info("算法单预算笔数：{}-{}", algorithm.getId(), entrustCount);

        long volume = algorithm.getVolume();

        int sleepTime = 50;
        long loopLeft = interval;
        SimpleDateFormat keyFormat = new SimpleDateFormat("HH:mm");
        while (AlgorithmStatus.PROCESSING.value.equals(algorithm.getAlgorithmStatus())) {
            currentMill = System.currentTimeMillis();
            try {
                //时间内才委托
                if (currentMill >= beginTime && currentMill <= endTime) {
                    if (volume > 0 && timeMillSeconds / interval < entrustCount) {
                        long entrustVolume = volume / entrustCount;
                        if (totalVolume > 0) {
                            Bar bar = getBar(barMap, currentMill, keyFormat);
                            if (Objects.nonNull(bar) && bar.getVolume().longValue() > 0) {
                                entrustVolume = TradeUtils.getBigDecimal((bar.getVolume().doubleValue() / totalVolume) * volume, 0).longValue();
                                logger.info("总权重 {}, bar线量 {}， 计算量：{}", totalVolume, bar.getVolume(), entrustVolume);
                                totalVolume -= bar.getVolume().longValue();
                            }
                        }
                        volume = volume - doEntrust(algorithm, algorithmParameter, instruction, entrustVolume);
                        --entrustCount;
                        loopLeft = interval;
                    } else {
                        loopLeft = loopLeft - sleepTime;
                    }
                    timeMillSeconds = endTime - System.currentTimeMillis();
                    //过半时补单
                    if (loopLeft <= interval >> 1) {
                        logger.info("算法[{}]，统计未成交委托，尝试补单", algorithm.getId());
                        cancelUnFinishedAlgorithmEntrust(algorithm, algorithmParameter.getSupplementAction());
                        loopLeft = interval + sleepTime;
                    }
                } else if (currentMill > endTime) {
                    if (AlgorithmAction.CANCEL.value == algorithm.getAfterAction()) {
                        cancelUnFinishedAlgorithmEntrust(algorithm, AlgorithmAction.CANCEL.value);
                    }
                    algorithm.setAlgorithmStatus(AlgorithmStatus.FINISHED.value);
                    algorithmService.asyncUpdate(algorithm);
                    break;
                }
                Thread.sleep(sleepTime);
            } catch (Exception e) {
                logger.info("runAlgorithm exception: {}", e);
            }
        }
        logger.info("算法[{}]运行结束", algorithm.getId());

    }

    private long filterBars(List<Bar> barList, long beginTime, long endTime, Map<String, Bar> barMap) {
        if (begin) {
            //bar 线的时间是开始时间
            beginTime -= 60000;
            endTime -= 60000;
        }
        long total = 0;
        SimpleDateFormat filterFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String beginTimeStr = filterFormat.format(new Date(beginTime)).split(" ")[1];
        String endTimeStr = filterFormat.format(new Date(endTime)).split(" ")[1];
        for (Bar bar : barList) {
            if (beginTimeStr.compareTo(bar.getStrTime().split(" ")[1]) <= 0 && endTimeStr.compareTo(bar.getStrTime().split(" ")[1]) >= 0) {
                String key = bar.getStrTime().substring(11, 16);
                Bar bar1 = barMap.get(key);
                if (Objects.nonNull(bar1)) {
                    bar.setVolume(bar1.getVolume().add(bar.getVolume()));
                }
                barMap.put(key, bar);
                total += bar.getVolume().longValue();
            }
        }
        return total;
    }

    private Bar getBar(Map<String, Bar> barMap, long currentMill, SimpleDateFormat keyFormat) {
        String key = keyFormat.format(new Date(begin ? currentMill : currentMill + 60000));
        ;
        return barMap.get(key);
    }

    private void cancelUnFinishedAlgorithmEntrust(Algorithm algorithm, int action) {
        Order order = orderService.queryOrderByAlgorithmId(algorithm.getId());
        List<Entrust> entrustList = entrustService.queryEntrustByOrderId(order.getId());
        entrustList.forEach(entrust -> {
            if (EntrustType.ENTRUST.value.equals(entrust.getEntrustType()) && !EntrustStatus.isFinished(entrust.getEntrustStatus())) {
                entrustManagement.cancelEntrust(entrust.getUserId(), entrust.getId());
                if (AlgorithmAction.SUPPLEMENT.value == action)
                    supplementEntrustMap.put(entrust.getId(), entrust);
            }
        });
    }

    public void doSupplement(EntrustInterface entrust, Order order) {
        if (Objects.nonNull(order) && BusinessFlag.ALGO.value.equals(order.getBusinessFlag())) {
            if (entrust instanceof Entrust) {
                logger.info("算法单委托撤单，检查是否需要补单...");
                if (supplementEntrustMap.containsKey(entrust.getId())) {
                    logger.info("开始补单...");
                    Algorithm algorithm = algorithmService.findById(order.getAlgorithmId());
                    if (AlgorithmName.TWAP.name.equals(algorithm.getAlgorithmName())) {
                        AlgorithmParameter algorithmParameter = JsonUtils.getObjectFromJson(algorithm.getParameter(), AlgorithmParameter.class);
                        doEntrust(algorithm, algorithmParameter, instructionService.findById(entrust.getInstructionId()), entrust.getCancelVolume());
                        supplementEntrustMap.remove(entrust.getId());
                    }
                } else {
                    logger.info("无需补单");
                }
            }
        }
    }

    private long doEntrust(Algorithm algorithm, AlgorithmParameter algorithmParameter, Instruction instruction, long entrustVolume) {
        Order order = orderService.queryOrderByAlgorithmId(algorithm.getId());
        OrderPlaceBean orderPlaceBean = new OrderPlaceBean();
        orderPlaceBean.setInstructionId(algorithm.getInstructionId());
        orderPlaceBean.setAlgorithmId(algorithm.getId());
        orderPlaceBean.setPortfolioId(instruction.getPortfolioId());
        orderPlaceBean.setUserId(instruction.getTradeId());
        orderPlaceBean.setUsername(instruction.getTraderName());
        orderPlaceBean.setAccountId(instruction.getAcctNo());
        orderPlaceBean.setStockCode(algorithm.getStockCode());
        orderPlaceBean.setEntrustBs(algorithm.getEntrustBs());
        orderPlaceBean.setOffsetFlag(algorithm.getOffsetFlag());
        orderPlaceBean.setEntrustProp(order.getEntrustProp());

        int scale = 1;
        InstrumentInterface instrument = instrumentService.findById(algorithm.getStockCode());
        if (Objects.isNull(instrument)) {
            instrument = futuresInstrumentService.findById(algorithm.getStockCode());
        }
        if (Objects.nonNull(instrument)) {
            scale = instrument.getPriceStep().scale();
            if (BatchPriceMode.LAST_PRICE.value == algorithmParameter.getPriceMode()) {
                entrustVolume = Math.max(entrustVolume, instrument.getMarketLowVolume());
            } else {
                entrustVolume = Math.max(entrustVolume, instrument.getLowVolume());
            }
            //满足最小交易单位的整数倍
            entrustVolume = TradeUtils.divide(new BigDecimal(entrustVolume), instrument.getBuyUnit(), 0).longValue() * instrument.getBuyUnit();
            orderPlaceBean.setEntrustVolume(entrustVolume);
            logger.info("算法单预算下单：{}-{}", algorithm.getId(), orderPlaceBean.getEntrustVolume());
        } else {
            logger.info("没有找到合约，算法单无法下单：{}-{}", algorithm.getId(), algorithm.getStockCode());
            algorithm.setAlgorithmStatus(AlgorithmStatus.CANCELED.value);
            return 0;
        }
        QuoteProto.TickBean tick = quoteTickService.findFromRedisById(algorithm.getStockCode());
        if (Objects.nonNull(tick)) {
            //todo 系统约定填净价，全价债券的行情中取到的是全价，这里直接填充的行情价
            if (BatchPriceMode.LAST_OFFSET.value == algorithmParameter.getPriceMode()) {
                orderPlaceBean.setEntrustPrice(TradeUtils.getBigDecimal(tick.getLastPrice(), scale));
            } else if (BatchPriceMode.ASK_OFFSET.value == algorithmParameter.getPriceMode()) {
                orderPlaceBean.setEntrustPrice(TradeUtils.getBigDecimal(tick.getAskPrice(0), scale));
            } else if (BatchPriceMode.BID_OFFSET.value == algorithmParameter.getPriceMode()) {
                orderPlaceBean.setEntrustPrice(TradeUtils.getBigDecimal(tick.getBidPrice(0), scale));
            } else {
                orderPlaceBean.setEntrustProp(Market.SHSE.value.equals(StockCodeUtils.getMarket(order.getStockCode())) ? BEST5_TO_LIMIT.value : COUNTERPARTY.value);
            }
            ResultBean resultBean = orderManagement.sendOrder(instruction.getTradeId(), orderPlaceBean);
            if (!ErrorCode.isSuccess(resultBean.getErrorCode())) return 0;
        } else {
            logger.info("没有找到行情，算法单无法下单：{}-{}", algorithm.getId(), algorithm.getStockCode());
            orderPlaceBean.setEntrustVolume(0);
        }
        return orderPlaceBean.getEntrustVolume();
    }

    public ResultBean cancelAlgorithm(String algorithmId) {
        Algorithm algorithm = algorithmService.findById(algorithmId);
        if (Objects.isNull(algorithm)) return new ResultBean(ErrorCode.ALGORITHM_NOT_EXIST);
        if (!AlgorithmStatus.PROCESSING.value.equals(algorithm.getAlgorithmStatus()))
            return new ResultBean(ErrorCode.ALGORITHM_STATUS_ERROR);
        algorithm.setAlgorithmStatus(AlgorithmStatus.CANCELED.value);
        logger.info("算法单 [{}] 撤销", algorithmId);
        algorithmService.asyncUpdate(algorithm);
        return new ResultBean(ErrorCode.SUCCESS, algorithm);
    }
}
