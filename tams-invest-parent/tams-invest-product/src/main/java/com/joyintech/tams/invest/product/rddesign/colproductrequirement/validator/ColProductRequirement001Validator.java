package com.joyintech.tams.invest.product.rddesign.colproductrequirement.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.product.rddesign.colproductrequirement.dto.ColProductRequirement001Dto;

/**
 * 产品收集删除修改验证
 * 
 * @author yaozhenguo
 * @version 1.0 2019年9月226日
 * @see ColProductRequirement001Validator
 * @since 1.0
 */
@Component
public class ColProductRequirement001Validator implements BeanValidator {

	/**
     * {@link MstWorkflowCommonService}
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
    
	@Override
	public List<ValidMessage> valid(Object value) {
		ColProductRequirement001Dto entity = (ColProductRequirement001Dto) value;
		// 验证信息
		List<ValidMessage> validList = new ArrayList<ValidMessage>();
		if (StringUtil.equals(entity.getOperateType(), "edit")
				|| StringUtil.equals(entity.getOperateType(), "delete")) {
			if (!StringUtil.equals(entity.getDataStatus(), DictCodeListExt1.有效状态_有效.getValue())) {
				 validList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0032",
		                    DictCodeListExt1.有效状态_有效.getCodeName())));
			}
			//流程验证
			WflValidInputParameter wflValidInputParameterIssue = new WflValidInputParameter();
            wflValidInputParameterIssue.setPageId(entity.getPageId());
            wflValidInputParameterIssue.setWorkflowId(entity.getWorkflowId());
            wflValidInputParameterIssue.setBusinessDataPk1(entity.getRequirementId());
            if (mstWorkflowCommonService.isInWflProcess(wflValidInputParameterIssue)) {
                validList.add(new ValidMessage(getMessage("FIN_PROD_E_0077",
                    "")));
            }
            
            //已发起初评不可操作
            if(StringUtil.equals(entity.getReviewYesno(), DictCodeListExt1.是否_是.getValue())) {
            	validList.add(new ValidMessage(getMessage("ColProductRequirement001.review.opert")));
            }
		}
		return validList;
	}

}
