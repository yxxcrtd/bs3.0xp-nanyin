package com.joyintech.tams.exchange.terminal.test;

import com.szkingdom.kcbpcli.*;
import com.szkingdom.kdencode.KDEncoder;

public class Test {






   /* public static int port = 21000;
    public static String ip = "180.168.125.215";
    public static String fundAccount = "26769948";
    public static String sendQueue = "req1gt";
    public static String recvQueue = "ans1gt";
    public static String psw = "123321";
    public static String orgid = "1263";
    public static String custid = "129501193947";
    public static String shStock = "BO80026592";
    public static String szStock = "0899026591";*/




/*
    public static int port = 21000;
    public static String ip = "180.168.125.215";
    public static String fundAccount = "60000050";
    public static String sendQueue = "req_fz4";
    public static String recvQueue = "ans_fz4";
    public static String psw = "123321";
    public static String orgid = "3001";
    public static String custid = "111900000055";
    public static String shStock = "B883050697";
    public static String szStock = "0800423641";

*/

    public static int port = 21002;
    public static String ip = "joyin.gaoyusoft.com";
    public static String fundAccount = "20324940";
    public static String sendQueue = "jxq_hlwjr_bj1";
    public static String recvQueue = "ans_hlwjr_bj1";
    public static String psw = "123321";
    public static String orgid = "1203";
    public static String custid = "120300020269";
    public static String shStock = "B880515847";
    public static String szStock = "0800109948";

    public static void print(KCBPClient client, KCBPHandle handle) {


        // 获取第一结果集
        int ret = client.KCBPCLI_RsOpen(handle);
        if (ret != 0) {
            System.out.println("KCBPCLI_RsOpen error, error code : " + ret);
            return;
        }

        int count = 0;
        ++count;

        // 获取第一结果集名称
        System.out.println("++++++table " + count + ": " + client.KCBPCLI_RsGetCursorName(handle) + "++++++");

        // 获取第一结果集列名表（列名之间由逗号分隔，如LEVEL,CODE,MSG）
        String firstSetNames = client.KCBPCLI_RsGetColNames(handle);
        String[] firstSetNameList = firstSetNames.split(",");
        System.out.println("length: " + firstSetNames.length());
        System.out.println(firstSetNames);

        // 获取第一结果集列数
        int colNum = client.KCBPCLI_RsGetColNum(handle);

        // 获取第一结果的一行, 并需要判断业务是否执行成功
        if (client.KCBPCLI_RsFetchRow(handle) == 0) {
            // 打印第一结果集
            for (int i = 0; i < firstSetNameList.length; ++i) {
                System.out.print(client.KCBPCLI_RsGetColByName(handle, firstSetNameList[i]));
                if (i + 1 != firstSetNameList.length) {
                    System.out.print(',');
                } else {
                    System.out.println();
                }
            }
            // 判断业务是否调用成功
            if (colNum < 3 || Integer.parseInt(client.KCBPCLI_RsGetCol(handle, 2)) != 0) {
                System.out.println("first result error");
                client.KCBPCLI_RsClose(handle);
                client.KCBPCLI_DisConnect(handle);
                client.KCBPCLI_Exit(handle);
                return;
            }
        } else {
            System.out.println("KCBPCLI_RsFetchRow error");
            client.KCBPCLI_RsClose(handle);
            client.KCBPCLI_DisConnect(handle);
            client.KCBPCLI_Exit(handle);
            return;
        }

        // 获取其他结果集
        while (client.KCBPCLI_RsMore(handle) == 0) {
            ++count;
            System.out.println("++++++table " + count + ": " + client.KCBPCLI_RsGetCursorName(handle) + "++++++");
            System.out.println(client.KCBPCLI_RsGetColNames(handle));
            colNum = client.KCBPCLI_RsGetColNum(handle);
            // 获取结果集的一行
            while (client.KCBPCLI_RsFetchRow(handle) == 0) {
                for (int i = 0; i < colNum; i++) {
                    System.out.print(client.KCBPCLI_RsGetCol(handle, i + 1));
                    if (i + 1 != colNum) {
                        System.out.print(',');
                    }
                }
                System.out.println();
            }
        }
        client.KCBPCLI_RsClose(handle);
    }

