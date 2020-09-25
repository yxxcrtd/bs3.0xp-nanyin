/*
 * 文件名：ValProductInfoQuery005Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年9月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.basedata.productinfoquery.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.service.valuation.basedata.productinfoquery.dto.ValProductInfoFeeDto;
import com.joyintech.service.valuation.basedata.productinfoquery.service.ValProductInfoQuery005Service;

/**
 * 
 * 费用调整明细
 * @author zhangwei
 * @version 1.0 2018年9月11日
 * @see ValProductInfoQuery005Controller
 * @since 1.0
 */
@RestController(value="serviceValProductInfoQuery005Controller")
@RequestMapping("/ValProductInfoQuery005")
public class ValProductInfoQuery005Controller extends BaseController{

	@Autowired
    private ValProductInfoQuery005Service valProductInfoQuery005Service;
	
	@Override
	public String init(Model model) {
		model.addAllAttributes(RequestUtil.getPageParam());
		return super.init(model);
	}

	@PostMapping("/findPage/{page}/{length}")
    public Page<ValProductInfoFeeDto> findPage(@RequestBody Map<String, Object> params,
    		@PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        return valProductInfoQuery005Service.findFeeAdjust(page,length,params);
    }

	
}
