package com.joyintech.tams.exchange.service.trade.controller;

import com.joyintech.tams.exchange.common.bean.DynamicMonitorDTO;
import com.joyintech.tams.exchange.common.bean.ExchangeMonitorBean;
import com.joyintech.tams.exchange.common.bean.MonitorIndicator;
import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.domain.Message;
import com.joyintech.tams.exchange.common.microservice.TradeServiceURL;
import com.joyintech.tams.exchange.service.trade.management.service.SystemMonitorManagement;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trade/joyin")
//@Api(tags = "场内交易-内存数据监控接口")
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

//    @ApiOperation(value = "查询监控数据", notes = "查询监控数据")
//    @RequestMapping(value = "/monitor", method = RequestMethod.POST)
//    public Message<ExchangeMonitorBean> queryExchangeMonitor() {
//        return new Message<ExchangeMonitorBean>(systemMonitorManagement.queryExchangeMonitor());
//    }
//
//    @ApiOperation(value = "查询内存数据", notes = "查询内存数据")
//    @RequestMapping(value = "/cache/monitor", method = RequestMethod.POST)
//    public Message<Object> queryCacheMonitor() {
//        return new Message<Object>(systemMonitorManagement.queryCacheMonitor());
//    }
//
//    @ApiOperation(value = "查询动态监控数据", notes = "查询动态监控数据")
//    @PostMapping("/monitor/dynamic")
//    public Message<MonitorIndicator> queryExchangeMonitorDynamic(@RequestBody DynamicMonitorDTO dto) {
//        return new Message<MonitorIndicator>(systemMonitorManagement.queryExchangeMonitorDynamic(dto));
//    }
}
