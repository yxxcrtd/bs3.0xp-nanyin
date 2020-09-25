package com.joyintech.invest.interf.common.core.utility.r008;

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
 * 组合资产估值信息 -接受测试
 * 
 * @program: tams-base
 * @description:
 * @author: 王波
 * @create: 2020-02-10 11:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class R008Test {
	@Autowired
	private InterfRecvComponent recvComponent;

	@Test
	public void testR008() {
		Map<String, Object> dataMap = new HashMap<>();
		// 接口ID
		dataMap.put("interfId", "R008");
		// 日期
		dataMap.put("CDATE", new Date());
		// 组合代码
		dataMap.put("PORTFOLIO_ID", "1111111");
		// 组合名称
		dataMap.put("PORTFOLIO_NAME", "1111111");
		// 证券账户代码
		dataMap.put("SEC_ACCT_ID", "22222");
		// 资产代码
		dataMap.put("FINPROD_ID", "33333");
		// 投资目的
		dataMap.put("INV_AIM", "FVTPL");
		// 原币种
		dataMap.put("CCY", "CNY");
		// 数量
		dataMap.put("SHARE_AMT", BigDecimal.valueOf(100));
		// 公允价值变动_原币
		dataMap.put("TDY_FLOAT_INGPL", BigDecimal.valueOf(100));
		// 摊销总成本_原币
		dataMap.put("DSC_COST_AMT", BigDecimal.valueOf(1000));
		// 摊销成本净价_原币
		dataMap.put("DSC_CLEAN_PRICE", BigDecimal.valueOf(600));
		// 当日应计利息总额_原币
		dataMap.put("TDY_INTINCEXP", BigDecimal.valueOf(100));
		// 待清算资金_原币
		dataMap.put("DELAY_PAY_AMT", BigDecimal.valueOf(2000));
		// 实际日利率
		dataMap.put("ACT_D_YIELD", BigDecimal.valueOf(0.01));
		// 估值本位币
		dataMap.put("B_CCY", BigDecimal.valueOf(100));
		// 公允价值变动_本位币
		dataMap.put("TDY_FLOAT_INGPL_B", BigDecimal.valueOf(100));
		// 摊销总成本_本位币
		dataMap.put("DSC_COST_AMT_B", BigDecimal.valueOf(100));
		// 摊销成本净价_本位币
		dataMap.put("DSC_CLEAN_PRICE_B", BigDecimal.valueOf(100));
		// 当日应计利息总额_本位币
		dataMap.put("TDY_INTINCEXP_B", BigDecimal.valueOf(100));
		// 待清算资金_本位币
		dataMap.put("DELAY_PAY_AMT_B", BigDecimal.valueOf(100));
		// 剩余期限
		dataMap.put("END_DAYS_1", BigDecimal.valueOf(100));
		// 剩余存续期限
		dataMap.put("END_DAYS_2", BigDecimal.valueOf(100));
		// 计提利率
		dataMap.put("INT_RATE", BigDecimal.valueOf(0.01));
		// 收入端应计利息_原币
		dataMap.put("IN_TDY_INTINCEXP", BigDecimal.valueOf(100));
		// 支出端应计利息_原币
		dataMap.put("OUT_TDY_INTINCEXP", BigDecimal.valueOf(100));
		// 当日价差收入_本币
		dataMap.put("TDY_DSCLOSS_ADD_B", BigDecimal.valueOf(100));

		recvComponent.recv(dataMap);

	}
}
