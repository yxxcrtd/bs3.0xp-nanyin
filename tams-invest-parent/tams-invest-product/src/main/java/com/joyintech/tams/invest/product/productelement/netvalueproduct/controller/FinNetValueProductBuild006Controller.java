/*
 * 文件名：FinProductBuild001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年9月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productelement.netvalueproduct.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.common.base.entity.FinOpenPlanInfo;
import com.joyintech.tams.invest.product.productelement.netvalueproduct.dto.FinNetValueProductBuild005Dto;

/**
 * 产品新建管理
 * 
 * @author zhangliang
 * @version 1.0 2018年9月17日
 * @see FinNetValueProductBuild001Controller
 * @since 1.0
 */
@Controller
@PageId(value = "FinNetValueProductBuild006")
@RequestMapping("/invest/product/productelement/netvalueproduct/FinNetValueProductBuild006")

public class FinNetValueProductBuild006Controller extends BaseController {

	/**
	 * {@linkplain}
	 */

	@Override
	@RequestMapping(PAGE_INIT)
	public String init(Model model) {

		model.addAttribute("finprodId", this.getParam("finprodId"));
		Map<String, Object> paramMap = RequestUtil.getPageParam();
		//String selectedItem = JsonUtils.object2Json(paramMap.get("selectedItem"));
		model.addAttribute("list", this.getParam("list"));
		model.addAttribute("items", paramMap.get("items"));
		model.addAttribute("period", paramMap.get("period"));
		model.addAttribute("cycle", paramMap.get("cycle"));
		return super.init(model);
	}

	@ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody FinNetValueProductBuild005Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperationType());
    }
	
	@ResponseBody
	@RequestMapping("getList")
	public List<FinNetValueProductBuild005Dto> getList() {		
		Map<String, Object> paramMap = RequestUtil.getPageParam();
		JSONArray itemArray = JSON.parseArray(this.getParam("items").toString());
		JSONArray period = JSON.parseArray(paramMap.get("period").toString());
		List<FinOpenPlanInfo> dtoList = new ArrayList<>();;
		for (Object obj : itemArray) {
			FinOpenPlanInfo dto = JSONObject.parseObject(obj.toString(), FinOpenPlanInfo.class);
			dtoList.add(dto);
        }
		List<Object> pList = new ArrayList<>();;
		if(period!=null) {
		for (Object obj : period) {
			Object dto = JSONObject.parseObject(obj.toString(), Object.class);
			pList.add(dto);
        }}
		String cycle = this.getParam("cycle");				
		List<FinNetValueProductBuild005Dto> list1 = new ArrayList<FinNetValueProductBuild005Dto>();
		if(StringUtils.isNotBlank(cycle)) {
			if(cycle.indexOf('.')!=-1)
				cycle = cycle.substring(0,cycle.indexOf('.'));
			for (FinOpenPlanInfo obj : dtoList) {
				FinNetValueProductBuild005Dto finNetDto = new FinNetValueProductBuild005Dto();
				Date dateEnd = DateUtil.addDay(DateUtil.parse(DateUtils.format(Long.parseLong(obj.getOpenVdate()), "YYYY/MM/dd")),1);
				finNetDto.setOpenVdate(dateEnd);
				finNetDto.setVdateStr(dateEnd);
				finNetDto.setInvestmentCycle(cycle);
				finNetDto.setVdateEnd(DateUtil.addDay(dateEnd, Integer.parseInt(cycle)));
				list1.add(finNetDto);
			}
		}
		else if(!pList.isEmpty()){
			for (Object p : pList) {
				String cyc = p.toString();
				cyc = cyc.substring(cyc.indexOf(':')+2, cyc.lastIndexOf('"'));
				if(cyc.indexOf('.')!=-1)
					cyc = cyc.substring(0,cyc.indexOf('.'));
				for (FinOpenPlanInfo obj : dtoList) {
					FinNetValueProductBuild005Dto finNetDto = new FinNetValueProductBuild005Dto();
					Date dateEnd = DateUtil.addDay(DateUtil.parse(DateUtils.format(Long.parseLong(obj.getOpenVdate()), "YYYY/MM/dd")),1);
					finNetDto.setOpenVdate(dateEnd);
					finNetDto.setVdateStr(dateEnd);
					finNetDto.setInvestmentCycle(cyc);
					finNetDto.setVdateEnd(DateUtil.addDay(dateEnd, Integer.parseInt(cyc)));
					list1.add(finNetDto);
				}
	        }
		}
		
		return list1;
	}
	
}
