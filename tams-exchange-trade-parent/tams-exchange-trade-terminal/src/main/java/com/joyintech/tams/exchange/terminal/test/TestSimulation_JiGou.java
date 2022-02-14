package com.joyintech.tams.exchange.terminal.test;

import com.szkingdom.kcbpcli.*;
import com.szkingdom.kdencode.KDEncoder;

public class TestSimulation_JiGou {

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
        client.KCBPCLI_SetValue(handle, "orgid", "1203");
        // 操作方式
        client.KCBPCLI_SetValue(handle, "operway", "7");
        client.KCBPCLI_SetValue(handle, "ext", "0");
        //
//        pKESBCli.KCBPCLI_SetValue(handle, "fundid", "20324940");
        // 登录类型
        client.KCBPCLI_SetValue(handle, "inputtype", "Z");
        // 登录标识
        client.KCBPCLI_SetValue(handle, "inputid", "27004935");
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
        client.KCBPCLI_SetValue(handle, "custid", "120300020269");
        client.KCBPCLI_SetValue(handle, "custorgid", "1203");
        client.KCBPCLI_SetValue(handle, "trdpwd", "xp7pp7U/5gpsYCfYElgIZg==");
        client.KCBPCLI_SetValue(handle, "netaddr", "127.0.0.1");
        client.KCBPCLI_SetValue(handle, "orgid", "1203");
        client.KCBPCLI_SetValue(handle, "operway", "7");
        client.KCBPCLI_SetValue(handle, "ext", "0");
        client.KCBPCLI_SetValue(handle, "fundid", "20324940");
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
        client.KCBPCLI_SetValue(handle, "custid", "120300008514");
        client.KCBPCLI_SetValue(handle, "custorgid", "1203");
//        client.KCBPCLI_SetValue(handle, "trdpwd", "xp7pp7U/5gpsYCfYElgIZg==");
        client.KCBPCLI_SetValue(handle, "trdpwd", trdPwd);
        client.KCBPCLI_SetValue(handle, "netaddr", "127.0.0.1");
        client.KCBPCLI_SetValue(handle, "orgid", "1203");
        client.KCBPCLI_SetValue(handle, "operway", "7");
        client.KCBPCLI_SetValue(handle, "ext", "0");
        client.KCBPCLI_SetValue(handle, "fundid", "20311965");
//        client.KCBPCLI_SetValue(handle, "moneytype", "0");
        client.KCBPCLI_SetValue(handle, "custcert", "LaJwAhwAAAB4cDdwcDdVLzVncHNZQ2ZZRWxnSVpnPT0AAAAAAAAAAAAlWAEAmBwEXgAAAACgjAAA");

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
        String funcCode = "410436";
        String funcCode1 = "410512";
        client.KCBPCLI_BeginWrite(handle);
        // 资金账号
        client.KCBPCLI_SetValue(handle, "funcid", funcCode);
        // 财富账户
        client.KCBPCLI_SetValue(handle, "custid", "129501421640");
        client.KCBPCLI_SetValue(handle, "custorgid", "1230");
        client.KCBPCLI_SetValue(handle, "trdpwd", trdPwd);
        client.KCBPCLI_SetValue(handle, "netaddr", "127.0.0.1");
        client.KCBPCLI_SetValue(handle, "orgid", "1230");
        client.KCBPCLI_SetValue(handle, "operway", "M");
        client.KCBPCLI_SetValue(handle, "ext", "0");
        client.KCBPCLI_SetValue(handle, "fundid", "27004935");
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

