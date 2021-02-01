package com.joyintech.tams.invest.portfolio.feeprovisionsetting.validator;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.portfolio.feeprovisionsetting.dto.FeeProvisionSetting001Dto;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.workflow.engine.WorkflowContext;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;

@Component
public class FeeProvisionSetting001Validator implements BeanValidator{
	@Autowired
	private MstWorkflowCommonService mstWorkflowCommonService;
	private final static Logger logger = LoggerFactory.getLogger(
			FeeProvisionSetting001Validator.class);
	@Override
	public List<ValidMessage> valid(Object value) {
		FeeProvisionSetting001Dto feeProvisionSetting001Dto = (FeeProvisionSetting001Dto) value;
		ArrayList<ValidMessage> validList = new ArrayList<>();

		//当操作类型为修改或撤销时，判断是否在流程中
		if (StringUtil.equals(feeProvisionSetting001Dto.getOperateType(),"modify")
				|| StringUtil.equals(feeProvisionSetting001Dto.getOperateType(),"revoke")){
			WflValidInputParameter validInputParameter = new WflValidInputParameter();
			ProcessMark processMark = WorkflowContext.getProcessMarkService().getParameterByAnnotation(feeProvisionSetting001Dto);
			validInputParameter.setWorkflowId(feeProvisionSetting001Dto.getWorkflowId());
			validInputParameter.setPageId(processMark.getPageId());
			validInputParameter.setBusinessDataPk1(feeProvisionSetting001Dto.getSeqNo());
			if (mstWorkflowCommonService.isInWflProcess(validInputParameter)){
				if (StringUtil.equals(feeProvisionSetting001Dto.getOperateType(),"modify")){
					validList.add(new ValidMessage("已存在流程中数据，不允许修改!"));
				}else if (StringUtil.equals(feeProvisionSetting001Dto.getOperateType(),"revoke")){
					validList.add(new ValidMessage("已存在流程中数据，不允许撤销!"));
				}
				return validList;
			}
			try {

			}catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		return null;
	}

}