    public static void login(KCBPClient client, KCBPHandle handle, String strSecretText) {
        String funcCode = "410301";
        //登录
        // 登陆成功用custcert作为后续调用的入参
        client.KCBPCLI_BeginWrite(handle);
        // 功能号
        client.KCBPCLI_SetValue(handle, "funcid", funcCode);
        // 客户代码
        client.KCBPCLI_SetValue(handle, "custid", "");
        // 客户机构
        client.KCBPCLI_SetValue(handle, "custorgid", "");
        // 交易密码
        client.KCBPCLI_SetValue(handle, "trdpwd", strSecretText);
        client.KCBPCLI_SetValue(handle, "netaddr", "127.0.0.1");
        //操作机构
//        client.KCBPCLI_SetValue(handle, "orgid", "1203");
        // 操作方式
        client.KCBPCLI_SetValue(handle, "operway", "7");
        client.KCBPCLI_SetValue(handle, "ext", "0");
        //
//        pKESBCli.KCBPCLI_SetValue(handle, "fundid", "20324940");
        // 登录类型
        client.KCBPCLI_SetValue(handle, "inputtype", "Z");
        // 登录标识
        client.KCBPCLI_SetValue(handle, "inputid", Test.fundAccount);
        // client.KCBPCLI_SetValue(handle, "inputid", "60000050");
        // client.KCBPCLI_SetValue(handle, "inputid", "20324940");
        int ret = client.KCBPCLI_CallProgramAndCommit(handle, funcCode);
        if (ret != KCBPReturnCode.RC_OK) {
            System.out.println("KCBPCLI_CallProgramAndCommit error: " + client.KCBPCLI_GetErrorMsg(handle));
            return;
        } else {
            System.out.println("send msgid success");
        }
        print(client, handle);
    }

    public static void queryBalance(KCBPClient client, KCBPHandle handle, String trdPwd) {
        String funcCode = "410502";
        client.KCBPCLI_BeginWrite(handle);
        // 资金账号
        client.KCBPCLI_SetValue(handle, "funcid", funcCode);
        // 财富账户
        client.KCBPCLI_SetValue(handle, "custid", Test.custid);
        client.KCBPCLI_SetValue(handle, "custorgid", Test.orgid);
        client.KCBPCLI_SetValue(handle, "trdpwd", trdPwd);
        client.KCBPCLI_SetValue(handle, "netaddr", "127.0.0.1");
        client.KCBPCLI_SetValue(handle, "orgid", Test.orgid);
        client.KCBPCLI_SetValue(handle, "operway", "7");
        client.KCBPCLI_SetValue(handle, "ext", "0");
        client.KCBPCLI_SetValue(handle, "fundid", Test.fundAccount);
        client.KCBPCLI_SetValue(handle, "moneytype", "0");
//        client.KCBPCLI_SetValue(handle, "custcert", "LaJwAhwAAAB4cDdwcDdVLzVncHNZQ2ZZRWxnSVpnPT0AAAAAAAAAAAAlWAEAXR0DXgAAAACgjAAA");

        int ret = client.KCBPCLI_CallProgramAndCommit(handle, funcCode);
        if (ret != KCBPReturnCode.RC_OK) {
            System.out.println("KCBPCLI_CallProgramAndCommit error: " + client.KCBPCLI_GetErrorMsg(handle));
            return;
        } else {
            System.out.println("send msgid success");
        }

        print(client, handle);
    }

    public static void queryOrder(KCBPClient client, KCBPHandle handle, String trdPwd) {
        String funcCode = "410510";
        client.KCBPCLI_BeginWrite(handle);
        // 资金账号
        client.KCBPCLI_SetValue(handle, "funcid", funcCode);
        // 财富账户
        client.KCBPCLI_SetValue(handle, "custid", Test.custid);
        client.KCBPCLI_SetValue(handle, "custorgid", Test.orgid);
//        client.KCBPCLI_SetValue(handle, "trdpwd", "xp7pp7U/5gpsYCfYElgIZg==");
        client.KCBPCLI_SetValue(handle, "trdpwd", trdPwd);
        client.KCBPCLI_SetValue(handle, "netaddr", "127.0.0.1");
        client.KCBPCLI_SetValue(handle, "orgid", Test.orgid);
        client.KCBPCLI_SetValue(handle, "operway", "7");
        client.KCBPCLI_SetValue(handle, "ext", "0");
        client.KCBPCLI_SetValue(handle, "fundid", Test.fundAccount);
//        client.KCBPCLI_SetValue(handle, "moneytype", "0");
        client.KCBPCLI_SetValue(handle, "custcert", "22bfJh4AAABUcC9oNkhzeENaNGNYZ3gwWEdaejJRPT0AAAAAAAAAAADpAwAAcBFOXwAAAACgjAAA");

        // 查询当日委托
        client.KCBPCLI_SetValue(handle, "bankcode", "");
        client.KCBPCLI_SetValue(handle, "ordersno", "");
        client.KCBPCLI_SetValue(handle, "stkcode", "");
        client.KCBPCLI_SetValue(handle, "secuid", "");
        client.KCBPCLI_SetValue(handle, "market", "");
        client.KCBPCLI_SetValue(handle, "qryflag", "1");
        // 定位串
        client.KCBPCLI_SetValue(handle, "poststr", "");
        client.KCBPCLI_SetValue(handle, "count", "");

        int ret = client.KCBPCLI_CallProgramAndCommit(handle, funcCode);
        if (ret != KCBPReturnCode.RC_OK) {
            System.out.println("KCBPCLI_CallProgramAndCommit error: " + client.KCBPCLI_GetErrorMsg(handle));
            return;
        } else {
            System.out.println("send msgid success");
        }

        print(client, handle);
    }

