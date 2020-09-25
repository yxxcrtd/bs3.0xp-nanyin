package com.joyintech.service.product.rddesgin.colproductrequirement.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.product.common.dto.FinProductCommonInfoDto;
import com.joyintech.service.product.rddesgin.colproductrequirement.dto.ColProductRequirement001Dto;
import com.joyintech.service.product.rddesgin.colproductrequirement.service.ColProductRequirement001Service;
/**
 * 产品需求收集controller
 * 
 * @author yaozhenguo
 * @version 1.0 2018年8月21日
 * @see ColProductRequirement001Dto
 * @since 1.0
 */
@RestController("backColProductRequirementController_dtx")
@PageId("ColProductRequirement001")
@RequestMapping("/ColProductRequirement")
public class ColProductRequirementController  {
	
	@Autowired
	private ColProductRequirement001Service colProductRequirement001Service;
	
	@PostMapping("/findPage/{page}/{length}")
	public Page<ColProductRequirement001Dto> selectForPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page, @PathVariable("length") int length) {
		return colProductRequirement001Service.selectForPage(page, length, param);
	}
	
	@GetMapping("/queryRequirementByRequireId")
	public ColProductRequirement001Dto queryRequirementByRequireId(String requirementId) {
		return colProductRequirement001Service.queryRequirementByRequireId(requirementId);
	}
	
	@DTXTransaction
	@Transactional
	@PostMapping("/insertColProductRequirement")
	public void insertColProductRequirement(@RequestBody ColProductRequirement001Dto colProductRequirement001Dto) throws Exception{
	    colProductRequirement001Service.insertColProductRequirement(colProductRequirement001Dto);
	    return;
	}
	
	/**
	 * 根据requirementId删除（物理删除）
	 */
	@DTXTransaction
	@Transactional
	@GetMapping("/deleteColProductRequirement")
	public void deleteColProductRequirement(String requirementId) {
		colProductRequirement001Service.deleteColProductRequirement(requirementId);
		return;
	}
	
	/**
	 * 更新需求
	 * @param colProductRequirement001Dto
	 */
	@DTXTransaction
	@Transactional
	@PostMapping("/updateColProductRequirement")
	public void updateColProductRequirement(@RequestBody ColProductRequirement001Dto colProductRequirement001Dto) {
		colProductRequirement001Service.updateColProductRequirement(colProductRequirement001Dto);
		return;
	}
	
	/**
	 * 逻辑删除
	 * @param requirementId
	 */
	@DTXTransaction
	@Transactional
	@PostMapping("/deleteByRequirementId")
	public void deleteByRequirementId(@RequestBody ColProductRequirement001Dto colProductRequirement001Dto) {
		colProductRequirement001Service.deleteByRequirementId(colProductRequirement001Dto);
		return;
	}
	
	/**
	 * 发起初评
	 * @param requirementId
	 */
	@DTXTransaction
	@Transactional
	@PostMapping("/reviewStart")
	public void reviewStart(@RequestBody ColProductRequirement001Dto colProductRequirement001Dto) {
		colProductRequirement001Service.reviewStart(colProductRequirement001Dto);
	}
}
