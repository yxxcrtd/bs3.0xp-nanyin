package com.joyintech.tams.exchange.service.trade.controller;

import com.joyintech.tams.exchange.common.bean.DynamicMonitorDTO;
import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.microservice.TradeServiceURL;
import com.joyintech.tams.exchange.service.trade.management.service.SystemMonitorManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName SystemMonitorController
 * @Description TODO
 * @Author swwan
 * @Date 2020/8/24 17:35
 * @Version 1.0
 **/


@RestController
@RequestMapping("/service/joyin")
public class SystemMonitorController {

    @Autowired
    private SystemMonitorManagement systemMonitorManagement;

    @RequestMapping(value = "/monitor", method = RequestMethod.GET)
    public ResultBean queryExchangeMonitor() {
        return systemMonitorManagement.queryExchangeMonitor();
    }


    @RequestMapping(value = "/cache/monitor", method = RequestMethod.GET)
    public ResultBean queryCacheMonitor() {
        return systemMonitorManagement.queryCacheMonitor();
    }

    @PostMapping(TradeServiceURL.querySystemMonitorDynamic)
    public ResultBean queryExchangeMonitorDynamic(@RequestBody DynamicMonitorDTO dto){
        return systemMonitorManagement.queryExchangeMonitorDynamic(dto);
    }
}
