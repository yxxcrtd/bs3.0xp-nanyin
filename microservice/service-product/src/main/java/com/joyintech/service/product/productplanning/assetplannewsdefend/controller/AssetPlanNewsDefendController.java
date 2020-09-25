package com.joyintech.service.product.productplanning.assetplannewsdefend.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.product.productplanning.assetplannewsdefend.dto.AssetPlanNewsDefend;
import com.joyintech.service.product.productplanning.assetplannewsdefend.dto.AssetPlanNewsForm;
import com.joyintech.service.product.productplanning.assetplannewsdefend.service.AssetPlanNewsDefendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 资产规划信息维护
 * @author guyunfei
 * 20191226
 */
@RestController("AssetPlanNewsDefendController_dtx")
@RequestMapping("/AssetPlanNewsDefend")
public class AssetPlanNewsDefendController {

    @Autowired
    private AssetPlanNewsDefendService assetPlanNewsDefendService;

    /**
     *  资产规划信息维护 一览查询
     * @param params
     * @param page
     * @param length
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectForPage/{page}/{length}")
    public Page<AssetPlanNewsDefend> selectForPage(@RequestBody Map<String,Object> params,
                                                   @PathVariable("page") int page, @PathVariable("length") int length)throws Exception{
        return assetPlanNewsDefendService.selectForPage(page,length,params);
    }

    /**
     * 资产规划信息维护 用于相同时间check
     * @param params
     * @return
     */
    @PostMapping("/getConflictCount")
    public int getConflictCount(@RequestBody Map<String,Object> params){
        return assetPlanNewsDefendService.getConflictCount(params);
    }

    /**
     * 资产规划信息维护 用于重叠时间check
     * @param params
     * @return
     */
    @PostMapping("/getOverlapCount")
    public int getOverlapCount(@RequestBody Map<String,Object> params){
        return assetPlanNewsDefendService.getOverlapCount(params);
    }

    /**
     * 产品系列规划目标 用于check
     * @param params
     * @return
     */
    @PostMapping("/queryProductTarget")
    public List<AssetPlanNewsDefend> queryProductTarget(@RequestBody Map<String,Object> params){
        return assetPlanNewsDefendService.queryProductTarget(params);
    }

    /**
     * 删除
     * @param params
     * @return
     */
    @RequestMapping("/deleteByPrimary")
    @Transactional
    @DTXTransaction
    public Integer deleteByPrimary(@RequestBody Map<String,Object> params){
        return assetPlanNewsDefendService.deleteByPrimary(params);
    }

    /**
     * 查看详情
     * @param assetPlanCode
     * @return
     */
    @RequestMapping("/queryData")
    public AssetPlanNewsForm queryData(String assetPlanCode){
        return assetPlanNewsDefendService.queryData(assetPlanCode);
    }

    /**
     * 新增、修改
     * @param form
     * @return
     */
    @RequestMapping("/updateOrAdd")
    @Transactional
    @DTXTransaction
    public Integer updateOrAdd(@RequestBody AssetPlanNewsForm form){
        return assetPlanNewsDefendService.updateOrAdd(form);
    }
}
