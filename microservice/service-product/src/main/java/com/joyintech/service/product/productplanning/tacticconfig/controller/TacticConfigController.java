package com.joyintech.service.product.productplanning.tacticconfig.controller;

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
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.productplanning.tacticconfig.dto.TacticConfig001Dto;
import com.joyintech.service.product.productplanning.tacticconfig.dto.TacticConfig002Dto;
import com.joyintech.service.product.productplanning.tacticconfig.service.TacticConfigService;
import com.joyintech.tams.common.base.entity.TacticConfigOptDetail;
/**
 * yaozhenguo 
 * @author Administrator
 *	20191228 战术配置优化
 */
@RestController("tacticConfigController_dtx")
@RequestMapping("/TacticConfig")
public class TacticConfigController extends BaseController{

	@Autowired
	private TacticConfigService tacticConfigService;
	/**
	 * 查询资产大类下拉框
	 * @param param
	 * @param page
	 * @param length
	 * @return
	 */
	@PostMapping("/selectAllAssetType/{page}/{length}")
	public Page<SelectOptionDto> selectAllAssetType(@RequestBody Map<String, Object> param, @PathVariable("page") int page, @PathVariable("length") int length){
		return tacticConfigService.selectAllAssetType(param, page, length);
	}
	
	/**
	 * 战术配置优化新建插入
	 * @param param
	 */
	@DTXTransaction
	@Transactional
	@PostMapping("/insertTacticConfig")
	public void insertTacticConfig(@RequestBody TacticConfig002Dto tacticConfig002Dto) {
		tacticConfigService.insertTacticConfig(tacticConfig002Dto);
	}
	
	/**
	 * 战术配置优化修改
	 * @param tacticConfig002Dto
	 */
	@DTXTransaction
	@Transactional
	@PostMapping("updateTacticConfig")
	public void updateTacticConfig(@RequestBody TacticConfig002Dto tacticConfig002Dto) {
		tacticConfigService.updateTacTicConfig(tacticConfig002Dto);
	}
	
	/**
	 * 删除战术配置
	 * @param tacticConfig001Dto
	 */
	@DTXTransaction
	@Transactional
	@PostMapping("deleteTacticConfig")
	public void deleteTacticConfig(@RequestBody TacticConfig001Dto tacticConfig001Dto) {
		tacticConfigService.deleteTacticConfig(tacticConfig001Dto);
	}
	
	/**
	 * 页面查询
	 * @param tacticConfig001Dto
	 * @param param
	 * @param page
	 * @param length
	 * @return
	 */
	@PostMapping("/selectForPage/{page}/{length}")
	public Page<TacticConfig001Dto> selectForPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page, @PathVariable("length") int length){
		return tacticConfigService.selectForPage(param, page, length);
	}
	
	/**
	 * 战术配置明细列表查询
	 * @param tacticConfigCode
	 * @return
	 */
	@GetMapping("getTacticConfigOptDetailList")
	public List<TacticConfigOptDetail> getTacticConfigOptDetailList(String tacticConfigCode){
		return tacticConfigService.queryByCode(tacticConfigCode);
	}
	
	/**
	 * 查询最新一天战术配置
	 * @return
	 */
	@GetMapping("/selectFirstTacticConfigOpt")
	public TacticConfig002Dto selectFirstTacticConfigOpt(){
		return tacticConfigService.selectFirstTacticConfigOpt();
	}
	
	/**
	 * 根据战术配置ID查询战术配置
	 * @param tacticConfigCode
	 * @return
	 */
	@GetMapping("selectByTacticConfigCode")
	public TacticConfig002Dto selectByTacticConfigCode(String tacticConfigCode) {
		return tacticConfigService.selectByTacticConfigCode(tacticConfigCode);
	}
}
