package com.joyintech.service.product.messageadjust.supplement.controller;

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
import com.joyintech.service.product.messageadjust.supplement.dto.FinSupplementPlanChgDto;
import com.joyintech.service.product.messageadjust.supplement.service.FinSupplementPlanChgService;

@RestController("backFinSupplementPlanChgController")
@RequestMapping("/FinSupplementPlanChg")
public class FinSupplementPlanChgController {

	@Autowired
	private FinSupplementPlanChgService finSupplementPlanChgService;

	@PostMapping("getFinprodIds/{page}/{length}")
	public Page<SelectOptionDto> getFinprodIds(@RequestBody Map<String, Object> param,
	                                           @PathVariable("page")int page,@PathVariable("length") int length) {
		return finSupplementPlanChgService.getFinprodIds(param,page,length);
	}

	@PostMapping("findPage/{page}/{length}")
	public Page<FinSupplementPlanChgDto> findPage(@RequestBody Map<String, Object> param,
	                                              @PathVariable("page")int page,@PathVariable("length") int length) {
		return finSupplementPlanChgService.findPage(param,page,length);
	}

	@PostMapping("getcycleTdayRule")
	public String getcycleTdayRule(@RequestBody Map<String, Object> param) {
		return finSupplementPlanChgService.getcycleTdayRule(param);
	}

	@PostMapping("selectForPage/{page}/{length}")
	public Page<FinSupplementPlanChgDto> selectForPage(@RequestBody Map<String, Object> param,
			@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
		Page<FinSupplementPlanChgDto> dtoPage = finSupplementPlanChgService.selectForPage(page, length, param);
		return dtoPage;
	}
	
	/**
	 * 删除旧的回补计划
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	@Transactional
    @DTXTransaction
	@PostMapping("deleteAccrualSche")
	public boolean deleteAccrualSche(@RequestBody Map<String, Object> param) {
        return finSupplementPlanChgService.deleteAccrualSche(param);
	}

}
