package com.joyintech.tams.exchange.terminal.trade.kdgateway.test;

import com.alibaba.fastjson.util.TypeUtils;
import com.pingan.stock.client.TradeClientResponse;
import com.pingan.stock.core.TradeTransServiceImpl;
import com.pingan.stock.service.config.base.TradeSysHead;

import java.util.List;
import java.util.Map;

public class KDGatewayTest {

    public static void main(String[] args) {
        KDGatewayTest.Dictionaries();
        return;
        /*TypeUtils.compatibleWithFieldName = true;
        TradeTransServiceImpl Trade = new TradeTransServiceImpl();
        TradeSysHead head = new TradeSysHead();
        String str = "{\"USER_ID_CLS\":\"U\",\"USER_ID\":\"150348196\",\"USER_PWD\":\"123123\",\"PROC_NAME\":\"\",\"TERMINALINFO\":\"\"}";


        head.setCallSys("CTS");

        head.setFunId("301");

        head.setChannel("a");

        head.setBranch("3013");

        head.setOperatorSite("47.108.58.49");

        head.setOperatorCode("150357653");

        head.setRemark("备用字段3");

        head.setSessionSerialNO("会话序号");

        TradeClientResponse resp = Trade.callTradeService(head, str);

        List<Map> respBody = resp.getResBody(Map.class);

        String branchNo = (String) respBody.get(0).get("BRANCH");
        String session = (String) respBody.get(0).get("SESSION");
        String szAccount;
        String shAccount;
        if (respBody.get(0).get("MARKET").equals("00")) {
            szAccount = (String) respBody.get(0).get("SECU_ACC");
            shAccount = (String) respBody.get(1).get("SECU_ACC");
        } else {
            shAccount = (String) respBody.get(0).get("SECU_ACC");
            szAccount = (String) respBody.get(1).get("SECU_ACC");
        }
        System.out.println("session: " + session);
        System.out.println("shAccount: " + shAccount);
        System.out.println("szAccount: " + szAccount);
        System.out.println("------" + resp.toString());
        KDEntrust kdEntrust = new KDEntrust();
        kdEntrust.setUSER_CODE("150348196");
        kdEntrust.setMARKET("00");
        kdEntrust.setEXT_REC_NUM("123");
        kdEntrust.setSECU_ACC(szAccount);
        kdEntrust.setSECU_CODE("000001");
        kdEntrust.setTRD_ID(BSFlag.YXMR.kdValue);
        kdEntrust.setPRICE("14");
        kdEntrust.setQTY("30000");
        kdEntrust.setMATCH_SEAT("005100");
        kdEntrust.setMATCH_NUM("990");//约定号
        kdEntrust.setLINKMAN("1111");//联系人
        kdEntrust.setLINKADD("2222");
        head.setFunId("403");

        str = "{\"USER_CODE\":\"150357653\"}";
        head.setFunId("504");
        head.setSessionSerialNO(session);
        head.setBranch(branchNo);
        resp = Trade.callTradeService(head, str);
        System.out.println("query 持仓: " + resp.toString());*/
    }

    public static void Dictionaries() {
        TypeUtils.compatibleWithFieldName = true;
        TradeTransServiceImpl Trade = new TradeTransServiceImpl();
        TradeSysHead head = new TradeSysHead();
        String str = "{\"USER_ID_CLS\":\"U\",\"USER_ID\":\"150348196\",\"USER_PWD\":\"123123\",\"PROC_NAME\":\"\",\"TERMINALINFO\":\"\"}";


        head.setCallSys("CTS");

        head.setFunId("301");

        head.setChannel("q");

        head.setBranch("3013");

        head.setOperatorSite("47.108.58.49");

        head.setOperatorCode("150357653");

        head.setRemark("备用字段3");

        head.setSessionSerialNO("会话序号");

        TradeClientResponse resp = Trade.callTradeService(head, str);

        List<Map> respBody = resp.getResBody(Map.class);

        String branchNo = (String) respBody.get(0).get("BRANCH");
        String session = (String) respBody.get(0).get("SESSION");
        String szAccount;
        String shAccount;
        if (respBody.get(0).get("MARKET").equals("00")) {
            szAccount = (String) respBody.get(0).get("SECU_ACC");
            shAccount = (String) respBody.get(1).get("SECU_ACC");
        } else {
            shAccount = (String) respBody.get(0).get("SECU_ACC");
            szAccount = (String) respBody.get(1).get("SECU_ACC");
        }
        System.out.println("session: " + session);
        System.out.println("shAccount: " + shAccount);
        System.out.println("szAccount: " + szAccount);

        str = str = "{\"QUERY_TYPE\":48,\"INT_ORG\":0}";
        head.setFunId("600");
        head.setSessionSerialNO(session);
        head.setBranch(branchNo);
        resp = Trade.callTradeService(head, str);
        System.out.println("query 持仓: " + resp.toString());
    }

}
