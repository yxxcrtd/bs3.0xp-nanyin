package com.joyintech.tams.invest.riskredis.quota.rsksupervisionindicatorparam.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.invest.riskredis.quota.rsksupervisionindicatorparam.dto.RskSupervisionIndicatorParam006Dto;
import com.joyintech.tams.invest.riskredis.quota.rsksupervisionindicatorparam.service.RskSupervisionIndicatorParam001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;

@Component
public class RskSupervisionIndicatorParam006Validator implements BeanValidator {
	@Autowired
	private MstWorkflowCommonService mstWorkflowCommonService;
	@Autowired
	private RskSupervisionIndicatorParam001Service rskSupervisionIndicatorParamService;
	@Override
	public List<ValidMessage> valid(Object value) {
		RskSupervisionIndicatorParam006Dto dto = (RskSupervisionIndicatorParam006Dto) value;
        ArrayList<ValidMessage> errorList = new ArrayList<>();
        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
        	Map<String,Object> param = new HashMap<String,Object>();
          	param.put("paraName", dto.getParaName());
          	param.put("paraCode", dto.getParaCode());
        	if(rskSupervisionIndicatorParamService.isExistParaName(param)){
        		errorList.add(new ValidMessage(getMessage("RSKRDS_W_0069",getMessage("RskSupervisionIndicatorParam002.paraName"))));
          	}
        	if(dto!=null&&!StringUtil.isEmptyList(dto.getSelectedItem())){
        		for(RskSupervisionIndicatorParam006Dto e:dto.getSelectedItem()){
            		if(StringUtil.isEmpty(e.getContent())){
            			errorList.add(new ValidMessage("content",getMessage("BUSS_COM_E_0038",
                                getMessage("RskSupervisionIndicatorParam006.content"))));
            		}
            		if(e.getValidDate()==null){
            			errorList.add(new ValidMessage("validDate",getMessage("BUSS_COM_E_0038",
                                getMessage("RskSupervisionIndicatorParam006.validDate"))));
            		}
            		//wangyang1 风险二级法人改造 start
            		if (MultilevelDataAuthorityUtil.isEnabled()) {
            		    if(StringUtil.isEmpty(e.getInstitutionCode())){
                            errorList.add(new ValidMessage("institutionCode",getMessage("BUSS_COM_E_0038",
                                    getMessage("RskSupervisionIndicatorParam006.institutionName"))));
                        }  
                    }
            		//wangyang1 风险二级法人改造 end
            		/*if(e.getValidDate()!=null&&!StringUtil.equals(e.getUpdateFlag(), "02")&&
            				DateUtil.compareDate(e.getValidDate(), DateUtil.parse(DateUtil.format(DateUtil.platDateFromCache())))<0){
            			errorList.add(new ValidMessage(this.getMessage("BUSS_COM_E_0004",
                                getMessage("RskSupervisionIndicatorParam003.validDate") + DateUtil.format(dto.getValidDate()),
                                "平台日" + DateUtil.format(DateUtil.platDateFromCache()))));
            		}*/
            	}
        	}
        	if(StringUtil.isNotEmpty(dto.getParaCode())){
        		WflValidInputParameter parameter = new WflValidInputParameter();
        		parameter.setBusinessDataPk2(dto.getParaCode());
                if(StringUtil.isNotEmpty(dto.getWorkflowId())){
                	parameter.setWorkflowId(dto.getWorkflowId());
                }
                parameter.setPageId("RskSupervisionIndicatorParam006");
                if(null != mstWorkflowCommonService && 
                    mstWorkflowCommonService.isInWflProcess(parameter)){
                	errorList.add(
                        new ValidMessage(this.getMessage("BUSS_COM_I_0017","流程")));
                }
                return errorList;
        	}
        	//判断流程中是否存在相同的数据
        	WflValidInputParameter params = new WflValidInputParameter();
            params.setBusinessDataPk1(dto.getParaName());
            if(StringUtil.isNotEmpty(dto.getWorkflowId())){
            	params.setWorkflowId(dto.getWorkflowId());
            }
            params.setPageId("RskSupervisionIndicatorParam002");
            if(null != mstWorkflowCommonService && 
                mstWorkflowCommonService.isInWflProcess(params)){
            	errorList.add(
                    new ValidMessage(this.getMessage("RSKRDS_W_0073")));
            }
            WflValidInputParameter params2 = new WflValidInputParameter();
            params2.setBusinessDataPk1(dto.getParaName());
            if(StringUtil.isNotEmpty(dto.getWorkflowId())){
            	params2.setWorkflowId(dto.getWorkflowId());
            }
            params2.setPageId("RskSupervisionIndicatorParam003");
            if(null != mstWorkflowCommonService && 
                mstWorkflowCommonService.isInWflProcess(params2)){
            	errorList.add(
                    new ValidMessage(this.getMessage("RSKRDS_W_0073")));
            }
            WflValidInputParameter params3 = new WflValidInputParameter();
            params3.setBusinessDataPk1(dto.getParaName());
            if(StringUtil.isNotEmpty(dto.getWorkflowId())){
            	params3.setWorkflowId(dto.getWorkflowId());
            }
            params3.setPageId("RskSupervisionIndicatorParam005");
            if(null != mstWorkflowCommonService && 
                mstWorkflowCommonService.isInWflProcess(params3)){
            	errorList.add(
                    new ValidMessage(this.getMessage("RSKRDS_W_0073")));
            }
            WflValidInputParameter params4 = new WflValidInputParameter();
            params4.setBusinessDataPk1(dto.getParaName());
            if(StringUtil.isNotEmpty(dto.getWorkflowId())){
            	params4.setWorkflowId(dto.getWorkflowId());
            }
            params4.setPageId("RskSupervisionIndicatorParam006");
            if(null != mstWorkflowCommonService && 
                mstWorkflowCommonService.isInWflProcess(params4)){
            	errorList.add(
                    new ValidMessage(this.getMessage("RSKRDS_W_0073")));
            }
        	
        }
		return errorList;
	}

}
