package com.joyintech.tams.exchange.terminal.trade.kdgateway;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.joyintech.tams.exchange.common.bean.*;
import com.joyintech.tams.exchange.common.bean.bond.StandardBondQryBean;
import com.joyintech.tams.exchange.common.constant.*;
import com.joyintech.tams.exchange.common.db.domain.*;
import com.joyintech.tams.exchange.common.utils.*;
import com.joyintech.tams.exchange.terminal.constant.AccountLoginStatus;
import com.joyintech.tams.exchange.terminal.trade.EntrustIdManager;
import com.joyintech.tams.exchange.terminal.trade.kdgateway.bean.*;
import com.pingan.stock.client.TradeClientResponse;
import com.pingan.stock.core.TradeTransServiceImpl;
import com.pingan.stock.service.config.base.TradeSysHead;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static com.joyintech.tams.exchange.terminal.trade.kdgateway.KDGatewayConverter.getKdBsFlag;

public class KDGatewayApi {
    private static final Logger logger = LoggerFactory.getLogger(KDGatewayApi.class);

    private KDGatewayClientImpl kdGatewayClient;

    private static long beginMill;

    private TradeTransServiceImpl Trade = new TradeTransServiceImpl();
    private TradeSysHead head = new TradeSysHead();

    private SimpleDateFormat currentDay = new SimpleDateFormat("yyyyMMdd");
    private SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");

    private AtomicInteger offset = new AtomicInteger(0);

    private Map<String, Integer> status = Maps.newConcurrentMap();

    private EntrustIdManager entrustIdManager;

    public void setStatus(String accountId, AccountLoginStatus status) {
        logger.info("account {} status set to: {}", accountId, status.msg);
        this.status.put(accountId, status.value);
    }

    public int getStatus(String accountId) {
        return status.getOrDefault(accountId, AccountLoginStatus.NOT_LOGGED.value);
    }

    static {
        Calendar curDate = Calendar.getInstance();
        curDate.set(curDate
                .get(Calendar.YEAR), curDate.get(Calendar.MONTH), curDate
                .get(Calendar.DATE), 0, 0, 0);
        beginMill = curDate.getTimeInMillis();
    }

    public KDGatewayApi(KDGatewayClientImpl kdGatewayClient) {
        head.setCallSys("CTS");
        head.setChannel("a");
        head.setOperatorSite("47.108.58.49"); // 填本地ip
        this.kdGatewayClient = kdGatewayClient;
    }

    public String getLocalId() {
        int tempOffSet = offset.incrementAndGet();
        if (tempOffSet > 9999) {
            tempOffSet = 0;
            offset.set(0);
        }
        return String.format("%s%04d", (System.currentTimeMillis() - beginMill) / 1000, tempOffSet);
    }

    //除登录外，都需要调用
    private void setCommonParam(TradeSysHead head, KDGatewayConnection connection) {
        head.setOperatorCode(connection.getUserCode());
        head.setBranch(connection.getBranchNo());
        head.setSessionSerialNO(connection.getSession());
    }

    public KDGatewayConnection accountLogin(Account account, String terminalInfo) {
        setStatus(account.getId(), AccountLoginStatus.NOT_LOGGED);
        KDLogin kdLogin = new KDLogin();
        kdLogin.setUSER_ID_CLS("Z");
        kdLogin.setUSER_ID(account.getFinanceAccount());
        kdLogin.setUSER_PWD(account.getPwd());
        kdLogin.setPROC_NAME("JOYIN");
        kdLogin.setTERMINALINFO(terminalInfo);
        head.setFunId("301");
        logger.info("accountLogin param head {} -- KDLogin {}", JsonUtils.getJsonFromObject(head), JsonUtils.getJsonFromObject(kdLogin));
        TradeClientResponse resp = Trade.callTradeService(head, JsonUtils.getJsonFromObject(kdLogin));
        if (resp.getResCode() == 0) {
            List<Map> respBody = resp.getResBody(Map.class);
            KDGatewayConnection connection = new KDGatewayConnection();
            connection.setBranchNo((String) respBody.get(0).get("BRANCH"));
            connection.setSession((String) respBody.get(0).get("SESSION"));
            connection.setUserCode((String) respBody.get(0).get("USER_CODE"));
            if (respBody.get(0).get("MARKET").equals("00")) {
                connection.setSzAccount((String) respBody.get(0).get("SECU_ACC"));
                connection.setShAccount((String) respBody.get(1).get("SECU_ACC"));
            } else {
                connection.setShAccount((String) respBody.get(0).get("SECU_ACC"));
                connection.setSzAccount((String) respBody.get(1).get("SECU_ACC"));
            }
            connection.setAccountId(account.getId());
            connection.setTradeRecordNo("");
            connection.setEntrustRecordNo("");
            connection.setTerminalInfo(terminalInfo);
            kdGatewayClient.addKDGatewayAccount(connection);
            logger.info("login account success, get secAccount SH {}  SZ {}", connection.getShAccount(), connection.getSzAccount());
            setStatus(account.getId(), AccountLoginStatus.LOGGED);
            return connection;
        } else {
            logger.info("accountLogin failed: {}", resp.getResMsg());
            return null;
        }
    }

    public List<Trade> reqTrades(String accountId) {
        KDGatewayConnection kdGatewayConnection = kdGatewayClient.getKdGatewayAccounts(accountId);
        if (Objects.nonNull(kdGatewayConnection)) {
            KDTradeQuery kdTradeQuery = new KDTradeQuery();
            kdTradeQuery.setUSER_CODE(kdGatewayConnection.getUserCode());
            kdTradeQuery.setMARKETS("");
            kdTradeQuery.setSECU_ACC("");
            kdTradeQuery.setSECU_CODE("");
            kdTradeQuery.setTRD_ID("");
            kdTradeQuery.setORDER_ID("");
            kdTradeQuery.setACCOUNT("");
            kdTradeQuery.setBOARDS("");
            kdTradeQuery.setIS_WITHDRAW("");
            kdTradeQuery.setR_COUNT("");
            kdTradeQuery.setSECU_CLS("");
            kdTradeQuery.setIS_FILTER_HKT("0");
            kdTradeQuery.setIS_WITHDRAW("");
            kdTradeQuery.setOUT_ORDER_FLAG("");
            setCommonParam(head, kdGatewayConnection);
            head.setFunId("376");
            TradeClientResponse resp = Trade.callTradeService(head, JsonUtils.getJsonFromObject(kdTradeQuery));
            if (resp.getResCode() == 0) {
                List<Map> respBody = resp.getResBody(Map.class);
                return KDGatewayConverter.convertTradeList(respBody, accountId);
            } else {
                if (resp.getResCode() == 501)
                    return new ArrayList<>();
                logger.info("reqTrades error: {}", resp.getResMsg());
            }
        }
        return null;
    }

