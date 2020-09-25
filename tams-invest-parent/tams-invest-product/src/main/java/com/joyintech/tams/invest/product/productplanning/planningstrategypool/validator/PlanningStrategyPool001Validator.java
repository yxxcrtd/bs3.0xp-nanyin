/*
 * 文件名：PlanningStrategyPool001Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：niuqianyu
 * 修改时间：2019年12月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productplanning.planningstrategypool.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.product.productplanning.planningstrategypool.dto.PlanningStrategyPool001Dto;
import com.joyintech.tams.invest.product.productplanning.planningstrategypool.dto.PlanningStrategyPoolDetailDto;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;

/**
 * 规划策略池维护
 * 
 * @author niuqianyu
 * @version 1.0 2019年12月20日
 * @see PlanningStrategyPool001Validator
 * @since 1.0
 */
@Component
public class PlanningStrategyPool001Validator implements BeanValidator {

	@Autowired
	private MstWorkflowCommonService mstWorkflowCommonService;

	private final static Logger logger = LoggerFactory.getLogger(PlanningStrategyPool001Validator.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ValidMessage> valid(Object value) {
		PlanningStrategyPool001Dto dto = (PlanningStrategyPool001Dto) value;
		List<ValidMessage> errorList = new ArrayList<>();

		WflValidInputParameter pk = new WflValidInputParameter();
		pk.setWorkflowId(dto.getWorkflowId());
		pk.setPageId("PlanningStrategyPool002");
		pk.setBusinessDataPk1(dto.getStrategyId());
		if (mstWorkflowCommonService.isInWflProcess(pk)) {
			// 流程存在情况
			errorList.add(new ValidMessage(getMessage("COMMON_E_0021", "标识", dto.getStrategyId())));
			return errorList;
		}

		if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)
				|| StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {

			List<PlanningStrategyPoolDetailDto> detailList = dto.getPlanningStrategyPoolDetailList();
			if (CollectionUtils.isEmpty(detailList)) {
				errorList.add(new ValidMessage("策略明细列表不能为空"));
			} else {
				BigDecimal radio = BigDecimal.ZERO;
				List<String> strList = new ArrayList<>();
				for (int i = 0; i < detailList.size(); i++) {
					PlanningStrategyPoolDetailDto detailDto = detailList.get(i);
					if (!StringUtil.isNotEmpty(detailDto.getType2())) {
						errorList.add(new ValidMessage("第" + String.valueOf(i + 1) + "行资产大类不能为空"));
					}
					if (detailDto.getRatio() == null) {
						errorList.add(new ValidMessage("第" + String.valueOf(i + 1) + "行比例不能为空"));
					} else {
						radio = radio.add(detailDto.getRatio());
					}

					strList.add(detailList.get(i).getType2());
				}

				if (radio.compareTo(new BigDecimal(100)) != 0) {
					errorList.add(new ValidMessage("策略明细列表中的比例之和必须等于100"));
				}

				HashSet<String> set = new HashSet<>(strList);
				if (strList.size() != set.size()) {
					errorList.add(new ValidMessage("策略明细列表中同一资产大类不可以选多次"));
				}
			}

		}
		return errorList;
	}

}
