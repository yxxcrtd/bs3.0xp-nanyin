package com.joyintech.tams.invest.product.valuation.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.product.valuation.dto.FinProductValuation001Dto;
import com.joyintech.tams.invest.product.valuation.service.FinProductValuation001Service;

@Controller
@PageId("FinProductValuation001")
@RequestMapping("/invest/product/valuation/FinProductValuation001")
public class FinProductValuation001Controller extends BaseController {

	@Autowired
	private FinProductValuation001Service finProductValuation001Service;

	@Override
	public String init(Model model) {
		String platedate = DateUtil.getSysDate();
		platedate = platedate.concat(" - ").concat(platedate);
		model.addAttribute("platedate", platedate);
		return super.init(model);
	}

	/**
	 * 分页查询
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@PostMapping("/selectForPage")
	public Page<FinProductValuation001Dto> selectForPage() throws Exception {
		return finProductValuation001Service.selectForPage(RequestUtil.getPageParam());
	}

	/**
	 * 验证之后计算估值
	 * 
	 * @param condition
	 * @param result
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/calcValue")
	public AjaxResult calcValue(@Valid @RequestBody FinProductValuation001Dto condition, BindingResult result) {
		if (result != null && !result.hasErrors()) {
			// 界面不需要查询, 每次估值全删
			finProductValuation001Service.deleteValuation();
			Date vldate = condition.getVldateStart();
			while (vldate.compareTo(condition.getVldateEnd()) <= 0) {
				finProductValuation001Service.calcValue(condition, condition.getVldateStart(), vldate);
				vldate = DateUtil.addDay(vldate, 1);
			}
		}
		return new AjaxResult(result);
	}

}
