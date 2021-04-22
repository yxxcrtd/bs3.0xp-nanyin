package com.joyintech.tams.exchange.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.microservice.ServiceApplication;
import com.joyintech.tams.exchange.common.microservice.TradeServiceURL;
import org.springframework.stereotype.Service;

/**
 * @ClassName ExchangeRecordService
 * @Description TODO
 * @Author swwan
 * @Date 2020/4/17 13:50
 * @Version 1.0
 **/
@Service
public class ExchangeRecordService extends BaseService {

    public ResultBean queryTradeAll(String param) {
        return restTemplate.getForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.queryTrade + param, ResultBean.class);
    }

    public ResultBean queryHistoryTrade(String param) {
        return restTemplate.getForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.queryHistoryTrade + param, ResultBean.class);
    }

    public ResultBean queryAllForPage(String param) {
        return restTemplate.getForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.queryTradePage + param, ResultBean.class);
    }
}
