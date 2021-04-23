
package com.joyintech.tams.exchange.terminal.trade.ufx;

import com.google.common.collect.Maps;
import com.hundsun.t2sdk.impl.client.ClientSocket;
import com.hundsun.t2sdk.interfaces.ICallBackMethod;
import com.hundsun.t2sdk.interfaces.share.dataset.IDataset;
import com.hundsun.t2sdk.interfaces.share.dataset.IDatasets;
import com.hundsun.t2sdk.interfaces.share.event.EventTagdef;
import com.hundsun.t2sdk.interfaces.share.event.IEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class UfxTradeCallBack implements ICallBackMethod {
    private final static Logger logger = LoggerFactory.getLogger(UfxTradeCallBack.class);

    private static Map<Long, UfxTradeHandler> ufxTradeHandlerMap = Maps.newConcurrentMap();

    public static void setUfxTradeHandler(long eventId, UfxTradeHandler handler) {
        ufxTradeHandlerMap.put(eventId, handler);
    }

    public static StringBuilder getResult(IEvent arg0) {
        StringBuilder sb = new StringBuilder();
        IDatasets results = arg0.getEventDatas();
        int datasetCount_ = results.getDatasetCount();
        for (int i = 0; i < datasetCount_; i++) {
            IDataset ds = results.getDataset(i);
            int columnCount = ds.getColumnCount();
            for (int j = 1; j <= columnCount; j++) {
                sb.append(ds.getColumnName(j));
                sb.append("|");
            }
            ds.beforeFirst();
            while (ds.hasNext()) {
                sb.append("\n");
                ds.next();
                for (int j = 1; j <= columnCount; j++) {
                    sb.append(ds.getString(j)).append("|");
                }
            }
        }
        return sb;
    }

    @Override
    public void execute(IEvent arg0, ClientSocket arg1) {
        //获取功能号
        long iFunctionID = arg0.getIntegerAttributeValue(EventTagdef.TAG_FUNCTION_ID);
        long eventID = arg0.getIntegerAttributeValue(EventTagdef.TAG_EVENT_ID);
        UfxTradeHandler ufxTradeHandler = ufxTradeHandlerMap.get(eventID);
        if (ufxTradeHandler == null) {
            logger.warn("未知的 eventID: {}", eventID);
            return;
        }
        logger.info("callback functionCode : {} - {} ErrorInfo:{}", iFunctionID, getResult(arg0).toString(), arg0.getErrorInfo());
        IDatasets result = arg0.getEventDatas();
        if (iFunctionID == 331100) { //331100 登录回调解包
            ufxTradeHandler.onLogin(result, arg0.getReturnCode(), arg0.getErrorInfo());
        } else if (iFunctionID == 333002 || iFunctionID == 333012 || iFunctionID == 333013) {//委托下单
            logger.info("callback Timestamp:{} ", arg0.getTimestamp()[0]);
            String[] remark = arg0.getTimestamp()[0].split(" ");
            ufxTradeHandler.onOrderAck(result, arg0.getReturnCode(), arg0.getErrorInfo(), remark[0]);
            //        } else if (iFunctionID == 333101) {//证券委托查询
            //            TradeHandler.onOrderQueryResult(result, arg0.getReturnCode(), arg0.getErrorInfo());
        } else if (iFunctionID == 333017) {//委托撤单
            String[] remark = arg0.getTimestamp()[0].split(" ");
            ufxTradeHandler.onCancelOrder(result, arg0.getReturnCode(), arg0.getErrorInfo(), remark[0]);
            //        } else if (iFunctionID == 333102) {//证券成交查询
            //            TradeHandler.onTradeQueryResult(result, arg0.getReturnCode(), arg0.getErrorInfo());
            //        } else if (iFunctionID == 333104) {//证券持仓查询
            //            TradeHandler.onPositionQueryResult(result, arg0.getReturnCode(), arg0.getErrorInfo());
            //        } else if (iFunctionID == 332255) {//客户资金快速查询
            //            TradeHandler.onBalanceQueryResult(result, arg0.getReturnCode(), arg0.getErrorInfo());
        } else if (iFunctionID == 332702 || iFunctionID == 332722 || iFunctionID == 332400
                || iFunctionID == 332725 || iFunctionID == 332401 || iFunctionID == 332410) {//综合业务委托确认
            String[] remark = arg0.getTimestamp()[0].split(" ");
            ufxTradeHandler.onIntegratedEntrust(result, arg0.getReturnCode(), arg0.getErrorInfo(), remark[0]);
        } else if (iFunctionID == 332703 || iFunctionID == 332723 || iFunctionID == 332402 || iFunctionID == 332726) {//综合业务委托撤单
            String[] remark = arg0.getTimestamp()[0].split(" ");
            ufxTradeHandler.onCancelIntegratedEntrust(result, arg0.getReturnCode(), arg0.getErrorInfo(), remark[0]);
        } else {
            logger.info("unknown functionCode : {} - {}", iFunctionID, getResult(arg0).toString());
        }
    }
}
