/*
 * 文件名：PtlSubFundsAllocate001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：niuqianyu
 * 修改时间：2019年8月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.ptlsubfundsmange.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.portfolio.ptlsubfundsmange.dto.PtlSubFundsAllocate001Dto;

/**
 * 
 * 子组合资金调配
 * 
 * @author niuqianyu
 * @version 1.0 2019年8月14日
 * @see PtlSubFundsAllocate001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PtlSubFundsAllocate001Service extends CommonExportService<PtlSubFundsAllocate001Dto> {

	/**
	 * 子组合间资金调配-组合代码联想框
	 * 
	 * @param pageParam
	 * @return
	 */
	public Page<SelectOptionDto> getPortfolioId(Map<String, Object> pageParam) {
		return restTemplate.postForPage(
				ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlsubfundsallocate001/getPortfolioId", pageParam,
				SelectOptionDto.class);
	}

	/**
	 * 子组合间资金调配-子组合代码联想框
	 * 
	 * @param pageParam
	 * @return
	 */
	public Page<SelectOptionDto> getSubPortfolioId(Map<String, Object> pageParam) {
		return restTemplate.postForPage(
				ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlsubfundsallocate001/getSubPortfolioId", pageParam,
				SelectOptionDto.class);
	}

	/**
	 * 子组合间资金调配-分页查询
	 * 
	 * @param parameters
	 * @return
	 */
	public Page<PtlSubFundsAllocate001Dto> findFundsAllocatePage(Map<String, Object> pageParam) {
		String portfolioId = (String) pageParam.get("portfolioId");
		if (StringUtil.isNotEmpty(portfolioId)) {
			List<String> portfolioIdList = StringUtil.splitToList(",", portfolioId);
			pageParam.put("portfolioIdList", portfolioIdList);
		}

		String subPortfolioId = (String) pageParam.get("subPortfolioId");
		if (StringUtil.isNotEmpty(subPortfolioId)) {
			List<String> subPortfolioIdList = StringUtil.splitToList(",", subPortfolioId);
			pageParam.put("subPortfolioIdList", subPortfolioIdList);
		}
		return restTemplate.postForPage(
				ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlsubfundsallocate001/findFundsAllocatePage", pageParam,
				PtlSubFundsAllocate001Dto.class);
	}

	/**
	 * 子组合间资金调配-导出
	 * 
	 * @param parameters
	 * @return
	 */
	public List<PtlSubFundsAllocate001Dto> selectFundsAllocateList(Map<String, Object> pageParam) {
		String portfolioId = (String) pageParam.get("portfolioId");
		if (StringUtil.isNotEmpty(portfolioId)) {
			List<String> portfolioIdList = StringUtil.splitToList(",", portfolioId);
			pageParam.put("portfolioIdList", portfolioIdList);
		}

		String subPortfolioId = (String) pageParam.get("subPortfolioId");
		if (StringUtil.isNotEmpty(subPortfolioId)) {
			List<String> subPortfolioIdList = StringUtil.splitToList(",", subPortfolioId);
			pageParam.put("subPortfolioIdList", subPortfolioIdList);
		}
		return restTemplate.postForList(
				ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlsubfundsallocate001/selectFundsAllocateList",
				pageParam, PtlSubFundsAllocate001Dto.class);
	}

}
