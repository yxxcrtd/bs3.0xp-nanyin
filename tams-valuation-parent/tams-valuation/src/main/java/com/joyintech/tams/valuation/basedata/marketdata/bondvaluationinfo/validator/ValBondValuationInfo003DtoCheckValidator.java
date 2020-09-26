/*
 * 文件名：ValBondValuationInfo003DtoCheckValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ZhangYangFei
 * 修改时间：2018年12月9日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.basedata.marketdata.bondvaluationinfo.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.valuation.basedata.marketdata.bondvaluationinfo.dto.ValBondValuationInfo003Dto;
import com.joyintech.tams.valuation.basedata.marketdata.bondvaluationinfo.service.ValBondValuationInfo003Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;

/**
 * 
 * 新建、修改验证

 * @author ZhangYangFei
 * @version 1.0 2018年12月9日
 * @see ValBondValuationInfo003DtoCheckValidator
 * @since 1.0
 */
@Component
public class ValBondValuationInfo003DtoCheckValidator implements BeanValidator {
    /**
     * 债券估值行情-新建、删除、修改Service
     */
    @Autowired
    private ValBondValuationInfo003Service valBondValuationInfo003Service;

    /**
     * 工作流CommonServic
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        List<ValidMessage> validMessageList = new ArrayList<ValidMessage>();
        ValBondValuationInfo003Dto dto = (ValBondValuationInfo003Dto)value;
        ValidMessage validMessage;
        List<String> pageIdList = new ArrayList<String>();
        pageIdList.add("ValBondValuationInfo001");
        pageIdList.add("ValBondValuationInfo003");
        //1.修改，判断流程是否存在修改前数据
        if (StringUtil.isNotEmpty(dto.getValueDateBfUpd()) && StringUtil.isNotEmpty(dto.getValueSourceBfUpd())) {
            WflValidInputParameter pk4  = new WflValidInputParameter();
            pk4.setPageIdList(pageIdList);
            pk4.setBusinessDataPk4(dto.getSecId()+dto.getValueSourceBfUpd()+dto.getValueDateBfUpd());
            if(mstWorkflowCommonService.isInWflProcess(pk4)){
                // 流程存在情况
                String msg = this.getMessage("ValBondValuationInfo.secId") + ":" + dto.getSecId() 
                    + "，"+ this.getMessage("ValBondValuationInfo.valueSource") + ":" + SysDataUtil.getNameFromDataDict(DictCode.估值来源, dto.getValueSourceBfUpd())
                    + "，" + this.getMessage("ValBondValuationInfo.valueDate");
                validMessage = new ValidMessage(getMessage("COMMON_E_0021", msg, dto.getValueDateBfUpd()));
                validMessage.setOrder(1);
                validMessageList.add(validMessage);
            }
        }
        //2.判断流程是否存在当前数据
        //2.1.验证pk1、pk2、pk3的数据(修改后)是否在流程中
        WflValidInputParameter pk  = new WflValidInputParameter();
        pk.setPageIdList(pageIdList);
        pk.setBusinessDataPk1(dto.getSecId());
        pk.setBusinessDataPk2(dto.getValueSource());
        pk.setBusinessDataPk3(dto.getValueDate());
        pk.setWorkflowId(dto.getWorkflowId());
        //2.2.验证pk4的数据(修改钱)是否在流程中
        WflValidInputParameter pk4  = new WflValidInputParameter();
        pk4.setPageIdList(pageIdList);
        pk4.setBusinessDataPk4(dto.getSecId()+dto.getValueSource()+dto.getValueDate());
        if(mstWorkflowCommonService.isInWflProcess(pk) || mstWorkflowCommonService.isInWflProcess(pk4)){
            // 流程存在情况
            String msg = this.getMessage("ValBondValuationInfo.secId") + ":" + dto.getSecId() 
                + "，"+ this.getMessage("ValBondValuationInfo.valueSource") + ":" + SysDataUtil.getNameFromDataDict(DictCode.估值来源, dto.getValueSource())
                + "，" + this.getMessage("ValBondValuationInfo.valueDate");
            validMessage = new ValidMessage(getMessage("COMMON_E_0021", msg, dto.getValueDate()));
            validMessage.setOrder(1);
            validMessageList.add(validMessage);
        }
        
        boolean exist = false;
        //3.修改，判断表中是否已存在相同数据
        if (StringUtil.isNotEmpty(dto.getValueDateBfUpd()) && StringUtil.isNotEmpty(dto.getValueSourceBfUpd())) {
            //需要判断修改后的数据在库中是否存在，但是需要排除数据库中的自身的数据。
            ValBondValuationInfo003Dto dto2 = valBondValuationInfo003Service.checkExistMstBondValuationInfoByKey(dto);
            //表中存在
            if(dto2 != null ){
                exist = true;
            }
        }
        //4.新建，判断表中是否已存在相同数据
        else if (!StringUtil.isNotEmpty(dto.getValueDateBfUpd()) && !StringUtil.isNotEmpty(dto.getValueSourceBfUpd())) {
            ValBondValuationInfo003Dto dto2 = valBondValuationInfo003Service.getMstBondValuationInfoByKey(dto);
            //表中存在
            if(dto2 != null ){
                exist = true;
            }
        }
        if(exist){
            validMessage = 
                new ValidMessage("["+dto.getSecId()+"]在["+dto.getValueDate()+"]已存在["
                    +SysDataUtil.getNameFromDataDict(DictCode.估值来源, dto.getValueSource())+"]行情！");
            validMessage.setOrder(0);
            validMessageList.add(validMessage);
        }
        
        return validMessageList;
    }
}
