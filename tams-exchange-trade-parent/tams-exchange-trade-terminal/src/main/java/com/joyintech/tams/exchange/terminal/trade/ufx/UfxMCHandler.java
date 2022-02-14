package com.joyintech.tams.exchange.terminal.trade.ufx;

import com.hundsun.t2sdk.interfaces.share.dataset.IDataset;
import com.joyintech.tams.exchange.common.constant.EntrustStatus;
import com.joyintech.tams.exchange.common.db.domain.Entrust;
import com.joyintech.tams.exchange.common.db.domain.EntrustInterface;
import com.joyintech.tams.exchange.common.db.domain.IntegratedEntrust;
import com.joyintech.tams.exchange.common.db.domain.Trade;
import com.joyintech.tams.exchange.common.utils.ErrorMsgUtils;
import com.joyintech.tams.exchange.common.utils.IntegratedEntrustUtils;
import com.joyintech.tams.exchange.common.utils.JsonUtils;
import com.joyintech.tams.exchange.terminal.trade.TradeResponseImpl;
import com.joyintech.tams.exchange.terminal.trade.ufx.constant.TopicType;
import com.joyintech.tams.exchange.terminal.trade.ufx.constant.TradeConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

/**
 * create By MAXX on 2020/4/7
 */
public class UfxMCHandler {
    private final static Logger logger = LoggerFactory.getLogger(UfxMCHandler.class);


    private UfxTradeApi ufxTradeApi;
    private TradeResponseImpl ufxTradeResponse;

    public UfxMCHandler(UfxTradeApi ufxTradeApi, TradeResponseImpl ufxTradeResponse) {
        this.ufxTradeApi = ufxTradeApi;
        this.ufxTradeResponse = ufxTradeResponse;
    }

    public void mcReceived(String topicName, IDataset ds) {
        String entrustType = ds.getString("entrust_type");
        String entrustProp = ds.getString("entrust_prop");
        if (TopicType.tradeTopic.equals(topicName)) {//证券成交回报
            int realStatus = ds.getInt("real_status");
            String realType = ds.getString("real_type");
            logger.info("real_status:{} ,brokerEntrustId:{},real_type:{},entrust_no:{},business_id:{}, entrustType {} entrust_prop {}",
                    realStatus, ds.getString("entrust_no"), realType, ds.getString("entrust_no"), ds.getString("business_id"), entrustType, entrustProp);
            Trade trade;
            if (TradeConstant.entrustTypeBig.equals(entrustType) || IntegratedEntrustUtils.isIntegratedEntrust(entrustProp)) {
                trade = UfxConverter.dealIntegratedTrade(ds, realStatus, ufxTradeApi);
            } else {
                trade = UfxConverter.dealReturn(ds, realStatus, realType, ufxTradeApi);
            }
            //废单 转换为委托通知
            if (TradeConstant.realStatusInvalid == realStatus) {
                EntrustInterface entrust;
                if (TradeConstant.entrustTypeBig.equals(entrustType) || IntegratedEntrustUtils.isIntegratedEntrust(entrustProp)) {
                    entrust = new IntegratedEntrust();
                } else {
                    entrust = new Entrust();
                }
                BeanUtils.copyProperties(trade, entrust);
                entrust.setErrorMsg(ErrorMsgUtils.subErrorMsg(ds.getString("cancel_info")));
                entrust.setId(trade.getEntrustId());
                entrust.setEntrustStatus(EntrustStatus.INVALID.value);
                logger.info("废单成交转换为委托通知  trade：[{}]  entrust: [{}]", JsonUtils.getJsonFromObject(trade), JsonUtils.getJsonFromObject(entrust));
                if (TradeConstant.entrustTypeBig.equals(entrustType) || IntegratedEntrustUtils.isIntegratedEntrust(entrustProp)) {
                    ufxTradeResponse.integratedEntrustWriteBack((IntegratedEntrust) entrust);
                } else {
                    ufxTradeResponse.entrustWriteBack((Entrust) entrust);
                }
            } else {
                ufxTradeResponse.dealReturn(trade);
            }
        } else if (TopicType.entrustTopic.equals(topicName)) {//证券委托回报
            if (TradeConstant.entrustTypeBig.equals(entrustType) || IntegratedEntrustUtils.isIntegratedEntrust(entrustProp)) {
                IntegratedEntrust integratedEntrust = UfxConverter.integratedEntrustWriteBack(ds, ufxTradeApi);
                ufxTradeResponse.integratedEntrustWriteBack(integratedEntrust);
            } else {
                Entrust entrust = UfxConverter.entrustWriteBack(ds, ufxTradeApi);
                ufxTradeResponse.entrustWriteBack(entrust);
            }
        }
    }
}
