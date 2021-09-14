package com.joyintech.tams.common.util;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.ChbMonthAssetRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Service
public class ChbMonthAssetRuleUtil {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * 功能:检查匹配规则是否完善
     */
    public  boolean checkRuleIsRight(boolean isIncludeNull){
        // 检查配置规则项
        for (ChbMonthAssetRule singleRule :ChbMonthAssetRule.values()){
            // 检查属性代码是否为空
            if (StringUtil.isEmpty(singleRule.getPropertyCode())){
                logger.error("请检查属性代码是否为空！");
                return false;
            }
            if (!isIncludeNull){
                // 检查配置规则代码域与配置规则值域是否为空,支持为空时请关闭此项
                if (StringUtil.isEmptyList(singleRule.getRuleCodeList())
                        || StringUtil.isEmptyList(singleRule.getRuleValueList())){
                    logger.error("请检查置规则代码域与配置规则值域是否为空！");
                    return false;
                }
            }
            // 判断配置规则代码域与配置规则值域是否大小一致，即一一对应
            if (singleRule.getRuleCodeList().size() != singleRule.getRuleValueList().size()){
                logger.error("请检查属性代码:"+singleRule.getPropertyCode()+"对应规则值域与对应规则代码域大小是否对应一致！");
                return false;
            }
        }
        return true;
    }

    /**
     * 功能:判断评级信息大小
     * @param s1 待比较评级结果
     * @param s2 评级比较标准    S2固定只能为AA+或AA-
     * @return >0 s1>s2 =0 s1 = s2 <0 s1<s2
     */
    public  int compareRating(String s1,String s2){
        if(s1.equals(s2)){
            return 0;
        }
        if(s2.equals("AA+")){
            if(s1.length()<s2.length()){
                return -1;
            }else{
                if(s1.substring(0, 3).equals(s2)||s1.substring(0, 3).equals("AAA")){
                    return 1;
                }else{
                    return -1;
                }

            }
        }else{
            if(s1.length()<s2.length()){
                return -1;
            }else{
                if(s1.substring(0, 3).equals(s2)||s1.substring(0, 3).equals("AAA")||s1.substring(0, 3).equals("AA+")){
                    return 1;
                }else{
                    return -1;
                }

            }
        }
    }

    /**
     * 根据属性，获取get方法(不区分大小写)
     * @param ob 对象
     * @param name 属性名
     * @return
     */
    public Object getGetMethod(Object ob , String name){
        Method[] m = ob.getClass().getMethods();
        for(int i = 0;i < m.length;i++){
            if(("get"+name).toLowerCase().equals(m[i].getName().toLowerCase())){
                try {
                    return m[i].invoke(ob);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    logger.error(e.getMessage(),e);
                    return null;
                }
            }
        }
        return null;
    }
}
