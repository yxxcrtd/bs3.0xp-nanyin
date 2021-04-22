package com.joyintech.tams.exchange.service.trade.controller;

import com.joyintech.tams.exchange.service.trade.management.service.SettleManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SettlementController
 * @Description 场内交易清算接口
 * @Author swwan
 * @Date 2020/9/1 14:10
 * @Version 1.0
 **/
@RestController
@RequestMapping("/service/joyin")
public class SettleController {
    @Autowired
    private SettleManagement settleManagement;

    @RequestMapping(value = "/settle", method = RequestMethod.POST)
    public String settleHistory() {
        return settleManagement.settleHistory();
    }
}
