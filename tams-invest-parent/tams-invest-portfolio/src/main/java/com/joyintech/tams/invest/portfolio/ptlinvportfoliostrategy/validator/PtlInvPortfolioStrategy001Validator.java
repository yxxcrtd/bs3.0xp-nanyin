/*
 * 文件名：PtlInvPortfolioStrategy001Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：niuqianyu
 * 修改时间：2019年7月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.ptlinvportfoliostrategy.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.portfolio.ptlinvportfoliostrategy.dto.PtlInvPortfolioStrategy001Dto;
import com.joyintech.tams.invest.portfolio.ptlinvportfoliostrategy.service.PtlInvPortfolioStrategy001Service;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.workflow.engine.WorkflowContext;

@Component
public class PtlInvPortfolioStrategy001Validator implements BeanValidator {

	/**
	 * 平台日期组件接口
	 */
	@Autowired(required = false)
	private IPlatformDate iPlatformDate;

	@Autowired
	private PtlInvPortfolioStrategy001Service ptlInvPortfolioStrategy001Service;

	@Autowired
	private MstWorkflowCommonService mstWorkflowCommonService;

	private final static Logger logger = LoggerFactory.getLogger(PtlInvPortfolioStrategy001Validator.class);

	@Override
	public List<ValidMessage> valid(Object value) {
		PtlInvPortfolioStrategy001Dto dto = (PtlInvPortfolioStrategy001Dto) value;
		ArrayList<ValidMessage> errorList = new ArrayList<>();

		String sysDate = iPlatformDate.getSysDateStr();
		// 删除
		if (StringUtil.equals(dto.getOperateType(), "delete")) {
			// 只能删除策略生效日大于平台时间的数据
			if (dto.getEffDate().compareTo(sysDate) <= 0) {
				errorList.add(new ValidMessage("只能删除平台日之后的组合策略关联数据！"));
			}
		}

		// 新建
		if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)
				|| StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
			// 生效日不能小于平台日期
			if (dto.getEffDate().compareTo(sysDate) < 0) {
				errorList.add(new ValidMessage("生效日不能小于平台日期"));
			}

			// 选择的组合代码不是没有子组合的产品组合时
			Map<String, Object> params = new HashMap<>();
			params.put("portfolioId", dto.getPortfolioId());
			params.put("investYesOrNo", DictCodeList.是否_否.getValue());
			if (ptlInvPortfolioStrategy001Service.getPtlPortfolio(params) > 0) {
				errorList.add(new ValidMessage("只能对能进行投资的组合进行策略关联！"));
			}

			// 同一组合同一策略生效日，只能维护一条数据
			if (ptlInvPortfolioStrategy001Service.newCheck(dto) != null) {
				errorList.add(new ValidMessage("同一组合同一策略在同一生效日只能关联一次！"));
			}

			try {
				// 判断当前流程是否存在：数据主键
				WflValidInputParameter validParam1 = new WflValidInputParameter();
				ProcessMark processMark1 = WorkflowContext.getProcessMarkService().getParameterByAnnotation(dto);
				validParam1.setWorkflowId(dto.getWorkflowId());
				validParam1.setPageId(processMark1.getPageId());

				validParam1.setBusinessDataPk1(dto.getPortfolioId());
				validParam1.setBusinessDataPk2(dto.getStrategyId());
				validParam1.setBusinessDataPk3(dto.getEffDate());
				if (mstWorkflowCommonService.isInWflProcess(validParam1)) {
					// 流程存在情况
					errorList.add(new ValidMessage(getMessage("COMMON_E_0021", "标识",
							dto.getPortfolioId() + "_" + dto.getStrategyId() + "_" + dto.getEffDate())));
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}

		}
		return errorList;
	}

}
