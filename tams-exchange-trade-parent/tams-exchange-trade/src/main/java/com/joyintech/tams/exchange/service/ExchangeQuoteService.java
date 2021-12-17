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
public class ExchangeQuoteService extends BaseService {


    public ResultBean queryQuoteTick(String param) {
        return restTemplate.getForObject(ServiceApplication.QUOTE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.queryQuoteTick + param, ResultBean.class);
    }

    public ResultBean queryIntegratedBulkQuote(String param) {
        return restTemplate.getForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.queryIntegratedBulkQuote + param, ResultBean.class);

    }

    public ResultBean queryIntegratedBulkCloseQuote(String param) {
        return restTemplate.getForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.queryIntegratedBulkCloseQuote + param, ResultBean.class);

    }

    public ResultBean queryIntegratedRepurchaseQuote(String param) {
        return restTemplate.getForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.queryIntegratedRepurchaseQuote + param, ResultBean.class);
    }

    public ResultBean queryIntegratedFixedIncomeQuote(String param) {
        return restTemplate.getForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.queryIntegratedFixedIncomeQuote + param, ResultBean.class);
    }
}
