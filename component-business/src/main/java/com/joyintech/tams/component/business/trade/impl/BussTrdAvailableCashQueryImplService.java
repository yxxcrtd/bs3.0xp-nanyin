/*
 * 文件名：BussTrdAvailableCashQueryService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨智
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.component.business.trade.impl;

import com.joyintech.framework.JoyinRestTemplate;
import com.joyintech.tams.component.business.trade.BussTrdAvailableCashQueryService;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class BussTrdAvailableCashQueryImplService implements BussTrdAvailableCashQueryService {
    @Autowired
    private JoyinRestTemplate restTemplate;
    @Override
    public Map getAvailableCash(Map<String, Object> parameters) {
        return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdAvailableCashQuery/getAvailableCash",
                parameters, Map.class);
    }

    @Override
    public BigDecimal getSecManageAvailableCash(Map<String, Object> parameters) {
        return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdAvailableCashQuery/getSecManageAvailableCash",
                parameters, BigDecimal.class);
    }


    @Override
    public Map<String, Object> getShowHandNum(Map<String, Object> pageParam) {
        return restTemplate.postForObject(
                ServiceApplication.TRADE_SERVICE_NAME + "/TrdAvailableCashQuery/getShowHandNum",
                pageParam, Map.class);
    }
    @Override
    public Map<String, Object>  getFinAvaPosition(Map<String, Object> map){
        return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdAvailableCashQuery/getFinAvaPosition",
                map, Map.class);
    }
    @Override
    public BigDecimal getSecPosition(Map<String, Object> map) {
        return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdAvailableCashQuery/getSecPosition",
                map, BigDecimal.class);
    }
}
