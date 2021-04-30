/*
 * 文件名：TrdInvestPersonInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨智
 * 修改时间：2018年09月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.common.trdinvestpersoninfo.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.common.trdinvestpersoninfo.service.TrdInvestPersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/TrdInvestPersonInfo")
public class TrdInvestPersonInfoController {
    @Autowired
    private TrdInvestPersonInfoService trdInvestPersonInfoService;
    /**
     * 交易对手
     * @param param 过滤参数
     * @return 下拉框列表
     * @throws Exception
     */
    @RequestMapping("getCounterId/{page}/{length}")
    public Page<SelectOptionDto> getCounterId(@RequestBody Map<String, Object> param,
                                              @PathVariable("page") int page, @PathVariable("length") int length){
        return trdInvestPersonInfoService.getCounterId(page, length,param);
    }
    
    /**
     * 交易对手(市场机构)
     * @param param 过滤参数
     * @return 下拉框列表
     * @throws Exception
     */
    @RequestMapping("getCounterIdDepat/{page}/{length}")
    public Page<SelectOptionDto> getCounterIdDepat(@RequestBody Map<String, Object> param,
                                                   @PathVariable("page") int page, @PathVariable("length") int length){
        return trdInvestPersonInfoService.getCounterIdDepat(page, length,param);
    }

    /**
     * 查询用户表本方交易员
     * @param param
     * @param page
     * @param length
     * @return
     */
    @RequestMapping("getTrader/{page}/{length}")
    public Page<SelectOptionDto> getTrader(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length){
    	return trdInvestPersonInfoService.getTrader(page, length, param);
    }
    
    /**
     * 查询交易员表本方交易员
     * @param param
     * @param page
     * @param length
     * @return
     */
    @RequestMapping("getCfetsTrader/{page}/{length}")
    public Page<SelectOptionDto> getCfetsTrader(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length){
    	return trdInvestPersonInfoService.getCfetsTrader(page, length, param);
    }
}
