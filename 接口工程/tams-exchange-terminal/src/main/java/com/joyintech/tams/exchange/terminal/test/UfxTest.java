package com.joyintech.tams.exchange.terminal.test;

import com.alibaba.fastjson.JSONArray;
import com.hundsun.libsecurity.CryptUtil;
import com.hundsun.mcapi.MCServers;
import com.hundsun.t2sdk.common.core.context.ContextUtil;
import com.hundsun.t2sdk.common.share.dataset.DatasetService;
import com.hundsun.t2sdk.impl.client.T2Services;
import com.hundsun.t2sdk.interfaces.IClient;
import com.hundsun.t2sdk.interfaces.T2SDKException;
import com.hundsun.t2sdk.interfaces.share.dataset.IDataset;
import com.hundsun.t2sdk.interfaces.share.dataset.IDatasets;
import com.hundsun.t2sdk.interfaces.share.event.EventTagdef;
import com.hundsun.t2sdk.interfaces.share.event.EventType;
import com.hundsun.t2sdk.interfaces.share.event.IEvent;
import com.joyintech.tams.exchange.common.bean.PositionQueryBean;
import com.joyintech.tams.exchange.common.constant.ErrorCode;
import com.joyintech.tams.exchange.common.constant.ExchangeType;
import com.joyintech.tams.exchange.common.constant.Market;
import com.joyintech.tams.exchange.common.db.domain.ClientStkacct;
import com.joyintech.tams.exchange.common.db.domain.LoginInfo;
import com.joyintech.tams.exchange.common.db.domain.PositionBean;
import com.joyintech.tams.exchange.common.utils.JsonUtils;
import com.joyintech.tams.exchange.terminal.trade.ufx.UfxConverter;
import com.joyintech.tams.exchange.terminal.trade.ufx.UfxTradeCallBack;
import com.joyintech.tams.exchange.terminal.trade.ufx.log.util.UfxLogPrintUtils;
import com.joyintech.tams.exchange.terminal.utils.AddressUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * create By MAXX on 2020/6/8
 */
public class UfxTest {
    private static final Logger logger = LoggerFactory.getLogger(UfxTest.class);
    private static String configPath = "tams-exchange-terminal/ufxconfig/t2sdk_hundsun-config.xml";
    static String fundAccount = "65001608";
//    static String fundAccount = "65001568";
static String password = "111111";
    static String op_entrust_way = "3";
    static IClient client = null;
    static LoginInfo loginInfo = null;

    public static String getOpStation() {
        String op_station = "";
        try {
            String extendInfo = "O32Client;V2.0;ZYYH;O32Server1.0;" + AddressUtils.getLocalIp() + ";121.8.190.120";
            op_station = AddressUtils.genOpStation(extendInfo);
        } catch (Exception e) {
            logger.error("getOpStation:{}", e.getMessage());
        }
        return op_station;
    }

