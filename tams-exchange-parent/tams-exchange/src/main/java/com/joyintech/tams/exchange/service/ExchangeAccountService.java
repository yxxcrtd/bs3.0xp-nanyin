package com.joyintech.tams.exchange.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.db.domain.AccountRiskParameter;
import com.joyintech.tams.exchange.common.microservice.ServiceApplication;
import com.joyintech.tams.exchange.common.microservice.TradeServiceURL;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

/**
 * @ClassName ExchangeAccountService
 * @Description TODO
 * @Author swwan
 * @Date 2020/4/17 13:25
 * @Version 1.0
 **/
@Service
public class ExchangeAccountService extends BaseService {

    public ResultBean queryAccount(String param) {
        return restTemplate.getForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.queryAccount + param, ResultBean.class);
    }

    public ResultBean connectAccount(String param) {
        return restTemplate.postForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.connectAccount + param, null, ResultBean.class);
    }

    public ResultBean compareAccount(String param) {
        return restTemplate.postForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.compareAccount + param, null, ResultBean.class);
    }

    public ResultBean queryAccountFund(String param) {
        return restTemplate.getForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.queryAccountFund + param, ResultBean.class);
    }

    public ResultBean queryAccountRiskParam(String param) {
        return restTemplate.getForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.queryAccountRiskParam + param, ResultBean.class);
    }

    public ResultBean addAccountRiskParam(String param, HttpEntity<AccountRiskParameter> request) {
        return restTemplate.postForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.addAccountRiskParam + param, request, ResultBean.class);
    }

    public ResultBean updateAccountRiskParam(String param, HttpEntity<AccountRiskParameter> request) {
        return restTemplate.postForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.updateAccountRiskParam + param, request, ResultBean.class);
    }

    public ResultBean deleteAccountRiskParam(String param) {
        return restTemplate.postForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.deleteAccountRiskParam + param, null, ResultBean.class);
    }

    public ResultBean queryAccountAvailableStandardBonds(String param) {
        return restTemplate.getForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.queryAvailableStandardBond + param, ResultBean.class);

    }
}