package com.joyintech.tams.exchange.terminal.trade.jsd;

import com.joyintech.tams.exchange.common.bean.*;
import com.joyintech.tams.exchange.common.bean.bond.StandardBondQryBean;
import com.joyintech.tams.exchange.common.db.domain.Account;
import com.joyintech.tams.exchange.common.db.domain.CancelEntrustBean;
import com.joyintech.tams.exchange.common.db.domain.Entrust;
import com.joyintech.tams.exchange.common.db.domain.IntegratedEntrust;
import com.joyintech.tams.exchange.common.utils.JsonUtils;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.MsgBean;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.MsgType;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp.RespLoginData;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp.RespLoginHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class JSDMsgReceiver {

    private static final Logger logger = LoggerFactory.getLogger(JSDMsgReceiver.class);

    private JSDMsgHandler jsdMsgHandler;

    private BlockingQueue<MsgBean> msgQueue = new LinkedBlockingQueue<MsgBean>(1024 * 64);

    public void init() {
        Executors.newSingleThreadExecutor().submit(() -> {
            List<MsgBean> eventList = new ArrayList<>();
            while (true) {
                try {
                    MsgBean msgBean = msgQueue.poll(1, TimeUnit.MILLISECONDS);
                    if (Objects.nonNull(msgBean)) {
                        eventList.add(msgBean);
                        msgQueue.drainTo(eventList);
                        eventList.forEach(msgBean1 -> {
                            try {
                                jsdMsgHandler.onEvent(msgBean1, 0, false);
                            } catch (Exception e) {
                                logger.error("msgQueue exception:{}", e);
                            }
                        });
                        eventList.clear();
                    }
                } catch (Exception e) {
                    logger.error("msgQueue exception:{}", e);
                }
            }
        });
    }

    private void publish(int msgType, Object msg, Long requestId, RespLoginHeader respLoginHeader, Object entrustMsg, Map<Character, RespLoginData> dataMap) {
        MsgBean msgBean = new MsgBean();
        msgBean.setMsgType(msgType);
        msgBean.setMsg(msg);
        msgBean.setRequestId(requestId);
        msgBean.setRespLoginHeader(respLoginHeader);
        msgBean.setEntrustMsg(entrustMsg);
        msgBean.setDataMap(dataMap);
        boolean offer = msgQueue.offer(msgBean);
        if (!offer) {
            logger.error("publish msg msgType:{} msg:{} failed", msgType, JsonUtils.getJsonFromObject(msg));
        }
    }

    private void publish(int msgType, Object msg, Long requestId, RespLoginHeader respLoginHeader) {
        publish(msgType, msg, requestId, respLoginHeader, null, null);
    }

    private void publish(int msgType, Object msg, RespLoginHeader respLoginHeader) {
        publish(msgType, msg, null, respLoginHeader, null, null);
    }

    private void publish(int msgType, Object msg, Long requestId, RespLoginHeader respLoginHeader, Object entrustMsg) {
        publish(msgType, msg, requestId, respLoginHeader, entrustMsg, null);
    }

    private void publish(int msgType, Object msg) {
        publish(msgType, msg, null, null, null, null);
    }

    private void publish(int msgType, Object msg, RespLoginHeader respLoginHeader, Object entrustMsg) {
        publish(msgType, msg, null, respLoginHeader, entrustMsg, null);
    }

    public void reqLogin(Account account) {
        this.publish(MsgType.REQ_LOGIN, account);
    }

    public void reqEntrusts(Account account, Long requestId, RespLoginHeader respLoginHeader) {
        this.publish(MsgType.REQ_ENTRUST, account, requestId, respLoginHeader);
    }

    public void reqQryTrade(Account account, Long requestId, RespLoginHeader respLoginHeader) {
        this.publish(MsgType.REQ_TRADE, account, requestId, respLoginHeader);
    }

    public void entrust(Entrust entrust, Account account, Long requestId, RespLoginHeader respLoginHeader, Map<Character, RespLoginData> dataMap) {
        this.publish(MsgType.ENTRUST, account, requestId, respLoginHeader, entrust, dataMap);
    }

    public void integratedEntrust(IntegratedEntrust integratedEntrust, Long requestId, Account account, RespLoginHeader respLoginHeader, Map<Character, RespLoginData> dataMap) {
        this.publish(MsgType.INTEGRATED_ENTRUST, account, requestId, respLoginHeader, integratedEntrust, dataMap);
    }

    public void cancelEntrust(CancelEntrustBean cancelEntrustBean, Account account, RespLoginHeader respLoginHeader) {
        this.publish(MsgType.CANCEL_ENTRUST, account, respLoginHeader, cancelEntrustBean);
    }

    public void cancelIntegratedEntrust(CancelEntrustBean cancelEntrustBean, Account account, RespLoginHeader respLoginHeader) {
        this.publish(MsgType.CANCEL_INTEGRATED_ENTRUST, account, respLoginHeader, cancelEntrustBean);
    }

    public void queryIntegratedQuotes(QuoteQryBean quoteQryBean, Long requestId, Account account, RespLoginHeader respLoginHeader) {
        this.publish(MsgType.REQ_INTEGRATED_QUOTES, account, requestId, respLoginHeader, quoteQryBean);
    }

    public void queryEnable(EnableQryBean qryBean, Long requestId, Account account, RespLoginHeader respLoginHeader, Map<Character, RespLoginData> dataMap) {
        this.publish(MsgType.REQ_ENABLE, account, requestId, respLoginHeader, qryBean, dataMap);
    }

    public void queryHistoryTrade(HistoryQryBean historyQryBean, Long requestId, Account account, RespLoginHeader respLoginHeader) {
        this.publish(MsgType.REQ_HISTORY_TRADE, account, requestId, respLoginHeader, historyQryBean);
    }

    public void queryCustomerFinance(Long requestId, Account account, RespLoginHeader respLoginHeader) {
        this.publish(MsgType.REQ_CUSTOMER_FINANCE, account, requestId, respLoginHeader);
    }

    public void qryEntrust(Account account, RespLoginHeader respLoginHeader) {
        this.publish(MsgType.QRY_ENTRUST, account, respLoginHeader);
    }

    public void qryTrade(Account account, RespLoginHeader respLoginHeader) {
        this.publish(MsgType.QRY_TRADE, account, respLoginHeader);
    }

    public void qryIntegratedEntrust(Account account, RespLoginHeader respLoginHeader) {
        this.publish(MsgType.QRY_INTEGRATED_ENTRUST, account, respLoginHeader);
    }

    public void qryIntegratedTrade(Account account, RespLoginHeader respLoginHeader) {
        this.publish(MsgType.QRY_INTEGRATED_TRADE, account, respLoginHeader);
    }

    public void queryIntegratedBulkQuote(Long requestId, Account account, RespLoginHeader respLoginHeader, IntegratedBulkQuoteQryBean integratedBulkQuoteQryBean) {
        this.publish(MsgType.QRY_INTEGRATED_BULK_QUOTE, account, requestId, respLoginHeader, integratedBulkQuoteQryBean);
    }

    public void queryIntegratedBulkCloseQuote(Long requestId, Account account, RespLoginHeader respLoginHeader, IntegratedBulkCloseQuoteQryBean integratedBulkCloseQuoteQryBean) {
        this.publish(MsgType.QRY_INTEGRATED_BULK_CLOSE_QUOTE, account, requestId, respLoginHeader, integratedBulkCloseQuoteQryBean);
    }

    public void queryAccountPosition(Long requestId, Account account, RespLoginHeader respLoginHeader, PositionQueryBean positionQueryBean, Map<Character, RespLoginData> dataMap) {
        this.publish(MsgType.QRY_ACCOUNT_POSITION, account, requestId, respLoginHeader, positionQueryBean, dataMap);
    }

    public void setJsdMsgHandler(JSDMsgHandler jsdMsgHandler) {
        this.jsdMsgHandler = jsdMsgHandler;
    }

    public void queryStandardBonds(Long requestId, Account account, RespLoginHeader respLoginHeader, StandardBondQryBean standardBondQryBean) {
        this.publish(MsgType.QRY_STANDARD_BONDS, account, requestId, respLoginHeader, standardBondQryBean);
    }

    public void reqReissueEntrust(Long requestId, Account account, RespLoginHeader respLoginHeader) {
        this.publish(MsgType.REQ_REISSUE_ENTRUST, account, requestId, respLoginHeader);
    }

    public void reqReissueTrade(Long requestId, Account account, RespLoginHeader respLoginHeader) {
        this.publish(MsgType.REQ_REISSUE_TRADE, account, requestId, respLoginHeader);
    }
}

