package com.joyintech.tams.invest.product.rddesign.reportproductrequirement.validator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.product.rddesign.colproductrequirement.dto.ColProductRequirement001Dto;
import com.joyintech.tams.invest.product.rddesign.reportproductrequirement.dto.ReportProductRequirement002Dto;

/**
 * 产品收集删除修改验证
 * 
 * @author yaozhenguo
 * @version 1.0 2019年9月226日
 * @see ReportProductRequirement002Validator
 * @since 1.0
 */
@Component
public class ReportProductRequirement002Validator implements BeanValidator {

	/**
	 * {@link MstWorkflowCommonService}
	 */
	@Autowired
	private MstWorkflowCommonService mstWorkflowCommonService;

	@Override
	public List<ValidMessage> valid(Object value) {
		ReportProductRequirement002Dto entity = (ReportProductRequirement002Dto) value;
		// 验证信息
		List<ValidMessage> validList = new ArrayList<ValidMessage>();
		if (!StringUtil.equals(entity.getDataStatus(), DictCodeListExt1.有效状态_待处理.getValue())) {
			validList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0032",this.getMessage("ReportProductRequirement002.dataSatus.02"))));
		}

		return validList;
	}

}
