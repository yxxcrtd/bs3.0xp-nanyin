/*
 * 文件名：FlowForecastConfigValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chendijun
 * 修改时间：2019年8月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.forecast.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.product.forecast.dto.FinFlowForecastConfig;
 

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author chendijun
 * @version 1.0 2019年8月5日
 * @see FinFlowForecastConfigValidator
 * @since 1.0
 */
@Component
public class FinFlowForecastConfigValidator implements BeanValidator{
    
    private  static final int NUMBER_MAX_LENGTH = 30;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        // TODO Auto-generated method stub
        List<ValidMessage> validList = new ArrayList<>();
        FinFlowForecastConfig flowForecastConfig = (FinFlowForecastConfig)value;
        if(flowForecastConfig != null) {
            if(flowForecastConfig.getQueryDate() == null || !StringUtil.isNotEmpty(flowForecastConfig.getQueryDate())) {
                validList.add(new ValidMessage("queryDate", this.getMessage("VAL_COMMON_E_0002",
                    this.getMessage("FlowForecastConfig.queryDate"))));
                
            }
            
            if(!validNumberTooLang(flowForecastConfig.getReserveDetailList())||!validNumberTooLang(flowForecastConfig.getForecastList())){
                validList.add(new ValidMessage("", this.getMessage("FIN_FLOWFORECAST_E_001")));
            }
        }
        return validList;
    }
    
    private boolean validNumberTooLang(List<FinFlowForecastConfig> list){
        if(list != null && list.size()>0) {
            for(FinFlowForecastConfig flowForecastConfig : list) {
                
               if(StringUtil.isNotEmpty(flowForecastConfig.getTargetTimeone())
                   && flowForecastConfig.getTargetTimeone().precision()>NUMBER_MAX_LENGTH){
                   return false;
               }
               
               if(StringUtil.isNotEmpty(flowForecastConfig.getTargetTimetwo())
                   && flowForecastConfig.getTargetTimetwo().precision()>NUMBER_MAX_LENGTH){
                   return false;
               }
               
               if(StringUtil.isNotEmpty(flowForecastConfig.getTargetTimethree())
                   && flowForecastConfig.getTargetTimethree().precision()>NUMBER_MAX_LENGTH){
                   return false;
               }
               
               if(StringUtil.isNotEmpty(flowForecastConfig.getTargetTimefour())
                   && flowForecastConfig.getTargetTimefour().precision()>NUMBER_MAX_LENGTH){
                   return false;
               }
               
               if(StringUtil.isNotEmpty(flowForecastConfig.getTargetTimefive())
                   && flowForecastConfig.getTargetTimefive().precision()>NUMBER_MAX_LENGTH){
                   return false;
               }
               
               if(StringUtil.isNotEmpty(flowForecastConfig.getTargetTimesix())
                   && flowForecastConfig.getTargetTimesix().precision()>NUMBER_MAX_LENGTH){
                   return false;
               }
            }
            return true;
        } else {
            return true;
        }
        
    }
}
