package com.joyintech.invest.interf.common.core.utility.r011;

import com.joyintech.invest.interf.common.core.service.InterfRecvComponent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.*;

/**
 * 投资管理系统发送底层资产估值信息 -接受测试
 * @program: tams-base
 * @description:
 * @author: daiguofu
 * @create: 2020-02-25 16:56
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class R011Test {
    @Autowired
    private InterfRecvComponent recvComponent;

    @Test
    public void testR011() {
        Map<String, Object> dataMap = new HashMap<>();
        // 接口ID
        dataMap.put("interfId", "R011");
        //日期
        dataMap.put("CDATE",new Date());
        // 组合代码
        dataMap.put("PORTFOLIO_ID", "1111111");
        // 底层资产代码
        dataMap.put("FINPROD_ID", "45652232332");

        // 底层市场代码
        dataMap.put("FINPROD_MARKET_ID", "1545848");

        // 币种
        dataMap.put("CCY", "CNY");

        // 数量
        dataMap.put("SHARE_AMT",5);

        // 摊销总成本
        dataMap.put("DSC_COST_AMT", BigDecimal.valueOf(1000000));

        // 摊销成本净价
        dataMap.put("DSC_CLEAN_PRICE",  BigDecimal.valueOf(5000));

        // 当日应计利息
        dataMap.put("TDY_INTINCEXP", BigDecimal.valueOf(2000));

       //市值
        dataMap.put("MARKET_VALUE", BigDecimal.valueOf(100000));

        recvComponent.recv(dataMap);

    }
}
