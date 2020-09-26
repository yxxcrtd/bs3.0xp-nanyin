package com.joyintech.tams.valuation.basedata.marketdata.externaloptions.validator;

import com.jixu.common.util.DateUtil;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.valuation.basedata.marketdata.externaloptions.dto.ExternalOptions002Dto;
import com.joyintech.tams.valuation.basedata.marketdata.valfxvaluztioninfo.dto.ValFxValuztionInfo002Dto;
import com.joyintech.tams.valuation.basedata.marketdata.valfxvaluztioninfo.validator.ValFxValuztionInfo002DtoValidator;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.workflow.engine.WorkflowContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author 姚轶文
 * @create 2019- 12-31 13:40
 */
@Component
public class ExternalOptions002DtoValidator implements BeanValidator {


    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    @Autowired
    private IPlatformDate platformDateImpl;

    private final static Logger logger = LoggerFactory.getLogger(ValFxValuztionInfo002DtoValidator.class);

    @Override
    public List<ValidMessage> valid(Object value) {

        ExternalOptions002Dto dto = (ExternalOptions002Dto)value;
        ArrayList<ValidMessage> errorList = new ArrayList<>();
        try {
            // 判断当前流程是否存在：数据主键
            WflValidInputParameter validParam1 = new WflValidInputParameter();
            ProcessMark processMark1 = WorkflowContext.getProcessMarkService().getParameterByAnnotation(dto);
            validParam1.setWorkflowId(dto.getWorkflowId());
            validParam1.setPageId(processMark1.getPageId());

            validParam1.setBusinessDataPk1(dto.getFinprodId2());
            //validParam1.setBusinessDataPk2(dto.getDatasourceType());
            validParam1.setBusinessDataPk3(dto.getValDate().toString());
            if (mstWorkflowCommonService.isInWflProcess(validParam1)) {
                // 流程存在情况
                errorList.add(new ValidMessage(getMessage("COMMON_E_0021", "标识",
                        StringUtils.join(Arrays.asList(dto.getFinprodId2(), dto.getValDate()), ","))));
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
