package com.joyintech.tams.exchange.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.exchange.common.bean.DynamicMonitorDTO;
import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.microservice.ServiceApplication;
import com.joyintech.tams.exchange.common.microservice.TradeServiceURL;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ExchangeSystemMonitorService
 * @Description TODO
 * @Author swwan
 * @Date 2020/8/26 10:03
 * @Version 1.0
 **/
@Service
public class ExchangeSystemMonitorService extends BaseService {

    public ResultBean queryExchangeMonitor(String param) {
        return restTemplate.getForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.querySystemMonitor + param, ResultBean.class);
    }

    public ResultBean queryCacheExchangeMonitor(String param) {
        return restTemplate.postForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.queryCache + param, null, ResultBean.class);
    }

    public ResultBean addCacheExchangeMonitor(String param, String list) {
        return restTemplate.postForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.addCache + param, list, ResultBean.class);
    }

    public ResultBean recoverCacheExchangeMonitor(String param) {
        return restTemplate.postForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.recoverCache + param, null, ResultBean.class);
    }

    public ResultBean clearCacheExchangeMonitor(String param) {
        return restTemplate.postForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.clearCache + param, null, ResultBean.class);
    }

    public ResultBean queryExchangeMonitorDynamic(DynamicMonitorDTO dto) {
        return restTemplate.postForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.querySystemMonitorDynamic, dto, ResultBean.class);
    }

    public ResultBean deleteExchangeCacheMonitor(String param, List<String> id) {
        return restTemplate.postForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.deleteCache + param, id, ResultBean.class);
    }

    public ResultBean queryExchangeCacheMonitor() {
        return restTemplate.getForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.queryCacheMonitor, ResultBean.class);
    }
}
