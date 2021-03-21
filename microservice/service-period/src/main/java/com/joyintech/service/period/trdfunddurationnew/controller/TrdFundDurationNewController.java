/*
 * 文件名：TrdFundDurationNewController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年11月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.period.trdfunddurationnew.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.period.trdfunddurationnew.dto.TrdFundDurationNewDto;
import com.joyintech.service.period.trdfunddurationnew.service.TrdFundDurationNewService;
import com.joyintech.tams.invest.common.dto.period.trdfundduration.TrdFundDurationCommonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.dto.common.TrdCommonDeleteDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author Administrator
 * @version 1.0 2018年11月19日
 * @see TrdFundDurationNewController
 * @since 1.0
 */
@RestController("TrdFundDurationNewCtr_dtx")
@RequestMapping("/TrdFundDurationNew")
public class TrdFundDurationNewController {
	/**
	 * 
	 */
	@Autowired
	private TrdFundDurationNewService trdFundDurationNewService;
    /**
     * 查询有效、撤销或待处理的存续期交易数据
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<TrdFundDurationNewDto> findPage(@RequestBody Map<String, Object> param,
                                                @PathVariable("page") int page,
                                                @PathVariable("length") int length) {
        return trdFundDurationNewService.findPage(page, length, param);
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
    	return trdFundDurationNewService.finprodMarketIdLink(map);
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
    	return trdFundDurationNewService.portfolioIdLink(map);
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
        return trdFundDurationNewService.calRegistShare(map);
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
        return trdFundDurationNewService.doDelete(trdCommonDeleteDto);
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
    public String doComplete(@RequestBody TrdFundDurationNewDto entity) {
        return trdFundDurationNewService.doComplete(entity);
    }
    /**
     * 插入数据，并删除流程表数据
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findEffectiveData")
    public TrdFundDurationNewDto findEffectiveData(@RequestBody Map<String, Object> map) {
        return trdFundDurationNewService.findEffectiveData(map);
    }

    /**
     * 现金分红流程中
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findCashDividend")
    public TrdFundDurationNewDto findCashDividend(@RequestBody Map<String, Object> map) {
        return trdFundDurationNewService.findCashDividend(map);
    }

    /**
     * 统计是否存在后续交易
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("countFollowDeal")
    public int countFollowDeal(@RequestBody Map<String, Object> map) {
        return trdFundDurationNewService.countFollowDeal(map);
    }
    /**
     * 查询净值
     * @param map 参数
     * @return 查询结果集
     */
    @PostMapping("getLatestValuation")
    public BigDecimal getLatestValuation(@RequestBody Map<String, Object> map) {
        return trdFundDurationNewService.getLatestValuation(map);
    }
    /**
     * 根据交易删除数据
     * @param tradeId 参数
     * @return 结果
     */
    @Transactional
    @DTXTransaction
    @PostMapping("deleteWflByTradeId")
    public void deleteWflByTradeId(@RequestBody String tradeId) {
        trdFundDurationNewService.deleteWflByTradeId(tradeId);
    }
    /**
     * 查询利息明细信息
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findAssetIntInfo")
    public List<FinAssetIntInfo> findAssetIntInfo(@RequestBody Map<String, Object> params) {
        return trdFundDurationNewService.findAssetIntInfo(params);
    }
    /**
     * 撤销
     *
     * @return 结果
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doRevoke")
    public String doRevoke(@RequestBody TrdFundDurationCommonDto dto) {
        return trdFundDurationNewService.doRevoke(dto);
    }
    /**
     * 查询有效的存续期交易数据
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findEffectiveDeal")
    public List<BaseTradeProductDealDto> findEffectiveDeal(@RequestBody Map<String, Object> params) {
        return trdFundDurationNewService.findEffectiveDeal(params);
    }

    /**
     * 根据除权交易编号，查询分红交易
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findBTradeId")
    public String findBTradeId(@RequestBody Map<String, Object> params) {
        return trdFundDurationNewService.findBTradeId(params);
    }

    /**
     * 获取资产代码
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     */
    @PostMapping("getFinprodMarketId/{page}/{length}")
    public Page<SelectOptionDto> getFinprodMarketId(@RequestBody Map<String, Object> param,
                                                    @PathVariable("page") int page,
                                                    @PathVariable("length") int length)
            throws Exception {
        return trdFundDurationNewService.getFinprodMarketId(page, length, param);
    }

    /**
     * 统计是否有流程中的分红交易
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("countFollowFHDeal")
    public int countFollowFHDeal(@RequestBody Map<String, Object> map) {
        return trdFundDurationNewService.countFollowFHDeal(map);
    }
}
