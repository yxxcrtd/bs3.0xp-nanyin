package com.joyintech.interf.bankname.cfetsup.outbound.service;

import com.joyintech.framework.util.CacheUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CfetsQuoteClientService extends ClientService{
    /** 日志 */
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public String getServerLocation(String sendType) {
        return CacheUtil.getSysParam("cfets_quote_server_location");
    }
}
