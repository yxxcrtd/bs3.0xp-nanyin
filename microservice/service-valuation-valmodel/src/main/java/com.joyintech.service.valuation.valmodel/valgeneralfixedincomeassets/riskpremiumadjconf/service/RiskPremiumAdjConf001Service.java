/*
 * 文件名：RiskPremiumAdjConf001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xutong
 * 修改时间：2021-06-30
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.valmodel.valgeneralfixedincomeassets.riskpremiumadjconf.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.valmodel.valgeneralfixedincomeassets.riskpremiumadjconf.dao.RiskPremiumAdjConf001Mapper;
import com.joyintech.service.valuation.valmodel.valgeneralfixedincomeassets.riskpremiumadjconf.dto.RiskPremiumAdjConf001Dto;
import com.joyintech.service.valuation.valmodel.valgeneralfixedincomeassets.riskpremiumadjconf.dto.RiskPremiumAdjConf002Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 风险溢价调整配置
 * @author xutong
 * @createTime 2021-06-30
 * @see RiskPremiumAdjConf001Service
 * @since 1.0
 */
@Service(value="serviceRiskPremiumAdjConf001Service")
public class RiskPremiumAdjConf001Service extends BaseService<RiskPremiumAdjConf001Dto> {
	
	@Autowired
	private RiskPremiumAdjConf001Mapper riskPremiumAdjConf001Mapper;

	public Page<RiskPremiumAdjConf001Dto> selectForPage(int page, int length, Map<String, Object> params)
			 throws Exception {
		 return super.queryForPage(page,length,() -> riskPremiumAdjConf001Mapper.queryList(params));
	}

	public RiskPremiumAdjConf002Dto findByPrimaryKey(Map<String, Object> pageParam) {
		return riskPremiumAdjConf001Mapper.findByPrimaryKey(pageParam);
	}

}
