/*
 * 文件名：InterfRecvComponent.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2020年1月17日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */
 
package com.joyintech.invest.interf.common.core.service;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.invest.interf.common.core.client.ClientData;
import com.joyintech.invest.interf.common.core.contants.InterfColumns;
import com.joyintech.invest.interf.common.core.contants.InterfList;

@Component
public class InterfRecvComponent {
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * 
     * @description 从外系统接收数据
     * @param dataMap
     * @return
     * @see
     * @since 1.0
     */
    public Map<String, Object>  recv(Map<String, Object> dataMap) {
    	String resStr = JsonUtils.object2Json(dataMap);
        logger.info("请求报文:{}", resStr);
        
        // 1. 获取接口ID
        String interfId = (String)dataMap.get("interfId");

        // 1.1 若参数为空，则处理结束返回结果
        if (StringUtils.isBlank(interfId)) {
            return errMsg("参数异常【空的参数列表】");
        }
        
    
        // 2.根据接口ID取得接口模块实例进行事件分发
        InterfRecvBaseService<?> service = InterfList.getRecvBean(interfId);
        // 2.1 若接口模块不存在，则处理结束返回结果
        if (service == null) {
            return errMsg("接口模块异常【" + interfId + "对应的接口不存在】");
        }
       
        // 接口模块处理
        return service.execute(dataMap);
    	
    }
    
    /**
     * @Description errMsg
     * @param errMsg
     * @return
     * @see
     * @since 1.0
     */
    private Map<String, Object> errMsg(String errMsg) {
        ClientData data = new ClientData();
        data.setHeadField(InterfColumns.COM_RTNCODE.fieldName(), 1);
        data.setHeadField(InterfColumns.COM_RTNMSG.fieldName(), errMsg);
        return data.convertToMap();
    }
}
