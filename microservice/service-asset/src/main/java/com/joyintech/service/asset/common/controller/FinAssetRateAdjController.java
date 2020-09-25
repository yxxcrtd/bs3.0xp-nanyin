/*
 * 文件名：FinAssetRateAdjController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.common.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.asset.common.service.FinAssetRateAdjService;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.invest.common.asset.dto.AssetCommonInfoDto;
import com.joyintech.tams.invest.common.asset.dto.RateAdjDto;

/**
 *  利率调整
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月11日
 * @see FinAssetRateAdjController
 * @since 1.0
 */
@RestController("FinAssetRateAdjController_dtx")
@RequestMapping("/FinAssetRateAdj")
public class FinAssetRateAdjController {

    @Autowired
    private FinAssetRateAdjService finAssetRateAdjService;
    
    
    
    @PostMapping("getfinAssetRateAdjByProductId")
    public List<RateAdjDto> getfinAssetRateAdjByProductId(@RequestBody Map<String, Object> param) throws Exception {
        return finAssetRateAdjService.getfinAssetRateAdjByProductId(param);
    }
    
    /**
     * 费率调整下拉框
     * @param param
     * @return
     */
    @PostMapping("queryFeeInfoSel")
    public List<SelectOptionDto> queryFeeInfoSel(@RequestBody Map<String, Object> param){
    	return finAssetRateAdjService.queryFeeInfoSel(param);
    }
    
    /**
     * 重新查询利率调整初始化信息
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("selectInitAssetRateAdj")
    public List<RateAdjDto> selectInitAssetRateAdj(@RequestBody Map<String, Object> param){
        return finAssetRateAdjService.selectInitAssetRateAdj(param);
    }
    /**
     * 入库
     * <功能详细描述>
     * @param dto
     * @throws Exception
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public void insert(@RequestBody RateAdjDto dto) throws Exception {
        finAssetRateAdjService.insert(dto);
    }
    
    /**
     *  获取利率调整信息
     * <一句话功能简述>
     * <功能详细描述>
     * @param finprodId
     * @param finprodType
     * @param cashId
     * @param cashType
     * @param isInitial
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("getFinAssetRateAdjByCashId")
    public List<FinCashCalcRule> getFinAssetRateAdjByCashId(String finprodId,String finprodType,String cashId,String cashType,String isInitial){
        return finAssetRateAdjService.getFinAssetRateAdjByCashId(finprodId,finprodType,cashId,cashType,isInitial);
    }
    
    /**
     * 获取市场利率行情信息
     * <功能详细描述>
     * @param indexId
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("getMstRateInfo")
    public List<FinCashCalcRule> getMstRateInfo(String indexId){
        return finAssetRateAdjService.getMstRateInfo(indexId);
    }
    
    /**
     * 获取利率调整需要的资产信息
     * <功能详细描述>
     * @param finprodId
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("getAssetBasicInfoByFinprodId")
    public AssetCommonInfoDto getAssetBasicInfoByFinprodId(@RequestBody Map<String,Object> map){
        return finAssetRateAdjService.getAssetBasicInfoByFinprodId(map);
    }
}