    public List<Entrust> reqEntrusts(String accountId) {
        KDGatewayConnection kdGatewayConnection = kdGatewayClient.getKdGatewayAccounts(accountId);
        if (Objects.nonNull(kdGatewayConnection)) {
            KDEntrustQuery kdEntrustQuery = new KDEntrustQuery();
            kdEntrustQuery.setUSER_CODE(kdGatewayConnection.getUserCode());
            kdEntrustQuery.setGET_ORDERS_MODE("0");
            setCommonParam(head, kdGatewayConnection);
            head.setFunId("374");
            TradeClientResponse resp = Trade.callTradeService(head, JsonUtils.getJsonFromObject(kdEntrustQuery));
            if (resp.getResCode() == 0) {
                List<Map> respBody = resp.getResBody(Map.class);
                List<Entrust> res = KDGatewayConverter.convertEntrustList(respBody, accountId, entrustIdManager);
                res.forEach(entrust -> {
                    if (Strings.isNullOrEmpty(entrust.getId())) {
                        entrust.setId(entrustIdManager.getIdByBrokeEntrustId(entrust.getAccountId(), entrust.getBrokerEntrustId()));
                    }
                });
                return res;
            } else {
                if (resp.getResCode() == 501)
                    return new ArrayList<>();
                logger.info("reqEntrusts error: {}", resp.getResMsg());
            }
        }
        return null;
    }

    private List<Map> reqAllEntrusts(String accountId) {
        KDGatewayConnection kdGatewayConnection = kdGatewayClient.getKdGatewayAccounts(accountId);
        if (Objects.nonNull(kdGatewayConnection)) {
            KDEntrust kdEntrust = new KDEntrust();
            kdEntrust.setGET_ORDERS_MODE("6");//查询委托模式  6:去除撤单记录
            kdEntrust.setCUSTOMER(kdGatewayConnection.getUserCode());
            kdEntrust.setMARKET("");
            kdEntrust.setSECU_ACC("");
            kdEntrust.setSECU_CODE("");
            kdEntrust.setTRD_ID("");
            kdEntrust.setTRD_IDS("");
            kdEntrust.setBIZ_NO("");
            kdEntrust.setACCOUNT("");
            setCommonParam(head, kdGatewayConnection);
            head.setFunId("374");
            TradeClientResponse resp = Trade.callTradeService(head, JsonUtils.getJsonFromObject(kdEntrust));
            if (resp.getResCode() == 0) {
                return resp.getResBody(Map.class);
            } else {
                if (resp.getResCode() == 501)
                    return new ArrayList<>();
                logger.info("reqEntrusts error: {}", resp.getResMsg());
            }
        }
        return null;
    }

    public CancelEntrustBean reqCancelOrder(CancelEntrustBean cancelEntrustBean) {
        KDGatewayConnection kdGatewayConnection = kdGatewayClient.getKdGatewayAccounts(cancelEntrustBean.getAccountId());
        if (Objects.nonNull(kdGatewayConnection)) {
            KDCancelEntrust kdCancelEntrust = new KDCancelEntrust();
            kdCancelEntrust.setUSER_CODE(kdGatewayConnection.getUserCode());
            kdCancelEntrust.setMARKET(KDGatewayConverter.getKDMarket(cancelEntrustBean.getMarket()));
            kdCancelEntrust.setORDER_ID(cancelEntrustBean.getBrokerEntrustId());
            setCommonParam(head, kdGatewayConnection);
            head.setFunId("404");
            TradeClientResponse resp = Trade.callTradeService(head, JsonUtils.getJsonFromObject(kdCancelEntrust));
            if (resp.getResCode() == 0) {
                List<Map> respBody = resp.getResBody(Map.class);
                return KDGatewayConverter.convertCancelEntrust(respBody, cancelEntrustBean);
            }
            logger.info("reqCancelOrder error: {}", resp.getResMsg());
            cancelEntrustBean.setErrorCode(ErrorCode.TERMINAL_RETURN_ERROR.value);
            cancelEntrustBean.setErrorMsg(ErrorCode.TERMINAL_RETURN_ERROR.errorMsg + resp.getResCode() + "-" + resp.getResMsg());
        } else {
            cancelEntrustBean.setErrorCode(ErrorCode.TERMINAL_API_ERROR.value);
            cancelEntrustBean.setErrorMsg(ErrorCode.TERMINAL_API_ERROR.errorMsg);
        }
        return cancelEntrustBean;
    }

    public CancelEntrustBean reqCancelIntegratedEntrust(CancelEntrustBean cancelEntrustBean) {
        KDGatewayConnection kdGatewayConnection = kdGatewayClient.getKdGatewayAccounts(cancelEntrustBean.getAccountId());
        if (Objects.nonNull(kdGatewayConnection)) {
            KDCancelEntrust kdCancelEntrust = new KDCancelEntrust();
            kdCancelEntrust.setUSER_CODE(kdGatewayConnection.getUserCode());
            kdCancelEntrust.setMARKET(KDGatewayConverter.getKDMarket(cancelEntrustBean.getMarket()));
            kdCancelEntrust.setORDER_ID(cancelEntrustBean.getBrokerEntrustId());
            setCommonParam(head, kdGatewayConnection);
            head.setFunId("404");
            TradeClientResponse resp = Trade.callTradeService(head, JsonUtils.getJsonFromObject(kdCancelEntrust));
            if (resp.getResCode() == 0) {
                List<Map> respBody = resp.getResBody(Map.class);
                return KDGatewayConverter.convertCancelEntrust(respBody, cancelEntrustBean);
            }
            logger.info("reqCancelIntegratedEntrust error: {}", resp.getResMsg());
            cancelEntrustBean.setErrorCode(ErrorCode.TERMINAL_RETURN_ERROR.value);
            cancelEntrustBean.setErrorMsg(ErrorCode.TERMINAL_RETURN_ERROR.errorMsg + resp.getResCode() + "-" + resp.getResMsg());
        } else {
            cancelEntrustBean.setErrorCode(ErrorCode.TERMINAL_API_ERROR.value);
            cancelEntrustBean.setErrorMsg(ErrorCode.TERMINAL_API_ERROR.errorMsg);
        }
        return cancelEntrustBean;
    }

