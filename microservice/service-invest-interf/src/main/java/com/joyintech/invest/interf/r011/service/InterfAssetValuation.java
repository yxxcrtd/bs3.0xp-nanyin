package com.joyintech.invest.interf.r011.service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.invest.interf.business.BusinessUtil;
import com.joyintech.invest.interf.common.core.annotation.INF;
import com.joyintech.invest.interf.common.core.client.ClientData;
import com.joyintech.invest.interf.common.core.entity.InterfStatus;
import com.joyintech.invest.interf.common.core.service.InterfRecvBaseService;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 接口对账 ，【第三方系统调用接口后根据参数返回数据】
 * 
 * @author xkni
 * @version 1.0 2020年2月25日
 * @see InterfAssetValuation
 * @since 1.0
 */
@Service
@INF("R011")
public class InterfAssetValuation extends InterfRecvBaseService<String> {

    /**
     * 条件check {@inheritDoc}
     */
    @Override
    protected boolean checkWithCond(Map<String, Object> obj, ClientData response) {

        return false;
    }

    /**
     * 转换为标准输出报文 {@inheritDoc}
     */
    @Override
    protected ClientData resolveData(Map<String, Object> request, ClientData response) {
        Map<String, Object> body = new HashMap<>();

        // 从消息头中获取请求参数日期
        String platDate= DateUtil.format(new Date((Long)request.get("platDate")),
                DateUtil.DEFAULT_DATE_FORMAT_EN);
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("interfDate", platDate);
        String systemId="INV";//定义系统代码
        List<InterfStatus> logList = interfCommonService.getLogListByDate(paraMap);
        List<Map<String, Object>> mapList = new ArrayList<>();
        logList.forEach(log->{
            Map<String, Object> map = new HashMap<>();
            map.put("interfId",log.getInterfId());//接口id
            map.put("interfTermtrc",log.getInterfTermtrc());//接口流水号
            Map<String,Object> msgBodyMap= JsonUtils.stringToMap(log.getMsgBody());
            if(StringUtil.isNotEmpty(msgBodyMap)) {
                Map<String,Object> headMap=(Map<String,Object> )msgBodyMap.get("head");
                map.put("requestTime",headMap.get("REQUEST_TIME"));//报文时间戳REQUEST_TIME

                //交易流水核对：组合代码、交易编号、交易金额、交易份额、交易日期
                Map<String,Object> bodyMap=(Map<String,Object>) msgBodyMap.get("body");
                map.put("portfolioId",bodyMap.get("PORTFOLIO_ID"));//组合代码
                map.put("tradeId",bodyMap.get("TRADE_ID"));//交易编号
                map.put("tradeAmt",bodyMap.get("TRADE_AMT"));//交易金额
                map.put("shareAmt",bodyMap.get("SHARE_AMT"));//交易份额
                map.put("tradeDate",bodyMap.get("TRADE_DATE"));//交易日期
            }
            mapList.add(map);
        });

        body.put("platDate",platDate);//日期
        body.put("systemId",systemId);//系统代码
        body.put("count",logList.size());//总条数
        body.put("msgInfo",mapList);//消息体内容
        response.setBody(body);

        return response;
    }

    /**
     * 接口预留先决处理操作 {@inheritDoc}
     */
    @Override
    protected void beforeHandle(Map<String, Object> request, ClientData response) {

    }

    /**
     * 接口预留后置处理操作 {@inheritDoc}
     */
    @Override
    protected void afterHandle(Map<String, Object> request, ClientData response) {

    }
}
