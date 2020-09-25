/*
 * 文件名：BussSettleOrderCommController.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：杨智 修改时间：2018年09月20日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.settle.balance.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.settle.balance.service.OutflowFundsCheckManagementService;
import com.joyintech.tams.invest.common.dto.settle.balance.BaseOutflowFundsSystemFlowDto;
import com.joyintech.tams.invest.common.dto.settle.balance.OutflowManualMatchDto;
import com.joyintech.tams.invest.common.dto.settle.balance.PortfolioUnmatchedInfo;

@RestController
@RequestMapping("/OutflowFundsCheckManagement")
public class OutflowFundsCheckManagementController {

    @Autowired
    private OutflowFundsCheckManagementService outflowFundsCheckManagementService;

    @PostMapping("findEscrowAcctNo/{page}/{length}")
    public Page<SelectOptionDto> findEscrowAcctNo(@RequestBody Map<String, Object> params, @PathVariable("page") int page,
                                                  @PathVariable("length") int length) {
        return outflowFundsCheckManagementService.findEscrowAcctNo(page, length, params);
    }

    /**
     * 画面查询方法-组合代码
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("getPortfolioId/{page}/{length}")
    public Page<SelectOptionDto> getPortfolioId(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                @PathVariable("length") int length)
        throws Exception {
        return outflowFundsCheckManagementService.getPortfolioId(page, length, param);
    }

    /**
     * 画面查询方法-系统流出
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findSysPage/{page}/{length}")
    public Page<BaseOutflowFundsSystemFlowDto> findSysPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                           @PathVariable("length") int length) {
        return outflowFundsCheckManagementService.findSysPage(page, length, param);
    }

    /**
     * 画面查询方法-托管流出
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findEnPage/{page}/{length}")
    public Page<BaseOutflowFundsSystemFlowDto> findEnPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                           @PathVariable("length") int length) {
        return outflowFundsCheckManagementService.findEnPage(page, length, param);
    }

    @PostMapping("findPortfolioUnmatched")
    public List<PortfolioUnmatchedInfo> findPortfolioUnmatched(@RequestBody Map<String, Object> params) {
        return outflowFundsCheckManagementService.findPortfolioUnmatched(params);
    }

    @PostMapping("selectSysFlowOnWorkflow")
    public long selectSysFlowOnWorkflow(@RequestBody Map<String, Object> params) {
        return outflowFundsCheckManagementService.selectSysFlowOnWorkflow(params);
    }

    @PostMapping("selectSysFlowCountByTradeId")
    public Long selectSysFlowCountByTradeId(@RequestBody List<String> tradeIdList) {
        return outflowFundsCheckManagementService.selectSysFlowCountByTradeId(tradeIdList);
    }

    @PostMapping("selectEnFlowCountByTradeId")
    public Long selectEnFlowCountByTradeId(@RequestBody List<String> tradeIdList) {
        return outflowFundsCheckManagementService.selectEnFlowCountByTradeId(tradeIdList);
    }

    @PostMapping("selectMatchDtoForCancel")
    public OutflowManualMatchDto selectMatchDtoForCancel(@RequestBody Map<String, Object> params) {
        return outflowFundsCheckManagementService.selectMatchDtoForCancel(params);
    }

    @PostMapping("cancelMatch")
    public String cancelMatch(@RequestBody String groupId) {
        return outflowFundsCheckManagementService.cancelMatch(groupId);
    }

    @PostMapping("remarkSet")
    public String remarkSet(@RequestBody Map<String, Object> params) {
        return outflowFundsCheckManagementService.remarkSet(params);
    }
}
