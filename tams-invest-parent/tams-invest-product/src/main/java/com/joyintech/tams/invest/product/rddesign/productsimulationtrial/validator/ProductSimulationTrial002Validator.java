/*
 * 文件名：ProductSimulationTrial002Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chendijun
 * 修改时间：2019年11月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.rddesign.productsimulationtrial.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.product.rddesign.productsimulationtrial.dto.ProductSimulationTrial002Dto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author chendijun
 * @version 1.0 2019年11月17日
 * @see ProductSimulationTrial002Validator
 * @since 1.0
 */
@Component
public class ProductSimulationTrial002Validator implements BeanValidator {

    /**
     * 平台日期组件接口
     */
    @Autowired(required=false)
    private IPlatformDate iPlatformDate;
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        // TODO Auto-generated method stub
        ProductSimulationTrial002Dto productSimulationTrial002Dto = (ProductSimulationTrial002Dto)value;
        List<ValidMessage> errorList = new ArrayList<>();
        //试算日不能为空
        if(!StringUtil.isNotEmpty(productSimulationTrial002Dto.getMockDate())) {
            ValidMessage validMessage = new ValidMessage("mockDate",
                getMessage("BUSS_COM_E_0038", getMessage("ProductSimulationTrial001.mockDate")));
            errorList.add(validMessage);
        } else {
            //测算日不能小于平台日
            Date platdate = iPlatformDate.getSysDate();
            if(DateUtil.compareDate(productSimulationTrial002Dto.getMockDate(), platdate)<0) {
                ValidMessage validMessage = new ValidMessage("mockDate",
                    getMessage("BUSS_COM_E_0004", getMessage("ProductSimulationTrial001.mockDate"),"平台日"));
                errorList.add(validMessage);  
            }
        }
        //产品设计编号不能为空
        if(!StringUtil.isNotEmpty(productSimulationTrial002Dto.getPrdDesignId())) {
            ValidMessage validMessage = new ValidMessage("prdDesignId",
                getMessage("BUSS_COM_E_0038", getMessage("ProductSimulationTrial001.prdDesignId")));
            errorList.add(validMessage);
        }
        //产品规模不能为空
        if(!StringUtil.isNotEmpty(productSimulationTrial002Dto.getPrdScale())) {
            ValidMessage validMessage = new ValidMessage("prdScale",
                getMessage("BUSS_COM_E_0038", getMessage("ProductSimulationTrial001.prdScale")));
            errorList.add(validMessage);
        }
        //产品期限不能为空
        if(!StringUtil.isNotEmpty(productSimulationTrial002Dto.getPrdPeriod())) {
            ValidMessage validMessage = new ValidMessage("prdPeriod",
                getMessage("BUSS_COM_E_0038", getMessage("ProductSimulationTrial001.prdPeriod")));
            errorList.add(validMessage);
        }
        //收益率上限不能为空
        if(!StringUtil.isNotEmpty(productSimulationTrial002Dto.getRateMax())) {
            ValidMessage validMessage = new ValidMessage("rateMax",
                getMessage("BUSS_COM_E_0038", getMessage("ProductSimulationTrial001.rateMax")));
            errorList.add(validMessage);
        }
        //收益率下限不能为空
        if(!StringUtil.isNotEmpty(productSimulationTrial002Dto.getRateMix())) {
            ValidMessage validMessage = new ValidMessage("rateMix",
                getMessage("BUSS_COM_E_0038", getMessage("ProductSimulationTrial001.rateMix")));
            errorList.add(validMessage);
        }
        
        //模拟配置资产列表不能为空
        if(StringUtil.isEmptyList(productSimulationTrial002Dto.getSimulationAssetsList())) {
            ValidMessage validMessage = new ValidMessage("",
                getMessage("BUSS_COM_E_0038", getMessage("ProductSimulationTrial003.productSimulationTrialList")));
            errorList.add(validMessage);
        }
        
        //模拟可用现金不能小于0
        if(productSimulationTrial002Dto.getMockAvailableAmt() != null 
            && BigDecimal.ZERO.compareTo(productSimulationTrial002Dto.getMockAvailableAmt())>0) {
            ValidMessage validMessage = new ValidMessage("mockAvailableAmt",
                getMessage("BUSS_COM_E_0004", getMessage("ProductSimulationTrial002.mockAvailableAmt"),"0"));
            errorList.add(validMessage);
        }
        
        return errorList;
    }

}
