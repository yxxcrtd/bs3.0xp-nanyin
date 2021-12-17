/*
 Source: ufxTrader.java
 Software Copyright: Hundsun Technologies Inc.
 System: UFX Security Demo (Asynchronous)
 Module: Unpackage output parameter(Report push related)
 Describition:
 This class is receiveing reprot push related package from broker,and unpacking the package, the documents for function description and Java 
 programming helper can be found at ufx website: https://ufx.hscloud.cn/ufx/html/doc/index.html
 Function id include: 620001-12
 author: Kai Zhan
 Date:2016/03/29
 Others:
 This com.quant.UFT.api.com.quant.UFT.api is a sample for using Hundsun UFX System, we are highly not recommand any user to use this com.quant.UFT.api.com.quant.UFT.api at any real market，for more details
 please read <T2SDK JAVA Developer Manual>
 */
package com.joyintech.tams.exchange.terminal.trade.ufx;

import com.google.common.collect.Maps;
import com.hundsun.t2sdk.common.share.dataset.DatasetService;
import com.hundsun.t2sdk.common.share.event.PackService;
import com.hundsun.t2sdk.impl.client.ClientSocket;
import com.hundsun.t2sdk.interfaces.ICallBackMethod;
import com.hundsun.t2sdk.interfaces.IClient;
import com.hundsun.t2sdk.interfaces.T2SDKException;
import com.hundsun.t2sdk.interfaces.share.dataset.IDataset;
import com.hundsun.t2sdk.interfaces.share.dataset.IDatasets;
import com.hundsun.t2sdk.interfaces.share.event.EventTagdef;
import com.hundsun.t2sdk.interfaces.share.event.IEvent;
import com.hundsun.t2sdk.interfaces.share.event.IPack;
import org.slf4j.LoggerFactory;

import java.util.Map;

//接收消息主推
public class UfxMdCallBack implements ICallBackMethod {
    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(UfxMdCallBack.class);

    //TODO::赋值？
    private static IClient client = null;

    //设置节点
    public static void setClient(IClient client) {
        UfxMdCallBack.client = client;
    }

    @Override
    public void execute(IEvent arg0, ClientSocket arg1) {
        // 获取消息功能号
        //相当于IBizMessage的GetFunction()
        long iFunctionID = arg0.getIntegerAttributeValue(EventTagdef.TAG_FUNCTION_ID);//获取功能号
        long iIssueType = arg0.getIntegerAttributeValue(EventTagdef.TAG_ISSUE_TYPE);//获取事件号
        if (iFunctionID == 620000) { // 消息中心心跳
            arg0.changeToresponse(); //将请求事件转换为应答事件
            try {
                //                logger.info("Heart Beat..."); //心跳打印
                UfxMdCallBack.client.send(arg0);
            } catch (T2SDKException e) {
                logger.error("UfxMdCallBack.client.send:{}", e.getMessage());
            }
        } // 收到发布过来的实时行情（行情主推目前恒生环境暂不开放）
        else if (iFunctionID == 620001) {
            logger.info("以下为订阅应答");
            byte[] keyInfo = arg0.getByteArrayAttributeValue(EventTagdef.TAG_KEY_INFO);
            IPack outPack = PackService.getPacker(keyInfo, "GBK");
            StringBuilder sb = new StringBuilder();
            // 获得结果集
            // 开始读取单个结果集的信息
            IDataset ds = outPack.getDataset(0);//获取数据包
            int columnCount = ds.getColumnCount();//获取
            // 遍历单个结果集列信息
            for (int j = 1; j <= columnCount; j++) {
                sb.append(ds.getColumnName(j));//打印数据标题
                sb.append("|");
                //sb.append(ds.getColumnType(j)); //打印数据类型
                //sb.append("\n");
            }
            // 遍历单个结果集记录，遍历前首先将指针置到开始
            ds.beforeFirst();
            while (ds.hasNext()) {
                sb.append("\n");
                ds.next();
                for (int j = 1; j <= columnCount; j++) {
                    sb.append(ds.getString(j));//打印数据集
                    sb.append("|");
                }
            }
            logger.info(sb.toString());
        } else if (iFunctionID == 620003) {
            logger.info("主推-证券委托回报:{}", arg0.getEventDatas().getDataset(0));
            if (iIssueType == 25) {//判断事件类型
                //StringBuilder sb = new StringBuilder();
                //获得结果集
                logger.info("以下是实时行情回报");
                IDatasets result = arg0.getEventDatas();
                //获得结果集总数
                int datasetCount = result.getDatasetCount();
                //遍历所有的结果集
                if (datasetCount > 0) {
                    IDataset ds = result.getDataset(0);
                    logger.info(ds.getString("ZQ")); //打印证券行情
                }
            } else if (iIssueType == 12) {
                // 获取过滤信息
                //相当于IBizMessage的GetKeyInfo
                byte[] keyInfo = arg0.getByteArrayAttributeValue(EventTagdef.TAG_KEY_INFO);
                IPack outPack = PackService.getPacker(keyInfo, "GBK");//获得打包器
                // 获得结果集
                // 开始读取单个结果集的信息
                //                IDataset ds = outPack.getDataset(0);//获取数据包
                //TODO:: 处理成交通知
                //UfxTradeHandler.onTradeReturn(arg0.getEventDatas().getDataset(0), arg0.getReturnCode(), arg0.getErrorInfo());
                DatasetService.printDataset(arg0.getEventDatas().getDataset(0));

            } else if (iIssueType == 23) {
                byte[] keyInfo = arg0.getByteArrayAttributeValue(EventTagdef.TAG_KEY_INFO);
                IPack outPack = PackService.getPacker(keyInfo, "GBK");//获得打包器
                // 获得结果集
                // 开始读取单个结果集的信息
                //                IDataset ds = outPack.getDataset(0);//获取数据包

                //TODO:: 处理委托通知
                //UfxTradeHandler.onOrderReturn(arg0.getEventDatas().getDataset(0), arg0.getReturnCode(), arg0.getErrorInfo());
                DatasetService.printDataset(arg0.getEventDatas().getDataset(0));
            } else {
                logger.info("以下是主推应答： ");
                // 获取过滤信息
                //相当于IBizMessage的GetKeyInfo
                byte[] keyInfo = arg0.getByteArrayAttributeValue(EventTagdef.TAG_KEY_INFO);
                IPack outPack = PackService.getPacker(keyInfo, "utf-8");
                StringBuilder sb = new StringBuilder();
                // 获得结果集
                // 开始读取单个结果集的信息
                IDataset ds = outPack.getDataset(0);//获取数据包
                int columnCount = ds.getColumnCount();//获取数据个数
                // 遍历单个结果集列信息
                for (int j = 1; j <= columnCount; j++) {
                    sb.append(ds.getColumnName(j));//打印数据标题
                    sb.append("|");
                    //sb.append(ds.getColumnType(j)); //打印数据类型
                    //sb.append("\n");
                }
                // 遍历单个结果集记录，遍历前首先将指针置到开始
                ds.beforeFirst();
                while (ds.hasNext()) {
                    sb.append("\n");
                    ds.next();
                    for (int j = 1; j <= columnCount; j++) {
                        sb.append(ds.getString(j));//打印数据结果集
                        sb.append("|");
                    }
                }
                logger.info(sb.toString());
                // 获取主推包
                //相当于IBizMessage的GetContent
                if (arg0.getEventDatas().getDatasetCount() > 0) {
                    DatasetService.printDataset(arg0.getEventDatas().getDataset(0));
                }
            }
        }
    }
}
