package com.joyintech.tams.valuation.lossmodel.lossgivendefaultmanage.lossgivendefaultmaintain.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.tams.valuation.lossmodel.lossgivendefaultmanage.lossgivendefaultmaintain.dto.ModLgdConfigDto;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;

/**
 * 投资者明细信息管理-验证类
 * @author qiangchengxi
 *
 */
@Transactional
@Component
public class LossGivenDefaultMaintain002Validator implements BeanValidator{
//	@Autowired
//	private LossGivenDefaultMaintain002Service lossGivenDefaultMaintain002Service;

	@Override
	public List<ValidMessage> valid(Object value) {
		ArrayList<ValidMessage> errorList = new ArrayList<>();
		ModLgdConfigDto dto=(ModLgdConfigDto)value;

		//校验前瞻前年度边际PD%（小数位后可保留四位，区间为【0，100】，左闭右闭）
		BigDecimal minDec = new BigDecimal (0);
		BigDecimal maxDec = new BigDecimal (100);
		if(dto.getLgd().compareTo(maxDec) > 0 || dto.getLgd().compareTo(minDec) <0){
			errorList.add(new ValidMessage("LGD%区间应为[0，100]"));
		}

		return errorList;
	}

}
