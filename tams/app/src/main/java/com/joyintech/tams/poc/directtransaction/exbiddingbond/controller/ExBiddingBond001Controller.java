package com.joyintech.tams.poc.directtransaction.exbiddingbond.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.poc.directtransaction.exbiddingbond.dto.ExBiddingBond001Dto;
import com.joyintech.tams.poc.directtransaction.exbiddingbond.service.ExBiddingBond001Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 股票竞价交易
 * 〈功能详细描述〉
 * @author 
 * @version 1.0 2019年2月11日
 * @see ExBiddingBond001Controller
 * @since 1.0
 */
@PageId("ExBiddingBond001")
@RequestMapping("/invest/trade/exbiddingbond/ExBiddingBond001")
@Controller
public class ExBiddingBond001Controller extends BaseController{
	
	@Autowired
	private ExBiddingBond001Service exBiddingBond001Service;
	
	@Override
    public String init(Model model) {
		String entrustId = this.getParam("entrustId");
		
		ExBiddingBond001Dto entity = exBiddingBond001Service.search(entrustId);
		ExBiddingBond001Dto entity2 = new ExBiddingBond001Dto();
		if(entity != null){
			model.addAttribute("ps", entity.getPs());
			if(StringUtil.equals(entity.getPs(), "T02")){
				BeanUtil.copyPropertiesIgnoreNull(entity2, entity);
				entity=new ExBiddingBond001Dto();
			}
		}
		model.addAttribute("entity2", entity2);
		model.addAttribute("entity", entity);
        return super.init(model);
    }
}
