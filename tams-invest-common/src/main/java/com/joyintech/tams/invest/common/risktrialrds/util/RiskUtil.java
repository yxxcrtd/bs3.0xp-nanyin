package com.joyintech.tams.invest.common.risktrialrds.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joyintech.framework.base.intf.DataDict;
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.*;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 部分方法风险试算共通
 * 
 * @author 裴家俊
 * @version 1.0 2018年11月30日
 * @see RiskUtil
 * @since 1.0
 */
public class RiskUtil {

	public static final String redisKey = "RKEY";
	public static final String redisVal = "RVALUE";
	public static final String paramPref = "${";
	public static final String paramPrefZy = "\\$\\{";
	public static final String paramSuff = "}";
	public static final String LIMIT = "LIMIT_";
	public static final String FORBID = "FORBID_";
	public static final String AND = "AND";
	public static final String OR = "OR";
	public static final String COMMON_PLUGIN_REFLECT="pluginClassDao";
	public static final String COMMON_PLUGIN_NAME="com.joyintech.tams.invest.common.risktrialrds.reflect.impl.";
	public static final String MAX = "MAX";
	public static final String MIN = "MIN";
	public static final String PLATE_DATE = "PLATE_DATE";
	public static final String DELIMITER=new String(new byte[] { 3 });
	public static final String REMIND_FILE_PRE = "CPLRSK_ZDTX_";
	public static final String FILE_OK = ".ok";
	public static final int FTP_PORT = 21;
	public static final String FTP_ENCODE = "UTF-8";
	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	public static final String RISK_RESULT_ENTITY="riskTrialRetDto";
    public static final String APPROVE_LINE_ONE="APPROVE_LINE_ONE";
    public static final String APPROVE_LINE_TWO="APPROVE_LINE_TWO";
    public static final String APPROVE_LINE_THR="APPROVE_LINE_THR";

