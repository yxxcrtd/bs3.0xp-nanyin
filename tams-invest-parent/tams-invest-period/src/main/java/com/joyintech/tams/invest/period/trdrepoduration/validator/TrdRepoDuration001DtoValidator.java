package com.joyintech.tams.invest.period.trdrepoduration.validator;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trdrepoduration.dto.TrdRepoDuration001Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : 强成西
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.period.trdbondduration.validator
 * @Description: 撤销校验
 * @date Date : 2018年09月27日 13:57
 */
@Component
public class TrdRepoDuration001DtoValidator implements BeanValidator {

    @Autowired
    private PeriodCommonService periodCommonService;
    @Autowired
    private BussTrdTransferOrderService bussTrdTransferOrderService;
    /**
     * 平台日期
     */
    @Autowired
    private IPlatformDate platformDateImpl;
    /**
     * 平台日期
     */
    @Autowired(required = false)
    private MstWorkflowCommonService mstWorkflowCommonService;
    @Override
    public List<ValidMessage> valid(Object value) {
        TrdRepoDuration001Dto dto = (TrdRepoDuration001Dto)value;
        List<ValidMessage> errorList = new ArrayList<>();

            periodCommonService.periodCommonRevokeCheck(dto.getStatusValid(), dto.getBusiCode(), errorList);
            //只支持当日撤销
            if(dto.getSettleDate()!=null){//资金到账日与平台日期对比
                if(!dto.getSettleDate().equals(platformDateImpl.getSysDateStr())){
                    ValidMessage validMessage = new ValidMessage(
                            getMessage("TrdRepoDuration001Dtosettdate"));
                    errorList.add(validMessage);
                }
            }
            if(mstWorkflowCommonService != null){
                WflValidInputParameter wflParam = new WflValidInputParameter();
                wflParam.setPageId("TrdRepoDuration002");
                wflParam.setBusinessDataPk1(dto.getFinprodId());
                wflParam.setBusinessDataPk2(DictCodeList.共通业务类型_质押式回购存续期.getValue());
                if( mstWorkflowCommonService.isInWflProcess(wflParam)){
                    errorList.add(new ValidMessage(this.getMessage("BUSS_COM_E_0023")));
                }
            }
        return errorList;
    }


}
