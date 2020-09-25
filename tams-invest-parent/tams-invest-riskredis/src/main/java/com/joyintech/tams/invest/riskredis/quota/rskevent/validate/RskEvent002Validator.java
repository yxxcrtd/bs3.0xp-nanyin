package com.joyintech.tams.invest.riskredis.quota.rskevent.validate;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.component.business.core.dto.WflSpecialValidInputParameter;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.invest.riskredis.quota.rskevent.dto.RskEvent002Dto;
import com.joyintech.tams.invest.riskredis.quota.rskevent.service.RskEvent001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.RskEvent;
import com.joyintech.tams.common.base.entity.RskEventItemRule;

@Component
public class RskEvent002Validator implements BeanValidator {

	@Autowired
	private RskEvent001Service rskEvent001Service;

	@Autowired
	private MstWorkflowCommonService mstWorkflowCommonService;

	@Override
	public List<ValidMessage> valid(Object value) {
		List<ValidMessage> msgList = new ArrayList<>();
		RskEvent002Dto dto = (RskEvent002Dto) value;
		String operateType = dto.getOperateType();
		// 新建和编辑时
		if (GlobalConstant.DETAIL_PAGE_MODEL_NEW.equals(dto.getPageModel())
				|| GlobalConstant.DETAIL_PAGE_MODEL_EDIT.equals(dto.getPageModel())) {
			/*
			 * 通用校验
			 */
			// 事件名称未输入
			if (StringUtil.isEmpty(dto.getEventName())) {
				msgList.add(getItemBussComE0038Msg("RskEvent002.eventName"));
			}
			if(!StringUtil.isEmptyList(dto.getParamGroup())){
				for(List<RskEventItemRule> paramList:dto.getParamGroup()){
					// 特征值列表为空
					if (!StringUtil.isEmptyList(paramList)) {
						List<String> itemCodeList = new ArrayList<String>();
						// 遍历特征值列表进行判断
						paramList.forEach(item -> checkItem(msgList, item, itemCodeList));
						//msgList.add(new ValidMessage(this.getMessage("RSKRDS_E_0003", "特征值列表")));
					}
				}
			}
			
			//List<RskEventItemRule> paramList = dto.getParamList();
			

			/*
			 * 校验业务数据表是否重复
			 */
			List<RskEvent> eventList = null;
			// 新建时，校验业务表是否重复
			if (GlobalConstant.DETAIL_PAGE_MODEL_NEW.equals(dto.getPageModel())) {
				eventList = rskEvent001Service.selectByEventName(dto.getEventName());
			}
			// 编辑时，校验业务表是否重复要去除自己
			if (GlobalConstant.DETAIL_PAGE_MODEL_EDIT.equals(dto.getPageModel())) {
				eventList = rskEvent001Service.selectByEventNameNotEqCode(dto.getEventName(), dto.getEventCode());
			}
			if (null == eventList || eventList.size() > 0) {
				msgList.add(new ValidMessage(this.getMessage("BUSS_COM_E_0006", dto.getEventName())));
			}
			if(StringUtil.isNotEmpty(dto.getEventCode())&&StringUtil.equals(GlobalConstant.DETAIL_PAGE_MODEL_NEW, dto.getPageModel())
					&&rskEvent001Service.isExistEventCode(dto.getEventCode())){
				msgList.add(new ValidMessage(this.getMessage("BUSS_COM_E_0006", getMessage("RskEvent002.eventCode"))));
			}
			/*
			 * 校验工作流表是否重复
			 */
			// 判断流程中是否存在相同的数据
			WflValidInputParameter paramEventName = new WflValidInputParameter();
			paramEventName.setBusinessDataPk1(dto.getEventName());
			paramEventName.setPageId(dto.getPageId());
			// 判断是否有同一个事件代码的数据
			WflValidInputParameter paramEventCode = new WflValidInputParameter();
			paramEventCode.setBusinessDataPk2(dto.getEventCode());
			paramEventCode.setPageId(dto.getPageId());
			// 当存在工作流时去除当前工作流
			if (StringUtil.isNotEmpty(dto.getWorkflowId())) {
				paramEventName.setWorkflowId(dto.getWorkflowId());
				paramEventCode.setWorkflowId(dto.getWorkflowId());
			}

			if (null != mstWorkflowCommonService && mstWorkflowCommonService.isInWflProcess(paramEventName)) {
				msgList.add(new ValidMessage(this.getMessage("COMMON_E_0012", dto.getEventName())));
			}

			if (null != mstWorkflowCommonService &&StringUtil.isNotEmpty(paramEventCode.getBusinessDataPk2())&& mstWorkflowCommonService.isInWflProcess(paramEventCode)) {
				msgList.add(new ValidMessage(this.getMessage("BUSS_COM_E_0006", dto.getEventCode())));
			}

		} else if (StringUtil.equals(operateType, "delete")) {
			// 当为移除时，判断是否有风险事件代码相同的在流程中
			List<RskEvent002Dto> removeList = dto.getSelectedItem();
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
				// 判断流程中是否存在相同的数据
				WflValidInputParameter paramEventName = new WflValidInputParameter();
				paramEventName.setBusinessDataPk1(dto.getEventName());
				paramEventName.setPageId(dto.getPageId());
				// 判断是否有同一个事件代码的数据
				WflValidInputParameter paramEventCode1 = new WflValidInputParameter();
				paramEventCode1.setBusinessDataPk2(dto.getEventCode());
				paramEventCode1.setPageId(dto.getPageId());
//				msgList.add(new ValidMessage(this.getMessage("BUSS_COM_E_0010", "要移除的风险事件")));
				msgList.add(new ValidMessage(this.getMessage("已存在流程中的数据，不能重复提交，已存在流程数据相关KEY:"+eventCodeList)));
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
