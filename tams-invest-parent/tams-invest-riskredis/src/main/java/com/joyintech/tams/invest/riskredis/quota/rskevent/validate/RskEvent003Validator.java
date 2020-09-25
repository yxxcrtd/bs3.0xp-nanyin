package com.joyintech.tams.invest.riskredis.quota.rskevent.validate;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.RskEventItemRule;
import com.joyintech.tams.component.business.core.dto.WflSpecialValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.invest.riskredis.quota.rskevent.dto.RskEvent003Dto;
import com.joyintech.tams.invest.riskredis.quota.rskevent.service.RskEvent001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RskEvent003Validator implements BeanValidator {

	@Autowired
	private RskEvent001Service rskEvent001Service;

	@Autowired
	private MstWorkflowCommonService mstWorkflowCommonService;

	@Override
	public List<ValidMessage> valid(Object value) {
		List<ValidMessage> msgList = new ArrayList<>();
		RskEvent003Dto dto = (RskEvent003Dto) value;
		String operateType = dto.getOperateType();
		// 新建和编辑时
		if (GlobalConstant.DETAIL_PAGE_MODEL_NEW.equals(dto.getPageModel())
				|| GlobalConstant.DETAIL_PAGE_MODEL_EDIT.equals(dto.getPageModel())) {
			WflValidInputParameter map = new WflValidInputParameter();
			map.setBusinessDataPk1(dto.getEventCode());
			if(StringUtil.isNotEmpty(dto.getWorkflowId())){
				map.setWorkflowId(dto.getWorkflowId());
			}
			map.setPageId("RskEvent002");
			if(null != mstWorkflowCommonService && mstWorkflowCommonService.isInWflProcess(map)){
				msgList.add(new ValidMessage(this.getMessage("COMMON_E_0012",dto.getEventCode())));
			}
		} else if (StringUtil.equals(operateType, "delete")) {
			// 当为移除时，判断是否有风险事件代码相同的在流程中
			List<RskEvent003Dto> removeList = dto.getSelectedItem();
			List<String> eventCodeList = new ArrayList<String>();
			WflSpecialValidInputParameter paramEventCode = new WflSpecialValidInputParameter();
			if (null != removeList && removeList.size() > 0) {
				// 校验需要删除的数据内容是否在工作流中，在工作流中则不可删除
				removeList.forEach(item -> eventCodeList.add(item.getEventCode()));
				paramEventCode.setBusinessDataPk1List(eventCodeList);
				// 当存在工作流时
				if (StringUtil.isNotEmpty(dto.getWorkflowId())) {
					paramEventCode.setWorkflowId(dto.getWorkflowId());
				}
				paramEventCode.setPageId(dto.getPageId());
			}
			if (null != mstWorkflowCommonService && mstWorkflowCommonService.isInWflProcess(paramEventCode)) {
				msgList.add(new ValidMessage(this.getMessage("BUSS_COM_E_0010", "要移除的风险事件")));
			}
		}
		return msgList;
	}

	/*
	 * 校验风险特征对象
	 */
	private void checkItem(List<ValidMessage> msgList, RskEventItemRule dto, List<String> itemCodeList) {
		// 特征值代码不能为空
		if (StringUtil.isEmpty(dto.getItemCode())) {
			msgList.add(getItemBussComE0038Msg("特征值代码"));
		}
		// 匹配方式不能为空
		if (StringUtil.isEmpty(dto.getMatchType())) {
			msgList.add(getItemBussComE0038Msg("匹配方式"));
		}
		// 特征值不能为空
		if (StringUtil.isEmpty(dto.getItemValue())) {
			msgList.add(getItemBussComE0038Msg("特征值"));
		}
		if (StringUtil.isNotEmpty(dto.getOffsetVal())) {
			// 特征值列表记录特征值偏移量不为空时，未输入偏移方向
			if (StringUtil.isEmpty(dto.getOffsetType())) {
				msgList.add(getItemBussComE0038Msg("偏移方向"));
			}
			// 特征值列表记录特征值偏移量不为空时，未输入偏移单位
			if (StringUtil.isEmpty(dto.getOffsetUnit())) {
				msgList.add(getItemBussComE0038Msg("偏移单位"));
			}
		}
		// 判断特征值代码重复
		if (itemCodeList.contains(dto.getItemCode())) {
			msgList.add(new ValidMessage(this.getMessage("BUSS_COM_E_0009", "特征值代码")));
		} else {
			itemCodeList.add(dto.getItemCode());
		}
	}

	/**
	 * 取得 错误信息
	 * 
	 * @param filed
	 *            验证字段
	 * @return 错误信息
	 * @see
	 * @since 1.0
	 */
	private ValidMessage getItemBussComE0038Msg(String filed) {
		return new ValidMessage(this.getMessage("BUSS_COM_E_0038", getMessage(filed)));
	}

}
