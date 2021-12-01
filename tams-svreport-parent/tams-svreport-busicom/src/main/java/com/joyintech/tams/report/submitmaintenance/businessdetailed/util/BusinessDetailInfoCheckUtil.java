/*
 * 文件名：EquityInfoCheckUtil.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：Administrator
 * 修改时间：2017年9月21日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.report.submitmaintenance.businessdetailed.util;

import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.submitmaintenance.businessdetailed.dto.EquiteBusiDetails001Dto;
import com.joyintech.tams.report.submitmaintenance.equityinfo.dto.EquityInfo001Dto;
import com.joyintech.tams.report.submitmaintenance.equityinfo.util.EquityInfoCheckUtil;
import com.joyintech.tams.report.submitmaintenance.equityinfo.validate.EquityInfoValid;
import com.joyintech.tams.report.submitmaintenance.transformrule.dto.ChbTransformRuleObjDto;
import com.joyintech.tams.report.submitmaintenance.transformrule.dto.TransformRule001Dto;
import com.joyintech.tams.report.submitmaintenance.transformrule.service.TransformRule001Service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 *
 * @author Administrator
 * @version 1.0 2017年9月21日
 * @see EquityInfoCheckUtil
 * @since 1.0
 */
public class BusinessDetailInfoCheckUtil {
    /**
     * 日志
     */
//    private static Logger logger = LoggerFactory.getLogger(EquityInfoCheckUtil.class);
    
    /**
     * 校验输入内容是否满足正则表达式 <功能详细描述>
     *
     * @param parternid 正则表达式
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
     * 按照配置表的内容，对数据进行循环生僻字转化
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * 
     */
     public static void replaceByExchangeRuleForDtl(List<ChbTransformRuleObjDto> objFileds
                                          ,Map<String,TransformRule001Dto> spzMap,List<EquiteBusiDetails001Dto> listEquiteBusiDetails001Dto) {
      
   	  for(EquiteBusiDetails001Dto recDto: listEquiteBusiDetails001Dto){
			Map<String, String> tempMap=new HashMap<String, String>();
			try {
				
				for(ChbTransformRuleObjDto objfiled : objFileds){
					boolean flag = false;
					String srcValue = "";
						
					Field fId = recDto.getClass().getDeclaredField(objfiled.getInfoProperty());	
					
					//存在转化对象的情况下，进行以下处理
					if(fId != null) {
						fId.setAccessible(true);
						String changeResule ="";
						srcValue = (String)fId.get(recDto);
						char[] chars=  srcValue.toCharArray();
						for(char cc:chars){
							String key= objfiled.getTransType() + cc;
							  if(spzMap.containsKey(key)){                	  
								  flag = true;
								  changeResule = changeResule +  spzMap.get(key).getTargetField();  
							  }else {
								  changeResule = changeResule +  cc;
							  }                       
							}
							
						if(flag) {
							tempMap.put(objfiled.getInfoProperty(),changeResule);
							
						}
					}				
				}  
				
				recDto.setTempMap(tempMap);
				
			}
			catch(Exception e) {
				throw new BusinessException("生僻字转换异常" + e.getMessage());
		    }
		  }
   	  
     }
    
    /**
     * 包含生僻字检测的白名单校验 及白名单以外的但在变形转换规则表中能找到 算检测通过
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @param value 被检查的字段值
     * @param objFileds CHB_TRANSFORM_RULE_OBJ 
     * @param spzMap CHB_TRANSFORM_RULE 表中所有的有效的变形转换规则
     * @param EquiteBusiDetails001Dto 被处理的实体类
     * @return
     */
     public static boolean whiteListCheckForDetail(String value, List<ChbTransformRuleObjDto> objFileds
                                          , Map<String, TransformRule001Dto> spzMap, EquiteBusiDetails001Dto equiteBusiDetails001Dto) {
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
                 if(equiteBusiDetails001Dto!=null){
                     if(equiteBusiDetails001Dto.getTempMap()!=null){
                    	 equiteBusiDetails001Dto.getTempMap().put(objFileds.get(0).getInfoProperty(), result);
                     }else{
                         Map<String, String> tempMap=new HashMap<String, String>();
                         tempMap.put(objFileds.get(0).getInfoProperty(), result);
                         equiteBusiDetails001Dto.setTempMap(tempMap);
                     }
                 }
             }
         }
         return flag;
     }
}