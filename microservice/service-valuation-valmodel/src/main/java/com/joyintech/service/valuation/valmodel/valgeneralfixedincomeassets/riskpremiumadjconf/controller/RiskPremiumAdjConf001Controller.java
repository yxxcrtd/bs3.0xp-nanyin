/*
 * 文件名：RiskPremiumAdjConf001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xutong
 * 修改时间：2021-06-24
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.valmodel.valgeneralfixedincomeassets.riskpremiumadjconf.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.valmodel.valgeneralfixedincomeassets.riskpremiumadjconf.dto.RiskPremiumAdjConf001Dto;
import com.joyintech.service.valuation.valmodel.valgeneralfixedincomeassets.riskpremiumadjconf.dto.RiskPremiumAdjConf002Dto;
import com.joyintech.service.valuation.valmodel.valgeneralfixedincomeassets.riskpremiumadjconf.service.RiskPremiumAdjConf001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 风险溢价调整配置
 * @author xutong
 * @createTime 2021-06-30
 * @see RiskPremiumAdjConf001Controller
 * @since 1.0
 */
@RestController(value="serviceRiskPremiumAdjConf001Controller")
@RequestMapping("RiskPremiumAdjConf001")
public class RiskPremiumAdjConf001Controller {

	@Autowired
	private RiskPremiumAdjConf001Service riskPremiumAdjConf001Service;
	

	@PostMapping("/findPage/{page}/{length}")
	public Page<RiskPremiumAdjConf001Dto> findPage(@RequestBody Map<String, Object> params,
												   @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
		return  riskPremiumAdjConf001Service.selectForPage(page,length,params);
		
	}

	/**
	 * 主键查询
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("findByPrimaryKey")
	public RiskPremiumAdjConf002Dto findByPrimaryKey(String seqNo) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("seqNo", seqNo);
		return riskPremiumAdjConf001Service.findByPrimaryKey(map);
	}
}
