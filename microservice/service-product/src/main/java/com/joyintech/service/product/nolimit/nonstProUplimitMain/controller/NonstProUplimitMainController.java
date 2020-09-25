package com.joyintech.service.product.nolimit.nonstProUplimitMain.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.nolimit.nonstProUplimitMain.dto.NonstProUplimitMainDto;
import com.joyintech.service.product.nolimit.nonstProUplimitMain.service.NonstProUplimitMainService;

/**
 * @author NonstProUplimitMainController
 *	
 */
@RestController("nonstProUplimitMainController_dtx")
@RequestMapping("/NonstProUplimitMain")
public class NonstProUplimitMainController extends BaseService<NonstProUplimitMainDto> {
	@Autowired
	NonstProUplimitMainService nonstProUplimitMainService;
	/**
	 * 产品系列下拉框查询
	 * @param page
	 * @param length
	 * @param pageParam
	 * @return
	 */
	@PostMapping("selectPrdFamily/{page}/{length}")
	public Page<SelectOptionDto> selectPrdFamily(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
			@PathVariable("length") int length) {
		return nonstProUplimitMainService.selectPrdFamily(length, length, param);
	}
	/**
	 * 	画面查询方法
	 * @param page
	 * @param length
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@PostMapping("selectForPage/{page}/{length}")
	public Page<NonstProUplimitMainDto> selectForPage(@RequestBody Map<String, Object> param,
			@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
		return nonstProUplimitMainService.selectForPage(page, length, param);
	}
    /**
	 * 	根据系列id 查name
	 * @param page
	 * @param length
	 * @param param
	 * @return
	 */	
	@PostMapping("queryPrdPlanSerName/{page}/{length}")
	public Page<NonstProUplimitMainDto> queryPrdPlanSerName(@RequestBody Map<String, Object> pageParam,
			@PathVariable("page") int page, @PathVariable("length") int length) {
		return nonstProUplimitMainService.queryPrdPlanSerName(page, length, pageParam);
	}
	/**
	 * 	插入方法
	 * @param entity
	 * @return
	 */
	@PostMapping("insert")
	@Transactional
	@DTXTransaction
	public Integer insert(@RequestBody NonstProUplimitMainDto entity) {
		return nonstProUplimitMainService.insert(entity);
	}
	/**
	 * 	根据时间和编号判断是否有重复数据
	 * @param entity
	 * @return
	 */
	@PostMapping("selectPageData")
	public Integer selectPageData(@RequestBody NonstProUplimitMainDto entity) {
		return nonstProUplimitMainService.selectPageData(entity);
	}
	/**
	 * 	修改方法
	 * @param entity
	 * @return
	 */
	@PostMapping("update")
	@Transactional
	@DTXTransaction
	public Integer update(@RequestBody NonstProUplimitMainDto entity) {
		return nonstProUplimitMainService.update(entity);
	}
	/**
	 * 	修改方法
	 * @param entity
	 * @return
	 */
	@PostMapping("delete")
	@Transactional
	@DTXTransaction
	public Integer delete(@RequestBody NonstProUplimitMainDto entity) {
		return nonstProUplimitMainService.delete(entity);
	}
}
