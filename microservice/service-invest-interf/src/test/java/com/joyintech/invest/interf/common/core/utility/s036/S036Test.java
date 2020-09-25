package com.joyintech.invest.interf.common.core.utility.s036;

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
 * 投资管理系统债券买断式回购交易-发送测试
 * 
 * @program: tams-base
 * @description:
 * @author: 董志康
 * @create: 2020-02-10 11:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class S036Test {
	@Autowired
	private InterfCoreController interfCoreController;

	@Test
	public void testS036() {
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
		dataMap.put("pageId", "TrdOutRightRepoTrade002");
		// 交易编号
		trdProductDeal.put("tradeId", "JYSTOCK201908020003");
		// 成交编号/合同编号
		trdProductDeal.put("fTradeId", "11111");
		// 交易类型
		trdProductDeal.put("finprodType2", "01");
		paTrdProductDeal.put("ps", "02");
		dataMap.put("isInnerTrade", "01");

		// 组合代码
		trdProductDealAdd.put("portfolioId", "jixuzuhe0002");
		// 交易对手代码
		trdProductDeal.put("counterId", "JYDS000000000384");

		// 交易日
		trdProductDeal.put("tradeDate", date);
		// 交割日
		trdProductDeal.put("settleDate", date);
		// 起息日
		trdProductDeal.put("vdate", date);
		// 到期日
		trdProductDeal.put("mdate", date);
		// 清算速度
		trdProductDeal.put("SETTLE_SPEED", "2");

		// 收付方向
		stlPayOrders.put("ps", "02");
		// 结算金额
		stlPayOrders.put("settleAmt", BigDecimal.valueOf(110));
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

		// 首期百元净价
		trdProductDeal.put("UNIT_CPRICE", BigDecimal.valueOf(110));
		// 首期百元应计利息
		trdProductDeal.put("unitInt", "1");
		// 首期百元全价
		trdProductDeal.put("unitFprice", "1");
		// 首期全价总额
		trdProductDeal.put("fpriceAmt", BigDecimal.valueOf(1111));
		// 参与人名称
		dataMap.put("traderName", "AAAA");
		// 参与人联系方式
		dataMap.put("traderContactInfo", "1111111");

		// 对手方交易员
		trdProductDeal.put("counterTrader", "ABAC");

		// 参与人名称
		trdProductDeal.put("counterTrader", "FVVV");
		// 参与人联系方式
		trdProductDeal.put("counterContact", "123456");
		// 期限品种
		trdProductDealAdd.put("termType", "GC182");

		// 交易类金融产品标的表Map
		@SuppressWarnings("unchecked")
		Map<String, Object> finTradeProduct = new HashMap<>();
		 

		// 实际占款天数
		finTradeProduct.put("actCapDays", 1);
		// 计息基础
		finTradeProduct.put("BASIS", "A/A");
		// 到期应计利息
		finTradeProduct.put("basis", "0.66");
		// 到期结算金额
		finTradeProduct.put("mTradeAmt", BigDecimal.valueOf(1111));
		// 到期百元净价
		finTradeProduct.put("mUnitCprice", BigDecimal.valueOf(111));
		// 到期百元全价
		finTradeProduct.put("mUnitInt", BigDecimal.valueOf(2322));
		// 到期百元应计利息
		finTradeProduct.put("mUnitFprice", "0.8");
		// 账号
		stlPayOrders.put("counterAcctNo", "WWWQQ");
		stlPayOrders.put("myAcctNo", "ERERE");
		// 币种
		stlPayOrders.put("ccy", "AUD");

		// 费用明细信息
		List<Map<String, Object>> tradeFeeDetaillist = new ArrayList<Map<String, Object>>();
		Map<String, Object> list = new HashMap<>();

		// 费用类型
		list.put("feeType", "05");
		// 费用币种
		list.put("ccy", "CNY");
		// 费用金额
		list.put("feeAmt", BigDecimal.valueOf(110));
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
		trdProductDeal.put("tradeAmt", BigDecimal.valueOf(110));
		// 币种
		stlPayOrders.put("ccy", "CNY");
		dataMap.put("stlPayOrders", stlPayOrders);
		dataMap.put("tradeFeeDetaillist", tradeFeeDetaillist);
		dataMap.put("trdProductDeal", trdProductDeal);
		dataMap.put("trdProductDealAdd", trdProductDealAdd);
		dataMap.put("paTrdProductDeal", paTrdProductDeal);
		dataMap.put("finProduct", finProduct);
		dataMap.put("stlPayOrders", stlPayOrders);
		dataMap.put("finTradeProduct", finTradeProduct);
		interfCoreController.send(dataMap);

	}
}
