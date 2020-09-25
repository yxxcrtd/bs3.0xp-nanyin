package com.joyintech.service.valuation.tradecostmanagement.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseController;
import com.joyintech.service.valuation.tradecostmanagement.dto.TradeCostManagement002Dto;
import com.joyintech.service.valuation.tradecostmanagement.service.TradeCostManagement002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 交易费用管理 修改
 *
 * @Author wangXiaoYong
 * @create 2019/11/19
 */
@RestController("tradeCostManagement003Ctl_dtx")
@RequestMapping("/TradeCostManagement003")
public class TradeCostManagement003Controller extends BaseController {

    @Autowired
    private TradeCostManagement002Service tradeCostManagement002Service;



    @Transactional
    @DTXTransaction
    @RequestMapping("/update")
    public Integer update(@RequestBody TradeCostManagement002Dto dto) {
        tradeCostManagement002Service.update(dto);
        return 1;
    }


}
