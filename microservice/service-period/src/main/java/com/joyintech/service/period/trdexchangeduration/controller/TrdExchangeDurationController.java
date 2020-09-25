package com.joyintech.service.period.trdexchangeduration.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.service.period.trdexchangeduration.service.TrdExchangeDurationService;
import com.joyintech.tams.invest.common.dto.period.trdexchangeduration.TrdExchangeDurDbInertDto;

/**
 * 外汇远期掉期到期时待处理数据用微服务
 *
 * @author renping
 * @version 1.0 2019年7月19日
 * @version V1.0
 * @Project: microservice
 */
@RestController("trdExchangeDurationCtl_dtx")
@RequestMapping("/TrdExchangeDuration")
public class TrdExchangeDurationController {

    @Autowired
    private TrdExchangeDurationService trdExchangeDurationService;

    /**
     * 查询待处理的外汇远期掉期交易数据（所有数据）
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findAllData")
    public List<TrdExchangeDurDbInertDto> findAllData(@RequestBody Map<String, Object> param) {
        return trdExchangeDurationService.findAllData(param);
    }

    /**
     * 根据买入交易信息生成到期交易
     *
     * @param param 买入交易信息
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("doCreateEndDeal")
    public TrdExchangeDurDbInertDto doCreateEndDeal(@RequestBody TrdExchangeDurDbInertDto param) {
        List<TrdExchangeDurDbInertDto> list = new ArrayList<TrdExchangeDurDbInertDto>();
        list.add(param);
        list = trdExchangeDurationService.doCreateEndDeal(list);
        return list.get(0);
    }
}