    public static void queryTrade(KCBPClient client, KCBPHandle handle, String trdPwd) {
        String funcCode = "410512";
        client.KCBPCLI_BeginWrite(handle);
        // 资金账号
        client.KCBPCLI_SetValue(handle, "funcid", funcCode);
        // 财富账户
        client.KCBPCLI_SetValue(handle, "custid", "120300020269");
        client.KCBPCLI_SetValue(handle, "custorgid", "1203");
        client.KCBPCLI_SetValue(handle, "trdpwd", "xp7pp7U/5gpsYCfYElgIZg==");
        client.KCBPCLI_SetValue(handle, "netaddr", "127.0.0.1");
        client.KCBPCLI_SetValue(handle, "orgid", "1203");
        client.KCBPCLI_SetValue(handle, "operway", "7");
        client.KCBPCLI_SetValue(handle, "ext", "0");
        client.KCBPCLI_SetValue(handle, "fundid", "20324940");
        client.KCBPCLI_SetValue(handle, "moneytype", "0");

        // 查询当日委托
        client.KCBPCLI_SetValue(handle, "bankcode", "");
        client.KCBPCLI_SetValue(handle, "ordersno", "");
        client.KCBPCLI_SetValue(handle, "stkcode", "");
        client.KCBPCLI_SetValue(handle, "secuid", "");
        client.KCBPCLI_SetValue(handle, "market", "");
        // 查询方向是向上还是向下
        client.KCBPCLI_SetValue(handle, "qryflag", "1");
        client.KCBPCLI_SetValue(handle, "poststr", "");
        client.KCBPCLI_SetValue(handle, "count", "200");

        int ret = client.KCBPCLI_CallProgramAndCommit(handle, funcCode);
        if (ret != KCBPReturnCode.RC_OK) {
            System.out.println("KCBPCLI_CallProgramAndCommit error: " + client.KCBPCLI_GetErrorMsg(handle));
            return;
        } else {
            System.out.println("send msgid success");
        }

        print(client, handle);
    }

    public static void queryIntegratedQuote(KCBPClient client, KCBPHandle handle, String trdPwd) {
        //深证大宗交易行情查询          上海：410996
        String funcCode = "410995";
        client.KCBPCLI_BeginWrite(handle);
        // 资金账号
        client.KCBPCLI_SetValue(handle, "funcid", funcCode);
        // 财富账户
        client.KCBPCLI_SetValue(handle, "custid", "120300020269");
        client.KCBPCLI_SetValue(handle, "custorgid", "1203");
        client.KCBPCLI_SetValue(handle, "trdpwd", "xp7pp7U/5gpsYCfYElgIZg==");
        client.KCBPCLI_SetValue(handle, "netaddr", "127.0.0.1");
        client.KCBPCLI_SetValue(handle, "orgid", "1203");
        client.KCBPCLI_SetValue(handle, "operway", "7");
        client.KCBPCLI_SetValue(handle, "ext", "0");
        client.KCBPCLI_SetValue(handle, "fundid", "20324940");
        client.KCBPCLI_SetValue(handle, "moneytype", "0");

        //
        client.KCBPCLI_SetValue(handle, "stkcode", "000001");
        //client.KCBPCLI_SetValue(handle,"confermnum","12");
        //client.KCBPCLI_SetValue(handle,"bsflag","");
        // client.KCBPCLI_SetValue(handle,"status","");

        int ret = client.KCBPCLI_CallProgramAndCommit(handle, funcCode);
        if (ret != KCBPReturnCode.RC_OK) {
            System.out.println("KCBPCLI_CallProgramAndCommit error: " + client.KCBPCLI_GetErrorMsg(handle));
            return;
        } else {
            System.out.println("send msgid success");
        }

        print(client, handle);
    }

