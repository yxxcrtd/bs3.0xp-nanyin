/*
 * 文件名：ValProductInfoQuery005Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年9月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.basedata.productinfoquery.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.valuation.basedata.productinfoquery.dto.ValProductInfoAcctDto;
import com.joyintech.tams.valuation.basedata.productinfoquery.service.ValProductInfoQuery006Service;

/**
 * 
 * 资金账户
 * @author chufengjie 
 * @version 1.0 2019年07月09日
 * @see ValProductInfoQuery006Controller
 * @since 1.0
 */
@Controller
@PageId("ValProductInfoQuery006")
@RequestMapping("/valuation/basedata/productinfoquery/ValProductInfoQuery006")
public class ValProductInfoQuery006Controller extends BaseController{

	@Autowired
    private ValProductInfoQuery006Service valProductInfoQuery006Service;
	
	@Override
	public String init(Model model) {
		model.addAllAttributes(RequestUtil.getPageParam());
		return super.init(model);
	}

	@RequestMapping("findPage")
    @ResponseBody
    public Page<ValProductInfoAcctDto> findPage() throws Exception{
        return valProductInfoQuery006Service.findBankdesc(RequestUtil.getPageParam());
    }

	
}
