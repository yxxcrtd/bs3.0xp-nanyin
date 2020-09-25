/*
 * 文件名：PtlInvPortfolioStrategyController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：niuqianyu
 * 修改时间：2019年7月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.portfolio.ptlinvportfoliostrategy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.portfolio.ptlinvportfoliostrategy.dto.PtlIncomeIndexDto;
import com.joyintech.service.portfolio.ptlinvportfoliostrategy.dto.PtlInvPortfolioStrategyDto;
import com.joyintech.service.portfolio.ptlinvportfoliostrategy.service.PtlInvPortfolioStrategyService;
import com.joyintech.tams.common.base.entity.PtlPortfolio;
import com.joyintech.tams.common.base.entity.PtlPortfolioStgy;

/**
 * 
 * 投资组合策略管理
 * 
 * @author niuqianyu
 * @version 1.0 2019年7月29日
 * @see PtlInvPortfolioStrategyControllergetPortfolioName
 * @since 1.0
 */
@RestController("ptlInvPortfolioStrategyController_dtx")
@RequestMapping("/ptlinvportfoliostrategy")
public class PtlInvPortfolioStrategyController {

	@Autowired
	private PtlInvPortfolioStrategyService ptlInvPortfolioStrategyService;

	/**
	 * 
	 * 组合代码下拉列表
	 * 
	 * @return List<SelectOptionDto>
	 * @throws Exception
	 *             Exception
	 * @see
	 * @since 1.0
	 */
	@PostMapping("/getPortfolioId/{page}/{length}")
	public Page<SelectOptionDto> getPortfolioId(@RequestBody Map<String, Object> pageParam,
			@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
		return ptlInvPortfolioStrategyService.getPortfolioId(pageParam, page, length);
	}

	/**
	 * 
	 * 获取策略代码下拉列表
	 * 
	 * @return List<SelectOptionDto>
	 * @throws Exception
	 *             Exception
	 * @see
	 * @since 1.0
	 */
	@PostMapping("/getStrategyId/{page}/{length}")
	public Page<SelectOptionDto> getStrategyId(@RequestBody Map<String, Object> pageParam,
			@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
		return ptlInvPortfolioStrategyService.getStrategyId(pageParam, page, length);
	}

	/**
	 * 画面查询方法
	 * 
	 * @param param
	 *            请求参数
	 * @param page
	 *            页面
	 * @param length
	 *            长度
	 * @return 查询结果集
	 * @throws Exception
	 *             异常
	 */
	@PostMapping("selectForPage/{page}/{length}")
	public Page<PtlInvPortfolioStrategyDto> selectForPage(@RequestBody Map<String, Object> pageParam,
			@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
		return ptlInvPortfolioStrategyService.selectForPage(page, length, pageParam);
	}

	/**
	 * 导出
	 * 
	 * @param pageParam
	 * @return
	 */
	@PostMapping("/selectForList")
	public List<PtlInvPortfolioStrategyDto> selectForList(@RequestBody Map<String, Object> pageParam) {
		return ptlInvPortfolioStrategyService.selectForList(pageParam);
	}

	/**
	 * 
	 * 通过组合代码，获取组合名称
	 * 
	 * @return List<SelectOptionDto>
	 * @throws Exception
	 *             Exception
	 * @see
	 * @since 1.0
	 */
	@PostMapping("/getPortfolioName")
	public PtlPortfolio getPortfolioName(@RequestBody Map<String, Object> pageParam) {
		return ptlInvPortfolioStrategyService.getPortfolioName(pageParam);
	}

	/**
	 * 
	 * 通过策略代码，获取策略名称和策略描述
	 * 
	 * @return List<SelectOptionDto>
	 * @throws Exception
	 *             Exception
	 * @see
	 * @since 1.0
	 */
	@PostMapping("/getStrategy")
	public PtlInvPortfolioStrategyDto getStrategy(@RequestBody Map<String, Object> pageParam) {
		return ptlInvPortfolioStrategyService.getStrategy(pageParam);
	}

	/**
	 * 新建-唯一性校验
	 * 
	 * @param dto
	 */
	@PostMapping("/newCheck")
	public PtlPortfolioStgy newCheck(@RequestBody PtlInvPortfolioStrategyDto dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("portfolioId", dto.getPortfolioId());
		map.put("strategyId", dto.getStrategyId());
		map.put("effDate", dto.getEffDate());
		return ptlInvPortfolioStrategyService.newCheck(map);
	}

	/**
	 * 双击查看
	 * 
	 * @param params
	 * @return
	 */
	@PostMapping("/getPtlInvPortfolioStrategy")
	public PtlInvPortfolioStrategyDto getPtlInvPortfolioStrategy(@RequestBody Map<String, Object> params) {
		return ptlInvPortfolioStrategyService.getPtlInvPortfolioStrategy(params);
	}

	/**
	 * 删除
	 * 
	 * @param dto
	 * @return
	 */
	@PostMapping("/delete")
	@Transactional
	@DTXTransaction
	public int delete(@RequestBody PtlInvPortfolioStrategyDto dto) {
		return ptlInvPortfolioStrategyService.delete(dto);
	}

	/**
	 * 新建
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/insert")
	@Transactional
	@DTXTransaction
	public int insert(@RequestBody PtlInvPortfolioStrategyDto dto) throws Exception {
		return ptlInvPortfolioStrategyService.insert(dto);
	}

	/**
	 * 获取收益目标列表
	 * 
	 * @param pageParam
	 * @return
	 */
	@PostMapping("/getPtlIncomeIndexFloatList")
	public List<PtlIncomeIndexDto> getPtlIncomeIndexFloatList(@RequestBody Map<String, Object> pageParam) {
		return ptlInvPortfolioStrategyService.getPtlIncomeIndexFloatList(pageParam);
	}

	/**
	 * 查询组合代码不是没有子组合的产品组合
	 * 
	 * @param params
	 * @return
	 */
	@PostMapping("/getPtlPortfolio")
	public int getPtlPortfolio(@RequestBody Map<String, Object> params) {
		return ptlInvPortfolioStrategyService.getPtlPortfolio(params);
	}

}
