package com.joyintech.tams.poc.directtransaction.exbiddingsec.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.poc.directtransaction.exbiddingsec.dto.ExBiddingSec001Dto;
import com.joyintech.tams.poc.directtransaction.exbiddingsec.service.ExBiddingSec001Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 债券竞价交易
 * 〈功能详细描述〉
 * @author 
 * @version 1.0 2019年2月11日
 * @see ExBiddingSec001Controller
 * @since 1.0
 */
@PageId("ExBiddingSec001")
@RequestMapping("/invest/trade/exbiddingsec/ExBiddingSec001")
@Controller
public class ExBiddingSec001Controller extends BaseController{
	
	@Autowired
	private ExBiddingSec001Service exBiddingSec001Service;
	
	@Override
    public String init(Model model) {
		String entrustId = this.getParam("entrustId");
		
		ExBiddingSec001Dto entity = exBiddingSec001Service.search(entrustId);
		ExBiddingSec001Dto entity2 = new ExBiddingSec001Dto();
		if(entity != null){
			model.addAttribute("ps", entity.getPs());
			if(StringUtil.equals(entity.getPs(), "T02")){
				BeanUtil.copyPropertiesIgnoreNull(entity2, entity);
				entity=new ExBiddingSec001Dto();
			}
		}
		model.addAttribute("entity2", entity2);
		model.addAttribute("entity", entity);
		
          return super.init(model);
    }
}
