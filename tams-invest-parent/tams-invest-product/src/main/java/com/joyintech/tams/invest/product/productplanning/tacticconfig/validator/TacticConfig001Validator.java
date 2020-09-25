package com.joyintech.tams.invest.product.productplanning.tacticconfig.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.invest.product.productplanning.tacticconfig.dto.TacticConfig001Dto;
import com.joyintech.tams.invest.product.productplanning.tacticconfig.dto.TacticConfig002Dto;
import com.joyintech.tams.invest.product.productplanning.tacticconfig.service.TacticConfig002Service;

/**
 * 战术配置优化删除check
 * 
 * @author Administrator
 *
 */
@Component
public class TacticConfig001Validator implements BeanValidator {

	@Autowired
	private MstWorkflowCommonService mstWorkflowCommonService;
	@Autowired
	private TacticConfig002Service tacticConfig002Service;

	@Override
	public List<ValidMessage> valid(Object value) {
		TacticConfig001Dto entity = (TacticConfig001Dto) value;
		// 验证信息
		List<ValidMessage> validList = new ArrayList<ValidMessage>();
		if (StringUtil.equals(entity.getOperateType(), "edit")
				|| StringUtil.equals(entity.getOperateType(), "delete")) {
			// 流程验证
			WflValidInputParameter wflValidInputParameterIssue = new WflValidInputParameter();
			wflValidInputParameterIssue.setPageId(entity.getPageId());
			wflValidInputParameterIssue.setWorkflowId(entity.getWorkflowId());
			wflValidInputParameterIssue.setBusinessDataPk2(entity.getPkFlag());
			if (mstWorkflowCommonService.isInWflProcess(wflValidInputParameterIssue)) {
				validList.add(new ValidMessage(getMessage("FIN_PROD_E_0077", "")));
			}
			if(StringUtil.equals(entity.getOperateType(), "delete")) {
				//修正期限连续性
				TacticConfig002Dto tacticConfig002Dto = tacticConfig002Service.selectFirstTacticConfigOpt();
				if (StringUtil.isNotEmpty(tacticConfig002Dto)) {
					if (DateUtil.compareDate(entity.getVdate(),
							tacticConfig002Dto.getVdate()) != 0) {
						validList.add(new ValidMessage(getMessage("TacticConfig001.delete.continuity")));
					}
				}
			}
		}
		return validList;
	}

}
