package com.joyintech.tams.valuation.valuationparametermanage.withholdingamortize.validator;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.valuation.valuationparametermanage.withholdingamortize.dto.ValWithholdingAmortizeSet001Dto;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.workflow.engine.WorkflowContext;

/**
 * 待摊设置 check
 * 
 * @author tanxuan
 * @version 1.0 2019年11月25日
 * @see ValWithholdingAmortizeSet001DtoValidator
 * @since 1.0
 */
@Component
public class ValWithholdingAmortizeSet001DtoValidator implements BeanValidator {

    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    private final static Logger logger = LoggerFactory.getLogger(
        ValWithholdingAmortizeSet001DtoValidator.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        ValWithholdingAmortizeSet001Dto listdto = (ValWithholdingAmortizeSet001Dto)value;
        List<ValWithholdingAmortizeSet001Dto> list = listdto.getListValWithholdingAmortizeSetDto();
        ArrayList<ValidMessage> errorList = new ArrayList<>();
        List<String> booksetIds = new ArrayList<String>();

        try {
            for (ValWithholdingAmortizeSet001Dto dto : list) {
                // 判断当前流程是否存在：数据主键
                WflValidInputParameter validParam1 = new WflValidInputParameter();
                ProcessMark processMark1 = WorkflowContext.getProcessMarkService().getParameterByAnnotation(
                    dto);
                validParam1.setWorkflowId(dto.getWorkflowId());
                validParam1.setPageId(processMark1.getPageId());

                validParam1.setBusinessDataPk1(dto.getBooksetId());
                validParam1.setBusinessDataPk2(dto.getFinprodId());
                if (mstWorkflowCommonService.isInWflProcess(validParam1)) {
                    // 流程存在情况
                    booksetIds.add(dto.getFinprodId());
                    errorList.add(new ValidMessage(
                        getMessage("COMMON_E_0021", StringUtil.join(booksetIds, ","), "已在流程中")));
                    return errorList;
                }
            }
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return errorList;
    }

}
