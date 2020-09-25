/*
 * 文件名：PtlActivityPeriod001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年9月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.portfolio.common.ptlposition.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.tams.invest.common.component.base.group.BasePtlCashPositionInfoService;
import com.joyintech.tams.invest.common.component.base.group.BasePtlSecPositionInfoService;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.PtlPositionHoldDto;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;

/**
 * 活期结息
 * 
 * @author fanshuanghu
 * @version 1.0 2018年9月17日
 * @see PtlPositionController
 * @since 1.0
 */
@RestController("ptlPositionController_dtx")
@RequestMapping("/common/ptlposition")
public class PtlPositionController {

    /**
     * 组合可用持仓和实际持仓查询更新组件接口
     */
    @Autowired
    private BasePtlSecPositionInfoService basePtlSecPositionInfoServiceImpl;

    /**
     * 组合可用现金和实际现金查询更新组件
     */
    @Autowired
    private BasePtlCashPositionInfoService basePtlCashPositionInfoServiceImpl;

    /**
     * 调用组合可用持仓和实际持仓查询更新组件
     * 
     * @return 结果
     */
    @PostMapping("insertPosition")
    @Transactional
    @DTXTransaction
    public boolean insertPosition(@RequestBody PtlPositionHoldDto ptlPositionHoldDto) {
        if (ptlPositionHoldDto != null) {
            if (ptlPositionHoldDto.getPtlSecPositionFlows() != null
                && !ptlPositionHoldDto.getPtlSecPositionFlows().isEmpty()) {
                for (PtlSecPositionFlowDto ptlSecPositionFlow : ptlPositionHoldDto.getPtlSecPositionFlows()) {
                    basePtlSecPositionInfoServiceImpl.inertSecPositionFlow(ptlSecPositionFlow);
                }
            }
            if (ptlPositionHoldDto.getPtlCashPositionFlows() != null
                && !ptlPositionHoldDto.getPtlCashPositionFlows().isEmpty()) {
                for (PtlCashPositionFlowDto ptlCashPositionFlow : ptlPositionHoldDto.getPtlCashPositionFlows()) {
                    basePtlCashPositionInfoServiceImpl.insertCashPositionFlow(ptlCashPositionFlow);
                }
            }
        }
        return true;
    }
}
