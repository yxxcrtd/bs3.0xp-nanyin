/*
 * 文件名：EquityInfoCheckUtil.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：Administrator
 * 修改时间：2017年9月21日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.report.submitmaintenance.equityinfo.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.ChbEquHold;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.util.DateCalcUtil;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.report.interf.common.util.FileUtil;
import com.joyintech.tams.report.submitmaintenance.equityinfo.dto.EquityInfo001Dto;
import com.joyintech.tams.report.submitmaintenance.equityinfo.dto.EquityInfoDto;
import com.joyintech.tams.report.submitmaintenance.equityinfo.validate.EquityInfoValid;
import com.joyintech.tams.report.submitmaintenance.transformrule.dto.ChbTransformRuleObjDto;
import com.joyintech.tams.report.submitmaintenance.transformrule.dto.TransformRule001Dto;
import com.joyintech.tams.report.submitmaintenance.transformrule.service.TransformRule001Service;
import com.joyintech.tams.report.system.regtimepoints.dto.RegTimePoints001Dto;
import com.joyintech.tools.constant.InterfConstant;


/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 *
 * @author Administrator
 * @version 1.0 2017年9月21日
 * @see EquityInfoCheckUtil
 * @since 1.0
 */
public class EquityInfoCheckUtil {

    /**
     * 判断字符串或数字是否超出长度
     *
     * @param str 字符串
     * @param maxLength 最大长度
     * @return 比较结果
     * @see
     * @since 1.0
     */
    public static boolean isExceedLength(Object str, int maxLength) {
        if (StringUtil.isNotEmpty(str)) {
            return String.valueOf(str).length() > maxLength;
        }
        return false;
    }

    /**
     * 判断传入的值是否为空
     *
     * @param value 数据
     * @return 验证结果
     * @see
     * @since 1.0
     */
    public static boolean isEmpty(String value) {
        boolean flag = false;
        if(StringUtil.isEmpty(value)){
            flag = true;
        }
        else if(StringUtil.isEmpty(value.trim())){
            flag = true;
        }
        return flag;
    }

    /**
     * 判断数据字典是否存在工具类
     *
     * @param dictCode 传入的数据字典
     * @param code 数据字典的值
     * @return 存在false，不存在true
     * @see
     * @since 1.0
     */
    public static boolean isDictCodeExist(DictCode dictCode, String code) {
        boolean flag = false;
        if (StringUtil.isEmpty(code)) {
            flag = true;
        }
        if (code.trim().contains(StringUtil.SPACE)) {
            if (code.contains("null")) {
                flag = true;
            }
            String codeValue = code.substring(code.indexOf(" "), code.length()).trim();
            code = code.substring(0, code.indexOf(" "));
            try {
                if (StringUtil.isEmpty(SysDataUtil.getNameFromDataDict(dictCode, code))
                    || !codeValue.equals(SysDataUtil.getNameFromDataDict(dictCode, code))) {
                    flag = true;
                }
            }
            catch (Exception e) {
                flag = true;
            }

        }
        else {
            flag = true;
        }
        return flag;
    }

    /**
     * 判断数据字典是否存在工具类
     *
     * @param dictCode 传入的数据字典
     * @param code 数据字典的值
     * @return 存在false，不存在true
     * @see
     * @since 1.0
     */
    public static boolean isDictCodeKeyExist(DictCode dictCode, String code) {
        boolean flag = false;
        if (StringUtil.isEmpty(code)) {
            flag = true;
        }
        if (code.contains("null")) {
            flag = true;
        }
        if (StringUtil.isEmpty(SysDataUtil.getNameFromDataDict(dictCode, code))) {
            flag = true;
        }
        return flag;
    }
    
    /**
     * 判断数据字典是否存在工具类
     *
     * @param dictCode 传入的数据字典
     * @param code 数据字典的值
     * @return 存在false，不存在true
     * @see
     * @since 1.0
     */
//    public static boolean isDictCodeKeyExist(DictCode dictCode, String code) {
//        boolean flag = false;
//        if (StringUtil.isEmpty(code)) {
//            flag = true;
//        }
//        if (code.contains("null")) {
//            flag = true;
//        }
//        if (StringUtil.isEmpty(SvrDataUtil.getNameFromDataDict(dictCode, code))) {
//            flag = true;
//        }
//        return flag;
//    }

