/*
 * 文件名：TrdDepoDuration001Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dingzhipeng
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.period.trdassetlossclear.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.period.trdassetlossclear.dto.TrdAssetLossClear001Dto;
import com.joyintech.tams.invest.period.trdassetlossclear.service.TrdAssetLossClear001Service;


/**
 一句话功能简述〉
 * 〈功能详细描述〉* 〈
 * @author dingzhipeng
 * @version 1.0 2018年10月11日
 * @see TrdAssetLossClear001Validator
 * @since 1.0
 */
@Component
public class TrdAssetLossClear001Validator implements BeanValidator{
    /**
     * service
     */
    @Autowired
    private TrdAssetLossClear001Service trdAssetLossClear001Service;
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
    	TrdAssetLossClear001Dto dto = (TrdAssetLossClear001Dto)value;
        List<ValidMessage> messageList = new ArrayList<>();
        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
        	String finprodMarketId = dto.getFinprodId();
        	String clearDate =DateUtil.format(dto.getTradeDate());
        	List<TrdAssetLossClear001Dto> list = trdAssetLossClear001Service.selectDetail(finprodMarketId, clearDate);
        	if (list != null && list.size()>0){
        		messageList.add(new ValidMessage("","同一资产同一清理日只能做一次一次清理："+dto.getFinprodId()));	     	
        	}
        }
        return messageList;
    }
}
