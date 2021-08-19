/*
 * 文件名：ValVoucherQuery001Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */
 
package com.joyintech.service.valuation.lossmodel.assetratingallocation.assetcategories.validator;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.valuation.lossmodel.assetratingallocation.assetcategories.dto.ValAssetCategories001Dto;
import com.joyintech.service.valuation.lossmodel.assetratingallocation.assetcategories.service.ValAssetCategories001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * 资产大类评级配置删除校验
 * @author weipeng
 * @version 1.0 2018年7月11日
 * @see ValAssetCategories001Validator
 * @since 1.0
 */
@Component(value="serviceAssetCategories001Validator")
public class ValAssetCategories001Validator implements BeanValidator{
    
    @Autowired
    private ValAssetCategories001Service valAssetCategories001Service;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {

        ValAssetCategories001Dto dto = (ValAssetCategories001Dto) value;

        //错误信息
        ArrayList<ValidMessage> errorList = new ArrayList<>();


        return errorList;
    }
    
}
