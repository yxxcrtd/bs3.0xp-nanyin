package com.joyintech.tams.business.common.service;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.business.common.constant.PositionDictCodeListExt;
import com.joyintech.tams.business.common.dto.ManagerDto;
import com.joyintech.tams.business.common.dto.PositionRuleInterDto;
import com.joyintech.tams.business.common.exception.PositionException;
import com.joyintech.tams.business.common.util.PositionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 头寸引擎发送mq线程管理类
 */
@PropertySource("classpath:field.properties")
@Component
public class PositionFutureManager {
    private static Logger logger = LoggerFactory.getLogger(PositionFutureManager.class);
    public static Map<String, DefaultFuture> futureMap = new ConcurrentHashMap<String, DefaultFuture>(); // 线程阻塞存储，唤醒后删除
    private static PositionRuleService positionRuleService; // mq 请求接收实现类

    public final static String EXCHANGE_POSITION_RULE = "exchange.position.rule";
    public final static String EXCHANGE_POSITION_CANCEL = "exchange.position.cancel";
    
    public final static String INV_ETR_POS = "inv.etr.pos";
    public final static String INV_TRD_POS = "inv.trd.pos";
    public final static String INV_CANCEL = "inv.cancel"; // 数据inv.* 都可匹配到

    public static String QUEUE_POSITION_RULE = "queue.position.rule"; // 自己测试监听使用
    
    /** 优先field.properties中的computerId赋值
     *  实际为头寸引擎返回队列，投管根据此队列监听，必须在数据库有对应的配置
     */
    public static String QUEUE_POSITION_CALLBACK = "queue.position.callback";

    @Value("${computerId}")
    public void setQueuePositionCallback(String queuePositionCallback) {
        PositionFutureManager.QUEUE_POSITION_CALLBACK = queuePositionCallback;
    }
    
    public final static String CODE_TIMEOUT = "410";        // mq超时，需冲正
    public final static String CODE_SEND_ERROR = "420";     // mq发送异常
    public final static String CODE_BUSI_ERROR = "430";     // mq返回业务异常
    public final static String CODE_BUSI_REFUSE = "200";    // mq返回业务信息拒绝，需前端确认

