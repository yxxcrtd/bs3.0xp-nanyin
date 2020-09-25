package com.joyintech.tams.poc.directtransaction.exbiddingdepo.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.poc.directtransaction.exbiddingdepo.dto.ExBiddingDepo001Dto;
import com.joyintech.tams.poc.directtransaction.exbiddingdepo.service.ExBiddingDepo001Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 回购竞价交易
 * 〈功能详细描述〉
 * @author 
 * @version 1.0 2019年2月11日
 * @see ExBiddingDepo001Controller
 * @since 1.0
 */
@PageId("ExBiddingDepo001")
@RequestMapping("/invest/trade/exbiddingdepo/ExBiddingDepo001")
@Controller
public class ExBiddingDepo001Controller extends BaseController{

	@Autowired
	private ExBiddingDepo001Service exBiddingDepo001Service;
	@Override
	public String init(Model model) {
		String entrustId = this.getParam("entrustId");
		
		ExBiddingDepo001Dto entity = exBiddingDepo001Service.search(entrustId);
		ExBiddingDepo001Dto entity2 = new ExBiddingDepo001Dto();
		if(entity != null){
			model.addAttribute("ps", entity.getPs());
			if(StringUtil.equals(entity.getPs(), BussinessDictCodeList.回购拆借方向_逆回购.getValue())){
				BeanUtil.copyPropertiesIgnoreNull(entity2, entity);
				entity=new ExBiddingDepo001Dto();
			}
		}
		model.addAttribute("entity2", entity2);
		model.addAttribute("entity", entity);
		return super.init(model);
	}
}
