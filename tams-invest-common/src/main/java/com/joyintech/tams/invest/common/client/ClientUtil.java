/*
 * 文件名：ClientUtil.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年7月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.client;

import com.joyintech.framework.JoyinRestTemplate;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.dao.InterfRiskLogMapper;
import com.joyintech.tams.common.base.entity.InterfRiskLogWithBLOBs;
import com.joyintech.tams.invest.common.constantrds.BussinessConstantRds;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 接口客户端发送消息
 * 
 * @author zhangrui1
 * @version 1.0 2019年7月29日
 * @see ClientUtil
 * @since 1.0
 */
@Component
public class ClientUtil extends BaseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientUtil.class);

/*    @Autowired
    public void setRestTemplate(@Qualifier(value = "loadBalancedRestTemplate") RestTemplate template) {
        restTemplate = template;
    }*/
    /**
     * 设置 restTemplate
     *
     * @param restTemplate restTemplate
     */
    /*@Autowired(required = false)
    public void setRestTemplate(JoyinRestTemplate restTemplate) {
        ClientUtil.restTemplate = restTemplate;
    }*/

    private ClientUtil() {
        // private the constructor
    }

    @SuppressWarnings("unchecked")
    public static final ClientData sendRequestInf(String url, Map<String, Object> data,JoyinRestTemplate restTemplate) {
        ClientData rsp = new ClientData();
        rsp.convert((Map<String, Object>)(restTemplate.postForEntity(url, data,
                Map.class).getBody()));
        return rsp;
    }

    @SuppressWarnings("unchecked")
    public static final ClientData sendRequest(String url, Map<String, Object> data,JoyinRestTemplate restTemplate) {
        ClientData rsp = new ClientData();
        ClientData req = new ClientData();
        req.setHead(head());
        req.setBody(data);
        String seqStr = JsonUtils.object2Json(req);
        String errStr = "处理成功";
        LOGGER.info("请求报文【{}】", seqStr);
        try {
        	LOGGER.info("发送URL：{}", url);
			rsp.convert((Map<String, Object>)(restTemplate.postForEntity(url, req.convertToMap(),
			    Map.class).getBody()));
			if (!StringUtil.equals(rsp.getHeadField(BussinessConstantRds.STD400MGID),
                    BussinessConstantRds.AAAAAAA)) {
		        errStr = "应答反馈异常";
	        	LOGGER.info(errStr);
			}
		} catch (RestClientException e) {
	        errStr = "发送请求异常";
	        LOGGER.error(errStr, e);
		}
        String rspStr = JsonUtils.object2Json(rsp);
        LOGGER.info("应答报文【{}】", rspStr);

        // 登录日志表
        InterfRiskLogMapper infLog = SpringContextUtil.getBean(InterfRiskLogMapper.class);
        InterfRiskLogWithBLOBs record = new InterfRiskLogWithBLOBs();
        // UUID
        record.setUuid(IDGenerator.generate());
        // 交易接口
        record.setInfId("APPROVE001");
        // 系统代码
        record.setSysCode((String) data.get("sysCode"));
        // 请求报文
        record.setRequestData(seqStr);
        // 应答报文
        record.setResponseData(rspStr);
        record.setRtnMsg(errStr);
        // 返回码
        record.setRtnCode((String) rsp.getHeadField(BussinessConstantRds.STD400MGID));
        // 发送日期
        record.setTranDate((String) rsp.getHeadField(BussinessConstantRds.STD400RBDT));
        // 发送时间
        record.setTranTime((String) rsp.getHeadField(BussinessConstantRds.STD400RTIM));
        infLog.insert(record);
        return rsp;
    }

    public static final Map<String, Object> head() {
        Map<String, Object> head = new HashMap<>();
        // 设置 报文类型
        head.put(BussinessConstantRds.STDMSGTYPE, "0100");
        // 设置 多页查询标识
        head.put(BussinessConstantRds.STD400PGQF, BussinessDictCodeListRds.是否_否.getValue());
        // 设置 业务归属系统代码
        String sysCode = CacheUtil.getSysParam(BussinessConstantRds.RSK_SYSTEM_CODE);
        head.put(BussinessConstantRds.STD400BSYS, sysCode);
        // 设置 交易码
        head.put(BussinessConstantRds.STDPROCODE, "APPROVE001");
        // 设置 发起方系统代码
        head.put(BussinessConstantRds.STD400AQID, sysCode);
        // 设置 发起方流水号
        String sysDate = DateUtil.getSysDate();
        sysDate = StringUtil.remove(sysDate, "-");
        long time = System.currentTimeMillis();
        String tail = "00000" + time;
        head.put(BussinessConstantRds.STDTERMTRC, sysCode + sysDate + tail);
        // 设置 发起方系统日期
        String curTime = DateUtil.getCurrentDateTime();
        String curDt = StringUtil.substring(curTime, 0, 10);
        String curhms = StringUtil.substring(curTime, 11);
        head.put(BussinessConstantRds.STD400RBDT, StringUtil.remove(curDt, "-"));
        // 设置 发起方系统时间戳
        head.put(BussinessConstantRds.STD400RSTS, DateUtil.getCurrentDateTime());
        // 设置 发起方系统时间
        head.put(BussinessConstantRds.STD400RTIM, StringUtil.remove(curhms, ":"));
        // 设置 报文长度
        head.put(BussinessConstantRds.STD400IMTL, 0);
        return head;
    }
}
