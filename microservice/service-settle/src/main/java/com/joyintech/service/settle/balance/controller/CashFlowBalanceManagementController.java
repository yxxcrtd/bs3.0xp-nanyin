/*
 * 文件名：BussSettleOrderCommController.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：杨智 修改时间：2018年09月20日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.settle.balance.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SuggestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.settle.balance.service.CashFlowBalanceManagementService;
import com.joyintech.tams.invest.common.dto.settle.balance.BaseCashFlowBalanceManagementDto;

@RestController
@RequestMapping("/CashFlowBalanceManagement")
public class CashFlowBalanceManagementController {

    @Autowired
    private CashFlowBalanceManagementService cashFlowBalanceManagementService;

    @GetMapping("findCounterAcctNoForSugg")
    public List<SuggestDto> findCounterAcctNo(String search) {
        return cashFlowBalanceManagementService.findCounterAcctNoForSugg(search);
    }
     
    @GetMapping("findAcctNoByTg")
    public List<SuggestDto> findAcctNoByTg(String search) {
        return cashFlowBalanceManagementService.findAcctNoByTg(search);
    }
    
    @PostMapping("findCounterAcctNo/{page}/{length}")
    public Page<SelectOptionDto> findCounterAcctNo(@RequestBody Map<String, Object> params, @PathVariable("page") int page,
                                                   @PathVariable("length") int length) {
        return cashFlowBalanceManagementService.findCounterAcctNo(page, length, params);
    }

    @PostMapping("findPage/{page}/{length}")
    public Page<BaseCashFlowBalanceManagementDto> findPage(@RequestBody Map<String, Object> params, @PathVariable("page") int page,
                                                           @PathVariable("length") int length) {
        return cashFlowBalanceManagementService.findPage(page, length, params);
    }
}