    public static void queryXiYiQuote(KCBPClient client, KCBPHandle handle, String trdPwd) {
        String funcCode = "411027";
        client.KCBPCLI_BeginWrite(handle);
        // 资金账号
        client.KCBPCLI_SetValue(handle, "funcid", funcCode);
        // 财富账户
        client.KCBPCLI_SetValue(handle, "custid", "120300020269");
        client.KCBPCLI_SetValue(handle, "custorgid", "1203");
        client.KCBPCLI_SetValue(handle, "trdpwd", "xp7pp7U/5gpsYCfYElgIZg==");
        client.KCBPCLI_SetValue(handle, "netaddr", "127.0.0.1");
        client.KCBPCLI_SetValue(handle, "orgid", "1203");
        client.KCBPCLI_SetValue(handle, "operway", "7");
        client.KCBPCLI_SetValue(handle, "ext", "0");
        client.KCBPCLI_SetValue(handle, "fundid", "20324940");
        client.KCBPCLI_SetValue(handle, "moneytype", "0");

        //
        client.KCBPCLI_SetValue(handle, "stkcode", "155416");
        //client.KCBPCLI_SetValue(handle,"confermnum","12");
        //client.KCBPCLI_SetValue(handle,"bsflag","");
        // client.KCBPCLI_SetValue(handle,"status","");

        int ret = client.KCBPCLI_CallProgramAndCommit(handle, funcCode);
        if (ret != KCBPReturnCode.RC_OK) {
            System.out.println("KCBPCLI_CallProgramAndCommit error: " + client.KCBPCLI_GetErrorMsg(handle));
            return;
        } else {
            System.out.println("send msgid success");
        }

        print(client, handle);
    }

    public static void queryEnable(KCBPClient client, KCBPHandle handle, String trdPwd) {
        //深证大宗交易行情查询          上海：410996
        String funcCode = "410410";
        client.KCBPCLI_BeginWrite(handle);
        // 资金账号
        client.KCBPCLI_SetValue(handle, "funcid", funcCode);
        // 财富账户
        client.KCBPCLI_SetValue(handle, "custid", "120300020269");
        client.KCBPCLI_SetValue(handle, "custorgid", "1203");
        client.KCBPCLI_SetValue(handle, "trdpwd", "xp7pp7U/5gpsYCfYElgIZg==");
        client.KCBPCLI_SetValue(handle, "netaddr", "127.0.0.1");
        client.KCBPCLI_SetValue(handle, "orgid", "1203");
        client.KCBPCLI_SetValue(handle, "operway", "7");
        client.KCBPCLI_SetValue(handle, "ext", "0");
        client.KCBPCLI_SetValue(handle, "fundid", "20324940");
        client.KCBPCLI_SetValue(handle, "moneytype", "0");

        //
        client.KCBPCLI_SetValue(handle, "secuid", "0800109948");
        client.KCBPCLI_SetValue(handle, "stkcode", "000034");
        client.KCBPCLI_SetValue(handle, "market", "0");
        client.KCBPCLI_SetValue(handle, "bsflag", "1E");
        client.KCBPCLI_SetValue(handle, "price", "21.00");
        //client.KCBPCLI_SetValue(handle,"confermnum","12");
        //client.KCBPCLI_SetValue(handle,"bsflag","");
        // client.KCBPCLI_SetValue(handle,"status","");

        int ret = client.KCBPCLI_CallProgramAndCommit(handle, funcCode);
        if (ret != KCBPReturnCode.RC_OK) {
            System.out.println("KCBPCLI_CallProgramAndCommit error: " + client.KCBPCLI_GetErrorMsg(handle));
            return;
        } else {
            System.out.println("send msgid success");
        }

        print(client, handle);
    }

    public static void order(KCBPClient client, KCBPHandle handle, String psw) {
        String funcCode = "410411";
        client.KCBPCLI_BeginWrite(handle);
        // 资金账号
        client.KCBPCLI_SetValue(handle, "funcid", funcCode);
        // 财富账户
        client.KCBPCLI_SetValue(handle, "custid", Test.custid);
        client.KCBPCLI_SetValue(handle, "custorgid", Test.orgid);
        //client.KCBPCLI_SetValue(handle, "trdpwd", "Tp/h6HsxCZ4cXgx0XGZz2Q==");
        client.KCBPCLI_SetValue(handle, "trdpwd", psw);
        client.KCBPCLI_SetValue(handle, "netaddr", "127.0.0.1");
        client.KCBPCLI_SetValue(handle, "orgid", Test.orgid);
        client.KCBPCLI_SetValue(handle, "operway", "7");
        client.KCBPCLI_SetValue(handle, "ext", "0");
        client.KCBPCLI_SetValue(handle, "fundid", Test.fundAccount);
        client.KCBPCLI_SetValue(handle, "moneytype", "0");

        // 下单  1上海:B880515847 0深圳:0800109948
        client.KCBPCLI_SetValue(handle, "secuid", Test.shStock);
        client.KCBPCLI_SetValue(handle, "market", "1");
        client.KCBPCLI_SetValue(handle, "stkcode", "204001");
        client.KCBPCLI_SetValue(handle, "bsflag", "1I");
        client.KCBPCLI_SetValue(handle, "price", "1.00");
        client.KCBPCLI_SetValue(handle, "qty", "300000");
        client.KCBPCLI_SetValue(handle, "ordergroup", "");
        client.KCBPCLI_SetValue(handle, "bankcode", "0");
        client.KCBPCLI_SetValue(handle, "remark", "123");

        client.KCBPCLI_SetValue(handle, "promiseno", "123");//综合协议约定号
        client.KCBPCLI_SetValue(handle, "targetseat", "062700");
        client.KCBPCLI_SetValue(handle, "linkman", "123");
        client.KCBPCLI_SetValue(handle, "linkway", "123");


        //1I 1J  2I 2J
        // client.KCBPCLI_SetValue(handle, "promiseno", "119");
        // client.KCBPCLI_SetValue(handle, "targetseat", "");
        //1m 3n (仅深圳业务)
//        client.KCBPCLI_SetValue(handle, "pricetype", "1");
        //82 84
        // client.KCBPCLI_SetValue(handle, "linkmarket", "");
        // client.KCBPCLI_SetValue(handle, "linkman", "112");
        // client.KCBPCLI_SetValue(handle, "linkway", "334");


        int ret = client.KCBPCLI_CallProgramAndCommit(handle, funcCode);
        if (ret != KCBPReturnCode.RC_OK) {
            System.out.println("KCBPCLI_CallProgramAndCommit error: " + client.KCBPCLI_GetErrorMsg(handle));
            return;
        } else {
            System.out.println("send msgid success");
        }

        print(client, handle);
    }

