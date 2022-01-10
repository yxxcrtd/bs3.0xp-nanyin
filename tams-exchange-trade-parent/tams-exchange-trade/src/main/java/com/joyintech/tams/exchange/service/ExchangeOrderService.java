package com.joyintech.tams.exchange.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.exchange.common.bean.*;
import com.joyintech.tams.exchange.common.db.domain.Algorithm;
import com.joyintech.tams.exchange.common.microservice.ServiceApplication;
import com.joyintech.tams.exchange.common.db.domain.IntegratedEntrust;
import com.joyintech.tams.exchange.common.microservice.TradeServiceURL;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

/**
 * @ClassName ExchangeOrderService
 * @Description TODO
 * @Author swwan
 * @Date 2020/4/17 13:45
 * @Version 1.0
 **/
@Service
public class ExchangeOrderService extends BaseService {

    public ResultBean queryAll(String param) {
        return restTemplate.getForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.queryOrder + param, ResultBean.class);
    }

    public ResultBean sendOrder(String param, HttpEntity<OrderPlaceBean> request) {
        return restTemplate.postForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.sendOrder + param, request, ResultBean.class);
    }

    public ResultBean sendIntegratedEntrust (String param, HttpEntity<IntegratedEntrust> request)  {
        return restTemplate.postForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.sendIntegratedEntrust+param,request,ResultBean.class);
    }

    public ResultBean cancelOrder (String param)  {
        return restTemplate.postForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.cancelOrder+param,null,ResultBean.class);
    }

    public ResultBean sendOrderWithReview(String param, HttpEntity<ReviewDto> request) {
        return restTemplate.postForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.sendOrderWithReview + param, request, ResultBean.class);
    }

    public ResultBean sendIntegratedEntrustWithReview(String param, HttpEntity<TrdExchangeDto> request) {
        return restTemplate.postForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.sendIntegratedEntrustWithReview + param, request, ResultBean.class);
    }

    public ResultBean cancelOrderWithRevoke(String param, HttpEntity<RevokeDto> request) {
        return restTemplate.postForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.cancelOrderWithRevoke + param, request, ResultBean.class);
    }

    public ResultBean createAlgorithm(String param, HttpEntity<Algorithm> request) {
        return restTemplate.postForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.sendAlgorithm + param, request, ResultBean.class);
    }
}
