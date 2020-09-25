package com.joyintech.tams.invest.riskredis.quota.supervisionindividuation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.riskredis.quota.supervisionindividuation.dto.RskIndividAssetDelDto;

@Controller
@PageId("RskSupervisionIndividuation005")
@RequestMapping("/invest/riskredis/quota/supervisionindividuation/RskSupervisionIndividuation005")
public class RskSupervisionIndividuation005Controller extends BaseController{
	@Override
    public String init(Model model) {
		String indId = this.getParam("indId");
		String prtBagId = this.getParam("prtBagId");
		RskIndividAssetDelDto dto = new RskIndividAssetDelDto();
		if(StringUtil.isNotEmpty(this.getParam("delId"))){
			dto.setDelId(this.getParam("delId"));
			dto.setFinprodId(this.getParam("finprodId"));
			dto.setFinprodName(this.getParam("finprodName"));
			dto.setStartDate(DateUtil.parse(this.getParam("startDate")));
			dto.setEndDate(DateUtil.parse(this.getParam("endDate")));
		}else{
			dto.setDelId(IDGenerator.generate());
		}
		dto.setIndId(indId);
		dto.setPrtBagId(prtBagId);
		model.addAttribute("entity", dto);
		return super.init(model);
	}
}
