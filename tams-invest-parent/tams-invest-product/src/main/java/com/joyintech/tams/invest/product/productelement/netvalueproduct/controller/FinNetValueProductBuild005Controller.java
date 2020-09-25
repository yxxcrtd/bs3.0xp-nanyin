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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.prod.dto.BussFinProductInvestPeriodDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.product.productelement.netvalueproduct.dto.FinNetValueProductBuild005Dto;
import com.joyintech.tams.invest.product.productelement.netvalueproduct.service.FinNetValueProductBuild005Service;

/**
 * 产品新建管理
 * 
 * @author zhangliang
 * @version 1.0 2018年9月17日
 * @see FinNetValueProductBuild001Controller
 * @since 1.0
 */
@Controller
@PageId(value = "FinNetValueProductBuild005")
@RequestMapping("/invest/product/productelement/netvalueproduct/FinNetValueProductBuild005")

public class FinNetValueProductBuild005Controller extends BaseController {

	/**
	 * {@linkplain}
	 */
	@Autowired
	private FinNetValueProductBuild005Service finNetValueProductBuild005Service;

	/**
	 * {@linkplain}
	 */

	@Override
	@RequestMapping(PAGE_INIT)
	public String init(Model model) {

		model.addAttribute("finprodId", this.getParam("finprodId"));
		return super.init(model);
	}

	@ResponseBody
	@RequestMapping("queryForList")
	public List<FinNetValueProductBuild005Dto> queryForList() {
		Map<String, Object> map = new HashMap<String, Object>();

		List<FinNetValueProductBuild005Dto> list = new ArrayList<FinNetValueProductBuild005Dto>();
		List<FinNetValueProductBuild005Dto> list1 = new ArrayList<FinNetValueProductBuild005Dto>();
		String finprodId = this.getParam("finprodId");

		String openType = finNetValueProductBuild005Service.getOpenMode(finprodId);

		Date ptDate = DateUtil.platDateFromCache();

		// 判断是否高频
		if (StringUtil.equals(openType, DictCodeList.开放模式_高频开放.getValue())) {
			map.put("finprodId", finprodId);
			list = finNetValueProductBuild005Service.getTermType(map);
			for (FinNetValueProductBuild005Dto dto005 : list) {
				dto005.setVdateStr(ptDate);
				dto005.setVdateEnd(
						DateUtil.addDay(DateUtil.platDateFromCache(), Integer.parseInt(dto005.getInvestmentCycle())));
				dto005.setInvestmentCycle(dto005.getInvestmentCycle());
				list1.add(dto005);
			}
		} else {
			map.put("PtDate", DateUtil.format(ptDate));
			map.put("finprodId", finprodId);
			list = finNetValueProductBuild005Service.getTermType03(map);
			for (FinNetValueProductBuild005Dto dto005 : list) {
				if (dto005 != null) {
				    if (StringUtil.isNotEmpty(dto005.getInvestmentCycle())) {
				        dto005.setVdateStr(dto005.getOpenVdate());
	                    dto005.setInvestmentCycle(dto005.getInvestmentCycle());
	                    dto005.setVdateEnd(
	                            DateUtil.addDay(dto005.getOpenVdate(), Integer.parseInt(StringUtil.isNotEmpty(dto005.getInvestmentCycle())?dto005.getInvestmentCycle():"0")));
	                    list1.add(dto005); 
                    }else {
                        List<BussFinProductInvestPeriodDto> periodList = finNetValueProductBuild005Service.queryPeriodList(map);
                        if (!StringUtil.isEmptyList(periodList)) {
                            for (BussFinProductInvestPeriodDto bussFinProductInvestPeriodDto : periodList) {
                                FinNetValueProductBuild005Dto dto00501 = new FinNetValueProductBuild005Dto();
                                dto00501.setVdateStr(dto005.getOpenVdate());
                                dto00501.setInvestmentCycle(bussFinProductInvestPeriodDto.getInvestDays().toString());
                                dto00501.setVdateEnd(
                                        DateUtil.addDay(dto005.getOpenVdate(), bussFinProductInvestPeriodDto.getInvestDays()));
                                list1.add(dto00501); 
                            }
                        } 
                    } 
				}
			}
		}
		return list1;
	}
}
