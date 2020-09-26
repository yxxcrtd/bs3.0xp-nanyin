/*
 * 文件名：ValNetValueConfirmParam001Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：hechuan
 * 修改时间：2019年9月23日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.dto.ValNetValueConfirm001Dto;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.dto.ValNetValueConfirm004Dto;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.dto.ValNetValueConfirmParam001Dto;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.service.ValNetValueConfirm001Service;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.service.ValNetValueConfirm004Service;

/**
 * 净值下发确认 - 修改验证
 * 
 * @author hechuan
 * @version 1.0 2019年10月9日
 * @see ValNetValueConfirmParam001Validator
 * @since 1.0
 */
@Component
public class ValNetValueConfirmParam001Validator implements BeanValidator {
    
    @Autowired
    private ValNetValueConfirm001Service valNetValueConfirm001Service;
    @Autowired
    private ValNetValueConfirm004Service valNetValueConfirm004Service;

    @Override
    public List<ValidMessage> valid(Object value) {
        
        //获取传递值
        ValNetValueConfirmParam001Dto dto = (ValNetValueConfirmParam001Dto)value;
        //定义返回值：验证消息
        List<ValidMessage> validMessageList = new ArrayList<ValidMessage>();
        //获取页面勾选的数据
        String data = dto.getData();
        
        //仅支持对确认状态为“待确认”的净值记录进行修改
        if (StringUtil.equals(dto.getOperateType(), "edit")) {
        	if (!StringUtil.equals(dto.getConfirmStatusCode(), "0")) {
        		//validMessageList.add(new ValidMessage(getMessage("FIN_PROD_E_0077", getMessage("FinProductBuild001.issueStatusMan"))));
        		validMessageList.add(new ValidMessage(getMessage("VALNETVALUECONFIRM_E_001")));
    		}
        	if(dto.getCapiTal().compareTo(BigDecimal.ZERO) == 0){
        		validMessageList.add(new ValidMessage(getMessage("VALNETVALUECONFIRM_E_002")));
        	}
        	ValNetValueConfirm001Dto nowDto = JsonUtils.json2Object(data, ValNetValueConfirm001Dto.class);
        	ValNetValueConfirm004Dto nowDtoEdit = JsonUtils.json2Object(data, ValNetValueConfirm004Dto.class);
            if(nowDto != null){
            	//判断净值确认下发流程是否存在
                if(valNetValueConfirm001Service.checkBusinessDataInFlow(nowDto)){
                    //流程存在情况
                    validMessageList.add(new ValidMessage(getMessage("COMMON_E_0021","标识",nowDto.getFinprodId()+"（"+nowDto.getValDate()+"）"+nowDto.getValBalFlag())));
                }
                //判断净值修改流程是否存在
                if(valNetValueConfirm004Service.checkBusinessDataInFlow(nowDtoEdit)){
                    //流程存在情况
                    validMessageList.add(new ValidMessage(getMessage("COMMON_E_0021","标识",nowDto.getFinprodId()+"（"+nowDto.getValDate()+"）")));
                }
            }
		}
             
        return validMessageList;
    }
   
}
