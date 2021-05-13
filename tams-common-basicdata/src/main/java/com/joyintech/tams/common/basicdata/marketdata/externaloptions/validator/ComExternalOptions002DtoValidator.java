package com.joyintech.tams.common.basicdata.marketdata.externaloptions.validator;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.basicdata.marketdata.externaloptions.dto.ComExternalOptions002Dto;
import com.joyintech.workflow.engine.WorkflowContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 姚轶文
 * @create 2019- 12-31 13:40
 */
@Component
public class ComExternalOptions002DtoValidator implements BeanValidator {


//    @Autowired
//    private MstWorkflowCommonService mstWorkflowCommonService;

    private final static Logger logger = LoggerFactory.getLogger(ComExternalOptions002DtoValidator.class);

    @Override
    public List<ValidMessage> valid(Object value) {

        ComExternalOptions002Dto dto = (ComExternalOptions002Dto)value;
        ArrayList<ValidMessage> errorList = new ArrayList<>();
        try {
//            // 判断当前流程是否存在：数据主键
//            WflValidInputParameter validParam1 = new WflValidInputParameter();
//            ProcessMark processMark1 = WorkflowContext.getProcessMarkService().getParameterByAnnotation(dto);
//            validParam1.setWorkflowId(dto.getWorkflowId());
//            validParam1.setPageId(processMark1.getPageId());
//
//            validParam1.setBusinessDataPk1(dto.getFinprodId2());
//            //validParam1.setBusinessDataPk2(dto.getDatasourceType());
//            validParam1.setBusinessDataPk3(dto.getValDate().toString());
//            if (mstWorkflowCommonService.isInWflProcess(validParam1)) {
//                // 流程存在情况
//                errorList.add(new ValidMessage(getMessage("COMMON_E_0021", "标识",
//                        StringUtils.join(Arrays.asList(dto.getFinprodId2(), dto.getValDate()), ","))));
//                return errorList;
//            }

        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return errorList;
    }
}
