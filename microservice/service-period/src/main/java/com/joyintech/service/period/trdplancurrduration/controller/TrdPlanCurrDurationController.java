/*
 * 文件名：TrdFundDurationController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年11月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.period.trdplancurrduration.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.period.trdplancurrduration.dto.TrdPlanCurrDurationDto;
import com.joyintech.service.period.trdplancurrduration.service.TrdPlanCurrDurationService;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.dto.common.TrdCommonDeleteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author Administrator
 * @version 1.0 2018年11月19日
 * @see TrdPlanCurrDurationController
 * @since 1.0
 */
@RestController("TrdPlanCurrDurationCtr_dtx")
@RequestMapping("/TrdPlanCurrDuration")
public class TrdPlanCurrDurationController {
	/**
	 * 
	 */
	@Autowired
	private TrdPlanCurrDurationService trdPlanCurrDurationService;
    /**
     * 查询有效、撤销或待处理的存续期交易数据
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<TrdPlanCurrDurationDto> findPage(@RequestBody Map<String, Object> param,
                                             @PathVariable("page") int page,
                                             @PathVariable("length") int length) {
        return trdPlanCurrDurationService.findPage(page, length, param);
    }
    /**
     * 
     * 市场代码联动
     * <功能详细描述>
     * @param map 参数
     * @return 联动的详情数据
     * @see
     * @since 1.0
     */
    @PostMapping("finprodMarketIdLink")
    public Map<String,Object>finprodMarketIdLink(@RequestBody Map<String,Object>map){
    	return trdPlanCurrDurationService.finprodMarketIdLink(map);
    }
    /**
     * 
     * 组合代码联动
     * <功能详细描述>
     * @param map 参数
     * @return 联动的详情数据
     * @see
     * @since 1.0
     */
    @PostMapping("portfolioIdLink")
    public Map<String,Object>portfolioIdLink(@RequestBody Map<String,Object>map){
    	return trdPlanCurrDurationService.portfolioIdLink(map);
    }
    /**
     * 剩余本金计算联动 <功能详细描述>
     *
     * @param map 参数
     * @return 联动的详情数据
     * @see
     * @since 1.0
     */
    @PostMapping("calRegistShare")
    public Map<String, Object> calRegistShare(@RequestBody Map<String, Object> map) {
        return trdPlanCurrDurationService.calRegistShare(map);
    }
    /**
     * 作废流程， 并删除流程表数据
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doDelete")
    public String doDelete(@RequestBody TrdCommonDeleteDto trdCommonDeleteDto) {
        return trdPlanCurrDurationService.doDelete(trdCommonDeleteDto);
    }
    /**
     * 插入数据，并删除流程表数据
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doComplete")
    public String doComplete(@RequestBody TrdPlanCurrDurationDto entity) {
        return trdPlanCurrDurationService.doComplete(entity);
    }
    /**
     * 插入数据，并删除流程表数据
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findEffectiveData")
    public TrdPlanCurrDurationDto findEffectiveData(@RequestBody Map<String, Object> map) {
        return trdPlanCurrDurationService.findEffectiveData(map);
    }

    /**
     * 现金分红流程中
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findCashDividend")
    public TrdPlanCurrDurationDto findCashDividend(@RequestBody Map<String, Object> map) {
        return trdPlanCurrDurationService.findCashDividend(map);
    }

    /**
     * 统计是否存在后续交易
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("countFollowDeal")
    public int countFollowDeal(@RequestBody Map<String, Object> map) {
        return trdPlanCurrDurationService.countFollowDeal(map);
    }
    /**
     * 查询利息明细信息
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findAssetIntInfo")
    public List<FinAssetIntInfo> findAssetIntInfo(@RequestBody Map<String, Object> params) {
        return trdPlanCurrDurationService.findAssetIntInfo(params);
    }
    /**
     * 查询利息明细信息
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("distributeDataByPercent")
    public List<FinAssetIntInfo> distributeDataByPercent(@RequestBody Map<String, Object> params) {
        if(params.containsKey("preMap")){
            Map<String,Object>orgMap = JsonUtils.json2Object((String)params.get("preMap"),Map.class);
            Map<String,BigDecimal>preMap = new HashMap<>();
            for(String key : orgMap.keySet()){
                preMap.put(key, StringUtil.isNotEmpty(orgMap.get(key))?new BigDecimal(orgMap.get(key).toString()):BigDecimal.ZERO);
            }
            params.put("preMap",preMap);
        }
        if(params.containsKey("defaultMap")){
            Map<String,String>orgMap = JsonUtils.json2Object((String)params.get("defaultMap"),Map.class);
            params.put("defaultMap",orgMap);
        }
        return trdPlanCurrDurationService.distributeDataByPercent(params);
    }
}
