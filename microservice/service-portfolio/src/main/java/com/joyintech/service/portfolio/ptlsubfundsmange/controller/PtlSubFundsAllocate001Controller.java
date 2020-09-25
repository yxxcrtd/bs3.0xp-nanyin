/*
 * 文件名：PtlSubFundsAllocate001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：niuqianyu
 * 修改时间：2019年8月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.portfolio.ptlsubfundsmange.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.portfolio.ptlsubfundsmange.dto.PtlSubFundsAllocateDto;
import com.joyintech.service.portfolio.ptlsubfundsmange.service.PtlSubFundsAllocate001Service;

/**
 * 
 * 子组合间资金调配
 * 
 * @author niuqianyu
 * @version 1.0 2019年8月15日
 * @see PtlSubFundsAllocate001Controller
 * @since 1.0
 */
@RestController
@RequestMapping("/ptlsubfundsallocate001")
public class PtlSubFundsAllocate001Controller {

	@Autowired
	private PtlSubFundsAllocate001Service ptlSubFundsAllocate001Service;

	/**
	 * 子组合间资金调配-组合代码联想框
	 * 
	 * @param pageParam
	 * @return
	 */
	@PostMapping("/getPortfolioId/{page}/{length}")
	public Page<SelectOptionDto> getPortfolioId(@RequestBody Map<String, Object> pageParam,
			@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
		return ptlSubFundsAllocate001Service.getPortfolioId(pageParam, page, length);
	}

	/**
	 * 子组合间资金调配-子组合代码联想框
	 * 
	 * @param pageParam
	 * @return
	 */
	@PostMapping("/getSubPortfolioId/{page}/{length}")
	public Page<SelectOptionDto> getSubPortfolioId(@RequestBody Map<String, Object> pageParam,
			@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
		return ptlSubFundsAllocate001Service.getSubPortfolioId(pageParam, page, length);
	}

	/**
	 * 子组合间资金调配-分页查询
	 * 
	 * @param parameters
	 * @return
	 */
	@PostMapping("findFundsAllocatePage/{page}/{length}")
	public Page<PtlSubFundsAllocateDto> findFundsAllocatePage(@RequestBody Map<String, Object> pageParam,
			@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
		return ptlSubFundsAllocate001Service.findFundsAllocatePage(page, length, pageParam);
	}
	
	/**
	 * 子组合间资金调配-导出
	 * 
	 * @param parameters
	 * @return
	 */
	@PostMapping("selectFundsAllocateList")
	public List<PtlSubFundsAllocateDto> selectFundsAllocateList(@RequestBody Map<String, Object> pageParam) throws Exception {
		return ptlSubFundsAllocate001Service.selectFundsAllocateList(pageParam);
	}

	 @PostMapping("selectOther/{page}/{length}")
    public Page<PtlSubFundsAllocateDto> selectOther(@RequestBody Map<String, Object> param, @PathVariable("page") int page, @PathVariable("length") int length)
            throws Exception{
        return ptlSubFundsAllocate001Service.selectOther(page, length, param);
    }
	 
}
