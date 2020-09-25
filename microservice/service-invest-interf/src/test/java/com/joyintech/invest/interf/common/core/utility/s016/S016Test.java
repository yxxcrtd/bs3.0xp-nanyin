package com.joyintech.invest.interf.common.core.utility.s016;

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
 * 投资管理系统项目类资产存续期 -发送测试
 * 
 * @program: tams-base
 * @description:
 * @author: 董志康
 * @create: 2020-02-10 11:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class S016Test {
	@Autowired
	private InterfCoreController interfCoreController;

	@Test
	public void testS016() {
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
		long date =20191101;
		dataMap.put("platDate",date);
		// pageid
		dataMap.put("pageId", "TrdProjectDuration002");
		// 交易编号
		trdProductDeal.put("tradeId", "JYSTOCK201908020003");
		// 成交编号/合同编号
		trdProductDeal.put("fTradeId", "11111");
		// 交易类型
		trdProductDeal.put("finprodType2", "01");
		paTrdProductDeal.put("paBussType", "02");
		// 组合代码
		trdProductDealAdd.put("portfolioId", "jixuzuhe0002");
		// 交易对手代码
		trdProductDeal.put("counterId", "JYDS000000000384");
		// 交易场所
		trdProductDeal.put("tradeMarket", "03");
		// 资产代码
		trdProductDeal.put("finprodId", "F10_CS001.SH");
		// 获取id
		finProduct.put("finprodMarketId", "121212");
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

		// 清算速度
		trdProductDeal.put("SETTLE_SPEED", "2");
		// 百元净价
		trdProductDeal.put("UNIT_CPRICE", BigDecimal.valueOf(110));
		// 百元应计利息
		trdProductDeal.put("unitInt", "1");
		// 百元全价
		trdProductDeal.put("unitFprice", "1");
		// 净价总额
		trdProductDeal.put("cpriceAmt", BigDecimal.valueOf(110));
		// 券面总额
		trdProductDeal.put("shareAmt", BigDecimal.valueOf(110));
		// 应计利息总额
		trdProductDeal.put("intAmt", "19.73");
		// 全价总额
		trdProductDeal.put("fpriceAmt", BigDecimal.valueOf(110));
		// 到期收益率
		trdProductDeal.put("yield", "0.1");
		// 行权收益率
		trdProductDeal.put("exerYield", "0");
		// 收付方向
		stlPayOrders.put("ps", "02");
		// 结算金额
		stlPayOrders.put("settleAmt", BigDecimal.valueOf(110));
		// 结算币种
		stlPayOrders.put("ccy", "CNY");

		// 投资目的
		trdProductDeal.put("invAim", "AC");
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
		// 转换信息
		// 转换资产代码
		dataMap.put("actPortfolioId", "112");
		// 转换市场代码
		dataMap.put("actPortfolioId", "121211");
		// 转换资产名称
		dataMap.put("actPortfolioId", "ASAD");
		// 转换价格
		dataMap.put("actPortfolioId", BigDecimal.valueOf(110));
		// 转换数量
		dataMap.put("actPortfolioId", "1");
		// 零头折现金额
		dataMap.put("actPortfolioId", "20000");

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

		// 费用明细信息
		List<Map<String, Object>> tradeFeeDetaillist = new ArrayList<Map<String, Object>>();
		Map<String, Object> list = new HashMap<>();

		// 费用类型
		list.put("feeType", "01");
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
		
		interfCoreController.send(dataMap);

	}
}
