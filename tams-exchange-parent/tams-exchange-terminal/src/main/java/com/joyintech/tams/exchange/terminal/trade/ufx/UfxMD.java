package com.joyintech.tams.exchange.terminal.trade.ufx;

import com.hundsun.mcapi.MCServers;
import com.hundsun.mcapi.interfaces.ISubscriber;
import com.hundsun.mcapi.subscribe.MCSubscribeParameter;
import com.hundsun.t2sdk.common.core.context.ContextUtil;
import com.hundsun.t2sdk.common.share.dataset.DatasetService;
import com.hundsun.t2sdk.common.share.event.PackService;
import com.hundsun.t2sdk.impl.client.T2Services;
import com.hundsun.t2sdk.interfaces.IClient;
import com.hundsun.t2sdk.interfaces.share.dataset.IDataset;
import com.hundsun.t2sdk.interfaces.share.event.EventTagdef;
import com.hundsun.t2sdk.interfaces.share.event.EventType;
import com.hundsun.t2sdk.interfaces.share.event.IEvent;
import com.hundsun.t2sdk.interfaces.share.event.IPack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

//消息订阅
@Component
@ConditionalOnProperty(value = "ufx.active", matchIfMissing = false, havingValue = "true")
public class UfxMD {
    protected final static Logger logger = LoggerFactory.getLogger(UfxMD.class);

    private IClient client = null;
    private T2Services server = T2Services.getInstance();// 获取T2实例

    public void init() throws Exception {
        //ar_tree_msg是配置在配置文件中的服务
        client = server.getClient("ar_tree_msg");//走节点ar_tree_msg
        UfxMdCallBack.setClient(client);
        /*MCServers.MCInit();*/
    }
    //订阅-行情实时信息（恒生环境不可用）

    public void ReqFunction620001_25(String fundAccount, String pwd, String futuCode) throws Exception {
        //打印功能号
        System.err.println("ReqFunction620001_25");
        // 获取event
        IEvent event = ContextUtil.getServiceContext().getEventFactory()
                                  .getEventByAlias("620001", EventType.ET_REQUEST);
        // 往event中添加dataset
        IDataset dataset = DatasetService.getDefaultInstance().getDataset();
        dataset.appendRow();
        IPack outPack = PackService.getPacker(IPack.VERSION_2, "utf-8");//获取一个打包器
        outPack.addDataset(dataset);//数据打入包体
        event.setIntegerAttributeValue(EventTagdef.TAG_SENDERID, 1);//分配节点为1，应答
        event.setIntegerAttributeValue(EventTagdef.TAG_ISSUE_TYPE, 25);//设定事件类型
        event.setByteArrayAttributeValue(EventTagdef.TAG_KEY_INFO, outPack.Pack()); //打包
        client.send(event);//发送包
    }

    //订阅-成交主推-证券成交回报
    public void ReqSubTradeReturn(String account, Integer branch_no) throws Exception {

        ISubscriber subscriber = MCServers.GetSubscriber();
        MCSubscribeParameter subParam = new MCSubscribeParameter();
        subParam.SetTopicName("secu.deal_return");
        byte[] data = {1, 3, 5, 8, 9, 10, 2, 3, 0};
        subParam.SetAppData(data);
        subParam.SetFromNow(true);
        subParam.SetReplace(true);
        subParam.SetFilter("branch_no", branch_no.toString());
        subParam.SetFilter("fund_account", account);
        int iRet = subscriber.SubscribeTopic(subParam, 3000);
        //返回iRet>0表示成功
        logger.info("account :{} , branch_no:{} ,ReqSubTradeReturn:{}", account, branch_no, iRet);
    }

    //订阅-成交主推-证券委托回报
    public void ReqSubOrderReturn(String account, Integer branch_no) throws Exception {
        //MCServers.MCInit();
        ISubscriber subscriber = MCServers.GetSubscriber();
        MCSubscribeParameter subParam = new MCSubscribeParameter();
        subParam.SetTopicName("secu.entrustwriteback");
        byte[] data = {1, 3, 5, 8, 9, 10, 2, 3, 0};
        subParam.SetAppData(data);
        subParam.SetFromNow(true);
        subParam.SetReplace(true);
        subParam.SetFilter("branch_no", branch_no.toString());
        subParam.SetFilter("fund_account", account);
        int iRet = subscriber.SubscribeTopic(subParam, 3000);
        //返回iRet>0表示成功
        logger.info("account :{} , branch_no:{} ,ReqSubOrderReturn:{}", account, branch_no, iRet);
    }
}