    public static void order(KCBPClient client, KCBPHandle handle, String trdpwd) {

        String funcCode = "410411";

        //上交所固收平台业务
        String funcCode1 = "460010";
        client.KCBPCLI_BeginWrite(handle);
        // 资金账号
        client.KCBPCLI_SetValue(handle, "funcid", funcCode);
        // 财富账户
        client.KCBPCLI_SetValue(handle, "custid", "129501421640");
        client.KCBPCLI_SetValue(handle, "custorgid", "1230");
        client.KCBPCLI_SetValue(handle, "trdpwd", trdpwd);
        System.out.println(trdpwd);
        client.KCBPCLI_SetValue(handle, "netaddr", "127.0.0.1");
        client.KCBPCLI_SetValue(handle, "orgid", "1230");
        client.KCBPCLI_SetValue(handle, "operway", "M");
        client.KCBPCLI_SetValue(handle, "ext", "0");
        client.KCBPCLI_SetValue(handle, "fundid", "27004935");
        client.KCBPCLI_SetValue(handle, "moneytype", "0");

        // 下单  1上海:B878265139 席位 42115   0深圳:0816733126  席位 062700
        client.KCBPCLI_SetValue(handle, "secuid", "0816733126");
        client.KCBPCLI_SetValue(handle, "market", "0");
        client.KCBPCLI_SetValue(handle, "stkcode", "100303");
        client.KCBPCLI_SetValue(handle, "bsflag", "1E");
        client.KCBPCLI_SetValue(handle, "price", "100");
        client.KCBPCLI_SetValue(handle, "qty", "2000");
        client.KCBPCLI_SetValue(handle, "ordergroup", "20022417072601");
        client.KCBPCLI_SetValue(handle, "bankcode", "0");
        client.KCBPCLI_SetValue(handle, "remark", "123");


        //1I 1J  2I 2J
        //client.KCBPCLI_SetValue(handle,"promiseno","1234");
        //client.KCBPCLI_SetValue(handle,"targetseat","");
        //1m 3n (仅深圳业务)
        //client.KCBPCLI_SetValue(handle,"pricetype","1");
        //82 84
        //client.KCBPCLI_SetValue(handle,"linkmarket","");


        int ret = client.KCBPCLI_CallProgramAndCommit(handle, funcCode);
        if (ret != KCBPReturnCode.RC_OK) {
            System.out.println("KCBPCLI_CallProgramAndCommit error: " + client.KCBPCLI_GetErrorMsg(handle));
            return;
        } else {
            System.out.println("send msgid success");
        }

        print(client, handle);
    }