    /**
     * 导入或者接口传入的数据与登记表中的数据进行对比，从而判读出数据类型
     *
     * @param chbEquHoldp 登记表中的额数据
     * @param equityInfoDto 接口或者导入的数据
     * @return 数据状态 01 新建 02 识别标识修改 03 重要信息修改 04 其他信息修改
     * @see
     * @since 1.0
     */
    public static String getType(ChbEquHold chbEquHoldp, EquityInfoDto equityInfoDto) {
        if (equityInfoDto != null) {
            // 识别标识改变 数据状态为02
            if (!isEmpty(chbEquHoldp.getOrgIndentifyMark())) {
                return "02";
            } // 权益人名称”、“证件类型”、“证件号码”改变为03
            else if (!chbEquHoldp.getHolderName().equals(equityInfoDto.getHolderName())
                     || !chbEquHoldp.getPersonalIdentifyType().equals(
                         equityInfoDto.getPersonalIdentifyType())
                     || !chbEquHoldp.getInstitutionIdentifyType().equals(
                         equityInfoDto.getInstitutionIdentifyType())
                     || !chbEquHoldp.getIdentifyNumber().equals(
                         equityInfoDto.getIdentifyNumber())) {
                return "03";
            }
            else if (!chbEquHoldp.getHolderRiskPrefer().equals(equityInfoDto.getHolderRiskPrefer())
                     || !chbEquHoldp.getHolderCellphone().equals(
                         equityInfoDto.getHolderCellphone())) {
                return "04";
            }
            else {
                return "01";
            }
        }
        else {
            return "01";
        }
    }

    /**
     * 校验输入内容是否满足正则表达式 <功能详细描述>
     *
     * @param partern 正则表达式
     * @param value 需要校验的字符串
     * @return 满足返回false,不满足返回true
     * @see
     * @since 1.0
     */
    public static boolean findPatern(String parternid, String value) {
        boolean flag = true;
        Pattern pattern = Pattern.compile(parternid);
        Matcher matcher = pattern.matcher(value);
        if (matcher.find()) {
            flag = false;
        }
        return flag;
    }
    /**
     * 包含生僻字检测的白名单校验 及白名单以外的但在变形转换规则表中能找到 算检测通过
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @param value 被检查的字段值
     * @param objFileds CHB_TRANSFORM_RULE_OBJ 表中被检查字段对应的检查类型 如HOLDER_NAME 01 和 HOLDER_NAME 02
     * @param spzMap CHB_TRANSFORM_RULE 表中所有的有效的变形转换规则
     * @param equityInfo001Dto 被处理的实体类
     * @return
     */
     public static boolean whiteListCheck(String value,List<ChbTransformRuleObjDto> objFileds
                                          ,Map<String,TransformRule001Dto> spzMap,EquityInfo001Dto equityInfo001Dto) {
         boolean flag = true;
         Pattern pattern = Pattern.compile(EquityInfoValid.PATTERN_ALL);
         Matcher matcher = pattern.matcher(value);
         if (matcher.find()) {
             flag = false;
         }
         //对于白名单检查不过的且在规则对象中配置了的数据继续检查
         if(flag&&!StringUtil.isEmptyList(objFileds)){
             String result=value;//对原始值分解成单个
             for(ChbTransformRuleObjDto objfiled:objFileds){
                 //对于一个字段有2个类型的以前一次的转换后结果 重新转换
                 char[] chars=  result.toCharArray();
                 result="";
                 for(char cc:chars){
                     String key=objfiled.getTransType()+cc;
                     //仅对单个字符不能通过白名单校验的数据继续检测
                     if(findPatern(EquityInfoValid.PATTERN_ALL, ""+cc)){
                         if(spzMap.containsKey(key)){
                             flag = false;
                             result+=spzMap.get(key).getTargetField();;
                         }else{
                             TransformRule001Service transformRule001Service = 
                                 SpringContextUtil.getBean(TransformRule001Service.class);
                             transformRule001Service.genPendingData(""+cc);
                             flag = true;  
                             result+=cc;
                         }
                     }else{
                         result+=cc;
                     }
                 }
             }
             if(!flag){
                 if(equityInfo001Dto!=null){
                     if(equityInfo001Dto.getTempMap()!=null){
                         equityInfo001Dto.getTempMap().put(objFileds.get(0).getInfoProperty(), result);
                     }else{
                         Map<String, String> tempMap=new HashMap<String, String>();
                         tempMap.put(objFileds.get(0).getInfoProperty(), result);
                         equityInfo001Dto.setTempMap(tempMap);
                     }
                 }
             }
         }
         return flag;
     }
    public static void updateChcekDto(Object obj,String property,String value){
         Method m;
         try {
             m = obj.getClass()
                 .getMethod(InterfConstant.SET + FileUtil.captureName(property),value.getClass());
             m.invoke(obj, value);
             
         }
         catch (NoSuchMethodException e) {
             e.printStackTrace();
         }
         catch (SecurityException e) {
             e.printStackTrace();
         }catch (IllegalAccessException | IllegalArgumentException
             | InvocationTargetException e) {
             e.printStackTrace();
         }
     }
    public static String subDictcode(String value, String sign) {
        if (!isEmpty(value)) {
            int i = value.indexOf(sign);
            if (i > -1) {
                value = value.substring(0, i);
            }
        }
        return value;
    }

