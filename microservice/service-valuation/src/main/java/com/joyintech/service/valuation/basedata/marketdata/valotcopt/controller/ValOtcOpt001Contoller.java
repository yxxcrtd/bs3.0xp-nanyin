/*
 * 文件名：ValOtcOpt001Contoller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：tanxuan
 * 修改时间：2020年02月26日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.basedata.marketdata.valotcopt.controller;

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
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.basedata.marketdata.valotcopt.dto.ValOtcOpt001Dto;
import com.joyintech.service.valuation.basedata.marketdata.valotcopt.service.ValOtcOpt001Service;

/**
 * 场外期权交易
 * @author tanxuan
 * @version 1.0 2020年02月26日
 * @see ValOtcOpt001Contoller
 * @since 1.0
 */
@RestController(value="serviceValOtcOpt001Contoller")
@RequestMapping("/ValOtcOpt001")
public class ValOtcOpt001Contoller extends BaseController {
    
    /**
     * ValOtcOpt001Service
     */
    @Autowired
    private ValOtcOpt001Service valOtcOpt001Service;
    
    /**
     * 
     * 分页数据查询
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<ValOtcOpt001Dto> findPage(@RequestBody Map<String, Object> params,
    		@PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        return valOtcOpt001Service.findPage(page,length,params);
    }
    
    /**
     * 
     * 数据查询
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/findAll")
    public List<ValOtcOpt001Dto> findAll(@RequestBody Map<String, Object> params) throws Exception{
        return valOtcOpt001Service.findAll(params);
    }
    
    /**
     * 账套下拉框数据选择
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @PostMapping("/getBooksetId/{page}/{length}")
    public Page<SelectOptionDto> getBooksetId(@RequestBody Map<String,Object> map,
    		@PathVariable("page") int page, @PathVariable("length") int length) {
        return valOtcOpt001Service.getBooksetId(page,length,map);
    }
    
    /**
     * 交易编号下拉框数据选择
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @PostMapping("/getTotleTradeId")
    public List<SuggestDto> getTotleTradeId(@RequestBody Map<String,Object> map) {
        return valOtcOpt001Service.getTotleTradeId(map);
    }
    
    /**
     * 合约标的下拉框数据选择
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @PostMapping("/getBenchmarkId")
    public List<SuggestDto> getBenchmarkId(@RequestBody Map<String,Object> map) {
        return valOtcOpt001Service.getBenchmarkId(map);
    }

}
