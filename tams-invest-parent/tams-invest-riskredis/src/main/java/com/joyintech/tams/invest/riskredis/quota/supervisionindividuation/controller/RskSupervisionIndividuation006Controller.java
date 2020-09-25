package com.joyintech.tams.invest.riskredis.quota.supervisionindividuation.controller;

import java.util.List;

import com.joyintech.tams.invest.riskredis.quota.supervisionindividuation.dto.RskIndividUniteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.riskredis.quota.supervisionindividuation.service.RskSupervisionIndividuation001Service;

@Controller
@PageId("RskSupervisionIndividuation006")
@RequestMapping("/invest/riskredis/quota/supervisionindividuation/RskSupervisionIndividuation006")
public class RskSupervisionIndividuation006Controller extends BaseController{
	
	@Autowired
	private RskSupervisionIndividuation001Service rskSupervisionIndividuation001Service;
	
	@Override
    public String init(Model model) {
		String indId = this.getParam("indId");
		RskIndividUniteDto dto = new RskIndividUniteDto();
		if(StringUtil.isNotEmpty(this.getParam("uniteId"))){
			dto.setUniteId(this.getParam("uniteId"));
			String uniteIndIds = this.getParam("uniteIndId");
			if(uniteIndIds!=null){
				List<String> uniteIndIdList = StringUtil.splitToList(GlobalConstant.COMMA,uniteIndIds);
				//dto.setUniteIndId(uniteIndIdList);
			}
			//dto.setUniteIndIdStr(this.getParam("uniteIndId"));
			dto.setRemark(this.getParam("remark"));
		}else{
			dto.setUniteId(IDGenerator.generate());
		}
		dto.setIndId(indId);
		dto.setPrtBagId(this.getParam("prtBagId"));
		model.addAttribute("entity", dto);
		model.addAttribute("IND_TYPE", rskSupervisionIndividuation001Service.getIndType(indId));
		return super.init(model);
	}
}