    public Entrust reqEntrust(Entrust entrust) {
        KDGatewayConnection kdGatewayConnection = kdGatewayClient.getKdGatewayAccounts(entrust.getAccountId());
        if (Objects.nonNull(kdGatewayConnection)) {
            KDEntrust kdEntrust = new KDEntrust();
            kdEntrust.setUSER_CODE(kdGatewayConnection.getUserCode());
            kdEntrust.setMARKET(KDGatewayConverter.getKDMarket(entrust.getMarket()));
            if (entrust.getMarket().equals(Market.SHSE.value)) {
                kdEntrust.setSECU_ACC(kdGatewayConnection.getShAccount());
            } else if (entrust.getMarket().equals(Market.SZSE.value)) {
                kdEntrust.setSECU_ACC(kdGatewayConnection.getSzAccount());
            }
            kdEntrust.setSECU_CODE(StockCodeUtils.getStockCode(entrust.getStockCode()));
            if (!Strings.isNullOrEmpty(getKdBsFlag(entrust.getBsFlag())))
                kdEntrust.setTRD_ID(getKdBsFlag(entrust.getBsFlag()));
            else
                logger.info("has not found kdBsFlag {}", entrust.getBsFlag());
            kdEntrust.setPRICE(String.valueOf(entrust.getEntrustPrice()));
            kdEntrust.setQTY(String.valueOf(entrust.getEntrustVolume()));
            kdEntrust.setEXT_REC_NUM(entrust.getLocalEntrustId());//外部流水号 ： 做本地ID字段映射
            head.setFunId("403");
            setCommonParam(head, kdGatewayConnection);
            logger.info("get entrust KDBsFlag {}", kdEntrust.getTRD_ID());
            logger.info("send normal entrust param head {}  kdEntrust {}", JsonUtils.getJsonFromObject(head), JsonUtils.getJsonFromObject(kdEntrust));
            TradeClientResponse resp = Trade.callTradeService(head, JsonUtils.getJsonFromObject(kdEntrust));
            logger.info("Trade.callTradeService resp {}", JsonUtils.getJsonFromObject(resp));
            if (resp.getResCode() == 0) {
                List<Map> respBody = resp.getResBody(Map.class);
                KDGatewayConverter.convertEntrust(respBody, entrust);
                entrustIdManager.setBrokeEntrustIdMapping(entrust.getAccountId(), entrust.getBrokerEntrustId(), entrust.getId());
                return entrust;
            } else {
                entrust.setErrorCode(ErrorCode.TERMINAL_RETURN_ERROR.value);
                entrust.setErrorMsg(ErrorCode.TERMINAL_RETURN_ERROR.errorMsg + resp.getResCode() + "-" + resp.getResMsg());
                logger.info("reqEntrust error: {}", resp.getResMsg());
            }
        }
        return entrust;
    }

