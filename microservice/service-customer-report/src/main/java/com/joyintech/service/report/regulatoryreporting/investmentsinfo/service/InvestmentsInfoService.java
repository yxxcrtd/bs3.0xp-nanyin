/**  
 * @Project     service-customer-report  
 * @File        InvestmentsInfoService.java  
 * @Package     com.joyintech.service.report.regulatoryreporting.investmentsinfo.service  
 * @Version     V1.0  
 * @Date        2019年8月7日 下午1:58:19  
 * @Author      高健  
 * Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.  
 */  
    
package com.joyintech.service.report.regulatoryreporting.investmentsinfo.service;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.report.regulatoryreporting.investmentsinfo.dao.OverallInfoMapper;
import com.joyintech.service.report.regulatoryreporting.investmentsinfo.dao.TermStructureOfBusinessAndCostBenefitMapper;
import com.joyintech.service.report.regulatoryreporting.investmentsinfo.dto.OverallInfoDto;
import com.joyintech.service.report.regulatoryreporting.investmentsinfo.dao.InvestmentBusinessDetailsMapper;
import com.joyintech.service.report.regulatoryreporting.investmentsinfo.dto.InvestmentBusinessDetailsDto;



/**  
 * @Description TODO  
 * @ClassName   InvestmentsInfoService  
 * @Date        2019年8月7日 下午1:58:19  
 * @Author      高健  
 * Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.  
 */
@Service
public class InvestmentsInfoService {
	
	@Autowired
	private InvestmentBusinessDetailsMapper investmentBusinessDetailsMapper;
	@Autowired
	private TermStructureOfBusinessAndCostBenefitMapper termStructureOfBusinessAndCostBenefitMapper;
	
	@Autowired
	private OverallInfoMapper overallInfoMapper;
	// Map<单元格， 单元格需要存储数据的标识组合> 表一表六用
	private static Map<String, String> porpertyMap_1 = new HashMap<String, String>(1 << 6);
	
	// 存储属性代码和展示代码的映射关系
	private static Map<String, String> porpertyMap_2 = new HashMap<String, String>(1 << 7);
	//资产类型和属性代码映射关系（穿透用）
	private static Map<String, String> assetTypeMap_2 = new HashMap<String, String>();
	// 存储属性代码和展示代码的映射关系
	private static Map<String, String> porpertyMap_4_1 = new HashMap<String, String>();
	private static Map<String, String> porpertyMap_4_2 = new HashMap<String, String>();
	private static Map<String, String> porpertyMap_4_3 = new HashMap<String, String>();
	private static Map<String, String> porpertyMap_4_4 = new HashMap<String, String>();
	
