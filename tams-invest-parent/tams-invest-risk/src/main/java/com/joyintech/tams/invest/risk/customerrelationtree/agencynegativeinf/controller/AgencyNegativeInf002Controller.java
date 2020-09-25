package com.joyintech.tams.invest.risk.customerrelationtree.agencynegativeinf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.invest.risk.customerrelationtree.agencynegativeinf.dto.AgencyNegativeInf001Dto;

@Controller
@PageId("AgencyNegativeInf002")
@RequestMapping("/invest/risk/customerrelationtree/agencynegativeinf/AgencyNegativeInf002")
public class AgencyNegativeInf002Controller extends BaseController{

	@Override
	public String init(Model model) {
		AgencyNegativeInf001Dto agencyNegativeInf001Dto = new AgencyNegativeInf001Dto();
		agencyNegativeInf001Dto.setTitle(getParam("title"));
		agencyNegativeInf001Dto.setCominfo(getParam("cominfo"));
		agencyNegativeInf001Dto.setPubDate(getParam("pubDate"));
		agencyNegativeInf001Dto.setSource(getParam("source"));
		agencyNegativeInf001Dto.setLinkId(getParam("linkId"));
		model.addAttribute("agencyNegativeInf001Dto", agencyNegativeInf001Dto);
		return super.init(model);
	}
}