    public IntegratedEntrust reqIntegratedEntrust(IntegratedEntrust integratedEntrust) {
        KDGatewayConnection kdGatewayConnection = kdGatewayClient.getKdGatewayAccounts(integratedEntrust.getAccountId());
        if (Objects.nonNull(kdGatewayConnection)) {
            KDEntrust kdEntrust = new KDEntrust();
            kdEntrust.setUSER_CODE(kdGatewayConnection.getUserCode());
            kdEntrust.setMARKET(KDGatewayConverter.getKDMarket(integratedEntrust.getMarket()));
            kdEntrust.setEXT_REC_NUM(integratedEntrust.getLocalEntrustId());//外部流水号 ： 做本地ID字段映射
            if (integratedEntrust.getMarket().equals(Market.SHSE.value)) {
                kdEntrust.setSECU_ACC(kdGatewayConnection.getShAccount());
            } else if (integratedEntrust.getMarket().equals(Market.SZSE.value)) {
                kdEntrust.setSECU_ACC(kdGatewayConnection.getSzAccount());
            }
            kdEntrust.setSECU_CODE(StockCodeUtils.getStockCode(integratedEntrust.getStockCode()));
            if (!Strings.isNullOrEmpty(getKdBsFlag(integratedEntrust.getBsFlag()))) {
                kdEntrust.setTRD_ID(getKdBsFlag(integratedEntrust.getBsFlag()));
            } else
                logger.info("has not found kdBsFlag {}", integratedEntrust.getBsFlag());
            kdEntrust.setPRICE(String.valueOf(integratedEntrust.getEntrustPrice()));
            kdEntrust.setQTY(String.valueOf(integratedEntrust.getEntrustVolume()));
            head.setFunId("403");
            if (IntegratedEntrustUtils.isBulk(integratedEntrust.getEntrustProp())) {
                logger.info("KDG_API start handle bulk {}", getKdBsFlag(integratedEntrust.getBsFlag()));
                if (EntrustProp.CONFIRM.value.equals(integratedEntrust.getEntrustProp()))
                    kdEntrust.setCJSB_TYPE("2");
                else if (EntrustProp.SET_PRICE.value.equals(integratedEntrust.getEntrustProp()))
                    kdEntrust.setCJSB_TYPE("1");
                    // 盘后大宗交易时，价格字段的字典为：１－按收盘价执行，２－按加权平均价执行
                else if (EntrustProp.LARGE_AFTER_CLOSING_PRICE.value.equals(integratedEntrust.getEntrustProp()))
                    kdEntrust.setPRICE("1");
                else if (EntrustProp.LARGE_AFTER_CLOSING_WEIGHT_PRICE.value.equals(integratedEntrust.getEntrustProp()))
                    kdEntrust.setPRICE("2");
                if (!Strings.isNullOrEmpty(integratedEntrust.getPropSeatNo()))
                    kdEntrust.setMATCH_SEAT(integratedEntrust.getPropSeatNo());//对手席位
                if (!Strings.isNullOrEmpty(integratedEntrust.getContractId()))
                    kdEntrust.setMATCH_NUM(integratedEntrust.getContractId());//约定号
                if (!Strings.isNullOrEmpty(integratedEntrust.getLiaisonName()))
                    kdEntrust.setLINKMAN(integratedEntrust.getLiaisonName());//联系人
                if (!Strings.isNullOrEmpty(integratedEntrust.getLiaisonTel()))
                    kdEntrust.setLINKADD(integratedEntrust.getLiaisonTel());//联系地址
            } else if (IntegratedEntrustUtils.isFixedIncome(integratedEntrust.getEntrustProp())) {
                logger.info("KDG_API start handle FixedIncome {}", getKdBsFlag(integratedEntrust.getBsFlag()));
                // 上交所固定收益
                kdEntrust.setMATCH_NUM(integratedEntrust.getContractId());//约定号
                if (!Strings.isNullOrEmpty(integratedEntrust.getLiaisonName()))
                    kdEntrust.setLINKMAN(integratedEntrust.getLiaisonName());//联系人
                if (!Strings.isNullOrEmpty(integratedEntrust.getLiaisonTel()))
                    kdEntrust.setLINKADD(integratedEntrust.getLiaisonTel());//联系地址
                // 上交所固定收益
                if (!Strings.isNullOrEmpty(integratedEntrust.getLiaisonName()))
                    //固收平台指定对手方交易：前三位表示对手方交易商代码，后六位表示对手方交易员代码，逗号分隔）
                    kdEntrust.setEXT_ORDER_ID(integratedEntrust.getPropSeatNo() + "," + integratedEntrust.getLiaisonName());
                // 指定对手方交易
                if (!Strings.isNullOrEmpty(integratedEntrust.getLiaisonTel()) || !Strings.isNullOrEmpty(integratedEntrust.getSellerNo())) {
                    //前三位表示本方交易商代码，后六位表示本方交易员代码，逗号分隔），本方交易员可不送，默认取公共参数SHXY_COMPANY（交易商）、SHXY_TRADER（交易员代码）
                    kdEntrust.setEXT_BIZ_NO(integratedEntrust.getSellerNo() + "," + integratedEntrust.getLiaisonTel());
                }
            } else if (IntegratedEntrustUtils.isProtocolBack_SH(integratedEntrust.getEntrustProp())
                    || IntegratedEntrustUtils.isProtocolBack_SZ(integratedEntrust.getEntrustProp())) {
                // 质押式协议回购
                logger.info("KDG_API start handle ProtocolBack {}", getKdBsFlag(integratedEntrust.getBsFlag()));
                try {
                    head.setFunId("596");
                    kdEntrust.setCUSTOMER(kdGatewayConnection.getUserCode());
                    kdEntrust.setBOARD("00");
                    // 计算价格
                    if (integratedEntrust.getEntrustVolume() != 0) {
                        kdEntrust.setPRICE(String.valueOf(TradeUtils.formatDouble(integratedEntrust.getEntrustBalance().doubleValue() / integratedEntrust.getEntrustVolume(), 4)));
                    } else {
                        kdEntrust.setPRICE("0");
                    }
                    kdEntrust.setGP_CONTRACTS(String.valueOf(integratedEntrust.getEntrustBalance()));//初始金额
                    kdEntrust.setYD_FEE_RATE(String.valueOf(integratedEntrust.getEntrustPrice()));//初始百元利率
                    kdEntrust.setYD_BB_AMT(String.valueOf(integratedEntrust.getBackBalance()));//购回金额
                    kdEntrust.setYD_BB_DATE(formatDate.format(currentDay.parse(integratedEntrust.getBackDate())));//购回日期
                    if (Market.SZSE.value.equals(integratedEntrust.getMarket())) {
                        // 深交所协议回购填，上交所可不填
                        kdEntrust.setMATCH_NUM(integratedEntrust.getContractId());//约定号
                        if (!Strings.isNullOrEmpty(integratedEntrust.getPropSeatNo()))
                            kdEntrust.setMATCH_SEAT(integratedEntrust.getPropSeatNo());//对方席位
                    }
                    if (Market.SHSE.value.equals(integratedEntrust.getMarket())) {
                        if (!Strings.isNullOrEmpty(integratedEntrust.getLiaisonTel())) {
                            kdEntrust.setMATCHED_SN(integratedEntrust.getLiaisonTel());
                        }
                        // 上交所对手交易员代码，借用字段contractId
                        kdEntrust.setMATCH_SEAT(integratedEntrust.getLiaisonTel());//初始合同序号 购回时用，上海为对手交易员代码
                        if (!Strings.isNullOrEmpty(integratedEntrust.getPropSeatNo()))
                            kdEntrust.setLD_SECU_ACC(integratedEntrust.getPropSeatNo());//融出方股东号 购回时用，上海为对手交易商号
                    }
                    // 行情查询请求号
                    if (!Strings.isNullOrEmpty(integratedEntrust.getContractId()))
                        kdEntrust.setLINKADD(integratedEntrust.getContractId());
                    // 上海成交协议回购成交申报 接受或者拒绝标志 1：接收 0：拒绝
                    if (EntrustProp.SH_REPURCHASE_TRADE_CONFIRM.value.equals(integratedEntrust.getEntrustProp())) {
                        kdEntrust.setACCEPTFLAG("1");
                    } else if (EntrustProp.SH_REPURCHASE_TRADE_REFUSE.value.equals(integratedEntrust.getEntrustProp())) {
                        kdEntrust.setACCEPTFLAG("0");
                    }
                } catch (Exception e) {
                    logger.warn("API handle KDGProtocolBack error {}", e);
                }
            }
            setCommonParam(head, kdGatewayConnection);
            logger.info("send param {} kdEntrust {} ", JsonUtils.getJsonFromObject(head), JsonUtils.getJsonFromObject(kdEntrust));
            TradeClientResponse resp = Trade.callTradeService(head, JsonUtils.getJsonFromObject(kdEntrust));
            logger.info("Trade.callTradeService resp {}", JsonUtils.getJsonFromObject(resp));
            if (resp.getResCode() == 0) {
                List<Map> respBody = resp.getResBody(Map.class);
                KDGatewayConverter.convertIntegratedEntrust(respBody, integratedEntrust);
                entrustIdManager.setBrokeEntrustIdMapping(integratedEntrust.getAccountId(), integratedEntrust.getBrokerEntrustId(), integratedEntrust.getId());
                return integratedEntrust;
            } else {
                integratedEntrust.setErrorCode(ErrorCode.TERMINAL_RETURN_ERROR.value);
                integratedEntrust.setErrorMsg(ErrorCode.TERMINAL_RETURN_ERROR.errorMsg + resp.getResCode() + "-" + resp.getResMsg());
                if (KDGatewayConverter.unSupportFunction(integratedEntrust.getEntrustProp()))
                    integratedEntrust.setErrorMsg(ErrorCode.TERMINAL_UN_SUPPORT_FUNCTION.errorMsg);
                logger.info("reqIntegratedEntrust error: {}", resp.getResMsg());
            }
        }
        return integratedEntrust;
    }

    public List<HistoryTrade> reqHistoryTrade(HistoryQryBean historyQryBean) {
        KDGatewayConnection kdGatewayConnection = kdGatewayClient.getKdGatewayAccounts(historyQryBean.getAccountId());
        if (Objects.nonNull(kdGatewayConnection)) {
            try {
                KDHisTradeQuery kdHisTradeQuery = new KDHisTradeQuery();
                kdHisTradeQuery.setUSER_CODE(kdGatewayConnection.getUserCode());
                kdHisTradeQuery.setBGN_DATE(formatDate.format(currentDay.parse(historyQryBean.getStartDay())));
                kdHisTradeQuery.setEND_DATE(formatDate.format(currentDay.parse(historyQryBean.getEndDay())));
                kdHisTradeQuery.setR_COUNT(String.valueOf(historyQryBean.getPageSize()));
                kdHisTradeQuery.setRECORD_NO(String.valueOf(historyQryBean.getPostStr()));
                head.setFunId("392");//对帐单分页查询
                setCommonParam(head, kdGatewayConnection);
                TradeClientResponse resp = Trade.callTradeService(head, JsonUtils.getJsonFromObject(kdHisTradeQuery));
                if (resp.getResCode() == 0) {
                    List<Map> respBody = resp.getResBody(Map.class);
                    return KDGatewayConverter.convertHistoryTrade(respBody, historyQryBean.getAccountId());
                } else {
                    if (resp.getResCode() == 501)
                        return new ArrayList<>();
                    logger.info("reqHistoryTrade error: {}", resp.getResMsg());
                }
            } catch (ParseException e) {
                logger.warn("API query KDGHistoryTrade error {}", e);
            }
        }
        return null;
    }

