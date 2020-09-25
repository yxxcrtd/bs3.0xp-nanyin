package com.joyintech.invest.interf.common.core.utility.s044;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.joyintech.invest.interf.common.core.controller.InterfCoreController;

/**
 * 利率互换交易 -发送测试
 * 
 * @program: tams-base
 * @description:
 * @author: 王波
 * @create: 2020-02-10 11:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class S044Test {
	@Autowired
	private InterfCoreController interfCoreController;

	@Test
	public void testS044() {
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("pageId", "TrdIRSTrade002");
		dataMap.put("platDate", Long.valueOf("20200214"));

		// 交易表Map
		Map<String, Object> trdProductDeal = new HashMap<>();
		// 交易编号
		trdProductDeal.put("tradeId", "JYSTOCK201908020003");
		// 成交编号/合同编号
		trdProductDeal.put("fTradeId", "scd");
		// 金融产品类型
		trdProductDeal.put("finprodType2", "F10");
		// 交易方向
		trdProductDeal.put("ps", "T01");

		// 是否内部交易
		dataMap.put("isInnerTrade", "否");
		// 组合代码
		dataMap.put("portfolioId", "121212");

		// 交易对手代码
		trdProductDeal.put("counterId", "JYDS000000000384");
		// 交易场所
		trdProductDeal.put("tradeMarket", "03");
		// 资产代码
		trdProductDeal.put("finprodId", "F10_CS001.SH");
		// 交易市场
		trdProductDeal.put("tradeMarketMode", "01");
		// 交易平台
		trdProductDeal.put("tradePlat", "01");
		// 交易日
		trdProductDeal.put("tradeDate", Long.valueOf("20200214"));
		// 交割日
		trdProductDeal.put("settleDate", Long.valueOf("20200214"));
		// 起息日
		trdProductDeal.put("vdate", Long.valueOf("20200214"));
		// 到期日
		trdProductDeal.put("mdate", Long.valueOf("20200214"));

		// 交易类金融产品标的Map
		Map<String, Object> finTradeProduct = new HashMap<>();
		// 期限品种
		finTradeProduct.put("termType", "R007");
		dataMap.put("finTradeProduct", finTradeProduct);

		// 名义本金
		trdProductDeal.put("prin", "01");
		// 名义本金币种
		trdProductDeal.put("ccy", "CNY");

		// 其他信息
		List<Map<String, Object>> finRateInfoList = new ArrayList<>();
		Map<String, Object> finRateInfoMap = new HashMap<>();
		finRateInfoMap.put("intType", "01");
		// 计息基础
		finRateInfoMap.put("basis", "A/365");
		// 初始利率
		finRateInfoMap.put("rate", BigDecimal.valueOf(0.06));
		finRateInfoMap.put("cashId", "000bbfa300c57a821240cd3ce4169712");
		finRateInfoList.add(finRateInfoMap);
		dataMap.put("finRateInfoList", finRateInfoList);

		List<Map<String, Object>> finCashPayRuleList = new ArrayList<>();
		Map<String, Object> finCashPayRuleMap = new HashMap<>();
		finCashPayRuleMap.put("cashId", "3213c143de475bc2b1596af9c4e0e50c");
		// 付息频率
		finCashPayRuleMap.put("payCycle", "03");
		// 付息频率
		finCashPayRuleMap.put("payTdayRule", "02");
		// 计息营业日准则
		finCashPayRuleMap.put("endTdayRule", "02");
		finCashPayRuleList.add(finCashPayRuleMap);
		dataMap.put("finCashPayRuleList", finCashPayRuleList);

		// 首期利率确定日
		finRateInfoMap.put("FirstConfirmDate", "2019-07-19");
		// 挂钩利率代码
		finRateInfoMap.put("benchmarkId", "FR007");
		// 利率系数
		finRateInfoMap.put("coefficient", BigDecimal.valueOf(1));
		// 利差
		finRateInfoMap.put("spreadRate", BigDecimal.valueOf(1));
		// 利率重置频率
		finRateInfoMap.put("resetFreq", "09");

		// 原交易编号
		trdProductDeal.put("pTradeId", "111111");

		// 撤销说明
		dataMap.put("revokeReason", "撤销");
		// 交易状态
		dataMap.put("tradeStatus", "01");

		// 交易状态
		trdProductDeal.put("remark", "备注");

		// 费用明细信息
		List<Map<String, Object>> tradeFeeDetaillist = new ArrayList<>();
		Map<String, Object> tradeFeeDetailMap = new HashMap<>();
		// 费用类型
		tradeFeeDetailMap.put("feeType", "01");
		// 费用币种
		tradeFeeDetailMap.put("ccy", "CNY");
		// 费用金额
		tradeFeeDetailMap.put("feeAmt", BigDecimal.valueOf(10000));
		tradeFeeDetaillist.add(tradeFeeDetailMap);
		dataMap.put("tradeFeeDetaillist", tradeFeeDetaillist);

		// 费用收付方向
		dataMap.put("feePs", "01");

		// 是否随交易支付
		tradeFeeDetailMap.put("isPayWithTrade", "是");

		// 证券账户代码
		dataMap.put("secManageAcctId", "111212");

		// 通道代码
		trdProductDeal.put("chlId", "222222222");

		// 转换资产代码
		dataMap.put("actPortfolioId", "31321321");
		// 参与人名称
		dataMap.put("counterId", "32324");

		// 参与人名称
		trdProductDeal.put("counterTrader", "111");
		// 参与人联系方式
		trdProductDeal.put("counterContact", "11111");

		// 账户信息
		List<Map<String, Object>> stlFinProductAcctList = new ArrayList<>();
		Map<String, Object> stlFinProductAcctMap = new HashMap<>();
		// 账户类型
		stlFinProductAcctMap.put("acctType", "A01");
		// 账号
		stlFinProductAcctMap.put("acctNo", "ZSBK:13141487324");
		// 币种
		stlFinProductAcctMap.put("ccy", "CNY");
		stlFinProductAcctList.add(stlFinProductAcctMap);
		dataMap.put("stlFinProductAcctList", stlFinProductAcctList);

		dataMap.put("trdProductDeal", trdProductDeal);

		interfCoreController.send(dataMap);

	}
}
