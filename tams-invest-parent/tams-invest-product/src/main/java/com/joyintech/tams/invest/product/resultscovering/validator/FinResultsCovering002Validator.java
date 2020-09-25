package com.joyintech.tams.invest.product.resultscovering.validator;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.invest.product.common.service.ProductCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.product.resultscovering.dto.FinResultsCovering002Dto;
import com.joyintech.workflow.engine.constant.OperationType;

@Component
public class FinResultsCovering002Validator implements BeanValidator {

	@Autowired
	private ProductCommonService productCommonService;

	@Override
	public List<ValidMessage> valid(Object value) {
		FinResultsCovering002Dto entity = (FinResultsCovering002Dto) value;
		List<ValidMessage> validList = new ArrayList<ValidMessage>();
		if(StringUtil.equals("save", entity.getOperateType()) || StringUtil.equals("submit", entity.getOperateType())){
		// 回补日不能为空
		if (!StringUtil.isNotEmpty(entity.getCoveringDate())) {
			validList.add(new ValidMessage("coveringDate",
					this.getMessage("BUSS_COM_E_0038", this.getMessage("FinResultsCovering001.coveringDate"))));
		}
		// 产品代码不能为空
		if (!StringUtil.isNotEmpty(entity.getProdId())) {
			validList.add(new ValidMessage("prodId",
					this.getMessage("BUSS_COM_E_0038", this.getMessage("FinResultsCovering001.prodId"))));
		}

		// 回补方式不能为空
		if (!StringUtil.isNotEmpty(entity.getCoveringWay())) {
			validList.add(new ValidMessage("coveringWay",
					this.getMessage("BUSS_COM_E_0038", this.getMessage("FinResultsCovering002.coveringWay"))));
		}
		// 贴补形式不能为空
		if (!StringUtil.isNotEmpty(entity.getCoveringDate())) {
			validList.add(new ValidMessage("coveringForm",
					this.getMessage("BUSS_COM_E_0038", this.getMessage("FinResultsCovering002.coveringForm"))));
		}
		// 回补周期结束日不能为空
		if (!StringUtil.isNotEmpty(entity.getCovCycleStartDate())) {
			validList.add(new ValidMessage("covCycleStartDate",
					this.getMessage("BUSS_COM_E_0038", this.getMessage("FinResultsCovering002.covCycleStartDate"))));
		}
		//回补周期结束日不能为空
		if (!StringUtil.isNotEmpty(entity.getCovCycleEndDate())) {
			validList.add(new ValidMessage("covCycleEndDate",
					this.getMessage("BUSS_COM_E_0038", this.getMessage("FinResultsCovering002.covCycleEndDate"))));
		}
		//实际回补金额不能为空
		if (!StringUtil.isNotEmpty(entity.getActualAmount())) {
			validList.add(new ValidMessage("actualAmount",
					this.getMessage("BUSS_COM_E_0038", this.getMessage("FinResultsCovering001.actualAmount"))));
		}
		// 回补日期必须大于等于平台日
		String platedate = DateUtil.getSysDate();
		if (platedate.compareTo(DateUtil.format(entity.getCoveringDate())) > 0) {
			validList.add(
					new ValidMessage("coveringDate", this.getMessage("FIN_COVERINGDATE_001", this.getMessage("回补日"))));
		}

		// 回补日需大于等于起息日
		// add by wj 20200821  BS-1654
			//【业绩回补】添加校验：回补日需大于等于起息日
			FinProduct finProduct = productCommonService.queryFinProductByFinprodId(entity.getProdId());
			if(finProduct!=null && finProduct.getVdate()!=null){
				if(DateUtil.compareDate(entity.getCoveringDate(),finProduct.getVdate()) < 0){
					validList.add(new ValidMessage("coveringDate", this.getMessage("FIN_COVERINGDATE_002")));
				}
			}
		}
		return validList;
	}

}
