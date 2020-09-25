/*
 * 文件名：TrdIRSTradeController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：admin
 * 修改时间：2019年7月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.trade.irs.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinCashPlanEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.trade.irs.dto.TrdIRSTradeDto;
import com.joyintech.service.trade.irs.service.TrdIRSTradeService;

/**
 * 利率互换交易
 * 〈功能详细描述〉
 * @author qiaominghui
 * @version 1.0 2019年7月16日
 * @see TrdIRSTradeController
 * @since 1.0
 */

@RestController
@RequestMapping("/TrdIRSTrade")
public class TrdIRSTradeController {

	@Autowired
	private TrdIRSTradeService trdIRSTradeService;
    
    /**
     * 分页查询
     *
     * @return Page<TrdBondInvestTradeDto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage/{page}/{length}")
    public Page<TrdIRSTradeDto> queryForPage(@RequestBody Map<String, Object> param,
                                                        @PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
    	Page<TrdIRSTradeDto> returnPage= trdIRSTradeService.selectForPage(page, length, param);
    	return returnPage;
    }
    
    /**
     * 查询利率互换投资交易信息
     *
     * @param tradeId 交易编号
     * @return 利率互换投资交易信息
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("getTrdIRSTradeDto/{tradeId}/{branch}")
    public TrdIRSTradeDto getTrdIRSTradeDto(@PathVariable("tradeId") String tradeId,@PathVariable("branch")Long branch)
            throws Exception {
        return trdIRSTradeService.getTrdIRSTradeDto(tradeId,branch);
    }

    /**
     * 委托代码带出交易必要信息
     * 
     * @param entrustId 交易编号
     * @return 交易信息
     * @see
     * @since 1.0
     */
    @GetMapping("getDealInfoByEntrustId")
    public TrdIRSTradeDto getDealInfoByEntrustId(String entrustId) {
        return trdIRSTradeService.getDealInfoByEntrustId(entrustId);
    }

    /**
     * 根据交易编号获取相应的现金流计划明细
     *
     * @param tradeId 交易编号
     * @return 现金流计划明细list
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("getCashPlanList/{tradeId}")
    public List<FinCashPlanEntity> getCashPlanList(@PathVariable("tradeId") String tradeId)
            throws Exception {
        return trdIRSTradeService.getCashPlanList(tradeId);
    }

    /**
     * 查询利率互换投资交易信息
     *
     * @param tradeId 交易编号
     * @return 利率互换投资交易信息
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @GetMapping("getFinCashInfoList")
    public  List<FinCashInfo> getFinCashInfoList(String tradeId)
            throws Exception {
        return trdIRSTradeService.getFinCashInfoList(tradeId);
    }
}
