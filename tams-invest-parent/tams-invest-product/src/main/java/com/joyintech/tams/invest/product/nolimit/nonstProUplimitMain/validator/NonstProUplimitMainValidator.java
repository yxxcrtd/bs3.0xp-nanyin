package com.joyintech.tams.invest.product.nolimit.nonstProUplimitMain.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.product.nolimit.nonstProUplimitMain.dto.NonstProUplimitMain001Dto;
import com.joyintech.tams.invest.product.nolimit.nonstProUplimitMain.service.NonstProUplimitMain001Service;
import com.joyintech.tams.invest.product.nolimit.nonstProUplimitMain.service.NonstProUplimitMain002Service;
/**
 * @program: NonstProUplimitMainValidator
 * @description: 非标比例上限维护验证类
 * @author: cxq
 * @create: 2019-12-28
 **/
@Component
public class NonstProUplimitMainValidator implements BeanValidator {
	@Autowired
	NonstProUplimitMain001Service nonstProUplimitMain001Service;
	@Autowired
	NonstProUplimitMain002Service nonstProUplimitMain002Service;

	@Override
	public List<ValidMessage> valid(Object value) {
		NonstProUplimitMain001Dto dto = (NonstProUplimitMain001Dto) value;
		List<ValidMessage> validList = new ArrayList<ValidMessage>();
		List<NonstProUplimitMain001Dto> tableList = dto.getTableList();
		
		if (StringUtil.equals(dto.getOperateType(), "submit")) {
		//非空校验  
			//生效日 
		if (!StringUtil.isNotEmpty(dto.getEffDate())) {
			validList.add(new ValidMessage("", this.getMessage("请添加生效日")));
		}
		//列表数据
		if(StringUtil.isEmptyList(tableList)) {
			validList.add(new ValidMessage("", this.getMessage("请添加数据")));
		}
		for (NonstProUplimitMain001Dto dto4 : tableList) {
			if(!StringUtil.isNotEmpty(dto4.getPrdPlanSerisId())) {
				validList.add(new ValidMessage("", this.getMessage("系列编号不能为空")));
			}
			if(!StringUtil.isNotEmpty(dto4.getNonStandardLimit())) {
				validList.add(new ValidMessage("", this.getMessage("非标比例资产上限不能为空")));
			}
		}
	}
	  if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
		// 判断时间和id是否重复
		List<NonstProUplimitMain001Dto> NonstProUplimitMain001DtoListTmp = new ArrayList<NonstProUplimitMain001Dto>();
		//列表数据是否重复
		for (NonstProUplimitMain001Dto dto2 : tableList) {
			NonstProUplimitMain001Dto tmp = new NonstProUplimitMain001Dto();
			BeanUtil.copyPropertiesIgnoreNull(tmp, dto2);
			if (tmp.getEffDate() != null && tmp.getPrdPlanSerisId() != null) {
				tmp.setEffDate(dto2.getEffDate());
				tmp.setPrdPlanSerisId(dto2.getPrdPlanSerisId());
			}
			NonstProUplimitMain001DtoListTmp.add(tmp);
		}
		//重复的话给提示
		Map<String, NonstProUplimitMain001Dto> tableMap1 = NonstProUplimitMain001DtoListTmp.stream()
				.collect(Collectors.toMap(NonstProUplimitMain001Dto::getDetailKey, a -> a, (k1, k2) -> k1));
		if (tableMap1.size() != tableList.size()) {
			validList.add(new ValidMessage("tableList", this.getMessage("系列编号不能重复")));
		}
		
		for (NonstProUplimitMain001Dto dto3 : tableList) {
			NonstProUplimitMain001Dto temp = new NonstProUplimitMain001Dto();
			temp.setEffDate(dto.getEffDate());
			temp.setPrdPlanSerisId(dto3.getPrdPlanSerisId());
			//检查数据是否存在
			Integer checkData = nonstProUplimitMain001Service.checkData(temp);
			if(checkData>0) {
				validList.add(new ValidMessage("", this.getMessage("存在已有数据")));
			}
		}
	}
		return validList;
	}

}
