package com.joyintech.invest.interf.common.core.utility.s055;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.joyintech.invest.interf.common.core.controller.InterfCoreController;

/**
 * 市场机构信息 -发送测试
 * 
 * @program: tams-base
 * @description:
 * @author: 王波
 * @create: 2020-02-10 11:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class S055Test {
	@Autowired
	private InterfCoreController interfCoreController;

	@Test
	public void testS055() {
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("pageId", "MstMarketInstitutions002");
		dataMap.put("platDate", Long.valueOf("20200214"));

		// 市场信息Map
		Map<String, Object> mstCustomerInfo = new HashMap<>();
		// 机构代码
		mstCustomerInfo.put("customerId", "SCJG000000000426");
		// 市场机构名称
		mstCustomerInfo.put("customerName", "测试机构");
		// 备注
		mstCustomerInfo.put("remark", "备注");
		dataMap.put("mstCustomerInfo", mstCustomerInfo);

		// 市场信息附属表
		Map<String, Object> mstCustomerInfoAddWithBLOBs = new HashMap<>();
		// 机构类型（规模）
		mstCustomerInfoAddWithBLOBs.put("partyType1", "01");
		// 机构类型（经济类型）
		mstCustomerInfoAddWithBLOBs.put("partyType2", "01");
		// 机构类型（技术领域）
		mstCustomerInfoAddWithBLOBs.put("partyType3", "01");
		// 所属行业
		mstCustomerInfoAddWithBLOBs.put("industry", "01");
		// 行业二级分类
		mstCustomerInfoAddWithBLOBs.put("industry2", "A01");
		// 组织机构代码/统一社会信用代码
		mstCustomerInfoAddWithBLOBs.put("insitituteCode", "1111");
		// 是否政府融资平台
		mstCustomerInfoAddWithBLOBs.put("isGov", "否");
		// 融资人所属省
		mstCustomerInfoAddWithBLOBs.put("province", "130000");
		// 融资人所属市
		mstCustomerInfoAddWithBLOBs.put("city", "130100");
		// 融资人所属县
		mstCustomerInfoAddWithBLOBs.put("county", "130102");
		// 控股类型
		mstCustomerInfoAddWithBLOBs.put("holidingType", "01");
		// 客户主体类型
		mstCustomerInfoAddWithBLOBs.put("customerObjType", "02");
		// 是否上市公司
		mstCustomerInfoAddWithBLOBs.put("isListedCompany", "Y");
		// 是否集团客户
		mstCustomerInfoAddWithBLOBs.put("isGroupCustomer", "Y");
		// 所属集团客户编号
		mstCustomerInfoAddWithBLOBs.put("parentCustomerId", "111");
		// 所属集团客户名称
		mstCustomerInfoAddWithBLOBs.put("parentCustomerName", "招商银行");
		// 是否民营企业
		mstCustomerInfoAddWithBLOBs.put("isPrivate", "Y");
		// 是否为逃废债企业
		mstCustomerInfoAddWithBLOBs.put("isEscapeDept", "Y");
		// 产业结构调整类型
		mstCustomerInfoAddWithBLOBs.put("structAdjustType", "02");
		// 工业转型升级标识
		mstCustomerInfoAddWithBLOBs.put("indusUpgrateType", "01");
		// 经营范围
		mstCustomerInfoAddWithBLOBs.put("busiScope", "121213");
		// 主营业务
		mstCustomerInfoAddWithBLOBs.put("mainBusi", "1212131");
		// 注册资本
		mstCustomerInfoAddWithBLOBs.put("registCapital", BigDecimal.valueOf(10000));
		// 实收资本
		mstCustomerInfoAddWithBLOBs.put("actualCapital", BigDecimal.valueOf(101000));
		dataMap.put("mstCustomerInfoAddWithBLOBs", mstCustomerInfoAddWithBLOBs);

		interfCoreController.send(dataMap);

	}
}
