package com.joyintech.invest.interf.common.core.utility.s003;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.joyintech.invest.interf.common.core.controller.InterfCoreController;

/**
 * 债券现金流计划 -发送测试
 * 
 * @program: tams-base
 * @description:
 * @author: 王波
 * @create: 2020-02-10 11:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class S003Test {
	@Autowired
	private InterfCoreController interfCoreController;

	@Test
	public void testS003() {
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("pageId", "FinBondTargetAsset002S003");
		dataMap.put("platDate", Long.valueOf("20200214"));
		// 资产代码
		dataMap.put("FINPROD_ID", "1111111");
		// 市场代码
		dataMap.put("FINPROD_MARKET_ID", "222222");

		interfCoreController.send(dataMap);

	}
}
