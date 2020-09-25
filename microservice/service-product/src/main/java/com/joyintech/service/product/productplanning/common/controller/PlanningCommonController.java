package com.joyintech.service.product.productplanning.common.controller;

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
import com.joyintech.service.product.productplanning.common.dto.BondRepaymentAmountDto;
import com.joyintech.service.product.productplanning.common.dto.HoldPositionDto;
import com.joyintech.service.product.productplanning.common.dto.PlanningCommonDto;
import com.joyintech.service.product.productplanning.common.dto.TacticsOptimizeDto;
import com.joyintech.service.product.productplanning.common.service.PlanningCommonService;
import com.joyintech.tams.common.base.entity.AssetClass;
import com.joyintech.tams.common.base.entity.AssetConfigMonDetail;
import com.joyintech.tams.common.base.entity.AssetPlanInfo;
import com.joyintech.tams.common.base.entity.PrdPlanDetail;
import com.joyintech.tams.common.base.entity.PrdPlanInfo;
import com.joyintech.tams.common.base.entity.PtlInvStrategy;
/**
 * 产品规划共通
 */
@RestController("PlanningCommonController_dtx")
@RequestMapping("/PlanningCommon")
public class PlanningCommonController {

    @Autowired
    private PlanningCommonService planningCommonService;

    /**
     * 持仓查询
     * @param params :cdate 参数.基准日
     */
    @RequestMapping("/queryHoldPosition")
    public List<HoldPositionDto> queryHoldPosition(@RequestBody Map<String,Object> params){
        return planningCommonService.queryHoldPosition(params);
    }

    /**
     * 资产分类查询
     */
    @RequestMapping("/queryAssetType")
    public List<AssetClass> queryAssetType(){
        return planningCommonService.queryAssetType();
    }

    /**
     * 战术配置优化查询
     *
     * @param params :vdate 参数.开始日
     *               :edate 参数.结束日
     */
    @RequestMapping("/queryTacticsOptimize")
    public List<TacticsOptimizeDto> queryTacticsOptimize(@RequestBody Map<String,Object> params){
        return planningCommonService.queryTacticsOptimize(params);
    }

    /**
     * 产品规划信息查询
     * @param params :vdate 参数.开始日
     *               :edate 参数.结束日
     */
    @RequestMapping("/queryProductPlan")
    public List<PrdPlanInfo> queryProductPlan(@RequestBody Map<String,Object> params){
        return planningCommonService.queryProductPlan(params);
    }

    /**
     * 资产规划信息查询
     * @param params :vdate 参数.开始日
     *               :edate 参数.结束日
     */
    @RequestMapping("/quertAssetPlan")
    public List<AssetPlanInfo> quertAssetPlan(@RequestBody Map<String,Object> params){
        return planningCommonService.quertAssetPlan(params);
    }

    /**
     * 规划策略查询
     */
    @RequestMapping("/queryPlanStrategy")
    public List<PtlInvStrategy> queryPlanStrategy(){
        return planningCommonService.queryPlanStrategy();
    }

    /**
     * 债券还本到期金额
     * @param paramMap
     * @return
     */
    @RequestMapping("/getBondRepaymentAmount")
    public List<BondRepaymentAmountDto> getBondRepaymentAmount(@RequestBody Map<String, Object> paramMap){
        return planningCommonService.getBondRepaymentAmount(paramMap);
    }

    /**
     * 资产还本到期金额
     * @param map
     * @return
     */
    @RequestMapping("/getCurEndAmt")
    public List<AssetConfigMonDetail> getCurEndAmt(@RequestBody Map<String, Object> map){
        return planningCommonService.getCurEndAmt(map);
    }

    @PostMapping("findPage/{page}/{length}")
    public Page<PlanningCommonDto> queryForPagePlanningCommon(@RequestBody Map<String, Object> pageParam,
                                                    @PathVariable("page") int page,
                                                    @PathVariable("length") int length)
        throws Exception {
        return planningCommonService.queryForPagePlanningCommon(page, length, pageParam);
    }
    /**
     * 存续产品系列规划信息查询
     * @param map
     * @return
     */
    @RequestMapping("/getPrdPlanDetail")
    public List<PrdPlanDetail> getPrdPlanDetail(@RequestBody Map<String, Object> map){
        return planningCommonService.getPrdPlanDetail(map);
    }
    
    /**
     * 投资策略查询
     * @param map
     * @return
     */
    @RequestMapping("/getPtlInvStrategyList")
    public List<SelectOptionDto> getPtlInvStrategyList(@RequestBody Map<String, Object> map){
        return planningCommonService.getPtlInvStrategyList(map);
    }
}
