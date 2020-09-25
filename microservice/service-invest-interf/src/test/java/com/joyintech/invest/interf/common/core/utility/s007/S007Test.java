package com.joyintech.invest.interf.common.core.utility.s007;

import com.joyintech.invest.interf.common.core.controller.InterfCoreController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 投资管理系统发送交易数据-接受测试
 * @program: tams-base
 * @description:
 * @author: 平路远
 * @create: 2020-02-10 11:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class S007Test {
    @Autowired
    private InterfCoreController interfCoreController;

    @Test
    public void testS007() {
        Map<String, Object> dataMap = new HashMap<>();
        // 接口ID
        dataMap.put("pageId", "FinFundTargetAsset002S007");
        dataMap.put("platDate",Long.valueOf("20200214"));
        dataMap.put("finprodId", "9ceda0eaac3f49bc861b");
        dataMap.put("ccy", "CNY");
        dataMap.put("tradeMarket", "03");

        interfCoreController.send(dataMap);

    }
}
