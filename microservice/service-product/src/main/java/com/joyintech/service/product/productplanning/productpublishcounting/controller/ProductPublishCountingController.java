package com.joyintech.service.product.productplanning.productpublishcounting.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.productplanning.common.dto.HoldPositionDto;
import com.joyintech.service.product.productplanning.common.service.PlanningCommonService;
import com.joyintech.service.product.productplanning.productpublishcounting.dto.ProductPublishCounting001Dto;
import com.joyintech.service.product.productplanning.productpublishcounting.service.ProductPublishCountingService;
import com.joyintech.tams.common.base.entity.AssetPlanDetail;
/**
 * 产品发行规划试算
 * @version 1.0 2018年12月18日
 * @see ProductPublishCountingController
 * @since 1.0
 */
@RestController("ProductPublishCountingController_dtx")
@RequestMapping("/ProductPublishCounting")
public class ProductPublishCountingController {
	
	@Autowired
    private ProductPublishCountingService productPublishCountingService;
	
	/**
     * 产品系列列表查询
     */
    @PostMapping("/findPrdPage/{page}/{length}")
    public Page<ProductPublishCounting001Dto> findPrdPage(@RequestBody Map<String, Object> pageParam, 
    		@PathVariable("page") int page, @PathVariable("length") int length){
        Page<ProductPublishCounting001Dto> prdPage = productPublishCountingService.findPrdPage(pageParam, page, length);
        return prdPage;
    }
	/**
     * 资产类列表查询
     */
    @PostMapping("/findAssetPage/{page}/{length}")
    public Page<ProductPublishCounting001Dto> findAssetPage(@RequestBody Map<String, Object> pageParam, 
    		@PathVariable("page") int page, @PathVariable("length") int length){
        return productPublishCountingService.findAssetPage(pageParam, page, length);
    }
    
	/**
     * 查询资产大类列表
     */
    @RequestMapping("/getAssetPlanList")
    public List<ProductPublishCounting001Dto> getAssetPlanList(@RequestBody Map<String,Object> params){
        return productPublishCountingService.getAssetPlanList(params);
    }
    
    /**
     * 查询策略明细
     */
    @RequestMapping("/getInvStrategyDetailList")
    public List<ProductPublishCounting001Dto> getInvStrategyDetailList(@RequestBody String strategyId){
        return productPublishCountingService.getInvStrategyDetailList(strategyId);
    }
    
    /**
     * 发行规划信息查询
     */
    @RequestMapping("/getPrdIssuePlanInfoByCode")
    public ProductPublishCounting001Dto getPrdIssuePlanInfoByCode(@RequestBody String code){
        return productPublishCountingService.getPrdIssuePlanInfoByCode(code);
    }
    
    /**
     * 发行规划策略查询
     */
    @RequestMapping("/getPrdPlanStrategyListByCode")
    public List<ProductPublishCounting001Dto> getPrdPlanStrategyListByCode(@RequestBody String code){
        return productPublishCountingService.getPrdPlanStrategyListByCode(code);
    }
    
    /**
     * 发行规划资产大类查询
     */
    @RequestMapping("/getPrdPlanAssetListByCode")
    public List<ProductPublishCounting001Dto> getPrdPlanAssetListByCode(@RequestBody String code){
        return productPublishCountingService.getPrdPlanAssetListByCode(code);
    }
    
    /**
     * 通过产品发行规划编号删除产品发行规划策略
     */
    @RequestMapping("/deleteIssueplanStrategyByCode")
    public int deleteIssueplanStrategyByCode(@RequestBody String code){
        return productPublishCountingService.deleteIssueplanStrategyByCode(code);
    }
    
    /**
     * 通过产品发行规划编号删除产品发行规划策略明细
     */
    @RequestMapping("/deleteStrategyDetailByCode")
    public int deleteStrategyDetailByCode(@RequestBody String code){
        return productPublishCountingService.deleteStrategyDetailByCode(code);
    }
    
    /**
     * 通过产品发行规划编号删除产品发行资产大类
     */
    @RequestMapping("/deleteIssueplanAssetByCode")
    public int deleteIssueplanAssetByCode(@RequestBody String code){
        return productPublishCountingService.deleteIssueplanAssetByCode(code);
    }
    
    /**
     * 
     */
    @RequestMapping("/getPrdIssueplanCode/{page}/{length}")
    public Page<SelectOptionDto> getPrdIssueplanCode(@RequestBody Map<String, Object> map,
			@PathVariable("page") int page, @PathVariable("length") int length){
        return productPublishCountingService.getPrdIssueplanCode(map, page, length);
    }
}
