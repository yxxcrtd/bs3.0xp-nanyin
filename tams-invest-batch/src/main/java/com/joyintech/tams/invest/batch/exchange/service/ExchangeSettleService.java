package com.joyintech.tams.invest.batch.exchange.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * @ClassName ExchangeSettleService
 * @Description TODO
 * @Author swwan
 * @Date 2020/9/3 11:47
 * @Version 1.0
 **/
@Service
@Scope("prototype")
public class ExchangeSettleService extends BaseService {

    public void execute() {
        simpleRestTemplate.postForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + "/joyin/settle", null, String.class);
    }
}
