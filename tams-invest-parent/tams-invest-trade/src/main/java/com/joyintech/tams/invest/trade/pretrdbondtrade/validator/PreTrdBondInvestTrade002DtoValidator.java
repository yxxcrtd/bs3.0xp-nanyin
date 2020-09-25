/*
 * 文件名：PreTrdRepoTrade002DtoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：王伟
 * 修改时间：2019年12月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.pretrdbondtrade.validator;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.component.base.entrust.BaseEtrInvestInfoService;
import com.joyintech.tams.invest.trade.common.validator.TradeCommonValidator;
import com.joyintech.tams.invest.trade.pretrdbondtrade.dto.PreTrdBondInvestTrade002Dto;
import com.joyintech.tams.invest.trade.pretrdrepotrade.dto.PreTrdRepoTrade002Dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 债券交易预录入新建画面：PreTrdBondInvestTrade002DtoValidator
 * 
 * @author 王伟
 * @version 1.0 2020年1月6日
 * @see PreTrdBondInvestTrade002DtoValidator
 * @since 1.0
 */
@Component
public class PreTrdBondInvestTrade002DtoValidator implements TradeCommonValidator {

	@Autowired
	private BaseEtrInvestInfoService baseEtrInvestInfoService;

	/**
	 *
	 * @param value
	 *            需要被验证的业务对象
	 * @return
	 */
	@Override
	public List<ValidMessage> bussValid(Object value) {
		PreTrdBondInvestTrade002Dto inputDto = (PreTrdBondInvestTrade002Dto) value;
		List<ValidMessage> errorList = new ArrayList<>();
		// 委托场合时且交易方向为买入
		if (StringUtil.equals(DictCodeList.交易方向_买入.getValue(), inputDto.getPs())
				&& StringUtil.isNotEmpty(inputDto.getEntrustId())) {
			com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto etrInvestInfoDto = baseEtrInvestInfoService
					.queryActiveMandate(inputDto.getEntrustId());
			if (!StringUtil.isNotEmpty(etrInvestInfoDto)) {
				return errorList;
			}
			// 净价超过委托时的净价;
			if (inputDto.getUnitCprice() != null && etrInvestInfoDto.getEtrEntrust().getPrice() != null
					&& inputDto.getUnitCprice().compareTo(etrInvestInfoDto.getEtrEntrust().getPrice()) > 0) {
				ValidMessage validMessage = new ValidMessage("unitCprice",
						getMessage("TRD_W_0007", getMessage("TrdBondInvestTrade002.unitCprice"),
								getMessage("TrdBondInvestTrade002.unitCprice"),
								getMessage("TrdLendInvestTrade002.morethan")));
				errorList.add(validMessage);
			}

			// 到期收益率低于委托时的到期收益率;
			if (inputDto.getYield() != null && etrInvestInfoDto.getEtrEntrustAdd().getMatureYield() != null
					&& inputDto.getYield().compareTo(etrInvestInfoDto.getEtrEntrustAdd().getMatureYield()
							.divide(new BigDecimal(100))) < 0) {
				ValidMessage validMessage = new ValidMessage("yield",
						getMessage("TRD_W_0007", getMessage("TrdBondInvestTrade002.yield"),
								getMessage("TrdBondInvestTrade002.yield"),
								getMessage("TrdLendInvestTrade002.lessthan")));
				errorList.add(validMessage);
			}

			// 行权收益率低于委托时的行权收益率
			if (inputDto.getExerYield() != null && etrInvestInfoDto.getEtrEntrustAdd().getExerYield() != null
					&& inputDto.getExerYield().compareTo(etrInvestInfoDto.getEtrEntrustAdd().getExerYield()
							.divide(new BigDecimal(100))) < 0) {
				ValidMessage validMessage = new ValidMessage("exerYield",
						getMessage("TRD_W_0007", getMessage("TrdBondInvestTrade002.exerYield"),
								getMessage("TrdBondInvestTrade002.exerYield"),
								getMessage("TrdLendInvestTrade002.lessthan")));
				errorList.add(validMessage);
			}
		}
		return errorList;
	}
}