    //协议回购
    public static void buyBack(KCBPClient client, KCBPHandle handle, String trdPwd) {
        //深证  质押式协议回购
        String funcCode = "411000";
        //上海
        String funcCode1 = "411032";
        // 大宗
        String funcCode2 = "410411";

        client.KCBPCLI_BeginWrite(handle);
        // 功能号
        client.KCBPCLI_SetValue(handle, "funcid", funcCode2);
        // 财富账户
        client.KCBPCLI_SetValue(handle, "custid", Test.custid);
        client.KCBPCLI_SetValue(handle, "custorgid", Test.orgid);
        client.KCBPCLI_SetValue(handle, "trdpwd", trdPwd);
        client.KCBPCLI_SetValue(handle, "netaddr", "127.0.0.1");
        client.KCBPCLI_SetValue(handle, "orgid", Test.orgid);
        client.KCBPCLI_SetValue(handle, "operway", "7");
        client.KCBPCLI_SetValue(handle, "ext", "0");
        client.KCBPCLI_SetValue(handle, "fundid", Test.fundAccount);
        client.KCBPCLI_SetValue(handle, "moneytype", "0");

        // 下单  1上海:B880515847 0深圳:0800109948
        client.KCBPCLI_SetValue(handle, "secuid", Test.shStock);
        client.KCBPCLI_SetValue(handle, "market", "1");
        client.KCBPCLI_SetValue(handle, "stkcode", "204001");
        client.KCBPCLI_SetValue(handle, "bsflag", "1E");
        client.KCBPCLI_SetValue(handle, "price", "100.00");
        client.KCBPCLI_SetValue(handle, "qty", "100000");
        client.KCBPCLI_SetValue(handle, "ordergroup", "0");
        client.KCBPCLI_SetValue(handle, "bankcode", "0");
        client.KCBPCLI_SetValue(handle, "remark", "123");

//        //深证  报价回购
//        //产品编码
//        client.KCBPCLI_SetValue(handle,"prodcode","");
//        //预约类型
//        client.KCBPCLI_SetValue(handle,"prebooktype","1");
//        //预约数量
//        client.KCBPCLI_SetValue(handle,"matchqty","100");
//        //执行日期
//        client.KCBPCLI_SetValue(handle,"execdate","");
//        //原合约成交日期
//        client.KCBPCLI_SetValue(handle,"matchdate","");
//        //原合约成交编号
//        client.KCBPCLI_SetValue(handle,"matchcode","");

        //上海债券协议回购报价申报(411032)  深圳债券质押式协议回购
        //本方交易员代码
//        client.KCBPCLI_SetValue(handle,"companyoperid","");
        //对手方交易员代码
//        client.KCBPCLI_SetValue(handle,"taroperid","");
        //对手方交易商代码
//        client.KCBPCLI_SetValue(handle,"tarcompany","");


        //融资金额
//        client.KCBPCLI_SetValue(handle,"orderamt","100.00");
//        //年利率
//        client.KCBPCLI_SetValue(handle,"duerate","");
//        //对方交易单元
//        client.KCBPCLI_SetValue(handle,"targetseat","141");
//        //约定号
//        client.KCBPCLI_SetValue(handle,"confernum","20");
//        //产品期限
//        client.KCBPCLI_SetValue(handle,"ghdays","3");
//        //委托数量
//        client.KCBPCLI_SetValue(handle,"orderqty","100");

        //上海债券质押式协议回购(411032)
        //融资金额
        client.KCBPCLI_SetValue(handle, "orderamt", "100.00");
        //年利率
        client.KCBPCLI_SetValue(handle, "duerate", "");
        //对方交易单元
        client.KCBPCLI_SetValue(handle, "targetseat", "141");
        //约定号
        client.KCBPCLI_SetValue(handle, "confernum", "20");
        //产品期限
        client.KCBPCLI_SetValue(handle, "ghdays", "3");
        //委托数量
        client.KCBPCLI_SetValue(handle, "orderqty", "100");
        //到期续作类型
        client.KCBPCLI_SetValue(handle, "orderRestrictions", "");
        //初始交易日期
        client.KCBPCLI_SetValue(handle, "tradedate", "");
        //成交编号
        client.KCBPCLI_SetValue(handle, "tradematchcode", "");
        //风险揭示签署流水号
        client.KCBPCLI_SetValue(handle, "risksignsno", "");
        //初始合约号
        client.KCBPCLI_SetValue(handle, "zqzysno", "123");


        int ret = client.KCBPCLI_CallProgramAndCommit(handle, funcCode2);
        if (ret != KCBPReturnCode.RC_OK) {
            System.out.println("KCBPCLI_CallProgramAndCommit error: " + client.KCBPCLI_GetErrorMsg(handle));
            return;
        } else {
            System.out.println("send msgid success");
        }

        print(client, handle);
    }

