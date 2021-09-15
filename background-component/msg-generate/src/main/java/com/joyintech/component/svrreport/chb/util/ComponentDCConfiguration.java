/*
 * 文件名：ComponentDCConfiguration.java
 * 版权：Copyright 2020-2022 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2020年10月17日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */

package com.joyintech.component.svrreport.chb.util;

import com.joyintech.component.svrreport.chb.msgsend.dto.ExtFprDClient;
import ds.dc.api.app.DCconfig;
import ds.dc.api.app.DClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 组件中债直连客户端配置
 * @author 王波
 * @date 2020年10月17日
 * @see ComponentDCConfiguration
 * @since 1.0
 */
@Component
public class ComponentDCConfiguration {

    @Bean("dclientComponent")
    public DClient dcclinet() {
        return new DClient();
    }

    @Bean("dCconfigComponent")
    public DCconfig dcconfig() {
        return new DCconfig();
    }

    @Bean("fprDClientComponent")
    public ExtFprDClient extFprDClient(DClient dclientComponent) throws IllegalAccessException {
        ExtFprDClient client = new ExtFprDClient();
        client.setC(dclientComponent);

        // 登记银行代码和中债直连机路径，如果只有一个则不需要配置，默认使用"./AppCfg/DC/cfg"中的DIM_SERVER
        Map<String, String> dimServer = new HashMap<>();
        client.setDimServer(dimServer);
        return client;
    }
}
