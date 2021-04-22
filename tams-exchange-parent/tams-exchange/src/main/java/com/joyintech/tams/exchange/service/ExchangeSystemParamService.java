package com.joyintech.tams.exchange.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.microservice.ServiceApplication;
import com.joyintech.tams.exchange.common.db.domain.ServiceNode;
import com.joyintech.tams.exchange.common.db.domain.Terminal;
import com.joyintech.tams.exchange.common.db.domain.TerminalMapping;
import com.joyintech.tams.exchange.common.microservice.TradeServiceURL;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeSystemParamService extends BaseService {

    public ResultBean queryServiceNode(String param) {
        return restTemplate.getForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.queryServiceNodeInfo + param, ResultBean.class);
    }

    public ResultBean addServiceNode(String param, HttpEntity<ServiceNode> request) {
        return restTemplate.postForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.addServiceNode + param, request, ResultBean.class);
    }

    public ResultBean updateServiceNode(String param, HttpEntity<ServiceNode> request) {
        return restTemplate.postForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.updateServiceNode + param, request, ResultBean.class);
    }


    public ResultBean deleteServiceNode(String param) {
        return restTemplate.postForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.deleteServiceNode + param, null, ResultBean.class);
    }


    public ResultBean queryTerminal(String param) {
        return restTemplate.getForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.queryTerminal + param, ResultBean.class);
    }


    public ResultBean addTerminal(String param, HttpEntity<Terminal> request) {
        return restTemplate.postForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.addTerminal + param, request, ResultBean.class);
    }


    public ResultBean updateTerminal(String param, HttpEntity<Terminal> request) {
        return restTemplate.postForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.updateTerminal + param, request, ResultBean.class);
    }


    public ResultBean deleteTerminal(String param) {
        return restTemplate.postForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.deleteTerminal + param, null, ResultBean.class);
    }


    public ResultBean queryTerminalMapping(String param) {
        return restTemplate.getForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.queryTerminalMapping + param, ResultBean.class);
    }

    public ResultBean addTerminalMapping(String param, HttpEntity<List<TerminalMapping>> request) {
        return restTemplate.postForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.addTerminalMapping + param, request, ResultBean.class);
    }

    public ResultBean updateTerminalMapping(String param, HttpEntity<TerminalMapping> request) {
        return restTemplate.postForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.updateTerminalMapping + param, request, ResultBean.class);
    }

    public ResultBean deleteTerminalMapping(String param) {
        return restTemplate.postForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.deleteTerminalMapping + param, null, ResultBean.class);
    }
}
