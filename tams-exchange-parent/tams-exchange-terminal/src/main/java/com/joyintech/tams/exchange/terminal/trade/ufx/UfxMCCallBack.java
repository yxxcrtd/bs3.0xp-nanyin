package com.joyintech.tams.exchange.terminal.trade.ufx;

import com.hundsun.mcapi.interfaces.ISubCallback;
import com.hundsun.mcapi.subscribe.MCSubscribeParameter;
import com.hundsun.t2sdk.common.share.dataset.DatasetService;
import com.hundsun.t2sdk.interfaces.share.dataset.IDataset;
import com.hundsun.t2sdk.interfaces.share.event.IEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * create By MAXX on 2020/4/3
 */
public class UfxMCCallBack implements ISubCallback {
    protected final static Logger logger = LoggerFactory.getLogger(UfxMCCallBack.class);

    /***
     * @Description: 发布消息的回调
     * @Param: [topicName, event]
     * @return: void
     * @Author: MAXX
     * @Date: 2020/4/3
     */
    @Override
    public void OnReceived(String topicName, IEvent event) {
        // TODO Auto-generated method stub
        IDataset ds = event.getEventDatas().getDataset(0);
        logger.info("OnReceived:" + topicName);
        DatasetService.printDataset(ds);
        logger.info(topicName);
        // TODO Auto-generated method stub
        // DatasetService.printDataset(event.getEventDatas().getDataset(0));
        UfxMCHandler.mcReceived(topicName, ds);
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
