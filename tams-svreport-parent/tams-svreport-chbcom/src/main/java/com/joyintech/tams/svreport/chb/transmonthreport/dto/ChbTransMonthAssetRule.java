package com.joyintech.tams.svreport.chb.transmonthreport.dto;

import java.util.List;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;

public enum ChbTransMonthAssetRule {

	合计1_1("AMT1_1","1.1合计","ASSETTYPE2","1101|1110|1109|1102|1111|1112|1113|1114|1115|1116|1117|2501|2502|1106|2401"),
	信用债("AMT1_1_a","1.1.a信用债","ASSETTYPE2","1112|1113|1114|1115|1116|1117"),
	PPN等低流动性债券("AMT1_1_a_a","1.1.a.a PPN等低流动性债券","RATINGAA-|RATINGNUll","AA+"),
	利率债("AMT1_1_b","1.1.b利率债","ASSETTYPE2","1101|1110|1109|1102|1111"),
	固定收益类公募基金("AMT1_1_c","1.1.c固定收益类公募基金","ASSETTYPE2","1106|2401"),
	

	通过信托贷款和信托产品("AMT2_1","2.1通过信托贷款和信托产品","ASSETTYPE2","1202|1701"),
	通过证券期货经营机构资管产品("AMT2_2","2.2通过证券期货经营机构资管产品","ASSETTYPE2","1703|1704|1706"),
	通过保险资管产品("AMT2_3","2.3通过保险资管产品","ASSETTYPE2","1702"),
	通过委托贷款("AMT2_4","2.4通过委托贷款","ASSETTYPE2","1203"),
	通过债权融资类产品("AMT2_5","2.5通过债权融资类产品","ASSETTYPE2","1209"),
	通过其他形式("AMT2_6","2.6通过其他形式","ASSETTYPE2","1705"),
	直接投资("AMT2_7","2.7直接投资","ASSETTYPE2","1201|1204|1205|1206|1207|1208|1210|1299"),
	
	合计AMT2_8("AMT2_8","2.8合计","ASSETTYPE2","1202|1701|1703|1704|1706|1702|1203|1209|1705|1201|1204|1205|1206|1207|1208|1210|1299"),
	
	股票类资产合计("AMT3_1","3.1 股票类资产合计","ASSETTYPE2","1302|1305"),
	
	合计AMT4_1("AMT4_1","4.1合计","ASSETTYPE2","1301|2607|2610|2611|2612|2617"),
	
	政府出资产业投资基金("AMT4_1_a","4.1.a政府出资产业投资基金","ISGOVERNFUND1","01"),
	创业投资基金("AMT4_1_b","4.1.b创业投资基金","ASSETTYPE2","2613"),
	
	
	合计AMT5_1("AMT5_1","5.1合计","ASSETTYPE2,ASSETTYPE2,ASSETTYPE2,BANKSECPREFER|BANKSECPERPETUAL|BANKSECCAPITAL","!1901,!1902,!2000,01|01|01"),
	优先股("AMT5_1_a","5.1.a优先股","ASSETTYPE2,ASSETTYPE2,ASSETTYPE2,BANKSECPREFER","!1901,!1902,!2000,01"),
	永续债_债性("AMT5_1_b","5.1.b永续债(债性)","ASSETTYPE2,ASSETTYPE2,ASSETTYPE2,BANKSECPERPETUAL,REPSECNATURE","!1901,!1902,!2000,01,01"),
	永续债_股性("AMT5_1_c","5.1.c永续债(股性)","ASSETTYPE2,ASSETTYPE2,ASSETTYPE2,BANKSECPERPETUAL,REPSECNATURE","!1901,!1902,!2000,01,02"),
	二级资本债("AMT5_1_d","5.1.d二级资本债","ASSETTYPE2,ASSETTYPE2,ASSETTYPE2,BANKSECCAPITAL","!1901,!1902,!2000,01"),

	合计AMT6_1("AMT6_1","6.1合计","ASSETTYPE2,ISGOVERNFUND1","1107|1108|1001|1303|2403|2402|2499|1401|1402|1403|1404|1405|1406|1499|1501|1504|1506|1507|1508|1509|1505|1502|1503|1599|2101|2201|2202|2299|1399|2301|2302|2303|2304|2305|1603|2801|2802|2803|2804|2805|2806|2899|2601|2602|2604|2605|2606|2607|2610|2611|2612|2617|2611|2612|2617|2699|2616|2618|2619|2620,null|01"),
	拆放同业及买入返售("AMT6_1_a","6.1.a拆放同业及买入返售","ASSETTYPE2","1107|1108"),
	现金及银行存款("AMT6_1_b","6.1.b现金及银行存款","ASSETTYPE2","1001"),
	其他公募基金("AMT6_1_c","6.1.c其他公募基金","ASSETTYPE2","1303|2403|2402|2499"),
	商品及金融衍生产品("AMT6_1_d","6.1.d商品及金融衍生产品","ASSETTYPE2","1401|1402|1403|1404|1405|1406|1499"),
	QDII("AMT6_1_e","6.1.eQDII","ASSETTYPE2","1501|1504|1506|1507|1508|1509|1505|1502|1503|1599"),
	
	合计AMT7("AMT7","7合计","ASSETTYPE2","1101|1110|1109|1102|1111|1112|1113|1114|1115|1116|1117|2501|2502|1106|2401|1202|1701|1703|1704|1706|1702|1203|1209|1705|1201|1204|1205|1206|1207|1208|1210|1299|1302|1305|1301|2607|2610|2611|2612|2617"),

	;
	/**
	 * 属性代码 
	 */
	private String propertyCode;
	
	/**
	 * 属性名称
	 */
	private String propertyName;
	
	/**
	 * 属性映射规则列表，关联chb_month_asset_position中的字段名,以逗号隔开
	 */
	private List<String> ruleCodeList;
	
	/**
	 * 属性映射规则值，与属性映射规则列表中对应的取值
	 */
	private List<String> ruleValueList;

	ChbTransMonthAssetRule(String propertyCode,String propertyName,String ruleCodeList,String ruleValueList){
		this.propertyCode = propertyCode;
		this.propertyName = propertyName;
		this.ruleCodeList = StringUtil.splitToList(GlobalConstant.COMMA, ruleCodeList);
		this.ruleValueList = StringUtil.splitToList(GlobalConstant.COMMA, ruleValueList);
	}
	
	public String getPropertyCode() {
		return propertyCode;
	}

	public void setPropertyCode(String propertyCode) {
		this.propertyCode = propertyCode;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String prepertyName) {
		this.propertyName = prepertyName;
	}

	public List<String> getRuleCodeList() {
		return ruleCodeList;
	}

	public void setRuleCodeList(List<String> ruleCodeList) {
		this.ruleCodeList = ruleCodeList;
	}

	public List<String> getRuleValueList() {
		return ruleValueList;
	}

	public void setRuleValueList(List<String> ruleValueList) {
		this.ruleValueList = ruleValueList;
	}



}
