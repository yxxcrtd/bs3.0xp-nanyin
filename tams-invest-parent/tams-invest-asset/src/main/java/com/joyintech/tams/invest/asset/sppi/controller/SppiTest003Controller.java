package com.joyintech.tams.invest.asset.sppi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.common.base.entity.SppiImportData;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.asset.sppi.dto.SppiImportDto;
import com.joyintech.tams.invest.asset.sppi.service.SppiTest003Service;

/**
 * SPPI测试查询控制类
 *
 * @author Yaozhenguo
 * @version 1.0 2020-11-09
 */
@Controller
@PageId("SppiTest003")
@RequestMapping("/invest/asset/sppi/SppiTest003")
public class SppiTest003Controller extends BaseController{
	
	@Autowired
	private SppiTest003Service sppiTest003Service;

	@Override
	public String init(Model model) {
		setSelectOptionToModel(model, DictCode.平安资产类型,DictCode.SPPI测试状态);
		String finprodId = this.getParam("finprodId");
		SppiImportDto sppiImportDto = new SppiImportDto();
		sppiImportDto = sppiTest003Service.selectByBondId(finprodId);
		model.addAttribute("entity",sppiImportDto);
		return super.init(model);
	}
}
