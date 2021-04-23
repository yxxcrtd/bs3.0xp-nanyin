package com.joyintech.tams.exchange.terminal.trade.ufx.builder;

import com.hundsun.t2sdk.common.core.context.ContextUtil;
import com.hundsun.t2sdk.interfaces.share.dataset.IDataset;
import com.hundsun.t2sdk.interfaces.share.event.EventTagdef;
import com.hundsun.t2sdk.interfaces.share.event.EventType;
import com.hundsun.t2sdk.interfaces.share.event.IEvent;
import com.joyintech.tams.exchange.common.db.domain.Entrust;
import com.joyintech.tams.exchange.common.db.domain.LoginInfo;

public class OfficialUfxDataSetBuilder extends BaseUfxDataSetBuilder {
    @Override
    public IEvent genEvent(String funcCode, IDataset dataset) {
        IEvent event = ContextUtil.getServiceContext().getEventFactory()
                .getEventByAlias(funcCode, EventType.ET_REQUEST);
        event.setServiceId(funcCode); //打入功能号
        event.putEventData(dataset); //入参打包
        event.setIntegerAttributeValue(EventTagdef.TAG_SENDERID, senderId); //分配节点为5
        event.setIntegerAttributeValue(EventTagdef.TAG_EVENT_ID, eventId);
        event.setIntegerAttributeValue(EventTagdef.TAG_SUB_SYSTEM_NO, 1000);
        event.setIntegerAttributeValue("71", 91000);
        return event;
    }

    @Override
    public IEvent genSyncEvent(String funcCode, IDataset dataset) {
        IEvent event = ContextUtil.getServiceContext().getEventFactory()
                .getEventByAlias(funcCode, EventType.ET_REQUEST);
        event.setServiceId(funcCode); //打入功能号
        event.putEventData(dataset); //入参打包
        event.setIntegerAttributeValue(EventTagdef.TAG_SUB_SYSTEM_NO, 1000);
        event.setIntegerAttributeValue("71", 91000);
        return event;
    }

    @Override
    protected void entrustDataSetAddColumns(IDataset dataset) {
        super.entrustDataSetAddColumns(dataset);
        dataset.addColumn("user_token");
    }

    @Override
    protected void entrustDataSetSetValues(Entrust entrust, String password, LoginInfo loginInfo, IDataset dataset) {
        super.entrustDataSetSetValues(entrust, password, loginInfo, dataset);
        dataset.updateString("user_token", loginInfo.getUserToken());
    }
}
