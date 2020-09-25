/*
 * 文件名：FlowForecastController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chendijun
 * 修改时间：2019年8月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.forecast.controller;

import java.util.Map;

import com.joyintech.framework.annotation.DTXTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.product.forecast.dto.FinFlowForecastConfig;
import com.joyintech.service.product.forecast.dto.FinFlowForecastDealAmontDto;
import com.joyintech.service.product.forecast.service.FinFlowForecastService;

/**
 * 微服务---流动性预测维护 
 * 
 * @author chendijun
 * @version 1.0 2019年8月3日
 * @see FinFlowForecastController
 * @since 1.0
 */
@RestController(value = "serviceFlowForecastController_dtx")
@RequestMapping("/FlowForecast")
public class FinFlowForecastController {

    @Autowired
    private FinFlowForecastService flowForecastService;

    @PostMapping("/findForecast/{page}/{length}")
    public Page<FinFlowForecastConfig> findForecast(@RequestBody Map<String, Object> param,
                                                 @PathVariable("page") int page,
                                                 @PathVariable("length") int length)
        throws Exception {
        return flowForecastService.findForecast(param, page, length);
    }

    @PostMapping("/findReserveDetail/{page}/{length}")
    public Page<FinFlowForecastConfig> findReserveDetail(@RequestBody Map<String, Object> param,
                                                      @PathVariable("page") int page,
                                                      @PathVariable("length") int length)
        throws Exception {
        return flowForecastService.findReserveDetail(param, page, length);
    }

    @PostMapping("/findNetValueOpenforecast/{page}/{length}")
    public Page<FinFlowForecastDealAmontDto> findNetValueOpenforecast(@RequestBody Map<String, Object> param,
                                                             @PathVariable("page") int page,
                                                             @PathVariable("length") int length)
        throws Exception {
        return flowForecastService.findNetValueOpenforecast(param, page, length);
    }

    @Transactional
    @DTXTransaction
    @PostMapping("/save")
    public String flowForecastService(@RequestBody FinFlowForecastConfig param) {
        return flowForecastService.save(param);
    }
    
    @PostMapping("/findMaintainProductRedemptionData/{page}/{length}")
    public Page<FinFlowForecastDealAmontDto> findMaintainProductRedemptionData(@RequestBody Map<String, Object> param,
                                                                            @PathVariable("page") int page,
                                                                            @PathVariable("length") int length)
                                                                                throws Exception {
        return flowForecastService.findMaintainProductRedemptionData(param, page, length);
    }
}
