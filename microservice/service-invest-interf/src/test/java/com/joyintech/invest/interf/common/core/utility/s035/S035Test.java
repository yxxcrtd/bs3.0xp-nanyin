package com.joyintech.invest.interf.common.core.utility.s035;

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
 * 投资管理系统债券质押式回购存续期-发送测试
 * 
 * @program: tams-base
 * @description:
 * @author: 董志康
 * @create: 2020-02-10 11:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class S035Test {
	@Autowired
	private InterfCoreController interfCoreController;

	@Test
	public void testS035() {
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
				long date =20191101;
				dataMap.put("platDate",date);
		// pageid
		dataMap.put("pageId", "TrdRepoDuration002");
 		// 交易编号
		trdProductDeal.put("tradeId", "JYSTOCK201908020003");
	 
		// 交易类型
		trdProductDeal.put("finprodType2", "01");
		paTrdProductDeal.put("ps", "02");
		// 组合代码
		trdProductDealAdd.put("portfolioId", "jixuzuhe0002");
		 // 通道代码
		trdProductDeal.put("chlId", "11111");
		
		 
		// 交易日
		trdProductDeal.put("tradeDate", date);
		// 交割日
		trdProductDeal.put("settleDate",date);
		 // 交割日
		trdProductDeal.put("payDate", date); 
		 // 计息区间开始日
		trdProductDeal.put("vdate",date);
        // 计息区间结束日
		trdProductDeal.put("mdate",  date);
		  // 本金金额
		trdProductDeal.put("prinAmt", BigDecimal.valueOf(1110));
        // 应计利息总额
		trdProductDeal.put("intAmt", BigDecimal.valueOf(110));
        // 结算金额
		trdProductDeal.put("tradeAmt", BigDecimal.valueOf(11210));
		
		 
	 
		 
		 
		 
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
		 // 权益登记日
		trdProductDealAdd.put("regDate", date);

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
