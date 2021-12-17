package com.joyintech.tams.exchange.terminal.trade.ufx;

import com.google.common.collect.Maps;
import com.hundsun.mcapi.MCServers;
import com.hundsun.mcapi.interfaces.ISubCallback;
import com.hundsun.mcapi.interfaces.ISubscriber;
import com.hundsun.mcapi.subscribe.MCSubscribeParameter;
import com.hundsun.t2sdk.common.share.dataset.DatasetService;
import com.hundsun.t2sdk.interfaces.share.dataset.IDataset;
import com.hundsun.t2sdk.interfaces.share.event.IEvent;
import com.joyintech.tams.exchange.terminal.trade.ufx.log.util.UfxLogPrintUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * create By MAXX on 2020/4/3
 */
public class UfxMCCallBack implements ISubCallback {
    private final static Logger logger = LoggerFactory.getLogger(UfxMCCallBack.class);

    private static Map<String, UfxMCHandler> ufxMCHandlerMap = Maps.newConcurrentMap();

    public static void setUfxMCHandler(String eventId, UfxMCHandler handler) {
        ufxMCHandlerMap.put(eventId, handler);
    }

    /***
     * @Description: 发布消息的回调
     * @Param: [topicName, event]
     * @return: void
     * @Author: MAXX
     * @Date: 2020/4/3
     */
    @Override
    public void OnReceived(String topicName, IEvent event) {
        try {
            // 获取订阅时入参源信息 -- Appdata
            ISubscriber subscriber = MCServers.GetSubscriber();
            IDataset outDataSet = DatasetService.getDefaultInstance().getDataset();
            subscriber.GetSubcribeTopic(outDataSet);

            String eventID = new String(outDataSet.getByteArray("Appdata"), StandardCharsets.UTF_8);
            IDataset ds = event.getEventDatas().getDataset(0);
            UfxMCHandler ufxMCHandler = ufxMCHandlerMap.get(eventID);
            if (ufxMCHandler == null) {
                logger.warn("未知的 eventID: {}", eventID);
                return;
            }
            logger.info("接收到主题 [{}] 的主推消息\r\n [{}]", topicName, UfxLogPrintUtils.logDatasetInfo(ds));
            ufxMCHandler.mcReceived(topicName, ds);
        } catch (Exception e) {
            logger.info("接收主推消息失败： {}", e);
        }
    }

    /***
     * @Description: 踢人消息的回调
     * @Param: [param, tickMsgInfo]
     * @return: void
     * @Author: MAXX
     * @Date: 2020/4/3
     */
    @Override
    public void OnRecvTickMsg(MCSubscribeParameter param, String tickMsgInfo) {
        logger.info("===========OnRecvTickMsg==========");
        // TODO Auto-generated method stub
    }
}
