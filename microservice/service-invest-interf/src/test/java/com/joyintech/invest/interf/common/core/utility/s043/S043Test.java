package com.joyintech.invest.interf.common.core.utility.s043;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.joyintech.invest.interf.common.core.controller.InterfCoreController;

/**
 * 期权存续期 -发送测试
 * 
 * @program: tams-base
 * @description:
 * @author: 王波
 * @create: 2020-02-10 11:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class S043Test {
	@Autowired
	private InterfCoreController interfCoreController;

	@Test
	public void testS043() {
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("pageId", "TrdOptionDuration002");
		dataMap.put("platDate", Long.valueOf("20200214"));

		// 交易表Map
		Map<String, Object> trdProductDeal = new HashMap<>();
		// 交易编号
		trdProductDeal.put("tradeId", "JYSTOCK201908020003");
		// 金融产品类型
		trdProductDeal.put("finprodType2", "F10");
		// 交易方向
		trdProductDeal.put("ps", "T01");

		// 交易附加表Map
		Map<String, Object> trdProductDealAdd = new HashMap<>();
		// 组合代码
		trdProductDealAdd.put("portfolioId", "1212121");
		dataMap.put("trdProductDealAdd", trdProductDealAdd);

		// 交易场所
		trdProductDeal.put("tradeMarket", "03");
		// 资产代码
		trdProductDeal.put("finprodId", "F10_CS001.SH");

		// 标的表Map
		Map<String, Object> finProduct = new HashMap<>();
		// 市场代码
		finProduct.put("finprodMarketId", "222222");
		dataMap.put("finProduct", finProduct);

		// 通道代码
		trdProductDeal.put("chlId", "F28_TYTD01");
		// 交易日
		trdProductDeal.put("tradeDate", Long.valueOf("20200214"));
		// 交割日
		trdProductDeal.put("payDate", Long.valueOf("20200214"));
		// 计息区间开始日
		trdProductDeal.put("vdate", Long.valueOf("20200214"));
		// 计息区间结束日
		trdProductDeal.put("mdate", Long.valueOf("20200214"));
		// 本金金额
		trdProductDeal.put("prinAmt", BigDecimal.valueOf(8599500));
		// 应计利息总额
		trdProductDeal.put("intAmt", BigDecimal.valueOf(865479.45));
		// 结算金额
		trdProductDeal.put("tradeAmt", BigDecimal.valueOf(9464979.45));

		// 指令表Map
		Map<String, Object> stlPayOrders = new HashMap<>();
		// 收付方向
		stlPayOrders.put("ps", "02");
		// 结算币种
		stlPayOrders.put("ccy", "CNY");

		// 交易状态
		dataMap.put("tradeStatus", "01");

		// 原交易编号
		trdProductDeal.put("pTradeId", "CPCL000000001332");

		// 撤销说明
		dataMap.put("revokeReason", "撤销");
		// 证券账户代码
		dataMap.put("secManageAcctId", "121212444");

		// 备注
		trdProductDeal.put("remark", "1111");

		// 参与人名称
		dataMap.put("traderName", "11");
		// 参与人联系方式
		dataMap.put("traderContactInfo", "112");

		// 收款账户
		stlPayOrders.put("counterAcctNo", "4190522");
		// 付款账户
		stlPayOrders.put("myAcctNo", "1232324");

		dataMap.put("trdProductDeal", trdProductDeal);
		dataMap.put("stlPayOrders", stlPayOrders);

		interfCoreController.send(dataMap);

	}
}
