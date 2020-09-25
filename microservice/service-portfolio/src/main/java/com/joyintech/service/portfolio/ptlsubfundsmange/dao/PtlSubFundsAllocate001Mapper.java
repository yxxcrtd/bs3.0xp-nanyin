/*
 * 文件名：PtlSubFundsAllocate001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：niuqianyu
 * 修改时间：2019年8月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.portfolio.ptlsubfundsmange.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.portfolio.ptlsubfundsmange.dto.PtlSubFundsAllocateDto;

@Repository
public interface PtlSubFundsAllocate001Mapper {

	/**
	 * 子组合间资金调配-组合代码联想框
	 * 
	 * @param pageParam
	 * @return
	 */
	List<SelectOptionDto> getPortfolioId(Map<String, Object> pageParam);
	List<SelectOptionDto> getPortfolioIds(Map<String, Object> pageParam);

	/**
	 * 子组合间资金调配-子组合代码联想框
	 * 
	 * @param pageParam
	 * @return
	 */
	List<SelectOptionDto> getSubPortfolioId(Map<String, Object> pageParam);

	/**
	 * 子组合间资金调配-分页查询
	 * 
	 * @param pageParam
	 * @return
	 */
	List<PtlSubFundsAllocateDto> findFundsAllocatePage(Map<String, Object> pageParam);

	List<PtlSubFundsAllocateDto> getSubFundsAllocate(Map<String, Object> param);
	List<PtlSubFundsAllocateDto> getWfSubFundsAllocate(Map<String, Object> param);
	List<PtlSubFundsAllocateDto> selectOther(Map<String, Object> param);
	
	List<PtlSubFundsAllocateDto> SumCashFlowActualAmt(Map<String,Object> map);
	List<PtlSubFundsAllocateDto> SumCashFlowAvaAmt(Map<String,Object> map);
	/**
	 * 通过组合代码获取组合收益类型
	 */
	List<String> getProfitTypeById(Map<String,Object> map);
}
