package com.joyintech.tams.exchange.terminal.trade.ufx;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.hundsun.libsecurity.CryptUtil;
import com.hundsun.t2sdk.common.core.context.ContextUtil;
import com.hundsun.t2sdk.common.share.dataset.DatasetService;
import com.hundsun.t2sdk.impl.client.T2Services;
import com.hundsun.t2sdk.impl.util.ICrypt;
import com.hundsun.t2sdk.interfaces.IClient;
import com.hundsun.t2sdk.interfaces.share.dataset.IDataset;
import com.hundsun.t2sdk.interfaces.share.dataset.IDatasets;
import com.hundsun.t2sdk.interfaces.share.event.EventType;
import com.hundsun.t2sdk.interfaces.share.event.IEvent;
import com.joyintech.tams.exchange.common.bean.*;
import com.joyintech.tams.exchange.common.bean.bond.StandardBondQryBean;
import com.joyintech.tams.exchange.common.constant.*;
import com.joyintech.tams.exchange.common.db.domain.*;
import com.joyintech.tams.exchange.common.utils.CbptransTypeUtils;
import com.joyintech.tams.exchange.common.utils.IntegratedEntrustUtils;
import com.joyintech.tams.exchange.common.utils.JsonUtils;
import com.joyintech.tams.exchange.common.utils.StockCodeUtils;
import com.joyintech.tams.exchange.terminal.constant.AccountLoginStatus;
import com.joyintech.tams.exchange.terminal.trade.TradeResponseImpl;
import com.joyintech.tams.exchange.terminal.trade.ufx.builder.BaseUfxDataSetBuilder;
import com.joyintech.tams.exchange.terminal.trade.ufx.builder.UfxDataSetBuilderFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class UfxTradeApi {
    protected final static Logger logger = LoggerFactory.getLogger(UfxTradeApi.class);
    private volatile Map<String, PositionStr> positionStrMap = Maps.newConcurrentMap();//fundAccount-PositionStr
    private IClient client = null;
    private T2Services server = T2Services.getInstance(); // 获取T2实例
    private ICrypt crypt = server.getCrypt(); //加密实例化
    private String op_station;
    private TradeResponseImpl ufxTradeResponse;
    private UfxTradeClientImpl ufxTradeClient;

    private BaseUfxDataSetBuilder builder;
    private long senderId = 5;
    private AtomicBoolean isQueryNextTrade = new AtomicBoolean(false);

    private Map<String, Integer> status = Maps.newConcurrentMap();

//    private List<ReissueEntrustBean> reissueEntrustBeanList = new ArrayList<>();
//    private List<ReissueTradeBean> reissueTradeBeanList = new ArrayList<>();

    public void setStatus(String accountId, AccountLoginStatus status) {
        logger.info("account {} status set to: {}", accountId, status.msg);
        this.status.put(accountId, status.value);
    }

    public int getStatus(String accountId) {
        return status.getOrDefault(accountId, AccountLoginStatus.NOT_LOGGED.value);
    }

    public PositionStr getPositionStrMap(String fundAccount) {
        return positionStrMap.get(fundAccount);
    }

    public boolean isQueryNextTrade() {
        return isQueryNextTrade.get();
    }

    public void putPositionStrMap(String fundAccount, PositionStr positionStr) {
        positionStrMap.put(fundAccount, positionStr);
    }

    public void init(String tradeClientNode, long eventId, String brokerType, String opEntrustWay) throws Exception {
        logger.info("tradeClientNode: {}, brokerType: {}", tradeClientNode, brokerType);
        //ar_tree是配置在配置文件中的服务
        builder = UfxDataSetBuilderFactory.genDataSetBuild(brokerType);
        builder.setSenderId(senderId);
        builder.setOp_station(op_station);
        opEntrustWay = Strings.isNullOrEmpty(opEntrustWay) ? "D" : opEntrustWay;
        builder.setOp_entrust_way(opEntrustWay);
        builder.setEventId(eventId);
        isQueryNextTrade.set(builder.getIsQueryNextTrade());
        tradeClientNode = Strings.isNullOrEmpty(tradeClientNode) ? "ar_tree" : tradeClientNode;
        client = server.getClient(tradeClientNode); //走节点ar_tree
    }

    //异步登录打包
    public void ReqClientLogin(Account account, String password, String op_station) throws Exception {
        setStatus(account.getId(), AccountLoginStatus.NOT_LOGGED);
        logger.info("ReqClientLogin {} - {} -- {}", account.getFinanceAccount(), password, op_station);
        // 往event中添加dataset
        IDataset dataset = DatasetService.getDefaultInstance().getDataset();
        //开始打包
        dataset.addColumn("op_branch_no");//操作分支机构
        dataset.addColumn("op_entrust_way");//委托方式
        dataset.addColumn("op_station");//站点地址
        dataset.addColumn("password", IDataset.DS_BYTE_ARRAY);//密码
        dataset.addColumn("password_type");//密码类别
        dataset.addColumn("input_content");//客户标志类别
        dataset.addColumn("account_content");//输入内容（账号）
        dataset.appendRow();
        dataset.updateString("op_branch_no", "1");//操作分支机构打入值1
        dataset.updateString("op_entrust_way", builder.getOp_entrust_way());
        dataset.updateString("op_station", op_station);
        byte[] out = new byte[7];
        CryptUtil.HsPwdEncode(password.getBytes(), out);
        dataset.updateByteArray("password", out);
        //dataset.updateString("password", password); //普通密码打包
        dataset.updateString("password_type", "2");
        dataset.updateString("input_content", "1");
        dataset.updateString("account_content", account.getFinanceAccount());
        IEvent event = builder.genEvent("331100", dataset);
        unPack(event, event.getServiceId());
        client.send(event); //发送包
    }

    /***
     * @Description: 证券股东信息查询
     * @Param: [account, password, op_station]
     * @return: void
     * @Author: MAXX
     * @Date: 2020/3/17
     */
    public List<ClientStkacct> ReqClientStkacct(String password, Integer branch_no, String client_id, String user_token) throws Exception {
        logger.info("ReqClientStkacct data :password:{}, branch_no:{} ,client_id:{} ,user_token:{},op_station:{}", password, branch_no, client_id, user_token, op_station);
        // 往event中添加dataset
        IDataset dataset = DatasetService.getDefaultInstance().getDataset();
        //开始打包
        dataset.addColumn("op_branch_no");//操作分支机构
        dataset.addColumn("op_entrust_way");//委托方式
        dataset.addColumn("op_station");//站点地址
        dataset.addColumn("branch_no");//分支机构
        dataset.addColumn("client_id");//客户编号
        dataset.addColumn("password", IDataset.DS_BYTE_ARRAY);//密码
        dataset.addColumn("password_type");//密码类别
        dataset.addColumn("user_token");//用户口令
        dataset.appendRow();
        dataset.updateString("op_branch_no", "");//操作分支机构打入值1
        dataset.updateString("op_entrust_way", builder.getOp_entrust_way());
        dataset.updateString("op_station", op_station);
        dataset.updateString("branch_no", branch_no.toString());
        dataset.updateString("client_id", client_id);
        byte[] out = new byte[7];
        CryptUtil.HsPwdEncode(password.getBytes(), out);
        dataset.updateByteArray("password", out);
        //dataset.updateString("password", password); //普通密码打包
        dataset.updateString("password_type", "2");
        dataset.updateString("user_token", user_token);
        IEvent event = builder.genSyncEvent("331300", dataset);
        unPack(event, event.getServiceId());
        IEvent rsp = client.sendReceive(event);//发送包
        if (rsp.getReturnCode() != ErrorCode.SUCCESS.value) {
            logger.warn("ReqClientStkacct failed ErrorNo:{}，reason:{}", rsp.getErrorNo(), rsp.getErrorInfo());
            return null;
        }
        List<ClientStkacct> clientStkacctList = UfxConverter.reqClientStkacctReBack(rsp);
        logger.debug("ReqClientStkacct reback : {}", JSONArray.toJSONString(clientStkacctList));
        return clientStkacctList;
    }

    //委托下单
    public void entrust(Long requestId, Entrust entrust, String password, LoginInfo loginInfo) throws Exception {
        logger.info("entrust data :requestId:{}  ", requestId);
        List<ClientStkacct> clientStkacctList = loginInfo.getClientStkacctList();
        if (Objects.isNull(clientStkacctList)) {
            logger.error("entrust error no clientStkacctList");
            entrust.setEntrustStatus(EntrustStatus.INVALID.value);
            entrust.setErrorMsg("帐号" + entrust.getAccountId() + "证券股东信息获取失败");
            entrust.setErrorCode(ErrorCode.TERMINAL_API_ERROR.value);
            ufxTradeResponse.resEntrust(entrust);
            return;
        }
        IDataset dataset = builder.genEntrustDataSet(entrust, password, loginInfo);
        IEvent event = null;
        if (EntrustProp.ETF_APPLY.value.equals(entrust.getEntrustProp())) {
            if (EntrustBs.BUY.value.equals(entrust.getEntrustBs())) {
                event = builder.genEvent("333012", dataset);
            } else if (EntrustBs.SELL.value.equals(entrust.getEntrustBs())) {
                event = builder.genEvent("333013", dataset);
            }
        } else {
            event = builder.genEvent("333002", dataset);
        }
        event.markTimestamp(true);
        event.addTimestamp(Long.parseLong(entrust.getId().substring(1)), requestId.toString());
        unPack(event, event.getServiceId()); //入参解包，同时打印发送包参数信息
        entrust.setEntrustStatus(EntrustStatus.TOBE_REPORTED.value);
        client.send(event);//发送包
    }

    //证券委托查询
    public void ReqQueryOrder(Long requestId, LoginInfo loginInfo, String password, String accountId) throws Exception {
        logger.info("ReqQueryOrder data : accountId:{}", accountId);
        List<Entrust> entrusts = getOrder(loginInfo, password, accountId);
        ufxTradeResponse.resQueryEntrusts(entrusts, requestId);
    }

    //证券成交查询
    public void ReqQueryTrade(Long requestId, String accountId, String branch_no, String client_id, String user_token, String account, String password, String sysnode_id) throws Exception {
        logger.info("ReqQueryTrade data : branch_no:{} ,client_id:{} ,user_token:{} ,account:{} ,password:{},sysnode_id:{}, op_station:{}", branch_no, client_id, user_token, account, password, sysnode_id, op_station);
        List<Trade> trades = getTrade(accountId, branch_no, client_id, user_token, account, password);
        ufxTradeResponse.resQueryTrade(trades, requestId);
    }

    private List<Trade> getTrade(String accountId, String branch_no, String client_id, String user_token, String account, String password) {
        int pageSize = 1000;
        int size = pageSize;
        String positionStr = "";
        List<Trade> reList = new ArrayList<Trade>();
        while (pageSize == size) {
            IEvent rsp = null;
            try {
                rsp = getQueryTradeReback(user_token, branch_no, client_id, account, password, pageSize, positionStr);
            } catch (Exception e) {
                logger.warn("account:{}, ReqQueryTrade error, {}", account, e);
            }
            if (Objects.isNull(rsp) || rsp.getReturnCode() != ErrorCode.SUCCESS.value) {
                logger.warn("account:{} ReqQueryTrade failed ErrorNo:{}，reason:{}", account, rsp.getErrorNo(), rsp.getErrorInfo());
                return null;
            }
            List<Trade> list = UfxConverter.parseQueryTradeMsg(rsp, accountId);
            pageSize = list == null ? 0 : list.size();
            logger.debug("pageSize : {}", pageSize);
            reList.addAll(list);
            if (pageSize == size) {
                positionStr = list.get(pageSize - 1).getPositionStr();
            }
        }
        logger.debug("account:{}, ReqQueryTrade reback : {}", account, JSONArray.toJSONString(reList));
        return reList;
    }

    private List<Entrust> getOrder(LoginInfo loginInfo, String password, String accountId) {
        // 往event中添加dataset
        int pageSize = 1000;
        int size = pageSize;
        String positionStr = "";
        List<Entrust> reList = new ArrayList<Entrust>();
        while (pageSize == size) {
            IEvent rsp = null;
            try {
                rsp = getOrderReBack(loginInfo, password, pageSize, positionStr);
            } catch (Exception e) {
                logger.warn("account:{}, ReqQueryOrder error, {}", accountId, e);
            }
            if (Objects.isNull(rsp) || rsp.getReturnCode() != ErrorCode.SUCCESS.value) {
                logger.warn("account:{} ReqQueryOrder failed ErrorNo:{}，reason:{}", accountId, rsp.getErrorNo(), rsp.getErrorInfo());
                return null;
            }
            List<Entrust> list = UfxConverter.queryOrderReBack(rsp, accountId);
            pageSize = list == null ? 0 : list.size();
            logger.debug("pageSize : {}", pageSize);
            reList.addAll(list);
            if (pageSize == size) {
                positionStr = list.get(pageSize - 1).getPositionStr();
            }
        }
        logger.debug("ReqQueryOrder reback : {}", JSONArray.toJSONString(reList));
        return reList;
    }

    private List<IntegratedEntrust> getIntegratedOrder(LoginInfo loginInfo, String password, String accountId) {
        // 往event中添加dataset
        int pageSize = 1000;
        int size = pageSize;
        String positionStr = "";
        List<IntegratedEntrust> reList = new ArrayList<>();
        while (pageSize == size) {
            IEvent rsp = null;
            try {
                rsp = getIntegrateOrderReback(loginInfo, password, pageSize, positionStr);
            } catch (Exception e) {
                logger.warn("account:{}, ReqQueryIntegrateOrder error, {}", accountId, e);
            }
            if (Objects.isNull(rsp) || rsp.getReturnCode() != ErrorCode.SUCCESS.value) {
                logger.warn("account:{} ReqQueryIntegrateOrder failed ErrorNo:{}，reason:{}", accountId, rsp.getErrorNo(), rsp.getErrorInfo());
                return null;
            }
            List<IntegratedEntrust> list = UfxConverter.queryIntegratedOrder(rsp, accountId);
            pageSize = list == null ? 0 : list.size();
            logger.debug("pageSize : {}", pageSize);
            reList.addAll(list);
            if (pageSize == size) {
                positionStr = list.get(pageSize - 1).getPositionStr();
            }
        }
        logger.debug("ReqQueryIntegrateOrder reback : {}", JSONArray.toJSONString(reList));
        return reList;
    }

    private List<Trade> getProtocolBackTrade(LoginInfo loginInfo, String password, String accountId) {
        // 往event中添加dataset
        int pageSize = 1000;
        int size = pageSize;
        String positionStr = "";
        List<Trade> reList = new ArrayList<>();
        while (pageSize == size) {
            IEvent rsp = null;
            try {
                rsp = getProtocolBackTradeReback(loginInfo, password, pageSize, positionStr);
            } catch (Exception e) {
                logger.warn("account:{}, ReqQueryIntegrateTrade error, {}", accountId, e);
            }
            if (Objects.isNull(rsp) || rsp.getReturnCode() != ErrorCode.SUCCESS.value) {
                logger.warn("account:{} ReqQueryIntegrateTrade failed ErrorNo:{}，reason:{}", accountId, rsp.getErrorNo(), rsp.getErrorInfo());
                return null;
            }
            List<Trade> list = UfxConverter.qryProtocolBack_Trade_Back(rsp, accountId);
            pageSize = list == null ? 0 : list.size();
            logger.debug("pageSize : {}", pageSize);
            reList.addAll(list);
            if (pageSize == size) {
                positionStr = list.get(pageSize - 1).getPositionStr();
            }
        }
        logger.debug("ReqQueryIntegrateTrade reback : {}", JSONArray.toJSONString(reList));
        return reList;
    }

    private IEvent getOrderReBack(LoginInfo loginInfo, String password, int pageSize, String positionStr) throws Exception {
        IDataset dataset = DatasetService.getDefaultInstance().getDataset();
        dataset.addColumn("op_branch_no");//操作分支机构
        dataset.addColumn("op_entrust_way");//委托方式
        dataset.addColumn("op_station");//站点地址
        dataset.addColumn("branch_no");//分支机构
        dataset.addColumn("client_id");//客户编号
        dataset.addColumn("fund_account");//资产账户
        dataset.addColumn("password");//密码
        dataset.addColumn("user_token");//用户口令
        dataset.addColumn("request_num");//请求行数
        dataset.addColumn("position_str");//定位串
        dataset.appendRow();
        dataset.updateString("op_branch_no", "");
        dataset.updateString("op_entrust_way", builder.getOp_entrust_way()); //op_entrust_way 打入7 网上交易
        dataset.updateString("op_station", op_station);
        dataset.updateInt("branch_no", loginInfo.getBranchNo());
        dataset.updateString("client_id", loginInfo.getClientId());
        dataset.updateString("fund_account", loginInfo.getFundAccount());
        byte[] out = new byte[7];
        CryptUtil.HsPwdEncode(password.getBytes(), out);
        dataset.updateByteArray("password", out);
        dataset.updateString("user_token", loginInfo.getUserToken());
        dataset.updateInt("request_num", pageSize);
        dataset.updateString("position_str", positionStr);
        IEvent event = builder.genSyncEvent("333101", dataset);
        unPack(event, event.getServiceId()); //入参解包，同时打印发送包参数信息
        return client.sendReceive(event);//发送包
    }

    private IEvent getIntegrateOrderReback(LoginInfo loginInfo, String password, int pageSize, String positionStr) throws Exception {
        // 往event中添加dataset
        IDataset dataset = DatasetService.getDefaultInstance().getDataset();
        dataset.addColumn("op_branch_no");//操作分支机构
        dataset.addColumn("op_entrust_way");//委托方式
        dataset.addColumn("op_station");//站点地址
        dataset.addColumn("branch_no");//分支机构
        dataset.addColumn("client_id");//客户编号
        dataset.addColumn("fund_account");//资产账户
        dataset.addColumn("password");//密码
        dataset.addColumn("user_token");//用户口令
        dataset.addColumn("en_entrust_prop");//允许委托属性
        dataset.addColumn("position_str");//定位串
        dataset.addColumn("request_num");//请求行数
        dataset.appendRow();
        dataset.updateString("op_branch_no", "");
        dataset.updateString("op_entrust_way", builder.getOp_entrust_way()); //op_entrust_way 打入7 网上交易
        dataset.updateString("op_station", op_station);
        dataset.updateInt("branch_no", loginInfo.getBranchNo());
        dataset.updateString("client_id", loginInfo.getClientId());
        dataset.updateString("fund_account", loginInfo.getFundAccount());
        byte[] out = new byte[7];
        CryptUtil.HsPwdEncode(password.getBytes(), out);
        dataset.updateByteArray("password", out);
        dataset.updateString("user_token", loginInfo.getUserToken());
        //‘a,b,c,e,AFW,AFC’即大宗交易
        dataset.updateString("en_entrust_prop", "BPN,BPR,BPT,BPB,BPH,BPI,FI1,FI3,FI9,a,b,c,e,AFW,AFC");//FI1（确定报价），FI3（点击成交），FI9（指定对手方报价）
        dataset.updateString("position_str", positionStr);
        dataset.updateString("request_num", String.valueOf(pageSize));
        String funcCode = "332704";
        IEvent event = builder.genSyncEvent(funcCode, dataset);
        unPack(event, funcCode); //入参解包，同时打印发送包参数信息
        return client.sendReceive(event);//发送包
    }

    private IEvent getQueryTradeReback(String user_token, String branch_no, String client_id, String account, String password, int requestNum, String positionStr) throws Exception {
        IDataset dataset = DatasetService.getDefaultInstance().getDataset();
        dataset.addColumn("user_token");//用户口令
        dataset.addColumn("op_branch_no");//操作分支机构
        dataset.addColumn("op_entrust_way");//委托方式
        dataset.addColumn("op_station");//站点地址
        dataset.addColumn("branch_no");//分支机构
        dataset.addColumn("client_id");//客户编号
        dataset.addColumn("fund_account");//资产账户
        dataset.addColumn("password");//密码
        dataset.addColumn("position_str");//定位串
        dataset.addColumn("request_num");//请求行数
        dataset.addColumn("query_direction");//查询方向
        dataset.appendRow();
        dataset.updateString("user_token", user_token);
        dataset.updateString("op_branch_no", "");
        dataset.updateString("op_entrust_way", builder.getOp_entrust_way());
        dataset.updateString("op_station", op_station);
        dataset.updateString("branch_no", branch_no);
        dataset.updateString("client_id", client_id);
        dataset.updateString("fund_account", account);
        byte[] out = new byte[7];
        CryptUtil.HsPwdEncode(password.getBytes(), out);
        dataset.updateByteArray("password", out);
        dataset.updateString("position_str", positionStr);
        dataset.updateInt("request_num", requestNum);
        dataset.updateString("query_direction", "0");// '1'-逆序　'0'-正序
        IEvent event = builder.genSyncEvent("333102", dataset);
        unPack(event, event.getServiceId());
        return client.sendReceive(event);//发送包
    }

    private IEvent getProtocolBackTradeReback(LoginInfo loginInfo, String password, int pageSize, String positionStr) throws Exception {
        // 往event中添加dataset
        IDataset dataset = DatasetService.getDefaultInstance().getDataset();
        dataset.addColumn("op_branch_no");//操作分支机构
        dataset.addColumn("op_entrust_way");//委托方式
        dataset.addColumn("op_station");//站点地址
        dataset.addColumn("branch_no");//分支机构
        dataset.addColumn("client_id");//客户编号
        dataset.addColumn("fund_account");//资产账户
        dataset.addColumn("password");//密码
        dataset.addColumn("position_str");//定位串
        dataset.addColumn("en_entrust_prop");//允许委托属性
        dataset.addColumn("request_num");//请求行数
        dataset.addColumn("query_type");//请求行数
        dataset.addColumn("user_token");
        dataset.appendRow();
        dataset.updateString("op_branch_no", "");
        dataset.updateString("op_entrust_way", builder.getOp_entrust_way()); //op_entrust_way 打入7 网上交易
        dataset.updateString("op_station", op_station);
        dataset.updateInt("branch_no", loginInfo.getBranchNo());
        dataset.updateString("client_id", loginInfo.getClientId());
        dataset.updateString("fund_account", loginInfo.getFundAccount());
        dataset.updateString("password", password);
        dataset.updateString("position_str", positionStr);
        String en_entrust_prop = "BPN,BPR,BPT,BPB,BPH,BPI,FI1,FI3,FI9,a,b,c,e,AFW,AFC";//固收 深圳and上海协议回购成交申报
        dataset.updateString("en_entrust_prop", en_entrust_prop);
        dataset.updateString("request_num", String.valueOf(pageSize));
        dataset.updateString("query_type", "0");
        dataset.updateString("user_token", loginInfo.getUserToken());
        IEvent event = builder.genSyncEvent("332705", dataset);
        unPack(event, event.getServiceId()); //入参解包，同时打印发送包参数信息
        return client.sendReceive(event);//发送包
    }

    public void reqReissueEntrust(LoginInfo loginInfo, String password, String accountId, Long requestId) {
        Map<String, EntrustInterface> entrustInterfaceMap = new HashMap<>();
        try {
            List<Entrust> entrustList = qryOrder(loginInfo, password, accountId, true);
            List<IntegratedEntrust> integratedEntrustList = queryIntegratedOrder(loginInfo, password, accountId, true);
            entrustList.forEach(res -> {
                if (!Strings.isNullOrEmpty(res.getId())) {
                    entrustInterfaceMap.put(res.getId(), res);
                }
            });
            integratedEntrustList.forEach(res -> {
                if (!Strings.isNullOrEmpty(res.getId())) {
                    entrustInterfaceMap.put(res.getId(), res);
                }
            });
            ufxTradeResponse.resQueryReissueEntrust(accountId, entrustInterfaceMap, requestId);
        } catch (Exception e) {
            logger.warn("reqReissueEntrust error {}", e);
            ufxTradeResponse.resQueryReissueEntrust(accountId, entrustInterfaceMap, requestId);
        }
    }


    public void reqReissueTrade(Account account, LoginInfo loginInfo, Long requestId) {
        List<Trade> tradeList = new ArrayList<>();
        try {
            List<Trade> trades = qryTrade(loginInfo, account.getPwd(), account.getId(), true);
            List<Trade> protocolBackTradeList = qryProtocolBackTrade(loginInfo, account.getPwd(), account.getId(), true);
            tradeList.addAll(trades);
            tradeList.addAll(protocolBackTradeList);
            ufxTradeResponse.resQueryReissueTrade(account.getId(), tradeList, requestId);
        } catch (Exception e) {
            logger.warn("reqReissueTrade error {}", e);
            ufxTradeResponse.resQueryReissueTrade(account.getId(), tradeList, requestId);
        }
    }


    //委托撤单
    public void ReqCancelOrder(Long requestId, CancelEntrustBean cancelEntrustBean, LoginInfo loginInfo, String password) throws Exception {
        logger.info("ReqCancelOrder data :requestId:{}, cancelEntrustBean:{},  loginInfo:{}", requestId, JSONObject.toJSONString(cancelEntrustBean), loginInfo);
        // 往event中添加dataset
        IDataset dataset = DatasetService.getDefaultInstance().getDataset();
        dataset.addColumn("op_branch_no");//操作分支机构
        dataset.addColumn("op_entrust_way");//委托方式
        dataset.addColumn("op_station");//站点地址
        dataset.addColumn("branch_no");//分支机构
        dataset.addColumn("client_id");//客户编号
        dataset.addColumn("fund_account");//资产账户
        dataset.addColumn("password");//密码
        //dataset.addColumn("user_token");//用户口令
        dataset.addColumn("entrust_no");//委托编号
        dataset.addColumn("entrust_date");//委托日期
        dataset.appendRow();
        dataset.updateString("op_branch_no", "");
        dataset.updateString("op_entrust_way", builder.getOp_entrust_way());
        dataset.updateString("op_station", op_station);
        dataset.updateInt("branch_no", loginInfo.getBranchNo());
        dataset.updateString("client_id", loginInfo.getClientId());
        dataset.updateString("fund_account", loginInfo.getFundAccount());
        /*byte[] out = new byte[7];
        CryptUtil.HsPwdEncode(password.getBytes(), out);*/
        dataset.updateString("password", password);
        //dataset.updateString("user_token", loginInfo.getUserToken());
        dataset.updateString("entrust_no", cancelEntrustBean.getBrokerEntrustId());
        dataset.updateString("entrust_date", cancelEntrustBean.getTradingDay());
        IEvent event = builder.genEvent("333017", dataset);
        event.markTimestamp(true);
        event.addTimestamp(Long.parseLong(cancelEntrustBean.getCancelId().substring(1)), requestId.toString());
        unPack(event, event.getServiceId());
        client.send(event);//发送包
    }

    //证券持仓查询
    public void ReqQueryPosition(LoginInfo loginInfo, String password) throws Exception {
        // 往event中添加dataset
        IDataset dataset = DatasetService.getDefaultInstance().getDataset();
        dataset.addColumn("op_branch_no");//操作分支机构
        dataset.addColumn("op_entrust_way");//委托方式
        dataset.addColumn("op_station");//站点地址
        dataset.addColumn("branch_no");//分支机构
        dataset.addColumn("client_id");//客户编号
        dataset.addColumn("user_token");//用户口令
        dataset.addColumn("fund_account");//资产账户
        dataset.addColumn("password");//密码
        dataset.appendRow();
        dataset.updateString("op_branch_no", "");
        dataset.updateString("op_entrust_way", builder.getOp_entrust_way());
        dataset.updateString("op_station", op_station);
        dataset.updateInt("branch_no", loginInfo.getBranchNo());
        dataset.updateString("client_id", loginInfo.getClientId());
        dataset.updateString("user_token", loginInfo.getUserToken());
        dataset.updateString("fund_account", loginInfo.getFundAccount());
        byte[] out = new byte[7];
        CryptUtil.HsPwdEncode(password.getBytes(), out);
        dataset.updateByteArray("password", out);
        IEvent event = builder.genSyncEvent("333104", dataset);
        unPack(event, event.getServiceId());
        IEvent rsp = client.sendReceive(event);//发送包
        unPack(rsp, event.getServiceId());
        logger.info("ReqQueryPosition reback :");
        if (rsp.getReturnCode() != ErrorCode.SUCCESS.value) {
            logger.warn("ReqQueryPosition failed ErrorNo:{}，reason:{}", rsp.getErrorNo(), rsp.getErrorInfo());
            return;
        }
    }

    //客户资金快速查询
    public IEvent ReqAccountBalance(String branch_no, String client_id, String user_token, String account, String password, String sysnode_id) throws Exception {
        // 往event中添加dataset
        IDataset dataset = DatasetService.getDefaultInstance().getDataset();
        dataset.addColumn("op_branch_no");//操作分支机构
        dataset.addColumn("op_entrust_way");//委托方式
        dataset.addColumn("op_station");//站点地址
        dataset.addColumn("branch_no");//分支机构
        dataset.addColumn("client_id");//客户编号
        dataset.addColumn("user_token");//用户口令
        dataset.addColumn("fund_account");//资产账户
        dataset.addColumn("password");//密码
        dataset.appendRow();
        dataset.updateString("op_branch_no", "");
        dataset.updateString("op_entrust_way", builder.getOp_entrust_way());
        dataset.updateString("op_station", "1");
        dataset.updateString("branch_no", branch_no);
        dataset.updateString("client_id", client_id);
        dataset.updateString("user_token", user_token);
        dataset.updateString("fund_account", account);
        byte[] out = new byte[7];
        CryptUtil.HsPwdEncode(password.getBytes(), out);
        dataset.updateByteArray("password", out);
        IEvent event = builder.genSyncEvent("332255", dataset);
        unPack(event, event.getServiceId());
        return client.sendReceive(event);//发送包
    }

    //业务包解包，并打印包
    public void unPack(IEvent event, String functionId) {
        StringBuilder sb = new StringBuilder();
        //获取返回集
        IDatasets result = event.getEventDatas();
        //获取结果集总数
        int datasetCount = result.getDatasetCount();
        //遍历所有的结果集
        for (int i = 0; i < datasetCount; i++) {
            //读取单个结果集信息
            IDataset ds = result.getDataset(0);
            int columnCount = ds.getColumnCount(); //获取数据数量
            //打印结果集标题
            for (int j = 1; j <= columnCount; j++) {
                sb.append(ds.getColumnName(j));
                sb.append("|");
            }
            //遍历单个结果集记录，遍历前首先将指针置到开始
            ds.beforeFirst();
            //打印结果集数据
            while (ds.hasNext()) {
                sb.append("\n");
                ds.next();
                for (int j = 1; j <= columnCount; j++) {
                    sb.append(ds.getString(j)).append("|");
                }
            }
        }
        logger.info("功能号" + functionId + "运行\n以下是相关入参：\n" + sb);//打印入参
    }

    /***
     * @Description: 综合业务委托确认
     * @Param: [integratedEntrust, branch_no, client_id, user_token, account, password]
     * @return: com.joyintech.tams.exchange.common.db.domain.IntegratedEntrust
     * @Author: MAXX
     * @Date: 2020/3/17
     */
    public void integratedEntrust(Long requestId, IntegratedEntrust integratedEntrust, String password, LoginInfo loginInfo) throws Exception {
        logger.info("integratedEntrust data :integratedEntrust:{}, password:{}, op_station:{}", integratedEntrust, password, op_station);
        if (IntegratedEntrustUtils.isBulk(integratedEntrust.getEntrustProp())) {//大宗
            bulkIntegratedEntrust(requestId, integratedEntrust, password, loginInfo);
            return;
        } else if (Market.SHSE.value.equals(integratedEntrust.getMarket())
                && IntegratedEntrustUtils.isFixedIncome(integratedEntrust.getEntrustProp())) {//固收委托确认
            fixedIncome(requestId, integratedEntrust, loginInfo, password);
            return;
        } else if (IntegratedEntrustUtils.isRepurchaseSQSZ(integratedEntrust.getEntrustProp(),
                integratedEntrust.getEntrustBs(), integratedEntrust.getMarket())) {//债券质押式协议回购_首期_深交所
            repurchaseSQSZ(requestId, integratedEntrust, password, loginInfo);
            return;
        } else if (IntegratedEntrustUtils.isRepurchaseBuybackSZ(integratedEntrust.getEntrustProp(), integratedEntrust.getMarket())) {//深圳债券质押协议回购购回交易
            repurchaseBuybackSZ(requestId, integratedEntrust, password, loginInfo);
            return;
        } else if (IntegratedEntrustUtils.isRepurchaseRenewalSZ(integratedEntrust.getEntrustProp(), integratedEntrust.getMarket())) {//深圳债券质押协议回购到期续做
            repurchaseRenewalSZ(requestId, integratedEntrust, password, loginInfo);
            return;
        } else if (IntegratedEntrustUtils.isRepurchaseSH(integratedEntrust.getEntrustProp(),//债券质押式协议回购_上交所
                integratedEntrust.getEntrustBs(), integratedEntrust.getMarket())) {
            repurchaseSH(requestId, integratedEntrust, password, loginInfo);
            return;
        }
        logger.error("integratedEntrust error no functionId");
        integratedEntrust.setEntrustStatus(EntrustStatus.INVALID.value);
        integratedEntrust.setErrorMsg("bsFlag没有对应的functionId");
        integratedEntrust.setErrorCode(ErrorCode.REQ_PARAMETER_ERROR.value);
        ufxTradeResponse.resIntegratedEntrust(integratedEntrust);
        return;
    }

    /***
     * @Description: 综合业务委托撤单
     * @Param: [integratedEntrust, password, loginInfo]
     * @return: com.joyintech.tams.exchange.common.db.domain.IntegratedEntrust
     * @Author: MAXX
     * @Date: 2020/3/19
     */
    public void cancelIntegratedEntrust(Long requestId, CancelEntrustBean cancelEntrustBean, String password, LoginInfo loginInfo) throws Exception {
        logger.info("cancelIntegratedEntrust data :cancelEntrustBean:{}, password:{}, op_station:{}", cancelEntrustBean, password, op_station);
        String functionId = "";
        if (IntegratedEntrustUtils.isBulk(cancelEntrustBean.getEntrustProp())) {//大宗
            functionId = "332703";
        } else if (Market.SHSE.value.equals(cancelEntrustBean.getMarket())
                && IntegratedEntrustUtils.isFixedIncome(cancelEntrustBean.getEntrustProp())) {//固收撤回
            functionId = "332723";
        } else if (IntegratedEntrustUtils.isRepurchaseSZ(cancelEntrustBean.getEntrustProp(),
                cancelEntrustBean.getEntrustBs(), cancelEntrustBean.getMarket())) {//债券质押式协议回购_深交所撤回
            functionId = "332402";
        } else if (IntegratedEntrustUtils.isRepurchaseSH(cancelEntrustBean.getEntrustProp(),//债券质押式协议回购_上交所撤回
                cancelEntrustBean.getEntrustBs(), cancelEntrustBean.getMarket())) {
            functionId = "332726";
        }
        if ("".equals(functionId)) {
            logger.error("cancelIntegratedEntrust error no functionId");
            cancelEntrustBean.setEntrustStatus(EntrustStatus.INVALID.value);
            cancelEntrustBean.setErrorMsg("bsFlag没有对应的functionId");
            cancelEntrustBean.setErrorCode(ErrorCode.REQ_PARAMETER_ERROR.value);
            ufxTradeResponse.resCancelIntegratedEntrust(cancelEntrustBean);
            return;
        }
        // 往event中添加dataset
        IDataset dataset = DatasetService.getDefaultInstance().getDataset();
        dataset.addColumn("op_branch_no");//操作分支机构
        dataset.addColumn("op_entrust_way");//委托方式
        dataset.addColumn("op_station");//站点地址
        dataset.addColumn("branch_no");//分支机构
        dataset.addColumn("client_id");//客户编号
        dataset.addColumn("fund_account");//资产账户
        if ("332723".equals(functionId) || "332402".equals(functionId) || "332726".equals(functionId)) {//固收撤回 债券质押式协议回购_深交所撤回 债券质押式协议回购_上交所撤回
            dataset.addColumn("password");//密码
        } else if ("332703".equals(functionId)) {//大宗
            dataset.addColumn("password");//密码
            dataset.addColumn("user_token");//用户口令
        }
        dataset.addColumn("entrust_no");//委托编号
        dataset.appendRow();
        dataset.updateString("op_branch_no", "");
        dataset.updateString("op_entrust_way", builder.getOp_entrust_way());
        dataset.updateString("op_station", op_station);
        dataset.updateInt("branch_no", loginInfo.getBranchNo());
        dataset.updateString("client_id", loginInfo.getClientId());
        dataset.updateString("fund_account", loginInfo.getFundAccount());
        if ("332723".equals(functionId) || "332402".equals(functionId)) {//固收撤回 债券质押式协议回购_深交所撤回
            dataset.updateString("password", password);
        } else if ("332703".equals(functionId)) {//大宗
            byte[] out = new byte[7];
            CryptUtil.HsPwdEncode(password.getBytes(), out);
            dataset.updateByteArray("password", out);
            dataset.updateString("user_token", loginInfo.getUserToken());
        }
        dataset.updateString("entrust_no", cancelEntrustBean.getBrokerEntrustId());
        IEvent event = builder.genEvent(functionId, dataset);
        event.markTimestamp(true);
        event.addTimestamp(Long.parseLong(cancelEntrustBean.getCancelId().substring(1)), requestId.toString());
        unPack(event, functionId);
        client.send(event);//发送包
    }

    /***
     * @Description: 历史证券成交查询
     * @Param: [historyQryBean, loginInfo]
     * @return: java.util.List<com.joyintech.tams.exchange.common.bean.HistoryTrade>
     * @Author: MAXX
     * @Date: 2020/3/20
     */
    public void qryHisBusiness(Long requestId, HistoryQryBean historyQryBean, String password, LoginInfo loginInfo) throws Exception {
        logger.info("qryHisBusiness data :historyQryBean:{}, password:{}, op_station:{}", historyQryBean, password, op_station);
        // 往event中添加dataset
        IDataset dataset = DatasetService.getDefaultInstance().getDataset();
        dataset.addColumn("user_token");//用户口令
        dataset.addColumn("op_branch_no");//操作分支机构
        dataset.addColumn("op_entrust_way");//委托方式
        dataset.addColumn("op_station");//站点地址
        dataset.addColumn("branch_no");//分支机构
        dataset.addColumn("client_id");//客户编号
        dataset.addColumn("fund_account");//资产账户
        dataset.addColumn("password");//密码
        dataset.addColumn("start_date");//开始日期
        dataset.addColumn("end_date");//到期日期
        dataset.addColumn("request_num");//请求行数
        dataset.addColumn("position_str");//定位串
        dataset.appendRow();
        dataset.updateString("user_token", loginInfo.getUserToken());
        dataset.updateString("op_branch_no", "");
        dataset.updateString("op_entrust_way", builder.getOp_entrust_way());
        dataset.updateString("op_station", op_station);
        dataset.updateInt("branch_no", loginInfo.getBranchNo());
        dataset.updateString("client_id", loginInfo.getClientId());
        dataset.updateString("fund_account", loginInfo.getFundAccount());
        byte[] out = new byte[7];
        CryptUtil.HsPwdEncode(password.getBytes(), out);
        dataset.updateByteArray("password", out);
        dataset.updateString("start_date", historyQryBean.getStartDay());
        dataset.updateString("end_date", historyQryBean.getEndDay());
        dataset.updateInt("request_num", historyQryBean.getPageSize());
        dataset.updateString("position_str", historyQryBean.getPostStr());
        IEvent event = builder.genSyncEvent("339304", dataset);
        unPack(event, event.getServiceId());
        IEvent rsp = client.sendReceive(event);//发送包
        if (rsp.getReturnCode() != ErrorCode.SUCCESS.value) {
            logger.warn("qryHisBusiness failed ErrorNo:{}，reason:{}", rsp.getErrorNo(), rsp.getErrorInfo());
            ufxTradeResponse.resQueryHistoryTrade(null, requestId);
            return;
        }
        List<HistoryTrade> list = UfxConverter.qryHisBusinessReback(rsp, historyQryBean);
        logger.debug("qryHisBusiness reback : {}", JSONArray.toJSONString(list));
        ufxTradeResponse.resQueryHistoryTrade(list, requestId);
    }

    /***
     * @Description: 客户资金快速查询
     * @Param: [password, loginInfo]
     * @return: java.util.List<com.joyintech.tams.exchange.common.bean.CustomerFinance>
     * @Author: MAXX
     * @Date: 2020/3/23
     */
    public void queryCustomerFinance(String accountId, String password, LoginInfo loginInfo, long requestId) throws Exception {
        logger.info("queryCustomerFinance data : password:{}, op_station:{}", password, op_station);
        // 往event中添加dataset
        IDataset dataset = DatasetService.getDefaultInstance().getDataset();
        dataset.addColumn("op_branch_no");//操作分支机构
        dataset.addColumn("op_entrust_way");//委托方式
        dataset.addColumn("op_station");//站点地址
        dataset.addColumn("branch_no");//分支机构
        dataset.addColumn("client_id");//客户编号
        dataset.addColumn("fund_account");//资产账户
        dataset.addColumn("password");//密码
        dataset.appendRow();
        dataset.updateString("op_branch_no", "");
        dataset.updateString("op_entrust_way", builder.getOp_entrust_way());
        dataset.updateString("op_station", op_station);
        dataset.updateInt("branch_no", loginInfo.getBranchNo());
        dataset.updateString("client_id", loginInfo.getClientId());
        dataset.updateString("fund_account", loginInfo.getFundAccount());
        dataset.updateString("password", password);
        IEvent event = builder.genSyncEvent("332254", dataset);
        unPack(event, event.getServiceId());
        IEvent rsp = client.sendReceive(event);//发送包
        if (rsp.getReturnCode() != ErrorCode.SUCCESS.value) {
            logger.warn("queryCustomerFinance failed ErrorNo:{}，reason:{}", rsp.getErrorNo(), rsp.getErrorInfo());
            ufxTradeResponse.resQueryCustomerFinance(null, requestId);
            return;
        }
        List<AccountFinance> list = UfxConverter.queryCustomerFinanceReMsg(accountId, rsp);
        logger.debug("queryCustomerFinance reback : {}", JSONArray.toJSONString(list));
        ufxTradeResponse.resQueryCustomerFinance(list, requestId);
    }

    /***
     * @Description: 最大交易数量
     * @Param: [qryBean, password, loginInfo]
     * @return: int
     * @Author: MAXX
     * @Date: 2020/3/23
     */
    public void queryEnable(Long requestId, EnableQryBean qryBean, String password, LoginInfo loginInfo) throws Exception {
        logger.info("queryEnable data : qryBean:{} ,password:{}, op_station:{}", qryBean, password, op_station);
        String funcid = "333001";//大约可买获取
        String entrustBs = qryBean.getEntrustBs();
        if (EntrustBs.SELL.value.equals(entrustBs)) {//卖
            funcid = "333103";
        }
        String[] marketAndStockCode = StockCodeUtils.getMarketAndStockCode(qryBean.getStockCode());
        // 往event中添加dataset
        IDataset dataset = DatasetService.getDefaultInstance().getDataset();
        dataset.addColumn("op_branch_no");//操作分支机构
        dataset.addColumn("op_entrust_way");//委托方式
        dataset.addColumn("op_station");//站点地址
        dataset.addColumn("branch_no");//分支机构
        dataset.addColumn("client_id");//客户编号
        dataset.addColumn("fund_account");//资产账户
        dataset.addColumn("password");//密码
        dataset.addColumn("exchange_type");//交易类别
        dataset.addColumn("stock_code");//证券代码
        if (EntrustBs.BUY.value.equals(entrustBs)) {//买
            dataset.addColumn("entrust_price");//委托价格
            dataset.addColumn("entrust_prop");//委托属性
        }
        dataset.appendRow();
        dataset.updateString("op_branch_no", "");
        dataset.updateString("op_entrust_way", builder.getOp_entrust_way());
        dataset.updateString("op_station", op_station);
        dataset.updateInt("branch_no", loginInfo.getBranchNo());
        dataset.updateString("client_id", loginInfo.getClientId());
        dataset.updateString("fund_account", loginInfo.getFundAccount());
        dataset.updateString("password", password);
        if (marketAndStockCode[1].equals(Market.SZSE.value)) {
            dataset.updateString("exchange_type", ExchangeType.SZ.value);
        } else if (marketAndStockCode[1].equals(Market.SHSE.value)) {
            dataset.updateString("exchange_type", ExchangeType.SH.value);
        }
        dataset.updateString("stock_code", marketAndStockCode[0]);
        if (EntrustBs.BUY.value.equals(entrustBs)) {//买
            dataset.updateDouble("entrust_price", qryBean.getEntrustPrice());
            dataset.updateString("entrust_prop", qryBean.getEntrustProp());
        }
        IEvent event = builder.genSyncEvent(funcid, dataset);
        unPack(event, funcid);
        IEvent rsp = client.sendReceive(event);//发送包
        IDataset ds = rsp.getEventDatas().getDataset(0);
        logger.info("queryEnable reback :");
        unPack(rsp, funcid);
        if (rsp.getReturnCode() != ErrorCode.SUCCESS.value) {
            logger.warn("queryEnable failed ErrorNo:{}，reason:{}", rsp.getErrorNo(), rsp.getErrorInfo());
            ufxTradeResponse.resQueryEnable(EnableAmount.ERROR.value, requestId);
            return;
        }
        if (EntrustBs.BUY.value.equals(entrustBs)) {//买
            String enableBuyAmount = ds.getString("enable_buy_amount", null);
            if (Strings.isNullOrEmpty(enableBuyAmount)) {
                ufxTradeResponse.resQueryEnable(EnableAmount.ZERO.value, requestId);
                return;
            }
            int enable_buy_amount = Integer.parseInt(enableBuyAmount.substring(0, enableBuyAmount.lastIndexOf(".")));
            ufxTradeResponse.resQueryEnable(enable_buy_amount, requestId);
            return;
        }
        ds.beforeFirst();
        if (!ds.hasNext()) {
            ufxTradeResponse.resQueryEnable(EnableAmount.ZERO.value, requestId);
            return;
        }
        if (ds.hasNext()) {
            ds.next();
            String enableAmount = ds.getString("enable_amount", null);
            if (Strings.isNullOrEmpty(enableAmount)) {
                ufxTradeResponse.resQueryEnable(EnableAmount.ZERO.value, requestId);
                return;
            }
            int enable_amount = Integer.parseInt(enableAmount.substring(0, enableAmount.lastIndexOf(".")));
            ufxTradeResponse.resQueryEnable(enable_amount, requestId);
        }
    }

    /***
     * @Description: 大宗交易行情查询
     * @Param: [integratedQuote, password, loginInfo]
     * @return: java.util.List<com.joyintech.tams.exchange.common.bean.IntegratedQuote>
     * @Author: MAXX
     * @Date: 2020/3/24
     */
    public void queryIntegratedQuotes(Long requestId, QuoteQryBean quoteQryBean, LoginInfo loginInfo, String password) throws Exception {
        logger.info("queryIntegratedQuotes data :integratedQuote:{},  op_station:{}", JSONObject.toJSONString(quoteQryBean), op_station);
        String functionId = "";
        if (QuoteType.BULK_QUOTE.value == quoteQryBean.getQuoteType()
                || QuoteType.AFTER_BULK_QUOTE.value == quoteQryBean.getQuoteType()) {//大宗
            functionId = "398";
        } else if (QuoteType.FIXED_INCOME_QUOTE.value == quoteQryBean.getQuoteType()) {//固收
            functionId = "361";
        } else if (QuoteType.NON_PUBLIC_PROTOCOL_BACK_QUOTE.value == quoteQryBean.getQuoteType()
                || QuoteType.PUBLIC_PROTOCOL_BACK_QUOTE.value == quoteQryBean.getQuoteType()) {//上海债券质押式协议回购行情信息查询
            functionId = "332728";
        }
        if ("".equals(functionId)) {
            logger.error("queryIntegratedQuotes error no functionId");
            ufxTradeResponse.resQueryIntegratedQuotes(null, requestId);
            return;
        }
        IEvent event = ContextUtil.getServiceContext().getEventFactory()
                                  .getEventByAlias(functionId, EventType.ET_REQUEST);
        // 往event中添加dataset
        IDataset dataset = DatasetService.getDefaultInstance().getDataset();
        if ("398".equals(functionId) || "332728".equals(functionId)) {
            dataset.addColumn("op_branch_no");//操作分支机构
            dataset.addColumn("op_entrust_way");//委托方式
            dataset.addColumn("op_station");//站点地址
        }
        if ("398".equals(functionId)) {
            dataset.addColumn("exchange_type");//站点地址
            //dataset.addColumn("cbptrans_type");//综合业务指令类别
            dataset.addColumn("cbphq_type");//综合业务行情类别
        } else if ("332728".equals(functionId)) {
            dataset.addColumn("branch_no");//分支机构
            dataset.addColumn("password");//密码
            dataset.addColumn("client_id");//客户编号
            dataset.addColumn("fund_account");//资产账户
            dataset.addColumn("action_in");//操作控制值
            dataset.addColumn("position_str");//定位串
            dataset.addColumn("cbpconfer_id");//约定号
        }
        if ("398".equals(functionId) || "361".equals(functionId)) {
            dataset.addColumn("stock_code");//证券代码
        }
        dataset.appendRow();
        if ("398".equals(functionId) || "332728".equals(functionId)) {
            dataset.updateString("op_branch_no", "");
            dataset.updateString("op_entrust_way", builder.getOp_entrust_way());
            dataset.updateString("op_station", op_station);
        }
        if ("398".equals(functionId)) {
            String[] marketAndStockCode = StockCodeUtils.getMarketAndStockCode(quoteQryBean.getStockCode());
            if (marketAndStockCode[1].equals(Market.SZSE.value)) {
                dataset.updateString("exchange_type", ExchangeType.SZ.value);
            } else if (marketAndStockCode[1].equals(Market.SHSE.value)) {
                dataset.updateString("exchange_type", ExchangeType.SH.value);
            }
            //dataset.updateString("cbptrans_type",);
            dataset.updateString("cbphq_type", "00");
        } else if ("332728".equals(functionId)) {
            dataset.updateInt("branch_no", loginInfo.getBranchNo());
            dataset.updateString("password", password);
            dataset.updateString("client_id", loginInfo.getClientId());
            dataset.updateString("fund_account", loginInfo.getFundAccount());
            if (QuoteType.NON_PUBLIC_PROTOCOL_BACK_QUOTE.value == quoteQryBean.getQuoteType()) {//非公开报价行情
                dataset.updateInt("action_in", 1);
            } else if (QuoteType.PUBLIC_PROTOCOL_BACK_QUOTE.value == quoteQryBean.getQuoteType()) {//公开报价行情
                dataset.updateInt("action_in", 0);
            }
            dataset.updateString("position_str", quoteQryBean.getPostStr());
            dataset.updateString("cbpconfer_id", quoteQryBean.getContractId());
        }
        if ("398".equals(functionId) || "361".equals(functionId)) {
            String[] marketAndStockCode = StockCodeUtils.getMarketAndStockCode(quoteQryBean.getStockCode());
            dataset.updateString("stock_code", marketAndStockCode[0]);
        }
        event.setServiceId(functionId);//打入功能号
        event.putEventData(dataset); //入参打包
        unPack(event, functionId);
        IEvent rsp = client.sendReceive(event);//发送包
        if (rsp.getReturnCode() != ErrorCode.SUCCESS.value) {
            logger.warn("queryIntegratedQuotes failed ErrorNo:{}，reason:{}", rsp.getErrorNo(), rsp.getErrorInfo());
            ufxTradeResponse.resQueryIntegratedQuotes(null, requestId);
            return;
        }
        List<IntegratedQuote> list = UfxConverter.queryIntegratedQuotesReBack(quoteQryBean, rsp);
        logger.debug("queryIntegratedQuotes reback : {}", JSONArray.toJSONString(list));
        ufxTradeResponse.resQueryIntegratedQuotes(list, requestId);
    }

    /***
     * @Description: 固收可撤单委托查询
     * @Param: [loginInfo, password] 
     * @return: void
     * @Author: MAXX
     * @Date: 2020/4/1 
     */
    public void queryFixedIncomeOrder(LoginInfo loginInfo, String password) throws Exception {
        // 获取event
        // 往event中添加dataset
        IDataset dataset = DatasetService.getDefaultInstance().getDataset();
        dataset.addColumn("op_branch_no");//操作分支机构
        dataset.addColumn("op_entrust_way");//委托方式
        dataset.addColumn("op_station");//站点地址
        dataset.addColumn("branch_no");//分支机构
        dataset.addColumn("client_id");//客户编号
        dataset.addColumn("fund_account");//资产账户
        dataset.addColumn("password");//密码
        dataset.addColumn("user_token");//用户口令
        dataset.addColumn("request_num");//请求行数
        dataset.appendRow();
        dataset.updateString("op_branch_no", "");
        dataset.updateString("op_entrust_way", builder.getOp_entrust_way()); //op_entrust_way 打入7 网上交易
        dataset.updateString("op_station", op_station);
        dataset.updateInt("branch_no", loginInfo.getBranchNo());
        dataset.updateString("client_id", loginInfo.getClientId());
        dataset.updateString("fund_account", loginInfo.getFundAccount());
        byte[] out = new byte[7];
        CryptUtil.HsPwdEncode(password.getBytes(), out);
        dataset.updateByteArray("password", out);
        dataset.updateString("user_token", loginInfo.getUserToken());
        dataset.updateString("request_num", "1000");
        String funcCode = "332724";
        IEvent event = builder.genSyncEvent(funcCode, dataset);
        unPack(event, funcCode); //入参解包，同时打印发送包参数信息
        IEvent rsp = client.sendReceive(event);//发送包
        logger.info("queryFixedIncomeOrder reback :");
        unPack(rsp, funcCode);
        if (rsp.getReturnCode() != ErrorCode.SUCCESS.value) {
            logger.warn("queryFixedIncomeOrder failed ErrorNo:{}，reason:{}", rsp.getErrorNo(), rsp.getErrorInfo());
            return;
        }
    }

    /***
     * @Description: 协议大宗交易委托查询
     * @Param: [loginInfo, password]
     * @return: void
     * @Author: MAXX
     * @Date: 2020/4/1
     * businessFlag:3固收 2大宗
     */
    public void queryIntegratedOrder(LoginInfo loginInfo, String password, String accountId) throws Exception {
        queryIntegratedOrder(loginInfo, password, accountId, false);
    }

    public List<IntegratedEntrust> queryIntegratedOrder(LoginInfo loginInfo, String password, String accountId, boolean reissueFlag) throws Exception {
        // 往event中添加dataset
        IDataset dataset = DatasetService.getDefaultInstance().getDataset();
        PositionStr positionStr = positionStrMap.get(loginInfo.getFundAccount());
        if (Objects.isNull(positionStr)) {
            positionStr = new PositionStr();
        }
        dataset.addColumn("op_branch_no");//操作分支机构
        dataset.addColumn("op_entrust_way");//委托方式
        dataset.addColumn("op_station");//站点地址
        dataset.addColumn("branch_no");//分支机构
        dataset.addColumn("client_id");//客户编号
        dataset.addColumn("fund_account");//资产账户
        dataset.addColumn("password");//密码
        dataset.addColumn("user_token");//用户口令
        dataset.addColumn("en_entrust_prop");//允许委托属性
        dataset.addColumn("position_str");//定位串
        dataset.addColumn("request_num");//请求行数
        dataset.appendRow();
        dataset.updateString("op_branch_no", "");
        dataset.updateString("op_entrust_way", builder.getOp_entrust_way()); //op_entrust_way 打入7 网上交易
        dataset.updateString("op_station", op_station);
        dataset.updateInt("branch_no", loginInfo.getBranchNo());
        dataset.updateString("client_id", loginInfo.getClientId());
        dataset.updateString("fund_account", loginInfo.getFundAccount());
        byte[] out = new byte[7];
        CryptUtil.HsPwdEncode(password.getBytes(), out);
        dataset.updateByteArray("password", out);
        dataset.updateString("user_token", loginInfo.getUserToken());
        //‘a,b,c,e,AFW,AFC’即大宗交易
        dataset.updateString("en_entrust_prop", "BPN,BPR,BPT,BPB,BPH,BPI,FI1,FI3,FI9,a,b,c,e,AFW,AFC");//FI1（确定报价），FI3（点击成交），FI9（指定对手方报价）
        dataset.updateString("position_str", positionStr.getFixedPositionStr());
        dataset.updateString("request_num", "1000");
        String funcCode = "332704";
        IEvent event = builder.genSyncEvent(funcCode, dataset);
        unPack(event, funcCode); //入参解包，同时打印发送包参数信息
        IEvent rsp = client.sendReceive(event);//发送包
        if (rsp.getReturnCode() != ErrorCode.SUCCESS.value) {
            logger.warn("queryIntegratedOrder failed ErrorNo:{}，reason:{}", rsp.getErrorNo(), rsp.getErrorInfo());
            return new ArrayList<>();
        }
        List<IntegratedEntrust> list = UfxConverter.queryIntegratedOrder(rsp, accountId);
        logger.debug("queryIntegratedOrder reback : {}", JSONArray.toJSONString(list));
        if (list.size() == 0) {
            return list;
        }
        String position_str = list.get(list.size() - 1).getPositionStr();
        positionStr.setFixedPositionStr(position_str);
        positionStrMap.put(loginInfo.getFundAccount(), positionStr);
        if (!reissueFlag) {
            list.forEach(entrust -> {
                ufxTradeClient.onIntegratedEntrust(entrust);
            });
        }
        return list;
    }

    /***
     * @Description: 客户证券查询
     * @Param: [loginInfo, password]
     * @return: void
     * @Author: MAXX
     * @Date: 2020/4/8
     */
    public void qrySecufastStock(LoginInfo loginInfo, String password) throws Exception {
        // 往event中添加dataset
        IDataset dataset = DatasetService.getDefaultInstance().getDataset();
        dataset.addColumn("op_branch_no");//操作分支机构
        dataset.addColumn("op_entrust_way");//委托方式
        dataset.addColumn("op_station");//站点地址
        dataset.addColumn("branch_no");//分支机构
        dataset.addColumn("client_id");//客户编号
        dataset.addColumn("fund_account");//资产账户
        dataset.addColumn("password");//密码
        dataset.addColumn("request_num");//请求行数
        dataset.appendRow();
        dataset.updateString("op_branch_no", "");
        dataset.updateString("op_entrust_way", builder.getOp_entrust_way()); //op_entrust_way 打入7 网上交易
        dataset.updateString("op_station", op_station);
        dataset.updateInt("branch_no", loginInfo.getBranchNo());
        dataset.updateString("client_id", loginInfo.getClientId());
        dataset.updateString("fund_account", loginInfo.getFundAccount());
        dataset.updateString("password", password);
        dataset.updateString("request_num", "1000");
        String funcCode = "333620";
        IEvent event = builder.genSyncEvent(funcCode, dataset);
        unPack(event, funcCode); //入参解包，同时打印发送包参数信息
        IEvent rsp = client.sendReceive(event);//发送包
        logger.info("qrySecufastStock reback :  ");
        unPack(rsp, funcCode);
        if (rsp.getReturnCode() != ErrorCode.SUCCESS.value) {
            logger.warn("qrySecufastStock failed ErrorNo:{}，reason:{}", rsp.getErrorNo(), rsp.getErrorInfo());
            return;
        }
    }

    /***
     * @Description: 债券质押协议回购成交查询 固收成交查询 大宗
     * @Param: [loginInfo]
     * @return: void
     * @Author: MAXX
     * @Date: 2020/4/8
     */
    public void qryProtocolBackTrade(LoginInfo loginInfo, String password, String accountId) throws Exception {
        qryProtocolBackTrade(loginInfo, password, accountId, false);
    }

    public List<Trade> qryProtocolBackTrade(LoginInfo loginInfo, String password, String accountId, boolean reissueFlag) throws Exception {
        // 往event中添加dataset
        IDataset dataset = DatasetService.getDefaultInstance().getDataset();
        dataset.addColumn("op_branch_no");//操作分支机构
        dataset.addColumn("op_entrust_way");//委托方式
        dataset.addColumn("op_station");//站点地址
        dataset.addColumn("branch_no");//分支机构
        dataset.addColumn("client_id");//客户编号
        dataset.addColumn("fund_account");//资产账户
        dataset.addColumn("password");//密码
        PositionStr positionStr = positionStrMap.get(loginInfo.getFundAccount());
        if (!Objects.isNull(positionStr) && !Objects.isNull(positionStr.getProtocolBackPositionStr())) {
            dataset.addColumn("position_str");//定位串
        }
        dataset.addColumn("en_entrust_prop");//允许委托属性
        dataset.addColumn("request_num");//请求行数
        dataset.addColumn("query_type");//请求行数
        dataset.addColumn("user_token");
        dataset.appendRow();
        dataset.updateString("op_branch_no", "");
        dataset.updateString("op_entrust_way", builder.getOp_entrust_way()); //op_entrust_way 打入7 网上交易
        dataset.updateString("op_station", op_station);
        dataset.updateInt("branch_no", loginInfo.getBranchNo());
        dataset.updateString("client_id", loginInfo.getClientId());
        dataset.updateString("fund_account", loginInfo.getFundAccount());
        dataset.updateString("password", password);
        if (!Objects.isNull(positionStr) && !Objects.isNull(positionStr.getProtocolBackPositionStr())) {
            dataset.updateString("position_str", positionStr.getProtocolBackPositionStr());
        }
        String en_entrust_prop = "BPN,BPR,BPT,BPB,BPH,BPI,FI1,FI3,FI9,a,b,c,e,AFW,AFC";//固收 深圳and上海协议回购成交申报
        dataset.updateString("en_entrust_prop", en_entrust_prop);
        dataset.updateString("request_num", "1000");
        dataset.updateString("query_type", "0");
        dataset.updateString("user_token", loginInfo.getUserToken());
        IEvent event = builder.genSyncEvent("332705", dataset);
        unPack(event, event.getServiceId()); //入参解包，同时打印发送包参数信息
        IEvent rsp = client.sendReceive(event);//发送包
        if (rsp.getReturnCode() != ErrorCode.SUCCESS.value) {
            logger.warn("qryProtocolBack_Trade failed ErrorNo:{}，reason:{}", rsp.getErrorNo(), rsp.getErrorInfo());
            return new ArrayList<>();
        }
        List<Trade> list = UfxConverter.qryProtocolBack_Trade_Back(rsp, accountId);
        logger.debug("qryProtocolBack_Trade reback : {}", JSONArray.toJSONString(list));
        if (list.size() == 0) {
            return list;
        }
        String position_str = list.get(list.size() - 1).getPositionStr();
        if (Objects.isNull(positionStr)) {
            positionStr = new PositionStr();
        }
        positionStr.setProtocolBackPositionStr(position_str);
        positionStrMap.put(loginInfo.getFundAccount(), positionStr);
        if (!reissueFlag) {
            list.forEach(trade -> {
                ufxTradeClient.onTrade(trade);
            });
        }
        return list;
    }

    /***
     * @Description: 成交主动拉取
     * @Param: [loginInfo, password]
     * @return: void
     * @Author: MAXX
     * @Date: 2020/4/16
     */
    public void qryTrade(LoginInfo loginInfo, String password, String accountId) throws Exception {
        qryTrade(loginInfo, password, accountId, false);
    }

    public List<Trade> qryTrade(LoginInfo loginInfo, String password, String accountId, boolean reissueFlag) throws Exception {
        // 往event中添加dataset
        IDataset dataset = DatasetService.getDefaultInstance().getDataset();
        dataset.addColumn("user_token");//用户口令
        dataset.addColumn("op_branch_no");//操作分支机构
        dataset.addColumn("op_entrust_way");//委托方式
        dataset.addColumn("op_station");//站点地址
        dataset.addColumn("branch_no");//分支机构
        dataset.addColumn("client_id");//客户编号
        dataset.addColumn("fund_account");//资产账户
        dataset.addColumn("password");//密码
        PositionStr positionStr = positionStrMap.get(loginInfo.getFundAccount());
        if (!Objects.isNull(positionStr) && !Objects.isNull(positionStr.getTradePositionStr())) {
            dataset.addColumn("position_str");//定位串
        }
        dataset.addColumn("query_direction");//查询方向
        dataset.addColumn("request_num");//请求行数
        dataset.addColumn("query_type");//查询类别
        dataset.addColumn("query_mode");//查询模式
        dataset.appendRow();
        dataset.updateString("user_token", loginInfo.getUserToken());
        dataset.updateString("op_branch_no", "");
        dataset.updateString("op_entrust_way", builder.getOp_entrust_way());
        dataset.updateString("op_station", op_station);
        dataset.updateInt("branch_no", loginInfo.getBranchNo());
        dataset.updateString("client_id", loginInfo.getClientId());
        dataset.updateString("fund_account", loginInfo.getFundAccount());
        byte[] out = new byte[7];
        CryptUtil.HsPwdEncode(password.getBytes(), out);
        dataset.updateByteArray("password", out);
        if (!Objects.isNull(positionStr) && !Objects.isNull(positionStr.getTradePositionStr())) {
            dataset.updateString("position_str", positionStr.getTradePositionStr());
        }
        dataset.updateString("query_direction", "0");
        dataset.updateString("request_num", "1000");
        dataset.updateString("query_type", "0");
        dataset.updateString("query_mode", "0");
        IEvent event = builder.genSyncEvent("333102", dataset);
        unPack(event, event.getServiceId());
        IEvent rsp = client.sendReceive(event);//发送包
        if (rsp.getReturnCode() != ErrorCode.SUCCESS.value) {
            logger.warn("qryTrade failed ErrorNo:{}，reason:{}", rsp.getErrorNo(), rsp.getErrorInfo());
            return new ArrayList<>();
        }
        List<Trade> list = UfxConverter.parseQueryTradeMsg(rsp, accountId);
        logger.debug("qryTrade reback : {}", JSONArray.toJSONString(list));
        if (list.size() == 0) {
            return list;
        }
        String position_str = list.get(list.size() - 1).getPositionStr();
        if (Objects.isNull(positionStr)) {
            positionStr = new PositionStr();
        }
        positionStr.setTradePositionStr(position_str);
        positionStrMap.put(loginInfo.getFundAccount(), positionStr);
        if (!reissueFlag) {
            Set<String> businessIdSet = positionStr.getBusinessIdSet();
            list.forEach(trade -> {
                if (!Objects.isNull(businessIdSet) && businessIdSet.contains(trade.getBrokerTradeId())) {
                    logger.info("qryTrade 委托已发送，不重复发送:{}", trade.getBrokerTradeId());
                    return;
                }
                ufxTradeClient.onTrade(trade);
            });
        }
        return list;
    }

    /***
     * @Description: 委托主动拉取
     * @Param: [loginInfo, password]
     * @return: void
     * @Author: MAXX
     * @Date: 2020/4/16
     */
    public void qryOrder(LoginInfo loginInfo, String password, String accountId) throws Exception {
        qryOrder(loginInfo, password, accountId, false);
    }

    public List<Entrust> qryOrder(LoginInfo loginInfo, String password, String accountId, boolean reissueFlag) throws Exception {
        // 往event中添加dataset
        IDataset dataset = DatasetService.getDefaultInstance().getDataset();
        dataset.addColumn("op_branch_no");//操作分支机构
        dataset.addColumn("op_entrust_way");//委托方式
        dataset.addColumn("op_station");//站点地址
        dataset.addColumn("branch_no");//分支机构
        dataset.addColumn("client_id");//客户编号
        dataset.addColumn("fund_account");//资产账户
        dataset.addColumn("password");//密码
        dataset.addColumn("user_token");//用户口令
        PositionStr positionStr = positionStrMap.get(loginInfo.getFundAccount());
        if (!Objects.isNull(positionStr) && !Objects.isNull(positionStr.getOrderPositionStr())) {
            dataset.addColumn("position_str");//定位串
        }
        dataset.addColumn("request_num");//请求行数
        dataset.appendRow();
        dataset.updateString("op_branch_no", "");
        dataset.updateString("op_entrust_way", builder.getOp_entrust_way()); //op_entrust_way 打入7 网上交易
        dataset.updateString("op_station", op_station);
        dataset.updateInt("branch_no", loginInfo.getBranchNo());
        dataset.updateString("client_id", loginInfo.getClientId());
        dataset.updateString("fund_account", loginInfo.getFundAccount());
        byte[] out = new byte[7];
        CryptUtil.HsPwdEncode(password.getBytes(), out);
        dataset.updateByteArray("password", out);
        dataset.updateString("user_token", loginInfo.getUserToken());
        if (!Objects.isNull(positionStr) && !Objects.isNull(positionStr.getOrderPositionStr())) {
            dataset.updateString("position_str", positionStr.getOrderPositionStr());
        }
        dataset.updateString("request_num", "1000");
        IEvent event = builder.genSyncEvent("333101", dataset);
        unPack(event, event.getServiceId()); //入参解包，同时打印发送包参数信息
        IEvent rsp = client.sendReceive(event);//发送包
        if (rsp.getReturnCode() != ErrorCode.SUCCESS.value) {
            logger.warn("qryOrder failed ErrorNo:{}，reason:{}", rsp.getErrorNo(), rsp.getErrorInfo());
            return new ArrayList<>();
        }
        List<Entrust> list = UfxConverter.queryOrderReBack(rsp, accountId);
        logger.debug("qryOrder reback : {}", JSONArray.toJSONString(list));
        if (list.size() == 0) {
            return list;
        }
        String position_str = list.get(list.size() - 1).getPositionStr();
        if (Objects.isNull(positionStr)) {
            positionStr = new PositionStr();
        }
        positionStr.setOrderPositionStr(position_str);
        positionStrMap.put(loginInfo.getFundAccount(), positionStr);
        if (!reissueFlag) {
            Set<String> entrustNoSet = positionStr.getEntrustNoSet();
            list.forEach(entrust -> {
                if (!Objects.isNull(entrustNoSet) && entrustNoSet.contains(entrust.getBrokerEntrustId())) {
                    logger.info("qryOrder 委托已发送，不重复发送:{}", entrust.getBrokerEntrustId());
                    return;
                }
                ufxTradeClient.onEntrust(entrust);
            });
        }
        return list;
    }

    /***
     * @Description: 标准券查询
     * @Param: [password, loginInfo, market, stockCode, requestId]
     * @return: void
     * @Author: MAXX
     * @Date: 2020/5/9
     */
    public void queryStandardBonds(String password, LoginInfo loginInfo, StandardBondQryBean standardBondQryBean, long requestId) throws Exception {
        String[] marketAndStockCode = new String[2];
        String marketStr = standardBondQryBean.getMarket();
        if (!Strings.isNullOrEmpty(standardBondQryBean.getStockCode())) {
            marketAndStockCode = StockCodeUtils.getMarketAndStockCode(standardBondQryBean.getStockCode());
            marketStr = marketAndStockCode[1];
            if (!Strings.isNullOrEmpty(standardBondQryBean.getMarket()) && !standardBondQryBean.getMarket().equals(marketAndStockCode[1])) {
                ufxTradeResponse.resQueryStandardBonds(null, requestId);
                logger.error("查询标准券失败 {}", JsonUtils.getJsonFromObject(standardBondQryBean));
                return;
            }
        }
        if (Strings.isNullOrEmpty(standardBondQryBean.getMarket()) && Strings.isNullOrEmpty(standardBondQryBean.getStockCode())) {
            List<StandardBonds> szList = getStandardBonds(password, loginInfo, Market.SZSE.value);
            List<StandardBonds> shList = getStandardBonds(password, loginInfo, Market.SHSE.value);
            szList.addAll(shList);
            ufxTradeResponse.resQueryStandardBonds(szList, requestId);
            return;
        }
        List<StandardBonds> list = getStandardBonds(password, loginInfo, marketStr);
        ufxTradeResponse.resQueryStandardBonds(list, requestId);
    }

    /*** 
     * @Description: 质押回购可用查询
     * @Param: [password, loginInfo, market, stockCode, requestId] 
     * @return: void
     * @Author: MAXX
     * @Date: 2020/5/9
     */
    public List<StandardBonds> getStandardBonds(String password, LoginInfo loginInfo, String market) throws Exception {
        logger.info("queryStandardBonds data : password:{}, op_station:{}", password, op_station);
        // 往event中添加dataset
        IDataset dataset = DatasetService.getDefaultInstance().getDataset();
        dataset.addColumn("op_branch_no");//操作分支机构
        dataset.addColumn("op_entrust_way");//委托方式
        dataset.addColumn("op_station");//站点地址
        dataset.addColumn("branch_no");//分支机构
        dataset.addColumn("fund_account");//资产账户
        dataset.addColumn("password");//密码
        dataset.addColumn("exchange_type");//交易类别
        dataset.addColumn("stock_account");//证券账号
        dataset.appendRow();
        dataset.updateString("op_branch_no", "");
        dataset.updateString("op_entrust_way", builder.getOp_entrust_way());
        dataset.updateString("op_station", op_station);
        dataset.updateInt("branch_no", loginInfo.getBranchNo());
        dataset.updateString("fund_account", loginInfo.getFundAccount());
        dataset.updateString("password", password);
        List<ClientStkacct> clientStkacctList = loginInfo.getClientStkacctList();
        if (Objects.isNull(clientStkacctList)) {
            logger.error("queryStandardBonds error no clientStkacctList");
            return null;
        }
        if (Market.SZSE.value.equals(market)) {
            dataset.updateString("exchange_type", ExchangeType.SZ.value);
            clientStkacctList.forEach(clientStkacct -> {
                if (ExchangeType.SZ.value.equals(clientStkacct.getExchangeType())) {
                    dataset.updateString("stock_account", clientStkacct.getStockAccount());
                }
            });
        } else if (Market.SHSE.value.equals(market)) {
            dataset.updateString("exchange_type", ExchangeType.SH.value);
            clientStkacctList.forEach(clientStkacct -> {
                if (ExchangeType.SH.value.equals(clientStkacct.getExchangeType())) {
                    dataset.updateString("stock_account", clientStkacct.getStockAccount());
                }
            });
        }
        IEvent event = builder.genSyncEvent("333116", dataset);
        unPack(event, event.getServiceId());
        IEvent rsp = client.sendReceive(event);//发送包
        if (rsp.getReturnCode() != ErrorCode.SUCCESS.value) {
            logger.warn("queryStandardBonds failed ErrorNo:{}，reason:{}", rsp.getErrorNo(), rsp.getErrorInfo());
            return null;
        }
        List<StandardBonds> list = UfxConverter.standardBondsReturn(rsp);
        logger.debug("queryStandardBonds reback : {}", JSONArray.toJSONString(list));
        return list;
    }

    /**
     * @param loginInfo
     * @param
     * @return
     * @throws Exception
     */
    public List<Instrument> queryInstrumentInfo(LoginInfo loginInfo, InstrumentQryBean instrumentQryBean, Long requestId) throws Exception {
        logger.info("queryInstrumentInfo data : op_station:{}", op_station);
        // 往event中添加dataset
        IDataset dataset = DatasetService.getDefaultInstance().getDataset();
        dataset.addColumn("op_branch_no");//操作分支机构
        dataset.addColumn("op_entrust_way");//委托方式
        dataset.addColumn("op_station");//站点地址
        dataset.addColumn("query_type");//查询类别
        dataset.addColumn("exchange_type");//交易类别
        dataset.addColumn("stock_type");//证券类别
        dataset.addColumn("stock_code");//证券代码
        dataset.addColumn("position_str");//定位串
        dataset.addColumn("request_num");//请求行数
        dataset.appendRow();
        dataset.updateString("op_branch_no", "");
        dataset.updateString("op_entrust_way", builder.getOp_entrust_way());
        dataset.updateString("op_station", op_station);
        dataset.updateInt("query_type", 1);//（0：默认，查询单个代码或重复代码；1：查询全部代码；2：按stock_code条件查询全部代码）
        if (Market.SHSE.value.equals(instrumentQryBean.getMarket()))
            dataset.updateString("exchange_type", ExchangeType.SH.value);
        else if (Market.SZSE.value.equals(instrumentQryBean.getMarket()))
            dataset.updateString("exchange_type", ExchangeType.SZ.value);
        else
            logger.warn("unknown market {}", instrumentQryBean.getMarket());
        dataset.updateString("stock_type", "");
        dataset.updateString("stock_code", "");
        dataset.updateString("position_str", instrumentQryBean.getPostStr());
        dataset.updateInt("request_num", instrumentQryBean.getRowCount());
        List<ClientStkacct> clientStkacctList = loginInfo.getClientStkacctList();
        if (Objects.isNull(clientStkacctList)) {
            logger.error("queryInstrumentInfo error no clientStkacctList");
            return null;
        }
        IEvent event = builder.genSyncEvent("333116", dataset);
        unPack(event, event.getServiceId());
        IEvent rsp = client.sendReceive(event);//发送包
        if (rsp.getReturnCode() != ErrorCode.SUCCESS.value) {
            logger.warn("queryInstrumentInfo failed ErrorNo:{}，reason:{}", rsp.getErrorNo(), rsp.getErrorInfo());
            return null;
        }
        List<Instrument> list = UfxConverter.instrumentInfoReturn(rsp);
        logger.debug("queryInstrumentInfo reback : {}", JSONArray.toJSONString(list));
        return list;
    }

    /***
     * @Description: 协议回购行情查询
     * @Param: [requestId, integratedRepurchaseQuoteQryBean, loginInfo, password]
     * @return: void
     * @Author: MAXX
     * @Date: 2020/9/22
     */
    public void queryRepoQuotes(Long requestId, IntegratedRepurchaseQuoteQryBean integratedRepurchaseQuoteQryBean, LoginInfo loginInfo, String password) throws Exception {
        logger.info("queryRepoQuotes data :integratedRepurchaseQuoteQryBean:{},  op_station:{}", JSONObject.toJSONString(integratedRepurchaseQuoteQryBean), op_station);
        String functionId = "332728";
        String positionStr = "";
        if (integratedRepurchaseQuoteQryBean.getPageSize() == 0) {//不翻页 组合全部给出
            int pageSize = 1000;
            int size = pageSize;
            integratedRepurchaseQuoteQryBean.setPageSize(pageSize);
            List<IntegratedRepurchaseQuoteBean> reList = new ArrayList<IntegratedRepurchaseQuoteBean>();
            while (pageSize == size) {
                IEvent rsp = getQueryIntegratedRepurchaseQuote(positionStr, functionId, loginInfo, password, integratedRepurchaseQuoteQryBean);
                unPack(rsp, functionId);
                if (rsp.getReturnCode() != ErrorCode.SUCCESS.value) {
                    logger.warn("queryRepoQuotes failed ErrorNo:{}，reason:{}", rsp.getErrorNo(), rsp.getErrorInfo());
                    ufxTradeResponse.resQueryIntegratedRepurchaseQuote(null, requestId);
                    return;
                }
                List<IntegratedRepurchaseQuoteBean> list = UfxConverter.queryRepoQuotesReBack(rsp);
                pageSize = list == null ? 0 : list.size();
                logger.debug("pageSize : {}", pageSize);
                reList.addAll(list);
                if (pageSize == size) {
                    positionStr = list.get(pageSize - 1).getPositionStr();
                }
            }
            logger.debug("queryRepoQuotes reback : {}", JSONArray.toJSONString(reList));
            ufxTradeResponse.resQueryIntegratedRepurchaseQuote(reList, requestId);
        } else {
            IEvent rsp = getQueryIntegratedRepurchaseQuote(positionStr, functionId, loginInfo, password, integratedRepurchaseQuoteQryBean);
            if (rsp.getReturnCode() != ErrorCode.SUCCESS.value) {
                logger.warn("queryRepoQuotes failed ErrorNo:{}，reason:{}", rsp.getErrorNo(), rsp.getErrorInfo());
                ufxTradeResponse.resQueryIntegratedRepurchaseQuote(null, requestId);
                return;
            }
            List<IntegratedRepurchaseQuoteBean> list = UfxConverter.queryRepoQuotesReBack(rsp);
            logger.debug("queryRepoQuotes reback : {}", JSONArray.toJSONString(list));
            ufxTradeResponse.resQueryIntegratedRepurchaseQuote(list, requestId);
        }
    }

    private IEvent getQueryIntegratedRepurchaseQuote(String positionStr, String functionId, LoginInfo loginInfo, String password, IntegratedRepurchaseQuoteQryBean integratedRepurchaseQuoteQryBean) throws Exception {
        // 往event中添加dataset
        IDataset dataset = DatasetService.getDefaultInstance().getDataset();
        dataset.addColumn("op_branch_no");//操作分支机构
        dataset.addColumn("op_entrust_way");//委托方式
        dataset.addColumn("op_station");//站点地址
        dataset.addColumn("branch_no");//分支机构
        dataset.addColumn("password");//密码
        dataset.addColumn("client_id");//客户编号
        dataset.addColumn("fund_account");//资产账户
        dataset.addColumn("action_in");//操作控制值
        if (!"".equals(integratedRepurchaseQuoteQryBean.getEntrustProp())) {
            dataset.addColumn("entrust_prop");//委托属性
        }
        dataset.addColumn("request_num");//请求行数
        dataset.addColumn("position_str");//定位串
        dataset.appendRow();
        dataset.updateString("op_branch_no", "");
        dataset.updateString("op_entrust_way", "D");
        dataset.updateString("op_station", op_station);
        dataset.updateInt("branch_no", loginInfo.getBranchNo());
        dataset.updateString("password", password);
        dataset.updateString("client_id", loginInfo.getClientId());
        dataset.updateString("fund_account", loginInfo.getFundAccount());
        dataset.updateInt("action_in", integratedRepurchaseQuoteQryBean.getActionIn());//非公开报价行情
        if (!"".equals(integratedRepurchaseQuoteQryBean.getEntrustProp())) {
            dataset.updateString("entrust_prop", integratedRepurchaseQuoteQryBean.getEntrustProp());//委托属性
        }
        dataset.updateInt("request_num", integratedRepurchaseQuoteQryBean.getPageSize());//请求行数
        dataset.updateString("position_str", positionStr);//定位串
        IEvent event = builder.genSyncEvent(functionId, dataset);
        unPack(event, functionId);
        return client.sendReceive(event);//发送包
    }

    /***
     * @Description: 大宗交易行情查询
     * @Param: [requestId, integratedBulkQuoteQryBean, loginInfo, password]
     * @return: void
     * @Author: MAXX
     * @Date: 2020/9/22
     */
    public void queryIntegratedBulkQuote(Long requestId, IntegratedBulkQuoteQryBean integratedBulkQuoteQryBean) throws Exception {
        logger.info("queryIntegratedBulkQuote data :integratedBulkQuoteQryBean:{},  op_station:{}", JSONObject.toJSONString(integratedBulkQuoteQryBean), op_station);
        String positionStr = "";
        String functionId = "398";
        String cbptransType = CbptransTypeUtils.getCbptransTypeByEntrustBsAndEntrustProp(integratedBulkQuoteQryBean.getEntrustBs(), integratedBulkQuoteQryBean.getEntrustProp());
        if (integratedBulkQuoteQryBean.getPageSize() == 0) {//不翻页 组合全部给出
            int pageSize = 1000;
            int size = pageSize;
            integratedBulkQuoteQryBean.setPageSize(pageSize);
            List<IntegratedBulkQuoteBean> reList = new ArrayList<IntegratedBulkQuoteBean>();
            while (pageSize == size) {
                IEvent rsp = getQueryIntegratedBulkQuote(cbptransType, integratedBulkQuoteQryBean, positionStr, functionId);
                unPack(rsp, functionId);
                if (rsp.getReturnCode() != ErrorCode.SUCCESS.value) {
                    logger.warn("queryIntegratedBulkQuote failed ErrorNo:{}，reason:{}", rsp.getErrorNo(), rsp.getErrorInfo());
                    ufxTradeResponse.resQueryIntegratedBulkQuote(null, requestId);
                    return;
                }
                List<IntegratedBulkQuoteBean> list = UfxConverter.queryIntegratedBulkQuoteReBack(rsp, integratedBulkQuoteQryBean);
                pageSize = list == null ? 0 : list.size();
                logger.debug("pageSize : {}", pageSize);
                reList.addAll(list);
                if (pageSize == size) {
                    positionStr = list.get(pageSize - 1).getPositionStr();
                }
            }
            logger.debug("queryIntegratedBulkQuote reback : {}", JSONArray.toJSONString(reList));
            ufxTradeResponse.resQueryIntegratedBulkQuote(reList, requestId);
        } else {
            IEvent rsp = getQueryIntegratedBulkQuote(cbptransType, integratedBulkQuoteQryBean, positionStr, functionId);
            if (rsp.getReturnCode() != ErrorCode.SUCCESS.value) {
                logger.warn("queryIntegratedBulkQuote failed ErrorNo:{}，reason:{}", rsp.getErrorNo(), rsp.getErrorInfo());
                ufxTradeResponse.resQueryIntegratedBulkQuote(null, requestId);
                return;
            }
            List<IntegratedBulkQuoteBean> list = UfxConverter.queryIntegratedBulkQuoteReBack(rsp, integratedBulkQuoteQryBean);
            logger.debug("queryIntegratedBulkQuote reback : {}", JSONArray.toJSONString(list));
            ufxTradeResponse.resQueryIntegratedBulkQuote(list, requestId);
        }
    }

    private IEvent getQueryIntegratedBulkQuote(String cbptransType, IntegratedBulkQuoteQryBean integratedBulkQuoteQryBean, String positionStr, String functionId) throws Exception {
        // 往event中添加dataset
        IDataset dataset = DatasetService.getDefaultInstance().getDataset();
        dataset.addColumn("op_branch_no");//操作分支机构
        dataset.addColumn("op_entrust_way");//委托方式
        dataset.addColumn("op_station");//站点地址
        dataset.addColumn("exchange_type");//站点地址
        dataset.addColumn("stock_code");//证券代码
        dataset.addColumn("cbptrans_type");//综合业务指令类别
        dataset.addColumn("cbphq_type");//综合业务行情类别
        dataset.addColumn("request_num");//请求行数
        dataset.addColumn("position_str");//定位串
        dataset.appendRow();
        dataset.updateString("op_branch_no", "");
        dataset.updateString("op_entrust_way", "D");
        dataset.updateString("op_station", op_station);
        if (!Strings.isNullOrEmpty(integratedBulkQuoteQryBean.getStockCode())) {
            String[] marketAndStockCode = StockCodeUtils.getMarketAndStockCode(integratedBulkQuoteQryBean.getStockCode());
            if (marketAndStockCode[1].equals(Market.SZSE.value)) {
                dataset.updateString("exchange_type", ExchangeType.SZ.value);
            } else if (marketAndStockCode[1].equals(Market.SHSE.value)) {
                dataset.updateString("exchange_type", ExchangeType.SH.value);
            }
            dataset.updateString("stock_code", marketAndStockCode[0]);
        }
        dataset.updateString("cbptrans_type", cbptransType);
        dataset.updateString("cbphq_type", "00");
        dataset.updateInt("request_num", integratedBulkQuoteQryBean.getPageSize());
        dataset.updateString("position_str", positionStr);
        IEvent event = builder.genSyncEvent(functionId, dataset);
        unPack(event, functionId);
        return client.sendReceive(event);//发送包
    }

    /***
     * @Description: 盘后大宗交易行情查询
     * @Param: [requestId, integratedBulkCloseQuoteQryBean, loginInfo, password]
     * @return: void
     * @Author: MAXX
     * @Date: 2020/9/22
     */
    public void queryIntegratedBulkCloseQuote(Long requestId, IntegratedBulkCloseQuoteQryBean integratedBulkCloseQuoteQryBean) throws Exception {
        logger.info("queryIntegratedBulkCloseQuote data :integratedBulkCloseQuoteQryBean:{},  op_station:{}", JSONObject.toJSONString(integratedBulkCloseQuoteQryBean), op_station);
        String[] marketAndStockCode = StockCodeUtils.getMarketAndStockCode(integratedBulkCloseQuoteQryBean.getStockCode());
        String functionId = "";
        if (marketAndStockCode[1].equals(Market.SZSE.value)) {
            functionId = "394";
        } else if (marketAndStockCode[1].equals(Market.SHSE.value)) {
            functionId = "364";
        }
        if ("".equals(functionId)) {
            logger.error("queryIntegratedBulkCloseQuote error no functionId");
            ufxTradeResponse.resQueryIntegratedBulkCloseQuote(null, requestId);
            return;
        }
        // 往event中添加dataset
        IDataset dataset = DatasetService.getDefaultInstance().getDataset();
        dataset.addColumn("exchange_type");//站点地址
        dataset.addColumn("stock_code");//证券代码
        dataset.appendRow();
        dataset.updateString("exchange_type", integratedBulkCloseQuoteQryBean.getExchangeType());
        dataset.updateString("stock_code", marketAndStockCode[0]);
        IEvent event = builder.genSyncEvent(functionId, dataset);
        unPack(event, functionId);
        IEvent rsp = client.sendReceive(event);//发送包
        if (rsp.getReturnCode() != ErrorCode.SUCCESS.value) {
            logger.warn("queryIntegratedBulkCloseQuote failed ErrorNo:{}，reason:{}", rsp.getErrorNo(), rsp.getErrorInfo());
            ufxTradeResponse.resQueryIntegratedBulkCloseQuote(null, requestId);
            return;
        }
        List<IntegratedBulkCloseQuoteBean> list = UfxConverter.qryIntegratedBulkCloseQuoteReBack(rsp, integratedBulkCloseQuoteQryBean);
        logger.debug("queryIntegratedBulkCloseQuote reback : {}", JSONArray.toJSONString(list));
        ufxTradeResponse.resQueryIntegratedBulkCloseQuote(list, requestId);
    }

    /*** 
     * @Description: 上海固收行情查询
     * @Param: [requestId, integratedFixedIncomeQuoteQryBean, loginInfo, password] 
     * @return: void
     * @Author: MAXX
     * @Date: 2020/9/22 
     */
    public void queryIntegratedFixedIncomeQuote(Long requestId, IntegratedFixedIncomeQuoteQryBean integratedFixedIncomeQuoteQryBean) throws Exception {
        logger.info("queryIntegratedFixedIncomeQuote data :integratedFixedIncomeQuoteQryBean:{},  op_station:{}", JSONObject.toJSONString(integratedFixedIncomeQuoteQryBean), op_station);
        String functionId = "361";
        // 往event中添加dataset
        IDataset dataset = DatasetService.getDefaultInstance().getDataset();
        dataset.addColumn("exchange_type");//站点地址
        dataset.addColumn("stock_code");//证券代码
        dataset.appendRow();
        String[] marketAndStockCode = StockCodeUtils.getMarketAndStockCode(integratedFixedIncomeQuoteQryBean.getStockCode());
        dataset.updateString("exchange_type", integratedFixedIncomeQuoteQryBean.getExchangeType());
        dataset.updateString("stock_code", marketAndStockCode[0]);
        IEvent event = builder.genSyncEvent(functionId, dataset);
        unPack(event, functionId);
        IEvent rsp = client.sendReceive(event);//发送包
        unPack(rsp, functionId);
        if (rsp.getReturnCode() != ErrorCode.SUCCESS.value) {
            logger.warn("queryIntegratedFixedIncomeQuote failed ErrorNo:{}，reason:{}", rsp.getErrorNo(), rsp.getErrorInfo());
            ufxTradeResponse.resQueryIntegratedFixedIncomeQuote(null, requestId);
            return;
        }
        List<IntegratedFixedIncomeQuoteBean> list = UfxConverter.qryIntegratedFixedIncomeQuoteReBack(rsp, integratedFixedIncomeQuoteQryBean);
        logger.info("queryIntegratedFixedIncomeQuote reback : {}", JSONArray.toJSONString(list));
        ufxTradeResponse.resQueryIntegratedFixedIncomeQuote(list, requestId);
    }

    //债券质押式协议回购_首期_深交所
    private void repurchaseSQSZ(Long requestId, IntegratedEntrust integratedEntrust, String password, LoginInfo loginInfo) throws Exception {
        /* List<ClientStkacct> clientStkacctList = loginInfo.getClientStkacctList();
        if (Objects.isNull(clientStkacctList)) {
            logger.error("repurchase_SQ_SZ error no clientStkacctList");
            integratedEntrust.setEntrustStatus(EntrustStatus.INVALID.value);
            integratedEntrust.setErrorMsg("帐号" + integratedEntrust.getAccountId() + "证券股东信息获取失败");
            integratedEntrust.setErrorCode(ErrorCode.TERMINAL_API_ERROR.value);
            ufxTradeResponse.resIntegratedEntrust(integratedEntrust);
            return;
        }*/
        String functionId = "332400";
        IDataset dataset = DatasetService.getDefaultInstance().getDataset();
        dataset.addColumn("op_branch_no");//操作分支机构
        dataset.addColumn("op_entrust_way");//委托方式
        dataset.addColumn("op_station");//站点地址
        dataset.addColumn("branch_no");//分支机构
        dataset.addColumn("client_id");//客户编号
        dataset.addColumn("fund_account");//资产账户
        dataset.addColumn("password");//密码
        dataset.addColumn("exchange_type");//交易类别
        //dataset.addColumn("stock_account");//证券账号
        dataset.addColumn("stock_code");//证券代码
        dataset.addColumn("cbpconfer_id");//约定号
        dataset.addColumn("entrust_amount");//委托数量
        dataset.addColumn("entrust_bs");//买卖方向
        dataset.addColumn("entrust_balance");//委托金额
        dataset.addColumn("date_back");//转入日期
        dataset.addColumn("prop_seat_no");//对方席位号
        dataset.addColumn("expire_year_rate");//到期年收益率
        dataset.addColumn("back_term");//购回期限
        dataset.addColumn("entrust_reference");//委托引用
        dataset.appendRow();
        dataset.updateString("op_branch_no", "");
        dataset.updateString("op_entrust_way", builder.getOp_entrust_way());
        dataset.updateString("op_station", op_station);
        dataset.updateInt("branch_no", loginInfo.getBranchNo());
        dataset.updateString("client_id", loginInfo.getClientId());
        dataset.updateString("fund_account", loginInfo.getFundAccount());
        dataset.updateString("password", password);
        if (Market.SZSE.value.equals(integratedEntrust.getMarket())) {
            dataset.updateString("exchange_type", ExchangeType.SZ.value);
            /*clientStkacctList.forEach(clientStkacct -> {
                if (ExchangeType.SZ.value.equals(clientStkacct.getExchangeType())) {
                    dataset.updateString("stock_account", clientStkacct.getStockAccount());
                }
            });*/
        } else if (Market.SHSE.value.equals(integratedEntrust.getMarket())) {
            dataset.updateString("exchange_type", ExchangeType.SH.value);
            /*clientStkacctList.forEach(clientStkacct -> {
                if (ExchangeType.SH.value.equals(clientStkacct.getExchangeType())) {
                    dataset.updateString("stock_account", clientStkacct.getStockAccount());
                }
            });*/
        }
        String stockCode = StockCodeUtils.getStockCode(integratedEntrust.getStockCode());
        dataset.updateString("stock_code", stockCode);
        dataset.updateString("cbpconfer_id", integratedEntrust.getContractId());
        dataset.updateLong("entrust_amount", integratedEntrust.getEntrustVolume());
        dataset.updateString("entrust_bs", integratedEntrust.getEntrustBs());
        dataset.updateDouble("entrust_balance", integratedEntrust.getEntrustBalance().doubleValue());
        dataset.updateString("date_back", integratedEntrust.getBackDate());
        dataset.updateString("prop_seat_no", integratedEntrust.getPropSeatNo());
        dataset.updateDouble("expire_year_rate", UfxConverter.convertExpireYearRate(integratedEntrust.getEntrustPrice()));
        dataset.updateInt("back_term", integratedEntrust.getCompactTerm());
        dataset.updateString("entrust_reference", integratedEntrust.getId());
        IEvent event = builder.genEvent(functionId, dataset);
        event.markTimestamp(true);
        event.addTimestamp(Long.parseLong(integratedEntrust.getId().substring(1)), requestId.toString());
        unPack(event, functionId);
        client.send(event);//发送包
    }

    //债券质押式协议回购_上交所
    private void repurchaseSH(Long requestId, IntegratedEntrust integratedEntrust, String password, LoginInfo loginInfo) throws Exception {
        List<ClientStkacct> clientStkacctList = loginInfo.getClientStkacctList();
        if (Objects.isNull(clientStkacctList)) {
            logger.error("repurchase_SH error no clientStkacctList");
            integratedEntrust.setEntrustStatus(EntrustStatus.INVALID.value);
            integratedEntrust.setErrorMsg("帐号" + integratedEntrust.getAccountId() + "证券股东信息获取失败");
            integratedEntrust.setErrorCode(ErrorCode.TERMINAL_API_ERROR.value);
            ufxTradeResponse.resIntegratedEntrust(integratedEntrust);
            return;
        }
        String functionId = "332725";
        IDataset dataset = builder.genRepurchaseSHDataSet(integratedEntrust, loginInfo, password);
        IEvent event = builder.genEvent(functionId, dataset);
        event.markTimestamp(true);
        event.addTimestamp(Long.parseLong(integratedEntrust.getId().substring(1)), requestId.toString());
        unPack(event, functionId);
        client.send(event);//发送包
    }

    private void bulkIntegratedEntrust(Long requestId, IntegratedEntrust integratedEntrust, String password, LoginInfo loginInfo) throws Exception {
        // 往event中添加dataset
        String functionId = "332702";
        IDataset dataset = DatasetService.getDefaultInstance().getDataset();
        dataset.addColumn("op_branch_no");//操作分支机构
        dataset.addColumn("op_entrust_way");//委托方式
        dataset.addColumn("op_station");//站点地址
        dataset.addColumn("branch_no");//分支机构
        dataset.addColumn("client_id");//客户编号
        dataset.addColumn("fund_account");//资产账户
        dataset.addColumn("password");//密码
        dataset.addColumn("exchange_type");//交易类别
        dataset.addColumn("stock_account");//证券账号
        dataset.addColumn("stock_code");//证券代码
        dataset.addColumn("entrust_price");//委托价格
        dataset.addColumn("entrust_prop");//委托属性
        dataset.addColumn("entrust_amount");//委托数量
        dataset.addColumn("entrust_bs");//买卖方向
        //意向申报/定价/成交申报卖出
        if (!((EntrustBs.SELL.value.equals(integratedEntrust.getEntrustBs()) &&
                (EntrustProp.CONFIRM.value.equals(integratedEntrust.getEntrustProp())
                        || EntrustProp.SET_PRICE.value.equals(integratedEntrust.getEntrustProp())
                        || EntrustProp.INTENTION.value.equals(integratedEntrust.getEntrustProp())))
                || (EntrustBs.BUY.value.equals(integratedEntrust.getEntrustBs()) &&
                (EntrustProp.LARGE_AFTER_CLOSING_WEIGHT_PRICE.value.equals(integratedEntrust.getEntrustProp())
                ))
                    /*|| (EntrustBs.BUY.value.equals(integratedEntrust.getEntrustBs()) &&
                    Market.SZSE.value.equals(integratedEntrust.getMarket()) &&
                    (EntrustProp.INTENTION.value.equals(integratedEntrust.getEntrustProp())
                            || EntrustProp.SET_PRICE.value.equals(integratedEntrust.getEntrustProp())
                    )
            )*/
        )) {
            dataset.addColumn("entrust_balance");//委托金额
        }
        if (IntegratedEntrustUtils.isBulk_YX_DJ(integratedEntrust.getBsFlag())) {//意向申报 定价申报
            dataset.addColumn("relation_name");//联系人姓名
            dataset.addColumn("relation_tel");//联系人电话
        } else if (IntegratedEntrustUtils.isBulk_DJCJ_CJ(integratedEntrust.getBsFlag())) {// 点击成交申报
            dataset.addColumn("prop_seat_no");//对方席位号
            dataset.addColumn("cbpconfer_id");//约定号
        }
        dataset.addColumn("entrust_reference");//委托引用
        dataset.appendRow();
        dataset.updateString("op_branch_no", "");
        dataset.updateString("op_entrust_way", builder.getOp_entrust_way());
        dataset.updateString("op_station", op_station);
        dataset.updateInt("branch_no", loginInfo.getBranchNo());
        dataset.updateString("client_id", loginInfo.getClientId());
        dataset.updateString("fund_account", loginInfo.getFundAccount());
        dataset.updateString("password", password);
        List<ClientStkacct> clientStkacctList = loginInfo.getClientStkacctList();
        if (Objects.isNull(clientStkacctList)) {//大宗
            logger.error("integratedEntrust error no clientStkacctList");
            integratedEntrust.setEntrustStatus(EntrustStatus.INVALID.value);
            integratedEntrust.setErrorMsg("帐号" + integratedEntrust.getAccountId() + "证券股东信息获取失败");
            integratedEntrust.setErrorCode(ErrorCode.TERMINAL_API_ERROR.value);
            ufxTradeResponse.resIntegratedEntrust(integratedEntrust);
            return;
        }
        if (Market.SZSE.value.equals(integratedEntrust.getMarket())) {
            dataset.updateString("exchange_type", ExchangeType.SZ.value);
            clientStkacctList.forEach(clientStkacct -> {
                if (ExchangeType.SZ.value.equals(clientStkacct.getExchangeType())) {
                    dataset.updateString("stock_account", clientStkacct.getStockAccount());
                }
            });
        } else if (Market.SHSE.value.equals(integratedEntrust.getMarket())) {
            dataset.updateString("exchange_type", ExchangeType.SH.value);
            clientStkacctList.forEach(clientStkacct -> {
                if (ExchangeType.SH.value.equals(clientStkacct.getExchangeType())) {
                    dataset.updateString("stock_account", clientStkacct.getStockAccount());
                }
            });
        }
        String stockCode = StockCodeUtils.getStockCode(integratedEntrust.getStockCode());
        dataset.updateString("stock_code", stockCode);
        if (FullPriceConst.FULL_PRICE.value.equals(integratedEntrust.getIsFullPrice())) {
            dataset.updateDouble("entrust_price", integratedEntrust.getFullPrice().doubleValue());
        } else {
            dataset.updateDouble("entrust_price", integratedEntrust.getEntrustPrice().doubleValue());
        }
        dataset.updateString("entrust_prop", integratedEntrust.getEntrustProp());
        dataset.updateLong("entrust_amount", integratedEntrust.getEntrustVolume());
        dataset.updateString("entrust_bs", integratedEntrust.getEntrustBs());
        //意向申报/定价/成交申报卖出
        if (!((EntrustBs.SELL.value.equals(integratedEntrust.getEntrustBs()) &&
                (EntrustProp.CONFIRM.value.equals(integratedEntrust.getEntrustProp())
                        || EntrustProp.SET_PRICE.value.equals(integratedEntrust.getEntrustProp())
                        || EntrustProp.INTENTION.value.equals(integratedEntrust.getEntrustProp())))
                || (EntrustBs.BUY.value.equals(integratedEntrust.getEntrustBs()) &&
                (EntrustProp.LARGE_AFTER_CLOSING_WEIGHT_PRICE.value.equals(integratedEntrust.getEntrustProp())
                ))
                    /*|| (EntrustBs.BUY.value.equals(integratedEntrust.getEntrustBs()) &&
                    Market.SZSE.value.equals(integratedEntrust.getMarket()) &&
                    (EntrustProp.INTENTION.value.equals(integratedEntrust.getEntrustProp())
                            || EntrustProp.SET_PRICE.value.equals(integratedEntrust.getEntrustProp())
                    )
            )*/
        )) {
            dataset.updateDouble("entrust_balance", integratedEntrust.getEntrustBalance().doubleValue());
        }
        if (IntegratedEntrustUtils.isBulk_YX_DJ(integratedEntrust.getBsFlag())) {//意向申报 定价申报
            dataset.updateString("relation_name", integratedEntrust.getLiaisonName());
            dataset.updateString("relation_tel", integratedEntrust.getLiaisonTel());
        } else if (IntegratedEntrustUtils.isBulk_DJCJ_CJ(integratedEntrust.getBsFlag())) {// 点击成交申报
            dataset.updateString("prop_seat_no", integratedEntrust.getPropSeatNo());
            dataset.updateString("cbpconfer_id", integratedEntrust.getContractId());
        }
        dataset.updateString("entrust_reference", integratedEntrust.getId());
        integratedEntrust.setEntrustStatus(EntrustStatus.TOBE_REPORTED.value);
        IEvent event = builder.genEvent(functionId, dataset);
        event.markTimestamp(true);
        event.addTimestamp(Long.parseLong(integratedEntrust.getId().substring(1)), requestId.toString());
        unPack(event, functionId);
        client.send(event);//发送包
    }

    private void fixedIncome(Long requestId, IntegratedEntrust integratedEntrust, LoginInfo loginInfo, String password) throws Exception {
        String functionId = "332722";
        IDataset dataset = builder.genFixedIncomeEntrustDataSet(integratedEntrust, loginInfo, password);
        integratedEntrust.setEntrustStatus(EntrustStatus.TOBE_REPORTED.value);
        IEvent event = builder.genEvent(functionId, dataset);
        event.markTimestamp(true);
        event.addTimestamp(Long.parseLong(integratedEntrust.getId().substring(1)), requestId.toString());
        unPack(event, functionId);
        client.send(event);//发送包
    }

    //深圳债券质押协议回购购回交易
    private void repurchaseBuybackSZ(Long requestId, IntegratedEntrust integratedEntrust, String password, LoginInfo loginInfo) throws Exception {
        List<ClientStkacct> clientStkacctList = loginInfo.getClientStkacctList();
        if (Objects.isNull(clientStkacctList)) {
            logger.error("repurchase_Buyback_SZ error no clientStkacctList");
            integratedEntrust.setEntrustStatus(EntrustStatus.INVALID.value);
            integratedEntrust.setErrorMsg("帐号" + integratedEntrust.getAccountId() + "证券股东信息获取失败");
            integratedEntrust.setErrorCode(ErrorCode.TERMINAL_API_ERROR.value);
            ufxTradeResponse.resIntegratedEntrust(integratedEntrust);
            return;
        }
        String functionId = "332401";
        IDataset dataset = DatasetService.getDefaultInstance().getDataset();
        dataset.addColumn("op_branch_no");//操作分支机构
        dataset.addColumn("op_entrust_way");//委托方式
        dataset.addColumn("op_station");//站点地址
        dataset.addColumn("branch_no");//分支机构
        dataset.addColumn("client_id");//客户编号
        dataset.addColumn("fund_account");//资产账户
        dataset.addColumn("password");//密码
        dataset.addColumn("stock_account");//证券账号
        dataset.addColumn("cbpconfer_id");//约定号
        dataset.addColumn("entrust_balance");//委托金额
        dataset.addColumn("entrust_bs");//买卖方向
        dataset.addColumn("expire_year_rate");//到期年收益率
        dataset.addColumn("orig_report_id");//原申报合同序号
        dataset.addColumn("original_date");//原申请日期
        dataset.addColumn("compact_term");//合约期限
        dataset.appendRow();
        dataset.updateString("op_branch_no", "");
        dataset.updateString("op_entrust_way", builder.getOp_entrust_way());
        dataset.updateString("op_station", op_station);
        dataset.updateInt("branch_no", loginInfo.getBranchNo());
        dataset.updateString("client_id", loginInfo.getClientId());
        dataset.updateString("fund_account", loginInfo.getFundAccount());
        dataset.updateString("password", password);
        if (Market.SZSE.value.equals(integratedEntrust.getMarket())) {
            clientStkacctList.forEach(clientStkacct -> {
                if (ExchangeType.SZ.value.equals(clientStkacct.getExchangeType())) {
                    dataset.updateString("stock_account", clientStkacct.getStockAccount());
                }
            });
        } else if (Market.SHSE.value.equals(integratedEntrust.getMarket())) {
            clientStkacctList.forEach(clientStkacct -> {
                if (ExchangeType.SH.value.equals(clientStkacct.getExchangeType())) {
                    dataset.updateString("stock_account", clientStkacct.getStockAccount());
                }
            });
        }
        dataset.updateString("cbpconfer_id", integratedEntrust.getContractId());
        dataset.updateDouble("entrust_balance", integratedEntrust.getEntrustBalance().doubleValue());
        dataset.updateString("entrust_bs", integratedEntrust.getEntrustBs());
        dataset.updateDouble("expire_year_rate", UfxConverter.convertExpireYearRate(integratedEntrust.getEntrustPrice()));
        dataset.updateString("orig_report_id", integratedEntrust.getOrigSerialNo());
        dataset.updateString("original_date", integratedEntrust.getFirstSettleDate());
        dataset.updateInt("compact_term", integratedEntrust.getCompactTerm());
        IEvent event = builder.genEvent(functionId, dataset);
        event.markTimestamp(true);
        event.addTimestamp(Long.parseLong(integratedEntrust.getId().substring(1)), requestId.toString());
        unPack(event, functionId);
        client.send(event);//发送包
    }

    //深圳债券质押协议回购到期续做
    private void repurchaseRenewalSZ(Long requestId, IntegratedEntrust integratedEntrust, String password, LoginInfo loginInfo) throws Exception {
        List<ClientStkacct> clientStkacctList = loginInfo.getClientStkacctList();
        if (Objects.isNull(clientStkacctList)) {
            logger.error("repurchase_Renewal_SZ error no clientStkacctList");
            integratedEntrust.setEntrustStatus(EntrustStatus.INVALID.value);
            integratedEntrust.setErrorMsg("帐号" + integratedEntrust.getAccountId() + "证券股东信息获取失败");
            integratedEntrust.setErrorCode(ErrorCode.TERMINAL_API_ERROR.value);
            ufxTradeResponse.resIntegratedEntrust(integratedEntrust);
            return;
        }
        String functionId = "332410";
        IDataset dataset = DatasetService.getDefaultInstance().getDataset();
        dataset.addColumn("op_branch_no");//操作分支机构
        dataset.addColumn("op_entrust_way");//委托方式
        dataset.addColumn("op_station");//站点地址
        dataset.addColumn("branch_no");//分支机构
        dataset.addColumn("client_id");//客户编号
        dataset.addColumn("fund_account");//资产账户
        dataset.addColumn("password");//密码
        dataset.addColumn("stock_account");//证券账号
        dataset.addColumn("cbpconfer_id");//约定号
        dataset.addColumn("entrust_balance");//委托金额
        dataset.addColumn("entrust_bs");//买卖方向
        dataset.addColumn("expire_year_rate");//到期年收益率
        dataset.addColumn("orig_report_id");//原申报合同序号
        dataset.addColumn("back_term");//购回期限
        dataset.addColumn("original_date");//原申请日期
        dataset.appendRow();
        dataset.updateString("op_branch_no", "");
        dataset.updateString("op_entrust_way", builder.getOp_entrust_way());
        dataset.updateString("op_station", op_station);
        dataset.updateInt("branch_no", loginInfo.getBranchNo());
        dataset.updateString("client_id", loginInfo.getClientId());
        dataset.updateString("fund_account", loginInfo.getFundAccount());
        dataset.updateString("password", password);
        if (Market.SZSE.value.equals(integratedEntrust.getMarket())) {
            clientStkacctList.forEach(clientStkacct -> {
                if (ExchangeType.SZ.value.equals(clientStkacct.getExchangeType())) {
                    dataset.updateString("stock_account", clientStkacct.getStockAccount());
                }
            });
        } else if (Market.SHSE.value.equals(integratedEntrust.getMarket())) {
            clientStkacctList.forEach(clientStkacct -> {
                if (ExchangeType.SH.value.equals(clientStkacct.getExchangeType())) {
                    dataset.updateString("stock_account", clientStkacct.getStockAccount());
                }
            });
        }
        dataset.updateString("cbpconfer_id", integratedEntrust.getContractId());
        dataset.updateDouble("entrust_balance", integratedEntrust.getEntrustBalance().doubleValue());
        dataset.updateString("entrust_bs", integratedEntrust.getEntrustBs());
        dataset.updateDouble("expire_year_rate", UfxConverter.convertExpireYearRate(integratedEntrust.getEntrustPrice()));
        dataset.updateString("orig_report_id", integratedEntrust.getOrigSerialNo());
        dataset.updateInt("back_term", integratedEntrust.getCompactTerm());
        dataset.updateString("original_date", integratedEntrust.getFirstSettleDate());
        IEvent event = builder.genEvent(functionId, dataset);
        event.markTimestamp(true);
        event.addTimestamp(Long.parseLong(integratedEntrust.getId().substring(1)), requestId.toString());
        unPack(event, functionId);
        client.send(event);//发送包
    }

    //证券持仓快速查询
    public void queryAccountPosition(Long requestId, PositionQueryBean positionQueryBean, String password, LoginInfo loginInfo) throws Exception {
        if (positionQueryBean.getPageSize() == 0) {//不翻页 组合全部给出
            int pageSize = 1000;
            int size = pageSize;
            positionQueryBean.setPageSize(pageSize);
            List<PositionBean> reList = new ArrayList<PositionBean>();
            while (pageSize == size) {
                IEvent rsp = sendAccountPosition(positionQueryBean, loginInfo, password);
                IDataset ds = rsp.getEventDatas().getDataset(0);
                if (rsp.getReturnCode() != ErrorCode.SUCCESS.value) {
                    logger.warn("queryAccountPosition failed ErrorNo:{}，reason:{}", rsp.getErrorNo(), rsp.getErrorInfo());
                    ufxTradeResponse.resQueryAccountPosition(null, requestId);
                    return;
                }
                List<PositionBean> list = UfxConverter.qryAccountPositionReBack(rsp, positionQueryBean);
                pageSize = list == null ? 0 : list.size();
                logger.debug("pageSize : {}", pageSize);
                reList.addAll(list);
                if (pageSize == size) {
                    positionQueryBean.setPositionStr(list.get(pageSize - 1).getPositionStr());
                }
            }
            logger.debug("queryAccountPosition reback : {}", JSONArray.toJSONString(reList));
            ufxTradeResponse.resQueryAccountPosition(reList, requestId);
        } else {
            IEvent rsp = sendAccountPosition(positionQueryBean, loginInfo, password);
            IDataset ds = rsp.getEventDatas().getDataset(0);
            if (rsp.getReturnCode() != ErrorCode.SUCCESS.value) {
                logger.warn("queryAccountPosition failed ErrorNo:{}，reason:{}", rsp.getErrorNo(), rsp.getErrorInfo());
                ufxTradeResponse.resQueryAccountPosition(null, requestId);
                return;
            }
            List<PositionBean> list = UfxConverter.qryAccountPositionReBack(rsp, positionQueryBean);
            logger.debug("queryAccountPosition reback : {}", JSONArray.toJSONString(list));
            ufxTradeResponse.resQueryAccountPosition(list, requestId);
        }
    }

    private IEvent sendAccountPosition(PositionQueryBean positionQueryBean, LoginInfo loginInfo, String password) throws Exception {
        logger.info("queryAccountPosition data : positionQueryBean:{} ,password:{}, op_station:{}", JSONObject.toJSONString(positionQueryBean), password, op_station);
        String funcid = "333103";
        // 往event中添加dataset
        IDataset dataset = DatasetService.getDefaultInstance().getDataset();
        dataset.addColumn("op_branch_no");//操作分支机构
        dataset.addColumn("op_entrust_way");//委托方式
        dataset.addColumn("op_station");//站点地址
        dataset.addColumn("branch_no");//分支机构
        dataset.addColumn("client_id");//客户编号
        dataset.addColumn("fund_account");//资产账户
        dataset.addColumn("password");//密码
        if (!Strings.isNullOrEmpty(positionQueryBean.getStockCode())) {
            dataset.addColumn("exchange_type");//交易类别
            dataset.addColumn("stock_code");//证券代码
        }
        if (!Strings.isNullOrEmpty(positionQueryBean.getPositionStr())) {
            dataset.addColumn("position_str");//定位串
        }
        dataset.addColumn("request_num");//请求行数
        dataset.appendRow();
        dataset.updateString("op_branch_no", "");
        dataset.updateString("op_entrust_way", builder.getOp_entrust_way());
        dataset.updateString("op_station", op_station);
        dataset.updateInt("branch_no", loginInfo.getBranchNo());
        dataset.updateString("client_id", loginInfo.getClientId());
        dataset.updateString("fund_account", loginInfo.getFundAccount());
        dataset.updateString("password", password);
        if (!Strings.isNullOrEmpty(positionQueryBean.getStockCode())) {
            String[] marketAndStockCode = StockCodeUtils.getMarketAndStockCode(positionQueryBean.getStockCode());
            if (marketAndStockCode[1].equals(Market.SZSE.value)) {
                dataset.updateString("exchange_type", ExchangeType.SZ.value);
            } else if (marketAndStockCode[1].equals(Market.SHSE.value)) {
                dataset.updateString("exchange_type", ExchangeType.SH.value);
            }
            dataset.updateString("stock_code", marketAndStockCode[0]);
        }
        if (!Strings.isNullOrEmpty(positionQueryBean.getPositionStr())) {
            dataset.updateString("position_str", positionQueryBean.getPositionStr());//定位串
        }
        dataset.updateInt("request_num", positionQueryBean.getPageSize());
        IEvent event = builder.genSyncEvent(funcid, dataset);
        unPack(event, funcid);
        IEvent rsp = client.sendReceive(event);//发送包
        logger.info("queryAccountPosition reback :");
        unPack(rsp, funcid);
        return rsp;
    }

    public void setOp_station(String op_station) {
        this.op_station = op_station;
    }

    public void setUfxTradeResponse(TradeResponseImpl ufxTradeResponse) {
        this.ufxTradeResponse = ufxTradeResponse;
    }

    public void setUfxTradeClient(UfxTradeClientImpl ufxTradeClient) {
        this.ufxTradeClient = ufxTradeClient;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }
}
