/*
 * 文件名：TaxBalanceQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：leipeng
 * 修改时间：2018年10月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.trademanage.taxbalancequery.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.trademanage.taxbalancequery.dto.TaxBalanceQuery001Dto;
import com.joyintech.service.valuation.trademanage.taxbalancequery.service.TaxBalanceQuery001Service;

/**
 * 简易计税余额查询
 * 
 * @author leipeng
 * @version 1.0 2018年10月9日
 * @see TaxBalanceQuery001Controller
 * @since 1.0
 */
@RestController(value="serviceTaxBalanceQuery001Controller")
@RequestMapping("/TaxBalanceQuery001")
public class TaxBalanceQuery001Controller extends BaseController {

    @Autowired
    private TaxBalanceQuery001Service taxBalanceQuery001Service;

    @PostMapping("/findPage/{page}/{length}")
    public Page<TaxBalanceQuery001Dto> findPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                @PathVariable("length") int length)
        throws Exception {
        return taxBalanceQuery001Service.findByPage(page, length, param);
    }

    @PostMapping("/findAll")
    public List<TaxBalanceQuery001Dto> findAll(@RequestBody Map<String, Object> param)
        throws Exception {
        return taxBalanceQuery001Service.findByList(param);
    }

    /**
     * 资产下拉框数据选择
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @PostMapping("/getAssetId/{page}/{length}")
    public Page<SelectOptionDto> getAssetId(@RequestBody Map<String, Object> pageParam,
    		@PathVariable("page") int page, @PathVariable("length") int length) {
        return taxBalanceQuery001Service.getAssetId(page,length,pageParam);
    }
}
