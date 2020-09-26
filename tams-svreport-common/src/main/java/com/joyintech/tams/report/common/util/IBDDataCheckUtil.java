/*
 * 文件名：IBDDataCheckUtil.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：zyf
 * 修改时间：2017年9月14日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.report.common.util;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.time.DateUtils;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.svreport.datacheck.dto.CheckCondition;
import com.joyintech.tams.common.svreport.datacheck.dto.CheckRule;
import com.joyintech.tams.common.svreport.datacheck.service.DataCheckService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.dto.ExcelValidDto;

/**
 * 接口获取数据验证
 * 〈功能详细描述〉
 * @author zyf
 * @version 1.0 2017年9月14日
 * @see IBDDataCheckUtil
 * @since 1.0
 */
public class IBDDataCheckUtil {
	/**
	 * 必填方法
	 * --单一项目检查checkMethod参数常量
	 */
	public static final String COMMON_CHECK_METHOD_ISNOTEMPTY = "isNotEmpty";
	/**
	 * 字段固定长度判断
	 * --单一项目检查checkMethod参数常量
	 */
	public static final String COMMON_CHECK_METHOD_FIXEDLENGTH = "fixedLength";
	/**
	 * 是否在整个白名单判断
	 * --单一项目检查checkMethod参数常量
	 */
	public static final String COMMON_CHECK_METHOD_INWHITELISTALL = "inWhitelistAll";
    
    /**
     * 不能为空
     * @param errorList 
     * @param value 传入字符
     * @param field 页面field 
     * @param message msg参数
     * @return 结果
     * @see
     * @since 1.0
     */
    public static boolean notEmpty(List<ValidMessage> errorList, Object value, String field, String message){
        if(value == null || "".equals(value)) {
            if (errorList != null) {
                errorList.add(new ValidMessage(field,message));
            }
            return false;
        }
        return true;
    }
       

    
    /**
     * 是否固定长度
     * @param length 长度
     * @param errorList 
     * @param value 传入字符
     * @param field 页面field 
     * @param message msg参数
     * @return 结果 
     * @see
     * @since 1.0
     */
    public static boolean fixLength(int length, List<ValidMessage> errorList, String value, String field,
                                 String message) {
        if(!StringUtil.isEmpty(value) && value.length() != length) {
            if (errorList != null) {
                errorList.add(new ValidMessage(field,message));
            }
            return false;
        }
        return true;
    }
    
    /**
     * 最大长度
     * @param maxlength 长度max
     * @param errorList 
     * @param value 传入字符
     * @param field 页面field 
     * @param message msg参数
     * @return 结果 
     * @see
     * @since 1.0
     */
    public static boolean maxLength(int maxlength, List<ValidMessage> errorList, String value, String field,
                                 String message) {
        if(!StringUtil.isEmpty(value) && (value.length() > maxlength)) {
            if (errorList != null) {
                errorList.add(new ValidMessage(field,message));
            }
            return false;
        }
        return true;
    }
    
    /**
     * 最小长度
     * @param minlength 长度max
     * @param errorList 
     * @param value 传入字符
     * @param field 页面field 
     * @param message msg参数
     * @return 结果 
     * @see
     * @since 1.0
     */
    public static boolean minLength(int minlength, List<ValidMessage> errorList, String value, String field,
                                 String message) {
        if(!StringUtil.isEmpty(value) && (value.length() < minlength)) {
            if (errorList != null) {
                errorList.add(new ValidMessage(field,message));
            }
            return false;
        }
        return true;
    }
    
   
    
    /**
     * 正则表达式
     * @param regstr 表达式字符
     * @param errorList 
     * @param value 传入字符
     * @param field 页面field 
     * @param messageId msg参数
     * @param message msg参数
     * @return 结果 
     * @see
     * @since 1.0
     */
    public static boolean regMatcher(String regstr, List<ValidMessage> errorList, String value, String field,String message) {
        if(!StringUtil.isEmpty(value)) {
            Pattern pattern = Pattern.compile(regstr);
            Matcher matcher = pattern.matcher(value);
            if (!matcher.matches()) {
                if (errorList != null) {
                    errorList.add(new ValidMessage(field,message));
                }
                return false;
            }
        }
        return true;
    }
    
