package com.joyintech.invest.interf.common.core.utility.r005;

import com.joyintech.invest.interf.InvestInterfApplication;
import com.joyintech.invest.interf.business.BusinessUtil;
import com.joyintech.invest.interf.common.core.service.InterfRecvComponent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.*;

/**
 * 投资管理系统组合信息 -接受测试
 * 
 * @program: tams-base
 * @description:
 * @author: 董志康
 * @create: 2020-02-10 11:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class R005Test {
	@Autowired
	private InterfRecvComponent recvComponent;

	@Test
	public void testR005() {
		Map<String, Object> dataMap = new HashMap<>();
		// 接口ID
		dataMap.put("interfId", "R005");
		// 组合代码
		dataMap.put("PORTFOLIO_ID", "11111");
		// 组合名称
		dataMap.put("PORTFOLIO_NAME", "22222");
		// 组合开始日
		dataMap.put("VDATE", new Date());
		// 组合结束日
		dataMap.put("VDATE", new Date());
		// 收益类型
		dataMap.put("PROFIT_TYPE", "01");
		// 组合类型
		dataMap.put("PORTFOLIO_TYPE", "01");
		// 是否保本
		dataMap.put("INCOME_FLAG", "Y");
		// 开放类型
		dataMap.put("OPEN_TYPE", "01");
		// 募集方式
		dataMap.put("RAISE_TYPE", "1");
		// 投资性质
		dataMap.put("INVEST_NATURE", "01");
		// 产品经理
		dataMap.put("PROD_MANAGER", "ABABA");
		// 到账金额
		dataMap.put("FINPROD_MARKET_ID", BigDecimal.valueOf(110));
		// 风险等级
		dataMap.put("RISK_LEVEL", "01");
		// 收益类型
		dataMap.put("PROFIT_TYPE", "01");

		// 组合类型
		dataMap.put("PORTFOLIO_TYPE", "02");

		// 是否保本
		dataMap.put("INCOME_FLAG", "YES");

		// 开放类型
		dataMap.put("OPEN_TYPE", "01");

		// 募集方式
		dataMap.put("RAISE_TYPE", "03");

		// 投资性质
		dataMap.put("INVEST_TYPE", "03");
		
		// 投资性质
		dataMap.put("RISK_LEVEL", "03");
		
		recvComponent.recv(dataMap);

	}
}