    public static void cancelOrder(KCBPClient client, KCBPHandle handle) {
        String funcCode = "410413";
        client.KCBPCLI_BeginWrite(handle);
        // 资金账号
        client.KCBPCLI_SetValue(handle, "funcid", funcCode);
        // 财富账户
        client.KCBPCLI_SetValue(handle, "custid", "120300020269");
        client.KCBPCLI_SetValue(handle, "custorgid", "1203");
        client.KCBPCLI_SetValue(handle, "trdpwd", "xp7pp7U/5gpsYCfYElgIZg==");
        client.KCBPCLI_SetValue(handle, "netaddr", "127.0.0.1");
        client.KCBPCLI_SetValue(handle, "orgid", "1203");
        client.KCBPCLI_SetValue(handle, "operway", "7");
        client.KCBPCLI_SetValue(handle, "ext", "0");
        client.KCBPCLI_SetValue(handle, "fundid", "20324940");
        client.KCBPCLI_SetValue(handle, "moneytype", "0");

        // 下单  1上海:B880515847 0深圳:0800109948
        client.KCBPCLI_SetValue(handle, "orderdate", "20191226");
        client.KCBPCLI_SetValue(handle, "ordersno", "23");
//        client.KCBPCLI_SetValue(handle, "bsflag", "B");

        int ret = client.KCBPCLI_CallProgramAndCommit(handle, funcCode);
        if (ret != KCBPReturnCode.RC_OK) {
            System.out.println("KCBPCLI_CallProgramAndCommit error: " + client.KCBPCLI_GetErrorMsg(handle));
            return;
        } else {
            System.out.println("send msgid success");
        }

        print(client, handle);
    }

    public static void queryBiaoZhunQun(KCBPClient client, KCBPHandle handle) {
        String funcCode = "410574";
        client.KCBPCLI_BeginWrite(handle);
        // 资金账号
        client.KCBPCLI_SetValue(handle, "funcid", funcCode);
        // 财富账户
        client.KCBPCLI_SetValue(handle, "custid", "120300020269");
        client.KCBPCLI_SetValue(handle, "custorgid", "1203");
        client.KCBPCLI_SetValue(handle, "trdpwd", "xp7pp7U/5gpsYCfYElgIZg==");
        client.KCBPCLI_SetValue(handle, "netaddr", "127.0.0.1");
        client.KCBPCLI_SetValue(handle, "orgid", "1203");
        client.KCBPCLI_SetValue(handle, "operway", "7");
        client.KCBPCLI_SetValue(handle, "ext", "0");
        client.KCBPCLI_SetValue(handle, "fundid", "20324940");
//        client.KCBPCLI_SetValue(handle, "moneytype", "0");

        // 下单  1上海:B880515847 0深圳:0800109948
        client.KCBPCLI_SetValue(handle, "qryflag", "1");
        client.KCBPCLI_SetValue(handle, "count", "20");
        client.KCBPCLI_SetValue(handle, "poststr", "");
//        client.KCBPCLI_SetValue(handle, "stkcode", "019625");
//        client.KCBPCLI_SetValue(handle, "bsflag", "B");

        int ret = client.KCBPCLI_CallProgramAndCommit(handle, funcCode);
        if (ret != KCBPReturnCode.RC_OK) {
            System.out.println("KCBPCLI_CallProgramAndCommit error: " + client.KCBPCLI_GetErrorMsg(handle));
            return;
        } else {
            System.out.println("send msgid success");
        }

        print(client, handle);
    }