    public int reqEnable(EnableQryBean qryBean) {
        KDGatewayConnection kdGatewayConnection = kdGatewayClient.getKdGatewayAccounts(qryBean.getAccountId());
        if (Objects.nonNull(kdGatewayConnection)) {
            KDEnableQryBean kdEnableQryBean = new KDEnableQryBean();
            kdEnableQryBean.setUSER_CODE(kdGatewayConnection.getUserCode());
            String market = StockCodeUtils.getMarket(qryBean.getStockCode());
            if (market.equals(Market.SZSE.value))
                kdEnableQryBean.setSECU_ACC(kdGatewayConnection.getSzAccount());
            if (market.equals(Market.SHSE.value))
                kdEnableQryBean.setSECU_ACC(kdGatewayConnection.getShAccount());
            kdEnableQryBean.setMARKET(KDGatewayConverter.getKDMarket(market));
            kdEnableQryBean.setSECU_CODE(StockCodeUtils.getStockCode(qryBean.getStockCode()));
            kdEnableQryBean.setTRD_ID(getKdBsFlag(EntrustUtils.getBsFlag(qryBean.getEntrustProp(), qryBean.getEntrustBs())));
            kdEnableQryBean.setPRICE(String.valueOf(qryBean.getEntrustPrice()));
            head.setFunId("402");//取最大交易数量(402)
            setCommonParam(head, kdGatewayConnection);
            TradeClientResponse resp = Trade.callTradeService(head, JsonUtils.getJsonFromObject(kdEnableQryBean));
            if (resp.getResCode() == 0) {
                List<Map> respBody = resp.getResBody(Map.class);
                return KDGatewayConverter.convertEnable(respBody);
            } else {
                if (resp.getResCode() == 501)
                    return 0;
                logger.info("reqEnable error: {}", resp.getResMsg());
            }
        }
        return -1;
    }

    public List<IntegratedQuote> reqIntegratedQuotes(QuoteQryBean quoteQryBean) {
        KDGatewayConnection kdGatewayConnection = kdGatewayClient.getKdGatewayAccounts(quoteQryBean.getAccountId());
        if (Objects.nonNull(kdGatewayConnection)) {
            KDIntegratedQuote kdIntegratedQuote = new KDIntegratedQuote();
            kdIntegratedQuote.setYXYHDM(kdGatewayConnection.getUserCode());
            if (quoteQryBean.getQuoteType() == QuoteType.BULK_QUOTE.value
                    || QuoteType.AFTER_BULK_QUOTE.value == quoteQryBean.getQuoteType()) {
                if (!Strings.isNullOrEmpty(quoteQryBean.getStockCode()))
                    kdIntegratedQuote.setYXZQDM(StockCodeUtils.getStockCode(quoteQryBean.getStockCode()));
                if (!Strings.isNullOrEmpty(quoteQryBean.getBsFlag()))
                    kdIntegratedQuote.setYXMMLB(getKdBsFlag(quoteQryBean.getBsFlag()));
                head.setFunId("513");//意向行情查询（513）
            } else if (quoteQryBean.getQuoteType() == QuoteType.NON_PUBLIC_PROTOCOL_BACK_QUOTE.value) {
                // 上海质押式协议回购行情查询时，需要先进行 717 交易员的玉固收平台的连接
                head.setFunId("717");
                ProtocolSH protocolSH = new ProtocolSH();
                // 本方交易员代码
                if (!Strings.isNullOrEmpty(quoteQryBean.getLiaisonTel()))
                    protocolSH.setTRADER(quoteQryBean.getLiaisonTel());
                setCommonParam(head, kdGatewayConnection);
                TradeClientResponse resp = Trade.callTradeService(head, JsonUtils.getJsonFromObject(protocolSH));
                if (resp.getResCode() != 0) {
                    logger.warn("reqIntegratedQuotes 717 error  {}", resp.getResMsg());
                    return null;
                }
                head.setFunId("718");//非公开报价查询（718）
                // F:成交申报、 X:到期续做、 C:解除质押、 H:换券申报、 Z:提前终止
                kdIntegratedQuote.setORDER_TYPE("F");//报价类型
                if (!Strings.isNullOrEmpty(quoteQryBean.getOppositeLiaisonTel()))
                    kdIntegratedQuote.setTRADER_OUT(quoteQryBean.getOppositeLiaisonTel());//对手方交易员代码
                kdIntegratedQuote.setSECU_CODE(StockCodeUtils.getStockCode(quoteQryBean.getStockCode()));//
                kdIntegratedQuote.setORDER_QTY("");//
                kdIntegratedQuote.setRATE("");//
                kdIntegratedQuote.setPRICE("");//
                kdIntegratedQuote.setREPURCHASE_TERM("");//
            } else {
                IntegratedQuote integratedQuote = new IntegratedQuote();
                integratedQuote.setErrorCode(ErrorCode.TERMINAL_UN_SUPPORT_FUNCTION.value);
                integratedQuote.setErrorMsg(ErrorCode.TERMINAL_UN_SUPPORT_FUNCTION.errorMsg);
                List<IntegratedQuote> integratedQuotes = new ArrayList<>();
                integratedQuotes.add(integratedQuote);
                return integratedQuotes;
            }
            setCommonParam(head, kdGatewayConnection);
            logger.info("query quote head {} kdIntegratedQuote {}", JsonUtils.getJsonFromObject(head), JsonUtils.getJsonFromObject(kdIntegratedQuote));
            TradeClientResponse resp = Trade.callTradeService(head, JsonUtils.getJsonFromObject(kdIntegratedQuote));
            if (resp.getResCode() == 0) {
                List<Map> respBody = resp.getResBody(Map.class);
                return KDGatewayConverter.convertIntegratedQuote(respBody, quoteQryBean.getAccountId(), head.getFunId());
            } else {
                if (resp.getResCode() == 501)
                    return new ArrayList<>();
                logger.info("reqIntegratedQuotes error: {}", resp.getResMsg());
            }
        }
        return null;
    }

