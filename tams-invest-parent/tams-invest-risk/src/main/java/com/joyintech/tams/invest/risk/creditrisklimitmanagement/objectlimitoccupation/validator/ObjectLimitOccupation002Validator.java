/*
 * 文件名：CreditRightsManagementValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2019年1月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.creditrisklimitmanagement.objectlimitoccupation.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.CreditLines;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.objectlimitoccupation.dto.ObjectLimitOccupationDto;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.objectlimitoccupation.service.ObjectLimitOccupation002Service;

@Component
public class ObjectLimitOccupation002Validator implements BeanValidator{
	

	/**
	 * SingleLimitOccupation002Service
	 */
	@Autowired(required = false)
	private ObjectLimitOccupation002Service objectLimitOccupation002Service;

	
	@Override
	public List<ValidMessage> valid(Object value) {
		List<ValidMessage> errorList = new ArrayList<ValidMessage>();
		ObjectLimitOccupationDto dto = (ObjectLimitOccupationDto)value;
		if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)
				|| StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
			//占用日期不能大于释放日期
			if (dto.getCdate().compareTo(dto.getRdate()) > 0) {
				errorList.add(new ValidMessage("RSK_E_0089"));
			}
		}
		if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
			//查询授信额度管理
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("customerId", dto.getCustomerId());
			param.put("creditType", dto.getCreditType());
			param.put("term", dto.getTerm());
			param.put("termUnit", dto.getTermUnit());
			List<CreditLines> list = objectLimitOccupation002Service.selectCreditLine(param);
			if (list == null || list.size() == 0) {
				errorList.add(new ValidMessage("没有对应的分项信息，请先进行额度管理或者额度导入！"));
			}
		}
		return errorList;
	}

}