    /**
     * 数字格式check
     * @param intlength 整数位长度
     * @param declength 小数位长度  
     * @param errorList 
     * @param value 传入字符
     * @param field 页面field 
     * @param message msg参数
     * @return 结果 
     * @see
     * @since 1.0
     */
    public static boolean numberFormatCheck(int intlength, int declength, 
                                            List<ValidMessage> errorList, String value, String field,
                                 String message) {
        if(StringUtil.isEmpty(value)) {
            return false;
        }
        if (value != null) {
            int iLength = intlength - declength - 1;
            String format = null;
            if(declength > 0) {
                format = "[0]" + "|" + "[-]?[1-9]\\d{0," + iLength + "}" 
                    + "|" + "[-]?[0-9]"
                    + "|" + "[-]?[1-9]\\d{0," + iLength + "}" + "[\\.]\\d{1," + declength + "}"
                    + "|" + "[-]?0[\\.]\\d{1," + declength + "}";
            } 
            else {
                format = "[-]?[1-9]\\d{0," + iLength + "}"
                    + "|" + "[-]?[0-9]";
            }
            Pattern pattern = Pattern.compile(format);
            Matcher matcher = pattern.matcher(value);
            if (!matcher.matches()) {
                if (errorList != null) {
                    errorList.add(new ValidMessage(field,message));
                }
                return false;
            }
        }
        return true;
    }
    
    /**
     * 日期格式check
     * @param formatStr 格式
     * @param errorList 
     * @param value 传入字符
     * @param field 页面field 
     * @param message msg参数
     * @return 结果 
     * @see
     * @since 1.0
     */
    public static Date dateFormatCheck(String formatStr, 
                                            List<ValidMessage> errorList, String value, String field,
                                 String message) {
        if(value != null) {
            try {
                return DateUtils.parseDate(value, formatStr);
            }
            catch (ParseException e) {
                if (errorList != null) {
                    errorList.add(new ValidMessage(field,message));
                }
                return null;
            }
        }
        return null;
    }
    
    /**
     * 数字大小check
     * @param compareValue 比较参数
     * @param errorList 
     * @param value 传入字符
     * @param field 页面field 
     * @param message msg参数
     * @return 结果 
     * @see
     * @since 1.0
     */
    public static boolean numberLargeThanCheck(BigDecimal compareValue,
                                            List<ValidMessage> errorList, BigDecimal value, String field,
                                 String message) {
        if(value != null && compareValue != null) {
            if (value.compareTo(compareValue) < 0) {
                if (errorList != null) {
                    errorList.add(new ValidMessage(field,message));
                }
                return false;
            }
        }
        return true;
    }
    
    /**
     * 数字大小betcheck
     * @param minValue 比较参数
     * @param maxValue 比较参数
     * @param errorList 
     * @param value 传入字符
     * @param field 页面field 
     * @param message msgId参数
     * @param message msg参数
     * @return 结果 
     * @see
     * @since 1.0
     */
    public static boolean numberBetweenCheck(BigDecimal minValue, BigDecimal maxValue,
                                            List<ValidMessage> errorList, BigDecimal value, String field,
                                 String message) {
        if(minValue != null && maxValue != null && value != null) {
            if (value.compareTo(minValue) < 0 || value.compareTo(maxValue) > 0) {
                if (errorList != null) {
                    errorList.add(new ValidMessage(field,message));
                }
                return false;
            }
        }
        return true;
    }
    
    /**
     * 必须相等
     * @param eqValue 
     * @param errorList 
     * @param value 传入字符
     * @param field 页面field 
     * @param msgId msg参数
     * @param labelResource msg参数
     * @return 结果
     * @see
     * @since 1.0
     */
    public static boolean mustEquals(Object eqValue, List<ValidMessage> errorList, Object value, String field, String message){
        if(eqValue != null && StringUtil.isNotEmpty(eqValue)) {
            if(value != eqValue && !eqValue.equals(value)) {
                if (errorList != null) {
                    errorList.add(new ValidMessage(field,message));
                }
                return false;
            }
        }
        return true;
    }
    
    /**
     * 日期大小check 比较参数 <=
     * @param compareValue 比较参数
     * @param errorList 
     * @param value 传入字符
     * @param field 页面field 
     * @param message msg参数
     * @return 结果 
     * @see
     * @since 1.0
     */
    public static boolean dateLargeThanCheck(Date compareValue,
                                            List<ValidMessage> errorList, Date value, String field,
                                 String message) {
        if(value != null && compareValue != null) {
            if (value.compareTo(compareValue) < 0) {
                if (errorList != null) {
                    errorList.add(new ValidMessage(field,message));
                }
                return false;
            }
        }
        return true;
    }
    
