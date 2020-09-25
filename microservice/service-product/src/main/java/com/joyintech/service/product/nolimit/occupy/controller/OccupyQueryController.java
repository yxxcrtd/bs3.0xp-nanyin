package com.joyintech.service.product.nolimit.occupy.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.product.nolimit.occupy.dto.OccupyQueryDto;
import com.joyintech.service.product.nolimit.occupy.service.OccupyQueryService;
/**
 * yaozhenguo 
 * @author Administrator
 *	20191228 非标占比查询
 */
@RestController("OccupyQueryController_dtx")
@RequestMapping("/OccupyQuery")
public class OccupyQueryController extends BaseController{
	
	@Autowired
	private OccupyQueryService occupyQueryService;
	
	@PostMapping("selectForPageByAll")
	public List<OccupyQueryDto> selectForPageByAll(@RequestBody Map<String, Object> param){
		return occupyQueryService.selectForPageByAll(param);
	}
	
	@PostMapping("selectForPageByProdSeries/{page}/{length}")
	public Page<OccupyQueryDto> selectForPageByProdSeries(@RequestBody Map<String, Object> param,
			@PathVariable("page") int page, @PathVariable("length") int length){
		return occupyQueryService.selectForPageByProdSeries(page, length, param);
	}
}