    public List<IntegratedBulkQuoteBean> reqIntegratedBulkQuotes(IntegratedBulkQuoteQryBean integratedBulkQuoteQryBean) {
        KDGatewayConnection kdGatewayConnection = kdGatewayClient.getKdGatewayAccounts(integratedBulkQuoteQryBean.getAccountId());
        if (Objects.nonNull(kdGatewayConnection)) {
            KDIntegratedQuote kdIntegratedQuote = new KDIntegratedQuote();
            kdIntegratedQuote.setYXYHDM(kdGatewayConnection.getUserCode());
            if (!Strings.isNullOrEmpty(integratedBulkQuoteQryBean.getStockCode()))
                kdIntegratedQuote.setYXZQDM(StockCodeUtils.getStockCode(integratedBulkQuoteQryBean.getStockCode()));
            if (!Strings.isNullOrEmpty(integratedBulkQuoteQryBean.getEntrustProp()) && !Strings.isNullOrEmpty(integratedBulkQuoteQryBean.getEntrustBs()))
                kdIntegratedQuote.setYXMMLB(getKdBsFlag(EntrustUtils.getBsFlag(integratedBulkQuoteQryBean.getEntrustProp(), integratedBulkQuoteQryBean.getEntrustBs())));
            head.setFunId("513");//意向行情查询（513）
            setCommonParam(head, kdGatewayConnection);
            logger.info("query quote head {} reqIntegratedBulkQuotes {}", JsonUtils.getJsonFromObject(head), JsonUtils.getJsonFromObject(integratedBulkQuoteQryBean));
            TradeClientResponse resp = Trade.callTradeService(head, JsonUtils.getJsonFromObject(integratedBulkQuoteQryBean));
            logger.info("reqIntegratedBulkQuotes result {}", JsonUtils.getJsonFromObject(resp));
            if (resp.getResCode() == 0) {
                List<Map> respBody = resp.getResBody(Map.class);
                return KDGatewayConverter.convertIntegratedBulkQuote(respBody, integratedBulkQuoteQryBean.getAccountId(), head.getFunId());
            } else {
                if (resp.getResCode() == 501)
                    return new ArrayList<>();
                logger.info("reqIntegratedQuotes error: {}", resp.getResMsg());
            }
        }
        return null;
    }

    public List<IntegratedBulkCloseQuoteBean> reqIntegratedBulkCloseQuotes(IntegratedBulkCloseQuoteQryBean integratedBulkCloseQuoteQryBean) {
        KDGatewayConnection kdGatewayConnection = kdGatewayClient.getKdGatewayAccounts(integratedBulkCloseQuoteQryBean.getAccountId());
        if (Objects.nonNull(kdGatewayConnection)) {
            KDIntegratedQuote kdIntegratedQuote = new KDIntegratedQuote();
            kdIntegratedQuote.setYXYHDM(kdGatewayConnection.getUserCode());
            if (!Strings.isNullOrEmpty(integratedBulkCloseQuoteQryBean.getStockCode()))
                kdIntegratedQuote.setYXZQDM(StockCodeUtils.getStockCode(integratedBulkCloseQuoteQryBean.getStockCode()));
           /* if (!Strings.isNullOrEmpty(integratedBulkCloseQuoteQryBean.getExchangeType()))
                kdIntegratedQuote.setYXMMLB(integratedBulkCloseQuoteQryBean.getExchangeType());*/
            head.setFunId("880");//上海盘后固定价格行情查询

            setCommonParam(head, kdGatewayConnection);
            logger.info("query quote head {} reqIntegratedBulkCloseQuotes {}", JsonUtils.getJsonFromObject(head), JsonUtils.getJsonFromObject(integratedBulkCloseQuoteQryBean));
            TradeClientResponse resp = Trade.callTradeService(head, JsonUtils.getJsonFromObject(integratedBulkCloseQuoteQryBean));
            logger.info("reqIntegratedBulkCloseQuotes result {}", JsonUtils.getJsonFromObject(resp));
            if (resp.getResCode() == 0) {
                List<Map> respBody = resp.getResBody(Map.class);
                return KDGatewayConverter.convertIntegratedBulkCloseQuote(respBody, integratedBulkCloseQuoteQryBean.getAccountId(), head.getFunId());
            } else {
                if (resp.getResCode() == 501)
                    return new ArrayList<>();
                logger.info("reqIntegratedBulkCloseQuotes error: {}", resp.getResMsg());
            }
        }
        return null;
    }

    public List<IntegratedRepurchaseQuoteBean> reqIntegratedRepurchaseQuotes(IntegratedRepurchaseQuoteQryBean integratedRepurchaseQuoteQryBean) {
        KDGatewayConnection kdGatewayConnection = kdGatewayClient.getKdGatewayAccounts(integratedRepurchaseQuoteQryBean.getAccountId());
        if (Objects.nonNull(kdGatewayConnection)) {
            KDIntegratedQuote kdIntegratedQuote = new KDIntegratedQuote();
            kdIntegratedQuote.setYXYHDM(kdGatewayConnection.getUserCode());
            if (integratedRepurchaseQuoteQryBean.getActionIn() == ActionIn.NON_PUBLIC_QUOTE.value) {
                // 上海质押式协议回购行情查询时，需要先进行 717 交易员的玉固收平台的连接
                head.setFunId("717");
                ProtocolSH protocolSH = new ProtocolSH();
                protocolSH.setTRADER(kdGatewayClient.getServerExtendInfo().getLiaisonTel());
                setCommonParam(head, kdGatewayConnection);
                TradeClientResponse res = Trade.callTradeService(head, JsonUtils.getJsonFromObject(protocolSH));
                if (res.getResCode() != 0) {
                    logger.warn("IntegratedRepurchaseQuoteBean 717 error  {}", res.getResMsg());
                    return null;
                }
                head.setFunId("718");//非公开报价查询（718）
                // F:成交申报、 X:到期续做、 C:解除质押、 H:换券申报、 Z:提前终止
                kdIntegratedQuote.setORDER_TYPE(KDGatewayConverter.getOrdtype(integratedRepurchaseQuoteQryBean.getEntrustProp()));//报价类型
                kdIntegratedQuote.setTRADER_OUT(kdGatewayClient.getServerExtendInfo().getOppositeLiaisonTel());//必填且首字母大写
                kdIntegratedQuote.setSECU_CODE("600000");//必填
                kdIntegratedQuote.setORDER_QTY("");//
                kdIntegratedQuote.setRATE("");//
                kdIntegratedQuote.setPRICE("");//
                kdIntegratedQuote.setREPURCHASE_TERM("");//

                setCommonParam(head, kdGatewayConnection);
                logger.info("query quote head {} reqIntegratedRepurchaseQuotes {}", JsonUtils.getJsonFromObject(head), JsonUtils.getJsonFromObject(kdIntegratedQuote));
                TradeClientResponse resp = Trade.callTradeService(head, JsonUtils.getJsonFromObject(kdIntegratedQuote));
                logger.info("reqIntegratedRepurchaseQuotes result {}", JsonUtils.getJsonFromObject(resp));
                if (resp.getResCode() == 0) {
                    List<Map> respBody = resp.getResBody(Map.class);
                    return KDGatewayConverter.convertIntegratedRepurchaseQuote(respBody, integratedRepurchaseQuoteQryBean.getAccountId(), head.getFunId());
                } else {
                    if (resp.getResCode() == 501)
                        return new ArrayList<>();
                    logger.info("reqIntegratedRepurchaseQuotes error: {}", resp.getResMsg());
                }
            } else {
                IntegratedFixedIncomeQuoteBean integratedFixedIncomeQuoteBean = new IntegratedFixedIncomeQuoteBean();
                integratedFixedIncomeQuoteBean.setErrorCode(ErrorCode.TERMINAL_UN_SUPPORT_FUNCTION.value);
                integratedFixedIncomeQuoteBean.setErrorMsg(ErrorCode.TERMINAL_UN_SUPPORT_FUNCTION.errorMsg);
                List<IntegratedFixedIncomeQuoteBean> integratedFixedIncomeQuoteBeans = new ArrayList<>();
                integratedFixedIncomeQuoteBeans.add(integratedFixedIncomeQuoteBean);
            }

        }
        return null;
    }

