package com.joyintech.tams.invest.product.rddesign.productsimulationtrial.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.tams.component.business.prod.BussBenchMarketInfoService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.product.rddesign.productsimulationtrial.dto.ProductSimulationTrial002Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;
import java.util.List;


/**
 * 模拟交易controller
 *
 * @author jiaobo
 * @version 1.0 2019年11月1日
 * @see ProductSimulationTrial002Dto
 * @since 1.0
 */
@Controller
@PageId(value = "ProductSimulationTrial003")
@RequestMapping("/invest/product/rddesign/productsimulationtrial/ProductSimulationTrial003")
public class ProductSimulationTrial003Controller extends BaseController {

	@Autowired
	private BussBenchMarketInfoService bussBenchMarketInfoService;

	@Override
	public String init(Model model) {
		setSelectOptionToModel(model, DictCode.资产一级分类,DictCode.资产风险等级2);
		// 浮动利率下拉框（含利率行情、复合基准四个部分）
		List<SelectOptionDto> listFloatRate = new ArrayList<SelectOptionDto>();
		// 利率行情
		SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.利率行情, null);
		for (SelectOptionDto dto : selectDto.getOptions()) {
			SelectOptionDto dto1 = new SelectOptionDto();
			dto1.setId(dto.getId());
			dto1.setText(dto.getText());
			dto1.setObject(DictCodeListExt1.基准类型_2_利率.getValue());
			dto1.setCol(DictCodeListExt1.基准类型_2_利率.getCodeName());
			listFloatRate.add(dto1);
		}
		// 指数行情
		SelectDto selectDto2 = this.getSelectDataFromDataDic(DictCode.指数行情, null);
		for (SelectOptionDto dto : selectDto2.getOptions()) {
			SelectOptionDto dto1 = new SelectOptionDto();
			dto1.setId(dto.getId());
			dto1.setText(dto.getText());
			dto1.setObject(DictCodeListExt1.基准类型_2_指数.getValue());
			dto1.setCol(DictCodeListExt1.基准类型_2_指数.getCodeName());
			listFloatRate.add(dto1);
		}
		// 收益率曲线行情
		SelectDto selectDto3 = this.getSelectDataFromDataDic(DictCode.收益率曲线, null);
		for (SelectOptionDto dto : selectDto3.getOptions()) {
			SelectOptionDto dto1 = new SelectOptionDto();
			dto1.setId(dto.getId());
			dto1.setText(dto.getText());
			dto1.setObject(DictCodeListExt1.基准类型_2_收益率曲线.getValue());
			dto1.setCol(DictCodeListExt1.基准类型_2_收益率曲线.getCodeName());
			listFloatRate.add(dto1);
		}
		// 复合基准
		List<SelectOptionDto> finBaseRuleInfoList = bussBenchMarketInfoService.getFinBaseRuleInfo();
		for (SelectOptionDto dto : finBaseRuleInfoList) {
			SelectOptionDto dto1 = new SelectOptionDto();
			dto1.setId(dto.getId());
			dto1.setText(dto.getText());
			dto1.setObject(DictCodeListExt1.基准类型_2_复合.getValue());
			dto1.setCol(DictCodeListExt1.基准类型_2_复合.getCodeName());
			listFloatRate.add(dto1);
		}
		model.addAttribute("INDEX_ID_SOURCE", JsonUtils.objectArray2Json(listFloatRate.toArray()));
		return super.init(model);
	}

}
