package com.joyintech.tams.invest.basicdata.mstsaledepartment.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.basicdata.mstsaledepartment.dto.MstSaleDepartmentDto;
import com.joyintech.tams.invest.basicdata.mstsaledepartment.service.MstSaleDepartmentService;

@Component
public class MstSaleDepartment001Validator implements BeanValidator {

	/**
	 * 营业部代码信息管理service注入
	 */
	@Autowired
	private MstSaleDepartmentService mstSaleDepartmentService;

	@Override
	public List<ValidMessage> valid(Object value) {
		MstSaleDepartmentDto dto = (MstSaleDepartmentDto) value;
		List<ValidMessage> resultList = new ArrayList<ValidMessage>();
		if (StringUtil.equals(dto.getOperateType(), "new")) {
			// 添加 check 主键唯一性验证
			MstSaleDepartmentDto entity = new MstSaleDepartmentDto();
			try {
				entity = mstSaleDepartmentService.selectDetail(dto);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			if (entity != null) {
				resultList.add(new ValidMessage(this.getMessage("MST_SALE_DEPARTMENT_CODE_E_0001", entity.getSaledepid())));
			}
		}
		if(!StringUtil.equals(dto.getIntertype(), DictCodeListExt3.柜台接口类型_ctp.getValue())) {
			if(!StringUtil.isNotEmpty(dto.getIsnetreturn())) {
				resultList.add(new ValidMessage("isnetreturn",this.getMessage("MST_SEAT_CODE_E_0002",this.getMessage("MstSaleDepartment001.isnetreturnName") )));
			}
			if(!StringUtil.isNotEmpty(dto.getHandletype())) {
				resultList.add(new ValidMessage("handletype",this.getMessage("MST_SEAT_CODE_E_0002",this.getMessage("MstSaleDepartment001.handletypeName") )));
			}
		}
		return resultList;
	}

}