    //查询持仓
    public static void queryStock(KCBPClient client, KCBPHandle handle, String str) {
        String funcCode = "410503";
        client.KCBPCLI_BeginWrite(handle);
        // 资金账号
        client.KCBPCLI_SetValue(handle, "funcid", funcCode);
        // 财富账户
        client.KCBPCLI_SetValue(handle, "custid", "120300020269");
        client.KCBPCLI_SetValue(handle, "custorgid", "1203");
        client.KCBPCLI_SetValue(handle, "trdpwd", "xp7pp7U/5gpsYCfYElgIZg==");
        client.KCBPCLI_SetValue(handle, "netaddr", "127.0.0.1");
        client.KCBPCLI_SetValue(handle, "orgid", "1203");
        client.KCBPCLI_SetValue(handle, "operway", "7");
        client.KCBPCLI_SetValue(handle, "ext", "0");
        client.KCBPCLI_SetValue(handle, "fundid", "20324940");
        client.KCBPCLI_SetValue(handle, "fortuneid", "-1");
        client.KCBPCLI_SetValue(handle, "moneytype", "0");

        // 下单  1上海:B880515847 0深圳:0800109948
        client.KCBPCLI_SetValue(handle, "market", "1");
        client.KCBPCLI_SetValue(handle, "stkcode", "140659");
        client.KCBPCLI_SetValue(handle, "qryflag", "1");
        client.KCBPCLI_SetValue(handle, "count", "20");
        client.KCBPCLI_SetValue(handle, "poststr", "");
        client.KCBPCLI_SetValue(handle, "secuid", "B880515847");
//        client.KCBPCLI_SetValue(handle, "bsflag", "B");

        int ret = client.KCBPCLI_CallProgramAndCommit(handle, funcCode);
        if (ret != KCBPReturnCode.RC_OK) {
            System.out.println("KCBPCLI_CallProgramAndCommit error: " + client.KCBPCLI_GetErrorMsg(handle));
            return;
        } else {
            System.out.println("send msgid success");
        }

        print(client, handle);
    }

    //查询证券信息
    public static void queryInstrumentInfo(KCBPClient client, KCBPHandle handle, String str) {
        String funcCode = "410203";
        client.KCBPCLI_BeginWrite(handle);
        // 资金账号
        client.KCBPCLI_SetValue(handle, "funcid", funcCode);
        // 财富账户
        client.KCBPCLI_SetValue(handle, "custid", "120300020269");
        client.KCBPCLI_SetValue(handle, "custorgid", "1203");
        client.KCBPCLI_SetValue(handle, "trdpwd", "xp7pp7U/5gpsYCfYElgIZg==");
        client.KCBPCLI_SetValue(handle, "netaddr", "127.0.0.1");
        client.KCBPCLI_SetValue(handle, "orgid", "1203");
        client.KCBPCLI_SetValue(handle, "operway", "7");
        client.KCBPCLI_SetValue(handle, "ext", "0");
        client.KCBPCLI_SetValue(handle, "fundid", "20324940");
        client.KCBPCLI_SetValue(handle, "fortuneid", "-1");
        client.KCBPCLI_SetValue(handle, "moneytype", "0");

        // 下单  1上海:B880515847 0深圳:0800109948
        client.KCBPCLI_SetValue(handle, "market", "");
        client.KCBPCLI_SetValue(handle, "stkcode", "");
        client.KCBPCLI_SetValue(handle, "stklevel", "N");
        client.KCBPCLI_SetValue(handle, "rowcount", "100");
        client.KCBPCLI_SetValue(handle, "poststr", "");
//        client.KCBPCLI_SetValue(handle, "secuid", "");
//        client.KCBPCLI_SetValue(handle, "bsflag", "B");

        int ret = client.KCBPCLI_CallProgramAndCommit(handle, funcCode);
        if (ret != KCBPReturnCode.RC_OK) {
            System.out.println("KCBPCLI_CallProgramAndCommit error: " + client.KCBPCLI_GetErrorMsg(handle));
            return;
        } else {
            System.out.println("send msgid success");
        }

        print(client, handle);
    }

