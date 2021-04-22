package com.joyintech.tams.exchange.terminal.trade.ctp;

import com.google.common.base.Strings;
import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.bean.ServerExtendInfo;
import com.joyintech.tams.exchange.common.constant.EntrustBs;
import com.joyintech.tams.exchange.common.constant.EntrustProp;
import com.joyintech.tams.exchange.common.constant.ErrorCode;
import com.joyintech.tams.exchange.common.db.domain.*;
import com.joyintech.tams.exchange.common.utils.StockCodeUtils;
import com.joyintech.tams.exchange.terminal.constant.AccountLoginStatus;
import net.jctp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static net.jctp.JctpConstants.*;

public class CTPTradeApi {
    private static final Logger logger = LoggerFactory.getLogger(CTPTradeApi.class);
    private TraderApi ctpApi;

    public CTPTradeApi(TraderApi api) {
        ctpApi = api;
    }

    private AtomicInteger status = new AtomicInteger(AccountLoginStatus.NOT_LOGGED.value);

    public int getStatus() {
        return status.get();
    }

    private Account account;
    private ServerExtendInfo serverExtendInfo;

    public void setStatus(AccountLoginStatus status) {
        this.status.set(status.value);
    }

    public TraderApi getCtpApi() {
        return ctpApi;
    }

    public void setCtpApi(TraderApi ctpApi) {
        this.ctpApi = ctpApi;
    }

    public ResultBean init(String addr) {
        try {
            ctpApi.SubscribePrivateTopic(JctpConstants.THOST_TERT_QUICK);
            ctpApi.SubscribePublicTopic(JctpConstants.THOST_TERT_QUICK);
            logger.info("建立链接: {}", addr);
            ctpApi.SyncConnect(addr);
            logger.info("建立链接结果: {}", addr);
            return new ResultBean(ErrorCode.SUCCESS, addr);
        } catch (Exception e) {
            logger.error("初始化异常:{}", e);
            return new ResultBean(ErrorCode.TERMINAL_API_ERROR, e.getMessage());
        }
    }

    public ResultBean syncAuthenticate(String authCode, String appId, String userProductInfo, String brockerId, String userId) {
        CThostFtdcReqAuthenticateField reqAuthenticateField = new CThostFtdcReqAuthenticateField();
        reqAuthenticateField.AuthCode = authCode;
        reqAuthenticateField.AppID = appId;
        reqAuthenticateField.UserProductInfo = userProductInfo;
        reqAuthenticateField.BrokerID = brockerId;
        reqAuthenticateField.UserID = userId;
        CThostFtdcRspAuthenticateField rspAuthenticateField = null;

        try {
            rspAuthenticateField = ctpApi.SyncReqAuthenticate(reqAuthenticateField);
            return new ResultBean(ErrorCode.SUCCESS, rspAuthenticateField);
        } catch (Exception e) {
            logger.error("同步登录验证报送异常:{}", e);
            return new ResultBean(ErrorCode.TERMINAL_API_ERROR, e.getMessage());
        }
    }

    public ResultBean syncLogin(Account account, ServerExtendInfo serverExtendInfo) {
        try {
            logger.info("同步穿透式监管报送: {}", account.getFinanceAccount());
            ResultBean resultBean = syncAuthenticate(serverExtendInfo.getAuthCode(), serverExtendInfo.getAppId(), serverExtendInfo.getUserProductInfo(), account.getBrokerId(), account.getFinanceAccount());
            if (ErrorCode.SUCCESS.value != resultBean.getErrorCode()) {
                return resultBean;
            }
            logger.info("同步穿透式监管报送结果: {}", resultBean.getData());

            CThostFtdcReqUserLoginField loginField = new CThostFtdcReqUserLoginField();
            loginField.BrokerID = account.getBrokerId();
            loginField.UserID = account.getFinanceAccount();
            loginField.Password = account.getPwd();

            logger.info("同步登录系统: {}", loginField);
            CThostFtdcRspUserLoginField rspUserLoginField = null;
            rspUserLoginField = ctpApi.SyncReqUserLogin(loginField);
            logger.info("同步登录系统结果: {}", rspUserLoginField);
            this.account = account;
            this.serverExtendInfo = serverExtendInfo;
            if (syncSettlementInfoConfirm(account)) {
                status.set(AccountLoginStatus.LOGGED.value);
            } else {
                logger.error("同步确认结算单失败，等着状态为：{}", status.get());
            }

            return new ResultBean(ErrorCode.SUCCESS, rspUserLoginField);
        } catch (Exception e) {
            logger.error("登录报送异常:{}", e);
            return new ResultBean(ErrorCode.TERMINAL_API_ERROR, e.getMessage());
        }

    }

