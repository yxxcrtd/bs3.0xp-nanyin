/*
 * 文件名：ImpairmentProvision001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xutong
 * 修改时间：2021-07-05
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.lossmodel.impairmentprovision.controller;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.lossmodel.impairmentprovision.dto.ImpairmentProvision001Dto;
import com.joyintech.service.valuation.lossmodel.impairmentprovision.service.ImpairmentProvision001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 减值情况计提表
 * @author xutong
 * @createTime 2021-07-05
 * @see ImpairmentProvision001Controller
 * @since 1.0
 */
@RestController(value="serviceImpairmentProvision001Controller")
@RequestMapping("ImpairmentProvision001")
public class ImpairmentProvision001Controller extends BaseController {

	@Autowired
	private ImpairmentProvision001Service impairmentProvision001Service;

	@PostMapping("/findPage/{page}/{length}")
	public Page<ImpairmentProvision001Dto> findPage(@RequestBody Map<String, Object> params,
													@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
		// 交易日期区间
		String decDate = (String)params.get("decDate");
		if(StringUtil.isNotEmpty(decDate)){
			params.put("decDate", DateUtil.parseDate(decDate, "yyyy-MM-dd"));
		}else{
			params.put("decDate", null);
		}
		return impairmentProvision001Service.selectForPage(page, length, params);

	}

	@PostMapping("/findPage")
	public List<ImpairmentProvision001Dto> findPage(@RequestBody Map<String, Object> params) throws Exception {
		// 交易日期区间
		String decDate = (String)params.get("decDate");
		if(StringUtil.isNotEmpty(decDate)){
			params.put("decDate", DateUtil.parseDate(decDate, "yyyy-MM-dd"));
		}else{
			params.put("decDate", null);
		}
		return impairmentProvision001Service.queryDate(params);

	}

}
