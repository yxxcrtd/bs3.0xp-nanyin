package com.joyintech.tams.valuation.batch.unitfycaccountant.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;

@Service
@Component
public class ValBatchUnitfycAccountAntService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Value("${interf.hostname}")
    private String hostName;

    @Value("${interf.port}")
    private String port;

    @Value("${interf.contextPath}")
    private String contextPath;

    /**
     * 获取URL
     *
     * @return url
     * @see
     * @since 1.0
     */
    public String getUrl() {
        String url = "http://";
        url = url + hostName + ":" + port + contextPath + "/public" + InterfCommonConstant.接口消息入口;
        return url;
    }

}
