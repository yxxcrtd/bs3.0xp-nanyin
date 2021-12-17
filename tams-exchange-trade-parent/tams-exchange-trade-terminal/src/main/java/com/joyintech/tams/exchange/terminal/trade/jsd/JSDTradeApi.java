package com.joyintech.tams.exchange.terminal.trade.jsd;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.joyintech.tams.exchange.common.bean.*;
import com.joyintech.tams.exchange.common.bean.bond.StandardBondQryBean;
import com.joyintech.tams.exchange.common.constant.*;
import com.joyintech.tams.exchange.common.db.domain.*;
import com.joyintech.tams.exchange.common.utils.CbptransTypeUtils;
import com.joyintech.tams.exchange.common.utils.EntrustUtils;
import com.joyintech.tams.exchange.common.utils.StockCodeUtils;
import com.joyintech.tams.exchange.terminal.constant.AccountLoginStatus;
import com.joyintech.tams.exchange.terminal.trade.EntrustIdManager;
import com.joyintech.tams.exchange.terminal.trade.TradeResponseImpl;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.MessageManager;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.req.*;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp.*;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * create By MAXX on 2020/6/22
 */

public class JSDTradeApi {
    private Map<String, String> yybIdMap = Maps.newConcurrentMap();//financeAccount-yybId
    private volatile Map<String, PositionStr> positionStrMap = Maps.newConcurrentMap();//accountId-PositionStr

    private int connectTimeout;
    private String ip;
    private Integer port;
    private int delay;
    private final static Logger logger = LoggerFactory.getLogger(JSDTradeApi.class);
    private String splitStr = ";";
    private TradeResponseImpl tradeResponseImpl;

    private JSDTradeClientImpl jsdTradeClient;

    private JSDClient jsdClient;

    private MessageManager messageManager;

    private boolean hadInit = false;

    public String yybId;
    public String source;
    public String entrustType;
    public volatile int msgTag = 0;

    private AtomicInteger reqId = new AtomicInteger(0);
    private ReqHeader reqHeaderExtends;

    private Channel channel;
    private ChannelPromise channelPromise;
    private RespProtocol response;

    private volatile boolean isLogin = false;

    private AtomicInteger status = new AtomicInteger(AccountLoginStatus.NOT_LOGGED.value);

    private EntrustIdManager entrustIdManager;

    public JSDTradeApi() {
    }

    public JSDTradeClientImpl getJsdTradeClient() {
        return jsdTradeClient;
    }

    public void init() {
        jsdClient = new JSDClient();
        jsdClient.init(ip, port, delay, this, messageManager);
    }

