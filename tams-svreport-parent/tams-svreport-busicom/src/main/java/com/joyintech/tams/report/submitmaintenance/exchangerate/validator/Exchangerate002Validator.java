/*
 * 文件名：Exchangerate002Validator.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：dingzhipeng
 * 修改时间：2017年10月13日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.report.submitmaintenance.exchangerate.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.report.submitmaintenance.exchangerate.dao.Exchangerate001Mapper;
import com.joyintech.tams.report.submitmaintenance.exchangerate.dto.Exchangerate002Dto;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 * 汇率行情新建check
 * 〈功能详细描述〉
 * @author dingzhipeng
 * @version 1.0 2017年10月13日
 * @see Exchangerate002Validator
 * @since 1.0
 */
@Component
public class Exchangerate002Validator implements BeanValidator{
    /**
     * 数据dao
     */
    @Autowired
    private Exchangerate001Mapper exchangerate001Mapper;
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
        Exchangerate002Dto dto = (Exchangerate002Dto)value;
        //检查币种与折算币种是否相似
        if(StringUtil.equals(dto.getCcyOriginal(),dto.getCcyConvert())){
            errorList.add(new ValidMessage("原币种不能与折算币种相同！"));
        }
        //汇率
        BigDecimal exchangePrice = null;
        if( ! CommonUtil.isFloatNumeric(dto.getExchangePrice())){
            errorList.add(new ValidMessage("汇率必须为数值。"));
        }
        else{
            exchangePrice=new BigDecimal(dto.getExchangePrice());
            if(exchangePrice.scale()>6||(exchangePrice.precision()-exchangePrice.scale())>13){
                errorList.add(new ValidMessage(this.getMessage("REPORT_ASSETBOND_E_001","汇率","13","6")));
            }
            if(exchangePrice.compareTo(BigDecimal.ZERO)<=0){
                errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0059", "汇率","0")));
            }
        }
        //检查是否与表中数据冲突
        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
            if(exchangerate001Mapper.isExist(dto)>0){
                errorList.add(new ValidMessage("该汇率信息在系统中已存在，不能重复登录。"));
            }
        }
        return errorList;
    }

}
