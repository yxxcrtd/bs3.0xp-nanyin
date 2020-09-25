package com.joyintech.tams.poc.directtransaction.futuresordermanage.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.poc.directtransaction.futuresordermanage.dto.FuturesOrderMenegeDto;
import com.joyintech.tams.poc.directtransaction.futuresordermanage.service.FuturesOrderMenegeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@PageId("FuturesOrderMenege001")
@RequestMapping("/invest/trade/futuresordermanage/FuturesOrderMenege001")
@Controller
public class FuturesOrderMenege001Controller extends BaseController {

	@Autowired
	private FuturesOrderMenegeService futuresOrderMenegeService;
	
	@Override
	public String init(Model model) {
		String entrustId = this.getParam("entrustId");
		
		FuturesOrderMenegeDto entity = futuresOrderMenegeService.search(entrustId);
		FuturesOrderMenegeDto entity2 = new FuturesOrderMenegeDto();
		if(entity != null){
			model.addAttribute("ps", entity.getPs());
			if(StringUtil.equals(entity.getPs(), "T02")){
				BeanUtil.copyPropertiesIgnoreNull(entity2, entity);
				entity=new FuturesOrderMenegeDto();
			}
		}
		model.addAttribute("entity2", entity2);
		model.addAttribute("entity", entity);
        return super.init(model);
	}
}
