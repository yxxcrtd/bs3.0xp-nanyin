package com.joyintech.tams.invest.riskredis.quota.supervisionindividuation.valid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.riskredis.quota.supervisionindividuation.dto.RskIndividConditionCustomDto;
import com.joyintech.tams.invest.riskredis.quota.supervisionindividuation.service.RskSupervisionIndividuation001Service;

@Component
public class RskSupervisionIndividuation007Validator implements BeanValidator {

	@Autowired
	private RskSupervisionIndividuation001Service rskSupervisionIndividuation001Service;

	@Override
	public List<ValidMessage> valid(Object value) {
		ArrayList<ValidMessage> errorList = new ArrayList<>();
		RskIndividConditionCustomDto dto = (RskIndividConditionCustomDto)value;
		if ("save".equals(dto.getOperateType()) || "edit".equals(dto.getOperateType())){
			if (StringUtil.isEmpty(dto.getConditionName())){
				errorList.add(new ValidMessage("conditionName", getMessage("BUSS_COM_E_0038",
						getMessage("RskSupervisionIndividuation001.conditionName"))));
			}
			if (StringUtil.isEmpty(dto.getConditionItem())){
				errorList.add(new ValidMessage("conditionItem", getMessage("BUSS_COM_E_0038",
						getMessage("RskSupervisionIndividuation001.conditionItem"))));
			}
			if (StringUtil.isEmpty(dto.getConditionUnit())){
				errorList.add(new ValidMessage("conditionUnit", getMessage("BUSS_COM_E_0038",
						getMessage("RskSupervisionIndividuation001.conditionUnit"))));
			}
			if (StringUtil.isEmpty(dto.getContent())){
				errorList.add(new ValidMessage("content", getMessage("BUSS_COM_E_0038",
						getMessage("RskSupervisionIndividuation001.content"))));
			}
		}
		if ("save".equals(dto.getOperateType())){
			if(StringUtil.equals(dto.getConditionType(), BussinessDictCodeListRds.指标参数实现类型_SQL.getValue())) {
				if (StringUtil.isEmpty(dto.getContent())) {
					errorList.add(new ValidMessage("content", getMessage("BUSS_COM_E_0038",
							getMessage("RskSupervisionIndividuation001.content"))));
				}
			}
			Map<String, Object> params = new HashMap<>();
			if (StringUtil.isNotEmpty(dto.getConditionItem())){
				params.put("conditionItem",dto.getConditionItem());
				List<RskIndividConditionCustomDto> list1 = rskSupervisionIndividuation001Service.findPageForCustomized(params);
				if (list1.size() > 0){
					errorList.add(new ValidMessage(this.getMessage("自定义条件代码重复,无法提交：")+ list1.get(0).getConditionName()));
					return errorList;
				}
			}
			params.clear();
			if (StringUtil.isNotEmpty(dto.getConditionName())){
				params.put("conditionName",dto.getConditionName());
				List<RskIndividConditionCustomDto> list2 = rskSupervisionIndividuation001Service.findPageForCustomized(params);
				if (list2.size() > 0){
					errorList.add(new ValidMessage(this.getMessage("自定义条件名称重复,无法提交：")+ list2.get(0).getConditionItem()));
					return errorList;
				}
			}
		}
		if ("delete".equals(dto.getOperateType())){
			int count = rskSupervisionIndividuation001Service.checkExistsUsing(dto);
			if (count > 0){
				errorList.add(new ValidMessage(this.getMessage("该数据已被使用，无法删除")));
				return errorList;
			}
		}

        return errorList;
	}
	

}
