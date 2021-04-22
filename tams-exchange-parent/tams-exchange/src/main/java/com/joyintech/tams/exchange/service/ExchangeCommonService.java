package com.joyintech.tams.exchange.service;

import com.google.common.base.Strings;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.constant.ErrorCode;
import com.joyintech.tams.exchange.common.microservice.ServiceApplication;
import com.joyintech.tams.exchange.common.microservice.TradeServiceURL;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeCommonService extends BaseService {

    public ResultBean queryFairTradeParam(String param) {
        return restTemplate.getForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.queryFairCode + param, ResultBean.class);
    }

    public ResultBean queryInstrument(String param, HttpEntity<List<String>> body) {
        return restTemplate.postForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.queryInstrument + param, body, ResultBean.class);
    }

    public ResultBean queryFuturesInstrument(String param, HttpEntity<List<String>> body) {
        return restTemplate.postForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.queryFuturesInstrument + param, body, ResultBean.class);
    }

    public ResultBean queryServiceNode(String nodeName) {
        String param = Strings.isNullOrEmpty(nodeName) ? "?node_name= " : "?node_name=" + nodeName;
        return restTemplate.getForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.queryServiceNode + param, ResultBean.class);
    }

    public ResultBean queryMaster() {
        try {
            return restTemplate.getForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.queryMaster, ResultBean.class);
        } catch (Exception e) {
            return new ResultBean(ErrorCode.EUREKA_SERVER_ERROR, e.getMessage());
        }
    }

    public ResultBean setDataServiceConfig(String param) {
        return restTemplate.postForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.changeRedisConfig + param, null, ResultBean.class);
    }

    public ResultBean getTradeDate(String tradeCalendarType, String CDATE, int NDay) {
        String param = "?calender_type=" + tradeCalendarType + "&current_date=" + CDATE + "&NDay=" + NDay;
        return restTemplate.postForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + TradeServiceURL.prefix + TradeServiceURL.getTradeDate + param, null, ResultBean.class);
    }

}
