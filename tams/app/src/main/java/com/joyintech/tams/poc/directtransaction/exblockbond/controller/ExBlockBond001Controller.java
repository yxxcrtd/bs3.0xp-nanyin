package com.joyintech.tams.poc.directtransaction.exblockbond.controller;


import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.poc.directtransaction.exblockbond.dto.ExBlockBond001Dto;
import com.joyintech.tams.poc.directtransaction.exblockbond.service.ExBlockBond001Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 股票大宗交易
 * 〈功能详细描述〉
 * @author 
 * @version 1.0 2019年2月11日
 * @see ExBlockBond001Controller
 * @since 1.0
 */
@PageId("ExBlockBond001")
@RequestMapping("/invest/trade/exblockbond/ExBlockBond001")
@Controller
public class ExBlockBond001Controller extends BaseController{
	
	@Autowired
	private ExBlockBond001Service exBlockBond001Service;
	@Override
    public String init(Model model) {
		this.setSelectOptionToModel(model, BussinessDictCode.交易平台);
		String entrustId = this.getParam("entrustId");
		
		ExBlockBond001Dto entity = exBlockBond001Service.search(entrustId);
		
		model.addAttribute("entity", entity);
          return super.init(model);
    }
	
	/**
     * 获取新建界面的资产代码联想下拉框框
     * 
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getfinprodIdForAdd")
    public List<SelectOptionDto> getfinprodIdForAdd() 
        throws Exception {
        List<SelectOptionDto> temp = exBlockBond001Service.getfinprodIdForAdd(RequestUtil.getPageParam());
        return temp;
    }
}