    public void reAuthenticate() {
        logger.info("断线重连,穿透式监管报送: {}", account.getFinanceAccount());
        CThostFtdcReqAuthenticateField reqAuthenticateField = new CThostFtdcReqAuthenticateField();
        reqAuthenticateField.AuthCode = serverExtendInfo.getAuthCode();
        reqAuthenticateField.AppID = serverExtendInfo.getAppId();
        reqAuthenticateField.UserProductInfo = serverExtendInfo.getUserProductInfo();
        reqAuthenticateField.BrokerID = serverExtendInfo.getBrockerId();
        reqAuthenticateField.UserID = account.getFinanceAccount();
        try {
            ctpApi.ReqAuthenticate(reqAuthenticateField);
        } catch (Exception e) {
            logger.error("断线重连,登录验证报送异常:{}", e);
        }
    }

    public void reLogin() {

        CThostFtdcReqUserLoginField loginField = new CThostFtdcReqUserLoginField();
        loginField.BrokerID = account.getBrokerId();
        loginField.UserID = account.getFinanceAccount();
        loginField.Password = account.getPwd();

        logger.info("断线重连,登录系统: {}", loginField);
        try {
            ctpApi.ReqUserLogin(loginField);
        } catch (Exception e) {
            logger.error("断线重连,登录异常:{}", e);
        }
    }

    //为空查询全部
    public void qryInstrument(String symbol, String exchangeId, Long requestId) throws Exception {
        CThostFtdcQryInstrumentField qryInstrumentField = new CThostFtdcQryInstrumentField();
        qryInstrumentField.InstrumentID = symbol;
        qryInstrumentField.ExchangeID = exchangeId;

        logger.info("查询合约: {}", symbol);
        ctpApi.ReqQryInstrument(qryInstrumentField, requestId.intValue());
    }

    public List<FuturesInstrument> syncQryInstrument(String symbol, String exchangeId) {
        CThostFtdcQryInstrumentField qryInstrumentField = new CThostFtdcQryInstrumentField();
        qryInstrumentField.InstrumentID = symbol;
        qryInstrumentField.ExchangeID = exchangeId;

        logger.info("同步查询合约: {}", symbol);
        try {
            return CTPConverter.convertToFuturesInstrument(Arrays.asList(ctpApi.SyncAllReqQryInstrument(qryInstrumentField)));
        } catch (Exception e) {
            logger.error("同步查询合约报送异常:{}", e);
        }
        return new ArrayList<>();
    }

    public CThostFtdcOrderField[] syncQryOrder(Account account) {
        CThostFtdcQryOrderField qryOrderField = new CThostFtdcQryOrderField();
        qryOrderField.BrokerID = account.getBrokerId();
        qryOrderField.InvestorID = account.getFinanceAccount();
        logger.info("同步查询报单: {}", qryOrderField);
        try {
            CThostFtdcOrderField[] orderFields = ctpApi.SyncAllReqQryOrder(qryOrderField);
            logger.info("同步查询报单结果: {}", orderFields.length);
            return orderFields;
        } catch (Exception e) {
            logger.error("同步查询报单报送异常:{}", e);
            return null;
        }
    }

    public void qryOrder(Account account) {
        CThostFtdcQryOrderField qryOrderField = new CThostFtdcQryOrderField();
        qryOrderField.BrokerID = account.getBrokerId();
        qryOrderField.InvestorID = account.getFinanceAccount();

        logger.info("查询报单: {}", qryOrderField);
        try {
            int ret = ctpApi.ReqQryOrder(qryOrderField);
            logger.info("查询报单结果: {}", ret);
        } catch (Exception e) {
            logger.error("查询报单报送异常:{}", e);
        }
    }

    public CThostFtdcTradeField[] syncQryTrade(Account account) {
        CThostFtdcQryTradeField qryTradeField = new CThostFtdcQryTradeField();
        qryTradeField.BrokerID = account.getBrokerId();
        qryTradeField.InvestorID = account.getFinanceAccount();

        logger.info("同步查询成交: {}", qryTradeField);
        try {
            CThostFtdcTradeField[] tradeFields = ctpApi.SyncAllReqQryTrade(qryTradeField);
            logger.info("同步查询成交结果: {}", tradeFields.length);
            return tradeFields;
        } catch (Exception e) {
            logger.error("同步查询成交报送异常:{}", e);
            return null;
        }
    }

    public void qryTrade(Account account) {
        CThostFtdcQryTradeField qryTradeField = new CThostFtdcQryTradeField();
        qryTradeField.BrokerID = account.getBrokerId();
        qryTradeField.InvestorID = account.getFinanceAccount();

        logger.info("查询成交: {}", qryTradeField);
        try {
            int ret = ctpApi.ReqQryTrade(qryTradeField);
            logger.info("查询成交结果: {}", ret);
        } catch (Exception e) {
            logger.error("查询成交报送异常:{}", e);
        }
    }

