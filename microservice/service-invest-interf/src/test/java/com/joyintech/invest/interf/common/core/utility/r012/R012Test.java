package com.joyintech.invest.interf.common.core.utility.r012;

import com.joyintech.invest.interf.common.core.service.InterfRecvComponent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * 投资管理系统接收组合证券账户信息 -接受测试
 * @program: tams-base
 * @description:
 * @author: 代国富
 * @create: 2020-02-25 17:56
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class R012Test {
    @Autowired
    private InterfRecvComponent recvComponent;

    @Test
    public void testR012() {
        Map<String, Object> dataMap = new HashMap<>();
        // 接口ID
        dataMap.put("interfId", "R012");
        // 组合代码
        dataMap.put("PORTFOLIO_ID", "1111111");
        // 组合名称
        dataMap.put("PORTFOLIO_NAME", "组合名称测试");

        // 账户类型
        dataMap.put("ACCT_TYPE", "C01");

        // 账号
        dataMap.put("ACCT_NO", "446445");
        //名称
        dataMap.put("ACCT_NAME","dididdi");

        // 登记托管机构
        dataMap.put("REGIST_ORG", "06");

        // 交易场所
        dataMap.put("TRADE_MARKET", "03");

        // 股东代码
        dataMap.put("HOLDER_ID", "45465656");

        // 券商代码
        dataMap.put("BROKER_ID", "45222222");

        // 回款账户
        dataMap.put("REC_ACCT_ID", "123355555");

       //资金结算账户
        dataMap.put("TRD_ACCT_ID", "12155666");
        //回款账户币种
        dataMap.put("REC_ACCT_CCY", "CNY");
        //资金结算账户币种
        dataMap.put("TRD_ACCT_CCY", "CNY");
        //账户来源
        dataMap.put("SEC_ACCT_SOURCE","01");
        recvComponent.recv(dataMap);

    }
}
