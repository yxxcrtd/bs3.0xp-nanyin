package com.joyintech.invest.interf.common.core.utility.r010;

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
 * 组合费用估值信息 -接受测试
 * 
 * @program: tams-base
 * @description:
 * @author: 王波
 * @create: 2020-02-10 11:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class R010Test {
	@Autowired
	private InterfRecvComponent recvComponent;

	@Test
	public void testR010() {
		Map<String, Object> dataMap = new HashMap<>();
		// 接口ID
		dataMap.put("interfId", "R010");
		// 日期
		dataMap.put("CDATE", new Date());
		// 组合代码
		dataMap.put("PORTFOLIO_ID", "111111");
		// 组合名称
		dataMap.put("PORTFOLIO_NAME", "111111");
		// 原币种
		dataMap.put("O_CCY", "CNY");
		// 估值本位币
		dataMap.put("B_CCY", "CNY");
		// 费用类型
		dataMap.put("PTL_FEE_TYPE", "B01");
		// 费用场所
		dataMap.put("PTL_FEE_PLACE", "1111");
		// 当日区间应付费用_原币
		dataMap.put("TDY_INTERVAL_PAY_FEE", BigDecimal.valueOf(100));
		// 当日累计应付费用_原币
		dataMap.put("TDY_ACCUMUL_PAY_FEE", BigDecimal.valueOf(1000));
		// 当日区间应付费用_本位币
		dataMap.put("TDY_INTERVAL_PAY_FEE_B", BigDecimal.valueOf(100));
		// 当日累计应付费用_本位币
		dataMap.put("TDY_ACCUMUL_PAY_FEE_B", BigDecimal.valueOf(1000));

		recvComponent.recv(dataMap);

	}
}
