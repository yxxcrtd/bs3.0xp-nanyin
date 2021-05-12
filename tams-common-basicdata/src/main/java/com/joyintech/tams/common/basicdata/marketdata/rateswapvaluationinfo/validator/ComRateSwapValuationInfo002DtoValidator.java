/*
 * 文件名：ValRateSwapValuationInfo002DtoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年12月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.common.basicdata.marketdata.rateswapvaluationinfo.validator;

import java.util.*;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.basicdata.marketdata.rateswapvaluationinfo.dto.ComRateSwapValuationInfo002Dto;
import com.joyintech.tams.systemtools.service.CommonPlatformDateService;
import com.joyintech.workflow.engine.WorkflowContext;
import com.joyintech.tams.common.basicdata.marketdata.rateswapvaluationinfo.service.ComRateSwapValuationInfo002Service;
//import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
//import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;

/**
 * 利率互换估值信息 check
 * @author zhangwei
 * @version 1.0 2018年12月3日
 * @see ComRateSwapValuationInfo002DtoValidator
 * @since 1.0
 */
@Component
public class ComRateSwapValuationInfo002DtoValidator implements BeanValidator{
    
//    @Autowired
//    private MstWorkflowCommonService mstWorkflowCommonService;

    @Autowired
    private ComRateSwapValuationInfo002Service comRateSwapValuationInfo002Service;

    @Autowired
    private CommonPlatformDateService commonPlatformDateService;

    private final static Logger logger = LoggerFactory.getLogger(ComRateSwapValuationInfo002DtoValidator.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        ComRateSwapValuationInfo002Dto dto = (ComRateSwapValuationInfo002Dto)value;
        ArrayList<ValidMessage> errorList = new ArrayList<>();

        try {
            // 判断当前流程是否存在：数据主键
//            WflValidInputParameter validParam1 = new WflValidInputParameter();
//            ProcessMark processMark1 = WorkflowContext.getProcessMarkService().getParameterByAnnotation(dto);
//            validParam1.setWorkflowId(dto.getWorkflowId());
//            validParam1.setPageId(processMark1.getPageId());
//
//            validParam1.setBusinessDataPk1(dto.getFinprodId2());
//            validParam1.setBusinessDataPk2(dto.getDatasourceType());
//            validParam1.setBusinessDataPk3(dto.getValDate());
//            if (mstWorkflowCommonService.isInWflProcess(validParam1)) {
//                // 流程存在情况
//                errorList.add(new ValidMessage(getMessage("COMMON_E_0021", "标识",
//                        StringUtil.join(Arrays.asList(dto.getFinprodId2(), dto.getDatasourceType(), dto.getValDate()), ","))));
//                return errorList;
//            }

        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return errorList;
    }

}