    /**
     * 权益人通过报送时点配置与数据类型生成行内报送截止日与中债报送截止日
     *
     * @param list 报送时点配置
     * @param type 数据类型 01 新增、 02 修改、03 删除
     * @param equityInfo001Dto 投资者信息
     * @return 返回dto
     * @see
     * @since 1.0
     */
    public static EquityInfo001Dto getChbEndDate(List<RegTimePoints001Dto> list, String type,
                                                 EquityInfo001Dto equityInfo001Dto) {
        // 获取当前日期
        Date date = DateCalcUtil.getEndOfDay(DateUtil.now());
        // 获取相应报表的报送时点配置
        for (RegTimePoints001Dto regTimePoints001Dto : list) {
            if ("14".equals(regTimePoints001Dto.getReportCode())) {
                // 新增时，按照报送配置中行内
                if ("01".equals(type) && "03".equals(regTimePoints001Dto.getSubmitTimetype())) {
                    // 报送配置为数据有效日期
                    if ("05".equals(regTimePoints001Dto.getSubmitRequire3())) {
                        if ("02".equals(regTimePoints001Dto.getSubmitRequire2())) {
                            equityInfo001Dto.setSubmitDeadline(DateUtil.addDay(date,
                                regTimePoints001Dto.getSubmitPointBond().intValue()));
                            equityInfo001Dto.setChbSubmitDeadline(DateUtil.addDay(date,
                                regTimePoints001Dto.getSubmitPointBank().intValue()));
                        }
                    }
                }
                // 数据修改时按照，按照配置的修改时间确定
                else if ("02".equals(type)) {
                    equityInfo001Dto.setSubmitDeadline(DateUtil.addDay(date,
                        regTimePoints001Dto.getUpdateSubmitPoint().intValue()));
                    equityInfo001Dto.setChbSubmitDeadline(DateUtil.addDay(date,
                        regTimePoints001Dto.getUpdateSubmitPoint().intValue()));
                }
                // 数据删除时，按照删除时间
                else if ("03".equals(type)) {
                    equityInfo001Dto.setSubmitDeadline(DateUtil.addDay(date,
                        regTimePoints001Dto.getDeleteSubmingPoint().intValue()));
                    equityInfo001Dto.setChbSubmitDeadline(DateUtil.addDay(date,
                        regTimePoints001Dto.getDeleteSubmingPoint().intValue()));
                }
            }
        }

        return equityInfo001Dto;
    }

    /**
     * 检查字节数是否超长（GBK编码，一个汉字两个字节）
     *
     * @param str 检查项目值
     * @param maxLength 最大长度
     * @return 检查结果
     * @see
     * @since 1.0
     */
    public static boolean checkByteLength(String str, int maxLength) {
        if (StringUtil.isNotEmpty(str)) {
            try {
                return str.getBytes("GBK").length > maxLength;
            }
            catch (UnsupportedEncodingException e) {
            }
        }
        return false;
    }

    /**
     * 获取字节数（GBK编码，一个汉字两个字节）
     *
     * @param str str
     * @return 字节数
     * @see
     * @since 1.0
     */
    public static int getByteLength(String str) {
        if (StringUtil.isNotEmpty(str)) {
            try {
                return str.getBytes("GBK").length;
            }
            catch (UnsupportedEncodingException e) {
            }
        }
        return 0;
    }
}