package com.joyintech.tams.exchange.common.workflow.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.exchange.common.bean.TrdExchangeEntrustDto;
import com.joyintech.tams.exchange.common.microservice.ServiceApplication;
import com.joyintech.tams.exchange.common.microservice.TradeServiceURL;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TrdExchangePage002Service
 * @Description TODO
 * @Author swwan
 * @Date 2020/7/31 11:43
 * @Version 1.0
 **/
@Service("TrdExchangePage004Service")
public class TrdExchangePage004Service extends BaseService<TrdExchangeEntrustDto> implements BaseProcessService<TrdExchangeEntrustDto> {

    private static final Logger logger = LoggerFactory.getLogger(TrdExchangePage004Service.class);

    @Override
    public void doComplete(TrdExchangeEntrustDto dto, OperationType type) {
        //将审批结果 通知给交易系统
        logger.info("通知交易系统撤审状态接口[{}]", ServiceApplication.EXCHANGE_SERVICE_NAME +
                        TradeServiceURL.prefix + TradeServiceURL.examineEntrustRevoke);

        Map<String, Object> paramMap = new HashMap<String, Object>();
        String eventId = dto.getEventId();
        paramMap.put("eventId", eventId);
        paramMap.put("approveResult", "1");
        logger.info("请求报文[{}]","eventId = " + eventId + ";approveResult=1");

        Map<String, Object> result = restTemplate.postForObject(ServiceApplication.EXCHANGE_SERVICE_NAME +
                        TradeServiceURL.prefix + TradeServiceURL.examineEntrustRevoke + "?eventId="+eventId+"&approveResult=1",
                paramMap, Map.class);
        logger.info("应答报文[{}]", "errorCode:"+(Integer)result.get("errorCode")+";errorMsg:"+(String)result.get("errorMsg"));
    }
}