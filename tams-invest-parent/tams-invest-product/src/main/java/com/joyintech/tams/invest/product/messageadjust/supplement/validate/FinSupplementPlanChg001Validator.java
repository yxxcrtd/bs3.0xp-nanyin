package com.joyintech.tams.invest.product.messageadjust.supplement.validate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.product.messageadjust.supplement.dto.FinSupplementPlanChg001Dto;
@Component
public class FinSupplementPlanChg001Validator implements BeanValidator {

    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    @Override
    public List<ValidMessage> valid(Object value) {
        FinSupplementPlanChg001Dto dto=(FinSupplementPlanChg001Dto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        Map<String,Object> map = new HashMap<String,Object>();
        if(StringUtil.equals("01", dto.getOpType())){//合并
            if(dto.getDelList()==null||dto.getDelList().size()<2){
                resultList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0215")));
            } else {
                BigDecimal lastRn = dto.getDelList().get(0).getRn();//上一個行
                for(FinSupplementPlanChg001Dto delDto:dto.getDelList()) {
                    if(StringUtil.equals(DictCodeListExt3.贴补状态_已贴补.getValue(), delDto.getSupplementStatus())){
                        map.put(this.getMessage("FIN_PROD_E_0216"), new ValidMessage(this.getMessage("FIN_PROD_E_0216")));
                    }
                     
                    if(StringUtil.equals(dto.getIsLay(), DictCodeList.是否_是.getValue())) {
                        for(FinSupplementPlanChg001Dto delDto1:dto.getDelList()) {
                             //不同款型不能合并
                            if(!StringUtil.equals(delDto.getProdStyle(), delDto1.getProdStyle())) {
                                map.put(this.getMessage("FIN_PROD_E_0226"), new ValidMessage(this.getMessage("FIN_PROD_E_0226")));      
                            }
                        }
                    } 
                    
                    if(StringUtil.equals(delDto.getIsAvailable(), DictCodeList.是否_否.getValue())) {
                        map.put(this.getMessage("FIN_PROD_E_0229"), new ValidMessage(this.getMessage("FIN_PROD_E_0229")));      
                    }
                     
                    if(delDto.getRn().compareTo(ArithmeticUtil.doAdd(lastRn,BigDecimal.ONE))>0) {
                        map.put(this.getMessage("FIN_PROD_E_0217"), new ValidMessage(this.getMessage("FIN_PROD_E_0217")));
                    }
                    lastRn = delDto.getRn();
                }
            }
             
            if(StringUtil.isNotEmpty(map.get(this.getMessage("FIN_PROD_E_0216")))) {
                resultList.add((ValidMessage)map.get(this.getMessage("FIN_PROD_E_0216")));
            }
            
            if(StringUtil.isNotEmpty(map.get(this.getMessage("FIN_PROD_E_0226")))) {
                resultList.add((ValidMessage)map.get(this.getMessage("FIN_PROD_E_0226")));
            }
             
            if(StringUtil.isNotEmpty(map.get(this.getMessage("FIN_PROD_E_0217")))) {
                resultList.add((ValidMessage)map.get(this.getMessage("FIN_PROD_E_0217")));
            }
            
            if(StringUtil.isNotEmpty(map.get(this.getMessage("FIN_PROD_E_0229")))) {
                resultList.add((ValidMessage)map.get(this.getMessage("FIN_PROD_E_0229")));
            }
        }
        else if (StringUtil.equals("03", dto.getOpType())){//提交
            if(StringUtil.isEmptyList(dto.getDataList())) {
                resultList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0223"))); 
            }
            
            if(StringUtil.equals(dto.getIsLay(), DictCodeList.是否_是.getValue())) {
                for(FinSupplementPlanChg001Dto dataDto:dto.getDataList()){
                    for(FinSupplementPlanChg001Dto dataDto1:dto.getDataList()){
                        if(DateUtil.compareDate(dataDto.getAccrDate(), dataDto1.getAccrDate()) == 0
                                && DateUtil.compareDate(dataDto.getAccrStrDate(),dataDto1.getAccrStrDate()) != 0
                                && StringUtil.equals(dataDto.getProdStyle(), dataDto1.getProdStyle())){
                            map.put(this.getMessage("FIN_PROD_E_0218"), new ValidMessage(this.getMessage("FIN_PROD_E_0218")));

                        }
                    }
                        
                }
            } 
            else {
                for(FinSupplementPlanChg001Dto dataDto:dto.getDataList()){
                    for(FinSupplementPlanChg001Dto dataDto1:dto.getDataList()){
                        if(DateUtil.compareDate(dataDto.getAccrDate(), dataDto1.getAccrDate()) == 0
                                && DateUtil.compareDate(dataDto.getAccrStrDate(),dataDto1.getAccrStrDate()) != 0){
                            map.put(this.getMessage("FIN_PROD_E_0218"), new ValidMessage(this.getMessage("FIN_PROD_E_0218")));

                        }
                    }
                        
                } 
            }

            if(StringUtil.isNotEmpty(map.get(this.getMessage("FIN_PROD_E_0218")))) {
                resultList.add((ValidMessage)map.get(this.getMessage("FIN_PROD_E_0218")));
            }

            // 提交  该产品 有流程中的 不允许提交。
            WflValidInputParameter parameter = new WflValidInputParameter();
            parameter.setPageId("FinSupplementPlanChg001");
            parameter.setBusinessDataPk3(dto.getFinprodId());
            if(mstWorkflowCommonService.isInWflProcess(parameter)){
                resultList.add(new ValidMessage(this.getMessage("BUSS_COM_MDATE_0002")));
            }

        }
        return resultList;
    }

    
}
