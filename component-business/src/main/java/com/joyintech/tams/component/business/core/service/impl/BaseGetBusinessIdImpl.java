package com.joyintech.tams.component.business.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.JoyinRestTemplate;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;

/**
 * 业务编号生成类
 * 
 * @author dingzhipeng
 */
@Component
public class BaseGetBusinessIdImpl implements BaseGetBusinessId {

    @Autowired
    private JoyinRestTemplate restTemplate;

    /**
     * 生成业务编号
     * 
     * @param parameter 共通业务类型
     * @return 业务编号
     * @throws Exception
     */
    @Override
    public String buildBussinessCode(BussinessCodeParameter parameter) {
        BussinessCodeParameter req = new BussinessCodeParameter();
        req.setMapInfo(parameter.initMapInfo());
        // 生成的业务编号
        return restTemplate.postForObject(
            ServiceApplication.COMMON_SERVICE_NAME + "/core/selectRule", req,
            String.class);
    }

}
