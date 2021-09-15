package com.joyintech.tams.batch.report.investorsholdinfoexport.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.submitmanagement.investorsholdinforegist.dto.InvestorsHoldInfoRegist001Dto;

@Configuration(value="investorsHoldInfoExportDealUtil")
public class InvestorsHoldInfoExportDealUtil {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 处理查询前的参数 <功能详细描述>
	 *
	 * @param pageParam
	 * @see
	 * @since 1.0
	 */
	public void dealSearchParam(Map<String, Object> pageParam) {
		// 选择行数据时
		if (pageParam.containsKey("keyList")) {
			String keyList = pageParam.get("keyList").toString();
			try {
				List<InvestorsHoldInfoRegist001Dto> investList = JsonUtils.json2List(keyList,
						InvestorsHoldInfoRegist001Dto.class);
				pageParam.put("investList", investList);
			} catch (IOException e) {
//				e.printStackTrace();
	            logger.error(e.getMessage(),e);
			}
		} else {
			// 查询参数条件
			// 数据状态
			String status = (String) pageParam.get("status");
			// 报送状态
			String sendStatus = (String) pageParam.get("sendStatus");
			// 报送状态
			String orgCode = (String) pageParam.get("orgCode");
			// 反馈状态
			String submitFeedbackStatus = (String) pageParam.get("submitFeedbackStatus");
			// 产品登记编码
			String prdRegistCode = (String) pageParam.get("prdRegistCode");
			// 将数据状态当做多选下拉框处理
			if (StringUtil.isNotBlank(status)) {
				pageParam.put("statusList", StringUtil.splitToList(GlobalConstant.COMMA, status));
			}
			// 将报送状态当做多选下拉框处理
			if (StringUtil.isNotBlank(sendStatus)) {
				pageParam.put("sendStatusList", StringUtil.splitToList(GlobalConstant.COMMA, sendStatus));
			}
			// 将反馈状态当做多选下拉框处理
			if (StringUtil.isNotBlank(submitFeedbackStatus)) {
				pageParam.put("submitFeedbackStatusList",
						StringUtil.splitToList(GlobalConstant.COMMA, submitFeedbackStatus));
			}
			// 将机构代码当做多选下拉框处理
			if (StringUtil.isNotBlank(orgCode)) {
				pageParam.put("orgCodeList", StringUtil.splitToList(GlobalConstant.COMMA, orgCode));
			}
			// 将产品登记编码当做多选下拉框处理
			if (StringUtil.isNotBlank(prdRegistCode)) {
				pageParam.put("prdRegistCodeList", StringUtil.splitToList(GlobalConstant.COMMA, prdRegistCode));
			}
		}
	}

}