	// 存储值相同的单元格对应情况
	private static Map<String, String> sameValueMap = new HashMap<String, String>();
	static {
		porpertyMap_1.put("t1_cur13_amt", "ASSETAMT1_15_1_1-ASSETAMT1_15_2_1-ASSETAMT1_15_3_1-ASSETAMT1_15_4_1-ASSETAMT1_15_5_1-ASSETAMT1_15_6_1");
		porpertyMap_1.put("t1_last13_amt", "ASSETAMT1_15_1_1-ASSETAMT1_15_2_1-ASSETAMT1_15_3_1-ASSETAMT1_15_4_1-ASSETAMT1_15_5_1-ASSETAMT1_15_6_1");
		porpertyMap_1.put("t1_cur14_amt", "ASSETAMT1_15_1_2-ASSETAMT1_15_2_2-ASSETAMT1_15_3_2-ASSETAMT1_15_4_2-ASSETAMT1_15_5_2-ASSETAMT1_15_6_2");
		porpertyMap_1.put("t1_last14_amt", "ASSETAMT1_15_1_2-ASSETAMT1_15_2_2-ASSETAMT1_15_3_2-ASSETAMT1_15_4_2-ASSETAMT1_15_5_2-ASSETAMT1_15_6_2");
		porpertyMap_1.put("t1_cur15_amt", "ASSETAMT1_16_8");
		porpertyMap_1.put("t1_last15_amt", "ASSETAMT1_16_8");
		porpertyMap_1.put("t1_cur19_amt", "PRODJRTYZSLCCP");
		porpertyMap_1.put("t1_last19_amt", "PRODJRTYZSLCCP");
		porpertyMap_1.put("t6_cur35_amt", "ASSETAMT1_4_13-ASSETAMT1_2_1-ASSETAMT1_2_2");
		porpertyMap_1.put("t6_last35_amt", "ASSETAMT1_4_13-ASSETAMT1_2_1-ASSETAMT1_2_2");
		porpertyMap_1.put("t6_cur36_amt", "ASSETAMT1_4_a");
		porpertyMap_1.put("t6_last36_amt", "ASSETAMT1_4_a");
		porpertyMap_1.put("t6_cur37_amt", "ASSETAMT1_4_b");
		porpertyMap_1.put("t6_last37_amt", "ASSETAMT1_4_b");
		porpertyMap_1.put("t6_cur38_amt", "ASSETAMT1_4_c");
		porpertyMap_1.put("t6_last38_amt", "ASSETAMT1_4_c");
		porpertyMap_1.put("t6_cur42_amt", "ASSETAMT1_3_1");
		porpertyMap_1.put("t6_last42_amt", "ASSETAMT1_3_1");
		porpertyMap_1.put("t6_cur43_amt", "ASSETAMT1_3_2");
		porpertyMap_1.put("t6_last43_amt", "ASSETAMT1_3_2");
		porpertyMap_1.put("t6_cur44_amt", "ASSETAMT1_1_1");
		porpertyMap_1.put("t6_last44_amt", "ASSETAMT1_1_1");
		porpertyMap_1.put("t6_cur45_amt", "ASSETAMT1_5_3");
		porpertyMap_1.put("t6_last45_amt", "ASSETAMT1_5_3");
		porpertyMap_1.put("t6_cur46_amt", "ASSETAMT1_7_11_a-ASSETAMT1_7_11_b");
		porpertyMap_1.put("t6_last46_amt", "ASSETAMT1_7_11_a-ASSETAMT1_7_11_b");
		porpertyMap_1.put("t6_cur48_amt", "ASSETAMT1_8_5");
		porpertyMap_1.put("t6_last48_amt", "ASSETAMT1_8_5");
		porpertyMap_1.put("t6_cur50_amt", "ASSETAMT1_13_7-ASSETAMT1_14_9");
		porpertyMap_1.put("t6_last50_amt", "ASSETAMT1_13_7-ASSETAMT1_14_9");
		porpertyMap_1.put("t6_cur51_amt", "ASSETAMT1_16_8");
		porpertyMap_1.put("t6_last51_amt", "ASSETAMT1_16_8");
		porpertyMap_1.put("t6_cur54_amt", "ASSETAMT1_15_1");
		porpertyMap_1.put("t6_last54_amt", "ASSETAMT1_15_1");
		porpertyMap_1.put("t6_cur55_amt", "ASSETAMT1_15_2");
		porpertyMap_1.put("t6_last55_amt", "ASSETAMT1_15_2");
		porpertyMap_1.put("t6_cur56_amt", "ASSETAMT1_15_5");
		porpertyMap_1.put("t6_last56_amt", "ASSETAMT1_15_5");
		porpertyMap_1.put("t6_cur57_amt", "ASSETAMT1_15_3-ASSETAMT1_15_4-ASSETAMT1_15_6");
		porpertyMap_1.put("t6_last57_amt", "ASSETAMT1_15_3-ASSETAMT1_15_4-ASSETAMT1_15_6");
		porpertyMap_1.put("t6_cur58_amt", "ASSETAMT1_6_4-ASSETAMT1_7_12-ASSETAMT1_1_2-ASSETAMT1_1_3-ASSETAMT1_1_4-ASSETAMT1_1_5-ASSETAMT1_9_8-ASSETAMT1_10_11-ASSETAMT1_12_7");
		porpertyMap_1.put("t6_last58_amt", "ASSETAMT1_6_4-ASSETAMT1_7_12-ASSETAMT1_1_2-ASSETAMT1_1_3-ASSETAMT1_1_4-ASSETAMT1_1_5-ASSETAMT1_9_8-ASSETAMT1_10_11-ASSETAMT1_12_7");
		
//		porpertyMap_2.put("amt1_1", "");
		porpertyMap_2.put("amt1_1_1", "AMT1_1_1");
		porpertyMap_2.put("amt1_1_2", "AMT1_1_2");
		porpertyMap_2.put("amt1_1_3", "AMT1_1_3");
		porpertyMap_2.put("amt1_1_4", "AMT1_1_4");
		porpertyMap_2.put("amt1_1_5", "AMT1_1_5");
		porpertyMap_2.put("amt1_1_6", "");
//		porpertyMap_2.put("amt1_2", "");
		porpertyMap_2.put("amt1_2_1", "AMT1_3_1");
		porpertyMap_2.put("amt1_2_2", "AMT1_3_2");
		porpertyMap_2.put("amt1_2_3", "AMT1_2_1");
		porpertyMap_2.put("amt1_2_4", "AMT1_2_2");
		porpertyMap_2.put("amt1_2_5", "");
//		porpertyMap_2.put("amt1_3", "");
		porpertyMap_2.put("amt1_3_1", "AMT1_4_1");
		porpertyMap_2.put("amt1_3_2", "AMT1_4_2");
		porpertyMap_2.put("amt1_3_3", "AMT1_4_3");
		porpertyMap_2.put("amt1_3_4", "AMT1_4_4");
		porpertyMap_2.put("amt1_3_5", "AMT1_4_5");
		porpertyMap_2.put("amt1_3_6", "AMT1_4_6");
		porpertyMap_2.put("amt1_3_7", "AMT1_4_7");
		porpertyMap_2.put("amt1_3_8", "AMT1_4_8");
		porpertyMap_2.put("amt1_3_9", "AMT1_4_9");
		porpertyMap_2.put("amt1_3_10", "AMT1_4_10");
		porpertyMap_2.put("amt1_3_11", "AMT1_4_11");
		porpertyMap_2.put("amt1_3_12", "");
		porpertyMap_2.put("amt1_4", "AMT1_5_3");
//		porpertyMap_2.put("amt1_5", "AMT1_6_1+AMT1_6_2+AMT1_6_3");
		porpertyMap_2.put("amt1_5_1", "AMT1_6_1+AMT1_6_2");
		porpertyMap_2.put("amt1_5_2", "AMT1_6_3");
//		porpertyMap_2.put("amt1_6", "");
		porpertyMap_2.put("amt1_6_1", "AMT1_7_1");
		porpertyMap_2.put("amt1_6_2", "AMT1_7_2");
		porpertyMap_2.put("amt1_6_3", "AMT1_7_3");
		porpertyMap_2.put("amt1_6_4", "AMT1_7_4");
		porpertyMap_2.put("amt1_6_5", "AMT1_7_5");
		porpertyMap_2.put("amt1_6_6", "AMT1_7_6");//-融资融券收益权
		porpertyMap_2.put("amt1_6_7", "AMT1_7_7");
		porpertyMap_2.put("amt1_6_8", "AMT1_7_8");
		porpertyMap_2.put("amt1_6_9", "AMT1_7_9");
		porpertyMap_2.put("amt1_6_10", "AMT1_7_10");
		porpertyMap_2.put("amt1_6_11", "");
		porpertyMap_2.put("amt1_6_12", "AMT1_7_11-AMT1_7_11_a-AMT1_7_11_b");
//		porpertyMap_2.put("amt1_7", "");
		porpertyMap_2.put("amt1_7_1", "AMT1_8_1");
		porpertyMap_2.put("amt1_7_2", "AMT1_8_2");
		porpertyMap_2.put("amt1_7_2_1", "");
		porpertyMap_2.put("amt1_7_2_2", "");
		porpertyMap_2.put("amt1_7_3", "AMT1_8_3");
		porpertyMap_2.put("amt1_7_4", "AMT1_7_11_a+AMT1_7_11_b");
		porpertyMap_2.put("amt1_7_5", "");
		porpertyMap_2.put("amt1_7_6", "");
		porpertyMap_2.put("amt1_7_7", "AMT1_8_4");
//		porpertyMap_2.put("amt1_8", "");
		porpertyMap_2.put("amt1_8_1", "AMT1_13_1");
		porpertyMap_2.put("amt1_8_2", "AMT1_13_2");
		porpertyMap_2.put("amt1_8_3", "AMT1_13_3");
		porpertyMap_2.put("amt1_8_4", "AMT1_13_4");
		porpertyMap_2.put("amt1_8_5", "AMT1_13_5");
		porpertyMap_2.put("amt1_8_6", "AMT1_13_6");
//		porpertyMap_2.put("amt1_9", "");
		porpertyMap_2.put("amt1_9_1", "");
		porpertyMap_2.put("amt1_9_2", "");
		porpertyMap_2.put("amt1_9_3", "");
		porpertyMap_2.put("amt1_9_4", "");
		porpertyMap_2.put("amt1_9_5", "");
		porpertyMap_2.put("amt1_9_6", "");
		porpertyMap_2.put("amt1_9_7", "");
		porpertyMap_2.put("amt1_9_8", "");
		porpertyMap_2.put("amt1_9_9", "");
//		porpertyMap_2.put("amt1_10", "");
		porpertyMap_2.put("amt1_10_1", "");
		porpertyMap_2.put("amt1_10_2", "");
		porpertyMap_2.put("amt1_10_3", "");
		porpertyMap_2.put("amt1_10_4", "");
		porpertyMap_2.put("amt1_10_5", "");
		porpertyMap_2.put("amt1_11", "AMT1_9_8");
		porpertyMap_2.put("amt1_12", "AMT1_10_11");
		porpertyMap_2.put("amt1_13", "AMT1_11_4");
		porpertyMap_2.put("amt1_14", "AMT1_12_7");
		porpertyMap_2.put("amt1_14", "AMT1_12_7");
		porpertyMap_2.put("amt1_15", "AMT1_16_8");
		porpertyMap_2.put("amt1_16", "");
		porpertyMap_2.put("amt1_17", "AMT2_1_1");
		porpertyMap_2.put("amt1_18", "AMT2_1_2");
		porpertyMap_2.put("amt1_19", "");
		porpertyMap_2.put("amt1_20", "");
		
		assetTypeMap_2.put("2101", "amt1_4");
		assetTypeMap_2.put("2201", "amt1_5_1");
		assetTypeMap_2.put("2299", "amt1_5_2");
		assetTypeMap_2.put("1106", "amt1_8_1");
		assetTypeMap_2.put("2401", "amt1_8_2");
		assetTypeMap_2.put("1303", "amt1_8_3");
		assetTypeMap_2.put("2403", "amt1_8_4");
		assetTypeMap_2.put("2402", "amt1_8_5");
		assetTypeMap_2.put("2499", "amt1_8_6");
		assetTypeMap_2.put("2601", "amt1_9_1");
		assetTypeMap_2.put("2607", "amt1_9_1");
		assetTypeMap_2.put("2602", "amt1_9_2");
		assetTypeMap_2.put("2604", "amt1_9_4");
		assetTypeMap_2.put("2616", "amt1_9_5");
		assetTypeMap_2.put("2618", "amt1_9_5");
		assetTypeMap_2.put("2619", "amt1_9_5");
		assetTypeMap_2.put("2620", "amt1_9_5");
		assetTypeMap_2.put("2610", "amt1_9_7");
		assetTypeMap_2.put("2611", "amt1_9_8");
		assetTypeMap_2.put("2605", "amt1_9_9");
		assetTypeMap_2.put("2606", "amt1_9_9");
		assetTypeMap_2.put("2617", "amt1_9_9");
		assetTypeMap_2.put("2614", "amt1_9_9");
		assetTypeMap_2.put("2699", "amt1_9_9");
		assetTypeMap_2.put("2613", "amt1_10_2");
		assetTypeMap_2.put("2612", "amt1_10_4");
		assetTypeMap_2.put("07", "amt1_11");
		assetTypeMap_2.put("08", "amt1_12");
		assetTypeMap_2.put("09", "amt1_12");
		assetTypeMap_2.put("10", "amt1_12");
		assetTypeMap_2.put("11", "amt1_12");
		assetTypeMap_2.put("1599", "amt1_12");
		assetTypeMap_2.put("13", "amt1_13");
		assetTypeMap_2.put("18", "amt1_14");
		assetTypeMap_2.put("16", "amt1_15");
		assetTypeMap_2.put("1901", "amt1_17");
		assetTypeMap_2.put("1902", "amt1_18");
		
		porpertyMap_4_1.put("01", "t4_amt1_1");
		porpertyMap_4_1.put("02", "t4_amt1_2");
		porpertyMap_4_1.put("03", "t4_amt1_3");
		porpertyMap_4_1.put("04", "t4_amt1_4");
		porpertyMap_4_1.put("05", "t4_amt1_5");
		porpertyMap_4_1.put("06", "t4_amt1_6");
		porpertyMap_4_1.put("07", "t4_amt1_7");
		
		porpertyMap_4_2.put("01", "t4_amt2_1");
		porpertyMap_4_2.put("02", "t4_amt2_2");
		porpertyMap_4_2.put("03", "t4_amt2_3");
		porpertyMap_4_2.put("04", "t4_amt2_4");
		porpertyMap_4_2.put("05", "t4_amt2_5");
		porpertyMap_4_2.put("06", "t4_amt2_6");
		porpertyMap_4_2.put("07", "t4_amt2_7");
		
		porpertyMap_4_3.put("01", "t4_amt3_1");
		porpertyMap_4_3.put("02", "t4_amt3_2");
		porpertyMap_4_3.put("03", "t4_amt3_3");
		porpertyMap_4_3.put("04", "t4_amt3_4");
		porpertyMap_4_3.put("05", "t4_amt3_5");
		porpertyMap_4_3.put("06", "t4_amt3_6");
		porpertyMap_4_3.put("07", "t4_amt3_7");
		
		porpertyMap_4_4.put("1901", "t4_amt4_1");
		porpertyMap_4_4.put("1902", "t4_amt4_2");
		
		// 表六C60 = 表一E12
		sameValueMap.put("t6_cur60_amt", "t1_cur12_amt");
		// 表六D60 = 表一F12
		sameValueMap.put("t6_last60_amt", "t1_last12_amt");
		// 表六C61 = 表一E13
		sameValueMap.put("t6_cur61_amt", "t1_cur13_amt");
		// 表六D61 = 表一F13
		sameValueMap.put("t6_last61_amt", "t1_last13_amt");
		// 表六C62 = 表一E14
		sameValueMap.put("t6_cur62_amt", "t1_cur14_amt");
		// 表六D62 = 表一F14
		sameValueMap.put("t6_last62_amt", "t1_last14_amt");
		// 表六C63 = 表一E15
		sameValueMap.put("t6_cur63_amt", "t1_cur15_amt");
		// 表六D63 = 表一F15
		sameValueMap.put("t6_last63_amt", "t1_last16_amt");
	}

	/**  
	 * @Description 查询导出数据  
	 * @Author      高健  
	 * @Date        2019年8月7日 下午1:59:46  
	 * @param @param pageParam
	 * @param @return 参数  
	 * @return Map<Object,Object> 返回类型   
	 * @throws  
	 */  
	public Map<String, Object> createData(Map<String, Object> pageParam) {
		Map<String,Object> data  = new HashMap<String,Object>();
		List<InvestmentBusinessDetailsDto> investmentBusinessDetailsDtoList = new ArrayList<InvestmentBusinessDetailsDto>();
		investmentBusinessDetailsDtoList.addAll(queryTable1And6Info(pageParam));
		investmentBusinessDetailsDtoList.addAll(getInvestmentBusinessDetails(pageParam));
		investmentBusinessDetailsDtoList.addAll(queryTable3OtherInfo(pageParam));
		investmentBusinessDetailsDtoList.addAll(queryTable4Info(pageParam));
		for(InvestmentBusinessDetailsDto detail : investmentBusinessDetailsDtoList){
			if (data.get(detail.getPropertycode()) != null) {
				// 相同key值需要把数据合并
				detail = mergeSameDto((InvestmentBusinessDetailsDto) data.get(detail.getPropertycode()) , detail);
			}
			data.put(detail.getPropertycode(), divideBillion(detail));
		}
		return data;
	}
	
	
	
