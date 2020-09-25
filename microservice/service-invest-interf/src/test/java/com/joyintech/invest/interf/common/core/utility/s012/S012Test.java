package com.joyintech.invest.interf.common.core.utility.s012;

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
 * 投资管理系统项目要素信息-发送测试
 * 
 * @program: tams-base
 * @description:
 * @author: 董志康
 * @create: 2020-02-10 11:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class S012Test {
	@Autowired
	private InterfCoreController interfCoreController;

	@Test
	public void testS012() {
		 // 交易表Map
 		Map<String, Object> dataMap = new HashMap<>();
		// 交易表Map
		 
		// 报文时间
				long date =20191101;
				dataMap.put("platDate",date);
		// pageid
		dataMap.put("pageId", "FinProjectTargetAsset002S012");
 		// 资产代码
		dataMap.put("FINPROD_ID", "JYSTOCK201908020003");
		 
		//资产名称
		dataMap.put("FINPROD_NAME", "11111");
		// 币种
		dataMap.put("CCY", "CNY");
		// 境内外
		dataMap.put("BLN_AREA", "01");
		 // 融资人
		dataMap.put("ISSUER", "2222");
        // 担保人
		dataMap.put("SPONSOR", "33333");
		   //投资顾问
		dataMap.put("INVEST_ADVISER", "WWW");
      

        // 起息日
		dataMap.put("VDATE", date);
        // 到期日
		dataMap.put("MDATE", date);
        

        // 计息区间开始日
		dataMap.put("vdate",date);
        // 计息区间结束日
		dataMap.put("mdate",date);
		 

		 
		 
		 //融资金额
		dataMap.put("ISSUE_AMT", BigDecimal.valueOf(1130));
        // 保本标识
		dataMap.put("CAPI_INCOME_FEATURE","01");
        // 结算金额
		dataMap.put("INVEST_NATURE", BigDecimal.valueOf(1133));
		// 实际授信主体
		
		dataMap.put("ACTUAL_CREDIT_PROVID", "AAAAA");
		// 资产推荐信息
		dataMap.put("RECOMMENDER_INFO", "111");
		 
		// 资产推荐方
		dataMap.put("RECOMMENDER", "123123");

		 
		 
		
		interfCoreController.send(dataMap);

	}
}
