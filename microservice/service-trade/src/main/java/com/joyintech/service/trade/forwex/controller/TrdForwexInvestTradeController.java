/*
 * 文件名：TrdForwexInvestTradeController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：admin
 * 修改时间：2019年7月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.trade.forwex.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.trade.forwex.dto.TrdForwexInvestTradeDto;
import com.joyintech.service.trade.forwex.service.TrdForwexInvestTradeService;

/**
 * 外汇远期投资交易
 * 〈功能详细描述〉
 * @author qiaominghui
 * @version 1.0 2019年7月4日
 * @see TrdForwexInvestTradeController
 * @since 1.0
 */

@RestController
@RequestMapping("/TrdForwexInvestTrade")
public class TrdForwexInvestTradeController {

	@Autowired
	private TrdForwexInvestTradeService trdForwexInvestTradeService;
	 /**
     * 
     * 	组合可用现金查询
     * <功能详细描述>
     * @param dto PtlCashPositionFlowDto
     * @return BigDecimal
     * @see
     * @since 1.0
     */
    @PostMapping("getProAvailableCash")
    public Map<String, Object> getProAvailableCash(@RequestBody Map<String, Object> param) {
        return trdForwexInvestTradeService.getProAvailableCash(param);
    }
    
    /**
     * 分页查询
     *
     * @return Page<TrdBondInvestTradeDto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage/{page}/{length}")
    public Page<TrdForwexInvestTradeDto> queryForPage(@RequestBody Map<String, Object> param,
                                                        @PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
    	Page<TrdForwexInvestTradeDto> returnPage= trdForwexInvestTradeService.selectForPage(page, length, param);
    	return returnPage;
    }
    
    /**
     * 查询外汇远期投资交易信息
     *
     * @param tradeId 交易编号
     * @return 外汇远期投资交易信息
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("getTrdForwexInvestTradeDto/{tradeId}")
    public TrdForwexInvestTradeDto getTrdForwexInvestTradeDto(@PathVariable("tradeId") String tradeId)
            throws Exception {
        return trdForwexInvestTradeService.getTrdForwexInvestTradeDto(tradeId);
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
    public TrdForwexInvestTradeDto getDealInfoByEntrustId(String entrustId) {
        return trdForwexInvestTradeService.getDealInfoByEntrustId(entrustId);
    }
    
}