    public boolean syncSettlementInfoConfirm(Account account) {
        try {
            CThostFtdcSettlementInfoConfirmField settlementInfoConfirmField = new CThostFtdcSettlementInfoConfirmField(account.getBrokerId(), account.getFinanceAccount(), ctpApi.GetTradingDay(), "", 0, null, null);
            logger.info("同步确认结算单: {}", settlementInfoConfirmField);
            settlementInfoConfirmField = ctpApi.SyncReqSettlementInfoConfirm(settlementInfoConfirmField);
            logger.info("同步确认结算单结果: {}", settlementInfoConfirmField);
            return true;
        } catch (Exception e) {
            logger.error("同步确认结算单报送异常:{}", e);
            return false;
        }
    }

    private ResultBean placeOrder(Account account, String instrumentId, String orderRef, String direction, String offset, long volume, double price, String hedgeFlag, String entrustProp) {
        CThostFtdcInputOrderField inputOrderField = new CThostFtdcInputOrderField();
        inputOrderField.BrokerID = account.getBrokerId();
        inputOrderField.InvestorID = account.getFinanceAccount();
        inputOrderField.InstrumentID = instrumentId;

        if (!Strings.isNullOrEmpty(orderRef)) {
            inputOrderField.OrderRef = orderRef;
        }
        inputOrderField.UserID = account.getFinanceAccount();
        inputOrderField.MinVolume = 1;
        inputOrderField.ForceCloseReason = JctpConstants.THOST_FTDC_FCC_NotForceClose; //强平原因: 非强平
        inputOrderField.IsAutoSuspend = false; //自动挂起标志: 不挂起
        inputOrderField.UserForceClose = false; //用户强评标志: 否
        inputOrderField.TimeCondition = JctpConstants.THOST_FTDC_TC_GFD; //当日有效
        inputOrderField.StopPrice = 0; //止损价
        if (!Strings.isNullOrEmpty(entrustProp)) {
            if (EntrustProp.NORMAL.value.equals(entrustProp)) {
                inputOrderField.OrderPriceType = JctpConstants.THOST_FTDC_OPT_LimitPrice; //限价
                inputOrderField.LimitPrice = price;
                inputOrderField.TimeCondition = JctpConstants.THOST_FTDC_TC_GFD;
            } else {
                inputOrderField.OrderPriceType = entrustProp.replace("FU", "").charAt(0);
                inputOrderField.LimitPrice = 0;
                inputOrderField.TimeCondition = JctpConstants.THOST_FTDC_TC_IOC;
            }
        }

        //买卖标志
        if (EntrustBs.BUY.value.equals(direction)) {
            inputOrderField.Direction = THOST_FTDC_D_Buy;
        } else if (EntrustBs.SELL.value.equals(direction)) {
            inputOrderField.Direction = THOST_FTDC_D_Sell;
        }

        //开平标志
        inputOrderField.CombOffsetFlag = offset;

        inputOrderField.CombHedgeFlag = hedgeFlag;
        inputOrderField.ContingentCondition = THOST_FTDC_CC_Immediately; //立即触发
        inputOrderField.VolumeCondition = THOST_FTDC_VC_AV; //任意数量成交
        inputOrderField.VolumeTotalOriginal = (int) volume; //数量

        try {
            logger.info("提交现价单: {}", inputOrderField);
            int ret = ctpApi.ReqOrderInsert(inputOrderField);
            logger.info("提交现价单结果: {}", ret);
            return new ResultBean(ErrorCode.SUCCESS);
        } catch (Exception e) {
            logger.error("报单提交异常:{}", e);
            return new ResultBean(ErrorCode.TERMINAL_API_ERROR, e.getMessage());
        }
    }

    public ResultBean orderAction(Account account, CancelEntrustBean cancelEntrustBean) {
        CThostFtdcInputOrderActionField inputOrderActionField = new CThostFtdcInputOrderActionField();
        inputOrderActionField.BrokerID = account.getBrokerId();
        inputOrderActionField.InvestorID = account.getFinanceAccount();
        inputOrderActionField.ExchangeID = StockCodeUtils.getMarket(cancelEntrustBean.getStockCode());
        inputOrderActionField.OrderSysID = CTPConverter.formatSysId(cancelEntrustBean.getBrokerEntrustId(), 12);
        inputOrderActionField.ActionFlag = '0';
        try {
            logger.info("提交撤单: {}", inputOrderActionField);
            int ret = ctpApi.ReqOrderAction(inputOrderActionField);
            logger.info("提交撤单结果: {}", ret);
            if (0 <= ret) {
                return new ResultBean(ErrorCode.SUCCESS, inputOrderActionField);
            } else {
                return new ResultBean(ErrorCode.TERMINAL_API_ERROR, ret);
            }
        } catch (Exception e) {
            logger.error("撤单报送异常:{}", e);
            return new ResultBean(ErrorCode.TERMINAL_API_ERROR, e.getMessage());
        }
    }

