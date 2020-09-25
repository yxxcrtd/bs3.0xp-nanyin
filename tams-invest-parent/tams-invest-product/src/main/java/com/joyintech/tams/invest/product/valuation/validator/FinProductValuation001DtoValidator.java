package com.joyintech.tams.invest.product.valuation.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.product.valuation.dto.FinProductValuation001Dto;
import com.joyintech.tams.invest.product.valuation.dto.ProdInfoDto;
import com.joyintech.tams.invest.product.valuation.service.FinProductValuation001Service;

@Component
public class FinProductValuation001DtoValidator implements BeanValidator {

	@Autowired
	private FinProductValuation001Service finProductValuation001Service;

	@Override
	public List<ValidMessage> valid(Object value) {

		FinProductValuation001Dto entity = (FinProductValuation001Dto) value;
		List<ValidMessage> validList = new ArrayList<ValidMessage>();

		// 产品代码不能为空
		if (!StringUtil.isNotEmpty(entity.getProdcode())) {
			validList.add(new ValidMessage("prodcode",
					this.getMessage("BUSS_COM_E_0038", this.getMessage("FinProductValuation001.prodcode"))));
		}

		// 估值日不能为空
		if (!StringUtil.isNotEmpty(entity.getVldate())) {
			validList.add(new ValidMessage("vldate",
					this.getMessage("BUSS_COM_E_0038", this.getMessage("FinProductValuation001.vldate"))));
		}

		// 估值日必须等于平台日
		String platedate = DateUtil.getSysDate();
		if (!(platedate.equals(DateUtil.format(entity.getVldate()))
				&& platedate.equals(DateUtil.format(entity.getVldateEnd())))) {
			validList.add(new ValidMessage("vldate",
					this.getMessage("FIN_VALUATION_0002", this.getMessage("FinProductValuation001.vldate"))));
		}

		// 估值日在产品起息日和到期日之间
		if (StringUtil.isNotEmpty(entity.getVldate())) {
			List<String> prodcodes = StringUtil.splitToList(",", entity.getProdcode());
			prodcodes.forEach(prodcode -> {
				ProdInfoDto dto = finProductValuation001Service.selectProdInfo(prodcode);
				if (entity.getVldateStart().compareTo(dto.getVdate()) < 0
						|| entity.getVldateEnd().compareTo(dto.getMdate()) > 0) {
					validList.add(new ValidMessage("vldate",
							this.getMessage("FIN_VALUATION_0001", prodcode,
									this.getMessage("FinProductValuation001.vldate"),
									this.getMessage("FinProductValuation001.vdate"),
									this.getMessage("FinProductValuation001.mdate"))));
				}
			});
		}
		return validList;
	}

}
