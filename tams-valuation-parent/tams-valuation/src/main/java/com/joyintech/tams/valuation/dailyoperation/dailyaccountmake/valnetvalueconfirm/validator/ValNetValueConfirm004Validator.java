/*
 * 文件名：ValNetValueConfirm004Validator.java
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

import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.dto.ValNetValueConfirm004Dto;

/**
 * 净值下发确认 - 修改验证
 * 
 * @author hechuan
 * @version 1.0 2019年10月9日
 * @see ValNetValueConfirm004Validator
 * @since 1.0
 */
@Component
public class ValNetValueConfirm004Validator implements BeanValidator {
    

    @Override
    public List<ValidMessage> valid(Object value) {
        
        //获取传递值
        ValNetValueConfirm004Dto dto = (ValNetValueConfirm004Dto)value;
        //定义返回值：验证消息
        List<ValidMessage> validMessageList = new ArrayList<ValidMessage>();
        
        if (StringUtil.equals(dto.getOperateType(), "save") || StringUtil.equals(dto.getOperateType(), "submit")) {
        	if(dto.getCapiTal().compareTo(BigDecimal.ZERO) != 0 && dto.getAssetValueEdit() != null && dto.getNetUnitValueEdit() != null){
        		//update by hechuan 2019-11-5 start
        		BigDecimal result;
        		BigDecimal num = new BigDecimal("10000"); 
        		if(StringUtil.equals(dto.getProfitTypeCode(), "03")){//货币型
        			BigDecimal assetValueEdit = dto.getAssetValueEdit().multiply(num);
        			result = assetValueEdit.divide(dto.getCapiTal(),dto.getUnitvaluedec().intValue(),BigDecimal.ROUND_HALF_UP);
        		}else{
        			result = dto.getAssetValueEdit().divide(dto.getCapiTal(),dto.getUnitvaluedec().intValue(),BigDecimal.ROUND_HALF_UP);
        		}
        		//update by hechuan 2019-11-5 end
        		if(!result.equals(dto.getNetUnitValueEdit())){
        			validMessageList.add(new ValidMessage(getMessage("VALNETVALUECONFIRM_E_003")));
        		}
        	}
		}
        
        return validMessageList;
    }
   
}
