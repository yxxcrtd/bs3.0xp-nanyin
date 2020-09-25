/*
 * 文件名：SocketBusinessHandler.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ZhangYangFei
 * 修改时间：2018年12月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.disclosure.common.socket.server;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.reportDeal.service.ReportDealService;

/**
 * Socket业务处理
 * 
 * @author ZhangYangFei
 * @version 1.0 2018年12月11日
 * @see SocketBusinessHandler
 * @since 1.0
 */
@Service(value="serviceSocketBusinessHandler")
public class SocketBusinessHandler {
    
    /**
     * 业务处理
     * 
     * @return 业务处理返回消息
     * @see
     * @since 1.0
     */
    public String doBusiness(String reciveStr) {
        Map<String, Object> jsonMap = JSON.parseObject(reciveStr);
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("Method", jsonMap.get("Method") == null ? "" : jsonMap.get("Method").toString());
        paraMap.put("data", jsonMap.get("data") == null ? "" : jsonMap.get("data").toString());
        ReportDealService reportDealService = (ReportDealService)SpringContextUtil.getBean("reportDealService");
        String rtnData = reportDealService.selectDate(paraMap);
        return rtnData;
    }

}