	/**  
	 * @Description 查询表一数据并组装为Map  
	 * @Author      高健  
	 * @Date        2019年8月7日 下午3:41:08  
	 * @param @param pageParam
	 * @param @return 参数  
	 * @return Map<Object,Object> 返回类型   
	 * @throws  
	 */  
	private Map<String, BigDecimal> createTable1Data(Map<String, Object> pageParam) {
		Map<String, BigDecimal> resultMap = new HashMap<String, BigDecimal>(1 << 4);
		// 导出年份
		String exportYear = (String) pageParam.get("exportYear");
		// 导出季度
		String exportQuarter = (String) pageParam.get("exportQuarter");
		// 本季末期数
		String termId = exportYear + exportQuarter;
		Map<String, Object> paraMap = new HashMap<String, Object>();
		// 本季末参数
		paraMap.put("termId", termId);
		List<OverallInfoDto> overallInfoList = overallInfoMapper.queryOverallInfoList(paraMap);
		OverallInfoDto overallInfoDto = overallInfoMapper.queryMarginTrading(paraMap);
		// 上季末参数
		String lastTermId = getLastQuarter(termId);
		paraMap.put("termId", lastTermId);
		List<OverallInfoDto> lastOverallInfoList = overallInfoMapper.queryOverallInfoList(paraMap);
		OverallInfoDto lastOverallInfoDto = overallInfoMapper.queryMarginTrading(paraMap);
		// 本季末已组装数据
		Map<String, BigDecimal> currMap = assembleMap(overallInfoList);
		// 上季末已组装数据
		Map<String, BigDecimal> lastMap = assembleMap(lastOverallInfoList);
		// 表一E12单元格直接投资为减法运算，单独提取出来
		resultMap.put("t1_cur12_amt", queryMoney(currMap, "ASSETAMT1_17")  
				.subtract(queryMoney(currMap, "ASSETAMT1_16_8"))
				.subtract(queryMoney(currMap, "ASSETAMT1_15_7")));
		// 表一F12单元格直接投资为减法运算，单独提取出来
		resultMap.put("t1_last12_amt", queryMoney(lastMap, "ASSETAMT1_17")  
				.subtract(queryMoney(lastMap, "ASSETAMT1_16_8"))
				.subtract(queryMoney(lastMap, "ASSETAMT1_15_7")));
		// 表六C47数据
		resultMap.put("t6_cur47_amt", overallInfoDto.getBalance());
		// 表六D47数据
		resultMap.put("t6_last47_amt", lastOverallInfoDto.getBalance());
		
		// 表一、表六其余单元格赋值
		for (String key : porpertyMap_1.keySet()) {
			String[] valueList = porpertyMap_1.get(key).split("-");
			int length = valueList.length;
			BigDecimal money = BigDecimal.ZERO;
			// 本季末数额
			if (key.contains("cur")) {
				for (int i = 0; i < length; i ++) {
					money = money.add(queryMoney(currMap, valueList[i]));
				}
				// 上季末数额
			} else if (key.contains("last")) {
				for (int i = 0; i < length; i ++) {
					money = money.add(queryMoney(lastMap, valueList[i]));
				}
			}
			resultMap.put(key, money);
		}
		// 表六C41数据
		resultMap.put("t6_cur41_amt", resultMap.get("t6_cur42_amt")
				.add(resultMap.get("t6_cur43_amt"))
				.add(resultMap.get("t6_cur44_amt")));
		// 表D41数据
		resultMap.put("t6_last41_amt", resultMap.get("t6_last42_amt")
				.add(resultMap.get("t6_last43_amt"))
				.add(resultMap.get("t6_last44_amt")));
		// 表六C58重新赋值
		resultMap.put("t6_cur58_amt", resultMap.get("t6_cur58_amt")
				.subtract(resultMap.get("t6_cur46_amt"))
				.subtract(resultMap.get("t6_cur47_amt")));
		// 表六D58重新赋值
		resultMap.put("t6_last58_amt", resultMap.get("t6_last58_amt")
				.subtract(resultMap.get("t6_last46_amt"))
				.subtract(resultMap.get("t6_last47_amt")));
		// 表六C59赋值
		resultMap.put("t6_cur59_amt", resultMap.get("t6_cur35_amt")
				.add(resultMap.get("t6_cur41_amt"))
				.add(resultMap.get("t6_cur45_amt"))
				.add(resultMap.get("t6_cur46_amt"))
				.add(resultMap.get("t6_cur47_amt"))
				.add(resultMap.get("t6_cur48_amt"))
				.add(resultMap.get("t6_cur50_amt"))
				.add(resultMap.get("t6_cur51_amt"))
				.add(resultMap.get("t6_cur54_amt"))
				.add(resultMap.get("t6_cur55_amt"))
				.add(resultMap.get("t6_cur56_amt"))
				.add(resultMap.get("t6_cur57_amt"))
				.add(resultMap.get("t6_cur58_amt")));
		// 表六D59赋值
		resultMap.put("t6_last59_amt", resultMap.get("t6_last35_amt")
				.add(resultMap.get("t6_last41_amt"))
				.add(resultMap.get("t6_last45_amt"))
				.add(resultMap.get("t6_last46_amt"))
				.add(resultMap.get("t6_last47_amt"))
				.add(resultMap.get("t6_last48_amt"))
				.add(resultMap.get("t6_last50_amt"))
				.add(resultMap.get("t6_last51_amt"))
				.add(resultMap.get("t6_last54_amt"))
				.add(resultMap.get("t6_last55_amt"))
				.add(resultMap.get("t6_last56_amt"))
				.add(resultMap.get("t6_last57_amt"))
				.add(resultMap.get("t6_last58_amt")));
		// 值相同单元格赋值
		for (String key : sameValueMap.keySet()) {
			resultMap.put(key, resultMap.get(sameValueMap.get(key)));
		}
		return resultMap;
	}
	
	
	/**  
	 * @Description 获取上季末月份  
	 * @Author      高健  
	 * @Date        2019年8月7日 下午4:02:30  
	 * @param @param selMonth yyyyMM
	 * @param @return 参数  
	 * @return String 返回类型   yyyyMM
	 * @throws  
	 */  
	private String getLastQuarter(String selMonth) {
		String termId = null;
		try {
			Date d = DateUtils.parseDate(selMonth, new String[] {"yyyyMM"});
			Calendar calendar = Calendar.getInstance();
		    calendar.setTime(d);
		    calendar.add(Calendar.MONTH, -3);
		    termId = DateUtil.format(calendar.getTime(), "yyyyMM");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return termId;
	}
	
	/**  
	 * @Description TODO  
	 * @Author      高健  
	 * @Date        2019年8月7日 下午4:06:40  
	 * @param @param overallInfoList
	 * @param @return 参数  
	 * @return Map<String,BigDecimal> 返回类型   
	 * @throws  
	 */  
	private Map<String, BigDecimal> assembleMap(List<OverallInfoDto> overallInfoList) {
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>(1 << 4);
		for (OverallInfoDto overallInfoDto : overallInfoList) {
			// 资管机构主动管理产品类只需要属性代码作为唯一标识
//			if (StringUtil.equals("AMT1_16_8", overallInfoDto.getPropertyCode())) {
//				map.put(overallInfoDto.getPropertyCode(), overallInfoDto.getBalance());
//			} else {
				map.put(overallInfoDto.getReportType() + overallInfoDto.getPropertyCode(), overallInfoDto.getBalance());
//			}
		}
		return map;
	}
	/**
	 * 查询表二数据
	 * @param pageParam
	 * @return 
	 */
	private List<InvestmentBusinessDetailsDto> getInvestmentBusinessDetails(Map<String, Object> pageParam) {
		SimpleDateFormat sdf =new SimpleDateFormat("yyyyMM");
		// 导出年份
		String exportYear = (String) pageParam.get("exportYear");
		// 导出季度
		String exportQuarter = (String) pageParam.get("exportQuarter");
		String cdateStr = exportYear + exportQuarter+"01";
		Date cdate=new Date();
		try {
			cdate = DateUtil.parseDate(cdateStr,"yyyyMMdd");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(cdate);
	    calendar.add(Calendar.MONTH, 1);
	    calendar.set(Calendar.DAY_OF_MONTH, 0);
	    //取到本季末
	    Date endDate=calendar.getTime();
		calendar.add(Calendar.MONTH, -3);
		calendar.add(Calendar.MONTH, 1);
	    calendar.set(Calendar.DAY_OF_MONTH, 0);
		 //取到上季末
		Date lastEndDate=calendar.getTime();
		String termid=sdf.format(cdate);
		calendar.setTime(cdate);
		calendar.add(Calendar.MONTH, -3);
		Date lastDate=calendar.getTime();
		String lastTermid=sdf.format(lastDate);
		List<InvestmentBusinessDetailsDto> allList=investmentBusinessDetailsMapper.selectAllMonthDetailAssetByTermid(termid, lastTermid);
		Map<String,InvestmentBusinessDetailsDto> dtoMap=new HashMap<String,InvestmentBusinessDetailsDto>();
		for(InvestmentBusinessDetailsDto single:allList){
			dtoMap.put(single.getPropertycode(), single);
		}
		List<InvestmentBusinessDetailsDto> otherList=investmentBusinessDetailsMapper.selectOtherData(cdate, lastDate, termid, lastTermid);
		Map<String,InvestmentBusinessDetailsDto> otherdtoMap=new HashMap<String,InvestmentBusinessDetailsDto>();
		for(InvestmentBusinessDetailsDto single:otherList){
			otherdtoMap.put(single.getPropertycode(), single);
		}
		 //查询资管计划
		List<InvestmentBusinessDetailsDto> plans=investmentBusinessDetailsMapper.selectAssetsManagementPlanData(endDate);
		Map<String,InvestmentBusinessDetailsDto> bottomAssetsMap=new HashMap<String,InvestmentBusinessDetailsDto>();
		for(InvestmentBusinessDetailsDto single:plans){
			if(single.getAssetcode()!=null){
				List<InvestmentBusinessDetailsDto> dtos =investmentBusinessDetailsMapper.selectBottomAssetsData(endDate, single.getAssetcode());
				BigDecimal allnum=BigDecimal.ZERO;
				for(InvestmentBusinessDetailsDto dto:dtos){
					allnum=allnum.add(dto.getSessionendNum());
				}
				for(InvestmentBusinessDetailsDto dto:dtos){
					dto.setSessionendNum(dto.getSessionendNum().divide(allnum,17,RoundingMode.HALF_UP).multiply(single.getSessionendNum()));
					if(bottomAssetsMap.containsKey(dto.getAssettype2())){
						InvestmentBusinessDetailsDto mapdto=bottomAssetsMap.get(dto.getAssettype2());
						mapdto.setSessionendNum(mapdto.getSessionendNum().add(dto.getSessionendNum()));
					}else{
						bottomAssetsMap.put(dto.getAssettype2(), dto);
					}
				}
				
			}
		}
		List<InvestmentBusinessDetailsDto> lastplans=investmentBusinessDetailsMapper.selectAssetsManagementPlanData(lastEndDate);
		for(InvestmentBusinessDetailsDto single:lastplans){
			if(single.getAssetcode()!=null){
				List<InvestmentBusinessDetailsDto> dtos =investmentBusinessDetailsMapper.selectBottomAssetsData(lastEndDate, single.getAssetcode());
				BigDecimal allnum=BigDecimal.ZERO;
				for(InvestmentBusinessDetailsDto dto:dtos){
					allnum=allnum.add(dto.getSessionendNum());
				}
				for(InvestmentBusinessDetailsDto dto:dtos){
					dto.setLastSessionendNum(dto.getSessionendNum().divide(allnum,17,RoundingMode.HALF_UP).multiply(single.getSessionendNum()));
					if(bottomAssetsMap.containsKey(dto.getAssettype2())){
						InvestmentBusinessDetailsDto mapdto=bottomAssetsMap.get(dto.getAssettype2());
						if(mapdto.getLastSessionendNum()!=null) {
						    mapdto.setLastSessionendNum(mapdto.getLastSessionendNum().add(dto.getLastSessionendNum()));
						}else {
						    mapdto.setLastSessionendNum(dto.getLastSessionendNum());
						}
					}else{
						dto.setSessionendNum(null);
						bottomAssetsMap.put(dto.getAssettype2(), dto);
					}
				}
				
			}
		}
		Map<String,InvestmentBusinessDetailsDto> bottomResultMap=new HashMap<String,InvestmentBusinessDetailsDto>();
		for(String key:bottomAssetsMap.keySet()){
			InvestmentBusinessDetailsDto value=bottomAssetsMap.get(key);
			if("1002".equals(key)||"1003".equals(key)||"1004".equals(key)||"1005".equals(key)){
				if(bottomResultMap.containsKey("amt1_1_6")){
					InvestmentBusinessDetailsDto dto=bottomResultMap.get("amt1_1_6");
					dto.setSessionendNum(dto.getSessionendNum().add(value.getSessionendNum()));
					if(value.getLastSessionendNum()!=null) {
					    if(dto.getLastSessionendNum()!=null) {
					        dto.setLastSessionendNum(dto.getLastSessionendNum().add(value.getLastSessionendNum()));
					    }else {
					        dto.setLastSessionendNum(value.getLastSessionendNum());
					    }
					}
				}else{
					InvestmentBusinessDetailsDto dto=new InvestmentBusinessDetailsDto();
					dto.setSessionendNum(value.getSessionendNum());
					dto.setLastSessionendNum(value.getLastSessionendNum());
					dto.setPropertycode("amt1_1_6");
					bottomResultMap.put("amt1_1_6", dto);
				}
			}else if("1107".equals(key)||"1108".equals(key)||"2501".equals(key)||"2502".equals(key)){
				if(bottomResultMap.containsKey("amt1_2_5")){
					InvestmentBusinessDetailsDto dto=bottomResultMap.get("amt1_2_5");
					dto.setSessionendNum(dto.getSessionendNum().add(value.getSessionendNum()));
					if(value.getLastSessionendNum()!=null) {
                        if(dto.getLastSessionendNum()!=null) {
                            dto.setLastSessionendNum(dto.getLastSessionendNum().add(value.getLastSessionendNum()));
                        }else {
                            dto.setLastSessionendNum(value.getLastSessionendNum());
                        }
                    }
				}else{
					InvestmentBusinessDetailsDto dto=new InvestmentBusinessDetailsDto();
					dto.setSessionendNum(value.getSessionendNum());
					dto.setLastSessionendNum(value.getLastSessionendNum());
					dto.setPropertycode("amt1_2_5");
					bottomResultMap.put("amt1_2_5", dto);
				}
			}else if("1101".equals(key)||"1110".equals(key)||"1109".equals(key)||"1102".equals(key)
					||"1111".equals(key)||"1112".equals(key)||"1113".equals(key)||"1114".equals(key)
					||"1115".equals(key)||"1116".equals(key)||"1117".equals(key)){
				if(bottomResultMap.containsKey("amt1_3_12")){
					InvestmentBusinessDetailsDto dto=bottomResultMap.get("amt1_3_12");
					dto.setSessionendNum(dto.getSessionendNum().add(value.getSessionendNum()));
					if(value.getLastSessionendNum()!=null) {
                        if(dto.getLastSessionendNum()!=null) {
                            dto.setLastSessionendNum(dto.getLastSessionendNum().add(value.getLastSessionendNum()));
                        }else {
                            dto.setLastSessionendNum(value.getLastSessionendNum());
                        }
                    }
				}else{
					InvestmentBusinessDetailsDto dto=new InvestmentBusinessDetailsDto();
					dto.setSessionendNum(value.getSessionendNum());
					dto.setLastSessionendNum(value.getLastSessionendNum());
					dto.setPropertycode("amt1_3_12");
					bottomResultMap.put("amt1_3_12", dto);
				}
			}else if("1201".equals(key)||"1210".equals(key)||"1202".equals(key)||"1203".equals(key)
					||"1204".equals(key)||"1205".equals(key)||"1206".equals(key)||"1207".equals(key)
					||"1208".equals(key)||"1209".equals(key)||"1299".equals(key)){
				if(bottomResultMap.containsKey("amt1_6_11")){
					InvestmentBusinessDetailsDto dto=bottomResultMap.get("amt1_6_11");
					dto.setSessionendNum(dto.getSessionendNum().add(value.getSessionendNum()));
					if(value.getLastSessionendNum()!=null) {
                        if(dto.getLastSessionendNum()!=null) {
                            dto.setLastSessionendNum(dto.getLastSessionendNum().add(value.getLastSessionendNum()));
                        }else {
                            dto.setLastSessionendNum(value.getLastSessionendNum());
                        }
                    }
				}else{
					InvestmentBusinessDetailsDto dto=new InvestmentBusinessDetailsDto();
					dto.setSessionendNum(value.getSessionendNum());
					dto.setLastSessionendNum(value.getLastSessionendNum());
					dto.setPropertycode("amt1_6_11");
					bottomResultMap.put("amt1_6_11", dto);
				}
			}else if("1301".equals(key)||"1305".equals(key)||"1302".equals(key)||"1399".equals(key)){
				if(bottomResultMap.containsKey("amt1_7_6")){
					InvestmentBusinessDetailsDto dto=bottomResultMap.get("amt1_7_6");
					dto.setSessionendNum(dto.getSessionendNum().add(value.getSessionendNum()));
					if(value.getLastSessionendNum()!=null) {
                        if(dto.getLastSessionendNum()!=null) {
                            dto.setLastSessionendNum(dto.getLastSessionendNum().add(value.getLastSessionendNum()));
                        }else {
                            dto.setLastSessionendNum(value.getLastSessionendNum());
                        }
                    }
				}else{
					InvestmentBusinessDetailsDto dto=new InvestmentBusinessDetailsDto();
					dto.setSessionendNum(value.getSessionendNum());
					dto.setLastSessionendNum(value.getLastSessionendNum());
					dto.setPropertycode("amt1_7_6");
					bottomResultMap.put("amt1_7_6", dto);
				}
			}else {
				if(assetTypeMap_2.containsKey(value.getAssettype())){
					if(bottomResultMap.containsKey(assetTypeMap_2.get(value.getAssettype()))){
						InvestmentBusinessDetailsDto dto=bottomResultMap.get(assetTypeMap_2.get(value.getAssettype()));
						dto.setSessionendNum(dto.getSessionendNum().add(value.getSessionendNum()));
						if(value.getLastSessionendNum()!=null) {
	                        if(dto.getLastSessionendNum()!=null) {
	                            dto.setLastSessionendNum(dto.getLastSessionendNum().add(value.getLastSessionendNum()));
	                        }else {
	                            dto.setLastSessionendNum(value.getLastSessionendNum());
	                        }
	                    }
					}else{
						InvestmentBusinessDetailsDto dto=new InvestmentBusinessDetailsDto();
						dto.setSessionendNum(value.getSessionendNum());
						dto.setLastSessionendNum(value.getLastSessionendNum());
						dto.setPropertycode(assetTypeMap_2.get(value.getAssettype()));
						bottomResultMap.put(assetTypeMap_2.get(value.getAssettype()), dto);
					}
				}
				if(assetTypeMap_2.containsKey(key)){
					if(bottomResultMap.containsKey(assetTypeMap_2.get(key))){
						InvestmentBusinessDetailsDto dto=bottomResultMap.get(assetTypeMap_2.get(key));
						dto.setSessionendNum(dto.getSessionendNum().add(value.getSessionendNum()));
						if(value.getLastSessionendNum()!=null) {
	                        if(dto.getLastSessionendNum()!=null) {
	                            dto.setLastSessionendNum(dto.getLastSessionendNum().add(value.getLastSessionendNum()));
	                        }else {
	                            dto.setLastSessionendNum(value.getLastSessionendNum());
	                        }
	                    }
					}else{
						InvestmentBusinessDetailsDto dto=new InvestmentBusinessDetailsDto();
						dto.setSessionendNum(value.getSessionendNum());
						dto.setLastSessionendNum(value.getLastSessionendNum());
						dto.setPropertycode(assetTypeMap_2.get(key));
						bottomResultMap.put(assetTypeMap_2.get(key), dto);
					}
				}
			}
		}
		List<InvestmentBusinessDetailsDto> resultList=new ArrayList<InvestmentBusinessDetailsDto>();
		for(String key:porpertyMap_2.keySet()){
			String value=porpertyMap_2.get(key);
			if(StringUtil.isNotEmpty(value)){
				if(value.contains("+")){
					InvestmentBusinessDetailsDto model=new InvestmentBusinessDetailsDto();
					model.setPropertycode(key);
					model.setSessionendNum(BigDecimal.ZERO);
					model.setLastSessionendNum(BigDecimal.ZERO);
					model.setNonperformingAssetNum(BigDecimal.ZERO);
					model.setLastNonperformingAssetNum(BigDecimal.ZERO);
					String [] valueStrs=value.split("\\+");
					for(String va:valueStrs){
						if(dtoMap.containsKey(va)){
							model.setSessionendNum(model.getSessionendNum().add(dtoMap.get(va).getSessionendNum()));
							model.setLastSessionendNum(model.getLastSessionendNum().add(dtoMap.get(va).getLastSessionendNum()));
							model.setNonperformingAssetNum(model.getNonperformingAssetNum().add(dtoMap.get(va).getNonperformingAssetNum()));
							model.setLastNonperformingAssetNum(model.getLastNonperformingAssetNum().add(dtoMap.get(va).getLastNonperformingAssetNum()));
						}
					}
					if(bottomResultMap.containsKey(key)){
						model.setSessionendNum(model.getSessionendNum().add(bottomResultMap.get(key).getSessionendNum()));
						if(bottomResultMap.get(key).getLastSessionendNum()!=null) {
                            if(model.getLastSessionendNum()!=null) {
                                model.setLastSessionendNum(model.getLastSessionendNum().add(bottomResultMap.get(key).getLastSessionendNum()));
                            }else {
                                model.setLastSessionendNum(bottomResultMap.get(key).getLastSessionendNum()); 
                            }
                        }
					}
					resultList.add(model);
				}else if(value.contains("-")){
					InvestmentBusinessDetailsDto model=new InvestmentBusinessDetailsDto();
					model.setPropertycode(key);
					model.setSessionendNum(BigDecimal.ZERO);
					model.setLastSessionendNum(BigDecimal.ZERO);
					model.setNonperformingAssetNum(BigDecimal.ZERO);
					model.setLastNonperformingAssetNum(BigDecimal.ZERO);
					String [] valueStrs=value.split("-");
					for(int i=0;i<valueStrs.length; i++){
						String va=valueStrs[i];
						if(dtoMap.containsKey(va)){
							if(i==0){
								model.setSessionendNum(model.getSessionendNum().add(dtoMap.get(va).getSessionendNum()));
								model.setLastSessionendNum(model.getLastSessionendNum().add(dtoMap.get(va).getLastSessionendNum()));
								model.setNonperformingAssetNum(model.getNonperformingAssetNum().add(dtoMap.get(va).getNonperformingAssetNum()));
								model.setLastNonperformingAssetNum(model.getLastNonperformingAssetNum().add(dtoMap.get(va).getLastNonperformingAssetNum()));
							}else{
								model.setSessionendNum(model.getSessionendNum().subtract(dtoMap.get(va).getSessionendNum()));
								model.setLastSessionendNum(model.getLastSessionendNum().subtract(dtoMap.get(va).getLastSessionendNum()));
								model.setNonperformingAssetNum(model.getNonperformingAssetNum().subtract(dtoMap.get(va).getNonperformingAssetNum()));
								model.setLastNonperformingAssetNum(model.getLastNonperformingAssetNum().subtract(dtoMap.get(va).getLastNonperformingAssetNum()));
							}
						}
					}
					if(bottomResultMap.containsKey(key)){
						model.setSessionendNum(model.getSessionendNum().add(bottomResultMap.get(key).getSessionendNum()));
						if(bottomResultMap.get(key).getLastSessionendNum()!=null) {
						    if(model.getLastSessionendNum()!=null) {
						        model.setLastSessionendNum(model.getLastSessionendNum().add(bottomResultMap.get(key).getLastSessionendNum()));
						    }else {
						        model.setLastSessionendNum(bottomResultMap.get(key).getLastSessionendNum()); 
						    }
						}
					}
					resultList.add(model);
				}else{
					if(dtoMap.containsKey(value)){
						InvestmentBusinessDetailsDto model=dtoMap.get(value);
						model.setPropertycode(key);
						if(key.equals("amt1_6_6")){
							if(otherdtoMap.containsKey(key)){
								InvestmentBusinessDetailsDto othermodel=otherdtoMap.get(key);
								model.setSessionendNum(model.getSessionendNum().subtract(othermodel.getSessionendNum()==null?BigDecimal.ZERO:othermodel.getSessionendNum()));
								model.setLastSessionendNum(model.getLastSessionendNum().subtract(othermodel.getLastSessionendNum()==null?BigDecimal.ZERO:othermodel.getLastSessionendNum()));
							}
						}
						if(bottomResultMap.containsKey(key)){
							model.setSessionendNum(model.getSessionendNum().add(bottomResultMap.get(key).getSessionendNum()));
							if(bottomResultMap.get(key).getLastSessionendNum()!=null) {
							    if(model.getLastSessionendNum()!=null) {
							        model.setLastSessionendNum(model.getLastSessionendNum().add(bottomResultMap.get(key).getLastSessionendNum()));
							    }else {
							        model.setLastSessionendNum(bottomResultMap.get(key).getLastSessionendNum()); 
							    }
							}
						}
						resultList.add(model);
					}else {
					    if(bottomResultMap.containsKey(key)){
    					    InvestmentBusinessDetailsDto model=new InvestmentBusinessDetailsDto();
    	                    model.setPropertycode(key);
    	                    model.setSessionendNum(BigDecimal.ZERO);
    	                    model.setLastSessionendNum(BigDecimal.ZERO);
    	                    model.setNonperformingAssetNum(BigDecimal.ZERO);
    	                    model.setLastNonperformingAssetNum(BigDecimal.ZERO);
    					        model.setSessionendNum(model.getSessionendNum().add(bottomResultMap.get(key).getSessionendNum()));
    					        if(bottomResultMap.get(key).getLastSessionendNum()!=null) {
    					            if(model.getLastSessionendNum()!=null) {
    					                model.setLastSessionendNum(model.getLastSessionendNum().add(bottomResultMap.get(key).getLastSessionendNum()));
    					            }else {
    					                model.setLastSessionendNum(bottomResultMap.get(key).getLastSessionendNum()); 
    					            }
    					        }
    					        resultList.add(model);
					    }
					}
				}
			}else{
			 if(key.equals("amt1_1_6")){
				 if(bottomResultMap.containsKey("amt1_1_6")){
					 InvestmentBusinessDetailsDto dto= bottomResultMap.get("amt1_1_6");
					 dto.setPropertycode(key);
					 resultList.add(dto);
				 }
			 }else if(key.equals("amt1_2_5")){
				 if(bottomResultMap.containsKey("amt1_2_5")){
					 InvestmentBusinessDetailsDto dto= bottomResultMap.get("amt1_2_5");
					 dto.setPropertycode(key);
					 resultList.add(dto);
				 }
			 }else if(key.equals("amt1_3_12")){
				 if(bottomResultMap.containsKey("amt1_3_12")){
					 InvestmentBusinessDetailsDto dto= bottomResultMap.get("amt1_3_12");
					 dto.setPropertycode(key);
					 resultList.add(dto);
				 }
			 }else if(key.equals("amt1_6_11")){
				 if(bottomResultMap.containsKey("amt1_6_11")){
					 InvestmentBusinessDetailsDto dto= bottomResultMap.get("amt1_6_11");
					 dto.setPropertycode(key);
					 resultList.add(dto);
				 }
			 }else if(key.equals("amt1_7_6")){
				 if(bottomResultMap.containsKey("amt1_7_6")){
					 InvestmentBusinessDetailsDto dto= bottomResultMap.get("amt1_7_6");
					 dto.setPropertycode(key);
					 resultList.add(dto);
				 }
			 }else if(key.equals("amt1_7_5")){
				 if(otherdtoMap.containsKey("amt1_6_6")){
					 InvestmentBusinessDetailsDto dto= otherdtoMap.get("amt1_6_6");
					 dto.setPropertycode(key);
					 resultList.add(dto);
				 }
				}
			 else if(key.equals("amt1_7_2_1")){
				 if(otherdtoMap.containsKey(key)){
					 resultList.add(otherdtoMap.get(key));
				 }
			 }else if(key.equals("amt1_7_2_2")){
				 if(otherdtoMap.containsKey(key)){
					 resultList.add(otherdtoMap.get(key));
				 }
			 }else if(key.equals("amt1_10_1")){
				 if(otherdtoMap.containsKey(key)){
					 resultList.add(otherdtoMap.get(key));
				 }
			 }else if(key.equals("amt1_19")){
				 if(otherdtoMap.containsKey(key)){
					 resultList.add(otherdtoMap.get(key));
				 }
			 }else {
			     if(bottomResultMap.containsKey(key)){
                     InvestmentBusinessDetailsDto dto= bottomResultMap.get(key);
                     dto.setPropertycode(key);
                     resultList.add(dto);
                 }
			 }
			}
		
		}
		
		// 增加表三公募基金季末数额
		InvestmentBusinessDetailsDto investmentBusinessDetailsDto = investmentBusinessDetailsMapper.queryDiffAsset(cdate, lastDate);
		BigDecimal sessionendNum = dtoMap.get("AMT1_13_1") == null ? BigDecimal.ZERO : dtoMap.get("AMT1_13_1").getSessionendNum() 
				.add(dtoMap.get("AMT1_13_2") == null ? BigDecimal.ZERO : dtoMap.get("AMT1_13_2").getSessionendNum())
				.add(dtoMap.get("AMT1_13_3") == null ? BigDecimal.ZERO : dtoMap.get("AMT1_13_3").getSessionendNum())
				.add(dtoMap.get("AMT1_13_4") == null ? BigDecimal.ZERO : dtoMap.get("AMT1_13_4").getSessionendNum())
				.add(dtoMap.get("AMT1_13_5") == null ? BigDecimal.ZERO : dtoMap.get("AMT1_13_5").getSessionendNum())
				.add(dtoMap.get("AMT1_13_6") == null ? BigDecimal.ZERO : dtoMap.get("AMT1_13_6").getSessionendNum());
		BigDecimal lastSessionendNum = dtoMap.get("AMT1_13_1") == null ? BigDecimal.ZERO : dtoMap.get("AMT1_13_1").getLastSessionendNum()
				.add(dtoMap.get("AMT1_13_2") == null ? BigDecimal.ZERO : dtoMap.get("AMT1_13_2").getLastSessionendNum())
				.add(dtoMap.get("AMT1_13_3") == null ? BigDecimal.ZERO : dtoMap.get("AMT1_13_3").getLastSessionendNum())
				.add(dtoMap.get("AMT1_13_4") == null ? BigDecimal.ZERO : dtoMap.get("AMT1_13_4").getLastSessionendNum())
				.add(dtoMap.get("AMT1_13_5") == null ? BigDecimal.ZERO : dtoMap.get("AMT1_13_5").getLastSessionendNum())
				.add(dtoMap.get("AMT1_13_6") == null ? BigDecimal.ZERO : dtoMap.get("AMT1_13_6").getLastSessionendNum());
		investmentBusinessDetailsDto.setSessionendNum(sessionendNum);
		investmentBusinessDetailsDto.setLastSessionendNum(lastSessionendNum);
		resultList.add(investmentBusinessDetailsDto);
		// 增加表三其他（请列明）金融业本季末数额和上季末数额
		InvestmentBusinessDetailsDto investmentBusinessDetailsDto1 = new InvestmentBusinessDetailsDto();
		investmentBusinessDetailsDto1.setPropertycode("thamt1_1_10");
		BigDecimal otherAmount = dtoMap.get("AMT1_1_1") == null ? BigDecimal.ZERO : dtoMap.get("AMT1_1_1").getSessionendNum()
				.add(dtoMap.get("AMT1_1_2") == null ? BigDecimal.ZERO : dtoMap.get("AMT1_1_2").getSessionendNum())
				.add(dtoMap.get("AMT1_1_3") == null ? BigDecimal.ZERO : dtoMap.get("AMT1_1_3").getSessionendNum())
				.add(dtoMap.get("AMT1_1_4") == null ? BigDecimal.ZERO : dtoMap.get("AMT1_1_4").getSessionendNum())
				.add(dtoMap.get("AMT1_1_5") == null ? BigDecimal.ZERO : dtoMap.get("AMT1_1_5").getSessionendNum())
				.add(dtoMap.get("AMT1_3_1") == null ? BigDecimal.ZERO : dtoMap.get("AMT1_3_1").getSessionendNum())
				.add(dtoMap.get("AMT1_3_2") == null ? BigDecimal.ZERO : dtoMap.get("AMT1_3_2").getSessionendNum())
				.add(dtoMap.get("AMT1_2_1") == null ? BigDecimal.ZERO : dtoMap.get("AMT1_2_1").getSessionendNum())
				.add(dtoMap.get("AMT1_2_2") == null ? BigDecimal.ZERO : dtoMap.get("AMT1_2_2").getSessionendNum())
				.add(dtoMap.get("AMT1_9_8") == null ? BigDecimal.ZERO : dtoMap.get("AMT1_9_8").getSessionendNum());
		BigDecimal lastOtherAmount = dtoMap.get("AMT1_1_1") == null ? BigDecimal.ZERO : dtoMap.get("AMT1_1_1").getLastSessionendNum()
				.add(dtoMap.get("AMT1_1_2") == null ? BigDecimal.ZERO : dtoMap.get("AMT1_1_2").getLastSessionendNum())
				.add(dtoMap.get("AMT1_1_3") == null ? BigDecimal.ZERO : dtoMap.get("AMT1_1_3").getLastSessionendNum())
				.add(dtoMap.get("AMT1_1_4") == null ? BigDecimal.ZERO : dtoMap.get("AMT1_1_4").getLastSessionendNum())
				.add(dtoMap.get("AMT1_1_5") == null ? BigDecimal.ZERO : dtoMap.get("AMT1_1_5").getLastSessionendNum())
				.add(dtoMap.get("AMT1_3_1") == null ? BigDecimal.ZERO : dtoMap.get("AMT1_3_1").getLastSessionendNum())
				.add(dtoMap.get("AMT1_3_2") == null ? BigDecimal.ZERO : dtoMap.get("AMT1_3_2").getLastSessionendNum())
				.add(dtoMap.get("AMT1_2_1") == null ? BigDecimal.ZERO : dtoMap.get("AMT1_2_1").getLastSessionendNum())
				.add(dtoMap.get("AMT1_2_2") == null ? BigDecimal.ZERO : dtoMap.get("AMT1_2_2").getLastSessionendNum())
				.add(dtoMap.get("AMT1_9_8") == null ? BigDecimal.ZERO : dtoMap.get("AMT1_9_8").getLastSessionendNum());
		investmentBusinessDetailsDto1.setOtherAmount(otherAmount);
		investmentBusinessDetailsDto1.setLastOtherAmount(lastOtherAmount);
		resultList.add(investmentBusinessDetailsDto1);
		return resultList;
	}
	/**
	 * 查询表四数据
	 * @param pageParam
	 * @return
	 */
	private List<InvestmentBusinessDetailsDto> queryTable4Info(Map<String, Object> pageParam) {
		// 导出年份
		String exportYear = (String) pageParam.get("exportYear");
		// 导出季度
		String exportQuarter = (String) pageParam.get("exportQuarter");
		String cdateStr = exportYear + exportQuarter+"01";
		Date cdate=new Date();
		try {
			cdate = DateUtil.parseDate(cdateStr,"yyyyMMdd");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(cdate);
	    calendar.add(Calendar.MONTH, 1);
	    calendar.set(Calendar.DAY_OF_MONTH, 0);
	    //取到本季末
	    Date endDate=calendar.getTime();
		calendar.add(Calendar.MONTH, -3);
		calendar.add(Calendar.MONTH, 1);
	    calendar.set(Calendar.DAY_OF_MONTH, 0);
		 //取到上季末
		Date lastEndDate=calendar.getTime();
		List<InvestmentBusinessDetailsDto> allList=new ArrayList<InvestmentBusinessDetailsDto>();
		List<InvestmentBusinessDetailsDto> closedprodList =termStructureOfBusinessAndCostBenefitMapper.selectClosedProductBalance(endDate);
		List<InvestmentBusinessDetailsDto> lastclosedprodList =termStructureOfBusinessAndCostBenefitMapper.selectClosedProductBalance(lastEndDate);
		List<InvestmentBusinessDetailsDto> openprodList =termStructureOfBusinessAndCostBenefitMapper.selectOpenProductBalance(endDate);
		List<InvestmentBusinessDetailsDto> lastopenprodList =termStructureOfBusinessAndCostBenefitMapper.selectOpenProductBalance(lastEndDate);
		List<InvestmentBusinessDetailsDto> rprodRateList =termStructureOfBusinessAndCostBenefitMapper.selectRProductAvgRate(endDate);
		List<InvestmentBusinessDetailsDto> lastrprodRateList =termStructureOfBusinessAndCostBenefitMapper.selectRProductAvgRate(lastEndDate);
		List<InvestmentBusinessDetailsDto> nprodRateList =termStructureOfBusinessAndCostBenefitMapper.selectNProductAvgRate(endDate);
		List<InvestmentBusinessDetailsDto> lastnprodRateList =termStructureOfBusinessAndCostBenefitMapper.selectNProductAvgRate(lastEndDate);
		Map<String,InvestmentBusinessDetailsDto> prodMap=new HashMap<String,InvestmentBusinessDetailsDto>();
		for(InvestmentBusinessDetailsDto single:closedprodList){
			prodMap.put(single.getTermType(), single);
		}
		for(InvestmentBusinessDetailsDto single:lastclosedprodList){
			if(prodMap.containsKey(single.getTermType())){
				InvestmentBusinessDetailsDto dto=prodMap.get(single.getTermType());
				dto.setLastSessionendBalance(single.getSessionendBalance());
			}else{
				single.setLastSessionendBalance(single.getSessionendBalance());
				single.setSessionendBalance(BigDecimal.ZERO);
				prodMap.put(single.getTermType(), single);
			}
		}
		for(InvestmentBusinessDetailsDto single:openprodList){
			if(prodMap.containsKey(single.getTermType())){
				InvestmentBusinessDetailsDto dto=prodMap.get(single.getTermType());
				dto.setSessionendBalance(dto.getSessionendBalance().add(single.getSessionendBalance()));
			}else{
				prodMap.put(single.getTermType(), single);
			}
		}
		for(InvestmentBusinessDetailsDto single:lastopenprodList){
			if(prodMap.containsKey(single.getTermType())){
				InvestmentBusinessDetailsDto dto=prodMap.get(single.getTermType());
				dto.setLastSessionendBalance((dto.getLastSessionendBalance()==null?BigDecimal.ZERO:dto.getLastSessionendBalance()).add(single.getSessionendBalance()));
			}else{
				single.setLastSessionendBalance(single.getSessionendBalance());
				single.setSessionendBalance(BigDecimal.ZERO);
				prodMap.put(single.getTermType(), single);
			}
		}
		for(InvestmentBusinessDetailsDto single:rprodRateList){
			if(prodMap.containsKey(single.getTermType())){
				InvestmentBusinessDetailsDto dto=prodMap.get(single.getTermType());
				dto.setShareamt(single.getShareamt());
				dto.setSyamt(single.getSyamt());
				dto.setSessionendYield(single.getSessionendYield());
			}else{
				prodMap.put(single.getTermType(), single);
			}
		}
		for(InvestmentBusinessDetailsDto single:lastrprodRateList){
			if(prodMap.containsKey(single.getTermType())){
				InvestmentBusinessDetailsDto dto=prodMap.get(single.getTermType());
				dto.setLastshareamt(single.getShareamt());
				dto.setLastsyamt(single.getSyamt());
				dto.setLastSessionendYield(single.getSessionendYield());
			}else{
				single.setLastSessionendYield(single.getSessionendYield());
				single.setSessionendYield(BigDecimal.ZERO);
				single.setLastsyamt(single.getSyamt());
				single.setLastSessionendYield(single.getSessionendYield());
				prodMap.put(single.getTermType(), single);
			}
		}
		for(InvestmentBusinessDetailsDto single:nprodRateList){
			if(prodMap.containsKey(single.getTermType())){
				InvestmentBusinessDetailsDto dto=prodMap.get(single.getTermType());
				if(dto.getSessionendYield()!=null){
					BigDecimal yield=BigDecimal.ZERO;
					BigDecimal shartamt=dto.getShareamt().add(single.getShareamt());
					BigDecimal syamt=dto.getSyamt().add(single.getSyamt());
					yield=syamt.divide(shartamt,15,RoundingMode.HALF_UP);
					dto.setSessionendYield(yield);
				}else{
					dto.setSessionendYield(single.getSessionendYield());
				}
			}else{
				prodMap.put(single.getTermType(), single);
			}
		}
		for(InvestmentBusinessDetailsDto single:lastnprodRateList){
			if(prodMap.containsKey(single.getTermType())){
				InvestmentBusinessDetailsDto dto=prodMap.get(single.getTermType());
				if(dto.getLastSessionendYield()!=null){
					BigDecimal yield=BigDecimal.ZERO;
					BigDecimal shartamt=dto.getLastshareamt().add(single.getShareamt());
					BigDecimal syamt=dto.getLastsyamt().add(single.getSyamt());
					yield=syamt.divide(shartamt,15,RoundingMode.HALF_UP);
					dto.setLastSessionendYield(yield);
				}else{
					dto.setLastSessionendYield(single.getSessionendYield());
				}
			}else{
				single.setLastSessionendYield(single.getSessionendYield());
				single.setSessionendYield(BigDecimal.ZERO);
				prodMap.put(single.getTermType(), single);
			}
		}
		Map<String,InvestmentBusinessDetailsDto> assetMap=new HashMap<String,InvestmentBusinessDetailsDto>();
		List<InvestmentBusinessDetailsDto> assetBalanceList =termStructureOfBusinessAndCostBenefitMapper.selectAssetBalance(endDate);
		List<InvestmentBusinessDetailsDto> lastassetBalanceList =termStructureOfBusinessAndCostBenefitMapper.selectAssetBalance(lastEndDate);
		List<InvestmentBusinessDetailsDto> assetRateList =termStructureOfBusinessAndCostBenefitMapper.selectAssetRate(endDate);
		List<InvestmentBusinessDetailsDto> lastassetRateList =termStructureOfBusinessAndCostBenefitMapper.selectAssetRate(lastEndDate);
		for(InvestmentBusinessDetailsDto single:assetBalanceList){
			assetMap.put(single.getTermType(), single);
		}
		for(InvestmentBusinessDetailsDto single:lastassetBalanceList){
			if(assetMap.containsKey(single.getTermType())){
				InvestmentBusinessDetailsDto dto=assetMap.get(single.getTermType());
				dto.setLastSessionendBalance(single.getSessionendBalance());
			}else{
				single.setLastSessionendBalance(single.getSessionendBalance());
				single.setSessionendBalance(BigDecimal.ZERO);
				assetMap.put(single.getTermType(), single);
			}
		}
		for(InvestmentBusinessDetailsDto single:assetRateList){
			if(assetMap.containsKey(single.getTermType())){
				InvestmentBusinessDetailsDto dto=assetMap.get(single.getTermType());
				dto.setSessionendYield(single.getSessionendYield());
			}else{
				assetMap.put(single.getTermType(), single);
			}
		}
		for(InvestmentBusinessDetailsDto single:lastassetRateList){
			if(assetMap.containsKey(single.getTermType())){
				InvestmentBusinessDetailsDto dto=assetMap.get(single.getTermType());
				dto.setLastSessionendYield(single.getSessionendYield());
			}else{
				single.setLastSessionendYield(single.getSessionendYield());
				single.setSessionendYield(BigDecimal.ZERO);
				assetMap.put(single.getTermType(), single);
			}
		}
		Map<String,InvestmentBusinessDetailsDto> assetNSMap=new HashMap<String,InvestmentBusinessDetailsDto>();
		List<InvestmentBusinessDetailsDto> assetNSBalanceList =termStructureOfBusinessAndCostBenefitMapper.selectNSAssetBalance(endDate);
		List<InvestmentBusinessDetailsDto> lastassetNSBalanceList =termStructureOfBusinessAndCostBenefitMapper.selectNSAssetBalance(lastEndDate);
		List<InvestmentBusinessDetailsDto> assetNSRateList =termStructureOfBusinessAndCostBenefitMapper.selectNSAssetRate(endDate);
		List<InvestmentBusinessDetailsDto> lastassetNSRateList =termStructureOfBusinessAndCostBenefitMapper.selectNSAssetRate(lastEndDate);
		for(InvestmentBusinessDetailsDto single:assetNSBalanceList){
			assetNSMap.put(single.getTermType(), single);
		}
		for(InvestmentBusinessDetailsDto single:lastassetNSBalanceList){
			if(assetNSMap.containsKey(single.getTermType())){
				InvestmentBusinessDetailsDto dto=assetNSMap.get(single.getTermType());
				dto.setLastSessionendBalance(single.getSessionendBalance());
			}else{
				single.setLastSessionendBalance(single.getSessionendBalance());
				single.setSessionendBalance(BigDecimal.ZERO);
				assetNSMap.put(single.getTermType(), single);
			}
		}
		
		for(InvestmentBusinessDetailsDto single:assetNSRateList){
			if(assetNSMap.containsKey(single.getTermType())){
				InvestmentBusinessDetailsDto dto=assetNSMap.get(single.getTermType());
				dto.setSessionendYield(single.getSessionendYield());
				dto.setLastSessionendYield(single.getLastSessionendYield());
			}else{
				assetNSMap.put(single.getTermType(), single);
			}
		}
		for(InvestmentBusinessDetailsDto single:lastassetNSRateList){
			if(assetNSMap.containsKey(single.getTermType())){
				InvestmentBusinessDetailsDto dto=assetNSMap.get(single.getTermType());
				dto.setLastSessionendYield(single.getSessionendYield());
			}else{
				single.setLastSessionendYield(single.getSessionendYield());
				single.setSessionendYield(BigDecimal.ZERO);
				assetNSMap.put(single.getTermType(), single);
			}
		}
		for(String key:prodMap.keySet()){
			String propertycode=porpertyMap_4_1.get(key);
			InvestmentBusinessDetailsDto dto=prodMap.get(key);
			if(dto!=null){
				dto.setPropertycode(propertycode);
				allList.add(dto);
			}
		}
		for(String key:assetMap.keySet()){
			String propertycode=porpertyMap_4_2.get(key);
			InvestmentBusinessDetailsDto dto=assetMap.get(key);
			if(dto!=null){
				dto.setPropertycode(propertycode);
				allList.add(dto);
			}
		}
		for(String key:assetNSMap.keySet()){
			String propertycode=porpertyMap_4_3.get(key);
			InvestmentBusinessDetailsDto dto=assetNSMap.get(key);
			if(dto!=null){
				dto.setPropertycode(propertycode);
				allList.add(dto);
			}
		}
		Map<String,InvestmentBusinessDetailsDto> incurDebtsMap=new HashMap<String,InvestmentBusinessDetailsDto>();
		List<InvestmentBusinessDetailsDto> incurDebtsBalanceList =termStructureOfBusinessAndCostBenefitMapper.selectIncurDebtsBalance(endDate);
		List<InvestmentBusinessDetailsDto> lastincurDebtsBalanceList =termStructureOfBusinessAndCostBenefitMapper.selectIncurDebtsBalance(lastEndDate);
		List<InvestmentBusinessDetailsDto> incurDebtsRateList =termStructureOfBusinessAndCostBenefitMapper.selectIncurDebtsRate(endDate);
		List<InvestmentBusinessDetailsDto> lastincurDebtsRateList =termStructureOfBusinessAndCostBenefitMapper.selectIncurDebtsRate(lastEndDate);
		
		for(InvestmentBusinessDetailsDto single:incurDebtsBalanceList){
			incurDebtsMap.put(single.getTermType(), single);
		}
		for(InvestmentBusinessDetailsDto single:lastincurDebtsBalanceList){
			if(incurDebtsMap.containsKey(single.getTermType())){
				single.setLastSessionendBalance(single.getSessionendBalance());
			}else{
				single.setLastSessionendBalance(single.getSessionendBalance());
				single.setSessionendBalance(BigDecimal.ZERO);
				incurDebtsMap.put(single.getTermType(), single);
			}
		}
		for(InvestmentBusinessDetailsDto single:incurDebtsRateList){
			if(incurDebtsMap.containsKey(single.getTermType())){
				InvestmentBusinessDetailsDto dto=incurDebtsMap.get(single.getTermType());
				dto.setSessionendYield(single.getSessionendYield());
			}else{
				incurDebtsMap.put(single.getTermType(), single);
			}
		}
		for(InvestmentBusinessDetailsDto single:lastincurDebtsRateList){
			if(incurDebtsMap.containsKey(single.getTermType())){
				InvestmentBusinessDetailsDto dto=incurDebtsMap.get(single.getTermType());
				dto.setLastSessionendYield(single.getSessionendYield());
			}else{
				single.setLastSessionendYield(single.getSessionendYield());
				single.setSessionendYield(BigDecimal.ZERO);
				incurDebtsMap.put(single.getTermType(), single);
			}
		}
		for(String key:incurDebtsMap.keySet()){
			String propertycode=porpertyMap_4_4.get(key);
			InvestmentBusinessDetailsDto dto=incurDebtsMap.get(key);
			if(dto!=null){
				dto.setPropertycode(propertycode);
				allList.add(dto);
			}
		}
		return allList;
		
	}
	/**  
	 * @Description 组装表一、表六数据到实体类  
	 * @Author      高健  
	 * @Date        2019年8月8日 上午11:53:42  
	 * @param @param pageParam
	 * @param @return 参数  
	 * @return List<InvestmentBusinessDetailsDto> 返回类型   
	 * @throws  
	 */  
	private List<InvestmentBusinessDetailsDto> queryTable1And6Info(Map<String, Object> pageParam) {
		Map<String, BigDecimal> map = createTable1Data(pageParam);
		List<InvestmentBusinessDetailsDto> InvestmentBusinessDetailsList = new ArrayList<InvestmentBusinessDetailsDto>();
		for (String key : map.keySet()) {
			InvestmentBusinessDetailsDto investmentBusinessDetailsDto = new InvestmentBusinessDetailsDto();
			investmentBusinessDetailsDto.setPropertycode(key);
			// 本季末余额
			if (key.contains("cur")) {
				investmentBusinessDetailsDto.setTqeAmount(map.get(key));
				// 上季末数额
			} else if (key.contains("last")) {
				investmentBusinessDetailsDto.setTlqeAmount(map.get(key));
			}
			InvestmentBusinessDetailsList.add(investmentBusinessDetailsDto);
		}
		return InvestmentBusinessDetailsList;
	}
	
	/**  
	 * @Description 根据键值查询金额，为空返回 0   
	 * @Author      高健  
	 * @Date        2019年8月8日 上午9:46:01  
	 * @param @return 参数  
	 * @return BigDecimal 返回类型   
	 * @throws  
	 */  
	private BigDecimal queryMoney(Map<String, BigDecimal> map, String key) {
		return map.get(key) == null ? BigDecimal.ZERO : map.get(key);
	}
	
	public InvestmentBusinessDetailsDto divideBillion(InvestmentBusinessDetailsDto detail) {
		BigDecimal billion = new BigDecimal(100000000);
		if(detail.getSessionendNum()!=null)//期末余额
			detail.setSessionendNum(detail.getSessionendNum().divide(billion, 11, BigDecimal.ROUND_HALF_UP));
		if(detail.getLastSessionendNum()!=null)//期末余额（穿透后）
			detail.setLastSessionendNum(detail.getLastSessionendNum().divide(billion, 11, BigDecimal.ROUND_HALF_UP));
		if(detail.getNonperformingAssetNum()!=null)//本期银行收益
			detail.setNonperformingAssetNum(detail.getNonperformingAssetNum().divide(billion, 11, BigDecimal.ROUND_HALF_UP));
		if(detail.getLastNonperformingAssetNum()!=null)//本期客户收益
			detail.setLastNonperformingAssetNum(detail.getLastNonperformingAssetNum().divide(billion, 11, BigDecimal.ROUND_HALF_UP));
		if(detail.getTqeAmount()!=null)//本期募集金额
			detail.setTqeAmount(detail.getTqeAmount().divide(billion, 11, BigDecimal.ROUND_HALF_UP));
		if(detail.getTlqeAmount()!=null)//本期总募集金额
			detail.setTlqeAmount(detail.getTlqeAmount().divide(billion, 11, BigDecimal.ROUND_HALF_UP));
		if (detail.getNonsStandardAmount() != null) { // 非标准化债权类投资本季末数额
			detail.setNonsStandardAmount(detail.getNonsStandardAmount().divide(billion, 11, BigDecimal.ROUND_HALF_UP));
		}
		if (detail.getLastNonsStandardAmount() != null) { // 非标准化债权类投资上季末数额
			detail.setLastNonsStandardAmount(detail.getLastNonsStandardAmount().divide(billion, 11, BigDecimal.ROUND_HALF_UP));
		}
		if (detail.getIndInvestmentAmount() != null) { // 产业投资基金上季末数额
			detail.setIndInvestmentAmount(detail.getIndInvestmentAmount().divide(billion, 11, BigDecimal.ROUND_HALF_UP));
		}
		if (detail.getLastIndInvestmentAmount() != null) { // 产业投资基金上季末数额
			detail.setLastIndInvestmentAmount(detail.getLastIndInvestmentAmount().divide(billion, 11, BigDecimal.ROUND_HALF_UP));
		}
		if (detail.getOtherAmount() != null) { // 其他本季末数额
			detail.setOtherAmount(detail.getOtherAmount().divide(billion, 11, BigDecimal.ROUND_HALF_UP));
		}
		if (detail.getLastOtherAmount() != null) { // 其他上季末数额
			detail.setLastOtherAmount(detail.getLastOtherAmount().divide(billion, 11, BigDecimal.ROUND_HALF_UP));
		}
		if (detail.getSessionendBalance() != null) { // 表四本季末数额余额
			detail.setSessionendBalance(detail.getSessionendBalance().divide(billion, 11, BigDecimal.ROUND_HALF_UP));
		}
		if (detail.getSessionendYield() != null) { // 表四 本季末平均年化收益水平 收益率
			detail.setSessionendYield(detail.getSessionendYield().multiply(new BigDecimal(100)));
		}
		if (detail.getLastSessionendBalance() != null) { // 表四上季末数额余额
			detail.setLastSessionendBalance(detail.getLastSessionendBalance().divide(billion, 11, BigDecimal.ROUND_HALF_UP));
		}
		if (detail.getLastSessionendYield() != null) { // 表四 上季末平均年化收益水平 收益率
			detail.setLastSessionendYield(detail.getLastSessionendYield().multiply(new BigDecimal(100)));
		}
		return detail;
	}
	
	/**  
	 * @Description 查询表三剩余数据  
	 * @Author      高健  
	 * @Date        2019年8月9日 上午11:17:18  
	 * @param @param pageParam
	 * @param @return 参数  
	 * @return List<InvestmentBusinessDetailsDto> 返回类型   
	 * @throws  
	 */  
	private List<InvestmentBusinessDetailsDto> queryTable3OtherInfo(Map<String, Object> pageParam) {
		List<InvestmentBusinessDetailsDto> investmentBusinessDetailsDtoList = new ArrayList<InvestmentBusinessDetailsDto>();
		// 导出年份
		String exportYear = (String) pageParam.get("exportYear");
		// 导出季度
		String exportQuarter = (String) pageParam.get("exportQuarter");
		String startDateStr = exportYear + "-" + exportQuarter + "-01";
		Date startDate=new Date();
		try {
			startDate = DateUtil.parseDate(startDateStr,"yyyy-MM-dd");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(startDate);
		calendar.add(Calendar.MONTH, -3);
		Date lastDate=calendar.getTime();
		// 金融债券相关信息
		List<InvestmentBusinessDetailsDto> InvestmentBusinessDetails = investmentBusinessDetailsMapper.queryBankDebentureAmount(startDate, lastDate);
		investmentBusinessDetailsDtoList.addAll(InvestmentBusinessDetails);
		List<OverallInfoDto> overallInfoDtoList = overallInfoMapper.queryNotBankDebentureInfoList(startDate, lastDate);
		// Map<行业分类:产品形式, 余额>
		Map<String, BigDecimal> porpertyAmoutMap = new HashMap<String, BigDecimal>(1 << 8);
		for (OverallInfoDto overallInfoDto : overallInfoDtoList) {
			porpertyAmoutMap.put(overallInfoDto.getPropertyCode() + overallInfoDto.getReportType(), overallInfoDto.getBalance());
		}
		for (int i = 1; i < 21; i ++) {
			String porperty = "thamt1_1_";
			String flag = "";
			if (i < 10) {
				flag = "0" + i;
			} else {
				flag =  i + "";
			}
			porperty += flag;
			InvestmentBusinessDetailsDto investmentBusinessDetailsDto = new InvestmentBusinessDetailsDto();
			investmentBusinessDetailsDto.setPropertycode(porperty);
			investmentBusinessDetailsDto.setSessionendNum(porpertyAmoutMap.get(flag + "BONDCUR") == null ? BigDecimal.ZERO : porpertyAmoutMap.get(flag + "BONDCUR"));
			investmentBusinessDetailsDto.setLastSessionendNum(porpertyAmoutMap.get(flag + "BONDLAST") == null ? BigDecimal.ZERO : porpertyAmoutMap.get(flag + "BONDLAST"));
			investmentBusinessDetailsDto.setNonsStandardAmount(porpertyAmoutMap.get(flag + "NOSTABONDCUR") == null ? BigDecimal.ZERO : porpertyAmoutMap.get(flag + "NOSTABONDCUR"));
			investmentBusinessDetailsDto.setLastNonsStandardAmount(porpertyAmoutMap.get(flag + "NOSTABONDLAST") == null ? BigDecimal.ZERO : porpertyAmoutMap.get(flag + "NOSTABONDLAST"));
			investmentBusinessDetailsDto.setIndInvestmentAmount(porpertyAmoutMap.get(flag + "INDINCUR") == null ? BigDecimal.ZERO : porpertyAmoutMap.get(flag + "INDINCUR"));
			investmentBusinessDetailsDto.setLastIndInvestmentAmount(porpertyAmoutMap.get(flag + "INDINLAST") == null ? BigDecimal.ZERO : porpertyAmoutMap.get(flag + "INDINLAST"));
			investmentBusinessDetailsDto.setOtherAmount(porpertyAmoutMap.get(flag + "OTHERCUR") == null ? BigDecimal.ZERO : porpertyAmoutMap.get(flag + "OTHERCUR"));
			investmentBusinessDetailsDto.setLastOtherAmount(porpertyAmoutMap.get(flag + "OTHERLAST") == null ? BigDecimal.ZERO : porpertyAmoutMap.get(flag + "OTHERLAST"));
			investmentBusinessDetailsDtoList.add(investmentBusinessDetailsDto);
		}
		return investmentBusinessDetailsDtoList;
	}
	
	/**  
	 * @Description 正对propertycode相同的实体进行金额的合并  
	 * @Author      高健  
	 * @Date        2019年8月10日 上午11:48:16  
	 * @param @param dto1
	 * @param @param dto2
	 * @param @return 参数  
	 * @return InvestmentBusinessDetailsDto 返回类型   
	 * @throws  
	 */  
	private InvestmentBusinessDetailsDto mergeSameDto(InvestmentBusinessDetailsDto dto1, InvestmentBusinessDetailsDto dto2) {
		InvestmentBusinessDetailsDto investmentBusinessDetailsDto = new InvestmentBusinessDetailsDto();
		investmentBusinessDetailsDto.setPropertycode(dto1.getPropertycode());
		investmentBusinessDetailsDto.setTqeAmount(addMoney(dto1.getTqeAmount(), dto2.getTqeAmount()));
		investmentBusinessDetailsDto.setTlqeAmount(addMoney(dto1.getTlqeAmount(), dto2.getTlqeAmount()));
		investmentBusinessDetailsDto.setSessionendNum(addMoney(dto1.getSessionendNum(), dto2.getSessionendNum()));
		investmentBusinessDetailsDto.setNonperformingAssetNum(addMoney(dto1.getNonperformingAssetNum(), dto2.getNonperformingAssetNum()));
		investmentBusinessDetailsDto.setLastSessionendNum(addMoney(dto1.getLastSessionendNum(), dto2.getLastSessionendNum()));
		investmentBusinessDetailsDto.setLastNonperformingAssetNum(addMoney(dto1.getLastNonperformingAssetNum(), dto2.getLastNonperformingAssetNum()));
		investmentBusinessDetailsDto.setNonsStandardAmount(addMoney(dto1.getNonsStandardAmount(), dto2.getNonsStandardAmount()));
		investmentBusinessDetailsDto.setLastNonsStandardAmount(addMoney(dto1.getLastNonsStandardAmount(), dto2.getLastNonsStandardAmount()));
		investmentBusinessDetailsDto.setIndInvestmentAmount(addMoney(dto1.getIndInvestmentAmount(), dto2.getIndInvestmentAmount()));
		investmentBusinessDetailsDto.setLastIndInvestmentAmount(addMoney(dto1.getLastIndInvestmentAmount(), dto2.getLastIndInvestmentAmount()));
		investmentBusinessDetailsDto.setOtherAmount(addMoney(dto1.getOtherAmount(), dto2.getOtherAmount()));
		investmentBusinessDetailsDto.setLastOtherAmount(addMoney(dto1.getLastOtherAmount(), dto2.getLastOtherAmount()));
		return investmentBusinessDetailsDto;
	}
	
	/**  
	 * @Description 相同属性相对应金额相加  （第一个值需要乘以 100000000）
	 * @Author      高健  
	 * @Date        2019年8月10日 下午1:34:27  
	 * @param @param num1 
	 * @param @param num2
	 * @param @return 参数  
	 * @return BigDecimal 返回类型   
	 * @throws  
	 */  
	private BigDecimal addMoney(BigDecimal num1, BigDecimal num2) {
		BigDecimal billion = new BigDecimal(100000000);
		if (null == num1) {
			num1 = BigDecimal.ZERO;
		}
		if (null == num2) {
			num2 = BigDecimal.ZERO;
		}
		return num1.multiply(billion).add(num2);
	}
	
 }
