package com.joyintech.invest.interf.common.core.utility.s031;

import com.joyintech.invest.interf.common.core.controller.InterfCoreController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * 投资管理系统发送资产要素信息-接受测试
 * @program: tams-base
 * @description:
 * @author: 平路远
 * @create: 2020-02-10 11:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class S031Test {
    @Autowired
    private InterfCoreController interfCoreController;

    @Test
    public void testS031() {
        Map<String, Object> dataMap = new HashMap<>();
        // 接口ID
        dataMap.put("pageId", "FinStockTargetAsset002S031");
        dataMap.put("platDate",Long.valueOf("20200214"));
        dataMap.put("finprodId", "9ceda0eaac3f49bc861b");
        dataMap.put("ccy", "CNY");
        dataMap.put("vdate", "20200215");

        interfCoreController.send(dataMap);

    }
}
