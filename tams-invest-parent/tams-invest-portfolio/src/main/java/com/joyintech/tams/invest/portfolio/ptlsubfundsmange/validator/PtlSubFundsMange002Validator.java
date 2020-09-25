/*
 * 文件名：PtlSubFundsMange002Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangyf
 * 修改时间：2019年8月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.ptlsubfundsmange.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.portfolio.ptlsubfundsmange.dto.PtlSubFundsMange002Dto;
import com.joyintech.tams.invest.portfolio.ptlsubfundsmange.service.PtlSubFundsMange002Service;

@Component
public class PtlSubFundsMange002Validator implements BeanValidator {

	@Autowired
	private PtlSubFundsMange002Service ptlSubFundsMange002Service;

	@Override
	public List<ValidMessage> valid(Object value) {
		PtlSubFundsMange002Dto dto = (PtlSubFundsMange002Dto) value;
		ArrayList<ValidMessage> errorList = new ArrayList<>();

		// 新建
		if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
			// 检查：列表中数据存在有效或者流程中的数据
			if (ptlSubFundsMange002Service.isDataEffOrInWorkFlow(dto)) {
				errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0041")));
				return errorList;
			}
		}
		// 提交、保存
		if (StringUtil.equals(BussinessConstant.SUBMIT, dto.getOperateType())
				|| StringUtil.equals("save", dto.getOperateType())) {
			List<PtlSubFundsMange002Dto> portfolioDetailTable = dto.getPortfolioDetailTable();
			if (org.springframework.util.CollectionUtils.isEmpty(portfolioDetailTable)) {
				errorList.add(new ValidMessage("子组合资金分配明细不能为空！"));
				return errorList;
			} else {
				// 子组合的分配金额之和
				BigDecimal tradeAmtfpTotal = BigDecimal.ZERO;
				// 子组合的比例之和
				BigDecimal disPercentTotal = BigDecimal.ZERO;
				
				for (PtlSubFundsMange002Dto dto2 : portfolioDetailTable) {
					// 比例（%）不能小于0
					if (null == dto2.getDisPercent() || BigDecimal.ZERO.compareTo(dto2.getDisPercent()) > 0) {
						errorList.add(new ValidMessage(
								getMessage("BUSS_COM_E_0004", getMessage("PtlSubFundsMange002.disPercent"),"0")));
					}
					// 比例（%）不能大于100
					if (null == dto2.getDisPercent() || new BigDecimal(100).compareTo(dto2.getDisPercent()) < 0) {
						errorList.add(new ValidMessage(
								getMessage("BUSS_COM_E_0003", getMessage("PtlSubFundsMange002.disPercent"),"100")));
					}
					// 分配金额 必填
					if (null == dto2.getTradeAmtfp()) {
						errorList.add(new ValidMessage(
								getMessage("COMMON_E_0057", getMessage("PtlSubFundsMange002.tradeAmtfp"))));
					} else {
						tradeAmtfpTotal = tradeAmtfpTotal.add(dto2.getTradeAmtfp());

						// 分配金额<0时，如果子组合分配金额(取正值进行计算)>子组合分配前剩余现金
						// 分配前剩余现金
						BigDecimal preSurplusCash = dto2.getPreSurplusCash() == null ? BigDecimal.ZERO
								: dto2.getPreSurplusCash();
						if (BigDecimal.ZERO.compareTo(dto2.getTradeAmtfp()) > 0
								&& dto2.getTradeAmtfp().abs().compareTo(preSurplusCash) > 0) {
							// 分配出时，分配金额不能大于分配前剩余现金！（从子组合扣钱时，扣的钱不能大于子组合剩余现金）
							errorList.add(new ValidMessage(getMessage("PTL_COM_E_0017")));
						}
					}
					disPercentTotal=disPercentTotal.add(dto2.getDisPercent());
				}
				// 子组合的分配金额之和不等于交易的交易金额
				if (tradeAmtfpTotal.compareTo(dto.getTradeAmt()) != 0) {
					errorList.add(new ValidMessage(getMessage("PTL_COM_E_0015")));
				}
				//子组合的比例之和应等于100
				if(new BigDecimal(100).compareTo(disPercentTotal)!=0){
					errorList.add(new ValidMessage("子组合比例相加应为100%"));
				}
			}
		}
		return errorList;
	}

}
