package com.joyintech.invest.interf.common.core.utility.s053;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.invest.interf.common.core.contants.InfDictCodeList;
import com.joyintech.invest.interf.common.core.controller.InterfCoreController;
import com.joyintech.invest.interf.common.core.service.InterfRecvComponent;

/**
 * 投资管理系统债券交易对手信息-发送测试
 * 
 * @program: tams-base
 * @description:
 * @author: 董志康
 * @create: 2020-02-10 11:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class S053Test {
	@Autowired
	private InterfCoreController interfCoreController;

	@Test
	public void testS053() {
		// 交易表Map
		Map<String, Object> dataMap = new HashMap<>();
		// 交易表Map
		Map<String, Object> trdProductDeal = new HashMap<>();
		// 交易附加表Map
		Map<String, Object> trdProductDealAdd = new HashMap<>();
		// 交易表Map
		Map<String, Object> paTrdProductDeal = new HashMap<>();
		// 标的表Map
		Map<String, Object> finProduct = new HashMap<>();
		// 指令表Map
		Map<String, Object> stlPayOrders = new HashMap<>();
		// 报文时间
		long date = 20191101;
		dataMap.put("platDate", date);
		// pageid
		dataMap.put("pageId", "PtlFundAllocation002");
		// 交易编号
		trdProductDeal.put("tradeId", "JYSTOCK201908020003");

		// 交易类型
		trdProductDeal.put("finprodType2", "01");
		paTrdProductDeal.put("ps", "02");
		dataMap.put("isInnerTrade", "02");
		// 组合代码
		trdProductDealAdd.put("portfolioId", "jixuzuhe0002");

		// 交易日
		trdProductDeal.put("vdate", date);
		// 支付日
		trdProductDeal.put("settleDate", date);

		// 收付方向
		stlPayOrders.put("ps", stlPayOrders.get("01"));
		// 交易金额
		stlPayOrders.put("settleAmt", BigDecimal.valueOf(110));
		// 结算币种
		stlPayOrders.put("ccy", "CNY");

		// 交易对手代码
		trdProductDeal.put("counterId", "JYDS000000000384");
		// 交易场所
		trdProductDeal.put("tradeMarket", "03");
		// 资产代码
		trdProductDeal.put("finprodId", "F10_CS001.SH");
		// 市场代码
		finProduct.put("finprodMarketId", "AAAAA");
		// 交易市场
		trdProductDeal.put("tradeMarketMode", "02");
		// 交易平台
		trdProductDeal.put("tradePlat", "01");
		// 交易日
		trdProductDeal.put("tradeDate", date);
		// 交割日
		trdProductDeal.put("settleDate", date);
		// 交付日
		trdProductDeal.put("payDate", date);

		// 收付方向
		stlPayOrders.put("ps", "02");
		// 结算金额
		stlPayOrders.put("settleAmt", BigDecimal.valueOf(1101));
		// 结算币种
		stlPayOrders.put("ccy", "CNY");

		// 交易状态
		dataMap.put("tradeStatus", "01");
		// 原交易编号
		trdProductDeal.put("pTradeId", "CPCL000000001129");
		// 撤销说明
		dataMap.put("CANCEL_REMARK", "123");
		// 证券账户代码
		dataMap.put("secManageAcctId", "JIXUZQ0002");
		// 证券账户名称
		// body.put("SEC_MANAGE_ACCT_NAME", value);
		// 通道代码
		trdProductDeal.put("chlId", "TYTD01");
		// 通道名称
		// body.put("CHL_NAME", request.get("actPortfolioId"));
		// 备注
		trdProductDeal.put("remark", "111");
		// 通道本金
		trdProductDeal.put("prinAmt", BigDecimal.valueOf(11012));
		// 通道收益
		trdProductDeal.put("intAmt", BigDecimal.valueOf(11011));

		// 划款信息

		// 收款账户
		stlPayOrders.put("counterAcctNo", "111111");
		// 付款账户
		stlPayOrders.put("myAcctNo", "A");
		// 划款金额
		trdProductDeal.put("tradeAmt", BigDecimal.valueOf(1100));
		// 币种
		stlPayOrders.put("ccy", "CNY");
		dataMap.put("stlPayOrders", stlPayOrders);
		dataMap.put("trdProductDeal", trdProductDeal);
		dataMap.put("trdProductDealAdd", trdProductDealAdd);
		dataMap.put("paTrdProductDeal", paTrdProductDeal);
		dataMap.put("finProduct", finProduct);
		dataMap.put("stlPayOrders", stlPayOrders);

		interfCoreController.send(dataMap);

	}
}