    private static void unPack(IEvent event, String functionId) {
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
     * @Description: 登录
     * @Param: [account, password, op_station, client]
     * @return: void
     * @Author: MAXX
     * @Date: 2020/6/8
     */
    public static void login(String account, String password, String op_station) throws Exception {
        logger.info("login {} - {} -- {}", account, password, op_station);
        // 获取event
        IEvent event = ContextUtil.getServiceContext().getEventFactory()
                .getEventByAlias("331100", EventType.ET_REQUEST);
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
        dataset.updateString("op_entrust_way", op_entrust_way);
        dataset.updateString("op_station", op_station);
        byte[] out = new byte[7];
        CryptUtil.HsPwdEncode(password.getBytes(), out);
        dataset.updateByteArray("password", out);
        //dataset.updateString("password", password); //普通密码打包
        dataset.updateString("password_type", "2");
        dataset.updateString("input_content", "1");
        dataset.updateString("account_content", account);
        event.setServiceId("331100"); //打入功能号
        event.putEventData(dataset); //入参打包
        event.setIntegerAttributeValue(EventTagdef.TAG_SENDERID, 6); //分配节点为6
        unPack(event, event.getServiceId());
        client.send(event); //发送包
    }

    /***
     * @Description: 证券股东信息查询
     * @Param: [op_station, password, branch_no, client_id, user_token]
     * @return: java.util.List<com.joyintech.tams.exchange.common.db.domain.ClientStkacct>
     * @Author: MAXX
     * @Date: 2020/6/8
     */
    public static List<ClientStkacct> ReqClientStkacct(String op_station, String password, String fundAccount, Integer branch_no, String client_id, String user_token) throws Exception {
        logger.info("ReqClientStkacct data :password:{}, fundAccount{}, branch_no:{} ,client_id:{} ,user_token:{},op_station:{}", password, fundAccount, branch_no, client_id, user_token, op_station);
        // 获取event
//        IEvent event = ContextUtil.getServiceContext().getEventFactory()
//                                  .getEventByAlias("331300", EventType.ET_REQUEST);
        // 往event中添加dataset
        IDataset dataset = DatasetService.getDefaultInstance().getDataset();
        //开始打包
        dataset.addColumn("op_branch_no");//操作分支机构
        dataset.addColumn("op_entrust_way");//委托方式
        dataset.addColumn("op_station");//站点地址
        dataset.addColumn("branch_no");//分支机构
        dataset.addColumn("client_id");//客户编号
        dataset.addColumn("fund_account");//客户编号
        dataset.addColumn("password", IDataset.DS_BYTE_ARRAY);//密码
        dataset.addColumn("password_type");//密码类别
        dataset.addColumn("user_token");//用户口令
        dataset.appendRow();
        dataset.updateString("op_branch_no", "");//操作分支机构打入值1
        dataset.updateString("op_entrust_way", op_entrust_way);
        dataset.updateString("op_station", op_station);
        dataset.updateString("branch_no", branch_no.toString());
        dataset.updateString("client_id", client_id);
        dataset.updateString("fund_account", fundAccount);
        byte[] out = new byte[7];
        CryptUtil.HsPwdEncode(password.getBytes(), out);
        dataset.updateByteArray("password", out);
        //dataset.updateString("password", password); //普通密码打包
        dataset.updateString("password_type", "2");
        dataset.updateString("user_token", user_token);
//        event.setServiceId("331300"); //打入功能号
//        event.putEventData(dataset); //入参打包
//        IEvent event = builder.genSyncEvent("331300", dataset);
        IEvent event = ContextUtil.getServiceContext().getEventFactory()
                .getEventByAlias("331300", EventType.ET_REQUEST);
        event.setServiceId("331300"); //打入功能号
        event.putEventData(dataset); //入参打包
        event.setIntegerAttributeValue(EventTagdef.TAG_SUB_SYSTEM_NO, 1000);
        event.setIntegerAttributeValue("71", 91000);
        logger.info("ReqClientStkacct {}", JsonUtils.getJsonFromObject(event));
        unPack(event, event.getServiceId());
        IEvent rsp = client.sendReceive(event);//发送包
        if (rsp.getReturnCode() != ErrorCode.SUCCESS.value) {
            logger.warn("ReqClientStkacct failed ErrorNo:{}，reason:{}", rsp.getErrorNo(), rsp.getErrorInfo());
            return null;
        }
        List<ClientStkacct> clientStkacctList = UfxConverter.reqClientStkacctReBack(rsp);
        logger.info("ReqClientStkacct reback : {}", JSONArray.toJSONString(clientStkacctList));
        return clientStkacctList;
    }

    public static void qryStkcode(String op_station) throws Exception {
        logger.info("qryStkcode begin");
        // 获取event
        IEvent event = ContextUtil.getServiceContext().getEventFactory()
                .getEventByAlias("330300", EventType.ET_REQUEST);
        // 往event中添加dataset
        IDataset dataset = DatasetService.getDefaultInstance().getDataset();
        //开始打包
        dataset.addColumn("op_branch_no");//操作分支机构
        dataset.addColumn("op_entrust_way");//委托方式
        dataset.addColumn("op_station");//站点地址
        //dataset.addColumn("query_type");//查询类别
        //dataset.addColumn("stock_type");//证券类别
        dataset.addColumn("stock_code");
        dataset.appendRow();
        dataset.updateString("op_branch_no", "");//操作分支机构打入值1
        dataset.updateString("op_entrust_way", op_entrust_way);
        dataset.updateString("op_station", op_station);
        //dataset.updateString("query_type", "1");
        //dataset.updateString("stock_type", "U");
        dataset.updateString("stock_code", "162997");
        event.setServiceId("330300"); //打入功能号
        event.putEventData(dataset); //入参打包
        logger.info("qryStkcode {}", event);
        IEvent rsp = client.sendReceive(event);//发送包
        if (rsp.getReturnCode() != ErrorCode.SUCCESS.value) {
            logger.warn("qryStkcode failed ErrorNo:{}，reason:{}", rsp.getErrorNo(), rsp.getErrorInfo());
            return;
        }
        Map<String, String> tradePlatList = reqStkcodeReBack(rsp);
        logger.info("qryStkcode reback : {}", JSONArray.toJSONString(tradePlatList));
        return;
    }

    //证券持仓查询
    public static void qryPosition(String op_station) throws Exception {
        // 往event中添加dataset
        IDataset dataset = DatasetService.getDefaultInstance().getDataset();
        dataset.addColumn("op_branch_no");//操作分支机构
        dataset.addColumn("op_entrust_way");//委托方式
        dataset.addColumn("op_station");//站点地址
        dataset.addColumn("branch_no");//分支机构
        dataset.addColumn("client_id");//客户编号
        dataset.addColumn("fund_account");//资产账户
        dataset.addColumn("password");//密码
//        if (!Strings.isNullOrEmpty(positionQueryBean.getStockCode())) {
//            dataset.addColumn("exchange_type");//交易类别
//            dataset.addColumn("stock_code");//证券代码
//        }
//        if (!Strings.isNullOrEmpty(positionQueryBean.getPositionStr())) {
//            dataset.addColumn("position_str");//定位串
//        }
        dataset.addColumn("request_num");//请求行数
        dataset.appendRow();
        dataset.updateString("op_branch_no", "");
        dataset.updateString("op_entrust_way", op_entrust_way);
        dataset.updateString("op_station", op_station);
        dataset.updateInt("branch_no", loginInfo.getBranchNo());
        dataset.updateString("client_id", loginInfo.getClientId());
        dataset.updateString("fund_account", loginInfo.getFundAccount());
        dataset.updateString("password", password);
//        if (!Strings.isNullOrEmpty(positionQueryBean.getStockCode())) {
//            String[] marketAndStockCode = StockCodeUtils.getMarketAndStockCode(positionQueryBean.getStockCode());
//            if (marketAndStockCode[1].equals(Market.SZSE.value)) {
//                dataset.updateString("exchange_type", ExchangeType.SZ.value);
//            } else if (marketAndStockCode[1].equals(Market.SHSE.value)) {
//                dataset.updateString("exchange_type", ExchangeType.SH.value);
//            }
//            dataset.updateString("stock_code", marketAndStockCode[0]);
//        }
//        if (!Strings.isNullOrEmpty(positionQueryBean.getPositionStr())) {
//            dataset.updateString("position_str", positionQueryBean.getPositionStr());//定位串
//        }
        dataset.updateInt("request_num", 0);
        IEvent event = genSyncEvent("333103", dataset);
        unPack(event, "333103");
        IEvent rsp = client.sendReceive(event);//发送包
        logger.info("ReqQueryPosition reback {}:", JsonUtils.getJsonFromObject(rsp));
        if (rsp.getReturnCode() != ErrorCode.SUCCESS.value) {
            logger.warn("ReqQueryPosition failed ErrorNo:{}，reason:{}", rsp.getErrorNo(), rsp.getErrorInfo());
            return;
        } else {
            List<PositionBean> positionBeanList = UfxConverter.qryAccountPositionReBack(rsp, new PositionQueryBean());
            logger.info("positionBeanList:{}", JsonUtils.getJsonFromObject(positionBeanList));
        }
    }

    public static IEvent genSyncEvent(String funcCode, IDataset dataset) {
        IEvent event = ContextUtil.getServiceContext().getEventFactory()
                .getEventByAlias(funcCode, EventType.ET_REQUEST);
        event.setServiceId(funcCode); //打入功能号
        event.putEventData(dataset); //入参打包
        return event;
    }


    public static void qryInbond(String op_station, Integer branch_no, String fundAccount) throws Exception {
        logger.info("qryInbond begin");
        // 获取event
        IEvent event = ContextUtil.getServiceContext().getEventFactory()
                .getEventByAlias("333115", EventType.ET_REQUEST);
        // 往event中添加dataset
        IDataset dataset = DatasetService.getDefaultInstance().getDataset();
        //开始打包
        dataset.addColumn("op_branch_no");//操作分支机构
        dataset.addColumn("op_entrust_way");//委托方式
        dataset.addColumn("op_station");//站点地址
        dataset.addColumn("branch_no");//分支机构
        dataset.addColumn("fund_account");//资产账户
        dataset.addColumn("password");//密码
        //dataset.addColumn("query_type");//查询类别
        //dataset.addColumn("stock_type");//证券类别
        //dataset.addColumn("stock_code");
        dataset.appendRow();
        dataset.updateString("op_branch_no", "");//操作分支机构打入值1
        dataset.updateString("op_entrust_way", op_entrust_way);
        dataset.updateString("op_station", op_station);
        dataset.updateString("branch_no", branch_no.toString());
        dataset.updateString("fund_account", fundAccount);
        dataset.updateString("password", password);
        //dataset.updateString("query_type", "1");
        //dataset.updateString("stock_type", "U");
        //dataset.updateString("stock_code", "162997");
        event.setServiceId("333115"); //打入功能号
        event.putEventData(dataset); //入参打包
        logger.info("qryInbond {}", event);
        IEvent rsp = client.sendReceive(event);//发送包
        if (rsp.getReturnCode() != ErrorCode.SUCCESS.value) {
            logger.warn("qryInbond failed ErrorNo:{}，reason:{}", rsp.getErrorNo(), rsp.getErrorInfo());
            return;
        }
        unPack(rsp, event.getServiceId());
        return;
    }

    /*public static void reBackPrint(IEvent rsp) {
        IDatasets result = rsp.getEventDatas();
        IDataset ds = result.getDataset(0);
        ds.beforeFirst();
        while (ds.hasNext()) {
            ds.next();
            String exchange_type = "";
            if (ExchangeType.SZ.value.equals(ds.getString("exchange_type"))) {
                exchange_type = Market.SZSE.value;
            } else if (ExchangeType.SH.value.equals(ds.getString("exchange_type"))) {
                exchange_type = Market.SHSE.value;
            }
            logger.info("stock_code:{} exchange_type:{} store_amount:{} pre_out_amount:{}",
                    ds.getString("stock_code"), exchange_type, ds.getString("store_amount"),
                    ds.getString("pre_out_amount"));
        }
    }*/

    public static Map<String, String> reqStkcodeReBack(IEvent rsp) {
        IDatasets result = rsp.getEventDatas();
        IDataset ds = result.getDataset(0);
        Map<String, String> map = new HashMap<String, String>();
        ds.beforeFirst();
        while (ds.hasNext()) {
            ds.next();
            String exchange_type = "";
            if (ExchangeType.SZ.value.equals(ds.getString("exchange_type"))) {
                exchange_type = Market.SZSE.value;
            } else if (ExchangeType.SH.value.equals(ds.getString("exchange_type"))) {
                exchange_type = Market.SHSE.value;
            }
            map.put(ds.getString("stock_code") + "." + exchange_type, ds.getString("trade_plat"));
        }
        return map;
    }

    public static void qryTrade(String op_station) throws T2SDKException {
        // 往event中添加dataset
        IDataset dataset = DatasetService.getDefaultInstance().getDataset();
//        dataset.addColumn("user_token");//用户口令
        dataset.addColumn("op_branch_no");//操作分支机构
        dataset.addColumn("op_entrust_way");//委托方式
        dataset.addColumn("op_station");//站点地址
        dataset.addColumn("branch_no");//分支机构
        dataset.addColumn("client_id");//客户编号
        dataset.addColumn("fund_account");//资产账户
        dataset.addColumn("password");//密码
        dataset.addColumn("password_type");//密码类别
        dataset.addColumn("position_str");//定位串
        dataset.addColumn("query_direction");//查询方向
        dataset.addColumn("request_num");//请求行数
        dataset.addColumn("query_type");//查询类别
        dataset.addColumn("query_mode");//查询模式
        dataset.addColumn("report_no");//查询模式
        dataset.appendRow();
//        dataset.updateString("user_token", loginInfo.getUserToken());
        dataset.updateString("op_branch_no", "");
        dataset.updateString("op_entrust_way", op_entrust_way);
        dataset.updateString("op_station", op_station);
        dataset.updateInt("branch_no", loginInfo.getBranchNo());
        dataset.updateString("client_id", loginInfo.getClientId());
        dataset.updateString("fund_account", loginInfo.getFundAccount());
        byte[] out = new byte[7];
//        CryptUtil.HsPwdEncode(password.getBytes(), out);
//        dataset.updateByteArray("password", out);
        dataset.updateString("password", password);
        dataset.updateString("position_str", " ");
        dataset.updateString("password_type", "2");

        dataset.updateString("query_direction", "0");
        dataset.updateString("request_num", "1000");
        dataset.updateString("query_type", "2");
        dataset.updateString("query_mode", "0");
        dataset.updateString("report_no", "3");
        IEvent event = genSyncEvent("333102", dataset);
        IEvent rsp = client.sendReceive(event);//发送包
        unPack(event, event.getServiceId());
        if (rsp.getReturnCode() != ErrorCode.SUCCESS.value) {
            logger.warn("qryTrade failed ErrorNo:{}，reason:{}", rsp.getErrorNo(), rsp.getErrorInfo());
        }
        logger.info("qryTrade result");
        UfxConverter.parseQueryTradeMsg(rsp, fundAccount);
        logger.info("{}", UfxLogPrintUtils.logDatasetInfo(rsp.getEventDatas().getDataset(0)));
    }

    public static void qryOrder(String op_station) throws Exception {
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
        dataset.addColumn("position_str");//定位串
        dataset.addColumn("request_num");//请求行数
        dataset.addColumn("query_type");//查询类别
        dataset.appendRow();
        dataset.updateString("op_branch_no", "");
        dataset.updateString("op_entrust_way", op_entrust_way); //op_entrust_way 打入7 网上交易
        dataset.updateString("op_station", op_station);
        dataset.updateInt("branch_no", loginInfo.getBranchNo());
        dataset.updateString("client_id", loginInfo.getClientId());
        dataset.updateString("fund_account", loginInfo.getFundAccount());
        byte[] out = new byte[7];
        CryptUtil.HsPwdEncode(password.getBytes(), out);
        dataset.updateByteArray("password", out);
        dataset.updateString("user_token", loginInfo.getUserToken());
        dataset.updateString("position_str", " ");
        dataset.updateString("request_num", "1000");
        dataset.updateString("query_type", "0");
        IEvent event = genSyncEvent("333101", dataset);
        IEvent rsp = client.sendReceive(event);//发送包
        unPack(event, event.getServiceId()); //入参解包，同时打印发送包参数信息
        if (rsp.getReturnCode() != ErrorCode.SUCCESS.value) {
            logger.warn("qryOrder failed ErrorNo:{}，reason:{}", rsp.getErrorNo(), rsp.getErrorInfo());
        }
        logger.info("qryOrder result");

//        logger.info("{}", UfxLogPrintUtils.logDatasetInfo(rsp.getEventDatas().getDataset(0)));
        logger.info("{}", UfxLogPrintUtils.logDatasetInfo(rsp.getEventDatas().getDataset(0)));
//        queryOrderReBack(rsp);
    }

    public static void queryOrderReBack(IEvent rsp) {
        IDatasets result = rsp.getEventDatas();
        IDataset ds = result.getDataset(0);
        ds.beforeFirst();
        while (ds.hasNext()) {
            ds.next();
            String s = "{";
            s = s + "position_str" + ":" + ds.getString("position_str", null) + ",";
            s = s + "init_date" + ":" + ds.getString("init_date") + ",";
            s = s + "entrust_no" + ":" + ds.getString("entrust_no") + ",";
            s = s + "entrust_date" + ":" + ds.getString("entrust_date") + ",";
            s = s + "entrust_time" + ":" + ds.getString("entrust_time", null) + ",";
            s = s + "fund_account" + ":" + ds.getString("fund_account") + ",";
            s = s + "exchange_type" + ":" + ds.getString("exchange_type") + ",";
            s = s + "stock_code" + ":" + ds.getString("stock_code") + ",";
            s = s + "stock_name" + ":" + ds.getString("stock_name") + ",";
            s = s + "entrust_bs" + ":" + ds.getString("entrust_bs") + ",";
            s = s + "entrust_prop" + ":" + ds.getString("entrust_prop") + ",";
            s = s + "entrust_price" + ":" + ds.getDouble("entrust_price") + ",";
            s = s + "entrust_amount" + ":" + ds.getString("entrust_amount") + ",";
            s = s + "business_amount" + ":" + ds.getString("business_amount") + ",";
            s = s + "business_price" + ":" + ds.getDouble("business_price") + ",";
            s = s + "business_price" + ":" + ds.getDouble("business_price") + ",";
            s = s + "report_time" + ":" + ds.getString("report_time") + ",";
            s = s + "entrust_status" + ":" + ds.getString("entrust_status") + ",";
            s = s + "cancel_info" + ":" + ds.getString("cancel_info") + ",";
            s = s + "cancel_info" + ":" + ds.getString("cancel_info") + ",";
            s = s + "entrust_reference" + ":" + ds.getString("entrust_reference") + ",";
            s = s + "report_no" + ":" + ds.getString("report_no") + "}";

            logger.info("result  : {}", s);
        }
    }

    public static void qrySnapShot(String op_station) throws Exception {
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
        dataset.addColumn("exchange_type");//定位串
        dataset.addColumn("stock_code");//请求行数
        dataset.appendRow();
        dataset.updateString("op_branch_no", "");
        dataset.updateString("op_entrust_way", op_entrust_way); //op_entrust_way 打入7 网上交易
        dataset.updateString("op_station", op_station);
        dataset.updateInt("branch_no", loginInfo.getBranchNo());
        dataset.updateString("client_id", loginInfo.getClientId());
        dataset.updateString("fund_account", loginInfo.getFundAccount());
        byte[] out = new byte[7];
        CryptUtil.HsPwdEncode(password.getBytes(), out);
        dataset.updateByteArray("password", out);
        dataset.updateString("user_token", loginInfo.getUserToken());
        dataset.updateString("exchange_type", " ");
        dataset.updateString("stock_code", "000001");
        IEvent event = genSyncEvent("400", dataset);
        IEvent rsp = client.sendReceive(event);//发送包
        unPack(event, event.getServiceId()); //入参解包，同时打印发送包参数信息
        if (rsp.getReturnCode() != ErrorCode.SUCCESS.value) {
            logger.warn("qryOrder failed ErrorNo:{}，reason:{}", rsp.getErrorNo(), rsp.getErrorInfo());
        }
        logger.info("qryOrder result");
        logger.info("qryTrade callback {} ,ErrorInfo:{}", UfxTradeCallBack.getResult(rsp).toString(), rsp.getErrorInfo());
//        queryOrderReBack(rsp);
    }

    private static void qryBulkQuote(String op_station) throws Exception {
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
        dataset.updateString("op_entrust_way", op_entrust_way);
        dataset.updateString("op_station", op_station);
        dataset.updateString("exchange_type", ExchangeType.SZ.value);
        dataset.updateString("stock_code", "128136");

        dataset.updateString("cbptrans_type", "");
        dataset.updateString("cbphq_type", "00");
        dataset.updateInt("request_num", 1000);
        dataset.updateString("position_str", "");
        IEvent event = genSyncEvent("398", dataset);
        unPack(event, "398");
        IEvent rsp = client.sendReceive(event);//发送包
        logger.info("qryBulkQuote result");
        logger.info("{}", UfxLogPrintUtils.logDatasetInfo(rsp.getEventDatas().getDataset(0)));

    }

    private static void qryBulkOrder(String op_station) throws Exception {
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
        dataset.updateString("op_entrust_way", op_entrust_way); //op_entrust_way 打入7 网上交易
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
        dataset.updateString("position_str", "");
        dataset.updateString("request_num", "1000");
        String funcCode = "332704";
        IEvent event = genSyncEvent(funcCode, dataset);
        IEvent rsp = client.sendReceive(event);//发送包
        unPack(event, funcCode);
        logger.info("qryBulkOrder result");
        logger.info("{}", UfxLogPrintUtils.logDatasetInfo(rsp.getEventDatas().getDataset(0)));

    }

    private static void qryIncomeQuote(String op_station) throws Exception {
        // 往event中添加dataset
        IDataset dataset = DatasetService.getDefaultInstance().getDataset();
        dataset.addColumn("op_branch_no");//操作分支机构
        dataset.addColumn("op_entrust_way");//委托方式
        dataset.addColumn("op_station");//站点地址
        dataset.addColumn("exchange_type");//交易类别
        dataset.addColumn("stock_code");//证券代码
        dataset.appendRow();
        dataset.updateString("op_branch_no", "");
        dataset.updateString("op_entrust_way", op_entrust_way);
        dataset.updateString("op_station", op_station);
        dataset.updateString("exchange_type", "1");
        dataset.updateString("stock_code","152068");
        String funcCode = "361";
        IEvent event = genSyncEvent(funcCode, dataset);
        IEvent rsp = client.sendReceive(event);//发送包
        unPack(event, funcCode);
        logger.info("qryIncomeQuote result");
        logger.info("{}", UfxLogPrintUtils.logDatasetInfo(rsp.getEventDatas().getDataset(0)));

    }


    public static void main(String[] args) {
        T2Services server = T2Services.getInstance();//T2实例化
        server.setT2sdkConfigString(configPath);// 配置文件路径
        try {
            server.init();
            server.start();
            logger.info("T2Services init start success");
            logger.info("ufx_test");
            client = server.getClient("ar_tree"); //走节点ar_tree
            System.out.println("client success");
            String op_station = getOpStation();
            System.out.println(op_station);
            login(fundAccount, password, op_station);//登录
            Thread.sleep(2000L);
            if (Objects.nonNull(loginInfo)) {
                MCServers.MCInit();

                qryPosition(op_station);
//                qryStkcode(op_station);
//                qryInbond(op_station, 308, fundAccount);
                qryOrder(op_station);
                qryTrade(op_station);
                qryBulkQuote(op_station);
//                qrySnapShot(op_station);
                qryBulkOrder(op_station);
                qryIncomeQuote(op_station);
            }
//            qryStkcode(op_station);
//            qryInbond(op_station, 308, fundAccount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
