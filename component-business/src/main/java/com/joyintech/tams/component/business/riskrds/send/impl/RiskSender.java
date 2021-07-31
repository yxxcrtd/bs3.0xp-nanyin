/*
 * 文件名：RiskSender.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年9月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.riskrds.send.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.tams.component.business.riskrds.constant.InterfUtil;
import com.joyintech.tams.component.business.riskrds.send.IRiskSender;
import com.joyintech.tams.invest.common.client.ClientData;
import com.joyintech.tams.invest.common.client.ClientUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.riskinfrds.util.service.EntrustCompleteService;


/**
 * 风控调用接口-Service
 * 
 * @author zhangrui1
 * @version 1.0 2020年9月22日
 * @see RiskSender
 * @since 1.0
 */
@Service
public class RiskSender implements IRiskSender {

    /**
     * {@inheritDoc}
     */
    @Override
    public ClientData clientSend(Map<String, Object> headParam, Map<String, Object> body) {
        ClientData data = new ClientData();
        data.setHead(headParam);
        data.setBody(body);
        return ClientUtil.sendRequestInf(
            ServiceApplication.RISK_INF_SERVICE_NAME + InterfUtil.RISK_INF_URL,
            data.convertToMap(), SpringContextUtil.getApplicationContext().getBean(EntrustCompleteService.class).getJoyinRestTemplate());
    }

}
