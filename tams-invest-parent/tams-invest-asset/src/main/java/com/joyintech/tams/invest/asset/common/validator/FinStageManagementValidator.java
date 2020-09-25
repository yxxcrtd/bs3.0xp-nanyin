/*
 * 文件名：FinStageManagementValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年3月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.asset.common.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.asset.common.dto.FinStageManagementDto;

/**
 *  期次管理业务验证
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年3月14日
 * @see FinStageManagementValidator
 * @since 1.0
 */
@Component
public class FinStageManagementValidator implements BeanValidator{

    @Override
    public List<ValidMessage> valid(Object value) {
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        FinStageManagementDto dto = (FinStageManagementDto)value;
        Set<String> set = new HashSet<String>();
        dto.getSelectedItem().forEach(item ->{
            if(null==item.getVdate()){
                resultList.add(new ValidMessage("vdate",
                    getMessage("COMMON_E_0057", getMessage("FinStageManagement.vdate"))));
            }
            if(null==item.getMdate()){
                resultList.add(new ValidMessage("mdate",
                    getMessage("COMMON_E_0057", getMessage("FinStageManagement.mdate"))));
            }
            if(null==item.getIssueAmt()){
                resultList.add(new ValidMessage("issueAmt",
                    getMessage("COMMON_E_0057", getMessage("FinStageManagement.issueAmt"))));
            }
            if(null==item.getLoadRate()){
                resultList.add(new ValidMessage("loadRate",
                    getMessage("COMMON_E_0057", getMessage("FinStageManagement.loadRate"))));
            }else{
                if(item.getLoadRate().compareTo(BigDecimal.ZERO)<0){
                    resultList.add(new ValidMessage("loadRate",
                        getMessage("BUSS_COM_E_0004", getMessage("FinStageManagement.loadRate"),"0")));
                }
            }
            if(null==item.getInvestRate()){
                resultList.add(new ValidMessage("investRate",
                    getMessage("COMMON_E_0057", getMessage("FinStageManagement.investRate"))));
            }else{
                if(item.getInvestRate().compareTo(BigDecimal.ZERO)<0){
                    resultList.add(new ValidMessage("investRate",
                        getMessage("BUSS_COM_E_0004", getMessage("FinStageManagement.investRate"),"0")));
                }
            }
            if(null!=item.getBuyoutRate()){
                if(item.getBuyoutRate().compareTo(BigDecimal.ZERO)<0){
                    resultList.add(new ValidMessage("buyoutRate",
                        getMessage("BUSS_COM_E_0004", getMessage("FinStageManagement.buyoutRate"),"0")));
                }
            }
            if(null!=item.getReferRate()&&null!=item.getBuyoutRate()){
                if(item.getReferRate().compareTo(BigDecimal.ZERO)<0){
                    resultList.add(new ValidMessage("referRate",
                        getMessage("BUSS_COM_E_0004", getMessage("FinStageManagement.referRate"),"0")));
                }
            }
            if(!set.add(item.getPeriod())){
                resultList.add(new ValidMessage("",
                        getMessage("BUSS_COM_E_0006",
                                getMessage("FinStageManagement.period")
                        )));
            }
            try{
                Long.parseLong(item.getPeriod());
            }catch(Exception e){
                resultList.add(new ValidMessage("",
                        "请输入正确的期次号!",
                                getMessage("FinStageManagement.period")
                        ));
            }
            if(resultList.size()>0){
                return;
            }
        });
        return resultList;
    }
}