    /**
     * 参数列表check(单选)
     * @param code 参数
     * @param errorList 
     * @param value 传入字符
     * @param field 页面field 
     * @param labelResource msg参数
     * @return 结果 
     * @see
     * @since 1.0
     */
    public static boolean codeListSimpleCheck(DictCode code, List<ValidMessage> errorList, String value, String field,
                                 String message) {
        if(!StringUtil.isEmpty(value)) {
            if (SysDataUtil.getNameFromDataDict(code, value) == null ) {
                if (errorList != null) {
                    errorList.add(new ValidMessage(field,message));
                }
                return false;
            }
        }
        return true;
    }
 // etliu 20191115 add start
    /**
     * 根据数据类型，读取检查规则
     * 根据读取的检查规则，检查数据列表，对于某条数据有一个规则不满足，继续检查其它规则，即每条数据都进行全部规则检查
     * @param listData 被检查的数据列表，错误的数据被移出
     * @param datatype 数据类型
     * @param keyItemNameList 主键字段列表
     * @return 检查结果
     */
    public static List<ExcelValidDto> checkDataList(List<Map<String, String>> listData, String datatype, List<String> keyItemNameList) {
        if (StringUtil.isEmptyList(listData) || StringUtil.isBlank(datatype)) {
            return null;
        }

        DataCheckService dataCheckService = SpringContextUtil.getBean(DataCheckService.class);
        List<CheckRule> listRules = dataCheckService.queryCheckRules(datatype);
        
        List<ExcelValidDto> checkResultList = new ArrayList<ExcelValidDto>();
        ExcelValidDto excelValidDto = null;
        List<String> keyItemValueList = null;
        String ERRMSG = null;
        int rowNo = 2;
        List<Map<String, String>> checkList = new ArrayList<>();
        for (Map<String, String> dataMap : listData) {
            checkList.clear();
            checkList.add(dataMap);
            List<Map<String, String>> currCheckResultList = DataCheckService.checkDataList(checkList, listRules);
            if (!StringUtil.isEmptyList(currCheckResultList)) {
                excelValidDto = new ExcelValidDto();
                
                // 验证标识符（具有代表性的字段值）
                keyItemValueList = new ArrayList<>();
                for (String keyItemName : keyItemNameList) {
                    keyItemValueList.add(dataMap.get(keyItemName));
                }
                excelValidDto.setValidKey(StringUtil.joinString(keyItemValueList, GlobalConstant.COMMA));
                
                // excel的行号（导入验证用）
                excelValidDto.setRowNum(String.valueOf(rowNo));
                
                // 错误信息
                ERRMSG = currCheckResultList.get(0).get("ERRMSG");
                
                List<String> errorMsgList = StringUtil.splitToList(GlobalConstant.SEMICOLON, ERRMSG);
                List<String> newErrorMsgList = new ArrayList<>();
                for (String currMsg : errorMsgList) {
                    if(StringUtil.isBlank(currMsg)){
                        continue;
                    }
                    // 处理条件判断中包含的分号
                    if (!(currMsg.contains("[") && currMsg.contains("]")
                        && currMsg.contains("="))) {
                        continue;
                    }
                    
                    if (currMsg.startsWith("-->")) {
                        currMsg = StringUtil.substring(currMsg, 3);
                    }
                    newErrorMsgList.add(currMsg);
                }
                ERRMSG = StringUtil.joinString(newErrorMsgList, GlobalConstant.HTML_NEWLINE);

//                ERRMSG = StringUtil.replaceAll(ERRMSG, GlobalConstant.SEMICOLON, GlobalConstant.HTML_NEWLINE);
                excelValidDto.setErrorInfo(ERRMSG);

                checkResultList.add(excelValidDto);
            }
            
            rowNo++;
        }
        
        return checkResultList;
    }
    
    /**
     * 针对新建修改画面的数据校验，结合数据检查共通，返回符合画面展示用的错误消息
     * @param targetObject 待检查数据实体
     * @param datatype 数据类型
     * @return 检查结果
     */
    public static List<ValidMessage> checkDataForPageValid(Object targetObject, String datatype) {
        List<ValidMessage> validMessageList = new ArrayList<>();
        if (targetObject == null || StringUtil.isBlank(datatype)) {
            return validMessageList;
        }

        // 将targetObject转化为Map<String, String>
        Map<String, Object> resultObjMap = BeanUtil.object2Map(targetObject);
        Map<String, String> resultStrMap = new HashMap<String, String>();
        for (String key : resultObjMap.keySet()) {
            Object value = resultObjMap.get(key);
            if (value != null
                && value instanceof Date) {
                resultStrMap.put(key, DateUtil.format((Date) value, DateUtil.DEFAULT_OTHER_FORMAT));
            }
            else if (value != null) {
                resultStrMap.put(key, value.toString());
            }
            else {
                resultStrMap.put(key, null);
            }
        }

        // 根据数据类型，读取检查规则
        DataCheckService dataCheckService = SpringContextUtil.getBean(DataCheckService.class);
        List<CheckRule> listRules = dataCheckService.queryCheckRules(datatype);
        List<Map<String, String>> checkList = new ArrayList<>();
        checkList.add(resultStrMap);
        
        // 调用数据检查共通，进行数据检查
        List<Map<String, String>> currCheckResultList = DataCheckService.checkDataList(checkList, listRules);
        if (!StringUtil.isEmptyList(currCheckResultList)) {
            String ERRMSG = currCheckResultList.get(0).get("ERRMSG");
            List<String> errorMsgList = StringUtil.splitToList(GlobalConstant.SEMICOLON, ERRMSG);
            
            for (String currMsg : errorMsgList) {
                if(StringUtil.isBlank(currMsg)){
                    continue;
                }
                // 处理条件判断中包含的分号
                if (!(currMsg.contains("[") && currMsg.contains("]")
                    && currMsg.contains("="))) {
                    continue;
                }
                
                if (currMsg.startsWith("-->")) {
                    currMsg = StringUtil.substring(currMsg, 3);
                }

                String itemNameStr = StringUtil.substring(currMsg, currMsg.indexOf("[") + 1, currMsg.indexOf("]"));
                String itemName = StringUtil.splitToList("=", itemNameStr).get(0);
                ValidMessage validMessage = new ValidMessage(itemName, currMsg);
                validMessageList.add(validMessage);
            }
        }

        return validMessageList;
    }
    
