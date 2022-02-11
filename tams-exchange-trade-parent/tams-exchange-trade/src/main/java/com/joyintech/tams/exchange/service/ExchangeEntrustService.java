package com.joyintech.tams.exchange.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.bean.RevokeDto;
import com.joyintech.tams.exchange.common.microservice.ServiceApplication;
import com.joyintech.tams.exchange.common.microservice.TradeServiceURL;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

/**
 * @ClassName ExchangeEntrustService
 * @Description TODO
 * @Author swwan
 * @Date 2020/4/17 13:40
 * @Version 1.0
 **/
@Service
public class ExchangeEntrustService extends BaseService {


    public ResultBean queryAll(String param) {
        return restTemplate.getForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.queryEntrust + param, ResultBean.class);
    }

    public ResultBean queryIntegratedEntrustAll(String param) {
        return restTemplate.getForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.queryIntegratedEntrust + param, ResultBean.class);
    }

    public ResultBean cancelEntrust (String param)  {
        return restTemplate.postForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.cancelEntrust + param, null, ResultBean.class);
    }

    public ResultBean cancelEntrustWithRevoke (String param, HttpEntity<RevokeDto> request)  {
        return restTemplate.postForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.cancelEntrustWithRevoke + param, request, ResultBean.class);
    }

    public ResultBean queryIntegratedQuote(String param) {
        return restTemplate.getForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.queryIntegratedQuote + param, ResultBean.class);
    }

    public ResultBean queryEnable(String param) {
        return restTemplate.getForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.queryEnable + param, ResultBean.class);
    }

    public ResultBean queryAllForPage(String param) {
        return restTemplate.getForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.queryEntrustPage + param, ResultBean.class);
    }

    public ResultBean queryIntegratedEntrustForPage(String param) {
        return restTemplate.getForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.queryIntegratedEntrustPage + param, ResultBean.class);
    }

    public ResultBean queryExceptionEntrust(String param) {
        return restTemplate.getForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.queryExceptionEntrust + param, ResultBean.class);
    }

    public ResultBean cancelExceptionEntrust(String param)  {
        return restTemplate.postForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.cancelExceptionEntrust + param, null, ResultBean.class);
    }
}