    public static void queryDictionaries(KCBPClient client, KCBPHandle handle, String trdPwd) {
        String funcCode = "410220";
        client.KCBPCLI_BeginWrite(handle);
        client.KCBPCLI_SetValue(handle, "funcid", funcCode);
        // 财富账户
        client.KCBPCLI_SetValue(handle, "custid", "120300020269");
        client.KCBPCLI_SetValue(handle, "custorgid", "1203");
        client.KCBPCLI_SetValue(handle, "trdpwd", "xp7pp7U/5gpsYCfYElgIZg==");
        client.KCBPCLI_SetValue(handle, "netaddr", "127.0.0.1");
        client.KCBPCLI_SetValue(handle, "orgid", "1203");
        client.KCBPCLI_SetValue(handle, "operway", "7");
        client.KCBPCLI_SetValue(handle, "ext", "0");
        client.KCBPCLI_SetValue(handle, "fundid", "20324940");
        client.KCBPCLI_SetValue(handle, "moneytype", "0");

        client.KCBPCLI_SetValue(handle, "dictitem", "");
        client.KCBPCLI_SetValue(handle, "subitem", "");
        int ret = client.KCBPCLI_CallProgramAndCommit(handle, funcCode);
        if (ret != KCBPReturnCode.RC_OK) {
            System.out.println("KCBPCLI_CallProgramAndCommit error: " + client.KCBPCLI_GetErrorMsg(handle));
            return;
        } else {
            System.out.println("send msgid success");
        }

        print(client, handle);
    }

    public static void main(String[] args) {
        KCBPHandle handle = new KCBPHandle();
        KCBPClient client = new KCBPClient();

        // 初始化KCBP客户端
        if (client.KCBPCLI_Init(handle) != 0) {
            System.out.println("Init KCBP failed.");
            return;
        }

        client.KCBPCLI_SetOption(handle, KCBPClientC.KCBP_OPTION_CRYPT, 0);
        client.KCBPCLI_SetOption(handle, KCBPClientC.KCBP_OPTION_COMPRESS, 0);

        tagKCBPConnectOption tagKCBPConnectOption = new tagKCBPConnectOption();

        tagKCBPConnectOption.szServerName = "kcbp1";  // "KESB01";//"KESB01";//"kcbp1";
        tagKCBPConnectOption.nProtocal = 0;
        tagKCBPConnectOption.szAddress = Test.ip;
        // tagKCBPConnectOption.szAddress = "125.208.12.78";
        tagKCBPConnectOption.nPort = Test.port;
        tagKCBPConnectOption.szSendQName = Test.sendQueue;
        tagKCBPConnectOption.szReceiveQName = Test.recvQueue;

        // tagKCBPConnectOption.szSendQName = "req_fz4";
        // tagKCBPConnectOption.szReceiveQName = "ans_fz4";

        // tagKCBPConnectOption.szSendQName = "jxq_hlwjr_bj1";
        // tagKCBPConnectOption.szReceiveQName = "ans_hlwjr_bj1";

        if (client.KCBPCLI_SetOption(handle, KCBPClientC.KCBP_OPTION_CURRENT_CONNECT, tagKCBPConnectOption) != 0) {
            System.out.println("set connect option error!");
            return;
        }

        if (client.KCBPCLI_SetOption(handle,
                KCBPClientC.KCBP_OPTION_AUTHENTICATION, 0) != 0) {
            System.out.println("set connect option error!");
            return;
        }

        if (client.KCBPCLI_SetCliTimeOut(handle, 60) != 0) {
            System.out.println("KCBPCLI_SetCliTimeOut Error");
            client.KCBPCLI_Exit(handle);
            return;
        }

        int ret = client.KCBPCLI_ConnectServer(handle, "kcbp1", "KCXP00", "888888");
        if (ret != 0) {
            System.out.println("KESBCLI_SQLConnect failed:" + ret);
            client.KCBPCLI_Exit(handle);
            return;
        }
        System.out.println("KESBCLI_SQLConnect seccess");

        KDEncoder kdEncoder = new KDEncoder();
        String strText = Test.psw;
        // 除用户登录业务外其他业务涉及到密码加密采用的秘钥是: 交易密码: 秘钥是客户代码 资金密码:秘钥是资金账号
        login(client, handle, kdEncoder.KDEncode(6, strText, "410301"));

        // login(client, handle, kdEncoder.KDEncode(6, strText, "410301"));

        // 调410301的时候，加密因子是410301，调其他接口的时候，密码字段要用客户代码120300020269来加密
        String secretText = kdEncoder.KDEncode(6, strText, Test.custid);
        String secretText2 = kdEncoder.KDEncode(6, strText, "138501503336");
        System.out.println(kdEncoder.KDEncode(6, strText, "410301"));
        System.out.println(secretText);
        System.out.println(secretText2);
        //queryOrder(client, handle, secretText);
//        strSecretText = kdEncoder.KDEncode(6, strText, "");
        /*
        while(true){
            queryBalance(client, handle, secretText);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
        }
*/
        queryDictionaries(client, handle, secretText);
        //queryOrder(client, handle, secretText);
//        long t = System.currentTimeMillis();
        order(client, handle, secretText);
//        queryBiaoZhunQun(client, handle);
//        System.out.println(System.currentTimeMillis() - t);
//        buyBack(client,handle);
//        cancelOrder(client, handle);
        // queryIntegratedQuote(client, handle, secretText);
//        queryEnable(client, handle, secretText);

    }
}