    /*合并模拟测算代码 panbin 2020-8-11 start*/
	public static final String PAGE_REDIS_FLAG="pageRedisFlag";
	/*合并模拟测算代码 panbin 2020-8-11 end*/
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
		if (BussinessDictCodeListRds.单位_万元.getValue().equals(unit)) {
			return value * 10000;
		}
		if (BussinessDictCodeListRds.单位_PERCENT.getValue().equals(unit)) {
			return ArithmeticUtil.divide(new BigDecimal(value),new BigDecimal(100),10).doubleValue() ;
		}
		if (BussinessDictCodeListRds.单位_BP.getValue().equals(unit)) {
			return ArithmeticUtil.divide(new BigDecimal(value),new BigDecimal(10000),10).doubleValue() ;
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
			return SysDataUtil.getNameFromDataDict(BussinessDictCodeRds.单位, unit);
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
	

	private RiskUtil(){
	    // private the constructor
	}
	
    public static String transferWarnFoul(String value, String indCellType) {
        
        String wg = StringUtil.EMPTY;


        if (StringUtil.isNotEmpty(value)) {
			Map<?, ?> detailJson =JsonUtils.json2Object(value,Map.class);
			Map<?, ?> vThreshold1Map = (Map<?, ?>)detailJson.get("threshold_1");
            if (indCellType.indexOf("LIMIT_") != -1) {
                    if (StringUtil.isNotEmpty(vThreshold1Map.get("value"))) {
                        wg = (String)vThreshold1Map.get("op") + vThreshold1Map.get("value")
                             + valueConvertUnit((String)vThreshold1Map.get("unit"));
                    }
            }
            else {
                /* 禁止型 */
                if(StringUtil.isNotEmpty(vThreshold1Map.get("value_cn"))){
                	wg=vThreshold1Map.get("value_cn").toString();
                	wg=wg.substring(0, wg.length() - 1);
				}
            }
        }
        return wg;
    }

    /**
     * 单位转换
     */
    private static String valueConvertUnit(String value) {
        if (StringUtil.EMPTY.equals(value)) {
            return value;
        }
		if(BussinessDictCodeListRds.单位_无.getValue().equals(value)){
			return StringUtil.EMPTY;
		}
        String retStr = StringUtil.EMPTY;
        DataDict dataDict = CacheUtil.getDictCode(BussinessDictCodeRds.单位.getValue());
        List<? extends DataDictItem> itemList = dataDict.getItems();
        for (DataDictItem v : itemList) {
            if (v.getItemCode().equals(value)) {
                if(BussinessDictCodeListRds.单位_无.getValue().equals(value)){
                    retStr=StringUtil.EMPTY;
                }else {
                    retStr = v.getItemValue();
                }
                break;
            }
        }
        return retStr;
    }

    /**
     * 获取解析指标参数共通
     */
    public  static  List<String> analysisIndParam(String param,List<String> paramList){
        if(StringUtil.isNotEmpty(param)&&param.indexOf(RiskUtil.paramPref)!=-1&&param.indexOf(paramSuff)!=-1){
            String paramPre=param.substring(param.indexOf(paramPref)+2, param.indexOf(paramSuff));
            String exprNew=param.substring(param.indexOf(paramSuff)+1);
            paramList.add(paramPre);
            analysisIndParam(exprNew,paramList);
        }
        return paramList;
    }

	/**
	 * 取得新的参数代码
	 * @param exp
	 * @param cond
	 * @return
	 */
	public static String getParamWithMode(Set<String> fixParamList,String exp,String cond){
		Map<String,String> expMap=new HashMap<>();
		if(cond.indexOf(">")>-1||cond.indexOf(">=")>-1){
			expMap=dealParamMinMax(fixParamList,exp,expMap,RiskUtil.MAX);
		}else if(cond.indexOf("<")>-1||cond.indexOf("<=")>-1){
			expMap=dealParamMinMax(fixParamList,exp,expMap,RiskUtil.MIN);
		}else{
			expMap=dealParamMinMax(fixParamList,exp,expMap,RiskUtil.MAX);
		}
		List<String> expArray=new ArrayList<String>();
		for (Map.Entry<String, String> entry : expMap.entrySet()) {
			exp=exp.replaceAll(entry.getKey(),entry.getValue());
		}
		return exp;
	}

	/**
	 * 处理参数的MAX及Min
	 * @param exp
	 * @param expMap
	 * @return
	 */
	private static Map<String,String> dealParamMinMax(Set<String> fixParamList,String exp,Map<String,String> expMap,String direction){
		if(exp.indexOf("/")>-1){
			String fenZi=exp.substring(0,exp.indexOf("/"));
			String fenMu=exp.substring(exp.indexOf("/")+1,exp.length());
			dealMinMax(fixParamList,fenZi,expMap,direction.equals(RiskUtil.MAX)?RiskUtil.MAX:RiskUtil.MIN);
			if(fenMu.indexOf("/")>-1){
				dealParamMinMax(fixParamList,fenMu,expMap,direction);
			}
			dealMinMax(fixParamList,fenMu,expMap,direction.equals(RiskUtil.MAX)?RiskUtil.MIN:RiskUtil.MAX);
			return expMap;
		}
		if(exp.indexOf("*")>-1){
			String fenZi=exp.substring(0,exp.indexOf("*"));
			String fenMu=exp.substring(exp.indexOf("*")+1,exp.length());
			dealMinMax(fixParamList,fenZi,expMap,direction.equals(RiskUtil.MAX)?RiskUtil.MAX:RiskUtil.MIN);
			if(fenMu.indexOf("*")>-1){
				dealParamMinMax(fixParamList,fenMu,expMap,direction);
			}
			return expMap;
		}
		//处理即没有除法也没有乘法的表达式
		dealMinMax(fixParamList,exp,expMap,direction);
		return expMap;
	}

	/**
	 * 处理单个表达式中的最大最小值，比如分子或分母或没有除法及乘法的表达式
	 * @param entry
	 * @param entryRet
	 * @param operate
	 */
    private  static void dealMinMax(Set<String> fixParamList,String entry,Map<String,String> entryRet,String operate){
		String[] expAry=entry.split(RiskUtil.paramSuff);//分子
		List<String> listEnty=Arrays.asList(expAry).stream().filter(item->!item.equals("")&&!item.equals("(")&&!item.equals(")")).collect(Collectors.toList());
		String[] expAryRel=listEnty.toArray(new String[listEnty.size()]);
		for(String expStr:expAryRel){
			List<String> listEntySub=Arrays.asList(expStr.split(RiskUtil.paramPrefZy)).stream().filter(item->!item.equals("")&&!item.equals("(")&&!item.equals(")")).collect(Collectors.toList());
			String[] expArySec=listEntySub.toArray(new String[listEntySub.size()]);
			if(operate.equals(MAX)) {
				if (expArySec.length > 1 ) {
					if(expArySec[0].equals("-")) {
						//若参数是固定值，直接赋值FIX
						if(fixParamList.contains(expArySec[1])){
							entryRet.put(expArySec[1], expArySec[1] + GlobalConstant.COLON + BussinessDictCodeListRds.指标参数实现类型_固定值.getValue());
						}
						else {
							entryRet.put(expArySec[1], expArySec[1] + GlobalConstant.COLON + BussinessDictCodeListRds.参数粒度_卖出.getValue());
						}
					}
					else{
						//若参数是固定值，直接赋值FIX
						if(fixParamList.contains(expArySec[1])){
							entryRet.put(expArySec[1], expArySec[1] + GlobalConstant.COLON + BussinessDictCodeListRds.指标参数实现类型_固定值.getValue());
						}
						else {
							entryRet.put(expArySec[1], expArySec[1] + GlobalConstant.COLON + BussinessDictCodeListRds.参数粒度_买入.getValue());
						}
					}
				} else {
					//若参数是固定值，直接赋值FIX
					if(fixParamList.contains(expArySec[0])){
						entryRet.put(expArySec[0],expArySec[0] + GlobalConstant.COLON + BussinessDictCodeListRds.指标参数实现类型_固定值.getValue());
					}else {
						entryRet.put(expArySec[0], expArySec[0] + GlobalConstant.COLON + BussinessDictCodeListRds.参数粒度_买入.getValue());
					}
				}
			}
			else{
				if (expArySec.length > 1 ) {
					if(expArySec[0].equals("-")) {
						//若参数是固定值，直接赋值FIX
						if(fixParamList.contains(expArySec[1])){
							entryRet.put(expArySec[1], expArySec[1] + GlobalConstant.COLON + BussinessDictCodeListRds.指标参数实现类型_固定值.getValue());
						}else {
							entryRet.put(expArySec[1], expArySec[1] + GlobalConstant.COLON + BussinessDictCodeListRds.参数粒度_买入.getValue());
						}
					}
					else{
						//若参数是固定值，直接赋值FIX
						if(fixParamList.contains(expArySec[1])){
							entryRet.put(expArySec[1],expArySec[1] + GlobalConstant.COLON + BussinessDictCodeListRds.指标参数实现类型_固定值.getValue());
						}else {
							entryRet.put(expArySec[1], expArySec[1] + GlobalConstant.COLON + BussinessDictCodeListRds.参数粒度_卖出.getValue());
						}
					}
				} else {
					//若参数是固定值，直接赋值FIX
					if(fixParamList.contains(expArySec[0])){
						entryRet.put(expArySec[0],expArySec[0] + GlobalConstant.COLON + BussinessDictCodeListRds.指标参数实现类型_固定值.getValue());
					}else {
						entryRet.put(expArySec[0], expArySec[0] + GlobalConstant.COLON + BussinessDictCodeListRds.参数粒度_卖出.getValue());
					}
				}
			}
		}
	}

	/**
	 * 根据类型比较大小
	 * @param type
	 * @param valStr
	 * @param valEnd
	 * @return
	 */
	public static boolean compareNumberVal(String valType,String type, BigDecimal valStr, BigDecimal valEnd, Object comVal){
		if(valType.equals("DATE")){
			Date dateStr = new Date();
			dateStr.setTime(valStr.longValue());
			Date dateEnd = new Date();
			dateEnd.setTime(valEnd.longValue());
			return DateUtil.compareDate(simpleDateFormat.format(dateStr),DateUtil.format((Date) comVal))<=0&&DateUtil.compareDate(simpleDateFormat.format(dateEnd),DateUtil.format((Date) comVal))>=0;
		}else {
			if( comVal instanceof BigDecimal ) {
				BigDecimal comValN = (BigDecimal) comVal;
				if (type.equals(BussinessDictCodeListRds.控制类型_范围控制.getValue())) {
					return comValN.compareTo(valStr) >= 0 && comValN.compareTo(valEnd) <= 0;
				}
				if (type.equals(BussinessDictCodeListRds.控制类型_大于.getValue())) {
					return comValN.compareTo(valStr) > 0;
				}
				if (type.equals(BussinessDictCodeListRds.控制类型_大于等于.getValue())) {
					return comValN.compareTo(valStr) >= 0;
				}
				if (type.equals(BussinessDictCodeListRds.控制类型_小于.getValue())) {
					return comValN.compareTo(valStr) < 0;
				}
				if (type.equals(BussinessDictCodeListRds.控制类型_小于等于.getValue())) {
					return comValN.compareTo(valStr) <= 0;
				}
			}

		}
		return true;
	}

	/**
	 * Map转实体
	 * @param mapOther
	 * @param key
	 * @param cls
	 * @param <T>
	 * @return
	 */
	public static <T>  List<T> getMapOtherObj(Map<String,Object> mapOther,String key, Class<T> cls){
		List<T> t=(List<T>)mapOther.get(key);
		return t;
	}

	/**
	 * 取指标状态中最小的状态
	 * @param supState
	 * @return
	 */
	public static String getMinState(List<String> supState){
		List<Integer> supStateNum=new ArrayList<>();
		supState.forEach(item->{
			if(item.equals(BussinessDictCodeListRds.监督状态_正常.getValue())){
				supStateNum.add(0);
			}
			if(item.equals(BussinessDictCodeListRds.监督状态_触发预警.getValue())){
				supStateNum.add(1);
			}
			if(item.equals(BussinessDictCodeListRds.监督状态_触发审批.getValue())){
				supStateNum.add(2);
			}
			if(item.equals(BussinessDictCodeListRds.监督状态_触发超限.getValue())){
				supStateNum.add(3);
			}
			if(item.equals(BussinessDictCodeListRds.监督状态_异常.getValue())){
				supStateNum.add(4);
			}
		});
		if(Collections.min(supStateNum)==0){
			return BussinessDictCodeListRds.监督状态_正常.getValue();
		}
		if(Collections.min(supStateNum)==1){
			return BussinessDictCodeListRds.监督状态_触发预警.getValue();
		}
		if(Collections.min(supStateNum)==2){
			return BussinessDictCodeListRds.监督状态_触发审批.getValue();
		}
		if(Collections.min(supStateNum)==3){
			return BussinessDictCodeListRds.监督状态_触发超限.getValue();
		}
		if(Collections.min(supStateNum)==4){
			return BussinessDictCodeListRds.监督状态_异常.getValue();
		}
		return Collections.min(supStateNum).toString();
	}

	/**
	 * 获取列的SQL
	 * @param linkSign
	 * @param column
	 * @param valueStr
	 * @return
	 */
	public static String getTableColumnSql(String linkSign,String column,String valueStr,String sqlWhere){
		if(!StringUtil.isNotEmpty(linkSign)){
			sqlWhere=" ("+column+" in "+ RiskUtil.convertPortfCode(valueStr)+")" +sqlWhere;
			int rightCount=RiskUtil.getWordCount(sqlWhere,")");
			int leftCount=RiskUtil.getWordCount(sqlWhere,"(");
			for(int i=0;i<rightCount-leftCount;i++){
				sqlWhere="("+sqlWhere;
			}
		}
		else {
			if (linkSign.equals("OR")) {
				sqlWhere = " OR (" + column + " in " + RiskUtil.convertPortfCode(valueStr) + ")" +")" +sqlWhere;
			}
			if (linkSign.equals("AND")) {
				sqlWhere = " AND (" + column + " in " + RiskUtil.convertPortfCode(valueStr) + ")" +")"+ sqlWhere;
			}
		}
		return sqlWhere;
	}
	/**
	 * 获取对应数据库字段
	 * @param val
	 * @return
	 */
	public static String getTableColumn(String val){
		if(val.equals("01")){
			return "PROFIT_TYPE";
		}
		if(val.equals("02")){
			return "OPEN_TYPE";
		}
		if(val.equals("03")){
			return "RAISE_TYPE";
		}
		if(val.equals("04")){
			return "INVEST_NATURE";
		}
		return StringUtil.EMPTY;
	}

	/**
	 * 统计字符串中出现某个特定字符的个数
	 * @param targetStr
	 * @param compStr
	 * @return
	 */
	public static int getWordCount(String targetStr,String compStr){
		int fromIndex = 0;
		int count = 0;
		while(true){
			int index = targetStr.indexOf(compStr, fromIndex);
			if(-1 != index){
				fromIndex = index + 1;
				count++;
			}else{
				break;
			}
		}
		return count;
	}

	public static String getLogString(Exception e){
		StringBuilder sb = new StringBuilder();
		sb.append("处理异常, ");
		StackTraceElement[] trace = e.getStackTrace();
		sb.append("<br/>");
		sb.append(e.getMessage());
		sb.append("<br/>");
		int i = 0;
		for (StackTraceElement traceElement : trace) {
			if (i <= 30) {
				sb.append("&nbsp;&nbsp;at " + traceElement + "<br/>");
				i++ ;
			}
			else {
				break;
			}
		}
		return sb.toString();
	}
	
	/**
	 * 特殊字符转换
	 * @param item
	 * @param flag 正反向
	 * @return
	 */
	public static String specialCharacterConversion(String item,boolean flag){
		if(StringUtil.isNotEmpty(item)){
			if(flag) {
				return item.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&quot;", "\"");
			}else{
				return item.replaceAll("<","&lt;" ).replaceAll(">","&gt;" ).replaceAll("\"","&quot;" );
			}
		}
		return item;
	}
	/**
	 * check是否是日期
	 * @param param 参数
	 * @return check结果
	 */
	public  static <T> boolean isInvalidDate(T param) {
		if (StringUtil.isNotEmpty(param)) {
			Pattern  pattern = Pattern.compile("(\\d{4})-(\\d+)-(\\d+).*");
			Matcher matcher = pattern.matcher(param.toString());
			if(matcher.matches()){
				return true;
			}
			return false;
		}
		return false;
	}
}
