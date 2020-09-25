package com.joyintech.invest.interf.common.core.utility.s054;

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
public class S054Test {
	@Autowired
	private InterfCoreController interfCoreController;

	@Test
	public void testS054() {
		 // 交易表Map
 		Map<String, Object> dataMap = new HashMap<>();
		// 交易表Map
		Map<String, Object> mstCounterParty = new HashMap<>();
		 
		// 报文时间
				long date =20191101;
				dataMap.put("platDate",date);
		// pageid
		dataMap.put("pageId", "MstTradeOpponent002");
 	        // 交易对手代码
		mstCounterParty.put("counterId", "AAAAA");
	        // 交易对手名称
		mstCounterParty.put("counterName","1111111");
	        // 交易对手类型
		mstCounterParty.put("counterType","02");
	        // 关联市场机构代码
		mstCounterParty.put("linkId", "JYSTOCK201908020003");
	        // 人行一级分类
		mstCounterParty.put("pTypeOne","C01");
	        // 人行二级分类
		mstCounterParty.put("pTypeTwo", "C0100");
	        // 备注
		mstCounterParty.put("remark", "交易对手信息-发送测试");
		dataMap.put("mstCounterParty", mstCounterParty);
		
		interfCoreController.send(dataMap);

	}
}
