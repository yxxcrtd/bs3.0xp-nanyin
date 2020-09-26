/*
 * 文件名：BusiFinExercisePlanValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.component.element.validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//import org.springframework.stereotype.Component;

import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinExercisePlan;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.component.element.dto.ValBusiFinExercisePlanDto;


/**
 *  行权计划业务验证
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月12日
 * @see ValBusiFinExercisePlanValidator
 * @since 1.0
 */
//@Component
public class ValBusiFinExercisePlanValidator implements BeanValidator{

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        ValBusiFinExercisePlanDto dto = (ValBusiFinExercisePlanDto)value;
        List<FinExercisePlan> finExercisePlanList = dto.getFinExercisePlanList();
        List<FinCashPlan> finCashPlanList = dto.getFinCashPlanList();
        Set<Date> set = new  HashSet<Date>();
        boolean execDateEqualsMdate = false;  //行权日期 必须等于一条计息结束日
		boolean execDateErrorFlg = false;  //行权日期 必须等于一条计息结束日
        if(null!=finExercisePlanList&&!finExercisePlanList.isEmpty()){
            for(FinExercisePlan entity:finExercisePlanList){
                //add  by  张年 20190903  start
                //一开始并没有含权债行权登记的相关信息，所以这两个日期非必填
                /*if(null==entity.getVdate()){
                    resultList.add(new ValidMessage("",
                        getMessage("COMMON_E_0057", getMessage("BusiFinExercisePlan.vdate"))));
                }
                if(null==entity.getMdate()){
                    resultList.add(new ValidMessage("",
                        getMessage("COMMON_E_0057", getMessage("BusiFinExercisePlan.mdate"))));
                }*/
                //add  by  张年 20190903  end
                if(StringUtil.isEmpty(entity.getExerciseType())){
                    resultList.add(new ValidMessage("",
                        getMessage("COMMON_E_0057", getMessage("ValBusiFinExercisePlan.exerciseType"))));
                }
                if(null==entity.getExerciseDate()){
                    resultList.add(new ValidMessage("",
                        getMessage("COMMON_E_0057", getMessage("ValBusiFinExercisePlan.exerciseDate"))));
                }
                if(null==entity.getActExerciseDate()){
                    resultList.add(new ValidMessage("",
                            getMessage("COMMON_E_0057", getMessage("ValBusiFinExercisePlan.actExerciseDate"))));
                }
                if(null!=entity.getExerciseDate()&&null!=entity.getMdate()){
                    if(DateUtil.compareDate(entity.getExerciseDate(), entity.getMdate())==-1){
                        resultList.add(new ValidMessage("",
                            getMessage("BUSS_COM_E_0007", 
                                getMessage("ValBusiFinExercisePlan.exerciseDate"),
                                getMessage("大于等于"),
                                getMessage("ValBusiFinExercisePlan.mdate"))));
                    }
                }
                if(null!=entity.getMdate()&&null!=entity.getVdate()){
                    if(DateUtil.compareDate(entity.getVdate(), entity.getMdate())==1){
                        resultList.add(new ValidMessage("",
                            getMessage("BUSS_COM_E_0007", 
                                getMessage("ValBusiFinExercisePlan.mdate"),
                                getMessage("大于等于"),
                                getMessage("ValBusiFinExercisePlan.vdate"))));
                    }
                }
                if(null!=entity.getExerciseDate()&&null!=dto.getMdate()){
                    if(DateUtil.compareDate(entity.getExerciseDate(), dto.getMdate())==1){
                        resultList.add(new ValidMessage("",
                            getMessage("BUSS_COM_E_0003", 
                                getMessage("ValBusiFinExercisePlan.exerciseDate"),
                                getMessage("ValBondTargetAsset002.mdate"))));
                    }
                }
                //行权日期不能重复check改变 by赵东东 20191209 start
                //行权日期不可重复
//                if(!set.add(entity.getExerciseDate())){
//                    resultList.add(new ValidMessage("",
//                        getMessage("BUSS_COM_E_0006", 
//                            getMessage("BusiFinExercisePlan.exerciseDate")
//                            )));
//                }
              //行权日期不能重复check改变 by赵东东 20191209 end
//                if(null!=finCashPlanList&&!finCashPlanList.isEmpty()){
//                    for(FinCashPlan finCashPlanEntity:finCashPlanList){
//                        if(DateUtil.compareDate(entity.getExerciseDate(), finCashPlanEntity.getMdate())==0){
//                            execDateEqualsMdate = true;
//                            break;
//                        }
//                    }
//                }
                if (!this.isContainsDate(finCashPlanList, entity.getExerciseDate())) {
                    execDateErrorFlg = true;
                }
            }
            // 行权计划行权日在同一天可以回售和赎回 by 赵东东 20191209  by 赵东东 start
            for(int i=0;i<finExercisePlanList.size();i++) {
            	for(int j= i+1;j<finExercisePlanList.size();j++) {
            		// 判断行权计划集合中的元素是否为空
            		if(StringUtil.isNotEmpty(finExercisePlanList.get(i))&&StringUtil.isNotEmpty(finExercisePlanList.get(j))) {
            	     // 判断元素中的行权日是否为空
            		if(StringUtil.isNotEmpty(finExercisePlanList.get(i).getExerciseDate())&&StringUtil.isNotEmpty(finExercisePlanList.get(j).getExerciseDate())) {
            		if(finExercisePlanList.get(i).getExerciseDate().equals(finExercisePlanList.get(j).getExerciseDate())) {
            			//如果两个行权计划的行权日相同并且两者的行权方式不分别为赎回和回售时，报出行权日不能重复
            			if(!((StringUtil.equals(finExercisePlanList.get(i).getExerciseType(),DictCodeList.行权方式_赎回.getValue())
            					&&StringUtil.equals(finExercisePlanList.get(j).getExerciseType(),DictCodeList.行权方式_回售.getValue()))
            					||(StringUtil.equals(finExercisePlanList.get(i).getExerciseType(),DictCodeList.行权方式_回售.getValue())
                    					&&StringUtil.equals(finExercisePlanList.get(j).getExerciseType(),DictCodeList.行权方式_赎回.getValue()))
            					)) {
            				        resultList.add(new ValidMessage("",
                                    getMessage("BUSS_COM_E_0006", 
                                        getMessage("ValBusiFinExercisePlan.exerciseDate"))));
            			}
            			
            		}
            	}
            }
            }
            }
           // 行权计划行权日在同一天可以回售和赎回 by 赵东东 20191209  by 赵东东 end
            if(execDateErrorFlg){
                resultList.add(new ValidMessage("",
                    getMessage("FIN_ASSET_E_0017", 
                        getMessage("ValBusiFinExercisePlan.exerciseDate"),
                        getMessage("BUSS_COM_E_0003"),
                        getMessage("ValBondTargetAsset002.mdate"))));
            }
        }
        return resultList;
    }

    /**
     * 行权日期是否包含在付息计划的某个计息结束日
     * 
     * @param finCashPlanList 付息计划
     * @param tempDate 行权日期
     * @return 结果
     * @see
     * @since 1.0
     */
    private boolean isContainsDate(List<FinCashPlan> finCashPlanList, Date tempDate) {
        if (null != finCashPlanList && !finCashPlanList.isEmpty()) {
            for (FinCashPlan finCashPlanEntity : finCashPlanList) {
                if (DateUtil.compareDate(tempDate, finCashPlanEntity.getMdate()) == 0) {
                    return true;
                }
            }
        }

        return false;
    }

}