    // 发送并阻塞等待唤醒
    public static boolean notify(Message message){
        // 获取 message id
        String id = message.getMessageProperties().getMessageId();
        DefaultFuture obj = futureMap.get(id);
        if (obj != null){
            Object waitObject = obj.waitObject;
            synchronized (waitObject) {
                if(!obj.isDone){
                    obj = futureMap.get(id);
                    if (obj == null){
                        return false;
                    }

                    String str = null;
                    try {
                        str = new String(message.getBody(), "UTF-8");
                        // mq监听到的消息，可以在send后获取
                        obj.setMapMessage(JsonUtils.json2Object(str, Map.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                        logger.error(e.getMessage());
                    }finally {
                        waitObject.notify();
                        futureMap.remove(id);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public static void send(PositionRuleInterDto dto){

        ManagerDto managerDto = new ManagerDto();
        managerDto.setPositionRuleInterDto(dto);
        managerDto.setExchange(EXCHANGE_POSITION_RULE);
        if (StringUtil.equals(dto.getModelType(), PositionDictCodeListExt.子系统代码_委托.getValue())){
            managerDto.setRoutingKey(INV_ETR_POS);
        }else if (StringUtil.equals(dto.getModelType(), PositionDictCodeListExt.子系统代码_交易.getValue())){
            managerDto.setRoutingKey(INV_TRD_POS);
        }else{
            throw new PositionException("不存在该子系统代码:" + dto.getModelType());
        }

        // 发送后等待唤醒
        Map<String, Object> message = sendPrivate(managerDto);

        if (message == null || !StringUtil.equals("success", message.get("code"))){
            if(StringUtil.equals("FL0007", message.get("code"))){
                throw new PositionException(CODE_BUSI_REFUSE + "|" + (message==null?"":message.get("message")));
            }else{
                throw new PositionException(CODE_BUSI_ERROR + "|" + (message==null?"":message.get("message")));
            }
        }
    }

    // 关闭、撤销、撤回、退回时使用
    public static void sendRevoke(PositionRuleInterDto dto){
        
        ManagerDto managerDto = new ManagerDto();
        managerDto.setPositionRuleInterDto(dto);
        managerDto.setExchange(EXCHANGE_POSITION_CANCEL);
        managerDto.setRoutingKey(INV_CANCEL);
        managerDto.setNeedTimeOutRevoke(false); // 执行异常不抛，包括超时，ps:撤销时没办法反冲正，只能多发几次
        managerDto.setRevokeTimes(3); // 超时后重发次数
        
        // 发送后等待唤醒
        Map<String, Object> message = sendPrivate(managerDto);
        
        if (message == null || !StringUtil.equals("success", message.get("code"))){
            throw new PositionException(CODE_BUSI_ERROR + "|" + message);
        }
    }

    /**
     * 撤销、退回、关闭头寸，需要等待
     * @param totalId 全局标识 指令编号或全局交易编号
     * @param modelType 
     */
    public static void sendRevoke(String totalId, String modelType){
        PositionRuleInterDto dto = PositionUtil.initDto(totalId);
        dto.setModelType(modelType);
        
        ManagerDto managerDto = new ManagerDto();
        managerDto.setPositionRuleInterDto(dto);
        managerDto.setExchange(EXCHANGE_POSITION_CANCEL);
        managerDto.setRoutingKey(INV_CANCEL);
        managerDto.setNeedTimeOutRevoke(false); // 执行异常不抛，包括超时，ps:撤销时没办法反冲正，只能多发几次
        managerDto.setRevokeTimes(3); // 超时后重发次数

        // 发送后等待唤醒
        Map<String, Object> message = sendPrivate(managerDto);

        if (message == null || !StringUtil.equals("success", message.get("code"))){
            throw new PositionException(CODE_BUSI_ERROR + "|" + message);
        }
    }
    
    // 投管处理异常
    private static void revoke(PositionRuleInterDto dto, Exception e){
        revokeFromException(dto, "err001", e);
    }

    public static void revoke(String totalId, String modelType, Exception e){
        PositionRuleInterDto dto = PositionUtil.initDto(totalId);
        dto.setModelType(modelType);
        
        /**
         * err001   投管处理异常
         * err002   超时异常
         */
        revokeFromException(dto, "err001", e);
    }

    /**
     * 头寸引擎撤销
     * @param dto
     * @param errorCode 超时撤销冲正err002; 投管处理异常，手工发送撤销err001
     */
    private static void revokeFromException(PositionRuleInterDto dto, String errorCode, Exception e){

        dto.setErrorCode(errorCode);
        
        ManagerDto managerDto = new ManagerDto();
        managerDto.setPositionRuleInterDto(dto);
        managerDto.setExchange(EXCHANGE_POSITION_CANCEL);
        managerDto.setRoutingKey(INV_CANCEL);
        
        // 
        executeRouting(managerDto);
        // 页面端根据异常信息返回前端
        throw new PositionException(CODE_TIMEOUT + "|" + e.getMessage());
    }
    
    /**
     * 判断执行对应方法
     * @param isRule 正向执行
     */
    private static void executeRouting(ManagerDto managerDto){
        // 发送mq消息到头寸引擎
        positionRuleService.sendMessage(managerDto);
        
        if (managerDto.isNeedListener()){
            // 添加监控线程
            positionRuleService.executeListener();
        }
    }
    
    /**
     * mq发送
     * @param dto
     * @return 返回null或SimpleRabbitMessage为失败时返回错误
     */
    private static Map<String, Object> sendPrivate(ManagerDto managerDto){

        // 从系统参数中获取配置信息
        // INSERT INTO SYS_PARA(SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)VALUES('INV', 'MQRequest.timeout', '30000', NULL, NULL, '头寸引擎mq调用超时时间(毫秒)', 'system', NULL, sysdate, 'system', sysdate, NULL, '01');
        String timeoutStr = CacheUtil.getSysParam("MQRequest.timeout");

        // 初始化阻塞任务
        DefaultFuture obj = new DefaultFuture(managerDto.getId(), 
                StringUtil.isNotEmpty(timeoutStr)?Long.parseLong(timeoutStr):2000L);
        
        // 执行mq消息发送
        managerDto.setNeedListener(true);
        try {
            obj.doInvoker(e -> {
                executeRouting(managerDto);
                return true;
            }, managerDto);
            return obj.getMapMessage();
        } catch (PositionException e) {
            // mq超时
            if (managerDto.isNeedTimeOutRevoke()){
                /**
                 * err001   投管处理异常
                 * err002   超时异常
                 */
                revokeFromException(managerDto.getPositionRuleInterDto(), "err002", e);
            }
        } catch (Exception e) {
            // mq发送异常 不用冲正 页面端根据异常信息返回前端
            throw new PositionException(CODE_SEND_ERROR + "|" + e.getMessage());
        }
        return obj.getMapMessage();
    }
    
    /**
     * mq发送信息前添加
     * @param defaultFuture
     * @return
     */
    public static boolean add(DefaultFuture defaultFuture){
        synchronized (defaultFuture.waitObject) {
            DefaultFuture obj = futureMap.get(defaultFuture.getId());
            if(obj == null){
                futureMap.put(defaultFuture.getId(), defaultFuture);
                return true;
            }else{
                return false;
            }
        }
    }

    @Autowired
    public void setEtrEntrustMapper(PositionRuleService service){
        PositionFutureManager.positionRuleService=service;
    }
}