    /**
     * 按照配置规则check某一个值是否合法
     * @param targetItemValue 待检查数据值
     * @param dataType 检查的对象类型
     * @param srcproperty 被检查的字段的name
     * @param checkMethod 检查方法
     * @return 检查结果（错误MSG）
     */
    public static String checkOneItem(String targetItemValue, String datatype, String srcproperty, String checkMethod) {
        DataCheckService dataCheckService = SpringContextUtil.getBean(DataCheckService.class);
        List<CheckRule> listRules = dataCheckService.queryCheckRules(datatype);
        if (StringUtil.isEmptyList(listRules)) {
            return null;
        }

        StringBuilder sb = new StringBuilder();  //记录错误信息
        for (CheckRule checkRule : listRules) {
            if (StringUtil.equals(srcproperty, checkRule.getSrcproperty())
                && StringUtil.equals(checkMethod, checkRule.getCheckmethod())) {
                String checkRst = checkOneItem(targetItemValue, checkRule);
                if (StringUtil.isNotBlank(checkRst)) {
                    sb.append(checkRst);
                }
            }
        }

        if (sb.length() > 0) {
            return sb.toString();
        }

        return null;
    }
    
    /**
     * 按照配置规则check某一个值是否合法
     * @param targetItemValue 待检查数据值
     * @param r 检查规则
     * @return 检查结果（错误MSG）
     */
    private static String checkOneItem(String targetItemValue, CheckRule r) {
        //定义检查类的方法集合：Map<检查类,Map<方法名称,方法>>
        Map<String,Map<String,Method>> mapClassMethod = new HashMap<String,Map<String,Method>>();
        Map<String,Method> mapMethod = mapClassMethod.get(r.getCheckclass());
        if ( mapMethod==null ) {
            mapMethod = new HashMap<String,Method>();
            try {
                Method[] rm = Class.forName(r.getCheckclass()).getDeclaredMethods();
                for (Method m1 : rm) {
                    mapMethod.put(m1.getName(), m1);
                }
            }
            catch (SecurityException e) {
                e.printStackTrace();
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            mapClassMethod.put(r.getCheckclass(),mapMethod);
        }
        
        boolean legal=true;  //检查结果
        StringBuilder sb = new StringBuilder();  //记录错误信息
        Map<String, String> x = new HashMap<>();
        x.put(r.getSrcproperty(), targetItemValue);
        
        // 检查逻辑，如果有前置条件，先检查条件是否满足，如果条件满足，执行数据检查，否则不执行数据检查
        // 如果没有前置条件，检查数据
        // 如果有多个前置条件，循环每个条件检查，各条件之间是与的关系
        List<CheckCondition> listConditions = r.getListConditions();
        if (listConditions != null) { // 有前置条件，则先执行前置条件是否满足
            boolean conCheck = true;
            StringBuilder sbCon = new StringBuilder();
            sbCon.append("条件:");
            for (CheckCondition c : listConditions) {
                conCheck = conCheck && DataCheckService.checkCondition(x, c, mapMethod);
                sbCon.append(c.getRemark()+";");
                if (conCheck == false) { // 条件不满足，不做数据检查
                    break;
                }
            }
            if (conCheck == true) { // 条件满足，做数据检查
                sbCon.append("-->");
                boolean b = DataCheckService.checkData(x, r, mapMethod);
                if (b == false) {
                    sb.append(sbCon.toString()+r.getRemark() + "[" + r.getSrcproperty() + "="+x.get(r.getSrcproperty())+"];");
                }
                legal &=b;
            }
        } else { // 无前置条件，做数据检查
            boolean b = DataCheckService.checkData(x, r, mapMethod);
            if (b == false) {
                sb.append(r.getRemark() + "[" + r.getSrcproperty() + "="+x.get(r.getSrcproperty())+"];");
            }
            legal &=b;
        }

        if ( legal==false ) {
            return sb.toString();
        }

        return null;
    }
    // etliu 20191115 add end
}