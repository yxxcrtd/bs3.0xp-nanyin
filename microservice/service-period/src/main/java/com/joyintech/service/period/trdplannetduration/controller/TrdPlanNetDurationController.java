/*
 * 文件名：TrdFundDurationController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年11月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.period.trdplannetduration.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.period.trdplannetduration.dto.TrdPlanNetDurationDto;
import com.joyintech.service.period.trdplannetduration.service.TrdPlanNetDurationService;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author Administrator
 * @version 1.0 2018年11月19日
 * @see TrdPlanNetDurationController
 * @since 1.0
 */
@RestController("TrdPlanNetDurationCtr_dtx")
@RequestMapping("/TrdPlanNetDuration")
public class TrdPlanNetDurationController {
	/**
	 * 
	 */
	@Autowired
	private TrdPlanNetDurationService trdPlanNetDurationService;
    /**
     * 查询有效、撤销或待处理的存续期交易数据
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<TrdPlanNetDurationDto> findPage(@RequestBody Map<String, Object> param,
                                             @PathVariable("page") int page,
                                             @PathVariable("length") int length) {
        return trdPlanNetDurationService.findPage(page, length, param);
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
    	return trdPlanNetDurationService.finprodMarketIdLink(map);
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
    	return trdPlanNetDurationService.portfolioIdLink(map);
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
        return trdPlanNetDurationService.calRegistShare(map);
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
    public String doComplete(@RequestBody TrdPlanNetDurationDto entity) {
        return trdPlanNetDurationService.doComplete(entity);
    }
    /**
     * 获取有效数据
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findEffectiveData")
    public TrdPlanNetDurationDto findEffectiveData(@RequestBody Map<String, Object> map) {
        return trdPlanNetDurationService.findEffectiveData(map);
    }
    /**
     * 现金分红流程中
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findCashDividend")
    public TrdPlanNetDurationDto findCashDividend(@RequestBody Map<String, Object> map) {
        return trdPlanNetDurationService.findCashDividend(map);
    }
    /**
     * 统计是否存在后续交易
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("countFollowDeal")
    public int countFollowDeal(@RequestBody Map<String, Object> map) {
        return trdPlanNetDurationService.countFollowDeal(map);
    }
    /**
     * 查询利息明细信息
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findAssetIntInfo")
    public List<FinAssetIntInfo> findAssetIntInfo(@RequestBody Map<String, Object> params) {
        return trdPlanNetDurationService.findAssetIntInfo(params);
    }
}
