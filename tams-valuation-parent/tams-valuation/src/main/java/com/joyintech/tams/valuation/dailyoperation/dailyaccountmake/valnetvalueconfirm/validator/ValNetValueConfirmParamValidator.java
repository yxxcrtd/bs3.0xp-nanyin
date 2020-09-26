/*
 * 文件名：ValNetValueConfirmParamValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年9月28日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.validator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.dto.ValNetValueConfirm001Dto;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.dto.ValNetValueConfirmParamDto;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.service.ValNetValueConfirm001Service;

/**
 * 净值下发确认 - 确认和下发操作流程验证
 * 
 * @author yangjian
 * @version 1.0 2018年9月28日
 * @see ValNetValueConfirmParamValidator
 * @since 1.0
 */
@Component
public class ValNetValueConfirmParamValidator implements BeanValidator {
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ValNetValueConfirmParamValidator.class);
    
    @Autowired
    private ValNetValueConfirm001Service valNetValueConfirm001Service;

    @Override
    public List<ValidMessage> valid(Object value) {
        
        //获取传递值
        ValNetValueConfirmParamDto paDto = (ValNetValueConfirmParamDto)value;
        
        //获取页面勾选的数据
        String data=paDto.getData();
        
        //转换为实体类
        List<ValNetValueConfirm001Dto> listdto = new ArrayList<>();
        try {
            listdto = JsonUtils.json2List(data, ValNetValueConfirm001Dto.class);
        }
        catch (IOException e) {
        	logger.error("",e);
        }
        
        
        //定义返回值：验证消息
        List<ValidMessage> validMessageList = new ArrayList<ValidMessage>();
        
        //数据验证判断
        if(listdto!=null){
            for(ValNetValueConfirm001Dto nowDto : listdto){
                //判断当前流程是否存在
                if(valNetValueConfirm001Service.checkBusinessDataInFlow(nowDto)){
                    //流程存在情况
                    validMessageList.add(new ValidMessage(getMessage("COMMON_E_0021","标识",nowDto.getFinprodId()+"（"+nowDto.getValDate()+"）"+nowDto.getValBalFlag())));
                }
            }
        }
             
        return validMessageList;
    }
   
}