    public void reqQryInvestorPosition(Account account, String instrument, Long requestId) {
        CThostFtdcQryInvestorPositionField investorPositionField = new CThostFtdcQryInvestorPositionField();
        investorPositionField.BrokerID = account.getBrokerId();
        investorPositionField.InvestorID = account.getFinanceAccount();
        if (!Strings.isNullOrEmpty(instrument)) {
            investorPositionField.InstrumentID = instrument;
        }
        try {
            logger.info("提交持仓查询: {}", investorPositionField);
            ctpApi.ReqQryInvestorPosition(investorPositionField, requestId.intValue());
        } catch (Exception e) {
            logger.error("持仓查询报送异常:{}", e);
        }
    }

    public void reqQryInvestorPositionDetail(Account account, String instrument, Long requestId) {
        CThostFtdcQryInvestorPositionDetailField positionDetailField = new CThostFtdcQryInvestorPositionDetailField();
        positionDetailField.BrokerID = account.getBrokerId();
        positionDetailField.InvestorID = account.getFinanceAccount();
        if (!Strings.isNullOrEmpty(instrument)) {
            positionDetailField.InstrumentID = instrument;
        }
        try {
            logger.info("提交持仓明细查询: {}", positionDetailField);
            ctpApi.ReqQryInvestorPositionDetail(positionDetailField, requestId.intValue());
        } catch (Exception e) {
            logger.error("持仓查询明细报送异常:{}", e);
        }
    }

    public void reqQryInstrumentCommissionRate(Account account, String instrument, Long requestId) {
        CThostFtdcQryInstrumentCommissionRateField commissionRateField = new CThostFtdcQryInstrumentCommissionRateField();
        commissionRateField.BrokerID = account.getBrokerId();
        commissionRateField.InvestorID = account.getFinanceAccount();
        if (!Strings.isNullOrEmpty(instrument)) {
            commissionRateField.InstrumentID = instrument;
        }
        try {
            logger.info("提交合约手续费率查询: {}", commissionRateField);
            ctpApi.ReqQryInstrumentCommissionRate(commissionRateField, requestId.intValue());
        } catch (Exception e) {
            logger.error("合约手续费率查询报送异常:{}", e);
        }
    }

    public void reqQryInstrumentMarginRate(Account account, String instrument, Long requestId) {
        CThostFtdcQryInstrumentMarginRateField marginRateField = new CThostFtdcQryInstrumentMarginRateField();
        marginRateField.BrokerID = account.getBrokerId();
        marginRateField.InvestorID = account.getFinanceAccount();
        if (!Strings.isNullOrEmpty(instrument)) {
            marginRateField.InstrumentID = instrument;
        }
        try {
            logger.info("提交合约保证金率查询: {}", marginRateField);
            ctpApi.ReqQryInstrumentMarginRate(marginRateField, requestId.intValue());
        } catch (Exception e) {
            logger.error("保证金率查询报送异常:{}", e);
        }
    }

    public List<FuturesInstrumentMarginRateBean> syncQryInstrumentMarginRate(Account account, String instrument) throws Exception {
        CThostFtdcQryInstrumentMarginRateField marginRateField = new CThostFtdcQryInstrumentMarginRateField();
        marginRateField.BrokerID = account.getBrokerId();
        marginRateField.InvestorID = account.getFinanceAccount();
        if (!Strings.isNullOrEmpty(instrument)) {
            marginRateField.InstrumentID = instrument;
        }
        logger.info("提交同步合约保证金率查询: {}", marginRateField);
        CThostFtdcInstrumentMarginRateField[] marginRateFields = ctpApi.SyncAllReqQryInstrumentMarginRate(marginRateField);
        return CTPConverter.convertToFuturesInstrumentMarginRate(Arrays.asList(marginRateFields), account.getId());
    }

    public ResultBean entrust(Account account, Entrust entrust, Long requestId) {
        return placeOrder(account, StockCodeUtils.getStockCode(entrust.getStockCode()), entrust.getLocalEntrustId(), entrust.getEntrustBs(), entrust.getOffsetFlag(), entrust.getEntrustVolume(), entrust.getEntrustPrice().doubleValue(), entrust.getHedgeFlag(), entrust.getEntrustProp());
    }
}