    public void connect() {
        jsdClient.connect();
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void jsdChannelInactive() {
        // 如果连接成功，断开后自动重连
        this.isLogin = false;
        this.channel.close();
        if (hadInit) {
            logger.info("连接断开, 请求重连！！！");
            jsdClient.reconnect();
        }
    }

    public void jsdChannelActive() {
        hadInit = true;
    }

    public boolean hadInit() {
        return hadInit;
    }

    public void setStatus(String accountId, AccountLoginStatus status) {
        logger.info("account {} status set to: {}", accountId, status.msg);
        this.status.set(status.value);
    }

    public int getStatus(String accountId) {
        return status.get();
    }

    public RespProtocol write(ReqHeader reqHeader, long timeout) {
        if (Objects.isNull(channel))
            return new RespProtocol("-1", "server is not connected");
        reqHeader.reqId = reqId.incrementAndGet();
        reqHeader.source = this.source;
        reqHeaderExtends = reqHeader;
        channelPromise = channel.newPromise();
        messageManager.addMessage(reqHeader);
        String message = reqHeader.toString();
        logger.info("write message:{}", message);
        this.msgTag = 1;//发送
        channel.writeAndFlush(message);
        try {
            channelPromise.await(timeout, TimeUnit.MILLISECONDS);
            return response;
        } catch (Exception e) {
            logger.error("write message " + e.getMessage());
        }
        return null;
    }

    public void channelRead0(ChannelHandlerContext channelHandlerContext, RespProtocol s) {
        response = s;
        if (reqHeaderExtends.reqId == s.reqId) {
            channelPromise.setSuccess();
        }
    }

    /***
     * @Description: 登录
     * @Param: [account]
     * @return: void
     * @Author: MAXX
     * @Date: 2020/6/23 
     */
    public void reqClientLogin(Account account) {
        entrustType = "WSWT";//委托方式
        ReqAccountCheck reqAccountCheck = new ReqAccountCheck();
        reqAccountCheck.accountType = '0';
        reqAccountCheck.account = account.getFinanceAccount();
        reqAccountCheck.entrustType = entrustType;//委托方式
        try {
            RespProtocol respProtocol = write(reqAccountCheck, connectTimeout);
            if (Objects.nonNull(respProtocol) && (Objects.isNull(respProtocol.errorCode)
                    || (ErrorCode.SUCCESS.value + "").equals(respProtocol.errorCode))
                    && Objects.nonNull(respProtocol.header)) {
                RespAccountCheckHeader respAccountCheckHeader = (RespAccountCheckHeader) respProtocol.header;
                yybIdMap.put(account.getFinanceAccount(), respAccountCheckHeader.yybId);
                yybId = respAccountCheckHeader.yybId;//发起（源）营业部代码
                ReqLogin reqLogin = new ReqLogin();
                reqLogin.accountType = "0";//账户类型
                reqLogin.account = account.getFinanceAccount();
                reqLogin.password = account.getPwd();
                reqLogin.entrustType = entrustType;//委托方式
                respProtocol = write(reqLogin, connectTimeout);
                if (Objects.nonNull(respProtocol) && (Objects.isNull(respProtocol.errorCode)
                        || (ErrorCode.SUCCESS.value + "").equals(respProtocol.errorCode))
                        && Objects.nonNull(respProtocol.header)) {
                    RespLoginHeader respLoginHeader = (RespLoginHeader) respProtocol.header;
                    List<RespLoginData> dataList = ObjectToBeanUtils.objectToBean(respProtocol.dataMessages, RespLoginData.class);
                    if (Objects.nonNull(dataList) && dataList.size() > 0) {
                        logger.info("账号：{}登录成功", account.getId());
                        jsdTradeClient.addLoginDataMap(account.getId(), dataList);
                        jsdTradeClient.addLoginMap(account.getId(), respLoginHeader);
                        jsdTradeClient.addServerAccountMap(account.getId(), account);
                        jsdTradeClient.addFinanceAccountMap(account.getFinanceAccount(), account);
                        ResultBean resultBean = new ResultBean(ErrorCode.SUCCESS.value, "jsd reqLogin SUCCESS");
                        resultBean.setData(account);
                        isLogin = true;
                        setStatus(account.getId(), AccountLoginStatus.LOGGED);
                        tradeResponseImpl.resLogin(resultBean, account.getId());
                        return;
                    }
                    logger.info("账号：{}登录失败，dataList为null", account.getId());
                    tradeResponseImpl.resLogin(new ResultBean(ErrorCode.TERMINAL_API_ERROR.value, respProtocol.errorMsg), account.getId());
                    return;
                }
                tradeResponseImpl.resLogin(new ResultBean(ErrorCode.TERMINAL_API_ERROR.value, respProtocol.errorMsg), account.getId());
                return;
            }
            tradeResponseImpl.resLogin(new ResultBean(ErrorCode.TERMINAL_API_ERROR.value, respProtocol.errorMsg), account.getId());
        } catch (Exception e) {
            logger.error("reqClientLogin:{}", e.getMessage());
            tradeResponseImpl.resLogin(new ResultBean(ErrorCode.TERMINAL_API_ERROR.value, e.getMessage()), account.getId());
        }
    }

    private List<RespQryEntrustData> getEntrust(Account account, RespLoginHeader respLoginHeader) {
        List<RespQryEntrustData> res = null;
        ReqQryEntrust reqQryEntrust = new ReqQryEntrust();
        yybId = yybIdMap.get(account.getFinanceAccount());
        reqQryEntrust.accountNum = respLoginHeader.accountNum;
        reqQryEntrust.entrustType = entrustType;
        reqQryEntrust.orderType = '1';//0-市场代码、1-委托时间、2-委托时间倒序
        reqQryEntrust.market = ' ';
        try {
            RespProtocol respProtocol = write(reqQryEntrust, connectTimeout);
            if (Objects.nonNull(respProtocol) && (Objects.isNull(respProtocol.errorCode)
                    || (ErrorCode.SUCCESS.value + "").equals(respProtocol.errorCode))
                    && Objects.nonNull(respProtocol.header)) {
                res = ObjectToBeanUtils.objectToBean(respProtocol.dataMessages, RespQryEntrustData.class);
            }
        } catch (Exception e) {
            logger.error("reqQryEntrust:{}", e.getMessage());
        }
        return res;
    }

    public List<RespQryIntegratedOrder> getIntegrateEntrust(Account account, RespLoginHeader respLoginHeader) {
        List<RespQryIntegratedOrder> res = null;
        ReqQryIntegratedOrder reqQryIntegratedOrder = new ReqQryIntegratedOrder();
        yybId = yybIdMap.get(account.getFinanceAccount());
        reqQryIntegratedOrder.accountNum = respLoginHeader.accountNum;
        reqQryIntegratedOrder.orderType = '0';
        reqQryIntegratedOrder.market = ' ';
        reqQryIntegratedOrder.entrustType = entrustType;
        try {
            RespProtocol respProtocol = write(reqQryIntegratedOrder, connectTimeout);
            if (Objects.nonNull(respProtocol) && (Objects.isNull(respProtocol.errorCode)
                    || (ErrorCode.SUCCESS.value + "").equals(respProtocol.errorCode))
                    && Objects.nonNull(respProtocol.header)) {
                res = ObjectToBeanUtils.objectToBean(respProtocol.dataMessages, RespQryIntegratedOrder.class);
            }
        } catch (Exception e) {
            logger.error("qryIntegratedOrder:{}", e.getMessage());
        }
        return res;
    }

    public List<RespQryTradeData> getTrade(Account account, RespLoginHeader respLoginHeader) {
        List<RespQryTradeData> res = null;
        ReqQryTrade reqQryTrade = new ReqQryTrade();
        yybId = yybIdMap.get(account.getFinanceAccount());
        reqQryTrade.market = ' ';
        reqQryTrade.accountNum = respLoginHeader.accountNum;
        reqQryTrade.searchDirection = "1";
        reqQryTrade.queryMode = "0";
        reqQryTrade.queryTYpe = '1';
        try {
            RespProtocol respProtocol = write(reqQryTrade, connectTimeout);
            if (Objects.nonNull(respProtocol) && (Objects.isNull(respProtocol.errorCode)
                    || (ErrorCode.SUCCESS.value + "").equals(respProtocol.errorCode))
                    && Objects.nonNull(respProtocol.header)) {
                res = ObjectToBeanUtils.objectToBean(respProtocol.dataMessages, RespQryTradeData.class);
            }
        } catch (Exception e) {
            logger.error("qryTrade:{}", e.getMessage());
        }
        return res;
    }

    public List<RespQryProtocolBackTrade> getIntegrateTrade(Account account, RespLoginHeader respLoginHeader) {
        List<RespQryProtocolBackTrade> res = null;
        ReqQryProtocolBackTrade reqQryProtocolBackTrade = new ReqQryProtocolBackTrade();
        yybId = yybIdMap.get(account.getFinanceAccount());
        reqQryProtocolBackTrade.market = ' ';
        reqQryProtocolBackTrade.accountNum = respLoginHeader.accountNum;
        reqQryProtocolBackTrade.tradeStatusSet = "A";
        reqQryProtocolBackTrade.entrustType = entrustType;
        try {
            RespProtocol respProtocol = write(reqQryProtocolBackTrade, connectTimeout);
            if (Objects.nonNull(respProtocol) && (Objects.isNull(respProtocol.errorCode)
                    || (ErrorCode.SUCCESS.value + "").equals(respProtocol.errorCode))
                    && Objects.nonNull(respProtocol.header)) {
                res = ObjectToBeanUtils.objectToBean(respProtocol.dataMessages, RespQryProtocolBackTrade.class);
            }
        } catch (Exception e) {
            logger.error("qryIntegratedTrade:{}", e.getMessage());
        }
        return res;
    }

    public void reqReissueEntrust(Long requestId, Account account, RespLoginHeader respLoginHeader) {
        Map<String, EntrustInterface> entrustInterfaceMap = new HashMap<>();
        List<Entrust> entrustList = qryEntrust(account, respLoginHeader, true);
        List<IntegratedEntrust> integratedEntrustList = qryIntegratedOrder(account, respLoginHeader, true);

        entrustList.forEach(entrust -> {
            entrust.setId(entrustIdManager.getIdByBrokeEntrustId(entrust.getAccountId(), entrust.getBrokerEntrustId()));
            if (!Strings.isNullOrEmpty(entrust.getId())) {
                entrustInterfaceMap.put(entrust.getId(), entrust);
            }
        });
        integratedEntrustList.forEach(integratedEntrust -> {
            integratedEntrust.setId(entrustIdManager.getIdByBrokeEntrustId(integratedEntrust.getAccountId(), integratedEntrust.getBrokerEntrustId()));
            if (!Strings.isNullOrEmpty(integratedEntrust.getId())) {
                entrustInterfaceMap.put(integratedEntrust.getId(), integratedEntrust);
            }
        });
        tradeResponseImpl.resQueryReissueEntrust(account.getId(), entrustInterfaceMap, requestId);
    }

    public void reqReissueTrade(Long requestId, Account account, RespLoginHeader respLoginHeader) {
        List<Trade> tradeList = qryTrade(account, respLoginHeader, true);
        List<Trade> trades = qryProtocolBackTrade(account, respLoginHeader, true);
        tradeList.addAll(trades);
        tradeResponseImpl.resQueryReissueTrade(account.getId(), tradeList, requestId);
    }

    /***
     * @Description: 证券委托查询
     * @Param: [account, requestId, respLoginHeader]
     * @return: void
     * @Author: MAXX
     * @Date: 2020/6/23
     */
    public void reqQryEntrust(Account account, Long requestId, RespLoginHeader respLoginHeader) {
        List<RespQryEntrustData> entrustDataList = getEntrust(account, respLoginHeader);
        if (Objects.nonNull(entrustDataList)) {
            logger.info("reqQryEntrust list:{}", JSONArray.toJSONString(entrustDataList));
            List<Entrust> entrustList = Lists.newArrayListWithCapacity(entrustDataList.size());
            JSDConverter.getEntrustList(account, entrustDataList, entrustList);
            entrustList.forEach(ent -> {
                ent.setId(entrustIdManager.getIdByBrokeEntrustId(ent.getAccountId(), ent.getBrokerEntrustId()));
            });
            logger.info("entrustList:{}", JSONArray.toJSONString(entrustList));
            tradeResponseImpl.resQueryEntrusts(entrustList, requestId);
        } else {
            tradeResponseImpl.resQueryEntrusts(null, requestId);
        }
    }

    /*** 
     * @Description: 证券成交查询
     * @Param: [account, requestId, respLoginHeader] 
     * @return: void
     * @Author: MAXX
     * @Date: 2020/6/28 
     */
    public void reqQryTrade(Account account, Long requestId, RespLoginHeader respLoginHeader) {
        List<RespQryTradeData> respQryTradeDataList = getTrade(account, respLoginHeader);
        if (Objects.nonNull(respQryTradeDataList)) {
            logger.info("reqQryTrade list:{}", JSONArray.toJSONString(respQryTradeDataList));
            List<Trade> tradeList = Lists.newArrayListWithCapacity(respQryTradeDataList.size());
            JSDConverter.getTradeList(account, respQryTradeDataList, tradeList);
            logger.info("tradeList:{}", JSONArray.toJSONString(tradeList));
            tradeResponseImpl.resQueryTrade(tradeList, requestId);
        } else {
            tradeResponseImpl.resQueryTrade(null, requestId);
        }
    }

    /*** 
     * @Description: 普通委托
     * @Param: [entrust, account, requestId, respLoginHeader] 
     * @return: void
     * @Author: MAXX
     * @Date: 2020/6/28 
     */
    public void entrust(Entrust entrust, Account account, Long requestId, RespLoginHeader respLoginHeader, Map<Character, RespLoginData> dataMap) {
        if (EntrustProp.ETF_APPLY.value.equals(entrust.getEntrustProp())) {
            etfEntrust(entrust, account, requestId, respLoginHeader, dataMap);//ETF申赎
            return;
        }
        ReqEntrust reqEntrust = new ReqEntrust();
        yybId = yybIdMap.get(account.getFinanceAccount());
        if (Market.SZSE.value.equals(entrust.getMarket())) {
            reqEntrust.market = '2';
        } else if (Market.SHSE.value.equals(entrust.getMarket())) {
            reqEntrust.market = '1';
        }
        reqEntrust.gdAccount = dataMap.get(reqEntrust.market).gddm;
        String stockCode = StockCodeUtils.getStockCode(entrust.getStockCode());
        reqEntrust.entrustCode = stockCode;
        String direction = JSDConverter.getDirection(entrust.getEntrustProp(), entrust.getEntrustBs(), entrust.getMarket());
        if (!"".equals(direction)) {
            reqEntrust.direction = direction;
        }
        if (EntrustUtils.isSHZQ(entrust.getMarket(), entrust.getAssetType(), entrust.getStockCode())) {
            reqEntrust.volume = entrust.getEntrustVolume() / 10;
        } else {
            reqEntrust.volume = entrust.getEntrustVolume();
        }
        reqEntrust.price = entrust.getEntrustPrice();
        reqEntrust.entrustType = entrustType;
        reqEntrust.entrustFlag = '0';
        try {
            RespProtocol respProtocol = write(reqEntrust, connectTimeout);
            if (Objects.nonNull(respProtocol) && (Objects.isNull(respProtocol.errorCode)
                    || (ErrorCode.SUCCESS.value + "").equals(respProtocol.errorCode))
                    && Objects.nonNull(respProtocol.header)) {
                RespEntrustData respEntrustData = (RespEntrustData) respProtocol.header;
                entrust.setBrokerEntrustId(respEntrustData.entrustBatch + "");
                entrustIdManager.setBrokeEntrustIdMapping(entrust.getAccountId(), entrust.getBrokerEntrustId(), entrust.getId());
                tradeResponseImpl.resEntrust(entrust);
                return;
            }
            entrust.setErrorCode(ErrorCode.TERMINAL_API_ERROR.value);
            entrust.setEntrustStatus(EntrustStatus.INVALID.value);
            entrust.setErrorMsg(respProtocol.errorMsg);
            tradeResponseImpl.resEntrust(entrust);
        } catch (Exception e) {
            logger.error("entrust:{}", e.getMessage());
            entrust.setErrorCode(ErrorCode.TERMINAL_API_ERROR.value);
            entrust.setEntrustStatus(EntrustStatus.INVALID.value);
            entrust.setErrorMsg(e.getMessage());
            tradeResponseImpl.resEntrust(entrust);
        }
    }

    /***
     * @Description: ETF申赎
     * @Param: [entrust, account, requestId, respLoginHeader, dataMap]
     * @return: void
     * @Author: MAXX
     * @Date: 2020/11/19
     */
    private void etfEntrust(Entrust entrust, Account account, Long requestId, RespLoginHeader respLoginHeader, Map<Character, RespLoginData> dataMap) {
        ReqETFEntrust reqETFEntrust = new ReqETFEntrust();
        yybId = yybIdMap.get(account.getFinanceAccount());
        if (Market.SZSE.value.equals(entrust.getMarket())) {
            reqETFEntrust.market = '2';
        } else if (Market.SHSE.value.equals(entrust.getMarket())) {
            reqETFEntrust.market = '1';
        }
        reqETFEntrust.accountNum = respLoginHeader.accountNum;
        reqETFEntrust.gdAccount = dataMap.get(reqETFEntrust.market).gddm;
        String stockCode = StockCodeUtils.getStockCode(entrust.getStockCode());
        reqETFEntrust.entrustCode = stockCode;
        if (EntrustBs.BUY.value.equals(entrust.getEntrustBs())) {
            reqETFEntrust.entrustBs = '1';
        } else if (EntrustBs.SELL.value.equals(entrust.getEntrustBs())) {
            reqETFEntrust.entrustBs = '2';
        }
        reqETFEntrust.volume = entrust.getEntrustVolume();
        reqETFEntrust.entrustType = entrustType;
        try {
            RespProtocol respProtocol = write(reqETFEntrust, connectTimeout);
            if (Objects.nonNull(respProtocol) && (Objects.isNull(respProtocol.errorCode)
                    || (ErrorCode.SUCCESS.value + "").equals(respProtocol.errorCode))
                    && Objects.nonNull(respProtocol.header)) {
                RespETFEntrustData respETFEntrustData = (RespETFEntrustData) respProtocol.header;
                entrust.setBrokerEntrustId(respETFEntrustData.entrustId + "");
                entrustIdManager.setBrokeEntrustIdMapping(entrust.getAccountId(), entrust.getBrokerEntrustId(), entrust.getId());
                tradeResponseImpl.resEntrust(entrust);
                return;
            }
            entrust.setErrorCode(ErrorCode.TERMINAL_API_ERROR.value);
            entrust.setEntrustStatus(EntrustStatus.INVALID.value);
            entrust.setErrorMsg(respProtocol.errorMsg);
            tradeResponseImpl.resEntrust(entrust);
        } catch (Exception e) {
            logger.error("etf entrust:{}", e.getMessage());
            entrust.setErrorCode(ErrorCode.TERMINAL_API_ERROR.value);
            entrust.setEntrustStatus(EntrustStatus.INVALID.value);
            entrust.setErrorMsg(e.getMessage());
            tradeResponseImpl.resEntrust(entrust);
        }
    }

    /*** 
     * @Description: 综合业务委托确认 固收 债券质押式协议回购 
     * @Param: [integratedEntrust, requestId, account, respLoginHeader, dataMap] 
     * @return: void
     * @Author: MAXX
     * @Date: 2020/6/30 
     */
    public void integratedEntrust(IntegratedEntrust integratedEntrust, Long requestId, Account account, RespLoginHeader respLoginHeader, Map<Character, RespLoginData> dataMap) {
        String entrustSubBusiness = JSDConverter.getEntrustSubBusiness(integratedEntrust.getEntrustProp());
        /*if (!EntrustChildBusiness.SET_PRICE.value.equals(entrustSubBusiness)
                && !EntrustChildBusiness.INTENTION.value.equals(entrustSubBusiness)
                && !EntrustChildBusiness.MUTUAL_TRADE.value.equals(entrustSubBusiness)
                && !EntrustChildBusiness.CONFIRM.value.equals(entrustSubBusiness)
                && !EntrustChildBusiness.LARGE_AFTER_CLOSING_PRICE.value.equals(entrustSubBusiness)
                && !EntrustChildBusiness.LARGE_AFTER_CLOSING_WEIGHT_PRICE.value.equals(entrustSubBusiness)
                && !EntrustChildBusiness.SH_FIXEDINCOME_BAOJIA_OPPOSITE.value.equals(entrustSubBusiness)) {

            integratedEntrust.setErrorCode(ErrorCode.TERMINAL_UN_SUPPORT_FUNCTION.value);
            integratedEntrust.setEntrustStatus(EntrustStatus.INVALID.value);
            integratedEntrust.setErrorMsg(ErrorCode.TERMINAL_UN_SUPPORT_FUNCTION.errorMsg);
            tradeResponseImpl.resIntegratedEntrust(integratedEntrust);
            return;

        }*/
        ReqIntegratedEntrust reqIntegratedEntrust = new ReqIntegratedEntrust();
        yybId = yybIdMap.get(account.getFinanceAccount());
        if (Market.SZSE.value.equals(integratedEntrust.getMarket())) {
            reqIntegratedEntrust.market = '2';
        } else if (Market.SHSE.value.equals(integratedEntrust.getMarket())) {
            reqIntegratedEntrust.market = '1';
        }
        reqIntegratedEntrust.gdAccount = dataMap.get(reqIntegratedEntrust.market).gddm;
        reqIntegratedEntrust.accountNum = respLoginHeader.accountNum;
        String stockCode = StockCodeUtils.getStockCode(integratedEntrust.getStockCode());
        reqIntegratedEntrust.entrustCode = stockCode;
        String direction = JSDConverter.getDirection(integratedEntrust.getEntrustProp(), integratedEntrust.getEntrustBs(), integratedEntrust.getMarket());
        if (!"".equals(direction)) {
            reqIntegratedEntrust.direction = direction;
        }
        if (EntrustUtils.isSHZQ(integratedEntrust.getMarket(), integratedEntrust.getAssetType(), integratedEntrust.getStockCode())) {
            reqIntegratedEntrust.volume = (int) (integratedEntrust.getEntrustVolume() / 10);
        } else {
            reqIntegratedEntrust.volume = (int) integratedEntrust.getEntrustVolume();
        }
        reqIntegratedEntrust.price = integratedEntrust.getEntrustPrice();
        reqIntegratedEntrust.entrustType = entrustType;
        reqIntegratedEntrust.entrustSubBusiness = entrustSubBusiness;
        if (EntrustChildBusiness.CONFIRM.value.equals(entrustSubBusiness)) {
            reqIntegratedEntrust.appointNum = integratedEntrust.getContractId();
            reqIntegratedEntrust.dfjyUnit = integratedEntrust.getPropSeatNo();
        }
        reqIntegratedEntrust.entrustFlag = '0';
        if (EntrustChildBusiness.INTENTION.value.equals(entrustSubBusiness)) {
            reqIntegratedEntrust.relationName = integratedEntrust.getLiaisonName();
            reqIntegratedEntrust.relationTel = integratedEntrust.getLiaisonTel();
        }
        if (EntrustChildBusiness.SH_FIXEDINCOME_BAOJIA_OPPOSITE.value.equals(entrustSubBusiness)) {
            reqIntegratedEntrust.appointNum = integratedEntrust.getContractId();
            reqIntegratedEntrust.ourTrader = integratedEntrust.getLiaisonTel();
            reqIntegratedEntrust.counterpartyTrader = integratedEntrust.getLiaisonName();
        }
        try {
            RespProtocol respProtocol = write(reqIntegratedEntrust, connectTimeout);
            if (Objects.nonNull(respProtocol) && (Objects.isNull(respProtocol.errorCode)
                    || (ErrorCode.SUCCESS.value + "").equals(respProtocol.errorCode))
                    && Objects.nonNull(respProtocol.header)) {
                RespIntegratedEntrustHeader respIntegratedEntrustHeader = (RespIntegratedEntrustHeader) respProtocol.header;
                integratedEntrust.setBrokerEntrustId(respIntegratedEntrustHeader.entrustBatch + "");
                entrustIdManager.setBrokeEntrustIdMapping(integratedEntrust.getAccountId(), integratedEntrust.getBrokerEntrustId(), integratedEntrust.getId());
                tradeResponseImpl.resIntegratedEntrust(integratedEntrust);
                return;
            }
            integratedEntrust.setErrorCode(ErrorCode.TERMINAL_API_ERROR.value);
            integratedEntrust.setEntrustStatus(EntrustStatus.INVALID.value);
            integratedEntrust.setErrorMsg(respProtocol.errorMsg);
            tradeResponseImpl.resIntegratedEntrust(integratedEntrust);
        } catch (Exception e) {
            logger.error("integratedEntrust:{}", e.getMessage());
            integratedEntrust.setErrorCode(ErrorCode.TERMINAL_API_ERROR.value);
            integratedEntrust.setEntrustStatus(EntrustStatus.INVALID.value);
            integratedEntrust.setErrorMsg(e.getMessage());
            tradeResponseImpl.resIntegratedEntrust(integratedEntrust);
        }
    }

    /***
     * @Description: 委托撤单
     * @Param: [entrust, account, respLoginHeader]
     * @return: void
     * @Author: MAXX
     * @Date: 2020/6/30 
     */
    public void cancelEntrust(CancelEntrustBean cancelEntrustBean, Account account, RespLoginHeader respLoginHeader) {
        ReqCancelEntrust reqCancelEntrust = new ReqCancelEntrust();
        yybId = yybIdMap.get(account.getFinanceAccount());
        if (Market.SZSE.value.equals(cancelEntrustBean.getMarket())) {
            reqCancelEntrust.market = '2';
        } else if (Market.SHSE.value.equals(cancelEntrustBean.getMarket())) {
            reqCancelEntrust.market = '1';
        }
        reqCancelEntrust.accountNum = respLoginHeader.accountNum;
        reqCancelEntrust.contractId = Integer.parseInt(cancelEntrustBean.getBrokerEntrustId());
        reqCancelEntrust.entrustType = entrustType;
        cancelEntrustBean.setAgentType(AgentType.JSD.value);
        try {
            RespProtocol respProtocol = write(reqCancelEntrust, connectTimeout);
            if (Objects.nonNull(respProtocol) && (Objects.isNull(respProtocol.errorCode)
                    || (ErrorCode.SUCCESS.value + "").equals(respProtocol.errorCode))
                    && Objects.nonNull(respProtocol.header)) {
                cancelEntrustBean.setCancelBrokerEntrustId(cancelEntrustBean.getCancelId());
                tradeResponseImpl.resCancelEntrust(cancelEntrustBean);
                return;
            }
            cancelEntrustBean.setErrorCode(ErrorCode.TERMINAL_API_ERROR.value);
            cancelEntrustBean.setEntrustStatus(EntrustStatus.INVALID.value);
            cancelEntrustBean.setErrorMsg(respProtocol.errorMsg);
            tradeResponseImpl.resCancelEntrust(cancelEntrustBean);
        } catch (Exception e) {
            logger.error("cancelEntrust:{}", e.getMessage());
            cancelEntrustBean.setErrorCode(ErrorCode.TERMINAL_API_ERROR.value);
            cancelEntrustBean.setEntrustStatus(EntrustStatus.INVALID.value);
            cancelEntrustBean.setErrorMsg(e.getMessage());
            tradeResponseImpl.resCancelEntrust(cancelEntrustBean);
        }
    }

    /***
     * @Description: 综合业务委托撤单
     * @Param: [integratedEntrust, account, respLoginHeader]
     * @return: void
     * @Author: MAXX
     * @Date: 2020/6/30
     */
    public void cancelIntegratedEntrust(CancelEntrustBean cancelEntrustBean, Account account, RespLoginHeader respLoginHeader) {
        cancelEntrustBean.setAgentType(AgentType.JSD.value);
        ReqCancelIntegratedEntrust reqCancelIntegratedEntrust = new ReqCancelIntegratedEntrust();
        yybId = yybIdMap.get(account.getFinanceAccount());
        if (Market.SZSE.value.equals(cancelEntrustBean.getMarket())) {
            reqCancelIntegratedEntrust.market = '2';
        } else if (Market.SHSE.value.equals(cancelEntrustBean.getMarket())) {
            reqCancelIntegratedEntrust.market = '1';
        }
        reqCancelIntegratedEntrust.accountNum = respLoginHeader.accountNum;
        reqCancelIntegratedEntrust.contractId = Integer.parseInt(cancelEntrustBean.getBrokerEntrustId());
        reqCancelIntegratedEntrust.entrustType = entrustType;
        try {
            RespProtocol respProtocol = write(reqCancelIntegratedEntrust, connectTimeout);
            if (Objects.nonNull(respProtocol) && (Objects.isNull(respProtocol.errorCode)
                    || (ErrorCode.SUCCESS.value + "").equals(respProtocol.errorCode))
                    && Objects.nonNull(respProtocol.header)) {
                cancelEntrustBean.setCancelBrokerEntrustId(cancelEntrustBean.getCancelId());
                tradeResponseImpl.resCancelIntegratedEntrust(cancelEntrustBean);
                return;
            }
            cancelEntrustBean.setEntrustStatus(EntrustStatus.INVALID.value);
            cancelEntrustBean.setErrorCode(ErrorCode.TERMINAL_API_ERROR.value);
            cancelEntrustBean.setErrorMsg(respProtocol.errorMsg);
            tradeResponseImpl.resCancelIntegratedEntrust(cancelEntrustBean);
        } catch (Exception e) {
            logger.error("cancelIntegratedEntrust:{}", e.getMessage());
            cancelEntrustBean.setEntrustStatus(EntrustStatus.INVALID.value);
            cancelEntrustBean.setErrorCode(ErrorCode.TERMINAL_API_ERROR.value);
            cancelEntrustBean.setErrorMsg(e.getMessage());
            tradeResponseImpl.resCancelIntegratedEntrust(cancelEntrustBean);
        }
    }

    /*** 
     * @Description: 大宗交易行情查询
     * @Param: [quoteQryBean, requestId, account, respLoginHeader]
     * @return: void
     * @Author: MAXX
     * @Date: 2020/7/2
     */
    public void queryIntegratedQuotes(QuoteQryBean quoteQryBean, Long requestId, Account account, RespLoginHeader respLoginHeader) {
        logger.info("quoteType:{}", quoteQryBean.getQuoteType());
        if (QuoteType.BULK_QUOTE.value == quoteQryBean.getQuoteType()) {//大宗行情
            ReqQryIntegratedQuotes reqQryIntegratedQuotes = new ReqQryIntegratedQuotes();
            yybId = yybIdMap.get(account.getFinanceAccount());
            String[] marketAndStockCode = StockCodeUtils.getMarketAndStockCode(quoteQryBean.getStockCode());
            if (Market.SZSE.value.equals(marketAndStockCode[1])) {
                reqQryIntegratedQuotes.market = '2';
            } else if (Market.SHSE.value.equals(marketAndStockCode[1])) {
                reqQryIntegratedQuotes.market = '1';
            }
            if (Objects.nonNull(marketAndStockCode)) {
                reqQryIntegratedQuotes.stockCode = marketAndStockCode[0];
            }
            reqQryIntegratedQuotes.accountNum = respLoginHeader.accountNum;
            reqQryIntegratedQuotes.contractNo = quoteQryBean.getContractId();
            reqQryIntegratedQuotes.recordStatus = '1';
            try {
                RespProtocol respProtocol = write(reqQryIntegratedQuotes, connectTimeout);
                if (Objects.nonNull(respProtocol) && (Objects.isNull(respProtocol.errorCode)
                        || (ErrorCode.SUCCESS.value + "").equals(respProtocol.errorCode))
                        && Objects.nonNull(respProtocol.header)) {
                    List<IntegratedQuote> list = Lists.newArrayListWithCapacity(respProtocol.header.getResponseCount());
                    List<RespQryIntegratedQuotes> integratedQuotes = ObjectToBeanUtils.objectToBean(respProtocol.dataMessages, RespQryIntegratedQuotes.class);
                    logger.info("integratedQuotes list:{}", JSONArray.toJSONString(integratedQuotes));
                    JSDConverter.getIntegratedQuoteList(quoteQryBean, list, integratedQuotes);
                    tradeResponseImpl.resQueryIntegratedQuotes(list, requestId);
                    return;
                }
                tradeResponseImpl.resQueryIntegratedQuotes(null, requestId);
            } catch (Exception e) {
                logger.error("cancelIntegratedEntrust:{}", e.getMessage());
                tradeResponseImpl.resQueryIntegratedQuotes(null, requestId);
            }
            return;
        }
        if (QuoteType.AFTER_BULK_QUOTE.value == quoteQryBean.getQuoteType()) {//盘后大宗行情
            ReqQryAfterIntegratedQuotes reqQryAfterIntegratedQuotes = new ReqQryAfterIntegratedQuotes();
            yybId = yybIdMap.get(account.getFinanceAccount());
            String[] marketAndStockCode = StockCodeUtils.getMarketAndStockCode(quoteQryBean.getStockCode());
            if (Market.SZSE.value.equals(marketAndStockCode[1])) {
                reqQryAfterIntegratedQuotes.market = '2';
            } else if (Market.SHSE.value.equals(marketAndStockCode[1])) {
                reqQryAfterIntegratedQuotes.market = '1';
            }
            reqQryAfterIntegratedQuotes.accountNum = respLoginHeader.accountNum;
            if (Objects.nonNull(marketAndStockCode)) {
                reqQryAfterIntegratedQuotes.stockCode = marketAndStockCode[0];
            }
            try {
                RespProtocol respProtocol = write(reqQryAfterIntegratedQuotes, connectTimeout);
                if (Objects.nonNull(respProtocol) && (Objects.isNull(respProtocol.errorCode)
                        || (ErrorCode.SUCCESS.value + "").equals(respProtocol.errorCode))
                        && Objects.nonNull(respProtocol.header)) {
                    List<IntegratedQuote> list = Lists.newArrayListWithCapacity(respProtocol.header.getResponseCount());
                    List<RespQryAfterIntegratedQuotes> integratedQuotes = ObjectToBeanUtils.objectToBean(respProtocol.dataMessages, RespQryAfterIntegratedQuotes.class);
                    logger.info("integratedQuotes list:{}", JSONArray.toJSONString(integratedQuotes));
                    JSDConverter.getAfterIntegratedQuoteList(quoteQryBean, list, integratedQuotes);
                    tradeResponseImpl.resQueryIntegratedQuotes(list, requestId);
                    return;
                }
                tradeResponseImpl.resQueryIntegratedQuotes(null, requestId);
            } catch (Exception e) {
                logger.error("queryIntegratedQuotes:{}", e.getMessage());
                tradeResponseImpl.resQueryIntegratedQuotes(null, requestId);
            }
            return;
        }
        if (QuoteType.FIXED_INCOME_QUOTE.value == quoteQryBean.getQuoteType()) {//固收
            logger.info("没有相关接口");
            tradeResponseImpl.resQueryIntegratedQuotes(null, requestId);
            return;
        }
        if (QuoteType.NON_PUBLIC_PROTOCOL_BACK_QUOTE.value == quoteQryBean.getQuoteType()
                || QuoteType.PUBLIC_PROTOCOL_BACK_QUOTE.value == quoteQryBean.getQuoteType()) {//上海债券质押式协议回购行情信息查询
            logger.info("没有相关接口");
            tradeResponseImpl.resQueryIntegratedQuotes(null, requestId);
            return;
        }
    }

    /***
     * @Description: 最大交易数量
     * @Param: [qryBean, requestId, account, respLoginHeader, dataMap]
     * @return: void
     * @Author: MAXX
     * @Date: 2020/7/2
     */
    public void queryEnable(EnableQryBean qryBean, Long requestId, Account account, RespLoginHeader respLoginHeader, Map<Character, RespLoginData> dataMap) {
        ReqQryEnable reqQryEnable = new ReqQryEnable();
        yybId = yybIdMap.get(account.getFinanceAccount());
        String[] marketAndStockCode = StockCodeUtils.getMarketAndStockCode(qryBean.getStockCode());
        if (Market.SZSE.value.equals(marketAndStockCode[1])) {
            reqQryEnable.market = '2';
        } else if (Market.SHSE.value.equals(marketAndStockCode[1])) {
            reqQryEnable.market = '1';
        }
        reqQryEnable.gdAccount = dataMap.get(reqQryEnable.market).gddm;
        String direction = JSDConverter.getDirection(qryBean.getEntrustProp(), qryBean.getEntrustBs(), marketAndStockCode[1]);
        if (!"".equals(direction)) {
            reqQryEnable.direction = direction;
        }
        reqQryEnable.price = qryBean.getEntrustPrice();
        reqQryEnable.stockCode = marketAndStockCode[0];
        reqQryEnable.entrustType = entrustType;
        try {
            RespProtocol respProtocol = write(reqQryEnable, connectTimeout);
            if (Objects.nonNull(respProtocol) && (Objects.isNull(respProtocol.errorCode)
                    || (ErrorCode.SUCCESS.value + "").equals(respProtocol.errorCode))
                    && Objects.nonNull(respProtocol.header)) {
                RespQryEnable respQryEnable = (RespQryEnable) respProtocol.header;
                double maxNum = respQryEnable.maxNum;
                if (EntrustUtils.isSHZQ(marketAndStockCode[1], null, qryBean.getStockCode())) {
                    maxNum = maxNum * 10;
                }
                tradeResponseImpl.resQueryEnable(new Double(maxNum).intValue(), requestId);
                return;
            }
            tradeResponseImpl.resQueryEnable(EnableAmount.ZERO.value, requestId);
        } catch (Exception e) {
            logger.error("queryEnable:{}", e.getMessage());
            tradeResponseImpl.resQueryEnable(EnableAmount.ERROR.value, requestId);
        }
    }

    /*** 
     * @Description: 历史证券成交查询
     * @Param: [historyQryBean, requestId, account, respLoginHeader] 
     * @return: void
     * @Author: MAXX
     * @Date: 2020/7/2 
     */
    public void queryHistoryTrade(HistoryQryBean historyQryBean, Long requestId, Account account, RespLoginHeader respLoginHeader) {
        ReqQryHistoryTrade reqQryHistoryTrade = new ReqQryHistoryTrade();
        yybId = yybIdMap.get(account.getFinanceAccount());
        reqQryHistoryTrade.currencyCode = '1';
        reqQryHistoryTrade.accountNum = respLoginHeader.accountNum;
        reqQryHistoryTrade.startDate = historyQryBean.getStartDay();
        reqQryHistoryTrade.endDate = historyQryBean.getEndDay();
        reqQryHistoryTrade.serialNumStr = historyQryBean.getPostStr();
        reqQryHistoryTrade.maxNum = historyQryBean.getPageSize();
        reqQryHistoryTrade.entrustType = entrustType;
        try {
            RespProtocol respProtocol = write(reqQryHistoryTrade, connectTimeout);
            if (Objects.nonNull(respProtocol) && (Objects.isNull(respProtocol.errorCode)
                    || (ErrorCode.SUCCESS.value + "").equals(respProtocol.errorCode))
                    && Objects.nonNull(respProtocol.header)) {
                List<HistoryTrade> list = Lists.newArrayListWithCapacity(respProtocol.header.getResponseCount());
                List<RespQryHistoryTrade> historyTrades = ObjectToBeanUtils.objectToBean(respProtocol.dataMessages, RespQryHistoryTrade.class);
                logger.info("queryHistoryTrade list:{}", JSONArray.toJSONString(historyTrades));
                JSDConverter.getHistoryTrade(account, historyQryBean, list, historyTrades);
                tradeResponseImpl.resQueryHistoryTrade(list, requestId);
                return;
            }
            tradeResponseImpl.resQueryHistoryTrade(null, requestId);
        } catch (Exception e) {
            logger.error("queryHistoryTrade:{}", e.getMessage());
            tradeResponseImpl.resQueryHistoryTrade(null, requestId);
        }
    }

    /*** 
     * @Description: 客户资金快速查询
     * @Param: [requestId, account, respLoginHeader] 
     * @return: void
     * @Author: MAXX
     * @Date: 2020/7/2 
     */
    public void queryCustomerFinance(Long requestId, Account account, RespLoginHeader respLoginHeader) {
        ReqQryCustomerFinance reqQryCustomerFinance = new ReqQryCustomerFinance();
        yybId = yybIdMap.get(account.getFinanceAccount());
        reqQryCustomerFinance.currencyCode = '1';
        reqQryCustomerFinance.accountNum = respLoginHeader.accountNum;
        reqQryCustomerFinance.entrustType = entrustType;
        reqQryCustomerFinance.financingBalanceTag = '0';
        try {
            RespProtocol respProtocol = write(reqQryCustomerFinance, connectTimeout);
            if (Objects.nonNull(respProtocol) && (Objects.isNull(respProtocol.errorCode)
                    || (ErrorCode.SUCCESS.value + "").equals(respProtocol.errorCode))
                    && Objects.nonNull(respProtocol.header)) {
                RespQryCustomerFinance respQryCustomerFinance = (RespQryCustomerFinance) respProtocol.header;
                List<AccountFinance> list = Lists.newArrayListWithCapacity(1);
                JSDConverter.getCustomerFinance(account, respQryCustomerFinance, list);
                tradeResponseImpl.resQueryCustomerFinance(list, requestId);
                return;
            }
            tradeResponseImpl.resQueryCustomerFinance(null, requestId);
        } catch (Exception e) {
            logger.error("queryCustomerFinance:{}", e.getMessage());
            tradeResponseImpl.resQueryCustomerFinance(null, requestId);
        }
    }

    /*** 
     * @Description: 委托主动获取
     * @Param: [account, respLoginHeader] 
     * @return: void
     * @Author: MAXX
     * @Date: 2020/7/2 
     */
    public void qryEntrust(Account account, RespLoginHeader respLoginHeader) {
        qryEntrust(account, respLoginHeader, false);
    }

    public List<Entrust> qryEntrust(Account account, RespLoginHeader respLoginHeader, boolean reissueFlag) {
        ReqQryEntrust reqQryEntrust = new ReqQryEntrust();
        yybId = yybIdMap.get(account.getFinanceAccount());
        reqQryEntrust.accountNum = respLoginHeader.accountNum;
        reqQryEntrust.entrustType = entrustType;
        PositionStr positionStr = positionStrMap.get(account.getId());
        if (Objects.isNull(positionStr)) {
            positionStr = new PositionStr();
        }
        SimpleDateFormat tradingDayFormat = new SimpleDateFormat("yyyyMMdd");
        reqQryEntrust.indexContractId = positionStr.getOrderPositionStr();
        reqQryEntrust.indexDate = tradingDayFormat.format(new Date());
        reqQryEntrust.orderType = '1';//0-市场代码、1-委托时间、2-委托时间倒序
        reqQryEntrust.market = ' ';
        try {
            RespProtocol respProtocol = write(reqQryEntrust, connectTimeout);
            if (Objects.nonNull(respProtocol) && (Objects.isNull(respProtocol.errorCode)
                    || (ErrorCode.SUCCESS.value + "").equals(respProtocol.errorCode))
                    && Objects.nonNull(respProtocol.header)) {
                List<Entrust> entrustList = Lists.newArrayListWithCapacity(respProtocol.header.getResponseCount());
                List<RespQryEntrustData> list = ObjectToBeanUtils.objectToBean(respProtocol.dataMessages, RespQryEntrustData.class);
                logger.info("reqQryEntrust list:{}", JSONArray.toJSONString(list));
                JSDConverter.getEntrustList(account, list, entrustList);
                logger.info("entrustList:{}", JSONArray.toJSONString(entrustList));
                if (entrustList.size() == 0) {
                    return entrustList;
                }
                String position_str = entrustList.get(entrustList.size() - 1).getPositionStr();
                positionStr.setOrderPositionStr(position_str);
                positionStrMap.put(account.getId(), positionStr);
                if (!reissueFlag) {
                    entrustList.forEach(entrust -> {
                        entrust.setId(entrustIdManager.getIdByBrokeEntrustId(entrust.getAccountId(), entrust.getBrokerEntrustId()));
                        jsdTradeClient.onEntrust(entrust);
                    });
                }
                return entrustList;
            }
            return new ArrayList<>();
        } catch (Exception e) {
            logger.error("qryEntrust:{}", e.getMessage());
            return new ArrayList<>();
        }
    }

    /*** 
     * @Description: 大宗委托主动拉取
     * @Param: [account, respLoginHeader] 
     * @return: void
     * @Author: MAXX
     * @Date: 2020/7/3 
     */
    public void qryIntegratedOrder(Account account, RespLoginHeader respLoginHeader) {
        qryIntegratedOrder(account, respLoginHeader, false);
    }

    public List<IntegratedEntrust> qryIntegratedOrder(Account account, RespLoginHeader respLoginHeader, boolean reissueFlag) {
        ReqQryIntegratedOrder reqQryIntegratedOrder = new ReqQryIntegratedOrder();
        yybId = yybIdMap.get(account.getFinanceAccount());
        reqQryIntegratedOrder.accountNum = respLoginHeader.accountNum;
        reqQryIntegratedOrder.orderType = '0';
        reqQryIntegratedOrder.market = ' ';
        PositionStr positionStr = positionStrMap.get(account.getId());
        if (Objects.isNull(positionStr)) {
            positionStr = new PositionStr();
        }
        if (!Strings.isNullOrEmpty(positionStr.getFixedPositionStr())) {
            reqQryIntegratedOrder.indexContractId = Integer.parseInt(positionStr.getFixedPositionStr());
        }
        reqQryIntegratedOrder.entrustType = entrustType;
        try {
            RespProtocol respProtocol = write(reqQryIntegratedOrder, connectTimeout);
            if (Objects.nonNull(respProtocol) && (Objects.isNull(respProtocol.errorCode)
                    || (ErrorCode.SUCCESS.value + "").equals(respProtocol.errorCode))
                    && Objects.nonNull(respProtocol.header)) {
                List<IntegratedEntrust> integratedEntrustList = Lists.newArrayListWithCapacity(respProtocol.header.getResponseCount());
                List<RespQryIntegratedOrder> list = ObjectToBeanUtils.objectToBean(respProtocol.dataMessages, RespQryIntegratedOrder.class);
                logger.info("qryIntegratedOrder list:{}", JSONArray.toJSONString(list));
                JSDConverter.getIntegratedEntrustList(account, integratedEntrustList, list);
                logger.info("integratedEntrustList:{}", JSONArray.toJSONString(integratedEntrustList));
                if (integratedEntrustList.size() == 0) {
                    return integratedEntrustList;
                }
                String position_str = integratedEntrustList.get(integratedEntrustList.size() - 1).getPositionStr();
                positionStr.setFixedPositionStr(position_str);
                positionStrMap.put(account.getId(), positionStr);
                if (!reissueFlag) {
                    integratedEntrustList.forEach(entrust -> {
                        entrust.setId(entrustIdManager.getIdByBrokeEntrustId(entrust.getAccountId(), entrust.getBrokerEntrustId()));
                        jsdTradeClient.onIntegratedEntrust(entrust);
                    });
                }
                return integratedEntrustList;
            }
            return new ArrayList<>();
        } catch (Exception e) {
            logger.error("qryIntegratedOrder:{}", e.getMessage());
            return new ArrayList<>();
        }
    }

    /*** 
     * @Description: 成交主动获取
     * @Param: [account, respLoginHeader] 
     * @return: void
     * @Author: MAXX
     * @Date: 2020/7/3 
     */
    public void qryTrade(Account account, RespLoginHeader respLoginHeader) {
        qryTrade(account, respLoginHeader, false);
    }

    public List<Trade> qryTrade(Account account, RespLoginHeader respLoginHeader, boolean reissueFlag) {
        ReqQryTrade reqQryTrade = new ReqQryTrade();
        yybId = yybIdMap.get(account.getFinanceAccount());
        reqQryTrade.market = ' ';
        reqQryTrade.accountNum = respLoginHeader.accountNum;
        reqQryTrade.searchDirection = "1";
        reqQryTrade.queryMode = "0";
        reqQryTrade.queryTYpe = '1';
        PositionStr positionStr = positionStrMap.get(account.getId());
        if (Objects.isNull(positionStr)) {
            positionStr = new PositionStr();
        }
        if (!Strings.isNullOrEmpty(positionStr.getTradePositionStr())) {
            reqQryTrade.positionStr = positionStr.getTradePositionStr();
        }
        try {
            RespProtocol respProtocol = write(reqQryTrade, connectTimeout);
            if (Objects.nonNull(respProtocol) && (Objects.isNull(respProtocol.errorCode)
                    || (ErrorCode.SUCCESS.value + "").equals(respProtocol.errorCode))
                    && Objects.nonNull(respProtocol.header)) {
                List<Trade> tradeList = Lists.newArrayListWithCapacity(respProtocol.header.getResponseCount());
                List<RespQryTradeData> list = ObjectToBeanUtils.objectToBean(respProtocol.dataMessages, RespQryTradeData.class);
                logger.info("qryTrade list:{}", JSONArray.toJSONString(list));
                JSDConverter.getTradeList(account, list, tradeList);
                logger.info("tradeList:{}", JSONArray.toJSONString(tradeList));
                if (tradeList.size() == 0) {
                    return tradeList;
                }
                String position_str = tradeList.get(tradeList.size() - 1).getPositionStr();
                positionStr.setTradePositionStr(position_str);
                positionStrMap.put(account.getId(), positionStr);
                if (!reissueFlag) {
                    tradeList.forEach(trade -> {
                        jsdTradeClient.onTrade(trade);
                    });
                }
                return tradeList;
            }
            return new ArrayList<>();
        } catch (Exception e) {
            logger.error("qryTrade:{}", e.getMessage());
            return new ArrayList<>();
        }
    }

    /*** 
     * @Description: 大宗成交主动拉取
     * @Param: [account, respLoginHeader]
     * @return: void
     * @Author: MAXX
     * @Date: 2020/7/3 
     */
    public void qryProtocolBackTrade(Account account, RespLoginHeader respLoginHeader) {
        qryProtocolBackTrade(account, respLoginHeader, false);
    }

    public List<Trade> qryProtocolBackTrade(Account account, RespLoginHeader respLoginHeader, boolean reissueFlag) {
        ReqQryProtocolBackTrade reqQryProtocolBackTrade = new ReqQryProtocolBackTrade();
        yybId = yybIdMap.get(account.getFinanceAccount());
        reqQryProtocolBackTrade.market = ' ';
        reqQryProtocolBackTrade.accountNum = respLoginHeader.accountNum;
        PositionStr positionStr = positionStrMap.get(account.getId());
        if (Objects.isNull(positionStr)) {
            positionStr = new PositionStr();
        }
        if (!Strings.isNullOrEmpty(positionStr.getProtocolBackPositionStr())) {
            reqQryProtocolBackTrade.indexDealNo = positionStr.getProtocolBackPositionStr();
        }
        reqQryProtocolBackTrade.tradeStatusSet = "A";
        reqQryProtocolBackTrade.entrustType = entrustType;
        try {
            RespProtocol respProtocol = write(reqQryProtocolBackTrade, connectTimeout);
            if (Objects.nonNull(respProtocol) && (Objects.isNull(respProtocol.errorCode)
                    || (ErrorCode.SUCCESS.value + "").equals(respProtocol.errorCode))
                    && Objects.nonNull(respProtocol.header)) {
                List<Trade> tradeList = Lists.newArrayListWithCapacity(respProtocol.header.getResponseCount());
                List<RespQryProtocolBackTrade> list = ObjectToBeanUtils.objectToBean(respProtocol.dataMessages, RespQryProtocolBackTrade.class);
                logger.info("qryProtocolBackTrade list:{}", JSONArray.toJSONString(list));
                JSDConverter.getProtocolBackTradeList(account, tradeList, list, positionStr);
                logger.info("tradeList:{}", JSONArray.toJSONString(tradeList));
                if (tradeList.size() == 0) {
                    return tradeList;
                }
                String position_str = tradeList.get(tradeList.size() - 1).getBrokerTradeId();
                positionStr.setProtocolBackPositionStr(position_str);
                positionStrMap.put(account.getId(), positionStr);
                if (!reissueFlag) {
                    tradeList.forEach(trade -> jsdTradeClient.onTrade(trade));
                }
                return tradeList;
            }
            return new ArrayList<>();
        } catch (Exception e) {
            logger.error("qryProtocolBackTrade:{}", e.getMessage());
            return new ArrayList<>();
        }
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void setTradeResponseImpl(TradeResponseImpl tradeResponseImpl) {
        this.tradeResponseImpl = tradeResponseImpl;
    }

    public void setJsdTradeClient(JSDTradeClientImpl jsdTradeClient) {
        this.jsdTradeClient = jsdTradeClient;
    }

    /***
     * @Description: 大宗交易行情查询
     * @Param: [integratedBulkQuoteQryBean, requestId, account, respLoginHeader] 
     * @return: void
     * @Author: MAXX
     * @Date: 2020/9/22 
     */
    public void queryIntegratedBulkQuote(IntegratedBulkQuoteQryBean integratedBulkQuoteQryBean, Long requestId, Account account, RespLoginHeader respLoginHeader) {
        logger.info("queryIntegratedBulkQuote:{}", JSONObject.toJSONString(integratedBulkQuoteQryBean));
        ReqQryIntegratedQuotes reqQryIntegratedQuotes = new ReqQryIntegratedQuotes();
        yybId = yybIdMap.get(account.getFinanceAccount());
        if (!Strings.isNullOrEmpty(integratedBulkQuoteQryBean.getStockCode())) {
            String[] marketAndStockCode = StockCodeUtils.getMarketAndStockCode(integratedBulkQuoteQryBean.getStockCode());
            if (Market.SZSE.value.equals(marketAndStockCode[1])) {
                reqQryIntegratedQuotes.market = '2';
            } else if (Market.SHSE.value.equals(marketAndStockCode[1])) {
                reqQryIntegratedQuotes.market = '1';
            }
            if (Objects.nonNull(marketAndStockCode)) {
                reqQryIntegratedQuotes.stockCode = marketAndStockCode[0];
            }
        }
        reqQryIntegratedQuotes.instructionSet = CbptransTypeUtils.getCbptransTypeByEntrustBsAndEntrustProp(integratedBulkQuoteQryBean.getEntrustBs(), integratedBulkQuoteQryBean.getEntrustProp());
        reqQryIntegratedQuotes.accountNum = respLoginHeader.accountNum;
        reqQryIntegratedQuotes.recordStatus = '1';
        try {
            RespProtocol respProtocol = write(reqQryIntegratedQuotes, connectTimeout);
            if (Objects.nonNull(respProtocol) && (Objects.isNull(respProtocol.errorCode)
                    || (ErrorCode.SUCCESS.value + "").equals(respProtocol.errorCode))
                    && Objects.nonNull(respProtocol.header)) {
                List<IntegratedBulkQuoteBean> list = Lists.newArrayListWithCapacity(respProtocol.header.getResponseCount());
                List<RespQryIntegratedQuotes> integratedQuotes = ObjectToBeanUtils.objectToBean(respProtocol.dataMessages, RespQryIntegratedQuotes.class);
                logger.info("IntegratedBulkQuoteBean list:{}", JSONArray.toJSONString(integratedQuotes));
                JSDConverter.qryIntegratedBulkQuoteReBack(list, integratedQuotes);
                tradeResponseImpl.resQueryIntegratedBulkQuote(list, requestId);
                return;
            }
            tradeResponseImpl.resQueryIntegratedBulkQuote(null, requestId);
        } catch (Exception e) {
            logger.error("queryIntegratedBulkQuote:{}", e);
            tradeResponseImpl.resQueryIntegratedBulkQuote(null, requestId);
        }
        return;
    }

    /*** 
     * @Description: 盘后大宗交易行情查询
     * @Param: [integratedBulkCloseQuoteQryBean, requestId, account, respLoginHeader] 
     * @return: void
     * @Author: MAXX
     * @Date: 2020/9/22 
     */
    public void qryIntegratedBulkCloseQuote(IntegratedBulkCloseQuoteQryBean integratedBulkCloseQuoteQryBean, Long requestId, Account account, RespLoginHeader respLoginHeader) {
        logger.info("qryIntegratedBulkCloseQuote:{}", JSONObject.toJSONString(integratedBulkCloseQuoteQryBean));
        ReqQryAfterIntegratedQuotes reqQryAfterIntegratedQuotes = new ReqQryAfterIntegratedQuotes();
        yybId = yybIdMap.get(account.getFinanceAccount());
        String[] marketAndStockCode = StockCodeUtils.getMarketAndStockCode(integratedBulkCloseQuoteQryBean.getStockCode());
        if (Market.SZSE.value.equals(marketAndStockCode[1])) {
            reqQryAfterIntegratedQuotes.market = '2';
        } else if (Market.SHSE.value.equals(marketAndStockCode[1])) {
            reqQryAfterIntegratedQuotes.market = '1';
        }
        reqQryAfterIntegratedQuotes.accountNum = respLoginHeader.accountNum;
        if (Objects.nonNull(marketAndStockCode)) {
            reqQryAfterIntegratedQuotes.stockCode = marketAndStockCode[0];
        }
        try {
            RespProtocol respProtocol = write(reqQryAfterIntegratedQuotes, connectTimeout);
            if (Objects.nonNull(respProtocol) && (Objects.isNull(respProtocol.errorCode)
                    || (ErrorCode.SUCCESS.value + "").equals(respProtocol.errorCode))
                    && Objects.nonNull(respProtocol.header)) {
                List<IntegratedBulkCloseQuoteBean> list = Lists.newArrayListWithCapacity(respProtocol.header.getResponseCount());
                List<RespQryAfterIntegratedQuotes> integratedQuotes = ObjectToBeanUtils.objectToBean(respProtocol.dataMessages, RespQryAfterIntegratedQuotes.class);
                logger.info("IntegratedBulkCloseQuoteBean list:{}", JSONArray.toJSONString(integratedQuotes));
                JSDConverter.qryIntegratedBulkCloseQuoteReBack(list, integratedQuotes);
                tradeResponseImpl.resQueryIntegratedBulkCloseQuote(list, requestId);
                return;
            }
            tradeResponseImpl.resQueryIntegratedBulkCloseQuote(null, requestId);
        } catch (Exception e) {
            logger.error("qryIntegratedBulkCloseQuote:{}", e.getMessage());
            tradeResponseImpl.resQueryIntegratedBulkCloseQuote(null, requestId);
        }
    }

    public void queryStandardBonds(Long requestId, Account account, RespLoginHeader respLoginHeader, StandardBondQryBean standardBondQryBean) {
        logger.info("开始查询标准券信息----");
        ReqQryStandardBond reqQryStandardBond = new ReqQryStandardBond();
        yybId = yybIdMap.get(account.getFinanceAccount());
        if (Market.SZSE.value.equals(standardBondQryBean.getMarket())) {
            reqQryStandardBond.market = '2';
        } else if (Market.SHSE.value.equals(standardBondQryBean.getMarket())) {
            reqQryStandardBond.market = '1';
        }
        reqQryStandardBond.accountNum = respLoginHeader.accountNum;
        reqQryStandardBond.entrustType = entrustType;
        try {
            RespProtocol respProtocol = write(reqQryStandardBond, connectTimeout);
            if (Objects.nonNull(respProtocol) && (Objects.isNull(respProtocol.errorCode)
                    || (ErrorCode.SUCCESS.value + "").equals(respProtocol.errorCode))
                    && Objects.nonNull(respProtocol.header)) {
                List<StandardBonds> standardBondsList = Lists.newArrayListWithCapacity(respProtocol.header.getResponseCount());
                List<RespQryStandardBondData> list = ObjectToBeanUtils.objectToBean(respProtocol.dataMessages, RespQryStandardBondData.class);
                logger.info("查询到标准券数量信息为:{}", JSONArray.toJSONString(list));
                JSDConverter.getStandardBondList(account, standardBondsList, list);
                logger.info("JSD转换数据标准券查询数据成功:{}", JSONArray.toJSONString(standardBondsList));
                tradeResponseImpl.resQueryStandardBonds(standardBondsList, requestId);
            }
            tradeResponseImpl.resQueryStandardBonds(null, requestId);
        } catch (Exception e) {
            logger.error("查询标准券信息失败:{}", e.getMessage());
            tradeResponseImpl.resQueryStandardBonds(null, requestId);
        }
    }

    /***
     * @Description: 持仓查询
     * @Param: [positionQueryBean, requestId, account, respLoginHeader, dataMap]
     * @return: void
     * @Author: MAXX
     * @Date: 2020/9/28
     */
    public void queryAccountPosition(PositionQueryBean positionQueryBean, Long requestId, Account account, RespLoginHeader respLoginHeader, Map<Character, RespLoginData> dataMap) {
        if (positionQueryBean.getPageSize() == 0) {//多次请求
            int pageSize = 1000;
            int size = pageSize;
            positionQueryBean.setPageSize(pageSize);
            List<PositionBean> reList = new ArrayList<PositionBean>();
            try {
                while (pageSize == size) {
                    RespProtocol respProtocol = sendAccountPosition(positionQueryBean, account, respLoginHeader, dataMap);
                    if (Objects.nonNull(respProtocol) && (Objects.isNull(respProtocol.errorCode)
                            || (ErrorCode.SUCCESS.value + "").equals(respProtocol.errorCode))
                            && Objects.nonNull(respProtocol.header)) {
                        List<PositionBean> list = Lists.newArrayListWithCapacity(respProtocol.header.getResponseCount());
                        List<RespQryPositionData> respQryPositionData = ObjectToBeanUtils.objectToBean(respProtocol.dataMessages, RespQryPositionData.class);
                        JSDConverter.qryAccountPositionReBack(account, list, respQryPositionData);
                        pageSize = list == null ? 0 : list.size();
                        logger.info("pageSize : {}", pageSize);
                        reList.addAll(list);
                        if (pageSize == size) {
                            String positionStr = list.get(pageSize - 1).getMarket() + splitStr + list.get(pageSize - 1).getStockCode();
                            positionQueryBean.setPositionStr(positionStr);
                        }
                    } else {
                        tradeResponseImpl.resQueryAccountPosition(null, requestId);
                        return;
                    }
                }
                logger.info("queryAccountPosition list:{}", JSONArray.toJSONString(reList));
                tradeResponseImpl.resQueryAccountPosition(reList, requestId);
            } catch (Exception e) {
                logger.error("queryAccountPosition:{}", e.getMessage());
                tradeResponseImpl.resQueryAccountPosition(null, requestId);
                return;
            }
            return;
        }
        try {
            RespProtocol respProtocol = sendAccountPosition(positionQueryBean, account, respLoginHeader, dataMap);
            if (Objects.nonNull(respProtocol) && (Objects.isNull(respProtocol.errorCode)
                    || (ErrorCode.SUCCESS.value + "").equals(respProtocol.errorCode))
                    && Objects.nonNull(respProtocol.header)) {
                List<PositionBean> list = Lists.newArrayListWithCapacity(respProtocol.header.getResponseCount());
                List<RespQryPositionData> respQryPositionData = ObjectToBeanUtils.objectToBean(respProtocol.dataMessages, RespQryPositionData.class);
                logger.info("queryAccountPosition list:{}", JSONArray.toJSONString(respQryPositionData));
                JSDConverter.qryAccountPositionReBack(account, list, respQryPositionData);
                tradeResponseImpl.resQueryAccountPosition(list, requestId);
                return;
            }
            tradeResponseImpl.resQueryAccountPosition(null, requestId);
        } catch (Exception e) {
            logger.error("queryAccountPosition:{}", e.getMessage());
            tradeResponseImpl.resQueryAccountPosition(null, requestId);
        }
    }

    private RespProtocol sendAccountPosition(PositionQueryBean positionQueryBean, Account account, RespLoginHeader respLoginHeader, Map<Character, RespLoginData> dataMap) throws Exception {
        logger.info("queryAccountPosition:{}", JSONObject.toJSONString(positionQueryBean));
        ReqQryPosition reqQryPosition = new ReqQryPosition();
        yybId = yybIdMap.get(account.getFinanceAccount());
        if (!Strings.isNullOrEmpty(positionQueryBean.getStockCode())) {
            String[] marketAndStockCode = StockCodeUtils.getMarketAndStockCode(positionQueryBean.getStockCode());
            if (Market.SZSE.value.equals(marketAndStockCode[1])) {
                reqQryPosition.market = '2';
                reqQryPosition.reqMarket = reqQryPosition.market;
            } else if (Market.SHSE.value.equals(marketAndStockCode[1])) {
                reqQryPosition.market = '1';
                reqQryPosition.reqMarket = reqQryPosition.market;
            }
            reqQryPosition.reqStockCode = marketAndStockCode[0];
            reqQryPosition.gddm = dataMap.get(reqQryPosition.market).gddm;
        }
        if (!Strings.isNullOrEmpty(positionQueryBean.getPositionStr())) {
            String positionStr[] = positionQueryBean.getPositionStr().split(splitStr);
            if (Market.SZSE.value.equals(positionStr[0])) {
                reqQryPosition.indexMarketCode = '2';
            } else if (Market.SHSE.value.equals(positionStr[0])) {
                reqQryPosition.indexMarketCode = '1';
            }
            reqQryPosition.indexStockCode = StockCodeUtils.getStockCode(positionStr[1]);
            reqQryPosition.indexGddm = dataMap.get(reqQryPosition.indexMarketCode).gddm;
        }
        reqQryPosition.accountNum = respLoginHeader.accountNum;
        reqQryPosition.maxRetLen = positionQueryBean.getPageSize();
        reqQryPosition.off = "1";
        reqQryPosition.entrustType = entrustType;
        RespProtocol respProtocol = write(reqQryPosition, connectTimeout);
        return respProtocol;
    }

    public void setMessageManager(MessageManager messageManager) {
        this.messageManager = messageManager;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setEntrustIdManager(EntrustIdManager entrustIdManager) {
        this.entrustIdManager = entrustIdManager;
    }
}
