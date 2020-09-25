/*
 * 文件名：ReconcileSchemeInfo001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年11月13日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.valuationparametermanage.reconcilescheme.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.progressbar.ProgressBarService;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ExcelUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.valuationparametermanage.reconcilescheme.dto.ValReconcileSchemeAssetConfigDto;
import com.joyintech.service.valuation.valuationparametermanage.reconcilescheme.dto.ValReconcileSchemeInfo001Dto;
import com.joyintech.service.valuation.valuationparametermanage.reconcilescheme.dto.ValReconcileSchemeNatureConfigDto;
import com.joyintech.service.valuation.valuationparametermanage.reconcilescheme.dto.ValReconcileSchemeStructConfigDto;
import com.joyintech.service.valuation.valuationparametermanage.reconcilescheme.dto.ValReconcileSchemeSubjectConfigDto;
import com.joyintech.service.valuation.valuationparametermanage.reconcilescheme.service.ValReconcileSchemeInfo001Service;
import com.joyintech.tams.common.base.entity.BokSubject;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 对账方案管理Controller
 * 
 * @author weipeng
 * @version 1.0 2018年11月13日
 * @see ValReconcileSchemeInfo001Controller
 * @since 1.0
 */
@RestController(value="serviceValReconcileSchemeInfo001Controller")
@RequestMapping("/ValReconcileSchemeInfo001")
public class ValReconcileSchemeInfo001Controller extends BaseController {

	/** 对账规则方案配置Service */
	@Autowired
	private ValReconcileSchemeInfo001Service reconcileSchemeInfo001Service;

	/**
	 * 上传空间进度条Service
	 */
	@Autowired
	private ProgressBarService progressBarService;

	/**
	 * 检索方案名称左侧列表
	 * 
	 * @return 检索方案名称左侧列表
	 * @throws Exception
	 * @since 1.0
	 */
	@PostMapping("/findSchemeList/{page}/{length}")
	public Page<ValReconcileSchemeInfo001Dto> findSchemeList(@RequestBody Map<String, Object> params,
			@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
		return reconcileSchemeInfo001Service.findSchemeList(page, length,params);
	}

	/**
	 * 初始化方案名称下拉框
	 * 
	 * @return 方案名称List
	 * @throws Exception
	 *             错误
	 * @since 1.0
	 */
	@PostMapping("/getSchemeInfo")
	public ValReconcileSchemeInfo001Dto getSchemeInfo(String booksetSchemeId) {
		return reconcileSchemeInfo001Service.getSchemeInfo(booksetSchemeId);
	}

	/**
	 * 初始化结构映射
	 * 
	 * @return 结构映射明细
	 * @since 1.0
	 */
	@PostMapping("/initStructGrid")
	public List<ValReconcileSchemeStructConfigDto> initStructGrid(@RequestBody Map<String, Object> params) {
		return reconcileSchemeInfo001Service.initStructGrid(params);
	}

	/**
	 * 初始化科目映射
	 * 
	 * @return 科目映射明细
	 * @since 1.0
	 */
	@PostMapping("/initSubjectGrid")
	public List<ValReconcileSchemeSubjectConfigDto> initSubjectGrid(@RequestBody Map<String, Object> params) {
		return reconcileSchemeInfo001Service.initSubjectGrid(params);
	}

	/**
	 * 初始化科目性质映射
	 * 
	 * @return 科目映射明细
	 * @since 1.0
	 */
	@PostMapping("/initNatureGrid")
	public List<ValReconcileSchemeNatureConfigDto> initNatureGrid(@RequestBody Map<String, Object> params) {
		return reconcileSchemeInfo001Service.initNatureGrid(params);
	}

	/**
	 * 初始化资产映射
	 * 
	 * @return 资产映射明细
	 * @since 1.0
	 */
	@PostMapping("/initAssetGrid")
	public List<ValReconcileSchemeAssetConfigDto> initAssetGrid(@RequestBody Map<String, Object> params) {
		return reconcileSchemeInfo001Service.initAssetGrid(params);
	}

	/**
	 * 删除方案
	 * 
	 * @return 删除结果
	 * @since 1.0
	 */
	@PostMapping("/deleteBySchemeId")
	public void deleteBySchemeId(@RequestBody Map<String, Object> params) {
		String schId = (String) params.get("schemeId");
		String booksetId = (String) params.get("booksetId");
		reconcileSchemeInfo001Service.deleteBySchemeId(schId, booksetId);
	}

	/**
	 * 保存
	 * 
	 * @param schemeDto
	 *            方案DTO
	 * @param result
	 *            保存结果
	 * @return 保存结果
	 * @since 1.0
	 */
	@PostMapping("/doSaveOrUpdate")
	public void doSaveOrUpdate(@RequestBody ValReconcileSchemeInfo001Dto schemeDto) throws Exception {
		reconcileSchemeInfo001Service.doSaveOrUpdate(schemeDto);
	}

	/**
	 * 获取科目性质映射List
	 * @param params
	 * @return
	 */
	@PostMapping("/getAllBokSubjectMap")
	public List<BokSubject> getAllBokSubjectMap(@RequestBody Map<String, Object> params) {
		String booksetId = (String) params.get("booksetId");
		return reconcileSchemeInfo001Service.getAllBokSubjectMap(booksetId);
	}

}
