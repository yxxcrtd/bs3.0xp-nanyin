package com.joyintech.tams.exchange.terminal.test;

import com.alibaba.fastjson.JSONArray;
import com.hundsun.libsecurity.CryptUtil;
import com.hundsun.t2sdk.common.core.context.ContextUtil;
import com.hundsun.t2sdk.common.share.dataset.DatasetService;
import com.hundsun.t2sdk.impl.client.T2Services;
import com.hundsun.t2sdk.interfaces.IClient;
import com.hundsun.t2sdk.interfaces.share.dataset.IDataset;
import com.hundsun.t2sdk.interfaces.share.dataset.IDatasets;
import com.hundsun.t2sdk.interfaces.share.event.EventTagdef;
import com.hundsun.t2sdk.interfaces.share.event.EventType;
import com.hundsun.t2sdk.interfaces.share.event.IEvent;
import com.joyintech.tams.exchange.common.constant.ErrorCode;
import com.joyintech.tams.exchange.common.constant.ExchangeType;
import com.joyintech.tams.exchange.common.constant.Market;
import com.joyintech.tams.exchange.common.db.domain.ClientStkacct;
import com.joyintech.tams.exchange.common.db.domain.LoginInfo;
import com.joyintech.tams.exchange.terminal.trade.ufx.UfxConverter;
import com.joyintech.tams.exchange.terminal.trade.ufx.UfxMdCallBack;
import com.joyintech.tams.exchange.terminal.utils.AddressUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create By MAXX on 2020/6/8
 */
public class UfxTest {
    private static final Logger logger = LoggerFactory.getLogger(UfxTest.class);
    private static String configPath = "tams-exchange-terminal/ufxconfig/t2sdk_hundsun-config.xml";
    static String fundAccount = "21505632";
    static String password = "123123";
    static IClient client = null;
    static LoginInfo loginInfo = null;

    static String getOpStation() {
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
    private static void login(String account, String password, String op_station) throws Exception {
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
        dataset.updateString("op_entrust_way", "D");
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
    public static List<ClientStkacct> ReqClientStkacct(String op_station, String password, Integer branch_no, String client_id, String user_token) throws Exception {
        logger.info("ReqClientStkacct data :password:{}, branch_no:{} ,client_id:{} ,user_token:{},op_station:{}", password, branch_no, client_id, user_token, op_station);
        // 获取event
        IEvent event = ContextUtil.getServiceContext().getEventFactory()
                                  .getEventByAlias("331300", EventType.ET_REQUEST);
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
        dataset.updateString("op_entrust_way", "D");
        dataset.updateString("op_station", op_station);
        dataset.updateString("branch_no", branch_no.toString());
        dataset.updateString("client_id", client_id);
        byte[] out = new byte[7];
        CryptUtil.HsPwdEncode(password.getBytes(), out);
        dataset.updateByteArray("password", out);
        //dataset.updateString("password", password); //普通密码打包
        dataset.updateString("password_type", "2");
        dataset.updateString("user_token", user_token);
        event.setServiceId("331300"); //打入功能号
        event.putEventData(dataset); //入参打包
        logger.info("ReqClientStkacct {}", event);
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
        dataset.updateString("op_entrust_way", "D");
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
        dataset.updateString("op_entrust_way", "D");
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

    public static void main(String[] args) {
        T2Services server = T2Services.getInstance();//T2实例化
        server.setT2sdkConfigString(configPath);// 配置文件路径
        try {
            server.init();
            server.start();
            logger.info("T2Services init start success");
            logger.info("ufx_test");
            client = server.getClient("ufx_test"); //走节点ar_tree
            System.out.println("client success");
            UfxMdCallBack.setClient(client);
            String op_station = getOpStation();
            System.out.println(op_station);
            login(fundAccount, password, op_station);//登录
            //qryStkcode(op_station);
            qryInbond(op_station, 308, fundAccount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
