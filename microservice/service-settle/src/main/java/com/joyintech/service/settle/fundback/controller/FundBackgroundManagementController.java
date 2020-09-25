/*
 * 文件名：BussSettleOrderCommController.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：杨智 修改时间：2018年09月20日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.settle.fundback.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.settle.fundback.service.FundBackgroundManagementService;
import com.joyintech.tams.invest.common.dto.settle.fundback.FundBackPrintDataBaseDto;
import com.joyintech.tams.invest.common.dto.settle.fundback.FundBackgroundManagementDto;

@RestController
@RequestMapping("/FundBackgroundManagement")
public class FundBackgroundManagementController {

    @Autowired
    private FundBackgroundManagementService fundBackgroundManagementService;

    @PostMapping("findPortfolioId/{page}/{length}")
    public Page<SelectOptionDto> getPortfolioId(@RequestBody Map<String, Object> params, @PathVariable("page") int page,
                                                @PathVariable("length") int length) {
        return fundBackgroundManagementService.findPortfolioId(page, length, params);
    }

    @PostMapping("findBizCode/{page}/{length}")
    public Page<SelectOptionDto> findBizCode(@RequestBody Map<String, Object> params, @PathVariable("page") int page,
                                                @PathVariable("length") int length) {
        return fundBackgroundManagementService.findBizCode(page, length, params);
    }

    @PostMapping("findPage/{page}/{length}")
    public Page<FundBackgroundManagementDto> findPage(@RequestBody Map<String, Object> params, @PathVariable("page") int page,
                                                      @PathVariable("length") int length) {
        return fundBackgroundManagementService.findPage(page, length, params);
    }

    @PostMapping("findPrintData")
    public FundBackPrintDataBaseDto findPrintData(@RequestBody Map<String, Object> params) {
        return fundBackgroundManagementService.findPrintData(params);
    }

    @PostMapping("findWorkflowInfo")
    public Map<String, Object> findWorkflowInfo(@RequestBody Map<String, Object> params) {
        return fundBackgroundManagementService.findWorkflowInfo(params);
    }

}
