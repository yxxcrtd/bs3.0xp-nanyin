package com.joyintech.tams.exchange.terminal.test;

import com.alibaba.fastjson.JSON;
import com.hundsun.t2sdk.impl.client.ClientSocket;
import com.hundsun.t2sdk.interfaces.ICallBackMethod;
import com.hundsun.t2sdk.interfaces.share.dataset.IDataset;
import com.hundsun.t2sdk.interfaces.share.dataset.IDatasets;
import com.hundsun.t2sdk.interfaces.share.event.EventTagdef;
import com.hundsun.t2sdk.interfaces.share.event.IEvent;
import com.joyintech.tams.exchange.common.constant.ErrorCode;
import com.joyintech.tams.exchange.common.db.domain.ClientStkacct;
import com.joyintech.tams.exchange.common.db.domain.LoginInfo;
import com.joyintech.tams.exchange.terminal.trade.ufx.UfxConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * create By MAXX on 2020/6/8
 */
public class UfxRebackTest implements ICallBackMethod {

    private final static Logger logger = LoggerFactory.getLogger(UfxRebackTest.class);

    public static StringBuilder getResult(IEvent arg0) {
        StringBuilder sb = new StringBuilder();
        IDatasets results = arg0.getEventDatas();
        int datasetCount_ = results.getDatasetCount();
        for (int i = 0; i < datasetCount_; i++) {
            IDataset ds = results.getDataset(i);
            int columnCount = ds.getColumnCount();
            for (int j = 1; j <= columnCount; j++) {
                sb.append(ds.getColumnName(j));
                sb.append("|");
            }
            ds.beforeFirst();
            while (ds.hasNext()) {
                sb.append("\n");
                ds.next();
                for (int j = 1; j <= columnCount; j++) {
                    sb.append(ds.getString(j)).append("|");
                }
            }
        }
        return sb;
    }

    @Override
    public void execute(IEvent iEvent, ClientSocket clientSocket) {
        //获取功能号
        long iFunctionID = iEvent.getIntegerAttributeValue(EventTagdef.TAG_FUNCTION_ID);
        logger.info("UfxRebackTest callback functionCode : {} - {} ErrorInfo:{}", iFunctionID, getResult(iEvent).toString(), iEvent.getErrorInfo());
        IDatasets result = iEvent.getEventDatas();
        if (iFunctionID == 331100) { //331100 登录回调解包
            resLogin(result, iEvent.getReturnCode(), iEvent.getErrorInfo());
        } else {
            logger.info("unknown functionCode : {} - {}", iFunctionID, getResult(iEvent).toString());
        }
    }

    /*** 
     * @Description: 登录返回
     * @Param: [result, errorCode, errorInfo] 
     * @return: void
     * @Author: MAXX
     * @Date: 2020/6/8 
     */
    public void resLogin(IDatasets result, int errorCode, String errorInfo) {
        logger.info("onLogin result: {} - errorCode: {}", result, errorCode);
        IDataset ds = result.getDataset(0);
        String fundAccount = ds.getString("fund_account");
        //Account account = UfxTradeClient.getAccountByFinanceAccount(fundAccount);
        if (errorCode != ErrorCode.SUCCESS.value) {
            logger.warn("account:{} login failed reason:{}", fundAccount, errorInfo);
            return;
        }
        LoginInfo loginInfo = UfxConverter.parseLoginMsg(ds, fundAccount);
        UfxTest.loginInfo = loginInfo;
        try {
            List<ClientStkacct> clientStkacctList = UfxTest.ReqClientStkacct(UfxTest.getOpStation(), UfxTest.password, loginInfo.getBranchNo(), loginInfo.getClientId(), loginInfo.getUserToken());
            if (clientStkacctList != null) {
                logger.info("clientStkacctList:{}", JSON.toJSONString(clientStkacctList).toString());
                loginInfo.setClientStkacctList(clientStkacctList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("ReqClientStkacct failed. reason:{}", e.getMessage());
        }
    }
}
