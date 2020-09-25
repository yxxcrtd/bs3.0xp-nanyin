/*
 * 文件名：TrdFundDurationController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年11月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.period.trdfundobversion.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.period.trdfundobversion.dto.TrdFundObversionDto;
import com.joyintech.service.period.trdfundobversion.service.TrdFundObversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.period.trdfundduration.dto.TrdFundDurationDto;
import com.joyintech.service.period.trdfundduration.service.TrdFundDurationService;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.dto.common.TrdCommonDeleteDto;
import com.joyintech.tams.invest.common.dto.period.trdfundduration.TrdFundDurationCommonDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangxuelei
 * @version 1.0 2019年10月19日
 * @see TrdFundObversionController
 * @since 1.0
 */
@RestController("TrdFundObversionCtr_dtx")
@RequestMapping("/TrdFundObversion")
public class TrdFundObversionController {
	/**
	 * 
	 */
	@Autowired
	private TrdFundObversionService trdFundDurationService;
    /**
     * 查询有效、撤销或待处理的存续期交易数据
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<TrdFundDurationDto> findPage(@RequestBody Map<String, Object> param,
                                             @PathVariable("page") int page,
                                             @PathVariable("length") int length) {
        return trdFundDurationService.findPage(page, length, param);
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
    	return trdFundDurationService.finprodMarketIdLink(map);
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
        return trdFundDurationService.doDelete(trdCommonDeleteDto);
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
    public String doComplete(@RequestBody TrdFundObversionDto entity) {
        return trdFundDurationService.doComplete(entity);
    }
    /**
     * 插入数据，并删除流程表数据
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findEffectiveData")
    public TrdFundObversionDto findEffectiveData(@RequestBody Map<String, Object> map) {
        return trdFundDurationService.findEffectiveData(map);
    }

    /**
     * 基金折算流程中
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findCashDividend")
    public TrdFundDurationDto findCashDividend(@RequestBody Map<String, Object> map) {
        return trdFundDurationService.findCashDividend(map);
    }

    /**
     * 查询净值
     * @param map 参数
     * @return 查询结果集
     */
    @PostMapping("getLatestValuation")
    public BigDecimal getLatestValuation(@RequestBody Map<String, Object> map) {
        return trdFundDurationService.getLatestValuation(map);
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
        trdFundDurationService.deleteWflByTradeId(tradeId);
    }
    /**
     * 查询利息明细信息
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findAssetIntInfo")
    public List<FinAssetIntInfo> findAssetIntInfo(@RequestBody Map<String, Object> params) {
        return trdFundDurationService.findAssetIntInfo(params);
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
    public String doRevoke(@RequestBody  TrdFundDurationCommonDto dto) {
        return trdFundDurationService.doRevoke(dto);
    }
    /**
     * 查询有效的存续期交易数据
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findEffectiveDeal")
    public List<BaseTradeProductDealDto> findEffectiveDeal(@RequestBody Map<String, Object> params) {
        return trdFundDurationService.findEffectiveDeal(params);
    }

    /**
     * 根据除权交易编号，查询分红交易
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findBTradeId")
    public String findBTradeId(@RequestBody Map<String, Object> params) {
        return trdFundDurationService.findBTradeId(params);
    }

    @PostMapping("getFinprodMarketId/{page}/{length}")
    public Page<SelectOptionDto> getFinprodMarketId(@RequestBody Map<String, Object> param,
                                                    @PathVariable("page") int page,
                                                    @PathVariable("length") int length)
            throws Exception {
        return trdFundDurationService.getFinprodMarketId(page, length, param);
    }

    @PostMapping("selectObversionByTotleTradeId")
    public List<FinAssetIntInfo> selectObversionByTotleTradeId(@RequestBody Map<String, Object> params) {
        return trdFundDurationService.selectObversionByTotleTradeId(params);
    }
}
