/*
 * 文件名：FinProductModuleInfo002Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：陈卓
 * 修改时间：2018年9月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productelement.module.validator;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.product.productelement.module.dto.FinProductModuleInfo002Dto;
import com.joyintech.tams.invest.product.productelement.module.dto.FinProductModuleInfoDto;
import com.joyintech.tams.invest.product.productelement.module.service.FinProductModuleService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 模板新建Validator
 * 
 * @author 陈卓
 * @version 1.0 2018年10月15日
 * @see FinProductModuleInfo002Validator
 * @since 1.0
 */
@Component
public class FinProductModuleInfo002Validator implements BeanValidator {

	/**
	 * FinProductModuleService
	 */
	@Autowired
	private FinProductModuleService finProductModuleService;
	@Autowired
	private MstWorkflowCommonService mstWorkflowCommonService;

	@Override
	public List<ValidMessage> valid(Object value) {
		FinProductModuleInfo002Dto dto = (FinProductModuleInfo002Dto) value;
		List<ValidMessage> errorList = new ArrayList<ValidMessage>();
		String operateType = dto.getOperateType();
		// 新建验证产品id唯一性
		if (StringUtil.equals(operateType, "submit") || StringUtil.equals(operateType, "save")) {
			getErrors(dto, errorList);
			if (StringUtil.isNotEmpty(dto.getTemplateId()) && WorkFlowUtil.isNotFromToDoList()
					&& StringUtil.equals(dto.getPageModel(), "0")) {
				FinProductModuleInfo002Dto finProductModuleInfo002Dto = finProductModuleService
						.selectByTemplateId(dto.getTemplateId());
				if (finProductModuleInfo002Dto != null) {
					errorList.add(new ValidMessage("productModuleInfo.templateId",
							this.getMessage("FinProductModule002.templateId.already")));
				}
			}
		}
		// 流程验证
		WflValidInputParameter wflValidInputParameterIssue = new WflValidInputParameter();
		wflValidInputParameterIssue.setPageId(dto.getPageId());
		wflValidInputParameterIssue.setWorkflowId(dto.getWorkflowId());
		wflValidInputParameterIssue.setBusinessDataPk1(dto.getTemplateId());
		if (mstWorkflowCommonService.isInWflProcess(wflValidInputParameterIssue)) {
			errorList.add(new ValidMessage(getMessage("FIN_PROD_E_0077", "")));
		}

		return errorList;
	}

	private void getErrors(FinProductModuleInfo002Dto finProductModuleInfo002Dto, List<ValidMessage> errorList) {
		FinProductModuleInfoDto productModuleInfo = finProductModuleInfo002Dto.getProductModuleInfo();
		if (StringUtil.isNotEmpty(productModuleInfo)) {
			// 收益类型
			if (StringUtil.isEmpty(productModuleInfo.getProfitType())) {
				errorList.add(new ValidMessage("productModuleInfo.profitType",
						this.getMessage("FIN_PROD_E_0001", this.getMessage("FinProductModule002.profitType"))));
			}
			// 发行方式
			if (StringUtil.isEmpty(productModuleInfo.getOperationType())) {
				errorList.add(new ValidMessage("productModuleInfo.operationType",
						this.getMessage("FIN_PROD_E_0001", this.getMessage("FinProductModule001.operationType"))));
			}
			// 产品处理模式
			if (StringUtil.isEmpty(productModuleInfo.getDealMode())) {
				errorList.add(new ValidMessage("productModuleInfo.dealMode",
						this.getMessage("FIN_PROD_E_0001", this.getMessage("FinProductModule002.dealMode"))));
			}
			// 产品名称
			if (StringUtil.isEmpty(productModuleInfo.getTemplateName())) {
				errorList.add(new ValidMessage("productModuleInfo.templateName",
						this.getMessage("FIN_PROD_E_0001", this.getMessage("FinProductModule001.templetName"))));
			}
			// 收益型产品利率类型必填
			if (StringUtil.equals(productModuleInfo.getProfitType(), DictCodeList.收益类型_收益型.getValue())) {
				if (StringUtil.isEmpty(productModuleInfo.getCalcType())) {
					errorList.add(new ValidMessage("productModuleInfo.calcType",
							this.getMessage("FIN_PROD_E_0001", this.getMessage("FinProductBuild002.calcType"))));
				}
			}
		}
	}
}
