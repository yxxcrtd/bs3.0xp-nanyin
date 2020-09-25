package com.joyintech.tams.invest.common.risktrial.util;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.risktrial.dto.ExprTreeMath;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * 部分方法风险试算共通
 * 
 * @author 裴家俊
 * @version 1.0 2018年11月30日
 * @see RiskUtil
 * @since 1.0
 */
public class RiskUtil {
	public static final String LIMIT = "LIMIT_";
	public static final String FORBID = "FORBID_";
	public static final String AND = "AND";
	public static final String OR = "OR";
	public static final String COMMON_PLUGIN_REFLECT="pluginClassDao";
	public static final String COMMON_PLUGIN_NAME="com.joyintech.tams.invest.common.risktrial.reflect.impl.";
	public static final String TOT_AMT = "totalAmt";
	public static final String HAS_AMT = "hasAmt";

	public static final String AMT_FLAG = "ALL";

	/**
	 * @param str
	 * @return
	 */
	public static String convertPortfCode(String str) {
		if (!StringUtil.isNotEmpty(str)) {
			return "";
		}
		// 拆分组合代码
		String[] strPortfCodes = str.split(java.util.regex.Pattern.quote((new Character(',')).toString()), -1);

		StringBuilder builder = new StringBuilder();
		builder.append("(");

		for (String strPortfCode : strPortfCodes) {
			builder.append("'");
			builder.append(strPortfCode);
			builder.append("'");
			builder.append(",");
		}
		builder.deleteCharAt(builder.length() - 1);
		builder.append(")");

		return builder.toString();
	}

	/**
	 * 部分单位转换数值
	 * 
	 * @param unit
	 * @param value
	 * @return
	 */
	public static Double convertUnitToValue(String unit, Double value) {
		if (DictCodeListExt1.单位_万元.getValue().equals(unit)) {
			return value * 10000;
		}
		if (DictCodeListExt1.单位_PERCENT.getValue().equals(unit)) {
			return value / 100;
		}
		return value;
	}

	/**
	 * 部分单位转换为汉字
	 * 
	 * @param unit
	 * @return
	 */
	public static String convertValueToUnit(String unit) {
		if ("-".equals(unit))
			return StringUtil.EMPTY;
		else
			return SysDataUtil.getNameFromDataDict(DictCode.单位, unit);
	}
	
	/*
	 * 对于超过固定数量的集合进行二次分割----add by 裴家俊  2019/03/22--------
	 */
	public static List<List<String>> splitList(String[] listArr, int len) {
		List<String> list=Arrays.asList(listArr);
		if (list == null || list.size() == 0 || len < 1) {
			return null;
		}
		List<List<String>> result = new ArrayList<List<String>>();
		int size = list.size();
		int count = (size + len - 1) / len;
		for (int i = 0; i < count; i++) {
			List<String> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
			result.add(subList);
		}
		return result;
	}

	/**
	 * 处理可投已投信息
	 * @param exprTree
	 * @param exp
	 * @param threshVal
	 * @return
	 * @throws Exception
	 */
	public static Map<String,Object> dealParamTotal(ExprTreeMath exprTree, String exp, String threshVal) throws Exception {
		Map<String,Object> expMap=new HashMap<>();
		Object fenMuValue=null;
		if(exp.indexOf("/")>-1){
			String fenMu=exp.substring(exp.indexOf("/")+1,exp.length());
			fenMuValue=exprTree.calc(fenMu);
			expMap.put(RiskUtil.TOT_AMT, ArithmeticUtil.multiply(new BigDecimal[]{new BigDecimal(fenMuValue.toString()),	new BigDecimal(threshVal).divide(new BigDecimal(100))}));

		}else{
			fenMuValue=exprTree.calc(exp);
			expMap.put("totalAmt", threshVal);
		}
		return expMap;
	}

	/**
	 * 处理可投已投信息
	 * @param exprTree
	 * @param exp
	 * @param threshVal
	 * @return
	 * @throws Exception
	 */
	public static Map<String,Object> dealParamHas(ExprTreeMath exprTree, String exp, String threshVal) throws Exception {
		Map<String,Object> expMap=new HashMap<>();
		Object fenZiValue=null;
		if(exp.indexOf("/")>-1){
			String fenZi=exp.substring(0,exp.indexOf("/"));
			fenZiValue=exprTree.calc(fenZi);
			expMap.put(RiskUtil.HAS_AMT, ArithmeticUtil.multiply(new BigDecimal[]{new BigDecimal(fenZiValue.toString())}));
		}else{
			fenZiValue=exprTree.calc(exp);
			expMap.put("hasAmt", fenZiValue);
		}
		return expMap;
	}

	/**
	 * 获取JSON条件
	 * @param vdetailJson
	 * @return
	 * @throws Exception
	 */
	public static String getVdetailVal(String vdetailJson) throws Exception {
		Map<?, ?> violationMap = JsonUtils.json2Object(vdetailJson, Map.class);
		Map<?, ?> vThreshold2Map = (Map<?, ?>)violationMap.get("threshold_2");
		if(vThreshold2Map != null) {
			String vThreshold2Lnk = (String)vThreshold2Map.get("lnk");
			if(DictCodeList.连接符_并且.getValue().equals(vThreshold2Lnk) || DictCodeList.连接符_或.getValue().equals(vThreshold2Lnk)) {
				return (String)vThreshold2Map.get("value");
			}
		}
		Map<?, ?> vThreshold1Map = (Map<?, ?>)violationMap.get("threshold_1");
		String vThreshold1Value = (String)vThreshold1Map.get("value");
		String unit = (String)vThreshold1Map.get("unit");

		 if (unit.equals(DictCodeListExt1.单位_万元.getValue()))
		{
			vThreshold1Value=(new BigDecimal(vThreshold1Value).multiply(new BigDecimal(10000))).toString();
		}
		return vThreshold1Value;
	}
}
