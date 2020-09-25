package com.joyintech.invest.interf.common.core.utility.s005;

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
 * 债券交易 -发送测试
 * 
 * @program: tams-base
 * @description:
 * @author: 王波
 * @create: 2020-02-10 11:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class S005Test {
	@Autowired
	private InterfCoreController interfCoreController;

	@Test
	public void testS005() {
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("pageId", "TrdBondInvestTrade002");
		dataMap.put("platDate", Long.valueOf("20200214"));

		// 交易表Map
		Map<String, Object> trdProductDeal = new HashMap<>();
		// 交易编号
		trdProductDeal.put("tradeId", "00000000000000000004");
		// 成交编号/合同编号
		trdProductDeal.put("fTradeId", "12121212");
		// 金融产品类型
		trdProductDeal.put("finprodType2", "F10");
		// 交易方向
		trdProductDeal.put("ps", "T01");
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
		// 交付日
		trdProductDeal.put("payDate", Long.valueOf("20200214"));
		// 清算速度
		trdProductDeal.put("settleSpeed", Long.valueOf(2));
		// 百元净价
		trdProductDeal.put("unitCprice", BigDecimal.valueOf(12));
		// 百元应计利息
		trdProductDeal.put("unitInt", BigDecimal.valueOf(1));
		// 百元全价
		trdProductDeal.put("unitFprice", BigDecimal.valueOf(1));
		// 净价总额
		trdProductDeal.put("cpriceAmt", BigDecimal.valueOf(9000));
		// 券面总额
		trdProductDeal.put("shareAmt", BigDecimal.valueOf(10000));
		// 应计利息总额
		trdProductDeal.put("intAmt", BigDecimal.valueOf(100));
		// 全价总额
		trdProductDeal.put("fpriceAmt", BigDecimal.valueOf(10000));
		// 到期收益率
		trdProductDeal.put("yield", BigDecimal.valueOf(0.01));
		// 行权收益率
		trdProductDeal.put("exerYield", BigDecimal.valueOf(0));
		// 投资目的
		trdProductDeal.put("invAim", "FVTPL");
		// 原交易编号
		trdProductDeal.put("pTradeId", "CPCL000000001129");
		// 通道代码
		trdProductDeal.put("chlId", "F28_TYTD01");
		// 备注
		trdProductDeal.put("remark", "备注");
		// 零头折现金额
		trdProductDeal.put("tradeAmt", BigDecimal.valueOf(100));
		// 参与人名称
		trdProductDeal.put("counterTrader", "11");
		// 参与人联系方式
		trdProductDeal.put("counterContact", "111");
		// 证券账户代码
		trdProductDeal.put("secManageAcctId", "11321424");
		dataMap.put("trdProductDeal", trdProductDeal);

		// 是否内部交易
		dataMap.put("isInnerTrade", "是");
		// 组合代码
		dataMap.put("portfolioId", "11111");

		// 标的表Map
		Map<String, Object> finProduct = new HashMap<>();
		// 市场代码
		finProduct.put("finprodMarketId", "222222");
		// 资产名称
		finProduct.put("finprodName", "21212121");
		dataMap.put("finProduct", finProduct);

		// 指令表Map
		Map<String, Object> stlPayOrders = new HashMap<>();
		// 收付方向
		stlPayOrders.put("ps", "02");
		// 结算金额
		stlPayOrders.put("settleAmt", BigDecimal.valueOf(10000));
		// 结算币种
		stlPayOrders.put("ccy", "CNY");
		// 收款账户
		stlPayOrders.put("recAcctNo", "111");
		// 付款账户
		stlPayOrders.put("payAcctNo", "222");
		// 划款金额
		stlPayOrders.put("settleAmt", "10000");
		dataMap.put("stlPayOrders", stlPayOrders);

		// 交易状态
		dataMap.put("tradeStatus", "交易状态");
		// 撤销说明
		dataMap.put("revokeReason", "撤销说明");

		// 转换信息
		Map<String, Object> trdFinProductBarter = new HashMap<>();
		// 转换资产代码
		trdFinProductBarter.put("finprodId", "111122131313");
		// 转换市场代码
		trdFinProductBarter.put("finprodMarketId", "121212");
		// 转换资产名称
		trdFinProductBarter.put("finprodName", "1212122121");
		// 转换价格
		trdFinProductBarter.put("unitCprice", BigDecimal.valueOf(10000));
		// 转换数量
		trdFinProductBarter.put("shareAmt", BigDecimal.valueOf(1000));
		dataMap.put("trdFinProductBarter", trdFinProductBarter);

		// 保证品信息
		Map<String, Object> trdProductDealAdd = new HashMap<>();
		// 保证金金额
		trdProductDealAdd.put("depositAmt", BigDecimal.valueOf(10000));
		dataMap.put("trdProductDealAdd", trdProductDealAdd);

		// 参与人名称
		dataMap.put("traderName", "A");
		// 参与人联系方式
		dataMap.put("traderContactInfo", "111111");

		// 费用明细信息
		List<Map<String, Object>> tradeFeeDetaillist = new ArrayList<>();
		Map<String, Object> tradeFeeDetailMap = new HashMap<>();
		// 费用类型
		tradeFeeDetailMap.put("feeType", "B01");
		// 费用币种
		tradeFeeDetailMap.put("ccy", "CNY");
		// 费用金额
		tradeFeeDetailMap.put("feeAmt", BigDecimal.valueOf(100));
		// 费用收付方向
		tradeFeeDetailMap.put("feePs", "C1");
		// 是否随交易支付
		tradeFeeDetailMap.put("isPayWithTrade", "是");
		tradeFeeDetaillist.add(tradeFeeDetailMap);
		dataMap.put("tradeFeeDetaillist", tradeFeeDetaillist);

		interfCoreController.send(dataMap);

	}
}
