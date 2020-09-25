/*
 * 文件名：FinAssetConfigPlanController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhouyongjun
 * 修改时间：2019年12月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.product.productplanning.asset.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.product.productplanning.asset.dto.FinAssetClassDto;
import com.joyintech.service.product.productplanning.asset.dto.FinAssetConfigPlanDto;
import com.joyintech.service.product.productplanning.asset.dto.FinTacticConfigOptDto;
import com.joyintech.service.product.productplanning.asset.service.FinAssetConfigPlanService;
import com.joyintech.tams.common.base.entity.AssetConfigDetail;
import com.joyintech.tams.common.base.entity.AssetConfigMonDetail;
import com.joyintech.tams.common.base.entity.AssetConfigPlan;

/**
 * 资产配置
 * 
 * @author zhouyongjun
 * @version 1.0 2019年12月26日
 * @see FinAssetConfigPlanController
 * @since 1.0
 */
@RestController("backFinAssetConfigPlanController")
@RequestMapping("/FinAssetConfigPlan")
public class FinAssetConfigPlanController {

    /**
     * {@linkplain}
     */
    @Autowired(required=false)
    private FinAssetConfigPlanService finAssetConfigPlanService;
    
    /**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     * @param page page
     * @param length length
     * @param param param
     * @return Page类  Page
     * @throws Exception 异常信息
     */
    @PostMapping("selectForPage/{page}/{length}")
    public Page<FinAssetConfigPlanDto> selectForPage(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return finAssetConfigPlanService.selectForPage(page, length, param);
    }
    
    /**
     * 得到资产配置计划列表
     *
     * @param Map<String, Object> pageParam
     * @return List<FinAssetConfigPlanDto>结果
     * @see
     * @since 1.0
     */
    @PostMapping("queryAssetConfigPlanList")
    public List<FinAssetConfigPlanDto> queryAssetConfigPlanList(@RequestBody Map<String, Object> param) throws Exception {
        return finAssetConfigPlanService.queryAssetConfigPlanList(param);
    }
    
    /**
     * 得到战术配置列表
     *
     * @param Map<String, Object> param
     * @return List<FinTacticConfigOptDto>结果
     * @see
     * @since 1.0
     */
    @PostMapping("queryTactics")
	public List<FinTacticConfigOptDto> queryTactics(@RequestBody Map<String, Object> param){
	    return finAssetConfigPlanService.queryTactics(param);
	}
    
    /**
     * 得到持仓
     *
     * @param Map<String, Object> param
     * @return List<AssetConfigMonDetail>结果
     * @see
     * @since 1.0
     */
    @PostMapping("getCurEndAmt")
	public List<AssetConfigMonDetail> getCurEndAmt(@RequestBody Map<String, Object> param){
	    return finAssetConfigPlanService.getCurEndAmt(param);
	}
    
    /**
     * 调用【资产分类查询】
     *
     * @return List<FinAssetClassDto>结果
     * @see
     * @since 1.0
     */
    @PostMapping("queryAssetClassList")
	public List<FinAssetClassDto> queryAssetClassList(){
	    return finAssetConfigPlanService.queryAssetClassList();
	}
    
    /**
     * 得到资产分类明细
     *
     * @param Map<String, Object> param
     * @return List<AssetConfigDetail>结果
     * @see
     * @since 1.0
     */
    @PostMapping("queryAssetConfigDetails")
	public List<AssetConfigDetail> queryAssetConfigDetails(@RequestBody Map<String, Object> param){
	    return finAssetConfigPlanService.queryAssetConfigDetails(param);
	}
    
    /**
     * 得到资产配置计划列表
     *
     * @param Map<String, Object> param
     * @return List<AssetConfigPlan>结果
     * @see
     * @since 1.0
     */
    @PostMapping("queryAssetConfigPlan")
	public List<AssetConfigPlan> queryAssetConfigPlan(@RequestBody Map<String, Object> param){
	    return finAssetConfigPlanService.queryAssetConfigPlan(param);
	}
    
}
