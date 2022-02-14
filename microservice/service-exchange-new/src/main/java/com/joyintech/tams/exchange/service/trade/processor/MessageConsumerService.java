package com.joyintech.tams.exchange.service.trade.processor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.joyintech.framework.rabbitmq.entity.SimpleRabbitMessage;
import com.joyintech.redis.core.base.PipeReadService;
import com.joyintech.redis.core.data.RedisHash;
import com.joyintech.tams.exchange.common.bean.investment.EntrustOpeBean;
import com.joyintech.tams.exchange.common.constant.InstructionFlag;
import com.joyintech.tams.exchange.common.constant.InstructionStatus;
import com.joyintech.tams.exchange.common.constant.joyin.AutoReceiveFlag;
import com.joyintech.tams.exchange.common.db.domain.Instruction;
import com.joyintech.tams.exchange.common.db.domain.InstructionPledge;
import com.joyintech.tams.exchange.common.utils.JsonUtils;
import com.joyintech.tams.exchange.datacommon.db.service.InstructionPledgeService;
import com.joyintech.tams.exchange.datacommon.db.service.InstructionService;
import com.joyintech.tams.exchange.service.trade.management.service.CommonManagement;
import com.joyintech.tams.exchange.service.trade.management.service.InvestmentManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MessageConsumerService {
    private static final Logger logger = LoggerFactory.getLogger(MessageConsumerService.class);

    @Autowired
    private InstructionService instructionService;

    @Autowired
    private InstructionPledgeService instructionPledgeService;

    @Autowired
    private PipeReadService investRedisReadService;

    @Autowired
    private CommonManagement commonManagement;

    @Autowired
    private InvestmentManagement investmentManagement;

    @RabbitListener(queues = "queue.yitihua.instruction")
    public void getInstructionFromMQ(SimpleRabbitMessage instructionKeyMsg) {
        logger.info("receive RabbitMessage key {}--message {}", instructionKeyMsg.getId(), instructionKeyMsg.getMessage());
        //todo 暂时无法放进现有队列
        handleInstructionFromMQ(instructionKeyMsg);
    }

    public void handleInstructionFromMQ(SimpleRabbitMessage instructionKeyMsg) {
        try {
            logger.info("start handle message {}", instructionKeyMsg.getId());
            RedisHash<String, String, String> khvRedisHash = new RedisHash<>();
            String redisKey = "RSK{MQ}COMMAND";
            khvRedisHash.setKey(redisKey);
            khvRedisHash.setField((String) instructionKeyMsg.getMessage());
            String instructionRedis = (String) investRedisReadService.read(khvRedisHash);
            if (Strings.isNullOrEmpty(instructionRedis)) {
                logger.info("getInstructionFromMQ is null or empty by redis and the msgId is {}", khvRedisHash.getField());
                return;
            }
            JSONObject jsonObject = JSON.parseObject(instructionRedis);
            logger.info("receive MQ massage query from redis json  is  {}", jsonObject.toString());
            List<Instruction> instructionEtrEntrustList = JsonUtils.getObjectListFromJson(jsonObject.getString("etr_entrust_list"), Instruction.class);
            List<InstructionPledge> pledgeList = JsonUtils.getObjectListFromJson(jsonObject.getString("etr_pledge_list"), InstructionPledge.class);
            // 存储抵押券信息
            Set<String> instructionPledgeIdSet = new HashSet<>();
            if (Objects.nonNull(pledgeList))
                pledgeList.forEach(instructionPledge -> instructionPledgeIdSet.add(instructionPledge.getEntrustId()));
            // 处理指令信息，并清除过期指令下的抵押券信息
            processInstructions(instructionEtrEntrustList, instructionPledgeIdSet);
            // 处理抵押券信息
            processInstructionPledges(pledgeList, instructionPledgeIdSet);
        } catch (Exception e) {
            logger.warn("receive or handle rabbitMessage error {}", e);
        }
    }

    public void processInstructions(List<Instruction> instructionEtrEntrustList, Set<String> instructionPledgeIdSet) {
        List<Instruction> instructionList = new ArrayList<>();
        Set<String> removeInstructionIdSet = new HashSet<>();
        List<Instruction> removeInstructionList = new ArrayList<>();
        Map<String, Instruction> instructionMapCache = instructionService.getMap();
        // 记录推送指令
        Map<String, Map<String, Instruction>> traderMap = new HashMap<>();
        Map<String, Set<String>> instructionTraderMap = new HashMap<>();
        List<EntrustOpeBean> entrustList = new ArrayList<>();
        if (instructionEtrEntrustList.size() > 0) {
            instructionEtrEntrustList.forEach(instruction -> {
                // 为指令设置trader
                Set<String> tradeSet = instructionTraderMap.getOrDefault(instruction.getEntrustId(), new HashSet<>());
                tradeSet.add(instruction.getTradeId());
                instructionTraderMap.put(instruction.getEntrustId(), tradeSet);

                if (traderMap.containsKey(instruction.getTradeId())) {
                    traderMap.get(instruction.getTradeId()).put(instruction.getEntrustId(), instruction);
                } else {
                    Map<String, Instruction> instructionBeanMap = new HashMap<>();
                    instructionBeanMap.put(instruction.getEntrustId(), instruction);
                    traderMap.put(instruction.getTradeId(), instructionBeanMap);
                }
            });
            instructionEtrEntrustList.forEach(instruction -> {
                String instructionId = instruction.getEntrustId();
                Instruction instructionFormCache = instructionMapCache.get(instructionId);
                Set<String> stringSet = instructionTraderMap.get(instructionId);
                instruction.setTradersInfo(JsonUtils.getJsonFromObject(stringSet));
                // 过滤掉指令中平台日小于当前平台日和无tradeId的指令
                Date investSystemDate = commonManagement.getPlatDate();
                logger.debug("当前平台日: {}", investSystemDate.toString());
                if (!investSystemDate.after(instruction.getMdate()) && !Strings.isNullOrEmpty(instruction.getTradeId())) {
                    // 处理已存在且存在trader的指令
                    if (Objects.nonNull(instructionFormCache) && !Strings.isNullOrEmpty(instructionFormCache.getTradersInfo())) {
                        // 内存中缓存交易员信息
                        List<String> traderSetFromRedis = JsonUtils.getObjectListFromJson(instructionFormCache.getTradersInfo(), String.class);
                        if (Objects.nonNull(traderSetFromRedis)) {
                            // 当前分发指令的交易员信息
                            String tradersInfo = traderMap.get(instruction.getTradeId()).get(instructionId).getTradersInfo();
                            List<String> localTradersInfoList = JsonUtils.getObjectListFromJson(tradersInfo, String.class);
                            // 处理指令中user变化的情况
                            traderSetFromRedis.forEach(s -> {
                                if (!localTradersInfoList.contains(s)) {
                                    // 如果该条指令对应的trader减少，删除该指令,并重新添加该指令
                                    logger.info("instruction [{}] trader change and delete other traders [{}] ", instruction.getEntrustId(), s);
                                    instructionService.deleteTraderCache(s, instruction.getFinprodMarketId(), instructionId);
                                }
                            });
                            // 指令状态不同
                            if (!instructionFormCache.getEntrustStatus().equals(instruction.getEntrustStatus()) && !instructionFormCache.getEntrustStatus().equals(InstructionStatus.TOBE_CANCELED.value)) {
                                logger.info("instruction status updated {}", instruction.getEntrustStatus());
                                traderSetFromRedis.forEach(s -> {
                                    instructionService.deleteTraderCache(s, instructionFormCache.getFinprodMarketId(), instructionId);
                                });
                            }
                            instruction.setFrozenQuantity(instructionFormCache.getFrozenQuantity());
                            instruction.setEntrustLeftAmt(instructionFormCache.getEntrustLeftAmt());
                        }
                    } else {
                        logger.info("收到下发的新指令： {}", instruction.getEntrustId());
                        if (AutoReceiveFlag.AUTO.value.equals(instruction.getIsAutoReceive())) {
                            logger.info("自动接收指令：{}", instructionId);
                            EntrustOpeBean opeBean = new EntrustOpeBean();
                            opeBean.setTrader(instruction.getTradeId());
                            opeBean.setRecStatus(InstructionFlag.ACCEPT.value);
                            opeBean.setEntrustId(instructionId);
                            entrustList.add(opeBean);
                        }
                    }
                    instructionList.add(instruction);
                } else {
                    logger.info("investSystemDate {} > instruction mDate {}", JsonUtils.getJsonFromObject(investSystemDate), JsonUtils.getJsonFromObject(instruction.getMdate()));
                    // 清除对应指令的抵押券信息
                    instructionPledgeIdSet.remove(instruction.getEntrustId());
                    // 清除之前缓存的数据
                    if (Objects.nonNull(instructionFormCache)) {
                        removeInstructionList.add(instructionFormCache);
                    }
                    List<InstructionPledge> instructionPledges = instructionPledgeService.queryInstructionPledgesByInstructionId(instruction.getEntrustId());
                    if (Objects.nonNull(instructionPledges))
                        removeInstructionIdSet.add(instruction.getEntrustId());
                }
            });
            if (removeInstructionList.size() > 0) {
                logger.info("delete origin instructions from DB {}", removeInstructionList.size());
                instructionService.asyncDel(removeInstructionList);
            }
            if (removeInstructionIdSet.size() > 0) {
                logger.info("delete origin instructionPledges from DB {}", removeInstructionIdSet.size());
                instructionPledgeService.deleteInstructionPledgesByInstructionsIds(removeInstructionIdSet);
            }
            logger.info("存储下发指令 {}", instructionList.size());
            instructionService.asyncUpdate(instructionList);
            if (!entrustList.isEmpty()) {
                logger.info("自动接收中...");
                investmentManagement.doAcceptOrRefuse(entrustList);
                logger.info("自动接收完成。");
            }
        }
    }

    public void processInstructionPledges(List<InstructionPledge> pledgeList, Set<String> instructionIdSet) {
        if (Objects.nonNull(pledgeList)) {
            instructionPledgeService.deleteInstructionPledgesByInstructionsIds(instructionIdSet);
            // 重新存储抵押券信息
            pledgeList.forEach(instructionPledge -> {
                if (instructionIdSet.contains(instructionPledge.getEntrustId()))
                    instructionPledgeService.asyncUpdate(instructionPledge);
            });
        }
    }
}