    public List<IntegratedFixedIncomeQuoteBean> reqIntegratedFixedIncomeQuotes(IntegratedFixedIncomeQuoteQryBean integratedFixedIncomeQuoteQryBean) {
        IntegratedFixedIncomeQuoteBean integratedFixedIncomeQuoteBean = new IntegratedFixedIncomeQuoteBean();
        integratedFixedIncomeQuoteBean.setErrorCode(ErrorCode.TERMINAL_UN_SUPPORT_FUNCTION.value);
        integratedFixedIncomeQuoteBean.setErrorMsg(ErrorCode.TERMINAL_UN_SUPPORT_FUNCTION.errorMsg);
        List<IntegratedFixedIncomeQuoteBean> integratedFixedIncomeQuoteBeans = new ArrayList<>();
        integratedFixedIncomeQuoteBeans.add(integratedFixedIncomeQuoteBean);
        return integratedFixedIncomeQuoteBeans;
    }

    public List<AccountFinance> reqCustomerFinance(String accountId) {
        KDGatewayConnection kdGatewayConnection = kdGatewayClient.getKdGatewayAccounts(accountId);
        if (Objects.nonNull(kdGatewayConnection)) {
            KDCustomerFinance kdCustomerFinance = new KDCustomerFinance();
            kdCustomerFinance.setUSER_CODE(kdGatewayConnection.getUserCode());
            head.setFunId("502");//资金查询(502)
            setCommonParam(head, kdGatewayConnection);
            TradeClientResponse resp = Trade.callTradeService(head, JsonUtils.getJsonFromObject(kdCustomerFinance));
            if (resp.getResCode() == 0) {
                List<Map> respBody = resp.getResBody(Map.class);
                return KDGatewayConverter.convertCustomerFinance(respBody, accountId);
            } else {
                if (resp.getResCode() == 501)
                    return new ArrayList<>();
                logger.info("reqCustomerFinance error: {}", resp.getResMsg());
            }
        }
        return null;
    }


    public List<PositionBean> queryAccountPosition(PositionQueryBean positionQueryBean) {
        KDGatewayConnection kdGatewayConnection = kdGatewayClient.getKdGatewayAccounts(positionQueryBean.getAccountId());
        if (Objects.nonNull(kdGatewayConnection)) {
            KDPositionQuery kdPositionQuery = new KDPositionQuery();
            kdPositionQuery.setUSER_CODE(kdGatewayConnection.getUserCode());
            if (!Strings.isNullOrEmpty(positionQueryBean.getStockCode())) {
                kdPositionQuery.setSECU_CODE(String.valueOf(positionQueryBean.getPageSize()));
            }
            head.setFunId("504");//持仓查询(504)
            setCommonParam(head, kdGatewayConnection);
            TradeClientResponse resp = Trade.callTradeService(head, JsonUtils.getJsonFromObject(kdPositionQuery));
            if (resp.getResCode() == 0) {
                List<Map> respBody = resp.getResBody(Map.class);
                return KDGatewayConverter.convertAccountPosition(respBody, positionQueryBean);
            } else {
                if (resp.getResCode() == 501)
                    return new ArrayList<>();
                logger.info("queryAccountPosition error: {}", resp.getResMsg());
            }
        } else {
            logger.info("queryAccountPosition error, not found kdGatewayConnection");
        }
        return null;
    }

    public void queryIncrementKDTrade(KDGatewayConnection kdGatewayConnection) {
        queryIncrementKDTrade(kdGatewayConnection, false);
    }

    public List<Trade> queryIncrementKDTrade(KDGatewayConnection kdGatewayConnection, boolean reissueFlag) {
        KDTradeQuery kdTradeQuery = new KDTradeQuery();
        kdTradeQuery.setUSER_CODE(kdGatewayConnection.getUserCode());
        kdTradeQuery.setRECORD_SN(kdGatewayConnection.getTradeRecordNo());
        kdTradeQuery.setMARKETS("");
        kdTradeQuery.setSECU_ACC("");
        kdTradeQuery.setSECU_CODE("");
        kdTradeQuery.setTRD_ID("");
        kdTradeQuery.setORDER_ID("");
        kdTradeQuery.setACCOUNT("");
        kdTradeQuery.setBOARDS("");
        kdTradeQuery.setIS_WITHDRAW("");
        kdTradeQuery.setR_COUNT("");
        kdTradeQuery.setSECU_CLS("");
        kdTradeQuery.setIS_FILTER_HKT("0");
        kdTradeQuery.setIS_WITHDRAW("");
        kdTradeQuery.setOUT_ORDER_FLAG("");
        setCommonParam(head, kdGatewayConnection);
        head.setFunId("376");//当日成交查询（376）
        TradeClientResponse resp = Trade.callTradeService(head, JsonUtils.getJsonFromObject(kdTradeQuery));
        if (resp.getResCode() == 0) {
            List<Map> respBody = resp.getResBody(Map.class);
            List<Trade> trades = KDGatewayConverter.convertTradeList(respBody, kdGatewayConnection.getAccountId());
            if (!reissueFlag) {
                trades.forEach(trade -> kdGatewayClient.onTrade(trade));
            }
            if (respBody.size() > 0) {
                String newPositionStr = String.valueOf(respBody.get(respBody.size() - 1).get("RECORD_SN"));
                kdGatewayConnection.setTradeRecordNo(newPositionStr);
                logger.info("queryIncrementKDTrade newPositionStr {}", newPositionStr);
            }
        } else {
            logger.info("queryIncrementKDTrade error: {}", resp.getResMsg());
        }
        return new ArrayList<>();
    }

    public void queryIncrementKDEntrust(KDGatewayConnection kdGatewayConnection) {
        queryIncrementKDEntrust(kdGatewayConnection, false);
    }