    //大宗  协议回购   上交所固收
    public static void buyBack(KCBPClient client, KCBPHandle handle, String trdpwd) {
//        String funcCode = "410411";

        //深证  质押式协议回购
        String funcCode1 = "411000";
        //上海 上海债券协议回购报价申报
        String funcCode = "411032";

        //上交所固收
        String funcCode3 = "460010";
        client.KCBPCLI_BeginWrite(handle);
        // 功能号
        client.KCBPCLI_SetValue(handle, "funcid", funcCode);
        // 财富账户
        client.KCBPCLI_SetValue(handle, "custid", "120300008514");
        client.KCBPCLI_SetValue(handle, "custorgid", "1203");
        client.KCBPCLI_SetValue(handle, "trdpwd", "CorU4Duxs3j0OKtQcI2eNw==");
        client.KCBPCLI_SetValue(handle, "netaddr", "127.0.0.1");
        client.KCBPCLI_SetValue(handle, "orgid", "1203");
        client.KCBPCLI_SetValue(handle, "operway", "7");
        client.KCBPCLI_SetValue(handle, "ext", "0");
        client.KCBPCLI_SetValue(handle, "fundid", "20311965");
        client.KCBPCLI_SetValue(handle, "moneytype", "0");

        // 下单  1上海:A568595052 席位 42115   0深圳:0052734029  席位 062700
        client.KCBPCLI_SetValue(handle, "secuid", "A568595052");
        client.KCBPCLI_SetValue(handle, "market", "1");
        client.KCBPCLI_SetValue(handle, "stkcode", "010107");
        client.KCBPCLI_SetValue(handle, "bsflag", "2C");
        client.KCBPCLI_SetValue(handle, "price", "3");
        client.KCBPCLI_SetValue(handle, "qty", "100.00");
        client.KCBPCLI_SetValue(handle, "ordergroup", "0");
        client.KCBPCLI_SetValue(handle, "bankcode", "0");
        client.KCBPCLI_SetValue(handle, "remark", "123");

        //大宗交易  上海约定号席位长度不能大于6
//        client.KCBPCLI_SetValue(handle, "promiseno", "123");

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
//        //本方交易员代码
//        client.KCBPCLI_SetValue(handle, "companyoperid", "123");
//        //对手方交易员代码
//        client.KCBPCLI_SetValue(handle, "taroperid", "1234");
//        //对手方交易商代码
//        client.KCBPCLI_SetValue(handle, "tarcompany", "23123");


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
        client.KCBPCLI_SetValue(handle, "orderamt", "1000000.00");
        //年利率
        client.KCBPCLI_SetValue(handle, "duerate", "");
        //对方交易单元
        client.KCBPCLI_SetValue(handle, "targetseat", "141");
        //约定号
//        client.KCBPCLI_SetValue(handle,"confernum","20");
        //本方交易员代码
        client.KCBPCLI_SetValue(handle, "companyoperid", "Z11825");
        //        //对手方交易员代码
        client.KCBPCLI_SetValue(handle, "taroperid", "Z11825");
//        //对手方交易商代码
        client.KCBPCLI_SetValue(handle, "tarcompany", "118");
        //产品期限
        client.KCBPCLI_SetValue(handle, "ghdays", "3");
        //委托数量
        client.KCBPCLI_SetValue(handle, "orderqty", "10000");
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

//        //固收平台
//        //发起方交易员
//        client.KCBPCLI_SetValue(handle, "traderid", "1234");
//        //对手方交易商
//        //client.KCBPCLI_SetValue(handle,"targetcompany","5678");
//        //对手方交易员
//        client.KCBPCLI_SetValue(handle, "targettrader", "34234");
//        //约定号   必须为100到999之间的整数
//        client.KCBPCLI_SetValue(handle, "confernum", "101");
//        //录入类型  4J,4K:综合协议交易规则表
//        client.KCBPCLI_SetValue(handle, "accounttype", "1");


        int ret = client.KCBPCLI_CallProgramAndCommit(handle, funcCode);
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

    //查询持仓
    public static void queryStock(KCBPClient client, KCBPHandle handle, String str) {
        String funcCode = "410503";
        client.KCBPCLI_BeginWrite(handle);
        // 资金账号
        client.KCBPCLI_SetValue(handle, "funcid", funcCode);
        // 财富账户
        client.KCBPCLI_SetValue(handle, "custid", "129501421640");
        client.KCBPCLI_SetValue(handle, "custorgid", "1230");
        client.KCBPCLI_SetValue(handle, "trdpwd", str);
        client.KCBPCLI_SetValue(handle, "netaddr", "127.0.0.1");
        client.KCBPCLI_SetValue(handle, "orgid", "1230");
        client.KCBPCLI_SetValue(handle, "operway", "M");
        client.KCBPCLI_SetValue(handle, "ext", "0");
        client.KCBPCLI_SetValue(handle, "fundid", "27004935");
        client.KCBPCLI_SetValue(handle, "fortuneid", "-1");
        client.KCBPCLI_SetValue(handle, "moneytype", "0");

        // 下单  1上海:B880515847 0深圳:0800109948
        client.KCBPCLI_SetValue(handle, "market", "");
        client.KCBPCLI_SetValue(handle, "stkcode", "");
        client.KCBPCLI_SetValue(handle, "qryflag", "1");
        client.KCBPCLI_SetValue(handle, "count", "");
        client.KCBPCLI_SetValue(handle, "poststr", "");
        client.KCBPCLI_SetValue(handle, "secuid", "");
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

    //查询最大可用数
    public static void queryEnable(KCBPClient client, KCBPHandle handle, String str) {
        String funcCode = "410410";
        client.KCBPCLI_BeginWrite(handle);
        // 资金账号
        client.KCBPCLI_SetValue(handle, "funcid", funcCode);
        // 财富账户
        client.KCBPCLI_SetValue(handle, "custid", "120300008514");
        client.KCBPCLI_SetValue(handle, "custorgid", "1203");
        client.KCBPCLI_SetValue(handle, "trdpwd", str);
        client.KCBPCLI_SetValue(handle, "netaddr", "127.0.0.1");
        client.KCBPCLI_SetValue(handle, "orgid", "1203");
        client.KCBPCLI_SetValue(handle, "operway", "7");
        client.KCBPCLI_SetValue(handle, "ext", "0");
        client.KCBPCLI_SetValue(handle, "fundid", "20311965");
//        client.KCBPCLI_SetValue(handle,"fortuneid","-1");
        client.KCBPCLI_SetValue(handle, "moneytype", "0");

        // 下单  1上海:B880515847 0深圳:0800109948
        client.KCBPCLI_SetValue(handle, "market", "1");
        client.KCBPCLI_SetValue(handle, "stkcode", "601828");
        client.KCBPCLI_SetValue(handle, "secuid", "A568595052");
        client.KCBPCLI_SetValue(handle, "bsflag", "1I");
        client.KCBPCLI_SetValue(handle, "price", "10.65");

        int ret = client.KCBPCLI_CallProgramAndCommit(handle, funcCode);
        if (ret != KCBPReturnCode.RC_OK) {
            System.out.println("KCBPCLI_CallProgramAndCommit error: " + client.KCBPCLI_GetErrorMsg(handle));
            return;
        } else {
            System.out.println("send msgid success");
        }

        print(client, handle);
    }

    //查询最大可用数
    public static void queryIntegratedQuote(KCBPClient client, KCBPHandle handle, String str) {
        String funcCode = "410996";
        client.KCBPCLI_BeginWrite(handle);
        // 资金账号
        client.KCBPCLI_SetValue(handle, "funcid", funcCode);
//        // 财富账户
        client.KCBPCLI_SetValue(handle, "custid", "129501421640");
        client.KCBPCLI_SetValue(handle, "custorgid", "1203");
        client.KCBPCLI_SetValue(handle, "trdpwd", str);
        client.KCBPCLI_SetValue(handle, "netaddr", "127.0.0.1");
        client.KCBPCLI_SetValue(handle, "orgid", "1203");
        client.KCBPCLI_SetValue(handle, "operway", "7");
        client.KCBPCLI_SetValue(handle, "ext", "0");
//        client.KCBPCLI_SetValue(handle, "fundid", "20311965");
////        client.KCBPCLI_SetValue(handle,"fortuneid","-1");
//        client.KCBPCLI_SetValue(handle, "moneytype", "0");


        client.KCBPCLI_SetValue(handle, "market", "1");
        client.KCBPCLI_SetValue(handle, "stkcode", "601828");
        client.KCBPCLI_SetValue(handle, "secuid", "A568595052");
        client.KCBPCLI_SetValue(handle, "bsflag", "");

        int ret = client.KCBPCLI_CallProgramAndCommit(handle, funcCode);
        if (ret != KCBPReturnCode.RC_OK) {
            System.out.println("KCBPCLI_CallProgramAndCommit error: " + client.KCBPCLI_GetErrorMsg(handle));
            return;
        } else {
            System.out.println("send msgid success");
        }

        print(client, handle);
    }

    //查询历史成交
    public static void queryHistoryTrade(KCBPClient client, KCBPHandle handle, String str) {
        String funcCode = "411513";
        client.KCBPCLI_BeginWrite(handle);
        // 资金账号
        client.KCBPCLI_SetValue(handle, "funcid", funcCode);
        // 财富账户
        client.KCBPCLI_SetValue(handle, "custid", "120300008514");
        client.KCBPCLI_SetValue(handle, "custorgid", "1203");
        client.KCBPCLI_SetValue(handle, "trdpwd", str);
        client.KCBPCLI_SetValue(handle, "netaddr", "127.0.0.1");
        client.KCBPCLI_SetValue(handle, "orgid", "1203");
        client.KCBPCLI_SetValue(handle, "operway", "7");
        client.KCBPCLI_SetValue(handle, "ext", "0");
        client.KCBPCLI_SetValue(handle, "fundid", "20311965");
//        client.KCBPCLI_SetValue(handle,"fortuneid","-1");
        client.KCBPCLI_SetValue(handle, "moneytype", "0");


        client.KCBPCLI_SetValue(handle, "secuid", "A568595052");
        client.KCBPCLI_SetValue(handle, "strdate", "20200305");
        client.KCBPCLI_SetValue(handle, "enddate", "20200306");
        client.KCBPCLI_SetValue(handle, "qryflag", "");
        client.KCBPCLI_SetValue(handle, "count", "10");
        client.KCBPCLI_SetValue(handle, "poststr", "");
        client.KCBPCLI_SetValue(handle, "market", "");
        client.KCBPCLI_SetValue(handle, "stkcode", "");
        client.KCBPCLI_SetValue(handle, "bankcode", "");

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
        tagKCBPConnectOption.szAddress = "joyin.gaoyusoft.com";
        tagKCBPConnectOption.nPort = 21001;
        tagKCBPConnectOption.szSendQName = "req_bj1";
        tagKCBPConnectOption.szReceiveQName = "ans_bj1";

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
        String strText = "123321";
        // 除用户登录业务外其他业务涉及到密码加密采用的秘钥是: 交易密码: 秘钥是客户代码 资金密码:秘钥是资金账号
        login(client, handle, kdEncoder.KDEncode(6, strText, "410301"));
        // 调410301的时候，加密因子是410301，调其他接口的时候，密码字段要用客户代码120300020269来加密
        String secretText = kdEncoder.KDEncode(6, strText, "120300020269");
        queryOrder(client, handle, secretText);
//        strSecretText = kdEncoder.KDEncode(6, strText, "");
//        queryBalance(client, handle, secretText);
//        queryTrade(client, handle, secretText);

//        long t = System.currentTimeMillis();
//        order(client, handle, secretText);
//        System.out.println(System.currentTimeMillis() - t);
//
//        long t1 = System.currentTimeMillis();
//        queryEnable(client, handle, secretText);
//        System.out.println(System.currentTimeMillis() - t1);

//        buyBack(client,handle,secretText);
//        cancelOrder(client, handle);
//        queryStock(client, handle,secretText);

//        queryIntegratedQuote(client, handle, secretText);

//        queryHistoryTrade(client, handle, secretText);

    }
}
