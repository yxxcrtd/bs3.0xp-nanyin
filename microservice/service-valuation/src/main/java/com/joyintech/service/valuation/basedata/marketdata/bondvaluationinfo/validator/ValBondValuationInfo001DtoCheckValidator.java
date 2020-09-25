/*
 * 文件名：ValBondValuationInfo001DtoCheckValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ZhangYangFei
 * 修改时间：2018年12月9日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.basedata.marketdata.bondvaluationinfo.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.systemtools.service.SystemToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.valuation.basedata.marketdata.bondvaluationinfo.dto.ValBondValuationInfo001Dto;
import com.joyintech.service.valuation.basedata.marketdata.bondvaluationinfo.service.ValBondValuationInfo001Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;

/**
 * 
 * 删除验证

 * @author ZhangYangFei
 * @version 1.0 2018年12月9日
 * @see ValBondValuationInfo001DtoCheckValidator
 * @since 1.0
 */
@Component(value="serviceValBondValuationInfo001DtoCheckValidator")
public class ValBondValuationInfo001DtoCheckValidator implements BeanValidator {
    /**
     * 债券估值行情-新建、删除、修改Service
     */
    @Autowired
    private ValBondValuationInfo001Service valBondValuationInfo001Service;
    
    /**
     * 工作流CommonServic
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    /**
     * 工作流CommonServic
     */
    @Autowired
    private SystemToolService systemToolService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        List<ValidMessage> validMessageList = new ArrayList<ValidMessage>();
        ValBondValuationInfo001Dto dto = (ValBondValuationInfo001Dto)value;
        List<ValBondValuationInfo001Dto> listDto = dto.getListValBondValuationInfo001Dto();
        ValidMessage validMessage;
        int order = 0;
        List<String> pageIdList = new ArrayList<String>();
        pageIdList.add("ValBondValuationInfo001");
        pageIdList.add("ValBondValuationInfo003");
        Map<String, String> valueMap = systemToolService.getDataDictItems(DictCode.估值来源);
        for (ValBondValuationInfo001Dto dto1 : listDto) {
            //1.验证pk1、pk2、pk3的数据是否在流程中
            WflValidInputParameter pk  = new WflValidInputParameter();
            pk.setPageIdList(pageIdList);
            pk.setBusinessDataPk1(dto1.getSecId());
            pk.setBusinessDataPk2(dto1.getValueSource());
            pk.setBusinessDataPk3(dto1.getValueDate());
            //2.验证pk4的数据是否在流程中
            WflValidInputParameter pk4  = new WflValidInputParameter();
            pk4.setPageIdList(pageIdList);
            pk4.setBusinessDataPk4(dto1.getSecId()+dto1.getValueSource()+dto1.getValueDate());
            if(mstWorkflowCommonService.isInWflProcess(pk) || mstWorkflowCommonService.isInWflProcess(pk4)){
                String msg = this.getMessage("ValBondValuationInfo.secId") + ":" + dto1.getSecId() 
                    + "，"+ this.getMessage("ValBondValuationInfo.valueSource") + ":" + valueMap.get(dto1.getValueSource())
                    + "，" + this.getMessage("ValBondValuationInfo.valueDate");
                validMessage = new ValidMessage(getMessage("COMMON_E_0021", msg, dto1.getValueDate()));
                validMessage.setOrder(order);
                order ++;
                validMessageList.add(validMessage);
            }
            //校验是否有帐套使用了指定行情数据
            boolean isInfoUsed = valBondValuationInfo001Service.isInfoUsed(dto1);
            if(isInfoUsed){
                String msg = "该估值数据已有账套使用，不允许删除！" + "</br>"
                    + this.getMessage("ValBondValuationInfo.secId") + ":" + dto1.getSecId() 
                    + "，"+ this.getMessage("ValBondValuationInfo.valueSource") + ":" + valueMap.get(dto1.getValueSource())
                    + "，" + this.getMessage("ValBondValuationInfo.valueDate")+ ":" + dto1.getValueDate();
                validMessage = new ValidMessage(msg);
                validMessage.setOrder(order);
                order ++;
                validMessageList.add(validMessage);
            }
        }
        return validMessageList;
    }
}
