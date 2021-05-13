/*
 * 文件名：ValFxValuztionInfo002DtoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年12月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.common.basicdata.marketdata.valfxvaluztioninfo.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.joyintech.tams.common.basicdata.marketdata.valfxvaluztioninfo.dto.ComFxValuztionInfo002Dto;
import com.joyintech.workflow.engine.WorkflowContext;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;

/**
 * 外汇估值信息 check
 * @author zhangwei
 * @version 1.0 2018年12月3日
 * @see ComFxValuztionInfo002DtoValidator
 * @since 1.0
 */
@Component
public class ComFxValuztionInfo002DtoValidator implements BeanValidator{
    
    @Autowired
//    private MstWorkflowCommonService mstWorkflowCommonService;

    private final static Logger logger = LoggerFactory.getLogger(ComFxValuztionInfo002DtoValidator.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        ComFxValuztionInfo002Dto dto = (ComFxValuztionInfo002Dto)value;
        ArrayList<ValidMessage> resultList = new ArrayList<>();
        try {
        	if (StringUtil.equals(dto.getOperateType(), "delete")) {
                // 删除check
                List<ComFxValuztionInfo002Dto> list = dto.getSelectedItem();
                for (ComFxValuztionInfo002Dto mst : list) {
                	// 判断当前流程是否存在：数据主键
//                    WflValidInputParameter validParam1 = new WflValidInputParameter();
//                    ProcessMark processMark1 = WorkflowContext.getProcessMarkService().getParameterByAnnotation(mst);
//                    validParam1.setWorkflowId(mst.getWorkflowId());
//                    validParam1.setPageId(processMark1.getPageId());
//
//                    validParam1.setBusinessDataPk1(mst.getFinprodId2());
//                    validParam1.setBusinessDataPk2(mst.getDatasourceType());
//                    validParam1.setBusinessDataPk3(mst.getValDate());
//                    validParam1.setBusinessDataPk4(mst.getCcy());
//                    if (mstWorkflowCommonService.isInWflProcess(validParam1)) {
//                        // 流程存在情况
//                    	resultList.add(new ValidMessage(getMessage("COMMON_E_0021", "标识",
//                                StringUtils.join(Arrays.asList(mst.getFinprodId2(), mst.getDatasourceType(), mst.getValDate(), mst.getCcy()), ","))));
//                    }
                }
            }
        	else {
        		// 判断当前流程是否存在：数据主键
//                WflValidInputParameter validParam1 = new WflValidInputParameter();
//                ProcessMark processMark1 = WorkflowContext.getProcessMarkService().getParameterByAnnotation(dto);
//                validParam1.setWorkflowId(dto.getWorkflowId());
//                validParam1.setPageId(processMark1.getPageId());
//
//                validParam1.setBusinessDataPk1(dto.getFinprodId2());
//                validParam1.setBusinessDataPk2(dto.getDatasourceType());
//                validParam1.setBusinessDataPk3(dto.getValDate());
//                validParam1.setBusinessDataPk4(dto.getCcy());
//                if (mstWorkflowCommonService.isInWflProcess(validParam1)) {
//                    // 流程存在情况
//                	resultList.add(new ValidMessage(getMessage("COMMON_E_0021", "标识",
//                            StringUtils.join(Arrays.asList(dto.getFinprodId2(), dto.getDatasourceType(), dto.getValDate(), dto.getCcy()), ","))));
//                }
        	}
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return resultList;
    }

}
