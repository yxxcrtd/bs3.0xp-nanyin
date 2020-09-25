package com.joyintech.tams.invest.period.trdoptionduration.validator;

import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.period.common.validator.PeriodCommonValidator;
import com.joyintech.tams.invest.period.trddepoduration.dto.TrdDepoDuration003Dto;
import com.joyintech.tams.invest.period.trddepoduration.service.TrdDepoDuration001Service;
import com.joyintech.tams.invest.period.trdoptionduration.dto.OptionDurationDetailDto;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.invest.period.trdoptionduration.service.TrdOptionDuration001Service;
import com.joyintech.tams.invest.period.trdoptionduration.service.TrdOptionDuration002Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import com.joyintech.tams.invest.period.trdoptionduration.dto.TrdOptionDurationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class TrdOptionDurationValidator implements BeanValidator {

    /**
     * service
     */
    @Autowired(required = false)
    private MstWorkflowCommonService mstWorkflowCommonServie;
    @Autowired(required = false)
    private TrdOptionDuration002Service trdOptionDuration002Service;
    @Autowired(required = false)
    private TrdOptionDuration001Service trdOptionDuration001Service;
    @Autowired
    private IPlatformDate iPlatformDate;
    /**
     * 数据service
     */
    @Override
    public List<ValidMessage> valid(Object value) {
    	TrdOptionDurationDto dto = (TrdOptionDurationDto)value;
        List<ValidMessage> messageList = new ArrayList<>();

        //check流程中（防止重复保存）
        WflValidInputParameter wflValidInputParameter = new WflValidInputParameter();
      /*  if(StringUtil.isNotEmpty(dto.getXqType())){
            if(dto.getXqType().equals("FQXQ")){
                wflValidInputParameter.setPageId("TrdOptionDuration003");
            }else{
                wflValidInputParameter.setPageId("TrdOptionDuration002");
            }
        }else{
            if(StringUtil.isNotEmpty(dto.getBusiCode())){
                if(dto.getBusiCode().startsWith("FQXQ")){
                    wflValidInputParameter.setPageId("TrdOptionDuration003");
                }else{
                    wflValidInputParameter.setPageId("TrdOptionDuration002");
                }
            }
        }*/
//            设置前置交易编号
       /* wflValidInputParameter.setBusinessDataPk1(dto.getTotleTradeId());
        if(StringUtil.isNotEmpty(mstWorkflowCommonServie)&&mstWorkflowCommonServie.isInWflProcess(wflValidInputParameter)){
            messageList.add(new ValidMessage(getMessage("BUSS_COM_E_0023")));
        }*/

        // 存在流程中数据
        if (WorkFlowUtil.isNotFromToDoList()) {
        	HashMap<String,Object> params=new HashMap<String,Object>();
        	params.put("busiCode", dto.getBusiCode()+"R");
        	if(trdOptionDuration001Service.revokeCheck(params)>0){
        		messageList.add(new ValidMessage(this.getMessage("OPTION_DURATION_001")));
                return messageList;
        	}
        	Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("statusValidStr", dto.getStatusValidStr());
            paramMap.put("busiCode", dto.getpTradeId());
            paramMap.put("finprodId", dto.getFinprodId());
            OptionDurationDetailDto tempDto = trdOptionDuration002Service.getMyEntity(paramMap);
            if(tempDto.getXqDate()!=null&&tempDto.getXqDate().compareTo(iPlatformDate.getSysDate())!=0){
            	messageList.add(new ValidMessage(this.getMessage("OPTION_DURATION_002", dto.getBusiCode())));
                return messageList;
            }
        	OptionDurationDetailDto dto003 = new OptionDurationDetailDto();
        	if(dto.getBusiCode().startsWith("FQXQ")){
        		if (trdOptionDuration001Service.checkBusinessDataInFlow(dto)) {
                    messageList.add(new ValidMessage(this.getMessage("COMMON_E_0012", dto.getBusiCode())));
                    return messageList;
                }
        	}else{
            BeanUtil.copyPropertiesIgnoreNull(dto003, dto);
            if (trdOptionDuration002Service.checkBusinessDataInFlow(dto003)) {
                messageList.add(new ValidMessage(this.getMessage("COMMON_E_0012", dto.getBusiCode())));
                return messageList;
            }
        	}
            /*
            if (trdOptionDuration001Service.isInFlowDataExisted(dto.getFinprodId())) {
                ValidMessage validMessage = new ValidMessage(
                        getMessage("COMMON_E_0012", dto.getTotleTradeId()));
                messageList.add(validMessage);
            }*/
        }
        return messageList;
    }

}
