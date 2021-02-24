/*
 * 文件名：CheckLibUtil.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2019年3月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.common.svreport.datacheck.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.component.svrreport.chb.chbcommoncheck.dto.CheckElement;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 
 * 个性化检查方法，实现了多个数据检查方法
 * @author zhangliang
 * @version 1.7 2021年2月23日
 * @see BaseCheckLibExt
 * @since 1.0
 */
public class BaseCheckLibExt {

    /**
     * 判断指定的字符串是否是数字
     * @param str
     * @return
     * @see
     * @since 1.0
     */
    public static boolean isNumeric(String str) {
        if (str == null || str.trim().equals(""))
            return false;
        if (str.endsWith("."))  //不想调换复杂的正则表达式，对于[3.]这种数据也认为是合法的数字
            str = str.substring(0,str.length()-1);
        return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
    }

    /**
     * 判断是否是字符数组中
     * @param data 被检查的数据
     * @param rule 检查规则实体，年:小写，月:大写，日:小写，时:大写,分:小写，秒:小写
     * @return boolean
     */
    public  static boolean inArray(Map<String,String> data, CheckElement rule) {
        String srcValue = data.get(rule.getSrcproperty());
        String compareto = rule.getCompareto();
        if ( StringUtils.isEmpty(srcValue)  && rule.allowNullable() )  //传入数据为空，且允许为空
            return true;

        // add by zhangliang 20201023 判断符号如果是!=的场合 start
        // 此处判断是为了处理condition里比较值是空的场合，对于！=也是成立的
        if (StringUtil.equals(compareto, "!=") && StringUtils.isEmpty(srcValue)){
            return true;
        }
        // add by zhangliang 20201023 判断符号如果是!=的场合 end

        if ( StringUtils.isEmpty(srcValue) )
            return false;
        String objValue = rule.getObjvalue();
        List<String> objValueList = Arrays.asList(objValue.split(","));
        if(null != objValueList){
            if("==".equals(compareto)){
                if(objValueList.contains(srcValue))
                    return true;
            }else if("!=".equals(compareto)){
                if(!objValueList.contains(srcValue))
                    return true;
            }

        }

        return false;
    }

    /**
     * 比较两个属性值相加是否超过100
     * @param data 被检查的数据
     * @param rule 检查规则实体
     * @return
     * @see
     * @since 1.0
     */
    public static boolean addPropertyCompareHun(Map<String, String> data, CheckElement rule) {
        String srcValue = data.get(rule.getSrcproperty());

        String objValue = data.get(rule.getObjvalue());

        if (isNumeric(srcValue) && isNumeric(objValue) &&
                new BigDecimal(srcValue).add(new BigDecimal(objValue)).compareTo(BigDecimal.valueOf(100)) > 0) {
            return false;
        }

        return true;
    }

    /**
     * 投资资产种类及比例特殊判断方法
     * @param data  被检查的数据
     * @param rule  检查规则实体
     * @return 检查结果
     */
    public static boolean invAssetTypeAndPercent(Map<String, String> data, CheckElement rule){
        //传入进来的数据值
        String srcValue = data.get(rule.getSrcproperty());
        //投资资产种类及比例要素的判断
        boolean  flag=true;
        String  regex="^(100|[1-9]?\\d(\\.\\d\\d?)?)%$|0$";
        if(StringUtil.isNotEmpty(srcValue)){
            //对于投资资产种类及比例要素按照";"切割成各个单元
            List<String>  invTaypeRatList=Arrays.asList(srcValue.split(GlobalConstant.SEMICOLON));
            //循环list，对于单元进行处理
            for(String invTaype:invTaypeRatList){
                //按照":"对于单元进行切割
                List<String>  invTaypePatten=Arrays.asList(invTaype.split(GlobalConstant.COLON));
                //单元切割后，不是2个部分。则格式不通过
                if(invTaypePatten!=null&&invTaypePatten.size()!=2){
                    flag=false;
                    break;
                }else{
                    //单元切割后是2个部分，获取数值和文字
                    //数值
                    String  pattenOne=invTaypePatten.get(0);
                    if(pattenOne.contains(GlobalConstant.SPLIT)){
                        //表示为区间
                        List<String>  pattenOneSection=Arrays.asList(pattenOne.split(GlobalConstant.SPLIT));
                        //校验区间
                        if(!pattenOneSection.get(0).matches(regex)||
                                !pattenOneSection.get(1).matches(regex)){
                            flag=false;
                            break;
                        }
                    }else{
                        //不是区间，单个数字百分号
                        if(!pattenOne.matches(regex)){
                            flag=false;
                            break;
                        }
                    }
                    //文字
                    //String  pattenTwo=invTaypePatten.get(1);
                }
            }
        }
        return flag;
    }

    /**
     * 根据指定的规则，判断数据指定的属性的值是否是否是特殊格式的字符串，格式使用正则表达式规则
     * @param data 被检查的数据
     * @param rule 检查规则实体
     * @return
     * @see
     * @since 1.0
     */
    public static boolean isCustomizedStringForWhitelist(Map<String, String> data, CheckElement rule) {

        String srcValue = data.get(rule.getSrcproperty());
        if ( StringUtil.isEmpty(srcValue)  && rule.allowNullable() )  //传入数据为空，且允许为空
            return true;
        if ( StringUtil.isEmpty(srcValue))
            return false;

        String format = rule.getObjvalue();
        boolean ff;
        try {
            ff= srcValue.matches(format);
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return ff;
    }
}