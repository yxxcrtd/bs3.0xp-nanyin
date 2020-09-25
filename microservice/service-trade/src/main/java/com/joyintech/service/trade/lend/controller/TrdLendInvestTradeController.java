/*
 * 文件名：TrdLendInvestTradeController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨智
 * 修改时间：2018年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.trade.lend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.trade.lend.dto.TrdLendInvestTradeDto;
import com.joyintech.service.trade.lend.service.TrdLendInvestTradeService;

/**
 * 拆借交易微服务
 */
@RestController
@RequestMapping("/TrdLendInvestTrade")
public class TrdLendInvestTradeController {

    @Autowired
    private TrdLendInvestTradeService trdLendInvestTradeService;

    /**
     * 分页查询
     *
     * @return Page<TrdBondInvestTradeDto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage/{page}/{length}")
    public Page<TrdLendInvestTradeDto> queryForPage(@RequestBody Map<String, Object> param,
                                                    @PathVariable("page") int page,
                                                    @PathVariable("length") int length)
        throws Exception {
        return trdLendInvestTradeService.selectForPage(page, length, param);
    }

    @RequestMapping("getTrdLendInvestTradeDto/{tradeId}/{BRANCH_0}")
    public TrdLendInvestTradeDto getTrdBondInvestTradeDto(@PathVariable("tradeId") String tradeId,
                                                          @PathVariable("BRANCH_0") String BRANCH_0)
        throws Exception {
        return trdLendInvestTradeService.getTrdBondInvestTradeDto(tradeId, BRANCH_0);
    }

    /**
     * 根据工作流代码查询交易编号
     *
     * @param workflowId 工作流代码
     * @return 交易编号
     * @see
     * @since 1.0
     */
    @RequestMapping("selectTradeIdByWorkflowId/{workflowId}")
    public String selectTradeIdByWorkflowId(@PathVariable("workflowId") String workflowId) {
        return trdLendInvestTradeService.selectTradeIdByWorkflowId(workflowId);
    }

    /**
     * 根据交易编号查询流程中数据是否存在
     *
     * @param tradeId 交易编号
     * @return 流程中数据是否存在
     * @see
     * @since 1.0
     */
    @RequestMapping("isInFlowDataExisted/{tradeId}")
    public Boolean isInFlowDataExisted(@PathVariable("tradeId") String tradeId) {
        return trdLendInvestTradeService.isInFlowDataExisted(tradeId);
    }

    // 存在存续期交易存在时不能撤销check增加 add by renping 20191017 start 
    /**
     * 根据交易编号查询存续期数据条数
     *
     * @param tradeId 交易编号
     * @return 存续期数据是否存在
     * @see
     * @since 1.0
     */
    @RequestMapping("selectDurationDataCount/{tradeId}")
    public Boolean selectDurationDataCount(@PathVariable("tradeId") String tradeId) {
        return trdLendInvestTradeService.selectDurationDataCount(tradeId);
    }
    // 存在存续期交易存在时不能撤销check增加 add by renping 20191017 end 

    /**
     * 根据交易编号查询该交易是否已被撤销
     *
     * @param tradeId 交易编号
     * @return 该交易是否已被撤销
     * @see
     * @since 1.0
     */
    @RequestMapping("isAlreadyRevoked/{tradeId}")
    public Boolean isAlreadyRevoked(@PathVariable("tradeId") String tradeId) {
        return trdLendInvestTradeService.isAlreadyRevoked(tradeId);
    }

    /**
     * 根据交易编号查询该交易是否已做流出资金核对
     *
     * @param tradeId 交易编号
     * @return 该交易是否已做流出资金核对
     * @see
     * @since 1.0
     */
    @RequestMapping("isAlreadyLqdPipedTrade/{tradeId}")
    public Boolean isAlreadyLqdPipedTrade(@PathVariable("tradeId") String tradeId) {
        return trdLendInvestTradeService.isAlreadyLqdPipedTrade(tradeId);
    }
}
