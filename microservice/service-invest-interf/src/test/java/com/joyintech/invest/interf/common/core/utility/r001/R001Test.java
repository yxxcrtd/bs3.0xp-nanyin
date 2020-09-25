package com.joyintech.invest.interf.common.core.utility.r001;

import com.joyintech.invest.interf.InvestInterfApplication;
import com.joyintech.invest.interf.common.core.service.InterfRecvComponent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.*;

/**
 * 投资管理系统获取其他现金流交易信息 -接受测试
 * @program: tams-base
 * @description:
 * @author: 王波
 * @create: 2020-02-10 11:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class R001Test {
    @Autowired
    private InterfRecvComponent recvComponent;

    @Test
    public void testR001() {
        Map<String, Object> dataMap = new HashMap<>();
        // 接口ID
        dataMap.put("interfId", "R001");
        // 组合代码
        dataMap.put("PORTFOLIO_ID", "1111111");
        // 组合名称
        dataMap.put("PORTFOLIO_NAME", "组合名称测试");

        // 交易日期测试
        dataMap.put("TRADE_DATE", new Date());

        // 支付日
        dataMap.put("SETTLE_DATE", new Date());

        // 币种
        dataMap.put("SETTLE_DATE", "CNY");

        // 金额
        dataMap.put("CHL_ID", BigDecimal.valueOf(110));

        // 收付方向
        dataMap.put("TRADE_TYPE", "A");

        // 资金用途
        dataMap.put("PURPOSE", "TEST");

        // 备注
        dataMap.put("REMARK", "备注");

        // 划款信息
        Map<String, Object> settleMap = new HashMap<>();
        settleMap.put("REC_ACCT_NO", "11111");
        settleMap.put("PAY_ACCT_NO", "AAAAA");
        settleMap.put("SETTLE_AMT", BigDecimal.valueOf(1000000));
        settleMap.put("CCY", "CNY");

        List<Map<String, Object>> list = new ArrayList<>();
        list.add(settleMap);
        dataMap.put("SETTLE_INFO", list);

        recvComponent.recv(dataMap);

    }
}
