package com.joyintech.invest.interf.common.core.utility.s052;

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
 * 投资管理系统费用支付交易-发送测试
 * 
 * @program: tams-base
 * @description:
 * @author: 董志康
 * @create: 2020-02-10 11:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class S052Test {
	@Autowired
	private InterfCoreController interfCoreController;

	@Test
	public void testS054() {
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
		dataMap.put("pageId", "TrdRepoTrade002");

		// 交易编号
		trdProductDeal.put("tradeId", "JYSTOCK201908020003");

		// 交易类型
		trdProductDeal.put("finprodType2", "01");
		paTrdProductDeal.put("paBussType", "02");
		// 组合代码
		trdProductDealAdd.put("portfolioId", "jixuzuhe0002");

		// 交易日
		trdProductDeal.put("tradeDate", date);
		// 支付日
		trdProductDeal.put("settleDate", date);
		// 交割日
		trdProductDeal.put("payDate", date);
		// 费用区间开始日
		trdProductDeal.put("mdate", date);
		// 费用区间结束日
		trdProductDeal.put("vdate", date);

		// 收付方向
		stlPayOrders.put("ps", "02");

		// 结算币种
		stlPayOrders.put("ccy", "CNY");

		// 交易状态
		dataMap.put("tradeStatus", "01");
		// 原交易编号
		trdProductDeal.put("pTradeId", "11111");
		// 撤销说明
		dataMap.put("revokeReason", ("22222"));
		// 证券账户代码
		dataMap.put("secManageAcctId", "AAAAA");

		// 备注
		trdProductDeal.put("remark", "11111");
		// 费用明细信息

		// 费用明细信息
		List<Map<String, Object>> tradeFeeDetaillist = new ArrayList<Map<String, Object>>();
		Map<String, Object> list = new HashMap<>();

		// 费用类型
		list.put("feeType", "01");
		// 费用币种
		list.put("ccy", "CNY");
		// 费用金额
		list.put("feeAmt", BigDecimal.valueOf(11110));
		// 费用收付方向
		// feeTypeMap.put("PS", DictCodeList.支付);
		// 是否随交易支付
		list.put("isPayWithTrade", "01");
		tradeFeeDetaillist.add(list);

		// 划款信息

		// 收款账户
		stlPayOrders.put("counterAcctNo", "111111");
		// 付款账户
		stlPayOrders.put("myAcctNo", "A");
		// 划款金额
		trdProductDeal.put("tradeAmt", BigDecimal.valueOf(11330));
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
