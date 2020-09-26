/*
 * 文件名：ValRateSwapValuationInfo002DtoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年12月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.basedata.marketdata.rateswapvaluationinfo.validator;

import java.util.*;

import com.google.common.collect.Maps;
import com.jixu.common.util.DateUtil;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.valuation.basedata.marketdata.rateswapvaluationinfo.service.ValRateSwapValuationInfo002Service;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.valuation.basedata.marketdata.rateswapvaluationinfo.dto.ValRateSwapValuationInfo002Dto;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.workflow.engine.WorkflowContext;

/**
 * 利率互换估值信息 check
 * @author zhangwei
 * @version 1.0 2018年12月3日
 * @see ValRateSwapValuationInfo002DtoValidator
 * @since 1.0
 */
@Component
public class ValRateSwapValuationInfo002DtoValidator implements BeanValidator{
    
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    @Autowired
    private ValRateSwapValuationInfo002Service valRateSwapValuationInfo002Service;

    @Autowired
    private IPlatformDate platformDateImpl;

    private final static Logger logger = LoggerFactory.getLogger(ValRateSwapValuationInfo002DtoValidator.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        ValRateSwapValuationInfo002Dto dto = (ValRateSwapValuationInfo002Dto)value;
        ArrayList<ValidMessage> errorList = new ArrayList<>();

        try {
            // 判断当前流程是否存在：数据主键
            WflValidInputParameter validParam1 = new WflValidInputParameter();
            ProcessMark processMark1 = WorkflowContext.getProcessMarkService().getParameterByAnnotation(dto);
            validParam1.setWorkflowId(dto.getWorkflowId());
            validParam1.setPageId(processMark1.getPageId());

            validParam1.setBusinessDataPk1(dto.getFinprodId2());
            validParam1.setBusinessDataPk2(dto.getDatasourceType());
            validParam1.setBusinessDataPk3(dto.getValDate());
            if (mstWorkflowCommonService.isInWflProcess(validParam1)) {
                // 流程存在情况
                errorList.add(new ValidMessage(getMessage("COMMON_E_0021", "标识",
                        StringUtils.join(Arrays.asList(dto.getFinprodId2(), dto.getDatasourceType(), dto.getValDate()), ","))));
                return errorList;
            }

            if(GlobalConstant.DETAIL_PAGE_MODEL_EDIT.equals(dto.getPageModel())){
                String valDate = dto.getValDate();
                Date valTime = DateUtil.convertStr2Date2(valDate);

                Date platDate = platformDateImpl.getSysDate();

                if(DateUtil.compareTime(valTime,platDate)==-1){
                    //如果 估值日期 小于平台日 则提醒。
                    errorList.add(new ValidMessage(getMessage("VAL_COMMON_E_0072")));
                }
            }

        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return errorList;
    }

}
