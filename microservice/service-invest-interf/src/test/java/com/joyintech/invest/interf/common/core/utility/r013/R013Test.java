package com.joyintech.invest.interf.common.core.utility.r013;

import com.joyintech.invest.interf.common.core.service.InterfRecvComponent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.*;

/**
 * 投资管理系统接收组合资金账户信息 -接受测试
 * @program: tams-base
 * @description:
 * @author: 代国富
 * @create: 2020-02-25 16:56
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class R013Test {
    @Autowired
    private InterfRecvComponent recvComponent;

    @Test
    public void testR013() {
        Map<String, Object> dataMap = new HashMap<>();
        // 接口ID
        dataMap.put("interfId", "R013");
        // 组合代码
        dataMap.put("PORTFOLIO_ID", "1111111");
        // 组合名称
        dataMap.put("PORTFOLIO_NAME", "组合名称测试");

        // 账户类型
        dataMap.put("ACCT_TYPE", "A01");

        // 账号
        dataMap.put("ACCT_NO", "446445");

        // 币种
        dataMap.put("CCY", "CNY");

        // 户名
        dataMap.put("ACCT_NAME", "xiaodai");

        // 开户机构
        dataMap.put("BANK_DEP", "浦发");

        // 联行行号
        dataMap.put("CORR_NO", "46465656565623233");

        // 大额支付系统号/网点号
        dataMap.put("LARGE_PAYNO", "123355555");

       //银行国际代码（Swift Code）
        dataMap.put("SWIFT_CODE", "12155666");
        //境内境外
        dataMap.put("BLN_AREA", "01");
        //行内行外
        dataMap.put("IN_OUT_BANK", "02");

        recvComponent.recv(dataMap);

    }
}
