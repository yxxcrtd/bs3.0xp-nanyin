/*
 * 文件名：AssetImpairmentManagement001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xutong
 * 修改时间：2021-06-24
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.lossmodel.assetimpairmentmanagement.controller;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.lossmodel.assetimpairmentmanagement.dto.AssetImpairmentManagement001Dto;
import com.joyintech.service.valuation.lossmodel.assetimpairmentmanagement.dto.AssetImpairmentManagement002Dto;
import com.joyintech.service.valuation.lossmodel.assetimpairmentmanagement.dto.ModValBatchAssetInfoDto;
import com.joyintech.service.valuation.lossmodel.assetimpairmentmanagement.service.AssetImpairmentManagement001Service;
import com.joyintech.service.valuation.lossmodel.batchtask.dto.BatchAssetInfoDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资产减值管理
 * @author xutong
 * @createTime 2021-06-24
 * @see AssetImpairmentManagement001Controller
 * @since 1.0
 */
@RestController(value="serviceAssetImpairmentManagement001Controller")
@RequestMapping("AssetImpairmentManagement001")
public class AssetImpairmentManagement001Controller extends BaseController {

	@Autowired
	private AssetImpairmentManagement001Service assetImpairmentManagement001Service;
	

	@PostMapping("/findPage/{page}/{length}")
	public Page<AssetImpairmentManagement001Dto> findPage(@RequestBody Map<String, Object> params,
														  @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
		// 交易日期区间
		String tradeDateBegin = (String)params.get("tradeDateBegin");
		if(StringUtil.isNotEmpty(tradeDateBegin)){
			params.put("tradeDateBegin", DateUtil.parseDate(tradeDateBegin, "yyyy-MM-dd"));
		}else{
			params.put("tradeDateBegin", null);
		}
		String tradeDateEnd = (String)params.get("tradeDateEnd");
		if(StringUtil.isNotEmpty(tradeDateEnd)){
			params.put("tradeDateEnd", DateUtil.parseDate(tradeDateEnd, "yyyy-MM-dd"));
		}else{
			params.put("tradeDateEnd", null);
		}
		// 减值日区间
		String impairDateBegin = (String)params.get("impairDateBegin");
		if(StringUtil.isNotEmpty(impairDateBegin)){
			params.put("impairDateBegin", DateUtil.parseDate(impairDateBegin, "yyyy-MM-dd"));
		}else{
			params.put("impairDateBegin", null);
		}
		String impairDateEnd = (String)params.get("impairDateEnd");
		if(StringUtil.isNotEmpty(impairDateEnd)){
			params.put("impairDateEnd", DateUtil.parseDate(impairDateEnd, "yyyy-MM-dd"));
		}else{
			params.put("impairDateEnd", null);
		}
		return  assetImpairmentManagement001Service.selectForPage(page,length,params);
		
	}

	/**
	 * 资产下拉框数据选择
	 *
	 * @return 返回的数据
	 * @exception Exception
	 *                异常
	 * @since 1.0
	 */
	@PostMapping("/getBusiNum/{page}/{length}")
	public Page<SelectOptionDto> getBusiNum(@RequestBody Map<String, Object> pageParam,
											@PathVariable("page") int page, @PathVariable("length") int length) {
		return assetImpairmentManagement001Service.getBusiNum(page,length,pageParam);
	}

	/**
	 * 获取减值结果表中所有业务编号
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("findByPrimaryKey")
	public AssetImpairmentManagement002Dto findByPrimaryKey(String seqNo) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("seqNo", seqNo);
		return assetImpairmentManagement001Service.findByPrimaryKey(map);
	}

	@PostMapping("/findPageout")
	public List<AssetImpairmentManagement001Dto> findPageout(@RequestBody Map<String, Object> params) throws Exception {
		// 交易日期区间
		String tradeDateBegin = (String)params.get("tradeDateBegin");
		if(StringUtil.isNotEmpty(tradeDateBegin)){
			params.put("tradeDateBegin", DateUtil.parseDate(tradeDateBegin, "yyyy-MM-dd"));
		}else{
			params.put("tradeDateBegin", null);
		}
		String tradeDateEnd = (String)params.get("tradeDateEnd");
		if(StringUtil.isNotEmpty(tradeDateEnd)){
			params.put("tradeDateEnd", DateUtil.parseDate(tradeDateEnd, "yyyy-MM-dd"));
		}else{
			params.put("tradeDateEnd", null);
		}
		// 减值日区间
		String impairDateBegin = (String)params.get("impairDateBegin");
		if(StringUtil.isNotEmpty(impairDateBegin)){
			params.put("impairDateBegin", DateUtil.parseDate(impairDateBegin, "yyyy-MM-dd"));
		}else{
			params.put("impairDateBegin", null);
		}
		String impairDateEnd = (String)params.get("impairDateEnd");
		if(StringUtil.isNotEmpty(impairDateEnd)){
			params.put("impairDateEnd", DateUtil.parseDate(impairDateEnd, "yyyy-MM-dd"));
		}else{
			params.put("impairDateEnd", null);
		}
		return assetImpairmentManagement001Service.findPageout(params);

	}

	/**
	 * 资产代码：下拉框 资产减值管理
	 *
	 * @param param 画面参数
	 * @return 资产代码：下拉框
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	@RequestMapping("getFinprodIdForImpairment")
	public List<SelectOptionDto> getFinprodIdForImpairment(@RequestBody Map<String, Object> param) throws Exception {
		return assetImpairmentManagement001Service.getFinprodIdForImpairment( param);
	}

	/**
	 * 产品代码：下拉框 资产减值管理
	 *
	 * @param param 画面参数
	 * @return 产品代码：下拉框
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	@RequestMapping("getProdIdForImpairment")
	public List<SelectOptionDto> getProdIdForImpairment(@RequestBody Map<String, Object> param) throws Exception {
		return assetImpairmentManagement001Service.getProdIdForImpairment(param);
	}

	/**
	 * 业务编号：下拉框 资产减值管理
	 *
	 * @param param 画面参数
	 * @return 业务编号：下拉框
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	@PostMapping("getBusiNoForImpairment")
	public List<SelectOptionDto> getBusiNoForImpairment(@RequestBody Map<String, Object> param) throws Exception {
		return assetImpairmentManagement001Service.getBusiNoForImpairment(param);
	}


	@PostMapping("getAllAssetInfo")
	public List<ModValBatchAssetInfoDto> getAllAssetInfo(@RequestBody Map<String, Object> map) throws Exception {
		List<BatchAssetInfoDto> batchAssetInfoDtoList =  assetImpairmentManagement001Service.getAllAssetInfo(map);
		List<ModValBatchAssetInfoDto> modValBatchAssetInfoDtoList= new ArrayList<>();
		for(BatchAssetInfoDto dto:batchAssetInfoDtoList){
			ModValBatchAssetInfoDto  batchAssetInfo= new ModValBatchAssetInfoDto();
			BeanUtils.copyProperties(dto,batchAssetInfo);
			modValBatchAssetInfoDtoList.add(batchAssetInfo);
		}
		return modValBatchAssetInfoDtoList;
	}




}
