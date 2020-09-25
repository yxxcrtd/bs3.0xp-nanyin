package com.joyintech.invest.interf.common.core.utility.s013;

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
 * 投资管理系统项目利率信息-发送测试
 * 
 * @program: tams-base
 * @description:
 * @author: 董志康
 * @create: 2020-02-10 11:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class S013Test {
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
		dataMap.put("pageId", "FinProjectTargetAsset002S013");
 		// 市场代码
		dataMap.put("FINPROD_MARKET_ID", "JYSTOCK201908020003");
		 
		//资产名称
		dataMap.put("FINPROD_NAME", "11111");
		 
		//利率信息
				dataMap.put("YIELD_INFO", "1");
				 
        // 生效日
		dataMap.put("EFF_DATE", date);
        
 
		
		 
		
		interfCoreController.send(dataMap);

	}
}
