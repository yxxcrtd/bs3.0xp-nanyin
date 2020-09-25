package com.joyintech.invest.interf.common.core.utility.r009;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.joyintech.invest.interf.common.core.service.InterfRecvComponent;

/**
 * 组合估值信息 -接受测试
 * 
 * @program: tams-base
 * @description:
 * @author: 王波
 * @create: 2020-02-10 11:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class R009Test {
	@Autowired
	private InterfRecvComponent recvComponent;

	@Test
	public void testR009() {
		Map<String, Object> dataMap = new HashMap<>();
		// 接口ID
		dataMap.put("interfId", "R009");
		// 日期
		dataMap.put("CDATE", new Date());
		// 组合代码
		dataMap.put("PORTFOLIO_ID", "1111111");
		// 组合名称
		dataMap.put("PORTFOLIO_NAME", "1111111");
		// 估值币种
		dataMap.put("CCY", "CNY");
		// 当日资产净值
		dataMap.put("TDY_ASSET_NETVALUE", BigDecimal.valueOf(1000));
		// 当日资产总值
		dataMap.put("TDY_ASSET_VALUE", BigDecimal.valueOf(10000));
		// 当前份额
		dataMap.put("TDY_SHARE_AMT", BigDecimal.valueOf(1000));
		// 当日单位净值
		dataMap.put("TDY_UNIT_VALUE", BigDecimal.valueOf(100));
		// 当日累计净值
		dataMap.put("TDY_UNIT_VALUE_SUM", BigDecimal.valueOf(1000));
		// 当日未分配利润
		dataMap.put("TDY_UNPAID_INTINCEXP", BigDecimal.valueOf(200));
		// 7日年化收益率
		dataMap.put("YIELD_7", BigDecimal.valueOf(0.01));

		recvComponent.recv(dataMap);

	}
}
