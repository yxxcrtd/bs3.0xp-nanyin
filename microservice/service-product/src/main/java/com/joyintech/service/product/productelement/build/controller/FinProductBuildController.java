/*
 * 文件名：FinProductBuildController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年9月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.product.productelement.build.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.annotation.DTXTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.service.product.common.dto.BussFinProductLayeredInfoDto;
import com.joyintech.service.product.common.dto.FinProductCommonInfoDto;
import com.joyintech.service.product.productelement.build.dto.FinProductBuild001Dto;
import com.joyintech.service.product.productelement.build.service.FinProductBuildService;
import com.joyintech.tams.common.base.entity.FinBaseRuleInfo;
import com.joyintech.tams.constant.DictCodeListExt2;

/**
 * 理财产品信息管理
 * 
 * @author zhangliang
 * @version 1.0 2018年9月17日
 * @see FinProductBuildController
 * @since 1.0
 */
@RestController("backFinProductBuildController_dtx")
@RequestMapping("/FinProductBuild")
public class FinProductBuildController {

	/**
	 * {@linkplain}
	 */
	@Autowired
	private FinProductBuildService finProductBuildService;
	
	/**
	 * 画面查询方法
	 * 
	 * @return 查询结果集
	 * @throws Exception
	 *             异常
	 */
	@PostMapping("selectForPage/{page}/{length}")
	public Page<FinProductBuild001Dto> selectForPage(@RequestBody Map<String, Object> param,
			@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
		return finProductBuildService.selectForPage(page, length, param);
	}
	
	@PostMapping("getFinBaseRuleInfo")
	public List<FinBaseRuleInfo> getFinCashInfo(@RequestBody Map<String, Object> map){
		return finProductBuildService.getFinBaseRuleInfo(map);
	}
	
	/**
     * 发行状态画面查询方法
     * 
     * @return 查询结果集
     * @throws Exception
     *             异常
     */
	@PostMapping("selectForList")
	public List<FinProductBuild001Dto> selectForList(@RequestBody Map<String, Object> param){
	    return finProductBuildService.selectForList(param);
	}
	
	/**
	 * 组合代码的下拉框
	 * 
	 * @return 查询结果集
	 * @throws Exception
	 *             异常
	 */
	@PostMapping("queryGroupIdInfo/{page}/{length}")
	public Page<SelectOptionDto> queryGroupIdInfo(@RequestBody Map<String, Object> param,
			@PathVariable("page") int page, @PathVariable("length") int length) {
		return finProductBuildService.queryGroupIdInfo(param,page,length);
	}
	
	@PostMapping("queryPrdDesign")
	public List<SelectOptionDto> queryPrdDesign(@RequestBody Map<String, Object> param){
		return finProductBuildService.queryPrdDesign(param);
	}
	
	/**
	 * 产品代码的下拉框
	 * 
	 * @return 查询结果集
	 * @throws Exception
	 *             异常
	 */
	@PostMapping("queryProdIdInfo/{page}/{length}")
	public Page<SelectOptionDto> queryProdIdInfo(@RequestBody Map<String, Object> param,
			@PathVariable("page") int page, @PathVariable("length") int length) {
		return finProductBuildService.queryProdIdInfo(param,page,length);
	}
	
	/**
     * @param finprodId 金融产品代码
     * @return 查询个数
     */
	@GetMapping("querySchedulingCountByFinprodId")
    public int querySchedulingCountByFinprodId(String finprodId){
        return finProductBuildService.querySchedulingCountByFinprodId(finprodId);
    }
    
    /**
     * @param finprodId 金融产品代码
     * @return 查询个数
     */
    @GetMapping("queryProdCountByFinprodId")
    public int queryProdCountByFinprodId(String finprodId){
        return finProductBuildService.queryProdCountByFinprodId(finprodId);
    }
    
    /**
     * 根据金融产品代码查询产品信息
     * @param map 参数
     * @return 产品信息
     */
    @PostMapping("queryProdInfoByFinprodId")
    public FinProductCommonInfoDto queryProdInfoByFinprodId(@RequestBody Map<String, Object> map){
        return finProductBuildService.queryProdInfoByFinprodId(map);
    }
    
    /**
     * 根据金融产品代码修改发行状态
     * @param finProductBuild001Dto 更新对象
     * @return DB
     */
	@Transactional
	@DTXTransaction
    @PostMapping("updateIssueStatusByFinprodId")
    public int updateIssueStatusByFinprodId(@RequestBody FinProductBuild001Dto finProductBuild001Dto){
        return finProductBuildService.updateIssueStatusByFinprodId(finProductBuild001Dto);
    }
    /**
     * 查询机构
     *
     * @param dto dto
     * @return 结     
     * @since 1.0
     */
    @PostMapping("queryTrusteeId/{page}/{length}")
    public Page<SelectOptionDto> queryTrusteeId(@RequestBody Map<String, Object> map,
            @PathVariable("page") int page,
            @PathVariable("length") int length) {
    	return finProductBuildService.queryTrusteeId(map,page,length);
    }
    /**
     * 根据金融产品代码查询产品信息
     * @param map 参数
     * @return 产品信息
     */
    @PostMapping("queryProdInfoByProdId")
    public FinProductCommonInfoDto queryProdInfoByProdId(@RequestBody Map<String, Object> map){
        return finProductBuildService.queryProdInfoByProdId(map);
    }
    /**
     * 根据金融产品代码查询产品信息
     * @param map 参数
     * @return 产品信息
     */
    @PostMapping("queryLayerProdInfoByFinprodId")
    public List<SelectOptionDto> queryLayerProdInfoByFinprodId(@RequestBody Map<String, Object> map){
        return finProductBuildService.queryLayerProdInfoByFinprodId(map);
    }
    /**
     * 获取分层产品产品款型
	* @param finprodId
	* @return
	*/
    @PostMapping("queryLayerProdStyleList")
    public List<SelectOptionDto> queryLayerProdStyleList(@RequestBody Map<String, Object> map){
        return finProductBuildService.queryLayerProdStyleList(map);
    }
    /**
     * 获取分层产品产品款型
     * @param finprodId
     * @return
     */
    @PostMapping("getLayerParamFinprodIdList")
    public List<SelectOptionDto> getLayerParamFinprodIdList(@RequestBody Map<String, Object> map){
    	return finProductBuildService.getLayerParamFinprodIdList(map);
    }
    
    /**
     * 获取产品分层信息中的目标客户类型
     * @param map
     * @return
     */
    @PostMapping("getLayerParam")
    public List<BussFinProductLayeredInfoDto> getLayerParam(@RequestBody Map<String, Object> map){
    	return finProductBuildService.getLayerParam(map);
    }

    
    @RequestMapping("getPreview")
    public int getPreview(String finprodId){
        return finProductBuildService.getPreview(finprodId);
    }
    
    @RequestMapping("getTermType")
    public List<FinProductBuild001Dto> getTermType(Map<String, Object> map){
        return finProductBuildService.getTermType(map);
    }
    @RequestMapping("getTermType02")
    public List<FinProductBuild001Dto> getTermType02(@RequestBody Map<String, Object> map){
    	List<FinProductBuild001Dto> list = finProductBuildService.getTermType02(map);
    	return list;
    }
    
    @RequestMapping("getTermType03")
    public List<FinProductBuild001Dto> getTermType03(@RequestBody Map<String, Object> map){
        List<FinProductBuild001Dto> list = finProductBuildService.getTermType03(map);
        return list;
    }
    
    @RequestMapping("getOpenMode")
    public String getOpenMode(String finprodId) {
    	return finProductBuildService.getOpenMode(finprodId);
    }

}
