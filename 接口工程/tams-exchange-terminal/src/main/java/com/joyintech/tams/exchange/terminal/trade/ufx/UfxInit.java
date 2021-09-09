package com.joyintech.tams.exchange.terminal.trade.ufx;

import com.hundsun.t2sdk.impl.client.T2Services;
import com.joyintech.tams.exchange.terminal.utils.AddressUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * create By MAXX on 2020/3/11
 */
@Component
@Configuration
@ConditionalOnProperty(value = "ufx.active", matchIfMissing = false, havingValue = "true")
public class UfxInit {
    private final static Logger logger = LoggerFactory.getLogger(UfxInit.class);

    private static Map<String, String> ufxMessageMap = new HashMap<>();

    private T2Services server = T2Services.getInstance();//T2实例化
    @Value("${ufx.opStation.cinfo}")
    private String cinfo;
    @Value("${ufx.opStation.cver}")
    private String cver;
    @Value("${ufx.opStation.ch}")
    private String ch;
    @Value("${ufx.opStation.sver}")
    private String sver;
    @Value("${ufx.opStation.ar}")
    private String ar;

    @Value("${ufx.path}")
    private String configPath;

    @Bean("op_station")
    public String getOpStation() {
        String extendInfo = cinfo + ";" + cver + ";" + ch + ";" + sver + ";" + AddressUtils.getLocalIp() + ";" + ar;
        return AddressUtils.genOpStation(extendInfo);
    }

    @PostConstruct
    public void init() {
        try {
            server.setT2sdkConfigString(configPath);// 配置文件路径
            server.init();
            server.start();
            logger.info("T2Services init start success");
        } catch (Exception e) {
            logger.info("T2Services init start failed. reason:{}", e);
        }
    }
}
