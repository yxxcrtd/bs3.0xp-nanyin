/*
 * 文件名：Rsk998Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年9月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrdsinf.test.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.riskrdsinf.core.annotation.INF;
import com.joyintech.service.riskrdsinf.core.client.ClientData;
import com.joyintech.service.riskrdsinf.core.exception.InterfException;
import com.joyintech.service.riskrdsinf.core.service.InfBaseService;
import com.joyintech.tams.invest.common.riskinfrds.param.service.IndicatorParamService;

/**
 * 	清除Redis
 * 
 * @author zhangrui1
 * @version 1.0 2019年9月3日
 * @see Rsk998Service
 * @since 1.0
 */
@Service
@INF("RSK998")
public class Rsk998Service extends InfBaseService<String> {

    @Autowired
    private IndicatorParamService indicatorParamService;
    
    protected static final String PASSWORD = "Sombdy0517";

    /**
     * {@inheritDoc}
     */
    @Override
    protected Map<String, Map<String, String>> handle(ClientData request, ClientData response)
        throws InterfException {
    	Object password = request.getBodyField("password");
    	if (StringUtil.equals(PASSWORD, password)) {
    		indicatorParamService.flushDB();
    	}
    	else {
    		throw new InterfException("密码错误");
    	}
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean checkWithCond(String obj, ClientData response) {
        return false;
    }

}
