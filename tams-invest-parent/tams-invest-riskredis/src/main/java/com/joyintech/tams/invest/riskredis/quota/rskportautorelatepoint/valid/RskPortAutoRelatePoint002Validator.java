package com.joyintech.tams.invest.riskredis.quota.rskportautorelatepoint.valid;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.riskredis.quota.rskportautorelatepoint.dto.RskPortAutoRelatePoint002Dto;
import com.joyintech.tams.invest.riskredis.quota.rskportautorelatepoint.service.RskPortAutoRelatePoint002Service;
@Component
public class RskPortAutoRelatePoint002Validator implements BeanValidator {
	@Autowired
	private RskPortAutoRelatePoint002Service rskPortAutoRelatePoint002Service;
	@Override
	public List<ValidMessage> valid(Object value) {
		RskPortAutoRelatePoint002Dto dto = (RskPortAutoRelatePoint002Dto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)
    			||StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
			if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)&& StringUtil.equals(dto.getOperateType(), "edit")){
				if (!StringUtil.isNotEmpty(rskPortAutoRelatePoint002Service.getBussinessEntityByRuleId(dto.getRuleId()))) {
					resultList.add(new ValidMessage(this.getMessage("COMMON_E_0001")));
				}
			}else {
				if (!StringUtil.isNotEmpty(dto.getRuleName())) {
					resultList.add(new ValidMessage("ruleName",
							getMessage("COMMON_E_0057", getMessage("RskPortAutoRelatePoint002.ruleName"))));
				} else if (rskPortAutoRelatePoint002Service.isExist(dto)) {
					resultList.add(new ValidMessage(
							getMessage("RSKRDS_W_0069", "自动关联规则名称")));
				}
			}
    		if(!StringUtil.isEmptyList(dto.getSelectedItem())){
    			for(RskPortAutoRelatePoint002Dto e:dto.getSelectedItem()){
    				if(StringUtil.isEmpty(e.getClassifyDimension())){
    					resultList.add(new ValidMessage("classifyDimension",
                                getMessage("COMMON_E_0057", getMessage("RskPortAutoRelatePoint002.classifyDimension"))));
    				}
    				if(StringUtil.isEmpty(e.getClassifyDimensionVal())){
    					resultList.add(new ValidMessage("classifyDimensionVal",
                                getMessage("COMMON_E_0057", getMessage("RskPortAutoRelatePoint002.classifyDimensionVal"))));
    				}
    				if(StringUtil.isEmpty(e.getLinkSign())&&e.getParamNum()!=1){
    					resultList.add(new ValidMessage("linkSign",
                                getMessage("COMMON_E_0057", getMessage("RskPortAutoRelatePoint002.linkSign"))));
    				}
    			}
    		}
    		
    	}else{
    		if(!StringUtil.isEmptyList(dto.getSelectedItem())){
    			for(RskPortAutoRelatePoint002Dto e:dto.getSelectedItem()){
					if (!StringUtil.isNotEmpty(rskPortAutoRelatePoint002Service.getBussinessEntityByRuleId(e.getRuleId()))) {
						resultList.add(new ValidMessage(this.getMessage("COMMON_E_0001")));
					}
    				//如果规则已经绑定指标
    	    		if(rskPortAutoRelatePoint002Service.isAreadyHaveInd(e.getRuleId())){
    	    			resultList.add(new ValidMessage(
    	                        getMessage("SUPERVISION_E_0001")));
    	    		}
    			}
    		}
    		
    	}
		return resultList;
	}

}
