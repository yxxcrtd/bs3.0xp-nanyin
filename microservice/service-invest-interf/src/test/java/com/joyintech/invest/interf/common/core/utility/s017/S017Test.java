package com.joyintech.invest.interf.common.core.utility.s017;

import com.joyintech.invest.interf.common.core.controller.InterfCoreController;
import com.joyintech.invest.interf.common.core.service.InterfRecvComponent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.*;

/**
 * 计划类资产要素信息
 * @program: tams-base
 * @description:
 * @author: daiguofu
 * @create: 2020-02-17 7:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class S017Test {
//    @Autowired
//    private InterfRecvComponent recvComponent;
    @Autowired
    private InterfCoreController interfCoreController;
    @Test
    public void testS017() {
        Map<String, Object> dataMap = new HashMap<>();
        // pageId
        dataMap.put("pageId","FinPlanTargetAsset002S017");
        //资产代码
        dataMap.put("FINPROD_ID","123456659");
        //市场代码
        dataMap.put("FINPROD_MARKET_ID","11111222");
        dataMap.put("platDate",Long.valueOf("20200214"));


        interfCoreController.send(dataMap);
    }
}
