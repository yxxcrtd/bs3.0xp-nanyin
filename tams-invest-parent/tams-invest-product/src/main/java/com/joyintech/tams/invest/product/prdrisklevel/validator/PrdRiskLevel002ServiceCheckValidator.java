package com.joyintech.tams.invest.product.prdrisklevel.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.product.prdrisklevel.dto.PrdRiskLevel002Dto;



@Component
public class PrdRiskLevel002ServiceCheckValidator implements BeanValidator {

	@Override
	public List<ValidMessage> valid(Object o) {
		PrdRiskLevel002Dto dto = (PrdRiskLevel002Dto) o;
		ArrayList<ValidMessage> errorList = new ArrayList<>();

		if (!StringUtil.isNotEmpty(dto.getFinProductCode())) { // 金融产品代码
			errorList.add(new ValidMessage("finProductCode",
					getMessage("FIN_PROD_E_0225", getMessage("PrdRiskLevel001.table.finProductCode"))));
		}
		if (!StringUtil.isNotEmpty(dto.getRiskLevel())) { // 风险等级
			errorList.add(new ValidMessage("riskLevel",
					getMessage("FIN_PROD_E_0225", getMessage("PrdRiskLevel001.table.riskLevel"))));
		}
		if (!StringUtil.isNotEmpty(dto.getAjustRiskLevel())) { // 调整后风险等级
			errorList.add(new ValidMessage("ajustRiskLevel",
					getMessage("FIN_PROD_E_0225", getMessage("PrdRiskLevel001.table.ajustRiskLevel"))));
		}

		return errorList;
	}
}
