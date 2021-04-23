package com.joyintech.tams.exchange.terminal.trade.ufx;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.hundsun.t2sdk.common.share.dataset.DatasetService;
import com.hundsun.t2sdk.interfaces.share.dataset.IDataset;
import com.hundsun.t2sdk.interfaces.share.dataset.IDatasets;
import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.constant.AgentType;
import com.joyintech.tams.exchange.common.constant.EntrustStatus;
import com.joyintech.tams.exchange.common.constant.ErrorCode;
import com.joyintech.tams.exchange.common.constant.Prefixion;
import com.joyintech.tams.exchange.common.db.domain.*;
import com.joyintech.tams.exchange.terminal.constant.AccountLoginStatus;
import com.joyintech.tams.exchange.terminal.trade.TradeResponseImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class UfxTradeHandler {
    private final static Logger logger = LoggerFactory.getLogger(UfxTradeHandler.class);
    private Map<String, LoginInfo> loginInfoMap = Maps.newConcurrentMap();//账户 登录信息
    private UfxTradeApi ufxTradeApi;
    private TradeResponseImpl ufxTradeResponse;
    private UfxTradeClientImpl ufxTradeClient;

    public TradeResponseImpl getUfxTradeResponse() {
        return ufxTradeResponse;
    }

    public void setUfxTradeResponse(TradeResponseImpl ufxTradeResponse) {
        this.ufxTradeResponse = ufxTradeResponse;
    }

    public Map<String, LoginInfo> getLoginInfoMap() {
        return loginInfoMap;
    }


    /***
     * @Description: 登录回调
     * @Param: [result, errorCode, errorInfo]
     * @return: void
     * @Author: MAXX
     * @Date: 2020/3/13
     */
    public void onLogin(IDatasets result, int errorCode, String errorInfo) {
        logger.info("onLogin result: {} - errorCode: {}", result, errorCode);
        IDataset ds = result.getDataset(0);
        String fundAccount = ds.getString("fund_account");
        Account account = ufxTradeClient.getAccountByFinanceAccount(fundAccount);
        if (errorCode != ErrorCode.SUCCESS.value) {
            logger.warn("account:{} login failed reason:{}", fundAccount, errorInfo);
            ufxTradeResponse.resLogin(new ResultBean(ErrorCode.TERMINAL_API_ERROR.value, errorInfo), (account == null ? null : account.getId()));
            return;
        }
        LoginInfo loginInfo = UfxConverter.parseLoginMsg(ds, fundAccount);
        try {
            List<ClientStkacct> clientStkacctList = ufxTradeApi.ReqClientStkacct(account.getPwd(), loginInfo.getBranchNo(), loginInfo.getClientId(), loginInfo.getUserToken());
            if (clientStkacctList != null) {
                logger.info("clientStkacctList:{}", JSON.toJSONString(clientStkacctList).toString());
                loginInfo.setClientStkacctList(clientStkacctList);
            }
            loginInfoMap.put(fundAccount, loginInfo);
            //TODO::登录成功后，订阅委托成交主推
            //ufxTradeHandler.ufxMD.ReqSubTradeReturn(loginInfo.getFundAccount(), loginInfo.getBranchNo());//订阅-成交主推-证券成交回报
            // ufxTradeHandler.ufxMD.ReqSubOrderReturn(loginInfo.getFundAccount(), loginInfo.getBranchNo()); //订阅-成交主推-证券委托回报
            ResultBean resultBean = new ResultBean(ErrorCode.SUCCESS.value, "ufx reqLogin SUCCESS");
            resultBean.setData(account);
            ufxTradeResponse.resLogin(resultBean, account.getId());
            ufxTradeApi.setStatus(account.getId(), AccountLoginStatus.LOGGED);
        } catch (Exception e) {
            logger.error("ReqClientStkacct failed. reason:{}", e);
            ufxTradeResponse.resLogin(new ResultBean(ErrorCode.TERMINAL_API_ERROR.value, e.getMessage()), account.getId());
        }
    }

    /***
     * @Description: 委托下单返回
     * @Param: [result, errorCode, errorInfo]
     * @return: void
     * @Author: MAXX
     * @Date: 2020/3/27
     */
    public void onOrderAck(IDatasets result, int errorCode, String errorInfo, String id) {
        IDataset ds = result.getDataset(0);
        id = Prefixion.ENTRUST_PREFIXION.value + id;
        logger.info("onOrderAck result:  - errorCode: {} id:{}", errorCode, id);
        DatasetService.printDataset(ds);
        Entrust entrust = new Entrust();
        entrust.setId(id);
        entrust.setAccountId(ufxTradeClient.getAccountIdByEntrustId(id));
        if (errorCode != ErrorCode.SUCCESS.value) {
            logger.warn("onOrderAck failed reason:{}", ds.getString("error_info"));
            entrust.setErrorCode(errorCode);
            entrust.setEntrustStatus(EntrustStatus.INVALID.value);
            entrust.setErrorMsg(ds.getString("error_info"));
            ufxTradeResponse.resEntrust(entrust);
            return;
        }
        UfxConverter.entrustReBack(entrust, ds);
        ufxTradeResponse.resEntrust(entrust);
    }

    /***
     * @Description: 委托撤单
     * @Param: [result, errorCode, errorInfo, requestId, entrustNo]
     * @return: void
     * @Author: MAXX
     * @Date: 2020/3/27
     */
    public void onCancelOrder(IDatasets result, int errorCode, String errorInfo, String id) {
        IDataset ds = result.getDataset(0);
        id = Prefixion.ENTRUST_PREFIXION.value + id;
        logger.info("onCancelOrder result:  - errorCode: {}  id:{}", errorCode, id);
        DatasetService.printDataset(ds);
        CancelEntrustBean cancelEntrustBean = new CancelEntrustBean();
        cancelEntrustBean.setAgentType(AgentType.UFX.value);
        cancelEntrustBean.setCancelId(id);
        cancelEntrustBean.setAccountId(ufxTradeClient.getAccountIdByEntrustId(id));
        if (errorCode != ErrorCode.SUCCESS.value) {
            logger.warn("onCancelOrder failed reason:{}", ds.getString("error_info"));
            cancelEntrustBean.setErrorCode(errorCode);
            cancelEntrustBean.setEntrustStatus(EntrustStatus.INVALID.value);
            cancelEntrustBean.setErrorMsg(ds.getString("error_info"));
            ufxTradeResponse.resCancelEntrust(cancelEntrustBean);
            return;
        }
        UfxConverter.cancelEntrustReBack(cancelEntrustBean, ds);
        ufxTradeResponse.resCancelEntrust(cancelEntrustBean);
    }

    public void onOrderReturn(IDataset result, int errorCode, String errorInfo) {
        if (Objects.nonNull(result)) {
        }
    }

    public void onTradeReturn(IDataset result, int errorCode, String errorInfo) {
        if (Objects.nonNull(result)) {
        }
    }

    public LoginInfo getLoginInfo(String fundAccount) {
        return loginInfoMap.get(fundAccount);
    }

    /***
     * @Description: 综合业务委托确认
     * @Param: [result, errorCode, errorInfo, id]
     * @return: void
     * @Author: MAXX
     * @Date: 2020/3/30
     */
    public void onIntegratedEntrust(IDatasets result, int errorCode, String errorInfo, String id) {
        IDataset ds = result.getDataset(0);
        id = Prefixion.INTEGRATEDENTRUST_PREFIXION.value + id;
        logger.info("onIntegratedEntrust reback :  id:{} ", id);
        DatasetService.printDataset(ds);
        IntegratedEntrust integratedEntrust = new IntegratedEntrust();
        integratedEntrust.setId(id);
        integratedEntrust.setAccountId(ufxTradeClient.getAccountIdByEntrustId(id));
        if (errorCode != ErrorCode.SUCCESS.value) {
            integratedEntrust.setEntrustStatus(EntrustStatus.INVALID.value);
            integratedEntrust.setErrorMsg(ds.getString("error_info"));
            integratedEntrust.setErrorCode(errorCode);
            logger.warn("integratedEntrust failed ErrorNo:{}，reason:{}", errorCode, ds.getString("error_info"));
            ufxTradeResponse.resIntegratedEntrust(integratedEntrust);
            return;
        }
        integratedEntrust.setBrokerEntrustId(ds.getString("entrust_no"));
        String init_date = ds.getString("init_date", null);
        if (!Strings.isNullOrEmpty(init_date)) {
            integratedEntrust.setTradingDay(init_date);
        }
        ufxTradeResponse.resIntegratedEntrust(integratedEntrust);
    }

    /***
     * @Description: 综合业务委托撤单返回
     * @Param: [result, errorCode, errorInfo, id]
     * @return: void
     * @Author: MAXX
     * @Date: 2020/3/30
     */
    public void onCancelIntegratedEntrust(IDatasets result, int errorCode, String errorInfo, String id) {
        IDataset ds = result.getDataset(0);
        CancelEntrustBean cancelEntrustBean = new CancelEntrustBean();
        id = Prefixion.INTEGRATEDENTRUST_PREFIXION.value + id;
        cancelEntrustBean.setCancelId(id);
        cancelEntrustBean = ufxTradeClient.getEntrustByCancelId(id);
        cancelEntrustBean.setAgentType(AgentType.UFX.value);
        logger.info("cancelIntegratedEntrust reback : ");
        DatasetService.printDataset(ds);
        if (errorCode != ErrorCode.SUCCESS.value) {
            logger.warn("cancelIntegratedEntrust failed ErrorNo:{}，reason:{}", errorCode, ds.getString("error_info"));
            cancelEntrustBean.setEntrustStatus(EntrustStatus.INVALID.value);
            cancelEntrustBean.setErrorMsg(ds.getString("error_info"));
            cancelEntrustBean.setErrorCode(errorCode);
            ufxTradeResponse.resCancelIntegratedEntrust(cancelEntrustBean);
            return;
        }
        cancelEntrustBean.setCancelBrokerEntrustId(ds.getString("entrust_no"));
        ufxTradeResponse.resCancelIntegratedEntrust(cancelEntrustBean);
    }

    public UfxTradeClientImpl getUfxTradeClient() {
        return ufxTradeClient;
    }

    public void setUfxTradeClient(UfxTradeClientImpl ufxTradeClient) {
        this.ufxTradeClient = ufxTradeClient;
    }

    public void setUfxTradeApi(UfxTradeApi ufxTradeApi) {
        this.ufxTradeApi = ufxTradeApi;
    }
}
