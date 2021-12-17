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
import com.lmax.disruptor.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JSDMsgHandler implements EventHandler<MsgBean> {
    private static final Logger logger = LoggerFactory.getLogger(JSDMsgHandler.class);

    private JSDTradeApi jsdTradeApi;

    @Override
    public void onEvent(MsgBean msgBean, long l, boolean b) throws Exception {
        try {
            if (jsdTradeApi.msgTag == 0) {
                switch (msgBean.getMsgType()) {
                    case MsgType.REQ_LOGIN: {
                        jsdTradeApi.reqClientLogin((Account) msgBean.getMsg());
                        break;
                    }
                    case MsgType.REQ_ENTRUST: {
                        jsdTradeApi.reqQryEntrust((Account) msgBean.getMsg(), msgBean.getRequestId(), msgBean.getRespLoginHeader());
                        break;
                    }
                    case MsgType.REQ_TRADE: {
                        jsdTradeApi.reqQryTrade((Account) msgBean.getMsg(), msgBean.getRequestId(), msgBean.getRespLoginHeader());
                        break;
                    }
                    case MsgType.ENTRUST: {
                        jsdTradeApi.entrust((Entrust) msgBean.getEntrustMsg(), (Account) msgBean.getMsg(), msgBean.getRequestId(), msgBean.getRespLoginHeader(), msgBean.getDataMap());
                        break;
                    }
                    case MsgType.INTEGRATED_ENTRUST: {
                        jsdTradeApi.integratedEntrust((IntegratedEntrust) msgBean.getEntrustMsg(), msgBean.getRequestId(), (Account) msgBean.getMsg(), msgBean.getRespLoginHeader(), msgBean.getDataMap());
                        break;
                    }
                    case MsgType.CANCEL_ENTRUST: {
                        jsdTradeApi.cancelEntrust((CancelEntrustBean) msgBean.getEntrustMsg(), (Account) msgBean.getMsg(), msgBean.getRespLoginHeader());
                        break;
                    }
                    case MsgType.CANCEL_INTEGRATED_ENTRUST: {
                        jsdTradeApi.cancelIntegratedEntrust((CancelEntrustBean) msgBean.getEntrustMsg(), (Account) msgBean.getMsg(), msgBean.getRespLoginHeader());
                        break;
                    }
                    case MsgType.REQ_INTEGRATED_QUOTES: {
                        jsdTradeApi.queryIntegratedQuotes((QuoteQryBean) msgBean.getEntrustMsg(), msgBean.getRequestId(), (Account) msgBean.getMsg(), msgBean.getRespLoginHeader());
                        break;
                    }
                    case MsgType.REQ_ENABLE: {
                        jsdTradeApi.queryEnable((EnableQryBean) msgBean.getEntrustMsg(), msgBean.getRequestId(), (Account) msgBean.getMsg(), msgBean.getRespLoginHeader(), msgBean.getDataMap());
                        break;
                    }
                    case MsgType.REQ_HISTORY_TRADE: {
                        jsdTradeApi.queryHistoryTrade((HistoryQryBean) msgBean.getEntrustMsg(), msgBean.getRequestId(), (Account) msgBean.getMsg(), msgBean.getRespLoginHeader());
                        break;
                    }
                    case MsgType.REQ_CUSTOMER_FINANCE: {
                        jsdTradeApi.queryCustomerFinance(msgBean.getRequestId(), (Account) msgBean.getMsg(), msgBean.getRespLoginHeader());
                        break;
                    }
                    case MsgType.QRY_ENTRUST: {
                        jsdTradeApi.qryEntrust((Account) msgBean.getMsg(), msgBean.getRespLoginHeader());
                        break;
                    }
                    case MsgType.QRY_TRADE: {
                        jsdTradeApi.qryTrade((Account) msgBean.getMsg(), msgBean.getRespLoginHeader());
                        break;
                    }
                    case MsgType.QRY_INTEGRATED_ENTRUST: {
                        jsdTradeApi.qryIntegratedOrder((Account) msgBean.getMsg(), msgBean.getRespLoginHeader());
                        break;
                    }
                    case MsgType.QRY_INTEGRATED_TRADE: {
                        jsdTradeApi.qryProtocolBackTrade((Account) msgBean.getMsg(), msgBean.getRespLoginHeader());
                        break;
                    }
                    case MsgType.QRY_INTEGRATED_BULK_QUOTE: {
                        jsdTradeApi.queryIntegratedBulkQuote((IntegratedBulkQuoteQryBean) msgBean.getEntrustMsg(), msgBean.getRequestId(), (Account) msgBean.getMsg(), msgBean.getRespLoginHeader());
                        break;
                    }
                    case MsgType.QRY_INTEGRATED_BULK_CLOSE_QUOTE: {
                        jsdTradeApi.qryIntegratedBulkCloseQuote((IntegratedBulkCloseQuoteQryBean) msgBean.getEntrustMsg(), msgBean.getRequestId(), (Account) msgBean.getMsg(), msgBean.getRespLoginHeader());
                        break;
                    }
                    case MsgType.QRY_STANDARD_BONDS: {
                        jsdTradeApi.queryStandardBonds(msgBean.getRequestId(), (Account) msgBean.getMsg(), msgBean.getRespLoginHeader(), (StandardBondQryBean) msgBean.getEntrustMsg());
                        break;
                    }
                    case MsgType.QRY_ACCOUNT_POSITION: {
                        jsdTradeApi.queryAccountPosition((PositionQueryBean) msgBean.getEntrustMsg(), msgBean.getRequestId(), (Account) msgBean.getMsg(), msgBean.getRespLoginHeader(), msgBean.getDataMap());
                        break;
                    }
                    case MsgType.REQ_REISSUE_ENTRUST: {
                        jsdTradeApi.reqReissueEntrust(msgBean.getRequestId(), (Account) msgBean.getMsg(), msgBean.getRespLoginHeader());
                        break;
                    }
                    case MsgType.REQ_REISSUE_TRADE: {
                        jsdTradeApi.reqReissueTrade(msgBean.getRequestId(), (Account) msgBean.getMsg(), msgBean.getRespLoginHeader());
                        break;
                    }
                    default: {
                        logger.warn("unknown msg type. msgType:{} msg:{}", msgBean.getMsgType(),
                                JsonUtils.getJsonFromObject(msgBean.getMsg()));
                    }
                }
            }
        } catch (Exception e) {
            logger.warn("handle msg bean exception:{} ", e);
        }
    }

    public void setJsdTradeApi(JSDTradeApi jsdTradeApi) {
        this.jsdTradeApi = jsdTradeApi;
    }
}
