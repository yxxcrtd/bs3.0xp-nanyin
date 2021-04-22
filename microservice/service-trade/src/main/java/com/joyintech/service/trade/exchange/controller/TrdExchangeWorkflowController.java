package com.joyintech.service.trade.exchange.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.service.trade.exchange.dto.TrdExchangeEntrustDto;
import com.joyintech.service.trade.exchange.dto.TrdExchangeIntegratedEntrustDto;
import com.joyintech.service.trade.exchange.service.TrdExchangeWorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * 场内交易工作流
 *
 * @author swwan
 * @className TrdExchangeWorkflowController
 * @date 2021/2/24 10:35
 */

@RestController("TrdExchangeWorkflowCtr_dtx")
@RequestMapping("/TrdExchangeWorkflow")
public class TrdExchangeWorkflowController {

    @Autowired
    private TrdExchangeWorkflowService trdExchangeWorkflowService;

    /**
     *
     * 复核流程时调用方法
     * <功能详细描述>
     * @see
     * @since 1.0
     */
    @Transactional(rollbackFor = {Exception.class})
    @DTXTransaction(propagation = Propagation.REQUIRES_NEW)
    @PostMapping("reviewEntrust")
    public boolean startReviewWorkflow(@RequestBody TrdExchangeEntrustDto dto) {
        return trdExchangeWorkflowService.startReviewWorkflow(dto);
    }

    /**
     *
     * 复核流程时调用方法
     * <功能详细描述>
     * @see
     * @since 1.0
     */
    @Transactional(rollbackFor = {Exception.class})
    @DTXTransaction(propagation = Propagation.REQUIRES_NEW)
    @PostMapping("reviewIntegratedEntrust")
    public boolean startReviewWorkflow(@RequestBody TrdExchangeIntegratedEntrustDto dto) {
        return trdExchangeWorkflowService.startReviewWorkflow(dto);
    }

    /**
     *
     * 撤审流程时调用方法
     * <功能详细描述>
     * @see
     * @since 1.0
     */
    @Transactional(rollbackFor = {Exception.class})
    @DTXTransaction(propagation = Propagation.REQUIRES_NEW)
    @PostMapping("revokeEntrust")
    public boolean startRevokeWorkflow(@RequestBody TrdExchangeEntrustDto dto) {
        return trdExchangeWorkflowService.startRevokeWorkflow(dto);
    }

    /**
     *
     * 撤审流程时调用方法
     * <功能详细描述>
     * @see
     * @since 1.0
     */
    @Transactional(rollbackFor = {Exception.class})
    @DTXTransaction(propagation = Propagation.REQUIRES_NEW)
    @PostMapping("revokeIntegratedEntrust")
    public boolean startRevokeWorkflow(@RequestBody TrdExchangeIntegratedEntrustDto dto) {
        return trdExchangeWorkflowService.startRevokeWorkflow(dto);
    }

    /**
     *
     * 作废流程时调用方法
     * <功能详细描述>
     * @see
     * @since 1.0
     */
    @Transactional(rollbackFor = {Exception.class})
    @DTXTransaction(propagation = Propagation.REQUIRES_NEW)
    @PostMapping("doRevoke")
    public boolean doRevoke(@RequestParam String entrustId ) {
        return trdExchangeWorkflowService.doRevoke(entrustId);
    }

//    /**
//     *
//     * 流程处理结束时调用方法
//     * <功能详细描述>
//     * @param entity
//     * @see
//     * @since 1.0
//     */
//    @Transactional
//    @DTXTransaction
//    @RequestMapping("doComplete")
//    public void doComplete(@RequestBody TrdFundInvestTradeDto entity){
//        trdFundInvestTradeService.doComplete(entity);
//    }
}