    public List<Entrust> queryIncrementKDEntrust(KDGatewayConnection kdGatewayConnection, boolean reissueFlag) {
        KDEntrust kdEntrust = new KDEntrust();
        kdEntrust.setGET_ORDERS_MODE("6");//查询委托模式  6:去除撤单记录
        kdEntrust.setCUSTOMER(kdGatewayConnection.getUserCode());
        kdEntrust.setRECORD_SN(kdGatewayConnection.getEntrustRecordNo());
        kdEntrust.setMARKET("");
        kdEntrust.setSECU_ACC("");
        kdEntrust.setSECU_CODE("");
        kdEntrust.setTRD_ID("");
        kdEntrust.setTRD_IDS("");
        kdEntrust.setBIZ_NO("");
        kdEntrust.setACCOUNT("");
        setCommonParam(head, kdGatewayConnection);
        head.setFunId("374");//当日委托查询（374）
        TradeClientResponse resp = Trade.callTradeService(head, JsonUtils.getJsonFromObject(kdEntrust));
        if (resp.getResCode() == 0) {
            List<Map> respBody = resp.getResBody(Map.class);
            List<Entrust> entrusts = KDGatewayConverter.convertEntrustList(respBody, kdGatewayConnection.getAccountId(), entrustIdManager);
            if (!reissueFlag) {
                entrusts.forEach(entrust -> {
                    if (!Strings.isNullOrEmpty(entrust.getId())) {
                        if (entrust.getId().startsWith(IDPrefix.NORMAL.value))
                            kdGatewayClient.onEntrust(entrust);
                    } else {
                        IntegratedEntrust integratedEntrust = new IntegratedEntrust();
                        BeanUtils.copyProperties(entrust, integratedEntrust);
                        integratedEntrust.setId(entrustIdManager.getIdByBrokeEntrustId(integratedEntrust.getAccountId(), integratedEntrust.getBrokerEntrustId()));
                        kdGatewayClient.onIntegratedEntrust(integratedEntrust);
                    }
                });
            }
            if (respBody.size() > 0) {
                String newPositionStr = String.valueOf(respBody.get(respBody.size() - 1).get("RECORD_SN"));
                kdGatewayConnection.setEntrustRecordNo(newPositionStr);
                logger.info("queryIncrementKDEntrust newPositionStr {}", newPositionStr);
            }
            return entrusts;
        } else {
            logger.info("queryIncrementKDEntrust error: {}", resp.getResMsg());
        }
        return new ArrayList<>();
    }

    /***
     * @Description: 债券质押率返回
     * @Param: [accountId, market, stockCode]
     * @return: java.util.List<com.joyintech.tams.exchange.common.bean.PledgeDiscount>
     * @Author: MAXX
     * @Date: 2020/5/14
     */
    public List<PledgeDiscount> queryPledgeDiscount(String accountId, String market, String stockCode) {
        TradeClientResponse resp = this.queryDiscountAndBonds(accountId, market, stockCode);
        if (resp.getResCode() == 0) {
            List<Map> respBody = resp.getResBody(Map.class);
            return KDGatewayConverter.convertPledgeDiscount(respBody, accountId);
        }
        if (resp.getResCode() == 501) {
            return new ArrayList<PledgeDiscount>();
        }
        logger.error("queryPledgeDiscount error: {}", resp.getResMsg());
        return null;
    }

    /***
     * @Description: 标准券查询
     * @Param: [accountId, market, stockCode]
     * @return: java.util.List<com.joyintech.tams.exchange.common.bean.StandardBonds>
     * @Author: MAXX
     * @Date: 2020/5/14
     */
    public List<StandardBonds> queryStandardBonds(StandardBondQryBean standardBondQryBean) {
        TradeClientResponse resp = this.queryDiscountAndBonds(standardBondQryBean.getAccountId(), standardBondQryBean.getMarket(), standardBondQryBean.getStockCode());
        if (resp.getResCode() == 0) {
            List<Map> respBody = resp.getResBody(Map.class);
            return KDGatewayConverter.convertStandardBonds(respBody, standardBondQryBean.getAccountId());
        }
        if (resp.getResCode() == 501) {
            return new ArrayList<StandardBonds>();
        }
        logger.error("queryStandardBonds error: {}", resp.getResMsg());
        return null;
    }

    /***
     * @Description: 查询已质押的债券
     * @Param: [accountId, market, stockCode]
     * @return: com.pingan.stock.client.TradeClientResponse
     * @Author: MAXX
     * @Date: 2020/5/14
     */
    public TradeClientResponse queryDiscountAndBonds(String accountId, String market, String stockCode) {
        KDGatewayConnection kdGatewayConnection = kdGatewayClient.getKdGatewayAccounts(accountId);
        if (Objects.isNull(kdGatewayConnection)) {
            logger.error("queryDiscountAndBonds error kdGatewayConnection is null,accountId:{}", accountId);
            return null;
        }
        String[] marketAndStockCode = new String[2];
        String marketStr = market;
        if (!Strings.isNullOrEmpty(stockCode)) {
            marketAndStockCode = StockCodeUtils.getMarketAndStockCode(stockCode);
            marketStr = marketAndStockCode[1];
            if (!Strings.isNullOrEmpty(market) && !market.equals(marketAndStockCode[1])) {
                logger.error("queryDiscountAndBonds failed. market:{},stockCode:{}", market, stockCode);
                return null;
            }
        }
        KDEntrust kdEntrust = new KDEntrust();
        kdEntrust.setCUSTOMER(kdGatewayConnection.getUserCode());
        if (Market.SHSE.value.equals(marketStr)) {
            kdEntrust.setSECU_ACC(kdGatewayConnection.getShAccount());
        } else if (Market.SZSE.value.equals(marketStr)) {
            kdEntrust.setSECU_ACC(kdGatewayConnection.getSzAccount());
        }
        head.setFunId("367");//查询已质押的债券
        setCommonParam(head, kdGatewayConnection);
        TradeClientResponse resp = Trade.callTradeService(head, JsonUtils.getJsonFromObject(kdEntrust));
        return resp;
    }

    public Map<String, EntrustInterface> reqReissueEntrust(String accountId, Long requestId) {
        KDGatewayConnection kdGatewayConnection = kdGatewayClient.getKdGatewayAccounts(accountId);
        Map<String, EntrustInterface> entrustMap = new HashMap<>();
        if (Objects.nonNull(kdGatewayConnection)) {
            List<Entrust> entrusts = queryIncrementKDEntrust(kdGatewayConnection, true);
            ;
            entrusts.forEach(entrust -> {
                if (Strings.isNullOrEmpty(entrust.getId())) {
                    entrust.setId(entrustIdManager.getIdByBrokeEntrustId(entrust.getAccountId(), entrust.getBrokerEntrustId()));
                }
                if (!Strings.isNullOrEmpty(entrust.getId())) {
                    entrustMap.put(entrust.getId(), entrust);
                }
            });
        }
        return entrustMap;
    }


    public List<Trade> reqReissueTrade(String accountId, Long requestId) {
        KDGatewayConnection kdGatewayConnection = kdGatewayClient.getKdGatewayAccounts(accountId);
        if (Objects.nonNull(kdGatewayConnection)) {
            return queryIncrementKDTrade(kdGatewayConnection, true);
        }
        return new ArrayList<>();
    }

    public void setEntrustIdManager(EntrustIdManager entrustIdManager) {
        this.entrustIdManager = entrustIdManager;
    }
}
