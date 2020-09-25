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
import com.joyintech.service.settle.balance.service.InflowFundsCheckManagementService;
import com.joyintech.tams.invest.common.dto.settle.balance.BaseOutflowFundsSystemFlowDto;
import com.joyintech.tams.invest.common.dto.settle.balance.PortfolioUnmatchedInfo;

@RestController
@RequestMapping("/InflowFundsCheckManagement")
public class InflowFundsCheckManagementController {

    @Autowired
    private InflowFundsCheckManagementService inflowFundsCheckManagementService;

    @PostMapping("findPortfolioUnmatched")
    public List<PortfolioUnmatchedInfo> findPortfolioUnmatched(@RequestBody Map<String, Object> params) {
        return inflowFundsCheckManagementService.findPortfolioUnmatched(params);
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
        return inflowFundsCheckManagementService.findSysPage(page, length, param);
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
        return inflowFundsCheckManagementService.findEnPage(page, length, param);
    }

}
