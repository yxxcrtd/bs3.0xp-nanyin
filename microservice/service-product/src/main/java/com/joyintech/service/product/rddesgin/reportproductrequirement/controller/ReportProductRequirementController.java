package com.joyintech.service.product.rddesgin.reportproductrequirement.controller;

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
import com.joyintech.service.product.rddesgin.colproductrequirement.dto.ColProductRequirement001Dto;
import com.joyintech.service.product.rddesgin.colproductrequirement.service.ColProductRequirement001Service;
import com.joyintech.service.product.rddesgin.reportproductrequirement.dto.ReportProductRequirementDto;
import com.joyintech.service.product.rddesgin.reportproductrequirement.service.ReportProductRequirementService;
/**
 * 产品需求上报controller
 * 
 * @author yaozhenguo
 * @version 1.0 2019年9月27日
 * @see ColProductRequirement001Dto
 * @since 1.0
 */
@RestController("backReportProductRequirementController_dtx")
@PageId("ReportProductRequirement001")
@RequestMapping("/ReportProductRequirement")
public class ReportProductRequirementController  {
	
	@Autowired
	private ReportProductRequirementService reportProductRequirementService;
	
	@PostMapping("/findPage/{page}/{length}")
	public Page<ReportProductRequirementDto> selectForPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page, @PathVariable("length") int length) {
		return reportProductRequirementService.selectForPage(page, length, param);
	}
	
	/**
	 * 根据Id查询
	 * @param requirementId
	 * @return
	 */
	@GetMapping("/queryRequirementByRequireId")
	public ReportProductRequirementDto queryRequirementByRequireId(String requirementId) {
		return reportProductRequirementService.queryRequirementByRequireId(requirementId);
	}
	
	@DTXTransaction
	@Transactional
	@PostMapping("/insertColProductRequirementAdd")
	public void insertColProductRequirementAdd(@RequestBody ReportProductRequirementDto reportProductRequirementDto){
		reportProductRequirementService.insertColProductRequirementAdd(reportProductRequirementDto);
	    return;
	}
	
	/**
	 * 根据requirementId删除
	 */
	@DTXTransaction
	@Transactional
	@GetMapping("/deleteColProductRequirement")
	public void deleteColProductRequirement(String requirementId) {
		reportProductRequirementService.deleteColProductRequirement(requirementId);
		return;
	}
	
	/**
	 * 更新需求附属表
	 * @param colProductRequirement001Dto
	 */
	@DTXTransaction
	@Transactional
	@PostMapping("/updateReportStatus")
	public void updateReportStatus(@RequestBody ReportProductRequirementDto reportProductRequirementDto) {
		reportProductRequirementService.updateReportStatus(reportProductRequirementDto);
		return;
	}
	
	/**
	 * 根据ID查询产品收集附属表是否存在
	 * @param requirementId
	 * @return
	 */
	@GetMapping("/countByRequirementId")
	public int countByRequirementId(String requirementId) {
		return reportProductRequirementService.countByRequirementId(requirementId);
	}
}
