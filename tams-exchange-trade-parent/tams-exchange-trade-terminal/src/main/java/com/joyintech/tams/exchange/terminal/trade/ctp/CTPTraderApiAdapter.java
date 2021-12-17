package com.joyintech.tams.exchange.terminal.trade.ctp;

import com.google.common.base.Strings;
import com.joyintech.tams.exchange.common.bean.FuturesPositionBean;
import com.joyintech.tams.exchange.common.bean.FuturesPositionDetailBean;
import com.joyintech.tams.exchange.common.constant.*;
import com.joyintech.tams.exchange.common.db.domain.Entrust;
import com.joyintech.tams.exchange.common.db.domain.FuturesInstrument;
import com.joyintech.tams.exchange.common.db.domain.Trade;
import com.joyintech.tams.exchange.common.utils.JsonUtils;
import com.joyintech.tams.exchange.common.utils.StockCodeUtils;
import com.joyintech.tams.exchange.terminal.agent.ITerminalMsgNotify;
import net.jctp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class CTPTraderApiAdapter implements TraderApiListener {
    private final static Logger logger = LoggerFactory.getLogger(CTPTraderApiAdapter.class);
    private ITerminalMsgNotify iTerminalMsgNotify;
    private CTPTradeClientImpl ctpTradeClientImpl;
    private boolean isDisconnected = false;
    private int loginErrorCount = 0;
    private Map<Long, List<Object>> responseCache = new ConcurrentHashMap<>();

    private boolean isErrorRspInfo(CThostFtdcRspInfoField pRspInfo, String prefix) {
        // 如果ErrorID != 0, 说明收到了错误的响应
        boolean ret = Objects.nonNull(pRspInfo) && (pRspInfo.ErrorID != 0);
        if (ret) {
            logger.error(prefix + " | {}", pRspInfo.ErrorMsg);
        }
        return ret;
    }


    public CTPTraderApiAdapter(ITerminalMsgNotify iTerminalMsgNotify, CTPTradeClientImpl client) {
        this.iTerminalMsgNotify = iTerminalMsgNotify;
        ctpTradeClientImpl = client;
    }

    public void OnFrontConnected() {
        String prefix = "rsp OnFrontConnected";
        if (isDisconnected && loginErrorCount < 3) {
            ctpTradeClientImpl.getCtpTradeApi().reAuthenticate();
        }
        logger.debug(prefix + " | OnFrontConnected");
    }

    public void OnFrontDisconnected(int var1) {
        String prefix = "rsp OnFrontDisconnected";
        isDisconnected = true;
        logger.error(prefix + " | OnFrontDisconnected: {}", var1);
    }

    public void OnHeartBeatWarning(int var1) {
        String prefix = "rsp OnHeartBeatWarning";
        logger.warn(prefix + " | OnHeartBeatWarning: {}", var1);
    }

    public void OnRspAuthenticate(CThostFtdcRspAuthenticateField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspAuthenticate";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
            if (isDisconnected && loginErrorCount < 3) {
                ctpTradeClientImpl.getCtpTradeApi().reLogin();
            }
        }
    }

    public void OnRspUserLogin(CThostFtdcRspUserLoginField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspUserLogin";
        if (Objects.nonNull(var1) && var2.ErrorID != 0) {
            loginErrorCount++;
        }
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            loginErrorCount = 0;
            isDisconnected = true;
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspUserLogout(CThostFtdcUserLogoutField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspUserLogout";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspUserPasswordUpdate(CThostFtdcUserPasswordUpdateField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspUserPasswordUpdate";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspTradingAccountPasswordUpdate(CThostFtdcTradingAccountPasswordUpdateField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspTradingAccountPasswordUpdate";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspUserAuthMethod(CThostFtdcRspUserAuthMethodField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspUserAuthMethod";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspGenUserCaptcha(CThostFtdcRspGenUserCaptchaField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspGenUserCaptcha";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspGenUserText(CThostFtdcRspGenUserTextField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspGenUserText";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspOrderInsert(CThostFtdcInputOrderField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspOrderInsert";
        //报单错误处理
        if (!Objects.isNull(var2) && (var2.ErrorID != 0) && !Objects.isNull(var1)) {
            this.ctpTradeClientImpl.getCtpTradeResponse().resEntrust(CTPConverter.convertInputOrderToEntrust(var1, var2, ctpTradeClientImpl.getEntrustIdManager(), this.ctpTradeClientImpl.getAccountId()));
        }
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspParkedOrderInsert(CThostFtdcParkedOrderField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspParkedOrderInsert";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspParkedOrderAction(CThostFtdcParkedOrderActionField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspParkedOrderAction";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspOrderAction(CThostFtdcInputOrderActionField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspOrderAction";
        if (!Objects.isNull(var2) && (var2.ErrorID != 0) && !Objects.isNull(var1)) {
            //撤单错误处理
            if (!Objects.isNull(var2) && (var2.ErrorID != 0) && !Objects.isNull(var1)) {
                iTerminalMsgNotify.onTrade(CTPConverter.convertInputOrderActionToTrade(var1, this.ctpTradeClientImpl.getAccountId()));
            }
        }
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQueryMaxOrderVolume(CThostFtdcQueryMaxOrderVolumeField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQueryMaxOrderVolume";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspSettlementInfoConfirm(CThostFtdcSettlementInfoConfirmField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspSettlementInfoConfirm";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspRemoveParkedOrder(CThostFtdcRemoveParkedOrderField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspRemoveParkedOrder";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspRemoveParkedOrderAction(CThostFtdcRemoveParkedOrderActionField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspRemoveParkedOrderAction";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspExecOrderInsert(CThostFtdcInputExecOrderField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspExecOrderInsert";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspExecOrderAction(CThostFtdcInputExecOrderActionField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspExecOrderAction";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspForQuoteInsert(CThostFtdcInputForQuoteField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspForQuoteInsert";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQuoteInsert(CThostFtdcInputQuoteField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQuoteInsert";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQuoteAction(CThostFtdcInputQuoteActionField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQuoteAction";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspBatchOrderAction(CThostFtdcInputBatchOrderActionField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspBatchOrderAction";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspOptionSelfCloseInsert(CThostFtdcInputOptionSelfCloseField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspOptionSelfCloseInsert";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspOptionSelfCloseAction(CThostFtdcInputOptionSelfCloseActionField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspOptionSelfCloseAction";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspCombActionInsert(CThostFtdcInputCombActionField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspCombActionInsert";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryOrder(CThostFtdcOrderField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryOrder";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            Long requestId = new Long(var3);
            List<Object> objects = responseCache.get(requestId);
            if (var4) {
                if (Objects.isNull(objects)) {
                    objects = new ArrayList<>();
                    objects.add(var1);
                } else {
                    objects.add(var1);
                    responseCache.remove(requestId);
                }
                List<Entrust> entrusts = new ArrayList<>();
                for (Object object : objects) {
                    Entrust entrust = CTPConverter.convertToEntrust((CThostFtdcOrderField) object, ctpTradeClientImpl.getEntrustIdManager(), this.ctpTradeClientImpl.getAccountId());
                    entrusts.add(entrust);
                }

                this.ctpTradeClientImpl.getCtpTradeResponse().resQueryEntrusts(entrusts, requestId);
            } else {
                if (Objects.isNull(objects)) {
                    objects = new ArrayList<>();
                    responseCache.put(requestId, objects);
                }
                objects.add(var1);
            }
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryTrade(CThostFtdcTradeField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryTrade";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            Long requestId = new Long(var3);
            List<Object> objects = responseCache.get(requestId);
            if (var4) {
                if (Objects.isNull(objects)) {
                    objects = new ArrayList<>();
                    objects.add(var1);
                } else {
                    objects.add(var1);
                    responseCache.remove(requestId);
                }
                List<Trade> trades = new ArrayList<>();
                for (Object object : objects) {
                    Trade trade = CTPConverter.convertToTrade((CThostFtdcTradeField) object, ctpTradeClientImpl.getEntrustIdManager(), this.ctpTradeClientImpl.getAccountId());
                    trades.add(trade);
                }
                this.ctpTradeClientImpl.getCtpTradeResponse().resQueryTrade(trades, requestId);
            } else {
                if (Objects.isNull(objects)) {
                    objects = new ArrayList<>();
                    responseCache.put(requestId, objects);
                }
                objects.add(var1);
            }
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryInvestorPosition(CThostFtdcInvestorPositionField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryInvestorPosition";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            Long requestId = new Long(var3);
            List<Object> objects = responseCache.get(requestId);
            if (var4) {
                if (Objects.isNull(objects)) {
                    objects = new ArrayList<>();
                    objects.add(var1);
                } else {
                    objects.add(var1);
                    responseCache.remove(requestId);
                }
                List<FuturesPositionBean> futuresPositionBeans = CTPConverter.convertToFuturesPosition(objects, this.ctpTradeClientImpl.getAccountId());
                this.ctpTradeClientImpl.getCtpTradeResponse().resQueryFuturesPosition(futuresPositionBeans, requestId);
            } else {
                if (Objects.isNull(objects)) {
                    objects = new ArrayList<>();
                    responseCache.put(requestId, objects);
                }
                objects.add(var1);
            }
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryTradingAccount(CThostFtdcTradingAccountField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryTradingAccount";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryInvestor(CThostFtdcInvestorField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryInvestor";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryTradingCode(CThostFtdcTradingCodeField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryTradingCode";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryInstrumentMarginRate(CThostFtdcInstrumentMarginRateField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryInstrumentMarginRate";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
           /* Long requestId = new Long(var3);
            List<Object> objects = responseCache.get(requestId);
            if (var4) {
                if (Objects.isNull(objects)) {
                    objects = new ArrayList<>();
                    objects.add(var1);
                } else {
                    objects.add(var1);
                    responseCache.remove(requestId);
                }
                this.ctpTradeClientImpl.getCtpTradeResponse().resQueryFuturesInstrumentMarginRate(CTPConverter.convertToFuturesInstrumentMarginRate(objects,ctpTradeClientImpl.getAccountId()), requestId);

            } else {
                if (Objects.isNull(objects)) {
                    objects = new ArrayList<>();
                    responseCache.put(requestId, objects);
                }
                objects.add(var1);
            }*/
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryInstrumentCommissionRate(CThostFtdcInstrumentCommissionRateField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryInstrumentCommissionRate";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            /*Long requestId = new Long(var3);
            List<Object> objects = responseCache.get(requestId);
            if (var4) {
                if (Objects.isNull(objects)) {
                    objects = new ArrayList<>();
                    objects.add(var1);
                } else {
                    objects.add(var1);
                    responseCache.remove(requestId);
                }
                this.ctpTradeClientImpl.getCtpTradeResponse().resQueryFuturesInstrumentCommissionRate(CTPConverter.convertToFuturesCommissionRate(objects,ctpTradeClientImpl.getAccountId()), requestId);

            } else {
                if (Objects.isNull(objects)) {
                    objects = new ArrayList<>();
                    responseCache.put(requestId, objects);
                }
                objects.add(var1);
            }*/
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryExchange(CThostFtdcExchangeField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryExchange";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryProduct(CThostFtdcProductField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryProduct";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryInstrument(CThostFtdcInstrumentField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryInstrument";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            Long requestId = new Long(var3);
            List<Object> objects = responseCache.get(requestId);
            if (var4) {
                if (Objects.isNull(objects)) {
                    objects = new ArrayList<>();
                    objects.add(var1);
                } else {
                    objects.add(var1);
                    responseCache.remove(requestId);
                }
                List<FuturesInstrument> futuresInstruments = CTPConverter.convertToFuturesInstrument(objects);
                this.ctpTradeClientImpl.getCtpTradeResponse().resQueryFuturesInstrument(futuresInstruments, requestId);
            } else {
                if (Objects.isNull(objects)) {
                    objects = new ArrayList<>();
                    responseCache.put(requestId, objects);
                }
                objects.add(var1);
            }
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryDepthMarketData(CThostFtdcDepthMarketDataField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryDepthMarketData";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQrySettlementInfo(CThostFtdcSettlementInfoField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQrySettlementInfo";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryTransferBank(CThostFtdcTransferBankField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryTransferBank";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryInvestorPositionDetail(CThostFtdcInvestorPositionDetailField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryInvestorPositionDetail";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            Long requestId = new Long(var3);
            List<Object> objects = responseCache.get(requestId);
            if (var4) {
                if (Objects.isNull(objects)) {
                    objects = new ArrayList<>();
                    objects.add(var1);
                } else {
                    objects.add(var1);
                    responseCache.remove(requestId);
                }
                List<FuturesPositionDetailBean> futuresPositionDetailBeans = CTPConverter.convertToFuturesPositionDetail(objects, this.ctpTradeClientImpl.getAccountId());
                this.ctpTradeClientImpl.getCtpTradeResponse().resQueryFuturesPositionDetail(futuresPositionDetailBeans, requestId);
            } else {
                if (Objects.isNull(objects)) {
                    objects = new ArrayList<>();
                    responseCache.put(requestId, objects);
                }
                objects.add(var1);
            }
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryNotice(CThostFtdcNoticeField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryNotice";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQrySettlementInfoConfirm(CThostFtdcSettlementInfoConfirmField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQrySettlementInfoConfirm";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryInvestorPositionCombineDetail(CThostFtdcInvestorPositionCombineDetailField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryInvestorPositionCombineDetail";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryCFMMCTradingAccountKey(CThostFtdcCFMMCTradingAccountKeyField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryCFMMCTradingAccountKey";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryEWarrantOffset(CThostFtdcEWarrantOffsetField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryEWarrantOffset";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryInvestorProductGroupMargin(CThostFtdcInvestorProductGroupMarginField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryInvestorProductGroupMargin";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryExchangeMarginRate(CThostFtdcExchangeMarginRateField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryExchangeMarginRate";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryExchangeMarginRateAdjust(CThostFtdcExchangeMarginRateAdjustField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryExchangeMarginRateAdjust";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryExchangeRate(CThostFtdcExchangeRateField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryExchangeRate";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQrySecAgentACIDMap(CThostFtdcSecAgentACIDMapField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQrySecAgentACIDMap";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryProductExchRate(CThostFtdcProductExchRateField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryProductExchRate";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryProductGroup(CThostFtdcProductGroupField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryProductGroup";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryMMInstrumentCommissionRate(CThostFtdcMMInstrumentCommissionRateField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryMMInstrumentCommissionRate";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryMMOptionInstrCommRate(CThostFtdcMMOptionInstrCommRateField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryMMOptionInstrCommRate";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryInstrumentOrderCommRate(CThostFtdcInstrumentOrderCommRateField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryInstrumentOrderCommRate";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQrySecAgentTradingAccount(CThostFtdcTradingAccountField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQrySecAgentTradingAccount";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQrySecAgentCheckMode(CThostFtdcSecAgentCheckModeField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQrySecAgentCheckMode";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQrySecAgentTradeInfo(CThostFtdcSecAgentTradeInfoField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQrySecAgentTradeInfo";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryOptionInstrTradeCost(CThostFtdcOptionInstrTradeCostField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryOptionInstrTradeCost";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryOptionInstrCommRate(CThostFtdcOptionInstrCommRateField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryOptionInstrCommRate";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryExecOrder(CThostFtdcExecOrderField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryExecOrder";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryForQuote(CThostFtdcForQuoteField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryForQuote";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryQuote(CThostFtdcQuoteField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryQuote";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryOptionSelfClose(CThostFtdcOptionSelfCloseField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryOptionSelfClose";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryInvestUnit(CThostFtdcInvestUnitField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryInvestUnit";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryCombInstrumentGuard(CThostFtdcCombInstrumentGuardField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryCombInstrumentGuard";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryCombAction(CThostFtdcCombActionField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryCombAction";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryTransferSerial(CThostFtdcTransferSerialField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryTransferSerial";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryAccountregister(CThostFtdcAccountregisterField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryAccountregister";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspError(CThostFtdcRspInfoField var1, int var2, boolean var3) {
        String prefix = "rsp OnRspError";
        this.isErrorRspInfo(var1, prefix);
    }

    public void OnRtnOrder(CThostFtdcOrderField var1) {
        String prefix = "rsp OnRtnOrder";
        if (Objects.nonNull(var1)) {
            logger.debug(prefix + " | {}", JsonUtils.getJsonFromObject(var1));
            Entrust entrust = CTPConverter.convertToEntrust(var1, ctpTradeClientImpl.getEntrustIdManager(), this.ctpTradeClientImpl.getAccountId());
            entrust.setErrorMsg(var1.StatusMsg);
            if ('0' == var1.OrderStatus) {///全部成交
                entrust.setEntrustStatus(EntrustStatus.SUCCEEDED.value);
            } else if ('1' == var1.OrderStatus) { ///部分成交还在队列中
                entrust.setEntrustStatus(EntrustStatus.PART_SUCCESS.value);
            } else if ('2' == var1.OrderStatus) { ///部分成交不在队列中
                entrust.setEntrustStatus(EntrustStatus.PART_SUCCESS.value);
            } else if ('3' == var1.OrderStatus) { ///未成交还在队列中 和撤单提交
                entrust.setEntrustStatus(EntrustStatus.REPORTED.value);
                if (!var1.OrderSysID.equals("") && var1.ActiveUserID.equals("")) {
                    ctpTradeClientImpl.getCtpTradeResponse().resEntrust(entrust);
                    ctpTradeClientImpl.getEntrustIdManager().setBrokeEntrustIdMapping(entrust.getAccountId(), entrust.getBrokerEntrustId(), entrust.getId());
                }
            } else if ('4' == var1.OrderStatus) { ///未成交不在队列中
                logger.warn("OrderStatus: {} is not process！", var1.OrderStatus);
            } else if ('5' == var1.OrderStatus) { ///撤单
                //如果是正常撤单则返回撤单成功
                if (var1.StatusMsg.equals("已撤单")) {
                    Trade trade = CTPConverter.convertEntrustToTrade(var1, ctpTradeClientImpl.getEntrustIdManager(), this.ctpTradeClientImpl.getAccountId());
                    trade.setEntrustId(entrust.getId());
                    trade.setEntrustType(EntrustType.CANCEL.value);
                    trade.setBrokerTradeId(var1.OrderSysID);
                    entrust.setErrorMsg(null);
                    entrust.setEntrustStatus(EntrustStatus.CANCELED.value);

                    iTerminalMsgNotify.onTrade(trade);
                } else {
                    //其他异常撤单，按照废单处理
                    entrust.setEntrustStatus(EntrustStatus.INVALID.value);
                    entrust.setErrorCode(ErrorCode.TERMINAL_RETURN_ERROR.value);
                }
            } else if ('a' == var1.OrderStatus) { ///未知
                entrust.setEntrustStatus(EntrustStatus.REPORTED.value);
                if (!var1.OrderSysID.equals("")) {
                    ctpTradeClientImpl.getCtpTradeResponse().resEntrust(entrust);
                }
            } else if ('b' == var1.OrderStatus) { ///尚未触发
                logger.warn("OrderStatus: {} is not process！", var1.OrderStatus);
            } else if ('c' == var1.OrderStatus) { ///已触发
                logger.warn("OrderStatus: {} is not process！", var1.OrderStatus);
            }
            iTerminalMsgNotify.onEntrust(entrust);

        }
    }

    public void OnRtnTrade(CThostFtdcTradeField var1) {
        String prefix = "rsp OnRtnTrade";
        if (Objects.nonNull(var1)) {
            Trade trade = CTPConverter.convertToTrade(var1, ctpTradeClientImpl.getEntrustIdManager(), this.ctpTradeClientImpl.getAccountId());
            if (Objects.nonNull(trade)) {
                iTerminalMsgNotify.onTrade(trade);
            }

            logger.debug(prefix + " | {}", JsonUtils.getJsonFromObject(var1));
        }
    }

    public void OnErrRtnOrderInsert(CThostFtdcInputOrderField var1, CThostFtdcRspInfoField var2) {
        String prefix = "rsp OnErrRtnOrderInsert";
        //报单错误处理
        if (!Objects.isNull(var2) && (var2.ErrorID != 0) && !Objects.isNull(var1)) {
            iTerminalMsgNotify.onEntrust(CTPConverter.convertInputOrderToEntrust(var1, var2, ctpTradeClientImpl.getEntrustIdManager(), this.ctpTradeClientImpl.getAccountId()));
        }

        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.error(prefix + " | {}", JsonUtils.getJsonFromObject(var1));
        }
    }

    public void OnErrRtnOrderAction(CThostFtdcOrderActionField var1, CThostFtdcRspInfoField var2) {
        String prefix = "rsp OnErrRtnOrderAction";
        //撤单错误处理
        if (!Objects.isNull(var2) && (var2.ErrorID != 0) && !Objects.isNull(var1)) {
            iTerminalMsgNotify.onTrade(CTPConverter.convertOrderActionToTrade(var1, this.ctpTradeClientImpl.getAccountId()));
        }
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.error(prefix + " | {}", JsonUtils.getJsonFromObject(var1));
        }
    }

    public void OnRtnInstrumentStatus(CThostFtdcInstrumentStatusField var1) {
        String prefix = "rsp OnRtnInstrumentStatus";
        if (Objects.nonNull(var1)) {
            logger.debug(prefix + " | {}", JsonUtils.getJsonFromObject(var1));
        }
    }

    public void OnRtnBulletin(CThostFtdcBulletinField var1) {
        String prefix = "rsp OnRtnBulletin";
        if (Objects.nonNull(var1)) {
            logger.debug(prefix + " | {}", JsonUtils.getJsonFromObject(var1));
        }
    }

    public void OnRtnTradingNotice(CThostFtdcTradingNoticeInfoField var1) {
        String prefix = "rsp OnRtnTradingNotice";
        if (Objects.nonNull(var1)) {
            logger.debug(prefix + " | {}", JsonUtils.getJsonFromObject(var1));
        }
    }

    public void OnRtnErrorConditionalOrder(CThostFtdcErrorConditionalOrderField var1) {
        String prefix = "rsp OnRtnErrorConditionalOrder";
        if (Objects.nonNull(var1)) {
            logger.debug(prefix + " | {}", JsonUtils.getJsonFromObject(var1));
        }
    }

    public void OnRtnExecOrder(CThostFtdcExecOrderField var1) {
        String prefix = "rsp OnRtnExecOrder";
        if (Objects.nonNull(var1)) {
            logger.debug(prefix + " | {}", JsonUtils.getJsonFromObject(var1));
        }
    }

    public void OnErrRtnExecOrderInsert(CThostFtdcInputExecOrderField var1, CThostFtdcRspInfoField var2) {
        String prefix = "rsp OnErrRtnExecOrderInsert";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {}", JsonUtils.getJsonFromObject(var1));
        }
    }

    public void OnErrRtnExecOrderAction(CThostFtdcExecOrderActionField var1, CThostFtdcRspInfoField var2) {
        String prefix = "rsp OnErrRtnExecOrderAction";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {}", JsonUtils.getJsonFromObject(var1));
        }
    }

    public void OnErrRtnForQuoteInsert(CThostFtdcInputForQuoteField var1, CThostFtdcRspInfoField var2) {
        String prefix = "rsp OnErrRtnForQuoteInsert";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {}", JsonUtils.getJsonFromObject(var1));
        }
    }

    public void OnRtnQuote(CThostFtdcQuoteField var1) {
        String prefix = "rsp OnRtnQuote";
        if (Objects.nonNull(var1)) {
            logger.debug(prefix + " | {}", JsonUtils.getJsonFromObject(var1));
        }
    }

    public void OnErrRtnQuoteInsert(CThostFtdcInputQuoteField var1, CThostFtdcRspInfoField var2) {
        String prefix = "rsp OnErrRtnQuoteInsert";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {}", JsonUtils.getJsonFromObject(var1));
        }
    }

    public void OnErrRtnQuoteAction(CThostFtdcQuoteActionField var1, CThostFtdcRspInfoField var2) {
        String prefix = "rsp OnErrRtnQuoteAction";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {}", JsonUtils.getJsonFromObject(var1));
        }
    }

    public void OnRtnForQuoteRsp(CThostFtdcForQuoteRspField var1) {
        String prefix = "rsp OnRtnForQuoteRsp";
        if (Objects.nonNull(var1)) {
            logger.debug(prefix + " | {}", JsonUtils.getJsonFromObject(var1));
        }
    }

    public void OnRtnCFMMCTradingAccountToken(CThostFtdcCFMMCTradingAccountTokenField var1) {
        String prefix = "rsp OnRtnCFMMCTradingAccountToken";
        if (Objects.nonNull(var1)) {
            logger.debug(prefix + " | {}", JsonUtils.getJsonFromObject(var1));
        }
    }

    public void OnErrRtnBatchOrderAction(CThostFtdcBatchOrderActionField var1, CThostFtdcRspInfoField var2) {
        String prefix = "rsp OnErrRtnBatchOrderAction";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {}", JsonUtils.getJsonFromObject(var1));
        }
    }

    public void OnRtnOptionSelfClose(CThostFtdcOptionSelfCloseField var1) {
        String prefix = "rsp OnRtnOptionSelfClose";
        if (Objects.nonNull(var1)) {
            logger.debug(prefix + " | {}", JsonUtils.getJsonFromObject(var1));
        }
    }

    public void OnErrRtnOptionSelfCloseInsert(CThostFtdcInputOptionSelfCloseField var1, CThostFtdcRspInfoField var2) {
        String prefix = "rsp OnErrRtnOptionSelfCloseInsert";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {}", JsonUtils.getJsonFromObject(var1));
        }
    }

    public void OnErrRtnOptionSelfCloseAction(CThostFtdcOptionSelfCloseActionField var1, CThostFtdcRspInfoField var2) {
        String prefix = "rsp OnErrRtnOptionSelfCloseAction";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {}", JsonUtils.getJsonFromObject(var1));
        }
    }

    public void OnRtnCombAction(CThostFtdcCombActionField var1) {
        String prefix = "rsp OnRtnCombAction";
        if (Objects.nonNull(var1)) {
            logger.debug(prefix + " | {}", JsonUtils.getJsonFromObject(var1));
        }
    }

    public void OnErrRtnCombActionInsert(CThostFtdcInputCombActionField var1, CThostFtdcRspInfoField var2) {
        String prefix = "rsp OnErrRtnCombActionInsert";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {}", JsonUtils.getJsonFromObject(var1));
        }
    }

    public void OnRspQryContractBank(CThostFtdcContractBankField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryContractBank";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryParkedOrder(CThostFtdcParkedOrderField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryParkedOrder";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryParkedOrderAction(CThostFtdcParkedOrderActionField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryParkedOrderAction";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryTradingNotice(CThostFtdcTradingNoticeField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryTradingNotice";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryBrokerTradingParams(CThostFtdcBrokerTradingParamsField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryBrokerTradingParams";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQryBrokerTradingAlgos(CThostFtdcBrokerTradingAlgosField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQryBrokerTradingAlgos";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQueryCFMMCTradingAccountToken(CThostFtdcQueryCFMMCTradingAccountTokenField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQueryCFMMCTradingAccountToken";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRtnFromBankToFutureByBank(CThostFtdcRspTransferField var1) {
        String prefix = "rsp OnRtnFromBankToFutureByBank";
        if (Objects.nonNull(var1)) {
            logger.debug(prefix + " | {}", JsonUtils.getJsonFromObject(var1));
        }
    }

    public void OnRtnFromFutureToBankByBank(CThostFtdcRspTransferField var1) {
        String prefix = "rsp OnRtnFromFutureToBankByBank";
        if (Objects.nonNull(var1)) {
            logger.debug(prefix + " | {}", JsonUtils.getJsonFromObject(var1));
        }
    }

    public void OnRtnRepealFromBankToFutureByBank(CThostFtdcRspRepealField var1) {
        String prefix = "rsp OnRtnRepealFromBankToFutureByBank";
        if (Objects.nonNull(var1)) {
            logger.debug(prefix + " | {}", JsonUtils.getJsonFromObject(var1));
        }
    }

    public void OnRtnRepealFromFutureToBankByBank(CThostFtdcRspRepealField var1) {
        String prefix = "rsp OnRtnRepealFromFutureToBankByBank";
        if (Objects.nonNull(var1)) {
            logger.debug(prefix + " | {}", JsonUtils.getJsonFromObject(var1));
        }
    }

    public void OnRtnFromBankToFutureByFuture(CThostFtdcRspTransferField var1) {
        String prefix = "rsp OnRtnFromBankToFutureByFuture";
        if (Objects.nonNull(var1)) {
            logger.debug(prefix + " | {}", JsonUtils.getJsonFromObject(var1));
        }
    }

    public void OnRtnFromFutureToBankByFuture(CThostFtdcRspTransferField var1) {
        String prefix = "rsp OnRtnFromFutureToBankByFuture";
        if (Objects.nonNull(var1)) {
            logger.debug(prefix + " | {}", JsonUtils.getJsonFromObject(var1));
        }
    }

    public void OnRtnRepealFromBankToFutureByFutureManual(CThostFtdcRspRepealField var1) {
        String prefix = "rsp OnRtnRepealFromBankToFutureByFutureManual";
        if (Objects.nonNull(var1)) {
            logger.debug(prefix + " | {}", JsonUtils.getJsonFromObject(var1));
        }
    }

    public void OnRtnRepealFromFutureToBankByFutureManual(CThostFtdcRspRepealField var1) {
        String prefix = "rsp OnRtnRepealFromFutureToBankByFutureManual";
        if (Objects.nonNull(var1)) {
            logger.debug(prefix + " | {}", JsonUtils.getJsonFromObject(var1));
        }
    }

    public void OnRtnQueryBankBalanceByFuture(CThostFtdcNotifyQueryAccountField var1) {
        String prefix = "rsp OnRtnQueryBankBalanceByFuture";
        if (Objects.nonNull(var1)) {
            logger.debug(prefix + " | {}", JsonUtils.getJsonFromObject(var1));
        }
    }

    public void OnErrRtnBankToFutureByFuture(CThostFtdcReqTransferField var1, CThostFtdcRspInfoField var2) {
        String prefix = "rsp OnErrRtnBankToFutureByFuture";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {}", JsonUtils.getJsonFromObject(var1));
        }
    }

    public void OnErrRtnFutureToBankByFuture(CThostFtdcReqTransferField var1, CThostFtdcRspInfoField var2) {
        String prefix = "rsp OnErrRtnFutureToBankByFuture";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {}", JsonUtils.getJsonFromObject(var1));
        }
    }

    public void OnErrRtnRepealBankToFutureByFutureManual(CThostFtdcReqRepealField var1, CThostFtdcRspInfoField var2) {
        String prefix = "rsp OnErrRtnRepealBankToFutureByFutureManual";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {}", JsonUtils.getJsonFromObject(var1));
        }
    }

    public void OnErrRtnRepealFutureToBankByFutureManual(CThostFtdcReqRepealField var1, CThostFtdcRspInfoField var2) {
        String prefix = "rsp OnErrRtnRepealFutureToBankByFutureManual";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {}", JsonUtils.getJsonFromObject(var1));
        }
    }

    public void OnErrRtnQueryBankBalanceByFuture(CThostFtdcReqQueryAccountField var1, CThostFtdcRspInfoField var2) {
        String prefix = "rsp OnErrRtnQueryBankBalanceByFuture";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {}", JsonUtils.getJsonFromObject(var1));
        }
    }

    public void OnRtnRepealFromBankToFutureByFuture(CThostFtdcRspRepealField var1) {
        String prefix = "rsp OnRtnRepealFromBankToFutureByFuture";
        if (Objects.nonNull(var1)) {
            logger.debug(prefix + " | {}", JsonUtils.getJsonFromObject(var1));
        }
    }

    public void OnRtnRepealFromFutureToBankByFuture(CThostFtdcRspRepealField var1) {
        String prefix = "rsp OnRtnRepealFromFutureToBankByFuture";
        if (Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1));
        }
    }

    public void OnRspFromBankToFutureByFuture(CThostFtdcReqTransferField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspFromBankToFutureByFuture";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspFromFutureToBankByFuture(CThostFtdcReqTransferField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspFromFutureToBankByFuture";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRspQueryBankAccountMoneyByFuture(CThostFtdcReqQueryAccountField var1, CThostFtdcRspInfoField var2, int var3, boolean var4) {
        String prefix = "rsp OnRspQueryBankAccountMoneyByFuture";
        if (!this.isErrorRspInfo(var2, prefix) && Objects.nonNull(var1)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(var1), var3, var4);
        }
    }

    public void OnRtnOpenAccountByBank(CThostFtdcOpenAccountField var1) {
        String prefix = "rsp OnRtnOpenAccountByBank";
        if (Objects.nonNull(var1)) {
            logger.debug(prefix + " | {}", JsonUtils.getJsonFromObject(var1));
        }
    }

    public void OnRtnCancelAccountByBank(CThostFtdcCancelAccountField var1) {
        String prefix = "rsp OnRtnCancelAccountByBank";
        if (Objects.nonNull(var1)) {
            logger.debug(prefix + " | {}", JsonUtils.getJsonFromObject(var1));
        }
    }

    public void OnRtnChangeAccountByBank(CThostFtdcChangeAccountField var1) {
        String prefix = "rsp OnRtnChangeAccountByBank";
        if (Objects.nonNull(var1)) {
            logger.debug(prefix + " | {}", JsonUtils.getJsonFromObject(var1));
        }
    }

}
