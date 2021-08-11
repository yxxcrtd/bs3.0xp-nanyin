package com.joyintech.tams.valuation.lossmodel.lossgivendefaultmanage.lossgivendefaultmaintain.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
		if(dto.getLgd()!=null&&dto.getLgd().compareTo(new BigDecimal(100))>0){
			errorList.add(new ValidMessage("LGD值不能大于100"));
		}
			
		return errorList;
	}

}
