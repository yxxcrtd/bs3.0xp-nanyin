package com.joyintech.tams.exchange.terminal.trade.ufx;

import com.google.common.base.Strings;
import com.hundsun.t2sdk.interfaces.share.dataset.IDataset;
import com.joyintech.tams.exchange.common.constant.EntrustStatus;
import com.joyintech.tams.exchange.common.constant.TradeType;
import com.joyintech.tams.exchange.common.db.domain.Trade;
import com.joyintech.tams.exchange.common.utils.ErrorMsgUtils;
import com.joyintech.tams.exchange.common.utils.IntegratedEntrustUtils;
import com.joyintech.tams.exchange.common.utils.JsonUtils;
import com.joyintech.tams.exchange.terminal.bean.EntrustBean;
import com.joyintech.tams.exchange.terminal.bean.IntegratedEntrustBean;
import com.joyintech.tams.exchange.terminal.trade.TradeResponseImpl;
import com.joyintech.tams.exchange.terminal.trade.ufx.bean.TopicTypeBean;
import com.joyintech.tams.exchange.terminal.trade.ufx.constant.TradeConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * create By MAXX on 2020/4/7
 */
public class UfxMCHandler {
    private final static Logger logger = LoggerFactory.getLogger(UfxMCHandler.class);


    private UfxTradeApi ufxTradeApi;
    private TradeResponseImpl ufxTradeResponse;
    private TopicTypeBean topicTypeBean;

    public UfxMCHandler(UfxTradeApi ufxTradeApi, TradeResponseImpl ufxTradeResponse, TopicTypeBean topicTypeBean) {
        this.ufxTradeApi = ufxTradeApi;
        this.ufxTradeResponse = ufxTradeResponse;
        this.topicTypeBean = topicTypeBean;
    }

    public void mcReceived(String topicName, IDataset ds) {
        if (topicTypeBean.getTradeTopicName().equals(topicName)) {//证券成交回报
            //成交主题中区分竞价、大宗 ---- 大宗回报中缺少entrustProp字段， 暂时丢弃该回报信息，通过成交查询通知
            List<Trade>  tradeList = UfxConverter.dealReturn(ds, ufxTradeApi);
            tradeList.forEach(trade -> {
                if (Strings.isNullOrEmpty(trade.getEntrustProp())) {
                    logger.info("缺少entrustProp字段信息，不通知该成交信息 BrokerTradeId {} -- BrokerEntrustId {}", trade.getBrokerTradeId(), trade.getBrokerEntrustId());
                } else {
                    if (TradeType.isInvalid(trade.getTradeType())) {
                        EntrustBean entrust = new EntrustBean();
                        BeanUtils.copyProperties(trade, entrust);
                        entrust.setErrorMsg(ErrorMsgUtils.subErrorMsg(ds.getString("cancel_info")));
                        entrust.setId(trade.getEntrustId());
                        entrust.setEntrustStatus(EntrustStatus.INVALID.value);
                        logger.info("废单成交转换为委托通知  trade：[{}]  entrust: [{}]", JsonUtils.getJsonFromObject(trade), JsonUtils.getJsonFromObject(entrust));
                        ufxTradeResponse.entrustWriteBack(entrust);
                    } else {
                        ufxTradeResponse.dealReturn(trade);
                    }
                }
            });
        } else if (topicTypeBean.getEntrustTopicName().equals(topicName)) {//证券委托回报
            String entrustType = ds.getString("entrust_type");
            String entrustProp = ds.getString("entrust_prop");
            if (TradeConstant.entrustTypeBig.equals(entrustType) || IntegratedEntrustUtils.isIntegratedEntrust(entrustProp)) {
                IntegratedEntrustBean integratedEntrust = UfxConverter.integratedEntrustWriteBack(ds, ufxTradeApi);
                ufxTradeResponse.integratedEntrustWriteBack(integratedEntrust);
            } else {
                EntrustBean entrust = UfxConverter.entrustWriteBack(ds, ufxTradeApi);
                ufxTradeResponse.entrustWriteBack(entrust);
            }
        } else if (topicTypeBean.getIncomeTradeTopic().equals(topicName)) {
            // 推送缺少entrustProp字段 ---- 暂不支持推送
            // 该主题中包含委托和成交  --- 无 我们自身的委托id返回
            // 当real_status、real_type为空时，无法判断成交类型，则通过委托通知
//            if (Strings.isNullOrEmpty(realStatus) || Strings.isNullOrEmpty(realType)) {
//                IntegratedEntrustBean integratedEntrust = UfxConverter.integratedEntrustWriteBack(ds, ufxTradeApi);
//                ufxTradeResponse.integratedEntrustWriteBack(integratedEntrust);
//            }

        }
    }
}
