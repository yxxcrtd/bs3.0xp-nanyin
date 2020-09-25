/*
 * 文件名：PlanningStrategyPoolController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：niuqianyu
 * 修改时间：2019年12月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.productplanning.planningstrategypool.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.product.productplanning.planningstrategypool.dto.PlanningStrategyPoolDetailDto;
import com.joyintech.service.product.productplanning.planningstrategypool.dto.PlanningStrategyPoolDto;
import com.joyintech.service.product.productplanning.planningstrategypool.service.PlanningStrategyPoolService;

/**
 * 规划策略池维护
 * 
 * @author niuqianyu
 * @version 1.0 2019年12月16日
 * @see PlanningStrategyPoolController
 * @since 1.0
 */
@RestController("planningStrategyPoolController_dtx")
@RequestMapping("/PlanningStrategyPool")
public class PlanningStrategyPoolController {

	@Autowired
	private PlanningStrategyPoolService planningStrategyPoolService;

	/**
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
		return planningStrategyPoolService.getStrategyId(pageParam, page, length);
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
	public Page<PlanningStrategyPoolDto> selectForPage(@RequestBody Map<String, Object> pageParam,
			@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
		// 策略代码处理
		if (StringUtil.isNotEmpty(pageParam.get("strategyId"))) {
			pageParam.put("strategyId", StringUtil.splitToList(null, pageParam.get("strategyId").toString()));
		}
		// 资产大类处理
		if (StringUtil.isNotEmpty(pageParam.get("type2"))) {
			pageParam.put("type2", StringUtil.splitToList(null, pageParam.get("type2").toString()));
		}
		// 使用状态处理
		if (StringUtil.isNotEmpty(pageParam.get("useStatus"))) {
			pageParam.put("useStatus", StringUtil.splitToList(null, pageParam.get("useStatus").toString()));
		}
		return planningStrategyPoolService.selectForPage(page, length, pageParam);
	}

	/**
	 * excel导出查询
	 * 
	 * @param tradeId
	 * @return
	 */
	@PostMapping("selectForExport")
	public List<PlanningStrategyPoolDto> selectForExport(@RequestBody Map<String, Object> pageParam) {
		// 策略代码处理
		if (StringUtil.isNotEmpty(pageParam.get("strategyId"))) {
			pageParam.put("strategyId", StringUtil.splitToList(null, pageParam.get("strategyId").toString()));
		}
		// 资产大类处理
		if (StringUtil.isNotEmpty(pageParam.get("type2"))) {
			pageParam.put("type2", StringUtil.splitToList(null, pageParam.get("type2").toString()));
		}
		// 使用状态处理
		if (StringUtil.isNotEmpty(pageParam.get("useStatus"))) {
			pageParam.put("useStatus", StringUtil.splitToList(null, pageParam.get("useStatus").toString()));
		}
		return planningStrategyPoolService.selectForExport(pageParam);
	}

	/**
	 * 新建
	 * 
	 * @param pageParam
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/insert")
	@Transactional
	@DTXTransaction
	public int insert(@RequestBody PlanningStrategyPoolDto entity) throws Exception {
		return planningStrategyPoolService.insert(entity);
	}

	/**
	 * 修改
	 * 
	 * @param pageParam
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/modify")
	@Transactional
	@DTXTransaction
	public int modify(@RequestBody PlanningStrategyPoolDto entity) throws Exception {
		return planningStrategyPoolService.modify(entity);
	}

	/**
	 * 删除
	 * 
	 * @param pageParam
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/delete")
	@Transactional
	@DTXTransaction
	public int delete(@RequestBody PlanningStrategyPoolDto entity) throws Exception {
		return planningStrategyPoolService.delete(entity);
	}

	/**
	 * 编辑查看
	 * 
	 * @param strategyId
	 * @return
	 */
	@GetMapping("getPlanningStrategyPool")
	public PlanningStrategyPoolDto getPlanningStrategyPool(String strategyId) {

		return planningStrategyPoolService.getPlanningStrategyPool(strategyId);
	}

	/**
	 * 根据策略代码查询策略明细
	 * 
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	@GetMapping("getPlanningStrategyPoolDetail")
	public List<PlanningStrategyPoolDetailDto> getPlanningStrategyPoolDetail(String strategyId) {
		return planningStrategyPoolService.getPlanningStrategyPoolDetail(strategyId);
	}
}
