package com.joyintech.tams.invest.basicdata.mstseatinfo.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.basicdata.mstseatinfo.dto.MstSeatInfoDto;
import com.joyintech.tams.invest.basicdata.mstseatinfo.service.MstSeatInfoService;

@Component
public class MstSeatInfo001Validator implements BeanValidator {
	/**
	 * 席位信息管理service注入
	 */
	@Autowired
	private MstSeatInfoService mstSeatInfoService;

	@Override
	public List<ValidMessage> valid(Object value) {
		MstSeatInfoDto dto = (MstSeatInfoDto) value;
		List<ValidMessage> resultList = new ArrayList<ValidMessage>();
		if (StringUtil.equals(dto.getOperateType(), "new")) {
			// 添加 check 主键唯一性验证
			MstSeatInfoDto entity = new MstSeatInfoDto();
			try {
				entity = mstSeatInfoService.selectByPrimaryKey(dto);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			if (entity != null) {
				resultList.add(new ValidMessage(this.getMessage("MST_SEAT_CODE_E_0001", entity.getSeatId())));
			}
		}
		return resultList;
	}
}
