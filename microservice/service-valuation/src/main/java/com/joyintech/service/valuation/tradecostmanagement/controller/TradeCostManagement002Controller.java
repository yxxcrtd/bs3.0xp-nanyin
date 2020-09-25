package com.joyintech.service.valuation.tradecostmanagement.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseController;
import com.joyintech.service.valuation.accounadjustmanage.assetbook.service.ValAssetBook001Service;
import com.joyintech.service.valuation.tradecostmanagement.dto.TradeCostManagement002Dto;
import com.joyintech.service.valuation.tradecostmanagement.service.TradeCostManagement002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 交易费用管理 新建
 *
 * @Author wangXiaoYong
 * @create 2019/11/19
 */
@RestController("tradeCostManagement002Ctl_dtx")
@RequestMapping("/TradeCostManagement002")
public class TradeCostManagement002Controller extends BaseController {

    @Autowired
    private TradeCostManagement002Service tradeCostManagement002Service;


    @Transactional
    @DTXTransaction
    @RequestMapping("/save")
    public Integer save(@RequestBody TradeCostManagement002Dto dto) {

        tradeCostManagement002Service.save(dto);
        return 1;
    }


}
