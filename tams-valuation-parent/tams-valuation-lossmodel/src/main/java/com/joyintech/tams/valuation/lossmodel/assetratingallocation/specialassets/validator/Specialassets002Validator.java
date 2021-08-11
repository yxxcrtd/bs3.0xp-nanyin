package com.joyintech.tams.valuation.lossmodel.assetratingallocation.specialassets.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.valuation.lossmodel.assetratingallocation.specialassets.dto.ModAssetGradeSpecialConfigDto;
import com.joyintech.tams.valuation.lossmodel.assetratingallocation.specialassets.service.Specialassets002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 投资者明细信息管理-验证类
 * @author qiangchengxi
 *
 */
@Transactional
@Component
public class Specialassets002Validator implements BeanValidator{

	@Autowired
    private Specialassets002Service specialassets002Service;
	
	@Override
	public List<ValidMessage> valid(Object value) {
		ArrayList<ValidMessage> errorList = new ArrayList<>();
		ModAssetGradeSpecialConfigDto dto=(ModAssetGradeSpecialConfigDto)value;
		Map<String,Object> map=new HashMap<String,Object>();
		if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
			map.put("finprodId", dto.getFinprodId());
			String ss=specialassets002Service.check(map);
			if("Y".equals(ss)){
				errorList.add(new ValidMessage("数据库中已存在该资产代码"));
			}
		}
			
		return errorList;
	}

}
