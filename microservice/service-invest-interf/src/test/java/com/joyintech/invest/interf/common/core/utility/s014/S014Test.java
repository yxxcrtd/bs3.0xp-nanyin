package com.joyintech.invest.interf.common.core.utility.s014;

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
 * 投资管理系统项目现金流计划-发送测试
 * 
 * @program: tams-base
 * @description:
 * @author: 董志康
 * @create: 2020-02-10 11:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class S014Test {
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
		dataMap.put("pageId", "FinProjectTargetAsset002S014");
 		// 资产代码
		dataMap.put("FINPROD_ID", "JYSTOCK201908020003");
		// 市场代码
		dataMap.put("FINPROD_MARKET_ID", "JYSTOCK20190802");
		//现金流类型
		dataMap.put("FINPROD_NAME", "11111");
		 
		  
      

        // 区间开始日
		dataMap.put("VDATE", date);
        // 区间结束日
		dataMap.put("MDATE", date);
        

         
		
		 
		
		interfCoreController.send(dataMap);

	}
}
